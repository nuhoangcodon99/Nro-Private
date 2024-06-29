package red.team.boss.dhvt;

import red.s1.boss.BossID;
import red.s1.boss.BossesData;
import red.team.player.Player;

/**
 * 
 */
public class SoiHecQuyn extends BossDHVT {
    public SoiHecQuyn(Player player) throws Exception {
        super(BossID.SOI_HEC_QUYN, BossesData.SOI_HEC_QUYN);
        this.playerAtt = player;
    }
}
