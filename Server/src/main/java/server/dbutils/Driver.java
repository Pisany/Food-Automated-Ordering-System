package server.dbutils;

import java.sql.*;
import java.util.ArrayList;

public class Driver {

    private static final String JDBC_MYSQL_LOCALHOST_3306_FAOS = "jdbc:mysql://127.0.0.1:3306/faos";
    private static final String USER = "root";
    private static final String PASSWORD = "root";
    private static final String TIMEZONE = "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

    private String foo = "";

    private Connection myConnection;
    private Statement statement;
    private ResultSet results;


    public void getMealList() {
        try {

            myConnection = DriverManager.getConnection(JDBC_MYSQL_LOCALHOST_3306_FAOS + TIMEZONE, USER, PASSWORD);
            statement = myConnection.createStatement();
            results = statement.executeQuery(" SELECT * FROM produkt");
            while (results.next()) {
                foo = foo +
                        results.getString("id_kat_prod") + "-#-" +
                        results.getString("id_produktu") + "-#-" +
                        results.getString("nazwa") + "-#-" +
                        results.getString("cena_szt") + "-#-" +
                        results.getString("waga") + "-#-" +
                        results.getString("file_path") + "--";
            }
            System.out.println(foo);
            myConnection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveRecipe(String splited) {
        String[] splitedArray;

        splitedArray = splited.split(" - ");

        try {
            myConnection = DriverManager.getConnection(JDBC_MYSQL_LOCALHOST_3306_FAOS + TIMEZONE, USER, PASSWORD);
            statement = myConnection.createStatement();
            String query = " insert into ZAMOWIENIE (id_zammowienia, datatime, id_urzadzenia, id_platnosci)"
                    + " values (?, ?, ?, 1)";

            PreparedStatement preparedStmt = myConnection.prepareStatement(query);
            preparedStmt.setInt(1, Integer.parseInt(splitedArray[2]));
            preparedStmt.setDate(2, Date.valueOf(splitedArray[1]));
            preparedStmt.setInt(3, Integer.parseInt(splitedArray[0]));

            preparedStmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Zapytanie nie wykonane! " + e.getMessage() + ": " + e.getErrorCode());
        }
        System.out.println("Lenght: " + splitedArray.length);
        for (int i = 3; i < splitedArray.length;i=i+3) {
            String query2 = " insert into zamowienia_szczegoly (ilosc, koszt, id_produktu, id_zammowienia)"
                    + " values (?, ?, ?, ?)";
            System.out.println("i: "+i );
            try {

                PreparedStatement preparedStmt = myConnection.prepareStatement(query2);
                preparedStmt.setInt(1, Integer.parseInt(splitedArray[i + 1]));
                System.out.println(splitedArray[i + 1]);
                preparedStmt.setDouble(2, Double.parseDouble(splitedArray[i + 2]));
                System.out.println(splitedArray[i + 2]);
                preparedStmt.setInt(3, Integer.parseInt(splitedArray[i]));
                System.out.println(splitedArray[i]);
                preparedStmt.setInt(4, Integer.parseInt(splitedArray[2]));

                preparedStmt.executeUpdate();



            } catch (SQLException e) {
                System.out.println("Zapytanie nie wykonane! " + e.getMessage() + ": " + e.getErrorCode());
            }
        }
        try {
            myConnection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public String getFoo() {
        return foo;
    }

}



