package Exceptions;

import java.io.IOError;
import java.io.IOException;

public class IncorrectData extends IOException{
    
    public IncorrectData (String message) {
        super(message);
    }


}
