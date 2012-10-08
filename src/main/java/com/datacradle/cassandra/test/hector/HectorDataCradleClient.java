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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * @author Sergii.Zagriichuk
 */
public class HectorDataCradleClient {

    public static final String SYSTEM_KEYSPACE = "SystemKeyspace";
    private Cluster cluster;
    private Keyspace  keyspace;

    public void getDataCradleCluster(String host, int port){
        CassandraHostConfigurator cassandraHostConfigurator = new CassandraHostConfigurator();
        cassandraHostConfigurator.setHosts(host + ":" + port);
        cassandraHostConfigurator.setAutoDiscoverHosts(true);
        this.cluster = HFactory.getOrCreateCluster("DataCradle Cluster", cassandraHostConfigurator);
    }

    public void createSystemKeySpace(){
        keyspace = HFactory.createKeyspace(SYSTEM_KEYSPACE, cluster);
    }

    public List<String> getDataCradleClusterHosts() {

        Set<CassandraHost> hosts = cluster.getConnectionManager().getHosts();

        List<String> resultList = new ArrayList<String>();

        for (CassandraHost cassandraHost : hosts) {
            resultList.add(cassandraHost.getIp() + " : " + cassandraHost.getPort());
        }

        return resultList;
    }

}
