package com.seed.search.seed;

/**
 * @author Tom Lerner
 *
 */
public class WeightedKeyword {
	
	private String keyword;
	
	private Double relevance;
	
	public WeightedKeyword(String keyword, Double relevance) {
		this.setKeyword(keyword);
		this.setRelevance(relevance);
	}

	/**
	 * @return the keyword
	 */
	public String getKeyword() {
		return keyword;
	}

	/**
	 * @param keyword the keyword to set
	 */
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	/**
	 * @return the relevance
	 */
	public Double getRelevance() {
		return relevance;
	}

	/**
	 * @param relevance the relevance to set
	 */
	public void setRelevance(Double relevance) {
		this.relevance = relevance;
	}
	
	
	
}