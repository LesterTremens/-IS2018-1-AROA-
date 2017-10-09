/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.unam.ciencias.is.modelo;

import mx.unam.ciencias.is.mapeobd.Usuario;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author orlando
 */
public class UsuarioDAO {
    
    
    /*Sesion para conectarnos a la base de datos*/
    private SessionFactory sessionFactory;
    
    /**
     * Inicialisamos la sesion a la base de datos.
     * @param sessionFactory 
     */
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    
    public void guardar(Usuario usuario){
        //Se inicia la sesion
        Session session = sessionFactory.getCurrentSession();
        //La transaccion a realizar
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            // Guardamos el usuario
            session.persist(usuario);
            
            tx.commit();
        } catch (Exception e){
            if(tx != null){
                tx.rollback();
            }
            e.printStackTrace();
        }
        finally{
            session.close();
        }
    }
    
    public void eliminar(Usuario usuario){
        //Se inicia la sesion
        Session session = sessionFactory.getCurrentSession();
        //La transaccion a realizar
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            // Guardamos el usuario
            session.delete(usuario);
            
            tx.commit();
        } catch (Exception e){
            if(tx != null){
                tx.rollback();
            }
            e.printStackTrace();
        }
        finally{
            session.close();
        }
    }
    
    public void actualizar(Usuario usuario){
        //Se inicia la sesion
        Session session = sessionFactory.getCurrentSession();
        //La transaccion a realizar
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            // Guardamos el usuario
            session.update(usuario);
            
            tx.commit();
        } catch (Exception e){
            if(tx != null){
                tx.rollback();
            }
            e.printStackTrace();
        }
        finally{
            session.close();
        }
    } 
    
    public Usuario getUsuario(String correo){
        Usuario salida = null;
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            // Guardamos el usuario
            String val = "From Usuario where correo = : c";
            
            Query query = session.createQuery(val);
            query.setParameter("c",correo);
            salida = (Usuario)query.uniqueResult();
            tx.commit();
            
            
            tx.commit();
        } catch (Exception e){
            if(tx != null){
                tx.rollback();
            }
            e.printStackTrace();
        }
        finally{
            session.close();
        }
        return salida;
        
    }
    
    
    
}
