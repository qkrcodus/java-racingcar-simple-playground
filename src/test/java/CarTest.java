import car.Car;
import car.generator.random.RandomGenerator;
import car.move.decider.MoveDecider;
import car.move.decider.ThresholdBaseMoveDecider;
import car.move.evaluator.GreaterThanOrEqualThresholdEvaluator;
import car.move.evaluator.ThresholdEvaluator;
import car.move.strategy.MoveStrategy;
import car.move.strategy.OneStepMoveStrategy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CarTest {

    @DisplayName("랜덤값이 조건을 만족할 경우 자동차는 1칸 전진한다")
    @Test
    void 움직일_수_있을_때_1칸_전진() {
        // given
        RandomGenerator fixedGenerator = () -> 7;
        ThresholdEvaluator evaluator=new GreaterThanOrEqualThresholdEvaluator(4);
        MoveDecider decider= new ThresholdBaseMoveDecider(evaluator);
        MoveStrategy strategy = new OneStepMoveStrategy(fixedGenerator, decider);
        Car car = new Car("car1", strategy);

        // when
        car.move();

        // then
        assertThat(car.getMovedDistance()).isEqualTo(1);
        assertThat(car.getName()).isEqualTo("car1");
    }


    @DisplayName("랜덤값이 조건을 만족하지 않을 경우 자동차는 전진하지 않는다")
    @Test
    void 움직일_수_없을_때_전진_하지_않음() {
        RandomGenerator fixedGenerator = () -> 2;
        ThresholdEvaluator evaluator=new GreaterThanOrEqualThresholdEvaluator(4);
        MoveDecider decider= new ThresholdBaseMoveDecider(evaluator);
        MoveStrategy strategy = new OneStepMoveStrategy(fixedGenerator, decider);
        Car car = new Car("car2", strategy);

        car.move();

        assertThat(car.getMovedDistance()).isEqualTo(0);
        assertThat(car.getName()).isEqualTo("car2");
    }

    @DisplayName("자동차는 여러 번 움직일 경우 이동 거리가 누적된다")
    @Test
    void 여러번_움직이면_누적됨() {
        RandomGenerator fixedGenerator = () -> 8;
        ThresholdEvaluator evaluator=new GreaterThanOrEqualThresholdEvaluator(4);
        MoveDecider decider= new ThresholdBaseMoveDecider(evaluator);
        MoveStrategy strategy = new OneStepMoveStrategy(fixedGenerator, decider);
        Car car = new Car("car3", strategy);

        car.move();
        car.move();
        car.move();

        assertThat(car.getMovedDistance()).isEqualTo(3);
        assertThat(car.getName()).isEqualTo("car3");
    }
}
