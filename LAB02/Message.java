import java.math.BigInteger;
import java.util.ArrayList;

public class Message {
  private ArrayList<BigInteger> messageParts = new ArrayList<BigInteger>();
  public boolean isEncrypted = false;

  Message(String message) {
    char[] messageLetters = message.toCharArray();

    for (char c : messageLetters)
      this.messageParts.add(BigInteger.valueOf((int) c));
  }

  public void printParts() {
    System.out.print("Message parts: ");
    for (BigInteger part : this.messageParts)
      System.out.print(part + ", ");

    System.out.println();
  }

  public String toString() {
    String result = "";
    for (BigInteger part : this.messageParts) 
      result += (char) Integer.parseInt(part.toString());

    return result;
  }

  public void encrypt(Key publicKey) {
    int i = 0;
    for (BigInteger part : this.messageParts) {
      BigInteger encryptedPart = part.modPow(publicKey.getExponent(), publicKey.getModuloDivider());
      this.messageParts.set(i, encryptedPart);
      i++;
    }
    
    this.isEncrypted = true;
  }

  public void decrypt(Key privateKey) {
    int i = 0;
    for (BigInteger part : this.messageParts) {
      BigInteger encryptedPart = part.modPow(privateKey.getExponent(), privateKey.getModuloDivider());
      this.messageParts.set(i, encryptedPart);
      i++;
    }

    this.isEncrypted = false;
  }
}