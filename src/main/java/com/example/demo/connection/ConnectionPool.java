package com.example.demo.connection;

import java.util.ArrayList;

import com.example.demo.connection.DBConnection;
/**
 * manages 5 DB connections
 * @author Elad Cohen
 *
 */
public class ConnectionPool {

    // set maximum of 5 connections
    private static final int NUMBER_OF_CONNECTIONS = 5;

    private static ConnectionPool _instance = null;

    // list of connections
    private ArrayList<DBConnection> connections = null;

    public static synchronized ConnectionPool getInstance()
    {
        if (_instance == null)
        {
            _instance = new ConnectionPool();
        }
        return _instance;
    }

    // CTOR - will create 5 connections and add them into the list
    private ConnectionPool() {

        // create new list
        this.connections = new ArrayList<>();

        // add 5 itmes into the list
        for (int i = 0; i < NUMBER_OF_CONNECTIONS; i++)
        {
            // create DB connection
            DBConnection dbConnection = new DBConnection(8080 + i, "Connection number " + i);

            // add DBConnection to the list
            this.connections.add( dbConnection );
        }
    }

    // get a connection from the list
    // if there is no connection available- wait
    public synchronized DBConnection getConnection() throws InterruptedException {
        while (this.connections.size() == 0) {
            System.out.println("Thread " + Thread.currentThread().getName() + " is now Waiting since no available connections...");
            wait();
        }
        DBConnection connection = this.connections.get(0);
        this.connections.remove(0);
        System.out.println("Giving connection : " + connection + " to Thread " + Thread.currentThread().getName());
        return connection;

    }

    // return connection into the list- notify (the waiters)
    public synchronized void returnConenction(DBConnection connection)
    {
        this.connections.add(connection);
        System.out.println(Thread.currentThread().getName() + " is Calling notify!");
        notify();
    }

}