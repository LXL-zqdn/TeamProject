package tallybook_system.controller;

import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import tallybook_system.MainApp;
import tallybook_system.bean.Session;
import tallybook_system.bean.TableData;
import tallybook_system.bean.User;
import tallybook_system.dao.JDBCUtils;
import tallybook_system.dao.RecordDao;
import tallybook_system.dao.UserDao;
import tallybook_system.tools.DateTools;
import tallybook_system.tools.PublicTools;
import tallybook_system.tools.SimpleTools;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.Iterator;
import java.util.Properties;

/**
 * 主界面控制器
 *
 * @author GHY LXL ＹＦ　ZFX
 */
public class MainPageController {

    private SimpleTools simpleTools = new SimpleTools();
    private RecordDao recordDao = new RecordDao();
    private MainApp mainApp = new MainApp();
    private PublicTools publicTools = new PublicTools();
    private DateTools dateTools = new DateTools();

    @FXML
    public RadioMenuItem defaultRadioMenuItem;

    @FXML
    public RadioMenuItem blackRadioMenuItem;

    @FXML
    public RadioMenuItem whiteRadioMenuItem;

    @FXML
    private MenuItem refreshContextMenu;

    @FXML
    private TableColumn<TableData, String> classificationColumn;

    @FXML
    private TextField totalOutputTextField;

    @FXML
    private TextField totalInputTextField;

    @FXML
    private ImageView userImage;

    @FXML
    private Label userNameLabel;

    @FXML
    private TableColumn<TableData, String> moneyColumn;

    public TableView<TableData> getTableView() {
        return tableView;
    }

    public void setTableView(TableView<TableData> tableView) {
        this.tableView = tableView;
    }

    @FXML
    private TableView<TableData> tableView;

    @FXML
    private TableColumn<TableData, String> typeColumn;

    @FXML
    private TableColumn<TableData, String> memoColumn;

    @FXML
    private TextField balanceTextField;

    @FXML
    private TableColumn<TableData, String> dateColumn;

    @FXML
    private TableColumn<TableData, String> idColumn;

    /**
     * 初始化界面信息
     */
    @FXML
    public void initialize() {
        // 刷新主键编号
        new RecordDao().refreshPrimaryKey();
        // 初始化用户记录
        initUserRecord();
        // 初始化表格数据
        initAddDataToTableView();
        // 初始化程序主题皮肤选择
        initThemeRadioMenuItem();
    }

    /**
     * “导入”菜单项的事件监听器
     *
     * @param actionEvent 事件
     */
    @FXML
    public void importMenuItemEvent(ActionEvent actionEvent) {

    }

    /**
     * “导出”菜单项的事件监听器
     *
     * @param actionEvent 事件
     */
    @FXML
    public void exportMenuItemEvent(ActionEvent actionEvent) {

    }

    /**
     * “备份”菜单项的事件监听器
     *
     * @param actionEvent 事件
     */
    @FXML
    public void backupMenuItemEvent(ActionEvent actionEvent)throws IOException {
        //实例化文件选择器
        FileChooser fileChooser = new FileChooser();
        //设置打开文件选择框默认输入的文件名
        fileChooser.setInitialFileName("Database_Backup_" + dateTools.dateFormat(new Date(), "yyyy-MM-dd") + ".sql");
        //打开文件选择框
        File result = fileChooser.showSaveDialog(null);
        if (result != null) {
            String savePath = result.getAbsolutePath();
            // 实例化Properties对象
            Properties properties = new Properties();
            // 加载properties配置文件
            FileInputStream fis = new FileInputStream(new File("tally_book\\src\\tallybook_system\\properties\\db.properties"));
            properties.load(fis);
            // 通过键名获取对应的值
            String databaseName = properties.get("databaseName").toString();
            String user = properties.get("user").toString();
            String password = properties.get("password").toString();
            // 调用备份方法需要提供MySQL的用户名、密码和数据库名，这些数据从properties文件中读取
            boolean b = JDBCUtils.backup(user, password, savePath, databaseName);
            if (b) {
                SimpleTools.informationDialog(Alert.AlertType.INFORMATION, "提示", "信息", "备份数据库成功！");
            } else {
                SimpleTools.informationDialog(Alert.AlertType.ERROR, "提示", "错误", "备份数据库失败！");
            }
            // 关闭流
            fis.close();
        }
    }

