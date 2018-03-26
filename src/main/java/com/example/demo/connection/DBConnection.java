package com.example.demo.connection;
/**
 * holds a connection of DBConnection
 * 
 * @author Elad Cohen
 *
 */
public class DBConnection {
    private int port;
    private String url;

    public DBConnection(int port, String url) {
        this.port = port;
        this.url = url;
    }

    @Override
    public String toString() {
        return "DBConnection{" +
                "port=" + port +
                ", url='" + url + '\'' +
                '}';
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
