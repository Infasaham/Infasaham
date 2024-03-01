
public class BePolite {
	int number;
	
	
	public BePolite () {
		number = 5;
	}
	
	public void printGreetingIn4Lines() {		
		System.out.println("Hello");
		System.out.println("How");
		System.out.println("Are");
		System.out.println("You?");		
		System.out.println("Are you " + (number) + " years old?");
	}
	
	public void printGoodBye() {
		number = 3;
		System.out.println("Good bye!");
	}
}
