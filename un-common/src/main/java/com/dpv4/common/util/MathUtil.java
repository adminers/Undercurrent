package com.dpv4.common.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public class MathUtil {

    private static final Random RANDOM = new Random();
    
    private MathUtil() {
    }

    public static double round(double value, int places) {
        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public static int max(int... values) {
        return Arrays.stream(values).max().orElse(Integer.MIN_VALUE);
    }

    public static int min(int... values) {
        return Arrays.stream(values).min().orElse(Integer.MAX_VALUE);
    }

    public static double average(double... values) {
        if (values.length == 0) {
            return 0;
        }
        return Arrays.stream(values).average().orElse(0);
    }

    public static long sum(long... values) {
        return Arrays.stream(values).sum();
    }

    public static boolean isPrime(int n) {
        if (n <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static long factorial(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Factorial not defined for negative numbers");
        }
        long result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }

    public static double percentage(double part, double total) {
        if (total == 0) {
            return 0;
        }
        return (part / total) * 100;
    }

    public static int randomInt(int min, int max) {
        return RANDOM.nextInt((max - min) + 1) + min;
    }

    public static double randomDouble(double min, double max) {
        return min + (max - min) * RANDOM.nextDouble();
    }

    public static boolean isEven(int n) {
        return n % 2 == 0;
    }

    public static boolean isOdd(int n) {
        return n % 2 != 0;
    }

    public static double degToRad(double deg) {
        return deg * Math.PI / 180;
    }

    public static double radToDeg(double rad) {
        return rad * 180 / Math.PI;
    }

    public static long gcd(long a, long b) {
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    public static long lcm(long a, long b) {
        if (a == 0 || b == 0) {
            return 0;
        }
        return Math.abs(a * b) / gcd(a, b);
    }
}
