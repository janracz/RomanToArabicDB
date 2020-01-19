/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.raczynski.romantoarabicdb.model;

/**
 * Exception class for objects thrown when numbers doesnt't meet conditions
 *
 * @author Jan Raczynsk
 * @version 1.1
 */
public class NumberException extends Exception {


    /**
     * Exception class constructor
     *
     * @param message display message
     */
    public NumberException(String message) {
        super(message);
    }
}