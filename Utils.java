package com.example.naivebayes;


import java.util.HashMap;
import java.util.Map;

public class Utils {


    public  static Map<String, Counter> OUTLOOK
            = new HashMap<String, Counter>();
    public  static Map<String, Counter> TEMPERATURE
            = new HashMap<String, Counter>();
    public  static Map<String, Counter> HUMIDITY
            = new HashMap<String, Counter>();
    public  static Map<String, Counter> WIND
            = new HashMap<String, Counter>();
    public  static Map<String, Counter> DECISION
            = new HashMap<String, Counter>();




    public static void count(Map<String, Counter> map, String key, String decision){

        if(map.get(key) == null){
            initialize(map, decision, key);
        }
        else{
            update(map, decision, key);
        }

    }


    public static double calculateProbability(String outlook, String humidity,String temperature, String wind, boolean check){
        double result = 0;

        if(check){

            result  = calculateYesProbability(OUTLOOK, outlook) * calculateYesProbability(HUMIDITY, humidity)
                           * calculateYesProbability(TEMPERATURE,temperature)
                              * calculateYesProbability(WIND, wind) *  calculateDecisionProbability(true);


         }
        else{

            result = calculateNoProbability(OUTLOOK, outlook) * calculateNoProbability(HUMIDITY, humidity)
                           * calculateNoProbability(TEMPERATURE, temperature)
                               *calculateNoProbability(WIND, wind) * calculateDecisionProbability(false);

        }
        return result;
    }





    private  static void initialize(Map<String, Counter> map, String decision, String key){
        Counter counter = new Counter();
        counter.countAttribute = 1;
        countDecision(counter, decision);
        map.put(key, counter);
    }

    private static void update(Map<String, Counter> map, String decision, String key){
        Counter counter = map.get(key);
        counter.countAttribute++;
        countDecision(counter, decision);
        map.put(key, counter);

    }

    private static void countDecision(Counter counter, String decision){
        if(decision.equals("yes"))
            counter.countYes++;
        else
            counter.countNo++;
    }


    private static double calculateYesProbability(Map<String, Counter> map, String key){
        Counter counter = map.get(key);
        double numberYesConditionedValues = counter.countYes;
        double numberTotalYesValues = DECISION.get("yes").countYes;
        return numberYesConditionedValues/numberTotalYesValues;
    }

    private static double calculateNoProbability(Map<String, Counter> map, String key){

        Counter counter = map.get(key);
        double numberNoConditionedValues = counter.countNo;
        double numberNoTotalValues  = DECISION.get("no").countNo;
        return numberNoConditionedValues/numberNoTotalValues;
    }

    private static double calculateDecisionProbability(boolean decision){
        Counter  counter = DECISION.get("yes");
        Counter counter1 = DECISION.get("no");

        double numberYesValues = counter.countYes;
        double numberNoValues  = counter1.countNo;

        return decision == true ? numberYesValues/(numberYesValues + numberNoValues)
                      : numberNoValues/(numberYesValues + numberNoValues);

    }
}
