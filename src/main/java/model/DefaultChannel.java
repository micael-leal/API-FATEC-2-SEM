package model;

public class DefaultChannel {
    String name;
    String type;

    String auth;

    /*public DefaultChannel(){
    }*/

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getType(){
        return this.type;
    }

    public void setType(String type){
        this.type = type;
    }

    public String getAuth(){
        return this.auth;
    }

    public void setAuth(String auth){
        this.auth = auth;
    }
}
