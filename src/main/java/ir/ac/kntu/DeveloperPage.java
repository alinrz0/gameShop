package ir.ac.kntu;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class DeveloperPage {
    @FXML
    private JFXButton exitButton;

    @FXML
    private Button gamesButton;

    @FXML
    private Button inboxButton;

    @FXML
    private JFXButton logOutButton;

    @FXML
    private Button profileButton;

    @FXML
    private Button scheduledEventsButton;

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
    void pressInboxButton(MouseEvent event) throws IOException {
        Stage stage=(Stage) inboxButton.getScene().getWindow();
        stage.close();
        Stage newStage=new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("InboxDeveloper.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        newStage.initStyle(StageStyle.UNDECORATED);
        newStage.setScene(scene);
        newStage.show();
    }

    @FXML
    void pressLogOutButton(MouseEvent event) throws IOException {
        LoginPage.getDeveloper().setUsername(null);
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
    void pressScheduledEventsButton(MouseEvent event) {

    }

}
