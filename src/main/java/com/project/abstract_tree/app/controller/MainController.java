package com.project.abstract_tree.app.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MainController {
    @FXML private Button load;
    @FXML
    public void onClickLoad() {
        System.out.println("Загружаем...");
    }
}
