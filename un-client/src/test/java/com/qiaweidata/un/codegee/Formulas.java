//package com.qiaweidata.un.codegee;
//
//import java.util.regex.*;
//
//public class Formulas {
//    public static void main(String[] args) {
//        System.out.println(parseFormula("a - b/c"));
//        System.out.println(parseFormula("a - b * c"));
//        System.out.println(parseFormula("a - b / c"));
//        System.out.println(parseFormula("a - (b + c) * d"));
//    }
//    static double parseFormula(String formula) {
//        return new PatternBuilder()
//            .add(Pattern.compile("([-+]?\\d*\\.?\\d*)")).add(
//                Matcher.class, Pattern.CASE_INSENSITIVE).build()
//            .parse(formula, new Context()).get(0).getValue();
//    }
//    static class Context implements Pattern.MatchCallback {
//        private double value;
//        public void doMatch(int start, int end, Matcher matcher) {
//            value = parse(matcher.group(1));
//        }
//        public double getValue() {
//            return value;
//        }
//    }
//    static double parse(String s) {
//        return new PatternBuilder()
//            .add(Pattern.compile("[+-]?\\d*\\.?\\d*"))
//            .add(Double::parseDouble, Pattern.CASE_INSENSITIVE)
//            .build()
//            .parse(s, new Context()).get(0).getValue();
//    }
//}
