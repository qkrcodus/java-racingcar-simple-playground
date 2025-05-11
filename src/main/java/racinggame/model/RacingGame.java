package racinggame.model;
import racinggame.policy.strategy.MoveStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RacingGame {
    private final List<Car> cars;
    private final int moveCount;

    public RacingGame(List<String> names, int moveCount, MoveStrategy moveStrategy) {
        this.cars = createCars(names,moveStrategy);
        this.moveCount = moveCount;
    }

    private void moveAllCars( ){
        for (Car car : cars) {
            car.move();
        }
    }

    public void moveOneTurn() {
        moveAllCars();
    }

    public List<Car> getCars() {
        return cars;
    }

    public List<String> getWinners(){
        int maxDistance=findMaxDistance();
        return cars.stream().filter(car->car.getMovedDistance()==maxDistance).map(Car::getName).collect(Collectors.toList());
    }

    private List<Car> createCars(List<String> names, MoveStrategy strategy) {
        List<Car> cars = new ArrayList<>();
        for(String name : names){
            cars.add(new Car(name,strategy));
        }
        return cars;
    }
    private int findMaxDistance(){
        int max=0;
        for (Car car : cars) {
            max=Math.max(max,car.getMovedDistance());
        }
        return max;
    }
}
