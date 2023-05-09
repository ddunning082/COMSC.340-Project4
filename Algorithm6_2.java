/* Author: Donovan Dunning
 * Class:  CONSC.340
 * Project: 4
 * Date: 5/8/2023
 */


import java.util.PriorityQueue;
import java.util.Scanner;

public class Algorithm6_2 {
   static int[] maxProfit = new int[1];
    
    class Node implements Comparable<Node> {
        int level;
        int profit;
        int weight;
        float bound;

        @Override
        public int compareTo(Node other) {
            return Float.compare(other.bound, this.bound);
        }
    }

    public void knapsack3(int n, int[] p, int[] w, int W, int[] maxprofit) {
        PriorityQueue<Node> PQ = new PriorityQueue<>();
        Node u, v;

        v = new Node();
        v.level = 0;
        v.profit = 0;
        v.weight = 0;
        v.bound = bound(v, n, p, w, W);
        PQ.add(v);

        while (!PQ.isEmpty()) {
            v = PQ.poll();
            if (v.bound > maxprofit[0]) {
                u = new Node();
                u.level = v.level + 1;
                u.weight = v.weight + w[u.level];
                u.profit = v.profit + p[u.level];

                if (u.weight <= W && u.profit > maxprofit[0])
                    maxprofit[0] = u.profit;

                u.bound = bound(u, n, p, w, W);
                if (u.bound > maxprofit[0])
                    PQ.add(u);
                
                u = new Node();
                u.weight = v.weight;
                u.profit = v.profit;
                u.bound = bound(u, n, p, w, W);
                if (u.bound > maxprofit[0])
                    PQ.add(u);
            }
        }
    }

    public float bound(Node u, int n, int[] p, int[] w, int W) {
        int j, k;
        int totweight;
        float result;

        if (u.weight >= W)
            return 0;

        result = u.profit;
        j = u.level + 1;
        totweight = u.weight;

        while (j < n && totweight + w[j] <= W) {
            totweight += w[j];
            result += p[j];
            j++;
        }

        k = j;
        if (k < n)
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
    public static void main(String[] args){
    	Algorithm6_2 algorithm = new Algorithm6_2();
    	int n, W;
        int[] w = new int[4];
        int[] p = new int[4];
        System.out.println("Enter set number out of 1-5: ");
        Scanner sc = new Scanner(System.in);
        int input = sc.nextInt();
        //switch case inputting which set number for which test case
        algorithm.knapsack3(n, p, w, W, maxProfit);
        switch(input){
           case 1: W = 16;
                   p = {40, 30, 50, 10};
                   w = {2, 5, 10, 5};
                   break;
           case 2: W = 18;
                   p = {40, 30, 50,10};
                   w = {2, 5, 10, 5};
                   break;
           case 3: W = 25;
                   p = {50, 55, 15, 50};
                   w = {2, 10, 5, 20};
                   break;
           case 4: W = 40;
                   p = {50, 55, 15,50};
                   w = {2, 10, 5, 20};
                   break;
           case 5: W = 1;
                   p = {1, 1, 1, 1};
                   w = {2, 3, 4, 5};
                   break;
        }
        }
       
        System.out.println("There was a total profit of " + maxProfit);
        System.out.println("The items selected were ");
        System.out.println("Number of nodes visited was ");

     }
  }
}

