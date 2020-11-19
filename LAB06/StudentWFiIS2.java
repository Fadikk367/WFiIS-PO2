public class StudentWFiIS2 implements StudentUSOS {
    private String[] przedmioty;
    private int rok;
    private Student stud;

    /* IMPLEMETACJA METOD: */
    public StudentWFiIS2(
        String imie, 
        String nazwisko, 
        int id, 
        int rok, 
        String przedmiot1, 
        double ocena1, 
        String przedmiot2, 
        double ocena2, 
        String przedmiot3, 
        double ocena3
    ) {
        this.stud = new Student(imie, nazwisko, id, ocena1, ocena2, ocena3);
        this.rok = rok;
        this.przedmioty = new String[3];
        this.przedmioty[0] = przedmiot1;
        this.przedmioty[1] = przedmiot2;
        this.przedmioty[2] = przedmiot3;
    }

    public String toString() {
        return new String("[" + this.rok + "] " + this.stud.toString());
    }

    public double srednia() {
        return this.stud.average();
    }

    public void listaPrzedmiotow() {
        for (int i = 0; i < this.przedmioty.length; i++) {
            System.out.println("\t" + (i + 1) + ". " + this.przedmioty[i] + ": " + this.stud.getGrade(i));
        }
    }
}
