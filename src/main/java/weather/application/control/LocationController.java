package weather.application.control;

import java.util.List;
import weather.data.database.LocationDatabase;
import weather.data.singledata.Location;

public class LocationController {

  private final LocationDatabase database;
  private Location currentLocation;

  public LocationController() {
    database = new LocationDatabase();
  }

  protected LocationDatabase getDatabase() {
    return database;
  }

  public List<Location> getLocations(String locationName) {
    return database.getLocationsByName(locationName);
  }

  public Location getCurrentLocation() {
    return currentLocation;
  }

  public List<Location> getAllLocations() {
    return database.getDatas();
  }

  public void changeCurrentLocation(Location location) {
    currentLocation = location;
  }

  public void initData() {
    database.initFromFile();
  }

  public void saveData() {
    database.saveIntoFile();
  }
}
