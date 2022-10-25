package com.ugo.ejerciciotemauno.Main;

import com.ugo.ejerciciotemauno.Data.MenuAplicacion;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.FileNotFoundException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws ParserConfigurationException, SAXException, FileNotFoundException {
        MenuAplicacion menu = new MenuAplicacion();
        menu.Ejecutar();
    }
}
