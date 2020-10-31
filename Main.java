package com.company;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import static java.lang.System.exit;

public class Main {

    public static void main(String[] args) {

        HashMap<String, Integer> goodies = new HashMap<>();

        //Reading file
        try {
            File myObj = new File("sample_input.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String[] strings = myReader.nextLine().split(":");
                goodies.put(strings[0].trim(), Integer.valueOf(strings[1].trim()));
            }
            myReader.close();
        } catch (Exception e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            exit(0);
        }

        List<String> list = sortByValue(goodies);
        for (int i = 0; i < list.size(); i++) {
            System.out.println(i + " ==> " + list.get(i));
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("\nEnter Number of employee");
        int numberOfEmployee = scanner.nextInt();

        int min = 0;
        List<String> selectedList = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {

            //initialize 1st bach value
            if (i == 0) {
                int low = Integer.parseInt(list.get(i).split(",")[1]);
                int high = Integer.parseInt(list.get(i + numberOfEmployee - 1).split(",")[1]);
                min = high - low;
            }

            //check for minimum and assign
            if (i < list.size() - numberOfEmployee) {
                int low = Integer.parseInt(list.get(i).split(",")[1]);
                int high = Integer.parseInt(list.get(i + numberOfEmployee - 1).split(",")[1]);

                if ((high - low) < min) {
                    min = high - low;
                    selectedList = new ArrayList<>();
                    for (int j = i; j < i + numberOfEmployee; j++) {
                        selectedList.add(list.get(j));
                    }
                }
            }
        }

        String output = "The goodies selected for distribution are:\n\n";
        for (String str : selectedList) {
            output = output + str + "\n";
        }
        output = output + "\nAnd the difference between the chosen goodie with highest price and the lowest price is "
                + min;

        //Write File
        try {
            FileWriter myWriter = new FileWriter("sample_output.txt");
            myWriter.write(output);
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    // function to sort hashmap by values
    public static List<String> sortByValue(HashMap<String, Integer> hm) {
        List<Map.Entry<String, Integer>> list = new LinkedList<Map.Entry<String, Integer>>(hm.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });
        List<String> list1 = new ArrayList<>();
        for (Map.Entry<String, Integer> aa : list) {
            list1.add(aa.getKey() + "," + aa.getValue());
        }

        return list1;
    }
}
