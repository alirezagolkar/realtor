package com.artinrayan.foodi.core.impl;

import com.artinrayan.foodi.core.CategoryDao;
import com.artinrayan.foodi.model.Category;
import com.artinrayan.foodi.model.Host;
import exceptions.CategoryDaoException;
import until.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by asus on 1/2/2018.
 */
public class CategoryJDBCDaoImp /*implements CategoryDao*/{

//    private static final String INSERT_CATEGORY_QUERY = "";
//    private static final String UPDATE_CATEGORY_QUERY = "";
//    private static final String DELETE_CATEGORY_QUERY = "";
//
//    @Override
//    public void save(Category category) throws CategoryDaoException {
//        Connection con = null;
//        try {
//            con = DBConnection.getConnection();
//            System.out.println("con.getTransactionIsolation()="+con.getTransactionIsolation());
//
//            PreparedStatement stmt =  con.prepareStatement(INSERT_CATEGORY_QUERY);
//            stmt.setInt(1, category.getParentId());
//            stmt.setString(2, category.getCategoryTitle());
//            stmt.setString(3, category.getCategoryDescription());
//            stmt.setTimestamp(4, category.getCreationDate() == null ? null :
//                    new java.sql.Timestamp(
//                            category.getCreationDate().getTime()));
//            stmt.executeUpdate();
//            stmt.close();
//
//            //now commit transaction
//            con.commit();
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//            try {
//                con.rollback();
//                System.out.println("JDBC Transaction rolled back successfully");
//            } catch (SQLException e1) {
//                System.out.println("SQLException in rollback"+e.getMessage());
//            }
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        } finally {
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
//    public void delete(long categoryId) throws CategoryDaoException {
//
//    }
//
//    @Override
//    public List<Category> findCategoryByHostId(Host host) {
//        return null;
//    }
//
//    private static java.sql.Timestamp getCurrentTimeStamp() {
//
//        java.util.Date today = new java.util.Date();
//        return new java.sql.Timestamp(today.getTime());
//
//    }
}
