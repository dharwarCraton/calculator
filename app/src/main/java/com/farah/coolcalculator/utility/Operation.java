package com.farah.coolcalculator.utility;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public abstract class Operation {

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({DIVIDE, ADD, MULTIPLY, SUBTRACT})
    public @interface Operator {}

    public static final int DIVIDE = 0;
    public static final int ADD = 1;
    public static final int MULTIPLY = 2;
    public static final int SUBTRACT = 3;

    @Operator
    public abstract int getOperaror();

    public abstract void setOperators(@Operator int operator);
}
