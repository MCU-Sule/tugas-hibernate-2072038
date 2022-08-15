package com.pterapan.uts2072038.dao;

import com.pterapan.uts2072038.model.MovieEntity;
import com.pterapan.uts2072038.util.MyConnection;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class MovieDao implements DaoInterface<MovieEntity> {

    @Override
    public List<MovieEntity> getData() {
        List<MovieEntity> mlist;

        SessionFactory sf = MyConnection.getSessionFactory();
        Session s = sf.openSession();

        CriteriaBuilder bob = s.getCriteriaBuilder();
        CriteriaQuery q = bob.createQuery(MovieEntity.class);
        q.from(MovieEntity.class);

        mlist = s.createQuery(q).getResultList();

        s.close();
        return mlist;
    }

    @Override
    public int addData(MovieEntity data) {
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
    public int delData(MovieEntity data) {
        SessionFactory sf = MyConnection.getSessionFactory();
        Session s = sf.openSession();
        Transaction t = s.beginTransaction();
        int hasil = 0;
        try {
            s.delete(data);
            t.commit();
            hasil =  1;
        } catch (Exception e) {
            t.rollback();
        }
        s.close();
        return hasil;
    }

    @Override
    public int updateData(MovieEntity data) {
        SessionFactory sf = MyConnection.getSessionFactory();
        Session s = sf.openSession();
        Transaction t = s.beginTransaction();
        int hasil = 0;
        try {
            s.update(data);
            t.commit();
            hasil =  1;
        } catch (Exception e) {
            t.rollback();
        }
        s.close();
        return hasil;
    }
}
