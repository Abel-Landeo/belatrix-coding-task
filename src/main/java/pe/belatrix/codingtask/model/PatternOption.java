package pe.belatrix.codingtask.model;

public enum PatternOption {
	HASH_TAG("(?:\\s|\\A)[##]+([A-Za-z0-9-_]+)"),
	TWITTER_ACCOUNT("(?<=^|(?<=[^a-zA-Z0-9-\\.]))@[A-Za-z0-9-]+(?=[^a-zA-Z0-9-_\\.])");
	
	private String regex;
	
	PatternOption(String regex) {
		this.regex = regex;
	}
	
	public String getRegex() {
		return this.regex;
	}
}
