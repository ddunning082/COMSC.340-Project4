/* Author: Donovan Dunning and Nicholas Vieira
 * Class:  CONSC.340
 * Project: 4
 * Date: 5/8/2023
 */

import java.util.PriorityQueue;

public class Algorithm6_2
{
	//Variables needed to test
	static int n = 4;
	static int W = 16;
	static int[] p = {0, 40, 30, 50, 10};	//Arrays padded to compensate for book starting at 1
	static int[] w = {0, 2, 5, 10, 5};
    static int maxProfit;
    static boolean[] bestSet = new boolean[n+1];
    static int numOfNode = 0;
    


    static void knapsack3() 
    {
        PriorityQueue<Node> PQ = new PriorityQueue<Node>();
        Node u, v;

        v = new Node();
        numOfNode++;
        v.level = 0;
        v.profit = 0;
        v.weight = 0;
        v.included = new boolean[n+1];
        v.bound = bound(v);
        PQ.add(v);

        while (!PQ.isEmpty()) 
        {
            v = PQ.poll();
            if (v.bound > maxProfit) {
                u = new Node();
                numOfNode++;
                u.level = v.level + 1;
                u.weight = v.weight + w[u.level];
                u.profit = v.profit + p[u.level];
                u.included = new boolean[n+1];
                
                for(int i = 0; i <= n; i++) 
                {
                	u.included[i] = v.included[i];
                }
                u.included[u.level] = true;

                if (u.weight <= W && u.profit > maxProfit)
                    maxProfit = u.profit;
                	for(int t = 0; t<= n; t++) 
                	{
                		bestSet[t] = u.included[t];
                	}

                u.bound = bound(u);
                
                if (u.bound > maxProfit)
                    PQ.add(u);
                
                u = new Node();
                numOfNode++;
                u.weight = v.weight;
                u.profit = v.profit;
                u.bound = bound(u);
                u.included = new boolean[n+1];
                
                for(int i = 0; i <= n; i++) 
                {
                	u.included[i] = v.included[i];
                }
                u.included[u.level] = false;
                
                if (u.bound > maxProfit)
                    PQ.add(u);
            }
        }
    }

    public static float bound(Node u) 
    {
        int j, k;
        int totweight;
        float result;

        if (u.weight >= W)
            return 0;

        result = u.profit;
        j = u.level + 1;
        totweight = u.weight;

        while (j <= n && totweight + w[j] <= W) 
        {
            totweight += w[j];
            result += p[j];
            j++;
        }

        k = j;
        if (k <= n)
            result += (W - totweight) * ((float) p[k] / w[k]);

        return result;
    }

    /*public static void main(String[] args) {
        Algorithm6_2 algorithm = new Algorithm6_2();
        int n = 4;
        int[] p = {10, 20, 30, 40};
        int[] w = {2, 5, 10, 15};
        int W = 30;
        int[] maxProfit = {0};
        algorithm.knapsack3(n, p, w, W, maxProfit);
        System.out.println("Max Profit: " + maxProfit[0]);
    }
    */
    public static void main(String[] args)
    {
      knapsack3();
      
      System.out.println("There was a total profit of " + maxProfit);
      for(int t = 1; t <= n; t++) 
  	  {
      	if(bestSet[t]) 
      	{
      		System.out.println("Items: " + t);
      	}
      	
  	  }
      System.out.println("Number of nodes visited was " + numOfNode);
           
     }
       
    
     }
  
  class Node implements Comparable<Node>
  {
      int level;
      int profit;
      int weight;
      float bound;
      boolean[] included;
      
      @Override
      public int compareTo(Node other) {
          return Float.compare(other.bound, this.bound);
      }
	}

 

