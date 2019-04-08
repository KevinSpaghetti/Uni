package concessionario;

public class AutomobileConBatteria extends Automobile {

    Automobile auto;
    Batteria batteria;

    public enum Batteria{
        V75,
        V80,
        V110
    }

    public AutomobileConBatteria(Automobile a, Batteria tipoBatteria){
        this.auto = a;
        this.batteria = tipoBatteria;
    }

    public double getPrezzo(){
        return prezzoPerBatteria(batteria) + auto.getPrezzo();
    }

    private double prezzoPerBatteria(Batteria tipoBatteria){
        switch (tipoBatteria){
            case V75:
                    return 2000.0;

            default:
                throw new IllegalStateException();
        }
    }

    @Override
    public String toString() {
        return auto.toString() + "+ Batteria: " + batteria.name();
    }
}
