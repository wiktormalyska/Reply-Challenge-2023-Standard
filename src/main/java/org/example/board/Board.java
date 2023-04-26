package org.example.board;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example.position.Position;

import java.util.List;

@RequiredArgsConstructor
public class Board {

    private final List<Cell> board;

    @Getter
    private final int column, row;

    public Cell getCellAt(int x, int y) {
        x = relative(x, row);
        y = relative(y, column);

        for (Cell cell : board) {
            final Position position = cell.getPosition();
            if (position.x() == x && position.y() == y) {
                return cell;
            }
        }
        return null;
    }

    private int relative(int val, int size) {
        if (val >= size) return val - size;
        else if (val < 0) return val + size;
        return val;
    }

    public void setOccupied(int x, int y) {
        getCellAt(x, y).setState(CellState.OCCUPIED);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        int column = 0;
        for (Cell cell : board) {
            if(cell.getPosition().x() != column) {
                builder.append('\n');
                column += 1;
            }
            builder.append(cell).append(" ");
        }
        return builder.toString();
    }
}

