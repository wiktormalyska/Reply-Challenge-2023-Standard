package org.example.move;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum MoveType {
    NONE('-'), LEFT('L'), RIGHT('R'), UP('U'), DOWN('D');

    @Getter
    private final char sign;
}
