package model;

public class SimpleProgressBar extends BasicProgress{
	private String bar;
	private char filling;
	private char currentChar;
	
	public SimpleProgressBar(int max, char fill, char currCh) {
		super(max);
		init(fill, currCh);
	}
	
	public SimpleProgressBar(int max, char fill) {
		super(max);
		init(fill, fill);
	}
	
	private void init(char fill, char currCh) {
		bar = template;
		filling = fill;
		currentChar = currCh;
	}
	
	@Override
	public void advance() {
		if(current < maximum) {
			current++;
			
			int numberBarChars = template.length()-2;
			int numberFilledChars = (int)((current/(double)maximum)*numberBarChars);
			bar = template;
			for (int i = 0; i < numberFilledChars; i++) {
				bar = bar.replaceFirst(" ", filling+"");				
			}
			bar = bar.replaceFirst(" ", getCurrentChar());
		}
	}
	
	protected String getCurrentChar() {
		return currentChar+"";
	}
	
	private String getPercent() {
		return String.format("%3d",(int)(current*100.0/maximum));
	}

	@Override
	public String getState() {
		return bar+getPercent()+"%";
	}
	
	@Override
	public boolean finished() {
		return current>=maximum;
	}
}
