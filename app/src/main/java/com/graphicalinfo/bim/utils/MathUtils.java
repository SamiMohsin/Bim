package com.graphicalinfo.bim.utils;

/**
 * Created by ayoob on 25/07/17.
 */

public class MathUtils {


    public static double add(double a,double b){
        double result = a + b;
        return result;
    }
    public static double less(double a,double b){
        double result = a - b;
        return result;
    }
    public static double multi(double a,double b){
        double  result = a * b;
        return result;
    }
    public static double qasmah(double a,double b){
        double  result = a / b;
        return result;
    }
    public static double tarbeh(double a){
        double result = a / a;
        return result;
    }
    public static double adoldrops(double weight){
        double result = (weight * 15.0) / 100;
        return result;
    }

    public static double antibiotic(double a){
        double result = a * 45.0;
        return result;
    }
}
