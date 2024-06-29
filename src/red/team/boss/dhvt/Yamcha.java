package red.team.boss.dhvt;

import red.s1.boss.BossID;
import red.s1.boss.BossesData;
import red.team.player.Player;
/**
 * @author BTH sieu cap vippr0 
 */
public class Yamcha extends BossDHVT {

    public Yamcha(Player player) throws Exception {
        super(BossID.YAMCHA, BossesData.YAMCHA);
        this.playerAtt = player;
    }
}