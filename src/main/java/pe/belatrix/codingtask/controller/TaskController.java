package pe.belatrix.codingtask.controller;

import java.io.File;
import java.net.UnknownHostException;
import java.util.List;
import java.util.stream.Collectors;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import pe.belatrix.codingtask.exception.InvalidFilePathException;
import pe.belatrix.codingtask.model.PatternOption;
import pe.belatrix.codingtask.model.TaskModel;
import pe.belatrix.codingtask.util.TaskUtil;
import pe.belatrix.codingtask.view.TaskView;

public class TaskController {
	
	private TaskModel model;
	private TaskView view;
	
	private TaskUtil taskutil;
	
	private String filePath;
	private PatternOption option;
	
	
	
	public TaskController( TaskModel model, TaskView view ) {
		this.model = model;
		this.view = view;
	}
	
	public void greet() {
		this.view.greet();
	}

	public void askForFilePath() {
		boolean notValidPathProvided = true;
		do {
			notValidPathProvided = true;
			try {
				this.filePath = this.view.askForFilePath();
				if( !taskutil.isAValidPathFile( this.filePath ) ) 
					throw new InvalidFilePathException("invalid file path");
				
				notValidPathProvided = false;
			}catch(InvalidFilePathException e) {
				this.view.notifyInvalidPathFile();
			}
			
		} while ( notValidPathProvided );
	}

	public void processProvidedFile() {
		this.retrieveCorrectURLs();
		this.scrapEachRetrievedURLInParallel();
	}

	private void scrapEachRetrievedURLInParallel() {
		this.model.getUrlList()
			.parallelStream()
			.forEach(this::scrapUrl);
	}
	
	public void scrapUrl(String url) {
		try {
			Document doc = Jsoup.connect(url).get();
			String stringToSearch = doc.text();
			List<String> matches = this.taskutil.getMatchesAsList(stringToSearch, this.option.getRegex());
			
			File file = new File(this.filePath);
			this.taskutil.generateOutputFile(file.getParent(), url, matches);
			this.view.showMessage( "output file generated for " + url + " with " + matches.size() + " match(es)" );
		} catch( UnknownHostException e ) {
			this.view.showMessage("No output file for " + e.getMessage());
		} catch (Exception e) {
			this.view.showMessage("Something is not OK for "+url+": " + e.getMessage());
		}
	}

	private void retrieveCorrectURLs() {
		this.view.showMessage("Reading provided file...");
		List<String> urlList = this.taskutil.readLinesAsList( this.filePath );
		if( urlList != null )
			this.view.showMessage("File read correctly!");
		else {
			this.view.showMessage("Could not read file!");
			return;
		}
		this.view.showMessage("Filtering valid URL lines ...");
		
		List<String> correctUrl = urlList.stream()
			.filter( this.taskutil::isAValidURL )
			.collect(Collectors.toList());
		
		this.model.setUrlList(correctUrl);
		this.view.showMessage("Only valid URLs have been kept:");
		this.view.showMessage(this.model.getUrlList());
	}

	public void sayGoodBye() {
		this.view.sayGoodBye();
		
	}

	public void letChoosePattern() {
		this.option = this.view.promptForPatternOption();
	}
	
	public void setTaskUtil( TaskUtil taskutil ) {
		this.taskutil = taskutil;
	}
	
}
