//CSCI 1913 Project 2
//Author: Syed Hashimi

public class WordReplacementCipher extends BaseCipher{
    private String originalWord, replacementWord;

    /*
     * Constructor of the WordReplacementCipher
     * @param String originalWord - The target word that is supposed to be replaced from a string
     * @param String replacement- Word which is suppose to replace the target word from a string
     * The constructors invokes the parent class constructor using super() method.
     */
    public WordReplacementCipher(String originalWord, String replacementWord){
        super("WordReplacementCipher");
        this.originalWord = originalWord;
        this.replacementWord = replacementWord;
    }

    /*
     * The function is given a String message, from that string the function replaces the target word with its replacement word
     * The function then returns the encrypted String.
     */
    @Override
    public String encrypt(String message){
        return message.replace(originalWord, replacementWord);
    }

    /*
     * The function is given encrypted message String, which the function then decryptes by placing the original word back in the String in the correct position.
     * The fucntion then returns the decoded message.
     */
    @Override
    public String decrypt(String codedMessage){
        return codedMessage.replace(replacementWord, originalWord);
    }

    /*
     * Returns the string of the name of the cipher, which is 'WordReplacementCipher' and the original word and the replacement word is also returned.
     */
    @Override
    public String toString(){
        return "WordReplacementCipher(" + originalWord + ", " + replacementWord + ")";
    }

    /*
     * The function is given a parameter of type Object
     * If the object is the instance of WordReplacement and the originalWord and replacementWord is the same for the argument and the passed object, then the function returns true.
     */
    @Override
    public boolean equals(Object o){
        if(this == o){
            return true;
        }

        else if(o == null){
            return false;
        }

        else if(o instanceof WordReplacementCipher){
            WordReplacementCipher other = (WordReplacementCipher) o;
            if(this.originalWord.equals(other.originalWord) && this.replacementWord.equals(other.replacementWord)){
                return true;
            }
        }

        return false;
    }
}
