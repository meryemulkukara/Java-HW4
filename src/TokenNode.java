//tokens node
public class TokenNode {

	private String name;
	private String type;
	private int number;
	
	TokenNode(String name1,String type1,int number1)
	{ 
		name=name1;
		type=type1;
		number=number1;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int n) {
		this.number =getNumber()- n;
	}
	
	
}
