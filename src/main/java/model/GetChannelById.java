package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GetChannelById {
    static int channel_id;
    static String name;
    static String type;
    static String authType;

    public static void setChannel_id(int channel_id) {
        GetChannelById.channel_id = channel_id;
    }

    public static void setName(String name) {
        GetChannelById.name = name;
    }

    public static void setType(String type) {
        GetChannelById.type = type;
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

    public GetChannelById() {
        this.channel_id = channel_id;
        this.name = name;
        this.type = type;
        this.authType = authType;
    }

    public static String getAuthType() {
        return authType;
    }

    public static void setAuthType(String authType) {
        GetChannelById.authType = authType;
    }

    public static Channel GetChannel(int channel_id) {
        String id = new String();
        id = Integer.toString(channel_id);

        Statement s = null;
        Connection con = new ConnectionFactory().getConnection();
        try {
            s = (Statement) con.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        ResultSet r = null;
        try {
            r = s.executeQuery("SELECT * FROM defaultChannels WHERE channel_id=" + id);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            while (r.next()) {
                setChannel_id(r.getInt("channel_id"));
                setName(r.getString("name"));
                setType(r.getString("type"));
                setAuthType(r.getString("auth"));
            }

            r.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        Channel channel = new Channel(channel_id, name, type, authType);

        return channel;
    }

    public static void main(String[] args) {
    }
}
