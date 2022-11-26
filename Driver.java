import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Driver {

    
    public static void main(String[] args) {

        // {"Susie", "Kris", "Ralsei", "Burghley", "Noelle", "Bambi", "Eevee"};
        //
        String[] participants = {"Vaporeon", "Jolteon", "Flareon", "Espeon", "Umbreon", "Leafeon", "Glaceon"}; 
        Arrays.sort(participants); 
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
        File dir[] = null;

        dir = (new File("output")).listFiles();
        for (int i = 0; i < dir.length; i++) {
            dir[i].delete();  // delete previous santa assignments or else fw refuses to work and also clean up
        }

        for (int i = 0; i < participants.length; i++) {
            String fileName = participants[i];
            try {
                String message = "You are " + participants[i] + ". You are giving a gift to " + santas[i] + ".";
                f = new File("output\\" + fileName);
                fw = new FileWriter(f);
                bw = new BufferedWriter(fw);
                bw.write(message);
                bw.close();
                System.out.println("Successfully created a file named " + fileName);
                
            } catch (IOException e) {
                System.out.println("Failed to create a file named " + fileName);
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