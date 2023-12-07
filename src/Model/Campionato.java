package Model;

import java.util.ArrayList;

public class Campionato {
    private String nome;
    private int anno;
    private ArrayList<Squadra> listaSquadre;
    public Campionato(String nome, int anno, ArrayList<Squadra> listaSquadre){
        this.nome = nome;
        this.anno = anno;
        this.listaSquadre = listaSquadre;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getAnno() {
        return anno;
    }

    public void setAnno(int anno) {
        this.anno = anno;
    }

    public ArrayList<Squadra> getListaSquadre() {
        return listaSquadre;
    }

    public void setListaSquadre(ArrayList<Squadra> listaSquadre) {
        this.listaSquadre = listaSquadre;
    }

    public void aggiungiSquadra(Squadra s){
        listaSquadre.add(s);
    }
    public void rimuoviSquadra(Squadra s)  { listaSquadre.remove(s); }
}
