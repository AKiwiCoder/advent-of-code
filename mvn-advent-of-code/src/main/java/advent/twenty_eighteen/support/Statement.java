package advent.twenty_eighteen.support;

import java.util.Objects;

public class Statement {
    private final EMnemonic mnemonic;
    private final int a;
    private final int b;
    private final int c;

    public Statement(EMnemonic mnemonic, int a, int b, int c) {
        this.mnemonic = mnemonic;
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public EMnemonic getMnemonic() {
        return mnemonic;
    }

    public int getA() {
        return a;
    }

    public int getB() {
        return b;
    }

    public int getC() {
        return c;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Statement statement = (Statement) o;
        return a == statement.a &&
                b == statement.b &&
                c == statement.c &&
                mnemonic == statement.mnemonic;
    }

    @Override
    public int hashCode() {
        return Objects.hash(mnemonic, a, b, c);
    }

    @Override
    public String toString() {
        return "Statement{" +
                "mnemonic=" + mnemonic +
                ", a=" + a +
                ", b=" + b +
                ", c=" + c +
                '}';
    }
}
