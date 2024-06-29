package red.team.boss.dhvt;

import red.s1.boss.BossData;
import red.s1.boss.BossID;
import red.s1.boss.BossesData;
import red.team.player.Player;

/**
 * @author BTH sieu cap vippr0 
 */
public class JackyChun extends BossDHVT {

    public JackyChun(Player player) throws Exception {
        super(BossID.JACKY_CHUN, BossesData.JACKY_CHUN);
        this.playerAtt = player;
    }
}