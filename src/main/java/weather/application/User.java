package weather.application;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import weather.data.singledata.Location;

public class User {

  private final int userId;
  private final String account;
  List<Location> preferLocations;
  private String password;
  private Location currentLocation;

  public User(int id, String account, String password, Location location) {
    this.userId = id;
    this.account = account;
    this.password = password;
    this.currentLocation = location;
    this.preferLocations = new ArrayList<>();
  }

  public User(
      int id, String account, String password, Location location, List<Location> locationList) {
    this.userId = id;
    this.account = account;
    this.password = password;
    this.currentLocation = location;
    this.preferLocations = locationList;
  }

  public static String dataToFileInfo(User user) {
    StringBuilder info =
        new StringBuilder(
            String.format(
                "%d--%s--%s--%s",
                user.userId,
                user.account,
                user.password,
                Location.dataToFileInfo(user.currentLocation)));

    if (!user.preferLocations.isEmpty()) {
      info.append("--");
      boolean first = true;
      for (Location location : user.preferLocations) {
        if (first) {
          first = false;
        } else {
          info.append("+");
        }
        info.append(Location.dataToFileInfo(location));
      }
    }
    return info.toString();
  }

  public static User fileInfoToData(String info) {
    String[] data = info.split("--");
    int id = Integer.parseInt(data[0]);
    String account = data[1];
    String password = data[2];
    Location currentLocation = Location.fileInfoToData(data[3]);
    List<Location> preferLocations = new ArrayList<>();

    if (data.length != 4) {
      String[] prefers = data[4].split("\\+");
      for (String prefer : prefers) {
        preferLocations.add(Location.fileInfoToData(prefer));
      }
    }

    return new User(id, account, password, currentLocation, preferLocations);
  }

  public Location getCurrentLocation() {
    return currentLocation;
  }

  public String getAccount() {
    return account;
  }

  public boolean certificate(String givenPassword) {
    return Objects.equals(givenPassword, password);
  }

  public void modify(User newData) {
    this.password = newData.password;
    this.currentLocation = newData.currentLocation;
    for (Location newLocation : newData.preferLocations) {
      if (!this.preferLocations.contains(newLocation)) {
        this.preferLocations.add(newLocation);
      }
    }
  }

  public List<Location> addPreferLocation(Location location) {
    if (!preferLocations.contains(location)) {
      preferLocations.add(location);
    }
    return preferLocations;
  }

  public List<Location> removePreferLocation(Location location) {
    preferLocations.remove(location);
    return preferLocations;
  }

  public void changeCurrentLocation(Location location) {
    currentLocation = location;
  }

  public boolean containsPrefer(Location location) {
    return preferLocations.contains(location);
  }

  @Override
  public boolean equals(Object obj) {
    return obj instanceof User && this.userId == ((User) obj).userId;
  }

  public void showPreferLocations() {
    for (Location location : preferLocations) {
      System.out.println(location);
    }
  }
}
