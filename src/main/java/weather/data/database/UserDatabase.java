package weather.data.database;

import java.util.Objects;
import weather.application.User;

public class UserDatabase extends BasicDatabase<User> {

  public UserDatabase() {
    super(
        String.format(
            "%s//src//main//resources//DataSaves//user.txt", System.getProperty("user.dir")));
    initFromFile();
  }

  public User createUser(String account, String password) {
    User newUser = new User(datas.size(), account, password, LocationDatabase.defaultLocation);
    addData(newUser);
    return newUser;
  }

  public User getUser(int userid) {
    return datas.get(userid);
  }

  public User getUser(String account) {
    for (User user : datas) {
      if (Objects.equals(account, user.getAccount())) {
        return user;
      }
    }
    return null;
  }

  @Override
  public void updateData(User data) {
    for (User user : datas) {
      if (user.equals(data)) {
        user.modify(data);
        break;
      }
    }
  }

  public void initFromFile() {
    String[] fileInfo = readFromFile();
    for (String each : fileInfo) {
      addData(User.fileInfoToData(each));
    }
  }

  public void saveIntoFile() {
    String[] fileInfo = new String[datas.size()];
    for (int i = 0; i != datas.size(); i++) {
      fileInfo[i] = User.dataToFileInfo(datas.get(i));
    }
    writeToFile(fileInfo);
  }
}
