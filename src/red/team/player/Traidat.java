package red.team.player;

// đây
import java.util.ArrayList;
import java.util.List;

import red.consts.ConstMap;
import red.consts.ConstPlayer;
import red.s1.boss.BossesData;
import red.team.boss.list_boss.hotong.KiLan;
import red.team.boss.list_boss.karinboss.ToSuKaioKarin;
import red.team.boss.list_boss.nhanban.Traidatnhanban;
import red.team.map.Map;
import red.team.map.Zone;
import red.team.shop.ShopServiceNew;
import red.team.server.Manager;
import red.server.io.MySession;
import red.services.EffectSkillService;
import red.services.MapService;
import red.services.PlayerService;
import red.services.Service;
import red.services.TaskService;
import red.utils.Util;

/**
 * @author BTH sieu cap vippr0
 */
public class Traidat extends Player {

  private long lastTimeChat;
  private Player playerTarget;

  private long lastTimeTargetPlayer;
  private long timeTargetPlayer = 5000;
  private long lastZoneSwitchTime;
  private long zoneSwitchInterval;
  private List<Zone> availableZones;

  public void initTraidat() {
    init();
  }

  @Override
  public short getHead() {
    return 569;
  }

  @Override
  public short getBody() {
    return 472;
  }

  @Override
  public short getLeg() {
    return 473;
  }

  @Override
  public int injured(Player plAtt, int damage, boolean piercing, boolean isMobAttack) {
    this.checkAnThan(plAtt);
    if (plAtt != null && plAtt.isPl() && (plAtt.playerTask.taskMain.id == 11 || plAtt.playerTask.taskMain.id == 27)) {
      TaskService.gI().sendNextTaskMain(plAtt);
      this.chat("Á đù người hướng nội !");
    }
    if (this.nPoint.hp <= 500000000) {
      this.nPoint.hp = this.nPoint.hpMax;
      Service.gI().point(this);
    }
    if (plAtt != null && plAtt.isPl() && plAtt.cFlag == 1) {
      if (!plAtt.haveNhanBan) {
        try {
          Traidatnhanban traidatnhanban = new Traidatnhanban(Util.randomBossId(),
              BossesData.TRAI_DAT, plAtt.zone, plAtt);
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
    String[] textBaoCat = { "|7|serizawa.store", "|7|lucyonfire", "|7|Welcome to NRO Lucy!" };
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
      if (m.mapId == 190) {
        for (Zone z : m.zones) {
          Traidat pl = new Traidat();
          pl.name = "Trái đất";
          pl.gender = 0;
          pl.id = id;
          pl.isBuNhin = true;
          pl.nPoint.hpMax = (int) 2000000000;
          pl.nPoint.hpg = (int) 2000000000;
          pl.nPoint.hp = (int) 2000000000;
          pl.nPoint.tlPST = 5;
          pl.nPoint.setFullHpMp();
          pl.location.x = 784;
          pl.location.y = 384;
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