    /**
     * “恢复”菜单项的事件监听器
     *
     * @param actionEvent 事件
     */
    @FXML
    public void recoverMenuItemEvent(ActionEvent actionEvent) throws IOException {
        //实例化文件选择器
        FileChooser fileChooser = new FileChooser();
        //设置默认文件过滤器
        fileChooser.setSelectedExtensionFilter(new FileChooser.ExtensionFilter("sql(*.sql)", "sql"));
        //打开文件选择框
        File result = fileChooser.showOpenDialog(null);
        if (result != null) {
            // 恢复文件的路径
            String recoverPath = result.getAbsolutePath();
            // 实例化Properties对象
            Properties properties = new Properties();
            // 加载properties配置文件
            FileInputStream fis = new FileInputStream(new File("src\\AccountSystem\\properties\\db.properties"));
            properties.load(fis);
            // 通过键名获取对应的值
            String databaseName = properties.get("databaseName").toString();
            String user = properties.get("user").toString();
            String password = properties.get("password").toString();
            boolean b = JDBCUtils.recover(user, password, databaseName, recoverPath);
            if (b) {
                // 刷新界面显示的数据
                initialize();
                SimpleTools.informationDialog(Alert.AlertType.INFORMATION, "提示", "信息", "数据库恢复成功！");
            } else {
                SimpleTools.informationDialog(Alert.AlertType.ERROR, "提示", "错误", "数据库恢复失败！");
            }
            // 关闭流
            fis.close();
        }
    }

    /**
     * “退出”菜单项的事件监听器
     *
     * @param actionEvent 事件
     */
    @FXML
    public void exitMenuItemEvent(ActionEvent actionEvent) {
        System.exit(0);
    }

    /**
     * ”添加“菜单项的事件监听器
     *
     * @param actionEvent 事件
     */
    @FXML
    public void addMenuItemEvent(ActionEvent actionEvent) {
        // 刷新界面数据
        initialize();
        // 调用添加账目界面
        mainApp.initAddFrame();
    }

    /**
     * ”删除“菜单项的事件监听器
     *
     * @param actionEvent 事件
     */
    @FXML
    public void deleteMenuItemEvent(ActionEvent actionEvent) {
        mainApp.initDeleteFrame();//调用删除界面
        initialize();
    }

    /**
     * ”修改“菜单项的事件监听器
     *
     * @param actionEvent 事件
     */
    @FXML
    public void alterMenuItemEvent(ActionEvent actionEvent) {
        // 刷新数据
        initialize();
        // 调用修改界面控制器
        mainApp.initAlterFrame(null, false);
    }

    /**
     * “删除”右键菜单的事件监听器
     *
     * @param event 事件
     */
    @FXML
    public void deleteContextMenuEvent(ActionEvent event) {

    }

    /**
     * “添加”右键菜单的事件监听器
     *
     * @param event 事件
     */
    @FXML
    public void addContextMenuEvent(ActionEvent event) {

    }

    /**
     * “修改”右键菜单的事件监听器
     *
     * @param event 事件
     */
    @FXML
    public void alterContextMenuEvent(ActionEvent event) {

    }

    /**
     * ”刷新“右键菜单的事件监听器
     *
     * @param actionEvent 事件
     */
    public void refreshContextMenuEvent(ActionEvent actionEvent) {

    }

    /**
     * ”查询“菜单项的事件监听器
     *
     * @param actionEvent 事件
     */
    @FXML
    public void checkMenuItemEvent(ActionEvent actionEvent) {
        // 打开查询界面
        mainApp.initTableView();
        initialize();


    }

