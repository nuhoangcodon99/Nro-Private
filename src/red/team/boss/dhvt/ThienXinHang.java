package red.team.boss.dhvt;

import red.s1.boss.BossID;
import red.s1.boss.BossesData;
import red.team.player.Player;
import red.team.server.Manager;
import red.services.EffectSkillService;
import red.utils.Logger;
import red.utils.Util;

/**
 * @author BTH sieu cap vippr0
 */
public class ThienXinHang extends BossDHVT {

    private long lastTimePhanThan = System.currentTimeMillis();

    public ThienXinHang(Player player) throws Exception {
        super(BossID.THIEN_XIN_HANG, BossesData.THIEN_XIN_HANG);
        this.playerAtt = player;
        phanThan();
    }

    @Override
    public void attack() {
        super.attack();
        try {
            EffectSkillService.gI().removeStun(this);
            if (Util.canDoWithTime(lastTimePhanThan, 90000)) {
                lastTimePhanThan = System.currentTimeMillis();
                phanThan();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void phanThan() {
        try {
            new ThienXinHangClone(BossID.THIEN_XIN_HANG_CLONE, playerAtt);
        } catch (Exception e) {
            e.printStackTrace();
            Logger.logException(Manager.class, e, "Lỗi phân thân txh");
        }

    }
}