package com.vich_mat.util;

import com.vich_mat.entities.Dots;
import com.vich_mat.entities.Result;
import org.springframework.stereotype.Component;

import static com.vich_mat.util.Rounding.round;

@Component
public class ThreePolynomialApproximation {
    String name= "third degree polynomial approximation";
    private double[] phi;

    public Result calculate(Dots dots){
        double[][] a = new double[4][4];
        double [] b = new double[4];
        a[0][0] = dots.getN();
        a[0][1] = dots.SX();
        a[0][2] = dots.SXX();
        a[0][3] = dots.SXXX();
        b[0] = dots.SY();
        a[1][0] = dots.SX();
        a[1][1] = dots.SXX();
        a[1][2] = dots.SXXX();
        a[1][3] = dots.Sx4();
        b[1] = dots.SXY();
        a[2][0] = dots.SXX();
        a[2][1] = dots.SXXX();
        a[2][2] = dots.Sx4();
        a[2][3] = dots.Sx5();
        b[2] = dots.SXXY();
        a[3][0] = dots.SXXX();
        a[3][1] = dots.Sx4();
        a[3][2] = dots.Sx5();
        a[3][3] = dots.Sx6();
        b[3] = dots.SYX3();
        GaussMethod gaussMethod = new GaussMethod(a, b);
        double[] coefficients = gaussMethod.solve();
//        a3 a2 a1 a0
        fillPhi(coefficients[3], coefficients[2], coefficients[1], coefficients[0], dots.getN(), dots.getXArray());
        double[] epsArray = dots.calculateEpsArray(phi);
        double standardDeviation = round(dots.calculateStandardDeviation(epsArray));
        double[] result = {round(coefficients[3]), round(coefficients[2]), round(coefficients[1]), round(coefficients[0])};
        return new Result(result, phi, epsArray, standardDeviation, name );
    }

    public void fillPhi(double a3, double a2, double a1, double a0, int n, double[] xArray){
        phi = new double[n];
        for (int i = 0; i < n; i++){
            phi[i] = round(a0 + xArray[i] * a1 + xArray[i] * xArray[i] * a2 + Math.pow(xArray[i], 3) * a3);
        }
    }
}
