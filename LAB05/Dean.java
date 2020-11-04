import java.text.MessageFormat;

/**
 * Class representing dean of some faculty, extends Man by degree and boundaries of dean cadency
 */
public class Dean extends Man {
  private String degree;
  private int startYear;
  private int endYear;

  /** 
   * @param degree dean degree
   * @param name dean name
   * @param surname dean surname
   * @param startYear Year in which dean started his cadency
   * @param endYear Year in which dean completed his cadency
   */
  public Dean(String degree, String name, String surname, int startYear, int endYear) {
    super(name, surname);

    this.degree = degree;
    this.startYear = startYear;
    this.endYear = endYear;
  }

  /** 
   * Returns string representation of dean in following format:
   * "(degree) (name) (surname), Dean of the Faculty from (startYear) to (endYear)."
   */
  @Override
  public String toString() {
    return MessageFormat.format("{0} {1}, Dean of the Faculty from{2, number, #} to{3, number, #}.", this.degree, super.toString(), this.startYear, this.endYear);
  }

  /**
   * Calculates average grade - for Dean class returns fixed value and prints "  [Average not applicable]"
   * @return 0.0 
   */
  @Override
  public double average() {
    System.out.print("  [Average not applicable]");
    return super.average();
  }

  /**
   * Compares dean to another one according to their year of cadency completion
   * @param other dean that we are comparing to
   * @return dean with the greater endYear or null if other is not instance of Dean
   */
  @Override
  public Man compare(Man other) {
    if (other instanceof Dean) {
      Dean o = (Dean) other;
      return this.endYear > o.endYear ? this : other;
    }
    return null;
  }
}
