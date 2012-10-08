package com.datacradle.cassandra.test.hector;

import me.prettyprint.hector.api.Cluster;
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

    @Test
    public void testCreateSystemScheme() throws Exception {
       hectorDataCradleClient.getDataCradleCluster("127.0.0.1", 7201);

       hectorDataCradleClient.createSystemKeySpace();

       List<String> hosts = hectorDataCradleClient.getDataCradleClusterHosts();
        for (String host : hosts) {
            System.out.println(host);
        }

    }
}
