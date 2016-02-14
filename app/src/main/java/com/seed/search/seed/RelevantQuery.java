package com.seed.search.seed;

/**
 * @author Tom Lerner
 *
 */
public class RelevantQuery implements Comparable<Double>{
	
	private String sentence;
	
	private Double relevance;
	
	private String fullPage;
	
	public RelevantQuery(String sentence, Double relevance) {
		this.sentence = sentence;
		this.relevance = relevance;
	}

	/**
	 * @return the keyword
	 */
	public String getSentence() {
		return sentence;
	}

	/**
	 * @return the relevance
	 */
	public Double getRelevance() {
		return relevance;
	}
	
	public void setFullPage(String page) {
		this.fullPage = page;
	}
	
	
	public String getFullPage() {
		return fullPage;
	}

	@Override
	public int compareTo(Double o) {
		// TODO Auto-generated method stub
		if(this.getRelevance() < o) {
			return -1;
		} else if(this.getRelevance() > o) {
			return 1;
		} else {
			return 0;
		}
	}
	
	
	
}