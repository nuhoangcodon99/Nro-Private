package red.team.boss.list_boss.granola;

import java.util.Calendar;
import java.util.Random;

import red.s1.boss.Boss;
import red.s1.boss.BossesData;
import red.team.map.ItemMap;
import red.team.player.Player;
import red.services.EffectSkillService;
import red.services.Service;
import red.services.TaskService;
import red.utils.Util;

public class Granola extends Boss {

    public Granola() throws Exception {
        super(Util.randomBossId(), BossesData.GRANOLA);
    }

    @Override
    public void reward(Player plKill) {
        int[] manhthuong = new int[] { 1066, 1067, 1068, 1069, 1070 };
        int[] manhhiem = new int[] { 561 };

        int randomAWJ = new Random().nextInt(manhthuong.length);
        int randomGR = new Random().nextInt(manhhiem.length);
        if (Util.isTrue(30, 100)) {
            Service.gI().dropItemMap(this.zone,
                    Util.manhTS(zone, manhthuong[randomAWJ], 1, this.location.x, this.location.y, plKill.id));
        } else {
            Service.gI().dropItemMap(this.zone,
                    Util.manhTS(zone, manhhiem[randomGR], 3, this.location.x, this.location.y, plKill.id));
        }
        for (int i = 0; i < 3; i++) {
            Service.gI().dropItemMap(this.zone,
                    Util.ratiItem(zone, 2000, 1, this.location.x + i * 15, this.location.y, -1));
        }
        for (int i = 0; i < 3; i++) {
            Service.gI().dropItemMap(this.zone,
                    Util.ratiItem(zone, 2001, 1, this.location.x + i * 25, this.location.y, -1));
        }
        for (int i = 0; i < 3; i++) {
            Service.gI().dropItemMap(this.zone,
                    Util.ratiItem(zone, 2002, 1, this.location.x + i * 35, this.location.y, -1));
        }
        plKill.pointBoss += 10;

        TaskService.gI().checkDoneTaskKillBoss(plKill, this);
    }

    @Override
    public int injured(Player plAtt, int damage, boolean piercing, boolean isMobAttack) {
        this.checkAnThan(plAtt);
        if (Util.isTrue(20, 100) && plAtt != null) {// tỉ lệ hụt của thiên sứ
            Util.isTrue(this.nPoint.tlNeDon, 100);

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

/**
 * Vui lòng không sao chép mã nguồn này dưới mọi hình thức. Hãy tôn trọng tác
 * giả của mã nguồn này. Xin cảm ơn! - Lucy
 */
