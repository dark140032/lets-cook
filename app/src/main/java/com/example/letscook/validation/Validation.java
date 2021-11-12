package com.example.letscook.validation;

import android.util.Log;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

public class Validation {
    public static boolean isValidMail(String mail) {
        return Pattern.compile("\\S+@\\S+\\.\\S+").matcher(mail).matches();
    }

    public static boolean isFutureDate(String str) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date strDate = sdf.parse(str);
            if (System.currentTimeMillis() >= strDate.getTime())
                return true;
        } catch (Exception ex) {

        }
        return false;
    }
}
