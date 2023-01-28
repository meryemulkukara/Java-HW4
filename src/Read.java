import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Read {
	
	Read(){
		
	}
	
	public static ArrayList <Stack>items=new ArrayList<>();
	public static ArrayList <String>parts=new ArrayList<>();
	static ArrayList<TokenNode> priorityQueue=new ArrayList<TokenNode>();
    public static Queue q;
    
	//Reads the file and saves the lines into a String[] array.Then returns it
    public static String[] readFile(String path){
        try {
            int i=0;
            int lenght= Files.readAllLines(Paths.get(path)).size();
            String[] results = new String[lenght];
            for (String line : Files.readAllLines(Paths.get(path))) {
            	if(line.equals("\\n"))
            		continue;
                results[i++]=line;
            }
            return results;
            
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    //add to items.txt items, ArrayList<items>
    public static void items_stack(String line, ArrayList <Stack>items)
    {
    	 String[] information=line.split(" "); 
    	 boolean also_have=false;
    	 if(items.size()==0)
    	 {
    		 items.add(new Stack(information[1],information[0]));
    		 also_have=true;
    	 }
    	 for(int i=0;i<items.size()&& !also_have ; i++ )
    	 {
    		 Stack s=items.get(i);
    		 
    		 if(s.getType().equals(information[1])&& !also_have)
    		 {
    			 also_have=true;
    			 s.push(information[0], s.itemNode);
    			 break;
    		 }
    	 }
    	 
    	 if(!also_have)
    	 {
    		 items.add(new Stack(information[1],information[0]));
    	 } 
    }
    
    
    public static int val=0;
    //add to tokens.txt items, in priorityQueue
    public static void tokens_queue(String line)
    {
    	 String[] information=line.split(" ");
    	 
    	 if(val==0)
    	 {
    		 TokenNode firstNode= new TokenNode(information[0],information[1],Integer.parseInt(information[2])); 
    		 priorityQueue.add(firstNode);
    		 q=new Queue( priorityQueue);
    		 val++;
    	 }
    	 else
    	 {		 q.enqueue(information[0],information[1],Integer.parseInt(information[2]));    	 }
    }
    	
    //read task.txt commands
	public static void tasks(String line)
	{
		String[] information=line.split("\t");
		//control if BUY
		if(information[0].equals("BUY"))
		{
			for(int i=1; i<information.length;i++ )
			{
				String[] buyInfo= information[i].split(",");
				for(Stack item:items)
				{	 if(buyInfo[0].equals(item.getType()))
					{
						int l=Integer.parseInt(buyInfo[1]);
					 	 while(l>0)
					 	 {	item.pop();
					 		l--; }
					 	 break;		}		 
				}
				TokenNode newnode=q.dequeue( buyInfo[0],Integer.parseInt(buyInfo[1]));
				if(newnode!=null)	
					q.enqueue(newnode.getName(),newnode.getType(),newnode.getNumber());				
			}
		 }
		
		//control if PUT
		if(information[0].equals("PUT"))
		{
			for(int i=1; i<information.length;i++ )
			{
				String[] buyInfo= information[i].split(",");
				for(int j=1;j<buyInfo.length;j++)
				{	
					String newStack=buyInfo[j]+" "+buyInfo[0];
					items_stack(newStack, items);
				} 	
			}
		}
	}
	
	 //create output file
    public static void output_file(String file_name)
    {
    	try {
            File file = new File(file_name);
            if (file.createNewFile()) {
              //System.out.println("File created: " + file.getName());
            } else {
              //System.out.println("File already exists.");
            }
            FileWriter writer = new FileWriter(file);
            for(String p :parts)
            {     writer.write(p+":"); 
            		//write items
            	  for(Stack item:items)
            	  {
            		  if(item.getType().equals(p))
            		  {
            			  for(int i=item.itemNode.size()-1;i>=0;i--)
            			  {    writer.write("\n"+item.itemNode.get(i).getName());    	  }	  
            		  }  
            	  }
            	  writer.write("\n---------------\n");
            }
            //write token box
           writer.write("Token Box:");
           for(int i=q.priorityQueue.size()-1;i>=0;i--)
           {      writer.write("\n"+q.priorityQueue.get(i).getName()+" "+q.priorityQueue.get(i).getType()+" "+q.priorityQueue.get(i).getNumber());           }
           
           writer.close();
    	}catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
          }
    }
	

}
