package com.farah.coolcalculator.utility;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public abstract class Operation {

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({ADD, SUBTRACT, DIVIDE, MULTIPLY, EQUAL_TO})
    public @interface Operator {}

    public static final int EQUAL_TO = 0;
    public static final int DIVIDE = 1;
    public static final int ADD = 2;
    public static final int MULTIPLY = 3;
    public static final int SUBTRACT = 4;

    @Operator
    public abstract int getOperaror();

    public abstract void setOperators(@Operator int operator);
}
