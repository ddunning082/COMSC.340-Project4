/////////////////////////////////////////////////////////////
///Name: Nicholas Vieira	    		/////////////
///Date: 5/8/2023 (Updated on: 5/9/2023)	/////////////
///Professor: Professor Cates	    		/////////////
///Class: Analysis of Algorithms		/////////////
/////////////////////////////////////////////////////////////

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
	

class Algorithm6_1{	
	//Variables
	static int n = 4;
	static int W = 16;
	static int[] p = {0 ,40, 30, 50, 10};
	static int[] w = {0 ,2, 5, 10, 5};
	static int maxProfit; 
	static boolean[] bestSet = new boolean[n+1];
	
	

static void knapsack2() 
{
	Queue<Node> P = new LinkedList<Node>();	//Intialize Queue
	Node u, v;								//Intialize Nodes
	
	v = new Node();							//Node v info
	v.level = 0;
	v.profit = 0;
	v.weight = 0;
	v.included = new boolean[n+1];
	maxProfit = 0;
	P.add(v);
	
	while (!P.isEmpty()) 
	{
        	v = P.poll();
        	u = new Node();
            u.level = v.level + 1;		//Set u to a child to v
            u.weight = v.weight + w[u.level];	//Set u to the next item after child
            u.profit = v.profit + p[u.level];
            u.included = new boolean[n+1];
            
            //Gauges on which node have been included
            for(int i = 0; i <= n; i++) 
            {
            	u.included[i] = v.included[i];
            }
            u.included[u.level] = true;

            if (u.weight <= W && u.profit > maxProfit)
            {
            	maxProfit = u.profit;
            	for(int t = 0; t <= n; t++) 
            	{
            		bestSet[t] = u.included[u.level];
            	}
            }

            if (bound(u) > maxProfit) 
            {
                P.add(u);
            }
            
            u = new Node();
            u.level = v.level + 1;
            u.weight = v.weight;
            u.profit = v.profit;
            u.included = new boolean[n+1];
            
            for(int i = 0; i <= n; i++) 
            {
            	u.included[i] = v.included[i];
            }
            u.included[u.level] = false;

          
            if (bound(u) > maxProfit) 
            {
                P.add(u);
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


public static void main(String[] args) 
{
	
	knapsack2();
	
	System.out.println("There was a total profit of " + maxProfit);
    System.out.println("The items selected were " );
    System.out.println("Number of nodes visited was ");

}
}

class Node
{
	int level;
    int profit;
    int weight;
    boolean[] included; 
    
}
