package co.com.pragma.powerup.model.report.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class ReportResponse<T> {
    List<T> reports;
}
