/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package red.team.boss.list_boss.kami;

import red.consts.ConstPlayer;
import red.s1.boss.Boss;
import red.s1.boss.BossID;
import red.s1.boss.BossStatus;
import red.s1.boss.BossesData;
import red.team.boss.list_boss.kami.kamiSooMe;
import red.team.map.ItemMap;
import red.team.player.Player;
import red.services.EffectSkillService;
import red.services.PlayerService;
import red.services.Service;
import red.services.TaskService;
import red.utils.Util;

public class kamiLoc extends Boss {

    public kamiLoc() throws Exception {
        super(BossID.KAMILOC, BossesData.KAMILOC);
    }

    @Override
    public void reward(Player plKill) {
        ItemMap it = new ItemMap(this.zone, 457, 1, this.location.x, this.zone.map.yPhysicInTop(this.location.x,
                this.location.y - 24), plKill.id);
        Service.gI().dropItemMap(this.zone, it);
        if (Util.isTrue(10, 100)) {
            Service.gI().dropItemMap(this.zone,
                    Util.ratiItem(zone, 2000 + plKill.gender, 1, this.location.x, this.location.y, plKill.id));
        }
        plKill.pointBoss += 0;
        TaskService.gI().checkDoneTaskKillBoss(plKill, this);
    }

    @Override
    public void active() {
        super.active(); // To change body of generated methods, choose Tools | Templates.
        if (Util.canDoWithTime(st, 600000)) {
            this.changeStatus(BossStatus.LEAVE_MAP);
        }
    }

    @Override
    public void joinMap() {
        super.joinMap(); // To change body of generated methods, choose Tools | Templates.
        st = System.currentTimeMillis();
    }

    private long st;

    @Override
    public int injured(Player plAtt, int damage, boolean piercing, boolean isMobAttack) {
        this.checkAnThan(plAtt);
        if (Util.isTrue(30, 100) && plAtt != null) {// tỉ lệ hụt của thiên sứ
            Util.isTrue(this.nPoint.tlNeDon, 100);
            if (Util.isTrue(1, 100)) {
                this.chat("Ta Chính Là Thần");
                this.chat("Ta Chính Là Thần");
            } else if (Util.isTrue(1, 100)) {
                this.chat("Ta Chính Là Thần");
                this.chat("Chỉ cần hoàn thiện nó!");
                this.chat("Các ngươi sẽ tránh được mọi nguy hiểm");
            } else if (Util.isTrue(1, 100)) {
                this.chat("Ta Chính Là Thần");
            }
            damage = 0;

        }
        if (!this.isDie()) {
            if (!piercing && Util.isTrue(this.nPoint.tlNeDon, 100)) {
                this.chat("Xí hụt");
                return 0;
            }
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

}