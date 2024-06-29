/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package red.team.boss.list_boss;

import java.util.Random;

import red.consts.ConstPlayer;
import red.s1.boss.Boss;
import red.s1.boss.BossID;
import red.s1.boss.BossManager;
import red.s1.boss.BossStatus;
import red.s1.boss.BossesData;
import red.team.map.ItemMap;
import red.team.player.Player;
import red.services.EffectSkillService;
import red.services.PlayerService;
import red.services.Service;
import red.services.TaskService;
import red.utils.Util;

/**
 * @@Stole By ASOOME
 */
public class SuperXen extends Boss {
private long lastTimeHapThu;
    private int timeHapThu;
    public SuperXen() throws Exception {
        
        super(BossID.SUPER_XEN, BossesData.SUPER_XEN);
    }

    @Override
    public void reward(Player plKill) {
        if(Util.isTrue(40,100)){
        ItemMap it = new ItemMap(this.zone, 14, 1, this.location.x, this.location.y, plKill.id);
        Service.gI().dropItemMap(this.zone, it);
        if (Util.isTrue(2, 10)) {
            Service.gI().dropItemMap(this.zone, Util.ratiItem(zone, 2000+plKill.gender, 1, this.location.x, this.location.y, plKill.id));
        }
        TaskService.gI().checkDoneTaskKillBoss(plKill, this);
    }else  if(Util.isTrue(50,100)){
         ItemMap it = new ItemMap(this.zone, 15, 1, this.location.x, this.location.y, plKill.id);
        Service.gI().dropItemMap(this.zone, it);
        }
         ItemMap it = new ItemMap(this.zone, 15, 1, this.location.x, this.location.y, plKill.id);
         Service.gI().dropItemMap(this.zone, it);
        TaskService.gI().checkDoneTaskKillBoss(plKill, this);
    }
    @Override
    public void active() {
        if (this.typePk == ConstPlayer.NON_PK) {
            this.changeToTypePK();
        }
        this.hapThu();
        this.attack();
        if(Util.canDoWithTime(st,300000)){
            this.changeStatus(BossStatus.LEAVE_MAP);
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
//        ChangeMapService.gI().changeMapYardrat(this, this.zone, pl.location.x, pl.location.y);
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
        this.timeHapThu = Util.nextInt(30000, 70000);
    }
  @Override
    public int injured(Player plAtt, int damage, boolean piercing, boolean isMobAttack) {
 this.checkAnThan(plAtt);
        if (Util.isTrue(70, 100) && plAtt != null) {//tỉ lệ hụt của thiên sứ
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
            if (damage >= 1000000) {
                damage = 10000000;
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
    public void joinMap() {
        super.joinMap(); //To change body of generated methods, choose Tools | Templates.
        st = System.currentTimeMillis();
    }
    private long st;
    
    @Override
    public void leaveMap() {
        super.leaveMap();
        BossManager.gI().removeBoss(this);
        super.dispose();
        
    }
}
