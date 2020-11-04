abstract class Man {
    private final String name;
    private final String surname;

    // DONE: Konstruktor
    public Man(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    // DONE: Metody typu get
    public String getName() {
        return this.name;
    }

    public String getSurname() {
        return this.name;
    }

    // DONE: toString zwracajacy lancuch z imieniem i nazwiskiem
    public String toString() {
        return new String(this.name + " " + this.surname);
    }

    abstract public Man compare(Man ob);

    public double average() {
        return 0.0;
    }
}
