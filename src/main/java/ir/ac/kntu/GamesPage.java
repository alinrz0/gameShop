package ir.ac.kntu;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import ir.ac.kntu.objects.Equipment;
import ir.ac.kntu.objects.Game;
import ir.ac.kntu.types.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class GamesPage implements Initializable {
    private static boolean isAddMode=false;

    private boolean filter=false;

    private boolean search=false;

    private ArrayList<Game> allGame=new ArrayList<>();

    private ArrayList<Game> finalGame=new ArrayList<>();

    private ArrayList<Game> tempGame=new ArrayList<>();

    private ArrayList<Equipment> allEquipment=new ArrayList<>();

    private ArrayList<Equipment> finalEquipment=new ArrayList<>();

    private ArrayList<Equipment> tempEquipment=new ArrayList<>();

    private int page=1;

    private int numberOfGame;

    private static Game game=new Game();

    public static Game getGame() {
        return game;
    }

    public static boolean isAddMode() {
        return isAddMode;
    }

    public static void setIsAddMode(boolean isAddMode) {
        GamesPage.isAddMode = isAddMode;
    }

    @FXML
    private Label erorrLabel;

    @FXML
    private JFXButton addButton;

    @FXML
    private JFXButton backButton;

    @FXML
    private JFXRadioButton equipmentButton;

    @FXML
    private JFXButton exitButton;

    @FXML
    private JFXButton filterButton;

    @FXML
    private JFXRadioButton gameButton;

    @FXML
    private JFXButton gameButton1;

    @FXML
    private JFXButton gameButton2;

    @FXML
    private JFXButton gameButton3;

    @FXML
    private JFXButton gameButton4;

    @FXML
    private AnchorPane gamePage;

    @FXML
    private TextField maxPriceText;

    @FXML
    private TextField minPriceText;

    @FXML
    private Button pageButton1;

    @FXML
    private Button pageButton2;

    @FXML
    private Button pageButton3;

    @FXML
    private Button pageButton4;

    @FXML
    private Button pageButton5;

    @FXML
    private Button pageButton6;

    @FXML
    private Button pageButton7;

    @FXML
    private Label pageName;

    @FXML
    private JFXButton searchButton;

    @FXML
    private TextField searchText;

    @FXML
    void clickOnPage(MouseEvent event) {
        erorrLabel.setVisible(false);
    }

    @FXML
    void pressAddButton(MouseEvent event) throws IOException {
        isAddMode=true;
        Stage stage=(Stage) addButton.getScene().getWindow();
        stage.close();
        Stage newStage=new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("GamePage.fxml"));
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
            page= (int) Math.ceil((float)numberOfGame/4);
        }else {
            page= Integer.parseInt(pageButton1.getText());
        }
        backPageButtonToDefault();
        setPageButton();
        erorrLabel.setVisible(false);
        setGamesButton();
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
            page= (int) Math.ceil((float)numberOfGame/4);
        }else {
            page= Integer.parseInt(pageButton2.getText());
        }
        backPageButtonToDefault();
        setPageButton();
        erorrLabel.setVisible(false);
        setGamesButton();
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
            page= (int) Math.ceil((float)numberOfGame/4);
        }else {
            page= Integer.parseInt(pageButton3.getText());
        }
        backPageButtonToDefault();
        setPageButton();
        erorrLabel.setVisible(false);
        setGamesButton();
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
            page= (int) Math.ceil((float)numberOfGame/4);
        }else {
            page= Integer.parseInt(pageButton4.getText());
        }
        backPageButtonToDefault();
        setPageButton();
        erorrLabel.setVisible(false);
        setGamesButton();
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
            page= (int) Math.ceil((float)numberOfGame/4);
        }else {
            page= Integer.parseInt(pageButton5.getText());
        }
        backPageButtonToDefault();
        setPageButton();
        erorrLabel.setVisible(false);
        setGamesButton();
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
            page= (int) Math.ceil((float)numberOfGame/4);
        }else {
            page= Integer.parseInt(pageButton6.getText());
        }
        backPageButtonToDefault();
        setPageButton();
        erorrLabel.setVisible(false);
        setGamesButton();
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
            page= (int) Math.ceil((float)numberOfGame/4);
        }else {
            page= Integer.parseInt(pageButton7.getText());
        }
        backPageButtonToDefault();
        setPageButton();
        erorrLabel.setVisible(false);
        setGamesButton();
    }

    @FXML
    void pressBackButton(MouseEvent event) throws IOException {
        Stage stage=(Stage) backButton.getScene().getWindow();
        stage.close();
        Stage newStage=new Stage();
        FXMLLoader fxmlLoader = null;
        if (LoginPage.getAccessLevel().equals(AccessLevel.user)&&!UserPage.getGameFilter().equals(GameFilter.friend)) {
            fxmlLoader = new FXMLLoader(getClass().getResource("UserPage.fxml"));
        }else if (LoginPage.getAccessLevel().equals(AccessLevel.user)&&UserPage.getGameFilter().equals(GameFilter.friend)){
            fxmlLoader = new FXMLLoader(getClass().getResource("UsersPage.fxml"));
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
    void pressEquipmentButton(MouseEvent event) throws IOException {
        if (!equipmentButton.isSelected()){
            equipmentButton.setSelected(true);
        }
        gameButton.setSelected(false);
        erorrLabel.setVisible(false);
        Stage stage=(Stage) equipmentButton.getScene().getWindow();
        stage.close();
        Stage newStage=new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("EquipmentsPage.fxml"));
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
    void pressFilterButton(MouseEvent event) {
        if (Pattern.matches("[0-9]+",maxPriceText.getText())&&Pattern.matches("[0-9]+",minPriceText.getText())&&Integer.parseInt(maxPriceText.getText())>=Integer.parseInt(maxPriceText.getText())){
            finalGame.clear();
            for (Game game1:tempGame){
                if (search==true&&game1.getName().startsWith(searchText.getText())&&game1.getPrice()<=Integer.parseInt(maxPriceText.getText())&&game1.getPrice()>=Integer.parseInt(minPriceText.getText())){
                    finalGame.add(game1);
                }else if (search==false&&game1.getPrice()<=Integer.parseInt(maxPriceText.getText())&&game1.getPrice()>=Integer.parseInt(minPriceText.getText())){
                    finalGame.add(game1);
                }
            }
            numberOfGame=finalGame.size();
            setPageButton();
            setGamesButton();
            filter=true;
        }else {
            erorrLabel.setVisible(true);
            finalGame.clear();
            finalGame.addAll(tempGame);
            numberOfGame=finalGame.size();
            setPageButton();
            setGamesButton();
            filter=false;
        }
    }

    @FXML
    void pressGameButton(MouseEvent event) {
        if (!gameButton.isSelected()){
            gameButton.setSelected(true);
        }
        equipmentButton.setSelected(false);
        erorrLabel.setVisible(false);
    }

    @FXML
    void pressGameButton1(MouseEvent event) throws IOException {
        game=finalGame.get(page*4-4);
        Stage stage=(Stage) gameButton1.getScene().getWindow();
        stage.close();
        Stage newStage=new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("GamePage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        newStage.initStyle(StageStyle.UNDECORATED);
        newStage.setScene(scene);
        newStage.show();
    }

    @FXML
    void pressGameButton2(MouseEvent event) throws IOException {
        game=finalGame.get(page*4-3);
        Stage stage=(Stage) gameButton2.getScene().getWindow();
        stage.close();
        Stage newStage=new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("GamePage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        newStage.initStyle(StageStyle.UNDECORATED);
        newStage.setScene(scene);
        newStage.show();
    }

    @FXML
    void pressGameButton3(MouseEvent event) throws IOException {
        game=finalGame.get(page*4-2);
        Stage stage=(Stage) gameButton3.getScene().getWindow();
        stage.close();
        Stage newStage=new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("GamePage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        newStage.initStyle(StageStyle.UNDECORATED);
        newStage.setScene(scene);
        newStage.show();
    }

    @FXML
    void pressGameButton4(MouseEvent event) throws IOException {
        game=finalGame.get(page*4-1);
        Stage stage=(Stage) gameButton4.getScene().getWindow();
        stage.close();
        Stage newStage=new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("GamePage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        newStage.initStyle(StageStyle.UNDECORATED);
        newStage.setScene(scene);
        newStage.show();
    }

    @FXML
    void pressSearchButton(MouseEvent event) {
        finalGame.clear();
        for (Game game1:tempGame){
            if (filter==true&&game1.getPrice()<=Integer.parseInt(maxPriceText.getText())&&game1.getPrice()>=Integer.parseInt(minPriceText.getText())&&game1.getName().startsWith(searchText.getText())){
                finalGame.add(game1);
            }else if (filter==false&&game1.getName().startsWith(searchText.getText())){
                finalGame.add(game1);
            }
        }
        numberOfGame=finalGame.size();
        setPageButton();
        setGamesButton();
        erorrLabel.setVisible(false);
        search=true;
    }


    public ArrayList<Game> getAllGame() {
        return allGame;
    }

    public void setAllGame() throws SQLException {
        Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/data","root","");
        Statement statement=connection.createStatement();
        ResultSet resultSet=statement.executeQuery("SELECT * FROM game");
        while (resultSet.next()){
            Game game=new Game();
            game.setId(resultSet.getInt("id"));
            game.setName(resultSet.getString("name"));
            game.setInformation(resultSet.getString("information"));
            game.setGameGenre(GameGenre.valueOf(resultSet.getString("genre")));
            game.setPrice(resultSet.getInt("price"));
            game.setRate(resultSet.getInt("rate"));
            game.setBeta(Beta.valueOf(resultSet.getString("beta")));
            game.setFailure(Failure.valueOf(resultSet.getString("failure")));
            game.setImage(resultSet.getString("image"));
            game.setWhoRated(LoginPage.toHashmap2(resultSet.getString("whoRated")));
            game.setCommunity(LoginPage.stringToArray(resultSet.getString("community")));
            game.setFeedback(LoginPage.stringToArray(resultSet.getString("feedback")));
            allGame.add(game);
        }
    }



    public ArrayList<Game> getFinalGame() {
        return finalGame;
    }

    private void setFinalGameStore() {
        for (Game game:allGame){
            if (game.getFailure().equals(Failure.Correct)){
                finalGame.add(game);
            }
        }
    }



    private void setFinalGameLibrary() {
        for (Game game:allGame){
            if ((game.getFailure().equals(Failure.Correct))&&LoginPage.getUser().getGames().contains(String.valueOf(game.getId()))){
                finalGame.add(game);
            }
        }
    }

    private void setFinalGameFriend() {
        for (Game game:allGame){
            if ((game.getFailure().equals(Failure.Correct))&&UsersPage.getUser().getGames().contains(String.valueOf(game.getId()))){
                finalGame.add(game);
            }
        }
    }

    private void setFinalGameAdmin() {
        for (Game game:allGame){
            finalGame.add(game);
        }
    }

    private void setFinalGameDeveloper() {
        for (Game game:allGame){
            if (LoginPage.getDeveloper().getGames().contains(String.valueOf(game.getId()))){
                finalGame.add(game);
            }
        }
    }

    private void setPageButton(){
        if (numberOfGame<=4){
            pageButton1.setVisible(false);
            pageButton2.setVisible(false);
            pageButton3.setVisible(false);
            pageButton4.setVisible(true);
            pageButton5.setVisible(false);
            pageButton6.setVisible(false);
            pageButton7.setVisible(false);
            pageButton4.setText("1");
        }else if (numberOfGame<=8&&page==1){
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
        }else if (numberOfGame<=8&&page==2){
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
        }else if (numberOfGame<=12&&page==2){
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
        }else if (page==(int) Math.ceil((float)numberOfGame/4)){
            pageButton1.setVisible(false);
            pageButton2.setVisible(true);
            pageButton3.setVisible(true);
            pageButton4.setVisible(true);
            pageButton5.setVisible(true);
            pageButton6.setVisible(true);
            pageButton7.setVisible(false);
            pageButton2.setText("<<");
            pageButton6.setText(String.valueOf((int) Math.ceil((float)numberOfGame/4)));
            pageButton3.setText("<");
            pageButton4.setText(String.valueOf((int) Math.ceil((float)numberOfGame/4)-2));
            pageButton5.setText(String.valueOf((int) Math.ceil((float)numberOfGame/4)-1));
            pageButton4.setStyle("-fx-background-color:   #D8BFD8;");
            pageButton6.setStyle("-fx-background-color:   #F0E68C;");
        }else if (page==(int) Math.ceil((float)numberOfGame/4)-1&&numberOfGame>12){
            pageButton1.setVisible(true);
            pageButton2.setVisible(true);
            pageButton3.setVisible(true);
            pageButton4.setVisible(true);
            pageButton5.setVisible(true);
            pageButton6.setVisible(true);
            pageButton7.setVisible(false);
            pageButton1.setText("<<");
            pageButton5.setText(String.valueOf((int) Math.ceil((float)numberOfGame/4)));
            pageButton2.setText("<");
            pageButton3.setText(String.valueOf((int) Math.ceil((float)numberOfGame/4)-2));
            pageButton4.setText(String.valueOf((int) Math.ceil((float)numberOfGame/4)-1));
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

    private void setGamesButton(){
        if (numberOfGame==0){
            gameButton1.setVisible(false);
            gameButton2.setVisible(false);
            gameButton3.setVisible(false);
            gameButton4.setVisible(false);
        }else if (page==(int) Math.ceil((float)numberOfGame/4)&&numberOfGame%4==1){
            Image image=new Image(finalGame.get(numberOfGame-1).getImage());
            ImageView imageView1=new ImageView(image);
            imageView1.setFitWidth(150);
            imageView1.setFitHeight(150);
            gameButton1.setGraphic(imageView1);
            gameButton1.setVisible(true);
            gameButton2.setVisible(false);
            gameButton3.setVisible(false);
            gameButton4.setVisible(false);
            gameButton1.setText(finalGame.get(numberOfGame-1).getName());
        }else if (page==(int) Math.ceil((float)numberOfGame/4)&&numberOfGame%4==2){
            Image image1=new Image(finalGame.get(numberOfGame-2).getImage());
            Image image2=new Image(finalGame.get(numberOfGame-1).getImage());
            ImageView imageView1=new ImageView(image1);
            imageView1.setFitWidth(150);
            imageView1.setFitHeight(150);
            gameButton1.setGraphic(imageView1);
            ImageView imageView2=new ImageView(image2);
            imageView2.setFitWidth(150);
            imageView2.setFitHeight(150);
            gameButton2.setGraphic(imageView2);
            gameButton1.setVisible(true);
            gameButton2.setVisible(true);
            gameButton3.setVisible(false);
            gameButton4.setVisible(false);
            gameButton1.setText(finalGame.get(numberOfGame-2).getName());
            gameButton2.setText(finalGame.get(numberOfGame-1).getName());
        }else if (page==(int) Math.ceil((float)numberOfGame/4)&&numberOfGame%4==3){
            Image image1=new Image(finalGame.get(numberOfGame-3).getImage());
            Image image2=new Image(finalGame.get(numberOfGame-2).getImage());
            Image image3=new Image(finalGame.get(numberOfGame-1).getImage());
            ImageView imageView1=new ImageView(image1);
            imageView1.setFitWidth(150);
            imageView1.setFitHeight(150);
            gameButton1.setGraphic(imageView1);
            ImageView imageView2=new ImageView(image2);
            imageView2.setFitWidth(150);
            imageView2.setFitHeight(150);
            gameButton2.setGraphic(imageView2);
            ImageView imageView3=new ImageView(image3);
            imageView3.setFitWidth(150);
            imageView3.setFitHeight(150);
            gameButton3.setGraphic(imageView3);
            gameButton1.setVisible(true);
            gameButton2.setVisible(true);
            gameButton3.setVisible(true);
            gameButton4.setVisible(false);
            gameButton1.setText(finalGame.get(numberOfGame-3).getName());
            gameButton2.setText(finalGame.get(numberOfGame-2).getName());
            gameButton3.setText(finalGame.get(numberOfGame-1).getName());
        }else {
            Image image1=new Image(finalGame.get(page*4-4).getImage());
            Image image2=new Image(finalGame.get(page*4-3).getImage());
            Image image3=new Image(finalGame.get(page*4-2).getImage());
            Image image4=new Image(finalGame.get(page*4-1).getImage());
            ImageView imageView1=new ImageView(image1);
            imageView1.setFitWidth(150);
            imageView1.setFitHeight(150);
            gameButton1.setGraphic(imageView1);
            ImageView imageView2=new ImageView(image2);
            imageView2.setFitWidth(150);
            imageView2.setFitHeight(150);
            gameButton2.setGraphic(imageView2);
            ImageView imageView3=new ImageView(image3);
            imageView3.setFitWidth(150);
            imageView3.setFitHeight(150);
            gameButton3.setGraphic(imageView3);
            ImageView imageView4=new ImageView(image4);
            imageView4.setFitWidth(150);
            imageView4.setFitHeight(150);
            gameButton4.setGraphic(imageView4);
            gameButton1.setVisible(true);
            gameButton2.setVisible(true);
            gameButton3.setVisible(true);
            gameButton4.setVisible(true);
            gameButton1.setText(finalGame.get(page*4-4).getName());
            gameButton2.setText(finalGame.get(page*4-3).getName());
            gameButton3.setText(finalGame.get(page*4-2).getName());
            gameButton4.setText(finalGame.get(page*4-1).getName());
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            setAllGame();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (LoginPage.getAccessLevel().equals(AccessLevel.user)) {
            gameButton.setVisible(true);
            equipmentButton.setVisible(true);
            if (UserPage.getGameFilter().equals(GameFilter.Store)){
                pageName.setText(String.valueOf(GameFilter.Store));
                setFinalGameStore();
            }else if (UserPage.getGameFilter().equals(GameFilter.Library)){
                pageName.setText(String.valueOf(GameFilter.Library));
                setFinalGameLibrary();
            }else if (UserPage.getGameFilter().equals(GameFilter.friend)){
                //todo
                pageName.setText(UsersPage.getUser().getUsername());
                setFinalGameFriend();
                gameButton1.setMouseTransparent(true);
                gameButton2.setMouseTransparent(true);
                gameButton3.setMouseTransparent(true);
                gameButton4.setMouseTransparent(true);
            }
        } else if (LoginPage.getAccessLevel().equals(AccessLevel.admin)) {
            pageName.setText("Games");
            addButton.setVisible(true);
            setFinalGameAdmin();
        } else if (LoginPage.getAccessLevel().equals(AccessLevel.developer)) {
            pageName.setText("Games");
            addButton.setVisible(true);
            setFinalGameDeveloper();
        }
        tempGame.addAll(finalGame);
        numberOfGame=finalGame.size();
        setPageButton();
        setGamesButton();
    }
}