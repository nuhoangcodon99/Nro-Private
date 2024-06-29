package red.services.func;

import red.services.MapService;
import red.services.NpcService;
import red.services.PlayerService;
import red.services.NgocRongNamecService;
import red.services.ItemService;
import red.services.PetService;
import red.services.RewardService;
import red.services.Service;
import red.services.InventoryServiceNew;
import red.services.ItemTimeService;
import red.services.TaskService;
import red.card.Card;
import red.card.RadarCard;
import red.card.RadarService;
import red.consts.ConstMap;
import red.consts.ConstNpc;
import red.consts.ConstPlayer;
import red.team.item.Item;
import red.team.item.Item.ItemOption;
import red.team.map.Zone;
import red.team.npc.NpcFactory;
import red.team.player.Inventory;
import red.team.player.Player;
import red.team.skill.Skill;
import red.server.io.MySession;
import red.utils.Logger;
import red.utils.SkillUtil;
import red.utils.TimeUtil;
import red.utils.Util;
import com.girlkun.network.io.Message;
import red.team.item.Item;
import red.team.item.Item.ItemOption;
import lombok.var;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class UseItem {

    private static final int ITEM_BOX_TO_BODY_OR_BAG = 0;
    private static final int ITEM_BAG_TO_BOX = 1;
    private static final int ITEM_BODY_TO_BOX = 3;
    private static final int ITEM_BAG_TO_BODY = 4;
    private static final int ITEM_BODY_TO_BAG = 5;
    private static final int ITEM_BAG_TO_PET_BODY = 6;
    private static final int ITEM_BODY_PET_TO_BAG = 7;

    private static final byte DO_USE_ITEM = 0;
    private static final byte DO_THROW_ITEM = 1;
    private static final byte ACCEPT_THROW_ITEM = 2;
    private static final byte ACCEPT_USE_ITEM = 3;
    public static final int[][][] LIST_ITEM_CLOTHES = {
            // áo , quần , găng ,giày,rada
            // td -> nm -> xd
            { { 0, 33, 3, 34, 136, 137, 138, 139, 230, 231, 232, 233, 555 },
                    { 6, 35, 9, 36, 140, 141, 142, 143, 242, 243, 244, 245, 556 },
                    { 21, 24, 37, 38, 144, 145, 146, 147, 254, 255, 256, 257, 562 },
                    { 27, 30, 39, 40, 148, 149, 150, 151, 266, 267, 268, 269, 563 },
                    { 12, 57, 58, 59, 184, 185, 186, 187, 278, 279, 280, 281, 561 } },
            { { 1, 41, 4, 42, 152, 153, 154, 155, 234, 235, 236, 237, 557 },
                    { 7, 43, 10, 44, 156, 157, 158, 159, 246, 247, 248, 249, 558 },
                    { 22, 46, 25, 45, 160, 161, 162, 163, 258, 259, 260, 261, 564 },
                    { 28, 47, 31, 48, 164, 165, 166, 167, 270, 271, 272, 273, 565 },
                    { 12, 57, 58, 59, 184, 185, 186, 187, 278, 279, 280, 281, 561 } },
            { { 2, 49, 5, 50, 168, 169, 170, 171, 238, 239, 240, 241, 559 },
                    { 8, 51, 11, 52, 172, 173, 174, 175, 250, 251, 252, 253, 560 },
                    { 23, 53, 26, 54, 176, 177, 178, 179, 262, 263, 264, 265, 566 },
                    { 29, 55, 32, 56, 180, 181, 182, 183, 274, 275, 276, 277, 567 },
                    { 12, 57, 58, 59, 184, 185, 186, 187, 278, 279, 280, 281, 561 } }
    };

    private static UseItem instance;

    private UseItem() {

    }

    public static UseItem gI() {
        if (instance == null) {
            instance = new UseItem();
        }
        return instance;
    }

    public void getItem(MySession session, Message msg) {
        Player player = session.player;

        TransactionService.gI().cancelTrade(player);
        try {
            int type = msg.reader().readByte();
            int index = msg.reader().readByte();
            if (index == -1) {
                return;
            }
            switch (type) {
                case ITEM_BOX_TO_BODY_OR_BAG:
                    InventoryServiceNew.gI().itemBoxToBodyOrBag(player, index);
                    TaskService.gI().checkDoneTaskGetItemBox(player);
                    break;
                case ITEM_BAG_TO_BOX:
                    InventoryServiceNew.gI().itemBagToBox(player, index);
                    break;
                case ITEM_BODY_TO_BOX:
                    InventoryServiceNew.gI().itemBodyToBox(player, index);
                    break;
                case ITEM_BAG_TO_BODY:
                    InventoryServiceNew.gI().itemBagToBody(player, index);
                    break;
                case ITEM_BODY_TO_BAG:
                    InventoryServiceNew.gI().itemBodyToBag(player, index);
                    break;
                case ITEM_BAG_TO_PET_BODY:
                    InventoryServiceNew.gI().itemBagToPetBody(player, index);
                    break;
                case ITEM_BODY_PET_TO_BAG:
                    InventoryServiceNew.gI().itemPetBodyToBag(player, index);
                    break;
            }
            player.setClothes.setup();
            if (player.pet != null) {
                player.pet.setClothes.setup();
            }
            player.setClanMember();
            Service.gI().point(player);
        } catch (Exception e) {
            Logger.logException(UseItem.class, e);

        }
    }

    public void testItem(Player player, Message _msg) {
        TransactionService.gI().cancelTrade(player);
        Message msg;
        try {
            byte type = _msg.reader().readByte();
            int where = _msg.reader().readByte();
            int index = _msg.reader().readByte();
            System.out.println("type: " + type);
            System.out.println("where: " + where);
            System.out.println("index: " + index);
        } catch (Exception e) {
            Logger.logException(UseItem.class, e);
        }
    }

    public void doItem(Player player, Message _msg) {
        TransactionService.gI().cancelTrade(player);
        Message msg;
        byte type;
        try {
            type = _msg.reader().readByte();
            int where = _msg.reader().readByte();
            int index = _msg.reader().readByte();
            // System.out.println(type + " " + where + " " + index);
            switch (type) {
                case DO_USE_ITEM:
                    if (player != null && player.inventory != null) {
                        if (index != -1) {
                            Item item = player.inventory.itemsBag.get(index);
                            if (item.isNotNullItem()) {
                                if (item.template.type == 7) {
                                    msg = new Message(-43);
                                    msg.writer().writeByte(type);
                                    msg.writer().writeByte(where);
                                    msg.writer().writeByte(index);
                                    msg.writer().writeUTF("Bạn chắc chắn học "
                                            + player.inventory.itemsBag.get(index).template.name + "?");
                                    player.sendMessage(msg);
                                } else {
                                    UseItem.gI().useItem(player, item, index);
                                }
                            }
                        } else {
                            this.eatPea(player);
                        }
                    }
                    break;
                case DO_THROW_ITEM:
                    if (!(player.zone.map.mapId == 21 || player.zone.map.mapId == 22 || player.zone.map.mapId == 23)) {
                        Item item = null;
                        if (where == 0) {
                            item = player.inventory.itemsBody.get(index);
                        } else {
                            item = player.inventory.itemsBag.get(index);
                        }
                        msg = new Message(-43);
                        msg.writer().writeByte(type);
                        msg.writer().writeByte(where);
                        msg.writer().writeByte(index);
                        msg.writer().writeUTF("Bạn chắc chắn muốn vứt " + item.template.name + "?");
                        player.sendMessage(msg);
                    } else {
                        Service.gI().sendThongBao(player, "Không thể thực hiện");
                    }
                    break;
                case ACCEPT_THROW_ITEM:
                    InventoryServiceNew.gI().throwItem(player, where, index);
                    Service.gI().point(player);
                    InventoryServiceNew.gI().sendItemBags(player);
                    break;
                case ACCEPT_USE_ITEM:
                    UseItem.gI().useItem(player, player.inventory.itemsBag.get(index), index);
                    break;
            }
        } catch (Exception e) {
            // Logger.logException(UseItem.class, e);
        }
    }

    private void useItem(Player pl, Item item, int indexBag) {
        if (item.template.strRequire <= pl.nPoint.power) {
            switch (item.template.type) {
                case 7: // sách học, nâng skill
                    learnSkill(pl, item);
                    break;
                case 33:
                    UseCard(pl, item);
                    break;
                case 6: // đậu thần
                    this.eatPea(pl);
                    break;
                case 12: // ngọc rồng các loại
                    controllerCallRongThan(pl, item);
                    controllerCalltrb(pl, item);
                    controllerCallRongDen(pl, item);
                    controllerCallRongBang(pl, item);
                    break;
                case 23: // thú cưỡi mới
                case 24: // thú cưỡi cũ
                    InventoryServiceNew.gI().itemBagToBody(pl, indexBag);
                    break;
                case 11: // item bag
                    InventoryServiceNew.gI().itemBagToBody(pl, indexBag);
                    Service.gI().sendFlagBag(pl);
                    break;
                case 21:
                    if (pl.newpet != null) {
                        ChangeMapService.gI().exitMap(pl.newpet);
                        pl.newpet.dispose();
                        pl.newpet = null;
                    }
                    InventoryServiceNew.gI().itemBagToBody(pl, indexBag);

                    PetService.Pet2(pl, item.template.head, item.template.body, item.template.leg, item.template.name);
                    Service.getInstance().point(pl);
                    break;
                case 35:
                case 36:
                case 99:
                case 100:
                case 101:
                    Service.gI().removeTitle(pl);
                    InventoryServiceNew.gI().itemBagToBody(pl, indexBag);
                    Service.gI().sendFlagBag(pl);
                    break;
                case 72: {
                    InventoryServiceNew.gI().itemBagToBody(pl, indexBag);
                    Service.gI().sendPetFollow(pl, (short) (item.template.iconID - 1));
                    break;
                }
                default:
                    switch (item.template.id) {
                        case 992:
                            pl.type = 1;
                            pl.maxTime = 5;
                            Service.gI().Transport(pl);
                            break;

                        case 570:
                            openWoodChest(pl, item);
                            break;
                        case 361:
                            if (pl.idNRNM != -1) {
                                Service.gI().sendThongBao(pl, "Không thể thực hiện");
                                return;
                            }
                            pl.idGo = (short) Util.nextInt(0, 6);
                            NpcService.gI().createMenuConMeo(pl, ConstNpc.CONFIRM_TELE_NAMEC, -1,
                                    "1 Sao (" + NgocRongNamecService.gI().getDis(pl, 0, (short) 353) + " m)\n2 Sao ("
                                            + NgocRongNamecService.gI().getDis(pl, 1, (short) 354) + " m)\n3 Sao ("
                                            + NgocRongNamecService.gI().getDis(pl, 2, (short) 355) + " m)\n4 Sao ("
                                            + NgocRongNamecService.gI().getDis(pl, 3, (short) 356) + " m)\n5 Sao ("
                                            + NgocRongNamecService.gI().getDis(pl, 4, (short) 357) + " m)\n6 Sao ("
                                            + NgocRongNamecService.gI().getDis(pl, 5, (short) 358) + " m)\n7 Sao ("
                                            + NgocRongNamecService.gI().getDis(pl, 6, (short) 359) + " m)",
                                    "Đến ngay\nViên " + (pl.idGo + 1) + " Sao\n50 ngọc", "Kết thức");
                            InventoryServiceNew.gI().subQuantityItemsBag(pl, item, 1);
                            InventoryServiceNew.gI().sendItemBags(pl);
                            break;
                        case 211: // nho tím
                        case 212: // nho xanh
                            eatGrapes(pl, item);
                            break;
                        case 1105:// hop qua skh, item 2002 xd
                            UseItem.gI().Hopts(pl, item);
                            break;
                        case 1431:// hop qua skh, item 2002 xd
                            UseItem.gI().Hoptl(pl, item);
                            break;
                        case 1432:// hop qua skh, item 2002 xd
                            UseItem.gI().Hophd(pl, item);
                            break;
                        // case 342:
                        // case 343:
                        // case 344:
                        // case 345:
                        // if (pl.zone.items.stream().filter(it -> it != null && it.itemTemplate.type ==
                        // 22).count() < 5) {
                        // Service.gI().DropVeTinh(pl, item, pl.zone, pl.location.x, pl.location.y);
                        // InventoryServiceNew.gI().subQuantityItemsBag(pl, item, 1);
                        // } else {
                        // Service.gI().sendThongBao(pl, "Đặt ít thôi con");
                        // }
                        // break;
                        // ve tinh nhưng mà tao xóa rồi thay bằng item sư kiện , đừng bật lên lỗi chết
                        // mẹ m
                        case 380: // cskb
                            openCSKB(pl, item);
                            break;
                        case 381: // cuồng nộ
                        case 382: // bổ huyết
                        case 383: // bổ khí
                        case 384: // giáp xên
                        case 385: // ẩn danh
                        case 379: // máy dò capsule
                        case 2037: // máy dò cosmos
                        case 2105: // máy dò cosmos
                        case 663: // bánh pudding
                        case 664: // xúc xíc
                        case 665: // kem dâu
                        case 666: // mì ly
                        case 667: // sushi
                            // banhtrungthu
                        case 465:
                        case 466:
                        case 890:
                        case 891:

                        case 1347:
                        case 1348:
                        case 1349:

                            //
                        case 1099:
                        case 1100:
                        case 1101:
                        case 1102:
                        case 1103:
                        case 1193: // bánh pudding
                        case 1194: // xúc xíc
                        case 1195:
                        case 1201:// da nguc tu
                        case 1202:
                        case 1203:
                        case 1183:
                        case 1184:
                        case 753:
                        case 1261:
                        case 1300:
                        case 1301:
                        case 1302:
                            // case 1262: kiem go
                        case 1221:
                        case 1222:
                        case 1238:
                        case 1237:
                            useItemTime(pl, item);
                            break;
                        case 521: // tdlt
                            useTDLT(pl, item);
                            break;
                        case 454: // bông tai
                            UseItem.gI().usePorata(pl);
                            break;
                        case 193: // gói 10 viên capsule
                            InventoryServiceNew.gI().subQuantityItemsBag(pl, item, 1);
                        case 194: // capsule đặc biệt
                            openCapsuleUI(pl);
                            break;
                        case 401: // đổi đệ tử
                            changePet(pl, item);
                            break;
                        case 1108: // đổi đệ tử
                            changePetBerus(pl, item);
                            break;
                        case 2124: // mabu
                            changePetMabu(pl, item);
                            break;
                        case 2125: // đổi đệ tử
                            changePetNgokhong(pl, item);
                            break;
                        case 722: // đổi đệ tử
                            changePetGoku(pl, item);
                             case 1986://hop qua skh, item 2002 xd
                            UseItem.gI().Hoptl(pl, item);
                            break;
                        case 1355:
                            changePetNguyetThan(pl, item);
                            break;
                        case 1356:
                            changePetNhatThan(pl, item);
                            break;
                        case 1357:
                            changePetLucy(pl, item);
                            break;
                        case 402: // sách nâng chiêu 1 đệ tử
                        case 403: // sách nâng chiêu 2 đệ tử
                        case 404: // sách nâng chiêu 3 đệ tử
                        case 759: // sách nâng chiêu 4 đệ tử
                        case 2123:// sach nang chieu 5 de tu
                            upSkillPet(pl, item);
                            break;
                        case 921:// bông tai
                            pl.fusion.isBTC2 = item.template.id == 921;
                            UseItem.gI().usePorata2(pl);
                            break;
                        case 2074: // bông tai c3
                            UseItem.gI().usePorata3(pl);
                            break;
                        case 2075: // bông tai c4
                            UseItem.gI().usePorata4(pl);
                            break;
                        case 2000:// hop qua skh, item 2000 td
                        case 2001:// hop qua skh, item 2001 nm
                        case 2002:// hop qua skh, item 2002 xd
                            UseItem.gI().ItemSKH(pl, item);
                            break;
                        case 1312:
                            pl.isTitleUse = true;
                            Service.gI().point(pl);
                            Service.gI().sendTitle(pl, item.template.part);
                            break;
                        case 1399:
                            useCanCau(pl, item);
                            break;
                        case 2003:// hop qua skh, item 2003 td
                        case 2004:// hop qua skh, item 2004 nm
                        case 2005:// hop qua skh, item 2005 xd
                            UseItem.gI().ItemDHD(pl, item);
                            break;
                        case 1322:
                        case 1323:
                        case 1324:
                            openQuaMocNap(pl, item);
                            break;
                        /* */
                        case 1345:
                            openQuaThuong(pl, item);
                            break;
                        case 1346:
                            openQuaVip(pl, item);
                            break;
                        case 736:
                            ItemService.gI().OpenItem736(pl, item);
                            break;
                        case 457:
                            Input.gI().createFormUseGold(pl);
                            break;
                        case 627:
                            openPhieuCaiTrangHaiTac(pl, item);
                            break;
                        case 1178:
                            openPhieuCaiTrangTanjiro(pl, item);
                            break;
                        case 1179:
                            openPhieuCaiTrangBojack(pl, item);
                            break;
                        case 1180:
                            openPhieuCaiTrangDoremon(pl, item);
                            break;
                        case 1181:
                            openPhieuCaiTrangVothuat(pl, item);
                            break;
                        case 1182:
                            openPhieuCaiTrangTieudoi(pl, item);
                            break;

                        case 987:
                            Service.gI().sendThongBao(pl, "Bảo vệ trang bị không bị rớt cấp"); // đá bảo vệ
                            break;
                        case 1120:
                            useItemHopQuaTanThu(pl, item);
                            break;
                        case 569:
                            useItemQuaDua(pl, item);
                            break;
                        case 2006:
                            Input.gI().createFormChangeNameByItem(pl);
                            break;
                        case 1282:
                            useItemGiaHanCaiTrang(pl, item);
                            break;
                        case 1131:
                            if (pl.pet == null) {
                                Service.gI().sendThongBao(pl, "Ngươi làm gì có đệ tử?");
                                break;
                            }

                            if (pl.pet.playerSkill.skills.get(1).skillId != -1
                                    && pl.pet.playerSkill.skills.get(2).skillId != -1) {
                                pl.pet.openSkill2();
                                pl.pet.openSkill3();
                                InventoryServiceNew.gI().subQuantityItem(pl.inventory.itemsBag, item, 1);
                                InventoryServiceNew.gI().sendItemBags(pl);
                                Service.gI().sendThongBao(pl, "Đã đổi thành công chiêu 2 3 đệ tử");
                            } else {
                                Service.gI().sendThongBao(pl, "Ít nhất đệ tử ngươi phải có chiêu 2 chứ!");
                            }
                            break;
                        case 2122:
                            if (pl.pet == null) {
                                Service.gI().sendThongBao(pl, "Ngươi làm gì có đệ tử?");
                                break;
                            }

                            if (pl.pet.playerSkill.skills.get(3).skillId != -1
                                    && pl.pet.playerSkill.skills.get(4).skillId != -1) {
                                pl.pet.openSkill4();
                                pl.pet.openSkill5();
                                InventoryServiceNew.gI().subQuantityItem(pl.inventory.itemsBag, item, 1);
                                InventoryServiceNew.gI().sendItemBags(pl);
                                Service.gI().sendThongBao(pl, "Đã đổi thành công chiêu 4 5 đệ tử");
                            } else {
                                Service.gI().sendThongBao(pl, "Ít nhất đệ tử ngươi phải có chiêu 4 chứ!");
                            }
                            break;

                        case 2027:
                        case 2028: {
                            if (InventoryServiceNew.gI().getCountEmptyBag(pl) == 0) {
                                Service.gI().sendThongBao(pl, "Hành trang không đủ chỗ trống");
                            } else {
                                InventoryServiceNew.gI().subQuantityItemsBag(pl, item, 1);
                                Item linhThu = ItemService.gI().createNewItem((short) Util.nextInt(2019, 2026));
                                linhThu.itemOptions.add(new Item.ItemOption(50, 10));
                                linhThu.itemOptions.add(new Item.ItemOption(77, 5));
                                linhThu.itemOptions.add(new Item.ItemOption(103, 5));
                                linhThu.itemOptions.add(new Item.ItemOption(95, 3));
                                linhThu.itemOptions.add(new Item.ItemOption(96, 3));
                                InventoryServiceNew.gI().addItemBag(pl, linhThu);
                                InventoryServiceNew.gI().sendItemBags(pl);
                                Service.gI().sendThongBao(pl,
                                        "Chúc mừng bạn nhận được Linh thú " + linhThu.template.name);
                            }
                            break;

                        }
                    }
                    break;
            }
            InventoryServiceNew.gI().sendItemBags(pl);
        } else {
            Service.gI().sendThongBaoOK(pl, "Sức mạnh không đủ yêu cầu");
        }
    }

    private void openWoodChest(Player pl, Item item) {
        int time = (int) TimeUtil.diffDate(new Date(), new Date(item.createTime), TimeUtil.DAY);
        if (time != 0) {
            Item itemReward = null;
            int param = item.itemOptions.size();
            int gold = 0;
            int[] listItem = { 441, 442, 443, 444, 445, 446, 447, 220, 221, 222, 223, 224, 225, 16, 17, 18, 457 };
            int[] listClothesReward;
            int[] listItemReward;
            String text = "Bạn nhận được\n";
            if (param < 8) {
                gold = 100000 * param;
                listClothesReward = new int[] { randClothes(param) };
                listItemReward = Util.pickNRandInArr(listItem, 3);
            } else if (param < 10) {
                gold = 250000 * param;
                listClothesReward = new int[] { randClothes(param), randClothes(param) };
                listItemReward = Util.pickNRandInArr(listItem, 4);
            } else {
                gold = 500000 * param;
                listClothesReward = new int[] { randClothes(param), randClothes(param), randClothes(param) };
                listItemReward = Util.pickNRandInArr(listItem, 6);
                int ruby = Util.nextInt(10, 50);
                pl.inventory.ruby += ruby;
                pl.textRuongGo.add(text + "|1| " + ruby + " Hồng Ngọc");
            }
            for (int i : listClothesReward) {
                itemReward = ItemService.gI().createNewItem((short) i);
                RewardService.gI().initBaseOptionClothes(itemReward.template.id, itemReward.template.type,
                        itemReward.itemOptions);
                RewardService.gI().initStarOption(itemReward, new RewardService.RatioStar[] {
                        new RewardService.RatioStar((byte) 1, 1, 2), new RewardService.RatioStar((byte) 2, 1, 3),
                        new RewardService.RatioStar((byte) 3, 1, 4), new RewardService.RatioStar((byte) 4, 1, 5), });
                InventoryServiceNew.gI().addItemBag(pl, itemReward);
                pl.textRuongGo.add(text + itemReward.getInfoItem());
            }
            for (int i : listItemReward) {
                itemReward = ItemService.gI().createNewItem((short) i);
                // RewardService.gI().initBaseOptionSaoPhaLe(itemReward);
                itemReward.quantity = Util.nextInt(1, 5);
                InventoryServiceNew.gI().addItemBag(pl, itemReward);
                pl.textRuongGo.add(text + itemReward.getInfoItem());
            }
            if (param == 11) {
                itemReward = ItemService.gI().createNewItem((short) 457);
                itemReward.quantity = Util.nextInt(1, 5);
                InventoryServiceNew.gI().addItemBag(pl, itemReward);
                pl.textRuongGo.add(text + itemReward.getInfoItem());
            }
            NpcService.gI().createMenuConMeo(pl, ConstNpc.RUONG_GO, -1,
                    "Bạn nhận được\n|1|+" + Util.numberToMoney(gold) + " vàng", "OK [" + pl.textRuongGo.size() + "]");
            InventoryServiceNew.gI().subQuantityItemsBag(pl, item, 1);
            pl.inventory.addGold(gold);
            InventoryServiceNew.gI().sendItemBags(pl);
            PlayerService.gI().sendInfoHpMpMoney(pl);
        } else {
            Service.getInstance().sendThongBao(pl, "Vui lòng đợi 24h");
        }
    }

    private int randClothes(int level) {
        return LIST_ITEM_CLOTHES[Util.nextInt(0, 2)][Util.nextInt(0, 4)][level - 1];
    }

    public void UseCard(Player pl, Item item) {
        RadarCard radarTemplate = RadarService.gI().RADAR_TEMPLATE.stream().filter(c -> c.Id == item.template.id)
                .findFirst().orElse(null);
        if (radarTemplate == null)
            return;
        if (radarTemplate.Require != -1) {
            RadarCard radarRequireTemplate = RadarService.gI().RADAR_TEMPLATE.stream()
                    .filter(r -> r.Id == radarTemplate.Require).findFirst().orElse(null);
            if (radarRequireTemplate == null)
                return;
            Card cardRequire = pl.Cards.stream().filter(r -> r.Id == radarRequireTemplate.Id).findFirst().orElse(null);
            if (cardRequire == null || cardRequire.Level < radarTemplate.RequireLevel) {
                Service.gI().sendThongBao(pl, "Bạn cần sưu tầm " + radarRequireTemplate.Name + " ở cấp độ "
                        + radarTemplate.RequireLevel + " mới có thể sử dụng thẻ này");
                return;
            }
        }
        Card card = pl.Cards.stream().filter(r -> r.Id == item.template.id).findFirst().orElse(null);
        if (card == null) {
            Card newCard = new Card(item.template.id, (byte) 1, radarTemplate.Max, (byte) -1, radarTemplate.Options);
            if (pl.Cards.add(newCard)) {
                RadarService.gI().RadarSetAmount(pl, newCard.Id, newCard.Amount, newCard.MaxAmount);
                RadarService.gI().RadarSetLevel(pl, newCard.Id, newCard.Level);
                InventoryServiceNew.gI().subQuantityItemsBag(pl, item, 1);
                InventoryServiceNew.gI().sendItemBags(pl);
            }
        } else {
            if (card.Level >= 2) {
                Service.gI().sendThongBao(pl, "Thẻ này đã đạt cấp tối đa");
                return;
            }
            card.Amount++;
            if (card.Amount >= card.MaxAmount) {
                card.Amount = 0;
                if (card.Level == -1) {
                    card.Level = 1;
                } else {
                    card.Level++;
                }
                Service.gI().point(pl);
            }
            RadarService.gI().RadarSetAmount(pl, card.Id, card.Amount, card.MaxAmount);
            RadarService.gI().RadarSetLevel(pl, card.Id, card.Level);
            InventoryServiceNew.gI().subQuantityItemsBag(pl, item, 1);
            InventoryServiceNew.gI().sendItemBags(pl);
        }
    }

    private void useItemChangeFlagBag(Player player, Item item) {
        switch (item.template.id) {
            case 994: // vỏ ốc
                break;
            case 995: // cây kem
                break;
            case 996: // cá heo
                break;
            case 997: // con diều
                break;
            case 998: // diều rồng
                break;
            case 999: // mèo mun
                if (!player.effectFlagBag.useMeoMun) {
                    player.effectFlagBag.reset();
                    player.effectFlagBag.useMeoMun = !player.effectFlagBag.useMeoMun;
                } else {
                    player.effectFlagBag.reset();
                }
                break;
            case 1000: // xiên cá
                if (!player.effectFlagBag.useXienCa) {
                    player.effectFlagBag.reset();
                    player.effectFlagBag.useXienCa = !player.effectFlagBag.useXienCa;
                } else {
                    player.effectFlagBag.reset();
                }
                break;
            case 1001: // phóng heo
                if (!player.effectFlagBag.usePhongHeo) {
                    player.effectFlagBag.reset();
                    player.effectFlagBag.usePhongHeo = !player.effectFlagBag.usePhongHeo;
                } else {
                    player.effectFlagBag.reset();
                }
                break;
        }
        Service.gI().point(player);
        Service.gI().sendFlagBag(player);
    }

    private void changePet(Player player, Item item) {
        if (player.pet != null) {
            int gender = player.pet.gender + 1;
            if (gender > 2) {
                gender = 0;
            }
            PetService.gI().changeNormalPet(player, gender);
            InventoryServiceNew.gI().subQuantityItemsBag(player, item, 1);
        } else {
            Service.gI().sendThongBao(player, "Không thể thực hiện");
        }
    }

    private void changePetBerus(Player player, Item item) {
        if (player.pet != null) {
            int gender = player.pet.gender;
            PetService.gI().changeBerusPet(player, gender);
            InventoryServiceNew.gI().subQuantityItemsBag(player, item, 1);
        } else {
            Service.gI().sendThongBao(player, "Không thể thực hiện");
        }
    }

    private void changePetGoku(Player player, Item item) {
        if (player.pet != null) {
            int gender = player.pet.gender;
            PetService.gI().changeGokuPet(player, gender);
            InventoryServiceNew.gI().subQuantityItemsBag(player, item, 1);
        } else {
            Service.gI().sendThongBao(player, "Không thể thực hiện");
        }
    }

    private void changePetNguyetThan(Player player, Item item) {
        if (player.pet != null) {
            int gender = player.pet.gender;
            PetService.gI().changeNguyetThanPet(player, gender);
            InventoryServiceNew.gI().subQuantityItemsBag(player, item, 1);
        } else {
            Service.gI().sendThongBao(player, "Không thể thực hiện");
        }
    }

    private void changePetNhatThan(Player player, Item item) {
        if (player.pet != null) {
            int gender = player.pet.gender;
            PetService.gI().changeNhatThanPet(player, gender);
            InventoryServiceNew.gI().subQuantityItemsBag(player, item, 1);
        } else {
            Service.gI().sendThongBao(player, "Không thể thực hiện");
        }
    }

    private void changePetLucy(Player player, Item item) {
        if (player.pet != null) {
            int gender = player.pet.gender;
            PetService.gI().changeLucyPet(player, gender);
            InventoryServiceNew.gI().subQuantityItemsBag(player, item, 1);
        } else {
            Service.gI().sendThongBao(player, "Không thể thực hiện");
        }
    }

    private void changePetNgokhong(Player player, Item item) {
        if (player.pet != null) {
            int gender = player.pet.gender;
            PetService.gI().changeNgokhongPet(player, gender);
            InventoryServiceNew.gI().subQuantityItemsBag(player, item, 1);
        } else {
            Service.gI().sendThongBao(player, "Không thể thực hiện");
        }
    }

    private void changePetMabu(Player player, Item item) {
        if (player.pet != null) {
            int gender = player.pet.gender;
            PetService.gI().changeMabuPet(player, gender);
            InventoryServiceNew.gI().subQuantityItemsBag(player, item, 1);
        } else {
            Service.gI().sendThongBao(player, "Không thể thực hiện");
        }
    }

    /*
     * sđ 15%
     * sdcm 10%
     * hp ki 15%
     * 50%tnsm
     * 1000 sd
     * 10k hpki
     * k thể giao dịch
     * vv
     */
    private void useCanCau(Player pl, Item item) {
        if (InventoryServiceNew.gI().getCountEmptyBag(pl) > 0) {
            if (pl.zone.map.mapId != 207) {
                Service.gI().sendThongBao(pl, "Hãy đến khu vực câu cá để sử dụng cần câu!");
                return;
            }
            if (pl.itemTime.isCauCa) {
                Service.gI().sendThongBao(pl, "Đang trong thời gian đợi cá cắn!");
                return;
            }
            Item moicau = null;
            int durCancau = 0;
            int lvlCanCau = 0;
            try {
                moicau = InventoryServiceNew.gI().findItemBag(pl, 1400);
            } catch (Exception e) {
                // catch loi lam deo gi
            }
            if (moicau == null) {
                Service.gI().sendThongBao(pl, "Hết mồi câu rồi!");
                return;
            }

            for (ItemOption io : item.itemOptions) {
                switch (io.optionTemplate.id) {
                    case 247:
                        durCancau = io.param;
                        break;
                    case 72:
                        lvlCanCau = io.param;
                        break;
                }
            }

            if (durCancau == 0) {
                Service.gI().sendThongBao(pl, "Cần câu đã hư rồi, cần được sửa chữa!");
                return;
            }
            pl.lastTimeCauCa = System.currentTimeMillis();
            ItemTimeService.gI().sendTextCauCa(pl);
            pl.itemTime.lastTimeCauCa = System.currentTimeMillis();
            pl.itemTime.isCauCa = true;
            pl.rateCauCa = lvlCanCau;
            ItemTimeService.gI().removeItemTime(pl, pl.itemTime.iconCauCa);
            pl.itemTime.iconCauCa = item.template.iconID;
            Service.getInstance().point(pl);

            durCancau--;
            item.itemOptions.clear();
            item.itemOptions.add(new ItemOption(72, lvlCanCau));
            item.itemOptions.add(new ItemOption(247, durCancau));
            InventoryServiceNew.gI().subQuantityItemsBag(pl, moicau, 1);
            InventoryServiceNew.gI().sendItemBags(pl);

        } else {
            Service.gI().sendThongBao(pl, "Hàng trang đã đầy");
        }
    }

    private void openQuaThuong(Player pl, Item item) {
        if (InventoryServiceNew.gI().getCountEmptyBag(pl) > 0) {
            if (pl.countOpenBox < 10) {
                pl.countOpenBox++;
                pl.pointSukien++;
                //
                if (Util.isTrue(5, 100)) {
                    int[] itemDos = new int[] { 463, 525, 578, 577, 591, 606, 711, 2045, 632, 609,
                            623, 1091, 1093, 575, 724, 576, 458, 461, 455 };
                    int randomDo = new Random().nextInt(itemDos.length);
                    Item ct = ItemService.gI().createNewItem((short) itemDos[randomDo]);
                    ct.itemOptions.add(new ItemOption(22, 20));
                    ct.itemOptions.add(new ItemOption(23, 20));
                    ct.itemOptions.add(new ItemOption(77, 10));
                    ct.itemOptions.add(new ItemOption(103, 10));
                    ct.itemOptions.add(new ItemOption(50, 25));
                    if (Util.isTrue(95, 100)) {
                        ct.itemOptions.add(new ItemOption(93, Util.nextInt(1, 2)));
                    }
                    switch (ct.template.id) {
                        case 463:
                            ct.itemOptions.add(new ItemOption(116, 40));
                            ct.itemOptions.add(new ItemOption(115, 40));
                            break;
                        case 525:
                            ct.itemOptions.add(new ItemOption(3, 40));
                            break;
                        case 578:
                            ct.itemOptions.add(new ItemOption(29, 0));
                            break;
                        case 577:
                            ct.itemOptions.add(new ItemOption(26, 0));
                            break;
                        case 591:
                        case 606:
                            ct.itemOptions.add(new ItemOption(33, 0));
                            ct.itemOptions.add(new ItemOption(156, 50));
                            break;
                        case 711:
                            ct.itemOptions.add(new ItemOption(159, 4));
                            break;
                        case 2045:
                            ct.itemOptions.add(new ItemOption(108, 99));
                            break;
                        case 632:
                            ct.itemOptions.add(new ItemOption(167, 0));
                            ct.itemOptions.add(new ItemOption(106, 0));
                            break;
                        case 609:
                            ct.itemOptions.add(new ItemOption(97, 33));
                            break;
                        case 623:
                            ct.itemOptions.add(new ItemOption(169, 0));
                            break;
                        case 1091:
                            ct.itemOptions.add(new ItemOption(213, 0));
                            break;
                        case 1093:
                            ct.itemOptions.add(new ItemOption(5, 15));
                            break;
                        case 575:
                            ct.itemOptions.add(new ItemOption(24, 0));
                            break;
                        case 724:
                            ct.itemOptions.add(new ItemOption(162, 10));
                            break;
                        case 576:
                            ct.itemOptions.add(new ItemOption(25, 10));
                            break;
                        case 458:
                            ct.itemOptions.add(new ItemOption(111, 10));
                            break;
                        case 461:
                            ct.itemOptions.add(new ItemOption(231, 10));
                            break;
                        case 455:
                            ct.itemOptions.add(new ItemOption(109, 10));
                            break;

                    }
                    InventoryServiceNew.gI().addItemBag(pl, ct);
                    InventoryServiceNew.gI().sendItemBags(pl);
                    Service.gI().sendThongBao(pl, "|7|Bạn nhận được cải trang");
                } else if (Util.isTrue(5, 100)) {
                    int[] itemDos = new int[] { 1028, 1030, 1031, 800, 801, 803, 804, 805, 814, 822,
                            823, 852, 865, 1129, 1130, 1151, 1152, 954, 955, 966, 467, 468, 469,
                            470, 982, 471, 740, 741, 966, 996, 997, 998, 999, 1000, 745, 1001, 1007,
                            1013, 1021, 1022, 1023 };
                    int randomDo = new Random().nextInt(itemDos.length);
                    Item ct = ItemService.gI().createNewItem((short) itemDos[randomDo]);
                    ct.itemOptions.add(new ItemOption(50, 20));
                    ct.itemOptions.add(new ItemOption(22, 20));
                    ct.itemOptions.add(new ItemOption(23, 20));
                    ct.itemOptions.add(new ItemOption(77, 10));
                    ct.itemOptions.add(new ItemOption(103, 10));
                    ct.itemOptions.add(new ItemOption(108, 10));
                    if (Util.isTrue(95, 100)) {
                        ct.itemOptions.add(new ItemOption(93, Util.nextInt(1, 2)));
                    }
                    InventoryServiceNew.gI().addItemBag(pl, ct);
                    InventoryServiceNew.gI().sendItemBags(pl);
                    Service.gI().sendThongBao(pl, "|8|Bạn nhận được cánh");
                } else if (Util.isTrue(5, 100)) {
                    int[] itemDos = new int[] { 2095, 2090, 2089, 2088, 1046, 1191, 2087, 2086,
                            2085, 942, 943, 944, 967, 1107, 1307, 1306 };
                    int randomDo = new Random().nextInt(itemDos.length);
                    Item ct = ItemService.gI().createNewItem((short) itemDos[randomDo]);
                    ct.itemOptions.add(new ItemOption(5, 5));
                    ct.itemOptions.add(new ItemOption(22, 10));
                    ct.itemOptions.add(new ItemOption(23, 10));
                    ct.itemOptions.add(new ItemOption(50, 15));
                    ct.itemOptions.add(new ItemOption(77, 15));
                    ct.itemOptions.add(new ItemOption(103, 15));
                    if (Util.isTrue(90, 100)) {
                        ct.itemOptions.add(new ItemOption(93, Util.nextInt(1, 2)));
                    }

                    InventoryServiceNew.gI().addItemBag(pl, ct);
                    InventoryServiceNew.gI().sendItemBags(pl);
                    Service.gI().sendThongBao(pl, "|2|Bạn nhận được pet");

                } else {
                    int[] itemDos = new int[] { 663, 664, 665, 666, 667, 465, 466, 890, 891, 14, 15,
                            16, 1066, 1067, 1068, 1069, 1070, 1076, 1077, 1078,
                            1082,
                            1083, 1084, 1085, 1086, 1100, 1101, 1102, 1103, 1082, 1083, 1084, 1085,
                            1086, 1131, 2122, 925, 926, 927, 928, 929, 930, 931, 2091, 2092, 2093,
                            807, 808, 809, 810, 811, 812, 813, 1183, 1184, 2030, 457, 344, 674 };
                    int randomDo = new Random().nextInt(itemDos.length);
                    Item ct = ItemService.gI().createNewItem((short) itemDos[randomDo]);
                    InventoryServiceNew.gI().addItemBag(pl, ct);
                    InventoryServiceNew.gI().sendItemBags(pl);
                    Service.gI().sendThongBao(pl, "|1|Bạn nhận được item");
                }
                //
                InventoryServiceNew.gI().subQuantityItemsBag(pl, item, 1);
                InventoryServiceNew.gI().sendItemBags(pl);
                Service.getInstance().sendMoney(pl);
            } else {
                Service.gI().sendThongBao(pl,
                        "|7|Số lần mở quà hôm nay đã đạt giới hạn, hãy điểm danh hằng ngày để nhận thêm lượt");
            }

        } else {
            Service.gI().sendThongBao(pl, "Hàng trang đã đầy");
        }
    }

    private void openQuaVip(Player pl, Item item) {
        if (InventoryServiceNew.gI().getCountEmptyBag(pl) > 0) {
            pl.pointSukien++;
            //
            if (Util.isTrue(5, 100)) {
                int[] itemDos = new int[] { 463, 525, 578, 577, 591, 606, 711, 2045, 632, 609,
                        623, 1091, 1093, 575, 724, 576, 458, 461, 455 };
                int randomDo = new Random().nextInt(itemDos.length);
                Item ct = ItemService.gI().createNewItem((short) itemDos[randomDo]);
                ct.itemOptions.add(new ItemOption(22, 20));
                ct.itemOptions.add(new ItemOption(23, 20));
                ct.itemOptions.add(new ItemOption(77, 10));
                ct.itemOptions.add(new ItemOption(103, 10));
                ct.itemOptions.add(new ItemOption(50, 25));
                if (Util.isTrue(95, 100)) {
                    ct.itemOptions.add(new ItemOption(93, Util.nextInt(1, 2)));
                }
                switch (ct.template.id) {
                    case 463:
                        ct.itemOptions.add(new ItemOption(116, 40));
                        ct.itemOptions.add(new ItemOption(115, 40));
                        break;
                    case 525:
                        ct.itemOptions.add(new ItemOption(3, 40));
                        break;
                    case 578:
                        ct.itemOptions.add(new ItemOption(29, 0));
                        break;
                    case 577:
                        ct.itemOptions.add(new ItemOption(26, 0));
                        break;
                    case 591:
                    case 606:
                        ct.itemOptions.add(new ItemOption(33, 0));
                        ct.itemOptions.add(new ItemOption(156, 50));
                        break;
                    case 711:
                        ct.itemOptions.add(new ItemOption(159, 4));
                        break;
                    case 2045:
                        ct.itemOptions.add(new ItemOption(108, 99));
                        break;
                    case 632:
                        ct.itemOptions.add(new ItemOption(167, 0));
                        ct.itemOptions.add(new ItemOption(106, 0));
                        break;
                    case 609:
                        ct.itemOptions.add(new ItemOption(97, 33));
                        break;
                    case 623:
                        ct.itemOptions.add(new ItemOption(169, 0));
                        break;
                    case 1091:
                        ct.itemOptions.add(new ItemOption(213, 0));
                        break;
                    case 1093:
                        ct.itemOptions.add(new ItemOption(5, 15));
                        break;
                    case 575:
                        ct.itemOptions.add(new ItemOption(24, 0));
                        break;
                    case 724:
                        ct.itemOptions.add(new ItemOption(162, 10));
                        break;
                    case 576:
                        ct.itemOptions.add(new ItemOption(25, 10));
                        break;
                    case 458:
                        ct.itemOptions.add(new ItemOption(111, 10));
                        break;
                    case 461:
                        ct.itemOptions.add(new ItemOption(231, 10));
                        break;
                    case 455:
                        ct.itemOptions.add(new ItemOption(109, 10));
                        break;

                }
                InventoryServiceNew.gI().addItemBag(pl, ct);
                InventoryServiceNew.gI().sendItemBags(pl);
                Service.gI().sendThongBao(pl, "|7|Bạn nhận được cải trang");
            } else if (Util.isTrue(5, 100)) {
                int[] itemDos = new int[] { 1028, 1030, 1031, 800, 801, 803, 804, 805, 814, 822,
                        823, 852, 865, 1129, 1130, 1151, 1152, 954, 955, 966, 467, 468, 469,
                        470, 982, 471, 740, 741, 966, 996, 997, 998, 999, 1000, 745, 1001, 1007,
                        1013, 1021, 1022, 1023 };
                int randomDo = new Random().nextInt(itemDos.length);
                Item ct = ItemService.gI().createNewItem((short) itemDos[randomDo]);
                ct.itemOptions.add(new ItemOption(50, 20));
                ct.itemOptions.add(new ItemOption(22, 20));
                ct.itemOptions.add(new ItemOption(23, 20));
                ct.itemOptions.add(new ItemOption(77, 10));
                ct.itemOptions.add(new ItemOption(103, 10));
                ct.itemOptions.add(new ItemOption(108, 10));
                if (Util.isTrue(95, 100)) {
                    ct.itemOptions.add(new ItemOption(93, Util.nextInt(1, 2)));
                }
                InventoryServiceNew.gI().addItemBag(pl, ct);
                InventoryServiceNew.gI().sendItemBags(pl);
                Service.gI().sendThongBao(pl, "|8|Bạn nhận được cánh");
            } else if (Util.isTrue(5, 100)) {
                int[] itemDos = new int[] { 2095, 2090, 2089, 2088, 1046, 1191, 2087, 2086,
                        2085, 942, 943, 944, 967, 1107, 1307, 1306 };
                int randomDo = new Random().nextInt(itemDos.length);
                Item ct = ItemService.gI().createNewItem((short) itemDos[randomDo]);
                ct.itemOptions.add(new ItemOption(5, 10));
                ct.itemOptions.add(new ItemOption(22, 10));
                ct.itemOptions.add(new ItemOption(23, 10));
                ct.itemOptions.add(new ItemOption(50, 20));
                ct.itemOptions.add(new ItemOption(77, 10));
                ct.itemOptions.add(new ItemOption(103, 14));
                if (Util.isTrue(90, 100)) {
                    ct.itemOptions.add(new ItemOption(93, Util.nextInt(1, 2)));
                }

                InventoryServiceNew.gI().addItemBag(pl, ct);
                InventoryServiceNew.gI().sendItemBags(pl);
                Service.gI().sendThongBao(pl, "|2|Bạn nhận được pet");

            } else {
                int[] itemDos = new int[] { 663, 664, 665, 666, 667, 465, 466, 890, 891, 14, 15,
                        16, 1066, 1067, 1068, 1069, 1070, 1076, 1077, 1078,
                        1082,
                        1083, 1084, 1085, 1086, 1100, 1101, 1102, 1103, 1082, 1083, 1084, 1085,
                        1086, 1131, 2122, 925, 926, 927, 928, 929, 930, 931, 2091, 2092, 2093,
                        807, 808, 809, 810, 811, 812, 813, 1183, 1184, 2030, 457, 344, 674 };

                int randomDo = new Random().nextInt(itemDos.length);
                Item ct = ItemService.gI().createNewItem((short) itemDos[randomDo]);
                InventoryServiceNew.gI().addItemBag(pl, ct);
                InventoryServiceNew.gI().sendItemBags(pl);
                Service.gI().sendThongBao(pl, "|1|Bạn nhận được item");
            }
            //
            InventoryServiceNew.gI().subQuantityItemsBag(pl, item, 1);
            InventoryServiceNew.gI().sendItemBags(pl);
            Service.getInstance().sendMoney(pl);
        } else {
            Service.gI().sendThongBao(pl, "Hàng trang đã đầy");
        }
    }

    private void openQuaMocNap(Player pl, Item item) {
        if (InventoryServiceNew.gI().getCountEmptyBag(pl) > 0) {
            switch (item.template.id) {
                case 1322:
                    int[] itemDos20 = new int[] {};// them id item vo day
                    int randomDo20 = new Random().nextInt(itemDos20.length);
                    Item qua20 = ItemService.gI().createNewItem((short) itemDos20[randomDo20]);
                    qua20.itemOptions.add(new ItemOption(50, 15));
                    qua20.itemOptions.add(new ItemOption(5, 10));
                    qua20.itemOptions.add(new ItemOption(77, 15));
                    qua20.itemOptions.add(new ItemOption(103, 15));
                    qua20.itemOptions.add(new ItemOption(101, 50));
                    qua20.itemOptions.add(new ItemOption(22, 10));
                    qua20.itemOptions.add(new ItemOption(23, 10));
                    qua20.itemOptions.add(new ItemOption(30, 0));
                    InventoryServiceNew.gI().addItemBag(pl, qua20);
                    break;
                case 1323:
                    int[] itemDos50 = new int[] {};
                    int randomDo50 = new Random().nextInt(itemDos50.length);
                    Item qua50 = ItemService.gI().createNewItem((short) itemDos50[randomDo50]);
                    qua50.itemOptions.add(new ItemOption(50, 15));
                    qua50.itemOptions.add(new ItemOption(5, 10));
                    qua50.itemOptions.add(new ItemOption(77, 15));
                    qua50.itemOptions.add(new ItemOption(103, 15));
                    qua50.itemOptions.add(new ItemOption(101, 50));
                    qua50.itemOptions.add(new ItemOption(22, 10));
                    qua50.itemOptions.add(new ItemOption(23, 10));
                    qua50.itemOptions.add(new ItemOption(30, 0));
                    InventoryServiceNew.gI().addItemBag(pl, qua50);
                    break;
                case 1324:
                    int[] itemDos100 = new int[] {};
                    int randomDo100 = new Random().nextInt(itemDos100.length);
                    Item qua100 = ItemService.gI().createNewItem((short) itemDos100[randomDo100]);
                    qua100.itemOptions.add(new ItemOption(50, 15));
                    qua100.itemOptions.add(new ItemOption(5, 10));
                    qua100.itemOptions.add(new ItemOption(77, 15));
                    qua100.itemOptions.add(new ItemOption(103, 15));
                    qua100.itemOptions.add(new ItemOption(101, 50));
                    qua100.itemOptions.add(new ItemOption(22, 10));
                    qua100.itemOptions.add(new ItemOption(23, 10));
                    qua100.itemOptions.add(new ItemOption(30, 0));
                    InventoryServiceNew.gI().addItemBag(pl, qua100);
                    break;
            }
            Service.gI().sendThongBao(pl, "Bạn vừa sử dụng quà mốc nạp !");
            InventoryServiceNew.gI().subQuantityItemsBag(pl, item, 1);
            InventoryServiceNew.gI().sendItemBags(pl);
            Service.getInstance().sendMoney(pl);
        } else {
            Service.gI().sendThongBao(pl, "Hàng trang đã đầy");
        }
    }

    private void openThoiVang(Player pl, Item item) {
        if (InventoryServiceNew.gI().getCountEmptyBag(pl) > 0) {
            pl.inventory.gold += 500000000;
            Service.gI().sendThongBao(pl, "Bạn vừa dùng thỏi vàng và nhận được 500tr vàng");
            InventoryServiceNew.gI().subQuantityItemsBag(pl, item, 1);
            InventoryServiceNew.gI().sendItemBags(pl);
            Service.getInstance().sendMoney(pl);
        } else {
            Service.gI().sendThongBao(pl, "Hàng trang đã đầy");
        }
    }

    private void openPhieuCaiTrangHaiTac(Player pl, Item item) {
        if (InventoryServiceNew.gI().getCountEmptyBag(pl) > 0) {
            Item ct = ItemService.gI().createNewItem((short) Util.nextInt(618, 626));
            ct.itemOptions.add(new ItemOption(147, Util.nextInt(10, 30)));
            ct.itemOptions.add(new ItemOption(77, Util.nextInt(10, 30)));
            ct.itemOptions.add(new ItemOption(103, Util.nextInt(10, 30)));
            ct.itemOptions.add(new ItemOption(149, 0));
            ct.itemOptions.add(new ItemOption(162, 5));
            ct.itemOptions.add(new ItemOption(93, Util.nextInt(1, 2)));
            ct.itemOptions.add(new ItemOption(88, Util.nextInt(1, 50)));
            if (item.template.id == 2006) {
                ct.itemOptions.add(new ItemOption(93, Util.nextInt(1, 7)));
            } else if (item.template.id == 2007) {
                ct.itemOptions.add(new ItemOption(93, Util.nextInt(7, 30)));
            }
            InventoryServiceNew.gI().addItemBag(pl, ct);
            InventoryServiceNew.gI().subQuantityItemsBag(pl, item, 1);
            InventoryServiceNew.gI().sendItemBags(pl);
            CombineServiceNew.gI().sendEffectOpenItem(pl, item.template.iconID, ct.template.iconID);
        } else {
            Service.gI().sendThongBao(pl, "Hàng trang đã đầy");
        }
    }

    private void openPhieuCaiTrangBojack(Player pl, Item item) {
        if (InventoryServiceNew.gI().getCountEmptyBag(pl) > 0) {
            Item ct = ItemService.gI().createNewItem((short) Util.nextInt(423, 427));
            ct.itemOptions.add(new ItemOption(147, Util.nextInt(10, 30)));
            ct.itemOptions.add(new ItemOption(77, Util.nextInt(10, 30)));
            ct.itemOptions.add(new ItemOption(103, Util.nextInt(10, 30)));
            ct.itemOptions.add(new ItemOption(149, 0));
            ct.itemOptions.add(new ItemOption(162, 5));
            ct.itemOptions.add(new ItemOption(93, Util.nextInt(1, 2)));
            ct.itemOptions.add(new ItemOption(88, Util.nextInt(1, 50)));
            if (item.template.id == 2006) {
                ct.itemOptions.add(new ItemOption(93, Util.nextInt(1, 7)));
            } else if (item.template.id == 2007) {
                ct.itemOptions.add(new ItemOption(93, Util.nextInt(7, 30)));
            }
            InventoryServiceNew.gI().addItemBag(pl, ct);
            InventoryServiceNew.gI().subQuantityItemsBag(pl, item, 1);
            InventoryServiceNew.gI().sendItemBags(pl);
            CombineServiceNew.gI().sendEffectOpenItem(pl, item.template.iconID, ct.template.iconID);
        } else {
            Service.gI().sendThongBao(pl, "Hàng trang đã đầy");
        }
    }

    private void openPhieuCaiTrangTanjiro(Player pl, Item item) {
        if (InventoryServiceNew.gI().getCountEmptyBag(pl) > 0) {
            Item ct = ItemService.gI().createNewItem((short) Util.nextInt(1087, 1091));
            ct.itemOptions.add(new ItemOption(147, Util.nextInt(10, 30)));
            ct.itemOptions.add(new ItemOption(77, Util.nextInt(10, 30)));
            ct.itemOptions.add(new ItemOption(103, Util.nextInt(10, 30)));
            ct.itemOptions.add(new ItemOption(149, 0));
            ct.itemOptions.add(new ItemOption(162, 5));
            ct.itemOptions.add(new ItemOption(93, Util.nextInt(1, 2)));
            ct.itemOptions.add(new ItemOption(88, Util.nextInt(1, 50)));
            if (item.template.id == 2006) {
                ct.itemOptions.add(new ItemOption(93, Util.nextInt(1, 7)));
            } else if (item.template.id == 2007) {
                ct.itemOptions.add(new ItemOption(93, Util.nextInt(7, 30)));
            }
            InventoryServiceNew.gI().addItemBag(pl, ct);
            InventoryServiceNew.gI().subQuantityItemsBag(pl, item, 1);
            InventoryServiceNew.gI().sendItemBags(pl);
            CombineServiceNew.gI().sendEffectOpenItem(pl, item.template.iconID, ct.template.iconID);
        } else {
            Service.gI().sendThongBao(pl, "Hàng trang đã đầy");
        }
    }

    private void openPhieuCaiTrangDoremon(Player pl, Item item) {
        if (InventoryServiceNew.gI().getCountEmptyBag(pl) > 0) {
            Item ct = ItemService.gI().createNewItem((short) Util.nextInt(862, 864));
            ct.itemOptions.add(new ItemOption(147, Util.nextInt(10, 30)));
            ct.itemOptions.add(new ItemOption(77, Util.nextInt(10, 30)));
            ct.itemOptions.add(new ItemOption(103, Util.nextInt(10, 30)));
            ct.itemOptions.add(new ItemOption(149, 0));
            ct.itemOptions.add(new ItemOption(162, 5));
            ct.itemOptions.add(new ItemOption(93, Util.nextInt(1, 2)));
            ct.itemOptions.add(new ItemOption(88, Util.nextInt(1, 50)));
            if (item.template.id == 2006) {
                ct.itemOptions.add(new ItemOption(93, Util.nextInt(1, 7)));
            } else if (item.template.id == 2007) {
                ct.itemOptions.add(new ItemOption(93, Util.nextInt(7, 30)));
            }
            InventoryServiceNew.gI().addItemBag(pl, ct);
            InventoryServiceNew.gI().subQuantityItemsBag(pl, item, 1);
            InventoryServiceNew.gI().sendItemBags(pl);
            CombineServiceNew.gI().sendEffectOpenItem(pl, item.template.iconID, ct.template.iconID);
        } else {
            Service.gI().sendThongBao(pl, "Hàng trang đã đầy");
        }
    }

    private void openPhieuCaiTrangVothuat(Player pl, Item item) {
        if (InventoryServiceNew.gI().getCountEmptyBag(pl) > 0) {
            short[] itemDos = new short[] { 461, 455, 458, 607, 608 };
            int randomDo = new Random().nextInt(itemDos.length);
            Item ct = ItemService.gI().createNewItem(itemDos[randomDo]);
            ct.itemOptions.add(new ItemOption(147, Util.nextInt(10, 30)));
            ct.itemOptions.add(new ItemOption(77, Util.nextInt(10, 30)));
            ct.itemOptions.add(new ItemOption(103, Util.nextInt(10, 30)));
            ct.itemOptions.add(new ItemOption(149, 0));
            ct.itemOptions.add(new ItemOption(162, 5));
            ct.itemOptions.add(new ItemOption(93, Util.nextInt(1, 2)));
            ct.itemOptions.add(new ItemOption(88, Util.nextInt(1, 50)));
            if (item.template.id == 2006) {
                ct.itemOptions.add(new ItemOption(93, Util.nextInt(1, 7)));
            } else if (item.template.id == 2007) {
                ct.itemOptions.add(new ItemOption(93, Util.nextInt(7, 30)));
            }
            InventoryServiceNew.gI().addItemBag(pl, ct);
            InventoryServiceNew.gI().subQuantityItemsBag(pl, item, 1);
            InventoryServiceNew.gI().sendItemBags(pl);
            CombineServiceNew.gI().sendEffectOpenItem(pl, item.template.iconID, ct.template.iconID);
        } else {
            Service.gI().sendThongBao(pl, "Hàng trang đã đầy");
        }
    }

    private void openPhieuCaiTrangTieudoi(Player pl, Item item) {
        if (InventoryServiceNew.gI().getCountEmptyBag(pl) > 0) {
            Item ct = ItemService.gI().createNewItem((short) Util.nextInt(428, 433));
            ct.itemOptions.add(new ItemOption(147, Util.nextInt(10, 30)));
            ct.itemOptions.add(new ItemOption(77, Util.nextInt(10, 30)));
            ct.itemOptions.add(new ItemOption(103, Util.nextInt(10, 30)));
            ct.itemOptions.add(new ItemOption(149, 0));
            ct.itemOptions.add(new ItemOption(162, 5));
            ct.itemOptions.add(new ItemOption(93, Util.nextInt(1, 2)));
            ct.itemOptions.add(new ItemOption(88, Util.nextInt(1, 50)));
            if (item.template.id == 2006) {
                ct.itemOptions.add(new ItemOption(93, Util.nextInt(1, 7)));
            } else if (item.template.id == 2007) {
                ct.itemOptions.add(new ItemOption(93, Util.nextInt(7, 30)));
            }
            InventoryServiceNew.gI().addItemBag(pl, ct);
            InventoryServiceNew.gI().subQuantityItemsBag(pl, item, 1);
            InventoryServiceNew.gI().sendItemBags(pl);
            CombineServiceNew.gI().sendEffectOpenItem(pl, item.template.iconID, ct.template.iconID);
        } else {
            Service.gI().sendThongBao(pl, "Hàng trang đã đầy");
        }
    }

    private void eatGrapes(Player pl, Item item) {
        int percentCurrentStatima = pl.nPoint.stamina * 100 / pl.nPoint.maxStamina;
        if (percentCurrentStatima > 50) {
            Service.gI().sendThongBao(pl, "Thể lực vẫn còn trên 50%");
            return;
        } else if (item.template.id == 211) {
            pl.nPoint.stamina = pl.nPoint.maxStamina;
            Service.gI().sendThongBao(pl, "Thể lực của bạn đã được hồi phục 100%");
        } else if (item.template.id == 212) {
            pl.nPoint.stamina += (pl.nPoint.maxStamina * 20 / 100);
            Service.gI().sendThongBao(pl, "Thể lực của bạn đã được hồi phục 20%");
        }
        InventoryServiceNew.gI().subQuantityItemsBag(pl, item, 1);
        InventoryServiceNew.gI().sendItemBags(pl);
        PlayerService.gI().sendCurrentStamina(pl);
    }

    private void openCSKB(Player pl, Item item) {
        if (InventoryServiceNew.gI().getCountEmptyBag(pl) > 0) {
            short[] temp = { 16, 17, 18, 19, 20, 190, 381, 382, 383, 384, 385 };
            int[][] gold = { { 1000000, 2000000 } };
            byte index = (byte) Util.nextInt(0, temp.length - 1);
            short[] icon = new short[2];
            icon[0] = item.template.iconID;
            if (index <= 5) {
                pl.inventory.gold += Util.nextInt(gold[0][0], gold[0][1]);
                if (pl.inventory.gold > Inventory.LIMIT_GOLD) {
                    pl.inventory.gold = Inventory.LIMIT_GOLD;
                }
                PlayerService.gI().sendInfoHpMpMoney(pl);
                icon[1] = 930;
            } else {
                Item it = ItemService.gI().createNewItem(temp[index]);
                it.itemOptions.add(new ItemOption(73, 0));
                InventoryServiceNew.gI().addItemBag(pl, it);
                icon[1] = it.template.iconID;
            }
            InventoryServiceNew.gI().subQuantityItemsBag(pl, item, 1);
            InventoryServiceNew.gI().sendItemBags(pl);

            CombineServiceNew.gI().sendEffectOpenItem(pl, icon[0], icon[1]);
        } else {
            Service.gI().sendThongBao(pl, "Hàng trang đã đầy");
        }
    }

    private void useItemHopQuaTanThu(Player pl, Item item) {
        if (InventoryServiceNew.gI().getCountEmptyBag(pl) > 0) {
            short[] temp = { 14, 16, 17, 18, 19, 20, 21, 22 };
            int[][] gold = { { 100000000, 200000000 } };
            byte index = (byte) Util.nextInt(0, temp.length - 1);
            short[] icon = new short[2];
            icon[0] = item.template.iconID;
            if (index <= 3) {
                pl.inventory.gold += Util.nextInt(gold[0][0], gold[0][1]);
                if (pl.inventory.gold > Inventory.LIMIT_GOLD) {
                    pl.inventory.gold = Inventory.LIMIT_GOLD;
                }
                PlayerService.gI().sendInfoHpMpMoney(pl);
                icon[1] = 930;
            } else {
                Item it = ItemService.gI().createNewItem(temp[index]);
                it.itemOptions.add(new ItemOption(73, 0));
                InventoryServiceNew.gI().addItemBag(pl, it);
                icon[1] = it.template.iconID;
            }
            InventoryServiceNew.gI().subQuantityItemsBag(pl, item, 1);
            InventoryServiceNew.gI().sendItemBags(pl);

            CombineServiceNew.gI().sendEffectOpenItem(pl, icon[0], icon[1]);
        } else {
            Service.gI().sendThongBao(pl, "Hàng trang đã đầy");
        }
    }

    private void useItemGiaHanCaiTrang(Player pl, Item item) {
        Item ct = pl.inventory.itemsBody.get(5);
        Item newct = null;
        List<Item.ItemOption> ioCopy = new ArrayList<>();
        int countCheck = 0;
        int paramHSD = 0;
        for (Item.ItemOption io : ct.itemOptions) {
            if (io.optionTemplate.id == 93) {
                countCheck++;
                paramHSD += io.param;
                continue;
            } else {
                ioCopy.add(io);
            }

        }
        if (countCheck == 0) {
            Service.gI().sendThongBao(pl, "Yêu cầu cải trang có hạn sử dụng");
        } else {

            ct.itemOptions.clear();
            for (Item.ItemOption io : ioCopy) {
                ct.itemOptions.add(io);
            }
            ct.itemOptions.add(new ItemOption(93, paramHSD + 3));
            InventoryServiceNew.gI().subQuantityItemsBag(pl, item, 1);
            Service.gI().sendThongBao(pl, "Vật phẩm " + ct.template.name + " của bạn vừa được gia hạn thêm 3 ngày !");
            InventoryServiceNew.gI().sendItemBags(pl);
            InventoryServiceNew.gI().sendItemBody(pl);
        }
    };

    private void useItemQuaDua(Player pl, Item item) {
        if (InventoryServiceNew.gI().getCountEmptyBag(pl) > 0) {
            short[] temp = { 2069, 2070, 2071, 2072, 2073 };
            int[][] gold = { { 10000000, 20000000 } };
            int[][] ruby = { { 10, 20 } };
            byte index = (byte) Util.nextInt(0, temp.length - 1);
            short[] icon = new short[2];
            icon[0] = item.template.iconID;
            if (index <= 3) {
                pl.inventory.gold += Util.nextInt(gold[0][0], gold[0][1]);
                if (pl.inventory.gold > Inventory.LIMIT_GOLD) {
                    pl.inventory.gold = Inventory.LIMIT_GOLD;
                }
                PlayerService.gI().sendInfoHpMpMoney(pl);
                icon[1] = 930;
            } else {
                Item it = ItemService.gI().createNewItem(temp[index]);
                it.itemOptions.add(new ItemOption(73, 0));
                InventoryServiceNew.gI().addItemBag(pl, it);
                icon[1] = it.template.iconID;
            }
            InventoryServiceNew.gI().subQuantityItemsBag(pl, item, 1);
            InventoryServiceNew.gI().sendItemBags(pl);

            CombineServiceNew.gI().sendEffectOpenItem(pl, icon[0], icon[1]);
        } else {
            Service.gI().sendThongBao(pl, "Hàng trang đã đầy");
        }
    }

    private void useItemTime(Player pl, Item item) {
        switch (item.template.id) {
            case 382: // bổ huyết
                if (pl.itemTime.isUseBoHuyetSC == true) {
                    Service.getInstance().sendThongBao(pl, "Bạn đang sử dụng bổ huyết siêu cấp");
                    return;
                }
                pl.itemTime.lastTimeBoHuyet = System.currentTimeMillis();
                pl.itemTime.isUseBoHuyet = true;
                break;
            case 383: // bổ khí
                if (pl.itemTime.isUseBoKhiSC == true) {
                    Service.getInstance().sendThongBao(pl, "Bạn đang sử dụng bổ khí siêu cấp");
                    return;
                }
                pl.itemTime.lastTimeBoKhi = System.currentTimeMillis();
                pl.itemTime.isUseBoKhi = true;
                break;
            case 384: // giáp xên
                if (pl.itemTime.isUseGiapXenSC == true) {
                    Service.getInstance().sendThongBao(pl, "Bạn đang sử dụng giáp xên siêu cấp");
                    return;
                }
                pl.itemTime.lastTimeGiapXen = System.currentTimeMillis();
                pl.itemTime.isUseGiapXen = true;
                break;
            case 381: // cuồng nộ
                if (pl.itemTime.isUseCuongNoSC == true) {
                    Service.getInstance().sendThongBao(pl, "Bạn đang sử dụng cuồng nộ siêu cấp");
                    return;
                }
                pl.itemTime.lastTimeCuongNo = System.currentTimeMillis();
                pl.itemTime.isUseCuongNo = true;
                Service.getInstance().point(pl);
                break;
            case 385: // ẩn danh
                if (pl.itemTime.isUseAnDanhSC == true) {
                    Service.getInstance().sendThongBao(pl, "Bạn đang sử dụng ẩn danh đặc biệt");
                    return;
                }
                pl.itemTime.lastTimeAnDanh = System.currentTimeMillis();
                pl.itemTime.isUseAnDanh = true;
                break;
            case 379: // máy dò capsule
                pl.itemTime.lastTimeUseMayDo = System.currentTimeMillis();
                pl.itemTime.isUseMayDo = true;
                break;
            case 1100: // bổ huyết
                if (pl.itemTime.isUseBoHuyet == true) {
                    Service.getInstance().sendThongBao(pl, "Bạn đang sử dụng bổ huyết");
                    return;
                }
                pl.itemTime.lastTimeBoHuyetSC = System.currentTimeMillis();
                pl.itemTime.isUseBoHuyetSC = true;
                break;
            case 1101: // bổ khí
                if (pl.itemTime.isUseBoKhi == true) {
                    Service.getInstance().sendThongBao(pl, "Bạn đang sử dụng bổ khí");
                    return;
                }
                pl.itemTime.lastTimeBoKhiSC = System.currentTimeMillis();
                pl.itemTime.isUseBoKhiSC = true;
                break;
            case 1102: // giáp xên
                if (pl.itemTime.isUseGiapXen == true) {
                    Service.getInstance().sendThongBao(pl, "Bạn đang sử dụng giáp xên");
                    return;
                }
                pl.itemTime.lastTimeGiapXenSC = System.currentTimeMillis();
                pl.itemTime.isUseGiapXenSC = true;
                break;
            case 1099: // cuồng nộ
                if (pl.itemTime.isUseCuongNo == true) {
                    Service.getInstance().sendThongBao(pl, "Bạn đang sử dụng cuồng nộ");
                    return;
                }
                pl.itemTime.lastTimeCuongNoSC = System.currentTimeMillis();
                pl.itemTime.isUseCuongNoSC = true;
                Service.getInstance().point(pl);
                break;
            case 1347: // rada
                if (pl.itemTime.isEatRada == true) {
                    Service.getInstance().sendThongBao(pl, "Bạn đã sử dụng rada");
                    return;
                }
                pl.itemTime.lastTimeEatRada = System.currentTimeMillis();
                pl.itemTime.isEatRada = true;
                ItemTimeService.gI().removeItemTime(pl, pl.itemTime.iconRada);
                pl.itemTime.iconRada = item.template.iconID;
                Service.gI().Send_Caitrang(pl);
                Service.getInstance().point(pl);

                break;
            case 1348: // rada
                if (pl.itemTime.isEatCaiVot == true) {
                    Service.getInstance().sendThongBao(pl, "Bạn đã sử dụng cái vợt");
                    return;
                }
                pl.itemTime.lastTimeEatCaiVot = System.currentTimeMillis();
                pl.itemTime.isEatCaiVot = true;
                ItemTimeService.gI().removeItemTime(pl, pl.itemTime.iconCaiVot);
                pl.itemTime.iconCaiVot = item.template.iconID;
                Service.gI().Send_Caitrang(pl);
                Service.getInstance().point(pl);

                break;
            case 1349: // rada
                if (pl.itemTime.isEatMatOng == true) {
                    Service.getInstance().sendThongBao(pl, "Bạn đã sử dụng mật ong");
                    return;
                }
                pl.itemTime.lastTimeEatMatOng = System.currentTimeMillis();
                pl.itemTime.isEatMatOng = true;
                ItemTimeService.gI().removeItemTime(pl, pl.itemTime.iconMatOng);
                pl.itemTime.iconMatOng = item.template.iconID;
                Service.gI().Send_Caitrang(pl);
                Service.getInstance().point(pl);

                break;
            case 465:
            case 466:
            case 890:
            case 891: // banh
                if (pl.itemTime.isEatBanh == true) {
                    Service.getInstance().sendThongBao(pl, "Bạn đang sử dụng bánh trung thu");
                    return;
                }
                pl.itemTime.lastTimeEatBanh = System.currentTimeMillis();
                pl.itemTime.isEatBanh = true;
                ItemTimeService.gI().removeItemTime(pl, pl.itemTime.iconBanh);
                pl.itemTime.iconBanh = item.template.iconID;
                Service.getInstance().point(pl);
                break;
            case 1103: // ẩn danh
                if (pl.itemTime.isUseAnDanh == true) {
                    Service.getInstance().sendThongBao(pl, "Bạn đang sử dụng ẩn danh");
                    return;
                }
                pl.itemTime.lastTimeAnDanhSC = System.currentTimeMillis();
                pl.itemTime.isUseAnDanhSC = true;
                break;

            case 663: // bánh pudding
            case 664: // xúc xíc
            case 665: // kem dâu
            case 666: // mì ly
            case 667: // sushi

                pl.itemTime.lastTimeEatMeal = System.currentTimeMillis();
                pl.itemTime.isEatMeal = true;
                ItemTimeService.gI().removeItemTime(pl, pl.itemTime.iconMeal);
                pl.itemTime.iconMeal = item.template.iconID;
                Service.getInstance().point(pl);

                break;
            // .
            case 1300:
            case 1301:
            case 1302:
                if (pl.itemTime.isEatBanhTho == true) {
                    Service.getInstance().sendThongBao(pl, "Bạn đã sử dụng bánh thỏ");
                    return;
                }
                pl.itemTime.lastTimeEatBanhTho = System.currentTimeMillis();
                pl.itemTime.isEatBanhTho = true;
                ItemTimeService.gI().removeItemTime(pl, pl.itemTime.iconBanhTho);
                pl.itemTime.iconBanhTho = item.template.iconID;
                Service.gI().Send_Caitrang(pl);
                Service.getInstance().point(pl);

                break;
            // qua hong dao
            case 1183:
                pl.itemTime.lastTimeEatHongDao = System.currentTimeMillis();
                pl.itemTime.isEatHongDao = true;
                ItemTimeService.gI().removeItemTime(pl, pl.itemTime.iconHongDao);
                pl.itemTime.iconHongDao = item.template.iconID;
                Service.getInstance().point(pl);
                break;

            // banh trung
            case 753:
                pl.itemTime.lastTimeEatBanhTrung = System.currentTimeMillis();
                pl.itemTime.isEatBanhTrung = true;
                ItemTimeService.gI().removeItemTime(pl, pl.itemTime.iconBanhTrung);
                pl.itemTime.iconBanhTrung = item.template.iconID;
                Service.getInstance().point(pl);
                break;
            // huy hieu
            case 1184:
                pl.itemTime.lastTimeEatHuyHieu = System.currentTimeMillis();
                pl.itemTime.isEatHuyHieu = true;
                ItemTimeService.gI().removeItemTime(pl, pl.itemTime.iconHuyHieu);
                pl.itemTime.iconHuyHieu = item.template.iconID;
                Service.getInstance().point(pl);
                break;
            //
            case 1221: // da nguc tu
                if (pl.itemTime.isEatNhoXanh == true) {
                    Service.getInstance().sendThongBao(pl, "Bạn đã sử dụng nho xanh");
                    return;
                }
                pl.itemTime.lastTimeEatNhoXanh = System.currentTimeMillis();
                pl.itemTime.isEatNhoXanh = true;
                ItemTimeService.gI().removeItemTime(pl, pl.itemTime.iconNhoXanh);
                pl.itemTime.iconNhoXanh = item.template.iconID;
                Service.gI().Send_Caitrang(pl);
                Service.getInstance().point(pl);
                break;
            case 1261: // da nguc tu
                if (pl.itemTime.isEatMatTrang == true) {
                    Service.getInstance().sendThongBao(pl, "Bạn đã sử dụng mặt trăng");
                    return;
                }
                pl.itemTime.lastTimeEatMatTrang = System.currentTimeMillis();
                pl.itemTime.isEatMatTrang = true;
                ItemTimeService.gI().removeItemTime(pl, pl.itemTime.iconMatTrang);
                pl.itemTime.iconMatTrang = item.template.iconID;
                Service.gI().Send_Caitrang(pl);
                Service.getInstance().point(pl);
                break;
            case 1262: // da nguc tu
                if (pl.itemTime.isEatKiemGo == true) {
                    Service.getInstance().sendThongBao(pl, "Bạn đã sử dụng kiếm gỗ");
                    return;
                }
                pl.itemTime.lastTimeEatKiemGo = System.currentTimeMillis();
                pl.itemTime.isEatKiemGo = true;
                ItemTimeService.gI().removeItemTime(pl, pl.itemTime.iconKiemGo);
                pl.itemTime.iconKiemGo = item.template.iconID;
                Service.gI().Send_Caitrang(pl);
                Service.getInstance().point(pl);
                break;
            case 1222: // da nguc tu
                if (pl.itemTime.isEatNhoTim == true) {
                    Service.getInstance().sendThongBao(pl, "Bạn đã sử dụng nho tím");
                    return;
                }
                pl.itemTime.lastTimeEatNhoTim = System.currentTimeMillis();
                pl.itemTime.isEatNhoTim = true;
                ItemTimeService.gI().removeItemTime(pl, pl.itemTime.iconNhoTim);
                pl.itemTime.iconNhoTim = item.template.iconID;
                Service.gI().Send_Caitrang(pl);
                Service.getInstance().point(pl);
                break;
            case 1238: // da nguc tu
                if (pl.itemTime.isEatDuoiKhi == true) {
                    Service.getInstance().sendThongBao(pl, "Bạn đã sử dụng đuôi khỉ");
                    return;
                }
                pl.itemTime.lastTimeEatDuoiKhi = System.currentTimeMillis();
                pl.itemTime.isEatDuoiKhi = true;
                ItemTimeService.gI().removeItemTime(pl, pl.itemTime.iconDuoiKhi);
                pl.itemTime.iconDuoiKhi = item.template.iconID;
                Service.gI().Send_Caitrang(pl);
                Service.getInstance().point(pl);
                break;
            case 1237: // da nguc tu
                if (pl.itemTime.isEatLongDen == true) {
                    Service.getInstance().sendThongBao(pl, "Bạn đã sử dụng lồng đèn");
                    return;
                }
                pl.itemTime.lastTimeEatLongDen = System.currentTimeMillis();
                pl.itemTime.isEatLongDen = true;
                ItemTimeService.gI().removeItemTime(pl, pl.itemTime.iconLongDen);
                pl.itemTime.iconLongDen = item.template.iconID;
                Service.gI().Send_Caitrang(pl);
                Service.getInstance().point(pl);
                break;
            case 1201: // da nguc tu
                if (pl.itemTime.isEatDaTim == true) {
                    Service.getInstance().sendThongBao(pl, "Bạn đã chơi đá tím");
                    return;
                }
                pl.itemTime.lastTimeEatDaTim = System.currentTimeMillis();
                pl.itemTime.isEatDaTim = true;
                ItemTimeService.gI().removeItemTime(pl, pl.itemTime.iconDaTim);
                pl.itemTime.iconDaTim = item.template.iconID;
                Service.gI().Send_Caitrang(pl);
                Service.getInstance().point(pl);
                break;
            case 1202: // da nguc tu
                if (pl.itemTime.isEatDaXanh == true) {
                    Service.getInstance().sendThongBao(pl, "Bạn đã chơi đá xanh");
                    return;
                }
                pl.itemTime.lastTimeEatDaXanh = System.currentTimeMillis();
                pl.itemTime.isEatDaXanh = true;
                ItemTimeService.gI().removeItemTime(pl, pl.itemTime.iconDaXanh);
                pl.itemTime.iconDaXanh = item.template.iconID;
                Service.gI().Send_Caitrang(pl);
                Service.getInstance().point(pl);
                break;
            case 1203: // da nguc tu
                if (pl.itemTime.isEatDaDo == true) {
                    Service.getInstance().sendThongBao(pl, "Bạn đã chơi đá đỏ");
                    return;
                }
                pl.itemTime.lastTimeEatDaDo = System.currentTimeMillis();
                pl.itemTime.isEatDaDo = true;
                ItemTimeService.gI().removeItemTime(pl, pl.itemTime.iconDaDo);
                pl.itemTime.iconDaDo = item.template.iconID;
                Service.gI().Send_Caitrang(pl);
                Service.getInstance().point(pl);
                break;
            case 1193: // bánh pudding
            case 1194: // xúc xíc
            case 1195: // kem dâu
                if (pl.itemTime.isEatPotential == true) {
                    Service.getInstance().sendThongBao(pl, "Bạn đã kích hoạt sức mạnh tiềm ẩn");
                    return;
                }
                pl.itemTime.lastTimeEatPotential = System.currentTimeMillis();
                pl.itemTime.isEatPotential = true;
                ItemTimeService.gI().removeItemTime(pl, pl.itemTime.iconPotential);
                pl.itemTime.iconPotential = item.template.iconID;
                Service.gI().Send_Caitrang(pl);
                Service.getInstance().point(pl);
                break;
            case 2037: // máy dò đồ
                pl.itemTime.lastTimeUseMayDo2 = System.currentTimeMillis();
                pl.itemTime.isUseMayDo2 = true;
                break;
            case 2105: // máy dò đồ
                pl.itemTime.lastTimeUseMayDo3 = System.currentTimeMillis();
                pl.itemTime.isUseMayDo3 = true;
                break;
        }
        Service.gI().point(pl);
        ItemTimeService.gI().sendAllItemTime(pl);
        InventoryServiceNew.gI().subQuantityItemsBag(pl, item, 1);
        InventoryServiceNew.gI().sendItemBags(pl);
    }

    private void controllerCallRongThan(Player pl, Item item) {
        int tempId = item.template.id;
        if (tempId >= SummonDragon.NGOC_RONG_1_SAO && tempId <= SummonDragon.NGOC_RONG_7_SAO) {
            switch (tempId) {
                case SummonDragon.NGOC_RONG_1_SAO:
                case SummonDragon.NGOC_RONG_2_SAO:
                case SummonDragon.NGOC_RONG_3_SAO:
                    SummonDragon.gI().openMenuSummonShenron(pl, (byte) (tempId - 13));
                    break;
                default:
                    NpcService.gI().createMenuConMeo(pl, ConstNpc.TUTORIAL_SUMMON_DRAGON,
                            -1, "Bạn chỉ có thể gọi rồng từ ngọc 3 sao, 2 sao, 1 sao", "Hướng\ndẫn thêm\n(mới)", "OK");
                    break;
            }
        }
    }

    private void controllerCalltrb(Player pl, Item item) {
        int tempId = item.template.id;
        if (tempId >= SummonDragon.NGOC_RONGTRB1 && tempId <= SummonDragon.NGOC_RONGTRB3) {
            switch (tempId) {
                case SummonDragon.NGOC_RONGTRB1:
                    SummonDragon.gI().openMenuSummonShenronTRB(pl, (byte) (tempId - 2090));
                    break;
                default:
                    NpcService.gI().createMenuConMeo(pl, ConstNpc.TUTORIAL_SUMMON_DRAGONTRB,
                            -1, "Bạn chỉ có thể gọi rồng từ ngọc 1 sao TRB ", "Hướng\ndẫn thêm\n(mới)", "OK");
                    break;
            }
        }
    }

    private void controllerCallRongBang(Player pl, Item item) {
        int tempId = item.template.id;
        if (tempId >= SummonDragon.NGOC_RONG_BANG_1_SAO && tempId <= SummonDragon.NGOC_RONG_BANG_7_SAO) {
            switch (tempId) {
                case SummonDragon.NGOC_RONG_BANG_1_SAO:
                    SummonDragon.gI().openMenuSummonShenronBang(pl, (byte) (tempId - 924));
                    break;
                default:
                    NpcService.gI().createMenuConMeo(pl, ConstNpc.TUTORIAL_SUMMON_DRAGONBANG,
                            -1, "Bạn chỉ có thể gọi rồng từ ngọc 1 sao băng ", "Hướng\ndẫn thêm\n(mới)", "OK");
                    break;
            }
        }
    }

    private void controllerCallRongDen(Player pl, Item item) {
        int tempId = item.template.id;
        if (tempId >= SummonDragon.NGOC_RONG_DEN_1_SAO && tempId <= SummonDragon.NGOC_RONG_DEN_7_SAO) {
            switch (tempId) {
                case SummonDragon.NGOC_RONG_DEN_1_SAO:
                    SummonDragon.gI().openMenuSummonShenronBlack(pl, (byte) (tempId - 806));
                    break;
                default:
                    NpcService.gI().createMenuConMeo(pl, ConstNpc.TUTORIAL_SUMMON_DRAGONBLACK,
                            -1, "Bạn chỉ có thể gọi rồng từ ngọc 1 sao đen ", "Hướng\ndẫn thêm\n(mới)", "OK");
                    break;
            }
        }
    }

    private void learnSkill(Player pl, Item item) {
        Message msg;
        try {
            if (item.template.gender == pl.gender || item.template.gender == 3) {
                String[] subName = item.template.name.split("");
                byte level = Byte.parseByte(subName[subName.length - 1]);
                Skill curSkill = SkillUtil.getSkillByItemID(pl, item.template.id);
                if (curSkill.point == 7) {
                    Service.gI().sendThongBao(pl, "Kỹ năng đã đạt tối đa!");
                } else {
                    if (curSkill.point == 0) {
                        if (level == 1) {
                            curSkill = SkillUtil.createSkill(SkillUtil.getTempSkillSkillByItemID(item.template.id),
                                    level);
                            SkillUtil.setSkill(pl, curSkill);
                            InventoryServiceNew.gI().subQuantityItemsBag(pl, item, 1);
                            msg = Service.gI().messageSubCommand((byte) 23);
                            msg.writer().writeShort(curSkill.skillId);
                            pl.sendMessage(msg);
                            msg.cleanup();
                        } else {
                            Skill skillNeed = SkillUtil
                                    .createSkill(SkillUtil.getTempSkillSkillByItemID(item.template.id), level);
                            Service.gI().sendThongBao(pl,
                                    "Vui lòng học " + skillNeed.template.name + " cấp " + skillNeed.point + " trước!");
                        }
                    } else {
                        if (curSkill.point + 1 == level) {
                            curSkill = SkillUtil.createSkill(SkillUtil.getTempSkillSkillByItemID(item.template.id),
                                    level);
                            // System.out.println(curSkill.template.name + " - " + curSkill.point);
                            SkillUtil.setSkill(pl, curSkill);
                            InventoryServiceNew.gI().subQuantityItemsBag(pl, item, 1);
                            msg = Service.gI().messageSubCommand((byte) 62);
                            msg.writer().writeShort(curSkill.skillId);
                            pl.sendMessage(msg);
                            msg.cleanup();
                        } else {
                            Service.gI().sendThongBao(pl, "Vui lòng học " + curSkill.template.name + " cấp "
                                    + (curSkill.point + 1) + " trước!");
                        }
                    }
                    InventoryServiceNew.gI().sendItemBags(pl);
                }
            } else {
                Service.gI().sendThongBao(pl, "Không thể thực hiện");
            }
        } catch (Exception e) {
            Logger.logException(UseItem.class, e);
        }
    }

    private void useTDLT(Player pl, Item item) {
        if (pl.itemTime.isUseTDLT) {
            ItemTimeService.gI().turnOffTDLT(pl, item);
        } else {
            ItemTimeService.gI().turnOnTDLT(pl, item);
        }
    }

    private void usePorata(Player pl) {
        if (pl.pet == null || pl.fusion.typeFusion == 4 || pl.fusion.typeFusion == 8 || pl.fusion.typeFusion == 10
                || pl.fusion.typeFusion == 12) {
            Service.getInstance().sendThongBao(pl, "Không thể thực hiện");
        } else {
            if (pl.fusion.typeFusion == ConstPlayer.NON_FUSION) {
                pl.pet.fusion(true);
            } else {
                pl.pet.unFusion();
            }
        }
    }

    private void usePorata2(Player pl) {
        if (pl.fusion.typeFusion == 120) {
            Service.getInstance().sendThongBao(pl, "Không thể thực hiện");
        } else {
            if (pl.fusion.typeFusion == ConstPlayer.NON_FUSION) {
                pl.pet.fusion2(true);
            } else {
                pl.pet.unFusion();
            }
        }
    }

    private void usePorata3(Player pl) {
        if (pl.fusion.typeFusion == 120) {
            Service.getInstance().sendThongBao(pl, "Không thể thực hiện");
        } else {
            if (pl.fusion.typeFusion == ConstPlayer.NON_FUSION) {
                pl.pet.fusion3(true);
            } else {
                pl.pet.unFusion();
            }
        }
    }

    private void usePorata4(Player pl) {
        if (pl.fusion.typeFusion == 120) {
            Service.getInstance().sendThongBao(pl, "Không thể thực hiện");
        } else {
            if (pl.fusion.typeFusion == ConstPlayer.NON_FUSION) {
                pl.pet.fusion4(true);
            } else {
                pl.pet.unFusion();
            }
        }
    }

    private void openCapsuleUI(Player pl) {
        pl.iDMark.setTypeChangeMap(ConstMap.CHANGE_CAPSULE);
        ChangeMapService.gI().openChangeMapTab(pl);
    }

    public void choseMapCapsule(Player pl, int index) {
        int zoneId = -1;
        Zone zoneChose = pl.mapCapsule.get(index);
        // Kiểm tra số lượng người trong khu

        if (zoneChose.getNumOfPlayers() > 9
                || MapService.gI().isMapDoanhTrai(zoneChose.map.mapId)
                || MapService.gI().isMapMiNuong(zoneChose.map.mapId)
                || MapService.gI().isMapMaBu(zoneChose.map.mapId)
                || MapService.gI().isMapSatan(zoneChose.map.mapId)
                || MapService.gI().isMapDiaNguc(zoneChose.map.mapId)
                || MapService.gI().isMapVodai(zoneChose.map.mapId)
                || MapService.gI().isMapMabu13h(zoneChose.map.mapId)
                || MapService.gI().isMapBanDoKhoBau(zoneChose.map.mapId)
                || MapService.gI().isMapHuyDiet(zoneChose.map.mapId)) {
            Service.gI().sendThongBao(pl, "Hiện tại không thể vào được khu!");
            return;
        }
        if (index != 0 || zoneChose.map.mapId == 21
                || zoneChose.map.mapId == 22
                || zoneChose.map.mapId == 23) {
            pl.mapBeforeCapsule = pl.zone;
        } else {
            zoneId = pl.mapBeforeCapsule != null ? pl.mapBeforeCapsule.zoneId : -1;
            pl.mapBeforeCapsule = null;
        }
        ChangeMapService.gI().changeMapBySpaceShip(pl, pl.mapCapsule.get(index).map.mapId, zoneId, -1);
    }

    public void eatPea(Player player) {
        Item pea = null;
        for (Item item : player.inventory.itemsBag) {
            if (item.isNotNullItem() && item.template.type == 6) {
                pea = item;
                break;
            }
        }
        if (pea != null) {
            int hpKiHoiPhuc = 0;
            int lvPea = Integer.parseInt(pea.template.name.substring(13));
            for (Item.ItemOption io : pea.itemOptions) {
                if (io.optionTemplate.id == 2) {
                    hpKiHoiPhuc = io.param * 1000;
                    break;
                }
                if (io.optionTemplate.id == 48) {
                    hpKiHoiPhuc = io.param;
                    break;
                }
            }
            player.nPoint.setHp(player.nPoint.hp + hpKiHoiPhuc);
            player.nPoint.setMp(player.nPoint.mp + hpKiHoiPhuc);
            PlayerService.gI().sendInfoHpMp(player);
            Service.gI().sendInfoPlayerEatPea(player);
            if (player.pet != null && player.zone.equals(player.pet.zone) && !player.pet.isDie()) {
                int statima = 100 * lvPea;
                player.pet.nPoint.stamina += statima;
                if (player.pet.nPoint.stamina > player.pet.nPoint.maxStamina) {
                    player.pet.nPoint.stamina = player.pet.nPoint.maxStamina;
                }
                player.pet.nPoint.setHp(player.pet.nPoint.hp + hpKiHoiPhuc);
                player.pet.nPoint.setMp(player.pet.nPoint.mp + hpKiHoiPhuc);
                Service.gI().sendInfoPlayerEatPea(player.pet);
                Service.gI().chatJustForMe(player, player.pet, "Cảm ơn sư phụ đã cho con đậu thần");
            }

            InventoryServiceNew.gI().subQuantityItemsBag(player, pea, 1);
            InventoryServiceNew.gI().sendItemBags(player);
        }
    }

    private void upSkillPet(Player pl, Item item) {
        if (pl.pet == null) {
            Service.gI().sendThongBao(pl, "Không thể thực hiện");
            return;
        }
        try {
            switch (item.template.id) {
                case 402: // skill 1
                    if (SkillUtil.upSkillPet(pl.pet.playerSkill.skills, 0)) {
                        Service.gI().chatJustForMe(pl, pl.pet, "Cảm ơn sư phụ");
                        InventoryServiceNew.gI().subQuantityItemsBag(pl, item, 1);
                    } else {
                        Service.gI().sendThongBao(pl, "Không thể thực hiện");
                    }
                    break;
                case 403: // skill 2
                    if (SkillUtil.upSkillPet(pl.pet.playerSkill.skills, 1)) {
                        Service.gI().chatJustForMe(pl, pl.pet, "Cảm ơn sư phụ");
                        InventoryServiceNew.gI().subQuantityItemsBag(pl, item, 1);
                    } else {
                        Service.gI().sendThongBao(pl, "Không thể thực hiện");
                    }
                    break;
                case 404: // skill 3
                    if (SkillUtil.upSkillPet(pl.pet.playerSkill.skills, 2)) {
                        Service.gI().chatJustForMe(pl, pl.pet, "Cảm ơn sư phụ");
                        InventoryServiceNew.gI().subQuantityItemsBag(pl, item, 1);
                    } else {
                        Service.gI().sendThongBao(pl, "Không thể thực hiện");
                    }
                    break;
                case 759: // skill 4
                    if (SkillUtil.upSkillPet(pl.pet.playerSkill.skills, 3)) {
                        Service.gI().chatJustForMe(pl, pl.pet, "Cảm ơn sư phụ");
                        InventoryServiceNew.gI().subQuantityItemsBag(pl, item, 1);
                    } else {
                        Service.gI().sendThongBao(pl, "Không thể thực hiện");
                    }
                    break;
                case 2123: // skill 5
                    if (SkillUtil.upSkillPet(pl.pet.playerSkill.skills, 4)) {
                        Service.gI().chatJustForMe(pl, pl.pet, "Cảm ơn sư phụ");
                        InventoryServiceNew.gI().subQuantityItemsBag(pl, item, 1);
                    } else {
                        Service.gI().sendThongBao(pl, "Không thể thực hiện");
                    }
                    break;

            }

        } catch (Exception e) {
            Service.gI().sendThongBao(pl, "Không thể thực hiện");
        }
    }

    private void ItemSKH(Player pl, Item item) {// hop qua skh
        NpcService.gI().createMenuConMeo(pl, item.template.id, -1, "Hãy chọn một món quà", "Áo", "Quần", "Găng", "Giày",
                "Rada", "Từ Chối");
    }

    private void ItemDHD(Player pl, Item item) {// hop qua do huy diet
        NpcService.gI().createMenuConMeo(pl, item.template.id, -1, "Hãy chọn một món quà", "Áo", "Quần", "Găng", "Giày",
                "Rada", "Từ Chối");
    }

    private void Hopts(Player pl, Item item) {// hop qua do huy diet
        NpcService.gI().createMenuConMeo(pl, item.template.id, -1, "Chọn hành tinh của mày đi", "Set trái đất",
                "Set namec", "Set xayda", "Từ chổi");
    }
      private void Hoptl(Player pl, Item item) {//hop qua do tl top
        NpcService.gI().createMenuConMeo(pl, item.template.id, -1, "Chọn hành tinh của mày đi", "Set trái đất", "Set namec", "Set xayda", "Từ chổi");
    }

    private void Hophd(Player pl, Item item) {// hop qua do huy diet
        NpcService.gI().createMenuConMeo(pl, item.template.id, -1, "Chọn hành tinh của mày đi", "Set trái đất",
                "Set namec", "Set xayda", "Từ chổi");
    }
}
