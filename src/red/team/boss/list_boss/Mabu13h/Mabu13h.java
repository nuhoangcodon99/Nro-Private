package red.team.boss.list_boss.Mabu13h;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import red.consts.ConstPlayer;
import red.s1.boss.Boss;
import red.s1.boss.BossManager;
import red.s1.boss.BossesData;
import red.team.map.ItemMap;
import red.team.map.Zone;
import red.team.player.Player;
import red.team.server.Manager;
import red.services.EffectSkillService;
import red.services.PlayerService;
import red.services.Service;
import red.services.TaskService;
import red.services.func.ChangeMapService;
import red.utils.Util;

public class Mabu13h extends Boss {
  private long lastTimeHapThu = System.currentTimeMillis();
  private int timeHapThu = 60000;
  private long lastTimeUpdate = System.currentTimeMillis();

  public Mabu13h() throws Exception {
    super(Util.randomBossId(), BossesData.BU_BEO, BossesData.BU_SUPER, BossesData.BU_TENH, BossesData.BU_HAN,
        BossesData.BU_KID);
  }

  @Override
  public void reward(Player plKill) {
    byte randomDo = (byte) new Random().nextInt(Manager.itemIds_TL.length - 1);
    byte randomNR = (byte) new Random().nextInt(Manager.itemIds_NR_SB.length);
    int[] itemDos = new int[] { 555, 556, 557, 558, 559, 560, 561, 562, 563, 564, 565, 566, 567 };
    int randomc12 = new Random().nextInt(itemDos.length);
    if (Util.isTrue(BossManager.ratioReward, 100)) {
      if (Util.isTrue(3, 5)) {
        Service.gI().dropItemMap(this.zone,
            Util.ratiItem(zone, 561, 1, this.location.x, this.location.y, plKill.id));
      }
      if (Util.isTrue(2, 10)) {
        Service.gI().dropItemMap(this.zone,
            Util.ratiItem(zone, 2000 + plKill.gender, 1, this.location.x, this.location.y, plKill.id));
      }
      Service.gI().dropItemMap(this.zone,
          Util.ratiItem(zone, Manager.itemIds_TL[randomDo], 1, this.location.x, this.location.y, plKill.id));
    } else if (Util.isTrue(2, 5)) {
      Service.gI().dropItemMap(this.zone,
          Util.RaitiDoc12(zone, itemDos[randomc12], 1, this.location.x, this.location.y, plKill.id));

    } else {
      Service.gI().dropItemMap(this.zone,
          new ItemMap(zone, Manager.itemIds_NR_SB[randomNR], 1, this.location.x, this.location.y, plKill.id));
    }
    plKill.pointBoss += 0;
    TaskService.gI().checkDoneTaskKillBoss(plKill, this);
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
  protected void notifyJoinMap() {
    if (this.currentLevel == 1) {
      return;
    }
    super.notifyJoinMap();
  }

  @Override
  public void active() {
    this.hapThu();
    super.active(); // To change body of generated methods, choose Tools | Templates.
    if (Util.canDoWithTime(lastTimeUpdate, 10000)) {
      ChangeMapService.gI().changeMapYardrat(this, this.zone, this.location.x, this.location.y);
      lastTimeUpdate = System.currentTimeMillis();
    }
  }

  private void hapThu() {
    if (!Util.canDoWithTime(this.lastTimeHapThu, this.timeHapThu)) {
      return;
    }

    Player pl = this.zone.getRandomPlayerInMap();
    if (pl == null || pl.isDie()) {
      return;
    }
    ChangeMapService.gI().changeMapYardrat(pl, this.zone, pl.location.x, pl.location.y);
    ChangeMapService.gI().changeMap(pl, 128, Util.nextInt(0, 8), 400, 336);
    Service.gI().sendThongBao(pl, "Bạn vừa bị " + this.name + " hấp thu!");
    this.chat(2, "Ui cha cha, kinh dị quá. " + pl.name + " vừa bị tên " + this.name + " nuốt chửng kìa!!!");
    this.chat("Haha, ngọt lắm đấy " + pl.name + "..");
    this.lastTimeHapThu = System.currentTimeMillis();
    this.timeHapThu = Util.nextInt(60000, 120000);
    pl.lastZoneMabu = this.zone.zoneId;
  }

}

/**
 * Vui lòng không sao chép mã nguồn này dưới mọi hình thức. Hãy tôn trọng tác
 * giả của mã nguồn này. Xin cảm ơn! - Lucy
 */
