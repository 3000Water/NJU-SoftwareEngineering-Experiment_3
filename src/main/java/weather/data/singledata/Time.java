package weather.data.singledata;

import java.time.LocalDate;
import java.time.LocalTime;

public class Time {

  private final int year;
  private final int month;
  private final int day;
  private final int hour;

  public Time() {
    LocalDate date = LocalDate.now();
    LocalTime time = LocalTime.now();
    this.year = date.getYear();
    this.month = date.getMonthValue();
    this.day = date.getDayOfMonth();
    this.hour = time.getHour();
  }

  public Time(int day) {
    LocalDate date = LocalDate.now();
    LocalTime time = LocalTime.now();
    this.year = date.getYear();
    this.month = date.getMonthValue();
    this.day = day;
    this.hour = time.getHour();
  }

  public Time(int year, int month, int day, int hour) {
    this.year = year;
    this.month = month;
    this.day = day;
    this.hour = hour;
  }

  public static String showToday() {
    LocalDate date = LocalDate.now();
    return String.format("%d月%d日", date.getMonthValue(), date.getDayOfMonth());
  }

  // months < 12
  public Time addMonth(int months) {
    if (month + months > 12) {
      return new Time(year + 1, month + months - 12, day, hour);
    } else {
      return new Time(year, month + months, day, hour);
    }
  }

  // days < 31
  public Time addDay(int days) {
    boolean leapYear = false;
    if (year % 100 == 0) {
      leapYear = year % 400 == 0;
    } else {
      leapYear = year % 4 == 0;
    }
    if (days > 0) {
      int dayLimit;

      if (month == 2) {
        dayLimit = 28;
        if (leapYear) {
          dayLimit += 1;
        }
      } else if (month == 1
          || month == 3
          || month == 5
          || month == 7
          || month == 8
          || month == 10
          || month == 12) {
        dayLimit = 31;
      } else {
        dayLimit = 30;
      }

      if (day + days > dayLimit) {
        if (month == 12) {
          return new Time(year + 1, 1, day + days - dayLimit, hour);
        }
        return new Time(year, month + 1, day + days - dayLimit, hour);
      }
      return new Time(year, month, day + days, hour);
    } else if (days < 0) {
      // Only being -1
      int newMonth;
      if (day == 1) {
        if (month == 1) {
          return new Time(year - 1, 12, 31, hour);
        } else if (month == 2
            || month == 4
            || month == 6
            || month == 8
            || month == 9
            || month == 11) {
          return new Time(year, month - 1, 31, hour);
        } else if (month == 3) {
          if (leapYear) {
            return new Time(year, month - 1, 29, hour);
          }
          return new Time(year, month - 1, 28, hour);
        } else {
          return new Time(year, month - 1, 30, hour);
        }
      } else {
        return new Time(year, month, day - 1, hour);
      }
    } else {
      return this;
    }
  }

  // return the different hours between two days' Time
  public int differHoursFromLaterTime(Time later) {
    if (this.day != later.day) {
      return later.hour - this.hour + 24;
    } else {
      return later.hour - this.hour;
    }
  }

  public int getYear() {
    return year;
  }

  public int getMonth() {
    return month;
  }

  public int getDay() {
    return day;
  }

  public int getHour() {
    return hour;
  }

  public Time setHour(int h) {
    return new Time(year, month, day, h);
  }

  @Override
  public String toString() {
    return String.format("<%d/%d/%d %d时>", year, month, day, hour);
  }

  @Override
  public boolean equals(Object other) {
    if (other instanceof Time) {
      Time otherTime = (Time) other;
      return this.year == otherTime.year
          && this.month == otherTime.month
          && this.day == otherTime.day
          && this.hour == otherTime.hour;
    }
    return false;
  }

  public boolean earlyThan(Time other) {
    if (this.year == other.year) {
      if (this.month == other.month) {
        if (this.day == other.day) {
          return this.hour < other.hour;
        }
        return this.day < other.day;
      }
      return this.month < other.month;
    }
    return this.year < other.year;
  }
}
