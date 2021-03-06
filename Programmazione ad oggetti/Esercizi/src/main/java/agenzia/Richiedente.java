package agenzia;

public class Richiedente implements Contattabile {
    public String numeroTelefonico;
    public String nome;
    public String cognome;

    public Richiedente(String nome, String cognome, String numeroTelefonico){
        this.numeroTelefonico = numeroTelefonico;
        this.nome = nome;
        this.cognome = cognome;
    }

    @Override
    public String getNumeroTelefonico() {
        return numeroTelefonico;
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public String getCognome() {
        return cognome;
    }
}
