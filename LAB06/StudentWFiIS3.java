public class StudentWFiIS3 extends Student {
    private StudentUSOS stud;

    public StudentUSOS getStudentUSOS(int r, String p1, String p2, String p3) {
        return new StudentUSOS() {
            private String[] przedmioty = new String[] { p1, p2, p3 };
            private int rok = r;

            @Override
            public double srednia() {
                return average();
            }

            @Override
            public String toString() {
                return "" + this.rok;
            }

            @Override
            public void listaPrzedmiotow() {
                for (int i = 0; i < this.przedmioty.length; i++) {
                    System.out.println("\t" + (i+1) + ". " + this.przedmioty[i] + ": " + getGrade(i));
                }
            }
        };
    }

    public StudentWFiIS3(
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
        super(imie, nazwisko, id, ocena1, ocena2, ocena3);
        this.stud = getStudentUSOS(rok, przedmiot1, przedmiot2, przedmiot3);
    }

    public String toString() {
        return new String("[" + this.stud.toString() + "] " + super.toString());
    }

    public double srednia() {
        return this.stud.srednia();
    }

    public void listaPrzedmiotow() {
        this.stud.listaPrzedmiotow();
    }
}