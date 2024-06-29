package red.team.boss.list_boss.nronamec;

import java.util.Random;

import red.s1.boss.Boss;
import red.s1.boss.BossesData;
import red.team.map.ItemMap;
import red.team.player.Player;
import red.services.Service;
import red.services.TaskService;
import red.services.func.ChangeMapService;
import red.utils.Util;

public class NroNamec extends Boss {
  private long lastTimeUpdate = System.currentTimeMillis();
  private byte lvl = 0;
  public NroNamec() throws Exception {
    super(Util.randomBossId(), BossesData.KUKU_NRO, BossesData.MAP_DAU_DINH_NRO, BossesData.RAMBO_NRO,
        BossesData.SO_4_NRO, BossesData.SO_3_NRO, BossesData.SO_2_NRO, BossesData.SO_1_NRO,
        BossesData.TIEU_DOI_TRUONG_NRO);

  }

  @Override
  public void active() {
    super.active(); // To change body of generated methods, choose Tools | Templates.
    if (Util.canDoWithTime(lastTimeUpdate, 5000)) {
      ChangeMapService.gI().changeMapYardrat(this, this.zone, Util.nextInt(370 , 2692 ), Util.nextInt(228  , 912 ));
      lastTimeUpdate = System.currentTimeMillis();
    }
  }

  @Override
  public void reward(Player plKill) {
    plKill.pointBoss += 0;
    if(this.lvl < 6){
      this.lvl++;
    }
    
    Service.gI().dropItemMap(this.zone, Util.ratiItem(zone, 1386+lvl, 1, this.location.x, this.location.y, plKill.id));
    TaskService.gI().checkDoneTaskKillBoss(plKill, this);
  }

  @Override
  protected void notifyJoinMap() {
    if (this.currentLevel == 1) {
      return;
    }
    super.notifyJoinMap();
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
