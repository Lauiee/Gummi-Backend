package myproject.Gummi.domain.dto.request;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class WeightSaveRequest {
    private double weight;
    private LocalDate calDate;
}
