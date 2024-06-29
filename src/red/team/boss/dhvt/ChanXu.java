package red.team.boss.dhvt;

import red.s1.boss.BossData;
import red.s1.boss.BossID;
import red.s1.boss.BossesData;
import red.team.player.Player;
/**
 * @author BTH sieu cap vippr0 
 */
public class ChanXu extends BossDHVT {

    public ChanXu(Player player) throws Exception {
        super(BossID.CHAN_XU, BossesData.CHAN_XU);
        this.playerAtt = player;
    }
}