package com.ugo.ejerciciotemauno.Main;

import com.opencsv.exceptions.CsvException;
import com.opencsv.exceptions.CsvValidationException;
import com.ugo.ejerciciotemauno.Data.MenuAplicacion;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.FileNotFoundException;
import java.io.IOException;

public class App 
{
    public static void main( String[] args ) throws ParserConfigurationException, SAXException, IOException, CsvException {
        MenuAplicacion menu = new MenuAplicacion();
        menu.Ejecutar();
    }
}
