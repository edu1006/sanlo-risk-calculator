package io.sanlo.sanlo.riskengine.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LTVCACRatioIndicator {
    private double minValue;
    private double MaxValue;
    private int value;
}
