package ge.tbc.testautomation.enums;

public enum SwoopEndpoint {
    BASE_URL("https://api.swoop.ge"),
    SEARCH_OFFERS("/api/search");

    private final String url;

    SwoopEndpoint(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return url;
    }
}