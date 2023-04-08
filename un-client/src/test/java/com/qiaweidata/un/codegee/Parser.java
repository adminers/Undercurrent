//package com.qiaweidata.un.codegee;
//
//public class Parser {
//    private static final int LEFT = 0, RIGHT = 1, MUL = 2, DIV = 3, ADD = 4, SUB = 5;
//
//    private static class Expr {
//        int type;
//        Expr left, right;
//        double value;
//    }
//    private static Expr parse(String s) {
//        Expr e = parseExpr(s);
//        if (e.type == RIGHT) {
//            return e;
//        } else {
//            return new Expr() {
//                {
//                    type = RIGHT;
//                    left = e;
//                }
//            };
//        }
//    }
//    private static Expr parseExpr(String s) {
//        Expr e1 = parseTerm(s);
//        if (e1.type == RIGHT) {
//            return e1;
//        } else {
//            return new Expr() {
//                {
//                    type = RIGHT;
//                    left = e1;
//                }
//            };
//        }
//    }
//    private static Expr parseTerm(String s) {
//        Expr e1 = parseFactor(s);
//        if (e1.type == RIGHT) {
//            return e1;
//        } else {
//            return new Expr() {
//                {
//                    type = RIGHT;
//                    left = e1;
//                }
//            };
//        }
//    }
//    private static Expr parseFactor(String s) {
//        Expr e = null;
//        if (Character.isDigit(s.charAt(0))) {
//            int n = 0;
//            while (Character.isDigit(s.charAt(0))) {
//                n = 10 * n + (s.charAt(0) - '0');
//            }
//            return new Expr() {
//                {
//                    type = NUMBER;
//                    value = n;
//                }
//            };
//        }
//        else if (s.charAt(0) == '(') {
//            s = s.substring(1);
//            e = parseExpr(s);
//            if (s.charAt(0) != ')') {
//                throw new RuntimeException("Missing )");
//            }
//            s = s.substring(1);
//        }
//        else if (s.charAt(0) == '\'') {
//            s = s.substring(1);
//            if (s.length() < 2) {
//                throw new RuntimeException("Missing character value");
//            }
//            e = new Expr() {
//                {
//                    type = CHARACTER;
//                    value = s.charAt(0);
//                }
//            };
//        }
//        else if (s.charAt(0) == '{') {
//            s = s.substring(1);
//            Expr head = parseExpr(s);
//            s = s.substring(1);
//            if (s.charAt(0) != '}') {
//                throw new RuntimeException("Missing }");
//            }
//            s = s.substring(1);
//            if (s.length() == 0) {
//                throw new RuntimeException("Missing list contents");
//            }
//            Expr tail = head;
//            while (s.charAt(0)!= '}') {
//                Expr e2 = parseExpr(s);
//                tail = new Expr() {
//                    {
//                        type = LIST;
//                        value = e2;
//                    }
//                };
//                s = s.substring(1);
//            }
//            e = new Expr() {
//                {
//                    type = LIST;
//                    value = tail;
//                }
//            };
//        }
//        else {
//            e = new Expr() {
//                {
//                    type = INTEGER;
//                    value = Long.parseLong(s);
//                }
//            };
//        }
//        return e;
//    }
//
//    /**
//     * Convert a string to a Lisp expression.
//     * @param s the string to parse.
//     * @return the parsed expression.
//     */
//    public static Expr parseExpr(String s) {
//        Expr e = parseTerm(s);
//        if (e.type == LIST) {
//            return e.value;
//        } else {
//            return new Expr() {
//                {
//                    type = STRING;
//                    value = s;
//                }
//            };
//        }
//    }
//
//    /**
//     * Convert a Lisp expression to a string.
//     * @param e the expression to parse.
//     * @return the parsed expression.
//     */
//    public static String toString(Expr e) {
//        if (e.type == LIST) {
//            StringBuilder sb = new StringBuilder("(");
//            Expr head = e.value;
//            Expr tail = head;
//            while (tail.type!= LIST) {
//                sb.append(toString(head));
//                head = tail.value;
//                tail = head;
//            }
//            Expr head2 = head;
//            tail = head2;
//            while (tail.type == LIST) {
//                head2 = tail.value;
//                sb.append(toString(head2));
//                head2 = head2.value;
//                tail = head2;
//            }
//            sb.append(")");
//            return sb.toString();
//        }
//        else if (e.type == STRING) {
//            return e.value;
//        } else {
//            return String.valueOf(e.value);
//        }
//    }
//
//    /**
//     * Evaluate an arithmetic expression.
//     * @param e the expression to evaluate.
//     * @return the value of the expression.
//     */
//    public static double eval(Expr e) {
//        return eval(e, 0.0);
//    }
//
//    /**
//     * Evaluate an arithmetic expression.
//     * @param e the expression to evaluate.
//     * @param d the value to use for decimal places.
//     * @return the value of the expression.
//     */
//    public static double eval(Expr e, double d) {
//        return eval(e, d, 0);
//    }
//
//    /**
//     * Evaluate an arithmetic expression.
//     * @param e the expression to evaluate.
//     * @param d the value to use for decimal places.
//     * @param n the number of decimal places to display.
//     * @return the value of the expression.
//     */
//    public static double eval(Expr e, double d, int n) {
//        if (e.type == NUMBER) {
//            return e.value;
//        }
//        if (e.type == VARIABLE) {
//            return variables.get(e.value);
//        }
//        if (e.type == FUNCTION) {
//            Expr arg = e.args[0];
//            double[] args = new double[e.args.length - 1];
//            for (int i = 1; i < e.args.length; i++)
//                args[i - 1] = eval(arg, d, n);
//            return eval(arg, d, n).evaluate(args);
//        }
//        double[] args = new double[e.args.length];
//        for (int i = 0; i < e.args.length; i++)
//            args[i] = eval(e.args[i], d, n);
//        switch (e.type) {
//            case ADD:
//                return eval(args[0], d, n) + eval(args[1], d, n);
//            case SUBTRACT:
//                return eval(args[0], d, n) - eval(args[1], d, n);
//            case MULTIPLY:
//                return eval(args[0], d, n) * eval(args[1], d, n);
//            case DIVIDE:
//                return eval(args[0], d, n) / eval(args[1], d, n);
//            case POWER:
//                return Math.pow(eval(args[0], d, n), eval(args[1], d, n));
//            case MOD:
//                return (int) (eval(args[0], d, n) % (int) (eval(args[1], d, n)));
//            case LT:
//                return eval(args[0], d, n) < eval(args[1], d, n);
//            case GT:
//                return eval(args[0], d, n) > eval(args[1], d, n);
//            case LE:
//                return eval(args[0], d, n) <= eval(args[1], d, n);
//            case GE:
//                return eval(args[0], d, n) >= eval(args[1], d, n);
//            case EQUAL:
//                return eval(args[0], d, n) == eval(args[1], d, n);
//            case NOT_EQUAL:
//                return eval(args[0], d, n)!= eval(args[1], d, n);
//            case AND:
//                return eval(args[0], d, n) && eval(args[1], d, n);
//            case OR:
//                return eval(args[0], d, n) || eval(args[1], d, n);
//            default:
//                throw new IllegalArgumentException("Invalid operator: " + operator);
//        }
//    }
//}
