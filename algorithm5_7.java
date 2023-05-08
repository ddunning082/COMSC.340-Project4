////////////////////////////////////
//Author: Isabel Cyr
//5/9/2023
//COMSC 340
//Dr. Cates
//Algorithm 5.7 backtracking for knapsack problem
////////////////////////////////////

import java.util.Scanner;
import java.util.*;

Node n = new Node();
public class algorithm5_7{
   void knapsack(int i, int profit, int weight){
      if(weight <= W && profit > maxprofit){
         maxprofit = profit;
         numbest = i;
         bestset = include;
      }
      if(promising(i)){
         include[i+1] = "yes";
         knapsack(i+1, profit + p[i+1], weight + w[i+1]);
         include[i+1] = "no";
         knapsack(i+1, profit, weight);
      }
   }
   
   boolean promising (int i){
      int i,k;
      int totweight;
      float bound;
      
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
      int n, W;
      int w = new int[4];
      int p = new int[4];
      System.out.println("Enter set number out of 1-5: ");
      Scanner sc = new Scanner(System.in);
      int input = sc.nextInt();
      //switch case inputting which set number for which test case
      switch(input){
         case 1: W = 16;
                 int p = {40, 30, 50, 10};
                 int w = {2, 5, 10, 5};
                 break;
         case 2: W = 18;
                 int p = {40, 30, 50,10};
                 int w = {2, 5, 10, 5};
                 break;
         case 3: W = 25;
                 int p = {50, 55, 15, 50};
                 int w = {2, 10, 5, 20};
                 break;
         case 4: W = 40;
                 int p = {50, 55, 15,50};
                 int w = {2, 10, 5, 20};
                 break;
         case 5: W = 1;
                 int p = {1, 1, 1, 1};
                 int w = {2, 3, 4, 5};
                 break;
      }
      //extra pseudocode from the book
      numbest = 0;
      maxprofit = 0;
      knapsack(0, 0, 0);
      System.out.println(maxprofit);
      for(j=1; j<= numbest; j++) {
    	  System.out.println(bestset[j]);
      }
      //call the methods so it can run W
      	//knapsack(input);
      	//promising(input);
      //needs to print off for each data set
      System.out.println("There was a total profit of " + profit);
      System.out.println("The items selected were ");
      System.out.println("Number of nodes visited was ");

   }
}