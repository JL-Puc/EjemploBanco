package Ejemplo01;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import javax.print.attribute.standard.PrinterMessageFromOperator;

public class Main {
    
    public static void main(String[] args) throws IOException {
        
       FileReader bancoFile = new FileReader("Ejemplo01\\Banco.txt");
        BufferedReader obj = new BufferedReader(bancoFile);
        String cadena;

        while((cadena = obj.readLine())  != null){
        System.out.println(cadena);
    } 
    }
    
    






}





