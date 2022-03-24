package com.toby.spring;

import java.sql.Connection;
import java.sql.SQLException;

public class NConnectionMaker implements ConnectionMaker{

    @Override
    public Connection makeConnection() throws ClassNotFoundException, SQLException {
        System.out.println("NConnectionMaker make connection");
        return null;
    }
}
