import java.util.Enumeration;
import java.util.Vector;


public class start {
	static void findGoal(Node src, Node goal,int heuristic){
		OpenList ol = new OpenList();
		Closedlist cl = new Closedlist();
		ol.add(src);
		//System.out.println("asd");
		Boolean reachability = false;
		while(!ol.isEmpty()){
			
			Node node = ol.getFirst();
			//node.printNode();
			ol.remove(node);
			cl.add(node);
			if(node.compareEqual(goal)==0){
				System.out.println("Finally we found a path and the path is");
				reachability = true;
				int pathLength = node.gvalue;
				while(node.pnode!=null){
					node.printNode();
					node = node.pnode;
				}
				System.out.println("Path Length is "+pathLength);
				break;
			}
			else{
				//System.out.println("Reach");
				Vector<Node> neighbours = node.getAllNeighbours(heuristic,goal);
				Enumeration<Node> vEnum = neighbours.elements();
				while(vEnum.hasMoreElements()){
					Node element = vEnum.nextElement();
					if(cl.putNode(element)) continue;
					ol.putNode(element);
				}
			}
		}
		if(!reachability) System.out.println("Sorry No such path exists");
		System.out.println("Open List Size is "+ol.getSize());
		System.out.println("Closed List Size is "+cl.getSize());
		System.out.println("Nodes Moved "+cl.count);
		
		return;
	}
	
	static void findNewGoal(Node src, Node goal,int heuristic){
		Vector<Node> path = new Vector<Node>();
		OpenList olsrc = new OpenList();
		Closedlist clsrc = new Closedlist();
		olsrc.add(src);
		
		OpenList olgoal = new OpenList();
		Closedlist clgoal = new Closedlist();
		olgoal.add(goal);

		Boolean reachability = false;
		Boolean srcorgoal=true;
		while(!(olsrc.isEmpty() || olgoal.isEmpty())){
			Node node;
			Vector<Node> neighbours;
			if(srcorgoal){
				node = olsrc.getFirst();
				olsrc.remove(node);
				clsrc.add(node);
				if(clgoal.contains(node)){
					System.out.println("Path found in goal\n");
					int pathLength = 0;
					Node nodesrc=node;
					while(nodesrc.pnode!=null){
						nodesrc = nodesrc.pnode;
						path.addElement(nodesrc);
						pathLength++;
					}
					for(int i=0;i<path.size();i++){
						path.elementAt(path.size()-1-i).printNode();
					}
					
					node.printNode();
					
					node = clgoal.findEqual(node);
					while(node.pnode!=null){
						node=node.pnode;
						node.printNode();
						pathLength++;
					}
					
					System.out.println("Path Length is "+pathLength);
					reachability = true;
					break;
				}
				neighbours = node.getAllNeighbours(heuristic,goal);
			}
			else{
				node = olgoal.getFirst();
				olgoal.remove(node);
				clgoal.add(node);
				if(clsrc.contains(node)){
					System.out.println("Path found in source\n");

					reachability = true;
					int pathLength = 0;
					Node nodesrc = clsrc.findEqual(node);
					while(nodesrc.pnode!=null){
						pathLength++;
						nodesrc = nodesrc.pnode;
						path.addElement(nodesrc);
					}
					for(int i=0;i<path.size();i++){
						path.elementAt(path.size()-1-i).printNode();
					}
					node.printNode();
					
					while(node.pnode!=null){
						node=node.pnode;
						node.printNode();
						pathLength++;
					}
					System.out.println("Path Length is "+pathLength);
					break;
				}
				neighbours = node.getAllNeighbours(heuristic,src);
			}
			
			Enumeration<Node> vEnum = neighbours.elements();
			while(vEnum.hasMoreElements()){
				Node element = vEnum.nextElement();
				if(srcorgoal){
					if(clsrc.putNode(element)) continue;
					olsrc.putNode(element);
				}
				else{
					if(clgoal.putNode(element)) continue;
					olgoal.putNode(element);
				}
			}
			srcorgoal =!srcorgoal;
		}
		if(!reachability) System.out.println("Sorry No such path exists");
		System.out.println("Open List of source Size is "+olsrc.getSize());
		System.out.println("Closed List of source Size is "+clsrc.getSize());
		System.out.println("Open List of goal Size is "+olgoal.getSize());
		System.out.println("Closed List of goal Size is "+clgoal.getSize());
		System.out.println("Nodes Moved from source "+clsrc.count);
		System.out.println("Nodes Moved from goal "+clgoal.count);
		
		return;
	}
	
	
	public static void main(String[] args){
		//System.out.println("Started");
		int[][] checking = {{1,3,5},{0,7,4},{6,2,8}};
		int[][] checking2 = {{1,2,3},{4,5,6},{7,8,0}};
		//*
		Node src = new Node(checking);
		Node goal = new Node(checking2);
		//src.takeInput();
		//goal.takeInput();
		int heuristic = src.takeHeuristic();
		//System.out.println("Reached Here");
		if(src.checkReachibility(goal)) findGoal(src,goal,heuristic);
		else System.out.println("Not Reachable");
	}
}
