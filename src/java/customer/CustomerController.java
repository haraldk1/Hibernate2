/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package customer;

import javax.faces.bean.ManagedBean;
//import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

/**
 *
 * @author hkonnert
 */
@ManagedBean
@SessionScoped
public class CustomerController {
    
    CustomerHelper helper;
    int startId;
    int endId;
    DataModel filmTitles;
    private int recordCount = 1000;
    private int pageSize = 10;

    private Customer current;
    private int selectedItemIndex;
    //helper.getNames();

    /**
     * Creates a new instance of CustomerController
     */
    public CustomerController() {
        helper = new CustomerHelper();
        startId = 1;
        endId = 10;
    }
    
    
    public CustomerController(int startId, int endId) {
        helper = new CustomerHelper();
        this.startId = startId;
        this.endId = endId;
    }

    public Customer getSelected() {
        if (current == null) {
            current = new Customer();
            selectedItemIndex = -1;
        }
        return current;
    }


    public DataModel getCustomerNames() {
        if (filmTitles == null) {
            filmTitles = new ListDataModel(helper.getCustomerNames(startId, endId));
        }
        return filmTitles;
    }

    void recreateModel() {
        filmTitles = null;
    }
    
    
    public boolean isHasNextPage() {
        if (endId + pageSize <= recordCount) {
            return true;
        }
        return false;
    }

    public boolean isHasPreviousPage() {
        if (startId-pageSize > 0) {
            return true;
        }
        return false;
    }

    public String next() {
        startId = endId+1;
        endId = endId + pageSize;
        recreateModel();
        return "index";
    }

    public String previous() {
        startId = startId - pageSize;
        endId = endId - pageSize;
        recreateModel();
        return "index";
    }

    public int getPageSize() {
        return pageSize;
    }

    public String prepareView(){
        current = (Customer) getCustomerNames().getRowData();
        return "browse";
    }
    public String prepareList(){
        recreateModel();
        return "index";
    }
}
