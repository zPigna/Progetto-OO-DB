package Model;

import java.util.ArrayList;

public class Squadra {
    private String nome;
    private String nazione;
    private ArrayList<Giocatore> listaGiocatori;
    private ArrayList<Trofeo> listaTrofei;
    private Campionato campionatoAppartenenza;

    public Squadra(String nome, String nazione, Campionato campionatoAppartenenza){
        this.nome = nome;
        this.nazione = nazione;
        this.campionatoAppartenenza = campionatoAppartenenza;

        listaGiocatori = new ArrayList<>();
        listaTrofei = new ArrayList<>();
    }

    public void aggiungiGiocatore(Giocatore g){
        listaGiocatori.add(g);
        g.setSquadraAppartenenza(this);
    }
    public void aggiungiTrofeo(TrofeoSquadra T){
        listaTrofei.add(T);
    }

    public void eliminaGiocatore(Giocatore g){
        this.listaGiocatori.remove(g);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNazione() {
        return nazione;
    }

    public void setNazione(String nazione) {
        this.nazione = nazione;
    }

    public ArrayList<Giocatore> getListaGiocatori() {
        return listaGiocatori;
    }

    public void setListaGiocatori(ArrayList<Giocatore> listaGiocatori) {
        this.listaGiocatori = listaGiocatori;
    }

    public ArrayList<Trofeo> getListaTrofei() {
        return listaTrofei;
    }

    public void setListaTrofei(ArrayList<Trofeo> listaTrofei) {
        this.listaTrofei = listaTrofei;
    }

    public Campionato getCampionatoAppartenenza() {
        return campionatoAppartenenza;
    }

    public void setCampionatoAppartenenza(Campionato campionatoAppartenenza) {
        this.campionatoAppartenenza = campionatoAppartenenza;
    }
}
