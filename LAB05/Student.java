import java.text.MessageFormat;

/** Class representing student, extends base class Man by index number and 3 example grades
 * @see Man
 */
public class Student extends Man {
  /** Student's index number */
  private int id;
  /** Three example grades of a specfic student */
  private double[] grades = new double[3];

  /** 
   * @param name student name
   * @param surname student surname
   * @param id student index number
   * @param grades array of student grades
   */
  public Student(String name, String surname, int id, double ...grades) {
    super(name, surname);

    this.id = id;

    this.grades[0] = grades[0];
    this.grades[1] = grades[1];
    this.grades[2] = grades[2];
  }

  /** 
   * Returns string representation of student in following format:
   * "(name) (surname), id number: (id), grades: [(grade1), (grade2), (grade3)]"
   */
  public String toString() {
    StringBuilder gradeStrBuilder = new StringBuilder();
    for (int i = 0; i < this.grades.length - 1; i++)
      gradeStrBuilder.append(this.grades[i] + ", ");
    gradeStrBuilder.append(this.grades[this.grades.length - 1]);

    return MessageFormat.format("{0}, id number:{1, number, #}, grades: [{2}]", super.toString(), this.id, gradeStrBuilder.toString());
  }

  /** 
   * Compares one student to another according to their average grade 
   * @param other student that we are comparing to
   * @return man with the greater average grade
   */
  @Override
  public Man compare(Man other) {
    if (other instanceof Student)
      return this.average() > other.average() ? this : other;
    return null;
  }

  /** 
   * Calculates average grade 
   * @return average grade
  */
  @Override
  public double average() {
    double sum = 0;
    for (double grade : grades)
    sum += grade;

    return sum/this.grades.length;
  }
}
