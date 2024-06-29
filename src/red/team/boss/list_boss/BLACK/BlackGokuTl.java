package red.team.boss.list_boss.BLACK;

import java.util.Random;

import red.s1.boss.Boss;
import red.s1.boss.BossID;
import red.s1.boss.BossManager;
import red.s1.boss.BossStatus;
import red.s1.boss.BossesData;
import red.team.map.ItemMap;
import red.team.player.Player;
import red.team.server.Manager;
import red.services.EffectSkillService;
import red.services.Service;
import red.services.TaskService;
import red.utils.Util;


public class BlackGokuTl extends Boss {

    public BlackGokuTl() throws Exception {
        super(BossID.BLACK1, BossesData.BLACK_GOKU, BossesData.SUPER_BLACK_GOKU);
    }

    @Override
    public void reward(Player plKill) {
        byte randomDo = (byte) new Random().nextInt(Manager.itemIds_TL.length - 1);
        byte randomNR = (byte) new Random().nextInt(Manager.itemIds_NR_SB.length);
        if (Util.isTrue(BossManager.ratioReward, 100)) {
            if (Util.isTrue(10, 20)) {
                int[] manhthuong = new int[]{1066,1067,1068,1069,1070};
            int[] manhhiem = new int[]{561};

            int randomAWJ = new Random().nextInt(manhthuong.length);
            int randomGR = new Random().nextInt(manhhiem.length);
            if (Util.isTrue(30, 100)) {
                Service.gI().dropItemMap(this.zone, Util.manhTS(zone, manhthuong[randomAWJ], 1, this.location.x, this.location.y, plKill.id));
            } else {
                Service.gI().dropItemMap(this.zone, Util.manhTS(zone, manhhiem[randomGR], 3, this.location.x, this.location.y, plKill.id));
            }
                Service.gI().dropItemMap(this.zone, Util.ratiItem(zone, 561, 1, this.location.x, this.location.y, plKill.id));
                if (Util.isTrue(5, 10)) {
                    Service.gI().dropItemMap(this.zone, Util.ratiItem(zone, 344, 1, this.location.x+2, this.location.y, plKill.id));
                    Service.gI().dropItemMap(this.zone, Util.ratiItem(zone, 2000+plKill.gender, 1, this.location.x, this.location.y, plKill.id));
                }
                Service.gI().dropItemMap(this.zone,
                        new ItemMap(zone, 865, 1, this.location.x, this.location.y, plKill.id));
            } else {
                Service.gI().dropItemMap(this.zone, Util.ratiItem(zone, Manager.itemIds_TL[randomDo], 1, this.location.x, this.location.y, plKill.id));
            }
        } else {
            Service.gI().dropItemMap(this.zone, new ItemMap(zone, Manager.itemIds_NR_SB[randomNR], 1, this.location.x, zone.map.yPhysicInTop(this.location.x, this.location.y - 24), plKill.id));
        }
        plKill.pointBoss += 3;
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

   @Override
   public void moveTo(int x, int y) {
       if(this.currentLevel == 1){
           return;
       }
       super.moveTo(x, y);
   }
//
//    @Override
//    public void reward(Player plKill) {
//        if(this.currentLevel == 1){
//            return;
//        }
//        super.reward(plKill);
//    }
//
//    @Override
//    protected void notifyJoinMap() {
//        if(this.currentLevel == 1){
//            return;
//        }
//        super.notifyJoinMap();
//    }
}






















