import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

public class StoreMap {
    public static void main(String[] args) {
        HashMap<Integer, String> Numbers = new HashMap();
        File file = new File("C:\\Users\\GBTC441010ur\\IdeaProjects\\Hashmap\\Numbers.txt");
        Scanner sc = new Scanner(System.in);
        String numInput = "", wordInput = "";

        try {
            // Read numbers from file, store them to HashMap
            readFile(file, Numbers);
        }
        catch(FileNotFoundException e) {
            print("File not found");
        }

        while(!numInput.equalsIgnoreCase("q") && !wordInput.equalsIgnoreCase("q")) {
            print("Enter a number or Q to quit: ");
            numInput = sc.nextLine();

            if(numInput.equalsIgnoreCase("q")){
                break;
            }

            // If corresponding word for integer is found, print to screen
            if (!returnWord(Integer.parseInt(numInput), Numbers).equals("Not found")) {
                print("You've entered" + returnWord(Integer.parseInt(numInput), Numbers));
            }
            // If word is not found, prompt user for word of integer entered
            else {
                print("\nWord does not exist for this number, please enter word for this number: ");
                wordInput = sc.nextLine();

                // Add num + word values to HashMap and file
                try {
                    addValue(file, Numbers, Integer.parseInt(numInput), wordInput);
                } catch (IOException e) {
                    print("IO error");
                }
            }
        }

    }

    protected static void readFile(File file, HashMap<Integer, String> Numbers) throws FileNotFoundException {
        Scanner sc = new Scanner(file);

        while(sc.hasNextLine()){
            Numbers.put(sc.nextInt(), sc.nextLine());
        }
    }

    protected static String returnWord(Integer input, HashMap<Integer, String> Numbers) {
        String s = "";

        Iterator it = Numbers.entrySet().iterator();
        while(it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            if(input == pair.getKey()) {
                s = pair.getValue().toString();
                return s;
            }
        }

        return "Not found";
    }

    protected static void addValue(File file, HashMap<Integer, String> Numbers, Integer input, String word) throws IOException {
        FileWriter fw = new FileWriter(file, true);
        BufferedWriter bw = new BufferedWriter(fw);

        bw.write("\n" + input + " " + word, 0, input.toString().length() + word.length() + 2);
        bw.close();

        Numbers.put(input, word);
    }

    protected static void print(String message){
        System.out.println(message);
    }
}
