package com.datacradle.cassandra.test.hector;

import com.datacradle.cassandra.test.DataCradleCasandra;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import java.util.List;

/**
 * @author Sergii.Zagriichuk
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:casandra-test-config.xml"})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class})
public class HectorDataCradleClientTest {

    @Autowired
    private HectorDataCradleClient hectorDataCradleClient;
    @Autowired
    private DataCradleCasandra dataCradleCasandra;

//    @Before
//    public void setUp() throws Exception {
//        dataCradleCasandra.startEmbeddedService(1);
//    }

    @Test
    public void testCreateSystemScheme() throws Exception {

       hectorDataCradleClient.connectToDataCradleCluster("127.0.0.1", 9160);

       hectorDataCradleClient.createSystemKeySpace();

       List<String> hosts = hectorDataCradleClient.getDataCradleClusterHosts();
        for (String host : hosts) {
            System.out.println(host);
        }

    }
}