    /**
     * ”条形图“菜单项的事件监听器
     *
     * @param actionEvent 事件
     */
    @FXML
    public void barChartMenuItemEvent(ActionEvent actionEvent) {
        // 打开条形图界面
        mainApp.initBarChart();

    }

    /**
     * “折线图”菜单项的事件监听器
     *
     * @param actionEvent 事件
     */
    @FXML
    public void lineChartMenuItemEvent(ActionEvent actionEvent) {
        // 打开折线图界面
        mainApp.initLineChart();
    }

    /**
     * ”饼图“菜单项的事件监听器
     *
     * @param actionEvent 事件
     */
    @FXML
    public void pieChartMenuItemEvent(ActionEvent actionEvent) {
        // 打开饼图界面
        mainApp.initPieChart();
    }

    /**
     * ”添加分类“菜单项的事件监听器
     *
     * @param actionEvent 事件
     */
    @FXML
    public void addClassificationMenuItemEvent(ActionEvent actionEvent) {
        // 打开添加分类界面
        mainApp.initAddClassificationFrame();

    }

    /**
     * “用户信息”菜单项的事件监听器
     *
     * @param actionEvent 事件
     */
    @FXML
    public void userInfoMenuItemEvent(ActionEvent actionEvent) {
        // 打开用户信息界面
        mainApp.initUserInformationFrame();
        // 刷新主界面
        initialize();

    }

    /**
     * “报告”菜单项的事件监听器
     *
     * @param event 事件
     */
    @FXML
    public void reportMenuItemEvent(ActionEvent event) {
        // 打开报告界面
        mainApp.initReportFrame();
    }

    /**
     * “关于软件”菜单项的事件监听器
     *
     * @param actionEvent 事件
     */
    @FXML
    public void abutSoftMenuItemEvent(ActionEvent actionEvent) {
        // 打开关于软件界面
        mainApp.initSoftInformationFrame();
    }

    /**
     * “帮助”菜单项的事件监听器
     *
     * @param actionEvent 事件
     */
    @FXML
    public void helpMenuItemEvent(ActionEvent actionEvent) throws URISyntaxException, IOException {
        Desktop.getDesktop().browse(new URI("https://github.com/LXL-zqdn/TeamProject"));

    }
    /**
     * 操作结果：初始化用户名、总支出、总收入及余额
     */
    public void initUserRecord() {
        // 获取登录成功的用户
        User user = Session.getUser();
        // 获取用户支出的总金额
        float totalOutput = recordDao.getTotalAccount("支出", user.getUserId());
        // 获取用户收入的总金额
        float totalInput = recordDao.getTotalAccount("收入", user.getUserId());
        // 获取余额
        float balance = totalInput - totalOutput;
        // 设置图片
        userImage.setImage(new Image("file:" + new UserDao().selectUserById(Session.getUser().getUserId()).getUserImagePath()));
        userImage.setSmooth(true);
        userImage.setFitWidth(100);
        userImage.setFitHeight(100);
        userImage.setCache(false);
        userImage.setPreserveRatio(false);
        // 将支出金额、收入金额、余额、用户名设置到文本框种
        totalOutputTextField.setText(String.valueOf(totalOutput));
        totalInputTextField.setText(String.valueOf(totalInput));
        balanceTextField.setText(String.valueOf(balance));
        userNameLabel.setText(Session.getUser().getUserName());
    }

    /**
     * 操作结果：初始化数据表视图
     */
    public void initAddDataToTableView() {
        String sql = "select * from tb_records where uId=" + Session.getUser().getUserId() + ";";
        publicTools.public_initTableViewData(tableView
                , publicTools.public_getTableViewData(sql)
                , idColumn
                , typeColumn
                , moneyColumn
                , classificationColumn
                , memoColumn
                , dateColumn);
    }
    /**
     * ”按日期查询“菜单项的事件监听器
     *
     * @param event 事件
     */
    @FXML
    public void dateCheckMenuItemEvent(ActionEvent event) {
        /**
         * ”按日期查询“菜单项的事件监听器
         *
         * @param event 事件
         */
            // 打开按日期查询界面
            mainApp.initDateCheckTableView();


    }

