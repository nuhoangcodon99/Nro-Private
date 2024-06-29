package red.team.player;

import red.card.Card;
import red.consts.ConstPlayer;
import red.consts.ConstTask;
import red.data.DataGame;
import red.team.clan.Clan;
import red.team.clan.ClanMember;
import red.team.intrinsic.IntrinsicPlayer;
import red.team.item.Item;
import red.team.item.ItemTime;
import red.team.map.TrapMap;
import red.team.map.Zone;
import red.team.map.KhiGasHuyDiet.KhiGasHuyDiet;
import red.team.map.KhiGasHuyDiet.KhiGasHuyDietService;
import red.team.map.MapMaBu.MapMaBu;
import red.team.map.MapSatan.MapSatan;
import red.team.map.mapMabu13h.MapMaBu13h;
import red.team.map.KhiGasHuyDiet.KhiGasHuyDiet;

import red.team.map.MapVoDai.MapVodai;
import red.team.map.blackball.BlackBallWar;
import red.team.map.dianguc.MapDiaNguc;
import red.team.map.doanhtrai.DoanhTrai;
import red.team.map.doanhtrai.DoanhTraiService;
import red.team.map.minuong.MiNuong;
import red.team.map.minuong.MiNuongService;
import red.team.matches.IPVP;
import red.team.matches.TYPE_LOSE_PVP;
import red.team.matches.TYPE_PVP;
import red.team.matches.pvp.DaiHoiVoThuat;
import red.team.mob.MobMe;
import red.team.npc.specialnpc.BillEgg;
import red.team.npc.specialnpc.GokuEgg;
import red.team.npc.specialnpc.HacAmEgg;
import red.team.npc.specialnpc.MabuEgg;
import red.team.npc.specialnpc.MagicTree;
import red.team.npc.specialnpc.NgokhongEgg;
import red.team.npc.specialnpc.ThienThanEgg;
import red.team.skill.PlayerSkill;
import red.team.skill.Skill;
import red.team.task.TaskPlayer;
import red.team.server.Client;
import red.team.server.Manager;
import red.server.io.MySession;
import red.services.EffectSkillService;
import red.services.FriendAndEnemyService;
import red.services.MapService;
import red.services.PetService;
import red.services.Service;
import red.services.TaskService;
import red.services.func.ChangeMapService;
import red.services.func.ChonAiDay;
import red.services.func.CombineNew;
import red.services.func.TopService;
import red.utils.Logger;
import red.utils.Util;

import java.util.List;

import com.girlkun.network.io.Message;
import com.zaxxer.hikari.util.ConcurrentBag;

import bomong.BoMong;

import java.util.ArrayList;
import java.util.Timer;

public class Player {

    public int goldChallenge;
    public boolean receivedWoodChest;
    public List<String> textRuongGo = new ArrayList<>();
    public MySession session;

    public boolean beforeDispose;
    public boolean isShowCTHT = true;
    public boolean isPet;
    public boolean isNewPet;
    public boolean isBoss;
    public int NguHanhSonPoint = 0;
    public IPVP pvp;
    public int pointPvp;
    public int pointBoss;
    public int pointKarin;
    public int pointCauCa;
    public boolean haveChangeFlagNamec;
    public int pointNroNamec;
    public int pointMabu = 0;
    public int capChuyenSinh = 0;
    public byte maxTime = 30;
    public byte type = 0;
    public int lastZoneMabu = 0;
    public BoMong achievement;
    public byte isVertify = 0;
    public int mapIdBeforeLogout;
    public List<Zone> mapBlackBall;
    public List<Zone> mapMaBu;
    public List<Zone> mapSatan;
    public List<Zone> mapMabu13h;
    public List<Zone> mapDiaNguc;
    public List<Zone> mapVodai;
    public Zone zone;
    public Zone mapBeforeCapsule;
    public List<Zone> mapCapsule;
    public Pet pet;
    public NewPet newpet;
    public long last_time_dd;
    public int countOpenBox;
    public int pointSukien;
    public MobMe mobMe;
    public Location location;
    public SetClothes setClothes;
    public EffectSkill effectSkill;
    public long lastTimeCauCa;
    public boolean isNhatNguyet;
    public int lvlThienSu;
    public MabuEgg mabuEgg;
    public BillEgg billEgg;
    public GokuEgg gokuEgg;
    public NgokhongEgg ngokhongEgg;
    public ThienThanEgg thienThanEgg;
    public HacAmEgg hacAmEgg;
    public TaskPlayer playerTask;
    public ItemTime itemTime;
    public Fusion fusion;
    public MagicTree magicTree;
    public IntrinsicPlayer playerIntrinsic;
    public Inventory inventory;
    public PlayerSkill playerSkill;
    public CombineNew combineNew;
    public IDMark iDMark;
    public Charms charms;
    public EffectSkin effectSkin;
    public Gift gift;
    public NPoint nPoint;
    public RewardBlackBall rewardBlackBall;
    public EffectFlagBag effectFlagBag;
    public FightMabu fightMabu;

