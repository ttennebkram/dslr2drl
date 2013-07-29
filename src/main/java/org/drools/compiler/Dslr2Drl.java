package org.drools.compiler;

// Based on:
// http://stackoverflow.com/questions/2280068
// http://stackoverflow.com/questions/2402545
// http://anonsvn.jboss.org/repos/labs/labs/jbossrules/trunk/drools-compiler/src/test/java/org/drools/compiler/DrlParserTest.java

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;

/***
// Include these if using Injected Expander
import org.drools.RuntimeDroolsException;
import org.drools.lang.Expander;
import org.drools.lang.dsl.DSLMappingFile;
import org.drools.lang.dsl.DSLTokenizedMappingFile;
import org.drools.lang.dsl.DefaultExpander;
import org.drools.lang.dsl.DefaultExpanderResolver;
***/

public class Dslr2Drl {
	public static String readFileAsString( String inFileName ) throws IOException {
		StringBuffer buff = new StringBuffer();
		BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(inFileName), "UTF-8"));
		while( true ) {
			String line = in.readLine();
			if ( null==line ) {
				break;
			}
			buff.append( line ).append( "\r\n" );
		}
		in.close();
		return new String( buff );
	}
	public static void main(String[] args) throws DroolsParserException, IOException {
		if ( 2 != args.length ) {
			System.err.println( "syntax: Dslr2Drl sentences.dslr transform.dsl > output.drl" );
			System.exit(1);
		}
		// String dslr = "rule 'foo' \n when \n Something \n then \n another \nend";
		String dslr = readFileAsString( args[0] );
		// String dsl = "[condition]Something=Something()\n[then]another=another();";
		String dsl = readFileAsString( args[1] );
		// String expect = "rule 'foo' \n when \n Something() \n then \n another(); \nend";

		DrlParser parser = new DrlParser();
		String result = parser.getExpandedDRL( dslr, new StringReader(dsl) );
		System.out.println( result );

		/***
		// Using Injected Expander
		DefaultExpanderResolver resolver = new DefaultExpanderResolver(new StringReader(dsl));
		final DSLMappingFile file = new DSLTokenizedMappingFile();
		if ( file.parseAndLoad( new StringReader(dsl) ) ) {
			final Expander expander = new DefaultExpander();
			expander.addDSLMapping( file.getMapping() );
			resolver.addExpander("*", expander);
		} else {
			throw new RuntimeDroolsException( "Error parsing and loading DSL file." + file.getErrors() );
    	}
		DrlParser parser = new DrlParser();
		String result = parser.getExpandedDRL( drl, resolver);
		***/
	}
}
