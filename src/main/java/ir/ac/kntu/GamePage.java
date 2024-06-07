package ir.ac.kntu;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import ir.ac.kntu.objects.Developer;
import ir.ac.kntu.objects.Game;
import ir.ac.kntu.types.*;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import com.jfoenix.controls.JFXRadioButton;

public class GamePage implements Initializable {

    private File selectedFile;

    @FXML
    private JFXButton feedbacksButton;

    @FXML
    private JFXRadioButton beta;


    @FXML
    private JFXRadioButton failure;

    @FXML
    private ImageView imageAddress;

    @FXML
    private Label imageLabel;

    @FXML
    private MenuItem Shooting;

    @FXML
    private MenuItem Simulation;

    @FXML
    private MenuItem Strategy;

    @FXML
    private MenuButton genreItems;
    @FXML
    private TextField rateText;

    @FXML
    private JFXButton backButton;

    @FXML
    private JFXButton button1;

    @FXML
    private Label errorLabel;

    @FXML
    private JFXButton button2;

    @FXML
    private JFXButton exitButton;

    @FXML
    private Label gameName;

    @FXML
    private Label gameNameLabel;

    @FXML
    private TextField gameNameText;

    @FXML
    private TextField genreText;

    @FXML
    private JFXTextArea informationText;

    @FXML
    private TextField priceText;

    @FXML
    private JFXButton rateButton;

    @FXML
    private Slider rateSlider;

    @FXML
    void pressFeedbacksButton(MouseEvent event) throws IOException {
        Feedbacks.setIsGame(true);
        Stage stage=(Stage) feedbacksButton.getScene().getWindow();
        stage.close();
        Stage newStage=new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Feedbacks.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        newStage.initStyle(StageStyle.UNDECORATED);
        newStage.setScene(scene);
        newStage.show();
    }


    @FXML
    void pressImage(MouseEvent event) throws SQLException {
        Stage primaryStage=new Stage();
        primaryStage.setTitle("Choose File");
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        selectedFile = fileChooser.showOpenDialog(primaryStage);

        if (selectedFile != null) {
            Image image=new Image(selectedFile.getAbsolutePath());
            imageAddress.setImage(image);
            imageAddress.setFitWidth(120);
            imageAddress.setFitHeight(120);
        }
    }


