package PostgresDAO;

import Database.ConnessioneDB;
import Database.LoginFailedException;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class RegistrazioneImplementazioneDAO {
    private static Connection connection;

    public static boolean signUp(String login, String password){
        try {
            connection = ConnessioneDB.getInstance("registrazione", "registrazione").connection;
            //Si tratta di un utente specifico che ha il solo permesso di chiamare la funzione create user
            CallableStatement callableStatement = connection.prepareCall("call progetto.createuser(?, ?)");
            callableStatement.setString(1, login);
            callableStatement.setString(2, password);
            callableStatement.execute();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } catch (LoginFailedException loginFailedException) {
            return false;
        }
        return true;
    }
}
