package move.strategy;

public class OneStepMoveStrategy implements MoveStrategy {
    private static final int STEP_SIZE = 1;

    @Override
    public int addStepSize(){
        return STEP_SIZE;
    }
}
