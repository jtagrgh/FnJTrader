package Indicator;

@FunctionalInterface
public interface CmpFunc<A,B> {
    Boolean cmp(A left, B right);
}
