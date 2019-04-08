package ambulatorio;

public abstract class Persona {

    protected String nome;
    protected String cognome;
    protected String codiceFiscale;

    public Persona(String nome, String cognome, String codiceFiscale){
        this.nome = nome;
        this.cognome = cognome;
        this.codiceFiscale = codiceFiscale;
    }


}
