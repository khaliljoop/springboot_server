package sn.ssi.ersen.static_and_constants;
import java.util.UUID;

public class StaticObjects {
    public static String getCredential(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }
}