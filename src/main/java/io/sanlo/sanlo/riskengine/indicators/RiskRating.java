package io.sanlo.sanlo.riskengine.indicators;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RiskRating {
    private String rating;
    private Integer minScore;
    private Integer maxScore;
    private String description;
}
