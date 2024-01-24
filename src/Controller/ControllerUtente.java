package Controller;

import Model.*;
import PostgresDAO.RegistrazioneImplementazioneDAO;
import PostgresDAO.UtenteImplementazioneDAO;

import java.time.LocalDate;
import java.util.ArrayList;

public class ControllerUtente {

    UtenteImplementazioneDAO user;
    ArrayList<Giocatore> listaGiocatori = new ArrayList<>();
    ArrayList<Campionato> listaCampionati = new ArrayList<>();
    ArrayList<Squadra> listaSquadre = new ArrayList<>();
    ArrayList<TrofeoSquadra> listaTrofeiSquadra = new ArrayList<>();
    ArrayList<TrofeoIndividuale> listaTrofeiIndividuali = new ArrayList<>();
    ArrayList<Militanza> listaMilitanze = new ArrayList<>();

    public boolean login(String login, String password) {
        user = new UtenteImplementazioneDAO("pippo", "pippo");
        if(user.login()){
            buildModelFromDB();
            return true;
        }else{
            return false;
        }
    }


    public boolean signUp(String login, String password) {
        System.out.println("Sto registrandomi con " + login + password);
        return RegistrazioneImplementazioneDAO.signUp(login, password);
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


        user.fetchGiocatoriFromDB(listaNomi, listaCognomi, listaCodFisc, listaPiedi,
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

        user.fetchCampionatiFromDB(listaId, listaNomi, listaAnni);

        for(int i = 0; i < listaId.size(); i++){
            this.listaCampionati.add(new Campionato(listaNomi.get(i), listaAnni.get(i), listaId.get(i)));

        }
    }

    public void buildSquadreFromDB(){
        ArrayList<String> listaNomi = new ArrayList<>();
        ArrayList<String> listaNazionalita = new ArrayList<>();
        ArrayList<Integer> listaId = new ArrayList<>();

        user.fetchSquadreFromDB(listaNomi, listaNazionalita, listaId);

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

        user.fetchTrofeiDiSquadraFromDB(listaNomi, listaAnni, listaMeriti, listaNomiSquadra, listaNazionalitaSquadra);

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

        user.fetchTrofeiIndividiualiFromDB(listaNomi, listaAnni, listaMeriti, listaCodFisc);

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

        user.fetchMilitanzeFromDB(listaCodFisc, listaNomiSquadra, listaNazionalitaSquadra, listaDateInizio, listaDateFine, listaRuoli,
                listaPartitaGiocate, listaGolEffettuati, listaGolSubiti, listaAmmonizioni, listaEspulsioni);

        for(int i = 0; i < listaCodFisc.size(); i++){
            listaMilitanze.add(new Militanza(getGiocatoreFromPK(listaCodFisc.get(i)), getSquadraFromPK(listaNomiSquadra.get(i),
                    listaNazionalitaSquadra.get(i)), listaRuoli.get(i), listaDateInizio.get(i), listaDateFine.get(i), listaGolEffettuati.get(i),
                    listaGolSubiti.get(i), listaPartitaGiocate.get(i), listaAmmonizioni.get(i), listaEspulsioni.get(i)));
        }
    }

    public void visualizzaCarriere(ArrayList<String> listaCodFisc, ArrayList<String> listaNomi, ArrayList<String> listaCognomi,
                                   ArrayList<LocalDate> listaDateNascita, ArrayList<Integer> listaEta, ArrayList<String> listaPiedi, ArrayList<String> listaNomiSquadra, ArrayList<String> listaRuoli,
                                   ArrayList<String> listaCaratteristiche, ArrayList<Integer> listaPartiteGiocate, ArrayList<Integer> listaGolEffettuati, ArrayList<Integer> listaGolSubiti,
                                   ArrayList<Integer> listaAmmonizioni, ArrayList<Integer> listaEspulsioni){

        user.getCarriereFromDB(listaCodFisc, listaNomi, listaCognomi, listaDateNascita, listaEta, listaPiedi, listaNomiSquadra, listaRuoli,
                listaCaratteristiche, listaPartiteGiocate, listaGolEffettuati, listaGolSubiti, listaAmmonizioni, listaEspulsioni);

    }

    public void visualizzaSquadre(ArrayList<String> listaNomi, ArrayList<String> listaNazionalita){
        for(Squadra s: listaSquadre){
            listaNomi.add(s.getNome());
            listaNazionalita.add(s.getNazionalita());
        }
    }

    public void visualizzaTrofeiSquadra(String nome, String nazionalita, ArrayList<String> listaNomiTrofeo, ArrayList<String> listaAnniTrofeo, ArrayList<String> listaMeritiTrofeo){
        Squadra s = getSquadraFromPK(nome, nazionalita);
        for(Trofeo t: s.getListaTrofei()){
            listaNomiTrofeo.add(t.getNome());
            listaAnniTrofeo.add(t.getAnno());
            listaMeritiTrofeo.add(t.getMerito());
        }
    }

}