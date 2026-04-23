/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.smartcampus.exception;


public class LinkedResourceNotFoundException extends RuntimeException {

    public LinkedResourceNotFoundException() {
        super("Referenced resource does not exist.");
    }

    public LinkedResourceNotFoundException(String message) {
        super(message);
    }
}