package me.caneva20.Core.Util;


import me.caneva20.Core.Generics.Functions.F1.Func;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class CollectionUtil {
    public static <T> List<T> select(List<T> list, Func<T, T> selector) {
        ArrayList<T> selected = new ArrayList<>();

        if (list == null) {
            return selected;
        }

        for (T t : list) {
            selected.add(selector.run(t));
        }

        return selected;
    }

    public static <T> List<T> select(T[] list, Func<T, T> selector) {
        ArrayList<T> selected = new ArrayList<>();

        if (list == null) {
            return selected;
        }

        for (T t : list) {
            selected.add(selector.run(t));
        }

        return selected;
    }

    public static <T> List<T> select(Set<T> list, Func<T, T> selector) {
        ArrayList<T> selected = new ArrayList<>();

        if (list == null) {
            return selected;
        }

        for (T t : list) {
            selected.add(selector.run(t));
        }

        return selected;
    }

    public static <T> int count(List<T> list, Func<Boolean, T> selector) {
        if (list == null || list.size() == 0) {
            return 0;
        }

        int count = 0;

        for (T t : list) {
            if (selector.run(t)) {
                count++;
            }
        }

        return count;
    }

    public static <T> int count(T[] arr, Func<Boolean, T> selector) {
        return count(select(arr, t -> t), selector);
    }

    public static <T> int count(Set<T> set, Func<Boolean, T> selector) {
        return count(select(set, t -> t), selector);
    }

    public static <T> ArrayList<T> asList(T[] arr) {
        return new ArrayList<>(Arrays.asList(arr));
    }

    public static <T> ArrayList<T> asList(Set<T> set) {
        return new ArrayList<>(set);
    }

    public static <T> T first(List<T> list, Func<Boolean, T> selector) {
        for (T t : list) {
            if (selector.run(t)) {
                return t;
            }
        }

        return null;
    }

    public static <T> T first(Set<T> set, Func<Boolean, T> selector) {
        return first(new ArrayList<>(set), selector);
    }
}
