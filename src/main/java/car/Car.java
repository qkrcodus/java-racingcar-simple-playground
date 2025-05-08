package car;

import move.strategy.MoveStrategy;

public class Car {
    private final String name;
    private int movedDistance=0;
    private final MoveStrategy moveStrategy;

    public Car(String name,MoveStrategy moveStrategy) {
        this.name=name;
        this.moveStrategy=moveStrategy;
    }

    public void move(){
        int distance=moveStrategy.addStepSize();
        movedDistance+=distance;
    }

    public int getMovedDistance() {
        return movedDistance;
    }
    public String getName() {
        return name;
    }
}
