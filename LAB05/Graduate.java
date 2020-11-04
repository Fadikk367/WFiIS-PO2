import java.text.MessageFormat;

/**
 * Class representing student after graduation, extends base class Student by degree and graduationYear
 * @see Student
 */
public class Graduate extends Student {
  /** Graduate degree, e.g. "mgr. inż" */
  private String degree;
  /** Student graduation year */
  private int graduationYear;

  /** 
   * @param degree Student degree
   * @param name Student name
   * @param surname Student surname
   * @param id Student index number
   * @param graduationYear Year in which student graduated
   * @param grades array of student grades
   */
  public Graduate(String degree, String name, String surname, int id, int graduationYear, double ...grades) {
    super(name, surname, id, grades);

    this.degree = degree;
    this.graduationYear = graduationYear;
  }

  /** 
   * Returns string representation of student in following format:
   * "(degree) (name) (surname), id number: (id), grades: [(grade1), (grade2), (grade3)], year of graduation: (graduationYear)"
   */
  @Override
  public String toString() {
    return MessageFormat.format("{0} {1}, year of graduation:{2, number, #}", this.degree, super.toString(), this.graduationYear);
  }
}

