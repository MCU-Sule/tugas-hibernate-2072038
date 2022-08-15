package com.pterapan.uts2072038.dao;

import com.pterapan.uts2072038.model.UserEntity;
import com.pterapan.uts2072038.util.MyConnection;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class UserDao implements DaoInterface<UserEntity> {

    @Override
    public List<UserEntity> getData() {
        List<UserEntity> ulist;

        SessionFactory sf = MyConnection.getSessionFactory();
        Session s = sf.openSession();

        CriteriaBuilder bob = s.getCriteriaBuilder();
        CriteriaQuery q = bob.createQuery(UserEntity.class);
        q.from(UserEntity.class);

        ulist = s.createQuery(q).getResultList();

        s.close();
        return ulist;
    }

    @Override
    public int addData(UserEntity data) {
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
    public int delData(UserEntity data) {
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
    public int updateData(UserEntity data) {
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
