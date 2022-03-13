import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.*;

public class LocationStore {
    public static void main(String[] args) {
        List<List<String>> csv = readCSV();

        setDB(csv);
    }

    public static void setDB(List<List<String>> csv) {
        String user = "adminkuk"; // 사용자 이름
        String password = "kukjun"; // 비밀번호
        String url = "jdbc:postgresql://localhost:5432/simpletodays"; // 주소

        String query = "insert into location values (?, ?, ?, ?, ? ,?)";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            Class.forName("org.postgresql.Driver");

            for (int i = 1; i < csv.size(); i++) {
                preparedStatement.setLong(1, Long.parseLong(csv.get(i).get(0)));
                preparedStatement.setString(2, csv.get(i).get(1));
                preparedStatement.setString(3, csv.get(i).get(2));
                preparedStatement.setString(4, csv.get(i).get(3));
                preparedStatement.setString(5, csv.get(i).get(4));
                preparedStatement.setString(6, csv.get(i).get(5));
                preparedStatement.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<List<String>> readCSV() {
        List<List<String>> csvList = new ArrayList<List<String>>();

        File csv = new File(".//src//main//resources//Location_Information.csv");
        BufferedReader br = null;
        String line = "";

        try {
            br = new BufferedReader(new FileReader(csv));
            while ((line = br.readLine()) != null) {
                List<String> aLine = new ArrayList<String>();
                String[] lineArr = line.split(",");
                aLine = Arrays.asList(lineArr);
                csvList.add(aLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return csvList;
    }
}