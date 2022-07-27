package com.example.hamster.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;


public class ParsingValidationUtils {
    public static boolean isLongParsing(String number) {
        try {
            Long.parseLong(number);
            return true;
        } catch (final NumberFormatException e) {
            return false;
        }
    }

    public static boolean isIntParsing(String number) {
        try {
            Integer.parseInt(number);
            return true;
        } catch (final NumberFormatException e) {
            return false;
        }
    }

    public static boolean isDecimalParsing(String number) {
        try {
            Double.parseDouble(number);
            return true;
        } catch (final NumberFormatException e) {
            return false;
        }
    }

    public static boolean isDateParsingType1(String date) {
        try {
            new SimpleDateFormat("dd/MM/yyyy").parse(date);
            return true;
        } catch (final ParseException e) {
            return false;
        }
    }
    public static boolean isDateParsingType2(String date) {
        try {
            new SimpleDateFormat("yyyy-MM-dd").parse(date);
            return true;
        } catch (final ParseException e) {
            return false;
        }
    }
}
