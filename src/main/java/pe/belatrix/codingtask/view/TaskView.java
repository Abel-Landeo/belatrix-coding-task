package pe.belatrix.codingtask.view;

import java.util.Scanner;

import pe.belatrix.codingtask.model.PatternOption;

public class TaskView {
	
	private Scanner scanner;
	
	public void setScanner( Scanner scanner ) {
		this.scanner = scanner;
	}

	public void greet() {
		System.out.println("Greetings!!");
		
	}

	public String askForFilePath() {
		System.out.println("Provide the full path of the text file Please: ");
		String providedPath = this.scanner.nextLine();
		return providedPath;
		
	}

	public void notifyInvalidPathFile() {
		System.out.println("The provided path file is not correct.");
		
	}

	public PatternOption promptForPatternOption() {
		System.out.println("What pattern to find?");
		System.out.print("Type 1 for hashtag, any other value for twitter account: ");
		String option = this.scanner.nextLine();
		if( "1".equals( option ) ) return PatternOption.HASH_TAG;
		else return PatternOption.TWITTER_ACCOUNT;
		
	}
	
	public void showMessage( Object message ) {
		System.out.println(message);
	}

	public void sayGoodBye() {
		System.out.println("Task finished!!");
		System.out.println("Good Bye");
		
	}

}
