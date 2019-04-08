package ambulatorio;

import java.time.LocalDateTime;

public class Main {

    public static void main(String[] args){
        Ambulatorio ambulatorio = new Ambulatorio("9938276154");
        Sala a = new Sala("AAA");
        Sala b = new Sala("ABC");
        Sala c = new Sala("CBA");

        ambulatorio.addSala(a).addSala(b).addSala(c);
        System.out.println(ambulatorio);

        Dottore medico = new Medico("Jhon", "Smith", "ALSBDHF23G", "8827361205");
        Dottore igenista = new Igenista("Luigi", "Kwon", "KLASH18272A", "0192162838");
        Dottore assistente = new Medico("Walluigi", "Sinatra", "AKSJ2781JS", "103378374");

        Persona p = new Paziente("Mario", "Rossi", "AAAHGHOSGR", "6628173529");
        PianoTerapeutico piano = new PianoTerapeutico(p);

        LocalDateTime inizio = LocalDateTime.now();
        LocalDateTime fine = inizio.plusHours(2);
        Intervento primo = InterventoFactory.getIntervento(InterventoFactory.Tipo.PROTESI, inizio, fine);
        Intervento secondo = InterventoFactory.getIntervento(InterventoFactory.Tipo.PROTESI, inizio.plusHours(3), fine.plusHours(4));

        piano.aggiungiIntervento(primo).aggiungiIntervento(secondo);

        System.out.println(piano.costo());
    }

}
