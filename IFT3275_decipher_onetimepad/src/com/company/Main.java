package com.company;

import java.io.*;
import java.util.*;

public class Main {

    public static ArrayList<String> readFile (String fileName){

        ArrayList<String> dataTab = new ArrayList<>();
        int index = 0;
        int position = 0;

        try{
            File file = new File(fileName);
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            StringBuilder sb = new StringBuilder();
            int c = 0;
            while((c = br.read()) != -1) {

                char data = (char) c;
                if(!Objects.equals(data, '\n')){

                    if (position == 0) {
                        sb.replace(0,8, String.valueOf(data));

                    }else if (position == 8) {
                        index ++;
                        position = 0;
                        dataTab.add(String.valueOf(sb));
                        sb.replace(0,8, String.valueOf(data));


                    } else {
                        sb.append(data);
                    }
                    position++;
                }
            }
        } catch(FileNotFoundException e){
            System.out.println("An error occured.");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return dataTab;
    }

    public static ArrayList<String> xorTab (ArrayList<String> tab1, ArrayList<String> tab2){

        ArrayList<String> xorTab = new ArrayList<>();

        for(int i=0; i<tab1.size(); i++){

            StringBuilder sb = new StringBuilder();
            for(int j = 0; j<8; j++){
                int comp = Character.compare(tab1.get(i).charAt(j),tab2.get(i).charAt(j));

                if (comp == 0){
                    sb.append('0');
                }else{
                    sb.append('1');
                }
            }
            xorTab.add(String.valueOf(sb));
        }
        return xorTab;
    }

    public static HashMap<String, Float> statistics (ArrayList<String> xorTab){
        HashMap<String, Integer> frequence = new HashMap<>();

        for (String s : xorTab) {
            if (frequence.containsKey(s)) {
                int oldF = frequence.get(s);
                frequence.replace(s, oldF + 1);
            } else {
                frequence.put(s, 1);
            }
        }

        int total= 1;
        for(Map.Entry<String, Integer> entry : frequence.entrySet()){
            String key = entry.getKey();
            Integer value = entry.getValue();
            total += value;
        }

        HashMap<String, Float> stats = new HashMap<>();
        System.out.println(total);

        for(Map.Entry<String, Integer> entry : frequence.entrySet()){
            String key = entry.getKey();
            Integer value = entry.getValue();

            float stat = (float) value / (float) total;
            stats.put(key, stat);
        }

        return stats;
    }
/*
    public static HashMap<String, Float> frenchStats (){

        HashMap<String, Float> stats = new HashMap<String, Float>();
        stats.put("_", 0.193);
        stats.put("e", 0.139);
        stats.put("a", 0.067);
        stats.put("s", 0.063);
        stats.put("", );
        stats.put("", );
        stats.put("", );
        stats.put("", );
        stats.put("", );
        stats.put("", );
        stats.put("", );
        stats.put("", );
        stats.put("", );
        stats.put("", );
        stats.put("", );
        stats.put("", );
        stats.put("", );
        stats.put("", );
        stats.put("", );
        stats.put("", );
        stats.put("", );
        stats.put("", );
        stats.put("", );
        stats.put("", );
        stats.put("", );
        stats.put("", );
        stats.put("", );

        return null;
    }*/

    public static void main(String[] args) {
	// write your code here

        ArrayList<String> dataTab1 = readFile("cipher1.txt");
        ArrayList<String> dataTab2 = readFile("cipher2.txt");
        ArrayList<String> xorTab = xorTab(dataTab1, dataTab2);/*
        for(Map.Entry<String, Float> entry : statistics(xorTab).entrySet()){
            String key = entry.getKey();
            Float value = entry.getValue();

            System.out.println(key + "  " + value);
        }*/

        for(int i =0; i<xorTab.size();i++){
            if (Objects.equals(xorTab.get(i), "01000101")) {
                dataTab1.set(i, "_");
                dataTab2.set(i, "_");
            }
            if (Objects.equals(xorTab.get(i), "")) {
                dataTab1.set(i, "e");
                dataTab2.set(i, "e");
            }/*
            if (Objects.equals(xorTab.get(i), "11111010")) {
                dataTab1.set(i, "_");
                dataTab2.set(i, "e");
            }
            if (Objects.equals(xorTab.get(i), "10111110")){
                dataTab1.set(i, "e");
                dataTab2.set(i, "_");
            }*/
            if(Objects.equals(xorTab.get(i), "10011101")){
                dataTab1.set(i,"o");
                dataTab2.set(i,"o");
            }
            if(Objects.equals(xorTab.get(i), "01000110")){
                dataTab1.set(i,"a");
                dataTab2.set(i,"a");
            }
            if(Objects.equals(xorTab.get(i), "11001001")){
                dataTab1.set(i,"s");
                dataTab2.set(i,"s");
            }/*
            if(Objects.equals(xorTab.get(i), "11111010")){
                if(Objects.equals(dataTab1.get(i+1),"_") || Objects.equals(dataTab1.get(i-1), "_")){
                    dataTab1.set(i,"e");
                    dataTab2.set(i,"_");
                }else if(Objects.equals(dataTab2.get(i+1),"_") || Objects.equals(dataTab2.get(i-1), "_")){
                    dataTab1.set(i, "_");
                    dataTab2.set(i, "e");
                }
            }*/


        }

        System.out.println(dataTab1);
        System.out.println(dataTab2);

    }
}
