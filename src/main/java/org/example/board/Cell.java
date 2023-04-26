package org.example.board;

import lombok.Getter;
import lombok.Setter;
import org.example.position.Position;

@Getter
public class Cell {

    private final Position position;
    private final int value;

    @Setter
    private CellState state = CellState.EMPTY;

    public Cell(Position position, char number) {
        this.position = position;
        if (number == '*') {
            this.value = -1;
            this.state = CellState.WORMHOLE;
        } else {
            this.value = Integer.parseInt(String.valueOf(number));
        }
    }

    public boolean isWormhole() {
        return state == CellState.WORMHOLE;
    }

    public boolean isOccupied() {
        return state != CellState.EMPTY;
    }

    @Override
    public String toString() {
        return String.format("[%d:%d]{%d}", position.x(), position.y(), value);
    }
}
