package com.example.projetdiego;

public class Session {
    private static boolean IsAdmin = false;

    public static boolean isIsAdmin() {
        return IsAdmin;
    }

    public static void setIsAdmin(boolean isAdmin) {
        IsAdmin = isAdmin;
    }
}
