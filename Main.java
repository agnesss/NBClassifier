package com.example.naivebayes;

import java.util.Set;

/**
 * Created by hadoop on 4/16/16.
 */
public class Main {

    public static void main(String [] args ){


        BayesClassifier bayesClassifier = new BayesClassifier();
        bayesClassifier.train("input.txt");
        System.out.println("Classification result is: " + bayesClassifier.test("sunny", "high", "cool", "strong"));
        System.out.println("Classification result is: " + bayesClassifier.test("rain", "normal", "mild", "weak"));


    }
}
