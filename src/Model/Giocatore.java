package Model;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;

public class Giocatore {
    private String nome;
    private String cognome;
    private String codFisc;
    private String piede;
    private Date dataDiNascita;
    private String ruolo;
    private Squadra squadraAppartenenza;
    private ArrayList<String> listaCaratteristiche;
    private ArrayList<Trofeo> listaTrofei;
    private ArrayList<Militanza> listaMilitanze;

    public Giocatore(String nome, String cognome, String codFisc, String piede, Date dataDiNascita,
                     ArrayList<String> listaCaratteristiche, ArrayList<String> listaRuoli) {
        this.nome = nome;
        this.cognome = cognome;
        this.codFisc = codFisc;
        this.piede = piede;
        this.dataDiNascita = dataDiNascita;
        this.listaCaratteristiche = listaCaratteristiche;
        this.listaRuoli = listaRuoli;
        this.listaTrofei = new ArrayList<>();
        this.listaMilitanze = new ArrayList<>();
    }
    public void aggiungiMilitanza(Militanza M){
        listaMilitanze.add(M);
    }

    public void aggiungiTrofeo(TrofeoIndividuale T){
        listaTrofei.add(T);
        T.setGiocatoreVincitore(this);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getCodFisc() {
        return codFisc;
    }

    public void setCodFisc(String codFisc) {
        this.codFisc = codFisc;
    }

    public String getPiede() {
        return piede;
    }

    public void setPiede(String piede) {
        this.piede = piede;
    }

    public Date getDataDiNascita() {
        return dataDiNascita;
    }

    public void setDataDiNascita(Date dataDiNascita) {
        this.dataDiNascita = dataDiNascita;
    }

    public String getRuolo() {
        return ruolo;
    }

    public void setRuolo(String ruolo) {
        this.ruolo = ruolo;
    }

    public Squadra getSquadraAppartenenza() {
        return squadraAppartenenza;
    }

    public void setSquadraAppartenenza(Squadra squadraAppartenenza) {
        this.squadraAppartenenza = squadraAppartenenza;
    }

    public ArrayList<String> getListaCaratteristiche() {
        return listaCaratteristiche;
    }

    public void setListaCaratteristiche(ArrayList<String> listaCaratteristiche) {
        this.listaCaratteristiche = listaCaratteristiche;
    }

    public ArrayList<Trofeo> getListaTrofei() {
        return listaTrofei;
    }

    public void setListaTrofei(ArrayList<Trofeo> listaTrofei) {
        this.listaTrofei = listaTrofei;
    }

    public ArrayList<Militanza> getListaMilitanze() {
        return listaMilitanze;
    }

    public void setListaMilitanze(ArrayList<Militanza> listaMilitanze) {
        this.listaMilitanze = listaMilitanze;
    }
}
