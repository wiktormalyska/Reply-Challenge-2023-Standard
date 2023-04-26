package org.example;

import org.example.board.Board;
import org.example.iocontrollers.ConsoleOutputController;
import org.example.iocontrollers.ConsoleInputController;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        var inputController = new ConsoleInputController();
        Board board = inputController.getBoard();
        List<Snake> snakes = inputController.getSnakes();
        MoveController moveController = new MoveController(board, snakes);
        moveController.run();
        ConsoleOutputController output = new ConsoleOutputController(moveController);
        output.print();
    }
}