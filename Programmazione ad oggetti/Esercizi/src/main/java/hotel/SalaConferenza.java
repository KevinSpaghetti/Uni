package hotel;

public class SalaConferenza implements Prenotabile {

    private String identificatore;
    private int nMassimoPersone;

    public SalaConferenza(String identificatore, int nMassimoPersone){
        this.identificatore = identificatore;
        this.nMassimoPersone = nMassimoPersone;
    }

}
