package hyext.ebs.examples.netty;

import hyext.ebs.examples.utils.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @description:
 * @author: 周世焕
 * @time: 2020-08-11 18:14
 */
public class Task implements Runnable {

    RedisUtil redisUtil;
    Logger logger = LoggerFactory.getLogger(Task.class);

    public Task() throws IOException {
        redisUtil = RedisUtil.getRedisUtil();
    }

    @Override
    public void run() {
        logger.info("启动线程从Redis队列读指令");
        System.out.println("启动线程从Redis队列读指令");
        while (true){
            try {
                Thread.sleep(100);
                String command = redisUtil.lpop("huya:command");
                if(command != null){
                    System.out.println("广播指令:" + command);
                    ChannelGroups.broadcast(command + "\r\n");
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
