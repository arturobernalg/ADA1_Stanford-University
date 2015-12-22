package main.java.Q5;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Arturo Bernal on 22/12/2015.
 */
public class Dijkstra {

    HashMap<Integer, ArrayList<Integer>> adjacencyMap = new HashMap<Integer, ArrayList<Integer>>();
    HashMap<Integer, ArrayList<Integer>> weightMap = new HashMap<Integer, ArrayList<Integer>>();

    HashMap<Integer, Integer> distanceMap = new HashMap<Integer, Integer>();
    HashMap<Integer, Integer> parentMap = new HashMap<Integer, Integer>();

    // constructor
    public Dijkstra(String fileName) {
        readIn(fileName);
    }

    // read in graph info
    public void readIn(String f) {
        try {
            File input1 = new File(f);
            FileReader stream1 = null;
            try {
                stream1 = new FileReader(input1);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            BufferedReader buffer1 = new BufferedReader(stream1);
            String string1 = null;
            try {
                string1 = buffer1.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            while (string1 != null) {
                String[] array1 = string1.split("\t");
                Integer Int1 = Integer.parseInt(array1[0]);
                if (adjacencyMap.get(Int1) == null) {
                    adjacencyMap.put(Int1, new ArrayList<Integer>());
                    weightMap.put(Int1, new ArrayList<Integer>());
                    distanceMap.put(Int1, (int) Double.POSITIVE_INFINITY);
                    parentMap.put(Int1, 0);
                }
                for (int i = 1; i < array1.length; i++) {
                    String[] array2 = array1[i].split(",");
                    Integer Int2 = Integer.parseInt(array2[0]);
                    Integer Int3 = Integer.parseInt(array2[1]);
                    adjacencyMap.get(Int1).add(Int2);
                    weightMap.get(Int1).add(Int3);
                }
                string1 = buffer1.readLine();
            }
            stream1.close();
        } catch (IOException e) {
            System.out.println("Could find the file.");
        }
    }

    // Dijkstra's algorithm for shortest paths
    public void pathDijkstra() {
        // initialize single source of node 1
        initializeSingleSource(1);

        Set<Integer> S = new HashSet<Integer>();
        Set<Integer> Q = new HashSet<Integer>();
        for (Integer key : adjacencyMap.keySet()) {
            Q.add(key);
        }

        while (!Q.isEmpty()) {
            int u = extraMin(Q);
            S.add(u);
            int i = 0;
            for (Integer value : adjacencyMap.get(u))
                relax(u, value, weightMap.get(u).get(i++));
        }
    }

    // initialize node source as the single source
    public void initializeSingleSource(int source) {
        distanceMap.put(source, 0);
    }

    // extract the minimum path
    public int extraMin(Set<Integer> qSet) {
        int min = (int) Double.POSITIVE_INFINITY;
        int int1 = 0;
        for (Integer value : qSet) {
            if (distanceMap.get(value) < min) {
                min = distanceMap.get(value);
                int1 = value;
            }
        }
        qSet.remove(int1);
        return int1;
    }

    // RELAX(u, v, w)
    public void relax(int   u, int v, int w) {
        if (distanceMap.get(v) > distanceMap.get(u) + w) {
            distanceMap.put(v, distanceMap.get(u) + w);
            parentMap.put(v, u);
        }
    }

    // print out requested results
    public void printOut() {
        int[] outputIndex = { 7, 37, 59, 82, 99, 115, 133, 165, 188, 197 };
        for (int i = 0; i < outputIndex.length; i++) {
            if (i == 0) {
                System.out.print(distanceMap.get(outputIndex[i]));
            } else {
                System.out.print("," + distanceMap.get(outputIndex[i]));
            }
        }
    }

    public static void main(String[] args) {
        // construct a graph based a file
        Dijkstra graph1 = new Dijkstra("src/main/resources/dijkstraData.txt");

        // call Dijkstra's algorithm
        graph1.pathDijkstra();

        // print out results as requested
        graph1.printOut();
    }

}