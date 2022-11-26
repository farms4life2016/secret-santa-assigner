import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Driver {

    
    public static void main(String[] args) {

        String[] participants = {"Susie", "Kris", "Ralsei", "Burghley", "Noelle", "Bambi", "Eevee"};
        String[] santas = new String[participants.length]; 
        List<String> optimization = new LinkedList<>();
        for (int i = 0; i < participants.length; i++) optimization.add(participants[i]); // well that's annoying
        boolean invalid = true;

        while (invalid) {
            for (int i = 0; i < santas.length;) {
                int rng = randint(0, optimization.size()-1);
                if (optimization.size() == 1) {
                    if (! optimization.get(0).equals(participants[i])) {
                        santas[i] = optimization.remove(0); 
                        invalid = false;
                        
                    } else {
                        optimization = Arrays.asList(participants);
                        Arrays.fill(santas, null);
                    }
                    i++;
                }
                else if (! optimization.get(rng).equals(participants[i])) {
                    santas[i] = optimization.remove(rng);
                    i++;
                }
                
            }
            System.out.println(Arrays.toString(santas));
        }

        for (int i = 0; i < participants.length; i++) {
            String s = participants[i] + " gives a gift to " + santas[i];
            System.out.println(s);
        }

        FileWriter fw = null;
        BufferedWriter bw = null;
        File f = null;

        for (int i = 0; i < participants.length; i++) {
            try {
                String s = "You are " + participants[i] + ". You are giving a gift to " + santas[i] + ".";
                f = new File("output\\" + participants[i] + ".txt");
                if (f.exists()) f.delete(); // delete existing santa assignment or else fw refuses to work.
                fw = new FileWriter(f);
                bw = new BufferedWriter(fw);
                bw.write(s);
                bw.close();
                System.out.println("Successfully created " + participants[i] + ".txt");
                
            } catch (IOException e) {
                System.out.println("Failed to create " + participants[i] + ".txt");
                e.printStackTrace();
            }
        } 
        
    }

    /**
     * Generates a random number between low and high inclusive
     * Requires: low < high
     * 
     * @param low must be a natural number
     * @param high must be a natural number larger than or equal to low.
     * @return an int between low and high inclusive
     */
    public static int randint(int low, int high) {
        return low + (int) (Math.random() * (high - low + 1));
    }


}