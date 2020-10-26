import java.math.BigInteger;

public class RSA {
  private BigInteger n;
  private BigInteger e;
  private BigInteger d;

  RSA(BigInteger p, BigInteger q) {
    this.n = p.multiply(q);
    BigInteger phi = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));
    this.e = calculateEParameter(phi);
    this.d = extendedEuclidean(this.e, phi);
  }

  public Key getPublicKey() {
    return new Key(this.e, this.n);
  }

  public Key getPrivateKey() {
    return new Key(this.d, this.n);
  }

  private BigInteger calculateEParameter(BigInteger phi) {
    BigInteger e = BigInteger.valueOf(3);

    while (phi.gcd(e).compareTo(BigInteger.ONE) != 0)
      e = e.add(BigInteger.valueOf(2));

    return e;
  }

  private BigInteger extendedEuclidean(BigInteger a, BigInteger b) {
    BigInteger x0 = BigInteger.ONE;
    BigInteger x = BigInteger.ZERO;
    BigInteger b0 = new BigInteger(b.toString());

    while(b.compareTo(BigInteger.ZERO) != 0) {
      BigInteger q = a.divide(b);
      BigInteger tmp = new BigInteger(x.toString());
      x = x0.subtract(q.multiply(x));
      x0 = new BigInteger(tmp.toString());
      tmp = a.mod(b);
      a = new BigInteger(b.toString());
      b = new BigInteger(tmp.toString());
    }

    if (x0.compareTo(BigInteger.ZERO) < 0) {
      x0 = x0.add(b0);
    }

    return x0;
  }
}