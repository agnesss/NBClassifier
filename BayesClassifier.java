package com.example.naivebayes;

import java.io.*;

/**
 * Created by hadoop on 4/16/16.
 */
public class BayesClassifier {


    public void train(String fileName){

        try {
            BufferedReader buf = new BufferedReader( new FileReader(new File(fileName)));
            String line;
            while(!(line = buf.readLine()).equals("")){

                String [] tokens = line.split(" ");

                Utils.count(Utils.OUTLOOK, tokens[0], tokens[4]);
                Utils.count(Utils.TEMPERATURE, tokens[1], tokens[4]);
                Utils.count(Utils.HUMIDITY, tokens[2], tokens[4]);
                Utils.count(Utils.WIND, tokens[3], tokens[4]);
                Utils.count(Utils.DECISION, tokens[4], tokens[4]);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public String test(String outlook, String humidity, String temperature, String wind){

        double yes = Utils.calculateProbability(outlook, humidity, temperature, wind, true);
        System.out.println("Probability of YES is: "+yes);
        double no = Utils.calculateProbability(outlook, humidity, temperature, wind, false);
        System.out.println("Probability of NO is: "+no);

        return yes > no ? "yes" : "no";
    }
}
