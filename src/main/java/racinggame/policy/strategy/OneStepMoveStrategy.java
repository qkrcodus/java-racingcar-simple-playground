package racinggame.policy.strategy;

import racinggame.policy.decider.MoveDecider;

public class OneStepMoveStrategy implements MoveStrategy {
    private static final int STEP_SIZE = 1;

    private final racinggame.policy.numbergenerator.RandomGenerator randomGenerator;
    private final MoveDecider moveDecider;

    public OneStepMoveStrategy(racinggame.policy.numbergenerator.RandomGenerator randomGenerator, MoveDecider moveDecider) {
        this.randomGenerator = randomGenerator;
        this.moveDecider = moveDecider;
    }
    @Override
    public int addStepSize(){
        int randomNumber=randomGenerator.generate();
        if(moveDecider.canMove(randomNumber)){
            return STEP_SIZE;
        }
        return 0;
    }
}
