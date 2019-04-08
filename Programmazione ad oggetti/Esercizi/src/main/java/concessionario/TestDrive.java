package concessionario;

import java.time.LocalDate;

public class TestDrive {

    protected Automobile auto;
    protected LocalDate data;

    public TestDrive(Automobile auto, LocalDate data){
        this.auto = auto;
        this.data = data;
    }
}
