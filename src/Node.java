import java.util.Comparator;
import java.util.Scanner;
import java.util.Vector;
import java.lang.Math;

class MyNameComp implements Comparator<Node>{
	 
    @Override
    public int compare(Node n1, Node n2) {
    	int val = n1.compareEqual(n2);
        return val;
    }
}  
 
class MyFvalueComp implements Comparator<Node>{
 
    @Override
    public int compare(Node n1, Node n2) {
        if(n1.getFvalue() > n2.getFvalue()){
            return 1;
        }
        else if(n1.getFvalue() < n2.getFvalue()){
            return -1;
        }
        return n1.compareEqual(n2);
    }
}


public class Node {
	int[][] puzzle = new int[3][3];
	int fvalue;
	public int getFvalue() {
		return fvalue;
	}

	public void setFvalue(int fvalue) {
		this.fvalue = fvalue;
	}

	int gvalue;
	Node pnode;
	public Node(){
		for(int i=0;i<3;i++){
			for(int j=0;j<3;j++){
				puzzle[i][j]=-1;
			}
		}
		fvalue = 0;
		gvalue = 0;
		pnode = null;
	}
	
	public Node(int[][] check){
		for(int i=0;i<3;i++){
			for(int j=0;j<3;j++){
				puzzle[i][j]=check[i][j];
			}
			fvalue = 0;
			gvalue = 0;
			pnode = null;
		}
	}
	
	Node getLeft(int heuristic, Node goal){
		Node temp = new Node();
		for(int i=0;i<3;i++){
			for(int j=0;j<3;j++){
				if(puzzle[i][j]==0){
					if(j==0) return null;
					temp.puzzle[i][j-1] = puzzle[i][j];
					temp.puzzle[i][j] = puzzle[i][j-1];
				}
				else{
					temp.puzzle[i][j]=puzzle[i][j];
				}
			}
		}
		temp.gvalue = gvalue + 1;
		temp.fvalue = temp.gvalue+temp.hfunc(heuristic,goal);
		temp.pnode = this;
		return temp;
	}
	
	Node getRight(int heuristic, Node goal){
		Node temp = new Node();
		for(int i=0;i<3;i++){
			for(int j=0;j<3;j++){
				if(puzzle[i][j]==0){
					if(j==2) return null;
					temp.puzzle[i][j+1] = puzzle[i][j];
					temp.puzzle[i][j] = puzzle[i][j+1];
					j++;
				}
				else{
					temp.puzzle[i][j]=puzzle[i][j];
				}
			}
		}
		temp.gvalue = gvalue + 1;
		temp.fvalue = temp.gvalue+temp.hfunc(heuristic,goal);
		temp.pnode = this;
		return temp;
	}
	
	Node getUp(int heuristic, Node goal){
		Node temp = new Node();
		for(int i=0;i<3;i++){
			for(int j=0;j<3;j++){
				if(puzzle[i][j]==0){
					if(i==0) return null;
					temp.puzzle[i-1][j] = puzzle[i][j];
					temp.puzzle[i][j] = puzzle[i-1][j];
				}
				else{
					temp.puzzle[i][j]=puzzle[i][j];
				}
			}
		}
		temp.gvalue = gvalue + 1;
		temp.fvalue = temp.gvalue+temp.hfunc(heuristic,goal);
		temp.pnode = this;
		return temp;
	}
	
	Node getDown(int heuristic, Node goal){
		Node temp = new Node();
		for(int j=0;j<3;j++){
			for(int i=0;i<3;i++){
				if(puzzle[i][j]==0){
					if(i==2) return null;
					temp.puzzle[i+1][j] = puzzle[i][j];
					temp.puzzle[i][j] = puzzle[i+1][j];
					i++;
				}
				else{
					temp.puzzle[i][j]=puzzle[i][j];
				}
			}
		}
		temp.gvalue = gvalue + 1;
		temp.fvalue = temp.gvalue+temp.hfunc(heuristic,goal);
		temp.pnode = this;
		return temp;
	}
	
	void printNode(){
		System.out.println("Printing Node");
		System.out.println(puzzle[0][0]+" "+puzzle[0][1]+" "+puzzle[0][2]);
		System.out.println(puzzle[1][0]+" "+puzzle[1][1]+" "+puzzle[1][2]);
		System.out.println(puzzle[2][0]+" "+puzzle[2][1]+" "+puzzle[2][2]);
		System.out.println("f value is "+fvalue);
		System.out.println("g value is "+gvalue);
		
		System.out.println("Ending Node");
	}
	
	Vector<Node> getAllNeighbours(int heuristic,Node goal){
		Vector<Node> allNeighbours = new Vector<Node>();
		
		Node leftNode = this.getLeft(heuristic,goal);
		if(leftNode!=null)allNeighbours.add(leftNode);
		Node rightNode = this.getRight(heuristic,goal);
		if(rightNode!=null)allNeighbours.add(rightNode);
		Node upNode = this.getUp(heuristic,goal);
		if(upNode!=null)allNeighbours.add(upNode);
		Node downNode = this.getDown(heuristic,goal);
		if(downNode!=null)allNeighbours.add(downNode);
		return allNeighbours;
	}
	
