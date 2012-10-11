package com.datacradle.cassandra.test;


import org.apache.cassandra.thrift.*;
import org.apache.thrift.TException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.List;

/**
 * Unit test for simple Application.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:casandra-test-config.xml"})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class})
public class ApplicationTest {

    private Application application;
    public static final String DATACRADLE_KEYSPACE = "DataCradleSystem1111";

    @Autowired
    private CassandraTestConnection testConnection;

    @Autowired
    private DataCradleCasandra dataCradleCasandra;


    @Test
    public void testApp() throws Exception {
            testConnection.open();
            Cassandra.Client client = testConnection.getClient();

//            String cql="CREATE KEYSPACE test1 WITH strategy_class = 'SimpleStrategy'\n" +
//                    "    AND strategy_options:replication_factor = 1;";
//            client.execute_cql_query(ByteBuffer.wrap(cql.getBytes()), Compression.NONE);

            List<TokenRange> data= null;
                data = client.describe_ring("test1");
            for (TokenRange tokenRange : data) {
                System.out.println(tokenRange.endpoints);
            }
            testConnection.close();
    }

}


