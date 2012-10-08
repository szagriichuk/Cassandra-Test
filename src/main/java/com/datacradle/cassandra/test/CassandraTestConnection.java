package com.datacradle.cassandra.test;

import org.apache.cassandra.thrift.Cassandra;
import org.apache.cassandra.thrift.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

/**
 * @author Sergii.Zagriichuk
 */
public class CassandraTestConnection {
    private TTransport transport;
    private TProtocol protocol;
    private TSocket socket;

    public CassandraTestConnection(String host, int port) {
        socket = new TSocket(host, port);
        transport = new TFramedTransport(socket);
        protocol = new TBinaryProtocol(transport);
    }

    public void open() throws Exception {
        transport.open();
    }

    public void close() throws Exception {
        transport.close();
        socket.close();
    }

    public Cassandra.Client getClient() {
        return new Cassandra.Client(protocol);
    }
}
