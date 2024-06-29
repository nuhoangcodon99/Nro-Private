package red.services;

import static red.team.item.ItemTime.*;

import com.girlkun.network.io.Message;
import red.consts.ConstPlayer;
import red.team.item.Item;
import red.team.item.ItemTime;
import red.team.map.KhiGasHuyDiet.KhiGasHuyDiet;
import red.team.map.bando.BanDoKhoBau;
import red.team.map.doanhtrai.DoanhTrai;
import red.team.map.minuong.MiNuong;
import red.team.player.Fusion;
import red.team.player.Player;
import red.utils.Logger;

public class ItemTimeService {

    private static ItemTimeService i;

    public static ItemTimeService gI() {
        if (i == null) {
            i = new ItemTimeService();
        }
        return i;
    }

    // gửi cho client
    public void sendAllItemTime(Player player) {
        sendTextDoanhTrai(player);
        sendTextMiNuong(player);
        sendTextBanDoKhoBau(player);
        sendTextKhiGaHuyDiet(player);
        if (player.fusion.typeFusion == ConstPlayer.LUONG_LONG_NHAT_THE) {
            sendItemTime(player, player.gender == ConstPlayer.NAMEC ? 3901 : 3790,
                    (int) ((Fusion.TIME_FUSION - (System.currentTimeMillis() - player.fusion.lastTimeFusion)) / 1000));
        }
        if (player.itemTime.isUseCuaRangMe) {
            sendItemTime(player, 8060,
                    (int) ((TIME_ITEM - (System.currentTimeMillis() - player.itemTime.lastTimeCuaRangMe)) / 1000));
        }
        if (player.itemTime.isUseBoHuyet) {
            sendItemTime(player, 2755,
                    (int) ((TIME_ITEM - (System.currentTimeMillis() - player.itemTime.lastTimeBoHuyet)) / 1000));
        }
        if (player.itemTime.isUseBoKhi) {
            sendItemTime(player, 2756,
                    (int) ((TIME_ITEM - (System.currentTimeMillis() - player.itemTime.lastTimeBoKhi)) / 1000));
        }
        if (player.itemTime.isUseGiapXen) {
            sendItemTime(player, 2757,
                    (int) ((TIME_ITEM - (System.currentTimeMillis() - player.itemTime.lastTimeGiapXen)) / 1000));
        }
        if (player.itemTime.isUseCuongNo) {
            sendItemTime(player, 2754,
                    (int) ((TIME_ITEM - (System.currentTimeMillis() - player.itemTime.lastTimeCuongNo)) / 1000));
        }

        if (player.itemTime.isUseAnDanh) {
            sendItemTime(player, 2760,
                    (int) ((TIME_ITEM - (System.currentTimeMillis() - player.itemTime.lastTimeAnDanh)) / 1000));
        }
        if (player.itemTime.isUseBoHuyetSC) {
            sendItemTime(player, 10714,
                    (int) ((TIME_ITEM - (System.currentTimeMillis() - player.itemTime.lastTimeBoHuyetSC)) / 1000));
        }
        if (player.itemTime.isUseBoKhiSC) {
            sendItemTime(player, 10715,
                    (int) ((TIME_ITEM - (System.currentTimeMillis() - player.itemTime.lastTimeBoKhiSC)) / 1000));
        }
        if (player.itemTime.isUseGiapXenSC) {
            sendItemTime(player, 10712,
                    (int) ((TIME_ITEM - (System.currentTimeMillis() - player.itemTime.lastTimeGiapXenSC)) / 1000));
        }
        if (player.itemTime.isUseCuongNoSC) {
            sendItemTime(player, 10716,
                    (int) ((TIME_ITEM - (System.currentTimeMillis() - player.itemTime.lastTimeCuongNoSC)) / 1000));
        }
        if (player.itemTime.isUseAnDanhSC) {
            sendItemTime(player, 10717,
                    (int) ((TIME_ITEM - (System.currentTimeMillis() - player.itemTime.lastTimeAnDanhSC)) / 1000));
        }
        if (player.itemTime.isOpenPower) {
            sendItemTime(player, 3783,
                    (int) ((TIME_OPEN_POWER - (System.currentTimeMillis() - player.itemTime.lastTimeOpenPower))
                            / 1000));
        }
        if (player.itemTime.isUseMayDo) {
            sendItemTime(player, 2758,
                    (int) ((TIME_MAY_DO - (System.currentTimeMillis() - player.itemTime.lastTimeUseMayDo)) / 1000));
        }
        if (player.itemTime.isUseMayDo2) {// 2758 icon// cai nay time co cho bằng cái máy dò kia ko
            sendItemTime(player, 16004,
                    (int) ((TIME_MAY_DO2 - (System.currentTimeMillis() - player.itemTime.lastTimeUseMayDo2)) / 1000));
        }
        if (player.itemTime.isUseMayDo3) {// 2758 icon// cai nay time co cho bằng cái máy dò kia ko
            sendItemTime(player, 16173,
                    (int) ((TIME_MAY_DO3 - (System.currentTimeMillis() - player.itemTime.lastTimeUseMayDo3)) / 1000));
        }
        if (player.itemTime.isEatMeal) {
            sendItemTime(player, player.itemTime.iconMeal,
                    (int) ((TIME_EAT_MEAL - (System.currentTimeMillis() - player.itemTime.lastTimeEatMeal)) / 1000));
        }
        if (player.itemTime.isEatBanhTho) {
            sendItemTime(player, player.itemTime.iconBanhTho,
                    (int) ((TIME_EAT_MEAL - (System.currentTimeMillis() - player.itemTime.lastTimeEatBanhTho)) / 1000));
        }

        if (player.itemTime.isEatNhoXanh) {
            sendItemTime(player, player.itemTime.iconNhoXanh,
                    (int) ((TIME_EAT_MEAL - (System.currentTimeMillis() - player.itemTime.lastTimeEatNhoXanh)) / 1000));
        }
        if (player.itemTime.isEatNhoTim) {
            sendItemTime(player, player.itemTime.iconNhoTim,
                    (int) ((TIME_EAT_MEAL - (System.currentTimeMillis() - player.itemTime.lastTimeEatNhoTim)) / 1000));
        }
        if (player.itemTime.isEatLongDen) {
            sendItemTime(player, player.itemTime.iconLongDen,
                    (int) ((TIME_EAT_MEAL - (System.currentTimeMillis() - player.itemTime.lastTimeEatLongDen)) / 1000));
        }
        if (player.itemTime.isEatDuoiKhi) {
            sendItemTime(player, player.itemTime.iconDuoiKhi,
                    (int) ((TIME_EAT_MEAL - (System.currentTimeMillis() - player.itemTime.lastTimeEatDuoiKhi)) / 1000));
        }

        if (player.itemTime.isEatHongDao) {
            sendItemTime(player, player.itemTime.iconHongDao,
                    (int) ((TIME_EAT_MEAL - (System.currentTimeMillis() - player.itemTime.lastTimeEatHongDao)) / 1000));
        }
        if (player.itemTime.isEatBanhTrung) {
            sendItemTime(player, player.itemTime.iconBanhTrung,
                    (int) ((TIME_EAT_MEAL - (System.currentTimeMillis() - player.itemTime.lastTimeEatBanhTrung))
                            / 1000));
        }

        if (player.itemTime.isEatHuyHieu) {
            sendItemTime(player, player.itemTime.iconHuyHieu,
                    (int) ((TIME_EAT_MEAL - (System.currentTimeMillis() - player.itemTime.lastTimeEatHuyHieu)) / 1000));
        }

        if (player.itemTime.isEatPotential) {
            sendItemTime(player, player.itemTime.iconPotential,
                    (int) ((TIME_EAT_POTENTIAL - (System.currentTimeMillis() - player.itemTime.lastTimeEatPotential))
                            / 1000));
        }
        //
        if (player.itemTime.isEatDaDo) {
            sendItemTime(player, player.itemTime.iconDaDo,
                    (int) ((TIME_EAT_MEAL - (System.currentTimeMillis() - player.itemTime.lastTimeEatDaDo)) / 1000));
        }
        if (player.itemTime.isEatCaiVot) {
            sendItemTime(player, player.itemTime.iconCaiVot,
                    (int) ((TIME_EAT_MEAL - (System.currentTimeMillis() - player.itemTime.lastTimeEatCaiVot)) / 1000));
        }
        if (player.itemTime.isEatMatOng) {
            sendItemTime(player, player.itemTime.iconMatOng,
                    (int) ((TIME_EAT_MEAL - (System.currentTimeMillis() - player.itemTime.lastTimeEatMatOng)) / 1000));
        }
        if (player.itemTime.isEatRada) {
            sendItemTime(player, player.itemTime.iconRada,
                    (int) ((TIME_EAT_MEAL - (System.currentTimeMillis() - player.itemTime.lastTimeEatRada)) / 1000));
        }
        //
        if (player.itemTime.isEatDaXanh) {
            sendItemTime(player, player.itemTime.iconDaXanh,
                    (int) ((TIME_EAT_MEAL - (System.currentTimeMillis() - player.itemTime.lastTimeEatDaXanh)) / 1000));
        }
        //
        if (player.itemTime.isEatDaTim) {
            sendItemTime(player, player.itemTime.iconDaTim,
                    (int) ((TIME_EAT_MEAL - (System.currentTimeMillis() - player.itemTime.lastTimeEatDaTim)) / 1000));
        }
        //
        if (player.itemTime.isEatKiemGo) {
            sendItemTime(player, player.itemTime.iconKiemGo,
                    (int) ((TIME_EAT_MEAL - (System.currentTimeMillis() - player.itemTime.lastTimeEatKiemGo)) / 1000));
        }
        //
        if (player.itemTime.isEatMatTrang) {
            sendItemTime(player, player.itemTime.iconMatTrang,
                    (int) ((TIME_EAT_MEAL - (System.currentTimeMillis() - player.itemTime.lastTimeEatMatTrang))
                            / 1000));
        }
        //
        if (player.itemTime.isEatBanh) {
            sendItemTime(player, player.itemTime.iconBanh,
                    (int) ((TIME_EAT_BANH - (System.currentTimeMillis() - player.itemTime.lastTimeEatBanh)) / 1000));
        }
        //
        if (player.itemTime.isUseTDLT) {
            sendItemTime(player, 4387, player.itemTime.timeTDLT / 1000);
        }
        if (player.itemTime.istrbhp) {
            sendItemTime(player, 2281,
                    (int) ((TIME_TRB - (System.currentTimeMillis() - player.itemTime.lastTimetrbhp)) / 1000));
        }
        if (player.itemTime.istrbki) {
            sendItemTime(player, 2282,
                    (int) ((TIME_TRB - (System.currentTimeMillis() - player.itemTime.lastTimetrbki)) / 1000));
        }
        if (player.itemTime.istrbsd) {
            sendItemTime(player, 2280,
                    (int) ((TIME_TRB - (System.currentTimeMillis() - player.itemTime.lastTimetrbsd)) / 1000));
        }

    }

