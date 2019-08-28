package com.ssm.test;

import java.util.Locale;

public class LocaleTest {
    public static void main(String[] args) {
        Locale locale = new Locale("en", "US");
        Locale aDefault = locale.getDefault();
        System.out.println(aDefault);
        String displayLanguage = locale.getDisplayLanguage();
        System.out.println(displayLanguage);
        String s = locale.toString();
        System.out.println(s);
        Locale locale1 = new Locale("zh", "CN");
        System.out.println(locale1);

    }
}
