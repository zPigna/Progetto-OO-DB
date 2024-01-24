package PostgresDAO;

import DAO.UtenteDAO;
import Database.*;

import java.sql.*;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class UtenteImplementazioneDAO implements UtenteDAO {

    private String login;
    private String password;
    private Connection connection;


    public UtenteImplementazioneDAO(String login, String password) {
        this.login = login;
        this.password = password;
    }
    
    @Override
    public boolean login() {
        try {
            connection = ConnessioneDB.getInstance(login, password).connection;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } catch (LoginFailedException loginFailedException) {
            return false;
        }
        return true;
    }



    public void fetchGiocatoriFromDB(ArrayList<String> listaNomi, ArrayList<String> listaCognomi, ArrayList<String> listaCodFisc, ArrayList<String> listaPiedi,
                                     ArrayList<LocalDate> listaDateNascita, ArrayList<LocalDate> listaDateRitiro, ArrayList<ArrayList<String>> listaListaCaratteristiche) {
        try {
            connection = ConnessioneDB.getInstance(login, password).connection;
            PreparedStatement getGiocatoriFromDB = connection.prepareStatement("SELECT * FROM progetto.giocatore");
            ResultSet rs = getGiocatoriFromDB.executeQuery();
            while (rs.next()) {

                listaCodFisc.add(rs.getString(1));
                listaNomi.add(rs.getString(2));
                listaCognomi.add(rs.getString(3));
                listaDateNascita.add(rs.getObject(4, LocalDate.class));
                listaPiedi.add(rs.getString(5));
                listaDateRitiro.add(rs.getObject(6, LocalDate.class));

                ArrayList<String> listaCaratteristiche = new ArrayList<>();

                try{
                    PreparedStatement getCaratteristiche = connection.prepareStatement("SELECT * FROM progetto.getCaratteristiche(?)");
                    getCaratteristiche.setString(1, rs.getString(1));
                    ResultSet caratteristiche = getCaratteristiche.executeQuery();

                    while (caratteristiche.next()) {
                        listaCaratteristiche.add(rs.getString(1));
                    }
                    listaListaCaratteristiche.add(listaCaratteristiche);
                    caratteristiche.close();
                    getCaratteristiche.close();
                } catch(SQLException ex){
                    ex.printStackTrace();
                }
            }
            rs.close();
            getGiocatoriFromDB.close();
            connection.close();
        } catch(SQLException e){
            e.printStackTrace();
        } catch (LoginFailedException e) {
            throw new RuntimeException(e);
        }

    }

    public void fetchSquadreFromDB(ArrayList<String> listaNomi, ArrayList<String> listaNazionalita, ArrayList<Integer> listaIdCampionati){
        try{
            connection = ConnessioneDB.getInstance(login, password).connection;
            PreparedStatement getSquadreFromDB = connection.prepareStatement("SELECT * FROM progetto.SQUADRA");
            ResultSet rs = getSquadreFromDB.executeQuery();

            while(rs.next()){
                listaNomi.add(rs.getString(1));
                listaNazionalita.add(rs.getString(2));
                listaIdCampionati.add(rs.getInt(3));
            }
            rs.close();
            getSquadreFromDB.close();
            connection.close();
        } catch(SQLException e){
            e.printStackTrace();
        } catch (LoginFailedException e) {
            throw new RuntimeException(e);
        }
    }

    public void fetchTrofeiDiSquadraFromDB(ArrayList<String> listaNomi, ArrayList<String> listaAnni, ArrayList<String> listaMeriti,
                                           ArrayList<String> listaNomiSquadra, ArrayList<String> listaNazionalitaSquadra){
        try{
            connection = ConnessioneDB.getInstance(login, password).connection;
            PreparedStatement getTrofeiDiSquadraFromDB = connection.prepareStatement("SELECT * FROM progetto.TROFEODISQUADRA");
            ResultSet rs = getTrofeiDiSquadraFromDB.executeQuery();

            while(rs.next()){
                listaNomi.add(rs.getString(1));
                listaAnni.add(rs.getString(2));
                listaMeriti.add(rs.getString(3));
                listaNomiSquadra.add(rs.getString(4));
                listaNazionalitaSquadra.add(rs.getString(5));
            }
            rs.close();
            getTrofeiDiSquadraFromDB.close();
            connection.close();
        }catch(SQLException e){
            e.printStackTrace();
        } catch (LoginFailedException e) {
            throw new RuntimeException(e);
        }
    }

    public void fetchTrofeiIndividiualiFromDB(ArrayList<String> listaNomi, ArrayList<String> listaAnni, ArrayList<String> listaMeriti, ArrayList<String> listaCodFisc){
        try{
            connection = ConnessioneDB.getInstance(login, password).connection;
            PreparedStatement getTrofeiIndividualiFromDB = connection.prepareStatement("SELECT * FROM progetto.TROFEOINDIVIDUALE");
            ResultSet rs = getTrofeiIndividualiFromDB.executeQuery();

            while(rs.next()){
                listaNomi.add(rs.getString(1));
                listaAnni.add(rs.getString(2));
                listaMeriti.add(rs.getString(3));
                listaCodFisc.add(rs.getString(4));
            }
            rs.close();
            getTrofeiIndividualiFromDB.close();
            connection.close();
        }catch(SQLException e){
            e.printStackTrace();
        } catch (LoginFailedException e) {
            throw new RuntimeException(e);
        }
    }


    public void fetchCampionatiFromDB(ArrayList<Integer> listaId, ArrayList<String> listaNomi, ArrayList<String> listaAnni){
        try{
            connection = ConnessioneDB.getInstance(login, password).connection;
            PreparedStatement getCampionatiFromDB = connection.prepareStatement("SELECT * FROM progetto.CAMPIONATO");
            ResultSet rs = getCampionatiFromDB.executeQuery();

            while(rs.next()){
                listaId.add(rs.getInt(1));
                listaNomi.add(rs.getString(2));
                listaAnni.add(rs.getString(3));
            }
            rs.close();
            getCampionatiFromDB.close();
            connection.close();
        }catch(SQLException e){
            e.printStackTrace();
        } catch (LoginFailedException e) {
            throw new RuntimeException(e);
        }
    }

    public void fetchMilitanzeFromDB(ArrayList<String> listaCodFisc, ArrayList<String> listaNomiSquadra, ArrayList<String> listaNazionalitaSquadra,
                                     ArrayList<LocalDate> listaDateInizio, ArrayList<LocalDate> listaDateFine, ArrayList<String> listaRuoli,
                                     ArrayList<Integer> listaPartitaGiocate, ArrayList<Integer> listaGolEffettuati, ArrayList<Integer> listaGolSubiti,
                                     ArrayList<Integer> listaAmmonizioni, ArrayList<Integer> listaEspulsioni) {

        try{
            connection = ConnessioneDB.getInstance(login, password).connection;
            PreparedStatement getMilitanzeFromDB = connection.prepareStatement("SELECT * FROM progetto.Milita");
            ResultSet rs = getMilitanzeFromDB.executeQuery();

            while(rs.next()){
                listaCodFisc.add(rs.getString(1));
                listaNomiSquadra.add(rs.getString(2));
                listaNazionalitaSquadra.add(rs.getString(3));
                listaDateInizio.add(rs.getObject(4, LocalDate.class));
                listaDateFine.add(rs.getObject(5, LocalDate.class));
                listaRuoli.add(rs.getString(6));
                listaPartitaGiocate.add(rs.getInt(7));
                listaGolEffettuati.add(rs.getInt(8));
                listaGolSubiti.add(rs.getInt(9));
                listaAmmonizioni.add(rs.getInt(10));
                listaEspulsioni.add(rs.getInt(11));
            }
            rs.close();
            getMilitanzeFromDB.close();
            connection.close();
        }catch(SQLException e){
            e.printStackTrace();
        } catch (LoginFailedException e) {
            throw new RuntimeException(e);
        }
    }

    public void getCarriereFromDB(ArrayList<String> listaCodFisc, ArrayList<String> listaNomi, ArrayList<String> listaCognomi,
                                  ArrayList<LocalDate> listaDateNascita, ArrayList<Integer> listaEta, ArrayList<String> listaPiedi, ArrayList<String> listaNomiSquadra, ArrayList<String> listaRuoli,
                                  ArrayList<String> listaCaratteristiche, ArrayList<Integer> listaPartiteGiocate, ArrayList<Integer> listaGolEffettuati, ArrayList<Integer> listaGolSubiti,
                                  ArrayList<Integer> listaAmmonizioni, ArrayList<Integer> listaEspulsioni){
        try{
            connection = ConnessioneDB.getInstance(login, password).connection;
            CallableStatement callableStatement = connection.prepareCall("{call progetto.carrieragiocatoriall()}");
            ResultSet rs = callableStatement.executeQuery();
            while(rs.next()){
                listaCodFisc.add(rs.getString(1));
                listaNomi.add(rs.getString(2));
                listaCognomi.add(rs.getString(3));
                listaDateNascita.add(rs.getObject(4, LocalDate.class));
                listaEta.add(rs.getInt(5));
                listaPiedi.add(rs.getString(6));
                listaNomiSquadra.add(rs.getString(7));
                listaRuoli.add(rs.getString(8));
                listaCaratteristiche.add(rs.getString(9));
                listaPartiteGiocate.add(rs.getInt(10));
                listaGolEffettuati.add(rs.getInt(11));
                listaGolSubiti.add(rs.getInt(12));
                listaAmmonizioni.add(rs.getInt(13));
                listaEspulsioni.add(rs.getInt(14));
            }
            rs.close();
            callableStatement.close();
            connection.close();
        }catch(SQLException e){
            e.printStackTrace();
        } catch (LoginFailedException e) {
            throw new RuntimeException(e);
        }
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}