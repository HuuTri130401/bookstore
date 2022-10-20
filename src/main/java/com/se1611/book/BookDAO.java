/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.se1611.book;

import com.se1611.utils.DBHelper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author tuan vu
 */
public class BookDAO {

    public List<BookDTO> getInformationBook(int first, int last) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        BookDAO bd = null;
        ResultSet rs = null;
        List<BookDTO> listBook = null;
        try {
            con = DBHelper.getConnection();
            if (con != null) {
                String sql = "select *\n"
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

}