    public SkillSpecial skillSpecial;

    public Clan clan;
    public ClanMember clanMember;

    public List<Friend> friends;
    public List<Enemy> enemies;

    public long id;
    public String name;
    public byte gender;
    public boolean isNewMember;
    public short head;
    public boolean haveDuongTang;
    public boolean haveMiNuong;
    public boolean haveKiLan;
    public boolean haveNhanBan;

    public boolean haveTauPayPay;
    public boolean haveThanMeo;
    public boolean haveThuongDe;
    public boolean haveThanVuTru;
    public boolean haveToSuKaio;
    public boolean haveThanHuyDiet;
    public boolean haveThienSu;

    public int mapCongDuc;
    public byte typePk;

    public byte cFlag;
    // danh hieu
    public byte capCS;
    public byte capTT;
       public int kichhoat;
    public boolean titleitem;
    public boolean titlett;
    public boolean isTitleUse;
    public long lastTimeTitle;

    //
    public boolean haveTennisSpaceShip;

    public boolean justRevived;
    public long lastTimeRevived;

    public int violate;
    public byte totalPlayerViolate;
    public long timeChangeZone;
    public long lastTimeUseOption;
    public long lastTimeAddAchie;
    public boolean isDailyReward = true;

    public short idNRNM = -1;
    public short idGo = -1;
    public long lastTimePickNRNM;
    public long lastTimePickKiLan;
    public long lastTimePickDuongTang;
    public long lastTimePickMiNuong;
    public int goldNormar;
    public int goldVIP;
    public long lastTimeWin;
    public boolean isWin;
    public boolean isBuNhin = false;
    public List<Card> Cards = new ArrayList<>();
    public short idAura = -1;
    public int levelWoodChest;
    public int rateCauCa;
    public int rankSieuHang;
    public long numKillSieuHang;
    public boolean lockPK;
    public Timer timerDHVT;
    public Player _friendGiaoDich;

    public Player() {
        System.currentTimeMillis();
        location = new Location();
        nPoint = new NPoint(this);
        inventory = new Inventory();
        playerSkill = new PlayerSkill(this);
        setClothes = new SetClothes(this);
        effectSkill = new EffectSkill(this);
        fusion = new Fusion(this);
        playerIntrinsic = new IntrinsicPlayer();
        rewardBlackBall = new RewardBlackBall(this);
        effectFlagBag = new EffectFlagBag();
        fightMabu = new FightMabu(this);
        // ----------------------------------------------------------------------
        iDMark = new IDMark();
        combineNew = new CombineNew();
        playerTask = new TaskPlayer();
        friends = new ArrayList<>();
        enemies = new ArrayList<>();
        itemTime = new ItemTime(this);
        charms = new Charms();
        gift = new Gift(this);
        effectSkin = new EffectSkin(this);
        skillSpecial = new SkillSpecial(this);
        achievement = new BoMong(this);
    }

    // --------------------------------------------------------------------------
    public boolean isDie() {
        if (this.nPoint != null) {
            return this.nPoint.hp <= 0;
        }
        return true;
    }

    // --------------------------------------------------------------------------
    public void setSession(MySession session) {
        this.session = session;
    }

    public void chat(String text) {
        Service.gI().chat(this, text);
    }

    public void sendMessage(Message msg) {
        if (this.session != null) {
            session.sendMessage(msg);
        }
    }

    public MySession getSession() {
        return this.session;
    }

    public boolean isPl() {
        return !isPet && !isBoss && !isNewPet;
    }

    public boolean isHaveLongDen(int id) {
        return id >= 467 && id <= 471;
    }

    public boolean isTargerDe(Player plAtt) {
        return plAtt.isNewPet || plAtt.isPet || plAtt.isBoss || plAtt.isPl();
    }

