package poisonedwine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Barianos <Barianos@NiLE>
 */
public class PoisonedWine {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int numOfBottles = 0;
        String input = "";
        //INPUT FROM USER: Number of bottles in cellar
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("How many wines were in the cellar?");
            input = br.readLine();
        } catch (IOException ex) {
            Logger.getLogger(PoisonedWine.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Critical failure! Sory for the inconvinience!");
            System.exit(1);
        }
        numOfBottles = Integer.parseInt(input);

        //Calculate number of tasters, based on the log base two
        int tastersNeeded = (int) (Math.log(numOfBottles) / Math.log(2));
        System.out.println("We will need " + tastersNeeded + " testers to detect the poisoned wine");
        //Randomly kill some of the tasters. 0= alive, 1 is dead. 
        int[] tasterState = new int[tastersNeeded];
        for (int i = 0; i < tastersNeeded; i++) {
            tasterState[i] = (int) Math.round(Math.random());
            System.out.println("Taster " + i + " is " + tasterState[i]);
        }
        System.out.print("The following Tasters died:\t ");
        for (int i = 0; i < tastersNeeded; i++) {
            if (tasterState[i] == 1) {
                System.out.print(i + "\t");
            }
        }
        //calculate the number of the bottle based on the deaths
        int poisonedBottle = binaryToDecimal(tasterState);
        System.out.println("\n******");
        System.out.println("We can conclude that Bottle number " + poisonedBottle + " is the poisoned one");

    }
    
    
/**
 * This method returns the decimal number that corresponds to the binary given 
 * as an array of 0s and 1s
 * @param binaryNumber
 * @return 
 */
    private static int binaryToDecimal(int[] binaryNumber) {
        int decimal = 0;
        int count = 0;
        for(int i=0; i<binaryNumber.length; i++){
            int temp = binaryNumber[i];
            if (temp == 1) {
                decimal += Math.pow(2, count);
            }
            count++;
        }
        return decimal;
    }
}
