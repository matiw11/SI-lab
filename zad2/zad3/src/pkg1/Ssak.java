package pkg1;

public class Ssak extends Zwierze{
    int wysokosc;

    public Ssak(double waga, int wysokosc) {
        super(waga);
        this.wysokosc = wysokosc;
    }

    @Override
    public void pokazDane(){
        System.out.println(this.getClass()+ " Waga: " + waga + " Wysokosc: " + wysokosc);

    }
}
