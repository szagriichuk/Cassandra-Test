package com.datacradle.cassandra.test;

import org.apache.cassandra.service.EmbeddedCassandraService;

import java.io.IOException;

/**
 * @author Sergii.Zagriichuk
 */
public class DataCradleCasandra {

    public void startEmbeddedService(int countOfService) {
        EmbeddedCassandraService embeddedCassandraService;
        for (int index = 0; index < countOfService; index++) {
            embeddedCassandraService = new EmbeddedCassandraService();
            try {
                embeddedCassandraService.start();
            } catch (IOException e) {
                System.out.println("ERROR !!! " + e.getMessage());
            }
        }
    }
}
