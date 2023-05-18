package com.vich_mat.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Result {
    double coefficients[];

    double[] fi_x;
    double[] eps;
    double standardDeviation;
    String methodName;
}
