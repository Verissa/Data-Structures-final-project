package project;
import java.util.*;


public class GraphADT {
	// No. of vertices in graph 
    int v;  
      
    // adjacency list  
    ArrayList<Integer>[] adjList;  
      
    //Constructor 
    public GraphADT(int vertices){ 
          
        //initialise vertex count 
        this.v = vertices; 
          
        // initialise adjacency list 
        initAdjList();  
    } 
      
    // method to initialise 
    // adjacency list 
    private void initAdjList() 
    { 
        adjList = new ArrayList[v]; 
          
        for(int i = 0; i < v; i++) 
        { 
            adjList[i] = new ArrayList<>(); 
        } 
    } 
      
    // add path from location u to location v 
    public void addPath(int u, int v) 
    { 
        // Add v to u's list. 
        adjList[u].add(v);  
    } 
      
    // Prints all paths from 
    // start to destination 
    public void printAllPaths(int s, int d)  
    { 
        boolean[] isVisited = new boolean[v]; 
        ArrayList<Integer> pathList = new ArrayList<>();
        ArrayList<String> pathnames = new ArrayList<>();
        int i = 0;
          
        //add source to path[] 
        pathList.add(s); 
          
        //recursive call 
        printAllPathsRec(s, d, isVisited, pathList); 
        
        
        String[] map = {"Security checkpoint","Big Ben", "On campus hostels", "Administration block","Faculty offices", "MPR", "Essentials", "Library", 
        		"Archer Cornfield Courtyard","LH 115", "LH 116", "LH 216", "LH 217", "LH 218", "Engineering building", "Ashesi shop", 
        		"Research building","Akorno", "Fab Lab", "Health centre", "Sports centre" };
        for(int index = 0; index < pathList.size(); index ++) {
        	i = pathList.get(index);
        	pathnames.add(map[i]);
        }
        //System.out.println(pathnames);

    } 
  
    // A recursive function to print 
    // all paths from 'u' to 'd'. 
    // isVisited[] keeps track of 
    // vertices in current path. 
    // localPathList<> stores actual 
    // vertices in the current path 
    private void printAllPathsRec(Integer u, Integer d, 
                                    boolean[] isVisited, 
                            List<Integer> localPathList) { 
          
        // Mark the current node 
        isVisited[u] = true; 
          
        if (u.equals(d))  
        { 
            System.out.println(localPathList); 
            ArrayList<String> pathnames = new ArrayList<>();
            int i = 0;
            String[] map = {"Security checkpoint","Big Ben", "On campus hostels", "Administration block","Faculty offices", "MPR", "Essentials", "Library", 
            		"Archer Cornfield Courtyard","LH 115", "LH 116", "LH 216", "LH 217", "LH 218", "Engineering building", "Ashesi shop", 
            		"Research building","Akorno", "Fab Lab", "Health centre", "Sports centre" };
            for(int index = 0; index < localPathList.size(); index ++) {
            	i = localPathList.get(index);
            	pathnames.add(map[i]);
            	
            }
          System.out.println(pathnames);
        } 
          
        // Recur for all the vertices 
        // adjacent to current vertex 
        for (Integer i : adjList[u])  
        { 
            if (!isVisited[i]) 
            { 
                // store current node  
                // in path[] 
                localPathList.add(i); 
                printAllPathsRec(i, d, isVisited, localPathList); 
                  
                // remove current node 
                // in path[] 
                localPathList.remove(i); 
            } 
        } 
          
        // Mark the current node 
        isVisited[u] = false; 
    }  

	// Function to find out maximum valued node 
	// among the nodes which are not yet included in MST 
	public static int maxnode(int n, int[] keyval, boolean[] mstset)
	{
	int max = Integer.MAX_VALUE;
	int max_index = 0;

	// Loop through all the values of the nodes 
	// which are not yet included in MST and find 
	// the maximum valued one. 
	for (int i = 0; i < n; i++)
	{
		if (mstset[i] == false && keyval[i] < max)
		{
		max = keyval[i];
		max_index = i;
		}
	}
	return max_index;
	}

