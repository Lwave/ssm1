package com.ssm.test;

public class SelectNumber {
    public static void main(String[] args) {
        /*Character 类用于对单个字符进行操作。*/
        String str = "a1b2";
      StringBuffer sb = new StringBuffer();
        char a[] = str.toCharArray();
        System.out.println("打印出其中所有的数字:");
        for (int i = 0; i < a.length; i++) {
            if (Character.isDigit(a[i]))
                sb.append(a[i]);
        }
        System.out.println(sb);
        for (int i = 0; i < str.length(); i++) {
            char at = str.charAt(i);
            if (at > '0' && at < '9') {
                System.out.print(at);
            }
        }
    }
}
