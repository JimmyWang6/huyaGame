package hyext.ebs.examples.netty;

import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.ChannelGroupFuture;
import io.netty.channel.group.ChannelMatcher;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * @description:
 * @author: 周世焕
 * @time: 2020-04-02 21:21
 */
public class ChannelGroups {

    private static final ChannelGroup CHANNEL_GROUP = new DefaultChannelGroup("ChannelGroups", GlobalEventExecutor.INSTANCE);

    /**
     * 添加Channel
     * @param channel
     */
    public static void add(Channel channel) {
        CHANNEL_GROUP.add(channel);
    }

    /**
     * 无条件广播
     * @param msg
     * @return
     */
    public static ChannelGroupFuture broadcast(Object msg) {
        return CHANNEL_GROUP.writeAndFlush(msg);
    }

    /**
     * 有条件广播
     * @param msg 消息对象
     * @param matcher 自定义匹配规则
     * @return
     */
    public static ChannelGroupFuture broadcast(Object msg, ChannelMatcher matcher) {
        return CHANNEL_GROUP.writeAndFlush(msg, matcher);
    }

    /**
     *
     * @return
     */
    public static ChannelGroup flush() {
        return CHANNEL_GROUP.flush();
    }

    /**
     * 丢弃Channel
     * @param channel
     * @return
     */
    public static boolean discard(Channel channel) {
        return CHANNEL_GROUP.remove(channel);
    }

    /**
     * 全面终止连接
     * @return
     */
    public static ChannelGroupFuture disconnect() {
        return CHANNEL_GROUP.disconnect();
    }

    /**
     * 终止符合条件的连接
     * @param matcher
     * @return
     */
    public static ChannelGroupFuture disconnect(ChannelMatcher matcher) {
        return CHANNEL_GROUP.disconnect(matcher);
    }

    /**
     * 是否包含有某个Channel
     * @param channel
     * @return
     */
    public static boolean contains(Channel channel) {
        return CHANNEL_GROUP.contains(channel);
    }

    /**
     * Channel数量
     * @return
     */
    public static int size() {
        return CHANNEL_GROUP.size();
    }
}
