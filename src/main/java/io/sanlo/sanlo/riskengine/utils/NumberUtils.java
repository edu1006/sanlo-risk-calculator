package io.sanlo.sanlo.riskengine.utils;

public class NumberUtils {

    public static boolean isNumberInRange(double number, double lowerBound, double upperBound) {
        return (lowerBound <= number && number <= upperBound);
    }

    public static boolean isNumberInRange(int number, int lowerBound, int upperBound) {
        return (lowerBound <= number && number <= upperBound);
    }

}
