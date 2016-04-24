package com.example.naivebayes;


import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Utils {


    //Map stores other maps, each one corresponding to a category/class.
    //key of the Map represents the name of the class
    //Ex: key - temperature
    //    value - the map that stores data about temperature
    public  static Map<String, Map<String, Counter>> CLASSES
            = new HashMap<String,Map<String, Counter>>();


    // array is used to store the categories(from the header of the text file)
    // Ex: outlook, temperature, etc..
    public static String []  labels;



    public static void count( String determinedClass, String key, String decision){

        Map<String, Counter> map =  CLASSES.get(determinedClass);

        if(map.get(key) == null){
            initialize(map, decision, key);
        }
        else{
            update(map, decision, key);
        }

    }

    //calculates the probabilities for each attribute the multiplies them to obtain the final result
    //method id used for both cases: calculate probability for 'yes' and 'no', depending on
    //the value of the boolean 'check'
    public static double calculateProbability(String[] params, boolean check){
        double result = 1;

        if(check){


                for(int i=0; i< params.length; i++){
                    Map<String, Counter> myMap = getClassForAttribute(params, params[i]);
                    result *= calculateYesProbability(myMap, params[i]);
                }

            result *= calculateDecisionProbability(true);

         }
        else{

            for(int i=0; i< params.length; i++){
                Map<String, Counter> myMap = getClassForAttribute(params, params[i]);
                result *= calculateNoProbability(myMap, params[i]);
            }

            result *= calculateDecisionProbability(false);


        }
        return result;
    }

    //initialize the counter of an attribute with value 1
    private  static void initialize(Map<String, Counter> map, String decision, String key){
        Counter counter = new Counter();
        counter.countAttribute = 1;
        countDecision(counter, decision);
        map.put(key, counter);
    }

    //increments the counter of an attribute
    private static void update(Map<String, Counter> map, String decision, String key){
        Counter counter = map.get(key);
        counter.countAttribute++;
        countDecision(counter, decision);
        map.put(key, counter);

    }

    //counts the number of 'yes' and 'no' occurrences from the training set
    private static void countDecision(Counter counter, String decision){
        if(decision.equals("yes"))
            counter.countYes++;
        else
            counter.countNo++;
    }

    //calculates the probability for the case where the result of the classification would be 'yes' category
    private static double calculateYesProbability(Map<String, Counter> map, String key){
        Counter counter = map.get(key);
        double numberYesConditionedValues = counter.countYes;
        Map<String, Counter> decision = CLASSES.get("decision");
        double numberTotalYesValues = decision.get("yes").countYes;
        return numberYesConditionedValues/numberTotalYesValues;
    }

    //calculates the probability for the case  where the result of the classification would be 'no' category
    private static double calculateNoProbability(Map<String, Counter> map, String key){

        Counter counter = map.get(key);
        double numberNoConditionedValues = counter.countNo;
        Map<String, Counter> decision = CLASSES.get("decision");
        double numberNoTotalValues  = decision.get("no").countNo;
        return numberNoConditionedValues/numberNoTotalValues;
    }

    //calculates the probability for each possible decision: 'yes' or 'no'
    private static double calculateDecisionProbability(boolean decision){
        Map<String, Counter> decisionMap = CLASSES.get("decision");
        Counter  counter = decisionMap.get("yes");
        Counter counter1 = decisionMap.get("no");

        double numberYesValues = counter.countYes;
        double numberNoValues  = counter1.countNo;

        return decision == true ? numberYesValues/(numberYesValues + numberNoValues)
                      : numberNoValues/(numberYesValues + numberNoValues);

    }

    //process the first line of the input file and identifies the classes/categories
    public static void processInputHeader(String header){
        String [] classesTok = header.split(" ");
        labels = classesTok;

        for(int i = 0; i< classesTok.length; i++){

            Map<String, Counter> classMap = new HashMap<String, Counter>();
            CLASSES.put(classesTok[i], classMap);
        }

    }

    //decides which class/category the given attribute (from the test set) belongs to
    public static Map<String, Counter> getClassForAttribute(String[] params, String attribute){

        Map<String, Counter> myMap = null;
        for(int i=0; i< params.length; i++){
            if(attribute.equals(params[i])){
                 myMap = CLASSES.get(labels[i]);
            }
        }
          return myMap;
    }


}
