/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lgengui03;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Vector;
import java.util.Stack;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
                        
/**
 *
 * @author jrom8
 */

public class CompareLines {
         
//public static String fileNameA = "g:/_Java/compareLines/compareLinesResult.txt";    
public static String fileNameA = "g:/_Java/compareLines/clResult.txt";
public static String fileName = "f:/_Java_Swing/Swing/lGenGUI03/lottoArraysTest1.txt";
public static String op;
public static List listA = new ArrayList();    
public static List listB = new ArrayList();
public static int listLength = 0;

public CompareLines () throws IOException {
    listA = getTextFileByLines(fileNameA);
    Iterator iterator = listA.iterator();
    while(iterator.hasNext()){
        String str = (String) iterator.next();   
        System.out.println(str);         
    }         
}

public CompareLines (String s) throws IOException {
    listA = getTextFileByLines(fileNameA);
    boolean flag = false;
    String str = null;
    String copy = null;
    //int[] intArr = strToIntArr(s);
    Iterator<String> iterator = listA.iterator();
    while(iterator.hasNext()){
        str = iterator.next();
        //int i = 0;
        if (s.equals(str)) {
            //i++;
            int[] stia = strToIntArr(str);
            int[] ostia = strToIntArr(s);
            int cnt;
            for (cnt = 0; cnt < 8; cnt++){
            stia[7] += ostia[7];
            }
            op = intArrToString(stia);
            flag = true;
            //lGenGUI03.writeToLottoArr(op);
            appendToTextFile (fileNameA, (" " + op));
            System.out.println("Equal");    
            //System.out.println(str);    
            System.out.println(op);
            printListA();
        }else {
            copy = s;
            //System.out.println("Not equal");    
            //System.out.println(str);
        }
    }
        if (!flag) {
            appendToTextFile (fileNameA, copy);
            //printListA(copy);
            printListA();
        }       
}

public CompareLines (int[] a) throws IOException{
    listA = new ArrayList();
    Iterator iterator = listA.iterator();
    while(iterator.hasNext()){
        int[] intArray = (int[]) iterator.next();
        if (Arrays.equals(a, intArray)) { 
            intArray[7] += a[7];
        }else {
            listA.add(a);
        }        
    }
}

//////////////// Methods //////////////////
////////// Setters and Getters ////////////

public static List copyList (List list) {
    List cl = new ArrayList();
    Iterator iterator = list.iterator();
    while(iterator.hasNext()){
        String str = (String) iterator.next();
        cl.add(str);
    }
    return cl;
}

public static void printListA () throws IOException {
    listA = getTextFileByLines(fileNameA);
    List d = new ArrayList();
    String copy;
    Iterator<String> iterator = listA.iterator();
    while(iterator.hasNext()){
        copy = iterator.next();
        //copy = str;
        d.add(copy);
    }
    System.out.println(d);
}

public static void printListA (String line) throws IOException {
    listA = getTextFileByLines(fileNameA);
    List d = new ArrayList();
    int count = returnLength(listA);
    String copy;
    Iterator<String> iterator = listA.iterator();
    while(iterator.hasNext()){
        count++;
        String str = iterator.next();
        copy = str;
        d.add(count,copy);
        //System.out.println(str);
    }
    //listB = copyList(listA);    
    //System.out.println(listA);
    d.add(line);
    System.out.println(d);
    //return d;
}

public static int returnLength (List s) {
    Iterator<String> iterator = s.iterator();
    int i = 0;
    while(iterator.hasNext()){
        i++;
    }
    return i;
}

public static int[] getTextFile (String fn) throws IOException {
        String input = new String(Files.readAllBytes(Paths.get(fn)));
        int[] content = strToIntArr(input);
        return content;
    }
    
 

////////////// Text File Methods //////////
    public static String readTextFile (String fn) throws IOException {
        String content = new String(Files.readAllBytes(Paths.get(fn)));
        return content;
    }    
    
    public static List<String> readTextFileByLines (String fn) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(fn));        
        return lines;
    }
    
    public static List<String> getTextFileByLines (String fn) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(fn));        
        return lines;
    }
   
    public static void appendToTextFile (String fn, 
            String content) throws IOException {
        Files.write(Paths.get(fn), content.getBytes(), 
                StandardOpenOption.APPEND);
    }

    //////////// End of Text File Methods ////////////
    //////////////////////////////////////////////////

// Converts a string to an int array   
    public static int[] strToIntArr (String s)    
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

    public static int compareStr(String pbDrawn, String ticket)
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
    
    public static boolean compareLottoArrays (String m, String n) {
        boolean result = false; 
        if (compareStr(m, n) == 6) result = true;
        return result;
    }
    
    public static boolean compareLottoArrays (int[] m, int[] n) {
        boolean result = false; 
        String strM = intArrToString(m);
        String strN = intArrToString(n);
        if (compareStr(strM, strN) == 6) result = true;
        return result;
    }
	
	public static int[] addElements(int[] a, int b, int c) {
        a = Arrays.copyOf(a, a.length + 2);
        a[a.length - 2] = b;
        a[a.length - 1] = c;
        return a;
    }
        
        public static int[] insertionSort(int[] ar) {
        //int[] result;
        int i;
        for (i = 1; i < ar.length; i++) {
            int index = ar[i];
            int j = i;
            while(j > 0 && ar[j-1] > index) {
                ar[j] = ar[j-1];
                j--;
            }
            ar[j] = index;
        }
        return ar;
    }
        
        public static void printFile() {
            //String fileName = "g:/_Java/compareLines/compareLinesResult.txt"; 
            String line;
            try {
                FileReader fr = new FileReader(fileNameA);
                try (BufferedReader br = new BufferedReader(fr)) {
                    while((line = br.readLine()) != null) {
                        System.out.println(line);
                    }
                }
            }catch (Exception e){
            
        }
            
} 
/* 
    public static void main(String[] args) throws IOException {
        //System.out.println();
        Scanner arrIn = new Scanner(System.in);
        Scanner appIn = new Scanner(System.in);
        String strIn;
        System.out.print("Enter number: ");
        strIn = arrIn.nextLine();
        //int[] d = insertionSort(strToIntArr(strIn));
        //String outPut = intArrToString(d);
        //CompareLines c1 = new CompareLines();
        CompareLines c = new CompareLines(strIn);
        //printFile();
        //System.out.print("Append file? Y or N");
        //String af = appIn.next();
        //CompareLines c2 = new CompareLines(strIn);
    }
*/    
}
