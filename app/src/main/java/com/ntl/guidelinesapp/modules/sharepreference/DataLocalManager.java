package com.ntl.guidelinesapp.modules.sharepreference;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.ntl.guidelinesapp.general.model.General;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class DataLocalManager {
    private static final String PREF_FIRST_INSTALL_APP = "PREF_FIRST_INSTALL_APP";
    private static final String PREF_USER_NAMES = "PREF_USER_NAMES";
    private static final String PREF_GENERAL_OBJECT = "PREF_GENERAL_OBJECT";
    private static final String PREF_LIST_GENERAL_OBJECT = "PREF_LIST_GENERAL_OBJECT";

    private static DataLocalManager instance;
    private MySharePreference mySharePreference;

    public static void init(Context context) {
        instance = new DataLocalManager();
        instance.mySharePreference = new MySharePreference(context);
    }

    public static DataLocalManager getInstance() {
        if (instance == null) {
            instance = new DataLocalManager();
        }

        return instance;
    }

    public static void setFirstInstallApp(boolean value) {
        DataLocalManager.getInstance().mySharePreference.putBooleanValue(PREF_FIRST_INSTALL_APP, value);
    }

    public static boolean getFirstInstallApp() {
        return DataLocalManager.getInstance().mySharePreference.getBooleanValue(PREF_FIRST_INSTALL_APP);
    }

    public static void setUserNames(Set<String> names) {
        DataLocalManager.getInstance().mySharePreference.putStringSetValue(PREF_USER_NAMES, names);
    }

    public static Set<String> getUserNames() {
        return DataLocalManager.getInstance().mySharePreference.getStringSetValue(PREF_USER_NAMES);
    }

    public static void setGeneralObject(General general) {
        Gson gson = new Gson();
        DataLocalManager.getInstance().mySharePreference.putStringValue(PREF_GENERAL_OBJECT, gson.toJson(general));
    }

    public static General getGeneralObject() {
        String strGeneral = DataLocalManager.getInstance().mySharePreference.getStringValue(PREF_GENERAL_OBJECT);
        Gson gson = new Gson();
        return gson.fromJson(strGeneral, General.class);
    }

    public static void setListGeneralObject(List<General> generals) {
        Gson gson = new Gson();
        JsonArray jsonArray = gson.toJsonTree(generals).getAsJsonArray();
        DataLocalManager.getInstance().mySharePreference.putStringValue(PREF_LIST_GENERAL_OBJECT, jsonArray.toString());
    }

    public static List<General> getListGeneralObject() {
        String strJsonArray = DataLocalManager.getInstance().mySharePreference.getStringValue(PREF_LIST_GENERAL_OBJECT);
        List<General> list = new ArrayList<>();

        try {
            JSONArray jsonArray = new JSONArray(strJsonArray);
            JSONObject jsonObject;
            General general;
            Gson gson = new Gson();
            for (int i = 0; i < jsonArray.length(); i++) {
                jsonObject = jsonArray.getJSONObject(i);
                general = gson.fromJson(jsonObject.toString(), General.class);
                list.add(general);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }
}