	// Function to find out the MST and 
	// the time duration of the MST. 
	public static void findmaxtime(int n, ArrayList<ArrayList<Integer>> location)
	{

	// Array to store the parent node of a 
	// particular node. 
	int[] parent = new int[n];

	// Array to store key value of each node. 
	int[] keyval = new int[n];

	// Boolean Array to hold bool values whether 
	// a node is included in MST or not. 
	boolean[] mstset = new boolean[n];

	// Set all the key values to infinite and 
	// none of the nodes is included in MST. 
	for (int i = 0; i < n; i++)
	{
		keyval[i] = Integer.MAX_VALUE;
		mstset[i] = false;
	}

	// Start to find the MST from node 0. 
	// Parent of node 0 is none so set -1. 
	// key value or maximum time to reach 
	// 0th node from 0th node is 0. 
	parent[0] = -1;
	keyval[0] = 0;

	// Find the rest n-1 nodes of MST. 
	for (int i = 0; i < n - 1; i++)
	{

		// First find out the maximum node 
		// among the nodes which are not yet 
		// included in MST. 
		int u = maxnode(n, keyval, mstset);

		// Now the uth node is included in MST. 
		mstset[u] = true;

		// Update the values of neighbour 
		// nodes of u which are not yet 
		// included in MST. 
		for (int v = 0; v < n; v++)
		{

		if (location.get(u).get(v) != 0 && mstset[v] == false && location.get(u).get(v) < keyval[v])
		{
			keyval[v] = location.get(u).get(v);
			parent[v] = u;
		}
		}
	}

	// Find out the total time by adding 
	// the edge values of MST. 
	int time = 0;
	for (int i = 1; i < n; i++)
	{
		time += location.get(parent[i]).get(i);
	}
	System.out.print("The longest path takes " + time + " minutes.");
	System.out.print("\n");
	}

	// Function to find out minimum valued node 
		// among the nodes which are not yet included in MST 
		public static int minnode(int n, int[] keyval, boolean[] mstset)
		{
		int mini = Integer.MIN_VALUE;
		int mini_index = 0;

		// Loop through all the values of the nodes 
		// which are not yet included in MST and find 
		// the minimum valued one. 
		for (int i = 0; i < n; i++)
		{
			if (mstset[i] == false && keyval[i] < mini)
			{
			mini = keyval[i];
			mini_index = i;
			}
		}
		return mini_index;
		}

		// Function to find out the MST and 
		// the time duration of the MST. 
		public static void findmintime(int n, ArrayList<ArrayList<Integer>> location)
		{

		// Array to store the parent node of a 
		// particular node. 
		int[] parent = new int[n];

		// Array to store key value of each node. 
		int[] keyval = new int[n];

		// Boolean Array to hold bool values whether 
		// a node is included in MST or not. 
		boolean[] mstset = new boolean[n];

		// Set all the key values to infinite and 
		// none of the nodes is included in MST. 
		for (int i = 0; i < n; i++)
		{
			keyval[i] = Integer.MIN_VALUE;
			mstset[i] = false;
		}

		// Start to find the MST from node 0. 
		// Parent of node 0 is none so set -1. 
		// key value or minimum time to reach 
		// 0th node from 0th node is 0. 
		parent[0] = -1;
		keyval[0] = 0;

		// Find the rest n-1 nodes of MST. 
		for (int i = 0; i < n - 1; i++)
		{

			// First find out the minimum node 
			// among the nodes which are not yet 
			// included in MST. 
			int u = minnode(n, keyval, mstset);

			// Now the uth node is included in MST. 
			mstset[u] = true;

			// Update the values of neighbour 
			// nodes of u which are not yet 
			// included in MST. 
			for (int v = 0; v < n; v++)
			{

			if (location.get(u).get(v) != 0 && mstset[v] == false && location.get(u).get(v) < keyval[v])
			{
				keyval[v] = location.get(u).get(v);
				parent[v] = u;
			}
			}
		}

		// Find out the total time by adding 
		// the edge values of MST. 
		int time = 0;
		for (int i = 1; i < n; i++)
		{
			time += location.get(parent[i]).get(i);
		}
		System.out.print("The shortest path takes " + time + " minutes.");
		System.out.print("\n");
		}

