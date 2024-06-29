package red.team.player;

import red.card.Card;
import red.card.OptionCard;
import red.consts.ConstPlayer;
import red.consts.ConstRatio;
import red.team.intrinsic.Intrinsic;
import red.team.item.Item;
import red.team.mob.Mob;
import red.team.skill.Skill;
import red.team.server.Manager;
import red.services.EffectSkillService;
import red.services.InventoryServiceNew;
import red.services.ItemService;
import red.services.MapService;
import red.services.PlayerService;
import red.services.Service;
import red.services.TaskService;
import red.utils.Logger;
import red.utils.SkillUtil;
import red.utils.Util;

import java.util.ArrayList;
import java.util.List;

public class NPoint {

    public static final byte MAX_LIMIT = 9;

    private Player player;

    public NPoint(Player player) {
        this.player = player;
        this.tlHp = new ArrayList<>();
        this.tlMp = new ArrayList<>();
        this.tlDef = new ArrayList<>();
        this.tlDame = new ArrayList<>();
        this.tlDameAttMob = new ArrayList<>();
        this.tlSDDep = new ArrayList<>();
        this.tlTNSM = new ArrayList<>();
        this.tlDameCrit = new ArrayList<>();

    }

    public boolean isCrit;
    public boolean isCrit100;

    private Intrinsic intrinsic;
    private int percentDameIntrinsic;
    public int dameAfter;

    /*-----------------------Chỉ số cơ bản------------------------------------*/
    public byte numAttack;
    public short stamina, maxStamina;

    public byte limitPower;
    public long power;
    public long tiemNang;

    public int hp, hpMax, hpg;
    public int mp, mpMax, mpg;
    public int dame, dameg;
    public int def, defg;
    public int crit, critg;
    public byte speed;

    public boolean teleport;

    public boolean khangTDHS;

    public boolean isThoDaiCa;
    public boolean isDrabura;
    public boolean isMabu;
    public boolean isThieuDot;
    public boolean isFrost;
    public boolean isSieuThan;
    public boolean isBuiBui;
    public boolean isSexy;
    public boolean isYacon;
    public boolean isXinBaTo;
    public boolean isSoiDaiCa;

    public boolean isZomKing;

    public boolean isPhuThuy;

    public boolean isNoel;

    public boolean isSSJ4;
    public boolean isNhatThan;
    public boolean isNguyetThan;
    public boolean isTayDam;
    /**
     * Chỉ số cộng thêm
     */
    public int hpAdd, mpAdd, dameAdd, defAdd, critAdd, hpHoiAdd, mpHoiAdd;

    /**
     * //+#% sức đánh chí mạng
     */
    public List<Integer> tlDameCrit;

    /**
     * Tỉ lệ hp, mp cộng thêm
     */
    public List<Integer> tlHp, tlMp;

    /**
     * Tỉ lệ giáp cộng thêm
     */
    public List<Integer> tlDef;

    /**
     * Tỉ lệ sức đánh/ sức đánh khi đánh quái
     */
    public List<Integer> tlDame, tlDameAttMob;

    /**
     * Lượng hp, mp hồi mỗi 30s, mp hồi cho người khác
     */
    public int hpHoi, mpHoi, mpHoiCute;

    /**
     * Tỉ lệ hp, mp hồi cộng thêm
     */
    public short tlHpHoi, tlMpHoi;

    /**
     * Tỉ lệ hp, mp hồi bản thân và đồng đội cộng thêm
     */
    public short tlHpHoiBanThanVaDongDoi, tlMpHoiBanThanVaDongDoi;

    /**
     * Tỉ lệ hút hp, mp khi đánh, hp khi đánh quái
     */
    public short tlHutHp, tlHutMp, tlHutHpMob;

    /**
     * Tỉ lệ hút hp, mp xung quanh mỗi 5s
     */
    public short tlHutHpMpXQ;

    /**
     * Tỉ lệ phản sát thương
     */
    public short tlPST;
    public short tlXuyenGiap = 0;
    public short tlMayMan = 0;
    /**
     * Tỉ lệ tiềm năng sức mạnh
     */
    public List<Integer> tlTNSM;

    /**
     * Tỉ lệ vàng cộng thêm
     */
    public short tlGold;

    /**
     * Tỉ lệ né đòn
     */
    public short tlNeDon = 1;
    public short tlChinhXac = 1;
    public short tlSTDam = 0;
    /**
     * Tỉ lệ sức đánh đẹp cộng thêm cho bản thân và người xung quanh
     */
    public List<Integer> tlSDDep;
    public short tlDameSexy = 0;
    public short tlDameHorny = 0;

    /**
     * Tỉ lệ giảm sức đánh
     */
    public short tlSubSD;

    public int voHieuChuong;

    /*------------------------Effect skin-------------------------------------*/
    public Item trainArmor;
    public boolean wornTrainArmor;
    public boolean wearingTrainArmor;

    public boolean wearingVoHinh;
    public boolean isKhongLanh;

    public short tlHpGiamODo;
    public short tlHpGiamDiaNguc;

    public short test;

    /*-------------------------------------------------------------------------*/
    /**
     * Tính toán mọi chỉ số sau khi có thay đổi
     */
    public void calPoint() {
        if (this.player.pet != null) {
            this.player.pet.nPoint.setPointWhenWearClothes();
        }
        this.setPointWhenWearClothes();
    }

