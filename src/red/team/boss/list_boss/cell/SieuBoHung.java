package red.team.boss.list_boss.cell;
import java.util.Random;

import red.consts.ConstPlayer;
import red.s1.boss.Boss;
import red.s1.boss.BossID;
import red.s1.boss.BossStatus;
import red.s1.boss.BossesData;
import red.team.map.ItemMap;
import red.team.player.Player;
import red.team.server.Manager;
import red.services.EffectSkillService;
import red.services.PlayerService;
import red.services.Service;
import red.services.TaskService;
import red.services.func.ChangeMapService;
import red.utils.Util;

public class SieuBoHung extends Boss {
    private long lastTimeHapThu;
    private int timeHapThu;
    public SieuBoHung() throws Exception {
        super(BossID.SIEU_BO_HUNG, BossesData.SIEU_BO_HUNG_1, BossesData.SIEU_BO_HUNG_2, BossesData.SIEU_BO_HUNG_3);
    }
    @Override
    public void joinMap() {
        super.joinMap(); //To change body of generated methods, choose Tools | Templates.
        st = System.currentTimeMillis();
    }
    private long st;

   @Override
    public void reward(Player plKill) {
        int[] itemDos = new int[]{457,555,556,557,558,559,560,561,562,563,564,565,566,567};
        int[] NRs = new int[]{15,16,16,16,16,16,16,16,16,16,16,16,16};
        int randomDo = new Random().nextInt(itemDos.length);
        int randomNR = new Random().nextInt(NRs.length);
        if (Util.isTrue(30, 100)) {
            if (Util.isTrue(30, 100)) {
                Service.gI().dropItemMap(this.zone, Util.ratiItem(zone, 14, 1, this.location.x, this.location.y, plKill.id));
                if (Util.isTrue(3, 10)) {
                    Service.gI().dropItemMap(this.zone, Util.ratiItem(zone, 344, 1, this.location.x+2, this.location.y, plKill.id));
                    Service.gI().dropItemMap(this.zone, Util.ratiItem(zone, 2000+plKill.gender, 1, this.location.x, this.location.y, plKill.id));
                }
            }
            if (Util.isTrue(1, 3)) {
                Service.gI().dropItemMap(this.zone, Util.ratiItem(zone, 344, 1, this.location.x+2, this.location.y, plKill.id));
            }
            Service.gI().dropItemMap(this.zone, Util.ratiItem(zone, itemDos[randomDo], 1, this.location.x, this.location.y, plKill.id));
             } else  {
            Service.gI().dropItemMap(this.zone, new ItemMap(zone, NRs[randomNR], 1, this.location.x, zone.map.yPhysicInTop(this.location.x, this.location.y - 24), plKill.id));
        }
        plKill.pointBoss += 0;
        TaskService.gI().checkDoneTaskKillBoss(plKill, this);
    }
     @Override
    public void active() {
        if (this.typePk == ConstPlayer.NON_PK) {
            this.changeToTypePK();
        }
        this.hapThu();
        this.attack();
         super.active(); //To change body of generated methods, choose Tools | Templates.
        if (Util.canDoWithTime(st, 1800000)) {
            this.changeStatus(BossStatus.LEAVE_MAP);
        }
    }
   @Override
    public int injured(Player plAtt, int damage, boolean piercing, boolean isMobAttack) {
 this.checkAnThan(plAtt);
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
                damage = damage/1;
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
    private void hapThu() {
        if (!Util.canDoWithTime(this.lastTimeHapThu, this.timeHapThu) || !Util.isTrue(1, 100)) {
            return;
        }

        Player pl = this.zone.getRandomPlayerInMap();
        if (pl == null || pl.isDie()) {
            return;
        }
       ChangeMapService.gI().changeMapYardrat(this, this.zone, pl.location.x, pl.location.y);
       this.nPoint.dameg += (pl.nPoint.dame * 5 / 100);
       this.nPoint.hpg += (pl.nPoint.hp * 2 / 100);
       this.nPoint.critg++;
       this.nPoint.calPoint();
       PlayerService.gI().hoiPhuc(this, pl.nPoint.hp, 0);
       pl.injured(null, pl.nPoint.hpMax, true, false);
       Service.gI().sendThongBao(pl, "Bạn vừa bị " + this.name + " hấp thu!");
       this.chat(2, "Ui cha cha, kinh dị quá. " + pl.name + " vừa bị tên " + this.name + " nuốt chửng kìa!!!");
       this.chat("Haha, ngọt lắm đấy " + pl.name + "..");
       this.lastTimeHapThu = System.currentTimeMillis();
       this.timeHapThu = Util.nextInt(15000, 20000);
    }
 
//    @Override
//    public void moveTo(int x, int y) {
//        if(this.currentLevel == 1){
//            return;
//        }
//        super.moveTo(x, y);
//    }
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






















