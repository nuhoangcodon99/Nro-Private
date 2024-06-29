package red.team.boss.list_boss.Mabu13h;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import red.consts.ConstPlayer;
import red.s1.boss.Boss;
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

public class BuBung extends Boss {

  public BuBung() throws Exception {
    super(Util.randomBossId(), BossesData.BU_BUNG);
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
        this.non();
      }

      return damage;
    } else {
      return 0;
    }
  }

  @Override
  public void reward(Player plKill) {
    plKill.pointBoss += 0;
    TaskService.gI().checkDoneTaskKillBoss(plKill, this);
  }

  private void non() {
    List<Player> playersCopy = new ArrayList<>(this.zone.getPlayers());
    for (Player pl : playersCopy) {
      if (pl != null && pl.isPl()) {
        int zoneLast = pl.lastZoneMabu;
        if (zoneLast == 0) {
          zoneLast = Util.nextInt(0, 8);
        }
        ChangeMapService.gI().changeMapInYard(pl, 144, zoneLast, 200);
      }
    }

  }
}