	public static void main(String args[])  {
		// TODO Auto-generated method stub
		// Input 1 
		// Create a sample graph 
        GraphADT g = new GraphADT(21); 
        g.addPath(0,1); 
        g.addPath(0,2); 
        g.addPath(0,3);
        g.addPath(0,18);
        g.addPath(1,2); 
        g.addPath(1,3); 
        g.addPath(2,3); 
        g.addPath(3,4); 
        g.addPath(3,8); 
        g.addPath(3,9); 
        g.addPath(4,5); 
        g.addPath(5,6); 
        g.addPath(6,7); 
        g.addPath(7,11);
        g.addPath(7,14);
        g.addPath(8,9);
        g.addPath(8,11);
        g.addPath(9,10);
        g.addPath(10,12);
        g.addPath(11,12);
        g.addPath(12,13);
        g.addPath(12,15);
        g.addPath(14,16);
        g.addPath(14,18);
        g.addPath(15,16);
        g.addPath(16,17);
        g.addPath(18,19);
        g.addPath(19,20);


        // Start location, Security checkpoint
        int s = 0; 
        
      
        // Destination Engineering building 
        int d = 14; 
//
//        ArrayList<String> pathnames = new ArrayList<>();
//        int i = 0;
        String[] map = {"Security checkpoint","Big Ben", "On campus hostels", "Administration block","Faculty offices", "MPR", "Essentials", "Library", 
        		"Archer Cornfield Courtyard","LH 115", "LH 116", "LH 216", "LH 217", "LH 218", "Engineering building", "Ashesi shop", 
        		"Research building","Akorno", "Fab Lab", "Health centre", "Sports centre" };
//        for(int index = 0; index < localPathList.size(); index ++) {
//        	i = localPathList.get(index);
//        	pathnames.add(map[i]);
//        	index++;
//        	
//        }
      //System.out.println(pathnames);
      
        System.out.println("Following are all different paths from "+map[s]+" to "+map[d]); 
        g.printAllPaths(s, d);
	
		int n1 = 21;
		ArrayList<ArrayList<Integer>> Ashesimap = new ArrayList<ArrayList<Integer>>
		(Arrays.asList
				(
				new ArrayList<Integer>(Arrays.asList(0, 11, 7, 5, 0,0,0,0,0,0,0,0,0,0,0,0,0,0,7,0,0)), 
				new ArrayList<Integer>(Arrays.asList(11, 0, 3, 5, 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0)), 
				new ArrayList<Integer>(Arrays.asList(7, 3, 0, 5, 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0)), 
				new ArrayList<Integer>(Arrays.asList(5, 5, 5, 0, 3,0,0,0,5,5,0,0,0,0,0,0,0,0,0,0,0)), 
				new ArrayList<Integer>(Arrays.asList(0, 0, 0, 3, 0,3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0)), 
				new ArrayList<Integer>(Arrays.asList(0, 0, 0, 0, 3,0,3,0,0,0,0,0,0,0,0,0,0,0,0,0,0)),
				new ArrayList<Integer>(Arrays.asList(0, 0, 0, 0, 0,3,0,7,0,0,0,0,0,0,0,0,0,0,0,0,0)),
				new ArrayList<Integer>(Arrays.asList(0, 0, 0, 0, 0,0,7,0,0,0,0,5,0,0,4,0,0,0,0,0,0)),
				new ArrayList<Integer>(Arrays.asList(0, 0, 0, 5, 0,0,0,0,0,3,0,5,0,0,0,0,0,0,0,0,0)), 
				new ArrayList<Integer>(Arrays.asList(0, 0, 0, 5, 0,0,0,0,3,0,3,0,0,0,0,0,0,0,0,0,0)), 
				new ArrayList<Integer>(Arrays.asList(0, 0, 0, 0, 0,0,0,0,0,3,0,0,4,0,0,0,0,0,0,0,0)), 
				new ArrayList<Integer>(Arrays.asList(0, 0, 0, 0, 0,0,0,5,5,0,0,0,3,0,0,0,0,0,0,0,0)), 
				new ArrayList<Integer>(Arrays.asList(0, 0, 0, 0, 0,0,0,0,0,0,4,3,0,4,0,5,0,0,0,0,0)), 
				new ArrayList<Integer>(Arrays.asList(0, 0, 0, 0, 0,0,0,0,0,0,0,0,4,0,0,0,0,0,0,0,0)), 
				new ArrayList<Integer>(Arrays.asList(0, 0, 0, 0, 0,0,0,4,0,0,0,0,0,0,0,0,5,0,4,0,0)), 
				new ArrayList<Integer>(Arrays.asList(0, 0, 0, 0, 0,0,0,0,0,0,0,0,5,0,0,0,3,0,0,0,0)), 
				new ArrayList<Integer>(Arrays.asList(0, 0, 0, 0, 0,0,0,0,0,0,0,0,0,0,5,3,0,3,0,0,0)), 
				new ArrayList<Integer>(Arrays.asList(0, 0, 0, 0, 0,0,0,0,0,0,0,0,0,0,0,0,3,0,0,0,0)), 
				new ArrayList<Integer>(Arrays.asList(7, 0, 0, 0, 0,0,0,0,0,0,0,0,0,0,4,0,0,0,0,3,0)), 
				new ArrayList<Integer>(Arrays.asList(0,0,0,0, 0,0,0,0,0,0,0,0,0,0,0,0,0,0,3,0,3)), 
				new ArrayList<Integer>(Arrays.asList(0,0,0,0, 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3,0))

				)
		);
		findmintime(n1, new ArrayList<ArrayList<Integer>>(Ashesimap));
		findmaxtime(n1, new ArrayList<ArrayList<Integer>>(Ashesimap));


	}

}