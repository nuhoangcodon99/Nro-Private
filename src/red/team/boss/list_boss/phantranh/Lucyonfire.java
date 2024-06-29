package red.team.boss.list_boss.phantranh;

import java.util.Random;

import red.consts.ConstPlayer;
import red.s1.boss.Boss;
import red.s1.boss.BossID;
import red.s1.boss.BossStatus;
import red.s1.boss.BossesData;
import red.team.map.ItemMap;
import red.team.player.Player;
import red.team.skill.Skill;
import red.services.EffectSkillService;
import red.services.PetService;
import red.services.Service;
import red.services.TaskService;
import red.utils.Util;

/**
 *
 * @Stole By Lucy#0800
 */
public class Lucyonfire extends Boss {

  public Lucyonfire() throws Exception {
    super(BossID.LUCYONFIRE, BossesData.LUCYONFIRE);
  }

  @Override
  public int injured(Player plAtt, int damage, boolean piercing, boolean isMobAttack) {
    this.checkAnThan(plAtt);

    if (!this.isDie()) {
      damage = 1;
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
    super.active();
    if (Util.canDoWithTime(st, 900000)) {
      this.changeStatus(BossStatus.LEAVE_MAP);
    }

  }

  @Override
  public void reward(Player plKill) {
    plKill.pointBoss += 0;
    TaskService.gI().checkDoneTaskKillBoss(plKill, this);
  }

  @Override
  public void joinMap() {
    super.joinMap(); // To change body of generated methods, choose Tools | Templates.
    st = System.currentTimeMillis();
  }

  private long st;
}

/**
 * Vui lòng không sao chép mã nguồn này dưới mọi hình thức. Hãy tôn trọng tác
 * giả của mã nguồn này. Xin cảm ơn! - Lucy
 */
