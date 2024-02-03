package Controller;

import Model.*;
import PostgresDAO.AdminImplementazioneDAO;
import PostgresDAO.RegistrazioneImplementazioneDAO;

import java.time.LocalDate;
import java.util.ArrayList;

public class ControllerAdmin {

    AdminImplementazioneDAO admin;
    ArrayList<Giocatore> listaGiocatori = new ArrayList<>();
    ArrayList<Campionato> listaCampionati = new ArrayList<>();
    ArrayList<Squadra> listaSquadre = new ArrayList<>();
    ArrayList<TrofeoSquadra> listaTrofeiSquadra = new ArrayList<>();
    ArrayList<TrofeoIndividuale> listaTrofeiIndividuali = new ArrayList<>();
    ArrayList<Militanza> listaMilitanze = new ArrayList<>();

    public boolean login(String login, String password) {
        admin = new AdminImplementazioneDAO(login, password);
        if(admin.login()) {
            if (admin.checkAdmin()) {
                buildModelFromDB();
                return true;
            } else {
                return false;
            }
        } else{
            return false;
        }
    }

    public void buildModelFromDB(){
        buildGiocatoriFromDB();
        builCampionatiFromDB();
        buildSquadreFromDB();
        buildTrofeiIndividualiFromDB();
        buildTrofeiSquadraFromDB();
        buildMilitanzeFromDB();
    }

    public void buildGiocatoriFromDB() {
        ArrayList<String> listaNomi = new ArrayList<>();
        ArrayList<String> listaCognomi = new ArrayList<>();
        ArrayList<String> listaCodFisc = new ArrayList<>();
        ArrayList<String> listaPiedi = new ArrayList<>();
        ArrayList<LocalDate> listaDateNascita = new ArrayList<>();
        ArrayList<LocalDate> listaDateRitiro = new ArrayList<>();
        ArrayList<ArrayList<String>> listaListaCaratteristiche = new ArrayList<>();


        admin.fetchGiocatoriFromDB(listaNomi, listaCognomi, listaCodFisc, listaPiedi,
                listaDateNascita, listaDateRitiro, listaListaCaratteristiche);

        for(int i = 0; i < listaCodFisc.size(); i++){
            this.listaGiocatori.add(new Giocatore(listaNomi.get(i), listaCognomi.get(i), listaCodFisc.get(i), listaDateNascita.get(i),
                    listaPiedi.get(i), listaDateRitiro.get(i), listaListaCaratteristiche.get(i)));
        }
    }

    public void builCampionatiFromDB(){
        ArrayList<Integer> listaId = new ArrayList<>();
        ArrayList<String> listaNomi = new ArrayList<>();
        ArrayList<String> listaAnni = new ArrayList<>();

        admin.fetchCampionatiFromDB(listaId, listaNomi, listaAnni);

        for(int i = 0; i < listaId.size(); i++){
            this.listaCampionati.add(new Campionato(listaNomi.get(i), listaAnni.get(i), listaId.get(i)));

        }
    }

    public void buildSquadreFromDB(){
        ArrayList<String> listaNomi = new ArrayList<>();
        ArrayList<String> listaNazionalita = new ArrayList<>();
        ArrayList<Integer> listaId = new ArrayList<>();

        admin.fetchSquadreFromDB(listaNomi, listaNazionalita, listaId);

        for(int i = 0; i < listaNomi.size(); i++){
            this.listaSquadre.add(new Squadra(listaNomi.get(i), listaNazionalita.get(i), this.getCampionatoFromID(listaId.get(i))));
        }
    }

    private Campionato getCampionatoFromID(int id){
        for(Campionato c : listaCampionati){
            if(c.getIdCampionato() == id){
                return c;
            }
        }
        return null;
    }

    public void buildTrofeiSquadraFromDB(){
        ArrayList<String> listaNomi = new ArrayList<>();
        ArrayList<String> listaAnni = new ArrayList<>();
        ArrayList<String> listaMeriti = new ArrayList<>();
        ArrayList<String> listaNomiSquadra = new ArrayList<>();
        ArrayList<String> listaNazionalitaSquadra = new ArrayList<>();

        admin.fetchTrofeiDiSquadraFromDB(listaNomi, listaAnni, listaMeriti, listaNomiSquadra, listaNazionalitaSquadra);

        for(int i = 0; i < listaNomi.size(); i++){
            this.listaTrofeiSquadra.add(new TrofeoSquadra(listaNomi.get(i), listaAnni.get(i),
                    listaMeriti.get(i), getSquadraFromPK(listaNomiSquadra.get(i), listaNazionalitaSquadra.get(i))));
        }

    }

