import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import racinggame.model.RacingGame;
import racinggame.policy.decider.MoveDecider;
import racinggame.policy.decider.ThresholdBaseMoveDecider;
import racinggame.policy.evaluator.GreaterThanOrEqualThresholdEvaluator;
import racinggame.policy.evaluator.ThresholdEvaluator;
import racinggame.policy.numbergenerator.RandomGenerator;
import racinggame.policy.strategy.MoveStrategy;
import racinggame.policy.strategy.OneStepMoveStrategy;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class RacingGameTest {
    @Test
    @DisplayName("0이하 값을 이동 횟수로 입력하면 표준 예외를 출력한다.")
    void invalidInputMoveCount(){
        RandomGenerator fixedGenerator = () -> 7;
        ThresholdEvaluator evaluator=new GreaterThanOrEqualThresholdEvaluator(4);
        MoveDecider decider= new ThresholdBaseMoveDecider(evaluator);
        MoveStrategy strategy = new OneStepMoveStrategy(fixedGenerator, decider);
        assertThatThrownBy(() -> new RacingGame(List.of("car1", "car2"), 0,strategy))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("moveCount는 0보다 큰 값이여야 합니다.");
    }

    @Test
    @DisplayName("공동 우승자 발생한 경우 -> car1 car2 ")
    void getWinners_multipleWinners() {
        RandomGenerator fixedGenerator = () -> 7;
        ThresholdEvaluator evaluator=new GreaterThanOrEqualThresholdEvaluator(4);
        MoveDecider decider= new ThresholdBaseMoveDecider(evaluator);
        MoveStrategy strategy = new OneStepMoveStrategy(fixedGenerator, decider);
        RacingGame game = new RacingGame(List.of("car1", "car2"), 1, strategy);
        game.moveOneTurn();
        List<String> winners = game.getWinners();
        assertThat(winners).containsExactlyInAnyOrder("car1", "car2");
    }

    @Test
    @DisplayName("랜덤값이 threshold 값 이상일 때 자동차가 잘 움직이는지 확인한다.")
    void carMovesWhenRandomValueIsAboveThreshold() {
        RandomGenerator fixedGenerator = () -> 7;
        ThresholdEvaluator evaluator=new GreaterThanOrEqualThresholdEvaluator(4);
        MoveDecider decider= new ThresholdBaseMoveDecider(evaluator);
        MoveStrategy strategy = new OneStepMoveStrategy(fixedGenerator, decider);

        RacingGame game = new RacingGame(List.of("neo"), 1, strategy);
        game.moveOneTurn();
        assertThat(game.getCars().get(0).getMovedDistance()).isEqualTo(1);
    }

    @Test
    @DisplayName("랜덤값이 threshold 값 초과일 때 자동차가 멈추는지 확인한다.")
    void carStopsWhenRandomValueIsBelowThreshold() {
        RandomGenerator fixedGenerator = () -> 2;
        ThresholdEvaluator evaluator=new GreaterThanOrEqualThresholdEvaluator(4);
        MoveDecider decider= new ThresholdBaseMoveDecider(evaluator);
        MoveStrategy strategy = new OneStepMoveStrategy(fixedGenerator, decider);

        RacingGame game = new RacingGame(List.of("car1"), 1, strategy);
        game.moveOneTurn();
        assertThat(game.getCars().get(0).getMovedDistance()).isEqualTo(0);
    }


}
