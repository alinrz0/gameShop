package ir.ac.kntu;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import ir.ac.kntu.types.*;
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
import java.sql.*;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class EquipmentPage implements Initializable {

    private File selectedFile;

    @FXML
    private Label errorLabel;
    @FXML
    private JFXButton crashReportButton;
    @FXML
    private TextField priceText;

    @FXML
    private JFXButton feedbacksButton;

    @FXML
    private Label numberLabel;

    @FXML
    private TextField numberText;

    @FXML
    private JFXButton backButton;

    @FXML
    private JFXButton button1;

    @FXML
    private JFXButton button2;

    @FXML
    private Label compatibleAndRefreshRateLabel;

    @FXML
    private Label timeResponseLabel;

    @FXML
    private TextField compatibleAndRefreshRateText;

    @FXML
    private TextField timeResponseText;

    @FXML
    private MenuButton connectionItems;

    @FXML
    private MenuButton deviceItems;

    @FXML
    private Label deviceText;

    @FXML
    private JFXButton exitButton;

    @FXML
    private Label gameName;

    @FXML
    private Label gameNameLabel;

    @FXML
    private TextField gameNameText;

    @FXML
    private MenuItem gamingControllerItem;

    @FXML
    private ImageView imageAddress;

    @FXML
    private Label imageLabel;

    @FXML
    private JFXTextArea informationText;

    @FXML
    private MenuItem monitorItem;

    @FXML
    private TextField sizeText;

    @FXML
    private Label wireAndSizeLabel;

    @FXML
    private MenuItem wirelessItem;

    @FXML
    private MenuItem withWireItem;

    @FXML
    void pressCrashReportButton(MouseEvent event) throws IOException {
        Feedback.setIsGame(false);
        Stage stage=(Stage) button1.getScene().getWindow();
        stage.close();
        Stage newStage=new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Feedback.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        newStage.initStyle(StageStyle.UNDECORATED);
        newStage.setScene(scene);
        newStage.show();
    }

    @FXML
    void pressFeedbacksButton(MouseEvent event) throws IOException {
        Feedbacks.setIsGame(false);
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
    void pressBackButton(MouseEvent event) throws IOException {
        EquipmentsPage.setIsAddModeE(false);
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.close();
        Stage newStage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("EquipmentsPage.fxml"));
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
    }

    @FXML
    void pressButton2(MouseEvent event) throws SQLException, IOException {
        if (button2.getText().equals("Delete")){
            delete();
        }else if (button2.getText().equals("Community")){
            community();
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
    void pressImage(MouseEvent event) {
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Choose File");
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        selectedFile = fileChooser.showOpenDialog(primaryStage);

        if (selectedFile != null) {
            Image image = new Image(selectedFile.getAbsolutePath());
            imageAddress.setImage(image);
            imageAddress.setFitWidth(120);
            imageAddress.setFitHeight(120);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (LoginPage.getAccessLevel().equals(AccessLevel.user)) {
            gameName.setText(EquipmentsPage.getEquipment().getName());
            deviceText.setText(String.valueOf(EquipmentsPage.getEquipment().getDevice()));
            deviceText.setVisible(true);
            priceText.setText(String.valueOf(EquipmentsPage.getEquipment().getPrice()));
            if (deviceText.getText().equals(String.valueOf(Device.monitor))) {
                compatibleAndRefreshRateLabel.setText("refresh rate:");
                compatibleAndRefreshRateText.setText(EquipmentsPage.getEquipment().getRefreshRate());
                wireAndSizeLabel.setText("Size:");
                sizeText.setVisible(true);
                sizeText.setText(String.valueOf(EquipmentsPage.getEquipment().getSize()));
                timeResponseText.setText(String.valueOf(EquipmentsPage.getEquipment().getResponseTime()));
                timeResponseText.setVisible(true);
            } else {
                compatibleAndRefreshRateLabel.setText("compatible:");
                compatibleAndRefreshRateText.setText(EquipmentsPage.getEquipment().getCompatible());
                wireAndSizeLabel.setText("Connection:");
                sizeText.setVisible(true);
                sizeText.setText(String.valueOf(EquipmentsPage.getEquipment().getConnection()));
                timeResponseLabel.setVisible(false);
            }
            compatibleAndRefreshRateText.setVisible(true);
            informationText.setText(EquipmentsPage.getEquipment().getInformation());
            button1.setVisible(true);
            if (!(UserPage.getGameFilter().equals(GameFilter.Store) && !LoginPage.getUser().getEquipment().contains(String.valueOf(EquipmentsPage.getEquipment().getId())))) {
                button2.setVisible(true);
                crashReportButton.setVisible(true);
            }
            if (EquipmentsPage.getEquipment().getNumber()<1){
                button1.setVisible(false);
            }
        } else {
            if (!EquipmentsPage.isAddModeE()) {
                priceText.setText(String.valueOf(EquipmentsPage.getEquipment().getPrice()));
                feedbacksButton.setVisible(true);
                gameName.setText(EquipmentsPage.getEquipment().getName());
                gameNameLabel.setVisible(true);
                numberLabel.setVisible(true);
                gameNameText.setVisible(true);
                numberText.setVisible(true);
                gameNameText.setText(EquipmentsPage.getEquipment().getName());
                numberText.setText(String.valueOf(EquipmentsPage.getEquipment().getNumber()));
                deviceItems.setText(String.valueOf(EquipmentsPage.getEquipment().getDevice()));
                deviceItems.setVisible(true);
                if (deviceItems.getText().equals(String.valueOf(Device.monitor))) {
                    compatibleAndRefreshRateLabel.setText("refresh rate:");
                    compatibleAndRefreshRateText.setText(EquipmentsPage.getEquipment().getRefreshRate());
                    wireAndSizeLabel.setText("Size:");
                    sizeText.setVisible(true);
                    sizeText.setText(String.valueOf(EquipmentsPage.getEquipment().getSize()));
                    sizeText.setStyle("-fx-background-color:   #D8BFD8;");
                    timeResponseText.setText(String.valueOf(EquipmentsPage.getEquipment().getResponseTime()));
                    timeResponseText.setVisible(true);
                    timeResponseText.setStyle("-fx-background-color:   #D8BFD8;");
                } else {
                    compatibleAndRefreshRateLabel.setText("compatible:");
                    compatibleAndRefreshRateText.setText(EquipmentsPage.getEquipment().getCompatible());
                    wireAndSizeLabel.setText("Connection:");
                    connectionItems.setVisible(true);
                    connectionItems.setText(String.valueOf(EquipmentsPage.getEquipment().getConnection()));
                    timeResponseLabel.setVisible(false);
                }
                compatibleAndRefreshRateText.setStyle("-fx-background-color:   #D8BFD8;");
                compatibleAndRefreshRateText.setEditable(true);
                informationText.setText(EquipmentsPage.getEquipment().getInformation());
                gameNameText.setText(EquipmentsPage.getEquipment().getName());
                button2.setVisible(true);
                button1.setVisible(true);
                button1.setText("Change");
                button2.setText("Delete");
                informationText.setEditable(true);
                imageLabel.setVisible(true);
                imageAddress.setVisible(true);
                Image image = new Image(EquipmentsPage.getEquipment().getImage());
                imageAddress.setImage(image);
            } else {
                timeResponseLabel.setVisible(false);
                deviceItems.setVisible(true);
                gameNameLabel.setVisible(true);
                gameNameText.setVisible(true);
                numberText.setVisible(true);
                numberLabel.setVisible(true);
                button1.setVisible(true);
                button1.setText("Add");
                informationText.setEditable(true);
                imageLabel.setVisible(true);
                imageAddress.setVisible(true);
                monitorItem.setOnAction(event1 -> {
                    deviceItems.setText(String.valueOf(Device.monitor));
                    compatibleAndRefreshRateLabel.setText("refresh rate:");
                    wireAndSizeLabel.setText("Size:");
                    wireAndSizeLabel.setVisible(true);
                    sizeText.setVisible(true);
                    sizeText.setStyle("-fx-background-color:   #D8BFD8;");
                    timeResponseText.setVisible(true);
                    timeResponseText.setStyle("-fx-background-color:   #D8BFD8;");
                    compatibleAndRefreshRateText.setStyle("-fx-background-color:   #D8BFD8;");
                    compatibleAndRefreshRateText.setEditable(true);
                });
                gamingControllerItem.setOnAction(event1 -> {
                    deviceItems.setText(String.valueOf(Device.gamingController));
                    compatibleAndRefreshRateLabel.setText("compatible:");
                    wireAndSizeLabel.setText("Connection:");
                    connectionItems.setVisible(true);
                    timeResponseLabel.setVisible(false);
                    compatibleAndRefreshRateText.setStyle("-fx-background-color:   #D8BFD8;");
                    compatibleAndRefreshRateText.setEditable(true);
                });
            }
            priceText.setEditable(true);
            priceText.setStyle("-fx-background-color:   #D8BFD8;");
            imageAddress.setFitHeight(120);
            imageAddress.setFitWidth(120);
            wirelessItem.setOnAction(event1 -> {
                connectionItems.setText(String.valueOf(Connection0.wireless));
            });
            withWireItem.setOnAction(event1 -> {
                connectionItems.setText(String.valueOf(Connection0.withWire));
            });
            sizeText.setEditable(true);
            timeResponseText.setEditable(true);
            monitorItem.setOnAction(event1 -> {
                deviceItems.setText(String.valueOf(Device.monitor));
                compatibleAndRefreshRateLabel.setText("refresh rate:");
                wireAndSizeLabel.setText("Size:");
                wireAndSizeLabel.setVisible(true);
                sizeText.setVisible(true);
                sizeText.setStyle("-fx-background-color:   #D8BFD8;");
                timeResponseText.setVisible(true);
                timeResponseText.setStyle("-fx-background-color:   #D8BFD8;");
                timeResponseLabel.setVisible(true);
                compatibleAndRefreshRateText.setStyle("-fx-background-color:   #D8BFD8;");
                compatibleAndRefreshRateText.setEditable(true);
                connectionItems.setVisible(false);
            });
            gamingControllerItem.setOnAction(event1 -> {
                deviceItems.setText(String.valueOf(Device.gamingController));
                compatibleAndRefreshRateLabel.setText("compatible:");
                wireAndSizeLabel.setText("Connection:");
                sizeText.setVisible(false);
                connectionItems.setVisible(true);
                timeResponseLabel.setVisible(false);
                timeResponseText.setVisible(false);
                compatibleAndRefreshRateText.setStyle("-fx-background-color:   #D8BFD8;");
                compatibleAndRefreshRateText.setEditable(true);
            });
        }
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
            if (LoginPage.getUser().getWallet() >= EquipmentsPage.getEquipment().getPrice()) {
                java.sql.Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/data", "root", "");
                Statement statement = connection.createStatement();
                int wallet = LoginPage.getUser().getWallet() - EquipmentsPage.getEquipment().getPrice();
                LoginPage.getUser().setWallet(wallet);
                EquipmentsPage.getEquipment().setNumber(EquipmentsPage.getEquipment().getNumber()-1);
                String equipment = listToString(LoginPage.getUser().getEquipment()) + String.valueOf(EquipmentsPage.getEquipment().getId()) + ",";
                statement.executeUpdate("UPDATE user SET wallet=" + wallet + ",equipment='" + equipment + "'  WHERE username='" + LoginPage.getUser().getUsername() + "'");
                statement.executeUpdate("UPDATE equipment SET number=" + EquipmentsPage.getEquipment().getNumber() + " WHERE id='" + EquipmentsPage.getEquipment().getId() + "'");
                LoginPage.getUser().setEquipment(LoginPage.stringToArray(equipment));
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText("Action Completed");
                alert.setContentText("you buy it!");
                ButtonType buttonTypeOK = new ButtonType("OK");
                alert.getButtonTypes().setAll(buttonTypeOK);
                alert.showAndWait();
                Stage stage = (Stage) button1.getScene().getWindow();
                stage.close();
                Stage newStage = new Stage();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("EquipmentPage.fxml"));
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
        if (gameNameText.getText().equals("")||deviceItems.getText().equals("device")||priceText.getText().equals("")||numberText.getText().equals("")||compatibleAndRefreshRateText.getText().equals("")){
            errorLabel.setVisible(true);
            errorLabel.setText("Fill the text!");
            errorLabel.setTextFill(Color.RED);
        }else if (deviceItems.getText().equals(String.valueOf(Device.monitor))&&(sizeText.getText().equals("")||timeResponseText.getText().equals(""))){
            errorLabel.setVisible(true);
            errorLabel.setText("Fill the text!");
            errorLabel.setTextFill(Color.RED);
        }else if (deviceItems.getText().equals(String.valueOf(Device.gamingController))&&connectionItems.getText().equals("connection")){
            errorLabel.setVisible(true);
            errorLabel.setText("Fill the text!");
            errorLabel.setTextFill(Color.RED);
        } else if (selectedFile==null){
            errorLabel.setVisible(true);
            errorLabel.setText("choose picture!");
            errorLabel.setTextFill(Color.RED);
        }else if (!Pattern.matches("[0-9]+",priceText.getText())){
            errorLabel.setVisible(true);
            errorLabel.setText("price should be integer!");
            errorLabel.setTextFill(Color.RED);
        }else if (!Pattern.matches("[0-9]+",numberText.getText())){
            errorLabel.setVisible(true);
            errorLabel.setText("number should be integer!");
            errorLabel.setTextFill(Color.RED);
        }else if (deviceItems.getText().equals("monitor")&&!Pattern.matches("(?=^0*[1-9]\\d*(\\.\\d+)?$)[-+]?[0-9]*\\.?[0-9]",sizeText.getText())){
            errorLabel.setVisible(true);
            errorLabel.setText("size should be float!");
            errorLabel.setTextFill(Color.RED);
        }else if (deviceItems.getText().equals("monitor")&&!Pattern.matches("(?=^0*[1-9]\\d*(\\.\\d+)?$)[-+]?[0-9]*\\.?[0-9]",timeResponseText.getText())){
            errorLabel.setVisible(true);
            errorLabel.setText("time response should be float!");
            errorLabel.setTextFill(Color.RED);
        }else{
            java.sql.Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/data","root","");
            Statement statement=connection.createStatement();
            if (deviceItems.getText().equals(String.valueOf(Device.monitor))){
                statement.executeUpdate("INSERT INTO equipment(name,device,price,compatible,connection,image,refreshRate,size,community,feedback,responseTime,number,information) VALUE('"+gameNameText.getText()+"','"+deviceItems.getText()+"',"+Integer.parseInt(priceText.getText())+",'','','"+selectedFile.getAbsolutePath().replace("\\","\\\\")+"','"+compatibleAndRefreshRateText.getText()+"',"+Double.parseDouble(sizeText.getText())+",'','',"+Double.parseDouble(timeResponseText.getText())+","+Integer.parseInt(numberText.getText())+",'"+informationText.getText()+"')");
            } else if (deviceItems.getText().equals(String.valueOf(Device.gamingController))) {
                statement.executeUpdate("INSERT INTO equipment(name,device,price,compatible,connection,image,refreshRate,size,community,feedback,responseTime,number,information) VALUE('"+gameNameText.getText()+"','"+deviceItems.getText()+"',"+Integer.parseInt(priceText.getText())+",'"+compatibleAndRefreshRateText.getText()+"','"+connectionItems.getText()+"','"+selectedFile.getAbsolutePath().replace("\\","\\\\")+"','',0,'','',0,"+Integer.parseInt(numberText.getText())+",'"+informationText.getText()+"')");
            }
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText("Action Completed");
            alert.setContentText("Equipment add!");
            ButtonType buttonTypeOK = new ButtonType("OK");
            alert.getButtonTypes().setAll(buttonTypeOK);
            alert.showAndWait();
            ResultSet resultSet=statement.executeQuery("SELECT * FROM equipment");
            String id=null;
            while (resultSet.next()){
                id= String.valueOf(resultSet.getInt("id"));
            }
            if (LoginPage.getAccessLevel().equals(AccessLevel.seller)){
                LoginPage.getSeller().addEquipment(id);
                sendToSQL(LoginPage.getSeller().getEquipments());
            }
            EquipmentsPage.setIsAddModeE(false);
            Stage stage=(Stage) button1.getScene().getWindow();
            stage.close();
            Stage newStage=new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("EquipmentsPage.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            newStage.initStyle(StageStyle.UNDECORATED);
            newStage.setScene(scene);
            newStage.show();
        }
    }

    private String listToString(ArrayList<String> list){
        String line="";
        for (String word:list){
            line+=word+",";
        }
        return line;
    }

    private void sendToSQL(ArrayList<String> list) throws SQLException {
        String line="";
        for (String word:list){
            line+=word+",";
        }
        java.sql.Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/data","root","");
        Statement statement=connection.createStatement();
        statement.executeUpdate("UPDATE seller SET equipment='"+line+"' WHERE id="+LoginPage.getSeller().getId()+"");
    }

    private void change() throws SQLException {
        if (gameNameText.getText().equals(EquipmentsPage.getEquipment().getName())&&deviceItems.getText().equals(String.valueOf(EquipmentsPage.getEquipment().getDevice()))&&priceText.getText().equals(String.valueOf(EquipmentsPage.getEquipment().getPrice()))&&
                imageAddress.getImage().getUrl().equals(EquipmentsPage.getEquipment().getImage())&&informationText.getText().equals(EquipmentsPage.getEquipment().getInformation()) &&numberText.getText().equals(String.valueOf(EquipmentsPage.getEquipment().getNumber()))&&
                (compatibleAndRefreshRateText.getText().equals(EquipmentsPage.getEquipment().getCompatible())||compatibleAndRefreshRateText.getText().equals(EquipmentsPage.getEquipment().getRefreshRate()))&&
                (deviceItems.getText().equals(String.valueOf(Device.monitor))&&timeResponseText.getText().equals(String.valueOf(EquipmentsPage.getEquipment().getResponseTime()))&&sizeText.getText().equals(String.valueOf(EquipmentsPage.getEquipment().getSize()))||
                        deviceItems.getText().equals(String.valueOf(Device.gamingController))&&connectionItems.getText().equals(String.valueOf(EquipmentsPage.getEquipment().getConnection())))){
            errorLabel.setVisible(true);
            errorLabel.setText("No item change!");
            errorLabel.setTextFill(Color.RED);
        }else if (gameNameText.getText().equals("")||deviceItems.getText().equals("device")||priceText.getText().equals("")||numberText.getText().equals("")||compatibleAndRefreshRateText.getText().equals("")){
            errorLabel.setVisible(true);
            errorLabel.setText("Fill the text!");
            errorLabel.setTextFill(Color.RED);
        }else if (deviceItems.getText().equals(String.valueOf(Device.monitor))&&(sizeText.getText().equals("")||timeResponseText.getText().equals(""))){
            errorLabel.setVisible(true);
            errorLabel.setText("Fill the text!");
            errorLabel.setTextFill(Color.RED);
        }else if (deviceItems.getText().equals(String.valueOf(Device.gamingController))&&connectionItems.getText().equals("connection")){
            errorLabel.setVisible(true);
            errorLabel.setText("Fill the text!");
            errorLabel.setTextFill(Color.RED);
        }else if (!Pattern.matches("[0-9]+",priceText.getText())){
            errorLabel.setVisible(true);
            errorLabel.setText("price should be integer!");
            errorLabel.setTextFill(Color.RED);
        }else if (!Pattern.matches("[0-9]+",numberText.getText())){
            errorLabel.setVisible(true);
            errorLabel.setText("number should be integer!");
            errorLabel.setTextFill(Color.RED);
        }else if (deviceItems.getText().equals("monitor")&&!Pattern.matches("(?=^0*[1-9]\\d*(\\.\\d+)?$)[-+]?[0-9]*\\.?[0-9]",sizeText.getText())){
            errorLabel.setVisible(true);
            errorLabel.setText("size should be float!");
            errorLabel.setTextFill(Color.RED);
        }else if (deviceItems.getText().equals("monitor")&&!Pattern.matches("(?=^0*[1-9]\\d*(\\.\\d+)?$)[-+]?[0-9]*\\.?[0-9]",timeResponseText.getText())){
            errorLabel.setVisible(true);
            errorLabel.setText("time response should be float!");
            errorLabel.setTextFill(Color.RED);
        }else{
            errorLabel.setVisible(true);
            errorLabel.setText("Change done!");
            errorLabel.setTextFill(Color.GREEN);
            Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/data","root","");
            Statement statement=connection.createStatement();
            if (deviceItems.getText().equals(String.valueOf(Device.monitor))) {
                statement.executeUpdate("UPDATE equipment SET name='" + gameNameText.getText() + "' , device='" + deviceItems.getText() + "' , price='" + priceText.getText() + "' , information='" + informationText.getText() + "' , image='" + imageAddress.getImage().getUrl().replace("\\", "\\\\") + "' , number=" + Integer.parseInt(numberText.getText()) + " , refreshRate='" + compatibleAndRefreshRateText.getText() + "', size="+Double.parseDouble(sizeText.getText())+" , responseTime="+Double.parseDouble(informationText.getText())+" WHERE id=" + EquipmentsPage.getEquipment().getId() + "");
            }else if(deviceItems.getText().equals(String.valueOf(Device.gamingController))){
                statement.executeUpdate("UPDATE equipment SET name='" + gameNameText.getText() + "' , device='" + deviceItems.getText() + "' , price='" + priceText.getText() + "' , information='" + informationText.getText() + "' , image='" + imageAddress.getImage().getUrl().replace("\\", "\\\\") + "' , number=" + Integer.parseInt(numberText.getText()) + " , compatible='" + compatibleAndRefreshRateText.getText() + "', connection='"+connectionItems.getText()+"'  WHERE id=" + EquipmentsPage.getEquipment().getId() + "");
            }
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
            statement.executeUpdate("DELETE FROM equipment WHERE id="+EquipmentsPage.getEquipment().getId()+"");
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
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("EquipmentsPage.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            newStage.initStyle(StageStyle.UNDECORATED);
            newStage.setScene(scene);
            newStage.show();
        }
    }

    private void community() throws IOException {
        Community.setIsGame(false);
        Stage stage=(Stage) button2.getScene().getWindow();
        stage.close();
        Stage newStage=new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Community.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        newStage.initStyle(StageStyle.UNDECORATED);
        newStage.setScene(scene);
        newStage.show();
    }
}