    private Squadra getSquadraFromPK(String nome, String nazionalita) {
        for(Squadra s : listaSquadre){
            if(s.getNome().equals(nome) && s.getNazionalita().equals(nazionalita)){
                return s;
            }
        }
        return null;
    }

    public void buildTrofeiIndividualiFromDB(){
        ArrayList<String> listaNomi = new ArrayList<>();
        ArrayList<String> listaAnni = new ArrayList<>();
        ArrayList<String> listaMeriti = new ArrayList<>();
        ArrayList<String> listaCodFisc = new ArrayList<>();

        admin.fetchTrofeiIndividiualiFromDB(listaNomi, listaAnni, listaMeriti, listaCodFisc);

        for(int i = 0; i < listaNomi.size(); i++){
            this.listaTrofeiIndividuali.add(new TrofeoIndividuale(listaNomi.get(i), listaAnni.get(i),
                    listaMeriti.get(i), getGiocatoreFromPK(listaCodFisc.get(i))));
        }
    }

    private Giocatore getGiocatoreFromPK(String CodFisc){
        for(Giocatore g : listaGiocatori){
            if(g.getCodFisc().equals(CodFisc)){
                return g;
            }
        }
        return null;
    }

    public void buildMilitanzeFromDB(){
        ArrayList<String> listaCodFisc = new ArrayList<>();
        ArrayList<String> listaNomiSquadra = new ArrayList<>();
        ArrayList<String> listaNazionalitaSquadra = new ArrayList<>();
        ArrayList<LocalDate> listaDateInizio = new ArrayList<>();
        ArrayList<LocalDate> listaDateFine = new ArrayList<>();
        ArrayList<String> listaRuoli = new ArrayList<>();
        ArrayList<Integer> listaPartitaGiocate = new ArrayList<>();
        ArrayList<Integer> listaGolEffettuati = new ArrayList<>();
        ArrayList<Integer> listaGolSubiti = new ArrayList<>();
        ArrayList<Integer> listaAmmonizioni = new ArrayList<>();
        ArrayList<Integer> listaEspulsioni = new ArrayList<>();

        admin.fetchMilitanzeFromDB(listaCodFisc, listaNomiSquadra, listaNazionalitaSquadra, listaDateInizio, listaDateFine, listaRuoli,
                listaPartitaGiocate, listaGolEffettuati, listaGolSubiti, listaAmmonizioni, listaEspulsioni);

        for(int i = 0; i < listaCodFisc.size(); i++){
            listaMilitanze.add(new Militanza(getGiocatoreFromPK(listaCodFisc.get(i)), getSquadraFromPK(listaNomiSquadra.get(i),
                    listaNazionalitaSquadra.get(i)), listaRuoli.get(i), listaDateInizio.get(i), listaDateFine.get(i), listaGolEffettuati.get(i),
                    listaGolSubiti.get(i), listaPartitaGiocate.get(i), listaAmmonizioni.get(i), listaEspulsioni.get(i)));
        }
    }

    public void visualizzaGiocatori(
            ArrayList<String> listaCodFisc, ArrayList<String> listaNomi, ArrayList<String> listaCognomi, ArrayList<String> listaPiedi,
            ArrayList<String> listaCaratteristiche, ArrayList<LocalDate> listaDoB, ArrayList<LocalDate> listaDoR){
        for(Giocatore g : listaGiocatori){
            listaCodFisc.add(g.getCodFisc());
            listaNomi.add(g.getNome());
            listaCognomi.add(g.getCognome());
            listaPiedi.add(g.getPiede());
            ArrayList<String> listaCaratteristicheGiocatore = g.getListaCaratteristiche();
            String caratteristiche = "";
            if(listaCaratteristicheGiocatore != null && !listaCaratteristicheGiocatore.isEmpty()) {
                for(String x : listaCaratteristicheGiocatore){
                    caratteristiche = caratteristiche.concat(x + ", ");
                }
                caratteristiche = caratteristiche.substring(0, caratteristiche.length() - 2);
            }
            listaCaratteristiche.add(caratteristiche);
            listaDoB.add(g.getDataDiNascita());
            listaDoR.add(g.getDataRitiro());
        }
    }

    public boolean inserisciGiocatore(String nome, String cognome, String codFisc, String piede, LocalDate dataDiNascita){
        if(admin.inserisciGiocatore(nome, cognome, codFisc, piede, dataDiNascita)) {
            listaGiocatori.add(new Giocatore(nome, cognome, codFisc, piede, dataDiNascita));
            return true;
        }else{
            return false;
        }
    }

    public boolean rimuoviGiocatore(String codFisc){
        if(admin.rimuoviGiocatore(codFisc)){
            listaGiocatori.removeIf(giocatore -> giocatore.getCodFisc().equals(codFisc));
            return true;
        }else{
            return false;
        }
    }
}