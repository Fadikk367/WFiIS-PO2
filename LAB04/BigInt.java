import java.util.Arrays; 
import java.util.ArrayList; 
import java.util.Collections;
import java.lang.Math;

public class BigInt {
  final private byte[] digits;
  private final static byte asciiOffset = 48;

  BigInt(String digitsStr) {
    int length = digitsStr.length();
    this.digits = new byte[length];
    for (int i = 0; i < length; i++) {
      this.digits[i] = (byte) ((digitsStr.charAt(i)) - BigInt.asciiOffset);
    }
  }

  BigInt(byte[] digits) {
    this.digits = new byte[digits.length];
    for (int i = 0; i < digits.length; i++) {
      this.digits[i] = digits[i];
    }
  }

  BigInt(BigInt other) {
    this.digits = new byte[other.digits.length];
    System.out.println(other);
    for (int i = 0; i < other.digits.length; i++) {
      this.digits[i] = other.digits[i];
    }
  }

  public BigInt add(BigInt other) {
    ArrayList<Byte> result = new ArrayList<Byte>();

    int thisLength = this.digits.length;
    int otherLength = other.digits.length;
    int maxLength = Math.max(thisLength, otherLength);
    int minLength = Math.min(thisLength, otherLength);

    int rest = 0;
    // Add digits from common length
    for (int i = 0; i < minLength; i++) {
      int sum = this.digits[thisLength - 1 - i] + other.digits[otherLength - 1 - i] + rest;
      if ( sum >= 10 ) {
        sum = sum - 10;
        rest = 1;
      } else {
        rest = 0;
      }
      result.add((byte) sum);
    }

    // if one BigInt has more digits than another, handle remaining digits
    if (thisLength != otherLength) {
      BigInt longerNumber = thisLength > otherLength ? this : other;

      for (int i = minLength; i < maxLength; i++) {
        int sum = longerNumber.digits[maxLength - 1 - i] + rest;
        if ( sum >= 10 ) {
          sum = sum - 10;
          rest = 1;
        } else {
          rest = 0;
        }
        result.add((byte) sum);
      }

    }

    // Adds extra digit if there is still rest
    if (rest == 1)
      result.add((byte) 1);
    
    Collections.reverse(result);
    byte[] resutlDigits = new byte[result.size()];
    for (int i = 0; i < result.size(); i++)
      resutlDigits[i] = result.get(i);

    return new BigInt(resutlDigits);
  }

  public String toString() {
    StringBuilder result = new StringBuilder("");
    for (byte digit : this.digits)
      result.append(digit);

    return result.toString();
  }

  public boolean equals(Object obj) {
    if (obj instanceof BigInt) {
      BigInt other = (BigInt) obj;

      if (this.digits.length != other.digits.length)
        return false;

      boolean isEqual = true;
      for (int i = 0; i < this.digits.length; i++) {
        if (this.digits[i] != other.digits[i]) {
          isEqual = false;
          break;
        }
      }
      
      return isEqual;
    } else {
      return false;
    }
  }

  // Returns copy to keep BigInt immutability
  public byte[] getNum() {
    return Arrays.copyOf(this.digits, this.digits.length);
  }
}
