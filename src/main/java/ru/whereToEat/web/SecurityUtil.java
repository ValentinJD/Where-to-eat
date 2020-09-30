package ru.whereToEat.web;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class SecurityUtil {
    private static int admin = 100000;
    private static int user = 100001;

    private static int currentUserId = 0;

    private static final List<Integer> listUserAuthId = new CopyOnWriteArrayList<>();

    public static int authUserId() {
        return currentUserId;
    }

    public static void setAuthUserId(int userId) {
        if (!listUserAuthId.contains(userId)) {
            listUserAuthId.add(userId);
        }
        SecurityUtil.currentUserId = userId;
    }
}
