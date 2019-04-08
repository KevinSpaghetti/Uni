package hotel;

public class SalaRistorante implements Prenotabile {

    private String identificatore;
    private int nMassimoPersone;

    public SalaRistorante(String identificatore, int nMassimoPersone){
        this.identificatore = identificatore;
        this.nMassimoPersone = nMassimoPersone;
    }

}