    @FXML
    void pressBackButton(MouseEvent event) throws IOException {
        GamesPage.setIsAddMode(false);
        Stage stage=(Stage) backButton.getScene().getWindow();
        stage.close();
        Stage newStage=new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("GamesPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        newStage.initStyle(StageStyle.UNDECORATED);
        newStage.setScene(scene);
        newStage.show();
    }

    @FXML
    void pressButton1(MouseEvent event) throws SQLException, IOException {
        if (button1.getText().equals("Buy")){
            buy();
        }
        if (button1.getText().equals("Add")){
            add();
        }
        if (button1.getText().equals("Change")){
            change();
        }
        if (button1.getText().equals("Community")){
            community();
        }
        if (button1.getText().equals("Feedback")){
            feedback();
        }
    }

    @FXML
    void pressButton2(MouseEvent event) throws SQLException, IOException {
        if (button2.getText().equals("Delete")){
            delete();
        }
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
    void pressRateButton(MouseEvent event) throws SQLException {
        if (GamesPage.getGame().getWhoRated().containsKey(LoginPage.getUser().getUsername())){
            GamesPage.getGame().deleteWhoRate(LoginPage.getUser().getUsername());
            GamesPage.getGame().addRate(LoginPage.getUser().getUsername(),rateSlider.getValue());
        }else {
            GamesPage.getGame().addRate(LoginPage.getUser().getUsername(),rateSlider.getValue());
        }
        rateText.setText(String.valueOf(GamesPage.getGame().getRate()));
        java.sql.Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/data","root","");
        Statement statement=connection.createStatement();
        statement.executeUpdate("UPDATE game SET whoRated='"+mapToString(GamesPage.getGame().getWhoRated())+"' WHERE id="+GamesPage.getGame().getId()+"");
    }


    private String listToString(ArrayList<String> list){
        String line="";
        for (String word:list){
            line+=word+",";
        }
        return line;
    }

    private String mapToString(HashMap<String, Double> rates){
        String line="";
        for (String key:rates.keySet()){
            line=line+key+","+rates.get(key)+",";
        }
        return line;
    }

    private void buy() throws SQLException, IOException {
        Alert sure = new Alert(Alert.AlertType.CONFIRMATION);
        sure.setTitle("Confirmation Dialog");
        sure.setHeaderText("Are you sure to buy it?");
        ButtonType buttonTypeYes = new ButtonType("Yes");
        ButtonType buttonTypeNo = new ButtonType("No");
        sure.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);
        Optional<ButtonType> result = sure.showAndWait();
        if (result.isPresent() && result.get() == buttonTypeYes) {
            if (LoginPage.getUser().getWallet()>=GamesPage.getGame().getPrice()){
                java.sql.Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/data","root","");
                Statement statement=connection.createStatement();
                int wallet=LoginPage.getUser().getWallet()-GamesPage.getGame().getPrice();
                LoginPage.getUser().setWallet(wallet);
                String games=listToString(LoginPage.getUser().getGames())+String.valueOf(GamesPage.getGame().getId())+",";
                statement.executeUpdate("UPDATE user SET wallet="+wallet+",games='"+games+"'  WHERE username='"+LoginPage.getUser().getUsername()+"'");
                LoginPage.getUser().setGames(LoginPage.stringToArray(games));
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText("Action Completed");
                alert.setContentText("you buy it!");
                ButtonType buttonTypeOK = new ButtonType("OK");
                alert.getButtonTypes().setAll(buttonTypeOK);
                alert.showAndWait();
                Stage stage=(Stage) rateButton.getScene().getWindow();
                stage.close();
                Stage newStage=new Stage();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("GamePage.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                newStage.initStyle(StageStyle.UNDECORATED);
                newStage.setScene(scene);
                newStage.show();
            }else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Action Not Possible");
                alert.setContentText("you don't have enough money to buy it!.");
                ButtonType buttonTypeOK = new ButtonType("OK");
                alert.getButtonTypes().setAll(buttonTypeOK);
                alert.showAndWait();
            }
        }
    }

    private void add() throws SQLException, IOException {
        if (gameNameText.getText().equals("")||genreItems.getText().equals("genre")||priceText.getText().equals("")){
            errorLabel.setVisible(true);
            errorLabel.setText("Fill the text!");
            errorLabel.setTextFill(Color.RED);
        }else if (selectedFile==null){
            errorLabel.setVisible(true);
            errorLabel.setText("choose picture!");
            errorLabel.setTextFill(Color.RED);
        }else if (!Pattern.matches("[0-9]+",priceText.getText())){
            errorLabel.setVisible(true);
            errorLabel.setText("price should be number!");
            errorLabel.setTextFill(Color.RED);
        }else{
            java.sql.Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/data","root","");
            Statement statement=connection.createStatement();
            Beta beta1;
            if (beta.isSelected()){
                beta1=Beta.Beta;
            }else {
                beta1=Beta.Normal;
            }
            statement.executeUpdate("INSERT INTO game(name,genre,price,information,beta,image,failure,whoRated,community,feedback,rate) VALUE('"+gameNameText.getText()+"','"+genreItems.getText()+"',"+Integer.parseInt(priceText.getText())+",'"+informationText.getText()+"','"+String.valueOf(beta1)+"','"+selectedFile.getAbsolutePath().replace("\\","\\\\")+"','Correct','','','',0)");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText("Action Completed");
            alert.setContentText("Game add!");
            ButtonType buttonTypeOK = new ButtonType("OK");
            alert.getButtonTypes().setAll(buttonTypeOK);
            alert.showAndWait();
            ResultSet resultSet=statement.executeQuery("SELECT * FROM game");
            String id=null;
            while (resultSet.next()){
                id= String.valueOf(resultSet.getInt("id"));
            }
            if (LoginPage.getAccessLevel().equals(AccessLevel.developer)){
                LoginPage.getDeveloper().addGame(id);
                sendToSQL(LoginPage.getDeveloper().getGames());
            }
            GamesPage.setIsAddMode(false);
            Stage stage=(Stage) button1.getScene().getWindow();
            stage.close();
            Stage newStage=new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("GamesPage.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            newStage.initStyle(StageStyle.UNDECORATED);
            newStage.setScene(scene);
            newStage.show();
        }
    }

    private void delete() throws SQLException, IOException {
        Alert sure = new Alert(Alert.AlertType.CONFIRMATION);
        sure.setTitle("Confirmation Dialog");
        sure.setHeaderText("Are you sure to delete it?");
        ButtonType buttonTypeYes = new ButtonType("Yes");
        ButtonType buttonTypeNo = new ButtonType("No");
        sure.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);
        Optional<ButtonType> result = sure.showAndWait();
        if (result.isPresent() && result.get() == buttonTypeYes) {
            java.sql.Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/data","root","");
            Statement statement=connection.createStatement();
            statement.executeUpdate("DELETE FROM game WHERE id="+GamesPage.getGame().getId()+"");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText("Action Completed");
            alert.setContentText("you delete it!");
            ButtonType buttonTypeOK = new ButtonType("OK");
            alert.getButtonTypes().setAll(buttonTypeOK);
            alert.showAndWait();
            Stage stage=(Stage) button2.getScene().getWindow();
            stage.close();
            Stage newStage=new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("GamesPage.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            newStage.initStyle(StageStyle.UNDECORATED);
            newStage.setScene(scene);
            newStage.show();
        }
    }

    private void change() throws SQLException {
        if (gameNameText.getText().equals(GamesPage.getGame().getName())&&genreItems.getText().equals(String.valueOf(GamesPage.getGame().getGameGenre()))&&priceText.getText().equals(String.valueOf(GamesPage.getGame().getPrice()))&&imageAddress.getImage().getUrl().equals(GamesPage.getGame().getImage())&&informationText.getText().equals(GamesPage.getGame().getInformation())&&
                ((beta.isSelected()&&GamesPage.getGame().getBeta().equals(Beta.Beta))||(!beta.isSelected()&&GamesPage.getGame().getBeta().equals(Beta.Normal)))&&
                ((failure.isSelected()&&GamesPage.getGame().getFailure().equals(Failure.Failure))||(!failure.isSelected()&&GamesPage.getGame().equals(Failure.Correct)))){
            errorLabel.setVisible(true);
            errorLabel.setText("No item change!");
            errorLabel.setTextFill(Color.RED);
        } else if (gameNameText.getText().equals("")||genreItems.getText().equals("genre")||priceText.getText().equals("")){
            errorLabel.setVisible(true);
            errorLabel.setText("Fill the text!");
            errorLabel.setTextFill(Color.RED);
        }else if (!Pattern.matches("[0-9]+",priceText.getText())){
            errorLabel.setVisible(true);
            errorLabel.setText("price should be number!");
            errorLabel.setTextFill(Color.RED);
        }else{
            errorLabel.setVisible(true);
            errorLabel.setText("Change done!");
            errorLabel.setTextFill(Color.GREEN);
            Beta beta1;
            Failure failure1;
            if (beta.isSelected()){
                beta1=Beta.Beta;
            }else {
                beta1=Beta.Normal;
            }
            if (failure.isSelected()){
                failure1=Failure.Failure;
            }else {
                failure1=Failure.Correct;
            }
            java.sql.Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/data","root","");
            Statement statement=connection.createStatement();
            statement.executeUpdate("UPDATE game SET name='"+gameNameText.getText()+"' , genre='"+genreItems.getText()+"' , price='"+priceText.getText()+"' , information='"+informationText.getText()+"' , image='"+imageAddress.getImage().getUrl().replace("\\","\\\\")+"' , failure='"+String.valueOf(failure1)+"' , beta='"+String.valueOf(beta1)+"' WHERE id="+GamesPage.getGame().getId()+"");
        }
    }

    private void community() throws IOException {
        Community.setIsGame(true);
        Stage stage=(Stage) button1.getScene().getWindow();
        stage.close();
        Stage newStage=new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Community.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        newStage.initStyle(StageStyle.UNDECORATED);
        newStage.setScene(scene);
        newStage.show();
    }

    private void feedback() throws IOException {
        Feedback.setIsGame(true);
        Stage stage=(Stage) button1.getScene().getWindow();
        stage.close();
        Stage newStage=new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Feedback.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        newStage.initStyle(StageStyle.UNDECORATED);
        newStage.setScene(scene);
        newStage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (LoginPage.getAccessLevel().equals(AccessLevel.user)) {
            if (UserPage.getGameFilter().equals(GameFilter.Store) && !LoginPage.getUser().getGames().contains(String.valueOf(GamesPage.getGame().getId()))) {
                gameName.setText(GamesPage.getGame().getName());
                genreText.setText(String.valueOf(GamesPage.getGame().getGameGenre()));
                priceText.setText(String.valueOf(GamesPage.getGame().getPrice()));
                rateText.setText(String.valueOf(GamesPage.getGame().getRate()));
                informationText.setText(GamesPage.getGame().getInformation());
                button1.setVisible(true);
            } else {
                gameName.setText(GamesPage.getGame().getName());
                genreText.setText(String.valueOf(GamesPage.getGame().getGameGenre()));
                priceText.setText(String.valueOf(GamesPage.getGame().getPrice()));
                rateText.setText(String.valueOf(GamesPage.getGame().getRate()));
                informationText.setText(GamesPage.getGame().getInformation());
                rateSlider.setVisible(true);
                rateButton.setVisible(true);
                button2.setVisible(true);
                button1.setVisible(true);
                rateSlider.setValue(GamesPage.getGame().getRate());
                if (GamesPage.getGame().getBeta().equals(Beta.Beta)) {
                    button1.setText("Feedback");
                } else {
                    button1.setText("Community");
                }
            }
        } else {
            if (!GamesPage.isAddMode()) {
                gameName.setText(GamesPage.getGame().getName());
                genreText.setText(String.valueOf(GamesPage.getGame().getGameGenre()));
                priceText.setText(String.valueOf(GamesPage.getGame().getPrice()));
                rateText.setText(String.valueOf(GamesPage.getGame().getRate()));
                informationText.setText(GamesPage.getGame().getInformation());
                gameNameLabel.setVisible(true);
                gameNameText.setVisible(true);
                gameNameText.setText(GamesPage.getGame().getName());
                button2.setVisible(true);
                button1.setVisible(true);
                button1.setText("Change");
                button2.setText("Delete");
                priceText.setEditable(true);
                informationText.setEditable(true);
                gameNameText.setEditable(true);
                rateText.setStyle("-fx-background-color:   #D8BFD8;");
                priceText.setStyle("-fx-background-color:   #D8BFD8;");
                genreItems.setVisible(true);
                genreItems.setText(String.valueOf(GamesPage.getGame().getGameGenre()));
                failure.setVisible(true);
                beta.setVisible(true);
                imageLabel.setVisible(true);
                imageAddress.setVisible(true);
                Image image=new Image(GamesPage.getGame().getImage());
                imageAddress.setImage(image);
                if (GamesPage.getGame().getFailure().equals(Failure.Failure)){
                    failure.setSelected(true);
                }
                if (GamesPage.getGame().getBeta().equals(Beta.Beta)){
                    beta.setSelected(true);
                }
                if (GamesPage.getGame().getBeta().equals(Beta.Beta)) {
                    feedbacksButton.setVisible(true);
                }
            }else {
                gameNameLabel.setVisible(true);
                gameNameText.setVisible(true);
                button1.setVisible(true);
                button1.setText("Add");
                informationText.setEditable(true);
                gameNameText.setEditable(true);
                rateText.setStyle("-fx-background-color:   #D8BFD8;");
                priceText.setStyle("-fx-background-color:   #D8BFD8;");
                priceText.setEditable(true);
                genreItems.setVisible(true);
                beta.setVisible(true);
                imageLabel.setVisible(true);
                imageAddress.setVisible(true);
            }
            imageAddress.setFitHeight(120);
            imageAddress.setFitWidth(120);
            Shooting.setOnAction(event1 -> {
                genreItems.setText(String.valueOf(GameGenre.Shooting));
            });
            Strategy.setOnAction(event1 -> {
                genreItems.setText(String.valueOf(GameGenre.Strategy));
            });
            Simulation.setOnAction(event1 -> {
                genreItems.setText(String.valueOf(GameGenre.Simulation));
            });
            if (LoginPage.getAccessLevel().equals(AccessLevel.developer)){
                failure.setVisible(false);
            }
        }
    }

    private void sendToSQL(ArrayList<String> list) throws SQLException {
        String line="";
        for (String word:list){
            line+=word+",";
        }
        java.sql.Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/data","root","");
        Statement statement=connection.createStatement();
        statement.executeUpdate("UPDATE developer SET game='"+line+"' WHERE id="+LoginPage.getDeveloper().getId()+"");
    }
}
