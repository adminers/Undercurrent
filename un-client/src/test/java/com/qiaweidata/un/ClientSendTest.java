package com.qiaweidata.un;

import com.qiaweidata.undercurrent.NettyClient;
import org.junit.Test;

/**
 * @Title: ClientSendTest
 * @Description: ClientSendTest
 * @Company: www.qiaweidata.com
 * @author: shenshilong
 * @date: 2023-02-16
 * @version: V1.0
 */
public class ClientSendTest {

    public static void main(String[] args) {
        NettyClient nettyClient = new NettyClient("127.0.0.1", 6666);
        nettyClient.start();
    }
}
