package com.baislsl.decompiler.structure.constantPool;

import com.baislsl.decompiler.DecompileException;
import com.baislsl.decompiler.Result;
import com.baislsl.decompiler.structure.Name;

public abstract class ConstantPool implements ConstantPoolBuilder, Description, Name {
    public final static int TAG_SIZE = 1;
    protected int tag;

    public ConstantPool(int tag) {
        this.tag = tag;
    }

    public static ConstantPoolBuilder getConstantPoolBuilder(int tag) throws DecompileException {
        switch (tag){
            case 7: return new ClassTag(tag);
            case 9: return new FieldrefTag(tag);
            case 10: return new MethodrefTag(tag);
            case 11: return new InterfaceMethodrefTag(tag);
            case 8: return new StringTag(tag);
            case 3: return new IntegerTag(tag);
            case 4: return new FloatTag(tag);
            case 5: return new LongTag(tag);
            case 6: return new DoubleTag(tag);
            case 12: return new NameAndTypeTag(tag);
            case 1: return new Utf8Tag(tag);
            case 15: return new MethodHandleTag(tag);
            case 16: return new MethodTypeTag(tag);
            case 18: return new InvokeDynamicTag(tag);
            default: throw new DecompileException(
                    String.format("tag number of %d not match when building constant pool", tag)
            );
        }
    }

    public int getTag() {
        return tag;
    }

    @Override
    public String name() throws DecompileException {
        return null;
    }

    public String getConstantPoolInfo(Result clazz)
            throws DecompileException {
        String[] cpDescription = this.description(clazz);
        StringBuilder ans = new StringBuilder();
        if (cpDescription.length <= 3) {
            for (String s : cpDescription) {
                ans.append(s);
                ans.append("  ");
            }
        } else {
            ans.append(cpDescription[0]);
            ans.append("  ");
            ans.append(cpDescription[1]);
            ans.append("  ");
            for (int i = 2; i < cpDescription.length; i++) {
                ans.append(cpDescription[i]);
                if (i != cpDescription.length - 1) ans.append(":");
            }
        }
        return ans.toString();
    }
}
