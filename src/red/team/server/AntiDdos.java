package red.team.server;

import com.girlkun.network.example.MessageSendCollect;
import com.girlkun.network.server.GirlkunServer;
import com.girlkun.network.server.IServerClose;
import com.girlkun.network.server.ISessionAcceptHandler;
import com.girlkun.network.session.ISession;
import java.util.List;
import red.server.io.MyKeyHandler;
import red.server.io.MySession;
import red.team.player.Player;
import static red.team.server.ServerManager.CLIENTS;
import static red.team.server.ServerManager.PORT;

/**
 *
 * @author vantanz
 */
public class AntiDdos {
    
private static AntiDdos instance;
    private static Object i;
    private List<AntiDdos> AntiDdos;

private AntiDdos() {
        new Thread((Runnable) this).start();
    }

    public List<AntiDdos> getAntiDdos() {
        return this.AntiDdos;
    }

    public static AntiDdos gI() {
        if (i == null) {
            i = new Client();
        }
        return (AntiDdos) i;
    }
    private void AntiDdos() throws Exception {
        GirlkunServer.gI().init().setAcceptHandler(new ISessionAcceptHandler() {
            @Override
            public void sessionInit(ISession is) {
                if (!canConnectWithIp(is.getIP())) {
                    is.disconnect();
                    return;
                }

                is = is.setMessageHandler(Controller.getInstance())
                        .setSendCollect(new MessageSendCollect())
                        .setKeyHandler(new MyKeyHandler())
                        .startCollect();
            }

            @Override
            public void sessionDisconnect(ISession session) {
                Client.gI().kickSession((MySession) session);
            }

            private boolean canConnectWithIp(String ipAddress) {
        Object o = CLIENTS.get(ipAddress);
        if (o == null) {
            CLIENTS.put(ipAddress, 1);
            return true;
        } else {
            int n = Integer.parseInt(String.valueOf(o));
            if (n < Manager.MAX_PER_IP) {
                n++;
                CLIENTS.put(ipAddress, n);
                return true;
            } else {
                return false;
            }
        }
    }
        }).setTypeSessioClone(MySession.class)
                .setDoSomeThingWhenClose(new IServerClose() {
                    @Override
                    public void serverClose() {
                        System.out.println("ddos con mẹ mày");
                        System.exit(0);
                    }
                })
                .start(PORT);

    }
}
// antiddos by vantanz coppy con đĩ mẹ mày
//https://www.facebook.com/em.muoi.7146?mibextid=ZbWKwL