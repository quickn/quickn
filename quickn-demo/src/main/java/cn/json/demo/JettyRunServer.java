package cn.json.demo;

import cn.json.quicknCore.common.Main;

/**
 * Created by json2 on 2017/3/5.
 */
public class JettyRunServer {

    public static void main(String[] args) throws Exception{
        Integer port = 80;
        if(args.length!=0){
            port = Integer.valueOf(args[0]);
        }
        Main.runserver("/", port);
    }
}
