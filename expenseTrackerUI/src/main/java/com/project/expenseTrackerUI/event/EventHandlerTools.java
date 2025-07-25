package com.project.expenseTrackerUI.event;

import com.project.expenseTrackerUI.components.ExpenseForm;
import com.project.expenseTrackerUI.components.componentBase.CustomButton;
import javafx.scene.control.Alert;
import javafx.scene.layout.GridPane;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

public abstract class EventHandlerTools {

    public static void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR, message);
        alert.showAndWait();
    }

    public static void switchButtons(ExpenseForm expenseForm, CustomButton btn1, CustomButton btn2, CustomButton btn3, CustomButton btn4) {
        // btn1 <-> btn3
        // btn2 <-> btn4
        int col1 = GridPane.getColumnIndex(btn1);
        int row1 = GridPane.getRowIndex(btn1);

        int col2 = GridPane.getColumnIndex(btn2);
        int row2 = GridPane.getRowIndex(btn2);

        expenseForm.getChildren().remove(btn1);
        expenseForm.getChildren().remove(btn2);

        expenseForm.add(btn3, col1, row1);
        expenseForm.add(btn4, col2, row2);
    }

    public static StringBuilder getUrlParam(HashMap<String, String> map){
        StringBuilder str = new StringBuilder();
        for (String key : map.keySet()){
            if(!map.get(key).isEmpty()){
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
