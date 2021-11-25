package tallybook_system.controller;

import tallybook_system.MainApp;
import tallybook_system.bean.Session;
import tallybook_system.bean.TableData;
import tallybook_system.bean.User;
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

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Properties;

/**
 * 主界面控制器
 *
 * @author lck100
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
       // initThemeRadioMenuItem();
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
    public void backupMenuItemEvent(ActionEvent actionEvent) {

    }

    /**
     * “恢复”菜单项的事件监听器
     *
     * @param actionEvent 事件
     */
    @FXML
    public void recoverMenuItemEvent(ActionEvent actionEvent) {

    }

    /**
     * “退出”菜单项的事件监听器
     *
     * @param actionEvent 事件
     */
    @FXML
    public void exitMenuItemEvent(ActionEvent actionEvent) {

    }

    /**
     * ”添加“菜单项的事件监听器
     *
     * @param actionEvent 事件
     */
    @FXML
    public void addMenuItemEvent(ActionEvent actionEvent) {

    }

    /**
     * ”删除“菜单项的事件监听器
     *
     * @param actionEvent 事件
     */
    @FXML
    public void deleteMenuItemEvent(ActionEvent actionEvent) {

    }

    /**
     * ”修改“菜单项的事件监听器
     *
     * @param actionEvent 事件
     */
    @FXML
    public void alterMenuItemEvent(ActionEvent actionEvent) {

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

    }

    /**
     * “折线图”菜单项的事件监听器
     *
     * @param actionEvent 事件
     */
    @FXML
    public void lineChartMenuItemEvent(ActionEvent actionEvent) {

    }

    /**
     * ”饼图“菜单项的事件监听器
     *
     * @param actionEvent 事件
     */
    @FXML
    public void pieChartMenuItemEvent(ActionEvent actionEvent) {

    }

    /**
     * ”添加分类“菜单项的事件监听器
     *
     * @param actionEvent 事件
     */
    @FXML
    public void addClassificationMenuItemEvent(ActionEvent actionEvent) {

    }

    /**
     * “用户信息”菜单项的事件监听器
     *
     * @param actionEvent 事件
     */
    @FXML
    public void userInfoMenuItemEvent(ActionEvent actionEvent) {

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

    }

    /**
     * “帮助”菜单项的事件监听器
     *
     * @param actionEvent 事件
     */
    @FXML
    public void helpMenuItemEvent(ActionEvent actionEvent) {

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

    }

    /**
     * ”按分类查询“菜单项的事件监听器
     *
     * @param event 事件
     */
    @FXML
    public void classificationCheckMenuItemEvent(ActionEvent event) {

    }

    /**
     * ”按备注查询“菜单项的事件监听器
     *
     * @param event 事件
     */
    @FXML
    public void memoCheckMenuItemEvent(ActionEvent event) {

    }

    /**
     * “默认”菜单项的事件监听器方法
     *
     * @param event 事件
     */
    public void defaultRadioMenuItemEvent(ActionEvent event) {

    }

    /**
     * “经典黑”菜单项的事件监听器方法
     *
     * @param event 事件
     */
    public void blackRadioMenuItemEvent(ActionEvent event) {

    }

    /**
     * “优雅白”菜单项的事件监听器方法
     *
     * @param event 事件
     */
    public void whiteRadioMenuItemEvent(ActionEvent event) {

    }
}