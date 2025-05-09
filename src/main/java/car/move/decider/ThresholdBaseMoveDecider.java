package car.move.decider;

import car.move.evaluator.ThresholdEvaluator;

public class ThresholdBaseMoveDecider implements MoveDecider {
    private final ThresholdEvaluator evaluator;

    public ThresholdBaseMoveDecider(ThresholdEvaluator evaluator) {
        this.evaluator = evaluator;
    }

    @Override
    public boolean canMove(int randomNumber){
        return evaluator.isSatisfied(randomNumber);
    }
}
