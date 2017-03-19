package reflectionBasics;

public class Employee {
	
	public String Name;
	public double salary;
	private char id;
	public String[] friends;
	public Employee boss;
	
	public Employee(){
		
	}
	
	public Employee(double salary){
		this.salary = salary;
	}
	
	public Employee(Double salary){
		this.salary = salary;
	}
	
	public Employee(String Name, String[] friends){
		this.Name = Name;
		this.friends = friends;
	}
	
	public Employee(String Name, double salary){
		this.Name = Name;
		this.salary = salary;
	}
	
	public Employee(String Name, double salary, char id){
		this.Name = Name;
		this.salary = salary;
		this.id = id;
	}
	
	public Employee(String Name, double salary, Employee boss){
		this.Name = Name;
		this.salary = salary;
		this.boss = boss;
	}
	
	public double getSalary(){
		return this.salary;
	}
	
	public String getName(){
		return this.Name;
	}
	
	public void increaseSalary(double sum){
		this.salary += sum;
	}

}
