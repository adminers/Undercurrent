package com.qiaweidata.undercurrent;

import com.qiaweidata.undercurrent.server.NettyServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NettyServerApplication implements CommandLineRunner {

    @Autowired
    private NettyServer nettyServer;


    @Override
    public void run(String... args) throws Exception {
        nettyServer.start();
    }

    public static void main(String[] args) {
        SpringApplication.run(NettyServerApplication.class,args);
    }
}
