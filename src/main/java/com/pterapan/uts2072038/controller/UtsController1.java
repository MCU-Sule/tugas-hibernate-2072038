package com.pterapan.uts2072038.controller;

import com.pterapan.uts2072038.HelloApplication;
import com.pterapan.uts2072038.dao.MovieDao;
import com.pterapan.uts2072038.dao.UserDao;
import com.pterapan.uts2072038.dao.WatchlistDao;
import com.pterapan.uts2072038.model.*;
import com.pterapan.uts2072038.util.MyConnection;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;
import org.hibernate.engine.jdbc.connections.spi.JdbcConnectionAccess;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.engine.spi.SessionImplementor;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class UtsController1 implements Initializable {
    public ComboBox<String> cmbGenre;
    public ListView<UserEntity> lvUser;
    public TableView<MovieEntity> table1;
    public TableView<WatchlistEntity> table2;
    public TableColumn<MovieEntity, String> colTitle1;
    public TableColumn<MovieEntity, String> colGenre;
    public TableColumn<MovieEntity, Integer> colDurasi;
    public TableColumn<WatchlistEntity, MovieEntity> colTitle2;
    public TableColumn<WatchlistEntity, Integer> colLast;
    public TableColumn<WatchlistEntity, Byte> ColFavorite;

    private ObservableList<MovieEntity> mlist;
    private ObservableList<UserEntity> ulist;
    private ObservableList<WatchlistEntity> wlist;
    private MovieDao movieDao;
    private UserDao userDao;
    private WatchlistDao watchlistDao;

    private FilteredList<MovieEntity> filteredMovie;
    private FilteredList<WatchlistEntity> filteredUser;

    private Stage stage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        stage = new Stage();
        stage.initModality(Modality.WINDOW_MODAL);
        movieDao = new MovieDao();
        userDao = new UserDao();
        watchlistDao = new WatchlistDao();
        mlist = FXCollections.observableArrayList(movieDao.getData());
        ulist = FXCollections.observableArrayList(userDao.getData());
        wlist = FXCollections.observableArrayList(watchlistDao.getData());

        ObservableList<String> options =
                FXCollections.observableArrayList(
                        "All",
                        "Action",
                        "Musical",
                        "Comedy",
                        "Animated",
                        "Fantasy",
                        "Drama",
                        "Mistery",
                        "Thriller",
                        "Horror"
                );

        cmbGenre.setItems(options);
        cmbGenre.getSelectionModel().select(0);
        lvUser.setItems(ulist);
        table1.setItems(mlist);
        table2.setItems(wlist);
        colTitle1.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getTitle()));
        colGenre.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getGenre()));
        colDurasi.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getDurasi()));
        colTitle2.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getMovieByMovieIdMovie()));
        colLast.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getLastWatch()));
        ColFavorite.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getFavorite()));
    }

    public void refreshData() {
        UserDao dao = new UserDao();
        ulist = FXCollections.observableArrayList(dao.getData());
        lvUser.setItems(ulist);
        table1.setItems(mlist);
    }

    public void changeCombo(ActionEvent actionEvent) {
        String genre = cmbGenre.getValue();
        if (genre != "All") {
            filteredMovie = mlist.filtered(m -> (m.getGenre().contains(genre)));
            table1.setItems(filteredMovie);
        } else {
            refreshData();
        }
    }

    public void AddUserAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader;
        loader = new FXMLLoader(HelloApplication.class.getResource("UTSSecondPage.fxml"));
        Scene scene = new Scene(loader.load(), 333, 168);
        UtsController2 uts2Controller = loader.getController();
        uts2Controller.setUtsController1(this);
        stage.setTitle("Add Data");
        stage.setScene(scene);
        stage.showAndWait();
    }

    public void DelUserAction(ActionEvent actionEvent) {
        UserEntity selectedItems;
        selectedItems = lvUser.getSelectionModel().getSelectedItem();

        UserDao dao = new UserDao();

        Alert alertbox = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.OK, ButtonType.CANCEL);
        alertbox.showAndWait();
        if (alertbox.getResult() == ButtonType.OK) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Data berhasil dihapus", ButtonType.OK);
            alert.showAndWait();
            dao.delData(selectedItems);
        }
        refreshData();
    }

    public void printReport(ActionEvent actionEvent) throws SQLException {
        JasperPrint jp;
        SessionFactory sf = MyConnection.getSessionFactory();
        Session s = sf.openSession();

        Map param = new HashMap();
        try {
            jp = JasperFillManager.fillReport("report/UTS.jasper", param, (Connection) s);
            JasperViewer viewer = new JasperViewer(jp, false);
            viewer.setTitle("laporan movies");
            viewer.setVisible(true);
        } catch (JRException e) {
            throw new RuntimeException(e);
        }
    }

    public void filterUser(MouseEvent mouseEvent) {
        int user = lvUser.getSelectionModel().getSelectedItem().getIdUser();
        if (user > 0) {
            filteredUser = wlist.filtered(w -> (w.getUserIdUser() == user));
            table2.setItems(filteredUser);
        } else {
            refreshData();
        }
    }
}
