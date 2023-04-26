package org.example;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example.board.Cell;
import org.example.move.MoveData;
import org.example.move.MoveType;
import org.example.position.Position;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class Snake {
    @Getter
    private final int maxLength;
    boolean isAlive = true;
    private final List<MoveData> body = new ArrayList<>();

    public void setStartingPosition(Position position) {
        body.add(new MoveData(MoveType.NONE, position));
    }

    public void move(Cell cell, MoveType type) {
        MoveData data = new MoveData(type, cell.getPosition());
        body.add(data);
    }

    public Position getCurrentPosition() {
        return body.get(body.size() - 1).getPosition();
    }

    public boolean canMove() {
        return body.size() < maxLength && isAlive;
    }

    public Position getStartingPosition() {
        return body.stream()
                .filter(data -> data.getType()==MoveType.NONE)
                .map(MoveData::getPosition)
                .findFirst().orElseThrow();
    }

    public List<MoveData> getBody() {
        return body;
    }
}