    public void update() {
        if (this != null && this.name != null && !this.beforeDispose) {
            try {
                if (!iDMark.isBan()) {

                    if (nPoint != null) {
                        nPoint.update();
                    }
                    if (fusion != null) {
                        fusion.update();
                    }
                    if (effectSkin != null) {
                        effectSkill.update();
                    }
                    if (mobMe != null) {
                        mobMe.update();
                    }
                    if (effectSkin != null) {
                        effectSkin.update();
                    }
                    if (pet != null) {
                        pet.update();
                    }
                    if (newpet != null) {
                        newpet.update();
                    }

                    if (magicTree != null) {
                        magicTree.update();
                    }
                    if (itemTime != null) {
                        itemTime.update();
                    }
                    DoanhTraiService.gI().updatePlayer(this);
                    MiNuongService.gI().updatePlayer(this);
                    BlackBallWar.gI().update(this);
                    MapMaBu.gI().update(this);
                    MapSatan.gI().update(this);
                    MapDiaNguc.gI().update(this);
                    MapMaBu13h.gI().update(this);
                    MapVodai.gI().update(this);

                    if (!isBoss && this.iDMark.isGotoFuture()
                            && Util.canDoWithTime(this.iDMark.getLastTimeGoToFuture(), 6000)) {
                        ChangeMapService.gI().changeMapBySpaceShip(this, 102, -1, Util.nextInt(60, 200));
                        this.iDMark.setGotoFuture(false);
                    }
                    if (this.iDMark.isGoToBDKB() && Util.canDoWithTime(this.iDMark.getLastTimeGoToBDKB(), 6000)) {
                        ChangeMapService.gI().changeMapBySpaceShip(this, 135, -1, 35);
                        this.iDMark.setGoToBDKB(false);
                    }
                    if (!isBoss && this.iDMark.isGoToKGHD()
                            && Util.canDoWithTime(this.iDMark.getLastTimeGoToKGHD(), 6000)) {
                        ChangeMapService.gI().changeMapInYard(this, 184, -1, 303);
                        this.iDMark.setGoToKGHD(false);
                    }
                    if (this.iDMark.isGoToKG() && Util.canDoWithTime(this.iDMark.getLastTimeGoToKG(), 6000)) {
                        ChangeMapService.gI().changeMapBySpaceShip(this, 150, -1, 35);
                        this.iDMark.setGoToKG(false);
                    }
                    if (this.zone != null) {
                        TrapMap trap = this.zone.isInTrap(this);
                        if (trap != null) {
                            trap.doPlayer(this);
                        }
                    }
                    if ((this.isPl() || this.isPet) && this.inventory.itemsBody.size() == 16
                            && this.inventory.itemsBody.get(7) != null) {
                        Item it = this.inventory.itemsBody.get(7);
                        if (it != null && it.isNotNullItem() && this.newpet == null) {
                            PetService.Pet2(this, it.template.head, it.template.body, it.template.leg,
                                    it.template.name);

                            Service.getInstance().point(this);
                        }
                    } else if ((this.isPl() || this.isPet) && this.inventory.itemsBody.size() == 16 && newpet != null
                            && !this.inventory.itemsBody.get(7).isNotNullItem()) {
                        newpet.dispose();
                        newpet = null;
                    }
                    if ((this.isPl() || this.isPet) && this.inventory.itemsBody.size() == 16
                            && this.inventory.itemsBody.get(11) != null) {
                        Item it = this.inventory.itemsBody.get(11);
                        if (it != null && it.isNotNullItem()) {
                            if (it.template.type == 35) {
                                Service.gI().sendFoot(this, it.template.part);
                            }
                            Service.getInstance().sendFlagBag(this);
                        }
                    }
                    if ((this.isPl() || this.isPet) && this.inventory.itemsBody.size() == 16
                            && this.inventory.itemsBody.get(12) != null) {
                        Item it = this.inventory.itemsBody.get(12);
                        Item it1 = this.inventory.itemsBody.get(13);
                        Item it2 = this.inventory.itemsBody.get(14);
                        Item it3 = this.inventory.itemsBody.get(15);
                        if (it != null && it.isNotNullItem()) {
                            if (it.template.type == 36) {
                                Service.gI().sendTitle(this, it.template.part);
                            }
                            Service.getInstance().sendFlagBag(this);
                        }
                        if (it1 != null && it1.isNotNullItem() && it1.template.type == 99) {                           
                            Service.gI().sendTitle(this, it1.template.part);
                            
                        }
                        if (it2 != null && it.isNotNullItem() && it2.template.type == 100) {                           
                            Service.gI().sendTitle(this, it2.template.part);
                            
                        }
                        if (it3 != null && it3.isNotNullItem() && it3.template.type == 101) {                           
                            Service.gI().sendTitle(this, it3.template.part);
                            
                        }
                    }
                    if (this.isPl() && isWin && this.zone.map.mapId == 51 && Util.canDoWithTime(lastTimeWin, 2000)) {
                        ChangeMapService.gI().changeMapBySpaceShip(this, 52, 0, -1);
                        isWin = false;
                    }
                    if (location.lastTimeplayerMove < System.currentTimeMillis() - 30 * 60 * 1000) {
                        Client.gI().kickSession(getSession());
                    }
                } else {
                    if (Util.canDoWithTime(iDMark.getLastTimeBan(), 10000)) {
                        Service.gI().sendThongBaoFromAdmin(this,
                                "Tài khoản của bạn đã bị khóa do có hành vi xấu, bạn sẽ mất kết nối trong 10s");
                        Client.gI().kickSession(session);
                    }
                }
            } catch (Exception e) {
                e.getStackTrace();
            //    Logger.logException(Player.class, e, "Lỗi tại player: " + this.name);
            }
        }
    }

