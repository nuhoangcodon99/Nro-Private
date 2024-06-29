package red.team.map.minuong;

import red.s1.boss.Boss;
import red.team.clan.Clan;
import red.team.mob.Mob;
import red.services.ItemTimeService;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import red.s1.boss.BossID;
import red.s1.boss.BossManager;
import red.s1.boss.BossStatus;
import red.team.map.ItemMap;
import red.team.map.Zone;
import red.team.player.Player;
import red.services.MapService;
import red.services.Service;
import red.services.func.ChangeMapService;
import red.utils.Util;
import java.util.Arrays;

/**
 *
 * - Lucyonfire
 */
@Data
public class MiNuong {

    //bang hội đủ số người mới đc mở
    public static final int N_PLAYER_CLAN = 5;
    //số người đứng cùng khu
    public static final int N_PLAYER_MAP = 1;
    public static final int AVAILABLE = 9; // số lượng doanh trại trong server
    public static final int TIME_MI_NUONG = 1800000;

    private int id;
    private List<Zone> zones;
    private Clan clan;

    private long lastTimeOpen;

    public boolean timePickDragonBall;

    List<Integer> listMap = Arrays.asList(179,180,181,182,183);
    private int currentIndexMap = -1;
    private List<Boss> bossMiNuong;

    public MiNuong(int id) {
        this.id = id;
        this.zones = new ArrayList<>();
        this.bossMiNuong = new ArrayList<>();
    }

    public Zone getMapById(int mapId) {
        for (Zone zone : this.zones) {
            if (zone.map.mapId == mapId) {
                return zone;
            }
        }
        return null;
    }

    public void openMiNuong(Player player) {
        this.lastTimeOpen = System.currentTimeMillis();
        this.clan = player.clan;
        player.clan.miNuong = this;
        player.clan.miNuong_playerOpen = player.name;
        player.clan.miNuong_lastTimeOpen = this.lastTimeOpen;
        //Khởi tạo quái, boss
//        this.init();
        //Đưa thành viên vào doanh trại
        for (Player pl : player.clan.membersInGame) {
            if (pl == null || pl.zone == null) {
                continue;
            }

            ItemTimeService.gI().sendTextMiNuong(pl);
            if (player.zone.equals(pl.zone)) {
                ChangeMapService.gI().changeMapInYard(pl, 179, -1, 120);
            }
           
        }
         sendTextMiNuong();
    }

    public void init() {
        long totalDame = 0;
        long totalHp = 0;
        for (Player pl : this.clan.membersInGame) {
            totalDame += pl.nPoint.dame;
            totalHp += pl.nPoint.hpMax;
        }

        //Hồi sinh quái và thả boss
        for (Zone zone : this.zones) {
            if (zone.map.mapId == this.listMap.get(this.currentIndexMap)) {
                long newDameMob = (totalHp / 5);
                long newHpMob = totalDame * 40;//(totalDame * 20);
                for (Mob mob : zone.mobs) {
                    mob.point.dame = (int) (newDameMob > 2_000_000_000 ? 2_000_000_000 : newDameMob);
                    mob.point.maxHp = (int) (newHpMob > 2_000_000_000 ? 2_000_000_000 : newHpMob);
                    mob.hoiSinh();
                }
                int idBoss = this.getIdBoss(zone.map.mapId);
                if (idBoss != -1) {
                    
                        bossMiNuong.add(BossManager.gI().createBossMiNuong(idBoss, (int) (newDameMob * 3 > 2_000_000_000 ? 2_000_000_000 : newDameMob * 3), (int) (newHpMob * 6 > 2_000_000_000 ? 2_000_000_000 : newHpMob * 10), zone));
                    
                }
            }
        }

    }

    private int getIdBoss(int mapId) {
        switch (mapId) {
            case 180:
                return BossID.SON_TINH;
            case 182:
                return BossID.THUY_TINH;
           
            default:
                return -1;
        }
    }

    public void DropNgocRong() {
        for (Zone zone : zones) {
            ItemMap itemMap = null;
            switch (zone.map.mapId) {
                case 179:
                    itemMap = new ItemMap(zone, 753, 5, 917, 384, -1);
                    itemMap.isMiNuongBall = true;
                    Service.gI().dropItemMap(zone, itemMap);
                    break;
                case 180:
                     itemMap = new ItemMap(zone, 753, 5, 675, 240, -1);
                    itemMap.isMiNuongBall = true;
                    Service.gI().dropItemMap(zone, itemMap);
                    break;
                case 181:
                    itemMap = new ItemMap(zone, 753, 5, 675, 240, -1);
                    itemMap.isMiNuongBall = true;
                    Service.gI().dropItemMap(zone, itemMap);
                    break;
                case 182:
                     itemMap = new ItemMap(zone, 753, 5, 675, 240, -1);
                    itemMap.isMiNuongBall = true;
                    Service.gI().dropItemMap(zone, itemMap);
                    break;
                case 183:
                    itemMap = new ItemMap(zone, 611, 1, 789, 264, -1);
                    itemMap.isMiNuongBall = true;
                    Service.gI().dropItemMap(zone, itemMap);
                    break;
                
            }
        }

    }

    public void dispose() {
        for (Boss b : bossMiNuong) {
            if (b != null) {
                b.changeStatus(BossStatus.LEAVE_MAP);
                b = null;
            }

        }
        this.clan = null;
        this.bossMiNuong.clear();
        timePickDragonBall = false;
        currentIndexMap = -1;
        bossMiNuong.clear();
    }
    private void sendTextMiNuong() {
        for (Player pl : this.clan.membersInGame) {
            ItemTimeService.gI().sendTextMiNuong(pl);
        }
    }
}

/**
 * Vui lòng không sao chép mã nguồn này dưới mọi hình thức. Hãy tôn trọng tác
 * giả của mã nguồn này. Xin cảm ơn! - Stole By Arriety
 */
