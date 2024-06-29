package red.team.map.minuong;

import red.team.map.Zone;
import red.team.map.minuong.MiNuong;
import red.team.player.Player;
import red.services.ItemTimeService;
import red.services.MapService;
import red.services.Service;
import red.services.func.ChangeMapService;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lucyonfire
 */
public class MiNuongService {

    private static MiNuongService I;

    public static MiNuongService gI() {
        if (MiNuongService.I == null) {
            MiNuongService.I = new MiNuongService();
        }
        return MiNuongService.I;
    }

    public List<MiNuong> miNuongs;

    private MiNuongService() {
        this.miNuongs = new ArrayList<>();
        for (int i = 0; i < MiNuong.AVAILABLE; i++) {
            this.miNuongs.add(new MiNuong(i));
        }
    }

    public void addMapMiNuong(int id, Zone zone) {
        this.miNuongs.get(id).getZones().add(zone);
    }

    public void joinMiNuong(Player pl) {
        if (pl.clan == null) {
            Service.getInstance().sendThongBao(pl, "Không thể thực hiện");
            return;
        }
        if (pl.clan.miNuong != null) {
            ChangeMapService.gI().changeMapInYard(pl, 179, -1, 120);
            return;
        }
        MiNuong miNuong = null;
        for (MiNuong mn : this.miNuongs) {
            if (mn.getClan() == null) {
                miNuong = mn;
                break;
            }
        }
        if (miNuong == null) {
            Service.getInstance().sendThongBao(pl, "Phó bản đã đầy, hãy quay lại vào lúc khác!");
            return;
        }
        miNuong.openMiNuong(pl);
        ItemTimeService.gI().sendTextMiNuong(pl);
    }

    private void ketthucMiNuong(Player player) {
        List<Player> playersMap = player.zone.getPlayers();
        if (playersMap.isEmpty() && player == null && player.zone == null && player.isNewPet && !player.isPl()) {
            return;
        }
        for (int i = playersMap.size() - 1; i >= 0; i--) {
            Player pl = playersMap.get(i);
            if (MapService.gI().isMapMiNuong(pl.zone.map.mapId)) {
                /**
                 * Đưa mọi người trong map doanh trại về nhà
                 */
                if (MapService.gI().isMapMiNuong(pl.zone.map.mapId)) {
                    if (pl != null && pl.zone != null && !pl.isNewPet && pl.isPl()) {
                        Service.getInstance().sendThongBao(pl, "Trận đại chiến đã kết thúc, tàu vận chuyển sẽ đưa bạn về nhà");
                        ItemTimeService.gI().sendTextTime(pl, (byte) 0, "Aurura", 0);
                        ChangeMapService.gI().changeMapBySpaceShip(pl, pl.gender + 21, -1, 250);
                        if (pl.clan.miNuong != null) {
                            pl.clan.miNuong.dispose();
                            pl.clan.miNuong = null;
                        }
                    }
                }
            }
        }
    }

    public void updatePlayer(Player player) {
        if (player.zone == null || !MapService.gI().isMapMiNuong(player.zone.map.mapId)) {
            return;
        }
        if (player.isPl()) {
            try {
                if (player.clan != null && player.clan.miNuong != null) {
                    long now = System.currentTimeMillis();
                    if (!(now >= player.clan.miNuong_lastTimeOpen && now <= (player.clan.miNuong_lastTimeOpen + MiNuong.TIME_MI_NUONG))) {
                        ketthucMiNuong(player);
                    } else if (player.clan.miNuong.timePickDragonBall && (System.currentTimeMillis() - player.clan.miNuong.getLastTimeOpen() > 5000)) {
                        ketthucMiNuong(player);
                    }
                }
            } catch (Exception ignored) {
                ignored.printStackTrace();
                System.out.println("Lỗi update ở class Doanh trại");
            }
        }
    }
}

/**
 * Vui lòng không sao chép mã nguồn này dưới mọi hình thức. Hãy tôn trọng tác
 * giả của mã nguồn này. Xin cảm ơn! - Khánh đê zéc
 */
