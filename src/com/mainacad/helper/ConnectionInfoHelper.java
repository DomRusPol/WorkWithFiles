package com.mainacad.helper;

import com.mainacad.model.ConnectionInfo;

import java.util.Date;
import java.util.Random;

public class ConnectionInfoHelper {

    public static ConnectionInfo getRandomConnectionInfo(){
        ConnectionInfo connectionInfo = new ConnectionInfo();
        connectionInfo.setConnectionTime(new Date().getTime());
        connectionInfo.setSessionId(getRandomNumber(10000, 99999));
        connectionInfo.setIp(
            getRandomNumber(100, 999) + "." +
            getRandomNumber(100, 999) + "." +
            getRandomNumber(100, 999) + "." +
            getRandomNumber(100, 999)
        );

        return connectionInfo;
   }
//? 10-30
    private static int getRandomNumber(int from, int to) {
        return from + new Random().nextInt(to - from);
    }
}
