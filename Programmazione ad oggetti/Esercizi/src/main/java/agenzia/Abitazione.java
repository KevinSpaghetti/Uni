package agenzia;

import java.util.Date;

public abstract class Abitazione implements Comparable{

    protected String indirizzo;
    protected String citta;

    protected int piani;
    protected Date dataCostruzione;
    protected Date dataUltimaRistrutturazione;
    protected String zona;

    @Override
    public String toString() {
        StringBuilder descrizioneAbitazione = new StringBuilder();
        descrizioneAbitazione.append("indirizzo: " + indirizzo + " - ");
        descrizioneAbitazione.append("città: " + citta + "");

        return descrizioneAbitazione.toString();
    }

    @Override
    public int compareTo(Object o) {
        if(!(o instanceof Abitazione)){
            return 1;
        }
        Abitazione a = (Abitazione) o;
        if(a.indirizzo == this.indirizzo){
            return 0;
        }
        return -1;
    }

}
