package ir.ac.kntu;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import ir.ac.kntu.types.AccessLevel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Feedbacks implements Initializable {
    private static boolean isGame;

    public static boolean isIsGame() {
        return isGame;
    }

    public static void setIsGame(boolean isGame) {
        Feedbacks.isGame = isGame;
    }
    @FXML
    private JFXButton backButton;

    @FXML
    private JFXButton exitButton;

    @FXML
    private JFXTextArea feedbacks;

    @FXML
    void pressBackButton(MouseEvent event) throws IOException {
        Stage stage=(Stage) backButton.getScene().getWindow();
        stage.close();
        Stage newStage=new Stage();
        FXMLLoader fxmlLoader;
        if (isGame) {
            fxmlLoader=new FXMLLoader(getClass().getResource("GamePage.fxml"));
        }else {
            fxmlLoader=new FXMLLoader(getClass().getResource("EquipmentPage.fxml"));
        }
        Scene scene = new Scene(fxmlLoader.load());
        newStage.initStyle(StageStyle.UNDECORATED);
        newStage.setScene(scene);
        newStage.show();
    }

    @FXML
    void pressExitButton(MouseEvent event) throws SQLException {
        if (LoginPage.getAccessLevel().equals(AccessLevel.user)){
            LoginPage.setEndTime(System.currentTimeMillis());
            LoginPage.addTime();
        }
        System.exit(0);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (isGame) {
            feedbacks.setText(arrayToString(GamesPage.getGame().getFeedback()));
        }else {
            feedbacks.setText(arrayToString(EquipmentsPage.getEquipment().getFeedback()));
        }
    }

    private String arrayToString(ArrayList<String> feedbacks){
        String lines="";
        if (feedbacks.size()>1) {
            for (int i = 0; i < feedbacks.size()-1; i += 2) {
                lines = lines + feedbacks.get(i) + " : " + feedbacks.get(i + 1) + "\n";
            }
        }
        return lines;
    }
}

