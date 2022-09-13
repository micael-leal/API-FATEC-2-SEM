package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class TestSelectDB {

    static int channel_id;
    static String name;
    static String type;

    public static void setChannel_id(int channel_id) {
        TestSelectDB.channel_id = channel_id;
    }

    public static void setName(String name) {
        TestSelectDB.name = name;
    }

    public static void setType(String type) {
        TestSelectDB.type = type;
    }

    public static int getChannel_id() {
        return channel_id;
    }

    public static String getName() {
        return name;
    }

    public static String getType() {
        return type;
    }

    public TestSelectDB() {
        this.channel_id = channel_id;
        this.name = name;
        this.type = type;
    }

    public static void main(String[] args) {

        Statement s = null;
        Connection con = new ConnectionFactory().getConnection();
        try {
            s = (Statement) con.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        ResultSet r = null;
        try {
            r = s.executeQuery("SELECT * FROM defaultchannel WHERE channel_id='2'");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            while (r.next()) {
                setChannel_id(r.getInt("channel_id"));
                setName(r.getString("name"));
                setType(r.getString("type"));
            }

            r.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
