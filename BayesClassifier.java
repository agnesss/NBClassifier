package com.example.naivebayes;

import java.io.*;

/**
 * Created by hadoop on 4/16/16.
 */
public class BayesClassifier {


    //method used to train the algorithm
    public void train(String fileName){

        try {
            BufferedReader buf = new BufferedReader( new FileReader(new File(fileName)));
            Utils.processInputHeader(buf.readLine());
            String line;
            while(!(line = buf.readLine()).equals("")){

                String [] tokens = line.split(" ");

                for(int i=0; i< tokens.length; i++){
                    Utils.count(Utils.labels[i],tokens[i], tokens[tokens.length-1]);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //method used to test the algorithm with new input
    //it calculates probabilities for both possible cases: yes or no
    //which one is higher decides the result of classification
    public String test(String [] params){

        double probability1 = Utils.calculateProbability(params, true);
        System.out.println("Probability of YES is: "+probability1);
        double probability2 = Utils.calculateProbability(params, false);
        System.out.println("Probability of NO is: "+probability2);

        return probability1 > probability2 ? "yes" : "no";
    }
}
