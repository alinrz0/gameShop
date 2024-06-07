package ir.ac.kntu;

import com.jfoenix.controls.JFXButton;
import ir.ac.kntu.objects.Game;
import ir.ac.kntu.objects.User;
import ir.ac.kntu.types.AccessLevel;
import ir.ac.kntu.types.Friends;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Inbox implements Initializable {
    private ArrayList<User> allUsers = new ArrayList<>();

    private ArrayList<Game> allGames = new ArrayList<>();

    private ArrayList<String> inbox = new ArrayList<>();

    private int page = 1;

    private int numberOfInbox;
    @FXML
    private JFXButton backButton;

    @FXML
    private JFXButton exitButton;

    @FXML
    private Label nameLabel1;

    @FXML
    private Label nameLabel2;

    @FXML
    private Label nameLabel3;

    @FXML
    private Label nameLabel4;

    @FXML
    private Label nameLabel5;

    @FXML
    private Label nameLabel6;

    @FXML
    private Label nameLabel7;

    @FXML
    private Label nameLabel8;

    @FXML
    private JFXButton pageButton1;

    @FXML
    private JFXButton pageButton2;

    @FXML
    private JFXButton pageButton3;

    @FXML
    private JFXButton pageButton4;

    @FXML
    private JFXButton pageButton5;

    @FXML
    private JFXButton pageButton6;

    @FXML
    private JFXButton pageButton7;

    @FXML
    private Label pageName;

    @FXML
    private JFXButton rejectButton1;

    @FXML
    private JFXButton rejectButton2;

    @FXML
    private JFXButton rejectButton3;

    @FXML
    private JFXButton rejectButton4;

    @FXML
    private JFXButton rejectButton5;

    @FXML
    private JFXButton rejectButton6;

    @FXML
    private JFXButton rejectButton7;

    @FXML
    private JFXButton rejectButton8;

    @FXML
    private JFXButton tickButton1;

    @FXML
    private JFXButton tickButton2;

    @FXML
    private JFXButton tickButton3;

    @FXML
    private JFXButton tickButton4;

    @FXML
    private JFXButton tickButton5;

    @FXML
    private JFXButton tickButton6;

    @FXML
    private JFXButton tickButton7;

    @FXML
    private JFXButton tickButton8;

    @FXML
    private HBox user1;

    @FXML
    private HBox user2;

    @FXML
    private HBox user3;

    @FXML
    private HBox user4;

    @FXML
    private HBox user5;

    @FXML
    private HBox user6;

    @FXML
    private HBox user7;

    @FXML
    private HBox user8;

    @FXML
    void pressBackButton(MouseEvent event) throws IOException {
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.close();
        Stage newStage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("UsersPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        newStage.initStyle(StageStyle.UNDECORATED);
        newStage.setScene(scene);
        newStage.show();
    }

    @FXML
    void pressExitButton(MouseEvent event) throws SQLException {
        if (LoginPage.getAccessLevel().equals(AccessLevel.user)) {
            LoginPage.setEndTime(System.currentTimeMillis());
            LoginPage.addTime();
        }
        System.exit(0);
    }

    @FXML
    void pressPageButton1(MouseEvent event) {
        if (pageButton1.getText().equals("<")) {
            page--;
        } else if (pageButton1.getText().equals("<<")) {
            page = 1;
        } else if (pageButton1.getText().equals(">")) {
            page++;
        } else if (pageButton1.getText().equals(">>")) {
            page = (int) Math.ceil((float) numberOfInbox / 8);
        } else {
            page = Integer.parseInt(pageButton1.getText());
        }
        backPageButtonToDefault();
        setPageButton();
        setUsersButton();
    }

    @FXML
    void pressPageButton2(MouseEvent event) {
        if (pageButton2.getText().equals("<")) {
            page--;
        } else if (pageButton2.getText().equals("<<")) {
            page = 1;
        } else if (pageButton2.getText().equals(">")) {
            page++;
        } else if (pageButton2.getText().equals(">>")) {
            page = (int) Math.ceil((float) numberOfInbox / 8);
        } else {
            page = Integer.parseInt(pageButton2.getText());
        }
        backPageButtonToDefault();
        setPageButton();
        setUsersButton();
    }

    @FXML
    void pressPageButton3(MouseEvent event) {
        if (pageButton3.getText().equals("<")) {
            page--;
        } else if (pageButton3.getText().equals("<<")) {
            page = 1;
        } else if (pageButton3.getText().equals(">")) {
            page++;
        } else if (pageButton3.getText().equals(">>")) {
            page = (int) Math.ceil((float) numberOfInbox / 8);
        } else {
            page = Integer.parseInt(pageButton3.getText());
        }
        backPageButtonToDefault();
        setPageButton();
        setUsersButton();
    }

    @FXML
    void pressPageButton4(MouseEvent event) {
        if (pageButton4.getText().equals("<")) {
            page--;
        } else if (pageButton4.getText().equals("<<")) {
            page = 1;
        } else if (pageButton4.getText().equals(">")) {
            page++;
        } else if (pageButton4.getText().equals(">>")) {
            page = (int) Math.ceil((float) numberOfInbox / 8);
        } else {
            page = Integer.parseInt(pageButton4.getText());
        }
        backPageButtonToDefault();
        setPageButton();
        setUsersButton();
    }

    @FXML
    void pressPageButton5(MouseEvent event) {
        if (pageButton5.getText().equals("<")) {
            page--;
        } else if (pageButton5.getText().equals("<<")) {
            page = 1;
        } else if (pageButton5.getText().equals(">")) {
            page++;
        } else if (pageButton5.getText().equals(">>")) {
            page = (int) Math.ceil((float) numberOfInbox / 8);
        } else {
            page = Integer.parseInt(pageButton5.getText());
        }
        backPageButtonToDefault();
        setPageButton();
        setUsersButton();
    }

    @FXML
    void pressPageButton6(MouseEvent event) {
        if (pageButton6.getText().equals("<")) {
            page--;
        } else if (pageButton6.getText().equals("<<")) {
            page = 1;
        } else if (pageButton6.getText().equals(">")) {
            page++;
        } else if (pageButton6.getText().equals(">>")) {
            page = (int) Math.ceil((float) numberOfInbox / 8);
        } else {
            page = Integer.parseInt(pageButton6.getText());
        }
        backPageButtonToDefault();
        setPageButton();
        setUsersButton();
    }

    @FXML
    void pressPageButton7(MouseEvent event) {
        if (pageButton7.getText().equals("<")) {
            page--;
        } else if (pageButton7.getText().equals("<<")) {
            page = 1;
        } else if (pageButton7.getText().equals(">")) {
            page++;
        } else if (pageButton7.getText().equals(">>")) {
            page = (int) Math.ceil((float) numberOfInbox / 8);
        } else {
            page = Integer.parseInt(pageButton7.getText());
        }
        backPageButtonToDefault();
        setPageButton();
        setUsersButton();
    }

    @FXML
    void pressRejectButton1(MouseEvent event) throws SQLException, IOException {
        pressRejectButton(nameLabel1);
    }

    @FXML
    void pressRejectButton2(MouseEvent event) throws SQLException, IOException {
        pressRejectButton(nameLabel2);
    }

    @FXML
    void pressRejectButton3(MouseEvent event) throws SQLException, IOException {
        pressRejectButton(nameLabel3);
    }

    @FXML
    void pressRejectButton4(MouseEvent event) throws SQLException, IOException {
        pressRejectButton(nameLabel4);
    }

    @FXML
    void pressRejectButton5(MouseEvent event) throws SQLException, IOException {
        pressRejectButton(nameLabel5);
    }

    @FXML
    void pressRejectButton6(MouseEvent event) throws SQLException, IOException {
        pressRejectButton(nameLabel6);
    }

    @FXML
    void pressRejectButton7(MouseEvent event) throws SQLException, IOException {
        pressRejectButton(nameLabel7);
    }

    @FXML
    void pressRejectButton8(MouseEvent event) throws SQLException, IOException {
        pressRejectButton(nameLabel8);
    }

    @FXML
    void pressTickButton1(MouseEvent event) throws SQLException, IOException {
        pressTicks(nameLabel1);
    }

    @FXML
    void pressTickButton2(MouseEvent event) throws SQLException, IOException {
        pressTicks(nameLabel2);
    }

    @FXML
    void pressTickButton3(MouseEvent event) throws SQLException, IOException {
        pressTicks(nameLabel3);
    }

    @FXML
    void pressTickButton4(MouseEvent event) throws SQLException, IOException {
        pressTicks(nameLabel4);
    }

    @FXML
    void pressTickButton5(MouseEvent event) throws SQLException, IOException {
        pressTicks(nameLabel5);
    }

    @FXML
    void pressTickButton6(MouseEvent event) throws SQLException, IOException {
        pressTicks(nameLabel6);
    }

    @FXML
    void pressTickButton7(MouseEvent event) throws SQLException, IOException {
        pressTicks(nameLabel7);
    }

    @FXML
    void pressTickButton8(MouseEvent event) throws SQLException, IOException {
        pressTicks(nameLabel8);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            setAllUser();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        pageName.setText("Requests");
        if (inbox.get(0).equals("")) {
            inbox.clear();
        }
        numberOfInbox = inbox.size();
        setPageButton();
        setUsersButton();
    }

    public void setAllUser() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/data", "root", "");
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM user");
        while (resultSet.next()) {
            User user = new User();
            user.setId(resultSet.getInt("id"));
            user.setUsername(resultSet.getString("username"));
            user.setFriends(LoginPage.stringToArray(resultSet.getString("friends")));
            user.setFirstname(resultSet.getString("firstname"));
            user.setLastname(resultSet.getString("lastname"));
            user.setAge(resultSet.getInt("age"));
            user.setGames(LoginPage.stringToArray(resultSet.getString("games")));
            user.setEquipment(LoginPage.stringToArray(resultSet.getString("equipment")));
            user.setPhone(resultSet.getString("phone"));
            user.setEmail(resultSet.getString("email"));
            user.setTimeSpend(resultSet.getInt("timeSpend"));
            user.setPassword(resultSet.getString("password"));
            user.setWallet(resultSet.getInt("wallet"));
            user.setInbox(LoginPage.stringToArray(resultSet.getString("inbox")));
            if (user.getUsername().equals(LoginPage.getUser().getUsername())) {
                inbox = user.getInbox();
            }
            allUsers.add(user);
        }
    }

    private void setUsersButton() {
        if (numberOfInbox == 0) {
            user1.setVisible(false);
            user2.setVisible(false);
            user3.setVisible(false);
            user4.setVisible(false);
            user5.setVisible(false);
            user6.setVisible(false);
            user7.setVisible(false);
            user8.setVisible(false);
        } else if (page == (int) Math.ceil((float) numberOfInbox / 8) && numberOfInbox % 8 == 1) {
            user1.setVisible(true);
            user2.setVisible(false);
            user3.setVisible(false);
            user4.setVisible(false);
            user5.setVisible(false);
            user6.setVisible(false);
            user7.setVisible(false);
            user8.setVisible(false);
            nameLabel1.setText(inbox.get(numberOfInbox - 1));
        } else if (page == (int) Math.ceil((float) numberOfInbox / 8) && numberOfInbox % 8 == 2) {
            user1.setVisible(true);
            user2.setVisible(true);
            user3.setVisible(false);
            user4.setVisible(false);
            user5.setVisible(false);
            user6.setVisible(false);
            user7.setVisible(false);
            user8.setVisible(false);
            nameLabel1.setText(inbox.get(numberOfInbox - 2));
            nameLabel2.setText(inbox.get(numberOfInbox - 1));
        } else if (page == (int) Math.ceil((float) numberOfInbox / 8) && numberOfInbox % 8 == 3) {
            user1.setVisible(true);
            user2.setVisible(true);
            user3.setVisible(true);
            user4.setVisible(false);
            user5.setVisible(false);
            user6.setVisible(false);
            user7.setVisible(false);
            user8.setVisible(false);
            nameLabel1.setText(inbox.get(numberOfInbox - 3));
            nameLabel2.setText(inbox.get(numberOfInbox - 2));
            nameLabel3.setText(inbox.get(numberOfInbox - 1));
        } else if (page == (int) Math.ceil((float) numberOfInbox / 8) && numberOfInbox % 8 == 4) {
            user1.setVisible(true);
            user2.setVisible(true);
            user3.setVisible(true);
            user4.setVisible(true);
            user5.setVisible(false);
            user6.setVisible(false);
            user7.setVisible(false);
            user8.setVisible(false);
            nameLabel1.setText(inbox.get(numberOfInbox - 4));
            nameLabel2.setText(inbox.get(numberOfInbox - 3));
            nameLabel3.setText(inbox.get(numberOfInbox - 2));
            nameLabel4.setText(inbox.get(numberOfInbox - 1));
        } else if (page == (int) Math.ceil((float) numberOfInbox / 8) && numberOfInbox % 8 == 5) {
            user1.setVisible(true);
            user2.setVisible(true);
            user3.setVisible(true);
            user4.setVisible(true);
            user5.setVisible(true);
            user6.setVisible(false);
            user7.setVisible(false);
            user8.setVisible(false);
            nameLabel1.setText(inbox.get(numberOfInbox - 5));
            nameLabel2.setText(inbox.get(numberOfInbox - 4));
            nameLabel3.setText(inbox.get(numberOfInbox - 3));
            nameLabel4.setText(inbox.get(numberOfInbox - 2));
            nameLabel5.setText(inbox.get(numberOfInbox - 1));
        } else if (page == (int) Math.ceil((float) numberOfInbox / 8) && numberOfInbox % 8 == 6) {
            user1.setVisible(true);
            user2.setVisible(true);
            user3.setVisible(true);
            user4.setVisible(true);
            user5.setVisible(true);
            user6.setVisible(true);
            user7.setVisible(false);
            user8.setVisible(false);
            nameLabel1.setText(inbox.get(numberOfInbox - 6));
            nameLabel2.setText(inbox.get(numberOfInbox - 5));
            nameLabel3.setText(inbox.get(numberOfInbox - 4));
            nameLabel4.setText(inbox.get(numberOfInbox - 3));
            nameLabel5.setText(inbox.get(numberOfInbox - 2));
            nameLabel6.setText(inbox.get(numberOfInbox - 1));
        } else if (page == (int) Math.ceil((float) numberOfInbox / 8) && numberOfInbox % 8 == 7) {
            user1.setVisible(true);
            user2.setVisible(true);
            user3.setVisible(true);
            user4.setVisible(true);
            user5.setVisible(true);
            user6.setVisible(true);
            user7.setVisible(true);
            user8.setVisible(false);
            nameLabel1.setText(inbox.get(numberOfInbox - 7));
            nameLabel2.setText(inbox.get(numberOfInbox - 6));
            nameLabel3.setText(inbox.get(numberOfInbox - 5));
            nameLabel4.setText(inbox.get(numberOfInbox - 4));
            nameLabel5.setText(inbox.get(numberOfInbox - 3));
            nameLabel6.setText(inbox.get(numberOfInbox - 2));
            nameLabel7.setText(inbox.get(numberOfInbox - 1));
        } else {
            user1.setVisible(true);
            user2.setVisible(true);
            user3.setVisible(true);
            user4.setVisible(true);
            user5.setVisible(true);
            user6.setVisible(true);
            user7.setVisible(true);
            user8.setVisible(true);
            nameLabel1.setText(inbox.get(page * 8 - 8));
            nameLabel2.setText(inbox.get(page * 8 - 7));
            nameLabel3.setText(inbox.get(page * 8 - 6));
            nameLabel4.setText(inbox.get(page * 8 - 5));
            nameLabel5.setText(inbox.get(page * 8 - 4));
            nameLabel6.setText(inbox.get(page * 8 - 3));
            nameLabel7.setText(inbox.get(page * 8 - 2));
            nameLabel7.setText(inbox.get(page * 8 - 1));
        }
    }

    private void setPageButton() {
        if (numberOfInbox <= 8) {
            pageButton1.setVisible(false);
            pageButton2.setVisible(false);
            pageButton3.setVisible(false);
            pageButton4.setVisible(true);
            pageButton5.setVisible(false);
            pageButton6.setVisible(false);
            pageButton7.setVisible(false);
            pageButton4.setText("1");
        } else if (numberOfInbox <= 16 && page == 1) {
            pageButton1.setVisible(false);
            pageButton2.setVisible(false);
            pageButton3.setVisible(true);
            pageButton4.setVisible(true);
            pageButton5.setVisible(true);
            pageButton6.setVisible(false);
            pageButton7.setVisible(false);
            pageButton3.setText("1");
            pageButton3.setStyle("-fx-background-color:  #F0E68C;");
            pageButton4.setText("2");
            pageButton4.setStyle("-fx-background-color:   #D8BFD8;");
            pageButton5.setText(">");
        } else if (numberOfInbox <= 16 && page == 2) {
            pageButton1.setVisible(false);
            pageButton2.setVisible(false);
            pageButton3.setVisible(true);
            pageButton4.setVisible(true);
            pageButton5.setVisible(true);
            pageButton6.setVisible(false);
            pageButton7.setVisible(false);
            pageButton3.setText("<");
            pageButton4.setText("1");
            pageButton5.setText("2");
            pageButton4.setStyle("-fx-background-color:   #D8BFD8;");
            pageButton5.setStyle("-fx-background-color:   #F0E68C;");
        } else if (page == 1) {
            pageButton1.setVisible(false);
            pageButton2.setVisible(true);
            pageButton3.setVisible(true);
            pageButton4.setVisible(true);
            pageButton5.setVisible(true);
            pageButton6.setVisible(true);
            pageButton7.setVisible(false);
            pageButton2.setText("1");
            pageButton6.setText(">>");
            pageButton3.setText("2");
            pageButton4.setText("3");
            pageButton5.setText(">");
            pageButton4.setStyle("-fx-background-color:   #D8BFD8;");
            pageButton2.setStyle("-fx-background-color:   #F0E68C;");
        } else if (numberOfInbox <= 24 && page == 2) {
            pageButton1.setVisible(false);
            pageButton2.setVisible(true);
            pageButton3.setVisible(true);
            pageButton4.setVisible(true);
            pageButton5.setVisible(true);
            pageButton6.setVisible(true);
            pageButton7.setVisible(false);
            pageButton2.setText("<");
            pageButton6.setText(">");
            pageButton3.setText("1");
            pageButton4.setText("2");
            pageButton5.setText("3");
        } else if (page == 2) {
            pageButton1.setVisible(false);
            pageButton2.setVisible(true);
            pageButton3.setVisible(true);
            pageButton4.setVisible(true);
            pageButton5.setVisible(true);
            pageButton6.setVisible(true);
            pageButton7.setVisible(false);
            pageButton7.setText(">>");
            pageButton2.setText("<");
            pageButton6.setText(">");
            pageButton3.setText("1");
            pageButton4.setText("2");
            pageButton5.setText("3");
        } else if (page == (int) Math.ceil((float) numberOfInbox / 8)) {
            pageButton1.setVisible(false);
            pageButton2.setVisible(true);
            pageButton3.setVisible(true);
            pageButton4.setVisible(true);
            pageButton5.setVisible(true);
            pageButton6.setVisible(true);
            pageButton7.setVisible(false);
            pageButton2.setText("<<");
            pageButton6.setText(String.valueOf((int) Math.ceil((float) numberOfInbox / 8)));
            pageButton3.setText("<");
            pageButton4.setText(String.valueOf((int) Math.ceil((float) numberOfInbox / 8) - 2));
            pageButton5.setText(String.valueOf((int) Math.ceil((float) numberOfInbox / 8) - 1));
            pageButton4.setStyle("-fx-background-color:   #D8BFD8;");
            pageButton6.setStyle("-fx-background-color:   #F0E68C;");
        } else if (page == (int) Math.ceil((float) numberOfInbox / 8) - 1 && numberOfInbox > 24) {
            pageButton1.setVisible(true);
            pageButton2.setVisible(true);
            pageButton3.setVisible(true);
            pageButton4.setVisible(true);
            pageButton5.setVisible(true);
            pageButton6.setVisible(true);
            pageButton7.setVisible(false);
            pageButton1.setText("<<");
            pageButton5.setText(String.valueOf((int) Math.ceil((float) numberOfInbox / 8)));
            pageButton2.setText("<");
            pageButton3.setText(String.valueOf((int) Math.ceil((float) numberOfInbox / 8) - 2));
            pageButton4.setText(String.valueOf((int) Math.ceil((float) numberOfInbox / 8) - 1));
            pageButton6.setText(">");
        } else {
            pageButton2.setVisible(true);
            pageButton3.setVisible(true);
            pageButton4.setVisible(true);
            pageButton5.setVisible(true);
            pageButton6.setVisible(true);
            pageButton7.setVisible(true);
            pageButton1.setVisible(true);
            pageButton7.setText(">>");
            pageButton1.setText("<<");
            pageButton5.setText(String.valueOf(page + 1));
            pageButton2.setText("<");
            pageButton3.setText(String.valueOf(page - 1));
            pageButton4.setText(String.valueOf(page));
            pageButton6.setText(">");
        }
    }

    private void backPageButtonToDefault() {
        pageButton1.setStyle("-fx-background-color:   #D8BFD8");
        pageButton2.setStyle("-fx-background-color:   #D8BFD8;");
        pageButton3.setStyle("-fx-background-color:   #D8BFD8;");
        pageButton4.setStyle("-fx-background-color:  #F0E68C;");
        pageButton5.setStyle("-fx-background-color:   #D8BFD8;");
        pageButton6.setStyle("-fx-background-color:   #D8BFD8;");
        pageButton7.setStyle("-fx-background-color:   #D8BFD8;");
    }

    private void pressTicks(Label label) throws SQLException, IOException {
        java.sql.Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/data", "root", "");
        Statement statement = connection.createStatement();
        inbox.remove(label.getText());
        LoginPage.getUser().addFriends(label.getText());
        statement.executeUpdate("UPDATE user SET inbox='" + arrayToString(inbox) + "' , friends='" + arrayToString(LoginPage.getUser().getFriends()) + "' WHERE username='" + LoginPage.getUser().getUsername() + "'");
        User user = new User();
        for (User user0 : allUsers) {
            if (user0.getUsername().equals(label.getText())) {
                user = user0;
                break;
            }
        }
        user.addFriends(LoginPage.getUser().getUsername());
        statement.executeUpdate("UPDATE user SET friends='" + arrayToString(user.getFriends()) + "' WHERE username='" + label.getText() + "'");
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.close();
        Stage newStage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Inbox.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        newStage.initStyle(StageStyle.UNDECORATED);
        newStage.setScene(scene);
        newStage.show();
    }

    private void pressRejectButton(Label label) throws SQLException, IOException {
        java.sql.Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/data", "root", "");
        Statement statement = connection.createStatement();
        inbox.remove(label.getText());
        statement.executeUpdate("UPDATE user SET inbox='" + arrayToString(inbox) + "' WHERE username='" + LoginPage.getUser().getUsername() + "'");
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.close();
        Stage newStage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Inbox.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        newStage.initStyle(StageStyle.UNDECORATED);
        newStage.setScene(scene);
        newStage.show();
    }

    private String arrayToString(ArrayList<String> list) {
        String line = "";
        for (String word : list) {
            line += word + ",";
        }
        return line;
    }
}
