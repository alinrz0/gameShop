package ir.ac.kntu;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Pattern;

public class Wallet {

    @FXML
    private TextField amount;

    @FXML
    private JFXButton backButton;

    @FXML
    private JFXButton chargeButton;

    @FXML
    private Label errorLabel;

    @FXML
    private JFXButton exitButton;

    @FXML
    void pressBackButton(MouseEvent event) throws IOException {
        Stage stage=(Stage) backButton.getScene().getWindow();
        stage.close();
        Stage newStage=new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ProfilePage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        newStage.initStyle(StageStyle.UNDECORATED);
        newStage.setScene(scene);
        newStage.show();
    }

    @FXML
    void pressChargeButton(MouseEvent event) throws SQLException {
        if (!Pattern.matches("[0-9]+",amount.getText())){
            errorLabel.setVisible(true);
            errorLabel.setText("write correct amount!");
            errorLabel.setTextFill(Color.RED);
        }else {
            errorLabel.setVisible(true);
            errorLabel.setText("charge successfully!");
            errorLabel.setTextFill(Color.GREEN);
            java.sql.Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/data","root","");
            Statement statement=connection.createStatement();
            int wallet=LoginPage.getUser().getWallet()+Integer.parseInt(amount.getText());
            LoginPage.getUser().setWallet(wallet);
            String update="UPDATE user SET wallet="+wallet+" WHERE username='"+LoginPage.getUser().getUsername()+"'";
            statement.executeUpdate(update);
        }
    }

    @FXML
    void pressExitButton(MouseEvent event) throws SQLException {
        LoginPage.setEndTime(System.currentTimeMillis());
        LoginPage.addTime();
        System.exit(0);
    }

}
