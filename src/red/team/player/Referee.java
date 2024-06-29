package red.team.player;

// đây
import java.util.ArrayList;
import java.util.List;

import red.consts.ConstMap;
import red.team.map.Map;
import red.team.map.Zone;
import red.team.shop.ShopServiceNew;
import red.team.server.Manager;
import red.services.MapService;
import red.services.PlayerService;
import red.services.Service;
import red.utils.Util;

/**
 * @author BTH sieu cap vippr0
 */
public class Referee extends Player {

    private long lastTimeChat;
    private Player playerTarget;

    private long lastTimeTargetPlayer;
    private long timeTargetPlayer = 5000;
    private long lastZoneSwitchTime;
    private long zoneSwitchInterval;
    private List<Zone> availableZones;

    public void initReferee() {
        init();
    }

    @Override
    public short getHead() {
        return 114;
    }

    @Override
    public short getBody() {
        return 115;
    }

    @Override
    public short getLeg() {
        return 116;
    }

    public void joinMap(Zone z, Player player) {
        MapService.gI().goToMap(player, z);
        z.load_Me_To_Another(player);
    }

    @Override
    public void update() {
        if (Util.canDoWithTime(lastTimeChat, 5000)) {
            Service.getInstance().chat(this, "Đại Hội Võ Thuật lần thứ 23 đã chính thức khai mạc");
            Service.getInstance().chat(this, "Còn chờ gì nữa mà không đăng kí tham gia để nhận nhiều phẩn quà hấp dẫn");
            Service.getInstance().chat(this, "serizawa.store");
            lastTimeChat = System.currentTimeMillis();
            if (this.nPoint.hp<=1000){
                    Service.gI().hsChar(this, this.nPoint.hpMax, this.nPoint.mpMax);}
//            Referee pl = new Referee();
//            pl.nPoint.hp=2000000000;
        }
    }

    private void init() {
        int id = -1000000;
        for (Map m : Manager.MAPS) {
            if (m.mapId == 52) {
                for (Zone z : m.zones) {
                    Referee pl = new Referee();
                    pl.name = "Anh tài";
                    pl.gender = 0;
                    pl.id = id++;
                    pl.nPoint.hpMax = (int) 2000;
                    pl.nPoint.hpg = (int) 2000;
                    pl.nPoint.hp = (int) 2000;
//                    pl.nPoint.setFullHpMp();
                    pl.nPoint.setFullHpMp();
                    pl.location.x = 387;
                    pl.location.y = 336;
                    
                    pl.cFlag=0;
                    joinMap(z, pl);
                    z.setReferee(pl);
                    
                }
            } else if (m.mapId == 129) {
                for (Zone z : m.zones) {
                    Referee pl = new Referee();
                    pl.name = "Trọng Tài";
                    pl.gender = 0;
                    pl.id = id++;
                    pl.nPoint.hpMax = (int) 500;
                    pl.nPoint.hpg = (int) 500;
                    pl.nPoint.hp = (int) 500;
//                    pl.cFlag=8;
                    pl.nPoint.setFullHpMp();
                    pl.location.x = 385;
                    pl.location.y = 360;
                    joinMap(z, pl);
                    z.setReferee(pl);
                    
                 }         
            }
                }
            }
        }
    
//}