    private void setPointWhenWearClothes() {
        resetPoint();
        if (this.player.rewardBlackBall.timeOutOfDateReward[1] > System.currentTimeMillis()) {
            tlHutMp += RewardBlackBall.R2S_1;
        }
        if (this.player.rewardBlackBall.timeOutOfDateReward[3] > System.currentTimeMillis()) {
            tlDameAttMob.add(RewardBlackBall.R4S_2);
        }
        if (this.player.rewardBlackBall.timeOutOfDateReward[4] > System.currentTimeMillis()) {
            tlPST += RewardBlackBall.R5S_1;
        }
        if (this.player.rewardBlackBall.timeOutOfDateReward[5] > System.currentTimeMillis()) {
            tlPST += RewardBlackBall.R6S_1;
            tlNeDon += RewardBlackBall.R6S_2;
        }
        if (this.player.rewardBlackBall.timeOutOfDateReward[6] > System.currentTimeMillis()) {
            tlHpHoi += RewardBlackBall.R7S_1;
            tlHutHp += RewardBlackBall.R7S_2;
        }
        if (this.player.capChuyenSinh > 0 && this.player.capChuyenSinh < 11) {
            tlDame.add(2 * this.player.capChuyenSinh);
            tlHp.add(2 * this.player.capChuyenSinh);
            tlMp.add(2 * this.player.capChuyenSinh);
        }
        if (this.player.setClothes.TinhAn == 5) {
            tlDame.add(60);
        }
        if (this.player.setClothes.thienXinHang == 5) {
            tlDame.add(20);
        }
        //

        ////
        if (this.player.isPl() || this.player.isPet) {
            if (this.player.setClothes.setGod()) {
                tlDame.add(5);
                tlHp.add(5);
                tlMp.add(5);
                critAdd += 5;
            }
            if (this.player.setClothes.setHuyDiet()) {
                tlDame.add(10);
                tlHp.add(10);
                tlMp.add(10);
                tlDameCrit.add(10);
            }
//            if (this.player.setClothes.setThienSu()) {
//                tlDame.add(15);
//                tlHp.add(15);
//                tlMp.add(15);
//                tlNeDon += 33;
//            }
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
            Item itemAo = this.player.inventory.itemsBody.get(0);
            Item itemQuan = this.player.inventory.itemsBody.get(1);
            Item itemGang = this.player.inventory.itemsBody.get(2);
            Item itemGiay = this.player.inventory.itemsBody.get(3);
            Item itemNhan = this.player.inventory.itemsBody.get(4);
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
                tlDame.add(10);
                tlHp.add(10);
                tlMp.add(10);
            } else if (optionLevelAo != null && optionLevelQuan != null && optionLevelGang != null
                    && optionLevelGiay != null && optionLevelNhan != null
                    && levelAo >= 7 && levelQuan >= 7 && levelGang >= 7 && levelGiay >= 7 && levelNhan >= 7) {
                tlDame.add(8);
                tlHp.add(8);
                tlMp.add(8);
            } else if (optionLevelAo != null && optionLevelQuan != null && optionLevelGang != null
                    && optionLevelGiay != null && optionLevelNhan != null
                    && levelAo >= 6 && levelQuan >= 6 && levelGang >= 6 && levelGiay >= 6 && levelNhan >= 6) {
                tlDame.add(6);
                tlHp.add(6);
                tlMp.add(6);
            } else if (optionLevelAo != null && optionLevelQuan != null && optionLevelGang != null
                    && optionLevelGiay != null && optionLevelNhan != null
                    && levelAo >= 5 && levelQuan >= 5 && levelGang >= 5 && levelGiay >= 5 && levelNhan >= 5) {
                tlDame.add(4);
                tlHp.add(4);
                tlMp.add(4);
            } else if (optionLevelAo != null && optionLevelQuan != null && optionLevelGang != null
                    && optionLevelGiay != null && optionLevelNhan != null
                    && levelAo >= 4 && levelQuan >= 4 && levelGang >= 4 && levelGiay >= 4 && levelNhan >= 4) {
                tlDame.add(2);
                tlHp.add(2);
                tlMp.add(2);
            }
        }
        //////
        this.player.setClothes.worldcup = 0;
        for (Item item : this.player.inventory.itemsBody) {
            if (item.isNotNullItem()) {
                switch (item.template.id) {
                    case 966:
                    case 982:
                    case 983:
                    case 883:
                    case 904:
                        player.setClothes.worldcup++;
                }
                if (item.template.id >= 592 && item.template.id <= 594) {
                    teleport = true;
                }
                Card card = player.Cards.stream().filter(r -> r != null && r.Used == 1).findFirst().orElse(null);
                if (card != null) {
                    for (OptionCard io : card.Options) {
                        if (io.active == card.Level || (card.Level == -1 && io.active == 0)) {
                            switch (io.id) {
                                case 0: // Tấn công +#
                                    this.dameAdd += io.param;
                                    break;
                                case 2: // HP, KI+#000
                                    this.hpAdd += io.param * 1000;
                                    this.mpAdd += io.param * 1000;
                                    break;
                                case 3:// fake
                                    this.voHieuChuong += io.param;
                                    break;
                                case 5: // +#% sức đánh chí mạng
                                    this.tlDameCrit.add(io.param);
                                    break;
                                case 6: // HP+#
                                    this.hpAdd += io.param;
                                    break;
                                case 7: // KI+#
                                    this.mpAdd += io.param;
                                    break;
                                case 8: // Hút #% HP, KI xung quanh mỗi 5 giây
                                    this.tlHutHpMpXQ += io.param;
                                    break;
                                case 14:
                                case 242: // Chí mạng+#%
                                    this.critAdd += io.param;
                                    break;
                                case 19: // Tấn công+#% khi đánh quái
                                    this.tlDameAttMob.add(io.param);
                                    break;
                                case 22: // HP+#K
                                    this.hpAdd += io.param * 1000;
                                    break;
                                case 23: // MP+#K
                                    this.mpAdd += io.param * 1000;
                                    break;
                                case 24:
                                    this.isBuiBui = true;
                                    break;
                                case 25:
                                    this.isYacon = true;
                                    break;
                                case 27:
                                case 243: // +# HP/30s
                                    this.hpHoiAdd += io.param;
                                    break;
                                case 28:
                                case 244: // +# KI/30s
                                    this.mpHoiAdd += io.param;
                                    break;
                                case 33: // dịch chuyển tức thời
                                    this.teleport = true;
                                    break;
                                case 47: // Giáp+#
                                    this.defAdd += io.param;
                                    break;
                                case 48: // HP/KI+#
                                    this.hpAdd += io.param;
                                    this.mpAdd += io.param;
                                    break;
                                case 49: // Tấn công+#%
                                case 50: // Sức đánh+#%
                                case 236:
                                    this.tlDame.add(io.param);
                                    break;
                                case 77: // HP+#%
                                case 237:
                                    this.tlHp.add(io.param);
                                    break;
                                case 80: // HP+#%/30s
                                    this.tlHpHoi += io.param;
                                    break;
                                case 81: // MP+#%/30s
                                    this.tlMpHoi += io.param;
                                    break;
                                case 88: // Cộng #% exp khi đánh quái
                                    this.tlTNSM.add(io.param);
                                    break;
                                case 94:
                                case 241: // Giáp #%
                                    this.tlDef.add(io.param);
                                    break;
                                case 95:
                                case 239: // Biến #% tấn công thành HP
                                    this.tlHutHp += io.param;
                                    break;
                                case 96:
                                case 240: // Biến #% tấn công thành MP
                                    this.tlHutMp += io.param;
                                    break;
                                case 97: // Phản #% sát thương
                                    this.tlPST += io.param;
                                    break;
                                case 98:
                                case 99:
                                case 245:
                                    this.tlXuyenGiap += io.param;
                                    break;

                                case 100: // +#% vàng từ quái
                                    this.tlGold += io.param;
                                    break;
                                case 101: // +#% TN,SM
                                    this.tlTNSM.add(io.param);
                                    break;
                                case 103: // KI +#%
                                case 238:
                                    this.tlMp.add(io.param);
                                    break;
                                case 104: // Biến #% tấn công quái thành HP
                                    this.tlHutHpMob += io.param;
                                    break;
                                case 108:
                                case 246: // Biến #% tấn công quái thành HP
                                    this.tlNeDon += io.param;
                                case 117:
                                    this.isSexy = true;
                                    this.tlDameSexy += io.param;
                                    break;
                                case 147: // +#% sức đánh
                                    this.tlDame.add(io.param);
                                    break;
                                case 156:
                                    this.isTayDam = true;
                                    break;
                                case 212:
                                    this.tlHutHpMpXQ += io.param;
                                    break;
                                case 213: // test
                                    this.isSieuThan = true;
                                    break;
                                case 231: // test
                                    this.isSoiDaiCa = true;
                                    break;
                                case 234:
                                    this.tlMayMan += io.param;
                                    break;
                                case 248:
                                    this.isZomKing = true;
                                    break;
                                case 249:
                                    this.isPhuThuy = true;
                                    break;
                                case 250:
                                    this.isNoel = true;
                                    break;
                                case 252:
                                    this.isNhatThan = true;
                                    break;
                                case 253:
                                    this.isNguyetThan = true;
                                    break;
                                case 254:
                                    this.isSSJ4 = true;
                                    break;

                            }
                        }
                    }
                }
                for (Item.ItemOption io : item.itemOptions) {
                    switch (io.optionTemplate.id) {
                        case 0: // Tấn công +#
                            this.dameAdd += io.param;
                            break;
                        case 2: // HP, KI+#000
                            this.hpAdd += io.param * 1000;
                            this.mpAdd += io.param * 1000;
                            break;
                        case 3:// fake
                            this.voHieuChuong += io.param;
                            break;
                        case 5: // +#% sức đánh chí mạng
                            this.tlDameCrit.add(io.param);
                            break;
                        case 6: // HP+#
                            this.hpAdd += io.param;
                            break;
                        case 7: // KI+#
                            this.mpAdd += io.param;
                            break;
                        case 8: // Hút #% HP, KI xung quanh mỗi 5 giây
                            this.tlHutHpMpXQ += io.param;
                            break;
                        case 14:
                        case 242: // Chí mạng+#%
                            this.critAdd += io.param;
                            break;
                        case 19: // Tấn công+#% khi đánh quái
                            this.tlDameAttMob.add(io.param);
                            break;
                        case 22: // HP+#K
                            this.hpAdd += io.param * 1000;
                            break;
                        case 23: // MP+#K
                            this.mpAdd += io.param * 1000;
                            break;
                        case 24:
                            this.isBuiBui = true;
                            break;
                        case 25:
                            this.isYacon = true;
                            break;
                        case 26: // +# HP/30s
                            this.isDrabura = true;
                            break;
                        case 27:
                        case 243: // +# HP/30s
                            this.hpHoiAdd += io.param;
                            break;
                        case 28:
                        case 244: // +# KI/30s
                            this.mpHoiAdd += io.param;
                            break;
                        case 29: // +# KI/30s
                            this.isMabu = true;
                            break;
                        case 33: // dịch chuyển tức thời
                            this.teleport = true;
                            break;
                        case 47: // Giáp+#
                            this.defAdd += io.param;
                            break;
                        case 48: // HP/KI+#
                            this.hpAdd += io.param;
                            this.mpAdd += io.param;
                            break;
                        case 49: // Tấn công+#%
                        case 50: // Sức đánh+#%
                        case 236:
                            this.tlDame.add(io.param);
                            break;
                        case 77: // HP+#%
                        case 237:
                            this.tlHp.add(io.param);
                            break;
                        case 80: // HP+#%/30s
                            this.tlHpHoi += io.param;
                            break;
                        case 81: // MP+#%/30s
                            this.tlMpHoi += io.param;
                            break;
                        case 88: // Cộng #% exp khi đánh quái
                            this.tlTNSM.add(io.param);
                            break;
                        case 94:
                        case 241: // Giáp #%
                            this.tlDef.add(io.param);
                            break;
                        case 95:
                        case 239: // Biến #% tấn công thành HP
                            this.tlHutHp += io.param;
                            break;
                        case 96:
                        case 240: // Biến #% tấn công thành MP
                            this.tlHutMp += io.param;
                            break;
                        case 97: // Phản #% sát thương
                            this.tlPST += io.param;
                            break;
                        case 98:
                        case 99:
                        case 245:
                            this.tlXuyenGiap += io.param;
                            break;
                        case 100: // +#% vàng từ quái
                            this.tlGold += io.param;
                            break;
                        case 101: // +#% TN,SM
                            this.tlTNSM.add(io.param);
                            break;
                        case 103: // KI +#%
                        case 238:
                            this.tlMp.add(io.param);
                            break;
                        case 104: // Biến #% tấn công quái thành HP
                            this.tlHutHpMob += io.param;
                            break;
                        case 105: // Vô hình khi không đánh quái và boss
                            this.wearingVoHinh = true;
                            break;
                        case 106: // Không ảnh hưởng bởi cái lạnh
                            this.isKhongLanh = true;
                            break;
                        case 108:
                        case 246: // #% Né đòn
                            this.tlNeDon += io.param;// đối nghịch
                            break;
                        case 109: // Hôi, giảm #% HP
                            this.tlHpGiamODo += io.param;
                            break;
                        case 111:
                            this.isXinBaTo = true;
                            break;
                        case 116: // Kháng thái dương hạ san
                            this.khangTDHS = true;
                            this.isThoDaiCa = true;
                            break;
                        case 117: // Đẹp +#% SĐ cho mình và người xung quanh
                            this.isSexy = true;
                            this.tlDameSexy += io.param;
                            break;
                        case 147: // +#% sức đánh
                            this.tlDame.add(io.param);
                            break;
                        case 75: // Giảm 50% sức đánh, HP, KI và +#% SM, TN, vàng từ quái
                            this.tlSubSD += 50;
                            this.tlTNSM.add(io.param);
                            this.tlGold += io.param;
                            break;
                        case 156:
                            this.isTayDam = true;
                            break;
                        case 162: // Cute hồi #% KI/s bản thân và xung quanh
                            this.mpHoiCute += io.param;
                            break;
                        case 167: // Cute hồi #% KI/s bản thân và xung quanh
                            this.isFrost = true;
                            break;
                        case 173: // Phục hồi #% HP và KI cho đồng đội
                            this.tlHpHoiBanThanVaDongDoi += io.param;
                            this.tlMpHoiBanThanVaDongDoi += io.param;
                            break;
                        case 211: // test
                            this.test += io.param;
                            break;
                        case 213: // test
                            this.isSieuThan = true;
                            break;
                        case 231: // test
                            this.isSoiDaiCa = true;
                            break;
                        case 234:
                            this.tlMayMan += io.param;
                            break;
                        case 248:
                            this.isZomKing = true;
                            break;
                        case 249:
                            this.isPhuThuy = true;
                            break;
                        case 250:
                            this.isNoel = true;
                            break;
                        case 252:
                            this.isNhatThan = true;
                            break;
                        case 253:
                            this.isNguyetThan = true;
                            break;
                        case 254:
                            this.isSSJ4 = true;
                            break;
                    }
                }
            }
        }
        if (this.player.isPl() && this.player.fusion.typeFusion == ConstPlayer.HOP_THE_PORATA2) {
            for (Item item : this.player.inventory.itemsBag) {
                if (item.isNotNullItem() && item.template.id == 921) {
                    for (Item.ItemOption io : item.itemOptions) {
                        switch (io.optionTemplate.id) {
                            case 14:
                            case 242: // Chí mạng+#%
                                this.critAdd += io.param;
                                break;
                            case 50: // Sức đánh+#%
                            case 236:
                                this.tlDame.add(io.param);
                                break;
                            case 77: // HP+#%
                            case 237:
                                this.tlHp.add(io.param);
                                break;
                            case 80: // HP+#%/30s
                                this.tlHpHoi += io.param;
                                break;
                            case 81: // MP+#%/30s
                                this.tlMpHoi += io.param;
                                break;
                            case 94:
                            case 241: // Giáp #%
                                this.tlDef.add(io.param);
                                break;
                            case 103: // KI +#%
                            case 238:
                                this.tlMp.add(io.param);
                                break;
                            case 108:
                            case 246: // #% Né đòn
                                this.tlNeDon += io.param;
                                break;

                        }
                    }
                    break;
                }
            }
        } else if (this.player.isPl() && this.player.fusion.typeFusion == ConstPlayer.HOP_THE_PORATA3) {
            for (Item item : this.player.inventory.itemsBag) {
                if (item.isNotNullItem() && item.template.id == 2074) {
                    for (Item.ItemOption io : item.itemOptions) {
                        switch (io.optionTemplate.id) {
                            case 14:
                            case 242: // Chí mạng+#%
                                this.critAdd += io.param;
                                break;
                            case 50: // Sức đánh+#%
                            case 236:
                                this.tlDame.add(io.param);
                                break;
                            case 77: // HP+#%
                            case 237:
                                this.tlHp.add(io.param);
                                break;
                            case 80: // HP+#%/30s
                                this.tlHpHoi += io.param;
                                break;
                            case 81: // MP+#%/30s
                                this.tlMpHoi += io.param;
                                break;
                            case 94:
                            case 241: // Giáp #%
                                this.tlDef.add(io.param);
                                break;
                            case 103: // KI +#%
                            case 238:
                                this.tlMp.add(io.param);
                                break;
                            case 108:
                            case 246: // #% Né đòn
                                this.tlNeDon += io.param;
                                break;
                            case 10: // #% Né đòn
                                this.tlChinhXac += io.param / 5;
                                break;
                        }
                    }
                    break;
                }
            }
        } else if (this.player.isPl() && this.player.fusion.typeFusion == ConstPlayer.HOP_THE_PORATA4) {
            for (Item item : this.player.inventory.itemsBag) {
                if (item.isNotNullItem() && item.template.id == 2075) {
                    for (Item.ItemOption io : item.itemOptions) {
                        switch (io.optionTemplate.id) {
                            case 14:
                            case 242: // Chí mạng+#%
                                this.critAdd += io.param;
                                break;
                            case 50: // Sức đánh+#%
                            case 236:
                                this.tlDame.add(io.param);
                                break;
                            case 77: // HP+#%
                            case 237:
                                this.tlHp.add(io.param);
                                break;
                            case 80: // HP+#%/30s
                                this.tlHpHoi += io.param;
                                break;
                            case 81: // MP+#%/30s
                                this.tlMpHoi += io.param;
                                break;
                            case 94:
                            case 241: // Giáp #%
                                this.tlDef.add(io.param);
                                break;
                            case 103: // KI +#%
                            case 238:
                                this.tlMp.add(io.param);
                                break;
                            case 108:
                            case 246: // #% Né đòn
                                this.tlNeDon += io.param;
                                break;
                            case 10: // #% Né đòn
                                this.tlChinhXac += io.param / 5;
                                break;
                        }
                    }
                    break;
                }
            }
        }

