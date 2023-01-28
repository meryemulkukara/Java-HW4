import java.util.ArrayList;

public class Queue {

	ArrayList<TokenNode> priorityQueue;
	
	Queue(ArrayList<TokenNode> priorityQueue)
	{
		this.priorityQueue=priorityQueue;
	}
	
	//add new token
	public void enqueue(String name1,String type1,int number1)
	{
		TokenNode newNode=new TokenNode(name1,	type1, number1);
		int size=0;
		ArrayList<TokenNode> newPriority=new ArrayList<TokenNode>();
		for(size=0;size<priorityQueue.size();size++)
		{
			TokenNode q=priorityQueue.get(size);
			if(q.getNumber()<number1)
			{	break;	}
		}
		if(size>=priorityQueue.size())
		{	priorityQueue.add(newNode);		}
		else
		{
			for(int i=0;i<priorityQueue.size();i++)
			{
				if(i==size)
				{	newPriority.add(newNode);	}
				newPriority.add(priorityQueue.get(i));	
			}
			priorityQueue=newPriority;
		}
	}
	
	//delete node or decrease tokens number
	public TokenNode dequeue(String type1,int number1)
	{
		int i=0;
		while(i<priorityQueue.size())
		{
			TokenNode t=priorityQueue.get(i);
			if(t.getType().equals(type1))
			{
				//if token number bigger than number
				if(t.getNumber()> number1)
				{
					t.setNumber(number1);
					TokenNode newTNode= new TokenNode(t.getName(),t.getType(),t.getNumber());
					priorityQueue.remove(t);//delete token , after add new token with change old token number
					return newTNode;
				}
				else 
				{
					if(t.getNumber()== number1)//if token number and number is equal
					{
						priorityQueue.remove(t);//remove token
						return null;
					}
					if(t.getNumber()< number1)//if token number smaller than number , delete this token and control other token
					{	//it continues control until the number is 0
						number1= number1- t.getNumber();
						priorityQueue.remove(t);
					}
					i--;
				}
			}
			else
				i++;
		}
		return null;
	}


}
