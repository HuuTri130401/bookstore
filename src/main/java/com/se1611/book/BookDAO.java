/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.se1611.book;

import com.se1611.utils.DBHelper;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tuan vu
 */
public class BookDAO {

    public List<BookDTO> getInformationBook(int first, int last) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<BookDTO> listBook = null;
        try {
            con = DBHelper.getConnection();
            if (con != null) {
                String sql = "select book_Id,name_Book,author_Book,year_Of_Public,category,price_Book,quantity_Book,image\n"
                        + ",status_Book,description_Book\n"
                        + "from (\n"
                        + "	select *, ROW_NUMBER()over(Order by [book_Id]) as Rownum\n"
                        + "	from Book\n"
                        + ")as BookData\n"
                        + "where BookData.Rownum between ? and ?";
                stm = con.prepareStatement(sql);
                stm.setInt(1, first);
                stm.setInt(2, last);
                rs = stm.executeQuery();
                listBook = new ArrayList<>();
                while (rs.next()) {
                    BookDTO list = new BookDTO();
                    list.setBook_Id(rs.getInt("book_Id"));
                    list.setName(rs.getString("name_Book"));
                    list.setAuthor(rs.getString("author_Book"));
                    list.setYear_Of_Public(rs.getInt("year_Of_Public"));
                    list.setCategory(rs.getInt("category"));
                    list.setPrice_Book(rs.getFloat("price_Book"));
                    list.setQuantity_Book(rs.getInt("quantity_Book"));
                    list.setImage_Book(rs.getString("image"));
                    list.setStatus(rs.getBoolean("status_Book"));
                    list.setDescriptionBook(rs.getString("description_Book"));
                    listBook.add(list);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return listBook;
    }

    public List<BookDTO> getCategoryBook(int categoryId,int firstPage,int lastPage) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<BookDTO> listCategoryBook = null;
        try {
            con = DBHelper.getConnection();
            if (con != null) {
                String sql = "select book_Id,name_Book,author_Book,year_Of_Public,category,price_Book,quantity_Book,image\n" +
                        "                        ,status_Book, category_Name as nameCate,description_Book\n" +
                        "                        from (\n" +
                        "                        \tselect *, ROW_NUMBER()over(Order by [book_Id]) as Rownum\n" +
                        "                        \tfrom Book b inner join Category c on b.category=c.category_Id where b.category=?\n" +
                        "                        )as BookData \n" +
                        "                        where BookData.Rownum between ? and ?";
                stm = con.prepareStatement(sql);
                stm.setInt(1, categoryId);
                stm.setInt(2,firstPage);
                stm.setInt(3,lastPage);
                rs = stm.executeQuery();
                listCategoryBook = new ArrayList<>();
                while (rs.next()) {
                   BookDTO list=new BookDTO();
                    list.setBook_Id(rs.getInt("book_Id"));
                    list.setName(rs.getString("name_Book"));
                    list.setAuthor(rs.getString("author_Book"));
                    list.setYear_Of_Public(rs.getInt("year_Of_Public"));
                    list.setCategory(rs.getInt("category"));
                    list.setPrice_Book(rs.getFloat("price_Book"));
                    list.setQuantity_Book(rs.getInt("quantity_Book"));
                    list.setImage_Book(rs.getString("image"));
                    list.setStatus(rs.getBoolean("status_Book"));
                    list.setCategoryName(rs.getString("nameCate"));
                    list.setDescriptionBook(rs.getString("description_Book"));
                    listCategoryBook.add(list);
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return listCategoryBook;
    }

    public boolean createBook(String nameBook, String author, int yearOfPublic, int category, float price,
                              int quantity, String image, String description) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = true;
        int count = 0;
        boolean status = true;
        try {
            con = DBHelper.getConnection();
            if (con != null) {
                String sql = "insert Book ([name_Book],[author_Book],[year_Of_Public],[category],[price_Book],[quantity_Book],\n" +
                        "[image],[description_Book],[status_Book])\n" +
                        "values(?,?,?,?,?,?,?,?,?)";
                stm = con.prepareStatement(sql);
                stm.setString(1, nameBook);
                stm.setString(2, author);
                stm.setInt(3, yearOfPublic);
                stm.setInt(4, category);
                stm.setFloat(5, price);
                stm.setInt(6, quantity);
                stm.setString(7, image);
                stm.setString(8, description);
                stm.setBoolean(9, status);
                count = stm.executeUpdate();
                if (count == 0) {
                    result = false;
                } else {
                    result = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = false;
        } finally {
            stm.close();
            if (con != null) {
                con.close();
            }
        }
        return result;
    }

}
