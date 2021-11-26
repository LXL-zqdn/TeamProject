package tallybook_system.controller;

import tallybook_system.bean.Session;
import tallybook_system.bean.TableData;
import tallybook_system.tools.PublicTools;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import tallybook_system.bean.TableData;

/**
 * 按日期查询界面控制器
 *
 * @author lck100
 */
public class DateCheckFrameController {
    private PublicTools publicTools = new PublicTools();

    @FXML
    private TableColumn<TableData, String> check_classificationColumn;

    @FXML
    private DatePicker endDatePicker;

    @FXML
    private TableColumn<TableData, String> check_idColumn;

    @FXML
    private TableColumn<TableData, String> check_typeColumn;

    @FXML
    private TableColumn<TableData, String> check_memoColumn;

    @FXML
    private TableView<TableData> check_tableView;

    @FXML
    private DatePicker startDatePicker;

    @FXML
    private TableColumn<TableData, String> check_moneyColumn;

    @FXML
    private TableColumn<TableData, String> check_dateColumn;

    /**
     * ”按日期查询“按钮的事件监听器
     *
     * @param event 事件
     */

    @FXML
    public void dateCheckButtonEvent(ActionEvent event) {

    };
        @FXML
    /**
     * 初始化界面表格数据
     */
    public void initialize() {
        // 初始化表格数据
        String sql = "select * from tb_records where uId=" + Session.getUser().getUserId() + ";";
        publicTools.public_initTableViewData(check_tableView
                , publicTools.public_getTableViewData(sql)
                , check_idColumn
                , check_typeColumn
                , check_moneyColumn
                , check_classificationColumn
                , check_memoColumn
                , check_dateColumn);
    }
}
