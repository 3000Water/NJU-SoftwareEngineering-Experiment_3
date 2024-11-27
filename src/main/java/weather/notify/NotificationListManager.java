package weather.notify;

import java.util.ArrayList;
import java.util.List;

public class NotificationListManager {

  protected static List<Notification> notificationList;

  public NotificationListManager() {
    notificationList = new ArrayList<>();
  }

  public void addNotification(Notification notification) {
    notificationList.add(notification);
  }

  public void removeNotification(Notification notification) {
    notificationList.remove(notification);
  }

  public List<Notification> getNotificationList() {
    return notificationList;
  }

  public void clearList() {
    notificationList.clear();
  }
}
