package red.team.mob;

import red.services.ItemMapService;
import red.services.MapService;
import red.services.Service;
import red.services.TaskService;
import java.util.List;
import java.util.Random;

import com.girlkun.network.io.Message;
import red.consts.ConstMap;
import red.consts.ConstMob;
import red.consts.ConstPlayer;
import red.consts.ConstTask;
import red.team.item.Item;
import red.team.map.ItemMap;
import red.team.map.Zone;
import red.team.player.Location;
import red.team.player.Pet;
import red.team.player.Player;
import red.team.reward.ItemMobReward;
import red.team.reward.MobReward;
import red.team.server.Maintenance;
import red.team.server.Manager;
import red.team.server.ServerManager;
import red.utils.Logger;
import red.utils.Util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

public class Mob {

    public static void initMopbKhiGasHuyDiet(Mob m, int level) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
     private Player player;

    public int id;
    public Zone zone;
    public int tempId;
    public String name;
    public byte level;

    public MobPoint point;
    public MobEffectSkill effectSkill;
    public Location location;

    public byte pDame;
    public int pTiemNang;
    private long maxTiemNang;

    public long lastTimeDie;
    public int lvMob = 0;
    public int status = 5;

    public boolean isMobMe;

    public Mob(Mob mob) {
        this.point = new MobPoint(this);
        this.effectSkill = new MobEffectSkill(this);
        this.location = new Location();
        this.id = mob.id;
        this.tempId = mob.tempId;
        this.level = mob.level;
        this.point.setHpFull(mob.point.getHpFull());
        this.point.sethp(this.point.getHpFull());
        this.location.x = mob.location.x;
        this.location.y = mob.location.y;
        this.pDame = mob.pDame;
        this.pTiemNang = mob.pTiemNang;
        this.setTiemNang();
    }

    public Mob() {
        this.point = new MobPoint(this);
        this.effectSkill = new MobEffectSkill(this);
        this.location = new Location();
    }

    public void setTiemNang() {
        this.maxTiemNang = (long) this.point.getHpFull() * (this.pTiemNang + Util.nextInt(-2, 2)) / 100;
    }

    public static void initMobBanDoKhoBau(Mob mob, byte level) {
        mob.point.dame = level * 3250 * mob.level * 4;
        mob.point.maxHp = level * 12472 * mob.level * 2 + level * 7263 * mob.tempId;
    }

    public static void hoiSinhMob(Mob mob) {
        mob.point.hp = mob.point.maxHp;
        mob.setTiemNang();
        Message msg;
        try {
            msg = new Message(-13);
            msg.writer().writeByte(mob.id);
            msg.writer().writeByte(mob.tempId);
            msg.writer().writeByte(0); //level mob
            msg.writer().writeInt((mob.point.hp));
            Service.getInstance().sendMessAllPlayerInMap(mob.zone, msg);
            msg.cleanup();
        } catch (Exception e) {
        }
    }

    private long lastTimeAttackPlayer;

    public boolean isDie() {
        return this.point.gethp() <= 0;
    }

    public boolean isSieuQuai() {
        return this.lvMob > 0;
    }

    public synchronized void injured(Player plAtt, int damage, boolean dieWhenHpFull) {
        if (!this.isDie()) {
            if (damage >= this.point.hp) {
                damage = this.point.hp;
            }
            if (this.zone.map.mapId == 112) {
                plAtt.NguHanhSonPoint++;
            }
            if (!dieWhenHpFull) {
                if (this.point.hp == this.point.maxHp && damage >= this.point.hp) {
                    damage = this.point.hp - 1;
                }
                if (this.tempId == 0 && damage > 10) {
                    damage = 10;
                }
            }
//            if (plAtt != null) {
//                switch (plAtt.playerSkill.skillSelect.template.id) {
//                    case Skill.KAMEJOKO:
//                    case Skill.MASENKO:
//                    case Skill.ANTOMIC:
//                        if (plAtt.nPoint.multicationChuong > 0 && Util.canDoWithTime(plAtt.nPoint.lastTimeMultiChuong, PlayerSkill.TIME_MUTIL_CHUONG)) {
//                            damage *= plAtt.nPoint.multicationChuong;
//                            plAtt.nPoint.lastTimeMultiChuong = System.currentTimeMillis();
//                        }
//
//                }
//            }
            this.point.hp -= damage;

            if (this.isDie()) {
                if (plAtt != null) {
                    this.lvMob = 0;
                    this.status = 0;
                    this.sendMobDieAffterAttacked(plAtt, damage);
                    TaskService.gI().checkDoneTaskKillMob(plAtt, this);
                    TaskService.gI().checkDoneSideTaskKillMob(plAtt, this);
                }
                this.lastTimeDie = System.currentTimeMillis();

                if (this.id == 13) {
                    this.zone.isbulon13Alive = false;
                }
                if (this.id == 14) {
                    this.zone.isbulon14Alive = false;
                }
                if (this.isSieuQuai()) {
                    //    plAtt.achievement.plusCount(12);
                }
            } else {
                this.sendMobStillAliveAffterAttacked(damage, plAtt != null ? plAtt.nPoint.isCrit : false);
            }
            if (plAtt != null) {
                Service.gI().addSMTN(plAtt, (byte) 2, getTiemNangForPlayer(plAtt, damage), true);
            }
        }
    }

