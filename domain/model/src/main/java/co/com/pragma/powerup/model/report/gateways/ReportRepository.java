package co.com.pragma.powerup.model.report.gateways;

import co.com.pragma.powerup.model.report.Report;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ReportRepository {
    Mono<Report> save(Report report);
    Mono<Report> getById(String id);
    Flux<Report> scanAll();
}
