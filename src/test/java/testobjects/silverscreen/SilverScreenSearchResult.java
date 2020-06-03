package testobjects.silverscreen;

public class SilverScreenSearchResult {

    private final String searchName;
    private final String searchDescription;

    public SilverScreenSearchResult(String searchName, String searchDescription) {
        this.searchName = searchName;
        this.searchDescription = searchDescription;
    }

    public String getSearchName(){
        return searchName;
    }

    public String getSearchDescription(){
        return searchDescription;
    }
}