    public long getTiemNangForPlayer(Player pl, long dame) {
        int levelPlayer = Service.gI().getCurrLevel(pl);
        int n = levelPlayer - this.level;
        long pDameHit = dame * 100 / point.getHpFull();
        long tiemNang = pDameHit * maxTiemNang / 100;
        if (tiemNang <= 0) {
            tiemNang = 1;
        }
        if (n >= 0) {
            for (int i = 0; i < n; i++) {
                long sub = tiemNang * 10 / 100;
                if (sub <= 0) {
                    sub = 1;
                }
                tiemNang -= sub;
            }
        } else {
            for (int i = 0; i < -n; i++) {
                long add = tiemNang * 10 / 100;
                if (add <= 0) {
                    add = 1;
                }
                tiemNang += add;
            }
        }
        if (tiemNang <= 0) {
            tiemNang = 1;
        }
        tiemNang = (int) pl.nPoint.calSucManhTiemNang(tiemNang);
        if (pl.zone.map.mapId == 122 || pl.zone.map.mapId == 123 || pl.zone.map.mapId == 124 ) {
            tiemNang *= 2;
        }
        if (pl.zone.map.mapId == 135 || pl.zone.map.mapId == 136 || pl.zone.map.mapId == 137 || pl.zone.map.mapId == 138){
            tiemNang *= 10;
        }
        if (pl.capCS > 0) {
            if (pl.capCS <= 10) {
               tiemNang = tiemNang / 2;
            }
            if (pl.capCS <= 20 && pl.capCS > 10) {
               tiemNang = tiemNang / 4;
            }
            if (pl.capCS > 20) {
               tiemNang = tiemNang / 8;
            }
        }
        return tiemNang;
    }

    public boolean FindChar(int CharID) {
        List<Player> players = this.zone.getPlayers();
        for (int i = 0; i < players.size(); i++) {
            Player pl = players.get(i);
            if (pl != null && pl.id == CharID) {
                return true;
            }
        }
        return false;
    }

    public void update() {
        if (this.isDie() && !Maintenance.isRuning) {
            switch (zone.map.type) {
                case ConstMap.MAP_DOANH_TRAI:
                    if (this.tempId == 22 && this.zone.map.mapId == 59 && FindChar(-2_147_479_965)) {
                        if (Util.canDoWithTime(lastTimeDie, 10000)) {
                            if (this.id == 13) {
                                this.zone.isbulon13Alive = true;
                            }
                            if (this.id == 14) {
                                this.zone.isbulon14Alive = true;
                            }
                            this.hoiSinh();
                            this.sendMobHoiSinh();
                        }

                    }
                    break;
                case ConstMap.MAP_BAN_DO_KHO_BAU:
                    if (this.tempId == 72 || this.tempId == 71) {//ro bot bao ve
                        if (System.currentTimeMillis() - this.lastTimeDie > 3000) {
                            try {
                                Message t = new Message(102);
                                t.writer().writeByte((this.tempId == 71 ? 7 : 6));
                                Service.getInstance().sendMessAllPlayerInMap(this.zone, t);
                                t.cleanup();
                            } catch (IOException e) {
                            }
                        }
                    }
                    break;
                case ConstMap.MAP_KHI_GA_HUY_DIET:
                    break;
                default:
                    if (Util.canDoWithTime(lastTimeDie, 5000)) {
//                        this.randomSieuQuai();
                        this.hoiSinh();
                        this.sendMobHoiSinh();
                    }
            }
        }
        effectSkill.update();
        attackPlayer();
    }

