package org.example.iocontrollers;

import lombok.Getter;
import lombok.SneakyThrows;
import org.example.Snake;
import org.example.board.Board;
import org.example.board.Cell;
import org.example.position.Position;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleInputController {

    @Getter
    private final List<Snake> snakes = new ArrayList<>();
    @Getter
    public final Board board;

    @SneakyThrows
    public ConsoleInputController() {
        final Scanner scanner = new Scanner(new File("/home/jasieqb/Pulpit/reply_super_awasome_project/in.txt"));
//        final Scanner scanner = new Scanner(System.in);

        final int columns = scanner.nextInt();
        final int rows = scanner.nextInt();
        int snakesNumber = scanner.nextInt();

        List<Cell> cells = new ArrayList<>();

        for (int i = 0; i < snakesNumber; i++) {
            snakes.add(new Snake(scanner.nextInt()));
        }

        for (int j = 0; j < rows; j++) {
            for (int i = 0; i < columns; i++) {
                final char next = scanner.next().charAt(0);
                cells.add(new Cell(new Position(j, i), next));
            }
        }

//        System.out.println(columns + " " + rows);
        this.board = new Board(cells, columns, rows);
    }
}
