package move.evalutaor;

public class GreaterThanOrEqualThreshold implements ThresholdEvaluator {
    private final int threshold;

    public GreaterThanOrEqualThreshold(int threshold) {
        this.threshold = threshold;
    }
    @Override
    public boolean isSatisfied(int value){
        return value >= threshold;
    }
}
