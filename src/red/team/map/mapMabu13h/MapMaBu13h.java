package red.team.map.mapMabu13h;

import java.util.List;

import red.team.player.Player;
import red.services.MapService;
import red.services.Service;
import red.services.func.ChangeMapService;
import red.utils.TimeUtil;
import red.utils.Util;

public class MapMaBu13h {

    public static final byte HOUR_OPEN_MAP_MABU_13H = 13;
    public static final byte MIN_OPEN_MAP_MABU_13H = 1;
    public static final byte SECOND_OPEN_MAP_MABU_13H = 0;

    public static final byte HOUR_CLOSE_MAP_MABU_13H = 14;
    public static final byte MIN_CLOSE_MAP_MABU_13H = 0;
    public static final byte SECOND_CLOSE_MAP_MABU_13H = 0;

    public static final int AVAILABLE = 9;

    private static MapMaBu13h i;

    public static long TIME_OPEN_MABU_13H;
    public static long TIME_CLOSE_MABU_13H;

    private int day = -1;

    public static MapMaBu13h gI() {
        if (i == null) {
            i = new MapMaBu13h();
        }
        i.setTimeJoinMapMaBu13h();
        return i;
    }

    public void setTimeJoinMapMaBu13h() {
        if (i.day == -1 || i.day != TimeUtil.getCurrDay()) {
            i.day = TimeUtil.getCurrDay();
            try {
                TIME_OPEN_MABU_13H = TimeUtil.getTime(TimeUtil.getTimeNow("dd/MM/yyyy") + " " + HOUR_OPEN_MAP_MABU_13H
                        + ":" + MIN_OPEN_MAP_MABU_13H + ":" + SECOND_OPEN_MAP_MABU_13H, "dd/MM/yyyy HH:mm:ss");
                TIME_CLOSE_MABU_13H = TimeUtil.getTime(TimeUtil.getTimeNow("dd/MM/yyyy") + " " + HOUR_CLOSE_MAP_MABU_13H
                        + ":" + MIN_CLOSE_MAP_MABU_13H + ":" + SECOND_CLOSE_MAP_MABU_13H, "dd/MM/yyyy HH:mm:ss");
            } catch (Exception ignored) {
            }
        }
    }

    private void kickOutOfMapMabu13h(Player player) {
        if (MapService.gI().isMapMabu13h(player.zone.map.mapId)) {
            Service.gI().sendThongBao(player, "Trận đại chiến đã kết thúc, tàu vận chuyển sẽ đưa bạn về nhà");
            ChangeMapService.gI().changeMapBySpaceShip(player, player.gender + 21, -1, 250);
        }
    }

    private void ketthucmabu13h(Player player) {
        player.zone.finishMapMabu13h = true;
        List<Player> playersMap = player.zone.getPlayers();
        for (int i = playersMap.size() - 1; i >= 0; i--) {
            Player pl = playersMap.get(i);
            kickOutOfMapMabu13h(pl);
        }
    }

    public void joinMapMabu13h(Player player) {
        boolean changed = false;
        if (player.clan != null) {
            List<Player> players = player.zone.getPlayers();
            for (Player pl : players) {
                if (pl.clan != null && !player.equals(pl) && player.clan.equals(pl.clan) && !player.isBoss) {
                    Service.gI().changeFlag(player, 1);
                    changed = true;
                    break;
                }
            }
        }
        if (!changed && !player.isBoss) {
            Service.gI().changeFlag(player, 1);
        }
    }

    public void update(Player player) {
        if (player.zone == null || MapService.gI().isMapMabu13h(player.zone.map.mapId)) {
            try {
                long now = System.currentTimeMillis();
                if (now < TIME_OPEN_MABU_13H || now > TIME_CLOSE_MABU_13H) {
                    ketthucmabu13h(player);
                }
            } catch (Exception ignored) {
            }
        }

    }
}
