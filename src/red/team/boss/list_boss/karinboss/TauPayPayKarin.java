package red.team.boss.list_boss.karinboss;

import red.consts.ConstPlayer;
import red.s1.boss.Boss;
import red.s1.boss.BossData;
import red.s1.boss.BossID;
import red.s1.boss.BossManager;
import red.s1.boss.BossesData;
import red.team.map.ItemMap;
import red.team.map.Zone;
import red.team.player.Player;
import red.team.server.Client;
import red.services.EffectSkillService;
import red.services.Service;
import red.services.TaskService;
import red.utils.Util;

/**
 * @Stole By Lucy#0800
 */
public class TauPayPayKarin extends Boss {

    public TauPayPayKarin(int bossID, BossData bossData, Zone zone, Player plTarget) throws Exception {
//        super(bossID, bossData);
                super(BossID.TAU_PAY_PAY_KARIN, BossesData.TAU_PAY_PAY_KARIN);
        this.zone = zone;
        this.lockPlayerTarget = plTarget;
    }

    @Override
    public void reward(Player plKill) {
        // vật phẩm rơi khi diệt boss nhân bản
        if (plKill.pointKarin < 1) {
            plKill.pointKarin = 1;
                    TaskService.gI().checkDoneTaskKillBoss(plKill, this);
        }

    }

    @Override
    public void active() {
        super.active();
        if (this.typePk == ConstPlayer.NON_PK) {
            this.changeToTypePK();
        }
        if (this.playerTarger != null && Client.gI().getPlayer(this.playerTarger.id) == null) {
            playerTarger.haveTauPayPay = false;
            this.leaveMap();
        }
        if (this.playerTarger != null && this.playerTarger.isDie()) {
            playerTarger.haveTauPayPay = false;
            this.leaveMap();
        }
        if (this.playerTarger != null && this.playerTarger.zone != this.zone) {
            playerTarger.haveTauPayPay = false;
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
            damage = this.nPoint.subDameInjureWithDeff(damage);
            if (!piercing && effectSkill.isShielding) {
                if (damage > nPoint.hpMax) {
                    EffectSkillService.gI().breakShield(this);
                }

            }
            this.nPoint.subHP(damage);
            if (isDie()) {
                this.setDie(plAtt);
                die(plAtt);

            }
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
        playerTarger.haveTauPayPay = false;
    }
}
