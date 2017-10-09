package com.baislsl.decompiler.instruction;

import com.baislsl.decompiler.engine.Frame;
import com.baislsl.decompiler.structure.attribute.Code;

public class FCONST_0 extends ConstInstruction {
    @Override
    public Executable build(Code code, Frame frame) {
        this.value = "0.0f";
        return super.build(code, frame);
    }
}
