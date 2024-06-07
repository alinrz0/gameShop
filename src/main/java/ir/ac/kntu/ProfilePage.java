package ir.ac.kntu;

import com.jfoenix.controls.JFXButton;
import ir.ac.kntu.types.AccessLevel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class ProfilePage implements Initializable {
    private boolean mouseMovement=true;

    private int id;

    @FXML
    private TextField ageText;

    @FXML
    private JFXButton backButton;

    @FXML
    private Button chargeButton;

    @FXML
    private JFXButton doneButton;

    @FXML
    private TextField emailText;

    @FXML
    private Label errorLabel;

    @FXML
    private JFXButton exitButton;

    @FXML
    private TextField firstNameText;

    @FXML
    private TextField lastNameText;

    @FXML
    private PasswordField passwordText;

    @FXML
    private TextField phoneText;

    @FXML
    private AnchorPane profilePage;

    @FXML
    private TextField usernameText;

    @FXML
    private Label walletLabel;

    @FXML
    private TextField walletText;

    @FXML
    void pressBackButton(MouseEvent event) throws IOException {
        Stage stage=(Stage) backButton.getScene().getWindow();
        stage.close();
        Stage newStage=new Stage();
        FXMLLoader fxmlLoader = null;
        if (LoginPage.getAccessLevel().equals(AccessLevel.user)) {
            fxmlLoader = new FXMLLoader(getClass().getResource("UserPage.fxml"));
        }else if(LoginPage.getAccessLevel().equals(AccessLevel.admin)) {
            fxmlLoader = new FXMLLoader(getClass().getResource("AdminPage.fxml"));
        }else if(LoginPage.getAccessLevel().equals(AccessLevel.developer)){
            fxmlLoader = new FXMLLoader(getClass().getResource("DeveloperPage.fxml"));
        }else if(LoginPage.getAccessLevel().equals(AccessLevel.seller)){
            fxmlLoader = new FXMLLoader(getClass().getResource("SellerPage.fxml"));
        }
        Scene scene = new Scene(fxmlLoader.load());
        newStage.initStyle(StageStyle.UNDECORATED);
        newStage.setScene(scene);
        newStage.show();
    }

    @FXML
    void pressChargeButton(MouseEvent event) throws IOException {
        Stage stage=(Stage) chargeButton.getScene().getWindow();
        stage.close();
        Stage newStage=new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Wallet.fxml"));
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
    void pressExitButton(MouseEvent event) throws SQLException {
        if (LoginPage.getAccessLevel().equals(AccessLevel.user)){
            LoginPage.setEndTime(System.currentTimeMillis());
            LoginPage.addTime();
        }
        System.exit(0);
    }

    private void setUserLabel(){
        usernameText.setText(LoginPage.getUser().getUsername());
        firstNameText.setText(LoginPage.getUser().getFirstname());
        lastNameText.setText(LoginPage.getUser().getLastname());
        ageText.setText(String.valueOf(LoginPage.getUser().getAge()));
        passwordText.setText(LoginPage.getUser().getPassword());
        phoneText.setText(LoginPage.getUser().getPhone());
        emailText.setText(LoginPage.getUser().getEmail());
        walletText.setText(String.valueOf(LoginPage.getUser().getWallet()));
    }

    private void setAdminLabel(){
        usernameText.setText(LoginPage.getAdmin().getUsername());
        firstNameText.setText(LoginPage.getAdmin().getFirstname());
        lastNameText.setText(LoginPage.getAdmin().getLastname());
        ageText.setText(String.valueOf(LoginPage.getAdmin().getAge()));
        passwordText.setText(LoginPage.getAdmin().getPassword());
        phoneText.setText(LoginPage.getAdmin().getPhone());
        emailText.setText(LoginPage.getAdmin().getEmail());
    }

    private void setDeveloperLabel(){
        usernameText.setText(LoginPage.getDeveloper().getUsername());
        firstNameText.setText(LoginPage.getDeveloper().getFirstname());
        lastNameText.setText(LoginPage.getDeveloper().getLastname());
        ageText.setText(String.valueOf(LoginPage.getDeveloper().getAge()));
        passwordText.setText(LoginPage.getDeveloper().getPassword());
        phoneText.setText(LoginPage.getDeveloper().getPhone());
        emailText.setText(LoginPage.getDeveloper().getEmail());
    }

    private void setSellerLabel(){
        usernameText.setText(LoginPage.getSeller().getUsername());
        firstNameText.setText(LoginPage.getSeller().getFirstname());
        lastNameText.setText(LoginPage.getSeller().getLastname());
        ageText.setText(String.valueOf(LoginPage.getSeller().getAge()));
        passwordText.setText(LoginPage.getSeller().getPassword());
        phoneText.setText(LoginPage.getSeller().getPhone());
        emailText.setText(LoginPage.getSeller().getEmail());
    }

    private void checkErrors() throws SQLException, IOException {
        errorLabel.setTextFill(Color.RED);
        if ((LoginPage.getAccessLevel().equals(AccessLevel.user)&&usernameText.getText().equals(LoginPage.getUser().getUsername())&&passwordText.getText().equals(LoginPage.getUser().getPassword())&&firstNameText.getText().equals(LoginPage.getUser().getFirstname())&&lastNameText.getText().equals(LoginPage.getUser().getLastname())&&ageText.getText().equals(String.valueOf(LoginPage.getUser().getAge()))&&emailText.getText().equals(LoginPage.getUser().getEmail())&&phoneText.getText().equals(LoginPage.getUser().getPhone()))||
                (LoginPage.getAccessLevel().equals(AccessLevel.admin)&&usernameText.getText().equals(LoginPage.getAdmin().getUsername())&&passwordText.getText().equals(LoginPage.getAdmin().getPassword())&&firstNameText.getText().equals(LoginPage.getAdmin().getFirstname())&&lastNameText.getText().equals(LoginPage.getAdmin().getLastname())&&ageText.getText().equals(String.valueOf(LoginPage.getAdmin().getAge()))&&emailText.getText().equals(LoginPage.getAdmin().getEmail())&&phoneText.getText().equals(LoginPage.getAdmin().getPhone()))||
                (LoginPage.getAccessLevel().equals(AccessLevel.developer)&&usernameText.getText().equals(LoginPage.getDeveloper().getUsername())&&passwordText.getText().equals(LoginPage.getDeveloper().getPassword())&&firstNameText.getText().equals(LoginPage.getDeveloper().getFirstname())&&lastNameText.getText().equals(LoginPage.getDeveloper().getLastname())&&ageText.getText().equals(String.valueOf(LoginPage.getDeveloper().getAge()))&&emailText.getText().equals(LoginPage.getDeveloper().getEmail())&&phoneText.getText().equals(LoginPage.getDeveloper().getPhone()))||
                (LoginPage.getAccessLevel().equals(AccessLevel.seller)&&usernameText.getText().equals(LoginPage.getSeller().getUsername())&&passwordText.getText().equals(LoginPage.getSeller().getPassword())&&firstNameText.getText().equals(LoginPage.getSeller().getFirstname())&&lastNameText.getText().equals(LoginPage.getSeller().getLastname())&&ageText.getText().equals(String.valueOf(LoginPage.getSeller().getAge()))&&emailText.getText().equals(LoginPage.getSeller().getEmail())&&phoneText.getText().equals(LoginPage.getSeller().getPhone()))) {
            errorLabel.setVisible(true);
            errorLabel.setText("no item changed!");
        }else if (usernameText.getText().equals("")||passwordText.getText().equals("")||firstNameText.getText().equals("")||lastNameText.getText().equals("")||ageText.getText().equals("")||emailText.getText().equals("")||phoneText.getText().equals("")) {
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
        }else {
            errorLabel.setVisible(true);
            errorLabel.setText("change done!");
            errorLabel.setTextFill(Color.GREEN);
            updateSQL();
            changeInformation();
        }
    }


    private boolean checkUsername() throws SQLException {
        Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/data","root","");
        Statement statement=connection.createStatement();
        ResultSet resultSet=statement.executeQuery("SELECT * FROM "+LoginPage.getAccessLevel());
        while (resultSet.next()){
            if ((LoginPage.getAccessLevel().equals(AccessLevel.user)&&resultSet.getString("username").equals(LoginPage.getUser().getUsername()))||
                    (LoginPage.getAccessLevel().equals(AccessLevel.admin)&&resultSet.getString("username").equals(LoginPage.getAdmin().getUsername()))||
                    (LoginPage.getAccessLevel().equals(AccessLevel.developer)&&resultSet.getString("username").equals(LoginPage.getDeveloper().getUsername()))||
                    (LoginPage.getAccessLevel().equals(AccessLevel.seller)&&resultSet.getString("username").equals(LoginPage.getSeller().getUsername()))){
                continue;
            }
            if (resultSet.getString("username").equals(usernameText.getText())){
                return true;
            }
        }
        return false;
    }

    private String checkPhone() throws SQLException {
        if (!Pattern.matches("09[0-9]{9}",phoneText.getText())) {
            return "the phone is incorrect!";
        }else{
            Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/data","root","");
            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery("SELECT * FROM "+LoginPage.getAccessLevel());
            while (resultSet.next()){
                if ((LoginPage.getAccessLevel().equals(AccessLevel.user)&&resultSet.getString("phone").equals(LoginPage.getUser().getPhone()))||
                        (LoginPage.getAccessLevel().equals(AccessLevel.admin)&&resultSet.getString("phone").equals(LoginPage.getAdmin().getPhone()))||
                        (LoginPage.getAccessLevel().equals(AccessLevel.developer)&&resultSet.getString("phone").equals(LoginPage.getDeveloper().getPhone()))||
                        (LoginPage.getAccessLevel().equals(AccessLevel.seller)&&resultSet.getString("phone").equals(LoginPage.getSeller().getPhone()))){
                    continue;
                }
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
            Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/data","root","");
            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery("SELECT * FROM "+LoginPage.getAccessLevel());
            while (resultSet.next()){
                if ((LoginPage.getAccessLevel().equals(AccessLevel.user)&&resultSet.getString("email").equals(LoginPage.getUser().getEmail()))||
                        (LoginPage.getAccessLevel().equals(AccessLevel.admin)&&resultSet.getString("email").equals(LoginPage.getAdmin().getEmail()))||
                        (LoginPage.getAccessLevel().equals(AccessLevel.developer)&&resultSet.getString("email").equals(LoginPage.getDeveloper().getEmail()))||
                        (LoginPage.getAccessLevel().equals(AccessLevel.seller)&&resultSet.getString("email").equals(LoginPage.getSeller().getEmail()))){
                    continue;
                }
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

    private void updateSQL() throws SQLException {
        java.sql.Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/data","root","");
        Statement statement=connection.createStatement();
        statement.executeUpdate("UPDATE "+LoginPage.getAccessLevel()+" SET phone="+toSQLString(phoneText.getText())+" ,lastname="+toSQLString(lastNameText.getText())+",firstname="+toSQLString(firstNameText.getText())+",username="+toSQLString(usernameText.getText())+",email="+toSQLString(emailText.getText())+",password="+toSQLString(passwordText.getText())+",age="+Integer.parseInt(ageText.getText())+" WHERE id="+id+"");
    }

    private void changeInformation(){
        if (LoginPage.getAccessLevel().equals(AccessLevel.user)) {
            LoginPage.getUser().setEmail(emailText.getText());
            LoginPage.getUser().setAge(Integer.parseInt(ageText.getText()));
            LoginPage.getUser().setFirstname(firstNameText.getText());
            LoginPage.getUser().setLastname(lastNameText.getText());
            LoginPage.getUser().setPhone(phoneText.getText());
            LoginPage.getUser().setPassword(passwordText.getText());
            LoginPage.getUser().setUsername(usernameText.getText());
        }else if (LoginPage.getAccessLevel().equals(AccessLevel.admin)){
            LoginPage.getAdmin().setEmail(emailText.getText());
            LoginPage.getAdmin().setAge(Integer.parseInt(ageText.getText()));
            LoginPage.getAdmin().setFirstname(firstNameText.getText());
            LoginPage.getAdmin().setLastname(lastNameText.getText());
            LoginPage.getAdmin().setPhone(phoneText.getText());
            LoginPage.getAdmin().setPassword(passwordText.getText());
            LoginPage.getAdmin().setUsername(usernameText.getText());
        }else if (LoginPage.getAccessLevel().equals(AccessLevel.developer)){
            LoginPage.getDeveloper().setEmail(emailText.getText());
            LoginPage.getDeveloper().setAge(Integer.parseInt(ageText.getText()));
            LoginPage.getDeveloper().setFirstname(firstNameText.getText());
            LoginPage.getDeveloper().setLastname(lastNameText.getText());
            LoginPage.getDeveloper().setPhone(phoneText.getText());
            LoginPage.getDeveloper().setPassword(passwordText.getText());
            LoginPage.getDeveloper().setUsername(usernameText.getText());
        }else if (LoginPage.getAccessLevel().equals(AccessLevel.seller)){
            LoginPage.getSeller().setEmail(emailText.getText());
            LoginPage.getSeller().setAge(Integer.parseInt(ageText.getText()));
            LoginPage.getSeller().setFirstname(firstNameText.getText());
            LoginPage.getSeller().setLastname(lastNameText.getText());
            LoginPage.getSeller().setPhone(phoneText.getText());
            LoginPage.getSeller().setPassword(passwordText.getText());
            LoginPage.getSeller().setUsername(usernameText.getText());
        }
    }

    private String toSQLString(String word){
        return "'"+word+"'";
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (LoginPage.getAccessLevel().equals(AccessLevel.user)) {
            walletLabel.setVisible(true);
            walletText.setVisible(true);
            chargeButton.setVisible(true);
            id=LoginPage.getUser().getId();
            setUserLabel();
        } else if (LoginPage.getAccessLevel().equals(AccessLevel.admin)) {
            id=LoginPage.getAdmin().getId();
            setAdminLabel();
        } else if (LoginPage.getAccessLevel().equals(AccessLevel.developer)) {
            id=LoginPage.getDeveloper().getId();
            setDeveloperLabel();
        } else if (LoginPage.getAccessLevel().equals(AccessLevel.seller)) {
            id=LoginPage.getSeller().getId();
            setSellerLabel();
        }
    }
}


