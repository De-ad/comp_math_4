package com.vich_mat.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;

import static com.vich_mat.util.Rounding.round;

@Getter
@Setter
@AllArgsConstructor
public class Dots {
    private double[] xArray;
    private double[] yArray;
    private int n;
    
    public double[] lnX(){
        return Arrays.stream(xArray).map(num -> Math.log(num)).toArray();
    }

    public double[] lnY(){
        return  Arrays.stream(yArray).map(num -> Math.log(num)).toArray();
    }
    public double SX(){
        return Arrays.stream(xArray).sum();
    }

    public double SXX() {
        return Arrays.stream(xArray).map(num -> num*num).sum();
    }


    public double SY(){
        return Arrays.stream(yArray).sum();
    }
    public double SXY(){
        double[] temp = new double[xArray.length];
        for(int i = 0; i < xArray.length ; i++){
            temp[i] = xArray[i] * yArray[i];
        }
        return Arrays.stream(temp).sum();
    }

    public double[] calculateEpsArray(double[] phiArray){
        double[] temp = new double[n];
        for (int i = 0; i < n; i ++){
            temp[i] = round(phiArray[i] - yArray[i]);
        }
        return temp;
    }

    public double calculateStandardDeviation(double[] epsArray){
        double sum = 0;
        for (int i = 0; i < n; i++){
            sum += Math.pow(epsArray[i],2);
        }
        sum = sum/n;
        return Math.sqrt(sum);
    }
    public double SXXX(){
        return Arrays.stream(xArray).map(num -> num*num*num).sum();
    }

    public double Sx4(){
        return Arrays.stream(xArray).map(num -> num*num*num*num).sum();
    }
    
    public double Sx5(){
        return Arrays.stream(xArray).map(num -> Math.pow(num, 5)).sum();
    }
    public double Sx6(){
        return Arrays.stream(xArray).map(num -> Math.pow(num, 6)).sum();
    }
    public double SXXY(){
        double[] result = new double[xArray.length];
        double[] temp = Arrays.stream(xArray).map(num -> num*num).toArray();
        for (int i = 0; i < xArray.length ; i++){
            result[i] = temp[i] * yArray[i];
        }
        return Arrays.stream(result).sum();
    }

    public double SYX3(){
        double[] result = new double[xArray.length];
        double[] temp = Arrays.stream(xArray).map(num -> num*num*num).toArray();
        for (int i = 0; i < xArray.length ; i++){
            result[i] = temp[i] * yArray[i];
        }
        return Arrays.stream(result).sum();
    }

}
