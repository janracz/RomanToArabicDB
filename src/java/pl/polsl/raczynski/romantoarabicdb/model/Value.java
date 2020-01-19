/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.raczynski.romantoarabicdb.model;

/**
 * Class that contains values of symbols in roman numbers
 * 
 * @author Jasiek
 * @version 1.0
 */
public enum Value {
    
    /**
     * Contains a symbol and value of each roman number
     */
    ONE('I', 1),
    FIVE('V', 5),
    TEN('X', 10),
    FIFTY('L', 50),
    HUNDRED('C', 100),
    FIVEHUNDRED('D', 500),
    THOUSAND('M', 1000);
    
    /**
     * symbol of roman number
     */
    private final char symbol;
    
    /**
     * value of the roman number
     */
    private final int valueOfSymbol;
    
    
    /**
     * initiates a {@link Value} object
     * 
     * @param symbol a symbol to set
     * @param valueOfSymbol a value to set
     */
    Value(char symbol, int valueOfSymbol){
        this.symbol = symbol;
        this.valueOfSymbol = valueOfSymbol;
    }
    
    
    /**
     * Returns the value of the private field "symbol"
     * 
     * @return a symbol of roman number
     */
    public char getSymbol() {
        return symbol;
    }
    
    
    /**
     * Returns the value of the private field "valueOfSymbol"
     * 
     * @return a value of roman number
     */
    public int getValue() {
        return valueOfSymbol;
    }
}
