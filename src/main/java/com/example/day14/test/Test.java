package com.example.day14.test;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Queue;

public class Test {
  public static void main(String args[] ) throws Exception {
    String inputData = " 3 2 \n This is a test line. \n This line is test two.\n The third of the test lines.";
    // String thisLine = null;
    // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    // while ((thisLine = br.readLine()) != null) {
    //     inputData += thisLine + "\n";
    // }
    // Output the solution to the console
    System.out.println(inputData);
    System.out.println(codeHere(inputData));
  }
  public static String codeHere(String inputData) {
    // Use this function to write your solution;
    if(inputData==null || inputData.length()==0)return null;
    inputData = inputData.trim();
    String[] lines = inputData.split("\n");
    int n = Integer.parseInt(lines[0].split(" ")[0]);
    int k = Integer.valueOf(lines[0].split(" ")[1]);

    Map<String,Integer> map = new HashMap<>();

    for(int i=1;i<lines.length;i++){
      String[] line = lines[i].split(" ");
      for(String word:line){
        if(word.equals("."))continue;
        if(map.containsKey(word)){
          int count = map.get(word);
          map.put(word, count+1);
        }else{
          map.put(word, 1);
        }
      }
    }

    Queue<Entry<String,Integer>> queue = new PriorityQueue<>(new Comparator<Entry<String,Integer>>(){
      @Override
      public int compare(Map.Entry<String,Integer> entry1,Map.Entry<String,Integer> entry2){
        if(entry1.getValue()==entry2.getValue()){
          return entry1.getKey().compareTo(entry2.getKey());
        }else{
          return entry2.getValue()-entry1.getValue();
        }
      }
    });

    queue.addAll(map.entrySet());
    StringBuilder sb = new StringBuilder();
    String res ="";
    while(k>0){
      Map.Entry<String,Integer> entry = queue.poll();
      String word = entry.getKey();
      System.out.println(word);
      int count = entry.getValue();
      sb.append(word+" "+count+"\n");
      res+=word+" "+count+"\n";
      // System.out.println(k);
      k--;
    }
    // System.out.println(sb.toString());
    // System.out.println(res);
    return sb.toString();
  }

}
