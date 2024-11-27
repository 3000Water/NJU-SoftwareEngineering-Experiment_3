package weather.application.control;

import weather.data.database.FeedbackDatabase;

public class FeedbackController {

  private final FeedbackDatabase database;

  public FeedbackController() {
    database = new FeedbackDatabase();
  }

  public void provideFeedback(String feedback) {
    database.addData(feedback);
  }

  public void saveData() {
    database.saveIntoFile();
  }
}
