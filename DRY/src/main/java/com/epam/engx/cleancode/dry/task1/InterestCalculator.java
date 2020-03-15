package com.epam.engx.cleancode.dry.task1;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class InterestCalculator {

    private static final int AGE = 60;
    private static final double INTEREST_PERCENT = 4.5d;
    private static final double SENIOR_PERCENT = 5.5d;
    private static final int BONUS_AGE = 13;
    private static final int PERCENT = 100;

    private Calendar endCalendar;
    private Calendar startCalendar;

    public BigDecimal calculateInterest(AccountDetails accountDetails) {
        if (isAccountStartedAfterBonusAge(accountDetails)) {
            return interest(accountDetails);
        } else {
            return BigDecimal.ZERO;
        }
    }

    private boolean isAccountStartedAfterBonusAge(AccountDetails accountDetails) {
        return durationBetweenDatesInYears(accountDetails.getBirth(), accountDetails.getStartDate()) > BONUS_AGE;
    }

    private BigDecimal interest(AccountDetails accountDetails) {
        double interest = 0;
        if (isAccountStartedAfterBonusAge(accountDetails)) {
            if (AGE <= accountDetails.getAge()) {
                //interest = (PrincipalAmount * DurationInYears * AnnualInterestRate) / 100
                interest = getPrincipalandDuration(accountDetails) * SENIOR_PERCENT / PERCENT;
            } else {
                interest = getPrincipalandDuration(accountDetails) * INTEREST_PERCENT / PERCENT;
            }
        }
        return BigDecimal.valueOf(interest);
    }

    private int durationBetweenDatesInYears(Date from, Date to) {
        startCalendar = createCalendar();
        startCalendar.setTime(from);
        endCalendar = createCalendar();
        endCalendar.setTime(to);

        return getDiffYear();
    }

    private int durationSinceStartDateInYears(Date startDate) {
        startCalendar = createCalendar();
        startCalendar.setTime(startDate);
        endCalendar = createCalendar();
        endCalendar.setTime(new Date());

        return getDiffYear();
    }

    private int getDiffYear() {
        int diffYear = endCalendar.get(Calendar.YEAR) - startCalendar.get(Calendar.YEAR);
        if (endCalendar.get(Calendar.DAY_OF_YEAR) < startCalendar.get(Calendar.DAY_OF_YEAR))
            return diffYear - 1;
        return diffYear;
    }

    private Calendar createCalendar() {
        return new GregorianCalendar();
    }

    private double getPrincipalandDuration(AccountDetails accountDetails) {
        return accountDetails.getBalance().doubleValue() * durationSinceStartDateInYears(accountDetails.getStartDate());
    }
}
