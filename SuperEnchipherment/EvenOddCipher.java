//CSCI 1913 Project 2
//Author: Syed Hashimi

public class EvenOddCipher extends BaseCipher{

    /*
     * Constructor for the EvenOddCipher
     * The constructor does not have any parameters nor does it intialize any variables. The constructor only invokes the parent class constructor using super() method.
     */
    public EvenOddCipher(){
        super("EvenOddCipher");
    }

    /*
     * This function takes one parameter String original.
     * String orgininal is the message that will be encrypted into EvenOdd Cipher in this function.
     * For every letter in the even position is placed in the front of the new string, and the letters in the odd position is placed after the even letters.
     * The encrypted message is then returned. Type String.
     */
    @Override
    public String encrypt(String message){
        String code = "";

        for(int i = 0; i < message.length(); i+=2){
            if(i%2 == 0){
                code = code + message.charAt(i);
            }
        }

        for(int i = 0; i < message.length(); i++){
            if(i%2 != 0){
                code = code + message.charAt(i);
            }
        }

        return code;
    }

    /*
     * This function takes one String parameter.
     * Given an encrypted EvenOdd Cipher message to the function the function reverses it and decodes the message to its original state.
     * The function then returns the decoded message. Type String.
     */
    @Override
    public String decrypt(String codedMessage){
        int crossover = (int) Math.ceil(codedMessage.length() / 2.0);
        String decryptedMessage = "";

        for (int i = 0; i < crossover; i++) {
            decryptedMessage += codedMessage.charAt(i);
            if (i+crossover < codedMessage.length()) {
                decryptedMessage += codedMessage.charAt(i+crossover);
            }
        }
        return decryptedMessage;
    }

    /*
     * prints the name of the cipher- "EvenOddCipher"
     */
    @Override
    public String toString(){
        return "EvenOddCipher";
    }

    /*
     * The function is given one parameter of type object.
     * If the passed object is an instance of EvenOddCipher the function returns true.
     */
    @Override
    public boolean equals(Object o){
        if(this == o){
            return true;
        }

        else if(o == null){
            return false;
        }

        else if(o instanceof EvenOddCipher){
            return true;
        }

        return false;
    }
}
