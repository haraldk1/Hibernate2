/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package customer;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author hkonnert
 */
public class CustomerHelper {
    
    //erstelle Sitzung --> im Konstruktor 
    
    Session session;
    
    
    
    public CustomerHelper(){
        this.session = HibernateUtil.getSessionFactory().getCurrentSession();
    }
    
    
    
    //Sitzung öffnen, Transaktion beginnen , Abfrage ausführen und Liste zurückgeben
    
    public List getCustomerNames(int startid, int endid){
        
        List<Customer> CustList = null;        
        
        try{        
            Transaction beginTransaction = session.beginTransaction();
            Query q1 = session.createQuery("from Customer as customer where customer.customerId between "+1+" and "+5);
//            Query q1 = session.createQuery("from Customer as customer");
            CustList = (List<Customer>) q1.list();
            beginTransaction.commit();
        
        }catch (Exception e) {System.out.println(e);}
        
        return CustList ;
    }
}
