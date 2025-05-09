package racinggame;

import car.Car;
import car.generator.random.ZeroToNineRandomGenerator;
import car.move.decider.ThresholdBaseMoveDecider;
import car.move.evaluator.GreaterThanOrEqualThresholdEvaluator;
import car.move.strategy.MoveStrategy;
import car.move.strategy.OneStepMoveStrategy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
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
    public  void play(){
        for(int i=0;i<moveCount;i++){
            moveAllCars();
            printStatus();
        }
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
    private void printStatus() {
        for (Car car : cars) {
            System.out.println(car.getName() + " : " + "-".repeat(car.getMovedDistance()));
        }
        System.out.println();
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("경주할 자동차 이름을 입력하세요(이름은 쉼표(,)를 기준으로 구분).");
        String[] inputNames = scanner.nextLine().split(",");
        List<String> names = Arrays.stream(inputNames)
                .map(String::trim)
                .collect(Collectors.toList());

        System.out.println("시도할 회수는 몇회인가요?");
        int moveCount = scanner.nextInt();

        MoveStrategy strategy = new OneStepMoveStrategy(
                new ZeroToNineRandomGenerator(),
                new ThresholdBaseMoveDecider(new GreaterThanOrEqualThresholdEvaluator(4)));

        RacingGame game = new RacingGame(names,moveCount,strategy);
        System.out.println("\n실행 결과");
        game.play();

        List<String> winners = game.getWinners();
        System.out.println(String.join(", ", winners) + "가 최종 우승했습니다.");
    }

}
