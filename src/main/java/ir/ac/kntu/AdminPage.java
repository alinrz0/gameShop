package ir.ac.kntu;

import com.jfoenix.controls.JFXButton;
import ir.ac.kntu.types.Friends;
import ir.ac.kntu.types.GameFilter;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class AdminPage {
    @FXML
    private Button accessoriesButton;

    @FXML
    private JFXButton exitButton;

    @FXML
    private Button gamesButton;

    @FXML
    private JFXButton logOutButton;

    @FXML
    private Button profileButton;

    @FXML
    private Button reportsButton;

    @FXML
    private Button usersButton;

    @FXML
    void pressExitButton(MouseEvent event) {
        System.exit(0);
    }

    @FXML
    void pressGamesButton(MouseEvent event) throws IOException {
        Stage stage=(Stage) gamesButton.getScene().getWindow();
        stage.close();
        Stage newStage=new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("GamesPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        newStage.initStyle(StageStyle.UNDECORATED);
        newStage.setScene(scene);
        newStage.show();
    }

    @FXML
    void pressLogOutButton(MouseEvent event) throws IOException {
        LoginPage.getAdmin().setUsername(null);
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
    void pressReportsButton(MouseEvent event) {

    }

    @FXML
    void pressUsersButton(MouseEvent event) throws IOException {
        UsersPage.setFriends(Friends.Admin);
        Stage stage=(Stage) usersButton.getScene().getWindow();
        stage.close();
        Stage newStage=new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("UsersPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        newStage.initStyle(StageStyle.UNDECORATED);
        newStage.setScene(scene);
        newStage.show();
    }

    @FXML
    void pressAccessoriesButton(MouseEvent event) throws IOException {
        Stage stage=(Stage) accessoriesButton.getScene().getWindow();
        stage.close();
        Stage newStage=new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("EquipmentsPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        newStage.initStyle(StageStyle.UNDECORATED);
        newStage.setScene(scene);
        newStage.show();
    }

}
