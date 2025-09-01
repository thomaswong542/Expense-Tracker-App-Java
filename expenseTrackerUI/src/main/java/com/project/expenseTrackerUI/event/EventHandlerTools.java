package com.project.expenseTrackerUI.event;

import javafx.scene.control.Alert;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

public abstract class EventHandlerTools {

    public static void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR, message);
        alert.setResizable(true);
        alert.showAndWait();
    }

    public static StringBuilder getUrlParam(HashMap<String, String> map){
        StringBuilder str = new StringBuilder();
        for (String key : map.keySet()){
            if(map.get(key) != null){
                str.append(key)
                        .append("=")
                        .append(URLEncoder.encode(map.get(key), StandardCharsets.UTF_8))
                        .append("&");
            }
        }
        int len = str.length();
        if (len > 0){
            str.deleteCharAt(len - 1);
            str.insert(0, "?");
        }
        return str;
    }

}
