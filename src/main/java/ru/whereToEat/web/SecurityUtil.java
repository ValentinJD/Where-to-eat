package ru.whereToEat.web;

public class SecurityUtil {
    private static int admin = 100000;
    private static int user = 100001;

    public static int authUserId() {
        return admin;
    }
}
