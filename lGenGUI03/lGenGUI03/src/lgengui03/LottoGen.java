/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lgengui03;


import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class LottoGen {  
     
    private int[] array;        // merge sort variable
    private int[] tempMergArr;  // merge sort variable
    private int len;            // merge sort variable
    static int[] mcn;           // holds current "most common number" array 
    static int vlcntr	= 0;    // variable counter
    static int vllimit;         // variable counter limit, set by user 
// nullarray
    static int[] mcnNull        = {}; 
// >8
    static int[] mcnLLow	= {2,3,4,7,11,13,16,17,20,21,22,23,26,27,
                                  33,34,35,38,39,41}; 
// >9
    static int[] mcnLMed	= {2,3,7,8,11,13,14,16,19,24,28,29,35,36,38,39,42};
// >10
    static int[] mcnLHigh	= {2,3,6,7,8,14,16,29,35,36,39};
//{2,7,8,13,14,16,24,28,34,38,39,42};
    
    public static String intToString(int num) 
        {
            String w = Integer.toString(num);
            return w;
        }
    
    public static int stringToInt(String w)
        {
            int num = Integer.parseInt(w);
            return num;
        }

 public int[] strToIntArr (String s)    
    {
        String arr = s;
        String[] items = arr.replaceAll("\\[", "").replaceAll("\\]", "").replaceAll("\\s", "").split(",");
        int[] results = new int[items.length];
        int itemsLen = items.length;
        int r;
        for (r = 0; r < itemsLen; r++) {
            try {
                results[r] = Integer.parseInt(items[r]);
            } catch (NumberFormatException nfe) {
               //System.out.println("Numbers only, Dumbass!!");
               results[r] = 0;
    //NOTE: write something here if you need to recover from formatting errors
            };
        }
        return results;
    }

    public int compareStr(String pbDrawn, String ticket)
    {
        int[] lm = strToIntArr(ticket);
        int[] lt = strToIntArr(pbDrawn);
        int ltLen = lt.length;
        int count=0;
        int x, y;
        for(x=0; x<ltLen; x++)
        {
            for (y=0; y<ltLen; y++)
            {
                if (lm[x] == lt[y])
                {
                    count++;
                }
            }
        }
        return count;
    }    
// Converts an int array to a string    
    public static String intArrToString(int[] num) 
    {
        String w = Arrays.toString(num); 
        return w;
    }
/////////////////// Sorts ///////////////// 

/////////////// Insertion Sort /////////////
public void insertionSort(int ar[])
{
    //int n = ar.length();
    int i;
   //int[] d = strToIntArr (String (s));
   String res = intArrToString(ar);
   for (i = 1; i < res.length(); i++) {
      int index = ar[i]; 
      int j = i-1;
        while ((j > 0) && (ar[j-1] > index))
        {
             ar[j] = ar[j-1];
             j--;
        }
      ar[j] = index;
      }    
} 
//////////// End of Insertion Sort /////////////
////////////// Merge Sort ////////////////
    public void sort(int inputArr[]) {
        this.array = inputArr;
        this.len = inputArr.length;
        this.tempMergArr = new int[len];
        doMergeSort(0, len - 1);
    }
 
    private void doMergeSort(int lowerIndex, int higherIndex) {
         
        if (lowerIndex < higherIndex) {
            int middle = lowerIndex + (higherIndex - lowerIndex) / 2;
            // Below step sorts the left side of the array
            doMergeSort(lowerIndex, middle);
            // Below step sorts the right side of the array
            doMergeSort(middle + 1, higherIndex);
            // Now merge both sides
            mergeParts(lowerIndex, middle, higherIndex);
        }
    }
 
    private void mergeParts(int lowerIndex, int middle, int higherIndex) {
 
        for (int i = lowerIndex; i <= higherIndex; i++) {
            tempMergArr[i] = array[i];
        }
        int i = lowerIndex;
        int j = middle + 1;
        int k = lowerIndex;
        while (i <= middle && j <= higherIndex) {
            if (tempMergArr[i] <= tempMergArr[j]) {
                array[k] = tempMergArr[i];
                i++;
            } else {
                array[k] = tempMergArr[j];
                j++;
            }
            k++;
        }
        while (i <= middle) {
            array[k] = tempMergArr[i];
            k++;
            i++;
        } 
    }  
/////////////// Enf of Merge Sort //////////////
////////////////////////////////////////////////    
    public static int[] getRandomValues(int size)
    {
        int[]   values  = new int[size];// A working register to hold number data
        int[]   a       = new int[size];// Holds the lotto numbers we pick 
        boolean flag;
        //getMCN();
        int     d;
        Random  ranGen  = new Random();
        int     i       = 0; // counter
        a[i]            = 0; // Initialize a[0] to 0        
    // Start of while loop       
        while (i < size)
        {   
        // Make sure there are no duplicate numbers
            flag = false;                
            values[i] = 1 + ranGen.nextInt(42);// Colorado Lotto
            for(d=0; d<i; ++d)
            {
                if(a[d] == values[i])
                {
                    flag = true;
                    break;
                }
            }
        // Copy array and increment counter if no duplicates are in the array
            if(flag == false)
            {
                a[i] = values[i];
                i++;
            } 
        // Make sure there are enough MCNs in the array. If not enough MCNs 
        // are in the array, we reset the counter and repeat the while loop.
            if (i == size)
            {  
                compWMCN(a, mcn); // returns vlcntr

                if (vlcntr < vllimit)
                {
                    i = 0;
                    vlcntr = 0;  
                }
                //else System.out.print("\nLotto, # of MCNs picked: "+vlcntr+"\n");
                //else System.out.print(vlcntr+"\n");
            }           
        }
    // end of while loop
        return a;
    } 
// end of getRandomValues()

    
    public static int compWMCN (int[] a, int[] mcnIn)
    {        
        setMCN(mcnIn);
        int mcnLen	= getMCN().length;
        int aLen        = a.length;
        vlcntr = 0;
        for(int cnt=0; cnt<aLen; cnt++)
            {
                int[] bat = a;
                for (int c=0; c<mcnLen; c++)
                {
                    if (bat[cnt] == mcn[c])
                    {
                        vlcntr++;
                        //printInt(mcn[c]);
                    }
                }                        
            }
        return vlcntr;
    }  
       
    public static void setMCN(int[] a)
    {
        mcn = a;
    }
    
    public static int[] getMCN()
    {
        return mcn;
    }
    
/////////////////////////////////////////////////////
///////////////  Construction Zone  ///////////////// 
    
    public static int[] addElement(int[] a, int e) 
    {
        a  = Arrays.copyOf(a, a.length + 1);
        a[a.length - 1] = e;
        return a;
    }
    
    public static void setVlcntr(int n)
    {
        vlcntr = n;
    }
    
    public static int getVlcntr()
    {
        return vlcntr;
    }
    
    public static int setVllimit(int m)
    {
        vllimit = m;
        return vllimit;
    }

////////////////  End of Construction Zone  ///////////////  
///////////////////////////////////////////////////////////
    
    public static void print(int[] list)
    {
        int i;
        int listLen = list.length;
        for (i=0; i<listLen; i++)
        {
            System.out.print(list[i] + " ");
        }
       System.out.print(" \n");
    }
    
    public static int[] returnArr(int[] list)
    {
        //print(list);
        return list;
    }
    
    public static int returnInt(int n)
    {
        //System.out.print(n + " \n");
        return n;
    }
    public static void printInt(int n)
    {
        System.out.print(n + " \n");
        //return n;
    }
}




