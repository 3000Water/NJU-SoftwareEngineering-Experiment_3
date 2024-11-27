/**
 * 登录界面
 *
 * @author 3000_Water
 * @version 1.0
 */
package weather.screendisplay;

import weather.application.ScreenManager;
import weather.display.DisplayTextField;
import weather.display.DisplayTextLabel;

public class LoginScreen extends Screen {

  public LoginScreen(ScreenManager screenManager) {
    super(screenManager);
    String[] classPart = this.getClass().getName().split("\\.");
    String screenClass = classPart[classPart.length - 1];

    /*
    登录面板
     */
    DisplaySection accountPassword = addSection(226, 201, 988, 479);
    accountPassword.addTextLabel("账号", 48, 42, 87, DisplayTextLabel.TextPosition.None);
    accountPassword.addTextLabel("密码", 48, 42, 288, DisplayTextLabel.TextPosition.None);
    /*
    账号密码输入框
     */
    DisplaySection account = accountPassword.addSection(213, 51, 722, 172);
    DisplayTextField accountField = account.addTextField(476, "账号", 36, 29, 51);
    account.addImageLabel("AccountPassword", "1", 0, 0);

    DisplaySection password = accountPassword.addSection(213, 252, 722, 172);
    DisplayTextField passwordField = password.addTextField(476, "密码", 36, 29, 51);
    password.addImageLabel("AccountPassword", "1", 0, 0);

    accountPassword.addImageLabel("AccountPassword", "2", 0, 0);

    /*
    登录注册按钮
     */
    addButton(
        "登录",
        48,
        "Login",
        226,
        739,
        e -> screenManager.loginAction(accountField.getText(), passwordField.getText()));
    addButton(
        "注册",
        48,
        "Register",
        841,
        739,
        e -> screenManager.registerAction(accountField.getText(), passwordField.getText()));
  }
}
