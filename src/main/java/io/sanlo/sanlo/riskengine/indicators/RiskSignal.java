package io.sanlo.sanlo.riskengine.indicators;

public enum RiskSignal {
    PAYBACK_PERIOD(0.7,70),
    LTV_CAC_RATIO(0.3,30);

    private double riskScoreWeighting;
    private int maximumScore;

    private RiskSignal (double riskScoreWeighting, int maximumScore) {
        this.riskScoreWeighting = riskScoreWeighting;
        this.maximumScore = maximumScore;

    }

    public double getRiskScoreWeighting() {
        return riskScoreWeighting;
    }


    public int getMaximumScore() {
        return maximumScore;
    }
}
