package DAO;

import java.time.LocalDate;
import java.util.ArrayList;

public interface AdminDAO extends UtenteDAO {
    public boolean inserisciGiocatore(String nome, String cognome, String codFisc, String piede, LocalDate dataDiNascita);
    public void modificaNomeGiocatore(String codFisc, String nuovoNome);
    public void modificaCognomeGiocatore(String codFisc, String nuovoCognome);
    public void modificaCodiceFiscaleGiocatore(String codFisc, String nuovoCodFisc);
    public void modificaPiedeGiocatore(String codFisc, String nuovoPiede);
    public void modificaDataDiNascitaGiocatore(String codFisc, LocalDate nuovaDataNascita);
    public void modificaDataRitiroGiocatore(String codFisc, LocalDate nuovaDataRitiro);
    public void inserisciTrofeoIndividuale(String nome, String anno, String merito, String codF);
    public void modificaNomeTrofeoIndividuale(String nome, String anno, String nuovoNome);
    public void modificaAnnoTrofeoIndividuale(String nome, String anno, String nuovoAnno);
    public void modificaMeritoTrofeoIndividuale(String nome, String anno, String nuovoMerito);
    public void modificaGiocatoreTrofeoIndividuale(String nome, String anno, String nuovoCodF);
    public void modificaNomeTrofeoSquadra(String nome, String anno, String nuovoNome);
    public void modificaAnnoTrofeoSquadra(String nome, String anno, String nuovoAnno);
    public void modificaMeritoTrofeoSquadra(String nome, String anno, String nuovoMerito);
    public void modificaSquadraTrofeoSquadra(String nome, String anno, String nuovoNomeSquadra, String nuovaNazionalitaSquadra);
    public void inserisciSquadra(String nome, String nazionalita, int idCampionato);
    public void modificaNomeSquadra(String nome, String nazionalita, String nuovoNome);
    public void modificaNazionalitaSquadra(String nome, String nazionalita, String nuovaNazionalita);
    public void modificaCampionatoSquadra(String nome, String nazionalita, int nuovoIdCampionato);
    public void aggiungiCaratteristica(String tipoCaratteristica);
    public void aggiungiCaratteristicaGiocatore(String codFisc, String caratteristica);
    public void insertCampionato(int idCampionato, String nome, String anno);
    public void modificaIdCampionato(int idCampionato, int idNuovo);
    public void modificaNomeCampionato(int idCampionato, String nuovoNome);
    public void modificaAnnoCampionato(int idCampionato, String nuovoAnno);
    public void inserisciMilitanza(String codFisc, String nomeSquadra, String nazionalitaSquadra, LocalDate dataInizio,
                                   LocalDate dataFine, String ruolo, int partiteGiocate, int golEffettuati, int golSubiti,
                                   int ammonizioni, int espulsioni);
    public void modificaGiocatoreMilitanza(String codFisc, String nomeSquadra, String nazionalitaSquadra, LocalDate dataInizio, String nuovoCodFisc);
    public void modificaSquadraMilitanza(String codFisc, String nomeSquadra, String nazionalitaSquadra, LocalDate dataInizio, String nuovoNomeSquadra, String nuovaNazionalitaSquadra);
    public void modificaDataInizioMilitanza(String codFisc, String nomeSquadra, String nazionalitaSquadra, LocalDate dataInizio, LocalDate nuovaDataInizio);
    public void modificaDataFineMilitanza(String codFisc, String nomeSquadra, String nazionalitaSquadra, LocalDate dataInizio, LocalDate nuovaDataFine);
    public void modificaRuoloMilitanza(String codFisc, String nomeSquadra, String nazionalitaSquadra, LocalDate dataInizio, String nuovoRuolo);
    public void modificaPartiteGiocateMilitanza(String codFisc, String nomeSquadra, String nazionalitaSquadra, LocalDate dataInizio, int nuovePartiteGiocate);
    public void modificaGolEffettuatiMilitanza(String codFisc, String nomeSquadra, String nazionalitaSquadra, LocalDate dataInizio, int nuoviGolEffettuati);
    public void modificaGolSubitiMilitanza(String codFisc, String nomeSquadra, String nazionalitaSquadra, LocalDate dataInizio, int nuoviGolSubiti);
    public void modificaAmmonizioniMilitanza(String codFisc, String nomeSquadra, String nazionalitaSquadra, LocalDate dataInizio, int nuoveAmmonizioni);
    public void modificaEspulsioniMilitanza(String codFisc, String nomeSquadra, String nazionalitaSquadra, LocalDate dataInizio, int nuoveEspulsioni);
    public boolean rimuoviGiocatore(String codFisc);
    public void rimuoviCaratteristicaGiocatore(String codFisc, String caratteristica);
    public void rimuoviCaratteristica(String tipoCaratteristica);
    public void rimuoviMilitanza(String codFisc, String nomeSquadra, String nazionalitaSquadra, LocalDate dataInizio);
    public void rimuoviSquadra(String nomeSquadra, String nazionalitaSquadra);
    public void rimuoviTrofeoSquadra(String nome, String anno);
    public void rimuoviTrofeoIndividuale(String nome, String anno);
}
