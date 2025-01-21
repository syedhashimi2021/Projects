//CSCI 1913 Project 2
//Author: Syed Hashimi

/*
 * The class is used to encryt and decrypt a sequence of strings
 */
public class EncryptUtils {

    /*
     * The function encrypts a sequence of strings with the given cipher object.
     */
    public static String[] encryptMany(BaseCipher message, String[] encryption){
        String[] encoded = new String[encryption.length];
        for(int i = 0; i < encryption.length; i++){
            encoded[i] = message.encrypt(encryption[i]);
        }
        return encoded;
    }

    /*
     * The function decrypts the sequence of strings with the given cipher object.
     */
    public static String[] decryptMany(BaseCipher message, String[] decryption){
      String[] decoded = new String[decryption.length];
        for(int i = 0; i < decryption.length; i++){
            decoded[i] = message.decrypt(decryption[i]);
        }
        return decoded;
    }
}
