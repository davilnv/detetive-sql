package br.com.ihm.davilnv.statics;

public class StaticQuerySQL {
    public static final String LOGIN = "SELECT * FROM USUARIO WHERE login = ? AND senha = ?";

    public static final String GET_DAY_ACCESS_USERS = "SELECT \n" +
            "\tUSU.NOME\n" +
            "FROM ACESSO ACE\n" +
            "\tINNER JOIN USUARIO USU ON ACE.USUARIO_CODIGO = USU.CODIGO\n" +
            "WHERE DATA_ACESSO >= CURRENT_TIMESTAMP - INTERVAL '24' HOUR AND USU.LOGIN <> 'detetive';";

    public static final String GET_ACCESS_CAMERAS = "SELECT\n" +
            "\tUSU.NOME\n" +
            "FROM ACESSO_CAMERA ACE\n" +
            "\tINNER JOIN USUARIO USU ON ACE.USUARIO_ACESSO = USU.CODIGO\n" +
            "WHERE CODIGO_CAMERA = 3 AND USUARIO_AUTORIZACAO IS NULL;";
}
