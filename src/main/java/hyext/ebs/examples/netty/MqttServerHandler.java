package hyext.ebs.examples.netty;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import hyext.ebs.examples.utils.RedisUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.*;

public class MqttServerHandler extends SimpleChannelInboundHandler<String> {

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("thread-call-runner-%d").build();
    private ExecutorService taskExe =
            new ThreadPoolExecutor(1,1,0,
                    TimeUnit.MILLISECONDS,new LinkedBlockingQueue<Runnable>(),namedThreadFactory);

    public MqttServerHandler(){
        try {
            WebSocketServer webSocketServer = new WebSocketServer(9999);
            webSocketServer.start();
            taskExe.execute(new Task(webSocketServer));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        ChannelGroups.add(channel);
        System.out.println(sdf.format(new java.util.Date()) + ":[客户端]" + channel.remoteAddress() + " 建立链接了----" + "当前链接数为:" + ChannelGroups.size());
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String baseMQTTMessage) throws Exception {
        Channel channel = ctx.channel();
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        System.out.println(sdf.format(new java.util.Date()) + ":[客户端]" + channel.remoteAddress() + " 离开了");
        ChannelGroups.discard(channel);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        Channel ch = ctx.channel();
        ch.close();
    }
}
