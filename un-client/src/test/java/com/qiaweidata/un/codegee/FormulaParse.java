package com.qiaweidata.un.codegee;

import java.util.Scanner;
import java.util.regex.*;

public class FormulaParse {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        System.out.print("Addition: 4-2/1\nSubtraction: 3-2-1\nMultiplication: 2*2\nDivision: 2/2-1\n");
        String[] inputTokens = input.nextLine().split("\\s+");
        double result = 0.0;
        double a = 0.0;
        double b = 0.0;
        double sign = 1.0;
        if (inputTokens.length == 1) {
            try {
                a = Double.parseDouble(inputTokens[0]);
                result = a;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input");
            }
        } else if (inputTokens.length == 2) {
            try {
                a = Double.parseDouble(inputTokens[0]);
                b = Double.parseDouble(inputTokens[1]);
                if (b == 0.0) {
                    System.out.println("Division by zero error");
                } else {
                    result = a / b;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input");
            }
        } else {
            try {
                a = Double.parseDouble(inputTokens[0]);
                b = Double.parseDouble(inputTokens[2]);
                sign = Double.parseDouble(inputTokens[1]);
                if (b == 0.0) {
                    System.out.println("Division by zero error");
                } else {
                    result = (a * sign) / b;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input");
            }
        }
        System.out.println(result);
    }

    private static void extracted() {
        String formula = "10 - 2/1";
        System.out.println("Original formula: " + formula);
        String[] vars = Pattern.compile("\\D*").split(formula);
        String[] nums = Pattern.compile("\\d+").split(formula);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < vars.length; i++) {
            sb.append(nums[i]);
            sb.append(((i == vars.length - 1)? "" : " "));
            sb.append(vars[i]);
            sb.append(" + ");
        }
        formula = sb.toString();
        System.out.println("Formula with variables and numbers: " + formula);
        formula = formula.replaceAll("[^\\d\\.\\-^/]", "");
        System.out.println("Formula without numbers and variables: " + formula);
        double result = 0.0;
        try {
            result = Double.parseDouble(formula);
            System.out.println("Result: " + result);
        } catch (NumberFormatException e) {
            System.out.println("Error: " + e);
        }
    }
}
