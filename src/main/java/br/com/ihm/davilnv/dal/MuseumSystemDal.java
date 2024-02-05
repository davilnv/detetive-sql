package br.com.ihm.davilnv.dal;

import br.com.ihm.davilnv.utils.ErrorHandler;
import br.com.ihm.davilnv.statics.StaticQuerySQL;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class MuseumSystemDal {

    public static boolean getLogin(String username, String password) {
        String query = StaticQuerySQL.LOGIN;
        try (Connection conn = DatabaseConnection.getConnection()) {
            assert conn != null;
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            ErrorHandler.logAndExit(e);
        }
        return false;
    }

    public static String[] getTableNames() {
        ArrayList<String> tableNames = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection()) {
            assert conn != null;
            DatabaseMetaData metaData = conn.getMetaData();
            ResultSet tables = metaData.getTables(null, "PUBLIC", "%", null);

            while (tables.next()) {
                tableNames.add(tables.getString(3));
            }

        } catch (SQLException e) {
            ErrorHandler.logAndExit(e);
        }

        return tableNames.toArray(new String[0]);
    }

    public static TableModel executeQuery(String query) throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        assert conn != null;
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        return buildTableModel(rs);
    }

    private static TableModel buildTableModel(ResultSet rs) throws SQLException {
        ResultSetMetaData metaData = rs.getMetaData();

        // Nome das colunas
        Vector<String> columnNames = new Vector<>();
        int columnCount = metaData.getColumnCount();
        for (int column = 1; column <= columnCount; column++) {
            columnNames.add(metaData.getColumnName(column));
        }

        // Dados das colunas
        Vector<Vector<Object>> data = new Vector<>();
        while (rs.next()) {
            Vector<Object> vector = new Vector<>();
            for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                vector.add(rs.getObject(columnIndex));
            }
            data.add(vector);
        }

        return new DefaultTableModel(data, columnNames);
    }

    public static List<String> getAnswerDayAcessUsers() {
        String query = StaticQuerySQL.GET_DAY_ACCESS_USERS;
        return getStringsFromQuery(query);
    }


    public static List<String> getAnswerAcessUsersCameras() {
        String query = StaticQuerySQL.GET_ACCESS_CAMERAS;
        return getStringsFromQuery(query);
    }

    private static List<String> getStringsFromQuery(String query) {
        List<String> users = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection()) {
            assert conn != null;
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                users.add(rs.getString(1));
            }
        } catch (SQLException e) {
            ErrorHandler.logAndExit(e);
        }
        return users;
    }
}
