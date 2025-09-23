package co.com.pragma.powerup.sqs.listener;

import co.com.pragma.powerup.model.report.request.RequestMessage;
import co.com.pragma.powerup.usecase.report.ReportUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import software.amazon.awssdk.services.sqs.model.Message;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.function.Function;

@Log4j2
@Service
@RequiredArgsConstructor
public class SQSProcessor implements Function<Message, Mono<Void>> {
    private final ReportUseCase myUseCase;
    private final ObjectMapper objectMapper;


    @Override
    public Mono<Void> apply(Message message) {
        try {
            log.info("Raw body: {}", message.body());

            RequestMessage msge = objectMapper.readValue(message.body(), RequestMessage.class);
            log.info("Recieved messagge from SQS: {}", msge);
            return myUseCase.saveReport(msge)
            .then();
        } catch (Exception e) {
            log.error("Error parsing message: {}", message.body(), e);
            return Mono.error(e);
        }
    }
}
