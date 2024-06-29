package red.team.boss.list_boss.NRD;

import red.s1.boss.Boss;
import red.s1.boss.BossesData;
import red.team.map.ItemMap;
import red.team.player.Player;
import red.services.EffectSkillService;
import red.services.Service;
import red.services.TaskService;
import red.utils.Util;


public class Rong1Sao extends Boss {

    public Rong1Sao() throws Exception {
        super(Util.randomBossId(), BossesData.Rong_1Sao);
    }

    @Override
    public void reward(Player plKill) {
        ItemMap it = new ItemMap(this.zone, 372, 1, this.location.x, this.location.y, -1);
        Service.gI().dropItemMap(this.zone, it);
        plKill.pointBoss += 0;
        TaskService.gI().checkDoneTaskKillBoss(plKill, this);
    }
@Override
    public int injured(Player plAtt, int damage, boolean piercing, boolean isMobAttack) {
        this.checkAnThan(plAtt);
        if (!this.isDie()) {
            if (!piercing && Util.isTrue(this.nPoint.tlNeDon, 100)) {
                this.chat("Xí hụt");
                return 0;
            }
           if(plAtt != null && plAtt.nPoint.isSieuThan){
                damage = this.nPoint.subDameInjureWithDeff(damage);
            }else{
                damage = this.nPoint.subDameInjureWithDeff(damage / 2);
            }
            if (!piercing && effectSkill.isShielding) {
                if (damage > nPoint.hpMax) {
                    EffectSkillService.gI().breakShield(this);
                }
                  damage = damage/4;
            }
        if (!piercing && effectSkill.useTroi) {
            EffectSkillService.gI().removeUseTroi(this);
        }
        if (!piercing && effectSkill.isStun) {
            EffectSkillService.gI().removeStun(this);
        }
        if (!piercing && effectSkill.isThoiMien) {
            EffectSkillService.gI().removeThoiMien(this);
        }
        if (!piercing && effectSkill.isBlindDCTT) {
            EffectSkillService.gI().removeBlindDCTT(this);
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


