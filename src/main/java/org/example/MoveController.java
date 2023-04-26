package org.example;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example.board.Board;
import org.example.board.Cell;
import org.example.board.CellState;
import org.example.move.MoveType;
import org.example.position.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RequiredArgsConstructor
@Getter
public class MoveController {
    private final Board board;
    private final List<Snake> snakes;

    private void parseBoard(List<Integer> values) {
        for (int i = 0; i < board.getColumn() / 3; i++) {
            for (int j = 0; j < board.getRow() / 3; j++) {
                int sum = 0;
                if (i * 3 < board.getColumn() && j * 3 < board.getRow()) {
                    for (int k = 0; k < 3; k++) {
                        for (int l = 0; l < 3; l++) {
                            if (board.getCellAt(i * 3 + k, j * 3 + l).getValue() >= 0) {
                                sum += board.getCellAt(i * 3 + k, j * 3 + l).getValue();
                            }
                        }
                    }
                } else {
                    for (int k = 0; k < 3 - board.getColumn() - i * 3; k++) {
                        for (int l = 0; l < 3 - board.getRow() - i * 3; l++) {
                            if (board.getCellAt(i * 3 + k, j * 3 + l).getState() != CellState.WORMHOLE) {
                                sum += board.getCellAt(i * 3 + k, j * 3 + l).getValue();
                            }
                        }
                    }
                }
                values.add(sum);
            }
        }
    }

    public void findBestStartingPoints() {
        List<Integer> values = new ArrayList<>();
//        parseBoard(values);

        for (Snake snake : snakes) {
//            int i = findMaxIndex(values);
//            int x = i / (board.getColumns() / 3) * 3;
//            int y = i % (board.getRows() / 3) * 3;
//            if (x < board.getColumns() && y < board.getRows()) {
//                if (board.getCellAt(x, y).getState() != CellState.WORMHOLE &&
//                        board.getCellAt(x, y).getState() == CellState.EMPTY) {
//                    snake.setStartingPosition(new Position(i / (board.getColumns() / 3) * 3, i % (board.getRows() / 3) * 3));
//                    board.setOccupied(x, y);
//                } else {
//
//                    while (true) {
//                        Random radom = new Random();
//                        x = radom.nextInt(board.getColumns());
//                        y = radom.nextInt(board.getRows());
//                        if (board.getCellAt(x, y).getState() != CellState.WORMHOLE &&
//                                board.getCellAt(x, y).getState() == CellState.EMPTY) {
//                            snake.setStartingPosition(new Position(i / (board.getColumns() / 3) * 3,
//                                    i % (board.getRows() / 3) * 3));
//                            board.setOccupied(x, y);
//                            break;
//                        }
//                    }
//
//                }
//            } else {
//                System.out.println("err: bad starting position");
//            }
//
//            values.set(i, 0);
//            System.out.println(x + " " + y);

            int x;
            int y;
            while (true) {
                Random random = new Random();
                x = random.nextInt(0, board.getRow());
                y = random.nextInt(0, board.getColumn());

                if (x < board.getRow() && y < board.getColumn()) {
//                    System.out.println(x+" "+y);
                    if (!board.getCellAt(x, y).isOccupied()) {
                        snake.setStartingPosition(new Position(x, y));

                        board.setOccupied(x, y);
                        break;
                    }

                }
            }
        }
    }

    private int findMaxIndex(List<Integer> values) {
        int positon = 0;
        int max_value = values.get(0);
        for (int i = 1; i < values.size(); i++) {
            if (values.get(1) > max_value) {
                max_value = values.get(1);
                positon = i;
            }
        }
        return positon;
    }

    public boolean isGameOver() {
        for (Snake snake : snakes) {
            if (snake.canMove()) {
                return false;
            }
        }
        return true;
    }

    public void moveSnakes() {
        for (Snake snake : snakes) {
            if (!snake.canMove())
                continue;
            var nextPosition = findBestMove(snake);
            if (nextPosition == null) {
                snake.isAlive = false;

                continue;
            }

            MoveType moveType = getMoveType(snake.getCurrentPosition(), nextPosition);
            snake.move(board.getCellAt(nextPosition.x(), nextPosition.y()), moveType);
            board.setOccupied(nextPosition.x(), nextPosition.y());
        }
//        for (Snake snake : snakes) {
//            for(int i = 0; i < snake.getMaxLength(); i++){
//                if (!snake.canMove())
//                    break;
//                var nextPosition = findBestMove(snake);
//
//                if (nextPosition == null) {
//                    snake.isAlive = false;
//                    continue;
//                }
//                MoveType moveType = getMoveType(snake.getCurrentPosition(), nextPosition);
//                snake.move(board.getCellAt(nextPosition.x(), nextPosition.y()), moveType);
//                board.setOccupied(nextPosition.x(), nextPosition.y());
//            }
//        }
    }

    private MoveType getMoveType(Position currentPosition, Position nextPosition) {
        if (currentPosition.y() < nextPosition.y()) {
            return MoveType.RIGHT;
        } else if (currentPosition.x() > nextPosition.x()) {
            return MoveType.DOWN;
        } else if (currentPosition.x() < nextPosition.x()) {
            return MoveType.UP;
        }
//        else if (currentPosition.y() < nextPosition.y())
        return MoveType.LEFT;
    }


    private Position findBestMove(Snake snake) {
        List<Position> ring = getRingForSnake(snake);
        return findBestPostionInRing(ring);
    }

    private Position findBestPostionInRing(List<Position> ring) {
        int max = 0;
        Position bestPosition = null;
        for (Position position : ring) {
            int value = board.getCellAt(position.x(), position.y()).getValue();
            if (value > max) {
                max = value;
                bestPosition = position;
            }
        }
        return bestPosition;
    }

    private List<Position> getRingForSnake(Snake snake) {
        List<Cell> ring = new ArrayList<>();
        Position current = snake.getCurrentPosition();
        int x = current.x();
        int y = current.y();

        ring.add(board.getCellAt(x - 1, y));
        ring.add(board.getCellAt(x + 1, y));
        ring.add(board.getCellAt(x, y + 1));
        ring.add(board.getCellAt(x, y - 1));

//        System.out.println(ring.size());
//        for (Cell cell : ring) {
//            System.out.println(cell.toString());
//        }

        return ring.stream()
                .filter(cell -> !cell.isOccupied())
                .map(Cell::getPosition)
                .toList();
    }

//    private List<Position> getRingForSnake(Snake snake) {
//        List<Position> ring = new ArrayList<>();
//        Position startingPosition = snake.getStartingPosition();
//        int zero_x = startingPosition.x();
//        int zero_y = startingPosition.y();
//        for (int i = -1; i < 1; i++) {
//            for (int j = -1; j < 1; j++) {
//                if (i == 0 && j == 0)
//                    continue;
//                int new_x = zero_x + i;
//                int new_y = zero_y + j;
//
//
//
//            }
//        }
//        return null;
//    }

    public void run() {
        findBestStartingPoints();
        while (!isGameOver()) {
            moveSnakes();
        }
    }
}
