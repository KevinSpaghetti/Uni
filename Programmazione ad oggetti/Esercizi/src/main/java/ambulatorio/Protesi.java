package ambulatorio;

import java.time.LocalDateTime;
import java.util.Date;

public class Protesi extends Intervento {

    public Protesi(LocalDateTime dataInizio, LocalDateTime dataFine){
        super(dataInizio, dataFine);
    }

    @Override
    public double getPrezzo() {
        return 20;
    }

    @Override
    public String toString() {
        return "PROTESI: " + super.toString();
    }
}
