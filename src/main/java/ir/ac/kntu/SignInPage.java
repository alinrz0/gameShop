package ir.ac.kntu;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import ir.ac.kntu.objects.User;
import ir.ac.kntu.types.AccessLevel;
import ir.ac.kntu.types.DeveloperLevel;
import ir.ac.kntu.types.Level;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class SignInPage implements Initializable {
    @FXML
    private JFXRadioButton adminButton;

    @FXML
    private JFXRadioButton developerButton;

    @FXML
    private JFXRadioButton levelButton1;

    @FXML
    private JFXRadioButton levelButton2;

    @FXML
    private JFXRadioButton levelButton3;

    @FXML
    private JFXRadioButton userButton;

    @FXML
    private JFXRadioButton sellerButton;
    @FXML
    private Label signInLabel;

    @FXML
    private AnchorPane signInPage;
    @FXML
    private TextField phoneText;

    @FXML
    private TextField ageText;

    @FXML
    private JFXButton backButton;

    @FXML
    private JFXButton doneButton;

    @FXML
    private TextField emailText;

    @FXML
    private JFXButton exitButton;

    @FXML
    private TextField firstNameText;

    @FXML
    private TextField lastNameText;

    @FXML
    private PasswordField passwordText;

    @FXML
    private PasswordField repeatPasswordText;

    @FXML
    private TextField usernameText;


    @FXML
    private Label errorLabel;

    @FXML
    void pressAdminButton(MouseEvent event) {
        userButton.setSelected(false);
        sellerButton.setSelected(false);
        developerButton.setSelected(false);
        adminButton.setSelected(true);
        levelButton3.setVisible(false);
        levelButton2.setVisible(false);
        levelButton1.setVisible(false);
    }

    @FXML
    void pressDeveloperButton(MouseEvent event) {
        adminButton.setSelected(false);
        sellerButton.setSelected(false);
        userButton.setSelected(false);
        developerButton.setSelected(true);
        levelButton3.setVisible(true);
        levelButton2.setVisible(true);
        levelButton1.setVisible(true);
    }

    @FXML
    void pressLevelButton1(MouseEvent event) {
        levelButton2.setSelected(false);
        levelButton3.setSelected(false);
        levelButton1.setSelected(true);
    }

    @FXML
    void pressLevelButton2(MouseEvent event) {
        levelButton1.setSelected(false);
        levelButton3.setSelected(false);
        levelButton2.setSelected(true);
    }

    @FXML
    void pressLevelButton3(MouseEvent event) {
        levelButton2.setSelected(false);
        levelButton1.setSelected(false);
        levelButton3.setSelected(true);
    }

    @FXML
    void pressSellerButton(MouseEvent event) {
        adminButton.setSelected(false);
        userButton.setSelected(false);
        developerButton.setSelected(false);
        sellerButton.setSelected(true);
        levelButton3.setVisible(false);
        levelButton2.setVisible(false);
        levelButton1.setVisible(false);
    }

    @FXML
    void pressUserButton(MouseEvent event) {
        adminButton.setSelected(false);
        sellerButton.setSelected(false);
        developerButton.setSelected(false);
        userButton.setSelected(true);
        levelButton3.setVisible(false);
        levelButton2.setVisible(false);
        levelButton1.setVisible(false);
    }

    @FXML
    void pressBackButton(MouseEvent event) throws IOException {
        Stage stage=(Stage) backButton.getScene().getWindow();
        stage.close();
        Stage newStage=new Stage();
        FXMLLoader fxmlLoader ;
        if (LoginPage.getAccessLevel().equals(AccessLevel.user)) {
            fxmlLoader = new FXMLLoader(getClass().getResource("LoginPage.fxml"));
        }else {
            fxmlLoader = new FXMLLoader(getClass().getResource("UsersPage.fxml"));
        }
        Scene scene = new Scene(fxmlLoader.load());
        newStage.initStyle(StageStyle.UNDECORATED);
        newStage.setScene(scene);
        newStage.show();
    }

    @FXML
    void pressDoneButton(MouseEvent event) throws SQLException, IOException {
        checkErrors();
    }

    @FXML
    void pressExitButton(MouseEvent event) {
        System.exit(0);
    }

    private void checkErrors() throws SQLException, IOException {
        if (usernameText.getText().equals("")||passwordText.getText().equals("")||repeatPasswordText.getText().equals("")||firstNameText.getText().equals("")||lastNameText.getText().equals("")||ageText.getText().equals("")||emailText.getText().equals("")||phoneText.getText().equals("")) {
            errorLabel.setVisible(true);
            errorLabel.setText("fill the items!");
        } else if (checkUsername()) {
            errorLabel.setVisible(true);
            errorLabel.setText("this username has been used!");
        } else if (!Pattern.matches("[0-9]{1,2}",ageText.getText())){
            errorLabel.setVisible(true);
            errorLabel.setText("the age is incorrect!");
        } else if (checkPhone()!=null) {
            errorLabel.setVisible(true);
            errorLabel.setText(checkPhone());
        }else if (checkEmail()!=null) {
            errorLabel.setVisible(true);
            errorLabel.setText(checkEmail());
        } else if (checkPassword()) {
            errorLabel.setVisible(true);
            errorLabel.setText("password should contain at least 8 character and \nuse upper and lower case character and number!");
        } else if (!passwordText.getText().equals(repeatPasswordText.getText())){
            errorLabel.setVisible(true);
            errorLabel.setText("passwords is different !");
        }else {
            setUser();
            openStage();
        }
    }

    private boolean checkUsername() throws SQLException {
        java.sql.Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/data", "root", "");
        Statement statement = connection.createStatement();
        ResultSet resultSet = null;
        if (userButton.isSelected()) {
            resultSet = statement.executeQuery("SELECT * FROM user");
        }else if (adminButton.isSelected()){
            resultSet = statement.executeQuery("SELECT * FROM admin");
        }else if (developerButton.isSelected()){
            resultSet = statement.executeQuery("SELECT * FROM developer");
        }else if (sellerButton.isSelected()){
            resultSet = statement.executeQuery("SELECT * FROM seller");
        }
        while (resultSet.next()) {
            if (resultSet.getString("username").equals(usernameText.getText())) {
                return true;
            }
        }
        return false;
    }

    private String checkPhone() throws SQLException {
        if (!Pattern.matches("09[0-9]{9}",phoneText.getText())) {
            return "the phone is incorrect!";
        }else{
            java.sql.Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/data","root","");
            Statement statement=connection.createStatement();
            ResultSet resultSet = null;
            if (userButton.isSelected()) {
                resultSet = statement.executeQuery("SELECT * FROM user");
            }else if (adminButton.isSelected()){
                resultSet = statement.executeQuery("SELECT * FROM admin");
            }else if (developerButton.isSelected()){
                resultSet = statement.executeQuery("SELECT * FROM developer");
            }else if (sellerButton.isSelected()){
                resultSet = statement.executeQuery("SELECT * FROM seller");
            }
            while (resultSet.next()){
                if (resultSet.getString("phone").equals(phoneText.getText())){
                    return "this phone is has been used!";
                }
            }
        }
        return null;
    }

    private String checkEmail() throws SQLException {
        if (!Pattern.matches(".+@.+[.]com",emailText.getText())) {
            return "the email is incorrect!";
        }else{
            java.sql.Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/data","root","");
            Statement statement=connection.createStatement();
            ResultSet resultSet = null;
            if (userButton.isSelected()) {
                resultSet = statement.executeQuery("SELECT * FROM user");
            }else if (adminButton.isSelected()){
                resultSet = statement.executeQuery("SELECT * FROM admin");
            }else if (developerButton.isSelected()){
                resultSet = statement.executeQuery("SELECT * FROM developer");
            }else if (sellerButton.isSelected()){
                resultSet = statement.executeQuery("SELECT * FROM seller");
            }
            while (resultSet.next()){
                if (resultSet.getString("phone").equals(emailText.getText())){
                    return "this email is has been used!";
                }
            }
        }
        return null;
    }

    private boolean checkPassword(){
        if (Pattern.matches(".*[a-z]+.*",passwordText.getText())&&Pattern.matches(".*[A-z]+.*",passwordText.getText())&&Pattern.matches(".*[0-9]+.*",passwordText.getText())&passwordText.getText().length()>=8){
            return false;
        }
        return true;
    }

    private void openStage() throws IOException {
        Stage stage=(Stage) signInPage.getScene().getWindow();
        stage.close();
        Stage newStage=new Stage();
        FXMLLoader fxmlLoader;
        if (LoginPage.getAccessLevel().equals(AccessLevel.user)) {
            fxmlLoader = new FXMLLoader(getClass().getResource("UserPage.fxml"));
        }else {
            fxmlLoader = new FXMLLoader(getClass().getResource("UsersPage.fxml"));
        }
        Scene scene = new Scene(fxmlLoader.load());
        newStage.initStyle(StageStyle.UNDECORATED);
        newStage.setScene(scene);
        newStage.show();
    }

    private void setUser() throws SQLException {
        java.sql.Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/data", "root", "");
        Statement statement = connection.createStatement();
        if (LoginPage.getAccessLevel().equals(AccessLevel.user)||userButton.isSelected()) {
            LoginPage.getUser().setEmail(emailText.getText());
            LoginPage.getUser().setAge(Integer.parseInt(ageText.getText()));
            LoginPage.getUser().setFirstname(firstNameText.getText());
            LoginPage.getUser().setLastname(lastNameText.getText());
            LoginPage.getUser().setPhone(phoneText.getText());
            LoginPage.getUser().setPassword(passwordText.getText());
            LoginPage.getUser().setUsername(usernameText.getText());
            LoginPage.getUser().setWallet(0);
            LoginPage.getUser().setTimeSpend(0);
            LoginPage.setStartTime(System.currentTimeMillis());
            statement.executeUpdate("INSERT INTO user(email,firstname,lastname,phone,age,username,password,wallet,timeSpend,friends,games,equipment,level,inbox) VALUE(" + toSQLString(emailText.getText()) + "," + toSQLString(firstNameText.getText()) + "," + toSQLString(lastNameText.getText()) + "," + toSQLString(phoneText.getText()) + "," + Integer.parseInt(ageText.getText()) + "," + toSQLString(usernameText.getText()) + "," + toSQLString(passwordText.getText()) + ",0,0,'','',''," + toSQLString(String.valueOf(Level.LEVEL_1)) + ",'')");
        }else if (adminButton.isSelected()){
            statement.executeUpdate("INSERT INTO admin(email,firstname,lastname,phone,age,username,password) VALUE(" + toSQLString(emailText.getText()) + "," + toSQLString(firstNameText.getText()) + "," + toSQLString(lastNameText.getText()) + "," + toSQLString(phoneText.getText()) + "," + Integer.parseInt(ageText.getText()) + "," + toSQLString(usernameText.getText()) + "," + toSQLString(passwordText.getText()) + ")");
        }else if (developerButton.isSelected()){
            DeveloperLevel developerLevel = null;
            if (levelButton1.isSelected()){
                developerLevel=DeveloperLevel.LEVEL_1;
            }else if (levelButton2.isSelected()){
                developerLevel=DeveloperLevel.LEVEL_2;
            }else if (levelButton3.isSelected()){
                developerLevel=DeveloperLevel.LEVEL_3;
            }
            statement.executeUpdate("INSERT INTO developer(email,firstname,lastname,phone,age,username,password,game,inbox,scheduledEvents,level) VALUE(" + toSQLString(emailText.getText()) + "," + toSQLString(firstNameText.getText()) + "," + toSQLString(lastNameText.getText()) + "," + toSQLString(phoneText.getText()) + "," + Integer.parseInt(ageText.getText()) + "," + toSQLString(usernameText.getText()) + "," + toSQLString(passwordText.getText()) + ",'','','','"+developerLevel+"')");
        }else if (sellerButton.isSelected()){
            statement.executeUpdate("INSERT INTO seller(email,firstname,lastname,phone,age,username,password,equipment) VALUE(" + toSQLString(emailText.getText()) + "," + toSQLString(firstNameText.getText()) + "," + toSQLString(lastNameText.getText()) + "," + toSQLString(phoneText.getText()) + "," + Integer.parseInt(ageText.getText()) + "," + toSQLString(usernameText.getText()) + "," + toSQLString(passwordText.getText()) + ",'')");
        }
    }

    private String toSQLString(String word){
        return "'"+word+"'";
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(LoginPage.getAccessLevel().equals(AccessLevel.admin)){
            signInLabel.setText("new user");
            userButton.setVisible(true);
            adminButton.setVisible(true);
            developerButton.setVisible(true);
            sellerButton.setVisible(true);
        }else {
            LoginPage.setAccessLevel(AccessLevel.user);
        }
    }
}