    private void attackPlayer() {
        if (!isDie() && !effectSkill.isHaveEffectSkill() && !(tempId == 0)) {

            if ((this.tempId == 72 || this.tempId == 71) && Util.canDoWithTime(lastTimeAttackPlayer, 300)) {
                List<Player> pl = getListPlayerCanAttack();
                if (!pl.isEmpty()) {
                    this.sendMobBossBdkbAttack(pl, this.point.getDameAttack());
                } else {
                    if (this.tempId == 71) {
                        Player plA = getPlayerCanAttack();
                        if (plA != null) {
                            try {
                                Message t = new Message(102);
                                t.writer().writeByte(5);
                                t.writer().writeByte(plA.location.x);
                                this.location.x = plA.location.x;
                                Service.getInstance().sendMessAllPlayerInMap(this.zone, t);
                                t.cleanup();
                            } catch (IOException e) {
                            }
                        }

                    }
                }
                this.lastTimeAttackPlayer = System.currentTimeMillis();
            } else if (Util.canDoWithTime(lastTimeAttackPlayer, 2000)) {
                Player pl = getPlayerCanAttack();
                if (pl != null) {
                    this.mobAttackPlayer(pl);
                }
                this.lastTimeAttackPlayer = System.currentTimeMillis();
            }

        }
    }

    private void sendMobBossBdkbAttack(List<Player> players, long dame) {
        if (this.tempId == 72) {
            try {
                Message t = new Message(102);
                int action = Util.nextInt(0, 2);
                t.writer().writeByte(action);
                if (action != 1) {
                    this.location.x = players.get(Util.nextInt(0, players.size() - 1)).location.x;
                }
                t.writer().writeByte(players.size());
                for (Byte i = 0; i < players.size(); i++) {
                    t.writer().writeInt((int) players.get(i).id);
                    t.writer().writeInt((int) players.get(i).injured(null, (int) dame, false, true));
                }
                Service.getInstance().sendMessAllPlayerInMap(this.zone, t);
                t.cleanup();
            } catch (IOException e) {
            }
        } else if (this.tempId == 71) {
            try {
                Message t = new Message(102);
                t.writer().writeByte(Util.getOne(3, 4));
                t.writer().writeByte(players.size());
                for (Byte i = 0; i < players.size(); i++) {
                    t.writer().writeInt((int) players.get(i).id);
                    t.writer().writeInt((int) players.get(i).injured(null, (int) dame, false, true));
                }
                Service.getInstance().sendMessAllPlayerInMap(this.zone, t);
                t.cleanup();
            } catch (IOException e) {
            }
        }
    }

    private List<Player> getListPlayerCanAttack() {
        List<Player> plAttack = new ArrayList<>();
        int distance = (this.tempId == 71 ? 250 : 600);
        try {
            List<Player> players = this.zone.getNotBosses();
            for (Player pl : players) {
                if (!pl.isDie() && !pl.isBoss && !pl.effectSkin.isVoHinh) {
                    int dis = Util.getDistance(pl, this);
                    if (dis <= distance) {
                        plAttack.add(pl);
                    }
                }
            }
        } catch (Exception e) {
        }

        return plAttack;
    }

    private Player getPlayerCanAttack() {
        int distance = 100;
        Player plAttack = null;
        try {
            List<Player> players = this.zone.getNotBosses();
            for (Player pl : players) {
                if (!pl.isDie() && !pl.isBoss && !pl.effectSkin.isVoHinh && !pl.isNewPet) {
                    int dis = Util.getDistance(pl, this);
                    if (dis <= distance) {
                        plAttack = pl;
                        distance = dis;
                    }
                }
            }
        } catch (Exception e) {

        }
        return plAttack;
    }

    //**************************************************************************
    private void mobAttackPlayer(Player player) {
        int dameMob = this.point.getDameAttack();
        if (player.charms.tdDaTrau > System.currentTimeMillis()) {
            dameMob /= 2;
        }
        if (this.isSieuQuai()) {
            dameMob = player.nPoint.hpMax / 10;
        }
        int dame = player.injured(null, dameMob, false, true);
        this.sendMobAttackMe(player, dame);
        this.sendMobAttackPlayer(player);
    }

