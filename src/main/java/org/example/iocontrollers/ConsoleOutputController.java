package org.example.iocontrollers;

import lombok.RequiredArgsConstructor;
import org.example.MoveController;
import org.example.Snake;
import org.example.move.MoveData;
import org.example.move.MoveType;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class ConsoleOutputController {

    private final MoveController moves;

    public void print() {
        List<Snake> snakeList = moves.getSnakes();
        snakeList.forEach(snake -> {
            final String path = generateSnailPath(snake);
            System.out.printf(
                    "%d %d %s\n", snake.getStartingPosition().x(), snake.getStartingPosition().y(), path
            );
        });
    }

    private String generateSnailPath(Snake snake) {
        return snake.getBody().stream()
                .map(MoveData::getType)
                .filter(type -> type != MoveType.NONE)
                .map(MoveType::getSign)
                .map(sign -> sign + " ")
                .collect(Collectors.joining());
    }
}
