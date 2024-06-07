package ir.ac.kntu;

import com.jfoenix.controls.JFXButton;
import ir.ac.kntu.types.GameFilter;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class UserPage implements Initializable {
    private static GameFilter gameFilter;

    @FXML
    private AnchorPane UserStage;

    @FXML
    private JFXButton exitButton;

    @FXML
    private Button friendsButton;

    @FXML
    private Label levelLabel;

    @FXML
    private Button libraryButton;

    @FXML
    private JFXButton logOutButton;

    @FXML
    private Button profileButton;

    @FXML
    private Button storeButton;

    @FXML
    void pressExitButton(MouseEvent event) throws SQLException {
        LoginPage.setEndTime(System.currentTimeMillis());
        LoginPage.addTime();
        System.exit(0);
    }

    @FXML
    void pressFriendsButton(MouseEvent event) throws IOException {
        Stage stage=(Stage) friendsButton.getScene().getWindow();
        stage.close();
        Stage newStage=new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("UsersPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        newStage.initStyle(StageStyle.UNDECORATED);
        newStage.setScene(scene);
        newStage.show();
    }

    @FXML
    void pressLibraryButton(MouseEvent event) throws IOException {
        gameFilter=GameFilter.Library;
        Stage stage=(Stage) storeButton.getScene().getWindow();
        stage.close();
        Stage newStage=new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("GamesPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        newStage.initStyle(StageStyle.UNDECORATED);
        newStage.setScene(scene);
        newStage.show();
    }

    @FXML
    void pressLogOutButton(MouseEvent event) throws IOException, SQLException {
        LoginPage.setEndTime(System.currentTimeMillis());
        LoginPage.addTime();
        LoginPage.setStartTime(0);
        LoginPage.setEndTime(0);
        LoginPage.getUser().setUsername(null);
        Stage stage=(Stage) logOutButton.getScene().getWindow();
        stage.close();
        Stage newStage=new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("LoginPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        newStage.initStyle(StageStyle.UNDECORATED);
        newStage.setScene(scene);
        newStage.show();
    }

    @FXML
    void pressProfileButton(MouseEvent event) throws IOException {
        Stage stage=(Stage) profileButton.getScene().getWindow();
        stage.close();
        Stage newStage=new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ProfilePage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        newStage.initStyle(StageStyle.UNDECORATED);
        newStage.setScene(scene);
        newStage.show();
    }

    @FXML
    void pressStoreButton(MouseEvent event) throws IOException {
        gameFilter=GameFilter.Store;
        Stage stage=(Stage) storeButton.getScene().getWindow();
        stage.close();
        Stage newStage=new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("GamesPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        newStage.initStyle(StageStyle.UNDECORATED);
        newStage.setScene(scene);
        newStage.show();
    }


    public static GameFilter getGameFilter() {
        return gameFilter;
    }

    public static void setGameFilter(GameFilter gameFilter) {
        UserPage.gameFilter = gameFilter;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        levelLabel.setText(String.valueOf(LoginPage.getUser().getLevel()));
    }
}




