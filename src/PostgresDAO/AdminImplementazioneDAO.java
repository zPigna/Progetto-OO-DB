package PostgresDAO;

import DAO.AdminDAO;
import Database.ConnessioneDB;
import Database.LoginFailedException;

import java.sql.*;
import java.time.LocalDate;

public class AdminImplementazioneDAO extends UtenteImplementazioneDAO implements AdminDAO{
    private String login;
    private String password;
    private Connection connection;

    public AdminImplementazioneDAO(String login, String password) {
        super(login, password);
    }



    public boolean checkAdmin(){
        try{
            connection = ConnessioneDB.getInstance(login, password).connection;
            CallableStatement callableStatement = connection.prepareCall("SELECT * FROM progetto.getRuoloUtente()");
            ResultSet rs = callableStatement.executeQuery();
            rs.next();
            if(rs.getString(1).equals("ruolo_select")){
                callableStatement.close();
                rs.close();
                connection.close();
                return false;
            }else{
                callableStatement.close();
                rs.close();
                connection.close();
                return true;
            }
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        } catch (LoginFailedException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean inserisciGiocatore(String nome, String cognome, String codFisc, String piede, LocalDate dataDiNascita) {
         try{
             connection = ConnessioneDB.getInstance(login, password).connection;
             CallableStatement callableStatement = connection.prepareCall("call progetto.inserisciGiocatore(?, ?, ?, ?, ?)");
             callableStatement.setString(1, codFisc);
             callableStatement.setString(2, nome);
             callableStatement.setString(3, cognome);
             callableStatement.setObject(4, dataDiNascita);
             callableStatement.setString(5, piede);
             callableStatement.execute();
             callableStatement.close();
             connection.close();
         } catch (SQLException | LoginFailedException e) {
             e.printStackTrace();
             return false;
         }
        return true;
    }

    @Override
    public void modificaNomeGiocatore(String codFisc, String nuovoNome) {
        try{
            connection = ConnessioneDB.getInstance(login, password).connection;
            PreparedStatement updateNameGiocatore = connection.prepareStatement("UPDATE progetto.Giocatore SET NOME = ? WHERE codfisc LIKE ?");
            updateNameGiocatore.setString(1, nuovoNome);
            updateNameGiocatore.setString(2, codFisc);
            updateNameGiocatore.executeUpdate();
            updateNameGiocatore.close();
            connection.close();
        }catch(SQLException e){
            e.printStackTrace();
        } catch (LoginFailedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void modificaCognomeGiocatore(String codFisc, String nuovoCognome) {
        try{
            connection = ConnessioneDB.getInstance(login, password).connection;
            PreparedStatement updateStatement = connection.prepareStatement("UPDATE progetto.Giocatore SET COGNOME = ? WHERE codfisc LIKE ?");
            updateStatement.setString(1, nuovoCognome);
            updateStatement.setString(2, codFisc);
            updateStatement.executeUpdate();
            updateStatement.close();
            connection.close();
        }catch(SQLException e){
            e.printStackTrace();
        } catch (LoginFailedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void modificaCodiceFiscaleGiocatore(String codFisc, String nuovoCodFisc) {
        try{
            connection = ConnessioneDB.getInstance(login, password).connection;
            PreparedStatement updateStatement = connection.prepareStatement("UPDATE progetto.Giocatore SET CODFISC = ? WHERE codfisc LIKE ?");
            updateStatement.setString(1, nuovoCodFisc);
            updateStatement.setString(2, codFisc);
            updateStatement.executeUpdate();
            updateStatement.close();
            connection.close();
        }catch(SQLException e){
            e.printStackTrace();
        } catch (LoginFailedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void modificaPiedeGiocatore(String codFisc, String nuovoPiede) {
        try{
            connection = ConnessioneDB.getInstance(login, password).connection;
            PreparedStatement updateStatement = connection.prepareStatement("UPDATE progetto.Giocatore SET PIEDE = ? WHERE codfisc LIKE ?");
            updateStatement.setString(1, nuovoPiede);
            updateStatement.setString(2, codFisc);
            updateStatement.executeUpdate();
            updateStatement.close();
            connection.close();
        }catch(SQLException e){
            e.printStackTrace();
        } catch (LoginFailedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void modificaDataDiNascitaGiocatore(String codFisc, LocalDate nuovaDataNascita) {
        try{
            connection = ConnessioneDB.getInstance(login, password).connection;
            PreparedStatement updateStatement = connection.prepareStatement("UPDATE progetto.Giocatore SET DATANASCITA = ? WHERE codfisc LIKE ?");
            updateStatement.setObject(1, nuovaDataNascita);
            updateStatement.setString(2, codFisc);
            updateStatement.executeUpdate();
            updateStatement.close();
            connection.close();
        }catch(SQLException | LoginFailedException e){
            e.printStackTrace();
        }
    }
    public void modificaDataRitiroGiocatore(String codFisc, LocalDate nuovaDataRitiro){
        try{
            connection = ConnessioneDB.getInstance(login, password).connection;
            PreparedStatement updateStatement = connection.prepareStatement("UPDATE progetto.Giocatore SET DATARITIRO = ? WHERE codfisc LIKE ?");
            updateStatement.setObject(1, nuovaDataRitiro);
            updateStatement.setString(2, codFisc);
            updateStatement.executeUpdate();
            updateStatement.close();
            connection.close();
        }catch(SQLException e){
            e.printStackTrace();
        } catch (LoginFailedException e) {
            throw new RuntimeException(e);
        }
    }

    public void modificaNomeTrofeoIndividuale(String nome, String anno, String nuovoNome){
        try{
            connection = ConnessioneDB.getInstance(login, password).connection;
            PreparedStatement updateStatement = connection.prepareStatement("UPDATE progetto.TrofeoIndividuale SET NOME = ? WHERE NOME LIKE ? AND ANNO LIKE ?");
            updateStatement.setString(1, nuovoNome);
            updateStatement.setString(2, nome);
            updateStatement.setString(3, anno);
            updateStatement.executeUpdate();
            updateStatement.close();
            connection.close();
        }catch(SQLException | LoginFailedException e){
            e.printStackTrace();
        }
    }
    public void modificaAnnoTrofeoIndividuale(String nome, String anno, String nuovoAnno){
        try{
            connection = ConnessioneDB.getInstance(login, password).connection;
            PreparedStatement updateStatement = connection.prepareStatement("UPDATE progetto.TrofeoIndividuale SET ANNO = ? WHERE NOME LIKE ? AND ANNO LIKE ?");
            updateStatement.setString(1, nuovoAnno);
            updateStatement.setString(2, nome);
            updateStatement.setString(3, anno);
            updateStatement.executeUpdate();
            updateStatement.close();
            connection.close();
        }catch(SQLException e){
            e.printStackTrace();
        } catch (LoginFailedException e) {
            throw new RuntimeException(e);
        }
    }

    public void modificaMeritoTrofeoIndividuale(String nome, String anno, String nuovoMerito){
        try{
            connection = ConnessioneDB.getInstance(login, password).connection;
            PreparedStatement updateStatement = connection.prepareStatement("UPDATE progetto.TrofeoIndividuale SET MERITO = ? WHERE NOME LIKE ? AND ANNO LIKE ?");
            updateStatement.setString(1, nuovoMerito);
            updateStatement.setString(2, nome);
            updateStatement.setString(3, anno);
            updateStatement.executeUpdate();
            updateStatement.close();
            connection.close();
        }catch(SQLException e){
            e.printStackTrace();
        } catch (LoginFailedException e) {
            throw new RuntimeException(e);
        }
    }

    public void modificaGiocatoreTrofeoIndividuale(String nome, String anno, String nuovoCodF){
        try{
            connection = ConnessioneDB.getInstance(login, password).connection;
            PreparedStatement updateStatement = connection.prepareStatement("UPDATE progetto.TrofeoIndividuale SET CODF = ? WHERE NOME LIKE ? AND ANNO LIKE ?");
            updateStatement.setString(1, nuovoCodF);
            updateStatement.setString(2, nome);
            updateStatement.setString(3, anno);
            updateStatement.executeUpdate();
            updateStatement.close();
            connection.close();
        }catch(SQLException | LoginFailedException e){
            e.printStackTrace();
        }
    }

    public void inserisciTrofeoIndividuale(String nome, String anno, String merito, String codF){
        try{
            connection = ConnessioneDB.getInstance(login, password).connection;
            PreparedStatement insertStatement = connection.prepareStatement("INSERT INTO progetto.TROFEOINDIVIDUALE VALUES (?, ?, ?, ?, ?)");
            insertStatement.setString(1, nome);
            insertStatement.setString(2, anno);
            insertStatement.setString(3, merito);
            insertStatement.setString(4, codF);
            insertStatement.executeUpdate();
            insertStatement.close();
            connection.close();
        }catch(SQLException | LoginFailedException e){
            e.printStackTrace();
        }
    }
    public void modificaNomeTrofeoSquadra(String nome, String anno, String nuovoNome){
        try{
            connection = ConnessioneDB.getInstance(login, password).connection;
            PreparedStatement updateStatement = connection.prepareStatement("UPDATE progetto.TrofeoDiSquadra SET NOME = ? WHERE NOME LIKE ? AND ANNO LIKE ?");
            updateStatement.setString(1, nuovoNome);
            updateStatement.setString(2, nome);
            updateStatement.setString(3, anno);
            updateStatement.executeUpdate();
            updateStatement.close();
            connection.close();
        }catch(SQLException | LoginFailedException e){
            e.printStackTrace();
        }
    }
    public void modificaAnnoTrofeoSquadra(String nome, String anno, String nuovoAnno){
        try{
            connection = ConnessioneDB.getInstance(login, password).connection;
            PreparedStatement updateStatement = connection.prepareStatement("UPDATE progetto.TrofeoDiSquadra SET ANNO = ? WHERE NOME LIKE ? AND ANNO LIKE ?");
            updateStatement.setString(1, nuovoAnno);
            updateStatement.setString(2, nome);
            updateStatement.setString(3, anno);
            updateStatement.executeUpdate();
            updateStatement.close();
            connection.close();
        }catch(SQLException | LoginFailedException e){
            e.printStackTrace();
        }
    }
    public void modificaMeritoTrofeoSquadra(String nome, String anno, String nuovoMerito){
        try{
            connection = ConnessioneDB.getInstance(login, password).connection;
            PreparedStatement updateStatement = connection.prepareStatement("UPDATE progetto.TrofeoDiSquadra SET MERITO = ? WHERE NOME LIKE ? AND ANNO LIKE ?");
            updateStatement.setString(1, nuovoMerito);
            updateStatement.setString(2, nome);
            updateStatement.setString(3, anno);
            updateStatement.executeUpdate();
            updateStatement.close();
            connection.close();
        }catch(SQLException | LoginFailedException e){
            e.printStackTrace();
        }
    }
    public void modificaSquadraTrofeoSquadra(String nome, String anno, String nuovoNomeSquadra, String nuovaNazionalitaSquadra){
        try{
            connection = ConnessioneDB.getInstance(login, password).connection;
            PreparedStatement updateStatement = connection.prepareStatement("UPDATE progetto.TrofeoDiSquadra SET NOMESQUADRA = ?, NAZIONALITASQUADRA = ? WHERE NOME LIKE ? AND ANNO LIKE ?");
            updateStatement.setString(1, nuovoNomeSquadra);
            updateStatement.setString(2, nome);
            updateStatement.setString(3, anno);
            updateStatement.executeUpdate();
            updateStatement.close();
            connection.close();
        }catch(SQLException | LoginFailedException e){
            e.printStackTrace();
        }
    }

    public void inserisciSquadra(String nome, String nazionalita, int idCampionato){
        try{
            connection = ConnessioneDB.getInstance(login, password).connection;
            PreparedStatement insertStatement = connection.prepareStatement("INSERT INTO progetto.SQUADRA VALUES (?, ?, ?)");
            insertStatement.setString(1, nome);
            insertStatement.setString(2, nazionalita);
            insertStatement.setInt(3, idCampionato);
            insertStatement.executeUpdate();
            insertStatement.close();
            connection.close();
        }catch(SQLException | LoginFailedException e){
            e.printStackTrace();
        }
    }

    public void modificaNomeSquadra(String nome, String nazionalita, String nuovoNome){
        try{
            connection = ConnessioneDB.getInstance(login, password).connection;
            PreparedStatement updateStatement = connection.prepareStatement("UPDATE progetto.SQUADRA SET NOME = ? WHERE NOME LIKE ? AND NAZIONALITA LIKE ?");
            updateStatement.setString(1, nuovoNome);
            updateStatement.setString(2, nome);
            updateStatement.setString(3, nazionalita);
            updateStatement.executeUpdate();
            updateStatement.close();
            connection.close();
        }catch(SQLException | LoginFailedException e){
            e.printStackTrace();
        }
    }

    public void modificaNazionalitaSquadra(String nome, String nazionalita, String nuovaNazionalita){
        try{
            connection = ConnessioneDB.getInstance(login, password).connection;
            PreparedStatement updateStatement = connection.prepareStatement("UPDATE progetto.SQUADRA SET NAZIONALITA = ? WHERE NOME LIKE ? AND NAZIONALITA LIKE ?");
            updateStatement.setString(1, nuovaNazionalita);
            updateStatement.setString(2, nome);
            updateStatement.setString(3, nazionalita);
            updateStatement.executeUpdate();
            updateStatement.close();
            connection.close();
        }catch(SQLException | LoginFailedException e){
            e.printStackTrace();
        }
    }
    public void modificaCampionatoSquadra(String nome, String nazionalita, int nuovoIdCampionato){
        try{
            connection = ConnessioneDB.getInstance(login, password).connection;
            PreparedStatement updateStatement = connection.prepareStatement("UPDATE progetto.SQUADRA SET IDCAMPIONATO = ? WHERE NOME LIKE ? AND NAZIONALITA LIKE ?");
            updateStatement.setInt(1, nuovoIdCampionato);
            updateStatement.setString(2, nome);
            updateStatement.setString(3, nazionalita);
            updateStatement.executeUpdate();
            updateStatement.close();
            connection.close();
        }catch(SQLException | LoginFailedException e){
            e.printStackTrace();
        }
    }

    public void aggiungiCaratteristica(String tipoCaratteristica){
        try{
            connection = ConnessioneDB.getInstance(login, password).connection;
            PreparedStatement insertStatement = connection.prepareStatement("INSERT INTO progetto.CARATTERISTICA VALUES (?)");
            insertStatement.setString(1, tipoCaratteristica);
            insertStatement.executeUpdate();
            insertStatement.close();
            connection.close();
        }catch(SQLException | LoginFailedException e){
            e.printStackTrace();
        }
    }

    public void aggiungiCaratteristicaGiocatore(String codFisc, String caratteristica){
        try{
            connection = ConnessioneDB.getInstance(login, password).connection;
            PreparedStatement insertStatement = connection.prepareStatement("INSERT INTO progetto.POSSIEDE VALUES (?, ?)");
            insertStatement.setString(1, codFisc);
            insertStatement.setString(2, caratteristica);
            insertStatement.executeUpdate();
            insertStatement.close();
            connection.close();
        }catch(SQLException | LoginFailedException e){
            e.printStackTrace();
        }
    }

    public void insertCampionato(int idCampionato, String nome, String anno){
        try{
            connection = ConnessioneDB.getInstance(login, password).connection;
            PreparedStatement insertStatement = connection.prepareStatement("INSERT INTO progetto.CAMPIONATO VALUES (?, ?, ?)");
            insertStatement.setInt(1, idCampionato);
            insertStatement.setString(2, nome);
            insertStatement.setString(3, anno);
            insertStatement.executeUpdate();
            insertStatement.close();
            connection.close();
        }catch(SQLException | LoginFailedException e){
            e.printStackTrace();
        }
    }

    public void modificaIdCampionato(int idCampionato, int idNuovo){
        try{
            connection = ConnessioneDB.getInstance(login, password).connection;
            PreparedStatement updateStatement = connection.prepareStatement("UPDATE progetto.CAMPIONATO SET IDCAMPIONATO = ? WHERE IDCAMPIONATO = ?");
            updateStatement.setInt(1, idNuovo);
            updateStatement.setInt(2, idCampionato);
            updateStatement.executeUpdate();
            updateStatement.close();
            connection.close();
        }catch(SQLException | LoginFailedException e){
            e.printStackTrace();
        }
    }

    public void modificaNomeCampionato(int idCampionato, String nuovoNome){
        try{
            connection = ConnessioneDB.getInstance(login, password).connection;
            PreparedStatement updateStatement = connection.prepareStatement("UPDATE progetto.CAMPIONATO SET NOME = ? WHERE IDCAMPIONATO = ?");
            updateStatement.setString(1, nuovoNome);
            updateStatement.setInt(2, idCampionato);
            updateStatement.executeUpdate();
            updateStatement.close();
            connection.close();
        }catch(SQLException e){
            e.printStackTrace();
        } catch (LoginFailedException e) {
            throw new RuntimeException(e);
        }
    }

    public void modificaAnnoCampionato(int idCampionato, String nuovoAnno){
        try{
            connection = ConnessioneDB.getInstance(login, password).connection;
            PreparedStatement updateStatement = connection.prepareStatement("UPDATE progetto.CAMPIONATO SET ANNO = ? WHERE IDCAMPIONATO = ?");
            updateStatement.setString(1, nuovoAnno);
            updateStatement.setInt(2, idCampionato);
            updateStatement.executeUpdate();
            updateStatement.close();
            connection.close();
        }catch(SQLException | LoginFailedException e){
            e.printStackTrace();
        }
    }

    public void inserisciMilitanza(String codFisc, String nomeSquadra, String nazionalitaSquadra, LocalDate dataInizio, LocalDate dataFine,
                                   String ruolo, int partiteGiocate, int golEffettuati, int golSubiti, int ammonizioni, int espulsioni){

        try{
            connection = ConnessioneDB.getInstance(login, password).connection;
            CallableStatement callableStatement = connection.prepareCall("call progetto.creamilitanza(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            callableStatement.setString(1, codFisc);
            callableStatement.setString(2, nomeSquadra);
            callableStatement.setString(3, nazionalitaSquadra);
            callableStatement.setObject(4, dataInizio);
            callableStatement.setObject(5, dataFine);
            callableStatement.setString(6, ruolo);
            callableStatement.setInt(7, partiteGiocate);
            callableStatement.setInt(8, golEffettuati);
            callableStatement.setInt(9, golSubiti);
            callableStatement.setInt(10, ammonizioni);
            callableStatement.setInt(11, espulsioni);
            callableStatement.execute();
            callableStatement.close();
            connection.close();
        }catch(SQLException e){
            e.printStackTrace();
        } catch (LoginFailedException e) {
            throw new RuntimeException(e);
        }
    }

    public void modificaGiocatoreMilitanza(String codFisc, String nomeSquadra, String nazionalitaSquadra, LocalDate dataInizio, String nuovoCodFisc) {
        try{
            connection = ConnessioneDB.getInstance(login, password).connection;
            PreparedStatement updateStatement = connection.prepareStatement("UPDATE progetto.Milita SET codFisc = ? WHERE CODFISC LIKE ? AND NOMESQUADRA LIKE ? AND NAZIONALITASQUADRA LIKE ? AND DATAINIZIO = ?");
            updateStatement.setString(1, nuovoCodFisc);
            updateStatement.setString(2, codFisc);
            updateStatement.setString(3, nomeSquadra);
            updateStatement.setString(4, nazionalitaSquadra);
            updateStatement.setObject(5, dataInizio);
            updateStatement.executeUpdate();
            updateStatement.close();
            connection.close();
        }catch(SQLException e){
            e.printStackTrace();
        } catch (LoginFailedException e) {
            throw new RuntimeException(e);
        }
    }

    public void modificaSquadraMilitanza(String codFisc, String nomeSquadra, String nazionalitaSquadra, LocalDate dataInizio, String nuovoNomeSquadra, String nuovaNazionalitaSquadra){
        try{
            connection = ConnessioneDB.getInstance(login, password).connection;
            PreparedStatement updateStatement = connection.prepareStatement("UPDATE progetto.Milita SET NOMESQUADRA = ?, NAZIONALITASQUADRA = ? WHERE CODFISC LIKE ? AND NOMESQUADRA LIKE ? AND NAZIONALITASQUADRA LIKE ? AND DATAINIZIO = ?");
            updateStatement.setString(1, nuovoNomeSquadra);
            updateStatement.setString(2, nuovaNazionalitaSquadra);
            updateStatement.setString(3, codFisc);
            updateStatement.setString(4, nomeSquadra);
            updateStatement.setString(5, nazionalitaSquadra);
            updateStatement.setObject(6, dataInizio);
            updateStatement.executeUpdate();
            updateStatement.close();
            connection.close();
        }catch(SQLException e){
            e.printStackTrace();
        } catch (LoginFailedException e) {
            throw new RuntimeException(e);
        }
    }

    public void modificaDataInizioMilitanza(String codFisc, String nomeSquadra, String nazionalitaSquadra, LocalDate dataInizio, LocalDate nuovaDataInizio) {
        try{
            connection = ConnessioneDB.getInstance(login, password).connection;
            PreparedStatement updateStatement = connection.prepareStatement("UPDATE progetto.Milita SET DATAINIZIO = ? WHERE CODFISC LIKE ? AND NOMESQUADRA LIKE ? AND NAZIONALITASQUADRA LIKE ? AND DATAINIZIO = ?");
            updateStatement.setObject(1, nuovaDataInizio);
            updateStatement.setString(2, codFisc);
            updateStatement.setString(3, nomeSquadra);
            updateStatement.setString(4, nazionalitaSquadra);
            updateStatement.setObject(5, dataInizio);
            updateStatement.executeUpdate();
            updateStatement.close();
            connection.close();
        }catch(SQLException e){
            e.printStackTrace();
        } catch (LoginFailedException e) {
            throw new RuntimeException(e);
        }
    }

    public void modificaDataFineMilitanza(String codFisc, String nomeSquadra, String nazionalitaSquadra, LocalDate dataInizio, LocalDate nuovaDataFine) {
        try{
            connection = ConnessioneDB.getInstance(login, password).connection;
            PreparedStatement updateStatement = connection.prepareStatement("UPDATE progetto.Milita SET DATAFINE = ? WHERE CODFISC LIKE ? AND NOMESQUADRA LIKE ? AND NAZIONALITASQUADRA LIKE ? AND DATAINIZIO = ?");
            updateStatement.setObject(1, nuovaDataFine);
            updateStatement.setString(2, codFisc);
            updateStatement.setString(3, nomeSquadra);
            updateStatement.setString(4, nazionalitaSquadra);
            updateStatement.setObject(5, dataInizio);
            updateStatement.executeUpdate();
            updateStatement.close();
            connection.close();
        }catch(SQLException e){
            e.printStackTrace();
        } catch (LoginFailedException e) {
            throw new RuntimeException(e);
        }
    }

    public void modificaRuoloMilitanza(String codFisc, String nomeSquadra, String nazionalitaSquadra, LocalDate dataInizio, String nuovoRuolo) {
        try{
            connection = ConnessioneDB.getInstance(login, password).connection;
            PreparedStatement updateStatement = connection.prepareStatement("UPDATE progetto.Milita SET RUOLO = ? WHERE CODFISC LIKE ? AND NOMESQUADRA LIKE ? AND NAZIONALITASQUADRA LIKE ? AND DATAINIZIO = ?");
            updateStatement.setString(1, nuovoRuolo);
            updateStatement.setString(2, codFisc);
            updateStatement.setString(3, nomeSquadra);
            updateStatement.setString(4, nazionalitaSquadra);
            updateStatement.setObject(5, dataInizio);
            updateStatement.executeUpdate();
            updateStatement.close();
            connection.close();
        }catch(SQLException e){
            e.printStackTrace();
        } catch (LoginFailedException e) {
            throw new RuntimeException(e);
        }
    }

    public void modificaPartiteGiocateMilitanza(String codFisc, String nomeSquadra, String nazionalitaSquadra, LocalDate dataInizio, int nuovePartiteGiocate) {
        try{
            connection = ConnessioneDB.getInstance(login, password).connection;
            PreparedStatement updateStatement = connection.prepareStatement("UPDATE progetto.Milita SET PARTITEGIOCATE = ? WHERE CODFISC LIKE ? AND NOMESQUADRA LIKE ? AND NAZIONALITASQUADRA LIKE ? AND DATAINIZIO = ?");
            updateStatement.setInt(1, nuovePartiteGiocate);
            updateStatement.setString(2, codFisc);
            updateStatement.setString(3, nomeSquadra);
            updateStatement.setString(4, nazionalitaSquadra);
            updateStatement.setObject(5, dataInizio);
            updateStatement.executeUpdate();
            updateStatement.close();
            connection.close();
        }catch(SQLException e){
            e.printStackTrace();
        } catch (LoginFailedException e) {
            throw new RuntimeException(e);
        }
    }

    public void modificaGolEffettuatiMilitanza(String codFisc, String nomeSquadra, String nazionalitaSquadra, LocalDate dataInizio, int nuoviGolEffettuati) {
        try{
            connection = ConnessioneDB.getInstance(login, password).connection;
            PreparedStatement updateStatement = connection.prepareStatement("UPDATE progetto.Milita SET GOLEFFETTUATI = ? WHERE CODFISC LIKE ? AND NOMESQUADRA LIKE ? AND NAZIONALITASQUADRA LIKE ? AND DATAINIZIO = ?");
            updateStatement.setInt(1, nuoviGolEffettuati);
            updateStatement.setString(2, codFisc);
            updateStatement.setString(3, nomeSquadra);
            updateStatement.setString(4, nazionalitaSquadra);
            updateStatement.setObject(5, dataInizio);
            updateStatement.executeUpdate();
            updateStatement.close();
            connection.close();
        }catch(SQLException e){
            e.printStackTrace();
        } catch (LoginFailedException e) {
            throw new RuntimeException(e);
        }
    }

    public void modificaGolSubitiMilitanza(String codFisc, String nomeSquadra, String nazionalitaSquadra, LocalDate dataInizio, int nuoviGolSubiti) {
        try{
            connection = ConnessioneDB.getInstance(login, password).connection;
            PreparedStatement updateStatement = connection.prepareStatement("UPDATE progetto.Milita SET GOLSUBITI = ? WHERE CODFISC LIKE ? AND NOMESQUADRA LIKE ? AND NAZIONALITASQUADRA LIKE ? AND DATAINIZIO = ?");
            updateStatement.setInt(1, nuoviGolSubiti);
            updateStatement.setString(2, codFisc);
            updateStatement.setString(3, nomeSquadra);
            updateStatement.setString(4, nazionalitaSquadra);
            updateStatement.setObject(5, dataInizio);
            updateStatement.executeUpdate();
            updateStatement.close();
            connection.close();
        }catch(SQLException e){
            e.printStackTrace();
        } catch (LoginFailedException e) {
            throw new RuntimeException(e);
        }
    }

    public void modificaAmmonizioniMilitanza(String codFisc, String nomeSquadra, String nazionalitaSquadra, LocalDate dataInizio, int nuoveAmmonizioni) {
        try{
            connection = ConnessioneDB.getInstance(login, password).connection;
            PreparedStatement updateStatement = connection.prepareStatement("UPDATE progetto.Milita SET AMMONIZIONI = ? WHERE CODFISC LIKE ? AND NOMESQUADRA LIKE ? AND NAZIONALITASQUADRA LIKE ? AND DATAINIZIO = ?");
            updateStatement.setInt(1, nuoveAmmonizioni);
            updateStatement.setString(2, codFisc);
            updateStatement.setString(3, nomeSquadra);
            updateStatement.setString(4, nazionalitaSquadra);
            updateStatement.setObject(5, dataInizio);
            updateStatement.executeUpdate();
            updateStatement.close();
            connection.close();
        }catch(SQLException e){
            e.printStackTrace();
        } catch (LoginFailedException e) {
            throw new RuntimeException(e);
        }
    }

    public void modificaEspulsioniMilitanza(String codFisc, String nomeSquadra, String nazionalitaSquadra, LocalDate dataInizio, int nuoveEspulsioni) {
        try{
            connection = ConnessioneDB.getInstance(login, password).connection;
            PreparedStatement updateStatement = connection.prepareStatement("UPDATE progetto.Milita SET ESPULSIONI = ? WHERE CODFISC LIKE ? AND NOMESQUADRA LIKE ? AND NAZIONALITASQUADRA LIKE ? AND DATAINIZIO = ?");
            updateStatement.setInt(1, nuoveEspulsioni);
            updateStatement.setString(2, codFisc);
            updateStatement.setString(3, nomeSquadra);
            updateStatement.setString(4, nazionalitaSquadra);
            updateStatement.setObject(5, dataInizio);
            updateStatement.executeUpdate();
            updateStatement.close();
            connection.close();
        }catch(SQLException e){
            e.printStackTrace();
        } catch (LoginFailedException e) {
            throw new RuntimeException(e);
        }
    }


    public boolean rimuoviGiocatore(String codFisc){
        try{
            connection = ConnessioneDB.getInstance(login, password).connection;
            PreparedStatement removeStatement = connection.prepareStatement("DELETE FROM progetto.GIOCATORE WHERE CODFISC LIKE ?");
            removeStatement.setString(1, codFisc);
            removeStatement.executeUpdate();
            removeStatement.close();
            connection.close();
        }catch(SQLException | LoginFailedException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public void rimuoviCaratteristicaGiocatore(String codFisc, String caratteristica){
        try{
            connection = ConnessioneDB.getInstance(login, password).connection;
            PreparedStatement removeStatement = connection.prepareStatement("DELETE FROM progetto.POSSIEDE WHERE CODFISC LIKE ? AND CARATTERISTICA LIKE ?");
            removeStatement.setString(1, codFisc);
            removeStatement.setString(2, caratteristica);
            removeStatement.executeUpdate();
            removeStatement.close();
            connection.close();
        }catch(SQLException e){
            e.printStackTrace();
        } catch (LoginFailedException e) {
            throw new RuntimeException(e);
        }
    }

    public void rimuoviCaratteristica(String tipoCaratteristica){
        try{
            connection = ConnessioneDB.getInstance(login, password).connection;
            PreparedStatement removeStatement = connection.prepareStatement("DELETE FROM progetto.CARATTERISTICA WHERE TIPOCARATTERISTICA LIKE ?");
            removeStatement.setString(1, tipoCaratteristica);
            removeStatement.executeUpdate();
            removeStatement.close();
            connection.close();
        }catch(SQLException e){
            e.printStackTrace();
        } catch (LoginFailedException e) {
            throw new RuntimeException(e);
        }
    }

    public void rimuoviMilitanza(String codFisc, String nomeSquadra, String nazionalitaSquadra, LocalDate dataInizio){
        try{
            connection = ConnessioneDB.getInstance(login, password).connection;
            PreparedStatement removeStatement = connection.prepareStatement("DELETE FROM progetto.MILITA WHERE CODFISC LIKE ? AND NOMESQUADRA LIKE ? AND NAZIONALITASQUADRA LIKE ? AND DATAINIZIO = ?");
            removeStatement.setString(1, codFisc);
            removeStatement.setString(2, nomeSquadra);
            removeStatement.setString(3, nazionalitaSquadra);
            removeStatement.setObject(4, dataInizio);
            removeStatement.executeUpdate();
            removeStatement.close();
            connection.close();
        }catch(SQLException e){
            e.printStackTrace();
        } catch (LoginFailedException e) {
            throw new RuntimeException(e);
        }
    }

    public void rimuoviSquadra(String nomeSquadra, String nazionalitaSquadra){
        try{
            connection = ConnessioneDB.getInstance(login, password).connection;
            PreparedStatement removeStatement = connection.prepareStatement("DELETE FROM progetto.SQUADRA WHERE NOMESQUADRA LIKE ? AND NAZIONALITASQUADRA LIKE ?");
            removeStatement.setString(1, nomeSquadra);
            removeStatement.setObject(2, nazionalitaSquadra);
            removeStatement.executeUpdate();
            removeStatement.close();
            connection.close();
        }catch(SQLException e){
            e.printStackTrace();
        } catch (LoginFailedException e) {
            throw new RuntimeException(e);
        }
    }

    public void rimuoviTrofeoSquadra(String nome, String anno){
        try{
            connection = ConnessioneDB.getInstance(login, password).connection;
            PreparedStatement removeStatement = connection.prepareStatement("DELETE FROM progetto.TROFEOSQUADRA WHERE NOME LIKE ? AND ANNO LIKE ?");
            removeStatement.setString(1, nome);
            removeStatement.setObject(2, anno);
            removeStatement.executeUpdate();
            removeStatement.close();
            connection.close();
        }catch(SQLException e){
            e.printStackTrace();
        } catch (LoginFailedException e) {
            throw new RuntimeException(e);
        }
    }
    public void rimuoviTrofeoIndividuale(String nome, String anno){
        try{
            connection = ConnessioneDB.getInstance(login, password).connection;
            PreparedStatement removeStatement = connection.prepareStatement("DELETE FROM progetto.TROFEOINDIVIDUALE WHERE NOME LIKE ? AND ANNO LIKE ?");
            removeStatement.setString(1, nome);
            removeStatement.setObject(2, anno);
            removeStatement.executeUpdate();
            removeStatement.close();
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
