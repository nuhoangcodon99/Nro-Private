package red.team.map.MapVoDai;

import java.util.List;

import red.team.player.Player;
import red.services.MapService;
import red.services.Service;
import red.services.func.ChangeMapService;
import red.utils.TimeUtil;
import red.utils.Util;

public class MapVodai {


    public static final byte HOUR_OPEN_MAP_VODAI = 6;
    public static final byte MIN_OPEN_MAP_VODAI = 0;
    public static final byte SECOND_OPEN_MAP_VODAI = 0;


    public static final byte HOUR_CLOSE_MAP_VODAI = 23;
    public static final byte MIN_CLOSE_MAP_VODAI = 0;
    public static final byte SECOND_CLOSE_MAP_VODAI = 0;

    public static final int AVAILABLE = 1;

    private static MapVodai i;

    public static long TIME_OPEN_VODAI;
    public static long TIME_CLOSE_VODAI;

    private int day = -1;

    public static MapVodai gI() {
        if (i == null) {
            i = new MapVodai();
        }
        i.setTimeJoinMapVodai();
        return i;
    }

    public void setTimeJoinMapVodai() {
        if (i.day == -1 || i.day != TimeUtil.getCurrDay()) {
            i.day = TimeUtil.getCurrDay();
            try {
                TIME_OPEN_VODAI = TimeUtil.getTime(TimeUtil.getTimeNow("dd/MM/yyyy") + " " + HOUR_OPEN_MAP_VODAI + ":" + MIN_OPEN_MAP_VODAI + ":" + SECOND_OPEN_MAP_VODAI, "dd/MM/yyyy HH:mm:ss");
                TIME_CLOSE_VODAI = TimeUtil.getTime(TimeUtil.getTimeNow("dd/MM/yyyy") + " " + HOUR_CLOSE_MAP_VODAI + ":" + MIN_CLOSE_MAP_VODAI + ":" + SECOND_CLOSE_MAP_VODAI, "dd/MM/yyyy HH:mm:ss");
            } catch (Exception ignored) {
            }
        }
    }


    private void kickOutOfMapVodai(Player player) {
        if (MapService.gI().isMapVodai(player.zone.map.mapId)) {
            Service.gI().sendThongBao(player, "Trận đại chiến đã kết thúc, tàu vận chuyển sẽ đưa bạn về nhà");
            ChangeMapService.gI().changeMapBySpaceShip(player, player.gender + 21, -1, 250);
        }
    }

    private void ketthucvodai(Player player) {
        player.zone.finishMapVodai = true;
        List<Player> playersMap = player.zone.getPlayers();
        for (int i = playersMap.size() - 1; i >= 0; i--) {
            Player pl = playersMap.get(i);
            kickOutOfMapVodai(pl);
        }
    }

    public void joinMapVodai(Player player) {
        boolean changed = false;
        if (player.clan != null) {
            List<Player> players = player.zone.getPlayers();
            for (Player pl : players) {
                if (pl.clan != null && !player.equals(pl) && player.clan.equals(pl.clan) && !player.isBoss) {
                    Service.gI().changeFlag(player, Util.nextInt(1, 7));
                    changed = true;
                    break;
                }
            }
        }
        if (!changed && !player.isBoss) {
            Service.gI().changeFlag(player,8);
        }
    }

    public void update(Player player) {
        if (player.zone == null || MapService.gI().isMapVodai(player.zone.map.mapId)) {
            try {
                long now = System.currentTimeMillis();
                if (now < TIME_OPEN_VODAI || now > TIME_CLOSE_VODAI) {
                    ketthucvodai(player);
                }
            } catch (Exception ignored) {
            }
        }

    }
}
