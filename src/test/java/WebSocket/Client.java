package WebSocket;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.util.ArrayList;
import java.util.Iterator;

public class Client extends WebSocketClient {
    public ArrayList<String> responseMessage = new ArrayList<>();
    public String httpStatusMessage;
    public short httpStatus;

    public Client(URI serverURI ) {
        super( serverURI );
    }

    @Override
    public void onOpen(ServerHandshake serverHandshake) {
        httpStatusMessage = serverHandshake.getHttpStatusMessage();
        httpStatus = serverHandshake.getHttpStatus();
        System.out.println("onOpen : Connection is opened");
    }

    @Override
    public void onMessage(String s) {
        responseMessage.add(s);
    }

    @Override
    public void onClose( int code, String reason, boolean remote ) {
        System.out.println("onClosed : Connection closed by " + ( remote ? "remote peer" : "us " ) + code + "|" + reason );
    }

    @Override
    public void onError(Exception e) {
        e.printStackTrace();
    }
}
