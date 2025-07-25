package com.project.expenseTrackerUI.components;

import com.project.expenseTrackerUI.App;
import com.project.expenseTrackerUI.components.componentBase.CustomLabel;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.io.InputStream;

public class SideMenuItem extends HBox {

    private String name;
    private double size = 14.0d;
    private CustomLabel label;
    private ImageView image;
    private String image_url;

    public SideMenuItem(String name, String image_url){
        this.name = name;
        this.image_url = image_url;
        init();
    }

    private void init(){
        label = new CustomLabel(this.name);
        label.setFontSize(size);
        InputStream is = App.class.getResourceAsStream(image_url);
        if (is != null) {
            image = new ImageView(new Image(is));
            this.getChildren().addAll(image, label);
        }
        else {
            this.getChildren().addAll(label);
        }

        this.setPadding(new Insets(0, 10.0d, 0, 10.0d));
    }

}
