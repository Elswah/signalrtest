package com.sawah.signalrtest.utils;

import java.util.ArrayList;
import java.util.Collection;

public class IpvPredicate {


    public static <T> Collection<T> filter(Collection<T> target, IPvPredicateImp<T> predicate) {
        Collection<T> result = new ArrayList<T>();
        for (T element : target) {
            if (predicate.apply(element)) {
                result.add(element);
            }
        }

        return result;
    }

    @SuppressWarnings("unused")
    public static <T> T select(Collection<T> target, IPvPredicateImp<T> predicate) {
        T result = null;
        for (T element : target) {
            if (!predicate.apply(element))
                continue;
            result = element;
            break;
        }
        return result;
    }

    @SuppressWarnings("unused")
    public static <T> T select(Collection<T> target, IPvPredicateImp<T> predicate, T defaultValue) {
        T result = defaultValue;
        for (T element : target) {
            if (!predicate.apply(element))
                continue;
            result = element;
            break;
        }
        return result;
    }
}
