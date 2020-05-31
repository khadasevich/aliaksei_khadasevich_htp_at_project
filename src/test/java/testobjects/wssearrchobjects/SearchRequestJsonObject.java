package testobjects.wssearrchobjects;

public class SearchRequestJsonObject {

    private final String user;
    private final boolean strict;

    public SearchRequestJsonObject(String user, boolean strict){
        this.user = user;
        this.strict = strict;
    }

    public String getUser(){
        return user;
    }

    public boolean getStrict() {
        return strict;
    }
}
