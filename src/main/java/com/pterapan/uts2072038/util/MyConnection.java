package com.pterapan.uts2072038.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyConnection {
    public static SessionFactory getSessionFactory() {
        SessionFactory sf = new Configuration().configure().buildSessionFactory();
        return sf;
    }
}
