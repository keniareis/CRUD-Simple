package com.keniareis.crud.exceptions;

import java.net.PasswordAuthentication;

public class PacienteNotFoundException extends Exception{
    public PacienteNotFoundException(){
        super("Not Found");
    }

    public PacienteNotFoundException(String msg){
        super(msg);
    }
}
