package red.team.map.KhiGasHuyDiet;

import red.team.clan.Clan;
import red.team.map.TrapMap;
import red.team.map.Zone;
import red.team.mob.Mob;
import red.team.player.Player;
import red.services.ItemTimeService;
import red.services.MapService;
import red.services.Service;
import red.services.func.ChangeMapService;
import red.utils.Util;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author BTH
 */
public class KhiGasHuyDiet { 

    public static final long POWER_CAN_GO_TO_KGHD = 2000000000;

    public static final List<KhiGasHuyDiet> KHI_GA_HUY_DIETS;
    public static final int MAX_AVAILABLE = 9;
    public static final int TIME_KHI_GA_HUY_DIET = 1800000;
    public static final int TIME_OUT = 30000;

    private Player player;

    static {
        KHI_GA_HUY_DIETS = new ArrayList<>();
        for (int i = 0; i < MAX_AVAILABLE; i++) {
            KHI_GA_HUY_DIETS.add(new KhiGasHuyDiet(i));
        }
    }

    public int id;
    public int level;
    public final List<Zone> zones;

    public Clan clan;
    public boolean isOpened;
    private long lastTimeOpen;

    public KhiGasHuyDiet(int id) {
        this.id = id;
        this.zones = new ArrayList<>();
    }

    public void update(Player player) {
        if (this.isOpened) {
            if (Util.canDoWithTime(lastTimeOpen, TIME_KHI_GA_HUY_DIET)) {
                this.finish();
                player.clan.haveGoneKhiGasHuyDiet = true;
            }
        }
    }

    public void openKhiGaHuyDiet(Player plOpen, Clan clan, int level) {
        this.level = level;
        this.lastTimeOpen = System.currentTimeMillis();
        this.isOpened = true;
        this.clan = clan;
        this.clan.timeOpenKhiGaHuyDiet = this.lastTimeOpen;
        this.clan.playerOpenKhiGaHuyDiet = plOpen;
        this.clan.KhiGaHuyDiet = this;

        resetKhiGa();
        ChangeMapService.gI().goToKGHD(plOpen);
        sendTextKhiGaHuyDiet();
    }

    private void resetKhiGa() {
        for (Zone zone : zones) {
            for (Mob m : zone.mobs) {
                Mob.initMopbKhiGasHuyDiet(m, this.level);
                Mob.hoiSinhMob(m);
            }
        }
    }

    //kết thúc bản đồ kho báu
    public void finish() {
        List<Player> plOutDT = new ArrayList();
        for (Zone zone : zones) {
            List<Player> players = zone.getPlayers();
            for (Player pl : players) {
                plOutDT.add(pl);
            }

        }
        for (Player pl : plOutDT) {
            ChangeMapService.gI().changeMapBySpaceShip(pl, 0, -1, 384);
        }
        this.clan.KhiGaHuyDiet = null;
        this.isOpened = false;
    }


    public Zone getMapById(int mapId) {
        for (Zone zone : zones) {
            if (zone.map.mapId == mapId) {
                return zone;
            }
        }
        return null;
    }

   public static void addZone(int idKhiGa, Zone zone) {
        KHI_GA_HUY_DIETS.get(idKhiGa).zones.add(zone);
    }

    private void sendTextKhiGaHuyDiet() {
        for (Player pl : this.clan.membersInGame) {
            ItemTimeService.gI().sendTextKhiGaHuyDiet(pl);
        }
    }
}

