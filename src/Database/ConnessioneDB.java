package Database;

import GUI.Login;
import org.postgresql.util.PSQLException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnessioneDB {

    private static ConnessioneDB instance;
    public Connection connection = null;
    private String login;
    private String password;
    private String url = "jdbc:postgresql://localhost:5432/Progetto";
    private String driver = "org.postgresql.Driver";

    private ConnessioneDB(String login, String password) throws SQLException, LoginFailedException {
        try {
            this.login = login;
            this.password = password;
            Class.forName(driver);
            connection = DriverManager.getConnection(url, this.login, this.password);
        } catch (ClassNotFoundException ex) {
            System.out.println("Database Connection Creation Failed : " + ex.getMessage());
            ex.printStackTrace();
        } catch (PSQLException psqlException) {
            throw new LoginFailedException("Errore durante la connessione al database");
        }
    }


    public static ConnessioneDB getInstance(String login, String password) throws SQLException, LoginFailedException {
        try{
            if (instance == null || instance.connection.isClosed()) {
                instance = new ConnessioneDB(login, password);
            }
            return instance;
        } catch(LoginFailedException loginFailedException){
            throw new LoginFailedException("Errore durante la connessione al database");
        }

    }

    public Connection getConnection() {
        return connection;
    }
}

