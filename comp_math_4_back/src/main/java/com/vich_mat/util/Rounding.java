package com.vich_mat.util;

import java.text.DecimalFormat;

public class Rounding {
    public static double round(double value) {
        return Math.round(value * 10000.0) / 10000.0;
    }

}