    private void sendMobAttackMe(Player player, int dame) {
        if (!player.isPet && !player.isNewPet) {
            Message msg;
            try {
                msg = new Message(-11);
                msg.writer().writeByte(this.id);
                msg.writer().writeInt(dame); //dame
                player.sendMessage(msg);
                msg.cleanup();
            } catch (Exception e) {
            }
        }
    }

    private void sendMobAttackPlayer(Player player) {
        Message msg;
        try {
            msg = new Message(-10);
            msg.writer().writeByte(this.id);
            msg.writer().writeInt((int) player.id);
            msg.writer().writeInt(player.nPoint.hp);
            Service.gI().sendMessAnotherNotMeInMap(player, msg);
            msg.cleanup();
        } catch (Exception e) {
        }
    }

//    public void randomSieuQuai() {
//        if (this.tempId != 0 && MapService.gI().isMapKhongCoSieuQuai(this.zone.map.mapId) && Util.nextInt(0, 150) < 1) {
//            this.lvMob = 1;
//        }
//    }

    public void hoiSinh() {
        this.status = 5;
        this.point.hp = this.point.maxHp;
        this.setTiemNang();
    }

    public void sendMobHoiSinh() {
        Message msg;
        try {
            msg = new Message(-13);
            msg.writer().writeByte(this.id);
            msg.writer().writeByte(this.tempId);
            msg.writer().writeByte(lvMob);
            msg.writer().writeInt(this.point.hp);
            Service.gI().sendMessAllPlayerInMap(this.zone, msg);
            msg.cleanup();
        } catch (Exception e) {
        }
    }

    //**************************************************************************
    private void sendMobDieAffterAttacked(Player plKill, int dameHit) {
        Message msg;
        try {
            msg = new Message(-12);
            msg.writer().writeByte(this.id);
            msg.writer().writeInt(dameHit);
            msg.writer().writeBoolean(plKill.nPoint.isCrit); // crit
            List<ItemMap> items = mobReward(plKill, this.dropItemTask(plKill), msg);
            Service.gI().sendMessAllPlayerInMap(this.zone, msg);
            msg.cleanup();
            hutItem(plKill, items);
        } catch (Exception e) {
        }
    }

    public void sendMobDieAfterMobMeAttacked(Player plKill, int dameHit) {
        this.status = 0;
        Message msg;
        try {
            if (this.id == 13) {
                this.zone.isbulon13Alive = false;
            }
            if (this.id == 14) {
                this.zone.isbulon14Alive = false;
            }
            msg = new Message(-12);
            msg.writer().writeByte(this.id);
            msg.writer().writeInt(dameHit);
            msg.writer().writeBoolean(false); // crit

            List<ItemMap> items = mobReward(plKill, this.dropItemTask(plKill), msg);
            Service.getInstance().sendMessAllPlayerInMap(this.zone, msg);
            msg.cleanup();
            hutItem(plKill, items);
        } catch (IOException e) {
            Logger.logException(Mob.class, e);
        }
//        if (plKill.isPl()) {
//            if (TaskService.gI().IsTaskDoWithMemClan(plKill.playerTask.taskMain.id)) {
//                TaskService.gI().checkDoneTaskKillMob(plKill, this, true);
//            } else {
//                TaskService.gI().checkDoneTaskKillMob(plKill, this, false);
//            }
//
//        }
        this.lastTimeDie = System.currentTimeMillis();
    }

    private void hutItem(Player player, List<ItemMap> items) {
        if (!player.isPet && !player.isNewPet) {
            if (player.charms.tdThuHut > System.currentTimeMillis()) {
                for (ItemMap item : items) {
                    if (item.itemTemplate.id != 590) {
                        ItemMapService.gI().pickItem(player, item.itemMapId, true);
                    }
                }
            }
        } else {
            if (((Pet) player).master.charms.tdThuHut > System.currentTimeMillis()) {
                for (ItemMap item : items) {
                    if (item.itemTemplate.id != 590) {
                        ItemMapService.gI().pickItem(((Pet) player).master, item.itemMapId, true);
                    }
                }
            }
        }
    }