    // --------------------------------------------------------------------------
    /*
     * {380, 381, 382}: ht lưỡng long nhất thể xayda trái đất
     * {383, 384, 385}: ht porata xayda trái đất
     * {391, 392, 393}: ht namếc
     * {870, 871, 872}: ht c2 trái đất
     * {873, 874, 875}: ht c2 namếc
     * {867, 878, 869}: ht c2 xayda
     */
    private static final short[][] idOutfitFusion = {
            { 380, 381, 382 }, { 383, 384, 385 }, { 391, 392, 393 }, // bt1
            { 870, 871, 872 }, { 873, 874, 875 }, { 867, 868, 869 }, // bt2
            { 2036, 2037, 2038 }, { 2039, 2040, 2041 }, { 2042, 2043, 2044 }, // bt3
            { 1222, 1223, 1224 }, { 1225, 1226, 1227 }, { 1228, 1229, 1230 },// bt4
    };

    // Sua id vat pham muon co aura lai
    public byte getAura() {
        if (this.inventory.itemsBody.isEmpty() || this.inventory.itemsBody.size() < 13) {
            return -1;
        }

        if (this.capChuyenSinh > 0) {
            switch (this.capChuyenSinh) {
                case 1:
                    return 0;

                case 2:

                    return 21;
                case 3:

                    return 23;
                case 4:

                    return 13;
                case 5:

                    return 6;
                case 6:

                    return 8;
                case 7:

                    return 17;
                case 8:

                    return 4;
                case 9:

                    return 22;
                case 10:

                    return 20;

            }
        }
        return (byte) idAura;
    }

    // hieu ung theo set
    public byte getEffFront() {
        if (this.inventory.itemsBody.isEmpty() || this.inventory.itemsBody.size() < 13) {
            return -1;
        }

        int levelAo = 0;
        Item.ItemOption optionLevelAo = null;
        int levelQuan = 0;
        Item.ItemOption optionLevelQuan = null;
        int levelGang = 0;
        Item.ItemOption optionLevelGang = null;
        int levelGiay = 0;
        Item.ItemOption optionLevelGiay = null;
        int levelNhan = 0;
        Item.ItemOption optionLevelNhan = null;
        Item itemAo = this.inventory.itemsBody.get(0);
        Item itemQuan = this.inventory.itemsBody.get(1);
        Item itemGang = this.inventory.itemsBody.get(2);
        Item itemGiay = this.inventory.itemsBody.get(3);
        Item itemNhan = this.inventory.itemsBody.get(4);
        for (Item.ItemOption io : itemAo.itemOptions) {
            if (io.optionTemplate.id == 72) {
                levelAo = io.param;
                optionLevelAo = io;
                break;
            }
        }
        for (Item.ItemOption io : itemQuan.itemOptions) {
            if (io.optionTemplate.id == 72) {
                levelQuan = io.param;
                optionLevelQuan = io;
                break;
            }
        }
        for (Item.ItemOption io : itemGang.itemOptions) {
            if (io.optionTemplate.id == 72) {
                levelGang = io.param;
                optionLevelGang = io;
                break;
            }
        }
        for (Item.ItemOption io : itemGiay.itemOptions) {
            if (io.optionTemplate.id == 72) {
                levelGiay = io.param;
                optionLevelGiay = io;
                break;
            }
        }
        for (Item.ItemOption io : itemNhan.itemOptions) {
            if (io.optionTemplate.id == 72) {
                levelNhan = io.param;
                optionLevelNhan = io;
                break;
            }
        }
        if (optionLevelAo != null && optionLevelQuan != null && optionLevelGang != null && optionLevelGiay != null
                && optionLevelNhan != null
                && levelAo >= 8 && levelQuan >= 8 && levelGang >= 8 && levelGiay >= 8 && levelNhan >= 8) {
            return 8;
        } else if (optionLevelAo != null && optionLevelQuan != null && optionLevelGang != null
                && optionLevelGiay != null && optionLevelNhan != null
                && levelAo >= 7 && levelQuan >= 7 && levelGang >= 7 && levelGiay >= 7 && levelNhan >= 7) {
            return 7;
        } else if (optionLevelAo != null && optionLevelQuan != null && optionLevelGang != null
                && optionLevelGiay != null && optionLevelNhan != null
                && levelAo >= 6 && levelQuan >= 6 && levelGang >= 6 && levelGiay >= 6 && levelNhan >= 6) {
            return 6;
        } else if (optionLevelAo != null && optionLevelQuan != null && optionLevelGang != null
                && optionLevelGiay != null && optionLevelNhan != null
                && levelAo >= 5 && levelQuan >= 5 && levelGang >= 5 && levelGiay >= 5 && levelNhan >= 5) {
            return 5;
        } else if (optionLevelAo != null && optionLevelQuan != null && optionLevelGang != null
                && optionLevelGiay != null && optionLevelNhan != null
                && levelAo >= 4 && levelQuan >= 4 && levelGang >= 4 && levelGiay >= 4 && levelNhan >= 4) {
            return 4;
        } else {
            return -1;
        }
    }

