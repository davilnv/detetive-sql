package br.com.ihm.davilnv.bll;

import br.com.ihm.davilnv.dal.MuseumSystemDal;

public class MuseumSystemBll {
    public static boolean getLogin(String username, String password) {
        return MuseumSystemDal.getLogin(username, password);
    }
}
