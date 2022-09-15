package model;

public class RegisteredChannel {
    private final int id;
    private final int user_id;
    private final int channel_id;
    private final String channel_name;
    private final String token;
    private final String login;
    private final String password;


    public RegisteredChannel(int id, int user_id, int channel_id, String channel_name, String token, String login, String password) {
        this.id = id;
        this.user_id = user_id;
        this.channel_id = channel_id;
        this.token = token;
        this.channel_name = channel_name;
        this.login = login;
        this.password = password;
    }

    public int getId() { return id; }
    public int getUser_id() { return user_id; }
    public int getChannel_id() { return channel_id; }
    public String getChannel_name() { return channel_name; }
    public String getToken() { return token; }
    public String getLogin() { return login; }
    public String getPassword() { return password; }
}
