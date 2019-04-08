package hotel;

public class Azienda implements Cliente {

    public String nTelefonico;

    public Azienda(String nTelefonico){
        this.nTelefonico = nTelefonico;
    }

    @Override
    public String contatto() {
        return nTelefonico;
    }
}
