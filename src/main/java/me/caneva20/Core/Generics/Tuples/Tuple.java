package me.caneva20.Core.Generics.Tuples;

public class Tuple {
    public static <T1, T2> me.caneva20.Core.Generics.Tuples.T1.Tuple from(T1 t1, T2 t2) {
        return new me.caneva20.Core.Generics.Tuples.T1.Tuple<>(t1, t2);
    }

    public static <T1, T2, T3> me.caneva20.Core.Generics.Tuples.T2.Tuple from(T1 t1, T2 t2, T3 t3) {
        return new me.caneva20.Core.Generics.Tuples.T2.Tuple<>(t1, t2, t3);
    }

    public static <T1, T2, T3, T4> me.caneva20.Core.Generics.Tuples.T3.Tuple from(T1 t1, T2 t2, T3 t3, T4 t4) {
        return new me.caneva20.Core.Generics.Tuples.T3.Tuple<>(t1, t2, t3, t4);
    }

    public static <T1, T2, T3, T4, T5> me.caneva20.Core.Generics.Tuples.T4.Tuple from(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5) {
        return new me.caneva20.Core.Generics.Tuples.T4.Tuple<>(t1, t2, t3, t4, t5);
    }
}
