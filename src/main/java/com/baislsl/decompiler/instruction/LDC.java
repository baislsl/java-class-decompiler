package com.baislsl.decompiler.instruction;

public class LDC extends ldcInstruction {
    @Override
    protected int getIndex() {
        return get1u();
    }
}
