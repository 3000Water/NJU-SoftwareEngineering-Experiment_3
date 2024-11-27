package weather.data.database;

public class FeedbackDatabase extends BasicDatabase<String> {

  public FeedbackDatabase() {
    super(
        String.format(
            "%s//src//main//resources//DataSaves//feedback.txt", System.getProperty("user.dir")));
  }

  public void saveIntoFile() {
    String[] fileInfo = new String[datas.size()];
    for (int i = 0; i != datas.size(); i++) {
      fileInfo[i] = datas.get(i);
    }
    writeToFile(fileInfo);
  }
}
