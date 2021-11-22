package tallybook_system;

import tallybook_system.controller.LogupFrameController;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import java.sql.*;

import java.io.IOException;

public class MainApp extends Application {

    @FXML
    private Stage primaryStage;

    @FXML
    private HBox rootLayout;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("管家婆系统");
        initLogupFrame();
    }


    /**
     * 操作结果：登录界面
     */
    private Scene initLogupFrame() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("view/logupFrame.fxml"));
            rootLayout = (HBox) loader.load();

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);

            LogupFrameController controller = loader.getController();
            controller.setLogupStage(primaryStage);

            primaryStage.show();
            return scene;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        String driverName="com.microsoft.sqlserver.jdbc.SQLServerDriver";
        String dbURL="jdbc:sqlserver://localhost:1433;databaseName=LAPTOP-67CH1QG6";
        String userName="sa";
        String userPwd="123456";
        try
        {
            Class.forName(driverName);
            System.out.println("加载驱动成功！");
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("加载驱动失败！");
        }
        try{
            Connection dbConn=DriverManager.getConnection(dbURL,userName,userPwd);
            System.out.println("连接数据库成功！");
        }catch(Exception e)
        {
            e.printStackTrace();
            System.out.print("SQL Server连接失败！");
        }
        launch(args);
    }

}