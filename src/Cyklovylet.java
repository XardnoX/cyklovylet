import java.time.LocalDate;

public class Cyklovylet {
    private String vylet;
    private int delka;
    private LocalDate datum;

    public String getVylet() {
        return vylet;
    }

    public void setVylet(String vylet) {
        this.vylet = vylet;
    }

    public int getDelka() {
        return delka;
    }

    public void setDelka(int delka) {
        this.delka = delka;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }

    public Cyklovylet(String vylet, int delka, LocalDate datum) {
        this.vylet = vylet;
        this.delka = delka;
        this.datum = datum;
    }

    @Override
    public String toString() {
        return
                vylet + ',' +
                        delka +
                        "," + datum + "\n";
    }
}
