package weather.screendisplay;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import weather.application.ScreenManager;
import weather.application.WeatherAPP;
import weather.data.singledata.Location;
import weather.display.DisplayTextField;
import weather.display.DisplayTextLabel;

public class LocationScreen extends Screen {

  private DisplaySection preferSection;
  private DisplaySection searchSection;
  private List<Location> databaseList;

  public LocationScreen(
      ScreenManager screenManager, List<Location> preferLocations, List<Location> databaseList) {
    super(screenManager);
    this.databaseList = databaseList;

    addButton("返回", "Back", 15, 53, e -> screenManager.switchToMainScreen());

    DisplaySection locationSearch = addSection(205, 43, 1208, 938);
    DisplayTextField searchField = locationSearch.addTextField(590, "地区", 36, 274, 59);
    locationSearch.addTextLabel("收藏地区", 27, 42, 188, DisplayTextLabel.TextPosition.None);
    locationSearch.addTextLabel("搜索结果及未收藏地区", 27, 42, 458, DisplayTextLabel.TextPosition.None);
    preferSection = locationSearch.addSection(0, 245, 1208, 207);
    searchSection = locationSearch.addSection(0, 509, 1208, 429);
    showPreferLocations(preferLocations);
    showSearchLocations(new ArrayList<>());

    locationSearch.addButton(
        "", "Search", 0, 0, e -> screenManager.searchLocationAction(searchField.getText()));

    locationSearch.addImageLabel("Location", "1", 0, 0);
  }

  public void showPreferLocations(List<Location> preferLocations) {
    preferSection.removeAll();
    int currentY = 8;
    boolean firstLineDone = true;
    Location location;
    int index = 0;
    for (; currentY < preferSection.getHeight() && index != preferLocations.size(); index++) {
      location = preferLocations.get(index);
      DisplaySection eachLocation = preferSection.addSection(42, currentY, 505, 70);
      eachLocation.addTextLabel(location.toString(), 36, 0, 0, DisplayTextLabel.TextPosition.None);
      int finalIndex = index;
      eachLocation.addButton(
          "",
          "CancelPrefer",
          290,
          14,
          e -> {
            showPreferLocations(
                WeatherAPP.user.removePreferLocation(preferLocations.get(finalIndex)));
            showSearchLocations(new ArrayList<>());
          });
      eachLocation.addButton(
          "切换地区",
          14,
          "Switch",
          376,
          14,
          e -> screenManager.switchLocationAction(preferLocations.get(finalIndex)));

      currentY += 70;

      if (index + 1 != preferLocations.size()) {
        firstLineDone = false;
      }
    }
    if (!firstLineDone) {
      currentY = 2;
      for (;
          currentY < preferSection.getHeight() + 70 && index != preferLocations.size();
          index++) {
        location = preferLocations.get(index);
        DisplaySection eachLocation = preferSection.addSection(650, currentY, 505, 70);
        eachLocation.addTextLabel(
            location.toString(), 36, 0, 0, DisplayTextLabel.TextPosition.None);
        int finalIndex = index;
        eachLocation.addButton(
            "",
            "CancelPrefer",
            290,
            14,
            e -> {
              showPreferLocations(
                  WeatherAPP.user.removePreferLocation(preferLocations.get(finalIndex)));
              showSearchLocations(new ArrayList<>());
            });
        eachLocation.addButton(
            "切换地区",
            14,
            "Switch",
            376,
            14,
            e -> screenManager.switchLocationAction(preferLocations.get(finalIndex)));
        currentY += 70;
      }
    }
    preferSection.repaint();
  }

  public void showSearchLocations(List<Location> searchList) {
    searchSection.removeAll();
    for (Location location : databaseList) {
      if (!searchList.contains(location)) {
        searchList.add(location);
      }
    }
    Set<Location> removeSet = new HashSet<>();
    for (Location location : searchList) {
      if (WeatherAPP.user.containsPrefer(location)) {
        removeSet.add(location);
      }
    }
    searchList.removeAll(removeSet);
    int currentY = 2;
    boolean firstLineDone = true;
    Location location;
    int index = 0;
    for (; currentY < searchSection.getHeight() + 70 && index != searchList.size(); index++) {
      location = searchList.get(index);
      DisplaySection eachLocation = searchSection.addSection(42, currentY, 505, 70);
      eachLocation.addTextLabel(location.toString(), 36, 0, 0, DisplayTextLabel.TextPosition.None);
      int finalIndex = index;
      List<Location> finalSearchList = searchList;
      eachLocation.addButton(
          "",
          "AddPrefer",
          290,
          14,
          e -> {
            showPreferLocations(WeatherAPP.user.addPreferLocation(finalSearchList.get(finalIndex)));
            showSearchLocations(new ArrayList<>());
          });
      eachLocation.addButton(
          "切换地区",
          14,
          "Switch",
          376,
          14,
          e -> screenManager.switchLocationAction(searchList.get(finalIndex)));

      currentY += 70;

      if (index + 1 == searchList.size()) {
        firstLineDone = false;
      }
    }
    if (firstLineDone) {
      currentY = 2;
      for (; currentY < searchSection.getHeight() + 70 && index != searchList.size(); index++) {
        location = searchList.get(index);
        DisplaySection eachLocation = searchSection.addSection(650, currentY, 505, 70);
        eachLocation.addTextLabel(
            location.toString(), 36, 0, 0, DisplayTextLabel.TextPosition.None);
        int finalIndex = index;
        List<Location> finalSearchList = searchList;
        eachLocation.addButton(
            "",
            "AddPrefer",
            290,
            14,
            e -> {
              showPreferLocations(
                  WeatherAPP.user.addPreferLocation(finalSearchList.get(finalIndex)));
              showSearchLocations(new ArrayList<>());
            });
        eachLocation.addButton(
            "切换地区",
            14,
            "Switch",
            376,
            14,
            e -> screenManager.switchLocationAction(searchList.get(finalIndex)));
        currentY += 70;
      }
    }
    searchSection.repaint();
  }
}
