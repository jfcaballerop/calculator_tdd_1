package com.tdd.calculadora.calcapp.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class CalcServicesTest {

    @Autowired
    private CalculatorService calcService = new CalculatorServiceImpl();
    // private CalculatorServiceImpl calcService = new CalculatorServiceImpl();

    @Test
    public void sumaEnteros() {
        int num1 = 1;
        int num2 = 97;
        assertEquals(98, calcService.sumaInt(num1, num2));
    }

    @Test
    public void restaEnteros() {
        int num1 = 1;
        int num2 = 97;
        assertEquals(-96, calcService.restaInt(num1, num2));

    }

    @Test
    public void sumaTotalArray() {
        int[] arr = { 1, 2, 3 };

        assertEquals(6, calcService.sumatorioArrInt(arr));

    }

    @Test
    public void sumaStrings() {
        String val1 = "34";
        String val2 = "36";
        assertEquals("70.0", calcService.sumaStrings(val1, val2));

    }

    @Test
    public void sumaStringsNumber() {
        String val1 = "35,5";
        String val2 = "35,5";
        assertEquals("71.0", calcService.sumaStrings(val1, val2));

    }

}
