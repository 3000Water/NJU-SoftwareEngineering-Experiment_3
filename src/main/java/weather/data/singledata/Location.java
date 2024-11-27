package weather.data.singledata;

import java.util.Objects;

public class Location {

  private final String provinceName;
  private final String cityName;
  private final String countyName;
  private double longitude;
  private double latitude;
  private Fineness fineness;

  public Location(String province, String city, String county, Fineness f) {
    provinceName = province;
    cityName = city;
    countyName = county;
    fineness = f;
  }

  public static String dataToFileInfo(Location location) {
    String provinceInfo =
        Objects.equals(location.provinceName, "") ? "null" : location.provinceName;
    String countyInfo = Objects.equals(location.countyName, "") ? "null" : location.countyName;
    return String.format("[%s-%s-%s]", provinceInfo, location.cityName, countyInfo);
  }

  public static Location fileInfoToData(String info) {
    String[] data = info.substring(1, info.length() - 1).split("-");
    String province = data[0];
    String city = data[1];
    String county = data[2];
    Fineness fine = Fineness.CITY;

    if (Objects.equals(data[0], "null")) {
      province = "";
    }

    if (Objects.equals(data[2], "null")) {
      county = "";
    } else {
      fine = Fineness.COUNTY;
    }
    return new Location(province, city, county, fine);
  }

  @Override
  public String toString() {
    if (Objects.equals(provinceName, "")) {
      if (fineness == Fineness.CITY) {
        return String.format("%s", cityName);
      } else {
        return String.format("%s %s", cityName, countyName);
      }
    }
    if (fineness == Fineness.CITY) {
      return String.format("%s %s", provinceName, cityName);
    } else {
      return String.format("%s %s %s", provinceName, cityName, countyName);
    }
  }

  public String getProvince() {
    return provinceName;
  }

  public String getCity() {
    return cityName;
  }

  public String getCounty() {
    return countyName;
  }

  @Override
  public boolean equals(Object other) {
    if (other instanceof Location && this.fineness == ((Location) other).fineness) {
      if (this.fineness == Fineness.CITY) {
        return Objects.equals(this.cityName, ((Location) other).cityName);
      } else {
        return Objects.equals(this.countyName, ((Location) other).countyName);
      }
    }
    return false;
  }

  public enum Fineness {
    CITY(0),
    COUNTY(1);

    private final int type;

    Fineness(int i) {
      type = i;
    }
  }
}
