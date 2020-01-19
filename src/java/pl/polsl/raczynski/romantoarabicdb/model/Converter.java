/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.raczynski.romantoarabicdb.model;

import java.util.*;
import pl.polsl.raczynski.romantoarabicdb.model.Value;


/**
 * class that contains algorithm to convert roman number to arabic number
 * 
 * @author Jan Raczynski
 * @version 1.0
 */
public class Converter {
    
    /**
     * Initiates a {@link Converter} object 
     */
    public Converter() {
    }
    
    
    /**
     * a methods for converting roman number to arabic
     * 
     * @param valueToConvert number to convert int arabic number
     * @return an int of a value in arabic number
     */
    public int conversionFromRomanToArabic(String valueToConvert) {
        int resultat = 0; 
        
        List<Character> charactersOfRomanNumber = convertStringToCharList(valueToConvert);
  
        for (int i=0; i<charactersOfRomanNumber.size(); i++) 
        { 
            int value1 = convertCharacterToNumber(charactersOfRomanNumber.get(i)); 
  
            if (i+1 <charactersOfRomanNumber.size()) 
            { 
                int value2 = convertCharacterToNumber(charactersOfRomanNumber.get(i+1)); 
  
                if (value1 >= value2) 
                { 
                    resultat = resultat + value1; 
                } 
                else
                { 
                    resultat = resultat + value2 - value1; 
                    i++;
                } 
            } 
            else
            { 
                resultat = resultat + value1; 
                i++; 
            } 
        } 
  
        return resultat; 
    }
    
    
    /**
     * converting specific symbols to numbers
     * 
     * @param symbol symbol to convert
     * @return a value of specific symbol
     */
    private int convertCharacterToNumber(char symbol) {
        if (symbol == Value.ONE.getSymbol()) 
            return Value.ONE.getValue(); 
        if (symbol == Value.FIVE.getSymbol()) 
            return Value.FIVE.getValue(); 
        if (symbol == Value.TEN.getSymbol()) 
            return Value.TEN.getValue(); 
        if (symbol == Value.FIFTY.getSymbol()) 
            return Value.FIFTY.getValue(); 
        if (symbol == Value.HUNDRED.getSymbol()) 
            return Value.HUNDRED.getValue(); 
        if (symbol == Value.FIVEHUNDRED.getSymbol()) 
            return Value.FIVEHUNDRED.getValue(); 
        if (symbol == Value.THOUSAND.getSymbol()) 
            return Value.THOUSAND.getValue(); 
        return -1; 
    }
    
    
    /**
     * methode to convert strin into list of characters
     * 
     * @param str a string to convert
     * @return returns a converted list
     */
    private static List<Character> convertStringToCharList(String str) { 
        return new AbstractList<Character>() { 
  
            @Override
            public Character get(int index) 
            { 
                return str.charAt(index); 
            } 
  
            @Override
            public int size() 
            { 
                return str.length(); 
            } 
        }; 
    }
}