    public short getHead() {
        if (this.itemTime != null && this.itemTime.isEatDuoiKhi) {
            return 198;
        } else if (this.effectSkill != null && this.effectSkill.isMonkey) {
            return (short) ConstPlayer.HEADMONKEY[effectSkill.levelMonkey - 1];
        } else if (this.effectSkill != null && this.effectSkill.isSocola) {
            return 412;
        } else if (this.effectSkill != null && this.effectSkill.isHoaSocola) {
            return 412;
        } else if (this.effectSkill != null && this.effectSkill.isHoaCarot) {
            return 406;
        } else if (effectSkill != null && this.effectSkill.isHoaDa) {
            return 454;
        } else if (this.effectSkill != null && this.effectSkill.isBinh) {
            return 1321;
        }else if (fusion != null && fusion.typeFusion == ConstPlayer.LUONG_LONG_NHAT_THE && this.nPoint.isSSJ4 && this.pet != null && this.pet.nPoint.isSSJ4) {
            return 1351;
        } else if (this.itemTime != null && this.itemTime.isEatPotential) {
            switch (this.gender) {
                case 0:
                    return 1243;
                case 1:
                    return 1240;
                case 2:
                    return 1237;
            }
        } else if (fusion != null && fusion.typeFusion != ConstPlayer.NON_FUSION && this.isShowCTHT) {
            if (fusion.typeFusion == ConstPlayer.LUONG_LONG_NHAT_THE) {
                return idOutfitFusion[this.gender == ConstPlayer.NAMEC ? 2 : 0][0];
            } else if (fusion.typeFusion == ConstPlayer.HOP_THE_PORATA) {
                if (this.pet.typePet == 1) {
                    return idOutfitFusion[3 + this.gender][0];
                }
                return idOutfitFusion[this.gender == ConstPlayer.NAMEC ? 2 : 1][0];
            } else if (fusion.typeFusion == ConstPlayer.HOP_THE_PORATA2) {
                if (this.pet.typePet == 1) {
                    return idOutfitFusion[3 + this.gender][0];
                }
                return idOutfitFusion[3 + this.gender][0];
            } else if (fusion.typeFusion == ConstPlayer.HOP_THE_PORATA3) {
                if (this.pet.typePet == 1) {
                    return idOutfitFusion[3 + this.gender][0];
                }

                return idOutfitFusion[6 + this.gender][0];

            } else if (fusion.typeFusion == ConstPlayer.HOP_THE_PORATA4) {
                if (this.pet.typePet == 1) {
                    return idOutfitFusion[3 + this.gender][0];
                }

                return idOutfitFusion[9 + this.gender][0];
            }
        } else if (inventory != null && inventory.itemsBody.get(5).isNotNullItem()) {
            int head = inventory.itemsBody.get(5).template.head;
            if (head != -1) {
                return (short) head;
            }
        }
        return this.head;
    }

    public short getBody() {
        if (this.itemTime != null && this.itemTime.isEatDuoiKhi) {
            return 193;
        } else if (effectSkill.isMonkey) {
            return 193;
        } else if (effectSkill != null && effectSkill.isSocola) {
            return 413;
        } else if (effectSkill != null && effectSkill.isHoaSocola) {
            return 413;
        } else if (effectSkill != null && effectSkill.isHoaDa) {
            return 455;
        } else if (effectSkill != null && effectSkill.isHoaCarot) {
            return 407;
        } else if (effectSkill != null && effectSkill.isBinh) {
            return 1322;
        }else if (fusion != null && fusion.typeFusion == ConstPlayer.LUONG_LONG_NHAT_THE  && this.nPoint.isSSJ4 && this.pet != null && this.pet.nPoint.isSSJ4) {
            return 1352;
        } else if (itemTime != null && itemTime.isEatPotential) {
            switch (gender) {
                case 0:
                    return 1244;
                case 1:
                    return 1241;
                case 2:
                    return 1238;
            }
        } else if (fusion != null && fusion.typeFusion != ConstPlayer.NON_FUSION && this.isShowCTHT) {
            if (fusion.typeFusion == ConstPlayer.LUONG_LONG_NHAT_THE) {
                return idOutfitFusion[this.gender == ConstPlayer.NAMEC ? 2 : 0][1];
            } else if (fusion.typeFusion == ConstPlayer.HOP_THE_PORATA) {
                if (this.pet.typePet == 1) {
                    return idOutfitFusion[3 + this.gender][1];
                }
                return idOutfitFusion[this.gender == ConstPlayer.NAMEC ? 2 : 1][1];
            } else if (fusion.typeFusion == ConstPlayer.HOP_THE_PORATA2) {
                if (this.pet.typePet == 1) {
                    return idOutfitFusion[3 + this.gender][1];
                }
                return idOutfitFusion[3 + this.gender][1];
            } else if (fusion.typeFusion == ConstPlayer.HOP_THE_PORATA3) {
                if (this.pet.typePet == 1) {
                    return idOutfitFusion[3 + this.gender][1];
                }
                return idOutfitFusion[6 + this.gender][1];
            } else if (fusion.typeFusion == ConstPlayer.HOP_THE_PORATA4) {
                if (this.pet.typePet == 1) {
                    return idOutfitFusion[3 + this.gender][1];
                }
                return idOutfitFusion[9 + this.gender][1];
            }
        } else if (inventory != null && inventory.itemsBody.get(5).isNotNullItem()) {
            int body = inventory.itemsBody.get(5).template.body;
            if (body != -1) {
                return (short) body;
            }
        }
        if (inventory != null && inventory.itemsBody.get(0).isNotNullItem()) {
            return inventory.itemsBody.get(0).template.part;
        }
        return (short) (gender == ConstPlayer.NAMEC ? 59 : 57);
    }

