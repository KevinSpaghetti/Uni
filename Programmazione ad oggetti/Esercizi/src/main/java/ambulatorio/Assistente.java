package ambulatorio;

public class Assistente extends Dottore {

    public Assistente(String nome, String cognome, String codiceFiscale, String identificatore){
        super(nome, cognome, codiceFiscale, identificatore);
    }

    @Override
    public String specializzazione() {
        return "Assistente";
    }

}
