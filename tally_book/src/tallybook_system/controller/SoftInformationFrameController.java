package tallybook_system.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * 关于软件信息界面控制器
 *
 * @author YF
 */
public class SoftInformationFrameController {
    private Stage dialogStage;

    public Stage getDialogStage() {
        return dialogStage;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    @FXML
    private Hyperlink hyperlink;

    @FXML
    private ImageView imageView;

    /**
     * 初始化界面
     */
    public void initialize() {
        // 初始化链接组件的超链接
        hyperlink.setText("相关GitHub链接");
    }

    /**
     * “关闭”按钮的事件监听器
     *
     * @param event 事件
     */
    public void closeButtonEvent(ActionEvent event) {
        // 关闭该窗口
        dialogStage.close();
    }

    /**
     * 超链接的事件监听器
     *
     * @param event 事件
     * @throws URISyntaxException 抛出URISyntaxException
     * @throws IOException        抛出IOException
     */
    public void hyperlinkEvent(ActionEvent event) throws URISyntaxException, IOException {
        // 使用电脑本地的浏览器打开超链接
        Desktop.getDesktop().browse(new URI("https://github.com/LXL-zqdn/TeamProject"));
    }
}
