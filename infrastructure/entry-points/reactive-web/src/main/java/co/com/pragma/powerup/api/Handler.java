package co.com.pragma.powerup.api;

import co.com.pragma.powerup.model.report.Report;
import co.com.pragma.powerup.model.report.response.ReportResponse;
import co.com.pragma.powerup.model.report.utils.Constants;
import co.com.pragma.powerup.usecase.report.ReportUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.ErrorResponse;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import reactor.core.publisher.Mono;

@Log4j2
@Component
@RequiredArgsConstructor
public class Handler {
private  final ReportUseCase reportUseCase;



    @Operation(
        summary = Constants.SUMMARY_GET_REPORT,
        description =Constants.DESCRIPTION_GET_REPORT
        ,
        responses = {
            @ApiResponse(
            responseCode = Constants.CODE_200,
            description = Constants.RESPONSE_REPORT_LISTED,
            content = @Content(
                schema = @Schema(implementation = ReportResponse.class),
                examples = {
                    @ExampleObject(
                        name = Constants.EXAMPLE_REPORT_LISTED_NAME,
                        value = Constants.EXAMPLE_REPORT_LISTED_VALUE
                    )
                }
            )),
            @ApiResponse(
                responseCode = Constants.CODE_500,
                description = Constants.RESPONSE_INTERNAL_ERROR_GET,
                content = @Content(
                    schema = @Schema(implementation = ErrorResponse.class),
                    examples = {
                        @ExampleObject(
                            name = Constants.EXAMPLE_SERVER_ERROR_NAME_GET,
                            value = Constants.EXAMPLE_SERVER_ERROR_VALUE_GET
                        )
                    }
                )
            )
        }
    )
    @PreAuthorize(Constants.ROLE_ADMIN)
    public Mono<ServerResponse> getReport(ServerRequest request) {
        log.info(Constants.REPORT_RECEIVED);

        return reportUseCase.getReport()
                .collectList()
                .map(reports -> {
                    ReportResponse<Report> response = new ReportResponse<>();
                    response.setReports(reports);
                    return response;
                })
                .flatMap(response -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(response))
                .doOnSuccess(r -> log.info(Constants.GENERATE_REPORT_SUCCESSFUL))
                .doOnError(error -> log.error(Constants.GENERATE_REPORT_ERROR, error));
    }

}
