package tallybook_system;

import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import tallybook_system.bean.TableData;
import tallybook_system.controller.AddClassificationFrameController;
import tallybook_system.controller.AlterAccountFrameController;
import tallybook_system.controller.LogupFrameController;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import tallybook_system.controller.SoftInformationFrameController;

import java.io.File;
import java.io.FileInputStream;
import java.sql.*;

import java.io.IOException;
import java.util.Iterator;
import java.util.Properties;

public class MainApp extends Application {

    @FXML
    private Stage primaryStage;

    @FXML
    private HBox rootLayout;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("简易记账管理系统");
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
            // 加载CSS样式文件
            scene.getStylesheets().add(MainApp.class.getResource(getStyleValue()).toExternalForm());
            LogupFrameController controller = loader.getController();
            controller.setLogupStage(primaryStage);

            primaryStage.show();
            return scene;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 操作结果：主操作界面
     */
    public Scene initMainFrame() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/mainPage.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            Stage mainFrameStage = new Stage();
            mainFrameStage.setTitle("简易记账管理系统");
            mainFrameStage.setResizable(true);
            mainFrameStage.setAlwaysOnTop(false);
            mainFrameStage.initModality(Modality.APPLICATION_MODAL);
            mainFrameStage.initOwner(primaryStage);

            Scene scene = new Scene(page);
            mainFrameStage.setScene(scene);
            // 加载CSS样式文件
           // scene.getStylesheets().add(MainApp.class.getResource(getStyleValue()).toExternalForm());