    // bật tđlt
    public void turnOnTDLT(Player player, Item item) {
        int min = 0;
        for (Item.ItemOption io : item.itemOptions) {
            if (io.optionTemplate.id == 1) {
                min = io.param;
                io.param = 0;
                break;
            }
        }
        player.itemTime.isUseTDLT = true;
        player.itemTime.timeTDLT = min * 60 * 1000;
        player.itemTime.lastTimeUseTDLT = System.currentTimeMillis();
        sendCanAutoPlay(player);
        sendItemTime(player, 4387, player.itemTime.timeTDLT / 1000);
        InventoryServiceNew.gI().sendItemBags(player);
    }

    // tắt tđlt
    public void turnOffTDLT(Player player, Item item) {
        player.itemTime.isUseTDLT = false;
        for (Item.ItemOption io : item.itemOptions) {
            if (io.optionTemplate.id == 1) {
                io.param += (short) ((player.itemTime.timeTDLT
                        - (System.currentTimeMillis() - player.itemTime.lastTimeUseTDLT)) / 60 / 1000);
                break;
            }
        }
        sendCanAutoPlay(player);
        removeItemTime(player, 4387);
        InventoryServiceNew.gI().sendItemBags(player);
    }

    public void sendCanAutoPlay(Player player) {
        Message msg;
        try {
            msg = new Message(-116);
            msg.writer().writeByte(player.itemTime.isUseTDLT ? 1 : 0);
            player.sendMessage(msg);
        } catch (Exception e) {
            Logger.logException(ItemTimeService.class, e);
        }
    }

