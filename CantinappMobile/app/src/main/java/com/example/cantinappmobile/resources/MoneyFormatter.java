package com.example.cantinappmobile.resources;

import java.util.Locale;

public class MoneyFormatter {
    public static String moneyFormat(Float value){
        return String.format(Locale.US,"R$ %.2f", value);
    }
}
