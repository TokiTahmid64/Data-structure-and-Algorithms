package Lab1;

import java.util.*;

public class topological_sort_with_cycle {


    public static boolean dfs_visit(ArrayList<ArrayList<Integer>> adj_list, int[] color, int i)
    {

        color[i]=1;
        for(int j=0;j<adj_list.get(i).size();j++)
        {

            if(color[adj_list.get(i).get(j)]==0)
            {

                return dfs_visit(adj_list,color,adj_list.get(i).get(j));

            }
            else if(color[adj_list.get(i).get(j)]==1) {
                return true;

            }


        }
        color[i]=2;
        return false;

    }
    public static boolean detect_cycle(ArrayList<ArrayList<Integer>> adj_list)
    {
        int n=adj_list.size();

        int color[]=new int[n];
        for(int i=0;i<n;i++)
        {
            if(color[i]==0)
            {
                boolean c=(dfs_visit(adj_list,color,i));

                if(c)
                    return true;

            }
        }
        return false;

    }
    public static void topsort_util(ArrayList<ArrayList<Integer>> adj_list, boolean[] visited, Stack stack, int i)
    {
        visited[i]=true;
        for(int j=0;j<adj_list.get(i).size();j++)
        {
            if(!visited[adj_list.get(i).get(j)])
            {
                visited[adj_list.get(i).get(j)]=true;
                topsort_util(adj_list,visited, stack, adj_list.get(i).get(j));
            }
        }
        stack.add(i);
    }
    public static void topsort(ArrayList<ArrayList<Integer>> adj_list, boolean[] visited, Stack stack)
    {
        int n=adj_list.size();
        for(int i=0;i<n;i++)
        {
            topsort_util(adj_list,visited, stack,i);
        }
        while(!stack.isEmpty())
        {
            System.out.println(stack.pop());

        }
    }

    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        ArrayList<ArrayList<Integer>> adj_list=new ArrayList();

        Stack stack= new Stack();
        int n=scanner.nextInt();
        int e=scanner.nextInt();
        boolean visited[]=new boolean[n];

        for(int i=0;i<n;i++)
        {
            adj_list.add(new ArrayList<Integer>());
        }
        for(int i=0;i<e;i++)
        {
            int x=scanner.nextInt()-1;
            int y=scanner.nextInt()-1;
            adj_list.get(x).add(y);
        }

        if(!detect_cycle(adj_list))
        {
            topsort(adj_list,visited,stack);
        }
        else
            System.out.println("Contains cycle");

    }
}
