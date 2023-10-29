package racingcar.controller;

import java.util.List;
import racingcar.domain.Car;
import racingcar.domain.RacingCars;
import racingcar.views.InputView;
import racingcar.views.OutputView;

public class RacingCarGameController {

    private RacingCars racingCars;
    private List<Car> cars;
    private Integer movingCount;

    public RacingCarGameController() {
    }

    public void racingCarGameStart() {
        racingCarGameInit();

        racingCarGameRun();

        racingCarGameResult();
    }

    private void racingCarGameInit() {
        OutputView.racingCarOpeningComment();

        racingCarCreate();

        gameTryCountCreate();
    }

    private void racingCarCreate() {
        cars = InputView.getCarNames();
        racingCars = new RacingCars(cars);
    }

    private void gameTryCountCreate() {
        OutputView.tryCountInputComment();
        movingCount = InputView.getMovingCount();
    }

    private void racingCarGameRun() {
        OutputView.racingCarResultComment();

        for (int i = 0; i < movingCount; i++) {
            racingCars.MovingCars();
            racingCars.racingCarResultPrint();
        }
    }

    private void racingCarGameResult() {
        int maxPos = cars.stream()
                .mapToInt(Car::getMoveDistance)
                .max()
                .orElseGet(() -> 0);

        String[] winnerArr = cars.stream()
                .filter(car -> car.carEqualsMaxDistance(maxPos))
                .map(Car::getName)
                .toArray(String[]::new);

        OutputView.racingCarWinnerPrint(winnerArr);
    }
}
