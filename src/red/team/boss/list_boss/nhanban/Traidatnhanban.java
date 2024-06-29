package red.team.boss.list_boss.nhanban;

import red.consts.ConstPlayer;
import red.s1.boss.Boss;
import red.s1.boss.BossData;
import red.s1.boss.BossManager;
import red.team.map.ItemMap;
import red.team.map.Zone;
import red.team.player.Player;
import red.team.server.Client;
import red.services.EffectSkillService;
import red.services.Service;
import red.utils.Util;

/**
 * @Stole By Lucy#0800
 */
public class Traidatnhanban extends Boss {

    public Traidatnhanban(int bossID, BossData bossData, Zone zone, Player plTarget) throws Exception {
        super(bossID, bossData);
        this.zone = zone;
        this.lockPlayerTarget = plTarget;
        this.playerTarger = plTarget;
    }

    @Override
    public void reward(Player plKill) {

    }

    @Override
    public void active() {
        super.active();
        if (this.typePk == ConstPlayer.NON_PK) {
            this.changeToTypePK();
        }
        if (this.playerTarger != null && Client.gI().getPlayer(this.playerTarger.id) == null) {
            playerTarger.haveNhanBan = false;
            this.leaveMap();
        }
        if (this.playerTarger != null && this.playerTarger.isDie()) {
            playerTarger.haveNhanBan = false;
            this.leaveMap();
        }
        if (this.playerTarger != null && this.playerTarger.zone != this.zone) {
            playerTarger.haveNhanBan = false;
            this.leaveMap();
        }

    }

    @Override
    public void joinMap() {
        super.joinMap();
    }

    @Override
    public int injured(Player plAtt, int damage, boolean piercing, boolean isMobAttack) {
        this.checkAnThan(plAtt);

        if (!this.isDie()) {
            if (!piercing && Util.isTrue(this.nPoint.tlNeDon, 100)) {
                this.chat("Xí hụt");
                return 0;
            }
            this.chat("|7| Sát thương vừa nhận: " + Util.numberToMoney(damage));
            return damage;
        } else {
            return 0;
        }
    }

    @Override
    public void leaveMap() {
        super.leaveMap();
        BossManager.gI().removeBoss(this);
        this.dispose();
        playerTarger.haveNhanBan = false;
    }
}
