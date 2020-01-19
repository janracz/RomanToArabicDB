/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.raczynski.romantoarabicdb.model;


/**
 * class containing arabic number value
 * 
 * @author Jan Raczynski
 * @version 1.0
 */
public class ArabicNumber {
    /**
     * int containing value of arabic number
     */
    private int value;

    /**
     * Initiates a {@link ArabicNumber} object 
     */
    public ArabicNumber() {
    } 
    
    
    /**
     * Sets the value of {@link ArabicNumber}
     * 
     * @param arabicNum arabic number to set
     */
    public void setArabicNumber(int arabicNum)  {
        try {
            checkInt(arabicNum);
            this.value = arabicNum;
        } catch (NumberException ex) {
            System.err.println(ex.getMessage());
            System.exit(0);
        }
    }
    
    
    /**
     * Returns the value of the private field "value"
     * 
     * @return int containing the value of the private field in the {@link ArabicNumber}.
     */
    public int getArabicNumber() {
        return this.value;
    }
    
    
    /**
     * checks if value in {@link ArabicNumber} is positive
     * 
     * @param num number to check
     * @throws NumberException 
     */
    private void checkInt(int num) throws NumberException {
        if (num < 1) { //checking number is positive
          throw new NumberException("Number is not positive!!!");
        } 
    }
}



