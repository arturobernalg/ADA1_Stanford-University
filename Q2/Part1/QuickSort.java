package Part1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Arturo Bernal on 22/12/2015.
 */
public class QuickSort {
    static long noOfComparisons;
    public static void main(String args[]) throws IOException {

        BufferedReader bfr = new BufferedReader(new FileReader("resources/QuickSort.txt"));
        int n =10000;
        int[] A = new int[n];
        String str = bfr.readLine();
        int i = 0;
        while (str != null) {
            A[i] = Integer.parseInt(str);
            i++;
            str = bfr.readLine();
        }

        QuickSort(A,0,A.length-1);

        System.out.println(noOfComparisons);

    }

    private static void QuickSort(int[] a, int l, int r) {

        int pivot;
        if(r>l){
            add(r-l);
            pivot =Partition(a,l,r);

            QuickSort(a, l, pivot-1);
            QuickSort(a, pivot+1, r);
        }
    }

    private static void add(int i) {
        noOfComparisons+=i;

    }

    private static int Partition(int[] a, int l, int r) {


        int p=a[l];
        int i =l+1;

        for(int j=l+1;j<=r;j++){
            if(a[j]<p){
                Swap(a,j,i);
                i++;
            }
        }
        Swap(a, l, i-1);
        return (i-1);
    }



    private static void Swap(int[] a, int i, int j) {

        int temp =a[j];
        a[j]=a[i];
        a[i]=temp;
    }
}
