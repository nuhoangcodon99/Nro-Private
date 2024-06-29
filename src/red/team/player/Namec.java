package red.team.player;

// đây
import java.util.ArrayList;
import java.util.List;

import red.consts.ConstMap;
import red.s1.boss.BossesData;
import red.team.boss.list_boss.nhanban.Namecnhanban;
import red.team.boss.list_boss.nhanban.Traidatnhanban;
import red.team.map.Map;
import red.team.map.Zone;
import red.team.shop.ShopServiceNew;
import red.team.server.Manager;
import red.services.EffectSkillService;
import red.services.MapService;
import red.services.PlayerService;
import red.services.Service;
import red.services.TaskService;
import red.utils.Util;

/**
 * @author BTH sieu cap vippr0
 */
public class Namec extends Player {

  private long lastTimeChat;
  private Player playerTarget;

  private long lastTimeTargetPlayer;
  private long timeTargetPlayer = 5000;
  private long lastZoneSwitchTime;
  private long zoneSwitchInterval;
  private List<Zone> availableZones;

  public void initNamec() {
    init();
  }

  @Override
  public short getHead() {
    return 536;
  }

  @Override
  public short getBody() {
    return 476;
  }

  @Override
  public short getLeg() {
    return 477;
  }

  @Override
  public int injured(Player plAtt, int damage, boolean piercing, boolean isMobAttack) {
    this.checkAnThan(plAtt);
    if (this.nPoint.hp <= 500000000) {
      this.nPoint.hp = this.nPoint.hpMax;
      Service.gI().point(this);
    }
    if (plAtt != null && plAtt.isPl() && (plAtt.playerTask.taskMain.id == 11 || plAtt.playerTask.taskMain.id == 27)) {
      TaskService.gI().sendNextTaskMain(plAtt);
      this.chat("Á đù người hướng nội !");
    }
    if (plAtt != null && plAtt.isPl() && plAtt.cFlag == 1) {
      if (!plAtt.haveNhanBan) {
        try {
          Namecnhanban namecnhanban = new Namecnhanban(Util.randomBossId(),
              BossesData.NAMEC, plAtt.zone, plAtt);
          plAtt.haveNhanBan = true;
        } catch (Exception e) {

        }
      }

    }
    if (!this.isDie()) {
      if (!piercing && Util.isTrue(this.nPoint.tlNeDon, 100)) {
        this.chat("Xí hụt");
        return 0;
      }
      this.chat("|7| Sát thương vừa nhận: " + Util.numberToMoney(damage));
      return damage;
    } else {
      return 0;
    }
  }

  public void joinMap(Zone z, Player player) {
    MapService.gI().goToMap(player, z);
    z.load_Me_To_Another(player);
  }

  @Override
  public void update() {
    String[] textBaoCat = { "|7|nrolau.net", "|7|Ngọc Rồng online", "|7|Welcome to Ngọc Rồng online!" };
    if (Util.canDoWithTime(lastTimeChat, 5000)) {
      Service.getInstance().chat(this, textBaoCat[Util.nextInt(textBaoCat.length)]);
      lastTimeChat = System.currentTimeMillis();
      this.nPoint.setFullHpMp();
      Service.gI().point(this);
    }
  }

  private void init() {
    int id = Util.randomBossId();
    for (Map m : Manager.MAPS) {
      if (m.mapId == 191) {
        for (Zone z : m.zones) {
          Namec pl = new Namec();
          pl.name = "Namec";
          pl.gender = 1;
          pl.id = id;
          pl.isBuNhin = true;
          pl.nPoint.hpMax = (int) 2000000000;
          pl.nPoint.hpg = (int) 2000000000;
          pl.nPoint.hp = (int) 2000000000;
          pl.nPoint.tlPST = 5;

          pl.nPoint.setFullHpMp();
          pl.location.x = 706;
          pl.location.y = 360;
          pl.nPoint.power = 99999999999L;
          pl.cFlag = 8;
          joinMap(z, pl);
          z.setReferee(pl);

        }
      }
    }
  }
}

// }