	int compareEqual(Node node){
		for(int i=0;i<3;i++){
			for(int j=0;j<3;j++){
				if(puzzle[i][j]<node.puzzle[i][j]){
					return -1;
				}
				if(puzzle[i][j]>node.puzzle[i][j]){
					return 1;
				}
			}
		}
		return 0;
	}
	
	void takeInput(){
		
		Scanner in = new Scanner(System.in);
		for(int i=0;i<3;i++){
			for(int j=0;j<3;j++){
				puzzle[i][j] = in.nextInt();
			}
		}
		return;
	}

	public int hfunc(int heuristic, Node goal) {
		
		int count=0;
		if(heuristic == 2){
			for(int i=0;i<3;i++){
				for(int j=0;j<3;j++){
					if(puzzle[i][j]!=goal.puzzle[i][j]) return 1;
				}
			}
		}
		else if(heuristic == 3 || heuristic == 6){
			for(int i=0;i<3;i++){
				for(int j=0;j<3;j++){
					if(puzzle[i][j]==0) continue;
					if(puzzle[i][j]!=goal.puzzle[i][j] ) count++;
				}
			}
		}
		
		/*else if(heuristic == 4 || heuristic == 7){
			for(int i=0;i<3;i++){
				for(int j=0;j<3;j++){
					if(puzzle[i][j]==0) continue;
					int row = (puzzle[i][j]-1)/3;
					int column = (puzzle[i][j]-1)%3;
					count = count + Math.abs(i-row)+Math.abs(j-column);
				}
			}
		}*/
		
		else if(heuristic == 4 || heuristic == 7){
			int[] x = new int[8];
			int[] y = new int[8];
			for(int i=0;i<3;i++){
				for(int j=0;j<3;j++){
					if(puzzle[i][j]==0) continue;
					x[puzzle[i][j]-1] = i;
					y[puzzle[i][j]-1] = j;
				}
			}
			for(int i=0;i<3;i++){
				for(int j=0;j<3;j++){
					if(goal.puzzle[i][j]==0) continue;
					int row = x[goal.puzzle[i][j]-1];
					int column = y[goal.puzzle[i][j]-1];
					count = count + Math.abs(i-row)+Math.abs(j-column);
				}
			}
		}
		
		/*else if(heuristic == 5){
			for(int i=0;i<3;i++){
				for(int j=0;j<3;j++){
					if(puzzle[i][j]==0) continue;
					if((puzzle[i][j]-1)/3 !=i) count++;
					if((puzzle[i][j]-1)%3 !=j) count++;
				}
			}
		}*/
		else if(heuristic == 5){
			int[] x = new int[8];
			int[] y = new int[8];
			for(int i=0;i<3;i++){
				for(int j=0;j<3;j++){
					if(puzzle[i][j]==0) continue;
					x[puzzle[i][j]-1] = i;
					y[puzzle[i][j]-1] = j;
				}
			}
			for(int i=0;i<3;i++){
				for(int j=0;j<3;j++){
					if(goal.puzzle[i][j]==0) continue;
					int row = x[goal.puzzle[i][j]-1];
					int column = y[goal.puzzle[i][j]-1];
					if(row!=i) count++;
					if(column!=j) count++;
				}
			}
		}

		if(heuristic==6) return count*17;
		if(heuristic==7){
			if(puzzle[1][1]==5) return 0;
			return count;
		}
		return count;
	}
	int calculate(Node node){
		int[] sample=new int[8];
		int num=0;
		int index=0;
		for(int i=0;i<3;i++){
			for(int j=0;j<3;j++){
				if(node.puzzle[i][j]==0){
					index=1;
					continue;
				}
				sample[3*i+j-index]=node.puzzle[i][j];
			}
		}
		
		for(int i=0;i<8;i++){
			for(int j=i+1;j<8;j++){
				if(sample[j]<sample[i]) num++;
			}
		}
		return num;
	}
	public boolean checkReachibility(Node goal) {
		int srcnum = calculate(this);
		int goalnum = calculate(goal);
		System.out.println("srcnum is "+srcnum);
		System.out.println("goalnum is "+goalnum);
		if((srcnum-goalnum)%2==0) return true;
		return false;
	}

	public int takeHeuristic() {
		System.out.println("Choose Your Heuristic\n1 for BFS\n2 for Random\n"+
							"3 for Displaced Tiles\n4 for Manhatten Distance\n"+
							"5 for Our Own Heuristic\n6 for 17 * Displaced Tiles\n"+
							"7 for Non-monotonic heuristic");
		Scanner in = new Scanner(System.in);
		int h = in.nextInt();
		return h;
	}
	
//	public static void main(String[] args){
//		int[][] checking = {{1,2,0},{4,3,6},{7,8,5}};
//		int[][] checking2 = {{1,2,3},{4,0,6},{7,8,5}};
//		Node rand = new Node(checking);
//		Node tttt = new Node(checking2);
//		System.out.println(rand.compareEqual(tttt));
//		/*tttt = rand.getLeft();
//		tttt.printNode();
//		tttt = rand.getRight();
//		tttt.printNode();
//		tttt = rand.getUp();
//		tttt.printNode();
//		tttt = rand.getDown();
//		tttt.printNode();*/
//	}
}