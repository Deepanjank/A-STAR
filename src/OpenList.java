import java.util.TreeMap;
import java.util.TreeSet;

public class OpenList {
	TreeMap<Node,Integer> nameComp = new TreeMap<Node,Integer>(new MyNameComp());
	TreeSet<Node> fvalueComp = new TreeSet<Node>(new MyFvalueComp());
	
	void add(Node node){
		nameComp.put(node,node.fvalue);
		fvalueComp.add(node);
	}
	
	void remove(Node node){
		nameComp.remove(node);
		fvalueComp.remove(node);
	}
	
	Node getFirst(){
		Node node = fvalueComp.first();
		return node;
	}
	
	Boolean isEmpty(){
		return fvalueComp.isEmpty();
	}
	
	Boolean contains(Node node){
		return nameComp.containsKey(node);
	}
	
	void putNode(Node node){
		if(this.contains(node)){
			Integer oldVal = nameComp.get(node);
			if(oldVal > node.getFvalue()){
				int newVal = node.getFvalue();
				node.setFvalue(oldVal);
				fvalueComp.remove(node);
				nameComp.put(node,node.fvalue);
				node.setFvalue(newVal);
				fvalueComp.add(node);
			}
			
		}
		else{
			this.add(node);
		}
	
	}

	public int getSize() {
		// TODO Auto-generated method stub
		return fvalueComp.size();
	}
}