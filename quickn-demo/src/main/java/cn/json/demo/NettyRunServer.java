package cn.json.demo;

import cn.json.quicknCore.netty.XHttpServer;

/**
 * Created by json2 on 2017/3/5.
 */
public class NettyRunServer {

    public static void main(String[] args) {

        final XHttpServer server = new XHttpServer(8080);
        server.init().start();
        Runtime.getRuntime().addShutdownHook(new Thread(){
            @Override
            public void run() {
                server.stop();
            }
        });

    }
}
