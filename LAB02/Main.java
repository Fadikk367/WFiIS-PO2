import java.math.BigInteger;

class Main {
  public static void main(String[] args) {
    Message message = new Message(args[0]);
    System.out.println("Your message: " + message + '\n');

    BigInteger p = BigInteger.valueOf(397);
    BigInteger q = BigInteger.valueOf(103);
    RSA rsaAlgorithm = new RSA(p, q);

    Key publicKey = rsaAlgorithm.getPublicKey();
    Key privateKey = rsaAlgorithm.getPrivateKey();

    System.out.println("Public key: " + publicKey);
    System.out.println("Public key: " + privateKey + '\n');

    message.encrypt(publicKey);
    System.out.println("Encrpyted message: " + message + '\n');

    message.decrypt(privateKey);
    System.out.println("Decrypted message: " + message + '\n');
  }
}