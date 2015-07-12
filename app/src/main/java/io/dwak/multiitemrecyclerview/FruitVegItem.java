package io.dwak.multiitemrecyclerview;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class FruitVegItem<T> {
    @Retention(RetentionPolicy.SOURCE)
    @IntDef({FRUIT, VEGETABLE})
    public @interface ViewType {
    }
    public static final int FRUIT = 0;
    public static final int VEGETABLE = 1;

    T object;
    @ViewType int viewType;

    public FruitVegItem(T object, int viewType) {
        this.object = object;
        this.viewType = viewType;
    }
}
