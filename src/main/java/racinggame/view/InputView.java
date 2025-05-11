package racinggame.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {
    // scanner는 공유자원
    private static final Scanner scanner = new Scanner(System.in);

    public static List<String> readCarNames() {
        System.out.println("경주할 자동차 이름을 입력하세요(이름은 쉼표(,)를 기준으로 구분).");
        String[] inputNames = scanner.nextLine().split(",");
        List<String> names = Arrays.stream(inputNames)
                .map(String::trim)
                .collect(Collectors.toList());
        return names;
    }

    public static int readMoveCount() {
        System.out.println("시도할 회수는 몇회인가요?");
        int moveCount = scanner.nextInt();
        if (moveCount <= 0) {
            throw new IllegalArgumentException("moveCount must be positive");
        }
        return moveCount;
    }
}
