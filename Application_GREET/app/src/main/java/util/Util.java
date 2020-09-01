package util;

public interface Util {

    static String IP = "192.168.0.4";
    static String TYPE_SUBMIT = "type_submit";
    static String TYPE_LOAD = "type_load";
    static String TYPE_DELETE = "type_delete";
    static String SERVER_IP = "http://" + IP + ":9090/greet/";
    static String SERVER_URL = SERVER_IP + "nametag";
    static String SERVER_IMG_PATH = "http://" + IP + ":9090/greet/resources/upload/";
}
