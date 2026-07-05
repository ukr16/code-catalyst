package org.example.Impl;

import org.example.dao.FlatsDAO;
import org.example.entity.Flats;
import org.example.entity.Resident;
import org.example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class FlatsDAOImpl implements FlatsDAO {

    @Override
    public void save(Flats flat){
            Transaction tx = null;
            try(Session session = HibernateUtil.getSessionFactory().openSession()){
                tx = session.beginTransaction();
                session.persist(flat);
                tx.commit();
        }catch(Exception e){
                if(tx != null) tx.rollback();
                e.printStackTrace();
            }
    }

    @Override
    public void update(Flats flat){
        Transaction tx = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            tx = session.beginTransaction();
            session.merge(flat);
            tx.commit();
        }catch(Exception e){
            if(tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long flatId){
        Transaction tx = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            Flats flat = session.find(Flats.class, flatId);
            tx = session.beginTransaction();
            if(flat != null) session.remove(flat);
            tx.commit();
        }catch(Exception e){
            if(tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public Flats findById(Long flatId){
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.find(Flats.class, flatId);
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Flats> findAllFlats(){
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Flats", Flats.class).list();
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Flats> findByFloor(int floor){
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Flats where floor = :floor", Flats.class).setParameter("floor", floor).list();
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Flats> findByBlock(String block){
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Flats where block = :block", Flats.class).setParameter("block", block).list();
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Flats> findByStatus(Flats.FlatStatus flatStatus){
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Flats where flatStatus = :flatStatus", Flats.class).setParameter("flatStatus", flatStatus).list();
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Resident> findResidentsByFlat(String flatNumber){
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            Flats flat = session.createQuery("from Flats where flatNumber = :flatNumber", Flats.class).setParameter("flatNumber", flatNumber).uniqueResult();
            return flat != null ? flat.getResidents() : null;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Flats findByFlatNumber(String flatNumber){
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Flats where flatNumber = :flatNumber", Flats.class).setParameter("flatNumber", flatNumber).uniqueResult();
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
