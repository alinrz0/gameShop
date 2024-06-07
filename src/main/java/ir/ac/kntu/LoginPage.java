package ir.ac.kntu;

import com.jfoenix.controls.JFXButton;
import ir.ac.kntu.objects.Admin;
import ir.ac.kntu.objects.Developer;
import ir.ac.kntu.objects.Seller;
import ir.ac.kntu.objects.User;
import ir.ac.kntu.types.AccessLevel;
import ir.ac.kntu.types.Level;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class LoginPage {
    private static long startTime;

    private static long endTime;

    private boolean username=false;

    private boolean password=false;

    private static User user=new User();

    private static Admin admin=new Admin();

    private static Developer developer=new Developer();

    private static Seller seller=new Seller();

    private static AccessLevel accessLevel=null;

    public static AccessLevel getAccessLevel() {
        return accessLevel;
    }

    public static void setAccessLevel(AccessLevel accessLevel) {
        LoginPage.accessLevel = accessLevel;
    }



    @FXML
    private AnchorPane loginPage;

    @FXML
    private RadioButton adminButton;

    @FXML
    private RadioButton developerButton;

    @FXML
    private JFXButton exitButton;
    @FXML
    private Label errorLabel;

    @FXML
    private JFXButton loginButton;

    @FXML
    private PasswordField passwordPasswordField;

    @FXML
    private RadioButton sellerButton;

    @FXML
    private JFXButton singInButton;

    @FXML
    private RadioButton userButton;

    @FXML
    private TextField usernameTextField;

    @FXML
    void pressLoginButton(MouseEvent event) throws SQLException, IOException {
        if (usernameTextField.getText().equals("")||passwordPasswordField.getText().equals("")){
            errorLabel.setVisible(true);
            errorLabel.setText("fill the items!");
        }else if (userButton.isSelected()){
            readSQLUser(AccessLevel.user);
            checkData("UserPage.fxml",AccessLevel.user);
            accessLevel=AccessLevel.user;
        }else if (adminButton.isSelected()){
            readSQLUser(AccessLevel.admin);
            checkData("AdminPage.fxml",AccessLevel.admin);
            accessLevel=AccessLevel.admin;
        }else if (sellerButton.isSelected()){
            readSQLUser(AccessLevel.seller);
            checkData("SellerPage.fxml",AccessLevel.seller);
            accessLevel=AccessLevel.seller;
        }else if (developerButton.isSelected()){
            readSQLUser(AccessLevel.developer);
            checkData("DeveloperPage.fxml",AccessLevel.developer);
            accessLevel=AccessLevel.developer;
        }
    }

    @FXML
    void pressSingInButton(MouseEvent event) throws IOException {
        accessLevel=AccessLevel.user;
        Stage stage=(Stage) singInButton.getScene().getWindow();
        stage.close();
        Stage newStage=new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SignInPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        newStage.initStyle(StageStyle.UNDECORATED);
        newStage.setScene(scene);
        newStage.show();

    }

    @FXML
    void pressUserButton(ActionEvent event) {
        adminButton.setSelected(false);
        sellerButton.setSelected(false);
        developerButton.setSelected(false);
    }

    @FXML
    void pressAdminButton(ActionEvent event) {
        userButton.setSelected(false);
        sellerButton.setSelected(false);
        developerButton.setSelected(false);
    }

    @FXML
    void pressDeveloperButton(ActionEvent event) {
        adminButton.setSelected(false);
        sellerButton.setSelected(false);
        userButton.setSelected(false);
    }

    @FXML
    void pressSellerButton(ActionEvent event) {
        adminButton.setSelected(false);
        userButton.setSelected(false);
        developerButton.setSelected(false);
    }


    @FXML
    void pressExitButton(MouseEvent event) {
        System.exit(0);
    }

    public static User getUser() {
        return user;
    }

    public static void setUser(User user) {
        LoginPage.user = user;
    }

    public static Admin getAdmin() {
        return admin;
    }

    public static void setAdmin(Admin admin) {
        LoginPage.admin = admin;
    }

    public static Developer getDeveloper() {
        return developer;
    }

    public static void setDeveloper(Developer developer) {
        LoginPage.developer = developer;
    }

    public static Seller getSeller() {
        return seller;
    }

    public static void setSeller(Seller seller) {
        LoginPage.seller = seller;
    }

    public static long getStartTime() {
        return startTime;
    }

    public static void setStartTime(long startTime) {
        LoginPage.startTime = startTime;
    }

    public static long getEndTime() {
        return endTime;
    }

    public static void setEndTime(long endTime) {
        LoginPage.endTime = endTime;
    }

    private void readSQLUser(AccessLevel accessLevel) throws SQLException {
        java.sql.Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/data","root","");
        Statement statement=connection.createStatement();
        ResultSet resultSet=statement.executeQuery("SELECT * FROM "+accessLevel);
        while (resultSet.next()){
            if(resultSet.getString("username").equals(usernameTextField.getText())){
                username=true;
                if (resultSet.getString("password").equals(passwordPasswordField.getText())){
                    password=true;
                }
                break;
            }
        }

    }

    private void checkData(String fxmlName,AccessLevel accessLevel) throws IOException, SQLException {
        if (username==false){
            errorLabel.setVisible(true);
            errorLabel.setText("this username doesn't exist!");
        }else if(password==false){
            errorLabel.setVisible(true);
            errorLabel.setText("the password is wrong!");
        }
        else {
            setUser(accessLevel);
            openStage(fxmlName);
        }
        username=false;
        password=false;
    }

    private void openStage(String fxmlName) throws IOException {
        Stage stage=(Stage) loginPage.getScene().getWindow();
        stage.close();
        Stage newStage=new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlName));
        Scene scene = new Scene(fxmlLoader.load());
        newStage.initStyle(StageStyle.UNDECORATED);
        newStage.setScene(scene);
        newStage.show();
    }

    private void setUser(AccessLevel accessLevel) throws SQLException {
        java.sql.Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/data","root","");
        Statement statement=connection.createStatement();
        ResultSet resultSet=statement.executeQuery("SELECT * FROM "+accessLevel);
        while (resultSet.next()) {
            if (resultSet.getString("username").equals(usernameTextField.getText())) {
                if (userButton.isSelected()){
                    user.setId(resultSet.getInt("id"));
                    user.setEmail(resultSet.getString("email"));
                    user.setAge(resultSet.getInt("age"));
                    user.setFirstname(resultSet.getString("firstname"));
                    user.setLastname(resultSet.getString("lastname"));
                    user.setPhone(resultSet.getString("phone"));
                    user.setPassword(resultSet.getString("password"));
                    user.setUsername(resultSet.getString("username"));
                    user.setWallet(resultSet.getInt("wallet"));
                    user.setTimeSpend(resultSet.getInt("timeSpend"));
                    user.setGames(stringToArray(resultSet.getString("games")));
                    user.setFriends(stringToArray(resultSet.getString("friends")));
                    user.setEquipment(stringToArray(resultSet.getString("equipment")));
                    user.setLevel(Level.valueOf(resultSet.getString("level")));
                    user.setInbox(stringToArray(resultSet.getString("inbox")));
                    startTime=System.currentTimeMillis();
                }else if(adminButton.isSelected()){
                    admin.setId(resultSet.getInt("id"));
                    admin.setEmail(resultSet.getString("email"));
                    admin.setAge(resultSet.getInt("age"));
                    admin.setFirstname(resultSet.getString("firstname"));
                    admin.setLastname(resultSet.getString("lastname"));
                    admin.setPhone(resultSet.getString("phone"));
                    admin.setPassword(resultSet.getString("password"));
                    admin.setUsername(resultSet.getString("username"));
                }else if (developerButton.isSelected()){
                    developer.setId(resultSet.getInt("id"));
                    developer.setEmail(resultSet.getString("email"));
                    developer.setAge(resultSet.getInt("age"));
                    developer.setFirstname(resultSet.getString("firstname"));
                    developer.setLastname(resultSet.getString("lastname"));
                    developer.setPhone(resultSet.getString("phone"));
                    developer.setPassword(resultSet.getString("password"));
                    developer.setUsername(resultSet.getString("username"));
                    developer.setGames(stringToArray(resultSet.getString("game")));
                    developer.setInbox(stringToArray(resultSet.getString("inbox")));
                    developer.setScheduledEvents(stringToArray(resultSet.getString("scheduledEvents")));
                } else if (sellerButton.isSelected()) {
                    seller.setId(resultSet.getInt("id"));
                    seller.setEmail(resultSet.getString("email"));
                    seller.setAge(resultSet.getInt("age"));
                    seller.setFirstname(resultSet.getString("firstname"));
                    seller.setLastname(resultSet.getString("lastname"));
                    seller.setPhone(resultSet.getString("phone"));
                    seller.setPassword(resultSet.getString("password"));
                    seller.setUsername(resultSet.getString("username"));
                    seller.setEquipments(stringToArray(resultSet.getString("equipment")));
                }
                break;
            }
        }
    }

    public static ArrayList<String> stringToArray(String line){
        String[] valuesArray = line.split(",");
        ArrayList<String> valuesList = new ArrayList<>(Arrays.asList(valuesArray));
        return valuesList;
    }

    public static HashMap<String,Integer> toHashmap1(String line){
        HashMap<String, Integer> keyValueMap = new HashMap<>();
        String[] keyValueArray = line.split(",");
        if(keyValueArray.length>1)
        for (int i = 0; i < keyValueArray.length; i += 2) {
            String key = keyValueArray[i];
            int value = Integer.parseInt(keyValueArray[i + 1]);
            keyValueMap.put(key, value);
        }

        return keyValueMap;
    }

    public static HashMap<String,Double> toHashmap2(String line){
        HashMap<String, Double> keyValueMap = new HashMap<>();
        String[] keyValueArray = line.split(",");
        if(keyValueArray.length>1)
            for (int i = 0; i < keyValueArray.length; i += 2) {
                String key = keyValueArray[i];
                double value = Double.parseDouble(keyValueArray[i + 1]);
                keyValueMap.put(key, value);
            }

        return keyValueMap;
    }

    public static void addTime() throws SQLException {
        java.sql.Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/data","root","");
        Statement statement=connection.createStatement();
        user.setTimeSpend(user.getTimeSpend()+endTime/1000-startTime/1000);
        user.setLevel();
        statement.executeUpdate("UPDATE user SET timeSpend="+user.getTimeSpend()+" ,level='"+user.getLevel()+"' WHERE id="+LoginPage.getUser().getId()+"");
    }

}
