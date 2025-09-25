package co.com.pragma.powerup.usecase.report;

import co.com.pragma.powerup.model.report.Report;
import co.com.pragma.powerup.model.report.gateways.ReportRepository;
import co.com.pragma.powerup.model.report.request.RequestMessage;
import co.com.pragma.powerup.model.report.utils.Constants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static org.assertj.core.api.Assertions.assertThat;

import static org.assertj.core.api.AssertionsForClassTypes.tuple;
import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;



class ReportUseCaseTest {

    private ReportRepository reportRepository;
    private ReportUseCase reportUseCase;

    @BeforeEach
    void setUp() {
        reportRepository = mock(ReportRepository.class);
        reportUseCase = new ReportUseCase(reportRepository);
    }

    @Test
    void saveReport_shouldUpdateExistingReports() {
        // Arrange
        RequestMessage request = new RequestMessage(10, 5); // statusCount=5, amount=10

        Report existingCount = new Report(Constants.COUNT_REPORT_ID, 3.0);
        Report existingAmount = new Report(Constants.AMOUNT_REPORT_ID, 20.0);

        when(reportRepository.getById(Constants.COUNT_REPORT_ID))
                .thenReturn(Mono.just(existingCount));
        when(reportRepository.getById(Constants.AMOUNT_REPORT_ID))
                .thenReturn(Mono.just(existingAmount));

        when(reportRepository.save(any(Report.class))).thenReturn(Mono.empty());

        // Act & Assert
        StepVerifier.create(reportUseCase.saveReport(request))
                .verifyComplete();

        // Verify that the save was called with updated values
        ArgumentCaptor<Report> captor = ArgumentCaptor.forClass(Report.class);
        verify(reportRepository, times(2)).save(captor.capture());

        assertThat(captor.getAllValues())
                .extracting(Report::getMetrica, Report::getValor)
                .containsExactlyInAnyOrder(
                        tuple(Constants.COUNT_REPORT_ID, 8.0),   // 3 + 5
                        tuple(Constants.AMOUNT_REPORT_ID, 30.0) // 20 + 10
                );
    }

    @Test
    void saveReport_shouldCreateNewReportsWhenNotExist() {
        // Arrange
        RequestMessage request = new RequestMessage(50, 2);

        when(reportRepository.getById(any()))
                .thenReturn(Mono.empty()); // No existing reports

        when(reportRepository.save(any(Report.class))).thenReturn(Mono.empty());

        // Act & Assert
        StepVerifier.create(reportUseCase.saveReport(request))
                .verifyComplete();

        // Verify saves
        ArgumentCaptor<Report> captor = ArgumentCaptor.forClass(Report.class);
        verify(reportRepository, times(2)).save(captor.capture());

        assertThat(captor.getAllValues())
                .extracting(Report::getMetrica, Report::getValor)
                .containsExactlyInAnyOrder(
                        tuple(Constants.COUNT_REPORT_ID, 2.0),  // nuevo
                        tuple(Constants.AMOUNT_REPORT_ID, 50.0) // nuevo
                );
    }

    @Test
    void getReport_shouldReturnAllReports() {
        // Arrange
        Report r1 = new Report(Constants.COUNT_REPORT_ID, 100.0);
        Report r2 = new Report(Constants.AMOUNT_REPORT_ID, 200.0);

        when(reportRepository.scanAll())
                .thenReturn(Flux.just(r1, r2));

        // Act & Assert
        StepVerifier.create(reportUseCase.getReport())
                .expectNext(r1)
                .expectNext(r2)
                .verifyComplete();
    }
    @Test
    void constructorShouldThrowException() throws Exception {
        Constructor<Constants> constructor = Constants.class.getDeclaredConstructor();
        constructor.setAccessible(true); // forzar acceso al constructor privado

        InvocationTargetException exception = assertThrows(
                InvocationTargetException.class,
                constructor::newInstance
        );

        assertTrue(exception.getCause() instanceof IllegalStateException);
        assertEquals("Utility class", exception.getCause().getMessage());
    }
}