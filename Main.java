package com.example.naivebayes;

import java.util.Map;
import java.util.Set;

/**
 * Created by hadoop on 4/16/16.
 */
public class Main {

    public static void main(String [] args ){


        BayesClassifier bayesClassifier = new BayesClassifier();
        //Utils utils  = new Utils();
        bayesClassifier.train("input.txt");
        System.out.println("Classification result is: " + bayesClassifier.test(new String[]{"sunny", "cool",  "high", "strong"}));
        System.out.println("Classification result is: " + bayesClassifier.test(new String[]{"rain", "mild", "normal", "weak"}));



        //  <<<< Remove when done >>>>>>
        /*Set<String> keys = Utils.CLASSES.keySet();
        for(String key: keys){
            Map<String, Counter> myMap = Utils.CLASSES.get(key);
        }*/

    }
}
