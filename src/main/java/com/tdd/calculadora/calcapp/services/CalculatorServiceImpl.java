package com.tdd.calculadora.calcapp.services;

import java.text.NumberFormat;
import java.text.ParseException;

import org.springframework.stereotype.Service;

@Service
public class CalculatorServiceImpl implements CalculatorService {

    @Override
    public Integer sumaInt(int num1, int num2) {
        return num1 + num2;
    }

    @Override
    public Integer restaInt(int num1, int num2) {
        return num1 - num2;
    }

    @Override
    public Integer sumatorioArrInt(int[] arr) {
        int val = 0;
        for (int i = 0; i < arr.length; i++) {
            val += arr[i];
        }
        return val;
    }

    @Override
    public String sumaStrings(String val1, String val2) {
        try {
            Number num1;
            Number num2;
            num1 = NumberFormat.getInstance().parse(val1);
            num2 = NumberFormat.getInstance().parse(val2);
            return "" + (num1.doubleValue() + num2.doubleValue());
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }

}