    public void sendTextDoanhTrai(Player player) {
        if (player.clan != null && !player.clan.haveGoneDoanhTrai
                && player.clan.timeOpenDoanhTrai != 0) {
            int secondPassed = (int) ((System.currentTimeMillis() - player.clan.timeOpenDoanhTrai) / 1000);
            int secondsLeft = (DoanhTrai.TIME_DOANH_TRAI / 1000) - secondPassed;
            sendTextTime(player, DOANH_TRAI, "Doanh trại độc nhãn", secondsLeft);
        }
    }

    public void sendTextKhiGaHuyDiet(Player player) {
        if (player.clan != null && !player.clan.haveGoneKhiGasHuyDiet
                && player.clan.timeOpenKhiGaHuyDiet != 0) {
            int secondPassed = (int) ((System.currentTimeMillis() - player.clan.timeOpenKhiGaHuyDiet) / 1000);
            int secondsLeft = (KhiGasHuyDiet.TIME_KHI_GA_HUY_DIET / 1000) - secondPassed;
            sendTextTime(player, KHI_GAS_HUY_DIET, "Khí gas hủy diệt: ", secondsLeft);
        }
    }

    public void sendTextMiNuong(Player player) {
        if (player.clan != null && !player.clan.haveGoneMiNuong
                && player.clan.timeOpenMiNuong != 0) {
            int secondPassed = (int) ((System.currentTimeMillis() - player.clan.timeOpenMiNuong) / 1000);
            int secondsLeft = (MiNuong.TIME_MI_NUONG / 1000) - secondPassed;
            sendTextTime(player, MI_NUONG, "Phó bản mị nương", secondsLeft);
        }
    }

