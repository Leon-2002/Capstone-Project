/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.notification;

/**
 *
 * @author loena
 */
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.URL;

public class NetworkUtil {
    public static boolean isInternetAvailable() {
        try (Socket socket = new Socket()) {
            SocketAddress socketAddress = new InetSocketAddress("www.google.com", 80);
            socket.connect(socketAddress, 2000); // 2 seconds timeout
            return true;
        } catch (IOException e) {
            return false;
        }
    }
    
    
       public static boolean isInternetAvailable1() {
    try {
        URL url = new URL("https://www.google.com"); // You can use any URL here
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.connect();
        return conn.getResponseCode() == 200;
    } catch (IOException e) {
        return false;
    }
}
}

