package car.move.strategy;

import car.generator.random.RandomGenerator;
import car.move.decider.MoveDecider;

public class OneStepMoveStrategy implements MoveStrategy {
    private static final int STEP_SIZE = 1;

    private final RandomGenerator randomGenerator;
    private final MoveDecider moveDecider;

    public OneStepMoveStrategy(RandomGenerator randomGenerator, MoveDecider moveDecider) {
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
