/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minimumcostflow;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

/**
 *
 * @author zhuan
 */
public class MinimumCostFlow {

    static BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
    static int d;
    static int n;
    static int m;
    static Map<Integer,Set<Integer>> pipeConnectivity=new HashMap<Integer,Set<Integer>>();
    static Map<Pipe,Integer> pipeCosts=new HashMap<Pipe,Integer>();
    static Set<Pipe> currentPlan=new HashSet<Pipe>();
    
    static ArrayList<Set<Pipe>> minimumPlans=new ArrayList();
    static int minimunCost=0;
    static Stack<RunningPlan> runningPlans=new Stack();
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try {
            readInput();
            ArrayList<Set<Pipe>> plans=getMinimumCostPlans();
            int minDays=Integer.MAX_VALUE;
            for (Set<Pipe> plan:plans) {
                int days=getUpdateDays(plan);
                if (minDays>days) minDays=days;
            }
            System.out.println(minDays);
        } catch (IOException e) {
            System.out.println("Input Error");
        }
        
    }

    private static void readInput() throws IOException {
        String[] tokens=reader.readLine().split(" ");
        n=Integer.parseInt(tokens[0]);
        m=Integer.parseInt(tokens[1]);
        d=Integer.parseInt(tokens[2]);
        
        for (int i=0;i<m;i++) {
            tokens=reader.readLine().split(" ");
            int node1=Integer.parseInt(tokens[0]);
            int node2=Integer.parseInt(tokens[1]);
            Pipe pipe=new Pipe(node1,node2);
            setPipeConnectivity(pipe);
            pipeCosts.put(pipe, Integer.parseInt(tokens[2]));
            if (i<n-1) {
                currentPlan.add(pipe);
            }
        }
    }

    private static void setPipeConnectivity(Pipe pipe) {
        Set<Integer> conns;
        if (!pipeConnectivity.containsKey(pipe.n1)) {
            conns=new HashSet<Integer>();
        } else {
            conns=pipeConnectivity.get(pipe.n1);
        }
        conns.add(pipe.n2);
        
        
        if (!pipeConnectivity.containsKey(pipe.n2)) {
            conns=new HashSet<Integer>();
        } else {
            conns=pipeConnectivity.get(pipe.n2);
        }
        conns.add(pipe.n1);
    }

    private static int getUpdateDays(Set<Pipe> plan) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static ArrayList<Set<Pipe>> getMinimumCostPlans() {
        
        int startNode=0;
        while (runningPlans.isEmpty()) {
            startNode++;
            Set<Integer> connectedNodes=pipeConnectivity.get(startNode);
            
        }
    }
    
}

class Pipe {
    int n1;
    int n2;
    public Pipe(int node1, int node2) {
        if (node1<node2) {
            n1=node1;
            n2=node2;
        } else {
            n1=node2;
            n2=node1;
        }
    }
    
    @Override
    public boolean equals(Object o) {
        Pipe p=(Pipe)o;
        return (n1==p.n1 && n2==p.n2) ;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.n1;
        hash = 59 * hash + this.n2;
        return hash;
    }

    

    
    
   
}

