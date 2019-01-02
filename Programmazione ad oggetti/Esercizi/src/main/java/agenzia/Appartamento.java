package agenzia;

import java.util.Date;

public class Appartamento extends Abitazione {

    protected int nAppartamento;

    @Override
    public String toString() {
        StringBuilder rs = new StringBuilder();
        rs.append(super.toString() + ": ");
        rs.append("Numero Appartamento: " + nAppartamento);
        return rs.toString();
    }

    @Override
    public int compareTo(Object o){
        if(!(o instanceof Appartamento)){
            return 1;
        }
        Appartamento a = (Appartamento) o;
        if(a.indirizzo == this.indirizzo && a.nAppartamento == a.nAppartamento){
            return 0;
        }
        return -1;
    }

    public static class AppartamentoBuilder{

        protected String indirizzo;
        protected String citta;

        protected int piani = 0;
        protected Date dataCostruzione = null;
        protected Date dataUltimaRistrutturazione = null;
        protected String zona = null;
        protected int nAppartamento = 0;

        public AppartamentoBuilder(String indirizzo, String citta, int nAppartamento){
            this.indirizzo = indirizzo;
            this.citta = citta;
            this.nAppartamento = nAppartamento;
        }

        public AppartamentoBuilder conPiani(int piani){
            this.piani = piani;
            return this;
        }

        public AppartamentoBuilder conDataCostruzione(Date dataCostruzione){
            this.dataCostruzione = dataCostruzione;
            return this;
        }

        public AppartamentoBuilder conDataUltimaRistrutturazione(Date dataUltimaRistrutturazione){
            this.dataUltimaRistrutturazione = dataUltimaRistrutturazione;
            return this;
        }

        public AppartamentoBuilder conZona(String zona){
            this.zona = zona;
            return this;
        }

        public Abitazione build(){
            Appartamento appartamento = new Appartamento();
            appartamento.indirizzo = this.indirizzo;
            appartamento.citta = this.citta;

            appartamento.piani = this.piani;
            appartamento.dataCostruzione = this.dataCostruzione;
            appartamento.dataUltimaRistrutturazione = this.dataUltimaRistrutturazione;
            appartamento.zona = this.zona;
            appartamento.nAppartamento = this.nAppartamento;
            return appartamento;
        }


    }

}
