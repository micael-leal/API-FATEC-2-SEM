package model;

public class RegisteredChannelLogin {

    private final int registeredChannelLoginId;
    private final int user_id;
    private final int channel_id;
    private final String login;
    private final String password;

    public RegisteredChannelLogin(int registeredChannelLoginId, int user_id, int channel_id, String login, String password) {
        this.registeredChannelLoginId = registeredChannelLoginId;
        this.user_id = user_id;
        this.channel_id = channel_id;
        this.login = login;
        this.password = password;
    }

    public int getRegisteredChannelLoginId() {
        return registeredChannelLoginId;
    }

    public int getUser_id() {
        return user_id;
    }

    public int getChannel_id() {
        return channel_id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
