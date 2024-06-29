package red.team.item;

import red.team.item.Item.ItemOption;
import red.team.player.NPoint;
import red.team.player.Player;
import red.services.InventoryServiceNew;
import red.services.ItemService;
import red.services.ItemTimeService;
import red.services.Service;
import red.utils.Util;

public class ItemTime {

    // id item text
    public static final byte DOANH_TRAI = 0;
    public static final byte BAN_DO_KHO_BAU = 1;
    public static final byte KHI_GAS = 2;
    public static final byte KHI_GAS_HUY_DIET = 9;
    public static final byte MI_NUONG = 3;
    public static final byte DIA_NGUC = 12;
    public static final int TIME_ITEM = 600000;
    public static final int TIME_OPEN_POWER = 86400000;
    public static final int TIME_MAY_DO = 1800000;
    public static final int TIME_MAY_DO2 = 1800000;
    public static final int TIME_MAY_DO3 = 1800000;
    public static final int TIME_EAT_MEAL = 600000;
    public static final int TIME_EAT_POTENTIAL = 3600000;
    public static final int TIME_EAT_BANH = 600000;
    public static final int TIME_Cua_Rang_Me = 600000;
    public static final byte CAU_CA = 60;
    private Player player;

    public boolean isUseBoHuyet;
    public boolean isUseBoKhi;
    public boolean isUseGiapXen;
    public boolean isUseCuongNo;
    public boolean isUseAnDanh;
    public boolean isUseBoHuyet2;
    public boolean isUseBoKhi2;
    public boolean isUseGiapXen2;
    public boolean isUseCuongNo2;
    public boolean isUseAnDanh2;
    public boolean isUseCuaRangMe;

    public boolean isUseBoHuyetSC;
    public boolean isUseBoKhiSC;
    public boolean isUseGiapXenSC;
    public boolean isUseCuongNoSC;
    public boolean isUseAnDanhSC;

    public long lastTimeBoHuyet;
    public long lastTimeBoKhi;
    public long lastTimeGiapXen;
    public long lastTimeCuongNo;
    public long lastTimeAnDanh;

    public long lastTimeBoHuyet2;
    public long lastTimeBoKhi2;
    public long lastTimeGiapXen2;
    public long lastTimeCuongNo2;
    public long lastTimeAnDanh2;

    public long lastTimeBoHuyetSC;
    public long lastTimeBoKhiSC;
    public long lastTimeGiapXenSC;
    public long lastTimeCuongNoSC;
    public long lastTimeAnDanhSC;

    public long isTimeCuaRangMe;

    public boolean isUseMayDo;
    public long lastTimeUseMayDo;// lastime de chung 1 cai neu time = nhau
    public boolean isUseMayDo2;
    public long lastTimeUseMayDo2;
    public boolean isUseMayDo3;
    public long lastTimeUseMayDo3;

    public boolean isOpenPower;
    public long lastTimeOpenPower;

    public boolean isUseTDLT;
    public long lastTimeUseTDLT;
    public int timeTDLT;

    public boolean isEatMeal;
    public long lastTimeEatMeal;
    public int iconMeal;
    //
    public boolean isEatBanhTho;
    public long lastTimeEatBanhTho;
    public int iconBanhTho;

    public boolean isEatHongDao;
    public long lastTimeEatHongDao;
    public int iconHongDao;

    public boolean isEatBanhTrung;
    public long lastTimeEatBanhTrung;
    public int iconBanhTrung;

    public boolean isEatHuyHieu;
    public long lastTimeEatHuyHieu;
    public int iconHuyHieu;

    public boolean isEatPotential;
    public long lastTimeEatPotential;
    public int iconPotential;

    // da tim
    public boolean isEatDaTim;
    public long lastTimeEatDaTim;
    public int iconDaTim;
    // da tim
    public boolean isEatNhoXanh;
    public long lastTimeEatNhoXanh;
    public int iconNhoXanh;
    // da tim
    public boolean isEatMatTrang;
    public long lastTimeEatMatTrang;
    public int iconMatTrang;
    // da tim
    public boolean isEatKiemGo;
    public long lastTimeEatKiemGo;
    public int iconKiemGo;
    // da tim
    public boolean isEatNhoTim;
    public long lastTimeEatNhoTim;
    public int iconNhoTim;
    // da tim
    public boolean isEatDuoiKhi;
    public long lastTimeEatDuoiKhi;
    public int iconDuoiKhi;
    // da tim
    public boolean isEatLongDen;
    public long lastTimeEatLongDen;
    public int iconLongDen;
    // da xanh
    public boolean isEatDaXanh;
    public long lastTimeEatDaXanh;
    public int iconDaXanh;
    // da do

