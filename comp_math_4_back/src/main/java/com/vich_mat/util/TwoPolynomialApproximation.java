package com.vich_mat.util;

import com.vich_mat.entities.Dots;
import com.vich_mat.entities.Result;
import org.springframework.stereotype.Component;

import static com.vich_mat.util.Rounding.round;

@Component
public class TwoPolynomialApproximation {
    String name= "two degree polynomial approximation";
    private double[] phi;
    public Result calculate(Dots dots) {
        double[][] a = new double[3][3];
        double [] b = new double[3];
        a[0][0] = dots.getN();
        a[0][1] = dots.SX();
        a[0][2] = dots.SXX();
        b[0] = dots.SY();
        a[1][0] = dots.SX();
        a[1][1] = dots.SXX();
        a[1][2] = dots.SXXX();
        b[1] = dots.SXY();
        a[2][0] = dots.SXX();
        a[2][1] = dots.SXXX();
        a[2][2] = dots.Sx4();
        b[2] = dots.SXXY();
        GaussMethod gaussMethod = new GaussMethod(a, b);
        double[] coefficients = gaussMethod.solve();
//        a2 a1 a0
        fillPhi(coefficients[2], coefficients[1], coefficients[0], dots.getN(), dots.getXArray());
        double[] epsArray = dots.calculateEpsArray(phi);
        double standardDeviation = round(dots.calculateStandardDeviation(epsArray));
        double[] result = {round(coefficients[2]), round(coefficients[1]), round(coefficients[0])};
        return new Result(result, phi, epsArray, standardDeviation, name );
    }

    public void fillPhi(double a2, double a1, double a0, int n, double[] xArray){
        phi = new double[n];
        for (int i = 0; i < n; i++){
            phi[i] = round(a2* Math.pow(xArray[i],2) + a1 * xArray[i] + a0);
        }
    }
}
