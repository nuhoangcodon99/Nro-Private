package red.team.boss.dhvt;

import red.s1.boss.BossID;
import red.s1.boss.BossesData;
import red.team.player.Player;

/**
 * @author BTH sieu cap vippr0 
 */
public class PonPut extends BossDHVT {

    public PonPut(Player player) throws Exception {
        super(BossID.PON_PUT, BossesData.PON_PUT);
        this.playerAtt = player;
    }
}