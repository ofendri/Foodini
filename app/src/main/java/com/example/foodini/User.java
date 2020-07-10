package com.example.foodini;

import java.util.HashMap;

public class User {

    private String loginName = "";
    private String email = "";
    private String password = "";
    private HashMap<String, Integer> preferences = new HashMap<>();
    private DietClassification dietClassification;

    private enum DietClassification {
        VEGAN,
        VEGETARIAN,
        OMNIVORE,
        PESCATARIAN,
        FLEXITARIAN
    }

    public User(String loginName, String email, String password, HashMap<String, Integer> preferences, DietClassification dietClassification) {
        this.loginName = loginName;
        this.email = email;
        this.password = password;
        this.preferences = preferences;
        this.dietClassification = dietClassification;
    }

    public void addPreference(String str, Integer n) {
        preferences.put(str, n);
    }

    public void deletePreference(String str) {
        preferences.remove(str);
    }

    public void clearPreferences() {
        preferences.clear();
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public HashMap<String, Integer> getPreferences() {
        return preferences;
    }

    public void setPreferences(HashMap<String, Integer> preferences) {
        this.preferences = preferences;
    }

    public DietClassification getDietClassification() {
        return dietClassification;
    }

    public void setDietClassification(DietClassification dietClassification) {
        this.dietClassification = dietClassification;
    }


}