    private List<ItemMap> mobReward(Player player, ItemMap itemTask, Message msg) 
    {
        List<ItemMap> itemReward = new ArrayList<>();
        try {
            if (player == null) 
            {
                return new ArrayList<>();
            }
            if (Util.isTrue(5, 100)) {
                if (player.setClothes.godClothes && MapService.gI().isMapCold(player.zone.map)) {
//                    ArrietyDrop.DropItemReWard(player, ArrietyDrop.list_thuc_an[Util.nextInt(0, (ArrietyDrop.list_thuc_an.length - 1))], 1, this.location.x, this.location.y);
                }
            }
            itemReward = this.getItemMobReward(player, this.location.x + Util.nextInt(-10, 10),
                    this.zone.map.yPhysicInTop(this.location.x, this.location.y));
            if (itemTask != null) {
                itemReward.add(itemTask);
            }
            //   for (ItemMap itemMap : itemReward) {
            //      if (itemMap.itemTemplate.type <= 4 && itemMap.itemTemplate.name.contains("Thần")) {
            //          itemReward.remove(itemMap);
            //      }
            // }
//            if (Util.isTrue(0.1f, 100)) {
//                if (MapService.gI().isMapCold(player.zone.map)) {
//                    byte randomDo = (byte) new Random().nextInt(Manager.itemIds_TL.length - 1);
//                    ItemMap itemTL =  Util.ratiItem(zone, Manager.itemIds_TL[randomDo], 1, this.location.x, this.location.y, player.id);
//                    Service.gI().dropItemMap(this.zone,itemTL);
//                    if(player.charms.tdThuHut > System.currentTimeMillis())
//                    {
//                         ItemMapService.gI().pickItem(player, itemTL.itemMapId, true);
//                    }
//                }
//            }
            msg.writer().writeByte(itemReward.size());
            for (ItemMap itemMap : itemReward) 
            {

                msg.writer().writeShort(itemMap.itemMapId);
                msg.writer().writeShort(itemMap.itemTemplate.id);
                msg.writer().writeShort(itemMap.x);
                msg.writer().writeShort(itemMap.y);
                msg.writer().writeInt((int) itemMap.playerId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return itemReward;
    }

    private boolean MapStart(int mapid) {
        return mapid == 0 || mapid == 1 || mapid == 2 || mapid == 7 || mapid == 8 || mapid == 9 || mapid == 14 || mapid == 15 || mapid == 16;
    }

    public List<ItemMap> getItemMobReward(Player player, int x, int yEnd) {
        List<ItemMap> list = new ArrayList<>();
        MobReward mobReward = Manager.MOB_REWARDS.get(this.tempId);
        if (mobReward == null) {
            return list;
        }
        final Calendar rightNow = Calendar.getInstance();
        int hour = rightNow.get(11);
        if (MapStart(player.zone.map.mapId)) {
            return new ArrayList<>();
        }
        List<ItemMobReward> items = mobReward.getItemReward();
        List<ItemMobReward> golds = mobReward.getGoldReward();
        if (!items.isEmpty()) {
            ItemMobReward item = items.get(Util.nextInt(0, items.size() - 1));
            ItemMap itemMap = item.getItemMap(zone, player, x, yEnd);
            if (itemMap != null) {
                list.add(itemMap);
            }
        }
        if (!golds.isEmpty()) {
            ItemMobReward gold = golds.get(Util.nextInt(0, golds.size() - 1));
            ItemMap itemMap = gold.getItemMap(zone, player, x, yEnd);
            if (itemMap != null) {
                list.add(itemMap);
            }
        }
        if (player.itemTime.isUseMayDo && Util.isTrue(21, 100) && this.tempId > 57 && this.tempId < 66) {
            list.add(new ItemMap(zone, 380, 1, x, player.location.y, player.id));
        } 
         if ( Util.isTrue(1, 100) && this.tempId > 57 && this.tempId < 66) {
            list.add(new ItemMap(zone, 1370, 1, x, player.location.y, player.id));
        } 
// vat phẩm rơi khi user maaáy dò adu hoa r o day ti code choa
        if (player.itemTime.isUseMayDo2 && Util.isTrue(1, 100) && this.tempId > 1 && this.tempId < 81) {
            list.add(new ItemMap(zone, 2036, 1, x, player.location.y, player.id));// cai nay sua sau nha
        }
        
        if (player.cFlag >= 1 && Util.isTrue(100, 100) && this.tempId == 0 && hour != 1 && hour != 3 && hour != 5 && hour != 7 && hour != 9 && hour != 11 && hour != 13 && hour != 15 && hour != 17 && hour != 19 && hour != 21 && hour != 23) {    //up bí kíp
            list.add(new ItemMap(zone, 590, 1, x, player.location.y, player.id));// cai nay sua sau nha
            if (Util.isTrue(50, 100) && this.tempId == 0) {    //up bí kíp
                list.add(new ItemMap(zone, 590, 1, x, player.location.y, player.id));
                if (Util.isTrue(50, 100) && this.tempId == 0) {    //up bí kíp
                    list.add(new ItemMap(zone, 590, 1, x, player.location.y, player.id));
                    if (Util.isTrue(50, 100) && this.tempId == 0) {    //up bí kíp
                        list.add(new ItemMap(zone, 590, 1, x, player.location.y, player.id));
                    }
                }
            }
        }
        if (this.tempId > 0 && this.zone.map.mapId >= 156 && this.zone.map.mapId <= 159 && player.fusion.typeFusion == ConstPlayer.HOP_THE_PORATA2) {
//        if (Util.isTrue(10, 100)) {    //up bí kíp
//            list.add(new ItemMap(zone, 2076, 1, x, player.location.y, player.id));}
        }
        if (this.tempId > 0 && this.zone.map.mapId >= 156 && this.zone.map.mapId <= 159) {
            if (Util.isTrue(10, 100)) {    //up bí kíp
                list.add(new ItemMap(zone, 933, 1, x, player.location.y, player.id));
            }
        }
        if (this.tempId > 1329 && this.tempId < 1333 && this.zone.map.mapId >= 0) {
            if (Util.isTrue(10, 100)) {    //up khăn đỏ
                list.add(new ItemMap(zone, 1359, 1, x, player.location.y, player.id));
            }
        }
         if (  this.zone.map.mapId == 186 ) {
            if (Util.isTrue(10, 100)) {    //up bóng ma
                list.add(new ItemMap(zone, 1372, 1, x, player.location.y, player.id));
            }
        }
         if ( this.zone.map.mapId == 187 ) {
            if (Util.isTrue(10, 100)) {    //up bóng yêu
                list.add(new ItemMap(zone, 1373, 1, x, player.location.y, player.id));
            }
        }
         if ( this.zone.map.mapId == 188 ) {
            if (Util.isTrue(10, 100)) {    //up bóng thuật
                list.add(new ItemMap(zone, 1374, 1, x, player.location.y, player.id));
            }
        }
          if (this.tempId > 0 && this.zone.map.mapId >= 179  && this.zone.map.mapId <= 180) {
            if (Util.isTrue(10, 100)) {    //up ma quái
                list.add(new ItemMap(zone, 1248, 1, x, player.location.y, player.id));
            }
        }
//        if (this.tempId > 0 && this.zone.map.mapId == 155 && player.setClothes.IsSetHuyDiet()) {
//            if (Util.isTrue(0.5f, 100)) {    //up bí kíp
//                list.add(new ItemMap(zone, Util.nextInt(1066, 1070), 1, x, player.location.y, player.id));
//            }
//        }
        if (this.tempId > 0 && this.zone.map.mapId >= 156 && this.zone.map.mapId <= 159) {
            if (Util.isTrue(10, 100)) {    //up bí kíp
                list.add(new ItemMap(zone, 934, 1, x, player.location.y, player.id));
            }
        }
//        if (this.tempId>0 && this.zone.map.mapId>=156 && this.zone.map.mapId<=159 && player.fusion.typeFusion==ConstPlayer.HOP_THE_PORATA3){
//        if (Util.isTrue(10, 100)) {    //up bí kíp
//            list.add(new ItemMap(zone, 2077, 1, x, player.location.y, player.id));}}
//        if (this.tempId>0 && this.zone.map.mapId>=156 && this.zone.map.mapId<=159 && player.fusion.typeFusion==ConstPlayer.HOP_THE_PORATA3){
//        if (Util.isTrue(10, 100)) {    //up bí kíp
//            list.add(new ItemMap(zone, 2036, 1, x, player.location.y, player.id));}}
//        if (this.tempId>0 && this.zone.map.mapId>=156 && this.zone.map.mapId<=159 && player.fusion.typeFusion==ConstPlayer.HOP_THE_PORATA4){
//        if (Util.isTrue(10, 100)) {    //up bí kíp
//            list.add(new ItemMap(zone, 2036, 1, x, player.location.y, player.id));}}
//        if (player.setClothes.setGod() && this.zone.map.mapId>=105 && this.zone.map.mapId<=111){
//        if (Util.isTrue(10, 100)) {    //up bí kíp
//            list.add(new ItemMap(zone, Util.nextInt(663,667), 1, x, player.location.y, player.id));}
//        }
//        if (player.setClothes.setGod14() && this.zone.map.mapId== 155){
//        if (Util.isTrue(5, 100)) {    //up bí kíp
//            list.add(new ItemMap(zone, Util.nextInt(1066,1070), 1, x, player.location.y, player.id));}
//        }
        Item item = player.inventory.itemsBody.get(1);
        if (this.zone.map.mapId > 0) {
            if (item.isNotNullItem()) {
                if (item.template.id == 691) {
                    if (Util.isTrue(10, 100)) {    //up bí kíp
                        list.add(new ItemMap(zone, Util.nextInt(16, 16), 1, x, player.location.y, player.id));
                    }
                } else if (item.template.id != 691 && item.template.id != 692 && item.template.id != 693) {
                    if (Util.isTrue(0, 1)) {
                        list.add(new ItemMap(zone, 76, 1, x, player.location.y, player.id));
                    }
                }
            }
        }
        if (this.zone.map.mapId >= 0) {
            if (item.isNotNullItem()) {
                if (item.template.id == 692) {
                    if (Util.isTrue(10, 100)) {    //up bí kíp
                        list.add(new ItemMap(zone, Util.nextInt(16, 16), 1, x, player.location.y, player.id));
                    }
                } else if (item.template.id != 691 && item.template.id != 692 && item.template.id != 693) {
                    if (Util.isTrue(0, 1)) {
                        list.add(new ItemMap(zone, 76, 1, x, player.location.y, player.id));
                    }
                }
            }
        }
        if (this.zone.map.mapId > 0) {
            if (item.isNotNullItem()) {
                if (item.template.id == 693) {
                    if (Util.isTrue(10, 100)) {    //up bí kíp
                        list.add(new ItemMap(zone, Util.nextInt(16, 17), 1, x, player.location.y, player.id));
                    }
                } else if (item.template.id != 691 && item.template.id != 692 && item.template.id != 693) {
                    if (Util.isTrue(0, 1)) {
                        list.add(new ItemMap(zone, 76, 1, x, player.location.y, player.id));
                    }
                }
            }
        }
        if (this.zone.map.mapId == 250 && Util.isTrue(3 , 100)) 
        {
            ItemMap itemx =  new ItemMap(zone, 2000 + player.gender, 1, x, player.location.y, player.id);
            itemx.options.add(new Item.ItemOption(30,1));
            list.add(itemx);
        }
        if (player.zone != null &&player.zone.map != null && MapService.gI().isMapBanDoKhoBau(player.zone.map.mapId)) {
            if (player.clan != null && player.clan.banDoKhoBau != null) {
                int level = player.clan.banDoKhoBau.level;
                int slhn = Util.nextInt(1, 3) * (level / 10);
                slhn = slhn < 5 ? 5 : slhn;
                if (Util.nextInt(0, 100) < 100) {

                    list.add(new ItemMap(zone, 861, slhn, x, player.location.y, player.id));
                }
            }
        }
        return list;
    }
     public static void initMobKhiGaHuyDiet(Mob mob, int level) {
        mob.level = (byte) level;
        mob.point.dame = level * 4250 * mob.level * 3;
        mob.point.maxHp = level * 22472 * mob.level * 2 + level * 107263 * mob.tempId;
    }

    private ItemMap dropItemTask(Player player) {
        ItemMap itemMap = null;
        switch (this.tempId) {
            case ConstMob.KHUNG_LONG:
            case ConstMob.LON_LOI:
            case ConstMob.QUY_DAT:
                if (TaskService.gI().getIdTask(player) == ConstTask.TASK_2_0) {
                    itemMap = new ItemMap(this.zone, 73, 1, this.location.x, this.location.y, player.id);
                }
                break;
        }
        if (itemMap != null) {
            return itemMap;
        }
        return null;
    }

    private void sendMobStillAliveAffterAttacked(int dameHit, boolean crit) {
        Message msg;
        try {
            msg = new Message(-9);
            msg.writer().writeByte(this.id);
            msg.writer().writeInt(this.point.gethp());
            msg.writer().writeInt(dameHit);
            msg.writer().writeBoolean(crit); // chí mạng
            msg.writer().writeInt(-1);
            Service.gI().sendMessAllPlayerInMap(this.zone, msg);
            msg.cleanup();
        } catch (Exception e) {
        }
    }
}
