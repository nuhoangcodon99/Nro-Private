package red.team.map.doanhtrai;

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
 * - Stole By Lucy
 */
@Data
public class DoanhTrai {

    //bang hội đủ số người mới đc mở
    public static final int N_PLAYER_CLAN = 5;
    //số người đứng cùng khu
    public static final int N_PLAYER_MAP = 1;
    public static final int AVAILABLE = 9; // số lượng doanh trại trong server
    public static final int TIME_DOANH_TRAI = 1800000;

    private int id;
    private List<Zone> zones;
    private Clan clan;

    private long lastTimeOpen;

    public boolean timePickDragonBall;

    List<Integer> listMap = Arrays.asList(53, 58, 59, 60, 61, 62, 55, 56, 54, 57);
    private int currentIndexMap = -1;
    private List<Boss> bossDoanhTrai;

    public DoanhTrai(int id) {
        this.id = id;
        this.zones = new ArrayList<>();
        this.bossDoanhTrai = new ArrayList<>();
    }

    public Zone getMapById(int mapId) {
        for (Zone zone : this.zones) {
            if (zone.map.mapId == mapId) {
                return zone;
            }
        }
        return null;
    }

    public void openDoanhTrai(Player player) {
        this.lastTimeOpen = System.currentTimeMillis();
        this.clan = player.clan;
        player.clan.doanhTrai = this;
        player.clan.doanhTrai_playerOpen = player.name;
        player.clan.doanhTrai_lastTimeOpen = this.lastTimeOpen;
        //Khởi tạo quái, boss
//        this.init();
        //Đưa thành viên vào doanh trại
        for (Player pl : player.clan.membersInGame) {
            if (pl == null || pl.zone == null) {
                continue;
            }

            ItemTimeService.gI().sendTextDoanhTrai(pl);
            if (player.zone.equals(pl.zone)) {
                ChangeMapService.gI().changeMapInYard(pl, 53, -1, 60);
            }
            
        }
        sendTextDoanhTrai();
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
                long newHpMob = totalDame * 20;//(totalDame * 20);
                for (Mob mob : zone.mobs) {
                    mob.point.dame = (int) (newDameMob > 2_000_000_000 ? 2_000_000_000 : newDameMob);
                    mob.point.maxHp = (int) (newHpMob > 2_000_000_000 ? 2_000_000_000 : newHpMob);
                    mob.hoiSinh();
                }
                int idBoss = this.getIdBoss(zone.map.mapId);
                if (idBoss != -1) {
                    if (idBoss != 1111111111) {
                        bossDoanhTrai.add(BossManager.gI().createBossDoanhTrai(idBoss, (int) (newDameMob * 3 > 2_000_000_000 ? 2_000_000_000 : newDameMob * 3), (int) (newHpMob * 6 > 2_000_000_000 ? 2_000_000_000 : newHpMob * 10), zone));
                    } else { // là vệ sĩ
                        for (Byte i = 0; i < 4; i++) {
                            bossDoanhTrai.add(BossManager.gI().createBossDoanhTrai(BossID.ROBOT_VE_SI1 + i, (int) (newDameMob * 4 > 2_000_000_000 ? 2_000_000_000 : newDameMob * 4), (int) (newHpMob * 4 > 2_000_000_000 ? 2_000_000_000 : newHpMob * 6), zone));
                        }
                    }
                }
            }
        }

    }

    private int getIdBoss(int mapId) {
        switch (mapId) {
            case 59:
                return BossID.TRUNG_UY_TRANG;
            case 62:
                return BossID.TRUNG_UY_XANH_LO;
            case 55:
                return BossID.TRUNG_UY_THEP;
            case 54:
                return BossID.NINJA_AO_TIM;
            case 57:
                return 1111111111; // check vệ sĩ
            default:
                return -1;
        }
    }

    public void DropNgocRong() {
        for (Zone zone : zones) {
            ItemMap itemMap = null;
            switch (zone.map.mapId) {
                case 53:
                    itemMap = new ItemMap(zone, Util.nextInt(14, 20), 1, 917, 384, -1);
                    itemMap.isDoanhTraiBall = true;
                    Service.gI().dropItemMap(zone, itemMap);
                    break;
                case 58:
                    itemMap = new ItemMap(zone, Util.nextInt(14, 20), 1, 658, 336, -1);
                    itemMap.isDoanhTraiBall = true;
                    Service.gI().dropItemMap(zone, itemMap);
                    break;
                case 59:
                    itemMap = new ItemMap(zone, Util.nextInt(14, 20), 1, 675, 240, -1);
                    itemMap.isDoanhTraiBall = true;
                    Service.gI().dropItemMap(zone, itemMap);
                    break;
                case 60:
                    itemMap = new ItemMap(zone, Util.nextInt(14, 20), 1, Util.nextInt(725, 1241), 384, -1);
                    itemMap.isDoanhTraiBall = true;
                    Service.gI().dropItemMap(zone, itemMap);
                    break;
                case 61:
                    itemMap = new ItemMap(zone, Util.nextInt(14, 20), 1, 789, 264, -1);
                    itemMap.isDoanhTraiBall = true;
                    Service.gI().dropItemMap(zone, itemMap);
                    break;
                case 62:
                    itemMap = new ItemMap(zone, Util.nextInt(14, 20), 1, Util.nextInt(197, 1294), 384, -1);
                    itemMap.isDoanhTraiBall = true;
                    Service.gI().dropItemMap(zone, itemMap);
                    break;
                case 55:
                    itemMap = new ItemMap(zone, Util.nextInt(14, 20), 1, 789, 312, -1);
                    itemMap.isDoanhTraiBall = true;
                    Service.gI().dropItemMap(zone, itemMap);
                    break;
                case 56:
                    itemMap = new ItemMap(zone, Util.nextInt(14, 20), 1, 789, 312, -1);
                    itemMap.isDoanhTraiBall = true;
                    Service.gI().dropItemMap(zone, itemMap);
                    break;
                case 54:
                    itemMap = new ItemMap(zone, Util.nextInt(14, 20), 1, 789, 312, -1);
                    itemMap.isDoanhTraiBall = true;
                    Service.gI().dropItemMap(zone, itemMap);
                    break;
            }
        }

    }

    public void dispose() {
        for (Boss b : bossDoanhTrai) {
            if (b != null) {
                b.changeStatus(BossStatus.LEAVE_MAP);
                b = null;
            }

        }
        this.clan = null;
        this.bossDoanhTrai.clear();
        timePickDragonBall = false;
        currentIndexMap = -1;
        bossDoanhTrai.clear();
    }
    private void sendTextDoanhTrai() {
        for (Player pl : this.clan.membersInGame) {
            ItemTimeService.gI().sendTextDoanhTrai(pl);
        }
    }
}

/**
 * Vui lòng không sao chép mã nguồn này dưới mọi hình thức. Hãy tôn trọng tác
 * giả của mã nguồn này. Xin cảm ơn! - Stole By Arriety
 */
