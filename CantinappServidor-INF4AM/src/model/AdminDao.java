package model;

import factory.Conector;
import java.sql.*;
import domainModel.Admin;

//Data Acess Object (DAO)
public class AdminDao {

    private Connection con;

    public AdminDao() {
        con = Conector.getConnection();
    }

    public Admin loginAdmin(Admin admin) {
        PreparedStatement stmt = null;
        Admin selectedAdmin = null;

        try {
            String sql = "select * from admin "
                    + "where username = ? and password = ?";

            //criar stmt e trocar param
            stmt = con.prepareStatement(sql);
            stmt.setString(1, admin.getUsername());
            stmt.setString(2, admin.getPassword());

            //exe script
            ResultSet res = stmt.executeQuery();

            selectedAdmin = new Admin(res.getInt("id"),
                    res.getString("username"),
                    res.getString("name"),
                    res.getString("password"));

            //fechar conexoes e stmts
            res.close();
            stmt.close();
            con.close();

            return selectedAdmin;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