            mainFrameStage.showAndWait();
            return scene;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 操作结果：添加账目界面
     */
    public Scene initAddFrame() {
        try {
            Parent page = FXMLLoader.load(getClass().getResource("view/addAccountFrame.fxml"));

            Stage mainFrameStage = new Stage();
            mainFrameStage.setTitle("添加账目");
            mainFrameStage.setResizable(true);
            mainFrameStage.setAlwaysOnTop(false);
            mainFrameStage.initModality(Modality.APPLICATION_MODAL);
            mainFrameStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            mainFrameStage.setScene(scene);
            mainFrameStage.show();
            return scene;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 操作结果：删除账目界面
     */
    public Scene initDeleteFrame() {
        try {
            Parent page = FXMLLoader.load(getClass().getResource("view/deleteAccountFrame.fxml"));

            Stage mainFrameStage = new Stage();
            mainFrameStage.setTitle("删除账目");
            mainFrameStage.setResizable(true);
            mainFrameStage.setAlwaysOnTop(false);
            mainFrameStage.initModality(Modality.APPLICATION_MODAL);
            mainFrameStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            mainFrameStage.setScene(scene);
            mainFrameStage.show();
            return scene;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 操作结果：更改账目界面
     */
    public Scene initAlterFrame(TableData tableData, boolean isContextMenu) {
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/alterAccountFrame.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            Stage mainFrameStage = new Stage();
            mainFrameStage.setTitle("更改账目");
            mainFrameStage.setResizable(true);
            mainFrameStage.setAlwaysOnTop(false);
            mainFrameStage.initModality(Modality.APPLICATION_MODAL);
            mainFrameStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            mainFrameStage.setScene(scene);

            mainFrameStage.showAndWait();
            return scene;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 操作结果：“报告”查询结果界面
     */
    public Scene initReportFrame() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/reportFrame.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            Stage mainFrameStage = new Stage();
            mainFrameStage.setTitle("报告");
            mainFrameStage.setResizable(true);
            mainFrameStage.setAlwaysOnTop(false);
            mainFrameStage.initModality(Modality.APPLICATION_MODAL);
            mainFrameStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            mainFrameStage.setScene(scene);


            mainFrameStage.showAndWait();
            return scene;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 操作结果：查看数据表格界面
     */
    public Scene initTableView() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/tableView.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            Stage mainFrameStage = new Stage();
            mainFrameStage.setTitle("查询结果");
            mainFrameStage.setResizable(true);
            mainFrameStage.setAlwaysOnTop(false);
            mainFrameStage.initModality(Modality.APPLICATION_MODAL);
            mainFrameStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            mainFrameStage.setScene(scene);

            mainFrameStage.showAndWait();
            return scene;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 操作结果：”按日期查询“结果界面
     */
    public Scene initDateCheckTableView() {
        try {

            Parent page = FXMLLoader.load(getClass().getResource("view/dateCheckFrame.fxml"));

            Stage mainFrameStage = new Stage();
            mainFrameStage.setTitle("按日期查询");
            mainFrameStage.setResizable(true);
            mainFrameStage.setAlwaysOnTop(false);
            mainFrameStage.initModality(Modality.APPLICATION_MODAL);
            mainFrameStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            mainFrameStage.setScene(scene);

            mainFrameStage.showAndWait();
            return scene;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 操作结果：”按分类查询“结果界面
     */
    public Scene initClassificationTableView() {
        try {
            Parent page = FXMLLoader.load(getClass().getResource("view/classificationCheckFrame.fxml"));

            Stage mainFrameStage = new Stage();
            mainFrameStage.setTitle("按分类查询");
            mainFrameStage.setResizable(true);
            mainFrameStage.setAlwaysOnTop(false);
            mainFrameStage.initModality(Modality.APPLICATION_MODAL);
            mainFrameStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            mainFrameStage.setScene(scene);

            mainFrameStage.showAndWait();
            return scene;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 操作结果：”按备注查询“结果界面
     */
    public Scene initMemoTableView() {
        try {
            Parent page = FXMLLoader.load(getClass().getResource("view/memoCheckFrame.fxml"));

            Stage mainFrameStage = new Stage();
            mainFrameStage.setTitle("按备注查询");
            mainFrameStage.setResizable(true);
            mainFrameStage.setAlwaysOnTop(false);
            mainFrameStage.initModality(Modality.APPLICATION_MODAL);
            mainFrameStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            mainFrameStage.setScene(scene);

            mainFrameStage.showAndWait();
            return scene;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 操作结果：“条形图”查询结果界面
     */
    public Scene initBarChart() {
        try {
            Parent page = FXMLLoader.load(getClass().getResource("view/barChartFrame.fxml"));
            Stage mainFrameStage = new Stage();
            mainFrameStage.setTitle("条形图");
            mainFrameStage.setResizable(true);
            mainFrameStage.setAlwaysOnTop(false);
            mainFrameStage.initModality(Modality.APPLICATION_MODAL);
            mainFrameStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            mainFrameStage.setScene(scene);
            mainFrameStage.showAndWait();
            return scene;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 操作结果：“折线图”查询结果界面
     */
    public Scene initLineChart() {
        try {
            Parent page = FXMLLoader.load(getClass().getResource("view/lineChartFrame.fxml"));

            Stage mainFrameStage = new Stage();
            mainFrameStage.setTitle("折线图");
            mainFrameStage.setResizable(true);
            mainFrameStage.setAlwaysOnTop(false);
            mainFrameStage.initModality(Modality.APPLICATION_MODAL);
            mainFrameStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            mainFrameStage.setScene(scene);

            mainFrameStage.showAndWait();
            return scene;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 操作结果：”饼图“查询结果界面
     */
    public Scene initPieChart() {
        try {
            Parent page = FXMLLoader.load(getClass().getResource("view/pieChartFrame.fxml"));

            Stage mainFrameStage = new Stage();
            mainFrameStage.setTitle("饼图");
            mainFrameStage.setResizable(true);
            mainFrameStage.setAlwaysOnTop(false);
            mainFrameStage.initModality(Modality.APPLICATION_MODAL);
            mainFrameStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            mainFrameStage.setScene(scene);
            mainFrameStage.showAndWait();
            return scene;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 操作结果：”添加分类“查询结果界面
     */
    public Scene initAddClassificationFrame() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/addClassificationFrame.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            Stage mainFrameStage = new Stage();
            mainFrameStage.setTitle("添加分类");
            mainFrameStage.setResizable(true);
            mainFrameStage.setAlwaysOnTop(false);
            mainFrameStage.initModality(Modality.APPLICATION_MODAL);
            mainFrameStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            mainFrameStage.setScene(scene);

            AddClassificationFrameController controller = loader.getController();
            controller.setDialogStage(mainFrameStage);

            mainFrameStage.showAndWait();
            return scene;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 操作结果：“用户信息”查询结果界面
     */
    public Scene initUserInformationFrame() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/userInformationFrame.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            Stage mainFrameStage = new Stage();
            mainFrameStage.setTitle("用户信息");
            mainFrameStage.setResizable(true);
            mainFrameStage.setAlwaysOnTop(false);
            mainFrameStage.initModality(Modality.APPLICATION_MODAL);
            mainFrameStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            mainFrameStage.setScene(scene);
            mainFrameStage.showAndWait();
            return scene;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 获取styles.properties文件中的值
     *
     * @return 返回值
     * @throws IOException 抛出IOException
     */
    private String getStyleValue() throws IOException {
        Properties properties = new Properties();
        FileInputStream fis = new FileInputStream(new File("src\\tallybook_system\\properties\\styles.properties"));
        properties.load(fis);
        Iterator<String> iterator = properties.stringPropertyNames().iterator();
        String key = "";
        while (iterator.hasNext()) {
            key = iterator.next();
        }
        return properties.getProperty(key, "");
    }
    /**
     * 操作结果：“关于软件”查询结果界面
     */
    public Scene initSoftInformationFrame() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/softInformationFrame.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            Stage mainFrameStage = new Stage();
            mainFrameStage.setTitle("关于软件");
            mainFrameStage.setResizable(true);
            mainFrameStage.setAlwaysOnTop(false);
            mainFrameStage.initModality(Modality.APPLICATION_MODAL);
            mainFrameStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            mainFrameStage.setScene(scene);
            // 加载CSS样式文件
            scene.getStylesheets().add(MainApp.class.getResource(getStyleValue()).toExternalForm());

            SoftInformationFrameController controller = loader.getController();
            controller.setDialogStage(mainFrameStage);

            mainFrameStage.showAndWait();
            return scene;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static void main(String[] args) {
        launch(args);
    }

}


