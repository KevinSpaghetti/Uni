package ambulatorio;

public class Medico extends Dottore {

    public Medico(String nome, String cognome, String codiceFiscale, String identificatore){
        super(nome, cognome, codiceFiscale, identificatore);
    }

    @Override
    public String specializzazione() {
        return "Medico";
    }
}
