package model;

public class Channel {
    private final int id;
    private final String name;
    private final String type;
    private final String authType;

    public Channel(int id, String name, String type, String authType) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.authType = authType;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getType() { return type; }
    public String getAuthType() { return authType; }
}