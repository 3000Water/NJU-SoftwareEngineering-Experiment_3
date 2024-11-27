package weather.notify;

public class Notification {

  private int id;
  private String message;

  public Notification(String msg) {
    id = NotificationListManager.notificationList.size();
    message = msg;
  }

  public String getMessage() {
    return message;
  }
}
