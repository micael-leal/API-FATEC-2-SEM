package model;

public class RegisteredChannelToken {

    private final int registeredChannelTokenId;
    private final int user_id;
    private final int channel_id;
    private final String token;

    public RegisteredChannelToken(int registeredChannelTokenId, int user_id, int channel_id, String token) {
        this.registeredChannelTokenId = registeredChannelTokenId;
        this.user_id = user_id;
        this.channel_id = channel_id;
        this.token = token;
    }

    public int getRegisteredChannelTokenId() {
        return registeredChannelTokenId;
    }

    public int getUser_id() {
        return user_id;
    }

    public int getChannel_id() {
        return channel_id;
    }

    public String getToken() {
        return token;
    }
}
