package testobjects.wssearrchobjects;

import java.util.List;

public class ResponseBodyJsonObject {

    private final String code;
    private final List<UserJsonObject> data;

    public ResponseBodyJsonObject(String code, List<UserJsonObject> data) {
        this.code = code;
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public List<UserJsonObject> getData(){
        return data;
    }

    @Override
    public String toString(){
        return this.code + " " + this.data.stream();
    }
}
