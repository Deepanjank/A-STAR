import java.util.Map;
import java.util.TreeMap;
public class Closedlist {
	int count=0;
	TreeMap<Node,Integer> nameComp = new TreeMap<Node,Integer>(new MyNameComp());
	
	Node findEqual(Node node){
		for(Map.Entry<Node,Integer> entry : nameComp.entrySet()) {
			  Node key = entry.getKey();
			  if(key.compareEqual(node)==0){
				  return key;
			  }
			  
			}
		return null;
	}
	void add(Node node){
		nameComp.put(node,node.gvalue);
	}
	
	void remove(Node node){
		nameComp.remove(node);
	}
	
	Boolean contains(Node node){
		return nameComp.containsKey(node);
	}
	
	int getSize(){
		return nameComp.size();
	}
	
	Boolean putNode(Node node){
		if(this.contains(node)){
			if(nameComp.get(node) > node.gvalue){
				count++;
				nameComp.remove(node);
				return false;
			}
			return true;
		}
		return false;
	}
}