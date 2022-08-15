package com.pterapan.uts2072038.dao;

import com.pterapan.uts2072038.model.*;
import com.pterapan.uts2072038.util.MyConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class WatchlistDao implements DaoInterface<WatchlistEntity> {

    @Override
    public List<WatchlistEntity> getData() {
        List<WatchlistEntity> wlist;

        SessionFactory sf = MyConnection.getSessionFactory();
        Session s = sf.openSession();

        CriteriaBuilder bob = s.getCriteriaBuilder();
        CriteriaQuery q = bob.createQuery(WatchlistEntity.class);
        q.from(WatchlistEntity.class);

        wlist = s.createQuery(q).getResultList();

        s.close();
        return wlist;
    }

    @Override
    public int addData(WatchlistEntity data) {
        SessionFactory sf = MyConnection.getSessionFactory();
        Session s = sf.openSession();
        Transaction t = s.beginTransaction();
        int hasil = 0;
        try {
            s.save(data);
            t.commit();
            hasil =  1;
        } catch (Exception e) {
            t.rollback();
        }
        s.close();
        return hasil;
    }

    @Override
    public int delData(WatchlistEntity data) {
        SessionFactory sf = MyConnection.getSessionFactory();
        Session s = sf.openSession();
        Transaction t = s.beginTransaction();
        int hasil = 0;
        try {
            s.save(data);
            t.commit();
            hasil =  1;
        } catch (Exception e) {
            t.rollback();
        }
        s.close();
        return hasil;
    }

    @Override
    public int updateData(WatchlistEntity data) {
        SessionFactory sf = MyConnection.getSessionFactory();
        Session s = sf.openSession();
        Transaction t = s.beginTransaction();
        int hasil = 0;
        try {
            s.save(data);
            t.commit();
            hasil =  1;
        } catch (Exception e) {
            t.rollback();
        }
        s.close();
        return hasil;
    }
}
