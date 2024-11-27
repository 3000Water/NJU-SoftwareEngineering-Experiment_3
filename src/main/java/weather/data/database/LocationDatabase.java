package weather.data.database;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import weather.data.singledata.Location;

public class LocationDatabase extends BasicDatabase<Location> {

  public static Location defaultLocation = new Location("", "北京", "", Location.Fineness.CITY);

  public LocationDatabase() {
    super(
        String.format(
            "%s//src//main//resources//DataSaves//location.txt", System.getProperty("user.dir")));

    // 此处暂为显式赋值，完整的位置信息作为后续完善
    initFromFile();
  }

  public List<Location> getLocationsByName(String locationName) {
    List<Location> findOut = new ArrayList<>();
    for (Location location : datas) {
      if (Objects.equals(location.getProvince(), locationName)
          || Objects.equals(location.getCity(), locationName)
          || Objects.equals(location.getCounty(), locationName)) {
        findOut.add((location));
      }
    }
    return findOut;
  }

  public List<Location> getDatas() {
    return datas;
  }

  public void initFromFile() {
    String[] fileInfo = readFromFile();
    for (String each : fileInfo) {
      addData(Location.fileInfoToData(each));
    }
  }

  public void saveIntoFile() {
    String[] fileInfo = new String[datas.size()];
    for (int i = 0; i != datas.size(); i++) {
      fileInfo[i] = Location.dataToFileInfo(datas.get(i));
    }
    writeToFile(fileInfo);
  }
}
