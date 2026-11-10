package com.mathwhiz.service;

public class MathService {
    public static int isPrime(int number) {
        if (number < 2) return 0;
        for (int i = 2; i <= number / i; i++) {
            if (number % i == 0) return 0;
        }
        return 1;
    }

    public static int calculateMod(int a, int b) {
        if (b == 0) throw new IllegalArgumentException("Divisor cannot be zero.");
        return a % b;
    }

    public static double calculateSin(double degree) {
        return Math.sin(Math.toRadians(degree));
    }

    public static double calculateCos(double degree) {
        return Math.cos(Math.toRadians(degree));
    }

    public static double calculateTan(double degree) {
        double radians = Math.toRadians(degree);
        if (Math.abs(Math.cos(radians)) < 1e-10) {
            throw new IllegalArgumentException("Tangent is undefined for this angle.");
        }
        return Math.tan(radians);
    }
}