    public short getLeg() {
        if (this.itemTime != null && this.itemTime.isEatDuoiKhi) {
            return 194;
        } else if (effectSkill.isMonkey) {
            return 194;
        } else if (effectSkill != null && effectSkill.isSocola) {
            return 414;
        } else if (effectSkill != null && effectSkill.isHoaSocola) {
            return 414;
        } else if (effectSkill != null && effectSkill.isHoaDa) {
            return 456;
        } else if (effectSkill != null && effectSkill.isHoaCarot) {
            return 408;
        } else if (effectSkill != null && effectSkill.isBinh) {
            return 1323;
        }else if (fusion != null && fusion.typeFusion == ConstPlayer.LUONG_LONG_NHAT_THE  && this.nPoint.isSSJ4 && this.pet != null && this.pet.nPoint.isSSJ4) {
            return 1353;
        } else if (itemTime != null && itemTime.isEatPotential) {
            switch (gender) {
                case 0:
                    return 1245;
                case 1:
                    return 1242;
                case 2:
                    return 1239;
            }
        } else if (fusion != null && fusion.typeFusion != ConstPlayer.NON_FUSION && this.isShowCTHT) {
            if (fusion.typeFusion == ConstPlayer.LUONG_LONG_NHAT_THE) {
                return idOutfitFusion[this.gender == ConstPlayer.NAMEC ? 2 : 0][2];
            } else if (fusion.typeFusion == ConstPlayer.HOP_THE_PORATA) {
                if (this.pet.typePet == 1) {
                    return idOutfitFusion[3 + this.gender][2];
                }
                return idOutfitFusion[this.gender == ConstPlayer.NAMEC ? 2 : 1][2];
            } else if (fusion.typeFusion == ConstPlayer.HOP_THE_PORATA2) {
                if (this.pet.typePet == 1) {
                    return idOutfitFusion[3 + this.gender][2];
                }
                return idOutfitFusion[3 + this.gender][2];
            } else if (fusion.typeFusion == ConstPlayer.HOP_THE_PORATA3) {
                if (this.pet.typePet == 1) {
                    return idOutfitFusion[3 + this.gender][2];
                }
                return idOutfitFusion[6 + this.gender][2];
            } else if (fusion.typeFusion == ConstPlayer.HOP_THE_PORATA4) {
                if (this.pet.typePet == 1) {
                    return idOutfitFusion[3 + this.gender][2];
                }
                return idOutfitFusion[9 + this.gender][2];
            }
        } else if (inventory != null && inventory.itemsBody.get(5).isNotNullItem()) {
            int leg = inventory.itemsBody.get(5).template.leg;
            if (leg != -1) {
                return (short) leg;
            }
        }
        if (inventory != null && inventory.itemsBody.get(1).isNotNullItem()) {
            return inventory.itemsBody.get(1).template.part;
        }
        return (short) (gender == 1 ? 60 : 58);
    }

    public short getFlagBag() {
        if (this.nPoint.isThieuDot && this.nPoint.tlHpGiamDiaNguc > 0) {
            return 45;
        }
        if(this.itemTime.isCauCa){
            return 73;
        }
        if (this.iDMark.isHoldBlackBall()) {
            return 31;
        } else if (this.idNRNM >= 353 && this.idNRNM <= 359) {
            return 30;
        }
        if ((this.isPl() || this.isPet) && this.inventory.itemsBody.size() <= 16) {
            if (this.inventory.itemsBody.get(8).isNotNullItem()) {
                return this.inventory.itemsBody.get(8).template.part;
            }
        }

        if (TaskService.gI().getIdTask(this) == ConstTask.TASK_3_2) {
            return 28;
        }
        if (this.clan != null) {
            return (short) this.clan.imgId;
        }
        return -1;
    }

