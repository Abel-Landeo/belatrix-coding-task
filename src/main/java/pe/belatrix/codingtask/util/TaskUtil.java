package pe.belatrix.codingtask.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TaskUtil {
	
	public boolean isAValidPathFile( String filePath ) {
		if( filePath == null ) return false;
		else {
			File file = new File(filePath);
			if( !file.exists() ) return false;
		}
		return true;
	}
	
	public List<String> readLinesAsList( String filePath ){
		try {
			Stream<String> linesAsStream = Files.lines(Paths.get(filePath));
			List<String> linesAsList = linesAsStream.collect(Collectors.toList());
			linesAsStream.close();
			return linesAsList;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public boolean isAValidURL( String url ) {
		try { 
            new URL(url).toURI(); 
            return true;
        }catch (Exception e) { 
            return false; 
        } 
	}
	
	public List<String> getMatchesAsList( String stringToSearch, String regex ){
		Pattern p = Pattern.compile(regex);// the pattern to search for
	    Matcher m = p.matcher(stringToSearch);
	    List<String> matches = new ArrayList<>();
	    while (m.find()) {
	    	matches.add(m.group());
	    }
	    return matches;
	}

	public void generateOutputFile(String dirPath, String url, List<String> matches) {
		String fullPathFile = dirPath + File.separator + url.replace("/", "") + ".txt";
		Path path = Paths.get(fullPathFile);
		try (BufferedWriter writer = Files.newBufferedWriter(path)) 
		{
		    writer.write("Url: " + url);
		    writer.newLine();
		    writer.write("Total: " + matches.size());
		    for( String match : matches ) {
		    	writer.newLine();
		    	writer.write(match);
		    }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
