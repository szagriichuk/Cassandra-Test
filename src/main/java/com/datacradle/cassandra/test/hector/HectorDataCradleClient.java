package com.datacradle.cassandra.test.hector;

import me.prettyprint.cassandra.service.CassandraHost;
import me.prettyprint.cassandra.service.CassandraHostConfigurator;
import me.prettyprint.cassandra.service.ThriftKsDef;
import me.prettyprint.hector.api.Cluster;
import me.prettyprint.hector.api.Keyspace;
import me.prettyprint.hector.api.ddl.ColumnFamilyDefinition;
import me.prettyprint.hector.api.ddl.ComparatorType;
import me.prettyprint.hector.api.ddl.KeyspaceDefinition;
import me.prettyprint.hector.api.factory.HFactory;
import org.apache.cassandra.thrift.TokenRange;
import org.apache.cassandra.tools.NodeCmd;
import org.apache.cassandra.tools.NodeProbe;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * @author Sergii.Zagriichuk
 */
public class HectorDataCradleClient {

    public static final String SYSTEM_KEYSPACE = "SystemKeyspace";
    public static final String DATACRADLE_KEYSPACE = "DataCradleSystem";
    private Cluster cluster;

    public void connectToDataCradleCluster(String host, int port){
        CassandraHostConfigurator cassandraHostConfigurator = new CassandraHostConfigurator();
        cassandraHostConfigurator.setHosts(host + ":" + port);
        cassandraHostConfigurator.setAutoDiscoverHosts(true);
        this.cluster = HFactory.getOrCreateCluster("DataCradle Cluster", cassandraHostConfigurator);
    }

    public void createSystemKeySpace(){
        HFactory.createKeyspace(SYSTEM_KEYSPACE, cluster);
    }

    public List<String> getDataCradleClusterHosts() throws IOException, InterruptedException {

        List<TokenRange> hosts = cluster.describeRing(SYSTEM_KEYSPACE);
        List<String> resultList = new ArrayList<String>();

        for (TokenRange host : hosts) {
            resultList.addAll(host.getEndpoints());
        }

        return resultList;
    }

}
