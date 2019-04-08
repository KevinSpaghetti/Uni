package ambulatorio;

public class Igenista extends Dottore {

    public Igenista(String nome, String cognome, String codiceFiscale, String identificatore){
        super(nome, cognome, codiceFiscale, identificatore);
    }

    @Override
    public String specializzazione() {
        return "Igenista";
    }

}
