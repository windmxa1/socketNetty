package com.siyann.controller;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import javafx.event.ActionEvent;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by tanzhenyu on 2017/6/14.
 */
public class MainController implements Initializable {
    @FXML
    private ListView clientList;
    /**
     * listciew数据
     */
    private ObservableList<String> dataList = FXCollections.observableArrayList();

    @FXML
    private void StartClient(ActionEvent event) {
        System.out.println(event);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        {
            dataList.add("张三");
            dataList.add("李四");
            dataList.add("王五");
            dataList.add("李华");

            dataList.add("张三");
            dataList.add("李四");
            dataList.add("王五");
            dataList.add("李华");

            dataList.add("张三");
            dataList.add("李四");
            dataList.add("王五");
            dataList.add("李华");
            clientList.setItems(dataList);

            clientList.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {

                }
            });
        }
    }
}