package pkg1;

public class Pies extends Ssak {
    String rasa;

    public Pies(double waga, int wysokosc, String rasa) {
        super(waga, wysokosc);
        this.rasa = rasa;
    }

    @Override
    public void pokazDane(){
        System.out.println(this.getClass()+" Waga: " + waga + " Wysokosc: " + wysokosc + " rasa: " + rasa);

    }
}
