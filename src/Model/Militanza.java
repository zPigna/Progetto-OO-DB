package Model;

import java.util.Date;

public class Militanza {
    private Squadra squadra;
    private String ruolo;
    private Date dataInizio;
    private Date dataFine;
    private int goalSegnati;
    private int goalSubiti;
    private int partiteGiocate;

    public Militanza(Squadra squadra, String ruolo, Date  dataInizio, Date dataFine, int goalSegnati, int goalSubiti, int partiteGiocate){
        this.squadra = squadra;
        // Da gestire il settaggio del ruolo, anche nei metodi set/get (potresti utilizzare proprio quei metodi con dei try-catch). Per ora prendiamo per buono il ruolo che ci viene dato.
        this.ruolo = ruolo;
        this.dataInizio = dataInizio;
        this.dataFine = dataFine;
        this.goalSegnati = goalSegnati;
        if(this.ruolo == "Portiere"){ this.goalSubiti = goalSubiti; }
        this.partiteGiocate = partiteGiocate;
    }

    public Squadra getSquadra() {
        return squadra;
    }

    public void setSquadra(Squadra squadra) {
        this.squadra = squadra;
    }

    public String getRuolo() {
        return ruolo;
    }

    public void setRuolo(String ruolo) {
        this.ruolo = ruolo;
    }

    public Date getDataInizio() {
        return dataInizio;
    }

    public void setDataInizio(Date dataInizio) {
        this.dataInizio = dataInizio;
    }

    public Date getDataFine() {
        return dataFine;
    }

    public void setDataFine(Date dataFine) {
        this.dataFine = dataFine;
    }

    public int getGoalSegnati() {
        return goalSegnati;
    }

    public void setGoalSegnati(int goalSegnati) {
        this.goalSegnati = goalSegnati;
    }

    public int getGoalSubiti() {
        return goalSubiti;
    }

    public void setGoalSubiti(int goalSubiti) {
        this.goalSubiti = goalSubiti;
    }

    public int getPartiteGiocate() {
        return partiteGiocate;
    }

    public void setPartiteGiocate(int partiteGiocate) {
        this.partiteGiocate = partiteGiocate;
    }
}
