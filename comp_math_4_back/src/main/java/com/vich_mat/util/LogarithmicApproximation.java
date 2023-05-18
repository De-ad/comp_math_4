package com.vich_mat.util;

import com.vich_mat.entities.Dots;
import com.vich_mat.entities.Result;
import org.springframework.stereotype.Component;

import static com.vich_mat.util.Rounding.round;

@Component
public class LogarithmicApproximation {
    String name= "logarithmic approximation";
    private double[] phi;

    public Result calculate(Dots dots){
        Dots dots1 = new Dots(dots.lnX(), dots.getYArray(), dots.getN());
        LinearApproximation linearApproximation = new LinearApproximation();
        double[] coefficients = linearApproximation.calculateAndReturnOnlyCoefficients(dots1);
        double a = round(coefficients[0]);
        double b = round(coefficients[1]);
        fillPhi(a, b, dots.getXArray());
        double[] epsArray = dots.calculateEpsArray(phi);
        double standardDeviation = round(dots.calculateStandardDeviation(epsArray));
        double[] result = {a, b};
        return new Result(result, phi, epsArray, standardDeviation, name );
    }
    public void fillPhi(double a, double b, double[] xArray){
        phi = new double[xArray.length];
        for (int i = 0; i < xArray.length; i++){
            phi[i] = round(a * Math.log(xArray[i]) + b);
        }
    }

}
