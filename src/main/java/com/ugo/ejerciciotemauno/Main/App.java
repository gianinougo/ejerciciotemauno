package com.ugo.ejerciciotemauno.Main;

import com.ugo.ejerciciotemauno.Data.MenuAplicacion;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws ParserConfigurationException, SAXException {
        MenuAplicacion menu = new MenuAplicacion();
        menu.Ejecutar();
    }
}