    /**
     * ”按分类查询“菜单项的事件监听器
     *
     * @param event 事件
     */
    @FXML
    public void classificationCheckMenuItemEvent(ActionEvent event) {
        // 打开按分类查询界面
        mainApp.initClassificationTableView();


    }

    /**
     * ”按备注查询“菜单项的事件监听器
     *
     * @param event 事件
     */
    @FXML
    public void memoCheckMenuItemEvent(ActionEvent event) {
        // 打开按备注查询界面
        mainApp.initMemoTableView();

    }

    /**
     * “默认”菜单项的事件监听器方法
     *
     * @param event 事件
     */
    public void defaultRadioMenuItemEvent(ActionEvent event) throws IOException {
        File file = new File("src\\AccountSystem\\properties\\styles.properties");
        if (!file.exists()) {
            file.createNewFile();
        }
        // 实例化Properties对象
        Properties properties = new Properties();
        // 写入默认属性
        properties.setProperty("default", "");
        // 文件输出流
        FileOutputStream fos = new FileOutputStream(file);
        // 向properties文件中写入信息，有两个参数：第一个参数是文件输出流，第二个参数是properties文件注释
        properties.store(fos, "默认");
        SimpleTools.informationDialog(Alert.AlertType.INFORMATION,"信息","信息","切换默认皮肤成功，请重启软件使用新皮肤。");
        // 关闭流
        fos.close();
    }

    /**
     * “经典黑”菜单项的事件监听器方法
     *
     * @param event 事件
     */
    public void blackRadioMenuItemEvent(ActionEvent event) throws IOException {
        File file = new File("src\\AccountSystem\\properties\\styles.properties");
        if (!file.exists()) {
            file.createNewFile();
        }
        // 实例化Properties对象
        Properties properties = new Properties();
        // 写入经典黑属性
        properties.setProperty("black", "styles/BlackStyle.css");
        // 文件输出流
        FileOutputStream fos = new FileOutputStream(file);
        // 向properties文件中写入信息，有两个参数：第一个参数是文件输出流，第二个参数是properties文件注释
        properties.store(fos, "经典黑");
        SimpleTools.informationDialog(Alert.AlertType.INFORMATION,"信息","信息","切换经典黑皮肤成功，请重启软件使用新皮肤。");
        // 关闭流
        fos.close();
    }

    /**
     * “优雅白”菜单项的事件监听器方法
     *
     * @param event 事件
     */
    public void whiteRadioMenuItemEvent(ActionEvent event) throws IOException {
        File file = new File("src\\AccountSystem\\properties\\styles.properties");
        if (!file.exists()) {
            file.createNewFile();
        }
        // 实例化Properties对象
        Properties properties = new Properties();
        // 写入优雅白属性
        properties.setProperty("white", "styles/WhiteStyle.css");
        // 文件输出流
        FileOutputStream fos = new FileOutputStream(file);
        // 向properties文件中写入信息，有两个参数：第一个参数是文件输出流，第二个参数是properties文件注释
        properties.store(fos, "优雅白");
        SimpleTools.informationDialog(Alert.AlertType.INFORMATION,"信息","信息","切换优雅白皮肤成功，请重启软件使用新皮肤。");
        // 关闭流
        fos.close();
    }
    /**
     * 操作结果：初始主题皮肤菜单项单选框被选中情况
     */
    public void initThemeRadioMenuItem() {
        String key = "";
        try {
            Properties properties = new Properties();
            FileInputStream fis = new FileInputStream(new File("src\\AccountSystem\\properties\\styles.properties"));
            properties.load(fis);
            Iterator<String> iterator = properties.stringPropertyNames().iterator();
            while (iterator.hasNext()) {
                key = iterator.next();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 判断properties文件key的值
        if (key.equals("black")) {
            blackRadioMenuItem.setSelected(true);
        } else if (key.equals("white")) {
            whiteRadioMenuItem.setSelected(true);
        } else {
            defaultRadioMenuItem.setSelected(true);
        }
    }
}