    public boolean isEatDaDo;
    public long lastTimeEatDaDo;
    public int iconDaDo;
    //
    public boolean isEatBanh;
    public long lastTimeEatBanh;
    public int iconBanh;
    //
    public boolean isEatRada;
    public long lastTimeEatRada;
    public int iconRada;

    //
    public boolean isEatCaiVot;
    public long lastTimeEatCaiVot;
    public int iconCaiVot;
    //
    public boolean isEatMatOng;
    public long lastTimeEatMatOng;
    public int iconMatOng;

    public boolean istrbsd;
    public boolean istrbhp;
    public boolean istrbki;
    public boolean istrbcrit;
    public static final int TIME_TRB = 1800000;

    public long lastTimetrbsd;
    public long lastTimetrbhp;
    public long lastTimetrbki;
    public long lastTimetrbcrit;
    public long lastTimeCuaRangMe;

    //
    public boolean isCauCa;
    public long lastTimeCauCa;
    public int iconCauCa;

    public ItemTime(Player player) {
        this.player = player;
    }

    public void update() {
        if (isCauCa) {
            if (Util.canDoWithTime(lastTimeCauCa, 10000)) {
                isCauCa = false;
                int[] listCa1 = new int[] { 1416, 1415, 1406, 2000 + player.gender, 14, 1416, 1415, 1406, 1398, 1397, };// ca
                                                                                                                        // loai
                                                                                                                        // 1
                                                                                                                        // xin
                                                                                                                        // xo
                                                                                                                        // vcl
                int[] listCa2 = new int[] { 1407, 1409, 1417, 1412, 1078, 1084, 1085, 1086, 1407, 1409, 1417, 1412, 15,
                        1358, 1359, 1360, 1361, };// ca loai 2 cung hoi xin
                int[] listCa3 = new int[] { 1404, 1403, 1405, 1411, 1418, 16, 1066, 1067, 1404, 1403, 1405, 1411, 1418,
                        1068, 1069, 1070 };// ca loai 3 tam trung
                int[] listCa4 = new int[] { 1410, 1408, 1402, 1401, 1414, 1413, 1201, 1202, 1203, 1178, 1179, 1180,
                        1181, 1182 };// ca loai 4 phen
                int[] listCa5 = new int[] { 380, 663, 664, 664, 666, 667, 220, 221, 222, 223, 224, 987 };// rac
                int rd = Util.nextInt(0, 180-player.rateCauCa*2);
                if (rd <= 2) {
                    Item ca1 = ItemService.gI().createNewItem((short) listCa1[Util.nextInt(listCa1.length)]);
                    ca1.itemOptions.add(new ItemOption(30, 0));
                    InventoryServiceNew.gI().addItemBag(player, ca1);
                    player.chat("|7|Vừa câu được " + ca1.template.name + ", xịn vãi l! ");
                    InventoryServiceNew.gI().sendItemBags(player);
                    player.pointCauCa += 30;

                } else if (rd <= 5) {
                    Item ca2 = ItemService.gI().createNewItem((short) listCa2[Util.nextInt(listCa2.length)]);
                    ca2.itemOptions.add(new ItemOption(30, 0));
                    InventoryServiceNew.gI().addItemBag(player, ca2);
                    player.chat("|5|Vừa câu được " + ca2.template.name + ", cũng xịn ! ");
                    InventoryServiceNew.gI().sendItemBags(player);
                    player.pointCauCa += 20;

                } else if (rd <= 25) {
                    Item ca3 = ItemService.gI().createNewItem((short) listCa3[Util.nextInt(listCa1.length)]);
                    ca3.itemOptions.add(new ItemOption(30, 0));
                    InventoryServiceNew.gI().addItemBag(player, ca3);
                    player.chat("|4|Vừa câu được " + ca3.template.name + ", cũng thường thôi ! ");
                    InventoryServiceNew.gI().sendItemBags(player);
                    player.pointCauCa += 10;

                } else if (rd <= 50) {
                    Item ca4 = ItemService.gI().createNewItem((short) listCa4[Util.nextInt(listCa4.length)]);
                    ca4.itemOptions.add(new ItemOption(30, 0));
                    InventoryServiceNew.gI().addItemBag(player, ca4);
                    player.chat("|3|Vừa câu được " + ca4.template.name + ", tàm tạm ! ");
                    InventoryServiceNew.gI().sendItemBags(player);
                    player.pointCauCa += 5;

                } else {
                    Item ca5 = ItemService.gI().createNewItem((short) listCa5[Util.nextInt(listCa5.length)]);
                    
                    InventoryServiceNew.gI().addItemBag(player, ca5);
                    player.chat("Vừa câu được " + ca5.template.name + ", đen rồi !");
                    InventoryServiceNew.gI().sendItemBags(player);
                    player.pointCauCa += 1;

                }
                InventoryServiceNew.gI().sendItemBags(player);
            }
        }
        if (isEatMeal) {
            if (Util.canDoWithTime(lastTimeEatMeal, TIME_EAT_MEAL)) {
                isEatMeal = false;
                Service.gI().point(player);
            }
        }
        if (isEatBanhTho) {
            if (Util.canDoWithTime(lastTimeEatBanhTho, TIME_EAT_MEAL)) {
                isEatBanhTho = false;
                Service.gI().point(player);
            }
        }

        if (isEatRada) {
            if (Util.canDoWithTime(lastTimeEatRada, TIME_EAT_MEAL)) {
                isEatRada = false;
                Service.gI().point(player);
            }
        }
        if (isEatMatOng) {
            if (Util.canDoWithTime(lastTimeEatMatOng, TIME_EAT_MEAL)) {
                isEatMatOng = false;
                Service.gI().point(player);
            }
        }
        if (isEatCaiVot) {
            if (Util.canDoWithTime(lastTimeEatCaiVot, TIME_EAT_MEAL)) {
                isEatCaiVot = false;
                Service.gI().point(player);
            }
        }
        if (isEatHongDao) {
            if (Util.canDoWithTime(lastTimeEatHongDao, TIME_EAT_MEAL)) {
                isEatHongDao = false;
                Service.gI().point(player);
            }
        }
        if (isEatBanhTrung) {
            if (Util.canDoWithTime(lastTimeEatBanhTrung, TIME_EAT_MEAL)) {
                isEatBanhTrung = false;
                Service.gI().point(player);
            }
        }
        if (isEatHuyHieu) {
            if (Util.canDoWithTime(lastTimeEatHuyHieu, TIME_EAT_MEAL)) {
                isEatHuyHieu = false;
                Service.gI().point(player);
            }
        }
        if (isEatPotential) {
            if (Util.canDoWithTime(lastTimeEatPotential, TIME_EAT_POTENTIAL)) {
                isEatPotential = false;
                Service.gI().chat(player, "Mệt mỏi quá ");
                Service.gI().Send_Caitrang(player);
                Service.gI().point(player);
                Service.gI().Send_Info_NV(player);

            }
        }
        if (isEatBanh) {
            if (Util.canDoWithTime(lastTimeEatBanh, TIME_EAT_BANH)) {
                isEatBanh = false;
                Service.gI().point(player);
            }
        }
        if (isEatDaXanh) {
            if (Util.canDoWithTime(lastTimeEatDaXanh, TIME_EAT_MEAL)) {
                isEatDaXanh = false;
                Service.gI().point(player);
            }
        }
        if (isEatDaDo) {
            if (Util.canDoWithTime(lastTimeEatDaDo, TIME_EAT_MEAL)) {
                isEatDaDo = false;
                Service.gI().point(player);
            }
        }
        if (isEatDaTim) {
            if (Util.canDoWithTime(lastTimeEatDaTim, TIME_EAT_MEAL)) {
                isEatDaTim = false;
                Service.gI().point(player);
            }
        }
        if (isEatNhoXanh) {
            if (Util.canDoWithTime(lastTimeEatNhoXanh, TIME_EAT_MEAL)) {
                isEatNhoXanh = false;
                Service.gI().point(player);
            }
        }
        if (isEatMatTrang) {
            if (Util.canDoWithTime(lastTimeEatMatTrang, TIME_EAT_MEAL)) {
                isEatMatTrang = false;
                Service.gI().point(player);
            }
        }
        if (isEatKiemGo) {
            if (Util.canDoWithTime(lastTimeEatKiemGo, TIME_EAT_MEAL)) {
                isEatKiemGo = false;
                Service.gI().point(player);
            }
        }
        if (isEatNhoTim) {
            if (Util.canDoWithTime(lastTimeEatNhoTim, TIME_EAT_MEAL)) {
                isEatNhoTim = false;
                Service.gI().point(player);
            }
        }
        if (isEatLongDen) {
            if (Util.canDoWithTime(lastTimeEatLongDen, TIME_EAT_MEAL)) {
                isEatLongDen = false;
                Service.gI().point(player);
            }
        }
        if (isEatDuoiKhi) {
            if (Util.canDoWithTime(lastTimeEatDuoiKhi, TIME_EAT_MEAL)) {
                isEatDuoiKhi = false;
                Service.gI().chat(player, "Trở lại bình thường rồi ");
                Service.gI().Send_Caitrang(player);
                Service.gI().point(player);
                Service.gI().Send_Info_NV(player);
            }
        }
        if (isUseCuaRangMe) {
            if (Util.canDoWithTime(lastTimeCuaRangMe, TIME_ITEM)) {
                isUseCuaRangMe = false;
                Service.gI().point(player);
            }
        }
        if (isUseBoHuyet) {
            if (Util.canDoWithTime(lastTimeBoHuyet, TIME_ITEM)) {
                isUseBoHuyet = false;
                Service.gI().point(player);
            }
        }

        if (isUseBoKhi) {
            if (Util.canDoWithTime(lastTimeBoKhi, TIME_ITEM)) {
                isUseBoKhi = false;
                Service.gI().point(player);
            }
        }

        if (isUseGiapXen) {
            if (Util.canDoWithTime(lastTimeGiapXen, TIME_ITEM)) {
                isUseGiapXen = false;
            }
        }
        if (isUseCuongNo) {
            if (Util.canDoWithTime(lastTimeCuongNo, TIME_ITEM)) {
                isUseCuongNo = false;
                Service.gI().point(player);
            }
        }
        if (isUseAnDanh) {
            if (Util.canDoWithTime(lastTimeAnDanh, TIME_ITEM)) {
                isUseAnDanh = false;
            }
        }

        if (isUseBoHuyetSC) {
            if (Util.canDoWithTime(lastTimeBoHuyetSC, TIME_ITEM)) {
                isUseBoHuyetSC = false;
                Service.getInstance().point(player);
                // Service.getInstance().Send_Info_NV(this.player);
            }
        }
        if (isUseBoKhiSC) {
            if (Util.canDoWithTime(lastTimeBoKhiSC, TIME_ITEM)) {
                isUseBoKhiSC = false;
                Service.getInstance().point(player);
            }
        }
        if (isUseGiapXenSC) {
            if (Util.canDoWithTime(lastTimeGiapXenSC, TIME_ITEM)) {
                isUseGiapXenSC = false;
            }
        }
        if (isUseCuongNoSC) {
            if (Util.canDoWithTime(lastTimeCuongNoSC, TIME_ITEM)) {
                isUseCuongNoSC = false;
                Service.getInstance().point(player);
            }
        }
        if (isUseAnDanhSC) {
            if (Util.canDoWithTime(lastTimeAnDanhSC, TIME_ITEM)) {
                isUseAnDanhSC = false;
            }
        }
        if (isOpenPower) {
            if (Util.canDoWithTime(lastTimeOpenPower, TIME_OPEN_POWER)) {
                player.nPoint.limitPower++;
                if (player.nPoint.limitPower > NPoint.MAX_LIMIT) {
                    player.nPoint.limitPower = NPoint.MAX_LIMIT;
                }
                Service.gI().sendThongBao(player, "Giới hạn sức mạnh của bạn đã được tăng lên 1 bậc");
                isOpenPower = false;
            }
        }
        if (isUseMayDo) {
            if (Util.canDoWithTime(lastTimeUseMayDo, TIME_MAY_DO)) {
                isUseMayDo = false;
            }
        }
        if (isUseMayDo2) {
            if (Util.canDoWithTime(lastTimeUseMayDo2, TIME_MAY_DO2)) {
                isUseMayDo2 = false;
            }
        }
        if (isUseMayDo3) {
            if (Util.canDoWithTime(lastTimeUseMayDo3, TIME_MAY_DO3)) {
                isUseMayDo3 = false;
            }
        }
        if (isUseTDLT) {
            if (Util.canDoWithTime(lastTimeUseTDLT, timeTDLT)) {
                this.isUseTDLT = false;
                ItemTimeService.gI().sendCanAutoPlay(this.player);
            }
        }
        if (istrbsd) {
            if (Util.canDoWithTime(lastTimetrbsd, TIME_TRB)) {
                istrbsd = false;
                Service.gI().point(player);
            }
        }

        if (istrbhp) {
            if (Util.canDoWithTime(lastTimetrbhp, TIME_TRB)) {
                istrbhp = false;
                Service.gI().point(player);
            }
        }

        if (istrbki) {
            if (Util.canDoWithTime(lastTimetrbki, TIME_TRB)) {
                istrbki = false;
                Service.gI().point(player);
            }
        }
        if (istrbcrit) {
            if (Util.canDoWithTime(lastTimetrbcrit, TIME_TRB)) {
                istrbcrit = false;
                Service.gI().point(player);
            }
        }
    }

    public void dispose() {
        this.player = null;
    }
}
