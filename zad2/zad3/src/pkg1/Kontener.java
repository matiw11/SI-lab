package pkg1;

public class Kontener<T extends  Zwierze> {

    T obiekt;

    public Kontener(T obiekt) {
        this.obiekt = obiekt;
    }
}
