package br.com.ihm.davilnv.dal;

import br.com.ihm.davilnv.utils.ErrorHandler;
import br.com.ihm.davilnv.utils.StaticQuerySQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

}
