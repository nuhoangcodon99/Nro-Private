package red.team.map.sieuhang;

import red.s1.boss.BossData;
import red.team.boss.dhvt.BossDHVT;
import red.team.player.Player;
import red.utils.Util;

/**
 *
 * @author Béo Mập :3
 */
public class ClonePlayer extends BossDHVT{
    
    public ClonePlayer(Player player, BossData data, int id) throws Exception {
        super(Util.randomBossId(), data,5000);
        this.playerAtt = player;
        this.idPlayer = id;
    }
}
