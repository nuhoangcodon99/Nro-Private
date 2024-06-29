package red.team.map.dianguc;

import java.util.List;

import red.team.player.Player;
import red.services.MapService;
import red.services.Service;
import red.services.func.ChangeMapService;
import red.utils.TimeUtil;
import red.utils.Util;

public class MapDiaNguc{


    public static final byte HOUR_OPEN_MAP_DIA_NGUC = 6;
    public static final byte MIN_OPEN_MAP_DIA_NGUC = 0;
    public static final byte SECOND_OPEN_MAP_DIA_NGUC = 0;

    public static final byte HOUR_CLOSE_MAP_DIA_NGUC = 23;
    public static final byte MIN_CLOSE_MAP_DIA_NGUC = 59;
    public static final byte SECOND_CLOSE_MAP_DIA_NGUC = 0;

    public static final int AVAILABLE = 9;

    private static MapDiaNguc i;

    public static long TIME_OPEN_DIA_NGUC;
    public static long TIME_CLOSE_DIA_NGUC;

    private int day = -1;

    public static MapDiaNguc gI() {
        if (i == null) {
            i = new MapDiaNguc();
        }
        i.setTimeJoinMapDiaNguc();
        return i;
    }

    public void setTimeJoinMapDiaNguc() {
        if (i.day == -1 || i.day != TimeUtil.getCurrDay()) {
            i.day = TimeUtil.getCurrDay();
            try {
                TIME_OPEN_DIA_NGUC = TimeUtil.getTime(TimeUtil.getTimeNow("dd/MM/yyyy") + " " + HOUR_OPEN_MAP_DIA_NGUC + ":" + MIN_OPEN_MAP_DIA_NGUC + ":" + SECOND_OPEN_MAP_DIA_NGUC, "dd/MM/yyyy HH:mm:ss");
                TIME_CLOSE_DIA_NGUC = TimeUtil.getTime(TimeUtil.getTimeNow("dd/MM/yyyy") + " " + HOUR_CLOSE_MAP_DIA_NGUC + ":" + MIN_CLOSE_MAP_DIA_NGUC + ":" + SECOND_CLOSE_MAP_DIA_NGUC, "dd/MM/yyyy HH:mm:ss");
            } catch (Exception ignored) {
            }
        }
    }


    private void kickOutOfMapDiaNguc(Player player) {
        if (MapService.gI().isMapDiaNguc(player.zone.map.mapId)) {
            Service.gI().sendThongBao(player, "Trận đại chiến đã kết thúc, tàu vận chuyển sẽ đưa bạn về nhà");
            ChangeMapService.gI().changeMapBySpaceShip(player, player.gender + 21, -1, 250);
        }
    }

    private void ketthucdianguc(Player player) {
        player.zone.finishMapDiaNguc = true;
        List<Player> playersMap = player.zone.getPlayers();
        for (int i = playersMap.size() - 1; i >= 0; i--) {
            Player pl = playersMap.get(i);
            kickOutOfMapDiaNguc(pl);
        }
    }

    public void joinMapDiaNguc(Player player) {
        boolean changed = false;
        if (player.clan != null) {
            List<Player> players = player.zone.getPlayers();
            for (Player pl : players) {
                if (pl.clan != null && !player.equals(pl) && player.clan.equals(pl.clan) && !player.isBoss) {
                    Service.gI().changeFlag(player, 0);
                    changed = true;
                    break;
                }
            }
        }
        if (!changed && !player.isBoss) {
            Service.gI().changeFlag(player, 0);
        }
    }
    

    public void update(Player player) {
        if (player.zone == null || MapService.gI().isMapDiaNguc(player.zone.map.mapId)) {
            try {
                long now = System.currentTimeMillis();
                if (now < TIME_OPEN_DIA_NGUC || now > TIME_CLOSE_DIA_NGUC) {
                    ketthucdianguc(player);
                }
            } catch (Exception ignored) {
            }
        }

    }
}
