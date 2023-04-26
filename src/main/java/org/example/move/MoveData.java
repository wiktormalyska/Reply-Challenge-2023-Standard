package org.example.move;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.example.position.Position;

@Getter
@RequiredArgsConstructor
public class MoveData {
    private final MoveType type;
    private final Position position;

    @Setter
    private boolean isWormhole = false;
    @Setter
    private Position wormholePosition;
}
