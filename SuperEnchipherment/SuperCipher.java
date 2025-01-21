//CSCI 1913 Project 2
//Author: Syed Hashimi

import java.util.Arrays;

/*
 * The purpose of the class is to use mulitple baseCiphers to encrypt the a single message
 */
public class SuperCipher extends BaseCipher{
    private BaseCipher[] ciphers;

    /*
     * Constructor of the SuperCipher class
     * @param BaseCipher[] ciphers - this array includes multiple ciphers and the each encryption will be done in the order of the array.
     * invokes the parent class constructor using super() method.
     */
    public SuperCipher(BaseCipher[] ciphers){
        super("SuperCipher");
        this.ciphers = ciphers;
    }

    /*
     * The function checks if each of the BaseCipher is valid in the array
     * if all are valid the function returns true.
     */
    @Override
    public boolean isValid(){
        boolean ciphersAreValid = true;
        for(int i = 0; i < ciphers.length; i++){
            if(!ciphers[i].isValid()){
                return false;
            }
        }
       
        return ciphersAreValid;
    }

    /*
     * The function is given a String message as a parameter to encrypt
     * The function encrypts the message using the BaseCipher array. All the ciphers in the array are applied to encryted in the order of their position in the array.
     * The function then returns the encrypted message, type String.
     */
    @Override
    public String encrypt(String message){
        String encodedMessage = message;

        for(int i = 0; i < ciphers.length; i++){
            encodedMessage = ciphers[i].encrypt(encodedMessage);
        }

        return encodedMessage;
    }

    /*
     * Given an encrypted message, the function decrypts the message in the opposite direction of the array, to work itself backwards to the origanl message.
     * The function then returns the decoded message, type string.
     */
    @Override
    public String decrypt(String codedMessage){
        String decodedMessage = codedMessage;

        for(int i = ciphers.length-1; i >= 0; i--){
            decodedMessage = ciphers[i].decrypt(decodedMessage);

        }

        return decodedMessage;
    }

    /*
     * returns the name of the process 'SuperCipher' and also adds the toString() functions of all the ciphers in the BaseCipher array 
     */
    @Override
    public String toString(){
        String output = "";
        for(int i = 0; i < ciphers.length; i++){
            output = output + ciphers[i].toString();
            if(i < (ciphers.length - 1)){
                output = output + " | ";
            }
        }
        
        return "SuperCipher(" + output + ")";
    }
    
    /*
     * Checks if two SuperCiphers have the same length and the same order of each cipher in their BaseCipher array.
     */
    @Override
    public boolean equals(Object o){
        if(this == o){
            return true;
        }

        else if(o == null){
            return false;
        }

        else if(o instanceof SuperCipher){
            SuperCipher other = (SuperCipher) o;
            return Arrays.equals(this.ciphers, other.ciphers);
        }

        return false;
    }
}
