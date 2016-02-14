package com.seed.search.seed;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.xml.sax.SAXException;

//import edu.stanford.nlp.tagger.maxent.MaxentTagger;
import edu.smu.tspell.wordnet.*;

public class WordnetSynonymSearch{
    
    private static JSONParsing parse;
   
    public WordnetSynonymSearch(String s) throws FileNotFoundException, XPathExpressionException, IOException, SAXException, ParserConfigurationException{
      
        /* s is the String to search. */
        parse = new JSONParsing(s);
        
    }
    
    public WeightedKeyword[] getArray() {
        for (WeightedKeyword temp: parse.getW()){
            System.out.println(temp.getKeyword() + " " + temp.getRelevance());
        }
        return parse.getW();
        
    }

    public static void addSynonyms(WeightedKeyword[] array){
        
        ArrayList<WeightedKeyword> masterList = new ArrayList<WeightedKeyword>();
        
        /* Add all original words and values to the masterList */
        for (int i = 0; i < array.length; i++){
            masterList.add(new WeightedKeyword(array[i].getKeyword(), array[i].getRelevance()));
        }
        
        /* Now add all of the words' synonyms to the masterList with the
         * same values as the original word */
        double tempValue = 0;
        ArrayList<String> tempList = new ArrayList<String>();
        
        for (int i = 0; i < array.length; i++){
            tempValue = array[i].getRelevance();
            
            tempList = getSynonyms(array[i].getKeyword());
            
            for (int k = 0; k < tempList.size(); k++){
                masterList.add(new WeightedKeyword(tempList.get(k), tempValue));
            }
        }
        
        /*
        for (int i = 0; i < masterList.size(); i++){
            System.out.println(masterList.get(i).getKeyword() + " " + masterList.get(i).getRelevance());
        } */
        
        WeightedKeyword[] arr = new WeightedKeyword[masterList.size()];
        
        for (int i = 0; i < arr.length; i++){
            arr[i] = new WeightedKeyword(masterList.get(i).getKeyword(), masterList.get(i).getRelevance());
        }
        
        parse.setW(arr);
        
        array = arr;
        

        
    }

    /* Returns an arraylist of synonyms of a given word */
    public static ArrayList<String> getSynonyms(String word){
        
        String wordForm = word;
        ArrayList<String> al = new ArrayList<String>();
        System.out.println(System.getProperties());
        //System.setProperty("wordnet.database.dir","/apps/libs/WordNet/2.1/dict");

        WordNetDatabase database = WordNetDatabase.getFileInstance();
        Synset[] synsets = database.getSynsets(wordForm);
        //  Display the word forms and definitions for synsets retrieved

        if (synsets.length > 0){
            // add elements to al, including duplicates
            HashSet<String> hs = new HashSet<String>();
            for (int i = 0; i < synsets.length; i++){
                String[] wordForms = synsets[i].getWordForms();//TODO: possibly getKeywordForms() ? 
                for (int j = 0; j < wordForms.length; j++)
                {
                    al.add(wordForms[j]);
                }


                //removing duplicates
                hs.addAll(al);
                al.clear();
                al.addAll(hs);

                //showing all synsets
            }
        }
        
        return al;
    }
} 
