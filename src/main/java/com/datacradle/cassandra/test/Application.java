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
        Application application = new Application();
        application.printStartFolder();
        application.startEmbeddedCassandra();
    }

    public void printStartFolder() {
        System.out.println(new File(".").getAbsolutePath());
    }

    public void startEmbeddedCassandra() throws IOException {
        embeddedCassandraService.start();
    }

    @Override
    protected String[] getConfigLocations() {
        return new String[]{"casandra-test-config.xml"};
    }
}
