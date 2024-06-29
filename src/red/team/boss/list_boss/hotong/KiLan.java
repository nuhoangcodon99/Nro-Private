package red.team.boss.list_boss.hotong;

import red.consts.ConstPlayer;
import red.s1.boss.Boss;
import red.s1.boss.BossData;
import red.s1.boss.BossManager;
import red.team.item.Item;
import red.team.map.ItemMap;
import red.team.map.Zone;
import red.team.player.Player;
import red.team.skill.Skill;
import red.team.server.Client;
import red.services.EffectSkillService;
import red.services.InventoryServiceNew;
import red.services.ItemService;
import red.services.MapService;
import red.services.PlayerService;
import red.services.Service;
import red.services.func.ChangeMapService;
import red.utils.Util;
import red.team.player.Inventory;

/**
 * @author Administrator
 */
public class KiLan extends Boss {

  public KiLan(int bossID, BossData bossData, Zone zone, int x, int y) throws Exception {
    super(bossID, bossData);
    this.zone = zone;
    this.location.x = x;
    this.location.y = y;
  }

  // @Override
  // public void reward(Player plKill) {
  // ItemMap it = new ItemMap(this.zone, Util.nextInt(1099, 1103), Util.nextInt(3,
  // 4), this.location.x, this.zone.map.yPhysicInTop(this.location.x,
  // this.location.y - 24), plKill.id);
  // Service.getInstance().dropItemMap(this.zone, it);
  // }
  long lasttimemove;

  // @Override
  // public void reward(Player plKill) {
  // ItemMap it = new ItemMap(this.zone, 2319, 1, this.location.x,
  // this.zone.map.yPhysicInTop(this.location.x,
  // this.location.y - 24), plKill.id);
  // // it.options.add(new Item.ItemOption(73, 1));
  // Service.getInstance().dropItemMap(this.zone, it);
  // }

  @Override
  public void active() {
    if (this.typePk == ConstPlayer.NON_PK) {
      this.changeToTypePK();
    }
    if (this.playerTarger != null && Client.gI().getPlayer(this.playerTarger.id) == null) {
      this.leaveMap();
    }
    if (this.playerTarger != null && this.playerTarger.isDie()) {
      this.leaveMap();
    }
    if (this.playerTarger != null && this.zone != null
        && (this.zone.map.mapId == 21 || this.zone.map.mapId == 22 || this.zone.map.mapId == 23)) {
      playerTarger.haveKiLan = false;
      this.leaveMap();
    }
    if (Util.getDistance(playerTarger, this) > 500 && this.zone == this.playerTarger.zone) {
      Service.gI().sendThongBao(this.playerTarger, "Xa Kì lân là bao giông tố kéo đến!! ");
      this.leaveMap();
    }
    if (Util.getDistance(playerTarger, this) > 300 && this.zone == this.playerTarger.zone) {
      Service.gI().sendThongBao(this.playerTarger, "Khoảng cách qua xa, Kì lân sắp rời xa bạn!! ");
    }
    if (this.playerTarger != null && Util.getDistance(playerTarger, this) <= 300) {
      int dir = this.location.x - this.playerTarger.location.x <= 0 ? -1 : 1;
      if (Util.canDoWithTime(lasttimemove, 1000)) {
        lasttimemove = System.currentTimeMillis();
        this.moveTo(this.playerTarger.location.x + Util.nextInt(dir == -1 ? 0 : -30, dir == -1 ? 10 : 0),
            this.playerTarger.location.y);
      }
    }
    if (this.playerTarger != null && playerTarger.haveKiLan && this.zone.map.mapId == this.mapCongDuc) {
      playerTarger.inventory.ruby += 100;
      Item ct = ItemService.gI().createNewItem((short) 1237);
      ct.quantity = 1;
      InventoryServiceNew.gI().addItemBag(playerTarger, ct);
      InventoryServiceNew.gI().sendItemBags(playerTarger);
      Service.getInstance().sendMoney(playerTarger);
      Service.gI().sendThongBao(playerTarger, "Bạn nhận được 1 lồng đèn");
      Service.gI().sendThongBao(playerTarger, "Bạn nhận được 50 hồng ngọc");
      if (Util.isTrue(4, 100)) {
        Item duoiKhi = ItemService.gI().createNewItem((short) 1238);
        duoiKhi.quantity = 1;
        InventoryServiceNew.gI().addItemBag(playerTarger, duoiKhi);
        Service.gI().sendThongBao(playerTarger, "Bạn nhận được 1 đuôi khỉ");
      }
      this.leaveMap();
    }
    if (this.playerTarger != null && this.zone != null && this.zone.map.mapId != this.playerTarger.zone.map.mapId) {
      ChangeMapService.gI().changeMap(this, this.playerTarger.zone, this.playerTarger.location.x,
          this.playerTarger.location.y);
    }
    if (Util.canDoWithTime(this.lastTimeAttack, 5000)) {
      Service.gI().chat(this,
          "Hãy hộ tống bé về " + MapService.gI().getMapById(this.mapCongDuc).mapName);
      this.lastTimeAttack = System.currentTimeMillis();
    }
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

      if (plAtt != null && this.playerTarger == plAtt) {
        damage = 0;
        Service.gI().chat(this, "Sao đại ca lại đánh bé? ");
      }

      if (damage > 1) {
        damage = 1;
      }

      this.nPoint.subHP(damage);

      if (this.nPoint.hp <= 10 && plAtt.isPl() && !plAtt.haveKiLan) {
        this.nPoint.hp = this.nPoint.hpMax;

        if (playerTarger.typePk == ConstPlayer.PK_ALL) {
          PlayerService.gI().changeAndSendTypePK(playerTarger, ConstPlayer.NON_PK);
        }
        ;

        playerTarger.haveKiLan = false;// xoa ki la cua chu cu
        this.playerTarger = plAtt;// gan ki lan vao chu moi
        plAtt.haveKiLan = true;// gan target cua no thanh chu moi
        Service.gI().chat(this, plAtt.name + " sẽ là chủ nhân mới của bé !");

        if (playerTarger.typePk == ConstPlayer.NON_PK) {
          PlayerService.gI().changeAndSendTypePK(playerTarger, ConstPlayer.PK_ALL);
        }
        Service.gI().point(this);
      }

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
    if (zoneFinal != null) {
      joinMapByZone(zoneFinal);
      this.notifyJoinMap();
      return;
    }
    if (this.zone == null) {
      if (this.parentBoss != null) {
        this.zone = parentBoss.zone;
      } else if (this.lastZone == null) {
        this.zone = getMapJoin();
      } else {
        this.zone = this.lastZone;
      }
    }
    if (this.zone != null) {
      if (this.currentLevel == 0) {
        if (this.parentBoss == null) {
          ChangeMapService.gI().changeMap(this, this.zone, this.location.x, this.location.y);
        } else {
          ChangeMapService.gI().changeMap(this, this.zone, this.location.x, this.location.y);
          ;
        }
      } else {
        ChangeMapService.gI().changeMap(this, this.zone, this.location.x, this.location.y);
      }
      Service.getInstance().sendFlagBag(this);
      this.notifyJoinMap();
    }
  }

  @Override
  public void leaveMap() {
    super.leaveMap();
    BossManager.gI().removeBoss(this);
    this.dispose();
    playerTarger.haveKiLan = false;
    if (playerTarger.typePk == ConstPlayer.PK_ALL) {
      PlayerService.gI().changeAndSendTypePK(playerTarger, ConstPlayer.NON_PK);
    }
  }
}
