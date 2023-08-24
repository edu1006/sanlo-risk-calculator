package io.sanlo.sanlo.riskengine.indicators;

import io.sanlo.sanlo.riskcalculator.parser.LtvCacRatioParser;
import io.sanlo.sanlo.riskcalculator.parser.PaybackIndicatorParser;
import io.sanlo.sanlo.riskcalculator.parser.RiskRatingIndicatorParser;
import io.sanlo.sanlo.riskengine.model.LTVCACRatioIndicator;
import io.sanlo.sanlo.riskengine.model.PaybackPeriodIndicator;
import io.sanlo.sanlo.riskengine.utils.NumberUtils;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RiskScoreService {

    @Autowired
    private PaybackIndicatorParser paybackIndicatorParser;
    @Autowired
    private LtvCacRatioParser ltvCacRatioParser;
    @Autowired
    private RiskRatingIndicatorParser riskRatingIndicatorParser;

    private List<PaybackPeriodIndicator> intervalsPaybackPeriod;
    private List<LTVCACRatioIndicator> intervalsLtvCACRatio;
    private List<RiskRating> riskRatings;


    private void loadIntervalsLtvCACRatio() {
        intervalsLtvCACRatio = ltvCacRatioParser.loadDataFromCsv();
    }

    @PostConstruct
    public void init() {
        loadIntervalsPayBack();
        loadIntervalsLtvCACRatio();
        loadRiskRating();

    }

    private void loadRiskRating() {
        this.riskRatings = riskRatingIndicatorParser.loadDataFromCsv();
    }

    private void loadIntervalsPayBack() {
        this.intervalsPaybackPeriod = paybackIndicatorParser.loadPaybackIndicator();
    }

    private int getPayBackScore (int days) {
        return intervalsPaybackPeriod.stream().filter( i -> NumberUtils.isNumberInRange(days, i.getMinDays(), i.getMaxDays())).findFirst().get().getValue();
    }

    private int getLTVCACRatioScore (double ratio) {
        return intervalsLtvCACRatio.stream().filter( i -> NumberUtils.isNumberInRange(ratio, i.getMinValue(), i.getMaxValue())).findFirst().get().getValue();
    }


    public double calculateScore(int payBackPeriod, double cacRatio){

        int scorePaybackPeriod = getPayBackScore(payBackPeriod);
        int scoreCacRatio = getLTVCACRatioScore(cacRatio);
        return (scorePaybackPeriod * RiskSignal.PAYBACK_PERIOD.getRiskScoreWeighting()) + (scoreCacRatio * RiskSignal.LTV_CAC_RATIO.getRiskScoreWeighting());
    }
    public RiskRating getRiskRating(int riskScore){
        for (RiskRating risk:riskRatings) {
            if (NumberUtils.isNumberInRange(riskScore, risk.getMinScore(), risk.getMaxScore())) {
                return risk;
            }

        }
        throw new IllegalArgumentException("invalid risk score");
    }
}
