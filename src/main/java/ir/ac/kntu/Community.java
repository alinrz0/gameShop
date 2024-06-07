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
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Community implements Initializable {
    private static boolean isGame;

    public static boolean isIsGame() {
        return isGame;
    }

    public static void setIsGame(boolean isGame) {
        Community.isGame = isGame;
    }

    @FXML
    private JFXTextArea communityTexts;

    @FXML
    private JFXButton backButton;

    @FXML
    private JFXButton exitButton;

    @FXML
    private JFXButton sendButton;

    @FXML
    private JFXTextArea yourOpinion;

    @FXML
    void pressBackButton(MouseEvent event) throws IOException {
        Stage stage=(Stage) backButton.getScene().getWindow();
        stage.close();
        Stage newStage=new Stage();
        FXMLLoader fxmlLoader;
        if (isGame) {
            fxmlLoader = new FXMLLoader(getClass().getResource("GamePage.fxml"));
        }else {
            fxmlLoader = new FXMLLoader(getClass().getResource("EquipmentPage.fxml"));
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
        if (!yourOpinion.getText().equals("")) {
            if (isGame) {
                GamesPage.getGame().addCommunity(LoginPage.getUser().getUsername());
                GamesPage.getGame().addCommunity(yourOpinion.getText());
                communityTexts.clear();
                communityTexts.setText(arrayToString(GamesPage.getGame().getCommunity()));
                sendToSQLGame(GamesPage.getGame().getCommunity());
            }else {
                EquipmentsPage.getEquipment().addCommunity(LoginPage.getUser().getUsername());
                EquipmentsPage.getEquipment().addCommunity(yourOpinion.getText());
                communityTexts.clear();
                communityTexts.setText(arrayToString(EquipmentsPage.getEquipment().getCommunity()));
                sendToSQLEquipment(EquipmentsPage.getEquipment().getCommunity());
            }
            yourOpinion.clear();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (isGame) {
            if (GamesPage.getGame().getCommunity().size()>1) {
                communityTexts.setText(arrayToString(GamesPage.getGame().getCommunity()));
            }
        }else {
            if (EquipmentsPage.getEquipment().getCommunity().size()>1) {
                communityTexts.setText(arrayToString(EquipmentsPage.getEquipment().getCommunity()));
            }
        }
    }

    private String arrayToString(ArrayList<String> communities){
        String lines="";
        for (int i=1;i<communities.size();i+=2){
            lines=lines+communities.get(i)+" : "+communities.get(i+1)+"\n";
        }
        return lines;
    }

    private void sendToSQLGame(ArrayList<String> list) throws SQLException {
        String line="";
        for (String word:list){
            line+=word+",";
        }
        java.sql.Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/data","root","");
        Statement statement=connection.createStatement();
        statement.executeUpdate("UPDATE game SET community='"+line+"' WHERE id="+GamesPage.getGame().getId()+"");
    }

    private void sendToSQLEquipment(ArrayList<String> list) throws SQLException {
        String line="";
        for (String word:list){
            line+=word+",";
        }
        java.sql.Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/data","root","");
        Statement statement=connection.createStatement();
        statement.executeUpdate("UPDATE equipment SET community='"+line+"' WHERE id="+EquipmentsPage.getEquipment().getId()+"");
    }
}
