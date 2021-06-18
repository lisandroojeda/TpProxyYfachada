package ar.unrn.db;


import ar.unrn.model.DbFachada;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JdbcFachada implements DbFachada {
    private Connection connection;

    @Override
    public void open() {
        try {
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/dbpatronproxy", "root", "");
        } catch (SQLException e) {
            throw new RuntimeException("No se pudo hacer un conexion con la base de datos.", e);
        }
    }

    @Override
    public List<Map<String, String>> queryResultAsAsociation(String sql) {
        List<Map<String, String>> list = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                ResultSetMetaData resultSetMetaData = result.getMetaData();
                Map<String, String> map = new HashMap<>();
                for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
                    map.put(resultSetMetaData.getColumnName(i), result.getString(i));
                }
                list.add(map);
            }
            result.close();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
        return list;
    }

    @Override
    public List<String[]> queryResultAsArray(String sql) {
        List<String[]> list = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                ResultSetMetaData resultSetMetaData = result.getMetaData();
                String[] listaStrings = new String[resultSetMetaData.getColumnCount()];
                for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
                    listaStrings[i - 1] = result.getString(i);

                }
                list.add(listaStrings);
            }
            result.close();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
        return list;
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException("No se pudo cerrar la conexion con la base de datos.", e);
        }
    }
}
