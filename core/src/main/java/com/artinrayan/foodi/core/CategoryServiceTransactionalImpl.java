package com.artinrayan.foodi.core;

import com.artinrayan.foodi.model.Category;
import com.artinrayan.foodi.model.Host;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import until.DBConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by asus on 1/2/2018.

     Since you are not working with some framework, like Spring Transaction (which I would strong recommend you to use), I would recommend you do the following:

     Use just one DAO class to make all the business process that you need and use the transaction just there.
     Create the Connection in the Service layer and pass it as parameter through all the DAOs that you need in the business process. I dont like this way, because you will delegate the connection control to a service layer, nor a Data Access one.
     Change the JDBC to use Spring Transaction, it will allow you to annote the methods that will be in the same transaction in the service layer, despite they are located in different DAOs. This works because Spring will control the Connection, so it'll inject the same connection through the DAOs.
 */
//@Service
public class CategoryServiceTransactionalImpl{

//    @Autowired
//    CategoryDao categoryDao;
//
//
//    @Override
//    public void saveCategory(Category category) {
//
//        Connection con = null;
//        try {
//            con = DBConnection.getConnection();
//
//            System.out.println("con.getTransactionIsolation()="+con.getTransactionIsolation());
//
//
//        } catch (SQLException | ClassNotFoundException e) {
//            e.printStackTrace();
//        } finally {
//
//            try {
//                if (con != null)
//                    con.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    @Override
//    public List<Category> findHostCategoriesByHostId(Host host) {
//        return categoryDao.findCategoryByHostId(host);
//    }
}
