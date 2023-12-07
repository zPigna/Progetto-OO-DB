package Model;

public class TrofeoIndividuale extends Trofeo {
    private Giocatore giocatoreVincitore;

    public TrofeoIndividuale(String nome, Date data, Giocatore g){
        super.Trofeo(nome, data);
        this.giocatoreVincitore = g;
        g.aggiungiTrofeo(this);
    }

    public Giocatore getGiocatoreVincitore() {
        return giocatoreVincitore;
    }

    public void setGiocatoreVincitore(Giocatore giocatoreVincitore) {
        this.giocatoreVincitore = giocatoreVincitore;
    }
}
