package red.team.boss.list_boss.HuyDiet;

import java.util.Random;

import red.s1.boss.Boss;
import red.s1.boss.BossStatus;
import red.s1.boss.BossesData;
import red.team.player.Player;
import red.services.EffectSkillService;
import red.services.Service;
import red.services.TaskService;
import red.utils.Util;

public class Vados extends Boss {

    public Vados() throws Exception {
        super(Util.randomBossId(), BossesData.THIEN_SU_VADOS);
    }

    @Override
    public void reward(Player plKill) {
        int[] manhthuong = new int[]{1066,1067,1068,1069,1070};
        int[] manhhiem = new int[]{561};

        int randomAWJ = new Random().nextInt(manhthuong.length);
        int randomGR = new Random().nextInt(manhhiem.length);
        if (Util.isTrue(95, 100)) {
            for(Integer i =0; i<10;i++){
                Service.gI().dropItemMap(this.zone, Util.manhTS(zone, manhthuong[randomAWJ], 1, this.location.x, this.location.y, -1));
            }

        } else {
            for(Integer i =0; i<10;i++){
            Service.gI().dropItemMap(this.zone, Util.manhTS(zone, manhhiem[randomGR], 1, this.location.x, this.location.y, -1));
            }
        }
        if (Util.isTrue(10, 10)) {
            Service.gI().dropItemMap(this.zone, Util.ratiItem(zone, 2000+plKill.gender, 2, this.location.x, this.location.y, plKill.id));
            Service.gI().dropItemMap(this.zone, Util.ratiItem(zone, 1213, 2, this.location.x, this.location.y, plKill.id));
        }
         Service.gI().dropItemMap(this.zone, Util.ratiItem(zone, 344, 2, this.location.x+2, this.location.y, plKill.id));
        plKill.pointBoss += 10;
        TaskService.gI().checkDoneTaskKillBoss(plKill, this);
    }

   @Override
    public int injured(Player plAtt, int damage, boolean piercing, boolean isMobAttack) {
 this.checkAnThan(plAtt);
        if (Util.isTrue(95, 100) && plAtt != null) {//tỉ lệ hụt của thiên sứ
            Util.isTrue(this.nPoint.tlNeDon, 100);
            if (Util.isTrue(1, 100)) {
                this.chat("Hãy để bản năng tự vận động");
                this.chat("Tránh các động tác thừa");
            } else if (Util.isTrue(1, 100)) {
                this.chat("Chậm lại,các ngươi quá nhanh rồi");
                this.chat("Chỉ cần hoàn thiện nó!");
                this.chat("Các ngươi sẽ tránh được mọi nguy hiểm");
            } else if (Util.isTrue(1, 100)) {
                this.chat("Đây chính là bản năng vô cực");
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
                damage = 1;
            }
            if (damage >= 1) {
                damage = 1;
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
   public void active() {
       super.active(); //To change body of generated methods, choose Tools | Templates.
       if (Util.canDoWithTime(st, 1800000)) {
           this.changeStatus(BossStatus.LEAVE_MAP);
       }
   }

   @Override
   public void joinMap() {
       super.joinMap(); //To change body of generated methods, choose Tools | Templates.
       st = System.currentTimeMillis();
   }
   private long st;
}
