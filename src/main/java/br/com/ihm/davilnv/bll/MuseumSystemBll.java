package br.com.ihm.davilnv.bll;

import br.com.ihm.davilnv.dal.MuseumSystemDal;

import javax.swing.table.TableModel;
import java.sql.SQLException;

public class MuseumSystemBll {
    public static boolean getLogin(String username, String password) {
        return MuseumSystemDal.getLogin(username, password);
    }

    public static String[] getTableNames() {
        return MuseumSystemDal.getTableNames();
    }

    public static TableModel executeQuery(String query) throws SQLException {
        return MuseumSystemDal.executeQuery(query);
    }
}
