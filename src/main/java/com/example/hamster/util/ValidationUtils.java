package com.example.hamster.util;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.regex.Pattern;

public class ValidationUtils {
    public static final String FULL_NAME_REGEX = "[A-Za-z\\s]+";
    public static final String PASSWORD_REGEX = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$";
    public static final String PHONE_REGEX = "^[0][1-9][0-9]{8,9}$";
    public static final String IDCARD_REGEX = "^[0-9]{9}";
    public static final String EMAIL_REGEX =   "^[a-zA-Z][\\w-]+@([\\w]+\\.[\\w]+|[\\w]+\\.[\\w]{2,}\\.[\\w]{2,})$";
    public static final String USERNAME_REGEX = "^[A-Za-z][A-Za-z0-9_]{7,19}$";
    public static String validStartDate;
    public static String validEndDate;
    public static String minDateOfBirthValid;
    public static String maxDateOfBirthValid;

    public static boolean checkStartDate(LocalDate inputDate) {
        // inputDate: 24/07/2022

        int currentYear = LocalDate.now().getYear();
        int validRange = 0;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String minDateString = "01/01/" + (currentYear - validRange);
        LocalDate minDate = LocalDate.parse(minDateString, formatter);

        //minDate: 01/01/2022
        // inputDate: 01/01/22
        validStartDate = minDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        return inputDate.isAfter(minDate) || inputDate.isEqual(minDate);
    }

    public static boolean checkEndDate(LocalDate inputDate) {
        int currentYear = LocalDate.now().getYear();
        int validRange = 3;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String maxDateString = "31/12/" + (currentYear + validRange);
        LocalDate maxDate = LocalDate.parse(maxDateString, formatter);
        validEndDate = maxDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        return inputDate.isBefore(maxDate) || inputDate.isEqual(maxDate);
    }

    public static boolean checkDateOfBirth(Date inputDate) {
        int currentYear = LocalDate.now().getYear();
        int validRangeMin = 90;
        int validRangeMax = 10;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String maxDateString = "31/12/" + (currentYear - validRangeMax);
        String minDateString = "01/01/" + (currentYear - validRangeMin);

        LocalDate minDateOfBirth = LocalDate.parse(minDateString,formatter);
        LocalDate maxDateOfBirth = LocalDate.parse(maxDateString,formatter);

        minDateOfBirthValid = minDateOfBirth.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        maxDateOfBirthValid = maxDateOfBirth.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        LocalDate dateOfBirth = inputDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return (dateOfBirth.isAfter(minDateOfBirth) || dateOfBirth.isEqual(minDateOfBirth)) &&
                (dateOfBirth.isBefore(maxDateOfBirth) ||dateOfBirth.isEqual(maxDateOfBirth));
    }

    public static boolean isPhoneValid(String number) {
        return Pattern.compile(PHONE_REGEX).matcher(number).matches();
    }
    public static boolean isMailValid(String number) {
        return Pattern.compile(EMAIL_REGEX).matcher(number).matches();
    }
public static boolean isIdCardValid(String number) {
        return Pattern.compile(IDCARD_REGEX).matcher(number).matches();
}
}
