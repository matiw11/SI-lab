package pkg1;

public class Zwierze {
    double waga;

    public Zwierze(double waga) {
        this.waga = waga;
    }

    public void pokazDane(){
        System.out.println(this.getClass()+" Waga: "+ waga);
    }
}