        setDameTrainArmor();
        setBasePoint();
    }

    private void setDameTrainArmor() {
        if (!this.player.isPet && !this.player.isBoss) {
            if (this.player.inventory.itemsBody.size() < 7) {
                return;
            }
            try {
                Item gtl = this.player.inventory.itemsBody.get(6);
                if (gtl.isNotNullItem()) {
                    this.wearingTrainArmor = true;
                    this.wornTrainArmor = true;
                    this.player.inventory.trainArmor = gtl;
                    this.tlSubSD += ItemService.gI().getPercentTrainArmor(gtl);
                } else {
                    if (this.wornTrainArmor) {
                        this.wearingTrainArmor = false;
                        for (Item.ItemOption io : this.player.inventory.trainArmor.itemOptions) {
                            if (io.optionTemplate.id == 9 && io.param > 0) {
                                this.tlDame
                                        .add(ItemService.gI().getPercentTrainArmor(this.player.inventory.trainArmor));
                                break;
                            }
                        }
                    }
                }
            } catch (Exception e) {
                Logger.error("Lỗi get giáp tập luyện " + this.player.name + "\n");
            }
        }
    }

    public void setBasePoint() {

        setHpMax();
        setHp();
        setMpMax();
        setMp();
        setDame();
        setDef();
        setTLHPGiam();
        setCrit();
        setXuyenGiap();
        setHpHoi();
        setMpHoi();
        setNeDon();
        setChinhXac();
        setDameCrit();
        setSpeed();
        setMayMan();
        setTlHutHp();
        setTlHutMp();

    }

    private void setTlHutHp() {
        if (!this.player.isPet && this.player.itemTime.isEatBanhTho
                || this.player.isPet && ((Pet) this.player).master.itemTime.isEatBanhTho) {
            this.tlHutHp += 10;
        }
    }

    private void setTlHutMp() {
        if (!this.player.isPet && this.player.itemTime.isEatBanhTho
                || this.player.isPet && ((Pet) this.player).master.itemTime.isEatBanhTho) {
            this.tlHutMp += 10;
        }
    }

    private void setTLHPGiam() {
        if (this.player.zone != null && (this.player.isPl() || this.player.isPet) && !this.player.isDie()
                && MapService.gI().isMapDiaNguc(this.player.zone.map.mapId)) {
            this.isThieuDot = true;
            this.tlHpGiamDiaNguc = 20;
        } else {
            this.tlHpGiamDiaNguc = 0;
            this.isThieuDot = false;
        }
    }

    private void setXuyenGiap() {

        if (this.tlXuyenGiap > 66) {
            this.tlXuyenGiap = 66;
        }
        if (!this.player.isPet && this.player.itemTime.isEatNhoTim
                || this.player.isPet && ((Pet) this.player).master.itemTime.isEatNhoTim) {
            this.tlXuyenGiap += 20;
        }
        if (this.tlXuyenGiap < 0) {
            this.tlXuyenGiap = 0;
        }
    }

    private void setMayMan() {

        if (this.tlMayMan > 20) {
            this.tlMayMan = 20;
        }

        if (this.tlMayMan < 0) {
            this.tlMayMan = 0;
        }
    }

    private void setSpeed() {
        if (this.player.effectSkill.isLamCham) {
            this.speed = 1;
        } else if (this.player.effectSkill.isDanhHoi) {
            this.speed *= 2;
        } else {
            this.speed = 8;
        }

    }

    private void setNeDon() {
        if (this.player.setClothes.NguyetAn == 5) {
            this.tlNeDon = 66;
        }
        if (this.tlNeDon > 66) {
            this.tlNeDon = 66;
        }
        if (this.tlNeDon < 1) {
            this.tlNeDon = 1;
        }

    }

    private void setChinhXac() {
        if (this.tlChinhXac > 33) {
            this.tlChinhXac = 33;
        }
        if (this.tlChinhXac < 1) {
            this.tlChinhXac = 1;
        }

    }

    private void setHpHoi() {
        this.hpHoi = this.hpMax / 100;
        this.hpHoi += this.hpHoiAdd;
        this.hpHoi += ((long) this.hpMax * this.tlHpHoi / 100);
        this.hpHoi += ((long) this.hpMax * this.tlHpHoiBanThanVaDongDoi / 100);
    }

    private void setMpHoi() {
        this.mpHoi = this.mpMax / 100;
        this.mpHoi += this.mpHoiAdd;
        this.mpHoi += ((long) this.mpMax * this.tlMpHoi / 100);
        this.mpHoi += ((long) this.mpMax * this.tlMpHoiBanThanVaDongDoi / 100);
    }

    private void setHpMax() {
        this.hpMax = this.hpg;
        this.hpMax += this.hpAdd;
        // đồ
        for (Integer tl : this.tlHp) {
            this.hpMax += ((long) this.hpMax * tl / 100);
        }
        // set nappa
        if (this.player.setClothes.nappa == 5) {
            this.hpMax += ((long) this.hpMax * 8 / 10);
        }
        // set worldcup
        if (this.player.setClothes.worldcup == 2) {
            this.hpMax += ((long) this.hpMax * 5 / 100);
        }
        if (this.player.setClothes.NguyetAn == 5) {
            this.hpMax += ((long) this.hpMax * 12 / 10);
        }
        // ngọc rồng đen 1 sao
        if (this.player.rewardBlackBall.timeOutOfDateReward[0] > System.currentTimeMillis()) {
            this.hpMax += ((long) this.hpMax * RewardBlackBall.R1S_1 / 100);
        }
        // khỉ
        if (this.player.effectSkill.isMonkey) {
            if (!this.player.isPet || (this.player.isPet
                    && ((Pet) this.player).status != Pet.FUSION)) {
                int percent = SkillUtil.getPercentHpMonkey(player.effectSkill.levelMonkey);
                this.hpMax += ((long) this.hpMax * percent / 100);
            }
        }

        if (!this.player.isPet && this.player.itemTime.isEatDuoiKhi && !this.player.effectSkill.isMonkey) {
            this.hpMax += ((long) this.hpMax);
        }

        // pet mabư
        if (this.player.isPet && ((Pet) this.player).typePet == 1
                && ((Pet) this.player).master.fusion.typeFusion == ConstPlayer.HOP_THE_PORATA) {
            this.hpMax += ((long) this.hpMax * 4 / 100);
        }
        if (this.player.isPet && ((Pet) this.player).typePet == 1
                && ((Pet) this.player).master.fusion.typeFusion == ConstPlayer.HOP_THE_PORATA2) {
            this.hpMax += ((long) this.hpMax * 6 / 100);
        }
        if (this.player.isPet && ((Pet) this.player).typePet == 1
                && ((Pet) this.player).master.fusion.typeFusion == ConstPlayer.HOP_THE_PORATA3) {
            this.hpMax += ((long) this.hpMax * 8 / 100);
        }
        if (this.player.isPet && ((Pet) this.player).typePet == 1
                && ((Pet) this.player).master.fusion.typeFusion == ConstPlayer.HOP_THE_PORATA4) {
            this.hpMax += ((long) this.hpMax * 10 / 100);
        }
        // pet berus
        if (this.player.isPet && ((Pet) this.player).typePet == 2// chi so lam sao bac tu cho dj
                && ((Pet) this.player).master.fusion.typeFusion == ConstPlayer.HOP_THE_PORATA) {
            this.hpMax += ((long) this.hpMax * 4 / 100);// chi so hp
        }
        if (this.player.isPet && ((Pet) this.player).typePet == 2// chi so lam sao bac tu cho dj
                && ((Pet) this.player).master.fusion.typeFusion == ConstPlayer.HOP_THE_PORATA2) {
            this.hpMax += ((long) this.hpMax * 6 / 100);// chi so hp
        }
        if (this.player.isPet && ((Pet) this.player).typePet == 2// chi so lam sao bac tu cho dj
                && ((Pet) this.player).master.fusion.typeFusion == ConstPlayer.HOP_THE_PORATA3) {
            this.hpMax += ((long) this.hpMax * 8 / 100);// chi so hp
        }
        if (this.player.isPet && ((Pet) this.player).typePet == 2// chi so lam sao bac tu cho dj
                && ((Pet) this.player).master.fusion.typeFusion == ConstPlayer.HOP_THE_PORATA4) {
            this.hpMax += ((long) this.hpMax * 10 / 100);// chi so hp
        }
        // goku
        if (this.player.isPet && ((Pet) this.player).typePet == 3
                && ((Pet) this.player).master.fusion.typeFusion == ConstPlayer.HOP_THE_PORATA) {
            this.hpMax += ((long) this.hpMax * 4 / 100);
        }
        if (this.player.isPet && ((Pet) this.player).typePet == 3
                && ((Pet) this.player).master.fusion.typeFusion == ConstPlayer.HOP_THE_PORATA2) {
            this.hpMax += ((long) this.hpMax * 6 / 100);
        }
        if (this.player.isPet && ((Pet) this.player).typePet == 3
                && ((Pet) this.player).master.fusion.typeFusion == ConstPlayer.HOP_THE_PORATA3) {
            this.hpMax += ((long) this.hpMax * 8 / 100);
        }
        if (this.player.isPet && ((Pet) this.player).typePet == 3
                && ((Pet) this.player).master.fusion.typeFusion == ConstPlayer.HOP_THE_PORATA4) {
            this.hpMax += ((long) this.hpMax * 10 / 100);
        }
        // pet ngokhong
        if (this.player.isPet && ((Pet) this.player).typePet == 4
                && ((Pet) this.player).master.fusion.typeFusion == ConstPlayer.HOP_THE_PORATA) {
            this.hpMax += ((long) this.hpMax * 4 / 100);
        }
        if (this.player.isPet && ((Pet) this.player).typePet == 4
                && ((Pet) this.player).master.fusion.typeFusion == ConstPlayer.HOP_THE_PORATA2) {
            this.hpMax += ((long) this.hpMax * 6 / 100);
        }
        if (this.player.isPet && ((Pet) this.player).typePet == 4
                && ((Pet) this.player).master.fusion.typeFusion == ConstPlayer.HOP_THE_PORATA3) {
            this.hpMax += ((long) this.hpMax * 8 / 100);
        }
        if (this.player.isPet && ((Pet) this.player).typePet == 4
                && ((Pet) this.player).master.fusion.typeFusion == ConstPlayer.HOP_THE_PORATA4) {
            this.hpMax += ((long) this.hpMax * 10 / 100);
        }
        // pet ngokhong
        if (this.player.isPet && ((Pet) this.player).typePet == 4
                && ((Pet) this.player).master.fusion.typeFusion == ConstPlayer.HOP_THE_PORATA) {
            this.hpMax += ((long) this.hpMax * 4 / 100);
        }
        if (this.player.isPet && ((Pet) this.player).typePet == 4
                && ((Pet) this.player).master.fusion.typeFusion == ConstPlayer.HOP_THE_PORATA2) {
            this.hpMax += ((long) this.hpMax * 6 / 100);
        }
        if (this.player.isPet && ((Pet) this.player).typePet == 4
                && ((Pet) this.player).master.fusion.typeFusion == ConstPlayer.HOP_THE_PORATA3) {
            this.hpMax += ((long) this.hpMax * 8 / 100);
        }
        if (this.player.isPet && ((Pet) this.player).typePet == 4
                && ((Pet) this.player).master.fusion.typeFusion == ConstPlayer.HOP_THE_PORATA4) {
            this.hpMax += ((long) this.hpMax * 10 / 100);
        }
        // pet ngokhong
        if (this.player.isPet && ((Pet) this.player).typePet == 5
                && ((Pet) this.player).master.fusion.typeFusion == ConstPlayer.HOP_THE_PORATA) {
            this.mpMax += ((long) this.mpMax * 5 / 100);
        }
        if (this.player.isPet && ((Pet) this.player).typePet == 5
                && ((Pet) this.player).master.fusion.typeFusion == ConstPlayer.HOP_THE_PORATA2) {
            this.mpMax += ((long) this.mpMax * 7 / 100);
        }
        if (this.player.isPet && ((Pet) this.player).typePet == 5
                && ((Pet) this.player).master.fusion.typeFusion == ConstPlayer.HOP_THE_PORATA3) {
            this.mpMax += ((long) this.mpMax * 9 / 100);
        }
        if (this.player.isPet && ((Pet) this.player).typePet == 5
                && ((Pet) this.player).master.fusion.typeFusion == ConstPlayer.HOP_THE_PORATA4) {
            this.mpMax += ((long) this.mpMax * 12 / 100);
        }
        // pet ngokhong
        if (this.player.isPet && ((Pet) this.player).typePet == 6
                && ((Pet) this.player).master.fusion.typeFusion == ConstPlayer.HOP_THE_PORATA) {
            this.mpMax += ((long) this.mpMax * 5 / 100);
        }
        if (this.player.isPet && ((Pet) this.player).typePet == 6
                && ((Pet) this.player).master.fusion.typeFusion == ConstPlayer.HOP_THE_PORATA2) {
            this.mpMax += ((long) this.mpMax * 7 / 100);
        }
        if (this.player.isPet && ((Pet) this.player).typePet == 6
                && ((Pet) this.player).master.fusion.typeFusion == ConstPlayer.HOP_THE_PORATA3) {
            this.mpMax += ((long) this.mpMax * 9 / 100);
        }
        if (this.player.isPet && ((Pet) this.player).typePet == 6
                && ((Pet) this.player).master.fusion.typeFusion == ConstPlayer.HOP_THE_PORATA4) {
            this.mpMax += ((long) this.mpMax * 12 / 100);
        }
        // pet ngokhong
        if (this.player.isPet && ((Pet) this.player).typePet == 7
                && ((Pet) this.player).master.fusion.typeFusion == ConstPlayer.HOP_THE_PORATA) {
            this.mpMax += ((long) this.mpMax * 5 / 100);
        }
        if (this.player.isPet && ((Pet) this.player).typePet == 7
                && ((Pet) this.player).master.fusion.typeFusion == ConstPlayer.HOP_THE_PORATA2) {
            this.mpMax += ((long) this.mpMax * 7 / 100);
        }
        if (this.player.isPet && ((Pet) this.player).typePet == 7
                && ((Pet) this.player).master.fusion.typeFusion == ConstPlayer.HOP_THE_PORATA3) {
            this.mpMax += ((long) this.mpMax * 9 / 100);
        }
        if (this.player.isPet && ((Pet) this.player).typePet == 7
                && ((Pet) this.player).master.fusion.typeFusion == ConstPlayer.HOP_THE_PORATA4) {
            this.mpMax += ((long) this.mpMax * 12 / 100);
        }
        // phù
        if (this.player.zone != null && MapService.gI().isMapBlackBallWar(this.player.zone.map.mapId)) {
            this.hpMax *= this.player.effectSkin.xHPKI;
        }
        // +hp đệ
        if (this.player.fusion.typeFusion != ConstPlayer.NON_FUSION) {
            this.hpMax += this.player.pet.nPoint.hpMax;
        }
        if (!this.player.isPet && this.player.itemTime.isEatHongDao
                || this.player.isPet && ((Pet) this.player).master.itemTime.isEatHongDao) {
            this.hpMax += ((long) this.hpMax * 10 / 100);
        }
        // huýt sáo
        if (!this.player.isPet
                || (this.player.isPet
                        && ((Pet) this.player).status != Pet.FUSION)) {
            if (this.player.effectSkill.tiLeHPHuytSao != 0) {
                this.hpMax += ((long) this.hpMax * this.player.effectSkill.tiLeHPHuytSao / 100L);

            }
        }
//        if (!this.player.isPet && this.player.itemTime.isEatUltra) {
//            this.hpMax += ((long) this.hpMax * (this.player.lvlThienSu / 10) / 100);
//        }
        // bổ huyết
        if (this.player.itemTime != null && this.player.itemTime.isUseBoHuyet) {
            this.hpMax += this.hpMax / 2;

        } // item sieu cawsp
        if (this.player.itemTime != null && this.player.itemTime.isUseBoHuyetSC) {
            this.hpMax += this.hpMax;
        }
        if (this.player.zone != null && MapService.gI().isMapCold(this.player.zone.map)
                && !this.isKhongLanh && !this.player.isBoss) {
            this.hpMax /= 2;
        }
        // mèo mun
        if (this.player.effectFlagBag.useMeoMun) {
            this.hpMax += ((long) this.hpMax * 15 / 100);
        }
        if (this.player.itemTime != null && this.player.itemTime.istrbhp) {
            this.hpMax += (this.hpMax * 15 / 100);
        }
        if (this.player.effectSkill.isHoaLanh && !this.isKhongLanh) {
            this.hpMax /= 2;
        }
        if (this.hpMax > 2000000000) {
            this.hpMax = 2000000000;
        }
    }

    // (hp sư phụ + hp đệ tử ) + 15%
    // (hp sư phụ + 15% +hp đệ tử)
    private void setHp() {
        if (this.hp > this.hpMax) {
            this.hp = this.hpMax;
        }
        if (this.hp > 2000000000) {
            this.hp = 2000000000;
        }
    }

    private void setMpMax() {
        this.mpMax = this.mpg;
        this.mpMax += this.mpAdd;
        // đồ
        for (Integer tl : this.tlMp) {
            this.mpMax += (this.mpMax * tl / 100);
        }
        if (this.player.setClothes.picolo == 5) {
            this.mpMax += ((long) this.mpMax);
        }
        if (!this.player.isPet && this.player.itemTime.isEatBanhTrung
                || this.player.isPet && ((Pet) this.player).master.itemTime.isEatBanhTrung) {
            this.mpMax += ((long) this.mpMax * 10 / 100);
        }
        // ngọc rồng đen 3 sao
        if (this.player.rewardBlackBall.timeOutOfDateReward[2] > System.currentTimeMillis()) {
            this.mpMax += (this.mpMax * RewardBlackBall.R3S_1 / 100);
        }
        // set worldcup
        if (this.player.setClothes.worldcup == 2) {
            this.mpMax += ((long) this.mpMax * 5 / 100);
        }
        if (this.player.setClothes.NguyetAn == 5) {
            this.mpMax += ((long) this.mpMax * 20 / 10);
        }
        // pet mabư
        if (this.player.isPet && ((Pet) this.player).typePet == 1
                && ((Pet) this.player).master.fusion.typeFusion == ConstPlayer.HOP_THE_PORATA) {
            this.mpMax += ((long) this.mpMax * 4 / 100);
        }
        if (this.player.isPet && ((Pet) this.player).typePet == 1
                && ((Pet) this.player).master.fusion.typeFusion == ConstPlayer.HOP_THE_PORATA2) {
            this.mpMax += ((long) this.mpMax * 6 / 100);
        }
        if (this.player.isPet && ((Pet) this.player).typePet == 1
                && ((Pet) this.player).master.fusion.typeFusion == ConstPlayer.HOP_THE_PORATA3) {
            this.mpMax += ((long) this.mpMax * 8 / 100);
        }
        if (this.player.isPet && ((Pet) this.player).typePet == 1
                && ((Pet) this.player).master.fusion.typeFusion == ConstPlayer.HOP_THE_PORATA4) {
            this.mpMax += ((long) this.mpMax * 10 / 100);
        }
        // pet berus
        if (this.player.isPet && ((Pet) this.player).typePet == 2// chi so lam sao bac tu cho dj
                && ((Pet) this.player).master.fusion.typeFusion == ConstPlayer.HOP_THE_PORATA) {
            this.mpMax += ((long) this.mpMax * 4 / 100);// chi so hp
        }
        if (this.player.isPet && ((Pet) this.player).typePet == 2// chi so lam sao bac tu cho dj
                && ((Pet) this.player).master.fusion.typeFusion == ConstPlayer.HOP_THE_PORATA2) {
            this.mpMax += ((long) this.mpMax * 6 / 100);// chi so hp
        }
        if (this.player.isPet && ((Pet) this.player).typePet == 2// chi so lam sao bac tu cho dj
                && ((Pet) this.player).master.fusion.typeFusion == ConstPlayer.HOP_THE_PORATA3) {
            this.mpMax += ((long) this.mpMax * 8 / 100);// chi so hp
        }
        if (this.player.isPet && ((Pet) this.player).typePet == 2// chi so lam sao bac tu cho dj
                && ((Pet) this.player).master.fusion.typeFusion == ConstPlayer.HOP_THE_PORATA4) {
            this.mpMax += ((long) this.mpMax * 10 / 100);// chi so hp
        }
        // goku
        if (this.player.isPet && ((Pet) this.player).typePet == 3
                && ((Pet) this.player).master.fusion.typeFusion == ConstPlayer.HOP_THE_PORATA) {
            this.mpMax += ((long) this.mpMax * 4 / 100);
        }
        if (this.player.isPet && ((Pet) this.player).typePet == 3
                && ((Pet) this.player).master.fusion.typeFusion == ConstPlayer.HOP_THE_PORATA2) {
            this.mpMax += ((long) this.mpMax * 6 / 100);
        }
        if (this.player.isPet && ((Pet) this.player).typePet == 3
                && ((Pet) this.player).master.fusion.typeFusion == ConstPlayer.HOP_THE_PORATA3) {
            this.mpMax += ((long) this.mpMax * 8 / 100);
        }
        if (this.player.isPet && ((Pet) this.player).typePet == 3
                && ((Pet) this.player).master.fusion.typeFusion == ConstPlayer.HOP_THE_PORATA4) {
            this.mpMax += ((long) this.mpMax * 10 / 100);
        }
        // pet ngokhong
        if (this.player.isPet && ((Pet) this.player).typePet == 4
                && ((Pet) this.player).master.fusion.typeFusion == ConstPlayer.HOP_THE_PORATA) {
            this.mpMax += ((long) this.mpMax * 4 / 100);
        }
        if (this.player.isPet && ((Pet) this.player).typePet == 4
                && ((Pet) this.player).master.fusion.typeFusion == ConstPlayer.HOP_THE_PORATA2) {
            this.mpMax += ((long) this.mpMax * 6 / 100);
        }
        if (this.player.isPet && ((Pet) this.player).typePet == 4
                && ((Pet) this.player).master.fusion.typeFusion == ConstPlayer.HOP_THE_PORATA3) {
            this.mpMax += ((long) this.mpMax * 8 / 100);
        }
        if (this.player.isPet && ((Pet) this.player).typePet == 4
                && ((Pet) this.player).master.fusion.typeFusion == ConstPlayer.HOP_THE_PORATA4) {
            this.mpMax += ((long) this.mpMax * 10 / 100);
        }

        // pet ngokhong
        if (this.player.isPet && ((Pet) this.player).typePet == 5
                && ((Pet) this.player).master.fusion.typeFusion == ConstPlayer.HOP_THE_PORATA) {
            this.mpMax += ((long) this.mpMax * 5 / 100);
        }
        if (this.player.isPet && ((Pet) this.player).typePet == 5
                && ((Pet) this.player).master.fusion.typeFusion == ConstPlayer.HOP_THE_PORATA2) {
            this.mpMax += ((long) this.mpMax * 7 / 100);
        }
        if (this.player.isPet && ((Pet) this.player).typePet == 5
                && ((Pet) this.player).master.fusion.typeFusion == ConstPlayer.HOP_THE_PORATA3) {
            this.mpMax += ((long) this.mpMax * 9 / 100);
        }
        if (this.player.isPet && ((Pet) this.player).typePet == 5
                && ((Pet) this.player).master.fusion.typeFusion == ConstPlayer.HOP_THE_PORATA4) {
            this.mpMax += ((long) this.mpMax * 12 / 100);
        }
        // pet ngokhong
        if (this.player.isPet && ((Pet) this.player).typePet == 6
                && ((Pet) this.player).master.fusion.typeFusion == ConstPlayer.HOP_THE_PORATA) {
            this.mpMax += ((long) this.mpMax * 5 / 100);
        }
        if (this.player.isPet && ((Pet) this.player).typePet == 6
                && ((Pet) this.player).master.fusion.typeFusion == ConstPlayer.HOP_THE_PORATA2) {
            this.mpMax += ((long) this.mpMax * 7 / 100);
        }
        if (this.player.isPet && ((Pet) this.player).typePet == 6
                && ((Pet) this.player).master.fusion.typeFusion == ConstPlayer.HOP_THE_PORATA3) {
            this.mpMax += ((long) this.mpMax * 9 / 100);
        }
        if (this.player.isPet && ((Pet) this.player).typePet == 6
                && ((Pet) this.player).master.fusion.typeFusion == ConstPlayer.HOP_THE_PORATA4) {
            this.mpMax += ((long) this.mpMax * 12 / 100);
        }
        // pet ngokhong
        if (this.player.isPet && ((Pet) this.player).typePet == 7
                && ((Pet) this.player).master.fusion.typeFusion == ConstPlayer.HOP_THE_PORATA) {
            this.mpMax += ((long) this.mpMax * 5 / 100);
        }
        if (this.player.isPet && ((Pet) this.player).typePet == 7
                && ((Pet) this.player).master.fusion.typeFusion == ConstPlayer.HOP_THE_PORATA2) {
            this.mpMax += ((long) this.mpMax * 7 / 100);
        }
        if (this.player.isPet && ((Pet) this.player).typePet == 7
                && ((Pet) this.player).master.fusion.typeFusion == ConstPlayer.HOP_THE_PORATA3) {
            this.mpMax += ((long) this.mpMax * 9 / 100);
        }
        if (this.player.isPet && ((Pet) this.player).typePet == 7
                && ((Pet) this.player).master.fusion.typeFusion == ConstPlayer.HOP_THE_PORATA4) {
            this.mpMax += ((long) this.mpMax * 12 / 100);
        }

        if (this.player.setClothes.worldcup == 2) {
            this.mpMax += ((long) this.mpMax * 5 / 100);
        }
        // hợp thể
        if (this.player.fusion.typeFusion != 0) {
            this.mpMax += this.player.pet.nPoint.mpMax;
        }
        // bổ khí
        if (this.player.itemTime != null && this.player.itemTime.isUseBoKhi) {
            this.mpMax += this.mpMax / 2;
        }
        if (this.player.itemTime != null && this.player.itemTime.isUseBoKhiSC) {
            this.mpMax += this.mpMax;

        }
//        if (!this.player.isPet && this.player.itemTime.isEatUltra) {
//            this.mpMax += ((long) this.mpMax * (this.player.lvlThienSu / 10) / 100);
//        }
        // phù
        if (this.player.zone != null && MapService.gI().isMapBlackBallWar(this.player.zone.map.mapId)) {
            this.mpMax *= this.player.effectSkin.xHPKI;
        }
        // xiên cá
        if (this.player.effectFlagBag.useXienCa) {
            this.mpMax += ((long) this.mpMax * 15 / 100);
        }
        if (this.player.itemTime != null && this.player.itemTime.istrbki) {
            this.mpMax += (this.mpMax * 15 / 100);
        }
        if (this.player.effectSkill.isHoaLanh && !this.isKhongLanh) {
            this.mpMax /= 2;
        }
        if (this.mpMax > 2000000000) {
            this.mpMax = 2000000000;
        }
    }

    private void setMp() {
        if (this.mp > this.mpMax) {
            this.mp = this.mpMax;
        }
    }

    private void setDame() {
        this.dame = this.dameg;
        this.dame += this.dameAdd;
        // đồ
        for (Integer tl : this.tlDame) {
            this.dame += ((long) this.dame * tl / 100);
        }
        for (Integer tl : this.tlSDDep) {
            this.dame += ((long) this.dame * tl / 100);
        }

        // pet mabư
        if (this.player.isPet && ((Pet) this.player).typePet == 1
                && ((Pet) this.player).master.fusion.typeFusion == ConstPlayer.HOP_THE_PORATA) {
            this.dame += ((long) this.dame * 4 / 100);
        }
        if (this.player.isPet && ((Pet) this.player).typePet == 1
                && ((Pet) this.player).master.fusion.typeFusion == ConstPlayer.HOP_THE_PORATA2) {
            this.dame += ((long) this.dame * 6 / 100);
        }
        if (this.player.isPet && ((Pet) this.player).typePet == 1
                && ((Pet) this.player).master.fusion.typeFusion == ConstPlayer.HOP_THE_PORATA3) {
            this.dame += ((long) this.dame * 8 / 100);
        }
        if (this.player.isPet && ((Pet) this.player).typePet == 1
                && ((Pet) this.player).master.fusion.typeFusion == ConstPlayer.HOP_THE_PORATA4) {
            this.dame += ((long) this.dame * 10 / 100);
        }
        // pet berus
        if (this.player.isPet && ((Pet) this.player).typePet == 2// chi so lam sao bac tu cho dj
                && ((Pet) this.player).master.fusion.typeFusion == ConstPlayer.HOP_THE_PORATA) {
            this.dame += ((long) this.dame * 4 / 100);// chi so hp
        }
        if (this.player.isPet && ((Pet) this.player).typePet == 2// chi so lam sao bac tu cho dj
                && ((Pet) this.player).master.fusion.typeFusion == ConstPlayer.HOP_THE_PORATA2) {
            this.dame += ((long) this.dame * 5 / 100);// chi so hp
        }
        if (this.player.isPet && ((Pet) this.player).typePet == 2// chi so lam sao bac tu cho dj
                && ((Pet) this.player).master.fusion.typeFusion == ConstPlayer.HOP_THE_PORATA3) {
            this.dame += ((long) this.dame * 8 / 100);// chi so hp
        }
        if (this.player.isPet && ((Pet) this.player).typePet == 2// chi so lam sao bac tu cho dj
                && ((Pet) this.player).master.fusion.typeFusion == ConstPlayer.HOP_THE_PORATA4) {
            this.dame += ((long) this.dame * 10 / 100);// chi so hp
        }
        // goku
        if (this.player.isPet && ((Pet) this.player).typePet == 3
                && ((Pet) this.player).master.fusion.typeFusion == ConstPlayer.HOP_THE_PORATA) {
            this.dame += ((long) this.dame * 4 / 100);
        }
        if (this.player.isPet && ((Pet) this.player).typePet == 3
                && ((Pet) this.player).master.fusion.typeFusion == ConstPlayer.HOP_THE_PORATA2) {
            this.dame += ((long) this.dame * 6 / 100);
        }
        if (this.player.isPet && ((Pet) this.player).typePet == 3
                && ((Pet) this.player).master.fusion.typeFusion == ConstPlayer.HOP_THE_PORATA3) {
            this.dame += ((long) this.dame * 8 / 100);
        }
        if (this.player.isPet && ((Pet) this.player).typePet == 3
                && ((Pet) this.player).master.fusion.typeFusion == ConstPlayer.HOP_THE_PORATA4) {
            this.dame += ((long) this.dame * 10 / 100);
        }
        // pet ngokhong
        if (this.player.isPet && ((Pet) this.player).typePet == 4
                && ((Pet) this.player).master.fusion.typeFusion == ConstPlayer.HOP_THE_PORATA) {
            this.dame += ((long) this.dame * 4 / 100);
        }
        if (this.player.isPet && ((Pet) this.player).typePet == 4
                && ((Pet) this.player).master.fusion.typeFusion == ConstPlayer.HOP_THE_PORATA2) {
            this.dame += ((long) this.dame * 6 / 100);
        }
        if (this.player.isPet && ((Pet) this.player).typePet == 4
                && ((Pet) this.player).master.fusion.typeFusion == ConstPlayer.HOP_THE_PORATA3) {
            this.dame += ((long) this.dame * 8 / 100);
        }
        if (this.player.isPet && ((Pet) this.player).typePet == 4
                && ((Pet) this.player).master.fusion.typeFusion == ConstPlayer.HOP_THE_PORATA4) {
            this.dame += ((long) this.dame * 10 / 100);
        }
        // pet ngokhong
        if (this.player.isPet && ((Pet) this.player).typePet == 5
                && ((Pet) this.player).master.fusion.typeFusion == ConstPlayer.HOP_THE_PORATA) {
            this.dame += ((long) this.dame * 5 / 100);
        }
        if (this.player.isPet && ((Pet) this.player).typePet == 5
                && ((Pet) this.player).master.fusion.typeFusion == ConstPlayer.HOP_THE_PORATA2) {
            this.dame += ((long) this.dame * 7 / 100);
        }
        if (this.player.isPet && ((Pet) this.player).typePet == 5
                && ((Pet) this.player).master.fusion.typeFusion == ConstPlayer.HOP_THE_PORATA3) {
            this.dame += ((long) this.dame * 9 / 100);
        }
        if (this.player.isPet && ((Pet) this.player).typePet == 5
                && ((Pet) this.player).master.fusion.typeFusion == ConstPlayer.HOP_THE_PORATA4) {
            this.dame += ((long) this.dame * 12 / 100);
        }
        // pet ngokhong
        if (this.player.isPet && ((Pet) this.player).typePet == 6
                && ((Pet) this.player).master.fusion.typeFusion == ConstPlayer.HOP_THE_PORATA) {
            this.dame += ((long) this.dame * 5 / 100);
        }
        if (this.player.isPet && ((Pet) this.player).typePet == 6
                && ((Pet) this.player).master.fusion.typeFusion == ConstPlayer.HOP_THE_PORATA2) {
            this.dame += ((long) this.dame * 7 / 100);
        }
        if (this.player.isPet && ((Pet) this.player).typePet == 6
                && ((Pet) this.player).master.fusion.typeFusion == ConstPlayer.HOP_THE_PORATA3) {
            this.dame += ((long) this.dame * 9 / 100);
        }
        if (this.player.isPet && ((Pet) this.player).typePet == 6
                && ((Pet) this.player).master.fusion.typeFusion == ConstPlayer.HOP_THE_PORATA4) {
            this.dame += ((long) this.dame * 12 / 100);
        }

        // pet ngokhong
        if (this.player.isPet && ((Pet) this.player).typePet == 7
                && ((Pet) this.player).master.fusion.typeFusion == ConstPlayer.HOP_THE_PORATA) {
            this.dame += ((long) this.dame * 5 / 100);
        }
        if (this.player.isPet && ((Pet) this.player).typePet == 7
                && ((Pet) this.player).master.fusion.typeFusion == ConstPlayer.HOP_THE_PORATA2) {
            this.dame += ((long) this.dame * 7 / 100);
        }
        if (this.player.isPet && ((Pet) this.player).typePet == 7
                && ((Pet) this.player).master.fusion.typeFusion == ConstPlayer.HOP_THE_PORATA3) {
            this.dame += ((long) this.dame * 9 / 100);
        }
        if (this.player.isPet && ((Pet) this.player).typePet == 7
                && ((Pet) this.player).master.fusion.typeFusion == ConstPlayer.HOP_THE_PORATA4) {
            this.dame += ((long) this.dame * 12 / 100);
        }

        // thức ăn
        if (!this.player.isPet && this.player.itemTime.isEatMeal
                || this.player.isPet && ((Pet) this.player).master.itemTime.isEatMeal) {
            this.dame += ((long) this.dame * 10 / 100);
        }

        // thức ăn
        if (!this.player.isPet && this.player.itemTime.isEatPotential
                || this.player.isPet && ((Pet) this.player).master.itemTime.isEatPotential) {
            this.dame += ((long) this.dame * 10 / 100);
        }
//        if (!this.player.isPet && this.player.itemTime.isEatUltra) {
//            this.dame += ((long) this.dame * (this.player.lvlThienSu / 10) / 100);
//        }
        // hợp thể
        if (this.player.fusion.typeFusion != 0) {
            this.dame += this.player.pet.nPoint.dame;
        }

        if (!this.player.isPet && this.player.itemTime.isEatBanh
                || this.player.isPet && ((Pet) this.player).master.itemTime.isEatBanh) {
            this.dame += ((long) this.dame * 10 / 100);
        }
        // cuồng nộ
        if (this.player.itemTime != null && this.player.itemTime.isUseCuongNo) {
            this.dame += dameg;
        }

        if (this.player.itemTime != null && this.player.itemTime.isUseCuongNoSC) {
            this.dame += dameg * 3 / 2;

        }
        // giảm dame
        this.dame -= ((long) this.dame * tlSubSD / 100);
        // map cold
        if (this.player.zone != null && MapService.gI().isMapCold(this.player.zone.map)
                && !this.isKhongLanh) {
            this.dame /= 2;
        }
        // ngọc rồng đen 1 sao
        if (this.player.rewardBlackBall.timeOutOfDateReward[0] > System.currentTimeMillis()) {
            this.dame += ((long) this.dame * RewardBlackBall.R1S_2 / 100);
        }
        // set worldcup
        if (this.player.setClothes.worldcup == 2) {
            this.dame += ((long) this.dame * 5 / 100);
            this.tlDameCrit.add(20);
        }
        // phóng heo
        if (this.player.effectFlagBag.usePhongHeo) {
            this.dame += ((long) this.dame * 15 / 100);
        }
        // khỉ
        if (this.player.effectSkill.isMonkey) {
            if (!this.player.isPet || (this.player.isPet
                    && ((Pet) this.player).status != Pet.FUSION)) {
                int percent = SkillUtil.getPercentDameMonkey(player.effectSkill.levelMonkey);
                this.dame += ((long) this.dame * percent / 100);
            }
        }
        if (!this.player.isPet && this.player.itemTime.isEatDuoiKhi && !this.player.effectSkill.isMonkey) {
            this.dame += ((long) this.dame * 10 / 100);
        }
        if (this.player.itemTime != null && this.player.itemTime.istrbsd) {
            this.dame += (this.dame * 15 / 100);
        }

//        if (this.player.effectSkill.isHoaCarot || this.player.effectSkill.isHoaSocola
//                || this.player.effectSkill.isHoaKeo || this.player.effectSkill.isHoaBiNgo) {
//            this.dame /= 2;
//        }

        if (this.player.effectSkill.isHoaLanh && !this.isKhongLanh) {
            this.dame /= 2;
        }
        if (this.player.effectSkill.isHorny) {
            this.dame += ((long) this.dame * 15 / 100);
        }
        if (this.dame > 2000000000) {
            this.dame = 2000000000;
        }

    }

    private void setDameCrit() {
        // da xanh
        if (!this.player.isPet && this.player.itemTime.isEatDaXanh
                || this.player.isPet && ((Pet) this.player).master.itemTime.isEatDaXanh) {
            this.tlDameCrit.add(10);
        }

        if (this.player.nPoint.isSSJ4) {
            this.tlDameCrit.add(20);
        }

    }

    private void setDef() {
        this.def = this.defg;
        this.def += this.defAdd;
        // đồ
        for (Integer tl : this.tlDef) {
            this.def += (this.def * tl / 100);
        }
        // ngọc rồng đen 2 sao
        if (this.player.rewardBlackBall.timeOutOfDateReward[1] > System.currentTimeMillis()) {
            this.def += ((long) this.def * RewardBlackBall.R2S_2 / 100);
        }
        // pet mabư
        if (this.player.isPet && ((Pet) this.player).typePet == 1
                && ((Pet) this.player).master.fusion.typeFusion == ConstPlayer.HOP_THE_PORATA) {
            this.def += ((long) this.def * 4 / 100);
        }
        if (this.player.isPet && ((Pet) this.player).typePet == 1
                && ((Pet) this.player).master.fusion.typeFusion == ConstPlayer.HOP_THE_PORATA2) {
            this.def += ((long) this.def * 6 / 100);
        }
        if (this.player.isPet && ((Pet) this.player).typePet == 1
                && ((Pet) this.player).master.fusion.typeFusion == ConstPlayer.HOP_THE_PORATA3) {
            this.def += ((long) this.def * 8 / 100);
        }
        if (this.player.isPet && ((Pet) this.player).typePet == 1
                && ((Pet) this.player).master.fusion.typeFusion == ConstPlayer.HOP_THE_PORATA4) {
            this.def += ((long) this.def * 10 / 100);
        }
        // pet berus
        if (this.player.isPet && ((Pet) this.player).typePet == 2// chi so lam sao bac tu cho dj
                && ((Pet) this.player).master.fusion.typeFusion == ConstPlayer.HOP_THE_PORATA) {
            this.def += ((long) this.def * 4 / 100);// chi so hp
        }
        if (this.player.isPet && ((Pet) this.player).typePet == 2// chi so lam sao bac tu cho dj
                && ((Pet) this.player).master.fusion.typeFusion == ConstPlayer.HOP_THE_PORATA2) {
            this.def += ((long) this.def * 6 / 100);// chi so hp
        }
        if (this.player.isPet && ((Pet) this.player).typePet == 2// chi so lam sao bac tu cho dj
                && ((Pet) this.player).master.fusion.typeFusion == ConstPlayer.HOP_THE_PORATA3) {
            this.def += ((long) this.def * 8 / 100);// chi so hp
        }
        if (this.player.isPet && ((Pet) this.player).typePet == 2// chi so lam sao bac tu cho dj
                && ((Pet) this.player).master.fusion.typeFusion == ConstPlayer.HOP_THE_PORATA4) {
            this.def += ((long) this.def * 10 / 100);// chi so hp
        }
        // goku
        if (this.player.isPet && ((Pet) this.player).typePet == 3
                && ((Pet) this.player).master.fusion.typeFusion == ConstPlayer.HOP_THE_PORATA) {
            this.def += ((long) this.def * 4 / 100);
        }
        if (this.player.isPet && ((Pet) this.player).typePet == 3
                && ((Pet) this.player).master.fusion.typeFusion == ConstPlayer.HOP_THE_PORATA2) {
            this.def += ((long) this.def * 6 / 100);
        }
        if (this.player.isPet && ((Pet) this.player).typePet == 3
                && ((Pet) this.player).master.fusion.typeFusion == ConstPlayer.HOP_THE_PORATA3) {
            this.def += ((long) this.def * 8 / 100);
        }
        if (this.player.isPet && ((Pet) this.player).typePet == 3
                && ((Pet) this.player).master.fusion.typeFusion == ConstPlayer.HOP_THE_PORATA4) {
            this.def += ((long) this.def * 10 / 100);
        }
        // pet ngokhong
        if (this.player.isPet && ((Pet) this.player).typePet == 4
                && ((Pet) this.player).master.fusion.typeFusion == ConstPlayer.HOP_THE_PORATA) {
            this.def += ((long) this.def * 4 / 100);
        }
        if (this.player.isPet && ((Pet) this.player).typePet == 4
                && ((Pet) this.player).master.fusion.typeFusion == ConstPlayer.HOP_THE_PORATA2) {
            this.def += ((long) this.def * 6 / 100);
        }
        if (this.player.isPet && ((Pet) this.player).typePet == 4
                && ((Pet) this.player).master.fusion.typeFusion == ConstPlayer.HOP_THE_PORATA3) {
            this.def += ((long) this.def * 8 / 100);
        }
        if (this.player.isPet && ((Pet) this.player).typePet == 4
                && ((Pet) this.player).master.fusion.typeFusion == ConstPlayer.HOP_THE_PORATA4) {
            this.def += ((long) this.def * 10 / 100);
        }
        // pet ngokhong
        if (this.player.isPet && ((Pet) this.player).typePet == 5
                && ((Pet) this.player).master.fusion.typeFusion == ConstPlayer.HOP_THE_PORATA) {
            this.def += ((long) this.def * 5 / 100);
        }
        if (this.player.isPet && ((Pet) this.player).typePet == 5
                && ((Pet) this.player).master.fusion.typeFusion == ConstPlayer.HOP_THE_PORATA2) {
            this.def += ((long) this.def * 7 / 100);
        }
        if (this.player.isPet && ((Pet) this.player).typePet == 5
                && ((Pet) this.player).master.fusion.typeFusion == ConstPlayer.HOP_THE_PORATA3) {
            this.def += ((long) this.def * 9 / 100);
        }
        if (this.player.isPet && ((Pet) this.player).typePet == 5
                && ((Pet) this.player).master.fusion.typeFusion == ConstPlayer.HOP_THE_PORATA4) {
            this.def += ((long) this.def * 12 / 100);
        }
        // pet ngokhong
        if (this.player.isPet && ((Pet) this.player).typePet == 6
                && ((Pet) this.player).master.fusion.typeFusion == ConstPlayer.HOP_THE_PORATA) {
            this.def += ((long) this.def * 5 / 100);
        }
        if (this.player.isPet && ((Pet) this.player).typePet == 6
                && ((Pet) this.player).master.fusion.typeFusion == ConstPlayer.HOP_THE_PORATA2) {
            this.def += ((long) this.def * 7 / 100);
        }
        if (this.player.isPet && ((Pet) this.player).typePet == 6
                && ((Pet) this.player).master.fusion.typeFusion == ConstPlayer.HOP_THE_PORATA3) {
            this.def += ((long) this.def * 9 / 100);
        }
        if (this.player.isPet && ((Pet) this.player).typePet == 6
                && ((Pet) this.player).master.fusion.typeFusion == ConstPlayer.HOP_THE_PORATA4) {
            this.def += ((long) this.def * 12 / 100);
        }
        // pet ngokhong
        if (this.player.isPet && ((Pet) this.player).typePet == 7
                && ((Pet) this.player).master.fusion.typeFusion == ConstPlayer.HOP_THE_PORATA) {
            this.def += ((long) this.def * 5 / 100);
        }
        if (this.player.isPet && ((Pet) this.player).typePet == 7
                && ((Pet) this.player).master.fusion.typeFusion == ConstPlayer.HOP_THE_PORATA2) {
            this.def += ((long) this.def * 7 / 100);
        }
        if (this.player.isPet && ((Pet) this.player).typePet == 7
                && ((Pet) this.player).master.fusion.typeFusion == ConstPlayer.HOP_THE_PORATA3) {
            this.def += ((long) this.def * 9 / 100);
        }
        if (this.player.isPet && ((Pet) this.player).typePet == 7
                && ((Pet) this.player).master.fusion.typeFusion == ConstPlayer.HOP_THE_PORATA4) {
            this.def += ((long) this.def * 12 / 100);
        }

        if (this.player.fusion.typeFusion != 0) {
            this.def += this.player.pet.nPoint.def;
        }
//        if (this.player.effectSkill.isHoaCarot || this.player.effectSkill.isHoaSocola
//                || this.player.effectSkill.isHoaKeo || this.player.effectSkill.isHoaBiNgo) {
//            this.def = 0;
//        }
        if (!this.player.isPet && this.player.itemTime.isEatNhoXanh
                || this.player.isPet && ((Pet) this.player).master.itemTime.isEatNhoXanh) {
            this.def += 10000;
        }

    }

    public void setCrit() {
        this.crit = this.critg;
        this.crit += this.critAdd;
        // ngọc rồng đen 3 sao
        if (this.player.rewardBlackBall.timeOutOfDateReward[2] > System.currentTimeMillis()) {
            this.crit += RewardBlackBall.R3S_2;
        }
        // da do
        if (!this.player.isPet && this.player.itemTime.isEatDaDo
                || this.player.isPet && ((Pet) this.player).master.itemTime.isEatDaDo) {
            this.crit += 10;
        }
        if (this.player.itemTime != null && this.player.itemTime.istrbcrit) {
            this.crit += 10;
        }
        // biến khỉ
        if (this.player.effectSkill.isMonkey) {
            this.crit = 110;
        }
        if (!this.player.isPet && this.player.itemTime.isEatDuoiKhi && !this.player.effectSkill.isMonkey) {
            this.crit = 110;
        }
        // cuong chien
        if (this.player.effectSkill.isCuongChien) {
            this.crit += 10;
        }
//        if (this.player.effectSkill.isHoaCarot || this.player.effectSkill.isHoaSocola
//                || this.player.effectSkill.isHoaKeo || this.player.effectSkill.isHoaBiNgo) {
//            this.crit = 1;
//        }
        if (this.player.effectSkill.isHorny) {
            this.crit += 10;
        }
        //
        if (!this.player.effectSkill.isMonkey && !this.player.effectSkill.isCuongChien && this.crit > 33
                && !this.player.itemTime.isEatDuoiKhi) {
            this.crit = 50;
        }
        if (this.player.fusion.typeFusion == ConstPlayer.LUONG_LONG_NHAT_THE && this.player.nPoint.isSSJ4
                && this.player.pet != null && this.player.pet.nPoint.isSSJ4) {
            this.crit = 100;
        }

    }

    private void resetPoint() {
        this.voHieuChuong = 0;
        this.hpAdd = 0;
        this.mpAdd = 0;
        this.dameAdd = 0;
        this.defAdd = 0;
        this.critAdd = 0;
        this.tlHp.clear();
        this.tlMp.clear();
        this.tlDef.clear();
        this.tlDame.clear();
        this.tlDameCrit.clear();
        this.tlDameAttMob.clear();
        this.tlHpHoiBanThanVaDongDoi = 0;
        this.tlMpHoiBanThanVaDongDoi = 0;
        this.hpHoi = 0;
        this.mpHoi = 0;
        this.mpHoiCute = 0;
        this.tlHpHoi = 0;
        this.tlMpHoi = 0;
        this.tlHutHp = 0;
        this.tlHutMp = 0;
        this.tlHutHpMob = 0;
        this.tlHutHpMpXQ = 0;
        this.tlPST = 0;
        this.tlXuyenGiap = 0;
        this.tlMayMan = 0;
        this.tlSTDam = 0;
        this.tlTNSM.clear();
        this.tlDameAttMob.clear();
        this.tlGold = 0;
        this.tlNeDon = 1;
        this.tlChinhXac = 1;
        this.speed = 8;
        this.tlSDDep.clear();
        this.tlSubSD = 0;
        this.tlHpGiamODo = 0;
        this.tlHpGiamDiaNguc = 0;
        this.test = 0;
        this.teleport = false;
        this.isMabu = false;
        this.isThieuDot = false;
        this.isThoDaiCa = false;
        this.isDrabura = false;
        this.wearingVoHinh = false;
        this.isKhongLanh = false;
        this.khangTDHS = false;
        this.isFrost = false;
        this.isSieuThan = false;
        this.isSoiDaiCa = false;
        this.isZomKing = false;
        this.isPhuThuy = false;
        this.isNoel = false;
        this.isSSJ4 = false;
        this.isNhatThan = false;
        this.isNguyetThan = false;
        this.isTayDam = false;
        this.isBuiBui = false;
        this.isSexy = false;
        this.isYacon = false;
        this.tlDameSexy = 0;
        this.tlDameHorny = 0;
        this.isXinBaTo = false;
    }

    public void addHp(int hp) {
        if (hp < 1) {
            hp = 1;
        }
        if (hp > this.hpMax) {
            hp = this.hpMax;
        }
        this.hp += hp;
        if (this.hp > this.hpMax) {
            this.hp = this.hpMax;
        }
    }

    public void addMp(int mp) {
        if (mp < 1) {
            mp = 1;
        }
        if (mp > this.mpMax) {
            mp = this.mpMax;
        }
        this.mp += mp;
        if (this.mp > this.mpMax) {
            this.mp = this.mpMax;
        }
    }

    public void setHp(long hp) {
        if (hp > this.hpMax) {
            this.hp = this.hpMax;
        } else {
            this.hp = (int) hp;
        }
    }

    public void setMp(long mp) {
        if (mp > this.mpMax) {
            this.mp = this.mpMax;
        } else {
            this.mp = (int) mp;
        }
    }

    private void setIsCrit() {
        if (intrinsic != null && intrinsic.id == 25
                && this.getCurrPercentHP() <= intrinsic.param1) {
            isCrit = true;
        } else if (isCrit100) {
            isCrit100 = false;
            isCrit = true;
        } else {
            isCrit = Util.isTrue(this.crit, ConstRatio.PER100);
        }
    }

    public int getDameAttack(boolean isAttackMob) {
        setIsCrit();
        long dameAttack = this.dame;
        intrinsic = this.player.playerIntrinsic.intrinsic;
        percentDameIntrinsic = 0;
        int percentDameSkill = 0;
        int percentXDame = 0;
        Skill skillSelect = player.playerSkill.skillSelect;
        switch (skillSelect.template.id) {
            case Skill.DRAGON:
                if (intrinsic.id == 1) {
                    percentDameIntrinsic = intrinsic.param1;
                }
                percentDameSkill = skillSelect.damage;
                if (this.player.setClothes.NhatAn == 5) {
                    dameAttack *= 2;
                }
                if (this.player.nPoint.isTayDam && this.player.nPoint.tlSTDam > 0) {
                    dameAttack += dameAttack * tlSTDam / 100;
                }
                break;
            case Skill.KAMEJOKO:
                if (intrinsic.id == 2) {
                    percentDameIntrinsic = intrinsic.param1;

                }
                percentDameSkill = skillSelect.damage;
                if (this.player.setClothes.songoku == 5) {
                    percentXDame = 80;
                }
                if (this.player.setClothes.NhatAn == 5) {
                    dameAttack *= 2;
                }

                break;
            case Skill.GALICK:
                if (intrinsic.id == 16) {
                    percentDameIntrinsic = intrinsic.param1;
                }
                percentDameSkill = skillSelect.damage;
                if (this.player.setClothes.kakarot == 5) {
                    percentXDame = 80;

                }
                if (this.player.setClothes.NhatAn == 5) {
                    dameAttack *= 2;
                }
                if (this.player.nPoint.isTayDam && this.player.nPoint.tlSTDam > 0) {
                    dameAttack += dameAttack * tlSTDam / 100;
                }
                break;
            case Skill.ANTOMIC:
                if (intrinsic.id == 17) {
                    percentDameIntrinsic = intrinsic.param1;
                }
                percentDameSkill = skillSelect.damage;
                if (this.player.setClothes.NhatAn == 5) {
                    dameAttack *= 2;
                }
                break;
            case Skill.DEMON:
                if (intrinsic.id == 8) {
                    percentDameIntrinsic = intrinsic.param1;
                }

                percentDameSkill = skillSelect.damage;
                if (this.player.setClothes.NhatAn == 5) {
                    dameAttack *= 2;
                }
                if (this.player.nPoint.isTayDam && this.player.nPoint.tlSTDam > 0) {
                    dameAttack += dameAttack * tlSTDam / 100;
                }
                break;
            case Skill.MASENKO:
                if (intrinsic.id == 9) {
                    percentDameIntrinsic = intrinsic.param1;
                }
                percentDameSkill = skillSelect.damage;
                if (this.player.setClothes.NhatAn == 5) {
                    dameAttack *= 2;
                }
                break;
            case Skill.KAIOKEN:
                if (intrinsic.id == 26) {
                    percentDameIntrinsic = intrinsic.param1;
                }
                percentDameSkill = skillSelect.damage;
                if (this.player.setClothes.kirin == 5) {
                    percentXDame = 80;
                }
                if (this.player.setClothes.NhatAn == 5) {
                    dameAttack *= 2;
                }
                break;
            case Skill.SUPER_KAME:
                percentDameSkill = skillSelect.damage;
                break;
            case Skill.LIEN_HOAN_CHUONG:
                percentDameSkill = skillSelect.damage;

                break;
            // case Skill.MA_PHONG_BA:
            // percentDameSkill = skillSelect.damage;
            // break;
            case Skill.LIEN_HOAN:
                if (intrinsic.id == 13) {
                    percentDameIntrinsic = intrinsic.param1;
                }
                percentDameSkill = skillSelect.damage;
                if (this.player.setClothes.ocTieu == 5) {
                    percentXDame = 80;
                }
                if (this.player.setClothes.NhatAn == 5) {
                    dameAttack *= 2;
                }
                if (this.player.nPoint.isTayDam && this.player.nPoint.tlSTDam > 0) {
                    dameAttack += dameAttack * tlSTDam / 100;
                }
                break;
            case Skill.DICH_CHUYEN_TUC_THOI:
                dameAttack *= 2;
                dameAttack = Util.nextInt((int) (dameAttack - (dameAttack * 5 / 100)),
                        (int) (dameAttack + (dameAttack * 5 / 100)));
                return (int) dameAttack;
            case Skill.MAKANKOSAPPO:
                percentDameSkill = skillSelect.damage;
                int dameSkill = (int) ((long) this.mpMax * percentDameSkill / 100);
                return dameSkill;
            case Skill.QUA_CAU_KENH_KHI:
                int dame = this.dame * skillSelect.damage / 100;
                for (Mob mob : player.zone.mobs) {
                    if (!mob.isDie()) {
                        int dmgAddQCKK = mob.point.maxHp / 20;
                        mob.injured(player, dmgAddQCKK, false);
                        dame += dmgAddQCKK;
                    }
                }
                for (Player plZone : player.zone.getPlayers()) {
                    if (!plZone.isDie()) {
                        int dmgAddQCKK = plZone.nPoint.hpMax / 20;
                        plZone.injured(player, dmgAddQCKK, true, false);
                        dame += dmgAddQCKK;
                    }
                }

                return dame;
        }
        if (intrinsic.id == 18 && this.player.effectSkill.isMonkey) {
            percentDameIntrinsic = intrinsic.param1;
        }
        if (percentDameSkill != 0) {
            dameAttack = dameAttack * percentDameSkill / 100;
        }
        dameAttack += (dameAttack * percentDameIntrinsic / 100);
        dameAttack += (dameAttack * dameAfter / 100);

        if (isAttackMob) {
            for (Integer tl : this.tlDameAttMob) {
                dameAttack += (dameAttack * tl / 100);
            }
        }
        dameAfter = 0;
        if (this.player.isPet && ((Pet) this.player).master.charms.tdDeTu > System.currentTimeMillis()) {
            dameAttack *= 2;
        }
        if (isCrit) {
            dameAttack *= 2;
            for (Integer tl : this.tlDameCrit) {
                dameAttack += (dameAttack * tl / 100);
            }
        }
        dameAttack += ((long) dameAttack * percentXDame / 100);
        dameAttack = Util.nextInt((int) (dameAttack - (dameAttack * 5 / 100)),
                (int) (dameAttack + (dameAttack * 5 / 100)));
        // x4
        if (player.isPl()) {
            if (player.inventory.haveOption(player.inventory.itemsBody, 5, 159)) {
                if (Util.canDoWithTime(player.lastTimeUseOption, 60000)
                        && (player.playerSkill.skillSelect.template.id == Skill.KAMEJOKO
                                || player.playerSkill.skillSelect.template.id == Skill.ANTOMIC
                                || player.playerSkill.skillSelect.template.id == Skill.MASENKO)) {
                    dameAttack *= player.inventory.getParam(player.inventory.itemsBody.get(5), 159);
                    player.lastTimeUseOption = System.currentTimeMillis();
                }
            }
        }
        // check activation set
        return (int) dameAttack;
    }

    public int getCurrPercentHP() {
        if (this.hpMax == 0) {
            return 100;
        }
        return (int) ((long) this.hp * 100 / this.hpMax);
    }

    public int getCurrPercentMP() {
        return (int) ((long) this.mp * 100 / this.mpMax);
    }

    public void setFullHpMp() {
        this.hp = this.hpMax;
        this.mp = this.mpMax;

    }

    public void subHP(int sub) {
        this.hp -= sub;
        if (this.hp < 0) {
            this.hp = 0;
        }
    }

    public void subMP(int sub) {
        this.mp -= sub;
        if (this.mp < 0) {
            this.mp = 0;
        }
    }

    public long calSucManhTiemNang(long tiemNang) {
        if (power < getPowerLimit()) {
            for (Integer tl : this.tlTNSM) {
                tiemNang += ((long) tiemNang * tl / 100);
            }
            if (this.player.cFlag != 0) {
                if (this.player.cFlag == 8) {
                    tiemNang += ((long) tiemNang * 10 / 100);
                } else {
                    tiemNang += ((long) tiemNang * 5 / 100);
                }
            }
            long tn = tiemNang;
            if (this.player.charms.tdTriTue > System.currentTimeMillis()) {
                tiemNang += tn;
            }

            if (this.player.charms.tdTriTue3 > System.currentTimeMillis()) {
                tiemNang += tn * 2;
            }
            if (this.player.charms.tdTriTue4 > System.currentTimeMillis()) {
                tiemNang += tn * 3;
            }
            if (this.intrinsic != null && this.intrinsic.id == 24) {
                tiemNang += ((long) tiemNang * this.intrinsic.param1 / 100);
            }

            if (this.player.isPet) {
                if (((Pet) this.player).master.charms.tdDeTu > System.currentTimeMillis()) {
                    tiemNang = tiemNang * 6;
                }
            }

            // da tim
            if (!this.player.isPet && this.player.itemTime.isEatDaTim
                    || this.player.isPet && ((Pet) this.player).master.itemTime.isEatDaTim) {
                tiemNang += tiemNang * 0.2;
            }
            if (!this.player.isPet && this.player.itemTime.isEatPotential
                    || this.player.isPet && ((Pet) this.player).master.itemTime.isEatPotential) {
                tiemNang += tiemNang * 0.5;
            }
            if (!this.player.isPet && this.player.itemTime.isEatHuyHieu
                    || this.player.isPet && ((Pet) this.player).master.itemTime.isEatHuyHieu) {
                tiemNang += tiemNang * 0.2;
            }
            tiemNang = calSubTNSM(tiemNang);

            if (tiemNang <= 0) {
                tiemNang = 1;
            }
            if (tiemNang > 10000000) {
                tiemNang = 10000000;
            }
            tiemNang *= Manager.RATE_EXP_SERVER;
        } else {
            tiemNang = 1;
        }
        return tiemNang;
    }

    public long calSubTNSM(long tiemNang) {
        if (this.power >= 600000000000L) {
            tiemNang /= 6400;
        } else if (this.power >= 300000000000L) {
            tiemNang /= 4800;
        } else if (this.power >= 250000000000L) {
            tiemNang /= 3200;
        } else if (this.power >= 200000000000L) {
            tiemNang /= 1600;
        } else if (this.power >= 160000000000L) {
            tiemNang /= 800;
        } else if (this.power >= 120000000000L) {
            tiemNang /= 400;
        } else if (this.power >= 80000000000L) {
            tiemNang /= 200;
        } else if (this.power >= 60000000000L) {
            tiemNang /= 50;
        } else if (this.power >= 40000000000L) {
            tiemNang /= 10;
        }
        return tiemNang;
    }

    public short getTileHutHp(boolean isMob) {
        if (isMob) {
            return (short) (this.tlHutHp);
        } else {
            return this.tlHutHp;
        }
    }

    public short getTiLeHutMp() {
        return this.tlHutMp;
    }

    //
    public int subDameInjureWithDeff(int dame, Player plAtt) {
        int def = this.def;
        if (this.player.isPet && ((Pet) this.player).master.charms.tdDeTu > System.currentTimeMillis()) {
            dame /= 3;
        }

        if (this.player.setClothes.NguyetAn == 5) {
            dame /= 2;
        }

        if (this.player.itemTime.isUseGiapXen) {
            dame /= 2;
        }
        if (this.player.itemTime.isUseGiapXenSC || this.player.itemTime.isUseGiapXen2) {
            dame /= 3;
        }

        if (plAtt != null) {
            if (plAtt.isPl()) {
                if (plAtt.effectSkill.isPhanTam) {
                    if (Util.isTrue(33, 100)) {
                        Service.gI().chat(plAtt, "Mất tập chung quá");
                        return 0;
                    }

                }
                def -= ((int) def * plAtt.nPoint.tlXuyenGiap / 100);// check
                double tlDameR = (double) 10000 / (10000 + def);
                dame -= (int) dame * (1 - tlDameR);
            }

        } else {
            dame -= def;
        }

        if (dame < 0) {
            dame = 1;
        }

        return dame;
    }

    public int subDameInjureWithDeff(int dame) {
        int def = this.def;
        dame -= def;
        if (this.player.isPet && ((Pet) this.player).master.charms.tdDeTu > System.currentTimeMillis()) {
            dame /= 4;
        }

        if (this.player.setClothes.NguyetAn == 5) {
            dame /= 2;
        }

        if (this.player.itemTime.isUseGiapXen) {
            dame /= 2;
        }
        if (this.player.itemTime.isUseGiapXenSC || this.player.itemTime.isUseGiapXen2) {
            dame /= 3;
        } else {
        }
        if (dame < 0) {
            dame = 1;
        }
        return dame;
    }

    /*------------------------------------------------------------------------*/
    public boolean canOpenPower() {
        return this.power >= getPowerLimit();
    }

    public long getPowerLimit() {
        switch (limitPower) {
            case 0:
                return 17999999999L;
            case 1:
                return 18999999999L;
            case 2:
                return 20999999999L;
            case 3:
                return 24999999999L;
            case 4:
                return 30999999999L;
            case 5:
                return 40999999999L;
            case 6:
                return 60999999999L;
            case 7:
                return 80999999999L;
            case 8:
                return 150000000000L;
            case 9:
                return 900000000000L;
            default:
                return 0;
        }
    }

    public long getPowerNextLimit() {
        switch (limitPower + 1) {
            case 0:
                return 17999999999L;
            case 1:
                return 18999999999L;
            case 2:
                return 20999999999L;
            case 3:
                return 24999999999L;
            case 4:
                return 30999999999L;
            case 5:
                return 40999999999L;
            case 6:
                return 60999999999L;
            case 7:
                return 80999999999L;
            case 8:
                return 150000000000L;
            case 9:
                return 900000000000L;
            default:
                return 0;
        }
    }

    public int getHpMpLimit() {
        if (limitPower == 0) {
            return 220000;
        }
        if (limitPower == 1) {
            return 300000;
        }
        if (limitPower == 2) {
            return 360000;
        }
        if (limitPower == 3) {
            return 420000;
        }
        if (limitPower == 4) {
            return 480000;
        }
        if (limitPower == 5) {
            return 520000;
        }
        if (limitPower == 6) {
            return 550000;
        }
        if (limitPower == 7) {
            return 590000;
        }
        if (limitPower == 8) {
            return 620000;
        }
        if (limitPower == 9) {
            return 700000;
        }
        return 0;
    }

    public int getDameLimit() {
        if (limitPower == 0) {
            return 11000;
        }
        if (limitPower == 1) {
            return 12000;
        }
        if (limitPower == 2) {
            return 15000;
        }
        if (limitPower == 3) {
            return 18000;
        }
        if (limitPower == 4) {
            return 20000;
        }
        if (limitPower == 5) {
            return 22000;
        }
        if (limitPower == 6) {
            return 25000;
        }
        if (limitPower == 7) {
            return 30000;
        }
        if (limitPower == 8) {
            return 31000;
        }
        if (limitPower == 9) {
            return 32000;
        }
        return 0;
    }

    public short getDefLimit() {
        if (limitPower == 0) {
            return 1000;
        }
        if (limitPower == 1) {
            return 2000;
        }
        if (limitPower == 2) {
            return 3000;
        }
        if (limitPower == 3) {
            return 4000;
        }
        if (limitPower == 4) {
            return 5000;
        }
        if (limitPower == 5) {
            return 6000;
        }
        if (limitPower == 6) {
            return 10000;
        }
        if (limitPower == 7) {
            return 13000;
        }
        if (limitPower == 8) {
            return 15000;
        }
        if (limitPower == 9) {
            return 20000;
        }
        return 0;
    }

    public byte getCritLimit() {
        if (limitPower == 0) {
            return 5;
        }
        if (limitPower == 1) {
            return 6;
        }
        if (limitPower == 2) {
            return 7;
        }
        if (limitPower == 3) {
            return 8;
        }
        if (limitPower == 4) {
            return 9;
        }
        if (limitPower == 5) {
            return 10;
        }
        if (limitPower == 6) {
            return 11;
        }
        if (limitPower == 7) {
            return 12;
        }
        if (limitPower == 8) {
            return 13;
        }
        if (limitPower == 9) {
            return 14;
        }
        return 0;
    }

    // **************************************************************************
    // POWER - TIEM NANG
    public void powerUp(long power) {
        this.power += power;
        TaskService.gI().checkDoneTaskPower(player, this.power);
    }

    public void tiemNangUp(long tiemNang) {
        this.tiemNang += tiemNang;
    }

    public void increasePoint(byte type, short point) {
        if (point <= 0 || point > 100) {
            return;
        }
        long tiemNangUse = 0;
        if (type == 0) {
            int pointHp = point * 20;
            tiemNangUse = point * (2 * (this.hpg + 1000) + pointHp - 20) / 2;
            if ((this.hpg + pointHp) <= getHpMpLimit()) {
                if (doUseTiemNang(tiemNangUse)) {
                    hpg += pointHp;
                }
            } else {
                Service.gI().sendThongBao(player, "Vui lòng mở giới hạn sức mạnh");
                return;
            }
        }
        if (type == 1) {
            int pointMp = point * 20;
            tiemNangUse = point * (2 * (this.mpg + 1000) + pointMp - 20) / 2;
            if ((this.mpg + pointMp) <= getHpMpLimit()) {
                if (doUseTiemNang(tiemNangUse)) {
                    mpg += pointMp;
                }
            } else {
                Service.gI().sendThongBao(player, "Vui lòng mở giới hạn sức mạnh");
                return;
            }
        }
        if (type == 2) {
            tiemNangUse = point * (2 * this.dameg + point - 1) / 2 * 100;
            if ((this.dameg + point) <= getDameLimit()) {
                if (doUseTiemNang(tiemNangUse)) {
                    dameg += point;
                }
            } else {
                Service.gI().sendThongBao(player, "Vui lòng mở giới hạn sức mạnh");
                return;
            }
        }
        if (type == 3) {
            tiemNangUse = 2 * (this.defg + 5) / 2 * 100000;
            if ((this.defg + point) <= getDefLimit()) {
                if (doUseTiemNang(tiemNangUse)) {
                    defg += point;
                }
            } else {
                Service.gI().sendThongBao(player, "Vui lòng mở giới hạn sức mạnh");
                return;
            }
        }
        if (type == 4) {
            tiemNangUse = 50000000L;
            for (int i = 0; i < this.critg; i++) {
                tiemNangUse *= 5L;
            }
            if ((this.critg + point) <= getCritLimit()) {
                if (doUseTiemNang(tiemNangUse)) {
                    critg += point;
                }
            } else {
                Service.gI().sendThongBao(player, "Vui lòng mở giới hạn sức mạnh");
                return;
            }
        }
        Service.gI().point(player);
    }

    private boolean doUseTiemNang(long tiemNang) {
        if (this.tiemNang < tiemNang) {
            Service.gI().sendThongBaoOK(player, "Bạn không đủ tiềm năng");
            return false;
        }
        if (this.tiemNang >= tiemNang && this.tiemNang - tiemNang >= 0) {
            this.tiemNang -= tiemNang;
            TaskService.gI().checkDoneTaskUseTiemNang(player);
            return true;
        }
        return false;
    }

    // --------------------------------------------------------------------------
    private long lastTimeHoiPhuc;
    private long lastTimeHoiStamina;
    private long lastTimeUpdate;

    public void update() {
        if (player != null && player.effectSkill != null) {
            if (player.effectSkill.isCharging && player.effectSkill.countCharging < 10) {
                int tiLeHoiPhuc = SkillUtil.getPercentCharge(player.playerSkill.skillSelect.point);
                if (player.effectSkill.isCharging && !player.isDie() && !player.effectSkill.isHaveEffectSkill()
                        && (hp < hpMax || mp < mpMax)) {
                    PlayerService.gI().hoiPhuc(player, hpMax / 100 * tiLeHoiPhuc,
                            mpMax / 100 * tiLeHoiPhuc);
                    if (player.effectSkill.countCharging % 3 == 0) {
                        Service.gI().chat(player, "Phục hồi năng lượng " + getCurrPercentHP() + "%");
                    }
                } else {
                    EffectSkillService.gI().stopCharge(player);
                }
                if (++player.effectSkill.countCharging >= 10) {
                    EffectSkillService.gI().stopCharge(player);
                }
            }
            if (Util.canDoWithTime(lastTimeHoiPhuc, 30000)) {
                PlayerService.gI().hoiPhuc(this.player, hpHoi, mpHoi);
                this.lastTimeHoiPhuc = System.currentTimeMillis();
            }
            if (Util.canDoWithTime(lastTimeHoiStamina, 60000) && this.stamina < this.maxStamina) {
                this.stamina++;
                this.lastTimeHoiStamina = System.currentTimeMillis();
                if (!this.player.isBoss && !this.player.isPet) {
                    PlayerService.gI().sendCurrentStamina(this.player);
                }
            }
        }
        // hồi phục 30s
        // hồi phục thể lực

    }

    public void dispose() {
        this.intrinsic = null;
        this.player = null;
        this.tlHp = null;
        this.tlMp = null;
        this.tlDef = null;
        this.tlDame = null;
        this.tlDameCrit = null;
        this.tlDameAttMob = null;
        this.tlSDDep = null;
        this.tlTNSM = null;
    }
}
