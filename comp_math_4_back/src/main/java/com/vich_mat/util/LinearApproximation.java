package com.vich_mat.util;

import com.vich_mat.entities.Dots;
import com.vich_mat.entities.Result;
import org.springframework.stereotype.Component;

import static com.vich_mat.util.Rounding.round;

@Component
public class LinearApproximation {
    String name= "linear approximation";
    private double[] xArray;
    private double[] yArray;
    private double[] phi;

    public Result calculate(Dots dots) {
        this.xArray = dots.getXArray();
        this.yArray = dots.getYArray();
        double delta = dots.SXX() * dots.getN() - dots.SX() * dots.SX();
        double delta_1 = dots.SXY() * dots.getN() - dots.SX() * dots.SY();
        double delta_2 = dots.SXX() * dots.SY() - dots.SX() * dots.SXY();
        double a = round(delta_1/delta);
        double b = round(delta_2/delta);
        double[] temp = {a,b};
        fillPhi(a,b, xArray);
        double[] epsArray = dots.calculateEpsArray(phi);
        double standardDeviation = round(dots.calculateStandardDeviation(epsArray));
        double correlationCoefficient = round(solveCorrelationCoefficient(dots));
        String nameWithCoeff = name + " with correlation coefficient = " + correlationCoefficient;
        return new Result(temp, phi, epsArray, standardDeviation, nameWithCoeff);
    }

    private void fillPhi(double a, double b, double[] xArray){
        phi = new double[xArray.length];
        for (int i = 0; i < xArray.length; i++){
            phi[i] = round(a * xArray[i] + b);
        }
    }

    public double solveCorrelationCoefficient(Dots dots){
        double numerator = 0;
        double denomerator = 0;
        double sumX = 0;
        double x_mid = 0;
        double y_mid = 0;
        double sumY = 0;
        for (int i = 0; i < xArray.length; i++){
            x_mid += xArray[i];
            y_mid += yArray[i];
        }
        x_mid = x_mid/dots.getN();
        y_mid = y_mid/dots.getN();

        for (int i = 0; i < xArray.length; i ++){
            numerator +=  (xArray[i] - x_mid) * (yArray[i] - y_mid);
            sumX += Math.pow(xArray[i] - x_mid,2 );
            sumY +=  Math.pow(yArray[i] - y_mid,2);
        }
        denomerator = Math.sqrt(sumX * sumY);
        return numerator/denomerator;
    }

    public double[] calculateAndReturnOnlyCoefficients(Dots dots){
        double delta = dots.SXX() * dots.getN() - dots.SX() * dots.SX();
        double delta_1 = dots.SXY() * dots.getN() - dots.SX() * dots.SY();
        double delta_2 = dots.SXX() * dots.SY() - dots.SX() * dots.SXY();
        double a = delta_1/delta;
        double b = delta_2/delta;
        double[] temp = {a,b};
        return temp;
    }
}
