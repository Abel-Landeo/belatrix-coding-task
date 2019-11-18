package pe.belatrix.codingtask;

import java.util.Scanner;

import pe.belatrix.codingtask.controller.TaskController;
import pe.belatrix.codingtask.model.TaskModel;
import pe.belatrix.codingtask.util.TaskUtil;
import pe.belatrix.codingtask.view.TaskView;

public class App {
	
    public static void main( String[] args ) {
    	
    	TaskModel model = new TaskModel();
    	
    	TaskView view = new TaskView();
    	view.setScanner( new Scanner(System.in) );
    	
        TaskController controller = new TaskController(model, view);
        controller.setTaskUtil( new TaskUtil() );
        
        controller.greet();
        controller.askForFilePath();
        controller.letChoosePattern();
        controller.processProvidedFile();
        controller.sayGoodBye();
        
    }
}
