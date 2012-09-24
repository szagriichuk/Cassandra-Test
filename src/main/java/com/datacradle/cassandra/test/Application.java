package com.datacradle.cassandra.test;

import org.apache.cassandra.service.EmbeddedCassandraService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.IOException;

/**
 * Hello world!
 */
public class Application extends SpringApplication {
    @Autowired
    private EmbeddedCassandraService embeddedCassandraService;


    public static void main(String[] args) throws IOException {

        printStartFolder();
        startEmbeddedCassandra(new Application());
    }

    private static void printStartFolder() {

        System.out.println(new File(".").getAbsolutePath());
    }

    private static void startEmbeddedCassandra(Application application) throws IOException {
        application.embeddedCassandraService.start();
    }

    @Override
    protected String[] getConfigLocations() {
        return new String[]{"casandra-test-config.xml"};
    }
}
