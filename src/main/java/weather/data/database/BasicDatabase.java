package weather.data.database;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BasicDatabase<T> {

  protected DataList<T> datas;
  private String filePath;

  public BasicDatabase() {
    datas = null;
  }

  public BasicDatabase(String path) {
    datas = new DataList<>();
    filePath = path;
  }

  public void addData(T data) {
    datas.add(data);
  }

  public void removeData(T data) {
    datas.remove(data);
  }

  public void updateData(T data) {
    // TODO
  }

  protected List<T> getDataList() {
    return datas;
  }

  public void updateDataFromOnline() {
    // TODO
  }

  public String[] readFromFile() {
    List<String> lines = new ArrayList<>();

    try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
      String line;
      while ((line = br.readLine()) != null) {
        lines.add(line); // 将每一行添加到列表中
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

    // 将列表转换为数组
    return lines.toArray(new String[0]);
  }

  public void writeToFile(String[] data) {

    try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
      for (String line : data) {
        bw.write(line); // 写入一行内容
        bw.newLine(); // 换行
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
