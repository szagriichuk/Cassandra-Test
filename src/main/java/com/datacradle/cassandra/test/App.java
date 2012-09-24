package com.datacradle.cassandra.test;

import org.apache.cassandra.service.EmbeddedCassandraService;

import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException {
        EmbeddedCassandraService cassandra;
        cassandra = new EmbeddedCassandraService();
        cassandra.start();
    }
}
