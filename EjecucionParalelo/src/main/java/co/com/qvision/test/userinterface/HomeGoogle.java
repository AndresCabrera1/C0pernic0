package co.com.qvision.test.userinterface;

public enum HomeGoogle {

    UrlGoogle("https://www.google.com/");

    private final String url;

    HomeGoogle(String url){
        this.url = url;
    }

    public String Url(){
        return url;
    }
}
