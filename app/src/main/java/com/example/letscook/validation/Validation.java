package com.example.letscook.validation;

import java.util.regex.Pattern;

public class Validation {
    public static boolean isValidMail(String mail) {
        return Pattern.compile("\\S+@\\S+\\.\\S+").matcher(mail).matches();
    }
}
