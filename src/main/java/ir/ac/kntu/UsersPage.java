package ir.ac.kntu;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import ir.ac.kntu.objects.*;
import ir.ac.kntu.types.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class UsersPage implements Initializable {
    private ArrayList<User> allUsers=new ArrayList<>();

    private ArrayList<User> finalUsers=new ArrayList<>();

    private ArrayList<User> tempUsers=new ArrayList<>();

    private ArrayList<Developer> allDeveloper=new ArrayList<>();

    private ArrayList<Developer> finalDeveloper=new ArrayList<>();

    private ArrayList<Developer> tempDeveloper=new ArrayList<>();

    private ArrayList<Seller> allSeller=new ArrayList<>();

    private ArrayList<Seller> finalSeller=new ArrayList<>();

    private ArrayList<Seller> tempSeller=new ArrayList<>();

    private int page=1;

    private int numberOfUser;

    private static Friends friends=Friends.View;

    private static User user=new User();

    public static Friends getFriends() {
        return friends;
    }

    public static void setFriends(Friends friends) {
        UsersPage.friends = friends;
    }

    public static User getUser() {
        return user;
    }

    public static void setUser(User user) {
        UsersPage.user = user;
    }

    public ArrayList<Developer> getAllDeveloper() {
        return allDeveloper;
    }

    public void setAllDeveloper(ArrayList<Developer> allDeveloper) {
        this.allDeveloper = allDeveloper;
    }

    public ArrayList<Developer> getFinalDeveloper() {
        return finalDeveloper;
    }

    public void setFinalDeveloper(ArrayList<Developer> finalDeveloper) {
        this.finalDeveloper = finalDeveloper;
    }

    public ArrayList<Developer> getTempDeveloper() {
        return tempDeveloper;
    }

    public void setTempDeveloper(ArrayList<Developer> tempDeveloper) {
        this.tempDeveloper = tempDeveloper;
    }

    @FXML
    private JFXRadioButton developerButton;

    @FXML
    private JFXRadioButton userButton;
    @FXML
    private JFXRadioButton sellerButton;
    @FXML
    private JFXButton addAndFindButton;

    @FXML
    private JFXButton backButton;

    @FXML
    private JFXButton exitButton;

    @FXML
    private JFXButton inboxButton;

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
    private JFXButton searchButton;

    @FXML
    private TextField searchText;

    @FXML
    private JFXButton user1;

    @FXML
    private JFXButton user2;

    @FXML
    private JFXButton user3;

    @FXML
    private JFXButton user4;

    @FXML
    private JFXButton user5;

    @FXML
    private JFXButton user6;

    @FXML
    private JFXButton user7;

    @FXML
    private JFXButton user8;


    @FXML
    void pressDeveloperButton(MouseEvent event) {
        userButton.setSelected(false);
        sellerButton.setSelected(false);
        developerButton.setSelected(true);
        allDeveloper.clear();
        try {
            setAllDeveloper();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        inboxButton.setVisible(false);
        addAndFindButton.setVisible(true);
        addAndFindButton.setText("Add");
        numberOfUser = allDeveloper.size();
        setPageButton();
        setDeveloperButton();
    }

    @FXML
    void pressUserButton(MouseEvent event) {
        userButton.setSelected(true);
        sellerButton.setSelected(false);
        developerButton.setSelected(false);
        allUsers.clear();
        finalUsers.clear();
        tempUsers.clear();
        try {
            setAllUser();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        pageName.setText("Users");
        finalUsers.addAll(allUsers);
        inboxButton.setVisible(false);
        tempUsers.addAll(finalUsers);
        numberOfUser = finalUsers.size();
        setPageButton();
        setUsersButton();
    }

    @FXML
    void pressSellerButton(MouseEvent event) {
        userButton.setSelected(false);
        sellerButton.setSelected(true);
        developerButton.setSelected(false);
        allSeller.clear();
        try {
            setAllSeller();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        inboxButton.setVisible(false);
        addAndFindButton.setVisible(true);
        addAndFindButton.setText("Add");
        numberOfUser = allSeller.size();
        setPageButton();
        setSellerButton();
    }
    @FXML
    void pressAddAndFindButton(MouseEvent event) throws IOException {
        Stage stage=(Stage) backButton.getScene().getWindow();
        stage.close();
        Stage newStage=new Stage();
        FXMLLoader fxmlLoader = null;
        if (LoginPage.getAccessLevel().equals(AccessLevel.user)) {
            friends=Friends.Find;
            fxmlLoader = new FXMLLoader(getClass().getResource("UsersPage.fxml"));
        }else if(LoginPage.getAccessLevel().equals(AccessLevel.admin)) {
            fxmlLoader = new FXMLLoader(getClass().getResource("SignInPage.fxml"));
        }
        Scene scene = new Scene(fxmlLoader.load());
        newStage.initStyle(StageStyle.UNDECORATED);
        newStage.setScene(scene);
        newStage.show();
    }

    @FXML
    void pressBackButton(MouseEvent event) throws IOException {
        Stage stage=(Stage) backButton.getScene().getWindow();
        stage.close();
        Stage newStage=new Stage();
        FXMLLoader fxmlLoader = null;
        if (LoginPage.getAccessLevel().equals(AccessLevel.user)&&friends.equals(Friends.View)) {
            fxmlLoader = new FXMLLoader(getClass().getResource("UserPage.fxml"));
        }else if (LoginPage.getAccessLevel().equals(AccessLevel.user)&&friends.equals(Friends.Find)){
            friends=Friends.View;
            fxmlLoader = new FXMLLoader(getClass().getResource("UsersPage.fxml"));
        }else if(LoginPage.getAccessLevel().equals(AccessLevel.admin)) {
            fxmlLoader = new FXMLLoader(getClass().getResource("AdminPage.fxml"));
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
    void pressInboxButton(MouseEvent event) throws IOException {
        Stage stage=(Stage) inboxButton.getScene().getWindow();
        stage.close();
        Stage newStage=new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Inbox.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        newStage.initStyle(StageStyle.UNDECORATED);
        newStage.setScene(scene);
        newStage.show();
    }

    @FXML
    void pressPageButton1(MouseEvent event) {
        if(pageButton1.getText().equals("<")){
            page--;
        }else if(pageButton1.getText().equals("<<")){
            page=1;
        }else if(pageButton1.getText().equals(">")){
            page++;
        }else if(pageButton1.getText().equals(">>")){
            page= (int) Math.ceil((float)numberOfUser/8);
        }else {
            page= Integer.parseInt(pageButton1.getText());
        }
        backPageButtonToDefault();
        setPageButton();
        setUsersButton();
    }

    @FXML
    void pressPageButton2(MouseEvent event) {
        if(pageButton2.getText().equals("<")){
            page--;
        }else if(pageButton2.getText().equals("<<")){
            page=1;
        }else if(pageButton2.getText().equals(">")){
            page++;
        }else if(pageButton2.getText().equals(">>")){
            page= (int) Math.ceil((float)numberOfUser/8);
        }else {
            page= Integer.parseInt(pageButton2.getText());
        }
        backPageButtonToDefault();
        setPageButton();
        setUsersButton();
    }

    @FXML
    void pressPageButton3(MouseEvent event) {
        if(pageButton3.getText().equals("<")){
            page--;
        }else if(pageButton3.getText().equals("<<")){
            page=1;
        }else if(pageButton3.getText().equals(">")){
            page++;
        }else if(pageButton3.getText().equals(">>")){
            page= (int) Math.ceil((float)numberOfUser/8);
        }else {
            page= Integer.parseInt(pageButton3.getText());
        }
        backPageButtonToDefault();
        setPageButton();
        setUsersButton();
    }

    @FXML
    void pressPageButton4(MouseEvent event) {
        if(pageButton4.getText().equals("<")){
            page--;
        }else if(pageButton4.getText().equals("<<")){
            page=1;
        }else if(pageButton4.getText().equals(">")){
            page++;
        }else if(pageButton4.getText().equals(">>")){
            page= (int) Math.ceil((float)numberOfUser/8);
        }else {
            page= Integer.parseInt(pageButton4.getText());
        }
        backPageButtonToDefault();
        setPageButton();
        setUsersButton();
    }

    @FXML
    void pressPageButton5(MouseEvent event) {
        if(pageButton5.getText().equals("<")){
            page--;
        }else if(pageButton5.getText().equals("<<")){
            page=1;
        }else if(pageButton5.getText().equals(">")){
            page++;
        }else if(pageButton5.getText().equals(">>")){
            page= (int) Math.ceil((float)numberOfUser/8);
        }else {
            page= Integer.parseInt(pageButton5.getText());
        }
        backPageButtonToDefault();
        setPageButton();
        setUsersButton();
    }

    @FXML
    void pressPageButton6(MouseEvent event) {
        if(pageButton6.getText().equals("<")){
            page--;
        }else if(pageButton6.getText().equals("<<")){
            page=1;
        }else if(pageButton6.getText().equals(">")){
            page++;
        }else if(pageButton6.getText().equals(">>")){
            page= (int) Math.ceil((float)numberOfUser/8);
        }else {
            page= Integer.parseInt(pageButton6.getText());
        }
        backPageButtonToDefault();
        setPageButton();
        setUsersButton();
    }

    @FXML
    void pressPageButton7(MouseEvent event) {
        if(pageButton7.getText().equals("<")){
            page--;
        }else if(pageButton7.getText().equals("<<")){
            page=1;
        }else if(pageButton7.getText().equals(">")){
            page++;
        }else if(pageButton7.getText().equals(">>")){
            page= (int) Math.ceil((float)numberOfUser/8);
        }else {
            page= Integer.parseInt(pageButton7.getText());
        }
        backPageButtonToDefault();
        setPageButton();
        setUsersButton();
    }

    @FXML
    void pressSearchButton(MouseEvent event) {
        finalUsers.clear();
        for (User user:tempUsers){
            if (searchText.getText().equals(user.getUsername())||searchText.getText().equals(user.getEmail())||searchText.getText().equals(user.getPhone())||user.getUsername().startsWith(searchText.getText())){
                finalUsers.add(user);
            }
        }
        numberOfUser=finalUsers.size();
        setPageButton();
        setUsersButton();
    }

    @FXML
    void pressUser1(MouseEvent event) throws IOException, SQLException {
        for (User user0:allUsers){
            if (user0.getUsername().equals(user1.getText())){
                user=user0;
                break;
            }
        }
        pressUser(user1);
    }

    @FXML
    void pressUser2(MouseEvent event) throws IOException, SQLException {
        for (User user0:allUsers){
            if (user0.getUsername().equals(user2.getText())){
                user=user0;
                break;
            }
        }
        pressUser(user2);

    }

    @FXML
    void pressUser3(MouseEvent event) throws SQLException, IOException {
        for (User user0:allUsers){
            if (user0.getUsername().equals(user3.getText())){
                user=user0;
                break;
            }
        }
        pressUser(user3);
    }

    @FXML
    void pressUser4(MouseEvent event) throws SQLException, IOException {
        for (User user0:allUsers){
            if (user0.getUsername().equals(user4.getText())){
                user=user0;
                break;
            }
        }
        pressUser(user4);
    }

    @FXML
    void pressUser5(MouseEvent event) throws SQLException, IOException {
        for (User user0:allUsers){
            if (user0.getUsername().equals(user5.getText())){
                user=user0;
                break;
            }
        }
        pressUser(user5);
    }

    @FXML
    void pressUser6(MouseEvent event) throws SQLException, IOException {
        for (User user0:allUsers){
            if (user0.getUsername().equals(user6.getText())){
                user=user0;
                break;
            }
        }
        pressUser(user6);
    }

    @FXML
    void pressUser7(MouseEvent event) throws SQLException, IOException {
        for (User user0:allUsers){
            if (user0.getUsername().equals(user7.getText())){
                user=user0;
                break;
            }
        }
        pressUser(user7);
    }

    @FXML
    void pressUser8(MouseEvent event) throws SQLException, IOException {
        for (User user0:allUsers){
            if (user0.getUsername().equals(user8.getText())){
                user=user0;
                break;
            }
        }
        pressUser(user8);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (userButton.isSelected()||LoginPage.getAccessLevel().equals(AccessLevel.user)) {
            try {
                setAllUser();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            if (LoginPage.getAccessLevel().equals(AccessLevel.user) && friends.equals(Friends.View)) {
                setFriends();
                addAndFindButton.setText("Find");
                pageName.setText("Friends");
                sellerButton.setVisible(false);
                developerButton.setVisible(false);
                userButton.setVisible(false);
            } else if (LoginPage.getAccessLevel().equals(AccessLevel.user) && friends.equals(Friends.Find)) {
                pageName.setText("Find friends");
                setFindFriends();
                inboxButton.setVisible(false);
                addAndFindButton.setVisible(false);
                sellerButton.setVisible(false);
                developerButton.setVisible(false);
                userButton.setVisible(false);
            } else if (LoginPage.getAccessLevel().equals(AccessLevel.admin)) {
                pageName.setText("Users");
                finalUsers.addAll(allUsers);
                inboxButton.setVisible(false);
            }
            tempUsers.addAll(finalUsers);
            numberOfUser = finalUsers.size();
            setPageButton();
            setUsersButton();
        }else if (sellerButton.isSelected()){

        }else if (developerButton.isSelected()){

        }
    }

    public void setAllUser() throws SQLException {
        Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/data","root","");
        Statement statement=connection.createStatement();
        ResultSet resultSet=statement.executeQuery("SELECT * FROM user");
        while (resultSet.next()){
            User user=new User();
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
            allUsers.add(user);
        }
    }

    public void setAllDeveloper() throws SQLException {
        Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/data","root","");
        Statement statement=connection.createStatement();
        ResultSet resultSet=statement.executeQuery("SELECT * FROM developer");
        while (resultSet.next()){
            Developer developer=new Developer();
            developer.setId(resultSet.getInt("id"));
            developer.setUsername(resultSet.getString("username"));
            developer.setFirstname(resultSet.getString("firstname"));
            developer.setLastname(resultSet.getString("lastname"));
            developer.setAge(resultSet.getInt("age"));
            developer.setGames(LoginPage.stringToArray(resultSet.getString("game")));
            developer.setScheduledEvents(LoginPage.stringToArray(resultSet.getString("scheduledEvents")));
            developer.setPhone(resultSet.getString("phone"));
            developer.setEmail(resultSet.getString("email"));
            developer.setPassword(resultSet.getString("password"));
            developer.setInbox(LoginPage.stringToArray(resultSet.getString("inbox")));
            allDeveloper.add(developer);
        }
    }

    public void setAllSeller() throws SQLException {
        Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/data","root","");
        Statement statement=connection.createStatement();
        ResultSet resultSet=statement.executeQuery("SELECT * FROM seller");
        while (resultSet.next()){
            Seller seller=new Seller();
            seller.setId(resultSet.getInt("id"));
            seller.setUsername(resultSet.getString("username"));
            seller.setFirstname(resultSet.getString("firstname"));
            seller.setLastname(resultSet.getString("lastname"));
            seller.setAge(resultSet.getInt("age"));
            seller.setEquipments(LoginPage.stringToArray(resultSet.getString("equipment")));
            seller.setPhone(resultSet.getString("phone"));
            seller.setEmail(resultSet.getString("email"));
            seller.setPassword(resultSet.getString("password"));
            allSeller.add(seller);
        }
    }

    private void setFriends(){
        for (User user:allUsers){
            if (LoginPage.getUser().getFriends().contains(user.getUsername())){
                finalUsers.add(user);
            }
        }
    }

    private void setFindFriends(){
        for (User user:allUsers){
            if (!LoginPage.getUser().getFriends().contains(user.getUsername())&&!user.getUsername().equals(LoginPage.getUser().getUsername())){
                finalUsers.add(user);
            }
        }
    }

    private void setPageButton(){
        if (numberOfUser<=8){
            pageButton1.setVisible(false);
            pageButton2.setVisible(false);
            pageButton3.setVisible(false);
            pageButton4.setVisible(true);
            pageButton5.setVisible(false);
            pageButton6.setVisible(false);
            pageButton7.setVisible(false);
            pageButton4.setText("1");
        }else if (numberOfUser<=16&&page==1){
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
        }else if (numberOfUser<=16&&page==2){
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
        }else if (page==1){
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
        }else if (numberOfUser<=24&&page==2){
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
        }else if (page==2){
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
        }else if (page==(int) Math.ceil((float)numberOfUser/8)){
            pageButton1.setVisible(false);
            pageButton2.setVisible(true);
            pageButton3.setVisible(true);
            pageButton4.setVisible(true);
            pageButton5.setVisible(true);
            pageButton6.setVisible(true);
            pageButton7.setVisible(false);
            pageButton2.setText("<<");
            pageButton6.setText(String.valueOf((int) Math.ceil((float)numberOfUser/8)));
            pageButton3.setText("<");
            pageButton4.setText(String.valueOf((int) Math.ceil((float)numberOfUser/8)-2));
            pageButton5.setText(String.valueOf((int) Math.ceil((float)numberOfUser/8)-1));
            pageButton4.setStyle("-fx-background-color:   #D8BFD8;");
            pageButton6.setStyle("-fx-background-color:   #F0E68C;");
        }else if (page==(int) Math.ceil((float)numberOfUser/8)-1&&numberOfUser>24){
            pageButton1.setVisible(true);
            pageButton2.setVisible(true);
            pageButton3.setVisible(true);
            pageButton4.setVisible(true);
            pageButton5.setVisible(true);
            pageButton6.setVisible(true);
            pageButton7.setVisible(false);
            pageButton1.setText("<<");
            pageButton5.setText(String.valueOf((int) Math.ceil((float)numberOfUser/8)));
            pageButton2.setText("<");
            pageButton3.setText(String.valueOf((int) Math.ceil((float)numberOfUser/8)-2));
            pageButton4.setText(String.valueOf((int) Math.ceil((float)numberOfUser/8)-1));
            pageButton6.setText(">");
        }else {
            pageButton2.setVisible(true);
            pageButton3.setVisible(true);
            pageButton4.setVisible(true);
            pageButton5.setVisible(true);
            pageButton6.setVisible(true);
            pageButton7.setVisible(true);
            pageButton1.setVisible(true);
            pageButton7.setText(">>");
            pageButton1.setText("<<");
            pageButton5.setText(String.valueOf(page+1));
            pageButton2.setText("<");
            pageButton3.setText(String.valueOf(page-1));
            pageButton4.setText(String.valueOf(page));
            pageButton6.setText(">");
        }
    }

    private void backPageButtonToDefault(){
        pageButton1.setStyle("-fx-background-color:   #D8BFD8");
        pageButton2.setStyle("-fx-background-color:   #D8BFD8;");
        pageButton3.setStyle("-fx-background-color:   #D8BFD8;");
        pageButton4.setStyle("-fx-background-color:  #F0E68C;");
        pageButton5.setStyle("-fx-background-color:   #D8BFD8;");
        pageButton6.setStyle("-fx-background-color:   #D8BFD8;");
        pageButton7.setStyle("-fx-background-color:   #D8BFD8;");
    }

    private void setUsersButton(){
        if (numberOfUser==0){
            user1.setVisible(false);
            user2.setVisible(false);
            user3.setVisible(false);
            user4.setVisible(false);
            user5.setVisible(false);
            user6.setVisible(false);
            user7.setVisible(false);
            user8.setVisible(false);
        }else if (page==(int) Math.ceil((float)numberOfUser/8)&&numberOfUser%8==1){
            user1.setVisible(true);
            user2.setVisible(false);
            user3.setVisible(false);
            user4.setVisible(false);
            user5.setVisible(false);
            user6.setVisible(false);
            user7.setVisible(false);
            user8.setVisible(false);
            user1.setText(finalUsers.get(numberOfUser-1).getUsername());
        }else if (page==(int) Math.ceil((float)numberOfUser/8)&&numberOfUser%8==2){
            user1.setVisible(true);
            user2.setVisible(true);
            user3.setVisible(false);
            user4.setVisible(false);
            user5.setVisible(false);
            user6.setVisible(false);
            user7.setVisible(false);
            user8.setVisible(false);
            user1.setText(finalUsers.get(numberOfUser-2).getUsername());
            user2.setText(finalUsers.get(numberOfUser-1).getUsername());
        }else if (page==(int) Math.ceil((float)numberOfUser/8)&&numberOfUser%8==3){
            user1.setVisible(true);
            user2.setVisible(true);
            user3.setVisible(true);
            user4.setVisible(false);
            user5.setVisible(false);
            user6.setVisible(false);
            user7.setVisible(false);
            user8.setVisible(false);
            user1.setText(finalUsers.get(numberOfUser-3).getUsername());
            user2.setText(finalUsers.get(numberOfUser-2).getUsername());
            user3.setText(finalUsers.get(numberOfUser-1).getUsername());
        }else if (page==(int) Math.ceil((float)numberOfUser/8)&&numberOfUser%8==4){
            user1.setVisible(true);
            user2.setVisible(true);
            user3.setVisible(true);
            user4.setVisible(true);
            user5.setVisible(false);
            user6.setVisible(false);
            user7.setVisible(false);
            user8.setVisible(false);
            user1.setText(finalUsers.get(numberOfUser-4).getUsername());
            user2.setText(finalUsers.get(numberOfUser-3).getUsername());
            user3.setText(finalUsers.get(numberOfUser-2).getUsername());
            user4.setText(finalUsers.get(numberOfUser-1).getUsername());
        }else if (page==(int) Math.ceil((float)numberOfUser/8)&&numberOfUser%8==5){
            user1.setVisible(true);
            user2.setVisible(true);
            user3.setVisible(true);
            user4.setVisible(true);
            user5.setVisible(true);
            user6.setVisible(false);
            user7.setVisible(false);
            user8.setVisible(false);
            user1.setText(finalUsers.get(numberOfUser-5).getUsername());
            user2.setText(finalUsers.get(numberOfUser-4).getUsername());
            user3.setText(finalUsers.get(numberOfUser-3).getUsername());
            user4.setText(finalUsers.get(numberOfUser-2).getUsername());
            user5.setText(finalUsers.get(numberOfUser-1).getUsername());
        }else if (page==(int) Math.ceil((float)numberOfUser/8)&&numberOfUser%8==6){
            user1.setVisible(true);
            user2.setVisible(true);
            user3.setVisible(true);
            user4.setVisible(true);
            user5.setVisible(true);
            user6.setVisible(true);
            user7.setVisible(false);
            user8.setVisible(false);
            user1.setText(finalUsers.get(numberOfUser-6).getUsername());
            user2.setText(finalUsers.get(numberOfUser-5).getUsername());
            user3.setText(finalUsers.get(numberOfUser-4).getUsername());
            user4.setText(finalUsers.get(numberOfUser-3).getUsername());
            user5.setText(finalUsers.get(numberOfUser-2).getUsername());
            user6.setText(finalUsers.get(numberOfUser-1).getUsername());
        }else if (page==(int) Math.ceil((float)numberOfUser/8)&&numberOfUser%8==7){
            user1.setVisible(true);
            user2.setVisible(true);
            user3.setVisible(true);
            user4.setVisible(true);
            user5.setVisible(true);
            user6.setVisible(true);
            user7.setVisible(true);
            user8.setVisible(false);
            user1.setText(finalUsers.get(numberOfUser-7).getUsername());
            user2.setText(finalUsers.get(numberOfUser-6).getUsername());
            user3.setText(finalUsers.get(numberOfUser-5).getUsername());
            user4.setText(finalUsers.get(numberOfUser-4).getUsername());
            user5.setText(finalUsers.get(numberOfUser-3).getUsername());
            user6.setText(finalUsers.get(numberOfUser-2).getUsername());
            user7.setText(finalUsers.get(numberOfUser-1).getUsername());
        }else {
            user1.setVisible(true);
            user2.setVisible(true);
            user3.setVisible(true);
            user4.setVisible(true);
            user5.setVisible(true);
            user6.setVisible(true);
            user7.setVisible(true);
            user8.setVisible(true);
            user1.setText(finalUsers.get(page*8-8).getUsername());
            user2.setText(finalUsers.get(page*8-7).getUsername());
            user3.setText(finalUsers.get(page*8-6).getUsername());
            user4.setText(finalUsers.get(page*8-5).getUsername());
            user5.setText(finalUsers.get(page*8-4).getUsername());
            user6.setText(finalUsers.get(page*8-3).getUsername());
            user7.setText(finalUsers.get(page*8-2).getUsername());
            user7.setText(finalUsers.get(page*8-1).getUsername());
        }
    }

    private void setSellerButton(){
        if (numberOfUser==0){
            user1.setVisible(false);
            user2.setVisible(false);
            user3.setVisible(false);
            user4.setVisible(false);
            user5.setVisible(false);
            user6.setVisible(false);
            user7.setVisible(false);
            user8.setVisible(false);
        }else if (page==(int) Math.ceil((float)numberOfUser/8)&&numberOfUser%8==1){
            user1.setVisible(true);
            user2.setVisible(false);
            user3.setVisible(false);
            user4.setVisible(false);
            user5.setVisible(false);
            user6.setVisible(false);
            user7.setVisible(false);
            user8.setVisible(false);
            user1.setText(allSeller.get(numberOfUser-1).getUsername());
        }else if (page==(int) Math.ceil((float)numberOfUser/8)&&numberOfUser%8==2){
            user1.setVisible(true);
            user2.setVisible(true);
            user3.setVisible(false);
            user4.setVisible(false);
            user5.setVisible(false);
            user6.setVisible(false);
            user7.setVisible(false);
            user8.setVisible(false);
            user1.setText(allSeller.get(numberOfUser-2).getUsername());
            user2.setText(allSeller.get(numberOfUser-1).getUsername());
        }else if (page==(int) Math.ceil((float)numberOfUser/8)&&numberOfUser%8==3){
            user1.setVisible(true);
            user2.setVisible(true);
            user3.setVisible(true);
            user4.setVisible(false);
            user5.setVisible(false);
            user6.setVisible(false);
            user7.setVisible(false);
            user8.setVisible(false);
            user1.setText(allSeller.get(numberOfUser-3).getUsername());
            user2.setText(allSeller.get(numberOfUser-2).getUsername());
            user3.setText(allSeller.get(numberOfUser-1).getUsername());
        }else if (page==(int) Math.ceil((float)numberOfUser/8)&&numberOfUser%8==4){
            user1.setVisible(true);
            user2.setVisible(true);
            user3.setVisible(true);
            user4.setVisible(true);
            user5.setVisible(false);
            user6.setVisible(false);
            user7.setVisible(false);
            user8.setVisible(false);
            user1.setText(allSeller.get(numberOfUser-4).getUsername());
            user2.setText(allSeller.get(numberOfUser-3).getUsername());
            user3.setText(allSeller.get(numberOfUser-2).getUsername());
            user4.setText(allSeller.get(numberOfUser-1).getUsername());
        }else if (page==(int) Math.ceil((float)numberOfUser/8)&&numberOfUser%8==5){
            user1.setVisible(true);
            user2.setVisible(true);
            user3.setVisible(true);
            user4.setVisible(true);
            user5.setVisible(true);
            user6.setVisible(false);
            user7.setVisible(false);
            user8.setVisible(false);
            user1.setText(allSeller.get(numberOfUser-5).getUsername());
            user2.setText(allSeller.get(numberOfUser-4).getUsername());
            user3.setText(allSeller.get(numberOfUser-3).getUsername());
            user4.setText(allSeller.get(numberOfUser-2).getUsername());
            user5.setText(allSeller.get(numberOfUser-1).getUsername());
        }else if (page==(int) Math.ceil((float)numberOfUser/8)&&numberOfUser%8==6){
            user1.setVisible(true);
            user2.setVisible(true);
            user3.setVisible(true);
            user4.setVisible(true);
            user5.setVisible(true);
            user6.setVisible(true);
            user7.setVisible(false);
            user8.setVisible(false);
            user1.setText(allSeller.get(numberOfUser-6).getUsername());
            user2.setText(allSeller.get(numberOfUser-5).getUsername());
            user3.setText(allSeller.get(numberOfUser-4).getUsername());
            user4.setText(allSeller.get(numberOfUser-3).getUsername());
            user5.setText(allSeller.get(numberOfUser-2).getUsername());
            user6.setText(allSeller.get(numberOfUser-1).getUsername());
        }else if (page==(int) Math.ceil((float)numberOfUser/8)&&numberOfUser%8==7){
            user1.setVisible(true);
            user2.setVisible(true);
            user3.setVisible(true);
            user4.setVisible(true);
            user5.setVisible(true);
            user6.setVisible(true);
            user7.setVisible(true);
            user8.setVisible(false);
            user1.setText(allSeller.get(numberOfUser-7).getUsername());
            user2.setText(allSeller.get(numberOfUser-6).getUsername());
            user3.setText(allSeller.get(numberOfUser-5).getUsername());
            user4.setText(allSeller.get(numberOfUser-4).getUsername());
            user5.setText(allSeller.get(numberOfUser-3).getUsername());
            user6.setText(allSeller.get(numberOfUser-2).getUsername());
            user7.setText(allSeller.get(numberOfUser-1).getUsername());
        }else {
            user1.setVisible(true);
            user2.setVisible(true);
            user3.setVisible(true);
            user4.setVisible(true);
            user5.setVisible(true);
            user6.setVisible(true);
            user7.setVisible(true);
            user8.setVisible(true);
            user1.setText(allSeller.get(page*8-8).getUsername());
            user2.setText(allSeller.get(page*8-7).getUsername());
            user3.setText(allSeller.get(page*8-6).getUsername());
            user4.setText(allSeller.get(page*8-5).getUsername());
            user5.setText(allSeller.get(page*8-4).getUsername());
            user6.setText(allSeller.get(page*8-3).getUsername());
            user7.setText(allSeller.get(page*8-2).getUsername());
            user7.setText(allSeller.get(page*8-1).getUsername());
        }
    }

    private void setDeveloperButton(){
        if (numberOfUser==0){
            user1.setVisible(false);
            user2.setVisible(false);
            user3.setVisible(false);
            user4.setVisible(false);
            user5.setVisible(false);
            user6.setVisible(false);
            user7.setVisible(false);
            user8.setVisible(false);
        }else if (page==(int) Math.ceil((float)numberOfUser/8)&&numberOfUser%8==1){
            user1.setVisible(true);
            user2.setVisible(false);
            user3.setVisible(false);
            user4.setVisible(false);
            user5.setVisible(false);
            user6.setVisible(false);
            user7.setVisible(false);
            user8.setVisible(false);
            user1.setText(allDeveloper.get(numberOfUser-1).getUsername());
        }else if (page==(int) Math.ceil((float)numberOfUser/8)&&numberOfUser%8==2){
            user1.setVisible(true);
            user2.setVisible(true);
            user3.setVisible(false);
            user4.setVisible(false);
            user5.setVisible(false);
            user6.setVisible(false);
            user7.setVisible(false);
            user8.setVisible(false);
            user1.setText(allDeveloper.get(numberOfUser-2).getUsername());
            user2.setText(allDeveloper.get(numberOfUser-1).getUsername());
        }else if (page==(int) Math.ceil((float)numberOfUser/8)&&numberOfUser%8==3){
            user1.setVisible(true);
            user2.setVisible(true);
            user3.setVisible(true);
            user4.setVisible(false);
            user5.setVisible(false);
            user6.setVisible(false);
            user7.setVisible(false);
            user8.setVisible(false);
            user1.setText(allDeveloper.get(numberOfUser-3).getUsername());
            user2.setText(allDeveloper.get(numberOfUser-2).getUsername());
            user3.setText(allDeveloper.get(numberOfUser-1).getUsername());
        }else if (page==(int) Math.ceil((float)numberOfUser/8)&&numberOfUser%8==4){
            user1.setVisible(true);
            user2.setVisible(true);
            user3.setVisible(true);
            user4.setVisible(true);
            user5.setVisible(false);
            user6.setVisible(false);
            user7.setVisible(false);
            user8.setVisible(false);
            user1.setText(allDeveloper.get(numberOfUser-4).getUsername());
            user2.setText(allDeveloper.get(numberOfUser-3).getUsername());
            user3.setText(allDeveloper.get(numberOfUser-2).getUsername());
            user4.setText(allDeveloper.get(numberOfUser-1).getUsername());
        }else if (page==(int) Math.ceil((float)numberOfUser/8)&&numberOfUser%8==5){
            user1.setVisible(true);
            user2.setVisible(true);
            user3.setVisible(true);
            user4.setVisible(true);
            user5.setVisible(true);
            user6.setVisible(false);
            user7.setVisible(false);
            user8.setVisible(false);
            user1.setText(allDeveloper.get(numberOfUser-5).getUsername());
            user2.setText(allDeveloper.get(numberOfUser-4).getUsername());
            user3.setText(allDeveloper.get(numberOfUser-3).getUsername());
            user4.setText(allDeveloper.get(numberOfUser-2).getUsername());
            user5.setText(allDeveloper.get(numberOfUser-1).getUsername());
        }else if (page==(int) Math.ceil((float)numberOfUser/8)&&numberOfUser%8==6){
            user1.setVisible(true);
            user2.setVisible(true);
            user3.setVisible(true);
            user4.setVisible(true);
            user5.setVisible(true);
            user6.setVisible(true);
            user7.setVisible(false);
            user8.setVisible(false);
            user1.setText(allDeveloper.get(numberOfUser-6).getUsername());
            user2.setText(allDeveloper.get(numberOfUser-5).getUsername());
            user3.setText(allDeveloper.get(numberOfUser-4).getUsername());
            user4.setText(allDeveloper.get(numberOfUser-3).getUsername());
            user5.setText(allDeveloper.get(numberOfUser-2).getUsername());
            user6.setText(allDeveloper.get(numberOfUser-1).getUsername());
        }else if (page==(int) Math.ceil((float)numberOfUser/8)&&numberOfUser%8==7){
            user1.setVisible(true);
            user2.setVisible(true);
            user3.setVisible(true);
            user4.setVisible(true);
            user5.setVisible(true);
            user6.setVisible(true);
            user7.setVisible(true);
            user8.setVisible(false);
            user1.setText(allDeveloper.get(numberOfUser-7).getUsername());
            user2.setText(allDeveloper.get(numberOfUser-6).getUsername());
            user3.setText(allDeveloper.get(numberOfUser-5).getUsername());
            user4.setText(allDeveloper.get(numberOfUser-4).getUsername());
            user5.setText(allDeveloper.get(numberOfUser-3).getUsername());
            user6.setText(allDeveloper.get(numberOfUser-2).getUsername());
            user7.setText(allDeveloper.get(numberOfUser-1).getUsername());
        }else {
            user1.setVisible(true);
            user2.setVisible(true);
            user3.setVisible(true);
            user4.setVisible(true);
            user5.setVisible(true);
            user6.setVisible(true);
            user7.setVisible(true);
            user8.setVisible(true);
            user1.setText(allDeveloper.get(page*8-8).getUsername());
            user2.setText(allDeveloper.get(page*8-7).getUsername());
            user3.setText(allDeveloper.get(page*8-6).getUsername());
            user4.setText(allDeveloper.get(page*8-5).getUsername());
            user5.setText(allDeveloper.get(page*8-4).getUsername());
            user6.setText(allDeveloper.get(page*8-3).getUsername());
            user7.setText(allDeveloper.get(page*8-2).getUsername());
            user7.setText(allDeveloper.get(page*8-1).getUsername());
        }
    }
    private void pressUser(Button button) throws IOException, SQLException {
        if(LoginPage.getAccessLevel().equals(AccessLevel.user)&&friends.equals(Friends.View)){
            pressUserView();
        }else if(LoginPage.getAccessLevel().equals(AccessLevel.user)&&friends.equals(Friends.Find)){
            pressUserFind(button);
        } else if (LoginPage.getAccessLevel().equals(AccessLevel.admin)) {
            pressUserAdmin(button);
        }
    }

    private void pressUserFind(Button button) throws IOException, SQLException {
        String inbox = null;
        java.sql.Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/data","root","");
        Statement statement=connection.createStatement();
        ResultSet resultSet=statement.executeQuery("SELECT * FROM user");
        while (resultSet.next()){
            if (button.getText().equals(resultSet.getString("username"))){
                inbox=resultSet.getString("inbox");
                break;
            }
        }
        ArrayList<String> inboxA=new ArrayList<>();
        inboxA=LoginPage.stringToArray(inbox);
        if (inboxA.contains(LoginPage.getUser().getUsername())){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText("you get request to this user before");
            alert.setContentText("the user don't accept or reject your request yet!");
            ButtonType buttonTypeOK = new ButtonType("OK");
            alert.getButtonTypes().setAll(buttonTypeOK);
            alert.showAndWait();
        }else {
            Alert sure = new Alert(Alert.AlertType.CONFIRMATION);
            sure.setTitle("Confirmation Dialog");
            sure.setHeaderText("Are you sure to request to this user?");
            ButtonType buttonTypeYes = new ButtonType("Yes");
            ButtonType buttonTypeNo = new ButtonType("No");
            sure.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);
            Optional<ButtonType> result = sure.showAndWait();
            if (result.isPresent() && result.get() == buttonTypeYes) {
                inbox+=LoginPage.getUser().getUsername()+",";
                statement.executeUpdate("UPDATE user SET inbox='" + inbox + "' WHERE username='" + button.getText() + "'");
            }
        }
    }

    private void pressUserView() throws IOException {
        UserPage.setGameFilter(GameFilter.friend);
        Stage stage=(Stage) user1.getScene().getWindow();
        stage.close();
        Stage newStage=new Stage();
        FXMLLoader fxmlLoader =  new FXMLLoader(getClass().getResource("GamesPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        newStage.initStyle(StageStyle.UNDECORATED);
        newStage.setScene(scene);
        newStage.show();
    }
    private void pressUserAdmin(Button button) throws SQLException, IOException {
        java.sql.Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/data","root","");
        Statement statement=connection.createStatement();
        if (userButton.isSelected()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM user");
            User user0 = new User();
            while (resultSet.next()) {
                String username = resultSet.getString("username");
                if (button.getText().equals(username)) {
                    user0.setId(resultSet.getInt("id"));
                    user0.setEmail(resultSet.getString("email"));
                    user0.setAge(resultSet.getInt("age"));
                    user0.setFirstname(resultSet.getString("firstname"));
                    user0.setLastname(resultSet.getString("lastname"));
                    user0.setPhone(resultSet.getString("phone"));
                    user0.setPassword(resultSet.getString("password"));
                    user0.setUsername(username);
                    user0.setWallet(resultSet.getInt("wallet"));
                    user0.setTimeSpend(resultSet.getInt("timeSpend"));
                    user0.setGames(LoginPage.stringToArray(resultSet.getString("games")));
                    user0.setFriends(LoginPage.stringToArray(resultSet.getString("friends")));
                    user0.setEquipment(LoginPage.stringToArray(resultSet.getString("equipment")));
                    user0.setLevel(Level.valueOf(resultSet.getString("level")));
                    user0.setInbox(LoginPage.stringToArray(resultSet.getString("inbox")));
                    LoginPage.setUser(user0);
                    LoginPage.setAccessLevel(AccessLevel.user);
                    Stage stage = (Stage) user1.getScene().getWindow();
                    stage.close();
                    Stage newStage = new Stage();
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ProfilePage.fxml"));
                    Scene scene = new Scene(fxmlLoader.load());
                    newStage.initStyle(StageStyle.UNDECORATED);
                    newStage.setScene(scene);
                    newStage.show();
                    LoginPage.setAccessLevel(AccessLevel.admin);
                    break;
                }
            }

        }else if (developerButton.isSelected()){
            ResultSet resultSet=statement.executeQuery("SELECT * FROM developer");
            Developer developer=new Developer();
            while (resultSet.next()) {
                String username = resultSet.getString("username");
                if (button.getText().equals(username)) {
                    developer.setId(resultSet.getInt("id"));
                    developer.setUsername(username);
                    developer.setFirstname(resultSet.getString("firstname"));
                    developer.setLastname(resultSet.getString("lastname"));
                    developer.setAge(resultSet.getInt("age"));
                    developer.setGames(LoginPage.stringToArray(resultSet.getString("game")));
                    developer.setScheduledEvents(LoginPage.stringToArray(resultSet.getString("scheduledEvents")));
                    developer.setPhone(resultSet.getString("phone"));
                    developer.setEmail(resultSet.getString("email"));
                    developer.setPassword(resultSet.getString("password"));
                    developer.setInbox(LoginPage.stringToArray(resultSet.getString("inbox")));
                    LoginPage.setDeveloper(developer);
                    LoginPage.setAccessLevel(AccessLevel.developer);
                    Stage stage = (Stage) user1.getScene().getWindow();
                    stage.close();
                    Stage newStage = new Stage();
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ProfilePage.fxml"));
                    Scene scene = new Scene(fxmlLoader.load());
                    newStage.initStyle(StageStyle.UNDECORATED);
                    newStage.setScene(scene);
                    newStage.show();
                    LoginPage.setAccessLevel(AccessLevel.admin);
                    break;
                }
            }
        }else if (sellerButton.isSelected()){
            ResultSet resultSet=statement.executeQuery("SELECT * FROM seller");
            Seller seller=new Seller();
            while (resultSet.next()){
                String username = resultSet.getString("username");
                if (button.getText().equals(username)) {
                    seller.setId(resultSet.getInt("id"));
                    seller.setUsername(resultSet.getString("username"));
                    seller.setFirstname(resultSet.getString("firstname"));
                    seller.setLastname(resultSet.getString("lastname"));
                    seller.setAge(resultSet.getInt("age"));
                    seller.setEquipments(LoginPage.stringToArray(resultSet.getString("equipment")));
                    seller.setPhone(resultSet.getString("phone"));
                    seller.setEmail(resultSet.getString("email"));
                    seller.setPassword(resultSet.getString("password"));
                    LoginPage.setSeller(seller);
                    LoginPage.setAccessLevel(AccessLevel.seller);
                    Stage stage = (Stage) user1.getScene().getWindow();
                    stage.close();
                    Stage newStage = new Stage();
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ProfilePage.fxml"));
                    Scene scene = new Scene(fxmlLoader.load());
                    newStage.initStyle(StageStyle.UNDECORATED);
                    newStage.setScene(scene);
                    newStage.show();
                    LoginPage.setAccessLevel(AccessLevel.admin);
                    break;
                }
            }
        }
    }

}
