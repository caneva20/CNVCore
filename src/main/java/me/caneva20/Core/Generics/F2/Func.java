package me.caneva20.Core.Generics.F2;

public interface Func<TReturn, T1, T2> {
    TReturn run(T1 t1, T2 t2);
}