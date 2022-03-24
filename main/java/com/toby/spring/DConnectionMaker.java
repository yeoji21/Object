package com.toby.spring;

import java.sql.Connection;
import java.sql.SQLException;

public class DConnectionMaker implements ConnectionMaker{

    @Override
    public Connection makeConnection() throws ClassNotFoundException, SQLException {
        System.out.println("DConnectionMaker make connection");
        return null;
    }
}
