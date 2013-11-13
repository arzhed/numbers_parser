import java.io.*;
import java.lang.System;
import java.util.Vector;
import java.util.LinkedList;

import static sun.misc.Version.print;
import static sun.misc.Version.println;

/**
 * Created with IntelliJ IDEA.
 * User: arzhed
 * Date: 11/11/13
 * Time: 4:07 PM
 * To change this template use File | Settings | File Templates.
 */
public class ParserInOut {

    private static String numbers[];
    private static Vector<LinkedList<Integer>> data = new Vector<LinkedList<Integer>>();

    public static void getInput(String filename) {
        try {
            FileInputStream filestream = new FileInputStream(filename);
            BufferedReader buffRead = new BufferedReader(new InputStreamReader(filestream));
            String line;
            int i=0;

            while ((line = buffRead.readLine()) != null) {
                if (line.length() > 0) {
                    numbers = line.split("#");
                }
            }

            buffRead.close();
        }
        catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void storeData(int i) {
        LinkedList<Integer> tmpList = new LinkedList<Integer>();
        tmpList.add(numbers[i].length());
        data.add(tmpList);

        while (numbers[i].length()>=4) {
            data.elementAt(i).add(Integer.parseInt(numbers[i].substring(0, 4)));
            numbers[i] = numbers[i].substring(4);
        }

        if(numbers[i].length()>0)
            data.elementAt(i).add(Integer.parseInt(numbers[i]));
    }

    public static void retrieveData(int i) {
        int length = data.elementAt(i).getFirst();
        System.out.println(length);
        String number="";
        String tmpNumber;
        for(int j=1; j<data.elementAt(i).size()-1;j++) {
            tmpNumber=Integer.toString(data.elementAt(i).get(j));
            switch (tmpNumber.length()) {
                case 1: tmpNumber = "000" + tmpNumber;
                    break;
                case 2: tmpNumber = "00" + tmpNumber;
                    break;
                case 3: tmpNumber = "0" +  tmpNumber;
                    break;
            }
            number += tmpNumber;
        }
        tmpNumber=Integer.toString(data.elementAt(i).getLast());
        int tmpNumberLength=tmpNumber.length();
        for (int k=0;k<(length-(data.elementAt(i).size()-2)*4-tmpNumberLength);k++)
            tmpNumber="0"+tmpNumber;
        number+=tmpNumber;
        System.out.println("number: "+ number);

    }

    public static void main(String[] args) {
        getInput(args[0]);
        for(int i=0; i<numbers.length;i++) {
            storeData(i);
            retrieveData(i);
        }
    }
}
