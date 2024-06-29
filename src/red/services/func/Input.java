package red.services.func;

import com.girlkun.database.GirlkunDB;
import com.girlkun.network.io.Message;
import com.girlkun.network.session.ISession;
import red.consts.ConstNpc;
import red.jdbc.daos.PlayerDAO;
import red.team.item.Item;
import red.team.item.Item.ItemOption;
import red.team.map.Zone;
import red.team.npc.Npc;
import red.team.npc.NpcManager;
import red.team.player.Inventory;
import red.team.player.Player;
import red.team.server.Client;
import red.team.server.Maintenance;
import red.team.server.Manager;
import red.team.server.ServerNotify;
import red.services.GiftService;
import red.services.InventoryServiceNew;
import red.services.ItemService;
import red.services.NapThe;
import red.services.NpcService;
import red.services.Service;
import red.utils.Util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Input {

    public static String LOAI_THE;
    public static String MENH_GIA;
    private static final Map<Integer, Object> PLAYER_ID_OBJECT = new HashMap<Integer, Object>();

    public static final int CHANGE_PASSWORD = 500;
    public static final int GIFT_CODE = 501;
        public static final int DONATE_CS = 529;
    public static final int FIND_PLAYER = 502;
    public static final int CHANGE_NAME = 503;
    public static final int CHOOSE_LEVEL_BDKB = 504;
    public static final int SEND_ITEM = 5127;
    public static final int NAP_THE = 505;
    public static final int CHANGE_NAME_BY_ITEM = 506;
        public static final int GIVE_ITEM = 527;
    public static final int GIVE_IT = 507;
    public static final int SEND_ITEM_OP = 512;

    public static final int QUY_DOI_COIN = 508;
    public static final int QUY_DOI_HONG_NGOC = 509;
    public static final int NAP_TIEN = 600;
    public static final int THONG_BAO = 604;
    public static final int THONG_BAO_RIENG = 605;
    public static final int TANG_QUA_MOC_NAP = 606;
    public static final int TAI = 510;
    public static final int XIU = 511;
    public static final int QUY_DOI_DA_SANG_THE = 609;
    public static final int UseGold = 3;

    public static final int CHOOSE_LEVEL_KGHD = 601;
    public static final byte NUMERIC = 0;
    public static final byte ANY = 1;
    public static final byte PASSWORD = 2;

    private static Input intance;

    private Input() {

    }

    public static Input gI() {
        if (intance == null) {
            intance = new Input();
        }
        return intance;
    }

      public void doInput(Player player, Message msg) {
        try {
            String[] text = new String[msg.reader().readByte()];
            for (int i = 0; i < text.length; i++) {
                text[i] = msg.reader().readUTF();
            }
            switch (player.iDMark.getTypeInput()) {
                case GIVE_IT:
                    if (player.isAdmin()) {
                        int idItemBuff = Integer.parseInt(text[1]);
                        int quantityItemBuff = Integer.parseInt(text[2]);
                        Player pBuffItem = Client.gI().getPlayer(text[0]);
                        if (pBuffItem != null) {
                            String txtBuff = "Buff to player: " + pBuffItem.name + "\b";
                            if (idItemBuff == -1) {
                                pBuffItem.inventory.gold = Math.min(pBuffItem.inventory.gold + (long) quantityItemBuff, Inventory.LIMIT_GOLD);
                                txtBuff += quantityItemBuff + " vàng\b";
                                Service.getInstance().sendMoney(player);
                            } else if (idItemBuff == -2) {
                                pBuffItem.inventory.gem = Math.min(pBuffItem.inventory.gem + quantityItemBuff, 2000000000);
                                txtBuff += quantityItemBuff + " ngọc\b";
                                Service.getInstance().sendMoney(player);
                            } else if (idItemBuff == -3) {
                                pBuffItem.inventory.ruby = Math.min(pBuffItem.inventory.ruby + quantityItemBuff, 2000000000);
                                txtBuff += quantityItemBuff + " ngọc khóa\b";
                                Service.getInstance().sendMoney(player);
                            } else {
                                Item itemBuffTemplate = ItemService.gI().createNewItem((short) idItemBuff);
                                itemBuffTemplate.quantity = quantityItemBuff;
                                InventoryServiceNew.gI().addItemBag(pBuffItem, itemBuffTemplate);
                                InventoryServiceNew.gI().sendItemBags(pBuffItem);
                                txtBuff += "x" + quantityItemBuff + " " + itemBuffTemplate.template.name + "\b";
                            }
                            NpcService.gI().createTutorial(player, 24, txtBuff);
                            if (player.id != pBuffItem.id) {
                                NpcService.gI().createTutorial(pBuffItem, 24, txtBuff);
                            }
                        } else {
                            Service.getInstance().sendThongBao(player, "Player không online");
                        }
                        break;
                    }
                    break;
                case SEND_ITEM_OP:
                    if (player.isAdmin()) {
                        int idItemBuff = Integer.parseInt(text[1]);
                        int idOptionBuff = Integer.parseInt(text[2]);
                        int slOptionBuff = Integer.parseInt(text[3]);
                        int slItemBuff = Integer.parseInt(text[4]);
                        Player pBuffItem = Client.gI().getPlayer(text[0]);
                        if (pBuffItem != null) {
                            String txtBuff = "Buff to player: " + pBuffItem.name + "\b";
                            if (idItemBuff == -1) {
                                pBuffItem.inventory.gold = Math.min(pBuffItem.inventory.gold + (long) slItemBuff, Inventory.LIMIT_GOLD);
                                txtBuff += slItemBuff + " vàng\b";
                                Service.getInstance().sendMoney(player);
                            } else if (idItemBuff == -2) {
                                pBuffItem.inventory.gem = Math.min(pBuffItem.inventory.gem + slItemBuff, 2000000000);
                                txtBuff += slItemBuff + " ngọc\b";
                                Service.getInstance().sendMoney(player);
                            } else if (idItemBuff == -3) {
                                pBuffItem.inventory.ruby = Math.min(pBuffItem.inventory.ruby + slItemBuff, 2000000000);
                                txtBuff += slItemBuff + " ngọc khóa\b";
                                Service.getInstance().sendMoney(player);
                            } else {
                                Item itemBuffTemplate = ItemService.gI().createNewItem((short) idItemBuff);
                                itemBuffTemplate.itemOptions.add(new Item.ItemOption(idOptionBuff, slOptionBuff));
                                itemBuffTemplate.quantity = slItemBuff;
                                txtBuff += "x" + slItemBuff + " " + itemBuffTemplate.template.name + "\b";
                                InventoryServiceNew.gI().addItemBag(pBuffItem, itemBuffTemplate);
                                InventoryServiceNew.gI().sendItemBags(pBuffItem);
                            }
                            NpcService.gI().createTutorial(player, 24, txtBuff);
                            if (player.id != pBuffItem.id) {
                                NpcService.gI().createTutorial(player, 24, txtBuff);
                            }
                        } else {
                            Service.getInstance().sendThongBao(player, "Player không online");
                        }
                        break;
                    }
                    break;

                case CHANGE_PASSWORD:
                    Service.gI().changePassword(player, text[0], text[1], text[2]);
                    break;
                case GIFT_CODE:
                    GiftService.gI().giftCode(player, text[0]);
                    break;
                case FIND_PLAYER:
                    Player pl = Client.gI().getPlayer(text[0]);
                    if (pl != null) {
                        NpcService.gI().createMenuConMeo(player, ConstNpc.MENU_FIND_PLAYER, -1, "Ngài muốn..?",
                                new String[] { "Đi tới\n" + pl.name, "Gọi " + pl.name + "\ntới đây", "Đổi tên", "Ban",
                                        "Kick", "Ấy Cho Nó" },
                                pl);
                    } else {
                        Service.gI().sendThongBao(player, "Người chơi không tồn tại hoặc đang offline");
                    }
                    break;
                case NAP_TIEN:
                    String username = text[0];
                    Integer playerId = Integer.parseInt(text[1]);
                    Player playerNap = Client.gI().getPlayerByUser(playerId);
                    Integer soTienNap = Integer.parseInt(text[2]);
                    Integer soTienNapCheck = Integer.parseInt(text[3]);
                    if (playerNap != null && playerNap.getSession().uu.equals(username)) {
                        if (playerNap.getSession().userId == playerId) {
                            if (soTienNap.equals(soTienNapCheck)) {

                                try {
                                    GirlkunDB.executeUpdate(
                                            "UPDATE account SET vnd = vnd + ? WHERE id = ? AND username = ?",
                                            soTienNap, playerNap.getSession().userId, playerNap.getSession().uu);

                                    GirlkunDB.executeUpdate(
                                            "UPDATE account SET tongnap = tongnap + ? WHERE id = ? AND username = ?",
                                            soTienNap, playerId, playerNap.getSession().uu);

                                    Service.gI().sendThongBaoOK(player,
                                            "Đã nạp thành công " + soTienNap + " cho người chơi " + playerNap.name);
                                   
                                    Service.gI().sendThongBaoOK(Client.gI().getPlayer(playerNap.name),
                                    "Vui lòng đăng nhập lại!");   
                                    Service.gI().sendThongBao(Client.gI().getPlayer(playerNap.name),
                                            "Đã nạp thành công " + soTienNap); 
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                            } else {
                                Service.gI().sendThongBao(player, "Số tiền không trùng khớp");
                            }
                        } else {
                            Service.gI().sendThongBao(player, "Id không trùng khớp");
                        }
                    } else {
                        Service.gI().sendThongBao(player, "Người chơi không tồn tại hoặc đang offline");
                    }
                    break;
                case THONG_BAO:
                    String contentServer = text[0];
                    Integer typeNotiServer = Integer.parseInt(text[1]);
                    switch (typeNotiServer) {
                        case 1:
                            Service.getInstance().sendThongBaoAllPlayer(contentServer);
                            break;
                        case 2:
                            ServerNotify.gI().notify(contentServer);
                            break;
                        default:
                            Service.getInstance().sendThongBaoAllPlayer(contentServer);
                            break;
                    }
                    break;
                case THONG_BAO_RIENG:
                    Player playerNotice = Client.gI().getPlayer(text[2]);
                    Integer typeNotiPrivate = Integer.parseInt(text[1]);
                    String contentPrivate = text[0];
                    if (playerNotice != null) {
                        switch (typeNotiPrivate) {
                            case 1:
                                Service.gI().sendThongBao(playerNotice, contentPrivate);
                                break;
                            case 2:
                                Service.gI().sendThongBaoFromAdmin(player, contentPrivate);
                                break;
                            default:
                                Service.gI().sendThongBaoFromAdmin(player, contentPrivate);
                                break;
                        }
                    } else {
                        Service.gI().sendThongBao(player, "Người chơi không tồn tại hoặc đang offline");
                    }
                    break;
                case TANG_QUA_MOC_NAP:
                    Player playerNhanQua = Client.gI().getPlayer(text[2]);
                    Integer typeQua = Integer.parseInt(text[1]);
                    if (playerNhanQua != null) {
                        switch (typeQua) {
                            case 20:
                                InventoryServiceNew.gI().addItemBag(playerNhanQua,
                                        ItemService.gI().createNewItem((short) 1322));
                                break;
                            case 50:
                                InventoryServiceNew.gI().addItemBag(playerNhanQua,
                                        ItemService.gI().createNewItem((short) 1323));
                                break;
                            case 100:
                                InventoryServiceNew.gI().addItemBag(playerNhanQua,
                                        ItemService.gI().createNewItem((short) 1324));
                                break;
                        }
                        Service.gI().sendThongBaoFromAdmin(playerNhanQua,
                                "|7|Bạn vừa nhận quà mốc nạp " + typeQua + "K từ ADMIN !");
                        Service.gI().sendThongBaoFromAdmin(player,
                                "|7|Bạn vừa tặng quà mốc nạp " + typeQua + "K cho " + playerNhanQua.name);
                        InventoryServiceNew.gI().sendItemBags(playerNhanQua);
                        Service.getInstance().sendMoney(playerNhanQua);
                    } else {
                        Service.gI().sendThongBao(player, "Người chơi không tồn tại hoặc đang offline");
                    }
                    break;
                case CHANGE_NAME: {
                    Player plChanged = (Player) PLAYER_ID_OBJECT.get((int) player.id);
                    if (plChanged != null) {
                        if (GirlkunDB.executeQuery("select * from player where name = ?", text[0]).next()) {
                            Service.gI().sendThongBao(player, "Tên nhân vật đã tồn tại");
                        } else {
                            plChanged.name = text[0];
                            GirlkunDB.executeUpdate("update player set name = ? where id = ?", plChanged.name,
                                    plChanged.id);
                            Service.gI().player(plChanged);
                            Service.gI().Send_Caitrang(plChanged);
                            Service.gI().sendFlagBag(plChanged);
                            Zone zone = plChanged.zone;
                            ChangeMapService.gI().changeMap(plChanged, zone, plChanged.location.x,
                                    plChanged.location.y);
                            Service.gI().sendThongBao(plChanged,
                                    "Chúc mừng bạn đã có cái tên mới đẹp đẽ hơn tên ban đầu");
                            Service.gI().sendThongBao(player, "Đổi tên người chơi thành công");
                        }
                    }
                }
                    break;
                case CHANGE_NAME_BY_ITEM: {
                    if (player != null) {
                        if (GirlkunDB.executeQuery("select * from player where name = ?", text[0]).next()) {
                            Service.gI().sendThongBao(player, "Tên nhân vật đã tồn tại");
                            createFormChangeNameByItem(player);
                        } else {
                            Item theDoiTen = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 2006);
                            if (theDoiTen == null) {
                                Service.gI().sendThongBao(player, "Không tìm thấy thẻ đổi tên");
                            } else {
                                InventoryServiceNew.gI().subQuantityItemsBag(player, theDoiTen, 1);
                                player.name = text[0];
                                GirlkunDB.executeUpdate("update player set name = ? where id = ?", player.name,
                                        player.id);
                                Service.gI().player(player);
                                Service.gI().Send_Caitrang(player);
                                Service.gI().sendFlagBag(player);
                                Zone zone = player.zone;
                                ChangeMapService.gI().changeMap(player, zone, player.location.x, player.location.y);
                                Service.gI().sendThongBao(player,
                                        "Chúc mừng bạn đã có cái tên mới đẹp đẽ hơn tên ban đầu");
                            }
                        }
                    }
                }
                    break;

                case XIU:
                    int sotvxiu = Integer.valueOf(text[0]);
                    if (sotvxiu <= 0) {
                        Service.getInstance().sendThongBaoOK(player, "?");
                    } else {
                        TransactionService.gI().cancelTrade(player);
                        Item tv2 = null;
                        for (Item item : player.inventory.itemsBag) {
                            if (item.isNotNullItem() && item.template.id == 457) {
                                tv2 = item;
                                break;
                            }
                        }
                        try {
                            if (tv2 != null && tv2.quantity >= sotvxiu) {
                                InventoryServiceNew.gI().subQuantityItemsBag(player, tv2, sotvxiu);
                                InventoryServiceNew.gI().sendItemBags(player);
                                int TimeSeconds = 10;
                                Service.gI().sendThongBao(player, "Chờ 10 giây để biết kết quả.");
                                while (TimeSeconds > 0) {
                                    TimeSeconds--;
                                    Thread.sleep(1000);
                                }
                                int x = Util.nextInt(1, 6);
                                int y = Util.nextInt(1, 6);
                                int z = Util.nextInt(1, 6);
                                int tong = (x + y + z);
                                if (4 <= (x + y + z) && (x + y + z) <= 10) {
                                    if (player != null) {
                                        Item tvthang = ItemService.gI().createNewItem((short) 457);
                                        tvthang.quantity = (int) Math.round(sotvxiu * 1.8);
                                        int sovthang = tvthang.quantity;
                                        InventoryServiceNew.gI().addItemBag(player, tvthang);
                                        InventoryServiceNew.gI().sendItemBags(player);
                                        Service.gI().sendThongBaoOK(player,
                                                "Kết quả" + "\nSố hệ thống quay ra : " + x + " " +
                                                        y + " " + z + "\nTổng là : " + tong + "\nBạn đã cược : "
                                                        + sotvxiu +
                                                        " thỏi vàng vào Xỉu" + "\nKết quả : Xỉu" + " \n Bạn vừa bú "
                                                        + sovthang + " thỏi vàng" + "\n\nVề bờ");
                                        return;
                                    }
                                } else if (x == y && x == z) {
                                    if (player != null) {
                                        Service.gI().sendThongBaoOK(player,
                                                "Kết quả" + "Số hệ thống quay ra : " + x + " " + y + " " + z
                                                        + "\nTổng là : " + tong + "\nBạn đã cược : " + sotvxiu
                                                        + " thỏi vàng vào Xỉu" + "\nKết quả : Tam hoa"
                                                        + "\nCòn cái nịt.");
                                        return;
                                    }
                                } else if ((x + y + z) > 10) {
                                    if (player != null) {
                                        Service.gI().sendThongBaoOK(player, "Kết quả" + "\nSố hệ thống quay ra là :" +
                                                " " + x + " " + y + " " + z + "\nTổng là : " + tong + "\nBạn đã cược : "
                                                + sotvxiu + " thỏi vàng vào Xỉu" + "\nKết quả : Tài"
                                                + "\nCòn cái nịt.");
                                        return;
                                    }
                                }
                            } else {
                                Service.gI().sendThongBao(player, "Bạn không đủ thỏi vàng để chơi.");
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            Service.gI().sendThongBao(player, "Lỗi.");
                        }
                    }
                    break;
                case TAI:
                    int sotvtai = Integer.valueOf(text[0]);
                    if (sotvtai <= 0) {
                        Service.getInstance().sendThongBao(player, "?");
                    } else {
                        TransactionService.gI().cancelTrade(player);
                        Item tv1 = null;

                        for (Item item : player.inventory.itemsBag) {
                            if (item.isNotNullItem() && item.template.id == 457) {
                                tv1 = item;
                                break;
                            }
                        }
                        try {
                            if (tv1 != null && tv1.quantity >= sotvtai) {
                                InventoryServiceNew.gI().subQuantityItemsBag(player, tv1, sotvtai);
                                InventoryServiceNew.gI().sendItemBags(player);
                                int TimeSeconds = 10;
                                Service.gI().sendThongBao(player, "Chờ 10 giây để biết kết quả.");
                                while (TimeSeconds > 0) {
                                    TimeSeconds--;
                                    Thread.sleep(1000);
                                }
                                int x = Util.nextInt(1, 6);
                                int y = Util.nextInt(1, 6);
                                int z = Util.nextInt(1, 6);
                                int tong = (x + y + z);
                                if (4 <= (x + y + z) && (x + y + z) <= 10) {
                                    if (player != null) {
                                        Service.gI().sendThongBaoOK(player, "Kết quả" + "\nSố hệ thống quay ra là :" +
                                                " " + x + " " + y + " " + z + "\nTổng là : " + tong + "\nBạn đã cược : "
                                                + sotvtai + " thỏi vàng vào Tài" + "\nKết quả : Xỉu" + "\nMày Mất "
                                                + sotvtai + " Thòi Vàng");
                                        return;
                                    }
                                } else if (x == y && x == z) {
                                    if (player != null) {
                                        Service.gI().sendThongBaoOK(player,
                                                "Kết quả" + "Số hệ thống quay ra : " + x + " " + y + " " + z
                                                        + "\nTổng là : " + tong + "\nBạn đã cược : " + sotvtai
                                                        + " thỏi vàng vào Xỉu" + "\nKết quả : Tam hoa"
                                                        + "\nCòn cái nịt.");
                                        return;
                                    }
                                } else if ((x + y + z) > 10) {

                                    if (player != null) {
                                        Item tvthang = ItemService.gI().createNewItem((short) 457);
                                        tvthang.quantity = (int) Math.round(sotvtai * 1.8);
                                        int sovthang = tvthang.quantity;
                                        InventoryServiceNew.gI().addItemBag(player, tvthang);
                                        InventoryServiceNew.gI().sendItemBags(player);
                                        Service.gI().sendThongBaoOK(player,
                                                "Kết quả" + "\nSố hệ thống quay ra : " + x + " " +
                                                        y + " " + z + "\nTổng là : " + tong + "\nBạn đã cược : "
                                                        + sotvtai +
                                                        " thỏi vàng vào Tài" + "\nKết quả : Tài" + " \n Bạn vừa bú "
                                                        + sovthang + " thỏi vàng" + "\n\nVề bờ");
                                        return;
                                    }
                                }
                            } else {
                                Service.gI().sendThongBao(player, "Bạn không đủ thỏi vàng để chơi.");
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            Service.gI().sendThongBao(player, "Lỗi.");
                        }
                    }
                    break;
                    
                          case DONATE_CS:
                    int csbang = Integer.parseInt(text[0]);
                    Item cscanhan = InventoryServiceNew.gI().findItemBag(player, 1192);
                   // if (cscanhan == null || player.clanMember.memberPoint < 1) {
                   //     Service.gI().sendThongBao(player, "Số điểm capsule bản thân không đủ để thực hiện");
                   //     break;
                  //  }  
                        InventoryServiceNew.gI().subQuantityItemsBag(player, cscanhan, csbang);
                        InventoryServiceNew.gI().sendItemBags(player);
                        Service.gI().sendMoney(player);
                  //     player.clanMember.memberPoint -= csbang;
                        player.clan.capsuleClan += csbang;
                        player.clanMember.clanPoint += csbang;
                        //player.clan.reloadClanMember();
                        Service.gI().sendThongBao(player, "bạn đã quyên góp " + csbang + "");
                        break;
                case UseGold:
                    int Gold = Integer.parseInt(text[0]);
                    Item thoivangchange = null;
                    for (Item item : player.inventory.itemsBag) {
                        if (item.isNotNullItem() && item.template.id == 457) {
                            thoivangchange = item;
                            break;
                        }
                    }
                    if(thoivangchange.quantity >= Gold)
                    {
                        long goldsum =  (long)(500000000L* (long)Gold);
                        if(player.inventory.gold + goldsum >  Inventory.LIMIT_GOLD)
                        {
                            Service.gI().sendThongBao(player, "Số vàng quy đổi vượt quá giới hạn 100 tỉ");
                        }
                        else {
                        player.inventory.gold += (long)(500000000L* (long)Gold);
                        InventoryServiceNew.gI().subQuantityItemsBag(player, thoivangchange, Gold);
                        InventoryServiceNew.gI().sendItemBags(player);
                        Service.gI().sendMoney(player);
                        Service.gI().sendThongBao(player, "Đổi Thành Công");}
                    }
                    else 
                    {
                        Service.gI().sendThongBao(player, "Số lượng không đủ");
                    }
                    break;
                        
                    
                case CHOOSE_LEVEL_BDKB:
                    int level = Integer.parseInt(text[0]);
                    if (level >= 1 && level <= 110) {
                        Npc npc = NpcManager.getByIdAndMap(ConstNpc.QUY_LAO_KAME, player.zone.map.mapId);
                        if (npc != null) {
                            npc.createOtherMenu(player, ConstNpc.MENU_ACCEPT_GO_TO_BDKB,
                                    "Con có chắc chắn muốn tới bản đồ kho báu cấp độ " + level + "?",
                                    new String[] { "Đồng ý", "Từ chối" }, level);
                        }
                    } else {
                        Service.getInstance().sendThongBao(player, "Không thể thực hiện");
                    }

                    // BanDoKhoBauService.gI().openBanDoKhoBau(player, (byte) );
                    break;
                 case SEND_ITEM:
                    if (player.isAdmin()) {
                        int idItemBuff = Integer.parseInt(text[1]);
                        int quantityItemBuff = Integer.parseInt(text[2]);
                        Player pBuffItem = Client.gI().getPlayer(text[0]);
                        if (pBuffItem != null) {
                            String txtBuff = "Buff to player: " + pBuffItem.name + "\b";
                            if (idItemBuff == -1) {
                                pBuffItem.inventory.gold = Math.min(pBuffItem.inventory.gold + (long) quantityItemBuff, Inventory.LIMIT_GOLD);
                                txtBuff += quantityItemBuff + " vàng\b";
                                Service.getInstance().sendMoney(player);
                            } else if (idItemBuff == -2) {
                                pBuffItem.inventory.gem = Math.min(pBuffItem.inventory.gem + quantityItemBuff, 2000000000);
                                txtBuff += quantityItemBuff + " ngọc\b";
                                Service.getInstance().sendMoney(player);
                            } else if (idItemBuff == -3) {
                                pBuffItem.inventory.ruby = Math.min(pBuffItem.inventory.ruby + quantityItemBuff, 2000000000);
                                txtBuff += quantityItemBuff + " ngọc khóa\b";
                                Service.getInstance().sendMoney(player);
                            } else {
                                Item itemBuffTemplate = ItemService.gI().createNewItem((short) idItemBuff);
                                itemBuffTemplate.quantity = quantityItemBuff;
                                InventoryServiceNew.gI().addItemBag(pBuffItem, itemBuffTemplate);
                                InventoryServiceNew.gI().sendItemBags(pBuffItem);
                                txtBuff += "x" + quantityItemBuff + " " + itemBuffTemplate.template.name + "\b";
                            }
                            NpcService.gI().createTutorial(player, 24, txtBuff);
                            if (player.id != pBuffItem.id) {
                                NpcService.gI().createTutorial(pBuffItem, 24, txtBuff);
                            }
                        } else {
                            Service.getInstance().sendThongBao(player, "Player không online");
                        }
                        break;
                    }
                    break;
                case CHOOSE_LEVEL_KGHD:
                    int level2 = Integer.parseInt(text[0]);
                    if (level2 >= 1 && level2 <= 110) {
                        Npc npc = NpcManager.getByIdAndMap(ConstNpc.POPO, player.zone.map.mapId);
                        if (npc != null) {
                            npc.createOtherMenu(player, ConstNpc.MENU_ACCEPT_GO_TO_KGHD,
                                    "Con có chắc chắn muốn tới khí gas hủy diệt cấp độ " + level2 + "?",
                                    new String[] { "Đồng ý", "Từ chối" }, level2);
                        }
                    } else {
                        Service.gI().sendThongBao(player, "Không thể thực hiện");
                    }
                    break;
                case QUY_DOI_DA_SANG_THE:
                    int ratioDST = 1000;
                    int DSTTrade = Integer.parseInt(text[0]);
                    if (DSTTrade <= 0 || DSTTrade > 500) {
                        Service.getInstance().sendThongBao(player, "Quá giới hạn mỗi lần chỉ được 500");
                    } else if (DSTTrade * ratioDST <= player.getSession().vnd) {
                        if (InventoryServiceNew.gI().getCountEmptyBag(player) > 0) {
                            Item dast = ItemService.gI().createNewItem((short) 345);
                            dast.quantity = DSTTrade*Manager.QUY_DOI_SERVER;
                            dast.itemOptions.add(new ItemOption(30, 0));
                            InventoryServiceNew.gI().addItemBag(player, dast);
                            Service.gI().sendThongBao(player,
                                    "Bạn vừa nhận được " + DSTTrade*Manager.QUY_DOI_SERVER + " " + dast.template.name);
                            player.getSession().vnd -= DSTTrade * ratioDST;
                            
                            GirlkunDB.executeUpdate("UPDATE account SET vnd = vnd - ? WHERE id = ? AND username = ?",
                                    DSTTrade * ratioDST, player.getSession().userId, player.getSession().uu);
                            InventoryServiceNew.gI().sendItemBags(player);
                            Service.getInstance().sendMoney(player);
                        } else {
                            Service.getInstance().sendThongBao(player, "Hành trang không đủ chỗ trống!");
                        }

                    } else {
                        Service.getInstance().sendThongBao(player,
                                "Số dư của bạn là " + player.getSession().vnd + " không đủ để quy"
                                        + " đổi " + DSTTrade + " đá sáng thế" + " " + ", bạn cần thêm "
                                        + (DSTTrade * ratioDST - player.getSession().vnd) + " số dư");

                    }

                    break;
                case QUY_DOI_HONG_NGOC: {
                    int ratioRuby = 2000; // tỉ lệ đổi tv
                    int coinRuby = 1; // là cái loz
                    int rubyTrade = Integer.parseInt(text[0]);
                    Item daSangThe = null;
                    Integer daSangTheQuantity = 0;
                    try {
                        daSangThe = InventoryServiceNew.gI().findItemBag(player, 345);
                        if (daSangThe != null) {
                            daSangTheQuantity = daSangThe.quantity;
                        }

                    } catch (Exception e) {
                        // throw new RuntimeException(e);
                    }
                    if (rubyTrade <= 0 || rubyTrade > 500) {
                        Service.getInstance().sendThongBao(player, "Quá giới hạn mỗi lần chỉ được 500");
                    } else if (daSangTheQuantity >= rubyTrade) {
                        InventoryServiceNew.gI().subQuantityItemsBag(player, daSangThe, rubyTrade);
                        short ruby = 1443;
                        Item rubyAdd = ItemService.gI().createNewItem(ruby, rubyTrade * ratioRuby);// x2000
                        InventoryServiceNew.gI().addItemBag(player, rubyAdd);
                        InventoryServiceNew.gI().sendItemBags(player);
                        Service.getInstance().sendThongBaoOK(player, "bạn nhận được " + rubyTrade * ratioRuby
                                + " " + rubyAdd.template.name);
                        GirlkunDB.executeUpdate("update account set active = 1 where id = ? and username = ?",
                                player.getSession().userId, player.getSession().uu);
                        player.iDMark.setActive(true);
                        player.pointPvp += rubyTrade;
                        if (player.isPl()) {
                            player.achievement.plusCount(11, rubyTrade * 1000);
                        }
                    } else {
                        Service.getInstance().sendThongBao(player,
                                "Số đá của bạn là " + daSangTheQuantity + " không đủ để quy"
                                        + " đổi " + rubyTrade + " hồng ngọc" + " " + ", bạn cần thêm "
                                        + (daSangTheQuantity - rubyTrade) * -1 + " đá sáng thế");

                    }
                }
                    break;
                case QUY_DOI_COIN:
                    int ratioGold = 4; // tỉ lệ đổi tv
                    int coinGold = 1; // là cái loz
                    int goldTrade = Integer.parseInt(text[0]);
                    Item daSangThe = null;
                    Integer daSangTheQuantity = 0;
                    try {
                        daSangThe = InventoryServiceNew.gI().findItemBag(player, 345);
                        if (daSangThe != null) {
                            daSangTheQuantity = daSangThe.quantity;
                        }

                    } catch (Exception e) {
                        // throw new RuntimeException(e);
                    }
                    if (goldTrade <= 0 || goldTrade > 500) {
                        Service.getInstance().sendThongBao(player, "Quá giới hạn mỗi lần chỉ được 500");
                    } else if (daSangTheQuantity >= goldTrade) {

                        InventoryServiceNew.gI().subQuantityItemsBag(player, daSangThe, goldTrade);
                        Item thoiVang = ItemService.gI().createNewItem((short) 457, goldTrade * 4);// x4
                        InventoryServiceNew.gI().addItemBag(player, thoiVang);
                        InventoryServiceNew.gI().sendItemBags(player);
                        Service.getInstance().sendThongBaoOK(player, "bạn nhận được " + goldTrade * ratioGold
                                + " " + thoiVang.template.name);
                        GirlkunDB.executeUpdate("update account set active = 1 where id = ? and username = ?",
                                player.getSession().userId, player.getSession().uu);
                        player.iDMark.setActive(true);
                        player.pointPvp += goldTrade;
                        if (player.isPl()) {
                            player.achievement.plusCount(11, goldTrade * 1000);
                        }
                    } else {
                        Service.getInstance().sendThongBao(player,
                                "Số đá của bạn là " + daSangTheQuantity + " không đủ để quy"
                                        + " đổi " + goldTrade + " thỏi vàng" + " " + ", bạn cần thêm "
                                        + (daSangTheQuantity - goldTrade) * -1 + " đá sáng thế nữa");
                    }
                    break;

            }
        } catch (Exception e) {
        }
    }

    public void createForm(Player pl, int typeInput, String title, SubInput... subInputs) {
        pl.iDMark.setTypeInput(typeInput);
        Message msg;
        try {
            msg = new Message(-125);
            msg.writer().writeUTF(title);
            msg.writer().writeByte(subInputs.length);
            for (SubInput si : subInputs) {
                msg.writer().writeUTF(si.name);
                msg.writer().writeByte(si.typeInput);
            }
            pl.sendMessage(msg);
            msg.cleanup();
        } catch (Exception e) {
        }
    }

    public void createForm(ISession session, int typeInput, String title, SubInput... subInputs) {
        Message msg;
        try {
            msg = new Message(-125);
            msg.writer().writeUTF(title);
            msg.writer().writeByte(subInputs.length);
            for (SubInput si : subInputs) {
                msg.writer().writeUTF(si.name);
                msg.writer().writeByte(si.typeInput);
            }
            session.sendMessage(msg);
            msg.cleanup();
        } catch (Exception e) {
        }
    }

    public void createFormChangePassword(Player pl) {
        createForm(pl, CHANGE_PASSWORD, "Quên Mật Khẩu", new SubInput("Nhập mật khẩu đã quên", PASSWORD),
                new SubInput("Mật khẩu mới", PASSWORD),
                new SubInput("Nhập lại mật khẩu mới", PASSWORD));
    }

    public void createFormGiveItem(Player pl) {
        createForm(pl, GIVE_IT, "Tặng vật phẩm", new SubInput("Tên", ANY), new SubInput("Id Item", ANY),
                new SubInput("Số lượng", ANY));
    }

    public void createFormGiftCode(Player pl) {
        createForm(pl, GIFT_CODE, "Gift code ", new SubInput("Gift-code", ANY));
    }

    public void createFormFindPlayer(Player pl) {
        createForm(pl, FIND_PLAYER, "Tìm kiếm người chơi", new SubInput("Tên người chơi", ANY));
    }

    public void createFormNapTien(Player pl) {
        createForm(pl, NAP_TIEN, "Nạp tiền cho người chơi", new SubInput("Tài khoản người chơi", ANY),
                new SubInput("ID nguời chơi", NUMERIC), new SubInput("Số tiền cần nạp", NUMERIC),
                new SubInput("Nhập lại số tiền cần nạp", NUMERIC));
    }

    public void createFormThongBao(Player pl) {
        createForm(pl, THONG_BAO, "Thông báo Server", new SubInput("Thông báo", ANY),
                new SubInput("Kiểu: 1-NORMAL,2-SERVER-NOTI", NUMERIC));
    }

    public void createFormThongBaoRieng(Player pl) {
        createForm(pl, THONG_BAO_RIENG, "Thông báo riêng tư", new SubInput("Thông báo", ANY),
                new SubInput("Kiểu: 1-NORMAL,2-OK", NUMERIC), new SubInput("Tên người chơi", ANY));
    }

      public void DonateCsbang(Player pl) {
        createForm(pl, DONATE_CS, "Donate (Quyên góp capsule bang của bạn vào bang)", new SubInput("Nhập số lượng muốn quyên góp", NUMERIC));
    }
    public void createFormTangQuaMocNap(Player pl) {
        createForm(pl, TANG_QUA_MOC_NAP, "Tặng quà mốc nạp", new SubInput("ID", NUMERIC),
                new SubInput("MỐC: 20, 50, 100", NUMERIC), new SubInput("Tên người chơi", ANY));
    }

    public void TAI(Player pl) {
        createForm(pl, TAI, "Chọn số thỏi vàng đặt tài", new SubInput("Số thỏi vàng", ANY));
    }

    public void XIU(Player pl) {
        createForm(pl, XIU, "Chọn số thỏi vàng đặt xỉu", new SubInput("Số thỏi vàng", ANY));
    }

    public void createFormNapThe(Player pl, String loaiThe, String menhGia) {
        LOAI_THE = loaiThe;
        MENH_GIA = menhGia;
        createForm(pl, NAP_THE, "Nạp thẻ", new SubInput("Số Seri", ANY), new SubInput("Mã thẻ", ANY));
    }
    public void createFormUseGold(Player pl) {
        createForm(pl, UseGold, "Nhập số lượng cần dùng", new SubInput("1 thỏi vàng dùng sẽ được 500tr vàng", NUMERIC));
    }

    
    
    public void createFormSenditem1(Player pl) {
        createForm(pl, SEND_ITEM_OP, "SEND Vật Phẩm Option",
                new SubInput("Tên người chơi", ANY),
                new SubInput("ID Trang Bị", NUMERIC),
                new SubInput("ID Option", NUMERIC),
                new SubInput("Param", NUMERIC),
                new SubInput("Số lượng", NUMERIC));
    }

    public void createFormQDTV(Player pl) {

        createForm(pl, QUY_DOI_COIN, "Quy đổi thỏi vàng, giới hạn đổi không quá 500 ",
                new SubInput("Nhập số lượng muốn đổi", NUMERIC));
    }

    public void createFormQDHN(Player pl) {

        createForm(pl, QUY_DOI_HONG_NGOC, "Quy đổi hồng ngọc", new SubInput("Nhập số lượng muốn đổi", NUMERIC));
    }

    public void createFormQDDST(Player pl) {

        createForm(pl, QUY_DOI_DA_SANG_THE, "Quy đổi đá sáng thế", new SubInput("Nhập số lượng muốn đổi", NUMERIC));
    }

    public void createFormChangeName(Player pl, Player plChanged) {
        PLAYER_ID_OBJECT.put((int) pl.id, plChanged);
        createForm(pl, CHANGE_NAME, "Đổi tên " + plChanged.name, new SubInput("Tên mới", ANY));
    }

    public void createFormChangeNameByItem(Player pl) {
        createForm(pl, CHANGE_NAME_BY_ITEM, "Đổi tên " + pl.name, new SubInput("Tên mới", ANY));
    }

    public void createFormChooseLevelBDKB(Player pl) {
        createForm(pl, CHOOSE_LEVEL_BDKB, "Chọn cấp độ", new SubInput("Cấp độ (1-110)", NUMERIC));
    }

    public void createFormChooseLevelKGHD(Player pl) {
        createForm(pl, CHOOSE_LEVEL_KGHD, "Chọn cấp độ", new SubInput("Cấp độ (1-110)", NUMERIC));
    }
    public void createFormSenditem(Player pl) {
        createForm(pl, SEND_ITEM, "SEND ITEM",
                new SubInput("Tên người chơi", ANY),
                new SubInput("ID item", NUMERIC),
                new SubInput("Số lượng", NUMERIC));
    }



    public static class SubInput {

        private String name;
        private byte typeInput;

        public SubInput(String name, byte typeInput) {
            this.name = name;
            this.typeInput = typeInput;
        }
    }

}
