package Model;

public class TrofeoSquadra extends Trofeo{
    private Squadra squadraVincitrice;

    public TrofeoSquadra(String nome, Date data, Squadra squadraVincitrice{
        super.Trofeo(nome, data);
        this.squadraVincitrice = squadraVincitrice;
        squadraVincitrice.aggiungiTrofeo(this);
    }

    public Squadra getSquadraVincitrice() {
        return squadraVincitrice;
    }

    public void setSquadraVincitrice(Squadra squadraVincitrice) {
        this.squadraVincitrice = squadraVincitrice;
    }
}
