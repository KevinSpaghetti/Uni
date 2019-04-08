package ambulatorio;

import java.time.LocalDateTime;

public class InterventoFactory {

    public enum Tipo{
        PROTESI
    }

    public static Intervento getIntervento(InterventoFactory.Tipo tipoIntervento, LocalDateTime dataInizio, LocalDateTime dataFine){
        switch (tipoIntervento){
            case PROTESI:
                return new Protesi(dataInizio, dataFine);
            default:
                throw new IllegalStateException("Tipo intervento invalido");
        }
    }



}
