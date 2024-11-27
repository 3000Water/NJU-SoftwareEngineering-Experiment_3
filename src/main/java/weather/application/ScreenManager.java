package weather.application;

import static weather.application.WeatherAPP.currentLocation;
import static weather.application.WeatherAPP.currentTime;
import static weather.application.WeatherAPP.feedbackController;
import static weather.application.WeatherAPP.locationController;
import static weather.application.WeatherAPP.notificationController;
import static weather.application.WeatherAPP.numOfDisplayWeatherDay;
import static weather.application.WeatherAPP.numOfNotifyWeatherHours;
import static weather.application.WeatherAPP.user;
import static weather.application.WeatherAPP.userDatabase;
import static weather.application.WeatherAPP.weatherServiceController;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.List;
import java.util.Objects;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import weather.data.singledata.Location;
import weather.screendisplay.HomeScreen;
import weather.screendisplay.LocationScreen;
import weather.screendisplay.LoginScreen;
import weather.screendisplay.Screen;
import weather.screendisplay.TodayScreen;

public class ScreenManager {

  private final JFrame window;

  private Screen currentScreen;

  public ScreenManager(JFrame frame) {
    window = frame;

    currentScreen = new LoginScreen(this);
    window.add(currentScreen);

    currentScreen.initBackground();
  }

  public void switchToMainScreen() {
    window.getContentPane().removeAll();

    notificationController.setWeatherDayList(
        weatherServiceController.getDayWeatherByGivenNum(
            currentLocation, currentTime, numOfDisplayWeatherDay));
    notificationController.setWeatherHourList(
        weatherServiceController.getHourWeatherByGivenNum(
            currentLocation, currentTime, numOfNotifyWeatherHours));
    notificationController.modifyNotificationListByWeatherData();

    currentScreen =
        new HomeScreen(
            this,
            currentLocation,
            weatherServiceController.getDayWeatherByGivenNum(
                currentLocation, currentTime, numOfDisplayWeatherDay),
            notificationController.getSpecificNotifications());

    window.add(currentScreen);
    window.repaint();

    currentScreen.initBackground();
  }

  public void switchToLoginScreen() {
    window.getContentPane().removeAll();

    currentScreen = new LoginScreen(this);

    window.add(currentScreen);
    window.repaint();

    currentScreen.initBackground();
  }

  public void switchToTodayScreen() {
    window.getContentPane().removeAll();

    currentScreen =
        new TodayScreen(
            this,
            currentLocation,
            weatherServiceController.getOneWeather(currentLocation, currentTime),
            notificationController.getSpecificNotifications());

    window.add(currentScreen);
    window.repaint();

    currentScreen.initBackground();
  }

  public void switchToLocationScreen() {
    window.getContentPane().removeAll();

    currentScreen =
        new LocationScreen(this, user.preferLocations, locationController.getAllLocations());

    window.add(currentScreen);
    window.repaint();

    currentScreen.initBackground();
  }

  public void addInfoToScreen() {}

  public void loginAction(String account, String password) {
    User targetUser = WeatherAPP.userDatabase.getUser(account);
    if (Objects.equals(account, "")) {
      JOptionPane.showMessageDialog(window, "请输入账号");
    } else if (targetUser == null) {
      JOptionPane.showMessageDialog(window, "账号不存在");
    } else if (Objects.equals(password, "")) {
      JOptionPane.showMessageDialog(window, "请输入密码");
    } else if (targetUser.certificate(password)) {
      WeatherAPP.user = targetUser;
      WeatherAPP.initFromUser();
      switchToMainScreen();
    } else {
      JOptionPane.showMessageDialog(window, "密码错误");
    }
  }

  public void registerAction(String account, String password) {
    if (userDatabase.getUser(account) != null) {
      JOptionPane.showMessageDialog(window, "该账号已注册");
    } else if (Objects.equals(account, "")) {
      JOptionPane.showMessageDialog(window, "账号不能为空");
    } else if (Objects.equals(password, "")) {
      JOptionPane.showMessageDialog(window, "密码不能为空");
    } else {
      user = userDatabase.createUser(account, password);
      JOptionPane.showMessageDialog(window, "注册成功");
    }
  }

  public void logoutAction() {
    WeatherAPP.exitUpdateUserInfo();
    switchToLoginScreen();
  }

  public void switchLocationAction(Location targetLocation) {
    if (targetLocation.equals(currentLocation)) {
      JOptionPane.showMessageDialog(window, String.format("当前地区已为: %s", targetLocation.toString()));
    } else {
      JOptionPane.showMessageDialog(window, String.format("切换地区为: %s", targetLocation.toString()));
      user.changeCurrentLocation(targetLocation);
      locationController.changeCurrentLocation(targetLocation);
      currentLocation = targetLocation;
    }
  }

  // 弹出反馈窗口的方法
  public void feedbackAction() {
    // 创建模态对话框
    JDialog dialog = new JDialog(WeatherAPP.window, "反馈窗口", true);
    dialog.setSize(300, 200);
    dialog.setLayout(new BorderLayout());
    dialog.setLocationRelativeTo(WeatherAPP.window); // 居中显示

    // 创建输入框
    JTextField feedbackField = new JTextField();
    feedbackField.setPreferredSize(new Dimension(250, 30));

    // 创建按钮
    JButton confirmButton = new JButton("确定");
    JButton cancelButton = new JButton("取消");

    // 按钮容器
    JPanel buttonPanel = new JPanel();
    buttonPanel.setLayout(new FlowLayout());
    buttonPanel.add(confirmButton);
    buttonPanel.add(cancelButton);

    // 添加组件到对话框
    dialog.add(new JLabel("请输入您的反馈："), BorderLayout.NORTH);
    dialog.add(feedbackField, BorderLayout.CENTER);
    dialog.add(buttonPanel, BorderLayout.SOUTH);

    // 添加按钮事件
    confirmButton.addActionListener(
        e -> {
          String feedback = feedbackField.getText(); // 获取输入内容
          feedbackController.provideFeedback(feedback); // 调用反馈方法
          dialog.dispose(); // 关闭窗口
        });

    cancelButton.addActionListener(e -> dialog.dispose()); // 关闭窗口

    // 显示对话框
    dialog.setVisible(true);
  }

  public void searchLocationAction(String targetLocation) {
    List<Location> result = locationController.getLocations(targetLocation);
    if (result.isEmpty()) {
      JOptionPane.showMessageDialog(window, "找不到目标地区");
    } else {
      ((LocationScreen) currentScreen).showSearchLocations(result);
    }
  }
}
