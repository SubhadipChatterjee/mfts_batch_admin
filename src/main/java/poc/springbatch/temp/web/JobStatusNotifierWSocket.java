/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package poc.springbatch.temp.web;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.enterprise.event.Observes;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import poc.springbatch.event.messages.TimeSnap;
import poc.springbatch.events.TimerEvent;

/**
 *
 * @author subhadip.chatterjee@tcs.com
 */
@ServerEndpoint("/status")
public class JobStatusNotifierWSocket {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private static final Set<Session> peers =
            Collections.synchronizedSet(new HashSet<Session>());

    @OnOpen
    public void onOpen(final Session peer) {
        try {
            peer.getBasicRemote().sendText("[Socket] Session id:"+peer.getId());
            peers.add(peer);
            if (logger.isInfoEnabled()) {
                logger.info("New Client Session id: " + peer.getId());
            }
        } catch (IOException ex) {
            logger.error(ex.getMessage());
        }
    }

    @OnMessage
    public void onMessage(String message) {
        if (logger.isInfoEnabled()) {
            logger.info("Client sends the message:" + message);
        }
    }

    public void onTimeEvent(@Observes @TimerEvent TimeSnap timeSnap) {
        if (logger.isInfoEnabled()) {
            logger.info("WebSocket: Timer event received...");
        }
        try {
            for (Session each : peers) {
                if (each.isOpen()) {
                    each.getBasicRemote().sendText("[Job # " + timeSnap.getJobId()+"] Status: "+ timeSnap.getJobStatus());
                }
            }
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    @OnClose
    public void onClose(final Session peer) {
        try {
            peer.getBasicRemote().sendText("[Socket] Session closed");
            if (logger.isInfoEnabled()) {
                logger.info("Client Session closed. ID: " + peer.getId());
            }
            peers.remove(peer);
            peer.close();
        } catch (IOException ex) {
            logger.error(ex.getMessage());
        }
    }
}
