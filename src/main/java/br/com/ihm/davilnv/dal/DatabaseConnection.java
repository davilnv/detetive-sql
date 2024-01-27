package br.com.ihm.davilnv.dal;

import br.com.ihm.davilnv.utils.ErrorHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.Properties;
import java.util.stream.Collectors;

public class DatabaseConnection {
    private static final String DB_PATH = "./src/main/resources/database/game"; // Caminho do arquivo do banco de dados
    private static final String DB_URL = "jdbc:h2:file:" + DB_PATH; // URL do banco de dados
    private static String USER = "sa"; // Usuário padrão
    private static String PASS = ""; // Sem senha

    public static void getDatabaseProperties() {
        try (InputStream input = DatabaseConnection.class.getResourceAsStream("/database/db.properties")) {
            Properties prop = new Properties();

            if (input == null) {
                ErrorHandler.logAndExit("Desculpe, não foi possível encontrar o arquivo de configuração do banco de dados.");
                return;
            }

            // load a properties file from class path
            prop.load(input);

            // get the property value
            USER = prop.getProperty("db.user");
            PASS = prop.getProperty("db.password");

        } catch (IOException ex) {
            ErrorHandler.logAndExit(ex);
        }
    }

    public static Connection getConnection() {
        try {
            getDatabaseProperties();
            return DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (SQLException ex) {
            ErrorHandler.logAndExit(ex);
        }
        return null;
    }

    public static void executeScript(String scriptPath) {
        try (Connection conn = getConnection()) {
            assert conn != null;
            try (Statement stmt = conn.createStatement()) {

                InputStream is = DatabaseConnection.class.getResourceAsStream(scriptPath);
                if (is == null) {
                    ErrorHandler.logAndExit("Desculpe, não foi possível encontrar o arquivo de script: " + scriptPath );
                }

                assert is != null;
                BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                String sql = reader.lines().collect(Collectors.joining("\n"));

                stmt.execute(sql);

                conn.close();
            }
        } catch (SQLException e) {
            ErrorHandler.logAndExit(e);
        }
    }

    public static void main(String[] args) {
        executeScript("/files/create_data_game.sql");
        System.out.println("Connected to H2 embedded database.");
    }
}