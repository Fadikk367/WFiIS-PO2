import java.lang.Math;

public class Complex {
  private double re;
  private double im;

  public static final Complex I = new Complex(0.0, 1.0);
  public static final Complex ZERO = new Complex();
  public static final Complex ONE = new Complex(1.0);

  Complex() {
    this.re = 0.0;
    this.im = 0.0;
  }
  Complex(double re) {
    this.re = re;
    this.im = 0.0;
  }
  Complex(double re, double im) {
    this.re = re;
    this.im = im;
  }

  public String toString() {
    String result = "";
    if (this.re == 0.0) {
      if (this.im == 0.0)
        result = "0.0";
      else 
        result = this.im + "i";
    } else {
      result += this.re;

      if (this.im != 0) {
        if (this.im > 0)
          result += " + " + this.im + "i";
        else
          result += " - " + Math.abs(this.im) + "i";
      }
    }

    return result;
  }

  public double getRe() {
    return this.re;
  }

  public double getIm() {
    return this.im;
  }

  public boolean equals(Object other) {
    if (other instanceof Complex) {
      Complex o = (Complex) other;
      return this.re == o.re && this.im == o.im;
    } else {
      return false;
    }
  }

  public static Complex add(Complex c1, Complex c2) {
    return new Complex(c1.re + c2.re, c1.im + c2.im);
  }
  public static Complex add(double re1, Complex c2) {
    Complex c1 = new Complex(re1);
    return Complex.add(c1, c2);
  }

  public static Complex subtract(Complex c1, Complex c2) {
    return new Complex(c1.re - c2.re, c1.im - c2.im);
  }

  public static Complex multiply(Complex c1, Complex c2) {
    return new Complex(c1.re*c2.re - c1.im*c2.im, c1.im*c2.re + c1.re*c2.im);
  }

  public static Complex multiply(Complex c1, double re2) {
    Complex c2 = new Complex(re2);
    return Complex.multiply(c1, c2);
  }

  public static Complex divide(Complex c1, Complex c2) {
    return new Complex(
      (c1.re*c2.re + c1.im*c2.im)/(c2.re*c2.re + c2.im*c2.im), 
      (c1.im*c2.re - c1.re*c2.im)/(c2.re*c2.re + c2.im*c2.im)
    );
  }

  public static Complex sqrt(double num) {
    if (num >= 0)
      return new Complex(Math.sqrt(num));
    else
      return new Complex(0.0, Math.sqrt(Math.abs(num)));
  }

  public double mod() {
    return Math.sqrt(this.re*this.re + this.im*this.im);
  }

  public void conjugate() {
    this.im = -this.im;
  }

  public void opposite() {
    this.re = -this.re;
    this.im = -this.im;
  }
}
