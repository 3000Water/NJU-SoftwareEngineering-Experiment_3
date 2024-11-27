package weather.notify;

public class AlertNotification extends Notification {

  private String alertType;
  private String severity;

  public AlertNotification(String msg) {
    super(msg);
  }
}
