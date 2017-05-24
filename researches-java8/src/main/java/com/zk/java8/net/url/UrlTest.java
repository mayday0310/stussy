package com.zk.java8.net.url;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by zk_chs on 3/26/17.
 */
public class UrlTest {

    /** new URL()的时候还没有创建连接;
     *  openConnection()的时候也没有创建连接;
     *  connect()的时候知创建了连接,并没有发包;
     *  当invoke special method时,才发送的packets
     *  也可以不明确调用connect(),直接invoke special method,效果是一样的 */
    public static void main(String[] args) throws IOException {
        URL url = new URL("http://localhost:8080/getUsernames");
        URLConnection connection = url.openConnection();
//        connection.connect();
        connection.getContentLength();

        /** 此处同理,如果只是.get(),那么不会创建连接
         *  当invoke special method of Class HttpRequest时,便会创建连接了*/
        HttpRequest.get("http://localhost:8080/getUsernames").headers();
    }

}
