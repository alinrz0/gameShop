package ir.ac.kntu;

import ir.ac.kntu.types.GameGenre;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("LoginPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
    }

    public static void main(String[] args) throws SQLException {
        launch(args);
//        java.sql.Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/data","root","");
//        Statement statement=connection.createStatement();
//        ResultSet resultSet=statement.executeQuery("SELECT * FROM admin");
//        statement.executeUpdate("UPDATE user SET password='1' WHERE username='ali'");
//        long a=System.currentTimeMillis();
//        Scanner scanner=new Scanner(System.in);
//        int b= scanner.nextInt();
//        long c=System.currentTimeMillis();
//        System.out.println((a-c)/1000);
    }
}