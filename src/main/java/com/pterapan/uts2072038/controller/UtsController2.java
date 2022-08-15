package com.pterapan.uts2072038.controller;

import com.pterapan.uts2072038.dao.UserDao;
import com.pterapan.uts2072038.model.UserEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;

public class UtsController2 {
    public TextField txtUserName;
    public PasswordField txtPassword;
    public Label labelUsername;
    public Label labelPassword;

    private ObservableList<UserEntity> ulist;

    private UtsController1 utsController1;

    public void submit(ActionEvent actionEvent) {
        UserDao dao = new UserDao();
        UserEntity u = new UserEntity();
        u.setUserName(txtUserName.getText());
        u.setUserPassword(txtPassword.getText());
        int hasil = dao.addData(u);
        ulist = FXCollections.observableArrayList(dao.getData());

        utsController1.lvUser.setItems(ulist);
        if (hasil > 0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Berhasil Add Data", ButtonType.OK);
            alert.showAndWait();
            labelUsername.getScene().getWindow().hide();
        }
    }

    public void setUtsController1(UtsController1 utsController1) {
        this.utsController1 = utsController1;
    }
}
