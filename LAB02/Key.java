import java.math.BigInteger;

public class Key {
  private BigInteger exponent;
  private BigInteger moduloDivider;

  Key(BigInteger exponent, BigInteger moduloDivider) {
    this.exponent = exponent;
    this.moduloDivider = moduloDivider;
  }

  public BigInteger getExponent() {
    return this.exponent;
  }

    public BigInteger getModuloDivider() {
    return this.moduloDivider;
  }

  public String toString() {
    return new String("(" + this.exponent + ", " + this.moduloDivider + ")");
  }
}