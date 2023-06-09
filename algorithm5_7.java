////////////////////////////////////
//Author: Isabel Cyr
//5/16/2023
//COMSC 340
//Dr. Cates
//Algorithm 5.7 backtracking for knapsack problem
////////////////////////////////////

import java.util.*;

public class algorithm5_7{
   public static int n = 4;
   public static int numbest = 0;
   public static int maxprofit = 0;
   public static int nodesVisited = 0;
   public static boolean [] bestset = new boolean[n+1];
   public static boolean []include = new boolean[n+1];
   
      static int [] p = {0, 40, 30, 50, 10};
      static int [] w = {0, 2, 5, 10, 5};
      static int W = 16;
      
      /*
      static int [] p1 = {0, 40, 30, 50, 10};
      static int [] w1 = {0, 2, 5, 10, 5};
      static int set1W = 16;
      
      static int [] p2 = {0, 40, 30, 50, 10};
      static int [] w2 = {0, 2, 5, 10, 5};
      static int set2W = 18;
            
      static int [] p3 = {0, 50, 55, 15, 50};
      static int [] w3 = {0, 2, 10, 5, 20};
      static int set3W = 25;
      
      static int [] p4 = {0, 50, 55, 15, 50};
      static int [] w4 = {0, 2, 10,5, 20};
      static int set4W = 40;
      
      static int [] p5 = {0, 1, 1, 1, 1};
      static int [] w5 = {0, 2, 3, 4, 5};
      static int set5W = 1;
      */
   static void knapsack(int i, int profit, int weight){
      nodesVisited++;
      
      if(weight <= W && profit > maxprofit){
         maxprofit = profit;
         numbest = i;
         for(int l =0; l<=n; l++){
            bestset[l] = include[l];
         }
      }
      if(promising(i, weight, profit)){
         include[i+1] = true;
         knapsack(i+1, profit + p[i+1], weight + w[i+1]);
         include[i+1] = false;
         knapsack(i+1, profit, weight);
      }
   }
   
   static boolean promising (int i, int weight, int profit){
      int k, j;
      int totweight = 0;
      float bound = 0;;
      
      if(weight >= W)
         return false;
      else{
         j = i+1;
         bound = profit;
         totweight = weight;
         while(j <= n && totweight + w[j] <= W){
            totweight = totweight + w[j];
            bound = bound + p[j];
         }
         k = j;
         if(k<=n)
            bound = bound + (W - totweight) * p[k] / w[k];
         return bound > maxprofit;
      }
   }
   
   public static void main(String [] args){
      knapsack(0, 0, 0);
      //knapsack(0, weight, profit);
      //needs to print off for each data set
      System.out.println("Set 2: ");
      System.out.println("There was a total profit of " + maxprofit);
      System.out.println("The items selected were ");
      for(int m = 1; m<=n; m++){
         if(bestset[m])
            System.out.println(m);
      }
      System.out.println("Number of nodes visited was " + nodesVisited);
      
      /*
      System.out.println("Set 2: ");
      System.out.println("There was a total profit of " + maxprofit);
      System.out.println("The items selected were ");
      System.out.println("Number of nodes visited was " + nodesVisited);
      
      /*
      System.out.println("Set 3: ");
      System.out.println("There was a total profit of " + maxprofit);
      System.out.println("The items selected were ");
      System.out.println("Number of nodes visited was " + nodesVisited);
      
      System.out.println("Set 4: ");
      System.out.println("There was a total profit of " + maxprofit);
      System.out.println("The items selected were ");
      System.out.println("Number of nodes visited was " + nodesVisited);
      
      System.out.println("Set 5: ");
      System.out.println("There was a total profit of " + maxprofit);
      System.out.println("The items selected were ");
      System.out.println("Number of nodes visited was " + nodesVisited);
      */

   }
}
