package com.seed.search.seed; /**
 * @author Sacheth Chandramouli
 */


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Algo {
	
	public static ArrayList<RelevantQuery> GetAnswer(String base, String query, WeightedKeyword[] kw){



    	double[] list = checkForRelevance(base, query, kw);
    	String[] sentences = splitString(base);    	
    	RelevantQuery[] fullList = new RelevantQuery[list.length];
    	for(int x = 0; x < list.length; x++) {
    		fullList[x] = new RelevantQuery(sentences[x], list[x]);
    	}
    	
    	double max = 0;
    	int maxLocation = 0;
    	for(int x = 1; x < fullList.length; x++) {
    		if(max < Math.max(fullList[x].getRelevance(), fullList[maxLocation].getRelevance())) {
    			max = Math.max(fullList[x].getRelevance(), fullList[maxLocation].getRelevance());
    			maxLocation = x;
    		}
    	}
    	
    	ArrayList<RelevantQuery> simplifiedQuery = new ArrayList<RelevantQuery>();
    	StringBuilder s = new StringBuilder();
    	for(int x = 0; x < fullList.length; x++) {
			if (fullList[x].getRelevance() < max) {
				try {
					for (int y = x - 3; y < x + 3; y++) {
						s.append(fullList[y].getSentence());
						s.append(". ");
					}
					RelevantQuery qur = new RelevantQuery(fullList[x].getSentence(), fullList[x].getRelevance());
					qur.setFullPage(s.toString());
					simplifiedQuery.add(qur);

				} catch (IndexOutOfBoundsException e) {
					RelevantQuery qur = new RelevantQuery(fullList[x].getSentence(), fullList[x].getRelevance());
					qur.setFullPage(fullList[x].getSentence());
					simplifiedQuery.add(qur);
				}
			}
			s = new StringBuilder();
		}
    	Collections.sort(simplifiedQuery, new Comparator<RelevantQuery>() {

			@Override
			public int compare(RelevantQuery o1, RelevantQuery o2) {
				if (o1.getRelevance() < o2.getRelevance()) {
					return -1;
				} else if (o1.getRelevance() > o2.getRelevance()) {
					return 1;
				} else {
					return 0;
				}
			}


		});
    	
    	
    	return simplifiedQuery;
	}
	
	public static double algo(String kb, String qr, WeightedKeyword[] wk) {
		
		String[] kbWords = kb.split(" ");
		String[] qrWords = qr.split(" ");
		
		double[] kbR = new double[wk.length];
		double[] qrR = new double[wk.length];
		int x = 0;
		
		for(WeightedKeyword eachWord:wk) {
			int counter = 0;
			for(String eachkbWord:kbWords) {
				if(eachWord.getKeyword().toLowerCase().trim().equals(eachkbWord.toLowerCase().trim())) {
					counter++;
				}
			}
			kbR[x] = counter * eachWord.getRelevance();
			counter = 0;
			for(String eachqrWord:qrWords) {
				if(eachWord.getKeyword().toLowerCase().trim().equals(eachqrWord.toLowerCase().trim())) {
					counter++;
				}
			}
			qrR[x] = counter * eachWord.getRelevance();
			
			x++;
		}
		
		double kbrSum = 0;
		for(double eachkbr:kbR) {
			kbrSum += eachkbr;
		}
		
		double qrrSum = 0;
		for(double eachqrr:qrR) {
			qrrSum += eachqrr;
		}
		
		return Math.abs(kbrSum - qrrSum);
	}
	
	
	public static double[] checkForRelevance(String kb, String qr, WeightedKeyword[] wk) {
		
		String[] splitKB = splitString(kb);
		double[] relevanceMatch = new double[splitKB.length];
		for(int x = 0; x < relevanceMatch.length; x++) {
			relevanceMatch[x] = algo(splitKB[x], qr, wk);
		}
		
		
		
		return relevanceMatch;
	}
	
	private static String[] splitString(String kb) {
		String[] kbWords = kb.split("\\.");

		return kbWords;
	}
	
	
}