    public short getMount() {
        if (this.inventory.itemsBody.isEmpty() || this.inventory.itemsBody.size() < 13) {
            return -1;
        }

        Item item = this.inventory.itemsBody.get(9);
        if (!item.isNotNullItem()) {
            return -1;
        }
        if (item.template.type == 24) {
            if (item.template.gender == 3 || item.template.gender == this.gender) {
                return item.template.id;
            } else {
                return -1;
            }
        } else {
            if (item.template.id < 500) {
                return item.template.id;
            } else {
                return (short) DataGame.MAP_MOUNT_NUM.get(String.valueOf(item.template.id));
            }
        }

    }

    //
    public void checkAnThan(Player plAtt) {
        if (plAtt != null && (plAtt.isPl() || plAtt.isPet) && plAtt.effectSkill.isAnThan) {
            EffectSkillService.gI().removeAnThan(plAtt);
        }
    }

    // --------------------------------------------------------------------------
    public int injured(Player plAtt, int damage, boolean piercing, boolean isMobAttack) {
        this.checkAnThan(plAtt);
        if (!this.isDie()) {
            if (plAtt != null) {
                switch (plAtt.playerSkill.skillSelect.template.id) {
                    case Skill.KAMEJOKO:
                    case Skill.MASENKO:
                    case Skill.ANTOMIC:
                        if (this.nPoint.voHieuChuong > 0) {
                            red.services.PlayerService.gI().hoiPhuc(this, 0,
                                    damage * this.nPoint.voHieuChuong / 100);
                            return 0;
                        }
                }
            }

            if (this.pet != null && this.pet.status < 3) {
                this.pet.angry(plAtt);
            }
            if (this.isPet && (((Pet) this).status < 3)) {
                ((Pet) this).angry(plAtt);
            }

            if (Util.isTrue(this.nPoint.tlNeDon, 100)) {
                Service.gI().chat(this, "Xí hụt, tỉ lệ né của ta là " + this.nPoint.tlNeDon);
                return 0;

            }

            damage = this.nPoint.subDameInjureWithDeff(damage, plAtt);

            if (!piercing && effectSkill.isShielding) {
                if (damage > nPoint.hpMax) {
                    EffectSkillService.gI().breakShield(this);
                }
                damage = 1;
            }

            if (isMobAttack && this.charms.tdBatTu > System.currentTimeMillis() && damage >= this.nPoint.hp) {
                damage = this.nPoint.hp - 1;
            }

            this.nPoint.subHP(damage); // xoa tu day
            if (isDie()) {
                if (this.zone.map.mapId == 112) {

                } else {
                    if (MapService.gI().isMapMaBu(this.zone.map.mapId) && plAtt.isPl()) {
                        plAtt.fightMabu.changePoint(1);
                        Service.gI().sendThongBao(plAtt, "|7|Điểm của bạn là " + plAtt.fightMabu.pointMabu);
                    }
                }
                setDie(plAtt);
            }
            return damage; // xoa den day
        } else {
            return 0;
        }
    }

    protected void setDie(Player plAtt) {
        // xóa phù
        if (this.effectSkill != null && this.effectSkin.xHPKI > 1) {
            this.effectSkin.xHPKI = 1;
            Service.gI().point(this);
        }
        // xóa tụ skill đặc biệt
        this.playerSkill.prepareQCKK = false;
        this.playerSkill.prepareLaze = false;
        this.playerSkill.prepareTuSat = false;
        // xóa hiệu ứng skill
        this.effectSkill.removeSkillEffectWhenDie();
        //
        nPoint.setHp(0);
        nPoint.setMp(0);
        // xóa trứng
        if (this.mobMe != null) {
            this.mobMe.mobMeDie();
        }
        Service.gI().charDie(this);
        // add kẻ thù
        if (!this.isPet && !this.isNewPet && !this.isBoss && plAtt != null && !plAtt.isPet
                && !plAtt.isNewPet && !plAtt.isBoss) {
            if (!plAtt.itemTime.isUseAnDanh) {
                FriendAndEnemyService.gI().addEnemy(this, plAtt);
            }
        }
        if (this.isPl() && plAtt != null && plAtt.isPl()) {
            plAtt.achievement.plusCount(3);
        }
        // kết thúc pk
        if (this.pvp != null) {
            this.pvp.lose(this, TYPE_LOSE_PVP.DEAD);
        }
        // PVPServcice.gI().finishPVP(this, PVP.TYPE_DIE);
        BlackBallWar.gI().dropBlackBall(this);
    }

