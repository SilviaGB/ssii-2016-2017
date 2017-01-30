package es.urjc.ssii.holamundolog4j;

import org.apache.logging.log4j.LogManager;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	LogManager.getLogger(App.class).info("Hola mundo.");
    }
}