    public void sendTextBanDoKhoBau(Player player) {
        if (player.clan != null
                && player.clan.timeOpenBanDoKhoBau != 0) {
            int secondPassed = (int) ((System.currentTimeMillis() - player.clan.timeOpenBanDoKhoBau) / 1000);
            int secondsLeft = (BanDoKhoBau.TIME_BAN_DO_KHO_BAU / 1000) - secondPassed;
            sendTextTime(player, BAN_DO_KHO_BAU, "Bản đồ kho báu", secondsLeft);
        }
    }

    public void sendTextCauCa(Player player) {

        int secondPassed = (int) ((System.currentTimeMillis() - player.lastTimeCauCa) / 1000);
        int secondsLeft = 10  - secondPassed;
        sendTextTime(player, CAU_CA, "Câu cá: ", secondsLeft);

    }

    public void removeTextCauCa(Player player) {
        removeTextTime(player, CAU_CA);
    }

    public void removeTextDoanhTrai(Player player) {
        removeTextTime(player, DOANH_TRAI);
    }

    public void removeTextMiNuong(Player player) {
        removeTextTime(player, MI_NUONG);
    }

    public void removeTextBanDoKhoBau(Player player) {
        removeTextTime(player, BAN_DO_KHO_BAU);
    }

    public void removeTextKhiGas(Player player) {
        removeTextTime(player, KHI_GAS_HUY_DIET);
    }

    public void removeTextTime(Player player, byte id) {
        sendTextTime(player, id, "", 0);
    }

    public void sendTextTime(Player player, byte id, String text, int seconds) {
        Message msg;
        try {
            msg = new Message(65);
            msg.writer().writeByte(id);
            msg.writer().writeUTF(text);
            msg.writer().writeShort(seconds);
            player.sendMessage(msg);
            msg.cleanup();
        } catch (Exception e) {
        }
    }

    public void sendItemTime(Player player, int itemId, int time) {
        Message msg;
        try {
            msg = new Message(-106);
            msg.writer().writeShort(itemId);
            msg.writer().writeShort(time);
            player.sendMessage(msg);
            msg.cleanup();
        } catch (Exception e) {
        }
    }

    public void removeItemTime(Player player, int itemTime) {
        sendItemTime(player, itemTime, 0);
        Service.gI().point(player);
        Service.gI().Send_Caitrang(player);
        Service.getInstance().sendFlagBag(player);
    }

}