    // --------------------------------------------------------------------------
    public void setClanMember() {
        if (this.clanMember != null) {
            this.clanMember.powerPoint = this.nPoint.power;
            this.clanMember.head = this.getHead();
            this.clanMember.body = this.getBody();
            this.clanMember.leg = this.getLeg();
        }
    }

    public boolean isAdmin() {
        return this.session.isAdmin;
    }

    public void setJustRevivaled() {
        this.justRevived = true;
        this.lastTimeRevived = System.currentTimeMillis();
    }

    public void preparedToDispose() {

    }

    public void dispose() {
        if (pet != null) {
            pet.dispose();
            pet = null;
        }
        if (newpet != null) {
            newpet.dispose();
            newpet = null;
        }

        if (mapBlackBall != null) {
            mapBlackBall.clear();
            mapBlackBall = null;
        }
        zone = null;
        mapBeforeCapsule = null;
        //
        if (mapMaBu != null) {
            mapMaBu.clear();
            mapMaBu = null;
        }
        if (mapSatan != null) {
            mapSatan.clear();
            mapSatan = null;
        }
        if (mapDiaNguc != null) {
            mapDiaNguc.clear();
            mapDiaNguc = null;
        }
        if (mapMabu13h != null) {
            mapMabu13h.clear();
            mapMabu13h = null;
        }
        if (mapVodai != null) {
            mapVodai.clear();
            mapVodai = null;
        }
        //
        if (billEgg != null) {
            billEgg.dispose();
            billEgg = null;
        }
        if (ngokhongEgg != null) {
            ngokhongEgg.dispose();
            ngokhongEgg = null;
        }
        if (gokuEgg != null) {
            gokuEgg.dispose();
            gokuEgg = null;
        }
        if (thienThanEgg != null) {
            thienThanEgg.dispose();
            thienThanEgg = null;
        }
        if (hacAmEgg != null) {
            hacAmEgg.dispose();
            hacAmEgg = null;
        }
        zone = null;
        mapBeforeCapsule = null;
        if (mapCapsule != null) {
            mapCapsule.clear();
            mapCapsule = null;
        }
        if (mobMe != null) {
            mobMe.dispose();
            mobMe = null;
        }
        location = null;
        if (setClothes != null) {
            setClothes.dispose();
            setClothes = null;
        }
        if (effectSkill != null) {
            effectSkill.dispose();
            effectSkill = null;
        }
        if (mabuEgg != null) {
            mabuEgg.dispose();
            mabuEgg = null;
        }
        if (skillSpecial != null) {
            skillSpecial.dispose();
            skillSpecial = null;
        }
        if (playerTask != null) {
            playerTask.dispose();
            playerTask = null;
        }
        if (itemTime != null) {
            itemTime.dispose();
            itemTime = null;
        }
        if (fusion != null) {
            fusion.dispose();
            fusion = null;
        }
        if (magicTree != null) {
            magicTree.dispose();
            magicTree = null;
        }
        if (playerIntrinsic != null) {
            playerIntrinsic.dispose();
            playerIntrinsic = null;
        }
        if (inventory != null) {
            inventory.dispose();
            inventory = null;
        }
        if (playerSkill != null) {
            playerSkill.dispose();
            playerSkill = null;
        }
        if (combineNew != null) {
            combineNew.dispose();
            combineNew = null;
        }
        if (iDMark != null) {
            iDMark.dispose();
            iDMark = null;
        }
        if (charms != null) {
            charms.dispose();
            charms = null;
        }
        if (effectSkin != null) {
            effectSkin.dispose();
            effectSkin = null;
        }
        if (gift != null) {
            gift.dispose();
            gift = null;
        }
        if (nPoint != null) {
            nPoint.dispose();
            nPoint = null;
        }
        if (rewardBlackBall != null) {
            rewardBlackBall.dispose();
            rewardBlackBall = null;
        }
        if (effectFlagBag != null) {
            effectFlagBag.dispose();
            effectFlagBag = null;
        }
        if (pvp != null) {
            pvp.dispose();
            pvp = null;
        }
        effectFlagBag = null;
        clan = null;
        clanMember = null;
        friends = null;
        enemies = null;
        session = null;
        name = null;
    }

    public double percentGold(int type) {
        try {
            if (type == 0) {
                double percent = ((double) this.goldNormar / ChonAiDay.gI().goldNormar) * 100;
                return percent > 0 ? percent : 0;
            } else if (type == 1) {
                double percent = ((double) this.goldVIP / ChonAiDay.gI().goldVip) * 100;
                return percent > 0 ? percent : 0;
            }
        } catch (ArithmeticException e) {
            return 0;
        }
        return 0;
    }
}
