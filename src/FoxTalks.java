import java.io.File;
import java.util.*;

public class FoxTalks {

    /**
     * FoxTalks program reads first line from file Audio.txt, taking in an integer.
     * The program reads an amount of audio lines matching the integer into an
     * ArrayList The integer then reads in labels for different audio sounds in the
     * format "<animal> goes <sound>" Reading ends when input asks "what does the
     * fox say?" Program sorts through the audio and compares to labels. Program
     * prints out the remaining noises for the fox.
     * 
     * Can read multiple audio entries in same file when put in the same format. 
     *  NO EXTRA LINE BETWEEN AUDIO ENTRIES.(attempting to bug that out at line 148) 
     * 
     * @author Abby Bock
     * 
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {

        //
        System.out.println("What Does The Fox Say?");

        // Scanner stream reading from Audio.txt file 
        Scanner in = new Scanner(new File("src/Audio.txt"));

        // boolean initiated outside of while loop
        boolean again = true;
        // initiate counter for audio readings
        int cnt = 1;

        /*
         * The while loop will repeat the entire program for one file until there is no
         * more information to recieve.
         */
        while (again) {

            // Variable to pinpoint the end of a line for the ArrayList
            String endLine = "endOfLine";
            // ArrayList to hold audio information
            ArrayList<String> sounds = loadAudio(endLine, in);
            // calling loadAnimals method and returning fox-only audio ArrayList
            sounds = loadAnimals(sounds, in);

            System.out.println("\nAudio entry #" + cnt);
            // Outputting the fox sounds in order and same lines as input
            printResult(sounds, endLine);

            // if else statement to determine if there is more input to read into program.
            // if not, return false and exit loop.
            if (in.hasNextInt()) {
                again = true;
                // increase cnt for next audio reading
                cnt++;
            } else {
                again = false;
            }

        }
        // end program
        System.out.println("\nEnd of audio file. \n This could make a great song!!\n");

    }

    /**
     * printResult prints out the remaining sounds in the same order and amount of
     * lines they were entered.
     * 
     * @param sounds  The ArrayList containing the fox sounds
     * @param endLine The variable to skip to the next line
     */
    public static void printResult(ArrayList<String> sounds, String endLine) {
        // loops as long as cnt doesn't exceed ArrayList size
        for (int cnt = 0; cnt < sounds.size(); cnt++) {
            /*
             * if array slot is endLine variable, skips to next line. else, prints out array
             * value
             */
            if (sounds.get(cnt).equals(endLine)) {
                System.out.println();
            } else {
                System.out.print(sounds.get(cnt) + " ");
            }
        }
    }

    /**
     * loadAnimals loads the animal labels and noises into an ArrayList
     * 
     * @param sounds The ArrayList containing all audio recordings
     * @param in     The Scanner in order to read the Audio.txt file
     * @return The sounds ArrayList with only Fox sounds
     */
    public static ArrayList<String> loadAnimals(ArrayList<String> sounds, Scanner in) {
        // initiate boolean for while loop
        boolean foxy = true;
        // initialize ArrayList for known sounds
        ArrayList<String> labelNoise = new ArrayList<>();

        // while loop that reads and splits known sounds until question is asked
        while (foxy) {
            // create input string for unknown sounds
            String label = in.nextLine();
            // compare label to known keyphrase
            if (label.equals("what does the fox say?")) {
                // while value is now false. ends loop
                foxy = false;
                // sortSounds method call
                sounds = sortSounds(sounds, labelNoise);
                // break out of loop
                break;
            } else {
                // create String ArrayList
                String[] splitters;
                /*
                 * two split words, goes or go. both need to be removed words loaded into
                 * arrayList
                 */
                if (label.contains("goes")) {
                    splitters = label.split(" goes ");
                } else {
                    splitters = label.split(" go ");
                }
                // splitter ArrayList values added individually to labelNoise
                for (String a : splitters)
                    labelNoise.add(a);
            }

        }
        // returns sounds ArrayList
        return sounds;
    }

    /**
     * loadAudio loads the complete audio portion of the file Audio.txt
     * 
     * @param endLine Variable landmark for the end of the line of audio input
     * @param in      The Scanner in order to read the Audio.txt file
     * @return The completed sounds ArrayList with all audio cues
     */
    public static ArrayList<String> loadAudio(String endLine, Scanner in) {
        // initialize arraylist and read in integer
        ArrayList<String> sounds = new ArrayList<>();

        /* 
        boolean trying = true;
            
        int lines = 0;
        //try to scan next line for int. if there is, then it exits. 
        //if not, then reads the empty line and tries again. 
        //not sure if this is how try catch works
        while(trying)
        {
            try {
                lines = Integer.parseInt(in.nextLine());
                trying = false;
                
            } catch (Exception e) {
                //TODO: handle exception
                String empty = in.nextLine();
                trying = true;
            }
    
        }
        */
        int lines = Integer.parseInt(in.nextLine());
        
        /*
         * reads next line of input for lines value splits the String and stores in
         * arraylist stores each split arraylist value into arraylist adds end of line
         * point in array after each cycle
         */
        for (int cnt = 0; cnt < lines; cnt++) {
            String record = in.nextLine();
            String[] recording = record.split(" ");
            for (String a : recording)
                sounds.add(a);

            sounds.add(endLine);

        }
        // returns arraylst
        return sounds;
    }

    /**
     * sortSounds compares the known animal sounds from the loadAnimals method to
     * the unknown animal sounds from the sounds ArrayList. If the sound is known,
     * then that sound is deleted from the ArrayList.
     * 
     * @param sounds       The ArrayList containing all audio recordings
     * @param animalNoises The ArrayList holding the known animal noises from the
     *                     loadAnimals method
     * @return The new sounds method containing only remaining fox noises
     */
    public static ArrayList<String> sortSounds(ArrayList<String> sounds, ArrayList<String> animalNoises) {
        // loops until cnt meets arraylist size
        for (int cnt = 1; cnt < animalNoises.size() - 1; cnt++) {
            // initiates size as an arrayList size. initiates counter
            int size = sounds.size();
            int cnt2 = 0;
            /*
             * while loop using values size and second counter for truth value compares
             * values of animalNoises ArrayList with sounds ArrayList
             */
            while (cnt2 < size) {
                if (animalNoises.get(cnt).equals(sounds.get(cnt2))) {
                    // removes slot from ArrayList
                    sounds.remove(cnt2);
                    // decreases counter by 1 and updates size
                    cnt2--;
                    size = sounds.size();
                }
                // increments counter by 1
                cnt2++;
            }
            // increments counter by 1 so every slot compared in animalNoises is an odd slot
            cnt++;

        }
        // returns ArrayList sounds
        return sounds;

    }

    /*
     * The quote the fox says is:
     * "the best ideas come as jokes. make your thinking as funny as possible." -
     * david ogilvy
     * 
     * Some extras found in the leftover sounds: "darn it man." "ovvatikatiti."
     * 
     */
}
