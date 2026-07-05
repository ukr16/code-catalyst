package org.example.Impl;

import org.example.dao.ComplaintDAO;
import org.example.entity.Compliant;
import org.example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.time.LocalDateTime;
import java.util.List;

public class CompliantDAOImpl implements ComplaintDAO {

    public void save(Compliant compliant){
        Transaction tx = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            tx = session.beginTransaction();
            session.persist(compliant);
            tx.commit();
        }catch(Exception e){
            if(tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    public Compliant findByCompliantId(Long compliantId){
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            return session.find(Compliant.class, compliantId);
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    public List<Compliant> findAllCompliants(){
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            return session.createQuery("from Compliant", Compliant.class).list();
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    public void update(Compliant compliant){
        Transaction tx = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            tx = session.beginTransaction();
            session.merge(compliant);
            tx.commit();
        }catch(Exception e){
            if(tx != null) tx.rollback();
            e.printStackTrace();
        }
    }
    public void delete(Long compliantId){
        Transaction tx = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            tx = session.beginTransaction();
            session.find(Compliant.class, compliantId);
            if(compliantId != null) session.remove(compliantId);
            tx.commit();
        }catch(Exception e){
            if(tx != null) tx.rollback();
            e.printStackTrace();
        }
    }
    public List<Compliant> findByCompliantTitle(String compliantTitle){
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            return session.createQuery("from Compliant where compliantTitle = :compliantTitle ", Compliant.class).setParameter("compliantTitle", compliantTitle).list();
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    public List<Compliant> findByCompliantStatus(Compliant.CompliantStatus compliantStatus){
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            return session.createQuery("from Compliant where compliantStatus = :compliantStatus ", Compliant.class).setParameter("compliantStatus", compliantStatus).list();
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    public List<Compliant> findByResident(Long residentId){
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            return session.createQuery("from Compliant where resident.residentId = :residentId ", Compliant.class).setParameter("residentId", residentId).list();
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    public List<Compliant> findByFlat(Long flatId){
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            return session.createQuery("from Compliant where flats.flatId = :flatId ", Compliant.class).setParameter("flatId", flatId).list();
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    public List<Compliant> findByCompliantCreatedAt(LocalDateTime compliantCreatedAt){
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            return session.createQuery("from Compliant where compliantCreatedAt = :compliantCreatedAt ", Compliant.class).setParameter("compliantCreatedAt", compliantCreatedAt).list();
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
