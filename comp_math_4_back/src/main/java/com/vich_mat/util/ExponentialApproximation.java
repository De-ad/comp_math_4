package com.vich_mat.util;

import com.vich_mat.entities.Dots;
import com.vich_mat.entities.Result;
import org.springframework.stereotype.Component;

import static com.vich_mat.util.Rounding.round;

@Component
public class ExponentialApproximation {
    String name= "exponential approximation";
    private double[] phi;


    public Result calculate(Dots dots){
        LinearApproximation linearApproximation = new LinearApproximation();
        Dots dots1 = new Dots(dots.getXArray(), dots.lnY(), dots.getN());
        double[] coefficients = linearApproximation.calculateAndReturnOnlyCoefficients(dots1);
        double a = round(Math.pow(Math.E, coefficients[1]));
        double b = round(coefficients[0]);
        fillPhi(a, b, dots.getXArray());
        double[] epsArray = dots.calculateEpsArray(phi);
        double standardDeviation = round(dots.calculateStandardDeviation(epsArray));
        double[] arrayAB = {a,b};
        return new Result(arrayAB, phi, epsArray, standardDeviation, name );
    }

    public void fillPhi(double a, double b, double[] xArray){
        phi = new double[xArray.length];
        for (int i = 0; i < xArray.length; i++){
            phi[i] = round(a * Math.pow(Math.E, (b*xArray[i])));
        }
    }
}
