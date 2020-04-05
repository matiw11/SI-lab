package pkg1;

public class Wyswietlacz {

    public static void main(String[] args) {

        Kontener<Zwierze> zwierzeKontener = new Kontener<>(new Zwierze(50.00));
        Kontener<Ssak> ssakKontener = new Kontener<>(new Ssak(20.00, 6));
        Kontener<Pies> piesKontener = new Kontener<>(new Pies(15.00, 2, "York"));


        pokazDaneX(zwierzeKontener);
        pokazDaneX(ssakKontener);
        pokazDaneX(piesKontener);

        //pokazDaneY(zwierzeKontener);       //błąd
        pokazDaneY(ssakKontener);
        pokazDaneY(piesKontener);

//        pokazDaneZ(zwierzeKontener);        //błąd
        //pokazDaneZ(ssakKontener);           //błąd
        pokazDaneZ(piesKontener);


        System.out.println("Polimorfizm ad hoc:");
        zwierzeKontener.obiekt.pokazDane();
        ssakKontener.obiekt.pokazDane();
        piesKontener.obiekt.pokazDane();


    }

    static void pokazDaneX(Kontener<? extends Zwierze> k) {
        System.out.println(k.obiekt.getClass() + " Waga: " + k.obiekt.waga);
    }

    static void pokazDaneY(Kontener<? extends Ssak> k) {
        System.out.println(k.obiekt.getClass() + " Waga: " + k.obiekt.waga + " wiek: " + k.obiekt.wysokosc);
    }

    static void pokazDaneZ(Kontener<? extends Pies> k) {
        System.out.println(k.obiekt.getClass() + " Waga: " + k.obiekt.waga + " wiek: " + k.obiekt.wysokosc + " rasa: " + k.obiekt.rasa);
    }


}
