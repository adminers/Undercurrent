// Given two non-negative integers, num1 and num2 represented as string, return the sum of num1 and num2 as a string.
// You must solve the problem without using any built-in library for handling large integers (such as BigInteger). You must 
also not convert the inputs to integers directly.

public String addStrings(String num1, String num2) {
    StringBuilder sb = new StringBuilder();
    int carry = 0;
    int i1 = num1.length() - 1, i2 = num2.length() - 1;
    char c;
    while (i1 >= 0 || i2 >= 0) {
        int a = i1 >= 0? num1.charAt(i1) - '0' : 0;
        int b = i2 >= 0? num2.charAt(i2) - '0' : 0;
        int sum = a   b   carry;
        carry = sum / 10;
        sum %= 10;
        sb.append(sum);
        i1--;
        i2--;
    }
    if (carry > 0) sb.append(carry);
    return sb.reverse().toString();
}// 注释，代码。
