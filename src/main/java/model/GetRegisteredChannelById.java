package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GetRegisteredChannelById {

    static int registeredChannelLogin_id;
    static int registeredChannelToken_id;
    static int user_id;
    static int channel_id;
    static String login;
    static String password;
    static String token;

    public static RegisteredChannelLogin GetChannelLogin(int registeredChannelLogin_id) {
        String id = new String();
        id = Integer.toString(registeredChannelLogin_id);

        Statement s = null;
        Connection con = new ConnectionFactory().getConnection();

        try {
            s = (Statement) con.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        ResultSet r = null;
        try {
            r = s.executeQuery("SELECT * FROM registeredChannelLogin WHERE registeredChannelLogin_id=" + id);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            while (r.next()) {
                setRegisteredChannelLogin_id(r.getInt("registeredChannelLogin_id"));
                setUser_id(r.getInt("user_id"));
                setChannel_id(r.getInt("channel_id"));
                setLogin(r.getString("login"));
                setPassword(r.getString("password"));
            }

            r.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        RegisteredChannelLogin registeredChannelLogin = new RegisteredChannelLogin(registeredChannelLogin_id, user_id, channel_id, login, password);

        return registeredChannelLogin;
    }

    public static RegisteredChannelToken GetChannelToken(int registeredChannelToken_id) {
        String id = new String();
        id = Integer.toString(registeredChannelToken_id);

        Statement s = null;
        Connection con = new ConnectionFactory().getConnection();

        try {
            s = (Statement) con.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        ResultSet r = null;
        try {
            r = s.executeQuery("SELECT * FROM registeredChannelToken WHERE registeredChannelToken_id=" + id);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            while (r.next()) {
                setRegisteredChannelToken_id(r.getInt("registeredChannelToken_id"));
                setUser_id(r.getInt("user_id"));
                setChannel_id(r.getInt("channel_id"));
                setToken(r.getString("token"));
            }

            r.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        RegisteredChannelToken registeredChannelToken = new RegisteredChannelToken(registeredChannelToken_id, user_id, channel_id, token);

        return registeredChannelToken;
    }


    public static int getRegisteredChannelLogin_id() {
        return registeredChannelLogin_id;
    }

    public static void setRegisteredChannelLogin_id(int registeredChannelLogin_id) {
        GetRegisteredChannelById.registeredChannelLogin_id = registeredChannelLogin_id;
    }

    public static int getUser_id() {
        return user_id;
    }

    public static void setUser_id(int user_id) {
        GetRegisteredChannelById.user_id = user_id;
    }

    public static int getChannel_id() {
        return channel_id;
    }

    public static void setChannel_id(int channel_id) {
        GetRegisteredChannelById.channel_id = channel_id;
    }

    public static String getLogin() {
        return login;
    }

    public static void setLogin(String login) {
        GetRegisteredChannelById.login = login;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        GetRegisteredChannelById.password = password;
    }

    public static String getToken() {
        return token;
    }

    public static void setToken(String token) {
        GetRegisteredChannelById.token = token;
    }

    public static int getRegisteredChannelToken_id() {
        return registeredChannelToken_id;
    }

    public static void setRegisteredChannelToken_id(int registeredChannelToken_id) {
        GetRegisteredChannelById.registeredChannelToken_id = registeredChannelToken_id;
    }

    public static void main(String[] args) {

    }
}
