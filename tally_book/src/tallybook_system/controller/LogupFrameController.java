package tallybook_system.controller;

import tallybook_system.bean.Session;
import tallybook_system.bean.User;
import tallybook_system.dao.UserDao;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


/**
 * 登录控制器
 *
 * @author lck100
 */
public class LogupFrameController {
    private Stage logupStage;

    public Stage getLogupStage() {
        return logupStage;
    }

    public void setLogupStage(Stage logupStage) {
        this.logupStage = logupStage;
    }


    @FXML
    private PasswordField passwordTextField;

    @FXML
    private TextField nameTextField;

    /**
     * “注册”按钮事件监听器
     */
    @FXML
    void loginButtonEvent() {

    }

    /**
     * “登录”按钮事件监听器
     */
    @FXML
    void logupButtonEvent() {

    }

}
