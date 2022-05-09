package com.example.comicfx;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class HelloController {
    @FXML
    private TextField distanceField;
    @FXML
    private TextField countField;
    @FXML
    private TextField pathField;
    @FXML
    private TextField positionField;

    @FXML
    protected void click() throws IOException {
        Backgrounds c = new Backgrounds(pathField.getText(), Integer.parseInt(positionField.getText()));
        TextBoxes x = new TextBoxes(pathField.getText(), Integer.parseInt(positionField.getText()));

        x.getTextContent();


        x.run();
        File t = new File(pathField.getText()+"\\Input\\TextOutput");
        // File t = new File("H:\\OnePics\\Single\\SWF\\Comics\\AI\\Input\\TextOutput");
        ArrayList<File> alibi = new ArrayList<>();
        c.getAllFiles(t, alibi);
        System.out.println(alibi.size());
        Backgrounds b = new Backgrounds(pathField.getText(), Integer.parseInt(positionField.getText()));
        for (int j = 0; j < alibi.size(); j++) {
            b.run(Integer.parseInt(countField.getText()), j, Integer.parseInt(distanceField.getText()),Integer.parseInt(positionField.getText()));
        }
    }
}