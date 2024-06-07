package ir.ac.kntu;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import ir.ac.kntu.types.AccessLevel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Feedback {
    private static boolean isGame;

    public static boolean isIsGame() {
        return isGame;
    }

    public static void setIsGame(boolean isGame) {
        Feedback.isGame = isGame;
    }
    @FXML
    private JFXButton backButton;

    @FXML
    private JFXButton exitButton;

    @FXML
    private JFXTextArea feedback;

    @FXML
    private JFXButton sendButton;

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

    @FXML
    void send(MouseEvent event) throws SQLException {
        if (!feedback.getText().equals("")) {
            if (isGame) {
                GamesPage.getGame().addFeedback(LoginPage.getUser().getUsername());
                GamesPage.getGame().addFeedback(feedback.getText());
                sendToSQLGame(GamesPage.getGame().getFeedback());
            }else {
                EquipmentsPage.getEquipment().addFeedback(LoginPage.getUser().getUsername());
                EquipmentsPage.getEquipment().addFeedback(feedback.getText());
                sendToSQLEquipment(EquipmentsPage.getEquipment().getFeedback());
            }
            feedback.clear();
        }
    }

    private void sendToSQLGame(ArrayList<String> list) throws SQLException {
        String line="";
        for (String word:list){
            line+=word+",";
        }
        java.sql.Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/data","root","");
        Statement statement=connection.createStatement();
        statement.executeUpdate("UPDATE game SET feedback='"+line+"' WHERE id="+GamesPage.getGame().getId()+"");
    }

    private void sendToSQLEquipment(ArrayList<String> list) throws SQLException {
        String line="";
        for (String word:list){
            line+=word+",";
        }
        java.sql.Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/data","root","");
        Statement statement=connection.createStatement();
        statement.executeUpdate("UPDATE equipment SET feedback='"+line+"' WHERE id="+EquipmentsPage.getEquipment().getId()+"");
    }
}
