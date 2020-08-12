package hyext.ebs.examples.netty;

import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;

import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.HashSet;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @description:
 * @author: 周世焕
 * @time: 2020-08-12 21:11
 */
public class WebSocketServer extends org.java_websocket.server.WebSocketServer {

    private ConcurrentHashMap<WebSocket,Long> listeners = new ConcurrentHashMap();

    public WebSocketServer(InetSocketAddress address) {
        super(address);
        System.out.println("地址" + address);
    }

    public WebSocketServer(int port) throws UnknownHostException {
        super(new InetSocketAddress(port));
        System.out.println("端口" + port);
    }

    public void sendMessageToAll(String msg){
        for(Map.Entry<WebSocket,Long> listener : listeners.entrySet()){
            listener.getKey().send(msg);
        }
    }

    @Override
    public void onOpen(WebSocket webSocket, ClientHandshake clientHandshake) {
        listeners.put(webSocket,System.currentTimeMillis());
        System.out.println("新用户加入："+webSocket.getRemoteSocketAddress().getHostString());
    }

    @Override
    public void onClose(WebSocket webSocket, int i, String s, boolean b) {
        listeners.remove(webSocket);
        System.out.println("用户离开："+webSocket.getRemoteSocketAddress().getHostString());
    }

    @Override
    public void onMessage(WebSocket webSocket, String s) {
        System.out.println("msg receive: " + s);
    }

    @Override
    public void onError(WebSocket webSocket, Exception e) {

    }

    @Override
    public void onStart() {

    }
}
