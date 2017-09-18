/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.unam.ciencias.is.modelo;

import mx.unam.ciencias.is.mapeobd.Marcador;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author jonathan
 */
public class MarcadorDAO {
    
    /*Sesion para conectarnos a la base de datos*/
    private SessionFactory sessionFactory;
    
    /**
     * Inicialisamos la sesion a la base de datos.
     * @param sessionFactory 
     */
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    
    /**
     * Guarda un marcador a la base de datos 
     * @param marcador el marcador a guardar.
     */
    public void guardar(Marcador marcador) {
        //se inicia la sesion
        Session session = sessionFactory.openSession();
        //la transaccion a relizar
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            //guardamos el marcador
            session.persist(marcador);
           
            tx.commit();
        }
        catch (Exception e) {
            //Se regresa a un estado consistente 
            if (tx!=null){ 
                tx.rollback();
            }
            e.printStackTrace(); 
        }
        finally {
            //cerramos simpre la sesion
            session.close();
        }
    
    }
    
    /**
     * Regresa la lista de todos los marcadores en la base de datos
     * @return la lista que contiene a todos los marcadores de la base de datos
     */
    public List<Marcador> getMarcadores(){       
        //Aqui va tu codigo
        List<Marcador> result = null;        
        Session session = sessionFactory.openSession();        
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String hql = "FROM Marcador";
            Query query = session.createQuery(hql);
            result = (List<Marcador>)query.list();
            tx.commit();
        }
        catch (Exception e) {
            //Se regresa a un estado consistente 
            if (tx!=null){ 
                tx.rollback();
            }
            e.printStackTrace(); 
        }
        finally {
            //cerramos simpre la sesion
            session.close();
        }
        return result;
    }
    
    /**
     * Regresa el marcador con la longitud  y latitud dada. 
     * @param lattitud
     * @param longitud
     * @return el marcador con la longitud y latitud dada.
     */
    public Marcador getMarcador(double lattitud, double longitud) {
        Marcador result = null;
        Session session = sessionFactory.openSession();        
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String hql = "FROM Marcador Where latitud = :latitud and longitud = :longitud";
            Query query = session.createQuery(hql);
            query.setParameter("latitud", lattitud);
            query.setParameter("longitud", longitud);
            result = (Marcador)query.uniqueResult();
            tx.commit();
        }
        catch (Exception e) {
            //Se regresa a un estado consistente 
            if (tx!=null){ 
                tx.rollback();
            }
            e.printStackTrace(); 
        }
        finally {
            //cerramos simpre la sesion
            session.close();
        }
        return result;
        
    }
    
    /**
     * Regresa el marcador con el id dado
     * @param id del marcador 
     * @return el marcador con ese id
     */
    public Marcador getMarcadorId(int id) {
        //aqui va tu codigo
        Marcador result = null;
        Session session = sessionFactory.openSession();        
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String hql = "FROM Marcador Where  marcador_id = :id1";
            Query query = session.createQuery(hql);
            query.setParameter("id2                 ", id);
            result = (Marcador)query.uniqueResult();
            tx.commit();
        }
        catch (Exception e) {
            //Se regresa a un estado consistente 
            if (tx!=null){ 
                tx.rollback();
            }
            e.printStackTrace(); 
        }
        finally {
            //cerramos simpre la sesion
            session.close();
        }
        return result;
    }
    
    
    /**
     * Elimina el marcador de la base de datos
     * @param marcador el marcador a eliminar
     */
    public void eliminar(Marcador marcador) {
        //aqui va tu codigo
        //se inicia la sesion
        Session session = sessionFactory.openSession();
        //la transaccion a relizar
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            //guardamos el marcador
            session.delete(marcador);
           
            tx.commit();
        }
        catch (Exception e) {
            //Se regresa a un estado consistente 
            if (tx!=null){ 
                tx.rollback();
            }
            e.printStackTrace(); 
        }
        finally {
            //cerramos simpre la sesion
            session.close();
        }
    }
    
    /**
     * Actualiza el marcardor en la base de datos
     * @param marcador con los nuevos valores 
     */
    public void actualizar(Marcador marcador) {
        //aqui va tu codigo
        //se inicia la sesion
        Session session = sessionFactory.openSession();
        //la transaccion a relizar
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            //guardamos el marcador
            session.update(marcador);
           
            tx.commit();
        }
        catch (Exception e) {
            //Se regresa a un estado consistente 
            if (tx!=null){ 
                tx.rollback();
            }
            e.printStackTrace(); 
        }
        finally {
            //cerramos simpre la sesion
            session.close();
        }
    }
}
