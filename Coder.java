/*
File name: Coder.java
Written by Thomas Cheng
Student id: 100388047
Written on 30 June 2022

* input message to encrypt and the key
* output encrypted message
*/
import java.util.Scanner;
public class Coder {
    /** Test program to invoke myInput and print the message a character per line */
    public static void main (String[] args) {

        char[] msg;
        int key;
        Scanner input = new Scanner(System.in);
        msg = getMsg(input); // Read the message from keyboard
        key = getKey(input);
        System.out.println("Key : "+key);
        StringBuilder out = new StringBuilder();
        for (char character : msg) {
            out.append(ascii2binary(cipher(character, key)));
        }
        System.out.println(out);
    }
    //--------
    /** Read a line of message from keyboard and return it as an array of char
     @return : Array of characters
     */
    public static char[] getMsg(Scanner input){
        String myMessage;
        System.out.print("Input Message: ");
        myMessage = input.nextLine(); // Read a line of message
        return myMessage.toCharArray();
    }
    //--------
    /** Read an integer from keyboard and return it
     @return : code
     */
    public static int getKey(Scanner input){
        //int code=0;
        System.out.print("Input Key: ");
        return input.nextInt();
    }

    /**
     * encrypt char by the key
     * @param chars: input character
     * @param key: encryption key
     * @return encrypted character
     */
    public static char cipher(char chars, int key){
        if ((chars>= '0') && (chars <='9')){
            chars = cipherPart(chars,key,'0','9');
        }
        else if ((chars>= 'A') && (chars <='Z')) {
            chars = cipherPart(chars,key,'A','Z');
        }
        else if ((chars>= 'a') && (chars <='z')){
            chars = cipherPart(chars,key,'a','z');
        }
        return chars;
    }

    /**
     * parts of the cipher
     * @param chars: character to be encrypted
     * @param key: encryption key
     * @param low: character with the lowest ascii value
     * @param up: character with the biggest ascii value
     * @return encrypted character
     */
    public static char cipherPart(char chars, int key, char low, char up){
        chars +=key;
        if (chars > up){
            chars =(char)(chars - up + low-1);
        } else if (chars < low) {
            chars =(char)(up - low + chars + 1);
        }
        return chars;
    }

    /**
     * takes ascii value and translate it to 8bit binary number
     * @param n: ascii value
     * @return binary value
     */
    public static String ascii2binary(int n) {
        StringBuilder binary = new StringBuilder();
        int digit;
        int pad = 8;
        while (n>0){ //translate ascii to binary
            digit = n%2;
            binary.insert(0, digit);
            n/=2;
            pad -=1;
        }

        // pad the number into an 8-bit binary number
        while (pad >0){
            binary.insert(0, "0");
            pad -=1;
        }
        return binary.toString();
    }
}