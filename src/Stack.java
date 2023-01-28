import java.util.ArrayList;

public class Stack {
	
	private String type;
	ArrayList<ItemNode> itemNode; 
	
	Stack(String type1,String name1)
	{
		itemNode=new ArrayList<ItemNode>();
		type=type1;
		push(name1,itemNode);
	}
	
	
	public void push(String name, ArrayList<ItemNode> stackList)
	{
		ItemNode addedNode=new ItemNode(name);
		stackList.add(addedNode);
	
	}
	
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public ArrayList<ItemNode> getNode() {
		return itemNode;
	}

	public void setNode(ArrayList<ItemNode> node) {
		this.itemNode = node;
	}

	public void pop()
	{
		int length=getNode().size();
		itemNode.remove(length-1);
		
	}
	
}
