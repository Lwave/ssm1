package com.ssm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import javax.servlet.http.HttpSession;
import java.util.Locale;

@Controller
public class LocaleController {

    @RequestMapping("/changLocale")

    public String change(@RequestParam("request_locale") String request_locale, HttpSession session) {
        if ("zh_CN".equals(request_locale)) {
            Locale zh = new Locale("zh", "CN");
            session.setAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME,zh);
        } else {

            Locale en = new Locale("en", "US");
            session.setAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME,en);
        }
        return "login";
    }
}


