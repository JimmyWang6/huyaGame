package hyext.ebs.examples.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class MqttServer {
    public static boolean SEVER_STATUS = false;
    private static EventLoopGroup bossGroup;
    private static EventLoopGroup workerGroup;

    public static void run() throws Exception {
        bossGroup = new NioEventLoopGroup();
        workerGroup = new NioEventLoopGroup();
        ServerBootstrap b = new ServerBootstrap();
        b.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 1024)
                .childHandler(new MqttServerInitializer());
        ChannelFuture f = b.bind(9888).sync();
        f.addListener((ChannelFutureListener) channelFuture -> {
            SEVER_STATUS = true;
        });
        f.channel().closeFuture().sync();
    }

    public static void startupServer() {
        new Thread(() -> {
            try {
                run();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    

    public static void shutdownServer() {
        if ((bossGroup != null) && (!bossGroup.isShutdown())) {
            bossGroup.shutdownGracefully();
        }
        if ((workerGroup != null) && (!workerGroup.isShutdown())) {
            workerGroup.shutdownGracefully();
        }
    }

    public static void main(String[] argv) throws Exception {
        MqttServer.run();
    }
}
