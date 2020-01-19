/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.raczynski.romantoarabicdb.model;

/**
 * class containing roman number value
 * 
 * @author Jan Raczynski
 * @version 1.0
 */
public class RomanNumber {
    /**
     * String containing value of roman number
     */
    private String value;
    
    /**
     * Initiates a {@link RomanNumber} object 
     */
    public RomanNumber() {
    }
    
    /**
     * Sets the value of {@link RomanNumber}
     * 
     * @param romanNum roman number to set
     * @throws pl.polsl.raczynski.romantoarabicservlets.model.NumberException
     */
    public void setRomanNumber(String romanNum) throws NumberException {
            if (romanNum == null) { //checking if string exist
                throw new NumberException("The string doesn't exist!!!");
            } 
            if (romanNum.isEmpty()) { // checking if empty
                throw new NumberException("The string is empty!!!");
            } 
            if (romanNum.length() > 9) { //checking if not too long
                throw new NumberException("String is too long!!!");
            } 
            if (!romanNum.matches("^M{0,4}(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})$")){ //checking syntax
                throw new NumberException("Wrong syntax of string!!!");
            }
            this.value = romanNum;
    }
    
    
    /**
     * Returns the value of the private field "value"
     * 
     * @return int containing the value of the private field in the {@link RomanNumber}
     */
    public String getRomanNumber() {
        return this.value;
    }
    
    
    /**
     * checks if string in {@link RomanNumber} is exists
     * checks if string in {@link RomanNumber} is empty
     * checks if string in {@link RomanNumber} is too long
     * checks if string in {@link RomanNumber} have proper syntax
     * 
     * @param num number to check
     * @throws NumberException 
     */
    public void checkString(String num) throws NumberException {
        if (num == null) { //checking if string exist
          throw new NumberException("The string doesn't exist!!!");
        } else if (num.isEmpty()) { // checking if empty
          throw new NumberException("The string is empty!!!");
        } else if (num.length() > 9) { //checking if not too long
          throw new NumberException("String is too long!!!");
        } else if (!num.matches("^M{0,4}(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})$")){ //checking syntax
            throw new NumberException("Wrong syntax of string!!!");
        }
    }
}


