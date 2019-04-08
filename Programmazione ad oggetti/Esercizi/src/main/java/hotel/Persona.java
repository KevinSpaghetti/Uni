package hotel;

public class Persona implements Cliente {

    public String nTelefonico;



    @Override
    public String contatto() {
        return nTelefonico;
    }

}
