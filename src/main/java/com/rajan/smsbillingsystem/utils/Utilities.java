package com.rajan.smsbillingsystem.utils;

public class Utilities {
    /**
     * *Simply formats the double to the 3 round places string by appending the $ mark.
     *
     * @param money
     * @return
     */
    public static String formatMoneyToUSDollar(final double money) {
        return String.format("%,.3f$", money);
    }
}
