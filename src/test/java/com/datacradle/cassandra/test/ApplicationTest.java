package com.datacradle.cassandra.test;


import org.apache.cassandra.thrift.Cassandra;
import org.apache.cassandra.thrift.KsDef;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import java.io.IOException;

/**
 * Unit test for simple Application.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:casandra-test-config.xml"})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class})
public class ApplicationTest {

    private Application application;

    @Autowired
    private CassandraTestConnection testConnection;

    @Autowired
    private DataCradleCasandra dataCradleCasandra;

    @Before
    public void setUp() throws IOException {

        dataCradleCasandra.startEmbeddedService(3);
    }

    @Test
    public void testApp() throws Exception {
        testConnection.open();
        Cassandra.Client client = testConnection.getClient();
        System.out.println("version " + client.describe_version());
        System.out.println("partitioner"
                + client.describe_partitioner());
        System.out.println("cluster name " + client.describe_cluster_name());
        for (KsDef keyspace : client.describe_keyspaces()) {
            System.out.println("keyspace " + keyspace);
        }
        testConnection.close();
    }

}


