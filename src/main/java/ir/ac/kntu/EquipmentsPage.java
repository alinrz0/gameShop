package ir.ac.kntu;

import ir.ac.kntu.objects.Equipment;
import ir.ac.kntu.objects.Game;
import ir.ac.kntu.types.*;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class EquipmentsPage implements Initializable {
    private static boolean isAddModeE=false;

    private boolean filter=false;

    private boolean search=false;

    private ArrayList<Equipment> allEquipment=new ArrayList<>();

    private ArrayList<Equipment> finalEquipment=new ArrayList<>();

    private ArrayList<Equipment> tempEquipment=new ArrayList<>();

    private ArrayList<Equipment> tempDeviceEquipment=new ArrayList<>();

    private ArrayList<Equipment> tempFilterEquipment=new ArrayList<>();

    private int page=1;

    private int numberOfEquipment;

    private static Equipment equipment=new Equipment();

    public static Equipment getEquipment() {
        return equipment;
    }

    public static void setEquipment(Equipment equipment) {
        EquipmentsPage.equipment = equipment;
    }

    public static boolean isAddModeE() {
        return isAddModeE;
    }

    public static void setIsAddModeE(boolean isAddMode) {
        EquipmentsPage.isAddModeE = isAddMode;
    }

    @FXML
    private JFXRadioButton gamingControllerButton;

    @FXML
    private JFXRadioButton monitorButton;

    @FXML
    private JFXButton addButton;

    @FXML
    private JFXButton backButton;

    @FXML
    private JFXRadioButton equipmentButton;

    @FXML
    private JFXButton equipmentButton1;

    @FXML
    private JFXButton equipmentButton2;

    @FXML
    private JFXButton equipmentButton3;

    @FXML
    private JFXButton equipmentButton4;

    @FXML
    private Label erorrLabel;

    @FXML
    private JFXButton exitButton;

    @FXML
    private JFXButton filterButton;

    @FXML
    private JFXRadioButton gameButton;

    @FXML
    private TextField maxPriceText;

    @FXML
    private TextField minPriceText;

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
    void clickOnPage(MouseEvent event) {
        erorrLabel.setVisible(false);
    }

    @FXML
    void pressMonitorButton(MouseEvent event) {
        finalEquipment.clear();
        if(!monitorButton.isSelected()&&gamingControllerButton.isSelected()){
            for (Equipment equipment1:tempEquipment){
                if(equipment1.getDevice().equals(Device.gamingController)){
                    finalEquipment.add(equipment1);
                }
            }
            tempDeviceEquipment.clear();
            tempDeviceEquipment.addAll(finalEquipment);
            finalEquipment.clear();
            for (Equipment equipment1:tempFilterEquipment){
                if(equipment1.getDevice().equals(Device.gamingController)){
                    finalEquipment.add(equipment1);
                }
            }
        }else if (monitorButton.isSelected()&&!gamingControllerButton.isSelected()){
            for (Equipment equipment1:tempEquipment){
                if(equipment1.getDevice().equals(Device.monitor)){
                    finalEquipment.add(equipment1);
                }
            }
            tempDeviceEquipment.clear();
            tempDeviceEquipment.addAll(finalEquipment);
            finalEquipment.clear();
            for (Equipment equipment1:tempFilterEquipment){
                if(equipment1.getDevice().equals(Device.monitor)){
                    finalEquipment.add(equipment1);
                }
            }
        }else if(!monitorButton.isSelected()&&!gamingControllerButton.isSelected()){
        }else {
            finalEquipment.addAll(tempFilterEquipment);
        }
        numberOfEquipment=finalEquipment.size();
        setPageButton();
        setEquipmentButton();
    }

    @FXML
    void pressGamingControllerButton(MouseEvent event) {
        finalEquipment.clear();
        if(!monitorButton.isSelected()&&gamingControllerButton.isSelected()){
            for (Equipment equipment1:tempEquipment){
                if(equipment1.getDevice().equals(Device.gamingController)){
                    finalEquipment.add(equipment1);
                }
            }
            tempDeviceEquipment.clear();
            tempDeviceEquipment.addAll(finalEquipment);
            finalEquipment.clear();
            for (Equipment equipment1:tempFilterEquipment){
                if(equipment1.getDevice().equals(Device.gamingController)){
                    finalEquipment.add(equipment1);
                }
            }
        }else if (monitorButton.isSelected()&&!gamingControllerButton.isSelected()){
            for (Equipment equipment1:tempEquipment){
                if(equipment1.getDevice().equals(Device.monitor)){
                    finalEquipment.add(equipment1);
                }
            }
            tempDeviceEquipment.clear();
            tempDeviceEquipment.addAll(finalEquipment);
            finalEquipment.clear();
            for (Equipment equipment1:tempFilterEquipment){
                if(equipment1.getDevice().equals(Device.monitor)){
                    finalEquipment.add(equipment1);
                }
            }
        }else if(!monitorButton.isSelected()&&!gamingControllerButton.isSelected()){
        }else {
            finalEquipment.addAll(tempFilterEquipment);
        }
        numberOfEquipment=finalEquipment.size();
        setPageButton();
        setEquipmentButton();
    }
    @FXML
    void pressAddButton(MouseEvent event) throws IOException {
        isAddModeE=true;
        Stage stage=(Stage) addButton.getScene().getWindow();
        stage.close();
        Stage newStage=new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("EquipmentPage.fxml"));
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
    void pressEquipmentButton(MouseEvent event) throws IOException {
        if (!equipmentButton.isSelected()){
            equipmentButton.setSelected(true);
        }
        gameButton.setSelected(false);
        erorrLabel.setVisible(false);
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
            finalEquipment.clear();
            for (Equipment equipment:tempEquipment){
                if (search==true&&equipment.getName().startsWith(searchText.getText())&&equipment.getPrice()<=Integer.parseInt(maxPriceText.getText())&&equipment.getPrice()>=Integer.parseInt(minPriceText.getText())){
                    finalEquipment.add(equipment);
                }else if (search==false&&equipment.getPrice()<=Integer.parseInt(maxPriceText.getText())&&equipment.getPrice()>=Integer.parseInt(minPriceText.getText())){
                    finalEquipment.add(equipment);
                }
            }
            tempFilterEquipment.clear();
            tempFilterEquipment.addAll(finalEquipment);
            finalEquipment.clear();
            for (Equipment equipment:tempDeviceEquipment){
                if (search==true&&equipment.getName().startsWith(searchText.getText())&&equipment.getPrice()<=Integer.parseInt(maxPriceText.getText())&&equipment.getPrice()>=Integer.parseInt(minPriceText.getText())){
                    finalEquipment.add(equipment);
                }else if (search==false&&equipment.getPrice()<=Integer.parseInt(maxPriceText.getText())&&equipment.getPrice()>=Integer.parseInt(minPriceText.getText())){
                    finalEquipment.add(equipment);
                }
            }
            numberOfEquipment=finalEquipment.size();
            setPageButton();
            setEquipmentButton();
            filter=true;
        }else {
            erorrLabel.setVisible(true);
            finalEquipment.clear();
            finalEquipment.addAll(tempEquipment);
            numberOfEquipment = finalEquipment.size();
            setPageButton();
            setEquipmentButton();
            filter = false;
        }
    }

    @FXML
    void pressGameButton(MouseEvent event) throws IOException {
        if (!gameButton.isSelected()){
            gameButton.setSelected(true);
        }
        equipmentButton.setSelected(false);
        erorrLabel.setVisible(false);
        Stage stage=(Stage) gameButton.getScene().getWindow();
        stage.close();
        Stage newStage=new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("GamesPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        newStage.initStyle(StageStyle.UNDECORATED);
        newStage.setScene(scene);
        newStage.show();
    }

    @FXML
    void pressEquipmentButton1(MouseEvent event) throws IOException {
        equipment=finalEquipment.get(page*4-4);
        Stage stage=(Stage) equipmentButton1.getScene().getWindow();
        stage.close();
        Stage newStage=new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("EquipmentPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        newStage.initStyle(StageStyle.UNDECORATED);
        newStage.setScene(scene);
        newStage.show();
    }

    @FXML
    void pressEquipmentButton2(MouseEvent event) throws IOException {
        equipment=finalEquipment.get(page*4-3);
        Stage stage=(Stage) equipmentButton2.getScene().getWindow();
        stage.close();
        Stage newStage=new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("EquipmentPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        newStage.initStyle(StageStyle.UNDECORATED);
        newStage.setScene(scene);
        newStage.show();
    }

    @FXML
    void pressEquipmentButton3(MouseEvent event) throws IOException {
        equipment=finalEquipment.get(page*4-2);
        Stage stage=(Stage) equipmentButton3.getScene().getWindow();
        stage.close();
        Stage newStage=new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("EquipmentPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        newStage.initStyle(StageStyle.UNDECORATED);
        newStage.setScene(scene);
        newStage.show();
    }

    @FXML
    void pressEquipmentButton4(MouseEvent event) throws IOException {
        equipment=finalEquipment.get(page*4-1);
        Stage stage=(Stage) equipmentButton4.getScene().getWindow();
        stage.close();
        Stage newStage=new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("EquipmentPage.fxml"));
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
            page= (int) Math.ceil((float)numberOfEquipment/4);
        }else {
            page= Integer.parseInt(pageButton1.getText());
        }
        backPageButtonToDefault();
        setPageButton();
        erorrLabel.setVisible(false);
        setEquipmentButton();
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
            page= (int) Math.ceil((float)numberOfEquipment/4);
        }else {
            page= Integer.parseInt(pageButton2.getText());
        }
        backPageButtonToDefault();
        setPageButton();
        erorrLabel.setVisible(false);
        setEquipmentButton();
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
            page= (int) Math.ceil((float)numberOfEquipment/4);
        }else {
            page= Integer.parseInt(pageButton3.getText());
        }
        backPageButtonToDefault();
        setPageButton();
        erorrLabel.setVisible(false);
        setEquipmentButton();
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
            page= (int) Math.ceil((float)numberOfEquipment/4);
        }else {
            page= Integer.parseInt(pageButton4.getText());
        }
        backPageButtonToDefault();
        setPageButton();
        erorrLabel.setVisible(false);
        setEquipmentButton();
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
            page= (int) Math.ceil((float)numberOfEquipment/4);
        }else {
            page= Integer.parseInt(pageButton5.getText());
        }
        backPageButtonToDefault();
        setPageButton();
        erorrLabel.setVisible(false);
        setEquipmentButton();
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
            page= (int) Math.ceil((float)numberOfEquipment/4);
        }else {
            page= Integer.parseInt(pageButton6.getText());
        }
        backPageButtonToDefault();
        setPageButton();
        erorrLabel.setVisible(false);
        setEquipmentButton();
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
            page= (int) Math.ceil((float)numberOfEquipment/4);
        }else {
            page= Integer.parseInt(pageButton7.getText());
        }
        backPageButtonToDefault();
        setPageButton();
        erorrLabel.setVisible(false);
        setEquipmentButton();
    }

    @FXML
    void pressSearchButton(MouseEvent event) {
        finalEquipment.clear();
        for (Equipment equipment:tempEquipment){
            if (filter==true&&equipment.getPrice()<=Integer.parseInt(maxPriceText.getText())&&equipment.getPrice()>=Integer.parseInt(minPriceText.getText())&&equipment.getName().startsWith(searchText.getText())){
                finalEquipment.add(equipment);
            }else if (filter==false&&equipment.getName().startsWith(searchText.getText())){
                finalEquipment.add(equipment);
            }
        }
        tempFilterEquipment.clear();
        tempFilterEquipment.addAll(finalEquipment);
        finalEquipment.clear();
        for (Equipment equipment:tempDeviceEquipment){
            if (filter==true&&equipment.getPrice()<=Integer.parseInt(maxPriceText.getText())&&equipment.getPrice()>=Integer.parseInt(minPriceText.getText())&&equipment.getName().startsWith(searchText.getText())){
                finalEquipment.add(equipment);
            }else if (filter==false&&equipment.getName().startsWith(searchText.getText())){
                finalEquipment.add(equipment);
            }
        }
        numberOfEquipment=finalEquipment.size();
        setPageButton();
        setEquipmentButton();
        erorrLabel.setVisible(false);
        search=true;
    }
    private void setAllEquipment() throws SQLException {
        Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/data","root","");
        Statement statement=connection.createStatement();
        ResultSet resultSet=statement.executeQuery("SELECT * FROM equipment");
        while (resultSet.next()) {
            Equipment equipment = new Equipment();
            equipment.setId(resultSet.getInt("id"));
            equipment.setName(resultSet.getString("name"));
            equipment.setInformation(resultSet.getString("information"));
            equipment.setDevice(Device.valueOf(resultSet.getString("device")));
            equipment.setPrice(resultSet.getInt("price"));
            equipment.setCompatible(resultSet.getString("compatible"));
            if (!resultSet.getString("connection").equals("")) {
                equipment.setConnection(Connection0.valueOf(resultSet.getString("connection")));
            }
            equipment.setRefreshRate(resultSet.getString("refreshRate"));
            equipment.setImage(resultSet.getString("image"));
            equipment.setSize(resultSet.getDouble("size"));
            equipment.setResponseTime(resultSet.getDouble("responseTime"));
            equipment.setNumber(resultSet.getInt("number"));
            equipment.setCommunity(LoginPage.stringToArray(resultSet.getString("community")));
            equipment.setFeedback(LoginPage.stringToArray(resultSet.getString("feedback")));
            allEquipment.add(equipment);
        }
    }

    private void setFinalEquipmentStore() {
        for (Equipment equipment:allEquipment){
            if (equipment.getNumber()>0){
                finalEquipment.add(equipment);
            }
        }
    }



    private void setFinalEquipmentLibrary() {
        for (Equipment equipment:allEquipment){
            if (LoginPage.getUser().getEquipment().contains(String.valueOf(equipment.getId()))){
                finalEquipment.add(equipment);
            }
        }
    }

    private void setFinalEquipmentFriend() {
        for (Equipment equipment:allEquipment){
            if (UsersPage.getUser().getEquipment().contains(String.valueOf(equipment.getId()))){
                finalEquipment.add(equipment);
            }
        }
    }


    private void setFinalEquipmentAdmin() {
        for (Equipment equipment:allEquipment){
            finalEquipment.add(equipment);
        }
    }

    private void setFinalEquipmentSeller() {
        for (Equipment equipment:allEquipment){
            if (LoginPage.getSeller().getEquipments().contains(String.valueOf(equipment.getId()))){
                finalEquipment.add(equipment);
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            setAllEquipment();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (LoginPage.getAccessLevel().equals(AccessLevel.user)) {
            gameButton.setVisible(true);
            equipmentButton.setVisible(true);
            if (UserPage.getGameFilter().equals(GameFilter.Store)){
                pageName.setText(String.valueOf(GameFilter.Store));
                setFinalEquipmentStore();
            }else if (UserPage.getGameFilter().equals(GameFilter.Library)){
                pageName.setText(String.valueOf(GameFilter.Library));
                setFinalEquipmentLibrary();
            }else if (UserPage.getGameFilter().equals(GameFilter.friend)){
                pageName.setText(UsersPage.getUser().getUsername());
                setFinalEquipmentFriend();
                equipmentButton1.setMouseTransparent(true);
                equipmentButton2.setMouseTransparent(true);
                equipmentButton3.setMouseTransparent(true);
                equipmentButton4.setMouseTransparent(true);
            }
        } else if (LoginPage.getAccessLevel().equals(AccessLevel.admin)) {
            pageName.setText("Accessories");
            addButton.setVisible(true);
            setFinalEquipmentAdmin();
        } else if (LoginPage.getAccessLevel().equals(AccessLevel.seller)) {
            pageName.setText("Equipments");
            addButton.setVisible(true);
            setFinalEquipmentSeller();
        }
        tempEquipment.addAll(finalEquipment);
        tempDeviceEquipment.addAll(finalEquipment);
        tempFilterEquipment.addAll(finalEquipment);
        numberOfEquipment=finalEquipment.size();
        setPageButton();
        setEquipmentButton();
    }

    private void setPageButton(){
        if (numberOfEquipment<=4){
            pageButton1.setVisible(false);
            pageButton2.setVisible(false);
            pageButton3.setVisible(false);
            pageButton4.setVisible(true);
            pageButton5.setVisible(false);
            pageButton6.setVisible(false);
            pageButton7.setVisible(false);
            pageButton4.setText("1");
        }else if (numberOfEquipment<=8&&page==1){
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
        }else if (numberOfEquipment<=8&&page==2){
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
        }else if (numberOfEquipment<=12&&page==2){
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
        }else if (page==(int) Math.ceil((float)numberOfEquipment/4)){
            pageButton1.setVisible(false);
            pageButton2.setVisible(true);
            pageButton3.setVisible(true);
            pageButton4.setVisible(true);
            pageButton5.setVisible(true);
            pageButton6.setVisible(true);
            pageButton7.setVisible(false);
            pageButton2.setText("<<");
            pageButton6.setText(String.valueOf((int) Math.ceil((float)numberOfEquipment/4)));
            pageButton3.setText("<");
            pageButton4.setText(String.valueOf((int) Math.ceil((float)numberOfEquipment/4)-2));
            pageButton5.setText(String.valueOf((int) Math.ceil((float)numberOfEquipment/4)-1));
            pageButton4.setStyle("-fx-background-color:   #D8BFD8;");
            pageButton6.setStyle("-fx-background-color:   #F0E68C;");
        }else if (page==(int) Math.ceil((float)numberOfEquipment/4)-1&&numberOfEquipment>12){
            pageButton1.setVisible(true);
            pageButton2.setVisible(true);
            pageButton3.setVisible(true);
            pageButton4.setVisible(true);
            pageButton5.setVisible(true);
            pageButton6.setVisible(true);
            pageButton7.setVisible(false);
            pageButton1.setText("<<");
            pageButton5.setText(String.valueOf((int) Math.ceil((float)numberOfEquipment/4)));
            pageButton2.setText("<");
            pageButton3.setText(String.valueOf((int) Math.ceil((float)numberOfEquipment/4)-2));
            pageButton4.setText(String.valueOf((int) Math.ceil((float)numberOfEquipment/4)-1));
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

    private void setEquipmentButton(){
        if (numberOfEquipment==0){
            equipmentButton1.setVisible(false);
            equipmentButton2.setVisible(false);
            equipmentButton3.setVisible(false);
            equipmentButton4.setVisible(false);
        }else if (page==(int) Math.ceil((float)numberOfEquipment/4)&&numberOfEquipment%4==1){
            Image image=new Image(finalEquipment.get(numberOfEquipment-1).getImage());
            ImageView imageView1=new ImageView(image);
            imageView1.setFitWidth(150);
            imageView1.setFitHeight(150);
            equipmentButton1.setGraphic(imageView1);
            equipmentButton1.setVisible(true);
            equipmentButton2.setVisible(false);
            equipmentButton3.setVisible(false);
            equipmentButton4.setVisible(false);
            equipmentButton1.setText(finalEquipment.get(numberOfEquipment-1).getName());
        }else if (page==(int) Math.ceil((float)numberOfEquipment/4)&&numberOfEquipment%4==2){
            Image image1=new Image(finalEquipment.get(numberOfEquipment-2).getImage());
            Image image2=new Image(finalEquipment.get(numberOfEquipment-1).getImage());
            ImageView imageView1=new ImageView(image1);
            imageView1.setFitWidth(150);
            imageView1.setFitHeight(150);
            equipmentButton1.setGraphic(imageView1);
            ImageView imageView2=new ImageView(image2);
            imageView2.setFitWidth(150);
            imageView2.setFitHeight(150);
            equipmentButton2.setGraphic(imageView2);
            equipmentButton1.setVisible(true);
            equipmentButton2.setVisible(true);
            equipmentButton3.setVisible(false);
            equipmentButton4.setVisible(false);
            equipmentButton1.setText(finalEquipment.get(numberOfEquipment-2).getName());
            equipmentButton2.setText(finalEquipment.get(numberOfEquipment-1).getName());
        }else if (page==(int) Math.ceil((float)numberOfEquipment/4)&&numberOfEquipment%4==3){
            Image image1=new Image(finalEquipment.get(numberOfEquipment-3).getImage());
            Image image2=new Image(finalEquipment.get(numberOfEquipment-2).getImage());
            Image image3=new Image(finalEquipment.get(numberOfEquipment-1).getImage());
            ImageView imageView1=new ImageView(image1);
            imageView1.setFitWidth(150);
            imageView1.setFitHeight(150);
            equipmentButton1.setGraphic(imageView1);
            ImageView imageView2=new ImageView(image2);
            imageView2.setFitWidth(150);
            imageView2.setFitHeight(150);
            equipmentButton2.setGraphic(imageView2);
            ImageView imageView3=new ImageView(image3);
            imageView3.setFitWidth(150);
            imageView3.setFitHeight(150);
            equipmentButton3.setGraphic(imageView3);
            equipmentButton1.setVisible(true);
            equipmentButton2.setVisible(true);
            equipmentButton3.setVisible(true);
            equipmentButton4.setVisible(false);
            equipmentButton1.setText(finalEquipment.get(numberOfEquipment-3).getName());
            equipmentButton2.setText(finalEquipment.get(numberOfEquipment-2).getName());
            equipmentButton3.setText(finalEquipment.get(numberOfEquipment-1).getName());
        }else {
            Image image1=new Image(finalEquipment.get(page*4-4).getImage());
            Image image2=new Image(finalEquipment.get(page*4-3).getImage());
            Image image3=new Image(finalEquipment.get(page*4-2).getImage());
            Image image4=new Image(finalEquipment.get(page*4-1).getImage());
            ImageView imageView1=new ImageView(image1);
            imageView1.setFitWidth(150);
            imageView1.setFitHeight(150);
            equipmentButton1.setGraphic(imageView1);
            ImageView imageView2=new ImageView(image2);
            imageView2.setFitWidth(150);
            imageView2.setFitHeight(150);
            equipmentButton2.setGraphic(imageView2);
            ImageView imageView3=new ImageView(image3);
            imageView3.setFitWidth(150);
            imageView3.setFitHeight(150);
            equipmentButton3.setGraphic(imageView3);
            ImageView imageView4=new ImageView(image4);
            imageView4.setFitWidth(150);
            imageView4.setFitHeight(150);
            equipmentButton4.setGraphic(imageView4);
            equipmentButton1.setVisible(true);
            equipmentButton2.setVisible(true);
            equipmentButton3.setVisible(true);
            equipmentButton4.setVisible(true);
            equipmentButton1.setText(finalEquipment.get(page*4-4).getName());
            equipmentButton2.setText(finalEquipment.get(page*4-3).getName());
            equipmentButton3.setText(finalEquipment.get(page*4-2).getName());
            equipmentButton4.setText(finalEquipment.get(page*4-1).getName());
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
}
