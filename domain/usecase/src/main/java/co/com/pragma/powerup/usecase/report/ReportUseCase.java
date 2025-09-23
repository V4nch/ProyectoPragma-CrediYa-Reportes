package co.com.pragma.powerup.usecase.report;

import co.com.pragma.powerup.model.report.Report;
import co.com.pragma.powerup.model.report.gateways.ReportRepository;
import co.com.pragma.powerup.model.report.request.RequestMessage;
import co.com.pragma.powerup.model.report.utils.Constants;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class ReportUseCase {
private final ReportRepository reportRepository;

    public Mono<Void> saveReport(RequestMessage request) {


        Mono<Void> updateCount = reportRepository.getById(Constants.COUNT_REPORT_ID)
                .defaultIfEmpty(new Report(Constants.COUNT_REPORT_ID, 0.0))
                .map(current -> current.getValor() + request.getStatusCount())
                .flatMap(newCount -> {
                    Report report = new Report(Constants.COUNT_REPORT_ID, newCount);
                    return reportRepository.save(report);
                })
                .then();

        Mono<Void> updateAmount = reportRepository.getById(Constants.AMOUNT_REPORT_ID)
                .defaultIfEmpty(new Report(Constants.AMOUNT_REPORT_ID, 0.0))
                .map(current -> current.getValor() + request.getAmount())
                .flatMap(newAmount -> {
                    Report report = new Report(Constants.AMOUNT_REPORT_ID, newAmount);
                    return reportRepository.save(report);
                })
                .then();

        return Mono.when(updateCount, updateAmount).then();
    }

    public Flux<Report> getReport (){
        return reportRepository.scanAll();
    }
}
