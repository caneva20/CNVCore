package me.caneva20.Core.Generics.Tuples.T4;

public class Tuple<T1, T2, T3, T4, T5> {
    private T1 item1;
    private T2 item2;
    private T3 item3;
    private T4 item4;
    private T5 item5;

    public Tuple(T1 item1, T2 item2, T3 item3, T4 item4, T5 item5) {
        this.item1 = item1;
        this.item2 = item2;
        this.item3 = item3;
        this.item4 = item4;
        this.item5 = item5;
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

    public T4 item4() {
        return item4;
    }

    public T5 item5() {
        return item5;
    }
}
