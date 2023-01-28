public class Main {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//read parts.txt
		String[] lines_parts=Read.readFile(args[0]);//read film.txt
		for(String line:lines_parts)
		{       Read.parts.add(line);		}
		   
		//read items.txt
		String[] lines_items=Read.readFile(args[1]);//read film.txt
		for(String line:lines_items)
		{   Read.items_stack(line, Read.items);		}
		
		//read tokens.txt
		String[] lines_tokens=Read.readFile(args[2]);//read film.txt
		for(String line:lines_tokens)
		{   Read.tokens_queue( line);	}		
		
		//read tasks.txt
		String[] lines_tasks=Read.readFile(args[3]);//read film.txt
		for(String line:lines_tasks)
		{   Read.tasks( line);		}
		
		//create output and write
		Read.output_file( args[4]);
	}

}
