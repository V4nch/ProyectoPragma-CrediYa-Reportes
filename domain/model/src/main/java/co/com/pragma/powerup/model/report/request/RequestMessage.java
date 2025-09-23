package co.com.pragma.powerup.model.report.request;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class RequestMessage {
    private double amount;
    private int statusCount;
}
