dslr2drl
========

Drools utility to take a DSLR file and a DSL file and output a DRL file

Drools is a Business Rules engine.  DSLR are high level sentences, which are transformed by the DSL into the native DRL rules format used by Drools.

Code based on:

* http://anonsvn.jboss.org/repos/labs/labs/jbossrules/trunk/drools-compiler/src/test/java/org/drools/compiler/DrlParserTest.java
* http://stackoverflow.com/questions/2280068
* http://stackoverflow.com/questions/2402545


Syntax
------
```java -jar dslr2drl-1.0-SNAPSHOT.jar sentences.dslr transform.dsl > output.drl```

Build or Just Run
-----------------
This project creates a self-contained jar file by using the Maven shade plugin

I included a built jar in the target directory because some people don't have maven installed, so grab a copy of the file ```target/dslr2drl-1.0-SNAPSHOT.jar```.  You can rename it to just dslr2drl.jar on your machine if you want.

There's also sentences.dslr and transform.dsl in the main directory which were copied from the unit test source code.

To build from scratch:

```mvn -DskipTests clean package```

Code
----
This project only has 1 java class:

* ```dslr2drl/src/main/java/org/drools/compiler/Dslr2Drl.java```

Which boils down to 3 important lines of code from a Drools unit test:

```
        DrlParser parser = new DrlParser();
        String result = parser.getExpandedDRL( dslr, new StringReader(dsl) );
        System.out.println( result );
```

I added supporting code to read the file, and left some of the other code in place in case you need to use your own Expander.

