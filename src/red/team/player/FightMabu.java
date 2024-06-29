package red.team.player;

import red.services.MapService;
import red.services.Service;

public class FightMabu {
    public final byte POINT_MAX = 10;

    public int pointMabu = 0;
    private Player player;

    public FightMabu(Player player){
        this.player = player;
    }

    public void changePoint(int pointAdd) {
        if (MapService.gI().isMapMaBu(player.zone.map.mapId)) {
            pointMabu += pointAdd;
            if (pointMabu >= POINT_MAX) {
                Service.gI().sendThongBao(player, "Bạn đã đủ điểm lên tầng tiếp theo");
            }
        }
    }

    public void clear() {
        this.pointMabu=0;
    }
}
