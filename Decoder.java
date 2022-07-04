/*
File name: Decoder.java
Written by Thomas Cheng
Student id: 100388047
Written on 30 June 2022

* input encrypted message and the key
* output decrypted message
*/
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Decoder {
    public static void main(String[] args) {
        // create scanner object
        Scanner in = new Scanner(System.in);

        // initialize the key
        int key = getKey(in);

        // ket confirmation
        System.out.println("Key : "+key);

        // create string array with 8-bit binary in each cell
        List<String> outLst = binStringChoppa(getMsg(in));

        //print decrypted character one by one
        for (String eightBitNum: outLst) {
            System.out.print(decipher((char)binary2ascii(eightBitNum),key));
        }
    }

    /**
     * prompt user to enter decryption key
     * @param input : input object
     * @return : key
     */
    public static int getKey(Scanner input){
        System.out.print("Input Key: ");
        return input.nextInt();
    }

    /**
     * Read a line of message from keyboard and return it as a string of char
     * @return String of characters
     */
    public static String getMsg(Scanner input) {
        String myMessage;
        input.nextLine();
        System.out.print("Input Message: ");
        myMessage = input.nextLine();
        return myMessage;
    }

    /**
     * Takes character and key to decrypt the character
     * @param chars : character to decrypt
     * @param key : the decryption key
     * @return : decrypted character
     */
    public static char decipher(char chars, int key) {
        if ((chars >= '0') && (chars <= '9')) {// for character set 0-9
            chars = decipherPart(chars, key, '0', '9');
        }
        else if ((chars >= 'A') && (chars <= 'Z')) {// for character set A-Z
            chars = decipherPart(chars, key, 'A', 'Z');
        }
        else if ((chars >= 'a') && (chars <= 'z')) {// for character set a-z
            chars = decipherPart(chars, key, 'a', 'z');
        }
        return chars;
    }

    /**
     * decrypt character from a certain set of characters
     * @param chars : characters to be decrypted
     * @param key : decryption key
     * @param low : lowest value of the character set
     * @param up : highest value of the character set
     * @return decrypted character
     */
    public static char decipherPart(char chars, int key, char low, char up) {
        chars -= key;
        if (chars < low) {// wraps around to the top
            chars = (char) ( up- low + chars+1);
        }
        return chars;
    }

    /**
     * takes a 8bit binary number in string and turn it into ascii value
     * @param bin: 8-bit number
     * @return ascii value
     */
    public static int binary2ascii(String bin) {
        int num = 0;
        char[] binary = bin.toCharArray();
        for (int i = 0; i < binary.length; i++) {// for each digit in the binary number
            if (binary[i] == '1') {
                num += Math.pow(2, binary.length- 1 - i);
            }
        }
        return num;
    }

    /**
     * Takes a String and chop it into a string array with a 8bit binary number in each cell
     * @param bin: binary number string
     * @return String list
     */
    public static List<String> binStringChoppa(String bin) {
        char[] binCharArray = bin.toCharArray();
        List<String> stringArray = new ArrayList<>();
        for (int k = 0; k < binCharArray.length / 8; k++) {// for every 8 digit
            StringBuilder strToAdd= new StringBuilder();
            for (int i = k * 8; i <= k*8 + 7; i++) {// isolate 8-bit binary number as a string
                strToAdd.append(Character.toString(binCharArray[i]));
            }
            // insert the 8-bit binary number string into a new cell in the Array
            stringArray.add(strToAdd.toString());
        }
        return stringArray;
    }
}