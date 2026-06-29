package com.example.btl.data;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.btl.model.HistoryItem;

import java.util.ArrayList;
import java.util.List;

public class UserManager {

    private static final String PREF_USERS     = "pref_users";
    private static final String PREF_SESSION   = "pref_session";
    private static final String PREF_HISTORY   = "pref_history";
    private static final String KEY_LOGGED_IN  = "logged_in_user";
    private static final String KEY_COINS      = "coins_";
    private static final String KEY_HEARTS     = "hearts_";
    private static final String KEY_HISTORY    = "history_";

    // ── Registration ──────────────────────────────────────────────────────────

    public static boolean register(Context ctx, String username, String password) {
        SharedPreferences prefs = ctx.getSharedPreferences(PREF_USERS, Context.MODE_PRIVATE);
        if (prefs.contains(username)) return false; // already exists
        prefs.edit()
             .putString(username, password)
             .putInt(KEY_COINS + username, 100)
             .putInt(KEY_HEARTS + username, 5)
             .apply();
        return true;
    }

    // ── Login ─────────────────────────────────────────────────────────────────

    public static boolean login(Context ctx, String username, String password) {
        SharedPreferences prefs = ctx.getSharedPreferences(PREF_USERS, Context.MODE_PRIVATE);
        String stored = prefs.getString(username, null);
        if (password.equals(stored)) {
            ctx.getSharedPreferences(PREF_SESSION, Context.MODE_PRIVATE)
               .edit().putString(KEY_LOGGED_IN, username).apply();
            return true;
        }
        return false;
    }

    public static void logout(Context ctx) {
        ctx.getSharedPreferences(PREF_SESSION, Context.MODE_PRIVATE)
           .edit().remove(KEY_LOGGED_IN).apply();
    }

    public static String getLoggedInUser(Context ctx) {
        return ctx.getSharedPreferences(PREF_SESSION, Context.MODE_PRIVATE)
                  .getString(KEY_LOGGED_IN, null);
    }

    public static boolean isLoggedIn(Context ctx) {
        return getLoggedInUser(ctx) != null;
    }

    // ── Coins / Hearts ────────────────────────────────────────────────────────

    public static int getCoins(Context ctx, String username) {
        return ctx.getSharedPreferences(PREF_USERS, Context.MODE_PRIVATE)
                  .getInt(KEY_COINS + username, 100);
    }

    public static void addCoins(Context ctx, String username, int amount) {
        SharedPreferences prefs = ctx.getSharedPreferences(PREF_USERS, Context.MODE_PRIVATE);
        int current = prefs.getInt(KEY_COINS + username, 100);
        prefs.edit().putInt(KEY_COINS + username, current + amount).apply();
    }

    public static int getHearts(Context ctx, String username) {
        return ctx.getSharedPreferences(PREF_USERS, Context.MODE_PRIVATE)
                  .getInt(KEY_HEARTS + username, 5);
    }

    // ── History ───────────────────────────────────────────────────────────────

    public static void saveHistory(Context ctx, String username, List<HistoryItem> items) {
        SharedPreferences prefs = ctx.getSharedPreferences(PREF_HISTORY, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(KEY_HISTORY + username + "_count", items.size());
        for (int i = 0; i < items.size(); i++) {
            editor.putString(KEY_HISTORY + username + "_" + i, items.get(i).serialize());
        }
        editor.apply();
    }

    public static List<HistoryItem> loadHistory(Context ctx, String username) {
        SharedPreferences prefs = ctx.getSharedPreferences(PREF_HISTORY, Context.MODE_PRIVATE);
        int count = prefs.getInt(KEY_HISTORY + username + "_count", 0);
        List<HistoryItem> items = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            String data = prefs.getString(KEY_HISTORY + username + "_" + i, null);
            if (data != null) {
                HistoryItem item = HistoryItem.deserialize(data);
                if (item != null) items.add(item);
            }
        }
        return items;
    }

    public static void clearHistory(Context ctx, String username) {
        SharedPreferences prefs = ctx.getSharedPreferences(PREF_HISTORY, Context.MODE_PRIVATE);
        prefs.edit().putInt(KEY_HISTORY + username + "_count", 0).apply();
    }
}
