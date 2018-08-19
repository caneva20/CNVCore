package me.caneva20.Core.Generics.Tuples.T2;

public class Tuple<T1, T2, T3> {
    private T1 item1;
    private T2 item2;
    private T3 item3;

    public Tuple(T1 item1, T2 item2, T3 item3) {
        this.item1 = item1;
        this.item2 = item2;
        this.item3 = item3;
    }

    public T1 item1() {
        return item1;
    }

    public T2 item2() {
        return item2;
    }

    public T3 item3() {
        return item3;
    }
}
