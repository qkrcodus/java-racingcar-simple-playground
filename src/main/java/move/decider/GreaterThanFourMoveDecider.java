package move.decider;

public class GreaterThanFourMoveDecider implements MoveDecider {
    private static final int MOVE_THRESHOLD = 4;

    @Override
    public boolean canMove(int randomNumber){
        return isGreaterThanOrEqualToThreshold(randomNumber);
    }

    private boolean isGreaterThanOrEqualToThreshold(int randomNumber){
        return randomNumber >= MOVE_THRESHOLD;
    }
}
