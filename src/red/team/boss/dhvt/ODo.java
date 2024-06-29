package red.team.boss.dhvt;

import red.s1.boss.BossID;
import red.s1.boss.BossesData;
import red.team.player.Player;

/**
 * @author BTH sieu cap vippr0 
 */
public class ODo extends BossDHVT {

    public ODo(Player player) throws Exception {
        super(BossID.O_DO, BossesData.O_DO);
        this.playerAtt = player;
    }
}
