/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

/**
 *
 * @author HELLO
 */
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Human;
import model.HumanType;
public class DBContext {
    Connection connection;
    public DBContext()
    {
        try {
            String user = "sa";
            String pass = "abc123";
            String url = "jdbc:sqlserver://127.0.0.1:1433;databaseName=Human";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(url, user, pass);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public ArrayList<Human> getHumans() 
    {
        ArrayList<Human> humans = new ArrayList<>();
        try {
            String sql = "SELECT h.humanid,h.humanname,h.humandob,h.humangender,ht.typeid,ht.typename "
                    + " FROM Human h INNER JOIN HumanType ht ON h.typeid = ht.typeid";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while(rs.next())
            {
                Human h = new Human();
                h.setID( rs.getInt("humanid")  );
                h.setName(rs.getString("humanname")  );
                h.setDob(rs.getDate("humandob")  );
                h.setGender(rs.getBoolean("humangender")  );
                
                HumanType ht = new HumanType();
                ht.setID(rs.getInt("typeid")  );
                ht.setName(rs.getString("typename")  );
                h.setType(rs.getInt("typeid"));
                humans.add(h);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return humans;
    }
    public ArrayList<HumanType> getTypes()
    {
        ArrayList<HumanType> types = new ArrayList<>();
        try {
            String sql = "SELECT [typeid]\n" +
                    "     ,[typename]\n" +
                    "   FROM [HumanType] ";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while(rs.next())
            {
                HumanType ht = new HumanType();
                ht.setID(rs.getInt("typeid")  );
                ht.setName(rs.getString("typename")  );
                types.add(ht);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return types;
    }
}
