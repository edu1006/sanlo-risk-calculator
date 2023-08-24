package io.sanlo.sanlo.riskengine.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaybackPeriodIndicator {
    private Integer minDays;
    private Integer maxDays;
    private Integer value;
}
