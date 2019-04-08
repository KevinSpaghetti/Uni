package ambulatorio;

public class Paziente extends Persona implements Contattabile{

    protected String nTelefonico;

    public Paziente(String nome, String cognome, String codiceFiscale, String nTelefonico){
        super(nome, cognome, nTelefonico);
        this.nTelefonico = nTelefonico;
    }

    @Override
    public String contatto() {
        return nTelefonico;
    }
}
