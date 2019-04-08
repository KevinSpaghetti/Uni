package ambulatorio;

public abstract class Dottore extends Persona {

    public String identificatore;

    public Dottore(String nome, String cognome, String codiceFiscale, String identificatore){
        super(nome, cognome, codiceFiscale);
        this.identificatore = identificatore;
    }

    public abstract String specializzazione();

}
