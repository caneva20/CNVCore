package me.caneva20.Core.Generics.Tuples.T1;

public class Tuple<T1, T2> {
    private T1 item1;
    private T2 item2;

    public Tuple(T1 item1, T2 item2) {
        this.item1 = item1;
        this.item2 = item2;
    }

    public T1 item1() {
        return item1;
    }

    public T2 item2() {
        return item2;
    }
}
