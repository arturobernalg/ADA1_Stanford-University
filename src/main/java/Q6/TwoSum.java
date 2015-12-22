package main.java.Q6;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Arturo Bernal on 22/12/2015.
 */
public class TwoSum {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        ArrayList<Long> array = readNumbersArrayFromFile();
        Collections.sort(array);

        int counter = 0;

        for(int sum = -10000; sum <= 10000; sum++)
        {
            int start = 0;
            int end = array.size() - 1;

            boolean found = false;

            while(!found && start < end)
            {
                if(array.get(start) + array.get(end) == sum)
                {
                    if(array.get(start).longValue() != array.get(end).longValue()){
                        found = true;
                    }
                }
                else if(array.get(start) + array.get(end) > sum){
                    end--;
                }
                else if(array.get(start) + array.get(end) < sum){
                    start++;
                }
            }

            if(found){
                counter++;
            }
        }

        System.out.println("Result -->  " + counter + " ***");
    }


    /**
     * Reads the Long array to be used as input for the assignment
     * @return A Long array
     */
    private static ArrayList<Long> readNumbersArrayFromFile()
    {
        ArrayList<Long> longArray = new ArrayList<Long>();

        FileInputStream fstream = null;
        try
        {
            fstream = new FileInputStream("src/main/resources/algo1-programming_prob-2sum.txt");

            // Get the object of DataInputStream
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));

            String line;
            while ((line = br.readLine()) != null){
                longArray.add(Long.valueOf(line));
            }

            br.close();
        }catch (FileNotFoundException ex) {
        }catch (IOException ex) {
        }finally {
            try {
                fstream.close();
            } catch (IOException ex) {
            }
        }

        return longArray;
    }
}
