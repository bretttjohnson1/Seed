package com.seed.search.seed; /**
 * @author Tom Lerner
 *
 */

import com.alchemyapi.api.AlchemyAPI;
import org.xml.sax.SAXException;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import java.io.*;

public class JSONParsing {

	private WeightedKeyword[] w;
	
	
	public JSONParsing(String s) throws FileNotFoundException, IOException, XPathExpressionException, SAXException, ParserConfigurationException {
	       // Create an AlchemyAPI object.
        AlchemyAPI alchemyObj = AlchemyAPI.GetInstanceFromFile("api_key.txt");
                
        // Extract topic keywords for a text string.
        Document doc = alchemyObj.TextGetRankedKeywords(s);
        
        WeightedKeyword[] w = new WeightedKeyword[doc.getElementsByTagName("keyword").getLength()];
        NodeList nList = doc.getElementsByTagName("keyword");
        
        for(int x = 0; x < nList.getLength(); x++) {
            
            Node nNode = nList.item(x);
            
            if(nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                w[x] = new WeightedKeyword(eElement.getElementsByTagName("text").item(0).getTextContent(), Double.parseDouble(eElement.getElementsByTagName("relevance").item(0).getTextContent()));
            }
        }       
        
        this.w = w;
        
        /* Testing print */
//        for(WeightedKeyword temp : w) {
//            System.out.println(temp.getKeyword() + "      " + temp.getRelevance());
//        }
	}
	
//	public static void main(String[] args) throws FileNotFoundException, IOException, XPathExpressionException, SAXException, ParserConfigurationException {
//		// Create an AlchemyAPI object.
//		AlchemyAPI alchemyObj = AlchemyAPI.GetInstanceFromFile("api_key.txt");
//				
//		// Extract topic keywords for a text string.
//		Document doc = alchemyObj.TextGetRankedKeywords(
//				"Hello there, my name is Bob Jones.  I live in the United States of America.  " +
//				"Where do you live, Fred?");
//		
//		WeightedKeyword[] w = new WeightedKeyword[doc.getElementsByTagName("keyword").getLength()];
//		NodeList nList = doc.getElementsByTagName("keyword");
//		
//		for(int x = 0; x < nList.getLength(); x++) {
//			
//			Node nNode = nList.item(x);
//			
//			if(nNode.getNodeType() == Node.ELEMENT_NODE) {
//				Element eElement = (Element) nNode;
//				w[x] = new WeightedKeyword(eElement.getElementsByTagName("text").item(0).getTextContent(), Double.parseDouble(eElement.getElementsByTagName("relevance").item(0).getTextContent()));
//			}
//		}		
//		
//		
//		for(WeightedKeyword temp : w) {
//			System.out.println(temp.getKeyword() + "      " + temp.getRelevance());
//		}
//	}

	/**
	 * @return the w
	 */
	public WeightedKeyword[] getW() {
		return w;
	}
	
	public void setW(WeightedKeyword[] w){
	    this.w = w;
	}
}
