package racinggame.controller;

import racinggame.model.RacingGame;
import racinggame.policy.decider.ThresholdBaseMoveDecider;
import racinggame.policy.evaluator.GreaterThanOrEqualThresholdEvaluator;
import racinggame.policy.strategy.MoveStrategy;
import racinggame.policy.strategy.OneStepMoveStrategy;
import racinggame.view.InputView;
import racinggame.view.OutputView;

import java.util.List;

public class RacingGameController {
    public void run(){
        List<String> names= InputView.readCarNames();
        int moveCount = InputView.readMoveCount();

        MoveStrategy strategy = new OneStepMoveStrategy(
                new racinggame.policy.numbergenerator.ZeroToNineRandomGenerator(),
                new ThresholdBaseMoveDecider(new GreaterThanOrEqualThresholdEvaluator(4)));

        RacingGame game = new RacingGame(names,moveCount,strategy);

        for (int i = 0; i < moveCount; i++) {
            game.moveOneTurn();
            OutputView.printResult(game.getCars());
        }

        List<String> winners = game.getWinners();
        OutputView.printWinner(winners);
    }
}
