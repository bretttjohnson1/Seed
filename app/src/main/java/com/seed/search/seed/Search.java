package com.seed.search.seed;

import android.os.Bundle;

import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

/**
 * Created by brett on 2/13/16.
 */
public class Search {
    public static Bundle[] performSearch(String query){
        Bundle temporary[] = new Bundle[10];
        try {
            WordnetSynonymSearch wordsynonymsearch = new WordnetSynonymSearch(query);
            WeightedKeyword[] wkeywords = wordsynonymsearch.getArray();
            ArrayList<RelevantQuery> answers = Algo.GetAnswer("arbon (from Latin: carbo ) is a chemical element with symbol C and atomic number 6. On the periodic table, it is the first (row 2) of six elements in column (group) 14, which have in common the composition of their outer electron shell. It is nonmetallic and tetravalent—making four electrons available to form covalent chemical bonds. There are three naturally occurring isotopes, with 12C and 13C being stable, while 14C is radioactive, decaying with a half-life of about 5,730 years.[14] Carbon is one of the few elements known since antiquity.[15]\n" +
                    "Carbon is the 15th most abundant element in the Earth's crust, and the fourth most abundant element in the universe by mass after hydrogen, helium, and oxygen. It is present in all forms of carbon-based life, and in the human body carbon is the second most abundant element by mass (about 18.5%) after oxygen.[16] This abundance, together with the unique diversity of organic compounds and their unusual polymer-forming ability at the temperatures commonly encountered on Earth, make this element the chemical basis of all known life.\n" +
                    "The atoms of carbon can be bonded together in different ways: allotropes of carbon. The best known are graphite, diamond, and amorphous carbon.[17] The physical properties of carbon vary widely with the allotropic form. For example, graphite is opaque and black, while diamond is highly transparent. Graphite is soft enough to form a streak on paper (hence its name, from the Greek word \"γράφω\" which means \"to write\"), while diamond is the hardest naturally-occurring material known. Graphite is a very good conductor, while diamond has a very low electrical conductivity. Under normal conditions, diamond, carbon nanotubes, and graphene have the highest thermal conductivities of all known materials. All carbon allotropes are solids under normal conditions, with graphite being the most thermodynamically stable form. They are chemically resistant and require high temperature to react even with oxygen.\n" +
                    "The most common oxidation state of carbon in inorganic compounds is +4, while +2 is found in carbon monoxide and transition metal carbonyl complexes. The largest sources of inorganic carbon are limestones, dolomites and carbon dioxide, but significant quantities occur in organic deposits of coal, peat, oil and methane clathrates. Carbon forms a vast number of compounds, more than any other element, with almost ten million compounds described to date,[18] which in turn are a tiny fraction of such compounds that are theoretically possible under standard conditions.", query, wkeywords);

            for(int a= 0;a<temporary.length;a++){
                temporary[a].putInt("page",a);
                temporary[a].putString("sentence",answers.get(a).getSentence());
                temporary[a].putString("paragraph",answers.get(a).getFullPage());
            }
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        return temporary;
    }

}
