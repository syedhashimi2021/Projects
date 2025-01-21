//CSCI 1913 Project 2
//Author: Syed Hashimi

/*
 * A class given a key value encrypts a message into caesar cipher and can also decrypt the encrypted message.
 */
public class CaesarCipher extends BaseCipher{
    private int rotation; //key value which defines the position of the next letter.

    /*
     * Constructor for the CaesarChiper Class. 
     * It takes one int parameter and initializes the private varaible int rotation.
     */
    public CaesarCipher(int rotation){
        super("CaesarCipher");
        this.rotation = rotation;
    }

    /*
     * Checks if the rotation value provided is within the range 1 and 25. Because 0 and 26 would give a full loop.
     * if the rotation is valid the function returns true else false.
     */
    @Override
    public boolean isValid(){
        if (1 <= rotation && rotation <= 25){
            return true;
        }
        return false;
    }

    /*
     * This function takes one parameter String original.
     * String orgininal is the message that will be encrypted into Caesar Cipher in this function.
     * The encrypted message is then returned. Type String.
     */
    @Override
    public String encrypt(String original){
        String encrypted = "";
        char c;
        int x;
        int nextLetter;

        original = original.toLowerCase();
        for(int i = 0; i < original.length(); i++){
            c = original.charAt(i);
            if(Character.isAlphabetic(c)){
                x = (int) (c - 'a');
                nextLetter = (x + rotation + 26) % 26;
                c = (char) ('a' + nextLetter);
            }
            encrypted = encrypted + c;
        }

        return encrypted;
    }

    /*
     * This function takes one String parameter.
     * Given an encrypted Caesar Cipher message to the function the function reverses it and decodes the message to its original state.
     * The function then returns the decoded message. Type String.
     */
    @Override
    public String decrypt(String message){
        String decrypted = "";
        char c;
        int x;
        int nextLetter;

        for(int i = 0; i < message.length(); i++){
            c = message.charAt(i);
            if (Character.isAlphabetic(c)) {
                x = (int) (c - 'a');
                nextLetter = (x - rotation + 26) % 26;
                c = (char) ('a' + nextLetter);
            }
            decrypted = decrypted + c;
        }

        return decrypted;
    }

    /*
     * prints the rotation that is stored the CaesarCipher object.
     */
    @Override
    public String toString(){
        return "Caesar("+rotation+")";
    }

    /*
     * The function checks if the rotation of two CaesarCipher objects is the same
     * if the rotation is the same the function returns true
     */
    @Override
    public boolean equals(Object o){
        if(this == o){
            return true;
        }

        else if(o == null){
            return false;
        }

        else if(o instanceof CaesarCipher){
            CaesarCipher other = (CaesarCipher) o;
            if(other.rotation == this.rotation){
                return true;
            }

            else{
                return false;
            }
        }

        return false;
    }
}
