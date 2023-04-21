import java.time.LocalDate;

public class Cyklovylet {
    private String cil;
    private int pocetKm;
    private LocalDate datum;

    public Cyklovylet(String cil, int pocetKm, LocalDate datum) {
        this.cil = cil;
        this.pocetKm = pocetKm;
        this.datum = datum;
    }

    public String getCil() {
        return cil;
    }

    public void setCil(String cil) {
        this.cil = cil;
    }

    public int getPocetKm() {
        return pocetKm;
    }

    public void setPocetKm(int pocetKm) {
        this.pocetKm = pocetKm;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }

    @Override
    public String toString() {
        return " " + cil + "(" + pocetKm +
                ")" + datum ;
    }
}
