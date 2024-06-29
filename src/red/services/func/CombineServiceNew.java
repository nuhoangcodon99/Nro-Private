package red.services.func;

import red.services.ItemService;
import red.services.RewardService;
import red.services.InventoryServiceNew;
import red.services.Service;
import com.girlkun.network.io.Message;
import red.consts.ConstNpc;
import red.team.item.Item;
import red.team.item.Item.ItemOption;
import red.team.map.ItemMap;
import red.team.npc.Npc;
import red.team.npc.NpcManager;
import red.team.npc.specialnpc.HacAmEgg;
import red.team.npc.specialnpc.ThienThanEgg;
import red.team.player.Player;
import red.team.server.Manager;
import red.team.server.ServerNotify;
import red.utils.Logger;
import red.utils.Util;

import java.util.*;
import java.util.stream.Collectors;

public class CombineServiceNew {
    
     // SÁCH TUYỆT KỸ //
    public static final int GIAM_DINH_SACH = 1233;
    public static final int TAY_SACH = 1234;
    public static final int NANG_CAP_SACH_TUYET_KY = 1235;
    public static final int PHUC_HOI_SACH = 1236;
    public static final int PHAN_RA_SACH = 1237;
    

    private static final int COST_DOI_VE_DOI_DO_HUY_DIET = 500000000;
    private static final int COST_DAP_DO_KICH_HOAT = 500000000;
    private static final int COST_DOI_MANH_KICH_HOAT = 500000000;

    private static final int COST = 500000000;

    private static final int TIME_COMBINE = 1;

    private static final byte MAX_STAR_ITEM = 8;
    private static final byte MAX_LEVEL_ITEM = 8;

    private static final byte OPEN_TAB_COMBINE = 0;
    private static final byte REOPEN_TAB_COMBINE = 1;
    private static final int NANG_CAP_DO_HD = 19897;
    
    private static final byte COMBINE_SUCCESS = 2;
    private static final byte COMBINE_FAIL = 3;
    private static final byte COMBINE_CHANGE_OPTION = 4;
        public static final int NANG_HUY_DIET_LEN_SKH_VIP = 555;
    public static final int NANG_HUY_DIET_LEN_SKH = 2513;
    public static final int NANG_TL_LEN_HUY_DIET = 2512;
    private static final byte COMBINE_DRAGON_BALL = 5;
    public static final byte OPEN_ITEM = 6;

    public static final int EP_SAO_TRANG_BI = 500;
    public static final int PHA_LE_HOA_TRANG_BI = 501;
    public static final int CHUYEN_HOA_TRANG_BI = 502;

    public static final int NANG_CAP_VAT_PHAM = 510;
    public static final int NANG_CAP_BONG_TAI = 511;
    public static final int MO_CHI_SO_BONG_TAI = 519;
    public static final int NANG_CAP_BONG_TAI_CAP3 = 517;
    public static final int MO_CHI_SO_BONG_TAI_CAP3 = 518;
    public static final int NANG_CAP_BONG_TAI_CAP4 = 523;
    public static final int MO_CHI_SO_BONG_TAI_CAP4 = 524;
    // public static final int NANG_CAP_LINH_THU = 512;
    public static final int NHAP_NGOC_RONG = 513;
    public static final int LAM_PHEP_NHAP_DA = 524;
    public static final int PHAN_RA_DO_THAN_LINH = 514;
    public static final int NANG_CAP_DO_TS = 515;
    public static final int NANG_CAP_SKH_VIP = 516;
    public static final int SAO_PHA_LE_CAP2 = 516;
    public static final int CHE_TAO_TRANG_BI_TS = 520;
    public static final int LUYEN_HOA_CHIEN_LINH = 521;
    public static final int EP_AN_TRANG_BI = 522;
    public static final int NANG_CAP_LINH_THU = 530;
    public static final int DOI_DIEM = 502;
    public static final int NGUYET_AN = 512;
    public static final int CREATE_TRUNG_THIEN_THAN = 533;
    public static final int CREATE_TRUNG_HAC_AM = 534;

    public static final int OPEN_SKH_MA_SU = 535;

    public static final int OPEN_SKH_THAN_SU = 536;

    public static final int NANG_CAP_GAY_THIEN_SU = 537;

    public static final int NANG_CAP_CHAN_MENH = 538;

    public static final int MO_CHI_SO_CAI_TRANG = 539;

    public static final int TINH_LUYEN_CAI_TRANG = 639;
    public static final int TAY_TINH_LUYEN_CAI_TRANG = 659;

    public static final int NANG_CAP_CAN_CAU = 739;
    public static final int SUA_CHUA_CAN_CAU = 759;

    private static final int GOLD_BONG_TAI = 500_000_000;
    private static final int GEM_BONG_TAI = 5_000;
    private static final int RATIO_BONG_TAI = 50;
    private static final int RATIO_NANG_CAP = 45;

    private final Npc baHatMit;
    private final Npc bulmaTL;
    private final Npc granala;
    private final Npc npsthiensu64;
    private final Npc monaito;
    private final Npc kiLan;
    private final Npc arale;
    private static CombineServiceNew i;

    public CombineServiceNew() {
        this.baHatMit = NpcManager.getNpc(ConstNpc.BA_HAT_MIT);
        this.granala = NpcManager.getNpc(ConstNpc.Granola);
        this.npsthiensu64 = NpcManager.getNpc(ConstNpc.NPC_64);
        this.bulmaTL = NpcManager.getNpc(ConstNpc.BUNMA_TL);
        this.monaito = NpcManager.getNpc(ConstNpc.Monaito);
        this.kiLan = NpcManager.getNpc(ConstNpc.KI_LAN);
        this.arale = NpcManager.getNpc(ConstNpc.ARALE);
    }

    public static CombineServiceNew gI() {
        if (i == null) {
            i = new CombineServiceNew();
        }
        return i;
    }

    /**
     * Mở tab đập đồ
     *
     * @param player
     * @param type   kiểu đập đồ
     */
    public void openTabCombine(Player player, int type) {
        player.combineNew.setTypeCombine(type);
        Message msg;
        try {
            msg = new Message(-81);
            msg.writer().writeByte(OPEN_TAB_COMBINE);
            msg.writer().writeUTF(getTextInfoTabCombine(type));
            msg.writer().writeUTF(getTextTopTabCombine(type));
            if (player.iDMark.getNpcChose() != null) {
                msg.writer().writeShort(player.iDMark.getNpcChose().tempId);
            }
            player.sendMessage(msg);
            msg.cleanup();
        } catch (Exception e) {
        }
    }

    /**
     * Hiển thị thông tin đập đồ
     *
     * @param player
     */
    public void showInfoCombine(Player player, int[] index) {
        player.combineNew.clearItemCombine();
        if (index.length > 0) {
            for (int i = 0; i < index.length; i++) {
                player.combineNew.itemsCombine.add(player.inventory.itemsBag.get(index[i]));
            }
        }
        switch (player.combineNew.typeCombine) {
            case NANG_CAP_CAN_CAU:
                if (player.combineNew.itemsCombine.size() == 0) {
                    this.arale.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                            "Anh hãy đưa em 1 cần câu và 1 kit nâng cấp!",
                            "Đóng");
                    return;
                }
                if (player.combineNew.itemsCombine.size() == 2) {
                    if (player.combineNew.itemsCombine.stream().filter(
                            item -> item.isNotNullItem() && (item.template.id == 1399))
                            .count() < 1) {
                        this.arale.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Thiếu cần câu", "Đóng");
                        return;
                    }
                    if (player.combineNew.itemsCombine.stream()
                            .filter(item -> item.isNotNullItem() && item.template.id == 1426)
                            .count() < 1) {
                        this.arale.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Thiếu kit nâng cấp", "Đóng");
                        return;
                    }

                    Item cancau = null;

                    for (Item item : player.combineNew.itemsCombine) {
                        if (item.template.id == 1399) {
                            cancau = item;
                        }
                    }

                    int lvlCanCau = 0;

                    for (ItemOption io : cancau.itemOptions) {
                        if (io.optionTemplate.id == 72) {
                            lvlCanCau = io.param;
                        }
                    }

                    if (lvlCanCau >= 5) {
                        Service.gI().sendThongBao(player, "Cần câu đã nâng cấp tối đa!");
                        return;
                    }

                    String npcSay = "Anh muốn nâng câp cần câu? Cần 1 kít nâng và sau khi nâng cấp, độ bền của cần câu sẽ tăng lên 5 và tăng độ may mắn";

                    this.arale.createOtherMenu(player, ConstNpc.MENU_START_COMBINE,
                            npcSay, "Nâng cấp", "Từ chối");
                } else {
                    if (player.combineNew.itemsCombine.size() > 2) {
                        this.arale.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Nguyên liệu không phù hợp",
                                "Đóng");
                        return;
                    }
                    this.arale.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                            "Còn thiếu nguyên liệu để nâng cấp hãy quay lại sau", "Đóng");
                }
                break;
            case SUA_CHUA_CAN_CAU:
                if (player.combineNew.itemsCombine.size() == 0) {
                    this.arale.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                            "Anh hãy đưa em 1 cần câu và 1 kit sửa chữa!",
                            "Đóng");
                    return;
                }
                if (player.combineNew.itemsCombine.size() == 2) {
                    if (player.combineNew.itemsCombine.stream().filter(
                            item -> item.isNotNullItem() && (item.template.id == 1399))
                            .count() < 1) {
                        this.arale.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Thiếu cần câu", "Đóng");
                        return;
                    }
                    if (player.combineNew.itemsCombine.stream()
                            .filter(item -> item.isNotNullItem() && item.template.id == 1425)
                            .count() < 1) {
                        this.arale.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Thiếu kit sửa chữa", "Đóng");
                        return;
                    }

                    String npcSay = "Anh muốn sửa chữa cần câu chứ? Cần 1 kit sửa chữa. Sau khi sửa chữa thì độ bền sẽ được làm mới !";

                    this.arale.createOtherMenu(player, ConstNpc.MENU_START_COMBINE,
                            npcSay, "Nâng cấp", "Từ chối");
                } else {
                    if (player.combineNew.itemsCombine.size() > 2) {
                        this.arale.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Nguyên liệu không phù hợp",
                                "Đóng");
                        return;
                    }
                    this.arale.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                            "Còn thiếu nguyên liệu để nâng cấp hãy quay lại sau", "Đóng");
                }
                break;
            case TINH_LUYEN_CAI_TRANG:
                if (player.combineNew.itemsCombine.size() == 0) {
                    this.monaito.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                            "Cậu hãy đưa ta 1 cải trang tinh luyện dưới cấp 3 và đá tinh luyện !",
                            "Đóng");
                    return;
                }
                if (player.combineNew.itemsCombine.size() == 2) {
                    if (player.combineNew.itemsCombine.stream().filter(
                            item -> item.isNotNullItem() && (item.template.type == 5))
                            .count() < 1) {
                        this.monaito.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Thiếu cải trang", "Đóng");
                        return;
                    }
                    if (player.combineNew.itemsCombine.stream()
                            .filter(item -> item.isNotNullItem() && item.template.id == 1397)
                            .count() < 1) {
                        this.monaito.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Thiếu đá tinh luyện", "Đóng");
                        return;
                    }

                    Item ctTL = null;

                    for (Item item : player.combineNew.itemsCombine) {
                        if (item.template.type == 5) {
                            ctTL = item;
                        }
                    }

                    int lvlcurTL = 0;

                    for (ItemOption io : ctTL.itemOptions) {
                        if (io.optionTemplate.id == 235) {
                            lvlcurTL = io.param;
                        }
                    }

                    if (lvlcurTL >= 3) {
                        Service.gI().sendThongBao(player, "Cải trang đã tinh luyện tối đa!");
                        return;
                    }

                    String npcSay = "Cậu muốn tinh luyện chỉ số cải trang?\nSau khi nâng cấp sẽ nhận 1 option tinh luyện ngẫu nhiên\n và tăng cấp tinh luyện lên 1";

                    this.monaito.createOtherMenu(player, ConstNpc.MENU_START_COMBINE,
                            npcSay, "Nâng cấp", "Từ chối");
                } else {
                    if (player.combineNew.itemsCombine.size() > 2) {
                        this.monaito.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Nguyên liệu không phù hợp",
                                "Đóng");
                        return;
                    }
                    this.monaito.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                            "Còn thiếu nguyên liệu để nâng cấp hãy quay lại sau", "Đóng");
                }
                break;
                case GIAM_DINH_SACH:
                if (player.combineNew.itemsCombine.size() == 2) {
                    Item sachTuyetKy = null;
                    Item buaGiamDinh = null;
                    for (Item item : player.combineNew.itemsCombine) {
                        if (issachTuyetKy(item)) {
                            sachTuyetKy = item;
                        } else if (item.template.id == 1010) {
                            buaGiamDinh = item;
                        }
                    }
                    if (sachTuyetKy != null && buaGiamDinh != null) {

                        String npcSay = "|1|" + sachTuyetKy.getName() + "\n";
                        npcSay += "|2|" + buaGiamDinh.getName() + " " + buaGiamDinh.quantity + "/1";
                        baHatMit.createOtherMenu(player, ConstNpc.MENU_START_COMBINE, npcSay,
                                "Giám định", "Từ chối");
                    } else {
                        Service.getInstance().sendThongBaoOK(player, "Cần Sách Tuyệt Kỹ và bùa giám định");
                        return;
                    }
                } else {
                    Service.getInstance().sendThongBaoOK(player, "Cần Sách Tuyệt Kỹ và bùa giám định");
                    return;
                }
                break;
            case TAY_SACH:
                if (player.combineNew.itemsCombine.size() == 1) {
                    Item sachTuyetKy = null;
                    for (Item item : player.combineNew.itemsCombine) {
                        if (issachTuyetKy(item)) {
                            sachTuyetKy = item;
                        }
                    }
                    if (sachTuyetKy != null) {
                        String npcSay = "|2|Tẩy Sách Tuyệt Kỹ";
                        baHatMit.createOtherMenu(player, ConstNpc.MENU_START_COMBINE, npcSay,
                                "Đồng ý", "Từ chối");
                    } else {
                        Service.getInstance().sendThongBaoOK(player, "Cần Sách Tuyệt Kỹ để tẩy");
                        return;
                    }
                } else {
                    Service.getInstance().sendThongBaoOK(player, "Cần Sách Tuyệt Kỹ để tẩy");
                    return;
                }
                break;

            case NANG_CAP_SACH_TUYET_KY:
                if (player.combineNew.itemsCombine.size() == 2) {
                    Item sachTuyetKy = null;
                    Item kimBamGiay = null;
                    for (Item item : player.combineNew.itemsCombine) {
                        if (issachTuyetKy(item) && (item.template.id == 1002 || item.template.id == 1004 || item.template.id == 1006)) {
                            sachTuyetKy = item;
                        } else if (item.template.id == 1009) {
                            kimBamGiay = item;
                        }
                    }
                    if (sachTuyetKy != null && kimBamGiay != null) {
                        String npcSay = "|2|Nâng cấp sách tuyệt kỹ\n";
                        npcSay += "Cần 10 Kìm bấm giấy\n"
                                + "Tỉ lệ thành công: 10%\n"
                                + "Nâng cấp thất bại sẽ mất 10 Kìm bấm giấy";
                        baHatMit.createOtherMenu(player, ConstNpc.MENU_START_COMBINE, npcSay,
                                "Nâng cấp", "Từ chối");
                    } else {
                        Service.getInstance().sendThongBaoOK(player, "Cần Sách Tuyệt Kỹ 1 và 10 Kìm bấm giấy.");
                        return;
                    }
                } else {
                    Service.getInstance().sendThongBaoOK(player, "Cần Sách Tuyệt Kỹ 1 và 10 Kìm bấm giấy.");
                    return;
                }
                break;
            case PHUC_HOI_SACH:
                if (player.combineNew.itemsCombine.size() == 1) {
                    Item sachTuyetKy = null;
                    for (Item item : player.combineNew.itemsCombine) {
                        if (issachTuyetKy(item)) {
                            sachTuyetKy = item;
                        }
                    }
                    if (sachTuyetKy != null) {
                        String npcSay = "|2|Phục hồi " + sachTuyetKy.getName() + "\n"
                                + "Cần 10 cuốn sách cũ\n"
                                + "Phí phục hồi 10 triệu vàng";
                        baHatMit.createOtherMenu(player, ConstNpc.MENU_START_COMBINE, npcSay,
                                "Đồng ý", "Từ chối");
                    } else {
                        Service.getInstance().sendThongBaoOK(player, "Không tìm thấy vật phẩm");
                        return;
                    }
                } else {
                    Service.getInstance().sendThongBaoOK(player, "Không tìm thấy vật phẩm");
                    return;
                }
                break;
            case PHAN_RA_SACH:
                if (player.combineNew.itemsCombine.size() == 1) {
                    Item sachTuyetKy = null;
                    for (Item item : player.combineNew.itemsCombine) {
                        if (issachTuyetKy(item)) {
                            sachTuyetKy = item;
                        }
                    }
                    if (sachTuyetKy != null) {
                        String npcSay = "|2|Phân rã sách\n"
                                + "Nhận lại 5 cuốn sách cũ\n"
                                + "Phí rã 10 triệu vàng";
                        baHatMit.createOtherMenu(player, ConstNpc.MENU_START_COMBINE, npcSay,
                                "Đồng ý", "Từ chối");
                    } else {
                        Service.getInstance().sendThongBaoOK(player, "Không tìm thấy vật phẩm");
                        return;
                    }
                } else {
                    Service.getInstance().sendThongBaoOK(player, "Không tìm thấy vật phẩm");
                    return;
                }
                break;
            case TAY_TINH_LUYEN_CAI_TRANG:
                if (player.combineNew.itemsCombine.size() == 0) {
                    this.monaito.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                            "Hãy đưa ta cải trang chưa kích hoạt chỉ số !",
                            "Đóng");
                    return;
                }
                if (player.combineNew.itemsCombine.size() == 2) {
                    if (player.combineNew.itemsCombine.stream().filter(
                            item -> item.isNotNullItem() && (item.template.type == 5))
                            .count() < 1) {
                        this.monaito.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Thiếu cải trang", "Đóng");
                        return;
                    }
                    if (player.combineNew.itemsCombine.stream()
                            .filter(item -> item.isNotNullItem() && item.template.id == 1398)
                            .count() < 1) {
                        this.monaito.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Thiếu đá tẩy tinh luyện", "Đóng");
                        return;
                    }

                    Item ctTL = null;
                    Item daTTL = null;
                    for (Item item : player.combineNew.itemsCombine) {
                        if (item.template.type == 5) {
                            ctTL = item;
                        } else if (item.template.id == 1398) {
                            daTTL = item;
                        }
                    }

                    int lvlcurTL = 0;
                    for (ItemOption io : ctTL.itemOptions) {
                        if (io.optionTemplate.id == 235) {
                            lvlcurTL = io.param;
                        }
                    }

                    if (lvlcurTL == 0) {
                        Service.gI().sendThongBao(player, "Cải trang chưa tinh luyện !");
                        return;
                    }
                    if (daTTL.quantity < lvlcurTL) {
                        Service.gI().sendThongBao(player, "Thiếu đá tẩy nguyên liệu !");
                        return;
                    }
                    String npcSay = "Cậu muốn tẩy tinh luyện chỉ số cải trang?\nSẽ xóa hết tất cả các các dòng chỉ số tinh luyện và đặt cấp về 0\n Cần "
                            +
                            lvlcurTL + " đá tẩy tinh luyện";

                    this.monaito.createOtherMenu(player, ConstNpc.MENU_START_COMBINE,
                            npcSay, "Nâng cấp", "Từ chối");
                } else if (player.combineNew.itemsCombine.size() > 2) {
                    this.monaito.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Nguyên liệu không phù hợp",
                            "Đóng");
                    return;
                } else {

                    this.monaito.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                            "Còn thiếu nguyên liệu để nâng cấp hãy quay lại sau", "Đóng");
                }

                break;
            case MO_CHI_SO_CAI_TRANG:
                if (player.combineNew.itemsCombine.size() == 0) {
                    this.kiLan.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                            "Hãy đưa em cái trang chưa kích hoạt chỉ số !",
                            "Đóng");
                    return;
                }

                if (player.combineNew.itemsCombine.size() == 2) {
                    if (player.combineNew.itemsCombine.stream().filter(
                            item -> item.isNotNullItem() && (item.template.type == 5))
                            .count() < 1) {
                        this.kiLan.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Thiếu cải trang", "Đóng");
                        return;
                    }
                    if (player.combineNew.itemsCombine.stream()
                            .filter(item -> item.isNotNullItem() && item.template.id == 1237)
                            .count() < 1) {
                        this.kiLan.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Thiếu lồng đèn", "Đóng");
                        return;
                    }

                    String npcSay = "Anh có muốn mở chỉ số cải trang!";

                    this.kiLan.createOtherMenu(player, ConstNpc.MENU_START_COMBINE,
                            npcSay, "Nâng cấp", "Từ chối");
                } else {
                    if (player.combineNew.itemsCombine.size() > 2) {
                        this.kiLan.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Nguyên liệu không phù hợp",
                                "Đóng");
                        return;
                    }
                    this.kiLan.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                            "Còn thiếu nguyên liệu để nâng cấp hãy quay lại sau", "Đóng");
                }
                break;
            case NANG_CAP_CHAN_MENH:
                if (player.combineNew.itemsCombine.size() == 0) {
                    this.monaito.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                            "Hãy đưa ta chân mệnh và 5 viên ngọc rồng 1s ta sẽ phù phép cho !",
                            "Đóng");
                    return;
                }

                if (player.combineNew.itemsCombine.size() == 2) {
                    if (player.combineNew.itemsCombine.stream().filter(
                            item -> item.isNotNullItem() && (item.template.id >= 1252 && item.template.id <= 1259))
                            .count() < 1) {
                        this.monaito.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Thiếu chân mệnh", "Đóng");
                        return;
                    }
                    if (player.combineNew.itemsCombine.stream()
                            .filter(item -> item.isNotNullItem() && item.template.id == 14)
                            .count() < 1) {
                        this.monaito.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Thiếu ngọc rồng 1 sao", "Đóng");
                        return;
                    }

                    String npcSay = "|2|Con có muốn đổi các món nguyên liệu ?\n|7|"
                            + "|7|Chân mệnh "
                            + "10 viên ngọc rồng 1 sao + 5k HN";

                    if (player.inventory.ruby < 5000) {
                        this.monaito.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Hết tiền rồi\nẢo ít thôi con",
                                "Đóng");
                        return;
                    }
                    this.monaito.createOtherMenu(player, ConstNpc.MENU_START_COMBINE,
                            npcSay, "Nâng cấp\n" + "5k" + " Thỏi Vàng 2", "Từ chối");
                } else {
                    if (player.combineNew.itemsCombine.size() > 2) {
                        this.monaito.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Nguyên liệu không phù hợp",
                                "Đóng");
                        return;
                    }
                    this.monaito.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                            "Còn thiếu nguyên liệu để nâng cấp hãy quay lại sau", "Đóng");
                }
                break;
            case NANG_CAP_GAY_THIEN_SU:
                if (player.combineNew.itemsCombine.size() == 0) {
                    this.npsthiensu64.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                            "Hãy đưa ta 1 món gậy thiên sứ 20 đá nâng cấp 5, 50k Thỏi Vàng 2",
                            "Đóng");
                    return;
                }

                if (player.combineNew.itemsCombine.size() == 2) {
                    if (player.combineNew.itemsCombine.stream()
                            .filter(item -> item.isNotNullItem() && item.template.id == 1213)
                            .count() < 1) {
                        this.npsthiensu64.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Thiếu gậy thiên sứ", "Đóng");
                        return;
                    }
                    if (player.combineNew.itemsCombine.stream()
                            .filter(item -> item.isNotNullItem() && item.template.id == 1078)
                            .count() < 1) {
                        this.npsthiensu64.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Thiếu đá nâng cấp 5", "Đóng");
                        return;
                    }

                    String npcSay = "|2|Con có muốn đổi các món nguyên liệu ?\n|7|"
                            + "|7| Gậy thiên sứ nâng cấp"
                            + "|1|Cần " + "50k" + " Thỏi Vàng 2";

                    if (player.inventory.ruby < 50000) {
                        this.npsthiensu64.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Hết tiền rồi\nẢo ít thôi con",
                                "Đóng");
                        return;
                    }
                    this.npsthiensu64.createOtherMenu(player, ConstNpc.MENU_START_COMBINE,
                            npcSay, "Nâng cấp\n" + "50k" + " Thỏi Vàng 2", "Từ chối");
                } else {
                    if (player.combineNew.itemsCombine.size() > 2) {
                        this.npsthiensu64.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Nguyên liệu không phù hợp",
                                "Đóng");
                        return;
                    }
                    this.npsthiensu64.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                            "Còn thiếu nguyên liệu để nâng cấp hãy quay lại sau", "Đóng");
                }
                break;
            case OPEN_SKH_THAN_SU:
                if (player.combineNew.itemsCombine.size() == 0) {
                    this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                            "Hãy đưa ta 1 món thiên sứ,1 món hủy diệt và 1 món SKH Thần linh và 150 đá ngũ sắc",
                            "Đóng");
                    return;
                }
                if (player.combineNew.itemsCombine.size() == 4) {
                    if (player.combineNew.itemsCombine.stream().filter(item -> item.isNotNullItem() && item.isDTS())
                            .count() < 1) {
                        this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Thiếu đồ thiên sứ", "Đóng");
                        return;
                    }
                    if (player.combineNew.itemsCombine.stream().filter(item -> item.isNotNullItem() && item.isDHD())
                            .count() < 1) {
                        this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Thiếu đồ hủy diệt", "Đóng");
                        return;
                    }
                    if (player.combineNew.itemsCombine.stream()
                            .filter(item -> item.isNotNullItem() && item.isSKH() && item.isDTL())
                            .count() < 1) {
                        this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Thiếu đồ thần linh kích hoạt ",
                                "Đóng");
                        return;
                    }
                    if (player.combineNew.itemsCombine.stream()
                            .filter(item -> item.isNotNullItem() && (item.template.id == 674))
                            .count() < 1) {
                        this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Thiếu đá ngũ sắc .", "Đóng");
                        return;
                    }

                    String npcSay = "|2|Con có muốn đổi các món nguyên liệu ?\n|7|"
                            + "Và nhận được "
                            + player.combineNew.itemsCombine.stream().filter(Item::isDTS).findFirst().get().typeName()
                            + " hoặc"
                            + player.combineNew.itemsCombine.stream().filter(Item::isDHD).findFirst().get().typeName()
                            + " kích hoạt Thần sứ tương ứng\n"
                            + "|1|Cần " + "150k" + " Thỏi Vàng 2";

                    if (player.inventory.ruby < 150000) {
                        this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Hết tiền rồi\nẢo ít thôi con",
                                "Đóng");
                        return;
                    }
                    this.baHatMit.createOtherMenu(player, ConstNpc.MENU_START_COMBINE,
                            npcSay, "Nâng cấp\n" + "150k" + " Thỏi Vàng 2", "Từ chối");
                } else {
                    if (player.combineNew.itemsCombine.size() > 4) {
                        this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Nguyên liệu không phù hợp",
                                "Đóng");
                        return;
                    }
                    this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                            "Còn thiếu nguyên liệu để nâng cấp hãy quay lại sau", "Đóng");
                }
                break;
            ///
            case OPEN_SKH_MA_SU:
                if (player.combineNew.itemsCombine.size() == 0) {
                    this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                            "Hãy đưa ta 1 món thần linh,1 món hủy diệt và 1 món SKH ngẫu nhiên và 30 đá ngũ sắc",
                            "Đóng");
                    return;
                }
                if (player.combineNew.itemsCombine.size() == 4) {
                    if (player.combineNew.itemsCombine.stream().filter(item -> item.isNotNullItem() && item.isDTL())
                            .count() < 1) {
                        this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Thiếu đồ thần linh", "Đóng");
                        return;
                    }
                    if (player.combineNew.itemsCombine.stream().filter(item -> item.isNotNullItem() && item.isDHD())
                            .count() < 1) {
                        this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Thiếu đồ hủy diệt", "Đóng");
                        return;
                    }
                    if (player.combineNew.itemsCombine.stream().filter(item -> item.isNotNullItem() && item.isSKH())
                            .count() < 1) {
                        this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Thiếu đồ kích hoạt ", "Đóng");
                        return;
                    }
                    if (player.combineNew.itemsCombine.stream()
                            .filter(item -> item.isNotNullItem() && (item.template.id == 674))
                            .count() < 1) {
                        this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Thiếu đá ngũ sắc .", "Đóng");
                        return;
                    }

                    String npcSay = "|2|Con có muốn đổi các món nguyên liệu ?\n|7|"
                            + "Và nhận được "
                            + player.combineNew.itemsCombine.stream().filter(Item::isDTL).findFirst().get().typeName()
                            + " hoặc "
                            + player.combineNew.itemsCombine.stream().filter(Item::isDHD).findFirst().get().typeName()
                            + " kích hoạt Ma sứ tương ứng\n"
                            + "|1|Cần " + "60k" + " Thỏi Vàng 2";

                    if (player.inventory.ruby < 60000) {
                        this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Hết tiền rồi\nẢo ít thôi con",
                                "Đóng");
                        return;
                    }
                    this.baHatMit.createOtherMenu(player, ConstNpc.MENU_START_COMBINE,
                            npcSay, "Nâng cấp\n" + "60k" + " Thỏi Vàng 2", "Từ chối");
                } else {
                    if (player.combineNew.itemsCombine.size() > 4) {
                        this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Nguyên liệu không phù hợp",
                                "Đóng");
                        return;
                    }
                    this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                            "Còn thiếu nguyên liệu để nâng cấp hãy quay lại sau", "Đóng");
                }
                break;
            //
            case NANG_CAP_BONG_TAI:
                if (player.combineNew.itemsCombine.size() == 2) {
                    Item bongTai = null;
                    Item manhVo = null;
                    for (Item item : player.combineNew.itemsCombine) {
                        if (item.template.id == 454) {
                            bongTai = item;
                        } else if (item.template.id == 933) {
                            manhVo = item;
                        }
                    }
                    if (bongTai != null && manhVo != null && manhVo.quantity >= 99) {

                        player.combineNew.goldCombine = GOLD_BONG_TAI;
                        player.combineNew.gemCombine = GEM_BONG_TAI;
                        player.combineNew.ratioCombine = RATIO_BONG_TAI;

                        String npcSay = "Bông tai Porata cấp 2" + "\n|2|";
                        for (Item.ItemOption io : bongTai.itemOptions) {
                            npcSay += io.getOptionString() + "\n";
                        }
                        npcSay += "|7|Tỉ lệ thành công: " + player.combineNew.ratioCombine + "%" + "\n";
                        if (player.combineNew.goldCombine <= player.inventory.gold) {
                            npcSay += "|1|Cần " + Util.numberToMoney(player.combineNew.goldCombine) + " vàng";
                            baHatMit.createOtherMenu(player, ConstNpc.MENU_START_COMBINE, npcSay,
                                    "Nâng cấp\ncần " + player.combineNew.gemCombine + " ngọc");
                        } else {
                            npcSay += "Còn thiếu "
                                    + Util.numberToMoney(player.combineNew.goldCombine - player.inventory.gold)
                                    + " vàng";
                            baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, npcSay, "Đóng");
                        }
                    } else {
                        this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                                "Cần 1 Bông tai Porata cấp 1 và X99 Mảnh vỡ bông tai", "Đóng");
                    }
                } else {
                    this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                            "Cần 1 Bông tai Porata cấp 1 và X99 Mảnh vỡ bông tai", "Đóng");
                }
                break;
            case MO_CHI_SO_BONG_TAI:
                if (player.combineNew.itemsCombine.size() == 3) {
                    Item bongTai = null;
                    Item manhHon = null;
                    Item daXanhLam = null;
                    for (Item item : player.combineNew.itemsCombine) {
                        if (item.template.id == 921) {
                            bongTai = item;
                        } else if (item.template.id == 934) {
                            manhHon = item;
                        } else if (item.template.id == 935) {
                            daXanhLam = item;
                        }
                    }
                    if (bongTai != null && manhHon != null && daXanhLam != null && manhHon.quantity >= 99) {

                        player.combineNew.goldCombine = GOLD_BONG_TAI;
                        player.combineNew.gemCombine = GEM_BONG_TAI;
                        player.combineNew.ratioCombine = RATIO_NANG_CAP;

                        String npcSay = "Bông tai Porata cấp 2" + "\n|2|";
                        for (Item.ItemOption io : bongTai.itemOptions) {
                            npcSay += io.getOptionString() + "\n";
                        }
                        npcSay += "|7|Tỉ lệ thành công: " + player.combineNew.ratioCombine + "%" + "\n";
                        if (player.combineNew.goldCombine <= player.inventory.gold) {
                            npcSay += "|1|Cần " + Util.numberToMoney(player.combineNew.goldCombine) + " vàng";
                            baHatMit.createOtherMenu(player, ConstNpc.MENU_START_COMBINE, npcSay,
                                    "Nâng cấp\ncần " + player.combineNew.gemCombine + " ngọc");
                        } else {
                            npcSay += "Còn thiếu "
                                    + Util.numberToMoney(player.combineNew.goldCombine - player.inventory.gold)
                                    + " vàng";
                            baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, npcSay, "Đóng");
                        }
                    } else {
                        this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                                "Cần 1 Bông tai Porata cấp 2, X99 Mảnh hồn bông tai và 1 Đá xanh lam", "Đóng");
                    }
                } else {
                    this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                            "Cần 1 Bông tai Porata cấp 2, X99 Mảnh hồn bông tai và 1 Đá xanh lam", "Đóng");
                }
                break;
            case NANG_CAP_BONG_TAI_CAP3:
                if (player.combineNew.itemsCombine.size() == 2) {
                    Item bongTai = null;
                    Item mvbt = null;
                    for (Item item : player.combineNew.itemsCombine) {
                        if (item.template.id == 921) {
                            bongTai = item;
                        } else if (item.template.id == 2076) {
                            mvbt = item;
                        }
                    }
                    if (bongTai != null && mvbt != null && mvbt.quantity >= 999) {

                        player.combineNew.goldCombine = GOLD_BONG_TAI;
                        player.combineNew.gemCombine = GEM_BONG_TAI;
                        player.combineNew.ratioCombine = RATIO_BONG_TAI;

                        String npcSay = "Bông tai Porata cấp 3" + "\n|2|";
                        for (Item.ItemOption io : bongTai.itemOptions) {
                            npcSay += io.getOptionString() + "\n";
                        }
                        npcSay += "|7|Tỉ lệ thành công: " + player.combineNew.ratioCombine + "%" + "\n";
                        if (player.combineNew.goldCombine <= player.inventory.gold) {
                            npcSay += "|1|Cần " + Util.numberToMoney(player.combineNew.goldCombine) + " vàng";
                            baHatMit.createOtherMenu(player, ConstNpc.MENU_START_COMBINE, npcSay,
                                    "Nâng cấp\ncần " + player.combineNew.gemCombine + " ngọc");
                        } else {
                            npcSay += "Còn thiếu "
                                    + Util.numberToMoney(player.combineNew.goldCombine - player.inventory.gold)
                                    + " vàng";
                            baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, npcSay, "Đóng");
                        }
                    } else {
                        this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                                "Cần 1 Bông tai Porata cấp 2 và X999 MVBT ", "Đóng");
                    }
                } else {
                    this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                            "Cần 1 Bông tai Porata cấp 2 và X999 MVBT ", "Đóng");
                }
                break;
            case MO_CHI_SO_BONG_TAI_CAP3:
                if (player.combineNew.itemsCombine.size() == 3) {
                    Item bongTai = null;
                    Item thachPhu = null;
                    Item daXanhLam = null;
                    for (Item item : player.combineNew.itemsCombine) {
                        if (item.template.id == 2074) {
                            bongTai = item;
                        } else if (item.template.id == 2036) {
                            thachPhu = item;
                        } else if (item.template.id == 935) {
                            daXanhLam = item;
                        }
                    }
                    if (bongTai != null && thachPhu != null && daXanhLam != null && thachPhu.quantity >= 99) {

                        player.combineNew.goldCombine = GOLD_BONG_TAI;
                        player.combineNew.gemCombine = GEM_BONG_TAI;
                        player.combineNew.ratioCombine = RATIO_NANG_CAP;

                        String npcSay = "Bông tai Porata cấp 3" + "\n|2|";
                        for (Item.ItemOption io : bongTai.itemOptions) {
                            npcSay += io.getOptionString() + "\n";
                        }
                        npcSay += "|7|Tỉ lệ thành công: " + player.combineNew.ratioCombine + "%" + "\n";
                        if (player.combineNew.goldCombine <= player.inventory.gold) {
                            npcSay += "|1|Cần " + Util.numberToMoney(player.combineNew.goldCombine) + " vàng";
                            baHatMit.createOtherMenu(player, ConstNpc.MENU_START_COMBINE, npcSay,
                                    "Nâng cấp\ncần " + player.combineNew.gemCombine + " ngọc");
                        } else {
                            npcSay += "Còn thiếu "
                                    + Util.numberToMoney(player.combineNew.goldCombine - player.inventory.gold)
                                    + " vàng";
                            baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, npcSay, "Đóng");
                        }
                    } else {
                        this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                                "1Cần 1 Bông tai Porata cấp 3, X99 Thạch Phù và 1 Đá xanh lam", "Đóng");
                    }
                } else {
                    this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                            "2Cần 1 Bông tai Porata cấp 3, X99 Thạch Phù và 1 Đá xanh lam", "Đóng");
                }
                break;

            case NANG_CAP_BONG_TAI_CAP4:
                if (player.combineNew.itemsCombine.size() == 2) {
                    Item bongTai = null;
                    Item mvbt = null;
                    for (Item item : player.combineNew.itemsCombine) {
                        if (item.template.id == 2074) {
                            bongTai = item;
                        } else if (item.template.id == 2077) {
                            mvbt = item;
                        }
                    }
                    if (bongTai != null && mvbt != null && mvbt.quantity >= 999) {

                        player.combineNew.goldCombine = GOLD_BONG_TAI;
                        player.combineNew.gemCombine = GEM_BONG_TAI;
                        player.combineNew.ratioCombine = RATIO_BONG_TAI;

                        String npcSay = "Bông tai Porata cấp 3" + "\n|2|";
                        for (Item.ItemOption io : bongTai.itemOptions) {
                            npcSay += io.getOptionString() + "\n";
                        }
                        npcSay += "|7|Tỉ lệ thành công: " + player.combineNew.ratioCombine + "%" + "\n";
                        if (player.combineNew.goldCombine <= player.inventory.gold) {
                            npcSay += "|1|Cần " + Util.numberToMoney(player.combineNew.goldCombine) + " vàng";
                            baHatMit.createOtherMenu(player, ConstNpc.MENU_START_COMBINE, npcSay,
                                    "Nâng cấp\ncần " + player.combineNew.gemCombine + " ngọc");
                        } else {
                            npcSay += "Còn thiếu "
                                    + Util.numberToMoney(player.combineNew.goldCombine - player.inventory.gold)
                                    + " vàng";
                            baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, npcSay, "Đóng");
                        }
                    } else {
                        this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                                "Cần 1 Bông tai Porata cấp 3 và X999 MVBT ", "Đóng");
                    }
                } else {
                    this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                            "Cần 1 Bông tai Porata cấp 2 và X999 MVBT ", "Đóng");
                }
                break;
            case MO_CHI_SO_BONG_TAI_CAP4:
                if (player.combineNew.itemsCombine.size() == 3) {
                    Item bongTai = null;
                    Item thachPhu = null;
                    Item daXanhLam = null;
                    for (Item item : player.combineNew.itemsCombine) {
                        if (item.template.id == 2075) {
                            bongTai = item;
                        } else if (item.template.id == 2036) {
                            thachPhu = item;
                        } else if (item.template.id == 935) {
                            daXanhLam = item;
                        }
                    }
                    if (bongTai != null && thachPhu != null && daXanhLam != null && thachPhu.quantity >= 99) {

                        player.combineNew.goldCombine = GOLD_BONG_TAI;
                        player.combineNew.gemCombine = GEM_BONG_TAI;
                        player.combineNew.ratioCombine = RATIO_NANG_CAP;

                        String npcSay = "Bông tai Porata cấp 3" + "\n|2|";
                        for (Item.ItemOption io : bongTai.itemOptions) {
                            npcSay += io.getOptionString() + "\n";
                        }
                        npcSay += "|7|Tỉ lệ thành công: " + player.combineNew.ratioCombine + "%" + "\n";
                        if (player.combineNew.goldCombine <= player.inventory.gold) {
                            npcSay += "|1|Cần " + Util.numberToMoney(player.combineNew.goldCombine) + " vàng";
                            baHatMit.createOtherMenu(player, ConstNpc.MENU_START_COMBINE, npcSay,
                                    "Nâng cấp\ncần " + player.combineNew.gemCombine + " ngọc");
                        } else {
                            npcSay += "Còn thiếu "
                                    + Util.numberToMoney(player.combineNew.goldCombine - player.inventory.gold)
                                    + " vàng";
                            baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, npcSay, "Đóng");
                        }
                    } else {
                        this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                                "1Cần 1 Bông tai Porata cấp 3, X99 Thạch Phù và 15 Đá xanh lam", "Đóng");
                    }
                } else {
                    this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                            "2Cần 1 Bông tai Porata cấp 3, X99 Thạch Phù và 15 Đá xanh lam", "Đóng");
                }
                break;
            case CHE_TAO_TRANG_BI_TS:
                if (player.combineNew.itemsCombine.size() == 0) {
                    this.npsthiensu64.createOtherMenu(player, ConstNpc.IGNORE_MENU, "serizawa.store", "Yes");
                    return;
                }
                if (player.combineNew.itemsCombine.size() >= 2 && player.combineNew.itemsCombine.size() < 5) {
                    if (player.combineNew.itemsCombine.stream()
                            .filter(item -> item.isNotNullItem() && item.isCongThucVip()).count() < 1) {
                        this.npsthiensu64.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Thiếu Công thức Vip", "Đóng");
                        return;
                    }
                    if (player.combineNew.itemsCombine.stream()
                            .filter(item -> item.isNotNullItem() && item.isManhTS() && item.quantity >= 99)
                            .count() < 1) {
                        this.npsthiensu64.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Thiếu Mảnh đồ thiên sứ",
                                "Đóng");
                        return;
                    }
                    if (player.combineNew.itemsCombine.size() == 3
                            && player.combineNew.itemsCombine.stream()
                                    .filter(item -> item.isNotNullItem() && item.isDaNangCap()).count() < 1
                            || player.combineNew.itemsCombine.size() == 4 && player.combineNew.itemsCombine.stream()
                                    .filter(item -> item.isNotNullItem() && item.isDaNangCap()).count() < 1) {
                        this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Thiếu Đá nâng cấp", "Đóng");
                        return;
                    }
                    if (player.combineNew.itemsCombine.size() == 3
                            && player.combineNew.itemsCombine.stream()
                                    .filter(item -> item.isNotNullItem() && item.isDaMayMan()).count() < 1
                            || player.combineNew.itemsCombine.size() == 4 && player.combineNew.itemsCombine.stream()
                                    .filter(item -> item.isNotNullItem() && item.isDaMayMan()).count() < 1) {
                        this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Thiếu Đá may mắn", "Đóng");
                        return;
                    }
                    Item mTS = null, daNC = null, daMM = null;
                    for (Item item : player.combineNew.itemsCombine) {
                        if (item.isNotNullItem()) {
                            if (item.isManhTS()) {
                                mTS = item;
                            } else if (item.isDaNangCap()) {
                                daNC = item;
                            } else if (item.isDaMayMan()) {
                                daMM = item;
                            }
                        }
                    }
                    int tilemacdinh = 35;
                    int tilenew = tilemacdinh;
                    if (daNC != null) {
                        tilenew += (daNC.template.id - 1073) * 10;
                    }

                    String npcSay = "|2|Chế tạo "
                            + player.combineNew.itemsCombine.stream().filter(Item::isManhTS).findFirst().get()
                                    .typeNameManh()
                            + " Thiên sứ "
                            + player.combineNew.itemsCombine.stream().filter(Item::isCongThucVip).findFirst().get()
                                    .typeHanhTinh()
                            + "\n"
                            + "|7|Mảnh ghép " + mTS.quantity + "/99\n"
                            + "|2|Đá nâng cấp "
                            + player.combineNew.itemsCombine.stream().filter(Item::isDaNangCap).findFirst().get()
                                    .typeDanangcap()
                            + " (+" + (daNC.template.id - 1073) + "0% tỉ lệ thành công)\n"
                            + "|2|Đá may mắn "
                            + player.combineNew.itemsCombine.stream().filter(Item::isDaMayMan).findFirst().get()
                                    .typeDaMayman()
                            + " (+" + (daMM.template.id - 1078) + "0% tỉ lệ tối đa các chỉ số)\n"
                            + "|2|Tỉ lệ thành công: " + tilenew + "%\n"
                            + "|7|Phí nâng cấp: 500 triệu vàng";

                    if (daNC != null) {

                        npcSay += "|2|Đá nâng cấp "
                                + player.combineNew.itemsCombine.stream().filter(Item::isDaNangCap).findFirst().get()
                                        .typeDanangcap()
                                + " (+" + (daNC.template.id - 1073) + "0% tỉ lệ thành công)\n";
                    }
                    if (daMM != null) {
                        npcSay += "|2|Đá may mắn "
                                + player.combineNew.itemsCombine.stream().filter(Item::isDaMayMan).findFirst().get()
                                        .typeDaMayman()
                                + " (+" + (daMM.template.id - 1078) + "0% tỉ lệ tối đa các chỉ số)\n";
                    }
                    if (daNC != null) {
                        tilenew += (daNC.template.id - 1073) * 10;
                        npcSay += "|2|Tỉ lệ thành công: " + tilenew + "%\n";
                    } else {
                        npcSay += "|2|Tỉ lệ thành công: " + tilemacdinh + "%\n";
                    }
                    npcSay += "|7|Phí nâng cấp: 500 triệu vàng";
                    if (player.inventory.gold < 500000000) {
                        this.npsthiensu64.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Bạn không đủ vàng", "Đóng");
                        return;
                    }
                    this.npsthiensu64.createOtherMenu(player, ConstNpc.MENU_START_COMBINE,
                            npcSay, "Nâng cấp\n500 Tr vàng", "Từ chối");
                } else {
                    if (player.combineNew.itemsCombine.size() > 4) {
                        this.npsthiensu64.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Nguyên liệu không phù hợp",
                                "Đóng");
                        return;
                    }
                    this.npsthiensu64.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                            "Không đủ nguyên liệu, mời quay lại sau", "Đóng");
                }
                break;
            case NANG_CAP_LINH_THU:
                if (player.combineNew.itemsCombine.size() == 3) {
                    Item linhthu = null;
                    Item thangtinhthach = null;
                    Item thucan = null;
                    int[] linhThus = new int[] { 2023, 2025, 2022, 2020, 2040, 2038, 2039, 2021, 2041, 2019, 2042, 2024,
                            2026, 1363, 1364, 1365, 1366, 1367, 1368, 1369, 1370, 1371, 1372, 1373, 1374, 1375, 1376,
                            1377, 1378, 1379, 1380, 1381, 1382, 1383, 1384, 1385, 1386 };
                    for (Item item : player.combineNew.itemsCombine) {
                        if (Util.includes((int) item.template.id, linhThus)) {
                            linhthu = item;
                        } else if (item.template.id == 2030) {
                            thangtinhthach = item;
                        } else if (item.template.id >= 663 && item.template.id <= 667) {
                            thucan = item;
                        }
                    }
                    if (linhthu != null && thangtinhthach != null && thucan != null && thangtinhthach.quantity >= 99) {

                        player.combineNew.goldCombine = GOLD_BONG_TAI;
                        player.combineNew.gemCombine = GEM_BONG_TAI;
                        player.combineNew.ratioCombine = RATIO_NANG_CAP;

                        String npcSay = "Linh Thú Siêu Cấp" + "\n|2|";
                        for (Item.ItemOption io : linhthu.itemOptions) {
                            npcSay += io.getOptionString() + "\n";
                        }
                        npcSay += "|7|Tỉ lệ thành công: " + player.combineNew.ratioCombine + "%" + "\n";
                        if (player.combineNew.goldCombine <= player.inventory.gold) {
                            if (player.combineNew.gemCombine <= player.inventory.gem) {
                                npcSay += "|1|Cần " + Util.numberToMoney(player.combineNew.goldCombine) + " vàng";
                                baHatMit.createOtherMenu(player, ConstNpc.MENU_START_COMBINE, npcSay,
                                        "Nâng cấp\ncần " + player.combineNew.gemCombine + " ngọc");
                            } else {

                                npcSay += "\n Còn thiếu "
                                        + Util.numberToMoney(player.combineNew.gemCombine - player.inventory.gem)
                                        + " Ngọc";
                                baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, npcSay, "Đóng");
                            }
                        } else {
                            npcSay += "Còn thiếu "
                                    + Util.numberToMoney(player.combineNew.goldCombine - player.inventory.gold)
                                    + " vàng";

                            baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, npcSay, "Đóng");
                        }

                    } else {
                        this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                                "Cần 1 Linh Thú, X99 Đá Ma Thuât và 1 Thức Ăn", "Đóng");
                    }
                } else {
                    this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                            "Cần 1 Linh Thú, X99 Đá Ma Thuât và 1 Thức Ăn", "Đóng");
                }
                break;
            case EP_SAO_TRANG_BI:
                if (player.combineNew.itemsCombine.size() == 2) {
                    Item trangBi = null;
                    Item daPhaLe = null;
                    for (Item item : player.combineNew.itemsCombine) {
                        if (isTrangBiPhaLeHoa(item)) {
                            trangBi = item;
                        } else if (isDaPhaLe(item)) {
                            daPhaLe = item;
                        }
                    }
                    int star = 0; // sao pha lê đã ép
                    int starEmpty = 0; // lỗ sao pha lê
                    if (trangBi != null && daPhaLe != null) {
                        for (Item.ItemOption io : trangBi.itemOptions) {
                            if (io.optionTemplate.id == 102) {
                                star = io.param;
                            } else if (io.optionTemplate.id == 107) {
                                starEmpty = io.param;
                            }
                        }
                        if (star < starEmpty) {
                            player.combineNew.gemCombine = getGemEpSao(star);
                            String npcSay = trangBi.template.name + "\n|2|";
                            for (Item.ItemOption io : trangBi.itemOptions) {
                                if (io.optionTemplate.id != 102) {
                                    npcSay += io.getOptionString() + "\n";
                                }
                            }
                            if (daPhaLe.template.type == 30) {
                                for (Item.ItemOption io : daPhaLe.itemOptions) {
                                    npcSay += "|7|" + io.getOptionString() + "\n";
                                }
                            } else {
                                npcSay += "|7|" + ItemService.gI().getItemOptionTemplate(getOptionDaPhaLe(daPhaLe)).name
                                        .replaceAll("#", getParamDaPhaLe(daPhaLe) + "") + "\n";
                            }
                            npcSay += "|1|Cần " + Util.numberToMoney(player.combineNew.gemCombine) + " ngọc";
                            baHatMit.createOtherMenu(player, ConstNpc.MENU_START_COMBINE, npcSay,
                                    "Nâng cấp\ncần " + player.combineNew.gemCombine + " ngọc");

                        } else {
                            this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                                    "Cần 1 trang bị có lỗ sao pha lê và 1 loại đá pha lê để ép vào", "Đóng");
                        }
                    } else {
                        this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                                "Cần 1 trang bị có lỗ sao pha lê và 1 loại đá pha lê để ép vào", "Đóng");
                    }
                } else {
                    this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                            "Cần 1 trang bị có lỗ sao pha lê và 1 loại đá pha lê để ép vào", "Đóng");
                }
                break;
            // case PHA_LE_HOA_TRANG_BI:
            // if (player.combineNew.itemsCombine.size() == 1) {
            // Item item = player.combineNew.itemsCombine.get(0);
            // if (isTrangBiPhaLeHoa(item)) {
            // int star = 0;
            // for (Item.ItemOption io : item.itemOptions) {
            // if (io.optionTemplate.id == 107) {
            // star = io.param;
            // break;
            // }
            // }
            // if (star < MAX_STAR_ITEM) {
            // player.combineNew.goldCombine = getGoldPhaLeHoa(star);
            // player.combineNew.gemCombine = getGemPhaLeHoa(star);
            // player.combineNew.ratioCombine = getRatioPhaLeHoa(star);

            // String npcSay = item.template.name + "\n|2|";
            // for (Item.ItemOption io : item.itemOptions) {
            // if (io.optionTemplate.id != 102) {
            // npcSay += io.getOptionString() + "\n";
            // }
            // }
            // npcSay += "|7|Tỉ lệ thành công: " + player.combineNew.ratioCombine + "%" +
            // "\n";
            // if (player.combineNew.goldCombine <= player.inventory.gold) {
            // npcSay += "|1|Cần " + Util.numberToMoney(player.combineNew.goldCombine) + "
            // vàng";
            // baHatMit.createOtherMenu(player, ConstNpc.MENU_START_COMBINE, npcSay,
            // "Nâng cấp\ncần " + player.combineNew.gemCombine + " ngọc");
            // Item thoivang = null;
            // {
            // thoivang = InventoryServiceNew.gI().findItemBag(player, 457);
            // }
            // if (thoivang == null || thoivang.quantity <= 1) {
            // Service.getInstance().sendThongBao(player, "Sắp Hết Thỏi Vàng Rồi!!");

            // } else if (player.inventory.gold <= 1_000_000_000) {
            // InventoryServiceNew.gI().subQuantityItemsBag(player, thoivang, 2);
            // player.inventory.gold += 1_000_000_000;
            // Service.gI().sendMoney(player);
            // Service.getInstance().sendThongBao(player,
            // "Tự Bán 2 Thỏi Vàng Mày Nhận Được 1 Tỷ Vàng !!!");
            // }
            // } else {
            // this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Hết Vàng",
            // "Đóng");
            // }

            // } else {
            // this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU,
            // "Vật phẩm đã đạt tối đa sao pha lê", "Đóng");
            // }
            // } else {
            // this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Vật phẩm này
            // không thể đục lỗ",
            // "Đóng");
            // }
            // } else {
            // this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Hãy hãy chọn 1
            // vật phẩm để pha lê hóa",
            // "Đóng");
            // }
            // break;
            case PHA_LE_HOA_TRANG_BI:
                if (player.combineNew.itemsCombine.size() == 1) {
                    Item item = player.combineNew.itemsCombine.get(0);
                    if (isTrangBiPhaLeHoa(item)) {
                        int star = 0;
                        for (Item.ItemOption io : item.itemOptions) {
                            if (io.optionTemplate.id == 107) {
                                star = io.param;
                                break;
                            }
                        }
                      if (star < MAX_STAR_ITEM) {
                            player.combineNew.goldCombine = getGoldPhaLeHoa(star);
                            player.combineNew.gemCombine = getGemPhaLeHoa(star);
                            player.combineNew.ratioCombine = getRatioPhaLeHoa(star);

                            String npcSay = item.template.name + "\n|2|";
                            for (Item.ItemOption io : item.itemOptions) {
                                if (io.optionTemplate.id != 102) {
                                    npcSay += io.getOptionString() + "\n";
                                }
                            }
                            npcSay += "|7|Tỉ lệ thành công: " + player.combineNew.ratioCombine + "%" + "\n";
                            if (player.combineNew.goldCombine <= player.inventory.gold) {
                                npcSay += "|1|Cần " + Util.numberToMoney(player.combineNew.goldCombine) + " vàng";
                                baHatMit.createOtherMenu(player, ConstNpc.MENU_START_COMBINE, npcSay,
                                        "Nâng cấp\n" + player.combineNew.gemCombine + " ngọc\n" + "1 lần", "Nâng cấp\n" + (player.combineNew.gemCombine * 10) + " ngọc\n" + "10 Lần", "Nâng cấp\n" + (player.combineNew.gemCombine * 100) + " ngọc\n" + "100 lần");
                            } else {
                                npcSay += "Còn thiếu " + Util.numberToMoney(player.combineNew.goldCombine - player.inventory.gold) + " vàng";
                                baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, npcSay, "Đóng");
                            }

                        } else {
                            this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Vật phẩm đã đạt tối đa sao pha lê", "Đóng");
                        }
                    } else {
                        this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Vật phẩm này không thể đục lỗ", "Đóng");
                    }
                } else {
                    this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Hãy hãy chọn 1 vật phẩm để pha lê hóa", "Đóng");
                }
                break;
            case NHAP_NGOC_RONG:
                if (InventoryServiceNew.gI().getCountEmptyBag(player) > 0) {
                    if (player.combineNew.itemsCombine.size() == 1) {
                        Item item = player.combineNew.itemsCombine.get(0);
                        if (item != null && item.isNotNullItem() && (item.template.id > 14 && item.template.id <= 20)
                                && item.quantity >= 7) {
                            String npcSay = "|2|Con có muốn biến 7 " + item.template.name + " thành\n"
                                    + "1 viên " + ItemService.gI().getTemplate((short) (item.template.id - 1)).name
                                    + "\n"
                                    + "|7|Cần 7 " + item.template.name;
                            this.baHatMit.createOtherMenu(player, ConstNpc.MENU_START_COMBINE, npcSay, "Làm phép",
                                    "Từ chối");
                        } else {
                            this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                                    "Cần 7 viên ngọc rồng 2 sao trở lên", "Đóng");
                        }
                    } else {
                        this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                                "Cần 7 viên ngọc rồng 2 sao trở lên", "Đóng");
                    }
                } else {
                    this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Hành trang cần ít nhất 1 chỗ trống",
                            "Đóng");
                }
                break;
            case NANG_CAP_VAT_PHAM:
                if (player.combineNew.itemsCombine.size() >= 2 && player.combineNew.itemsCombine.size() < 4) {
                    if (player.combineNew.itemsCombine.stream()
                            .filter(item -> item.isNotNullItem() && item.template.type < 5).count() < 1) {
                        this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Thiếu đồ nâng cấp", "Đóng");
                        break;
                    }
                    if (player.combineNew.itemsCombine.stream()
                            .filter(item -> item.isNotNullItem() && item.template.type == 14).count() < 1) {
                        this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Thiếu đá nâng cấp", "Đóng");
                        break;
                    }
                    if (player.combineNew.itemsCombine.size() == 3 && player.combineNew.itemsCombine.stream()
                            .filter(item -> item.isNotNullItem() && item.template.id == 987).count() < 1) {
                        this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Thiếu đồ nâng cấp", "Đóng");
                        break;
                    }
                    Item itemDo = null;
                    Item itemDNC = null;
                    Item itemDBV = null;
                    for (int j = 0; j < player.combineNew.itemsCombine.size(); j++) {
                        if (player.combineNew.itemsCombine.get(j).isNotNullItem()) {
                            if (player.combineNew.itemsCombine.size() == 3
                                    && player.combineNew.itemsCombine.get(j).template.id == 987) {
                                itemDBV = player.combineNew.itemsCombine.get(j);
                                continue;
                            }
                            if (player.combineNew.itemsCombine.get(j).template.type < 5) {
                                itemDo = player.combineNew.itemsCombine.get(j);
                            } else {
                                itemDNC = player.combineNew.itemsCombine.get(j);
                            }
                        }
                    }
                    if (isCoupleItemNangCapCheck(itemDo, itemDNC)) {
                        int level = 0;
                        for (Item.ItemOption io : itemDo.itemOptions) {
                            if (io.optionTemplate.id == 72) {
                                level = io.param;
                                break;
                            }
                        }
                        if (level < MAX_LEVEL_ITEM) {
                            player.combineNew.goldCombine = getGoldNangCapDo(level);
                            player.combineNew.ratioCombine = (float) getTileNangCapDo(level);
                            player.combineNew.countDaNangCap = getCountDaNangCapDo(level);
                            player.combineNew.countDaBaoVe = (short) getCountDaBaoVe(level);
                            String npcSay = "|2|Hiện tại " + itemDo.template.name + " (+" + level + ")\n|0|";
                            for (Item.ItemOption io : itemDo.itemOptions) {
                                if (io.optionTemplate.id != 72) {
                                    npcSay += io.getOptionString() + "\n";
                                }
                            }
                            String option = null;
                            int param = 0;
                            for (Item.ItemOption io : itemDo.itemOptions) {
                                if (io.optionTemplate.id == 47
                                        || io.optionTemplate.id == 6
                                        || io.optionTemplate.id == 0
                                        || io.optionTemplate.id == 7
                                        || io.optionTemplate.id == 14
                                        || io.optionTemplate.id == 22
                                        || io.optionTemplate.id == 23) {
                                    option = io.optionTemplate.name;
                                    param = io.param + (io.param * 10 / 100);
                                    break;
                                }
                            }
                            npcSay += "|2|Sau khi nâng cấp (+" + (level + 1) + ")\n|7|"
                                    + option.replaceAll("#", String.valueOf(param))
                                    + "\n|7|Tỉ lệ thành công: " + player.combineNew.ratioCombine + "%\n"
                                    + (player.combineNew.countDaNangCap > itemDNC.quantity ? "|7|" : "|1|")
                                    + "Cần " + player.combineNew.countDaNangCap + " " + itemDNC.template.name
                                    + "\n" + (player.combineNew.goldCombine > player.inventory.gold ? "|7|" : "|1|")
                                    + "Cần " + Util.numberToMoney(player.combineNew.goldCombine) + " vàng";

                            String daNPC = player.combineNew.itemsCombine.size() == 3 && itemDBV != null
                                    ? String.format("\nCần tốn %s đá bảo vệ", player.combineNew.countDaBaoVe)
                                    : "";
                            if ((level == 2 || level == 4 || level == 6)
                                    && !(player.combineNew.itemsCombine.size() == 3 && itemDBV != null)) {
                                npcSay += "\nNếu thất bại sẽ rớt xuống (+" + (level - 1) + ")";
                            }
                            if (player.combineNew.countDaNangCap > itemDNC.quantity) {
                                this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                                        npcSay, "Còn thiếu\n" + (player.combineNew.countDaNangCap - itemDNC.quantity)
                                                + " " + itemDNC.template.name);
                            } else if (player.combineNew.goldCombine > player.inventory.gold) {
                                this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                                        npcSay,
                                        "Còn thiếu\n"
                                                + Util.numberToMoney(
                                                        (player.combineNew.goldCombine - player.inventory.gold))
                                                + " vàng");
                            } else if (player.combineNew.itemsCombine.size() == 3 && Objects.nonNull(itemDBV)
                                    && itemDBV.quantity < player.combineNew.countDaBaoVe) {
                                this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                                        npcSay, "Còn thiếu\n" + (player.combineNew.countDaBaoVe - itemDBV.quantity)
                                                + " đá bảo vệ");
                            } else {
                                this.baHatMit.createOtherMenu(player, ConstNpc.MENU_START_COMBINE,
                                        npcSay, "Nâng cấp\n" + Util.numberToMoney(player.combineNew.goldCombine)
                                                + " vàng" + daNPC,
                                        "Từ chối");
                            }
                        } else {
                            this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                                    "Trang bị của ngươi đã đạt cấp tối đa", "Đóng");
                        }
                    } else {
                        this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                                "Hãy chọn 1 trang bị và 1 loại đá nâng cấp", "Đóng");
                    }
                } else {
                    if (player.combineNew.itemsCombine.size() > 3) {
                        this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Cất đi con ta không thèm", "Đóng");
                        break;
                    }
                    this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                            "Hãy chọn 1 trang bị và 1 loại đá nâng cấp", "Đóng");
                }
                break;
            case PHAN_RA_DO_THAN_LINH:
                if (player.combineNew.itemsCombine.size() == 0) {
                    this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                            "Con hãy đưa ta đồ thần linh để phân rã", "Đóng");
                    return;
                }
                if (player.combineNew.itemsCombine.size() == 1) {
                    List<Integer> itemdov2 = new ArrayList<>(Arrays.asList(562, 564, 566));
                    int couponAdd = 0;
                    Item item = player.combineNew.itemsCombine.get(0);
                    if (item.isNotNullItem()) {
                        if (item.template.id >= 555 && item.template.id <= 567) {
                            couponAdd = itemdov2.stream().anyMatch(t -> t == item.template.id) ? 2
                                    : item.template.id == 561 ? 3 : 1;
                        }
                    }
                    if (couponAdd == 0) {
                        this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                                "Ta chỉ có thể phân rã đồ thần linh thôi", "Đóng");
                        return;
                    }
                    String npcSay = "|2|Sau khi phân rã vật phẩm\n|7|"
                            + "Bạn sẽ nhận được : " + couponAdd + " đá ngũ sắc\n"
                            + (50000000 > player.inventory.gold ? "|7|" : "|1|")
                            + "Cần " + Util.numberToMoney(50000000) + " vàng";

                    if (player.inventory.gold < 50000000) {
                        this.baHatMit.npcChat(player, "Hết tiền rồi\nẢo ít thôi con");
                        return;
                    }
                    this.baHatMit.createOtherMenu(player, ConstNpc.MENU_PHAN_RA_DO_THAN_LINH,
                            npcSay, "Nâng cấp\n" + Util.numberToMoney(50000000) + " vàng", "Từ chối");
                } else {
                    this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                            "Ta chỉ có thể phân rã 1 lần 1 món đồ thần linh", "Đóng");
                }
                break;
            case DOI_DIEM:
                if (player.combineNew.itemsCombine.size() == 0) {
                    this.npsthiensu64.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Con hãy đưa cho ta thức ăn",
                            "Đóng");
                    return;
                }
                if (player.combineNew.itemsCombine.size() == 1) {
                    List<Integer> itemdov2 = new ArrayList<>(Arrays.asList(663, 664, 665, 666, 667));
                    int couponAdd = 0;
                    Item item = player.combineNew.itemsCombine.get(0);
                    if (item.isNotNullItem()) {
                        if (item.template.id >= 663 && item.template.id <= 667) {
                            couponAdd = itemdov2.stream().anyMatch(t -> t == item.template.id) ? 1
                                    : item.template.id <= 667 ? 1 : 1;
                        }
                    }
                    if (couponAdd == 0) {
                        this.npsthiensu64.createOtherMenu(player, ConstNpc.IGNORE_MENU, "THỨC ĂN!!!!!!!!", "Đóng");
                        return;
                    }
                    String npcSay = "|2|Sau khi phân rã vật phẩm\n|7|"
                            + "Bạn sẽ nhận được : " + couponAdd + " điểm\n"
                            + (50000000 > player.inventory.gold ? "|7|" : "|1|")
                            + "Cần " + Util.numberToMoney(50000000) + " vàng";

                    if (player.inventory.gold < 50000000) {
                        this.npsthiensu64.npcChat(player, "Hết tiền rồi\nẢo ít thôi con");
                        return;
                    }
                    this.npsthiensu64.createOtherMenu(player, ConstNpc.MENU_PHAN_RA_DO_THAN_LINH,
                            npcSay, "Thức Ăn", "Từ chối");
                } else {
                    this.npsthiensu64.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Cái Đầu Buồi", "Đóng");
                }
                break;
            case NANG_CAP_DO_TS:
                if (player.combineNew.itemsCombine.size() == 0) {
                    this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                            "1 công thức, 1 đá nâng cấp, 99 mảnh thiên sứ, địt nhau", "Đóng");
                    return;
                }
                if (player.combineNew.itemsCombine.size() == 4) {
                    if (player.combineNew.itemsCombine.stream()
                            .filter(item -> item.isNotNullItem() && item.isCongThucVip()).count() < 1) {
                        this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Thiếu Công Thức", "Đóng");
                        return;
                    }
                    if (player.combineNew.itemsCombine.stream()
                            .filter(item -> item.isNotNullItem() && item.isDaNangCap()).count() < 1) {
                        this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Thiếu đồ hủy diệt", "Đóng");
                        return;
                    }
                    if (player.combineNew.itemsCombine.stream()
                            .filter(item -> item.isNotNullItem() && item.isManhTS() && item.quantity >= 99)
                            .count() < 1) {
                        this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Thiếu mảnh thiên sứ", "Đóng");
                        return;
                    }

                    String npcSay = "|2|Con có muốn đổi các món nguyên liệu ?\n|7|"
                            + "Và nhận được "
                            + player.combineNew.itemsCombine.stream().filter(Item::isManhTS).findFirst().get()
                                    .typeNameManh()
                            + " thiên sứ tương ứng\n"
                            + "|1|Cần " + Util.numberToMoney(COST) + " vàng";

                    if (player.inventory.gold < COST) {
                        this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Hết tiền rồi\nẢo ít thôi con",
                                "Đóng");
                        return;
                    }
                    this.baHatMit.createOtherMenu(player, ConstNpc.MENU_NANG_CAP_DO_TS,
                            npcSay, "Nâng cấp\n" + Util.numberToMoney(COST) + " vàng", "Từ chối");
                } else {
                    if (player.combineNew.itemsCombine.size() > 3) {
                        this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Cất đi con ta không thèm", "Đóng");
                        return;
                    }
                    this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                            "Còn thiếu nguyên liệu để nâng cấp hãy quay lại sau", "Đóng");
                }
                break;
                           case NANG_CAP_DO_HD:
                if (player.combineNew.itemsCombine.size() == 0) {
                    this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Hãy đưa ta 3 món Thần Linh bất kì và 1 đá Linh Thạch", "Đóng");
                    return;
                }
                if (player.combineNew.itemsCombine.size() == 4) {
                    if (player.combineNew.itemsCombine.stream().filter(item -> item.isNotNullItem() && item.isDaLinhThach()).count() < 1) {
                        this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Thiếu Đá Linh Thạch", "Đóng");
                        return;
                    }
                    if (player.combineNew.itemsCombine.stream().filter(item -> item.isNotNullItem() && item.isDTL()).count() < 3) {
                        this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Thiếu Thần Linh", "Đóng");
                        return;
                    }

                    String npcSay = "|2|Con có muốn đổi các món nguyên liệu ?\n|7|"
                            + "Và nhận được " + player.combineNew.itemsCombine.stream().filter(Item::isDTL).findFirst().get().typeName() + " Hủy diệt tương ứng\n"
                            + "|1|Cần " + Util.numberToMoney(COST) + " vàng";

                    if (player.inventory.gold < COST) {
                        this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Hết tiền rồi\nẢo ít thôi con", "Đóng");
                        return;
                    }
                    this.baHatMit.createOtherMenu(player, ConstNpc.MENU_START_COMBINE,
                            npcSay, "Nâng cấp\n" + Util.numberToMoney(COST) + " vàng", "Từ chối");
                } else {
                    if (player.combineNew.itemsCombine.size() > 4) {
                        this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Cất đi con ta không thèm", "Đóng");
                        return;
                    }
                    this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Còn thiếu nguyên liệu để nâng cấp hãy quay lại sau", "Đóng");
                }
                break;
            case NANG_TL_LEN_HUY_DIET:
                if (player.combineNew.itemsCombine.size() == 0) {
                    this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Hãy đưa ta 1 món thần linh, ta sẽ cho 1 món huỷ diệt tương ứng", "Đóng");
                    return;
                }
                if (player.combineNew.itemsCombine.size() == 1) {
                    if (player.combineNew.itemsCombine.stream().filter(item -> item.isNotNullItem() && item.isDTL()).count() != 1) {
                        this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Thiếu đồ thần linh rồi", "Đóng");
                        return;
                    }
                    Item dtl = player.combineNew.itemsCombine.get(0);
//                    Item dtl1 = player.combineNew.itemsCombine.get(1);
//                    Item dtl2 = player.combineNew.itemsCombine.get(2);
                    String npcSay = "|2|Con có muốn đổi các món nguyên liệu ?\n|7|"
                            + "Và nhận được " + player.combineNew.itemsCombine.stream().filter(Item::isDTL).findFirst().get().typeName() + " Huỷ diệt tương ứng\n"
                            + "|1|Cần " + Util.numberToMoney(500000000) + " vàng";

                    if (player.inventory.gold < 500000000) {
                        this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Hết tiền rồi\nẢo ít thôi con", "Đóng");
                        return;
                    }
                    this.baHatMit.createOtherMenu(player, ConstNpc.MENU_START_COMBINE,
                            npcSay, "Nâng cấp\n" + Util.numberToMoney(500000000) + " vàng", "Từ chối");
                } else {
                    if (player.combineNew.itemsCombine.size() > 3) {
                        this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Nguyên liệu không phù hợp", "Đóng");
                        return;
                    }
                    this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Còn thiếu nguyên liệu để nâng cấp hãy quay lại sau", "Đóng");
                }
                break;
            case NANG_HUY_DIET_LEN_SKH_VIP:
                if (player.combineNew.itemsCombine.size() == 0) {
                    this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Hãy đưa ta 3 món huỷ diệt....", "Đóng");
                    return;
                }
                if (player.combineNew.itemsCombine.size() == 3) {
                    if (player.combineNew.itemsCombine.stream().filter(item -> item.isNotNullItem() && item.isDHD()).count() != 3) {
                        this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Thiếu đồ huỷ điệt rồi", "Đóng");
                        return;
                    }
                    Item item1 = player.combineNew.itemsCombine.get(0);
                    Item item2 = player.combineNew.itemsCombine.get(1);
                    Item item3 = player.combineNew.itemsCombine.get(2);

                    String npcSay = "|2|Con có muốn đổi các món nguyên liệu ?\n|7|"
                            + "Và nhận được " + player.combineNew.itemsCombine.stream().filter(Item::isDHD).findFirst().get().typeName() + " kích hoạt VIP tương ứng\n"
                            + "|1|Cần " + Util.numberToMoney(COST) + " vàng";

                    if (player.inventory.gold < COST) {
                        this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Hết tiền rồi\nẢo ít thôi con", "Đóng");
                        return;
                    }
                    this.baHatMit.createOtherMenu(player, ConstNpc.MENU_START_COMBINE,
                            npcSay, "Nâng cấp\n" + Util.numberToMoney(500000000) + " vàng", "Từ chối");
                } else {
                    if (player.combineNew.itemsCombine.size() > 4) {
                        this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Nguyên liệu không phù hợp", "Đóng");
                        return;
                    }
                    this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Còn thiếu nguyên liệu để nâng cấp hãy quay lại sau", "Đóng");
                }
                break;
            case NANG_HUY_DIET_LEN_SKH:
                if (player.combineNew.itemsCombine.size() == 0) {
                    this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Hãy đưa ta 1 món huỷ diệt, ta sẽ cho 1 món huỷ diệt tương ứng", "Đóng");
                    return;
                }
                if (player.combineNew.itemsCombine.size() == 1) {
                    if (player.combineNew.itemsCombine.stream().filter(item -> item.isNotNullItem() && item.isDHD()).count() != 1) {
                        this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Thiếu đồ huỷ diệt rồi", "Đóng");
                        return;
                    }
                    String npcSay = "|2|Con có muốn đổi các món nguyên liệu ?\n|7|"
                            + "Và nhận được " + player.combineNew.itemsCombine.stream().filter(Item::isDHD).findFirst().get().typeName() + " kích hoạt tương ứng\n"
                            + "|1|Cần " + Util.numberToMoney(500000000) + " vàng";

                    if (player.inventory.gold < 500000000) {
                        this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Hết tiền rồi\nẢo ít thôi con", "Đóng");
                        return;
                    }
                    this.baHatMit.createOtherMenu(player, ConstNpc.MENU_START_COMBINE,
                            npcSay, "Nâng cấp\n" + Util.numberToMoney(500000000) + " vàng", "Từ chối");
                } else {
                    if (player.combineNew.itemsCombine.size() > 1) {
                        this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Nguyên liệu không phù hợp", "Đóng");
                        return;
                    }
                    this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Còn thiếu nguyên liệu để nâng cấp hãy quay lại sau", "Đóng");
                }
                break;
            case NANG_CAP_SKH_VIP:
                if (player.combineNew.itemsCombine.size() == 0) {
                    this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                            "Hãy đưa ta 1 món thần linh và 2 món SKH ngẫu nhiên và 20 đá ngũ sắc", "Đóng");
                    return;
                }
                if (player.combineNew.itemsCombine.size() == 4) {
                    if (player.combineNew.itemsCombine.stream().filter(item -> item.isNotNullItem() && item.isDTL())
                            .count() < 1) {
                        this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Thiếu đồ thần linh", "Đóng");
                        return;
                    }
                    if (player.combineNew.itemsCombine.stream().filter(item -> item.isNotNullItem() && item.isSKH())
                            .count() < 2) {
                        this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Thiếu đồ kích hoạt ", "Đóng");
                        return;
                    }
                    if (player.combineNew.itemsCombine.stream()
                            .filter(item -> item.isNotNullItem() && (item.template.id == 674))
                            .count() < 1) {
                        this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Thiếu đá ngũ sắc ", "Đóng");
                        return;
                    }
                    String npcSay = "|2|Con có muốn đổi các món nguyên liệu ?\n|7|"
                            + "Và nhận được "
                            + player.combineNew.itemsCombine.stream().filter(Item::isDTL).findFirst().get().typeName()
                            + " kích hoạt VIP tương ứng\n"
                            + "|1|Cần " + Util.numberToMoney(COST) + " vàng";

                    if (player.inventory.gold < COST) {
                        this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Hết tiền rồi\nẢo ít thôi con",
                                "Đóng");
                        return;
                    }
                    this.baHatMit.createOtherMenu(player, ConstNpc.MENU_NANG_DOI_SKH_VIP,
                            npcSay, "Nâng cấp\n" + Util.numberToMoney(COST) + " vàng", "Từ chối");
                } else {
                    if (player.combineNew.itemsCombine.size() > 3) {
                        this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Nguyên liệu không phù hợp",
                                "Đóng");
                        return;
                    }
                    this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                            "Còn thiếu nguyên liệu để nâng cấp hãy quay lại sau", "Đóng");
                }
                break;
            ////////////////////
            case EP_AN_TRANG_BI:
                if (player.combineNew.itemsCombine.size() == 4) {
                    Item cailoz = null;
                    Item concak = null;
                    Item thoivang = null;
                    Item doHDKH = null;

                    for (Item item : player.combineNew.itemsCombine) {
                        if (item.template.type < 5 && item.isDTS() && item.isSKH()) {
                            cailoz = item;
                        } else if (item.template.id == 674) {
                            concak = item;
                        } else if (item.template.id == 457) {
                            thoivang = item;
                        } else if (item.isDHD() && item.isSKH()) {
                            doHDKH = item;
                        }
                    }

                    if (cailoz != null && doHDKH != null && concak != null && thoivang != null
                            && thoivang.quantity >= 300
                            && concak.quantity >= 300) {

                        player.combineNew.goldCombine = 300000;
                        player.combineNew.gemCombine = 0;
                        player.combineNew.ratioCombine = 100;

                        String npcSay = "Trang Bị" + "\n" + player.combineNew.ratioCombine + "%\n300000 Hồng Ngọc";
                        for (Item.ItemOption io : cailoz.itemOptions) {
                            if (player.combineNew.goldCombine <= player.inventory.ruby) {
                                baHatMit.createOtherMenu(player, ConstNpc.MENU_START_COMBINE, npcSay,
                                        "NÂNG CẤP!!!!");
                            } else {
                                this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                                        "Cần 1 trang bị thiên sứ kích hoạt,1 trang bị hủy diệt kích hoạt 300000 Thỏi Vàng 2, 300 đá ngũ sắc và 300 thỏi vàng",
                                        "Đóng");
                            }
                        }
                    } else {
                        this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                                "Cần 1 trang bị thiên sứ, 300000 Thỏi Vàng 2, 300 đá ngũ sắc và 300 thỏi vàng",
                                "Đóng");
                    }
                } else {
                    this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                            "Cần 1 trang bị thiên sứ, 300000 Thỏi Vàng 2, 300 đá ngũ sắc và 300 thỏi vàng",
                            "Đóng");
                }
                break;
            ////////////////////
            case CREATE_TRUNG_THIEN_THAN:
                if (player.combineNew.itemsCombine.size() == 3) {
                    Item quatrung = null;
                    Item honlinhthu = null;
                    Item thoivang = null;
                    for (Item item : player.combineNew.itemsCombine) {
                        if (item.template.id == 1199) {
                            quatrung = item;
                        } else if (item.template.id == 2029) {
                            honlinhthu = item;
                        } else if (item.template.id == 457) {
                            thoivang = item;
                        }
                    }
                    if (quatrung != null && honlinhthu != null && thoivang != null && thoivang.quantity >= 20
                            && honlinhthu.quantity >= 99) {

                        String npcSay = "Cậu có muốn ấp trứng không ?";

                        bulmaTL.createOtherMenu(player, ConstNpc.MENU_START_COMBINE, npcSay,
                                "Ấp trứng");

                    } else {
                        this.bulmaTL.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                                "Cần 1 quả trứng thiên thần, 99 hồn linh thú và 20 thỏi vàng. ",
                                "Đóng");
                    }
                } else {
                    this.bulmaTL.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                            "Cần 1 quả trứng thiên thần, 99 hồn linh thú và 20 thỏi vàng. ",
                            "Đóng");
                }
                break;
            case CREATE_TRUNG_HAC_AM:
                if (player.combineNew.itemsCombine.size() == 3) {
                    Item quatrung = null;
                    Item honlinhthu = null;
                    Item thoivang = null;
                    for (Item item : player.combineNew.itemsCombine) {
                        if (item.template.id == 1200) {
                            quatrung = item;
                        } else if (item.template.id == 2029) {
                            honlinhthu = item;
                        } else if (item.template.id == 457) {
                            thoivang = item;
                        }
                    }
                    if (quatrung != null && honlinhthu != null && thoivang != null && thoivang.quantity >= 20
                            && honlinhthu.quantity >= 99) {

                        String npcSay = "Cậu có muốn ấp trứng không ?";

                        bulmaTL.createOtherMenu(player, ConstNpc.MENU_START_COMBINE, npcSay,
                                "Ấp trứng");

                    } else {
                        this.bulmaTL.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                                "Cần 1 quả trứng hắc ám, 99 hồn linh thú và 20 thỏi vàng. ",
                                "Đóng");
                    }
                } else {
                    this.bulmaTL.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                            "Cần 1 quả trứng hắc ám, 99 hồn linh thú và 20 thỏi vàng. ",
                            "Đóng");
                }
                break;
            ///////////////
            case NGUYET_AN:
                if (player.combineNew.itemsCombine.size() >= 2 && player.combineNew.itemsCombine.size() < 4) {
                    if (player.combineNew.itemsCombine.stream()
                            .filter(item -> item.isNotNullItem() && item.template.type < 5).count() < 1) {
                        this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Thiếu đồ nâng cấp", "Đóng");
                        break;
                    }
                    if (player.combineNew.itemsCombine.stream()
                            .filter(item -> item.isNotNullItem() && item.template.type == 14).count() < 1) {
                        this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Thiếu đá nâng cấp", "Đóng");
                        break;
                    }
                    if (player.combineNew.itemsCombine.size() == 3 && player.combineNew.itemsCombine.stream()
                            .filter(item -> item.isNotNullItem() && item.template.id == 987).count() < 1) {
                        this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Thiếu đồ nâng cấp", "Đóng");
                        break;
                    }
                    Item itemDo = null;
                    Item itemDNC = null;
                    Item itemDBV = null;
                    for (int j = 0; j < player.combineNew.itemsCombine.size(); j++) {
                        if (player.combineNew.itemsCombine.get(j).isNotNullItem()) {
                            if (player.combineNew.itemsCombine.size() == 3
                                    && player.combineNew.itemsCombine.get(j).template.id == 987) {
                                itemDBV = player.combineNew.itemsCombine.get(j);
                                continue;
                            }
                            if (player.combineNew.itemsCombine.get(j).template.type < 5) {
                                itemDo = player.combineNew.itemsCombine.get(j);
                            } else {
                                itemDNC = player.combineNew.itemsCombine.get(j);
                            }
                        }
                    }
                    if (isCoupleItemNangCapCheck(itemDo, itemDNC)) {
                        int level = 0;
                        for (Item.ItemOption io : itemDo.itemOptions) {
                            if (io.optionTemplate.id >= 34 && io.optionTemplate.id <= 36) {
                                level = io.param;
                                break;
                            }
                        }
                        if (level < 1) {
                            player.combineNew.goldCombine = 50000;
                            player.combineNew.ratioCombine = 50;
                            player.combineNew.countDaNangCap = 99;
                            player.combineNew.countDaBaoVe = (short) getCountDaBaoVe(level);
                            String npcSay = "|2|Hiện tại " + itemDo.template.name + " (+" + level + ")\n|0|";
                            for (Item.ItemOption io : itemDo.itemOptions) {
                                if (io.optionTemplate.id != 34 && io.optionTemplate.id != 36
                                        && io.optionTemplate.id != 35 && io.optionTemplate.id != 127
                                        && io.optionTemplate.id != 128 && io.optionTemplate.id != 129
                                        && io.optionTemplate.id != 130 && io.optionTemplate.id != 131
                                        && io.optionTemplate.id != 132 && io.optionTemplate.id != 133
                                        && io.optionTemplate.id != 134 && io.optionTemplate.id != 135) {
                                    npcSay += io.getOptionString() + "\n";
                                }
                            }
                            String option = null;
                            int param = 0;
                            for (Item.ItemOption io : itemDo.itemOptions) {
                                if (io.optionTemplate.id == 47
                                        || io.optionTemplate.id == 6
                                        || io.optionTemplate.id == 0
                                        || io.optionTemplate.id == 7
                                        || io.optionTemplate.id == 14
                                        || io.optionTemplate.id == 22
                                        || io.optionTemplate.id == 23) {
                                    option = io.optionTemplate.name;
                                    param = io.param + (io.param * 0 / 100);
                                    break;
                                }
                            }

                            npcSay += "|2|Nguyệt Ấn"
                                    + "\n|7|Tỉ lệ thành công: " + player.combineNew.ratioCombine + "%\n"
                                    + (player.combineNew.countDaNangCap > itemDNC.quantity ? "|7|" : "|1|")
                                    + "Cần " + player.combineNew.countDaNangCap + " " + itemDNC.template.name
                                    + "\n" + (player.combineNew.goldCombine > player.inventory.ruby ? "|7|" : "|1|")
                                    + "Cần " + Util.numberToMoney(50000) + " Hòng Ngọc";
                            // for (Item.ItemOption io : itemDo.itemOptions) {
                            String daNPC = player.combineNew.itemsCombine.size() == 3 && itemDBV != null
                                    ? String.format("\nCần tốn %s đá bảo vệ", player.combineNew.countDaBaoVe)
                                    : "";
                            if ((level == 2 || level == 4 || level == 6)
                                    && !(player.combineNew.itemsCombine.size() == 3 && itemDBV != null)) {
                                npcSay += "\nNếu thất bại sẽ rớt xuống (+" + (level - 1) + ")";
                                break;
                            }
                            // for (Item.ItemOption io : itemDo.itemOptions) {
                            // else if (io.optionTemplate.id >= 35 && io.optionTemplate.id <= 36) {
                            // npcSay += "|2|Tinh Ấn"
                            // + "\n|7|Đã Ép Ấn";
                            // break;
                            // }
                            if (player.combineNew.countDaNangCap > itemDNC.quantity) {
                                this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                                        npcSay, "Còn thiếu\n" + (player.combineNew.countDaNangCap - itemDNC.quantity)
                                                + " " + itemDNC.template.name);
                            } else if (player.combineNew.goldCombine > player.inventory.ruby) {
                                this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                                        npcSay, "Còn thiếu\n" + Util.numberToMoney((25000 - player.inventory.ruby))
                                                + " Hòng Ngọc");
                            } else if (player.combineNew.itemsCombine.size() == 3 && Objects.nonNull(itemDBV)
                                    && itemDBV.quantity < player.combineNew.countDaBaoVe) {
                                this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                                        npcSay, "Còn thiếu\n" + (player.combineNew.countDaBaoVe - itemDBV.quantity)
                                                + " đá bảo vệ");
                            } else {
                                this.baHatMit.createOtherMenu(player, ConstNpc.MENU_START_COMBINE,
                                        npcSay, "Nâng cấp\n" + Util.numberToMoney(player.combineNew.goldCombine)
                                                + " Hồng Ngọc" + daNPC,
                                        "Từ chối");
                            }
                        } else {
                            this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                                    "Trang bị này như cc tao đéo ép", "Đóng");
                        }
                    } else {
                        this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                                "Hãy chọn 1 trang bị và 1 loại đá nâng cấp", "Đóng");
                    }
                } else {
                    if (player.combineNew.itemsCombine.size() > 3) {
                        this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Cất đi con ta không thèm", "Đóng");
                        break;
                    }
                    this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                            "Hãy chọn 1 trang bị và 1 loại đá nâng cấp", "Đóng");
                }
                break;
        }
    }

    /**
     * Bắt đầu đập đồ - điều hướng từng loại đập đồ
     *
     * @param player
     */
    public void startCombine(Player player) {
        switch (player.combineNew.typeCombine) {
            case NANG_CAP_CAN_CAU:
                nangCapCanCau(player);
                break;
            case SUA_CHUA_CAN_CAU:
                suaChuaCanCau(player);
                break;
            case TINH_LUYEN_CAI_TRANG:
                tinhLuyenCaiTrang(player);
                break;
            case TAY_TINH_LUYEN_CAI_TRANG:
                tayTinhLuyenCaiTrang(player);
                break;
            case MO_CHI_SO_CAI_TRANG:
                moChiSoCaiTrang(player);
                break;
            case NANG_CAP_CHAN_MENH:
                nangCapChanMenh(player);
                break;
            case NANG_CAP_GAY_THIEN_SU:
                nangCapGayThienSu(player);
                break;
            case OPEN_SKH_THAN_SU:
                openSKHThanSu(player);
                break;
            case OPEN_SKH_MA_SU:
                openSKHMaSu(player);
                break;
            case EP_SAO_TRANG_BI:
                epSaoTrangBi(player);
                break;
                   case NANG_HUY_DIET_LEN_SKH_VIP:
                huyDietLenKichHoatVIP(player);
                break;
            case NANG_TL_LEN_HUY_DIET:
                nangCapThanLinhLenHuyDiet(player);
                break;
            case NANG_HUY_DIET_LEN_SKH:
                huyDietLenKichHoatThuong(player);
                break;
            case PHA_LE_HOA_TRANG_BI:
                phaLeHoaTrangBi(player);
                break;
            // case CHUYEN_HOA_TRANG_BI:
            //
            // break;
            case NHAP_NGOC_RONG:
                nhapNgocRong(player);
                break;

            case PHAN_RA_DO_THAN_LINH:
                phanradothanlinh(player);
                break;

            case NANG_CAP_DO_TS:
                openDTS(player);
                break;
            case CHE_TAO_TRANG_BI_TS:
                cheTaoDoTS(player);
                break;
            case NANG_CAP_SKH_VIP:
                openSKHVIP(player);
                break;
            case NANG_CAP_VAT_PHAM:
                nangCapVatPham(player);
                break;
            case NANG_CAP_BONG_TAI:
                nangCapBongTai(player);
                break;
            case MO_CHI_SO_BONG_TAI:
                moChiSoBongTai(player);
                break;
            case NANG_CAP_LINH_THU:
                moChiSolinhthu(player);
                break;
            case NANG_CAP_BONG_TAI_CAP3:
                nangCapBongTaicap3(player);
                break;
            case MO_CHI_SO_BONG_TAI_CAP3:
                moChiSoBongTaicap3(player);
                break;
            case NANG_CAP_BONG_TAI_CAP4:
                nangCapBongTaicap4(player);
                break;
            case MO_CHI_SO_BONG_TAI_CAP4:
                moChiSoBongTaicap4(player);
                break;
            case LUYEN_HOA_CHIEN_LINH:
                //
                break;
            case EP_AN_TRANG_BI:
                epantrangbi(player);
                break;
            case CREATE_TRUNG_THIEN_THAN:
                trungthienthan(player);
                break;
            case CREATE_TRUNG_HAC_AM:
                trunghacam(player);
                break;
            case NGUYET_AN:
                nguyetan(player);
                break;
            case DOI_DIEM:
                doidiem(player);
                break;
            case GIAM_DINH_SACH:
                giamDinhSach(player);
                break;
            case TAY_SACH:
                taySach(player);
                break;
            case NANG_CAP_SACH_TUYET_KY:
                nangCapSachTuyetKy(player);
                break;
            case PHUC_HOI_SACH:
                phucHoiSach(player);
                break;
            case PHAN_RA_SACH:
                phanRaSach(player);
                break;
        }

        player.iDMark.setIndexMenu(ConstNpc.IGNORE_MENU);
        player.combineNew.clearParamCombine();
        player.combineNew.lastTimeCombine = System.currentTimeMillis();

    }

    public void GetTrangBiKichHoathuydiet(Player player, int id) {
        Item item = ItemService.gI().createNewItem((short) id);
        int[][] optionNormal = { { 127, 128 }, { 130, 132 }, { 133, 135 } };
        int[][] paramNormal = { { 139, 140 }, { 142, 144 }, { 136, 138 } };
        int[][] optionVIP = { { 129 }, { 131 }, { 134 } };
        int[][] paramVIP = { { 141 }, { 143 }, { 137 } };
        int random = Util.nextInt(optionNormal.length);
        int randomSkh = Util.nextInt(100);
        if (item.template.type == 0) {
            item.itemOptions.add(new ItemOption(47, Util.nextInt(1500, 2000)));
        }
        if (item.template.type == 1) {
            item.itemOptions.add(new ItemOption(22, Util.nextInt(100, 150)));
        }
        if (item.template.type == 2) {
            item.itemOptions.add(new ItemOption(0, Util.nextInt(9000, 11000)));
        }
        if (item.template.type == 3) {
            item.itemOptions.add(new ItemOption(23, Util.nextInt(90, 150)));
        }
        if (item.template.type == 4) {
            item.itemOptions.add(new ItemOption(14, Util.nextInt(15, 20)));
        }
        if (randomSkh <= 20) {// tile ra do kich hoat
            if (randomSkh <= 5) { // tile ra option vip
                item.itemOptions.add(new ItemOption(optionVIP[player.gender][0], 1));
                item.itemOptions.add(new ItemOption(paramVIP[player.gender][0], 1));
                item.itemOptions.add(new ItemOption(30, 0));
            } else {//
                item.itemOptions.add(new ItemOption(optionNormal[player.gender][random], 1));
                item.itemOptions.add(new ItemOption(paramNormal[player.gender][random], 1));
                item.itemOptions.add(new ItemOption(30, 0));
            }
        }

        InventoryServiceNew.gI().addItemBag(player, item);
        InventoryServiceNew.gI().sendItemBags(player);
    }

    public void GetTrangBiKichHoatthiensu(Player player, int id) {
        Item item = ItemService.gI().createNewItem((short) id);
        int[][] optionNormal = { { 127, 128 }, { 130, 132 }, { 133, 135 } };
        int[][] paramNormal = { { 139, 140 }, { 142, 144 }, { 136, 138 } };
        int[][] optionVIP = { { 129 }, { 131 }, { 134 } };
        int[][] paramVIP = { { 141 }, { 143 }, { 137 } };
        int random = Util.nextInt(optionNormal.length);
        int randomSkh = Util.nextInt(100);
        if (item.template.type == 0) {
            item.itemOptions.add(new ItemOption(47, Util.nextInt(2000, 2500)));
        }
        if (item.template.type == 1) {
            item.itemOptions.add(new ItemOption(22, Util.nextInt(150, 200)));
        }
        if (item.template.type == 2) {
            item.itemOptions.add(new ItemOption(0, Util.nextInt(18000, 20000)));
        }
        if (item.template.type == 3) {
            item.itemOptions.add(new ItemOption(23, Util.nextInt(150, 200)));
        }
        if (item.template.type == 4) {
            item.itemOptions.add(new ItemOption(14, Util.nextInt(20, 25)));
        }
        if (randomSkh <= 20) {// tile ra do kich hoat
            if (randomSkh <= 5) { // tile ra option vip
                item.itemOptions.add(new ItemOption(optionVIP[player.gender][0], 1));
                item.itemOptions.add(new ItemOption(paramVIP[player.gender][0], 1));
                item.itemOptions.add(new ItemOption(30, 0));
            } else {//
                item.itemOptions.add(new ItemOption(optionNormal[player.gender][random], 1));
                item.itemOptions.add(new ItemOption(paramNormal[player.gender][random], 1));
                item.itemOptions.add(new ItemOption(30, 0));
            }
        }

        InventoryServiceNew.gI().addItemBag(player, item);
        InventoryServiceNew.gI().sendItemBags(player);
    }

    public void khilv2(Player player, int id) {
        Item item = ItemService.gI().createNewItem((short) id);
        item.itemOptions.add(new ItemOption(50, 20));// sd
        item.itemOptions.add(new ItemOption(77, 20));// hp
        item.itemOptions.add(new ItemOption(103, 20));// ki
        item.itemOptions.add(new ItemOption(14, 20));// cm
        item.itemOptions.add(new ItemOption(5, 20));// sd cm
        item.itemOptions.add(new ItemOption(106, 0));
        item.itemOptions.add(new ItemOption(34, 0));
        InventoryServiceNew.gI().addItemBag(player, item);
        InventoryServiceNew.gI().sendItemBags(player);
    }

    public void khilv3(Player player, int id) {
        Item item = ItemService.gI().createNewItem((short) id);
        item.itemOptions.add(new ItemOption(50, 22));// sd
        item.itemOptions.add(new ItemOption(77, 22));// hp
        item.itemOptions.add(new ItemOption(103, 22));// ki
        item.itemOptions.add(new ItemOption(14, 22));// cm
        item.itemOptions.add(new ItemOption(5, 22));// sd cm
        item.itemOptions.add(new ItemOption(106, 0));
        item.itemOptions.add(new ItemOption(35, 0));
        InventoryServiceNew.gI().addItemBag(player, item);
        InventoryServiceNew.gI().sendItemBags(player);
    }

    public void khilv4(Player player, int id) {
        Item item = ItemService.gI().createNewItem((short) id);
        item.itemOptions.add(new ItemOption(50, 24));// sd
        item.itemOptions.add(new ItemOption(77, 24));// hp
        item.itemOptions.add(new ItemOption(103, 24));// ki
        item.itemOptions.add(new ItemOption(14, 24));// cm
        item.itemOptions.add(new ItemOption(5, 24));// sd cm
        item.itemOptions.add(new ItemOption(106, 0));
        item.itemOptions.add(new ItemOption(36, 0));
        InventoryServiceNew.gI().addItemBag(player, item);
        InventoryServiceNew.gI().sendItemBags(player);
    }

    public void khilv5(Player player, int id) {
        Item item = ItemService.gI().createNewItem((short) id);
        item.itemOptions.add(new ItemOption(50, 26));// sd
        item.itemOptions.add(new ItemOption(77, 26));// hp
        item.itemOptions.add(new ItemOption(103, 26));// ki
        item.itemOptions.add(new ItemOption(14, 26));// cm
        item.itemOptions.add(new ItemOption(5, 26));// sd cm
        item.itemOptions.add(new ItemOption(106, 0));
        item.itemOptions.add(new ItemOption(36, 0));
        InventoryServiceNew.gI().addItemBag(player, item);
        InventoryServiceNew.gI().sendItemBags(player);
    }

    private void doiKiemThan(Player player) {
        if (player.combineNew.itemsCombine.size() == 3) {
            Item keo = null, luoiKiem = null, chuoiKiem = null;
            for (Item it : player.combineNew.itemsCombine) {
                if (it.template.id == 2015) {
                    keo = it;
                } else if (it.template.id == 2016) {
                    chuoiKiem = it;
                } else if (it.template.id == 2017) {
                    luoiKiem = it;
                }
            }
            if (keo != null && keo.quantity >= 99 && luoiKiem != null && chuoiKiem != null) {
                if (InventoryServiceNew.gI().getCountEmptyBag(player) > 0) {
                    sendEffectSuccessCombine(player);
                    Item item = ItemService.gI().createNewItem((short) 2018);
                    item.itemOptions.add(new Item.ItemOption(50, Util.nextInt(9, 15)));
                    item.itemOptions.add(new Item.ItemOption(77, Util.nextInt(8, 15)));
                    item.itemOptions.add(new Item.ItemOption(103, Util.nextInt(8, 15)));
                    if (Util.isTrue(80, 100)) {
                        item.itemOptions.add(new Item.ItemOption(93, Util.nextInt(1, 15)));
                    }
                    InventoryServiceNew.gI().addItemBag(player, item);

                    InventoryServiceNew.gI().subQuantityItemsBag(player, keo, 99);
                    InventoryServiceNew.gI().subQuantityItemsBag(player, luoiKiem, 1);
                    InventoryServiceNew.gI().subQuantityItemsBag(player, chuoiKiem, 1);

                    InventoryServiceNew.gI().sendItemBags(player);
                    Service.gI().sendMoney(player);
                    reOpenItemCombine(player);
                }
            }
        }
    }

    private void doiChuoiKiem(Player player) {
        if (player.combineNew.itemsCombine.size() == 1) {
            Item manhNhua = player.combineNew.itemsCombine.get(0);
            if (manhNhua.template.id == 2014 && manhNhua.quantity >= 99) {
                if (InventoryServiceNew.gI().getCountEmptyBag(player) > 0) {
                    sendEffectSuccessCombine(player);
                    Item item = ItemService.gI().createNewItem((short) 2016);
                    InventoryServiceNew.gI().addItemBag(player, item);

                    InventoryServiceNew.gI().subQuantityItemsBag(player, manhNhua, 99);

                    InventoryServiceNew.gI().sendItemBags(player);
                    Service.gI().sendMoney(player);
                    reOpenItemCombine(player);
                }
            }
        }
    }

    private void doiLuoiKiem(Player player) {
        if (player.combineNew.itemsCombine.size() == 1) {
            Item manhSat = player.combineNew.itemsCombine.get(0);
            if (manhSat.template.id == 2013 && manhSat.quantity >= 99) {
                if (InventoryServiceNew.gI().getCountEmptyBag(player) > 0) {
                    sendEffectSuccessCombine(player);
                    Item item = ItemService.gI().createNewItem((short) 2017);
                    InventoryServiceNew.gI().addItemBag(player, item);
                    InventoryServiceNew.gI().subQuantityItemsBag(player, manhSat, 99);

                    InventoryServiceNew.gI().sendItemBags(player);
                    Service.gI().sendMoney(player);
                    reOpenItemCombine(player);
                }
            }
        }
    }
       public void huyDietLenKichHoatThuong(Player player) {
        //  món đầu  làm gốc
        if (player.combineNew.itemsCombine.size() != 1) {
            Service.getInstance().sendThongBao(player, "Thiếu nguyên liệu");
            return;
        }
        Item item1 = player.combineNew.itemsCombine.get(0);
        if (item1.isDHD()) {
            if (InventoryServiceNew.gI().getCountEmptyBag(player) > 0) {
                if (player.inventory.gold < COST) {
                    Service.getInstance().sendThongBao(player, "Con cần thêm vàng để đổi...");
                    return;
                }

                player.inventory.gold -= COST;
                int tile = 100;
                if (Util.isTrue(tile, 100)) {
                    int type = item1.template.type;
                    int[][] items = {{0, 6, 21, 27, 12}, {1, 7, 22, 28, 12}, {2, 8, 23, 29, 12}};
                    int[][] options = {{128, 129, 127}, {130, 131, 132}, {133, 135, 134}};
                    int skhv1 = 25;// ti le
                    int skhv2 = 35;//ti le
                    int skhc = 40;//ti le
                    int skhId = -1;
                    int rd = Util.nextInt(1, 100);
                    if (rd <= skhv1) {
                        skhId = 0;
                    } else if (rd <= skhv1 + skhv2) {
                        skhId = 1;
                    } else if (rd <= skhv1 + skhv2 + skhc) {
                        skhId = 2;
                    }
                    Item item = null;
                    switch (player.gender) {
                        case 0:
                            item = ItemService.gI().itemSKH(items[0][item1.template.type], options[0][skhId]);
                            break;
                        case 1:
                            item = ItemService.gI().itemSKH(items[1][item1.template.type], options[1][skhId]);
                            break;
                        case 2:
                            item = ItemService.gI().itemSKH(items[2][item1.template.type], options[2][skhId]);
                            break;
                    }
                    if (item != null && InventoryServiceNew.gI().getCountEmptyBag(player) > 0) {
                        sendEffectSuccessCombine(player);
                        InventoryServiceNew.gI().addItemBag(player, item);
                        InventoryServiceNew.gI().sendItemBags(player);
                        Service.getInstance().sendThongBao(player, "Bạn đã nhận được " + item.template.name);
                        InventoryServiceNew.gI().subQuantityItemsBag(player, item1, 1);
                        InventoryServiceNew.gI().sendItemBags(player);
                    } else {
                        Service.getInstance().sendThongBao(player, "Bạn phải có ít nhất 1 ô trống hành trang");
                    }
                } else {
                    sendEffectFailCombine(player);
                }
            }
            InventoryServiceNew.gI().sendItemBags(player);
            Service.getInstance().sendMoney(player);
            player.combineNew.itemsCombine.clear();
            reOpenItemCombine(player);
        } else {
            Service.getInstance().sendThongBao(player, "Cần 1 món huỷ diệt");
        }
    }


    private void doiManhKichHoat(Player player) {
        if (player.combineNew.itemsCombine.size() == 2 || player.combineNew.itemsCombine.size() == 3) {
            Item nr1s = null, doThan = null, buaBaoVe = null;
            for (Item it : player.combineNew.itemsCombine) {
                if (it.template.id == 14) {
                    nr1s = it;
                } else if (it.template.id == 2010) {
                    buaBaoVe = it;
                } else if (it.template.id >= 555 && it.template.id <= 567) {
                    doThan = it;
                }
            }

            if (nr1s != null && doThan != null) {
                if (InventoryServiceNew.gI().getCountEmptyBag(player) > 0
                        && player.inventory.gold >= COST_DOI_MANH_KICH_HOAT) {
                    player.inventory.gold -= COST_DOI_MANH_KICH_HOAT;
                    int tiLe = buaBaoVe != null ? 100 : 50;
                    if (Util.isTrue(tiLe, 100)) {
                        sendEffectSuccessCombine(player);
                        Item item = ItemService.gI().createNewItem((short) 2009);
                        item.itemOptions.add(new Item.ItemOption(30, 0));
                        InventoryServiceNew.gI().addItemBag(player, item);
                    } else {
                        sendEffectFailCombine(player);
                    }
                    InventoryServiceNew.gI().subQuantityItemsBag(player, nr1s, 1);
                    InventoryServiceNew.gI().subQuantityItemsBag(player, doThan, 1);
                    if (buaBaoVe != null) {
                        InventoryServiceNew.gI().subQuantityItemsBag(player, buaBaoVe, 1);
                    }
                    InventoryServiceNew.gI().sendItemBags(player);
                    Service.gI().sendMoney(player);
                    reOpenItemCombine(player);
                }
            } else {
                this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                        "Hãy chọn 1 trang bị thần linh và 1 viên ngọc rồng 1 sao", "Đóng");
            }
        }
    }
       private void nangCapThanLinhLenHuyDiet(Player player) {
        if (player.combineNew.itemsCombine.size() == 1) {
            Item dtl = player.combineNew.itemsCombine.get(0);
//            Item dtl1 = player.combineNew.itemsCombine.get(1);
//            Item dtl2 = player.combineNew.itemsCombine.get(2);
            if (dtl != null && dtl.isDTL()) {
                if (InventoryServiceNew.gI().getCountEmptyBag(player) > 0 //check chỗ trống hành trang
                        && player.inventory.gold >= COST_DAP_DO_KICH_HOAT) {
                    player.inventory.gold -= COST_DAP_DO_KICH_HOAT;
                    int tiLe = 40; // tỉ lệ
                    if (Util.isTrue(tiLe, 40)) {
                        sendEffectSuccessCombine(player);
                        ItemService.gI().OpenDHD2(player, player.gender, dtl.template.type);
                        InventoryServiceNew.gI().subQuantityItemsBag(player, dtl, 1);
//                        InventoryServiceNew.gI().subQuantityItemsBag(player, dtl1, 1);
//                        InventoryServiceNew.gI().subQuantityItemsBag(player, dtl2, 1);
                    } else {
                        sendEffectFailCombine(player);
                    }
                    InventoryServiceNew.gI().sendItemBags(player);
                    Service.getInstance().sendMoney(player);
                    reOpenItemCombine(player);
                }
            }
        }
    }

 

    private void phanradothanlinh(Player player) {
        if (player.combineNew.itemsCombine.size() == 1) {
            player.inventory.gold -= 20000000;
            List<Integer> itemdov2 = new ArrayList<>(Arrays.asList(562, 564, 566));
            Item item = player.combineNew.itemsCombine.get(0);
            int couponAdd = itemdov2.stream().anyMatch(t -> t == item.template.id) ? 2
                    : item.template.id == 561 ? 3 : 1;
            sendEffectSuccessCombine(player);
            Item dangusac = ItemService.gI().createNewItem((short) 674);
            Item dangusac1 = ItemService.gI().createNewItem((short) 674);
            Item dangusac2 = ItemService.gI().createNewItem((short) 674);
            InventoryServiceNew.gI().addItemBag(player, dangusac);
            InventoryServiceNew.gI().sendItemBags(player);
            if (item.template.id == 561) {
                InventoryServiceNew.gI().addItemBag(player, dangusac);
                InventoryServiceNew.gI().addItemBag(player, dangusac1);
                InventoryServiceNew.gI().addItemBag(player, dangusac2);
                InventoryServiceNew.gI().sendItemBags(player);
            } else if (item.template.id == 562 || item.template.id == 564 || item.template.id == 566) {
                InventoryServiceNew.gI().addItemBag(player, dangusac);
                InventoryServiceNew.gI().addItemBag(player, dangusac1);
                InventoryServiceNew.gI().sendItemBags(player);
            }
            Service.gI().sendThongBaoOK(player, "Mày Nhận Được Đá Ngũ Sắc");
            InventoryServiceNew.gI().subQuantityItemsBag(player, item, 1);
            player.combineNew.itemsCombine.clear();
            InventoryServiceNew.gI().sendItemBags(player);
            Service.gI().sendMoney(player);
            reOpenItemCombine(player);
        }
    }
    private void giamDinhSach(Player player) {
        if (player.combineNew.itemsCombine.size() == 2) {

            Item sachTuyetKy = null;
            Item buaGiamDinh = null;
            for (Item item : player.combineNew.itemsCombine) {
                if (issachTuyetKy(item)) {
                    sachTuyetKy = item;
                } else if (item.template.id == 1010) {
                    buaGiamDinh = item;
                }
            }
            if (sachTuyetKy != null && buaGiamDinh != null) {
                Item sachTuyetKy_2 = ItemService.gI().createNewItem((short) sachTuyetKy.template.id);
                if (checkHaveOption(sachTuyetKy, 0, 233)) {
                    int tyle = new Random().nextInt(10);
                    if (tyle >= 0 && tyle <= 33) {
                        sachTuyetKy_2.itemOptions.add(new ItemOption(50, new Util().nextInt(5, 10)));
                    } else if (tyle > 33 && tyle <= 66) {
                        sachTuyetKy_2.itemOptions.add(new ItemOption(77, new Util().nextInt(10, 15)));
                    } else {
                        sachTuyetKy_2.itemOptions.add(new ItemOption(103, new Util().nextInt(10, 15)));
                    }
                    for (int i = 1; i < sachTuyetKy.itemOptions.size(); i++) {
                        sachTuyetKy_2.itemOptions.add(new ItemOption(sachTuyetKy.itemOptions.get(i).optionTemplate.id, sachTuyetKy.itemOptions.get(i).param));
                    }
                    sendEffectSuccessCombine(player);
                    InventoryServiceNew.gI().addItemBag(player, sachTuyetKy_2);
                    InventoryServiceNew.gI().subQuantityItemsBag(player, sachTuyetKy, 1);
                    InventoryServiceNew.gI().subQuantityItemsBag(player, buaGiamDinh, 1);
                    InventoryServiceNew.gI().sendItemBags(player);
                    reOpenItemCombine(player);
                } else {
                    Service.getInstance().sendThongBao(player, "Còn cái nịt mà giám");
                    return;
                }
            }
        }
    }

    private void nangCapSachTuyetKy(Player player) {
        if (player.combineNew.itemsCombine.size() == 2) {

            Item sachTuyetKy = null;
            Item kimBamGiay = null;

            for (Item item : player.combineNew.itemsCombine) {
                if (issachTuyetKy(item)) {
                    sachTuyetKy = item;
                } else if (item.template.id == 1009) {
                    kimBamGiay = item;
                }
            }
            Item sachTuyetKy_2 = ItemService.gI().createNewItem((short) ((short) sachTuyetKy.template.id + 1));
            if (sachTuyetKy != null && kimBamGiay != null) {
                if (kimBamGiay.quantity < 10) {
                    Service.getInstance().sendThongBao(player, "Không đủ Kìm bấm giấy mà đòi nâng cấp");
                    return;
                }
                if (checkHaveOption(sachTuyetKy, 0, 233)) {
                    Service.getInstance().sendThongBao(player, "Chưa giám định mà đòi nâng cấp");
                    return;
                }
                for (int i = 0; i < sachTuyetKy.itemOptions.size(); i++) {
                    sachTuyetKy_2.itemOptions.add(new ItemOption(sachTuyetKy.itemOptions.get(i).optionTemplate.id, sachTuyetKy.itemOptions.get(i).param));
                }
                sendEffectSuccessCombine(player);
                InventoryServiceNew.gI().addItemBag(player, sachTuyetKy_2);
                InventoryServiceNew.gI().subQuantityItemsBag(player, sachTuyetKy, 1);
                InventoryServiceNew.gI().subQuantityItemsBag(player, kimBamGiay, 10);
                InventoryServiceNew.gI().sendItemBags(player);
                reOpenItemCombine(player);

            }
        }
    }

    private void phucHoiSach(Player player) {
        if (player.combineNew.itemsCombine.size() == 1) {
            Item cuonSachCu = InventoryServiceNew.gI().findItemBag(player, (short) 1011);
            int goldPhanra = 10_000_000;
            Item sachTuyetKy = null;
            for (Item item : player.combineNew.itemsCombine) {
                if (issachTuyetKy(item)) {
                    sachTuyetKy = item;
                }
            }
            if (sachTuyetKy != null) {
                int doBen = 0;
                ItemOption optionLevel = null;
                for (ItemOption io : sachTuyetKy.itemOptions) {
                    if (io.optionTemplate.id == 231) {
                        doBen = io.param;
                        optionLevel = io;
                        break;
                    }
                }
                if (cuonSachCu == null) {
                    Service.getInstance().sendThongBaoOK(player, "Cần sách tuyệt kỹ và 10 cuốn sách cũ");
                    return;
                }
                if (cuonSachCu.quantity < 10) {
                    Service.getInstance().sendThongBaoOK(player, "Cần sách tuyệt kỹ và 10 cuốn sách cũ");
                    return;
                }
                if (player.inventory.gold < goldPhanra) {
                    Service.getInstance().sendThongBao(player, "Không có tiền mà đòi phục hồi à");
                    return;
                }
                if (doBen != 1000) {
                    for (int i = 0; i < sachTuyetKy.itemOptions.size(); i++) {
                        if (sachTuyetKy.itemOptions.get(i).optionTemplate.id == 231) {
                            sachTuyetKy.itemOptions.get(i).param = 1000;
                            break;
                        }
                    }
                    player.inventory.gold -= 10_000_000;
                    InventoryServiceNew.gI().subQuantityItemsBag(player, cuonSachCu, 10);
                    InventoryServiceNew.gI().sendItemBags(player);
                    Service.getInstance().sendMoney(player);
                    sendEffectSuccessCombine(player);
                    reOpenItemCombine(player);
                } else {
                    Service.getInstance().sendThongBao(player, "Còn dùng được phục hồi ăn cứt à");
                    return;
                }
            }
        }
    }

    private void phanRaSach(Player player) {
        if (player.combineNew.itemsCombine.size() == 1) {
            Item cuonSachCu = ItemService.gI().createNewItem((short) 1011, 5);
            int goldPhanra = 10_000_000;
            Item sachTuyetKy = null;
            for (Item item : player.combineNew.itemsCombine) {
                if (issachTuyetKy(item)) {
                    sachTuyetKy = item;
                }
            }
            if (sachTuyetKy != null) {
                int luotTay = 0;
                ItemOption optionLevel = null;
                for (ItemOption io : sachTuyetKy.itemOptions) {
                    if (io.optionTemplate.id == 230) {
                        luotTay = io.param;
                        optionLevel = io;
                        break;
                    }
                }
                if (player.inventory.gold < goldPhanra) {
                    Service.getInstance().sendThongBao(player, "Không có tiền mà đòi phân rã à");
                    return;
                }
                if (luotTay == 0) {

                    player.inventory.gold -= goldPhanra;
                    InventoryServiceNew.gI().subQuantityItemsBag(player, sachTuyetKy, 1);
                    InventoryServiceNew.gI().addItemBag(player, cuonSachCu);
                    InventoryServiceNew.gI().sendItemBags(player);
                    Service.getInstance().sendMoney(player);
                    sendEffectSuccessCombine(player);
                    reOpenItemCombine(player);

                } else {
                    Service.getInstance().sendThongBao(player, "Còn dùng được phân rã ăn cứt à");
                    return;
                }
            }
        }
    }

    private void taySach(Player player) {
        if (player.combineNew.itemsCombine.size() == 1) {
            Item sachTuyetKy = null;
            for (Item item : player.combineNew.itemsCombine) {
                if (issachTuyetKy(item)) {
                    sachTuyetKy = item;
                }
            }
            if (sachTuyetKy != null) {
                int luotTay = 0;
                ItemOption optionLevel = null;
                for (ItemOption io : sachTuyetKy.itemOptions) {
                    if (io.optionTemplate.id == 230) {
                        luotTay = io.param;
                        optionLevel = io;
                        break;
                    }
                }
                if (luotTay == 0) {
                    Service.getInstance().sendThongBao(player, "Còn cái nịt mà tẩy");
                    return;
                }
                Item sachTuyetKy_2 = ItemService.gI().createNewItem((short) sachTuyetKy.template.id);
                if (checkHaveOption(sachTuyetKy, 0, 233)) {
                    Service.getInstance().sendThongBao(player, "Còn cái nịt mà tẩy");
                    return;
                }
                int tyle = new Random().nextInt(10);
                for (int i = 1; i < sachTuyetKy.itemOptions.size(); i++) {
                    if (sachTuyetKy.itemOptions.get(i).optionTemplate.id == 230) {
                        sachTuyetKy.itemOptions.get(i).param -= 1;
                    }
                }
                sachTuyetKy_2.itemOptions.add(new ItemOption(233, 0));
                for (int i = 1; i < sachTuyetKy.itemOptions.size(); i++) {
                    sachTuyetKy_2.itemOptions.add(new ItemOption(sachTuyetKy.itemOptions.get(i).optionTemplate.id, sachTuyetKy.itemOptions.get(i).param));
                }
                sendEffectSuccessCombine(player);
                InventoryServiceNew.gI().addItemBag(player, sachTuyetKy_2);
                InventoryServiceNew.gI().subQuantityItemsBag(player, sachTuyetKy, 1);
                InventoryServiceNew.gI().sendItemBags(player);
                reOpenItemCombine(player);
            }
        }
    }

    private void doidiem(Player player) {
        if (player.combineNew.itemsCombine.size() == 1) {
            player.inventory.gold -= 0;
            List<Integer> itemdov2 = new ArrayList<>(Arrays.asList(663, 664, 665, 666, 667));
            Item item = player.combineNew.itemsCombine.get(0);
            sendEffectSuccessCombine(player);
            if (item.quantity < 99) {
                Service.gI().sendThongBaoOK(player, "Đéo Đủ Thức Ăn");
            } else if (item.quantity >= 99) {
                InventoryServiceNew.gI().sendItemBags(player);
                player.inventory.coupon += 1;
                Service.gI().sendThongBaoOK(player, "Bú 1 Điểm");
                InventoryServiceNew.gI().subQuantityItemsBag(player, item, 99);
                player.combineNew.itemsCombine.clear();
                InventoryServiceNew.gI().sendItemBags(player);
                Service.gI().sendMoney(player);
                reOpenItemCombine(player);
            }
        }
    }

    public void openDTS(Player player) {
        // check sl đồ tl, đồ hd
        // new update 2 mon huy diet + 1 mon than linh(skh theo style) + 5 manh bat ki
        if (player.combineNew.itemsCombine.size() != 4) {
            Service.gI().sendThongBao(player, "Thiếu đồ");
            return;
        }
        if (player.inventory.gold < COST) {
            Service.gI().sendThongBao(player, "Ảo ít thôi con...");
            return;
        }
        if (InventoryServiceNew.gI().getCountEmptyBag(player) < 1) {
            Service.gI().sendThongBao(player, "Bạn phải có ít nhất 1 ô trống hành trang");
            return;
        }
        Item itemTL = player.combineNew.itemsCombine.stream().filter(item -> item.isNotNullItem() && item.isDaNangCap())
                .findFirst().get();
        List<Item> itemHDs = player.combineNew.itemsCombine.stream()
                .filter(item -> item.isNotNullItem() && item.isCongThucVip()).collect(Collectors.toList());
        Item itemManh = player.combineNew.itemsCombine.stream()
                .filter(item -> item.isNotNullItem() && item.isManhTS() && item.quantity >= 99).findFirst().get();

        player.inventory.gold -= COST;
        sendEffectSuccessCombine(player);
        short[][] itemIds = { { 1048, 1051, 1054, 1057, 1060 }, { 1049, 1052, 1055, 1058, 1061 },
                { 1050, 1053, 1056, 1059, 1062 } }; // thứ tự td - 0,nm - 1, xd - 2

        Item itemTS = ItemService.gI().DoThienSu(
                itemIds[player.gender > 2 ? player.gender : player.gender][itemManh.typeIdManh()], player.gender);
        InventoryServiceNew.gI().addItemBag(player, itemTS);

        InventoryServiceNew.gI().subQuantityItemsBag(player, itemTL, 1);
        InventoryServiceNew.gI().subQuantityItemsBag(player, itemManh, 99);
        itemHDs.forEach(item -> InventoryServiceNew.gI().subQuantityItemsBag(player, item, 1));
        InventoryServiceNew.gI().sendItemBags(player);
        Service.gI().sendMoney(player);
        Service.gI().sendThongBao(player, "Bạn đã nhận được " + itemTS.template.name);
        player.combineNew.itemsCombine.clear();
        reOpenItemCombine(player);
    }

       public void huyDietLenKichHoatVIP(Player player) {
        // 3 đồ hd muốn bỏ gì bỏ món đầu k làm gốc
        if (player.combineNew.itemsCombine.size() != 3) {
            Service.gI().sendThongBao(player, "Sai nguyên liệu");
            return;
        }
        if (player.combineNew.itemsCombine.stream().filter(item -> item.isNotNullItem() && item.isDHD()).count() != 3) {
            Service.gI().sendThongBao(player, "Thiếu đồ huỷ diệt");
            return;
        }
        if (InventoryServiceNew.gI().getCountEmptyBag(player) > 0) {
            if (player.inventory.gold < 1) {
                Service.gI().sendThongBao(player, "Con cần thêm vàng để đổi...");
                return;
            }
            player.inventory.gold -= COST;
            Item dohdodayne = player.combineNew.itemsCombine.stream().filter(Item::isDHD).findFirst().get();
            List<Item> itemdohdlucbovao = player.combineNew.itemsCombine.stream().filter(item -> item.isNotNullItem() && item.isDHD()).collect(Collectors.toList());
            CombineServiceNew.gI().sendEffectOpenItem(player, dohdodayne.template.iconID, dohdodayne.template.iconID);
            short itemId;
            if (dohdodayne.template.gender == 3 || dohdodayne.template.type == 4) {
                itemId = Manager.radaSKHVip[0];
                if (player.getSession().bdPlayer > 0 && Util.isTrue(1, (int) (100 / player.getSession().bdPlayer))) {
                    itemId = Manager.radaSKHVip[0];
                }
            } else {
                itemId = Manager.doSKHVip[dohdodayne.template.gender][dohdodayne.template.type][0];
                if (player.getSession().bdPlayer > 0 && Util.isTrue(1, (int) (100 / player.getSession().bdPlayer))) {
                    itemId = Manager.doSKHVip[dohdodayne.template.gender][dohdodayne.template.type][0];
                }
            }
            int skhId = ItemService.gI().randomSKHId(player.gender);
            Item item = ItemService.gI().itemSKH(itemId, skhId);
            InventoryServiceNew.gI().addItemBag(player, item);
            InventoryServiceNew.gI().subQuantityItemsBag(player, dohdodayne, 1);
            itemdohdlucbovao.forEach(it -> InventoryServiceNew.gI().subQuantityItemsBag(player, it, 1));
            InventoryServiceNew.gI().sendItemBags(player);
            Service.gI().sendMoney(player);
            player.combineNew.itemsCombine.clear();
            reOpenItemCombine(player);
        } else {
            Service.gI().sendThongBao(player, "Bạn phải có ít nhất 1 ô trống hành trang");
        }
    }
    public void cheTaoDoTS(Player player) {
        // Công thức vip + x99 Mảnh thiên sứ + đá nâng cấp + đá may mắn
        if (player.combineNew.itemsCombine.size() < 2 || player.combineNew.itemsCombine.size() > 4) {
            Service.getInstance().sendThongBao(player, "Thiếu vật phẩm, vui lòng thêm vào");
            return;
        }
        if (player.combineNew.itemsCombine.stream().filter(item -> item.isNotNullItem() && item.isCongThucVip())
                .count() != 1) {
            Service.getInstance().sendThongBao(player, "Thiếu Công thức Vip");
            return;
        }
        if (player.combineNew.itemsCombine.stream()
                .filter(item -> item.isNotNullItem() && item.isManhTS() && item.quantity >= 99).count() != 1) {
            Service.getInstance().sendThongBao(player, "Thiếu Mảnh thiên sứ");
            return;
        }
        if (player.combineNew.itemsCombine.size() == 3
                && player.combineNew.itemsCombine.stream().filter(item -> item.isNotNullItem() && item.isDaNangCap())
                        .count() != 1
                || player.combineNew.itemsCombine.size() == 4 && player.combineNew.itemsCombine.stream()
                        .filter(item -> item.isNotNullItem() && item.isDaNangCap()).count() != 1) {
            Service.getInstance().sendThongBao(player, "Thiếu Đá nâng cấp");
            return;
        }
        if (player.combineNew.itemsCombine.size() == 3
                && player.combineNew.itemsCombine.stream().filter(item -> item.isNotNullItem() && item.isDaMayMan())
                        .count() != 1
                || player.combineNew.itemsCombine.size() == 4 && player.combineNew.itemsCombine.stream()
                        .filter(item -> item.isNotNullItem() && item.isDaMayMan()).count() != 1) {
            Service.getInstance().sendThongBao(player, "Thiếu Đá may mắn");
            return;
        }
        Item mTS = null, daNC = null, daMM = null, CtVip = null;
        for (Item item : player.combineNew.itemsCombine) {
            if (item.isNotNullItem()) {
                if (item.isManhTS()) {
                    mTS = item;
                } else if (item.isDaNangCap()) {
                    daNC = item;
                } else if (item.isDaMayMan()) {
                    daMM = item;
                } else if (item.isCongThucVip()) {
                    CtVip = item;
                }
            }
        }
        if (InventoryServiceNew.gI().getCountEmptyBag(player) > 0) {// check chỗ trống hành trang
            if (player.inventory.gold < 500000000) {
                Service.getInstance().sendThongBao(player, "Không đủ vàng để thực hiện");
                return;
            }
            player.inventory.gold -= 500000000;

            int tilemacdinh = 35;
            int tileLucky = 20;
            if (daNC != null) {
                tilemacdinh += (daNC.template.id - 1073) * 10;
            } else {
                tilemacdinh = tilemacdinh;
            }
            if (daMM != null) {
                tileLucky += tileLucky * (daMM.template.id - 1078) * 10 / 100;
            } else {
                tileLucky = tileLucky;
            }

            if (Util.nextInt(0, 100) < tilemacdinh) {
                Item itemCtVip = player.combineNew.itemsCombine.stream()
                        .filter(item -> item.isNotNullItem() && item.isCongThucVip()).findFirst().get();
                if (daNC != null) {
                    Item itemDaNangC = player.combineNew.itemsCombine.stream()
                            .filter(item -> item.isNotNullItem() && item.isDaNangCap()).findFirst().get();
                }
                if (daMM != null) {
                    Item itemDaMayM = player.combineNew.itemsCombine.stream()
                            .filter(item -> item.isNotNullItem() && item.isDaMayMan()).findFirst().get();
                }
                Item itemManh = player.combineNew.itemsCombine.stream()
                        .filter(item -> item.isNotNullItem() && item.isManhTS() && item.quantity >= 99).findFirst()
                        .get();

                tilemacdinh = Util.nextInt(0, 50);
                if (tilemacdinh == 49) {
                    tilemacdinh = 20;
                } else if (tilemacdinh == 48 || tilemacdinh == 47) {
                    tilemacdinh = 19;
                } else if (tilemacdinh == 46 || tilemacdinh == 45) {
                    tilemacdinh = 18;
                } else if (tilemacdinh == 44 || tilemacdinh == 43) {
                    tilemacdinh = 17;
                } else if (tilemacdinh == 42 || tilemacdinh == 41) {
                    tilemacdinh = 16;
                } else if (tilemacdinh == 40 || tilemacdinh == 39) {
                    tilemacdinh = 15;
                } else if (tilemacdinh == 38 || tilemacdinh == 37) {
                    tilemacdinh = 14;
                } else if (tilemacdinh == 36 || tilemacdinh == 35) {
                    tilemacdinh = 13;
                } else if (tilemacdinh == 34 || tilemacdinh == 33) {
                    tilemacdinh = 12;
                } else if (tilemacdinh == 32 || tilemacdinh == 31) {
                    tilemacdinh = 11;
                } else if (tilemacdinh == 30 || tilemacdinh == 29) {
                    tilemacdinh = 10;
                } else if (tilemacdinh <= 28 || tilemacdinh >= 26) {
                    tilemacdinh = 9;
                } else if (tilemacdinh <= 25 || tilemacdinh >= 23) {
                    tilemacdinh = 8;
                } else if (tilemacdinh <= 22 || tilemacdinh >= 20) {
                    tilemacdinh = 7;
                } else if (tilemacdinh <= 19 || tilemacdinh >= 17) {
                    tilemacdinh = 6;
                } else if (tilemacdinh <= 16 || tilemacdinh >= 14) {
                    tilemacdinh = 5;
                } else if (tilemacdinh <= 13 || tilemacdinh >= 11) {
                    tilemacdinh = 4;
                } else if (tilemacdinh <= 10 || tilemacdinh >= 8) {
                    tilemacdinh = 3;
                } else if (tilemacdinh <= 7 || tilemacdinh >= 5) {
                    tilemacdinh = 2;
                } else if (tilemacdinh <= 4 || tilemacdinh >= 2) {
                    tilemacdinh = 1;
                } else if (tilemacdinh <= 1) {
                    tilemacdinh = 0;
                }
                short[][] itemIds = { { 1048, 1051, 1054, 1057, 1060 }, { 1049, 1052, 1055, 1058, 1061 },
                        { 1050, 1053, 1056, 1059, 1062 } }; // thứ tự td - 0,nm - 1, xd - 2

                Item itemTS = ItemService.gI().DoThienSu(
                        itemIds[itemCtVip.template.gender > 2 ? player.gender : itemCtVip.template.gender][itemManh
                                .typeIdManh()],
                        itemCtVip.template.gender);

                tilemacdinh += 10;

                if (tilemacdinh > 0) {
                    for (byte i = 0; i < itemTS.itemOptions.size(); i++) {
                        if (itemTS.itemOptions.get(i).optionTemplate.id != 21
                                && itemTS.itemOptions.get(i).optionTemplate.id != 30) {
                            itemTS.itemOptions.get(i).param += (itemTS.itemOptions.get(i).param * tilemacdinh / 100);
                        }
                    }
                }
                tilemacdinh = Util.nextInt(0, 100);

                if (tilemacdinh <= tileLucky) {
                    if (tilemacdinh >= (tileLucky - 3)) {
                        tileLucky = 3;
                    } else if (tilemacdinh <= (tileLucky - 4) && tilemacdinh >= (tileLucky - 10)) {
                        tileLucky = 2;
                    } else {
                        tileLucky = 1;
                    }
                    itemTS.itemOptions.add(new Item.ItemOption(15, tileLucky));
                    ArrayList<Integer> listOptionBonus = new ArrayList<>();
                    listOptionBonus.add(50);
                    listOptionBonus.add(77);
                    listOptionBonus.add(103);
                    listOptionBonus.add(98);
                    listOptionBonus.add(99);
                    for (int i = 0; i < tileLucky; i++) {
                        tilemacdinh = Util.nextInt(0, listOptionBonus.size());
                        itemTS.itemOptions.add(new ItemOption(listOptionBonus.get(tilemacdinh), Util.nextInt(1, 5)));
                        listOptionBonus.remove(tilemacdinh);
                    }
                }

                InventoryServiceNew.gI().addItemBag(player, itemTS);
                sendEffectSuccessCombine(player);
            } else {
                sendEffectFailCombine(player);
            }
            if (mTS != null && daMM != null && daNC != null && CtVip != null) {
                InventoryServiceNew.gI().subQuantityItemsBag(player, CtVip, 1);
                InventoryServiceNew.gI().subQuantityItemsBag(player, daNC, 1);
                InventoryServiceNew.gI().subQuantityItemsBag(player, mTS, 99);
                InventoryServiceNew.gI().subQuantityItemsBag(player, daMM, 1);
            } else if (CtVip != null && mTS != null) {
                InventoryServiceNew.gI().subQuantityItemsBag(player, CtVip, 1);
                InventoryServiceNew.gI().subQuantityItemsBag(player, mTS, 99);
            } else if (CtVip != null && mTS != null && daNC != null) {
                InventoryServiceNew.gI().subQuantityItemsBag(player, CtVip, 1);
                InventoryServiceNew.gI().subQuantityItemsBag(player, mTS, 99);
                InventoryServiceNew.gI().subQuantityItemsBag(player, daNC, 1);
            } else if (CtVip != null && mTS != null && daMM != null) {
                InventoryServiceNew.gI().subQuantityItemsBag(player, CtVip, 1);
                InventoryServiceNew.gI().subQuantityItemsBag(player, mTS, 99);
                InventoryServiceNew.gI().subQuantityItemsBag(player, daMM, 1);
            }

            InventoryServiceNew.gI().sendItemBags(player);
            Service.getInstance().sendMoney(player);
            reOpenItemCombine(player);
        } else {
            Service.getInstance().sendThongBao(player, "Bạn phải có ít nhất 1 ô trống hành trang");
        }
    }

    public void nangCapCanCau(Player player) {
        if (player.combineNew.itemsCombine.size() != 2) {
            Service.gI().sendThongBao(player, "Thiếu nguyên liệu");
            return;
        }
        if (player.combineNew.itemsCombine.stream()
                .filter(item -> item.isNotNullItem() && (item.template.id == 1399))
                .count() != 1) {
            Service.gI().sendThongBao(player, "Thiếu cần câu");
            return;
        }
        if (player.combineNew.itemsCombine.stream().filter(item -> item.isNotNullItem() && item.template.id == 1426)
                .count() != 1) {
            Service.gI().sendThongBao(player, "Thiếu kit nâng cấp ");
            return;
        }
        Item cancau = null;
        Item kitNangCap = null;
        int lvlCanCau = 0;
        int durCanCau = 0;
        for (Item item : player.combineNew.itemsCombine) {
            if (item.template.id == 1399) {
                cancau = item;
            } else if (item.template.id == 1426) {
                kitNangCap = item;
            }
        }
        for (ItemOption io : cancau.itemOptions) {
            if (io.optionTemplate.id == 72) {
                lvlCanCau = io.param;
            }
        }

        if (lvlCanCau >= 5) {
            Service.gI().sendThongBao(player, "Cần câu đã đạt cấp tối đa!");
            return;
        }
        if (InventoryServiceNew.gI().getCountEmptyBag(player) == 0) {
            Service.gI().sendThongBao(player, "Hành trang không đủ chỗ trống!");
        } else if (cancau != null && kitNangCap != null && lvlCanCau < 5) {
            InventoryServiceNew.gI().subQuantityItemsBag(player, kitNangCap, 1);
            cancau.itemOptions.clear();
            lvlCanCau++;
            durCanCau = 20 + 5 * lvlCanCau;
            cancau.itemOptions.add(new ItemOption(72, lvlCanCau));
            cancau.itemOptions.add(new ItemOption(247, durCanCau));
            sendEffectSuccessCombine(player);
            InventoryServiceNew.gI().sendItemBags(player);
            Service.gI().sendMoney(player);
            reOpenItemCombine(player);
        } else {
            Service.gI().sendThongBao(player, "Nguyên liệu không phù hợp ");
            reOpenItemCombine(player);
        }

    }

    public void suaChuaCanCau(Player player) {
        if (player.combineNew.itemsCombine.size() != 2) {
            Service.gI().sendThongBao(player, "Thiếu nguyên liệu");
            return;
        }
        if (player.combineNew.itemsCombine.stream()
                .filter(item -> item.isNotNullItem() && (item.template.id == 1399))
                .count() != 1) {
            Service.gI().sendThongBao(player, "Thiếu cần câu");
            return;
        }
        if (player.combineNew.itemsCombine.stream().filter(item -> item.isNotNullItem() && item.template.id == 1425)
                .count() != 1) {
            Service.gI().sendThongBao(player, "Thiếu kit sửa chữa ");
            return;
        }
        Item cancau = null;
        Item kitSuaChua = null;
        int lvlCanCau = 0;
        int durCanCau = 0;
        for (Item item : player.combineNew.itemsCombine) {
            if (item.template.id == 1399) {
                cancau = item;
            } else if (item.template.id == 1425) {
                kitSuaChua = item;
            }
        }
        for (ItemOption io : cancau.itemOptions) {
            if (io.optionTemplate.id == 72) {
                lvlCanCau = io.param;
            }
        }

        if (InventoryServiceNew.gI().getCountEmptyBag(player) == 0) {
            Service.gI().sendThongBao(player, "Hành trang không đủ chỗ trống!");
        } else if (cancau != null && kitSuaChua != null) {
            InventoryServiceNew.gI().subQuantityItemsBag(player, kitSuaChua, 1);
            cancau.itemOptions.clear();
            durCanCau = 20 + 5 * lvlCanCau;
            cancau.itemOptions.add(new ItemOption(72, lvlCanCau));
            cancau.itemOptions.add(new ItemOption(247, durCanCau));
            sendEffectSuccessCombine(player);
            InventoryServiceNew.gI().sendItemBags(player);
            Service.gI().sendMoney(player);
            reOpenItemCombine(player);
        } else {
            Service.gI().sendThongBao(player, "Nguyên liệu không phù hợp ");
            reOpenItemCombine(player);
        }
    }

    //
    public void tinhLuyenCaiTrang(Player player) {
        if (player.combineNew.itemsCombine.size() != 2) {
            Service.gI().sendThongBao(player, "Thiếu nguyên liệu");
            return;
        }
        if (player.combineNew.itemsCombine.stream()
                .filter(item -> item.isNotNullItem() && (item.template.type == 5))
                .count() != 1) {
            Service.gI().sendThongBao(player, "Thiếu cải trang");
            return;
        }
        if (player.combineNew.itemsCombine.stream().filter(item -> item.isNotNullItem() && item.template.id == 1397)
                .count() != 1) {
            Service.gI().sendThongBao(player, "Thiếu đá tinh luyện ");
            return;
        }
        Item ctTL = null;
        Item daTL = null;
        int lvlcurTL = 0;
        List<Item.ItemOption> ioCopy = new ArrayList<>();

        for (Item item : player.combineNew.itemsCombine) {
            if (item.template.type == 5) {
                ctTL = item;
            } else if (item.template.id == 1397) {
                daTL = item;
            }
        }
        for (ItemOption io : ctTL.itemOptions) {
            if (io.optionTemplate.id == 235) {
                lvlcurTL = io.param;
                continue;
            } else {
                ioCopy.add(io);
            }
        }

        if (lvlcurTL >= 3) {
            Service.gI().sendThongBao(player, "Cải trang đã tinh luyện tối đa!");
        }
        if (InventoryServiceNew.gI().getCountEmptyBag(player) == 0) {
            Service.gI().sendThongBao(player, "Hành trang không đủ chỗ trống!");
        } else if (ctTL != null && daTL != null && lvlcurTL < 3) {
            InventoryServiceNew.gI().subQuantityItemsBag(player, daTL, 1);
            ctTL.itemOptions.clear();
            lvlcurTL++;
            ctTL.itemOptions.add(new ItemOption(235, lvlcurTL));
            for (ItemOption io : ioCopy) {
                ctTL.itemOptions.add(io);
            }
            ctTL.itemOptions.add(new ItemOption(Util.nextInt(236, 246), Util.nextInt(1, 10)));
            sendEffectSuccessCombine(player);
            InventoryServiceNew.gI().sendItemBags(player);
            Service.gI().sendMoney(player);
            reOpenItemCombine(player);
        } else {
            Service.gI().sendThongBao(player, "Nguyên liệu không phù hợp ");
            reOpenItemCombine(player);
        }

    }

    public void tayTinhLuyenCaiTrang(Player player) {
        if (player.combineNew.itemsCombine.size() != 2) {
            Service.gI().sendThongBao(player, "Thiếu nguyên liệu");
            return;
        }
        if (player.combineNew.itemsCombine.stream()
                .filter(item -> item.isNotNullItem() && (item.template.type == 5))
                .count() != 1) {
            Service.gI().sendThongBao(player, "Thiếu cải trang");
            return;
        }
        if (player.combineNew.itemsCombine.stream().filter(item -> item.isNotNullItem() && item.template.id == 1398)
                .count() != 1) {
            Service.gI().sendThongBao(player, "Thiếu đá tẩy tinh luyện ");
            return;
        }
        Item ctTL = null;
        Item daTTL = null;
        int lvlcurTL = 0;
        List<Item.ItemOption> ioCopy = new ArrayList<>();

        for (Item item : player.combineNew.itemsCombine) {
            if (item.template.type == 5) {
                ctTL = item;
            } else if (item.template.id == 1398) {
                daTTL = item;
            }
        }
        int[] OPTIONS = new int[] { 235, 236, 237, 238, 239, 240, 241, 242, 243, 244, 245, 246 };
        for (ItemOption io : ctTL.itemOptions) {
            if (Util.includes(io.optionTemplate.id, OPTIONS)) {
                if (io.optionTemplate.id == 235) {
                    lvlcurTL = io.param;
                }
                continue;
            } else {
                ioCopy.add(io);
            }
        }
        if (ctTL != null && daTTL != null && lvlcurTL > 0 && daTTL.quantity >= lvlcurTL) {
            InventoryServiceNew.gI().subQuantityItemsBag(player, daTTL, lvlcurTL);
            ctTL.itemOptions.clear();
            for (ItemOption io : ioCopy) {
                ctTL.itemOptions.add(io);
            }
            sendEffectSuccessCombine(player);
            InventoryServiceNew.gI().sendItemBags(player);
            Service.gI().sendMoney(player);
            reOpenItemCombine(player);
        } else {
            Service.gI().sendThongBao(player, "Nguyên liệu không phù hợp ");
            reOpenItemCombine(player);
        }

    }

    // chan menh
    public void moChiSoCaiTrang(Player player) {
        if (player.combineNew.itemsCombine.size() != 2) {
            Service.gI().sendThongBao(player, "Thiếu nguyên liệu");
            return;
        }
        if (player.combineNew.itemsCombine.stream()
                .filter(item -> item.isNotNullItem() && (item.template.type == 5))
                .count() != 1) {
            Service.gI().sendThongBao(player, "Thiếu cải trang");
            return;
        }
        if (player.combineNew.itemsCombine.stream().filter(item -> item.isNotNullItem() && item.template.id == 1237)
                .count() != 1) {
            Service.gI().sendThongBao(player, "Thiếu lồng đèn");
            return;
        }
        ///

        Item caiTrang = null;
        Item longDen = null;
        int checkOption = 0;
        for (Item item : player.combineNew.itemsCombine) {
            if (item.template.type == 5) {
                caiTrang = item;
            } else if (item.template.id == 1237) {
                longDen = item;
            }
        }
        for (Item.ItemOption io : caiTrang.itemOptions) {
            if (io.optionTemplate.id == 232) {
                checkOption++;
            } else if (io.optionTemplate.id == 233) {
                checkOption = 0;
            }
        }
        if (checkOption == 0) {
            Service.gI().sendThongBao(player, "Yêu cầu trang bị chưa kích hoạt ! ");
            return;
        }

        if (caiTrang != null && longDen != null && longDen.quantity >= 10) {
            InventoryServiceNew.gI().subQuantityItemsBag(player, longDen, 10);
            caiTrang.itemOptions.clear();
            caiTrang.itemOptions.add(new ItemOption(50, Util.nextInt(10, 20)));
            caiTrang.itemOptions.add(new ItemOption(77, Util.nextInt(15, 25)));
            caiTrang.itemOptions.add(new ItemOption(103, Util.nextInt(15, 25)));
            caiTrang.itemOptions.add(new ItemOption(101, Util.nextInt(15, 25)));
            caiTrang.itemOptions.add(new ItemOption(14, Util.nextInt(5, 10)));
            caiTrang.itemOptions.add(new ItemOption(5, Util.nextInt(5, 10)));
            caiTrang.itemOptions.add(new ItemOption(98, Util.nextInt(10, 20)));
            caiTrang.itemOptions.add(new ItemOption(108, Util.nextInt(5, 20)));
            caiTrang.itemOptions.add(new ItemOption(18, Util.nextInt(10, 20)));
            caiTrang.itemOptions.add(new ItemOption(233, 0));
            caiTrang.itemOptions.add(new ItemOption(93, Util.nextInt(7, 14)));
            sendEffectSuccessCombine(player);
            InventoryServiceNew.gI().sendItemBags(player);
            Service.gI().sendMoney(player);
            reOpenItemCombine(player);
        } else {
            Service.gI().sendThongBao(player, "Không đủ nguyên liệu nâng cấp!");
            reOpenItemCombine(player);
        }

        ///
    }

    // chan menh
    public void nangCapChanMenh(Player player) {
        if (player.combineNew.itemsCombine.size() != 2) {
            Service.gI().sendThongBao(player, "Thiếu nguyên liệu");
            return;
        }
        if (player.combineNew.itemsCombine.stream()
                .filter(item -> item.isNotNullItem() && (item.template.id >= 1252 && item.template.id <= 1259))
                .count() != 1) {
            Service.gI().sendThongBao(player, "Chân mệnh");
            return;
        }
        if (player.combineNew.itemsCombine.stream().filter(item -> item.isNotNullItem() && item.template.id == 14)
                .count() != 1) {
            Service.gI().sendThongBao(player, "Thiếu ngọc rồng 1 sao");
            return;
        }
        ///

        Item chanMenh = null;
        Item ngoc1sao = null;

        for (Item item : player.combineNew.itemsCombine) {
            if (item.template.id >= 1252 && item.template.id <= 1259) {
                chanMenh = item;
            } else if (item.template.id == 14) {
                ngoc1sao = item;
            }
        }

        if (chanMenh != null && ngoc1sao != null && ngoc1sao.quantity >= 10 && player.inventory.ruby > 5000) {
            player.inventory.ruby -= 5000;
            player.inventory.gem -= 0;
            Item chanMenhMoi = ItemService.gI().createNewItem((short) (chanMenh.template.id + 1));
            int capChanMenh = chanMenhMoi.template.id - 1252;
            chanMenhMoi.itemOptions.add(new ItemOption(50, 5 + capChanMenh * 3));
            chanMenhMoi.itemOptions.add(new ItemOption(77, 5 + capChanMenh * 3));
            chanMenhMoi.itemOptions.add(new ItemOption(103, 5 + capChanMenh * 3));
            chanMenhMoi.itemOptions.add(new ItemOption(5, 5 + capChanMenh));
            chanMenhMoi.itemOptions.add(new ItemOption(14, 5 + capChanMenh));
            chanMenhMoi.itemOptions.add(new ItemOption(101, 5 + capChanMenh * 3));
            chanMenhMoi.itemOptions.add(new ItemOption(98, 5 + capChanMenh * 3));
            InventoryServiceNew.gI().addItemBag(player, chanMenhMoi);
            Service.gI().sendThongBao(player, "|7|Bạn nhận được " + chanMenhMoi.template.name);
            InventoryServiceNew.gI().subQuantityItemsBag(player, ngoc1sao, 10);
            InventoryServiceNew.gI().subQuantityItemsBag(player, chanMenh, 1);
            sendEffectSuccessCombine(player);
            InventoryServiceNew.gI().sendItemBags(player);
            Service.gI().sendMoney(player);
            reOpenItemCombine(player);
        } else {
            Service.gI().sendThongBao(player, "Không đủ nguyên liệu nâng cấp!");
            reOpenItemCombine(player);
        }

        ///
    }

    // gay thien su
    public void nangCapGayThienSu(Player player) {
        if (player.combineNew.itemsCombine.size() != 2) {
            Service.gI().sendThongBao(player, "Thiếu nguyên liệu");
            return;
        }
        if (player.combineNew.itemsCombine.stream().filter(item -> item.isNotNullItem() && item.template.id == 1213)
                .count() != 1) {
            Service.gI().sendThongBao(player, "Thiếu gậy thiên sứ");
            return;
        }
        if (player.combineNew.itemsCombine.stream().filter(item -> item.isNotNullItem() && item.template.id == 1078)
                .count() != 1) {
            Service.gI().sendThongBao(player, "Thiếu đá thiên sứ");
            return;
        }
        ///

        Item gayThienSu = null;
        Item daNangCap5 = null;

        for (Item item : player.combineNew.itemsCombine) {
            if (item.template.id == 1213) {
                gayThienSu = item;
            } else if (item.template.id == 1078) {
                daNangCap5 = item;
            }
        }

        for (Item.ItemOption io : gayThienSu.itemOptions) {
            if (io.optionTemplate.id == 229) {
                Service.gI().sendThongBao(player, "Trang bị đã kích hoạt thần sứ ");
                return;
            }
        }

        if (gayThienSu != null && daNangCap5 != null && daNangCap5.quantity >= 20 && player.inventory.ruby > 50000) {
            player.inventory.ruby -= 50000;
            player.inventory.gem -= 0;
            InventoryServiceNew.gI().subQuantityItemsBag(player, daNangCap5, 20);
            gayThienSu.itemOptions.add(new ItemOption(21, 150));
            gayThienSu.itemOptions.add(new ItemOption(229, 0));
            gayThienSu.itemOptions.add(new ItemOption(0, 4000));
            gayThienSu.itemOptions.add(new ItemOption(5, 20));
            gayThienSu.itemOptions.add(new ItemOption(14, 10));
            gayThienSu.itemOptions.add(new ItemOption(50, 25));
            gayThienSu.itemOptions.add(new ItemOption(77, 25));
            gayThienSu.itemOptions.add(new ItemOption(103, 25));
            gayThienSu.itemOptions.add(new ItemOption(22, 25));
            gayThienSu.itemOptions.add(new ItemOption(23, 25));
            gayThienSu.itemOptions.add(new ItemOption(108, 25));

        }
        sendEffectSuccessCombine(player);
        InventoryServiceNew.gI().sendItemBags(player);
        Service.gI().sendMoney(player);
        reOpenItemCombine(player);
        ///
    }

    // than su
    public void openSKHThanSu(Player player) {
        if (player.combineNew.itemsCombine.size() != 4) {
            Service.gI().sendThongBao(player, "Thiếu nguyên liệu");
            return;
        }
        if (player.combineNew.itemsCombine.stream().filter(item -> item.isNotNullItem() && item.isDTS()).count() != 1) {
            Service.gI().sendThongBao(player, "Thiếu đồ Thiên sứ");
            return;
        }
        if (player.combineNew.itemsCombine.stream().filter(item -> item.isNotNullItem() && item.isDHD()).count() != 1) {
            Service.gI().sendThongBao(player, "Thiếu đồ Hủy diệt");
            return;
        }
        if (player.combineNew.itemsCombine.stream().filter(item -> item.isNotNullItem() && (item.template.id == 674))
                .count() < 1) {
            Service.gI().sendThongBao(player, "Thiếu đá ngũ sắc!");
            return;
        }
        if (player.combineNew.itemsCombine.stream().filter(item -> item.isNotNullItem() && item.isSKH() && item.isDTL())
                .count() != 1) {
            Service.gI().sendThongBao(player, "Thiếu đồ thần linh kích hoạt. ");
            return;
        }
        //
        Item doThienSu = null;
        Item doHuyDiet = null;
        Item doKH = null;
        Item daNS = null;
        for (Item item : player.combineNew.itemsCombine) {
            if (item.isDTS()) {
                doThienSu = item;
            } else if (item.isDHD()) {
                doHuyDiet = item;
            } else if (item.isSKH()) {
                doKH = item;
            } else if (item.template.id == 674) {
                daNS = item;
            }
        }
        for (Item.ItemOption io : doThienSu.itemOptions) {
            if (io.optionTemplate.id == 229) {
                Service.gI().sendThongBao(player, "Trang bị đã kích hoạt thần sứ ");
                return;
            }
        }

        for (Item.ItemOption io : doHuyDiet.itemOptions) {
            if (io.optionTemplate.id == 229) {
                Service.gI().sendThongBao(player, "Trang bị đã kích hoạt thần sứ ");
                return;
            }
        }
        if (doThienSu != null && doHuyDiet != null && doKH != null && player.inventory.ruby >= 150000
                && daNS.quantity >= 150) {
            player.inventory.ruby -= 150000;
            player.inventory.gem -= 0;
            InventoryServiceNew.gI().subQuantityItemsBag(player, daNS, 150);
            InventoryServiceNew.gI().subQuantityItemsBag(player, doKH, 1);
            if (Util.isTrue(10, 100)) {
                RewardService.gI().initActivationOption(
                        doThienSu.template.gender < 3 ? doThienSu.template.gender : player.gender,
                        doThienSu.template.type, doThienSu.itemOptions);
                InventoryServiceNew.gI().subQuantityItemsBag(player, doHuyDiet, 1);
                doThienSu.itemOptions.add(new ItemOption(229, 0));
                doThienSu.itemOptions.add(new ItemOption(21, 140));
            } else {
                RewardService.gI().initActivationOption(
                        doHuyDiet.template.gender < 3 ? doHuyDiet.template.gender : player.gender,
                        doHuyDiet.template.type, doHuyDiet.itemOptions);
                InventoryServiceNew.gI().subQuantityItemsBag(player, doThienSu, 1);
                doHuyDiet.itemOptions.add(new ItemOption(229, 0));
                doHuyDiet.itemOptions.add(new ItemOption(21, 140));
            }
            sendEffectSuccessCombine(player);

        }
        InventoryServiceNew.gI().sendItemBags(player);
        Service.gI().sendMoney(player);
        reOpenItemCombine(player);
    }

    //// ma su
    public void openSKHMaSu(Player player) {
        if (player.combineNew.itemsCombine.size() != 4) {
            Service.gI().sendThongBao(player, "Thiếu nguyên liệu");
            return;
        }
        if (player.combineNew.itemsCombine.stream().filter(item -> item.isNotNullItem() && item.isDTL()).count() != 1) {
            Service.gI().sendThongBao(player, "Thiếu đồ Thần linh");
            return;
        }
        if (player.combineNew.itemsCombine.stream().filter(item -> item.isNotNullItem() && item.isDHD()).count() != 1) {
            Service.gI().sendThongBao(player, "Thiếu đồ Hủy diệt");
            return;
        }
        if (player.combineNew.itemsCombine.stream().filter(item -> item.isNotNullItem() && (item.template.id == 674))
                .count() < 1) {
            Service.gI().sendThongBao(player, "Thiếu 2 đá ngũ sắc!");
            return;
        }
        if (player.combineNew.itemsCombine.stream().filter(item -> item.isNotNullItem() && item.isSKH()).count() != 1) {
            Service.gI().sendThongBao(player, "Thiếu đồ kích hoạt. ");
            return;
        }
        //
        Item doThanLinh = null;
        Item doHuyDiet = null;
        Item doKH = null;
        Item daNS = null;
        for (Item item : player.combineNew.itemsCombine) {
            if (item.isDTL()) {
                doThanLinh = item;
            } else if (item.isDHD()) {
                doHuyDiet = item;
            } else if (item.isSKH()) {
                doKH = item;
            } else if (item.template.id == 674) {
                daNS = item;
            }
        }
        for (Item.ItemOption io : doThanLinh.itemOptions) {
            if (io.optionTemplate.id == 228) {
                Service.gI().sendThongBao(player, "Trang bị đã kích hoạt ma sứ");
                return;
            }
        }

        for (Item.ItemOption io : doHuyDiet.itemOptions) {
            if (io.optionTemplate.id == 228) {
                Service.gI().sendThongBao(player, "Trang bị đã kích hoạt ma sứ");
                return;
            }
        }
        if (doThanLinh != null && doHuyDiet != null && doKH != null && player.inventory.ruby >= 60000
                && daNS.quantity >= 60) {
            player.inventory.ruby -= 60000;
            player.inventory.gem -= 0;
            InventoryServiceNew.gI().subQuantityItemsBag(player, daNS, 60);
            InventoryServiceNew.gI().subQuantityItemsBag(player, doKH, 1);
            if (Util.isTrue(90, 100)) {
                RewardService.gI().initActivationOption(
                        doThanLinh.template.gender < 3 ? doThanLinh.template.gender : player.gender,
                        doThanLinh.template.type, doThanLinh.itemOptions);

                doThanLinh.itemOptions.add(new ItemOption(228, 0));
                doThanLinh.itemOptions.add(new ItemOption(21, 100));
                InventoryServiceNew.gI().subQuantityItemsBag(player, doHuyDiet, 1);
            } else {
                RewardService.gI().initActivationOption(
                        doHuyDiet.template.gender < 3 ? doHuyDiet.template.gender : player.gender,
                        doHuyDiet.template.type, doHuyDiet.itemOptions);
                InventoryServiceNew.gI().subQuantityItemsBag(player, doThanLinh, 1);
                doHuyDiet.itemOptions.add(new ItemOption(229, 0));
                doHuyDiet.itemOptions.add(new ItemOption(21, 100));
            }
            sendEffectSuccessCombine(player);

        }
        InventoryServiceNew.gI().sendItemBags(player);
        Service.gI().sendMoney(player);
        reOpenItemCombine(player);
    }

    ///
    public void openSKHVIP(Player player) {
        // 1 thần linh + 2 món kích hoạt -- món đầu kh làm gốc
        if (player.combineNew.itemsCombine.size() != 4) {
            Service.gI().sendThongBao(player, "Thiếu nguyên liệu");
            return;
        }
        if (player.combineNew.itemsCombine.stream().filter(item -> item.isNotNullItem() && item.isDTL()).count() != 1) {
            Service.gI().sendThongBao(player, "Thiếu đồ thần linh");
            return;
        }
        if (player.combineNew.itemsCombine.stream().filter(item -> item.isNotNullItem() && item.isSKH()).count() != 2) {
            Service.gI().sendThongBao(player, "Thiếu đồ kích hoạt");
            return;
        }
        if (player.combineNew.itemsCombine.stream().filter(item -> item.isNotNullItem() && (item.template.id == 674))
                .count() != 1) {
            Service.gI().sendThongBao(player, "Thiếu đá ngũ sắc");
            return;
        }
        Item dnsVip = null;
        for (Item item : player.combineNew.itemsCombine) {
            if (item.isNotNullItem()) {
                if (item.template.id == 674) {
                    dnsVip = item;
                }
            }
        }
        if (dnsVip == null || dnsVip.quantity < 20) {
            Service.gI().sendThongBao(player, "Không đủ 20 đá ngũ sắc");
            return;
        }
        if (InventoryServiceNew.gI().getCountEmptyBag(player) > 0) {
            if (player.inventory.gold < 1) {
                Service.gI().sendThongBao(player, "Con cần thêm vàng để đổi...");
                return;
            }
            player.inventory.gold -= COST;

            Item itemTS = player.combineNew.itemsCombine.stream().filter(Item::isDTL).findFirst().get();
            List<Item> itemSKH = player.combineNew.itemsCombine.stream()
                    .filter(item -> item.isNotNullItem() && item.isSKH()).collect(Collectors.toList());
            CombineServiceNew.gI().sendEffectOpenItem(player, itemTS.template.iconID, itemTS.template.iconID);
            short itemId;
            if (itemTS.template.gender == 3 || itemTS.template.type == 4) {
                itemId = Manager.radaSKHVip[Util.nextInt(0, 5)];
                if (player.getSession().bdPlayer > 0 && Util.isTrue(1, (int) (100 / player.getSession().bdPlayer))) {
                    itemId = Manager.radaSKHVip[6];
                }
            } else {
                itemId = Manager.doSKHVip[itemTS.template.gender][itemTS.template.type][Util.nextInt(0, 5)];
                if (player.getSession().bdPlayer > 0 && Util.isTrue(1, (int) (100 / player.getSession().bdPlayer))) {
                    itemId = Manager.doSKHVip[itemTS.template.gender][itemTS.template.type][6];
                }
            }
            int skhId;
            if (itemTS.template.type == 4) {
                skhId = ItemService.gI().randomSKHId(player.gender);
            } else {
                skhId = ItemService.gI().randomSKHId(itemTS.template.gender);
            }

            Item item;
            if (new Item(itemId).isDTL()) {
                item = Util.ratiItemTL(itemId);
                item.itemOptions.add(new Item.ItemOption(skhId, 1));
                item.itemOptions.add(new Item.ItemOption(ItemService.gI().optionIdSKH(skhId), 1));
                item.itemOptions.remove(item.itemOptions.stream()
                        .filter(itemOption -> itemOption.optionTemplate.id == 21).findFirst().get());
                item.itemOptions.add(new Item.ItemOption(21, 15));
                item.itemOptions.add(new Item.ItemOption(30, 1));
            } else {
                item = ItemService.gI().itemSKH(itemId, skhId);
            }
            item.itemOptions.add(new ItemOption(21, 40));
            InventoryServiceNew.gI().addItemBag(player, item);
            InventoryServiceNew.gI().subQuantityItemsBag(player, itemTS, 1);
            InventoryServiceNew.gI().subQuantityItemsBag(player, dnsVip, 20);
            itemSKH.forEach(i -> InventoryServiceNew.gI().subQuantityItemsBag(player, i, 1));
            InventoryServiceNew.gI().sendItemBags(player);
            Service.gI().sendMoney(player);
            player.combineNew.itemsCombine.clear();
            reOpenItemCombine(player);
        } else {
            Service.gI().sendThongBao(player, "Bạn phải có ít nhất 1 ô trống hành trang");
        }
    }

    private void dapDoKichHoat(Player player) {
        if (player.combineNew.itemsCombine.size() == 1 || player.combineNew.itemsCombine.size() == 2) {
            Item dhd = null, dtl = null;
            for (Item item : player.combineNew.itemsCombine) {
                if (item.isNotNullItem()) {
                    if (item.template.id >= 650 && item.template.id <= 662) {
                        dhd = item;
                    } else if (item.template.id >= 555 && item.template.id <= 567) {
                        dtl = item;
                    }
                }
            }
            if (dhd != null) {
                if (InventoryServiceNew.gI().getCountEmptyBag(player) > 0 // check chỗ trống hành trang
                        && player.inventory.gold >= COST_DAP_DO_KICH_HOAT) {
                    player.inventory.gold -= COST_DAP_DO_KICH_HOAT;
                    int tiLe = dtl != null ? 100 : 50;
                    if (Util.isTrue(tiLe, 100)) {
                        sendEffectSuccessCombine(player);
                        Item item = ItemService.gI()
                                .createNewItem((short) getTempIdItemC0(dhd.template.gender, dhd.template.type));
                        RewardService.gI().initBaseOptionClothes(item.template.id, item.template.type,
                                item.itemOptions);
                        RewardService.gI().initActivationOption(
                                item.template.gender < 3 ? item.template.gender : player.gender, item.template.type,
                                item.itemOptions);
                        InventoryServiceNew.gI().addItemBag(player, item);
                    } else {
                        sendEffectFailCombine(player);
                    }
                    InventoryServiceNew.gI().subQuantityItemsBag(player, dhd, 1);
                    if (dtl != null) {
                        InventoryServiceNew.gI().subQuantityItemsBag(player, dtl, 1);
                    }
                    InventoryServiceNew.gI().sendItemBags(player);
                    Service.gI().sendMoney(player);
                    reOpenItemCombine(player);
                }
            }
        }
    }

    private void doiVeHuyDiet(Player player) {
        if (player.combineNew.itemsCombine.size() == 1) {
            Item item = player.combineNew.itemsCombine.get(0);
            if (item.isNotNullItem() && item.template.id >= 555 && item.template.id <= 567) {
                if (InventoryServiceNew.gI().getCountEmptyBag(player) > 0
                        && player.inventory.gold >= COST_DOI_VE_DOI_DO_HUY_DIET) {
                    player.inventory.gold -= COST_DOI_VE_DOI_DO_HUY_DIET;
                    Item ticket = ItemService.gI().createNewItem((short) (2001 + item.template.type));
                    ticket.itemOptions.add(new Item.ItemOption(30, 0));
                    InventoryServiceNew.gI().subQuantityItemsBag(player, item, 1);
                    InventoryServiceNew.gI().addItemBag(player, ticket);
                    sendEffectOpenItem(player, item.template.iconID, ticket.template.iconID);

                    InventoryServiceNew.gI().sendItemBags(player);
                    Service.gI().sendMoney(player);
                    reOpenItemCombine(player);
                }
            }
        }
    }

    private void nangCapBongTai(Player player) {
        if (player.combineNew.itemsCombine.size() == 2) {
            int gold = player.combineNew.goldCombine;
            if (player.inventory.gold < gold) {
                Service.gI().sendThongBao(player, "Không đủ vàng để thực hiện");
                return;
            }
            int gem = player.combineNew.gemCombine;
            if (player.inventory.gem < gem) {
                Service.gI().sendThongBao(player, "Không đủ ngọc để thực hiện");
                return;
            }
            Item bongTai = null;
            Item manhVo = null;
            for (Item item : player.combineNew.itemsCombine) {
                if (item.template.id == 454) {
                    bongTai = item;
                } else if (item.template.id == 933) {
                    manhVo = item;
                }
            }
            if (bongTai != null && manhVo != null && manhVo.quantity >= 99) {
                player.inventory.gold -= gold;
                player.inventory.gem -= gem;
                InventoryServiceNew.gI().subQuantityItemsBag(player, manhVo, 99);
                if (Util.isTrue(player.combineNew.ratioCombine, 100)) {
                    bongTai.template = ItemService.gI().getTemplate(921);
                    bongTai.itemOptions.add(new Item.ItemOption(72, 2));
                    sendEffectSuccessCombine(player);
                } else {
                    sendEffectFailCombine(player);
                }
                InventoryServiceNew.gI().sendItemBags(player);
                Service.gI().sendMoney(player);
                reOpenItemCombine(player);
            }
        }
    }

    private void moChiSoBongTai(Player player) {
        if (player.combineNew.itemsCombine.size() == 3) {
            int gold = player.combineNew.goldCombine;
            if (player.inventory.gold < gold) {
                Service.gI().sendThongBao(player, "Không đủ vàng để thực hiện");
                return;
            }
            int gem = player.combineNew.gemCombine;
            if (player.inventory.gem < gem) {
                Service.gI().sendThongBao(player, "Không đủ ngọc để thực hiện");
                return;
            }
            Item bongtai = null;
            Item honbongtai = null;
            Item daxanhlam = null;
            for (Item item : player.combineNew.itemsCombine) {
                if (item.template.id == 921) {
                    bongtai = item;
                } else if (item.template.id == 934) {
                    honbongtai = item;
                } else if (item.template.id == 935) {
                    daxanhlam = item;
                }
            }
            if (bongtai != null && honbongtai != null && honbongtai.quantity >= 99) {
                player.inventory.gold -= gold;
                player.inventory.gem -= gem;
                InventoryServiceNew.gI().subQuantityItemsBag(player, honbongtai, 99);
                InventoryServiceNew.gI().subQuantityItemsBag(player, daxanhlam, 1);
                if (Util.isTrue(player.combineNew.ratioCombine, 100)) {
                    bongtai.itemOptions.clear();
                    bongtai.itemOptions.add(new Item.ItemOption(72, 2));
                    int rdUp = Util.nextInt(0, 7);
                    if (rdUp == 0) {
                        bongtai.itemOptions.add(new Item.ItemOption(50, Util.nextInt(5, 25)));
                    } else if (rdUp == 1) {
                        bongtai.itemOptions.add(new Item.ItemOption(77, Util.nextInt(5, 25)));
                    } else if (rdUp == 2) {
                        bongtai.itemOptions.add(new Item.ItemOption(103, Util.nextInt(5, 25)));
                    } else if (rdUp == 3) {
                        bongtai.itemOptions.add(new Item.ItemOption(108, Util.nextInt(5, 25)));
                    } else if (rdUp == 4) {
                        bongtai.itemOptions.add(new Item.ItemOption(94, Util.nextInt(5, 15)));
                    } else if (rdUp == 5) {
                        bongtai.itemOptions.add(new Item.ItemOption(14, Util.nextInt(5, 15)));
                    } else if (rdUp == 6) {
                        bongtai.itemOptions.add(new Item.ItemOption(80, Util.nextInt(5, 25)));
                    } else if (rdUp == 7) {
                        bongtai.itemOptions.add(new Item.ItemOption(81, Util.nextInt(5, 25)));
                    }
                    sendEffectSuccessCombine(player);
                } else {
                    sendEffectFailCombine(player);
                }
                InventoryServiceNew.gI().sendItemBags(player);
                Service.gI().sendMoney(player);
                reOpenItemCombine(player);
            }
        }
    }

    private void nangCapBongTaicap3(Player player) {
        if (player.combineNew.itemsCombine.size() == 2) {
            int gold = player.combineNew.goldCombine;
            if (player.inventory.gold < gold) {
                Service.gI().sendThongBao(player, "Không đủ vàng để thực hiện");
                return;
            }
            int gem = player.combineNew.gemCombine;
            if (player.inventory.gem < gem) {
                Service.gI().sendThongBao(player, "Không đủ ngọc để thực hiện");
                return;
            }
            Item bongTai = null;
            Item manhVo = null;
            for (Item item : player.combineNew.itemsCombine) {
                if (item.template.id == 921) {
                    bongTai = item;
                } else if (item.template.id == 2076) {
                    manhVo = item;
                }
            }
            if (bongTai != null && manhVo != null && manhVo.quantity >= 999) {
                player.inventory.gold -= gold;
                player.inventory.gem -= gem;
                InventoryServiceNew.gI().subQuantityItemsBag(player, manhVo, 999);
                if (Util.isTrue(player.combineNew.ratioCombine, 100)) {
                    bongTai.template = ItemService.gI().getTemplate(2074);
                    bongTai.itemOptions.add(new Item.ItemOption(72, 2));
                    sendEffectSuccessCombine(player);
                } else {
                    sendEffectFailCombine(player);
                }
                InventoryServiceNew.gI().sendItemBags(player);
                Service.gI().sendMoney(player);
                reOpenItemCombine(player);
            }
        }
    }

    private void moChiSoBongTaicap3(Player player) {
        if (player.combineNew.itemsCombine.size() == 3) {
            int gold = player.combineNew.goldCombine;
            if (player.inventory.gold < gold) {
                Service.gI().sendThongBao(player, "Không đủ vàng để thực hiện");
                return;
            }
            int gem = player.combineNew.gemCombine;
            if (player.inventory.gem < gem) {
                Service.gI().sendThongBao(player, "Không đủ ngọc để thực hiện");
                return;
            }
            Item bongtai = null;
            Item thachPhu = null;
            Item daxanhlam = null;
            for (Item item : player.combineNew.itemsCombine) {
                if (item.template.id == 2074) {
                    bongtai = item;
                } else if (item.template.id == 2036) {
                    thachPhu = item;
                } else if (item.template.id == 935) {
                    daxanhlam = item;
                }
            }
            if (bongtai != null && thachPhu != null && thachPhu.quantity >= 99) {
                player.inventory.gold -= gold;
                player.inventory.gem -= gem;
                InventoryServiceNew.gI().subQuantityItemsBag(player, thachPhu, 99);
                InventoryServiceNew.gI().subQuantityItemsBag(player, daxanhlam, 1);
                if (Util.isTrue(player.combineNew.ratioCombine, 100)) {
                    bongtai.itemOptions.clear();
                    bongtai.itemOptions.add(new Item.ItemOption(72, 2));
                    int rdUp = Util.nextInt(0, 7);
                    if (rdUp == 0) {
                        bongtai.itemOptions.add(new Item.ItemOption(50, Util.nextInt(5, 25)));
                    } else if (rdUp == 1) {
                        bongtai.itemOptions.add(new Item.ItemOption(77, Util.nextInt(5, 25)));
                    } else if (rdUp == 2) {
                        bongtai.itemOptions.add(new Item.ItemOption(103, Util.nextInt(5, 25)));
                    } else if (rdUp == 3) {
                        bongtai.itemOptions.add(new Item.ItemOption(108, Util.nextInt(5, 25)));
                    } else if (rdUp == 4) {
                        bongtai.itemOptions.add(new Item.ItemOption(94, Util.nextInt(5, 15)));
                    } else if (rdUp == 5) {
                        bongtai.itemOptions.add(new Item.ItemOption(14, Util.nextInt(5, 15)));
                    } else if (rdUp == 6) {
                        bongtai.itemOptions.add(new Item.ItemOption(80, Util.nextInt(5, 25)));
                    } else if (rdUp == 7) {
                        bongtai.itemOptions.add(new Item.ItemOption(81, Util.nextInt(5, 25)));
                    }
                    sendEffectSuccessCombine(player);
                } else {
                    sendEffectFailCombine(player);
                }
                InventoryServiceNew.gI().sendItemBags(player);
                Service.gI().sendMoney(player);
                reOpenItemCombine(player);
            }
        }
    }

    private void nangCapBongTaicap4(Player player) {
        if (player.combineNew.itemsCombine.size() == 2) {
            int gold = player.combineNew.goldCombine;
            if (player.inventory.gold < gold) {
                Service.gI().sendThongBao(player, "Không đủ vàng để thực hiện");
                return;
            }
            int gem = player.combineNew.gemCombine;
            if (player.inventory.gem < gem) {
                Service.gI().sendThongBao(player, "Không đủ ngọc để thực hiện");
                return;
            }
            Item bongTai = null;
            Item manhVo = null;
            for (Item item : player.combineNew.itemsCombine) {
                if (item.template.id == 2074) {
                    bongTai = item;
                } else if (item.template.id == 2077) {
                    manhVo = item;
                }
            }
            if (bongTai != null && manhVo != null && manhVo.quantity >= 999) {
                player.inventory.gold -= gold;
                player.inventory.gem -= gem;
                InventoryServiceNew.gI().subQuantityItemsBag(player, manhVo, 999);
                if (Util.isTrue(player.combineNew.ratioCombine, 100)) {
                    bongTai.template = ItemService.gI().getTemplate(2075);
                    bongTai.itemOptions.add(new Item.ItemOption(72, 3));
                    sendEffectSuccessCombine(player);
                } else {
                    sendEffectFailCombine(player);
                }
                InventoryServiceNew.gI().sendItemBags(player);
                Service.gI().sendMoney(player);
                reOpenItemCombine(player);
            }
        }
    }

    private void moChiSoBongTaicap4(Player player) {
        if (player.combineNew.itemsCombine.size() == 3) {
            int gold = player.combineNew.goldCombine;
            if (player.inventory.gold < gold) {
                Service.gI().sendThongBao(player, "Không đủ vàng để thực hiện");
                return;
            }
            int gem = player.combineNew.gemCombine;
            if (player.inventory.gem < gem) {
                Service.gI().sendThongBao(player, "Không đủ ngọc để thực hiện");
                return;
            }
            Item bongtai = null;
            Item thachPhu = null;
            Item daxanhlam = null;
            for (Item item : player.combineNew.itemsCombine) {
                if (item.template.id == 2075) {
                    bongtai = item;
                } else if (item.template.id == 2036) {
                    thachPhu = item;
                } else if (item.template.id == 935) {
                    daxanhlam = item;
                }
            }
            if (bongtai != null && thachPhu != null && thachPhu.quantity >= 99 && daxanhlam != null
                    && daxanhlam.quantity >= 15) {
                player.inventory.gold -= gold;
                player.inventory.gem -= gem;
                InventoryServiceNew.gI().subQuantityItemsBag(player, thachPhu, 99);
                InventoryServiceNew.gI().subQuantityItemsBag(player, daxanhlam, 15);
                if (Util.isTrue(player.combineNew.ratioCombine, 100)) {
                    bongtai.itemOptions.clear();
                    bongtai.itemOptions.add(new Item.ItemOption(72, 2));
                    int rdUp = Util.nextInt(0, 7);
                    if (rdUp == 0) {
                        bongtai.itemOptions.add(new Item.ItemOption(50, Util.nextInt(5, 25)));
                    } else if (rdUp == 1) {
                        bongtai.itemOptions.add(new Item.ItemOption(77, Util.nextInt(5, 25)));
                    } else if (rdUp == 2) {
                        bongtai.itemOptions.add(new Item.ItemOption(103, Util.nextInt(5, 25)));
                    } else if (rdUp == 3) {
                        bongtai.itemOptions.add(new Item.ItemOption(108, Util.nextInt(5, 25)));
                    } else if (rdUp == 4) {
                        bongtai.itemOptions.add(new Item.ItemOption(94, Util.nextInt(5, 15)));
                    } else if (rdUp == 5) {
                        bongtai.itemOptions.add(new Item.ItemOption(14, Util.nextInt(5, 15)));
                    } else if (rdUp == 6) {
                        bongtai.itemOptions.add(new Item.ItemOption(80, Util.nextInt(5, 25)));
                    } else if (rdUp == 7) {
                        bongtai.itemOptions.add(new Item.ItemOption(81, Util.nextInt(5, 25)));
                    }
                    sendEffectSuccessCombine(player);
                } else {
                    sendEffectFailCombine(player);
                }
                InventoryServiceNew.gI().sendItemBags(player);
                Service.gI().sendMoney(player);
                reOpenItemCombine(player);
            }
        }
    }

    private void moChiSolinhthu(Player player) {
        if (player.combineNew.itemsCombine.size() == 3) {
            int gold = player.combineNew.goldCombine;
            if (player.inventory.gold < gold) {
                Service.gI().sendThongBao(player, "Không đủ vàng để thực hiện");
                return;
            }
            int gem = player.combineNew.gemCombine;
            if (player.inventory.gem < gem) {
                Service.gI().sendThongBao(player, "Không đủ ngọc để thực hiện");
                return;
            }
            Item linhthu = null;
            Item damathuat = null;
            Item thucan = null;
            int[] linhThus = new int[] { 2023, 2025, 2022, 2020, 2040, 2038, 2039, 2021, 2041, 2019, 2042, 2024, 2026,
                    1363, 1364, 1365, 1366, 1367, 1368, 1369, 1370, 1371, 1372, 1373, 1374, 1375, 1376, 1377, 1378,
                    1379, 1380, 1381, 1382, 1383, 1384, 1385, 1386 };
            for (Item item : player.combineNew.itemsCombine) {
                if (Util.includes((int) item.template.id, linhThus)) {
                    linhthu = item;
                } else if (item.template.id == 2030) {
                    damathuat = item;
                } else if (item.template.id >= 663 && item.template.id <= 667) {
                    thucan = item;
                }
            }
            if (linhthu != null && damathuat != null && damathuat.quantity >= 99) {
                player.inventory.gold -= gold;
                player.inventory.gem -= gem;
                InventoryServiceNew.gI().subQuantityItemsBag(player, damathuat, 99);
                InventoryServiceNew.gI().subQuantityItemsBag(player, thucan, 1);
                if (Util.isTrue(player.combineNew.ratioCombine, 100)) {
                    linhthu.itemOptions.clear();
                    linhthu.itemOptions.add(new Item.ItemOption(72, 2));

                    linhthu.itemOptions.add(new Item.ItemOption(50, Util.nextInt(5, 25)));

                    linhthu.itemOptions.add(new Item.ItemOption(77, Util.nextInt(5, 25)));

                    linhthu.itemOptions.add(new Item.ItemOption(103, Util.nextInt(5, 25)));

                    linhthu.itemOptions.add(new Item.ItemOption(108, Util.nextInt(5, 25)));

                    linhthu.itemOptions.add(new Item.ItemOption(94, Util.nextInt(5, 15)));

                    linhthu.itemOptions.add(new Item.ItemOption(14, Util.nextInt(5, 15)));

                    linhthu.itemOptions.add(new Item.ItemOption(80, Util.nextInt(5, 25)));

                    linhthu.itemOptions.add(new Item.ItemOption(81, Util.nextInt(5, 25)));

                    sendEffectSuccessCombine(player);
                } else {
                    sendEffectFailCombine(player);
                }
                InventoryServiceNew.gI().sendItemBags(player);
                Service.gI().sendMoney(player);
                reOpenItemCombine(player);
            }
        }
    }

    private void epSaoTrangBi(Player player) {
        if (player.combineNew.itemsCombine.size() == 2) {
            int gem = player.combineNew.gemCombine;
            if (player.inventory.gem < gem) {
                Service.gI().sendThongBao(player, "Không đủ ngọc để thực hiện");
                return;
            }
            Item trangBi = null;
            Item daPhaLe = null;
            for (Item item : player.combineNew.itemsCombine) {
                if (isTrangBiPhaLeHoa(item)) {
                    trangBi = item;
                } else if (isDaPhaLe(item)) {
                    daPhaLe = item;
                }
            }
            int star = 0; // sao pha lê đã ép
            int starEmpty = 0; // lỗ sao pha lê
            if (trangBi != null && daPhaLe != null) {
                Item.ItemOption optionStar = null;
                for (Item.ItemOption io : trangBi.itemOptions) {
                    if (io.optionTemplate.id == 102) {
                        star = io.param;
                        optionStar = io;
                    } else if (io.optionTemplate.id == 107) {
                        starEmpty = io.param;
                    }
                }
                if (star < starEmpty) {
                    player.inventory.gem -= gem;
                    int optionId = getOptionDaPhaLe(daPhaLe);
                    int param = getParamDaPhaLe(daPhaLe);
                    Item.ItemOption option = null;
                    for (Item.ItemOption io : trangBi.itemOptions) {
                        if (io.optionTemplate.id == optionId) {
                            option = io;
                            break;
                        }
                    }
                    if (option != null) {
                        option.param += param;
                    } else {
                        trangBi.itemOptions.add(new Item.ItemOption(optionId, param));
                    }
                    if (optionStar != null) {
                        optionStar.param++;
                    } else {
                        trangBi.itemOptions.add(new Item.ItemOption(102, 1));
                    }

                    InventoryServiceNew.gI().subQuantityItemsBag(player, daPhaLe, 1);
                    sendEffectSuccessCombine(player);
                }
                InventoryServiceNew.gI().sendItemBags(player);
                Service.gI().sendMoney(player);
                reOpenItemCombine(player);
            }
        }
    }

    // private void phaLeHoaTrangBi(Player player) {
    // if (!player.combineNew.itemsCombine.isEmpty()) {
    // int gold = player.combineNew.goldCombine;
    // int gem = player.combineNew.gemCombine;
    // if (player.inventory.gold < gold) {
    // Service.gI().sendThongBao(player, "Không đủ vàng để thực hiện");
    // return;
    // } else if (player.inventory.gem < gem) {
    // Service.gI().sendThongBao(player, "Không đủ ngọc để thực hiện");
    // return;
    // }
    // Item item = player.combineNew.itemsCombine.get(0);
    // if (isTrangBiPhaLeHoa(item)) {
    // int star = 0;
    // Item.ItemOption optionStar = null;
    // for (Item.ItemOption io : item.itemOptions) {
    // if (io.optionTemplate.id == 107) {
    // star = io.param;
    // optionStar = io;
    // break;
    // }
    // }
    // if (star < MAX_STAR_ITEM) {
    // player.inventory.gold -= gold;
    // player.inventory.gem -= gem;
    // byte ratio = (optionStar != null && optionStar.param > 4) ? (byte) 2 : 1;
    // if (Util.isTrue(player.combineNew.ratioCombine, 100 * ratio)) {
    // if (optionStar == null) {
    // item.itemOptions.add(new Item.ItemOption(107, 1));
    // } else {
    // optionStar.param++;
    // }
    // sendEffectSuccessCombine(player);
    // if (optionStar != null && optionStar.param >= 7) {
    // ServerNotify.gI().notify("Chúc mừng " + player.name + " vừa pha lê hóa "
    // + "thành công " + item.template.name + " lên " + optionStar.param + " sao pha
    // lê");
    // }
    // } else {
    // sendEffectFailCombine(player);
    // }
    // }
    // InventoryServiceNew.gI().sendItemBags(player);
    // Service.gI().sendMoney(player);
    // reOpenItemCombine(player);
    // }
    // }
    // }
    private void phaLeHoaTrangBi(Player player) {
        boolean flag = false;
        int solandap = player.combineNew.quantities;
        int TVC = 0;
        while (player.combineNew.quantities > 0 && !player.combineNew.itemsCombine.isEmpty() && !flag) {
            int gold = player.combineNew.goldCombine;
            int gem = player.combineNew.gemCombine;
            if (player.inventory.gold < gold) {
                Item thoivang = null;
                {
                    thoivang = InventoryServiceNew.gI().findItemBag(player, 457);
                }
                if (thoivang == null || thoivang.quantity <= 1) {
                    Service.gI().sendThongBao(player, "Không đủ vàng để thực hiện");
                    break;

                } else if (player.inventory.gold <= 1_000_000_000) {
                    InventoryServiceNew.gI().subQuantityItemsBag(player, thoivang, 2);
                    player.inventory.gold += 1_000_000_000;
                    TVC += 2;
                    Service.gI().sendMoney(player);

                }

            } else if (player.inventory.gem < gem) {
                Service.gI().sendThongBao(player, "Không đủ ngọc để thực hiện");
                break;
            }
            Item item = player.combineNew.itemsCombine.get(0);
            if (isTrangBiPhaLeHoa(item)) {
                int star = 0;
                Item.ItemOption optionStar = null;
                for (Item.ItemOption io : item.itemOptions) {
                    if (io.optionTemplate.id == 107) {
                        star = io.param;
                        optionStar = io;
                        break;
                    }
                }
                if (star < MAX_STAR_ITEM) {
                    player.inventory.gold -= gold;
                    float epint = (float) getRatioPhaLeHoa(star);
                    flag = Util.isTrue(epint, 1000);
                    if (flag) {
                        if (optionStar == null) {
                            item.itemOptions.add(new Item.ItemOption(107, 1));
                        } else {
                            optionStar.param++;
                        }
                        sendEffectSuccessCombine(player);
                        Service.getInstance().sendThongBao(player,
                                "Lên cấp sau " + (solandap - player.combineNew.quantities + 1) + " lần đập");
                        if (optionStar != null && optionStar.param >= 7) {
                            ServerNotify.gI().notify("Chúc mừng " + player.name + " vừa pha lê hóa "
                                    + "thành công " + item.template.name + " lên " + optionStar.param + " sao pha lê");
                        }
                    } else {
                        sendEffectFailCombine(player);
                    }

                }
            }

            player.combineNew.quantities -= 1;
        }
        if (!flag) {
            sendEffectFailCombine(player);
        }
        if (TVC > 0) {
            Service.gI().sendThongBao(player, "Đã bán tổng cộng " + TVC + " Thỏi vàng trong khi nâng cấp!");
        }
        InventoryServiceNew.gI().sendItemBags(player);
        Service.gI().sendMoney(player);
        reOpenItemCombine(player);

    }

    private void nhapNgocRong(Player player) {
        if (InventoryServiceNew.gI().getCountEmptyBag(player) > 0) {
            if (!player.combineNew.itemsCombine.isEmpty()) {
                Item item = player.combineNew.itemsCombine.get(0);
                if (item != null && item.isNotNullItem() && (item.template.id > 14 && item.template.id <= 20)
                        && item.quantity >= 7) {
                    Item nr = ItemService.gI().createNewItem((short) (item.template.id - 1));
                    InventoryServiceNew.gI().addItemBag(player, nr);
                    InventoryServiceNew.gI().subQuantityItemsBag(player, item, 7);
                    InventoryServiceNew.gI().sendItemBags(player);
                    reOpenItemCombine(player);
                    sendEffectCombineDB(player, item.template.iconID);
                }
            }
        }
    }

    private void nangCapVatPham(Player player) {
        if (player.combineNew.itemsCombine.size() >= 2 && player.combineNew.itemsCombine.size() < 4) {
            if (player.combineNew.itemsCombine.stream().filter(item -> item.isNotNullItem() && item.template.type < 5)
                    .count() != 1) {
                return;
            }
            if (player.combineNew.itemsCombine.stream().filter(item -> item.isNotNullItem() && item.template.type == 14)
                    .count() != 1) {
                return;
            }
            if (player.combineNew.itemsCombine.size() == 3 && player.combineNew.itemsCombine.stream()
                    .filter(item -> item.isNotNullItem() && item.template.id == 987).count() != 1) {
                return;// admin
            }
            Item itemDo = null;
            Item itemDNC = null;
            Item itemDBV = null;
            for (int j = 0; j < player.combineNew.itemsCombine.size(); j++) {
                if (player.combineNew.itemsCombine.get(j).isNotNullItem()) {
                    if (player.combineNew.itemsCombine.size() == 3
                            && player.combineNew.itemsCombine.get(j).template.id == 987) {
                        itemDBV = player.combineNew.itemsCombine.get(j);
                        continue;
                    }
                    if (player.combineNew.itemsCombine.get(j).template.type < 5) {
                        itemDo = player.combineNew.itemsCombine.get(j);
                    } else {
                        itemDNC = player.combineNew.itemsCombine.get(j);
                    }
                }
            }
            if (isCoupleItemNangCapCheck(itemDo, itemDNC)) {
                int countDaNangCap = player.combineNew.countDaNangCap;
                int gold = player.combineNew.goldCombine;
                short countDaBaoVe = player.combineNew.countDaBaoVe;
                if (player.inventory.gold < gold) {
                    Service.gI().sendThongBao(player, "Không đủ vàng để thực hiện");
                    return;
                }

                if (itemDNC.quantity < countDaNangCap)
                    return;
                if (player.combineNew.itemsCombine.size() == 3) {
                    if (Objects.isNull(itemDBV))
                        return;
                    if (itemDBV.quantity < countDaBaoVe)
                        return;
                }

                int level = 0;
                Item.ItemOption optionLevel = null;
                for (Item.ItemOption io : itemDo.itemOptions) {
                    if (io.optionTemplate.id == 72) {
                        level = io.param;
                        optionLevel = io;
                        break;
                    }
                }
                if (level < MAX_LEVEL_ITEM) {
                    player.inventory.gold -= gold;
                    Item.ItemOption option = null;
                    Item.ItemOption option2 = null;
                    for (Item.ItemOption io : itemDo.itemOptions) {
                        if (io.optionTemplate.id == 47
                                || io.optionTemplate.id == 6
                                || io.optionTemplate.id == 0
                                || io.optionTemplate.id == 7
                                || io.optionTemplate.id == 14
                                || io.optionTemplate.id == 22
                                || io.optionTemplate.id == 23) {
                            option = io;
                        } else if (io.optionTemplate.id == 27
                                || io.optionTemplate.id == 28) {
                            option2 = io;
                        }
                    }
                    if (Util.isTrue(player.combineNew.ratioCombine, 100)) {
                        option.param += (option.param * 10 / 100);
                        if (option2 != null) {
                            option2.param += (option2.param * 10 / 100);
                        }
                        if (optionLevel == null) {
                            itemDo.itemOptions.add(new Item.ItemOption(72, 1));
                        } else {
                            optionLevel.param++;
                        }
                        // if (optionLevel != null && optionLevel.param >= 5) {
                        // ServerNotify.gI().notify("Chúc mừng " + player.name + " vừa nâng cấp "
                        // + "thành công " + trangBi.template.name + " lên +" + optionLevel.param);
                        // }
                        sendEffectSuccessCombine(player);
                    } else {
                        if ((level == 2 || level == 4 || level == 6) && (player.combineNew.itemsCombine.size() != 3)) {
                            option.param -= (option.param * 10 / 100);
                            if (option2 != null) {
                                option2.param -= (option2.param * 10 / 100);
                            }
                            optionLevel.param--;
                        }
                        sendEffectFailCombine(player);
                    }
                    if (player.combineNew.itemsCombine.size() == 3)
                        InventoryServiceNew.gI().subQuantityItemsBag(player, itemDBV, countDaBaoVe);
                    InventoryServiceNew.gI().subQuantityItemsBag(player, itemDNC, player.combineNew.countDaNangCap);
                    InventoryServiceNew.gI().sendItemBags(player);
                    Service.gI().sendMoney(player);
                    reOpenItemCombine(player);
                }
            }
        }
    }

    private void epantrangbi(Player player) {
        if (player.combineNew.itemsCombine.size() == 4) {
            int gold = 200000;
            if (player.inventory.ruby < gold) {
                Service.gI().sendThongBao(player, "Không đủ Thỏi Vàng 2 để thực hiện");
                return;
            }

            Item cailoz = null;
            Item concac = null;
            Item thoivang = null;
            Item doHDKH = null;
            for (Item item : player.combineNew.itemsCombine) {
                if (item.template.type < 5 && item.isDTS() && item.isSKH()) {
                    cailoz = item;
                } else if (item.template.id == 674) {
                    concac = item;
                } else if (item.template.id == 457) {
                    thoivang = item;
                } else if (item.isDHD() && item.isSKH()) {
                    doHDKH = item;
                }
            }

            for (Item.ItemOption io : cailoz.itemOptions) {
                if (io.optionTemplate.id == 34 || io.optionTemplate.id == 35 || io.optionTemplate.id == 36) {
                    Service.gI().sendThongBao(player, "Trang bị đã kích hoạt thần ấn");
                    return;
                }
            }

            if (cailoz != null && doHDKH != null && concac != null && thoivang.quantity >= 300
                    && concac.quantity >= 300) {
                player.inventory.ruby -= 300000;
                player.inventory.gem -= 0;
                InventoryServiceNew.gI().subQuantityItemsBag(player, concac, 300);
                InventoryServiceNew.gI().subQuantityItemsBag(player, thoivang, 300);
                InventoryServiceNew.gI().subQuantityItemsBag(player, doHDKH, 1);

                if (Util.isTrue(100, 100)) {
                    cailoz.itemOptions.clear();
                    switch (cailoz.template.type) {
                        case 0:
                            cailoz.itemOptions.add(new Item.ItemOption(47, Util.nextInt(3000, 4000)));
                            cailoz.itemOptions.add(new Item.ItemOption(107, Util.nextInt(3, 6)));
                            cailoz.itemOptions.add(new Item.ItemOption(17, Util.nextInt(10, 15)));
                            cailoz.itemOptions.add(new Item.ItemOption(21, 160));
                            cailoz.itemOptions.add(new Item.ItemOption(30, 0));
                            break;
                        case 1:
                            cailoz.itemOptions.add(new Item.ItemOption(22, Util.nextInt(200, 250)));
                            cailoz.itemOptions.add(new Item.ItemOption(107, Util.nextInt(3, 6)));
                            cailoz.itemOptions.add(new Item.ItemOption(80, Util.nextInt(30, 40)));
                            cailoz.itemOptions.add(new Item.ItemOption(21, 160));
                            cailoz.itemOptions.add(new Item.ItemOption(30, 0));
                            break;
                        case 2:
                            cailoz.itemOptions.add(new Item.ItemOption(0, Util.nextInt(20000, 25000)));
                            cailoz.itemOptions.add(new Item.ItemOption(107, Util.nextInt(3, 6)));
                            cailoz.itemOptions.add(new Item.ItemOption(5, Util.nextInt(10, 15)));
                            cailoz.itemOptions.add(new Item.ItemOption(21, 160));
                            cailoz.itemOptions.add(new Item.ItemOption(30, 0));
                            break;
                        case 3:
                            cailoz.itemOptions.add(new Item.ItemOption(23, Util.nextInt(200, 250)));
                            cailoz.itemOptions.add(new Item.ItemOption(107, Util.nextInt(3, 6)));
                            cailoz.itemOptions.add(new Item.ItemOption(81, Util.nextInt(30, 40)));
                            cailoz.itemOptions.add(new Item.ItemOption(21, 160));
                            cailoz.itemOptions.add(new Item.ItemOption(30, 0));
                            break;
                        case 4:
                            cailoz.itemOptions.add(new Item.ItemOption(14, Util.nextInt(25, 30)));
                            cailoz.itemOptions.add(new Item.ItemOption(107, Util.nextInt(3, 6)));
                            cailoz.itemOptions.add(new Item.ItemOption(213, 0));
                            cailoz.itemOptions.add(new Item.ItemOption(21, 160));
                            cailoz.itemOptions.add(new Item.ItemOption(30, 0));
                            break;
                    }

                    int rdUp = Util.nextInt(0, 2);
                    if (rdUp == 0) {
                        cailoz.itemOptions.add(new Item.ItemOption(34, 1));
                    } else if (rdUp == 1) {
                        cailoz.itemOptions.add(new Item.ItemOption(35, 1));
                    } else if (rdUp == 2) {
                        cailoz.itemOptions.add(new Item.ItemOption(36, 1));
                    }
                    sendEffectSuccessCombine(player);

                } else {
                    sendEffectFailCombine(player);
                }
                InventoryServiceNew.gI().sendItemBags(player);
                Service.gI().sendMoney(player);
                reOpenItemCombine(player);
            }
        }
    }

    //
    private void trungthienthan(Player player) {
        if (player.combineNew.itemsCombine.size() == 3) {

            Item quatrung = null;
            Item honlinhthu = null;
            Item thoivang = null;

            for (Item item : player.combineNew.itemsCombine) {
                if (item.template.id == 1199) {
                    quatrung = item;
                } else if (item.template.id == 2029) {
                    honlinhthu = item;
                } else if (item.template.id == 457) {
                    thoivang = item;
                }
            }

            if (quatrung != null && honlinhthu != null && thoivang.quantity >= 20 && honlinhthu.quantity >= 99) {
                InventoryServiceNew.gI().subQuantityItemsBag(player, quatrung, 1);
                InventoryServiceNew.gI().subQuantityItemsBag(player, honlinhthu, 99);
                InventoryServiceNew.gI().subQuantityItemsBag(player, thoivang, 20);
                if (player.thienThanEgg == null) {
                    ThienThanEgg.createThienThanEgg(player);
                }
                ChangeMapService.gI().changeMapInYard(player,
                        player.zone.map.mapId, player.zone.zoneId, player.location.x);
            } else {
                sendEffectFailCombine(player);
            }
            InventoryServiceNew.gI().sendItemBags(player);
            Service.gI().sendMoney(player);
            reOpenItemCombine(player);
        }
    }

    private void trunghacam(Player player) {
        if (player.combineNew.itemsCombine.size() == 3) {

            Item quatrung = null;
            Item honlinhthu = null;
            Item thoivang = null;

            for (Item item : player.combineNew.itemsCombine) {
                if (item.template.id == 1200) {
                    quatrung = item;
                } else if (item.template.id == 2029) {
                    honlinhthu = item;
                } else if (item.template.id == 457) {
                    thoivang = item;
                }
            }

            if (quatrung != null && honlinhthu != null && thoivang.quantity >= 20 && honlinhthu.quantity >= 99) {
                InventoryServiceNew.gI().subQuantityItemsBag(player, quatrung, 1);
                InventoryServiceNew.gI().subQuantityItemsBag(player, honlinhthu, 99);
                InventoryServiceNew.gI().subQuantityItemsBag(player, thoivang, 20);
                if (player.hacAmEgg == null) {
                    HacAmEgg.createHacAmEgg(player);
                }
                ChangeMapService.gI().changeMapInYard(player,
                        player.zone.map.mapId, player.zone.zoneId, player.location.x);
            } else {
                sendEffectFailCombine(player);
            }
            InventoryServiceNew.gI().sendItemBags(player);
            Service.gI().sendMoney(player);
            reOpenItemCombine(player);
        }
    }

    private void nguyetan(Player player) {
        if (player.combineNew.itemsCombine.size() >= 2 && player.combineNew.itemsCombine.size() < 4) {
            if (player.combineNew.itemsCombine.stream().filter(item -> item.isNotNullItem() && item.template.type < 5)
                    .count() != 1) {
                return;
            }
            if (player.combineNew.itemsCombine.stream().filter(item -> item.isNotNullItem() && item.template.type == 14)
                    .count() != 1) {
                return;
            }
            if (player.combineNew.itemsCombine.size() == 3 && player.combineNew.itemsCombine.stream()
                    .filter(item -> item.isNotNullItem() && item.template.id == 987).count() != 1) {
                return;// admin
            }
            Item itemDo = null;
            Item itemDNC = null;
            Item itemDBV = null;
            for (int j = 0; j < player.combineNew.itemsCombine.size(); j++) {
                if (player.combineNew.itemsCombine.get(j).isNotNullItem()) {
                    if (player.combineNew.itemsCombine.size() == 3
                            && player.combineNew.itemsCombine.get(j).template.id == 987) {
                        itemDBV = player.combineNew.itemsCombine.get(j);
                        continue;
                    }
                    if (player.combineNew.itemsCombine.get(j).template.type < 5) {
                        itemDo = player.combineNew.itemsCombine.get(j);
                    } else {
                        itemDNC = player.combineNew.itemsCombine.get(j);
                    }
                }
            }
            if (isCoupleItemNangCapCheck(itemDo, itemDNC)) {
                int countDaNangCap = player.combineNew.countDaNangCap;
                int gold = 25000;
                short countDaBaoVe = player.combineNew.countDaBaoVe;
                if (player.inventory.ruby < gold) {
                    Service.gI().sendThongBao(player, "Không đủ ngọc để thực hiện");
                    return;
                }

                if (itemDNC.quantity < countDaNangCap)
                    return;
                if (player.combineNew.itemsCombine.size() == 3) {
                    if (Objects.isNull(itemDBV))
                        return;
                    if (itemDBV.quantity < countDaBaoVe)
                        return;
                }

                int level = 0;
                Item.ItemOption optionLevel = null;
                for (Item.ItemOption io : itemDo.itemOptions) {
                    if (io.optionTemplate.id == 35) {
                        // player.inventory.ruby -= gold;
                        level = io.param;
                        optionLevel = io;
                        break;
                    }

                }
                if (level <= 2) {

                    Item.ItemOption option = null;
                    Item.ItemOption option2 = null;
                    for (Item.ItemOption io : itemDo.itemOptions) {
                        if (io.optionTemplate.id == 47
                                || io.optionTemplate.id == 6
                                || io.optionTemplate.id == 0
                                || io.optionTemplate.id == 7
                                || io.optionTemplate.id == 14
                                || io.optionTemplate.id == 22
                                || io.optionTemplate.id == 23) {
                            option = io;
                        } else if (io.optionTemplate.id == 27
                                || io.optionTemplate.id == 28) {
                            option2 = io;
                        }
                    }
                    if (Util.isTrue(player.combineNew.ratioCombine, 100)) {
                        option.param += (option.param * 10 / 100);
                        if (option2 != null) {
                            option2.param += (option2.param * 10 / 100);
                        }
                        if (optionLevel == null) {
                            itemDo.itemOptions.add(new Item.ItemOption(Util.nextInt(35, 35), 1));
                        } else {
                            optionLevel.param++;
                        }
                        // if (optionLevel != null && optionLevel.param >= 5) {
                        // ServerNotify.gI().notify("Chúc mừng " + player.name + " vừa nâng cấp "
                        // + "thành công " + trangBi.template.name + " lên +" + optionLevel.param);
                        // }
                        sendEffectSuccessCombine(player);
                    } else {
                        if ((level == 2 || level == 4 || level == 6) && (player.combineNew.itemsCombine.size() != 3)) {
                            option.param -= (option.param * 10 / 100);
                            if (option2 != null) {
                                option2.param -= (option2.param * 10 / 100);
                            }
                            optionLevel.param--;
                        }
                        sendEffectFailCombine(player);
                    }
                    if (player.combineNew.itemsCombine.size() == 3)
                        InventoryServiceNew.gI().subQuantityItemsBag(player, itemDBV, countDaBaoVe);
                    player.inventory.ruby -= gold;
                    InventoryServiceNew.gI().subQuantityItemsBag(player, itemDNC, player.combineNew.countDaNangCap);
                    InventoryServiceNew.gI().sendItemBags(player);
                    Service.gI().sendMoney(player);
                    reOpenItemCombine(player);
                }
            }
        }
    }

    private void nhatan(Player player) {
        if (player.combineNew.itemsCombine.size() >= 2 && player.combineNew.itemsCombine.size() < 4) {
            if (player.combineNew.itemsCombine.stream().filter(item -> item.isNotNullItem() && item.template.type < 5)
                    .count() != 1) {
                return;
            }
            if (player.combineNew.itemsCombine.stream().filter(item -> item.isNotNullItem() && item.template.type == 14)
                    .count() != 1) {
                return;
            }
            if (player.combineNew.itemsCombine.size() == 3 && player.combineNew.itemsCombine.stream()
                    .filter(item -> item.isNotNullItem() && item.template.id == 987).count() != 1) {
                return;// admin
            }
            Item itemDo = null;
            Item itemDNC = null;
            Item itemDBV = null;
            for (int j = 0; j < player.combineNew.itemsCombine.size(); j++) {
                if (player.combineNew.itemsCombine.get(j).isNotNullItem()) {
                    if (player.combineNew.itemsCombine.size() == 3
                            && player.combineNew.itemsCombine.get(j).template.id == 987) {
                        itemDBV = player.combineNew.itemsCombine.get(j);
                        continue;
                    }
                    if (player.combineNew.itemsCombine.get(j).template.type < 5) {
                        itemDo = player.combineNew.itemsCombine.get(j);
                    } else {
                        itemDNC = player.combineNew.itemsCombine.get(j);
                    }
                }
            }
            if (isCoupleItemNangCapCheck(itemDo, itemDNC)) {
                int countDaNangCap = player.combineNew.countDaNangCap;
                int gold = 25000;
                short countDaBaoVe = player.combineNew.countDaBaoVe;
                if (player.inventory.ruby < gold) {
                    Service.gI().sendThongBao(player, "Không đủ ngọc để thực hiện");
                    return;
                }

                if (itemDNC.quantity < countDaNangCap)
                    return;
                if (player.combineNew.itemsCombine.size() == 3) {
                    if (Objects.isNull(itemDBV))
                        return;
                    if (itemDBV.quantity < countDaBaoVe)
                        return;
                }

                int level = 0;
                Item.ItemOption optionLevel = null;
                for (Item.ItemOption io : itemDo.itemOptions) {
                    if (io.optionTemplate.id == 36) {
                        // player.inventory.ruby -= gold;
                        level = io.param;
                        optionLevel = io;
                        break;
                    }

                }
                if (level <= 2) {

                    Item.ItemOption option = null;
                    Item.ItemOption option2 = null;
                    for (Item.ItemOption io : itemDo.itemOptions) {
                        if (io.optionTemplate.id == 47
                                || io.optionTemplate.id == 6
                                || io.optionTemplate.id == 0
                                || io.optionTemplate.id == 7
                                || io.optionTemplate.id == 14
                                || io.optionTemplate.id == 22
                                || io.optionTemplate.id == 23) {
                            option = io;
                        } else if (io.optionTemplate.id == 27
                                || io.optionTemplate.id == 28) {
                            option2 = io;
                        }
                    }
                    if (Util.isTrue(player.combineNew.ratioCombine, 100)) {
                        option.param += (option.param * 10 / 100);
                        if (option2 != null) {
                            option2.param += (option2.param * 10 / 100);
                        }
                        if (optionLevel == null) {
                            itemDo.itemOptions.add(new Item.ItemOption(Util.nextInt(36, 36), 1));
                        } else {
                            optionLevel.param++;
                        }
                        // if (optionLevel != null && optionLevel.param >= 5) {
                        // ServerNotify.gI().notify("Chúc mừng " + player.name + " vừa nâng cấp "
                        // + "thành công " + trangBi.template.name + " lên +" + optionLevel.param);
                        // }
                        sendEffectSuccessCombine(player);
                    } else {
                        if ((level == 2 || level == 4 || level == 6) && (player.combineNew.itemsCombine.size() != 3)) {
                            option.param -= (option.param * 10 / 100);
                            if (option2 != null) {
                                option2.param -= (option2.param * 10 / 100);
                            }
                            optionLevel.param--;
                        }
                        sendEffectFailCombine(player);
                    }
                    if (player.combineNew.itemsCombine.size() == 3)
                        InventoryServiceNew.gI().subQuantityItemsBag(player, itemDBV, countDaBaoVe);
                    player.inventory.ruby -= gold;
                    InventoryServiceNew.gI().subQuantityItemsBag(player, itemDNC, player.combineNew.countDaNangCap);
                    InventoryServiceNew.gI().sendItemBags(player);
                    Service.gI().sendMoney(player);
                    reOpenItemCombine(player);
                }
            }
        }
    }

    // --------------------------------------------------------------------------

    /**
     * r
     * Hiệu ứng mở item
     *
     * @param player
     */
    public void sendEffectOpenItem(Player player, short icon1, short icon2) {
        Message msg;
        try {
            msg = new Message(-81);
            msg.writer().writeByte(OPEN_ITEM);
            msg.writer().writeShort(icon1);
            msg.writer().writeShort(icon2);
            player.sendMessage(msg);
            msg.cleanup();
        } catch (Exception e) {
        }
    }

    /**
     * Hiệu ứng đập đồ thành công
     *
     * @param player
     */
    private void sendEffectSuccessCombine(Player player) {
        Message msg;
        try {
            msg = new Message(-81);
            msg.writer().writeByte(COMBINE_SUCCESS);
            player.sendMessage(msg);
            msg.cleanup();
        } catch (Exception e) {
        }
    }

    /**
     * Hiệu ứng đập đồ thất bại
     *
     * @param player
     */
    private void sendEffectFailCombine(Player player) {
        Message msg;
        try {
            msg = new Message(-81);
            msg.writer().writeByte(COMBINE_FAIL);
            player.sendMessage(msg);
            msg.cleanup();
        } catch (Exception e) {
        }
    }

    /**
     * Gửi lại danh sách đồ trong tab combine
     *
     * @param player
     */
    private void reOpenItemCombine(Player player) {
        Message msg;
        try {
            msg = new Message(-81);
            msg.writer().writeByte(REOPEN_TAB_COMBINE);
            msg.writer().writeByte(player.combineNew.itemsCombine.size());
            for (Item it : player.combineNew.itemsCombine) {
                for (int j = 0; j < player.inventory.itemsBag.size(); j++) {
                    if (it == player.inventory.itemsBag.get(j)) {
                        msg.writer().writeByte(j);
                    }
                }
            }
            player.sendMessage(msg);
            msg.cleanup();
        } catch (Exception e) {
        }
    }

    /**
     * Hiệu ứng ghép ngọc rồng
     *
     * @param player
     * @param icon
     */
    private void sendEffectCombineDB(Player player, short icon) {
        Message msg;
        try {
            msg = new Message(-81);
            msg.writer().writeByte(COMBINE_DRAGON_BALL);
            msg.writer().writeShort(icon);
            player.sendMessage(msg);
            msg.cleanup();
        } catch (Exception e) {
        }
    }

    // --------------------------------------------------------------------------Ratio,
    // cost combine
    private int getGoldPhaLeHoa(int star) {
        switch (star) {
            case 0:
                return 5000000;
            case 1:
                return 20000000;
            case 2:
                return 35000000;
            case 3:
                return 50000000;
            case 4:
                return 75000000;
            case 5:
                return 90000000;
            case 6:
                return 115000000;
            case 7:
                return 150000000;
            case 8:
                return 200000000;
            case 9:
                return 300000000;
            case 10:
                return 450000000;
            case 11:
                return 500000000;
            case 12:
                return 1000000000;
        }
        return 0;
    }

    private float getRatioPhaLeHoa(int star) { // tile dap do chi hat mit
        switch (star) {
            case 0:
                return 50f;
            case 1:
                return 30f;
            case 2:
                return 15f;
            case 3:
                return 10f;
            case 4:
                return 5f;
            case 5:
                return 2f;
            case 6:
                return 1f;
            case 7:
                return 0.5f;
            case 8:
                return 0.1f;
            case 9:
                return 0.05f;
            case 10:
                return 0.02f;
            case 11:
                return 0.01f;
            case 12:
                return 0.005f;
        }

        return 0;
    }

    private int getGemPhaLeHoa(int star) {
        switch (star) {
            case 0:
                return 10;
            case 1:
                return 20;
            case 2:
                return 30;
            case 3:
                return 40;
            case 4:
                return 50;
            case 5:
                return 60;
            case 6:
                return 70;
            case 7:
                return 80;
            case 8:
                return 50;
            case 9:
                return 50;
            case 10:
                return 50;
            case 11:
                return 50;
            case 12:
                return 50;
        }
        return 0;
    }

    private int getGemEpSao(int star) {
        switch (star) {
            case 0:
                return 1;
            case 1:
                return 2;
            case 2:
                return 5;
            case 3:
                return 10;
            case 4:
                return 25;
            case 5:
                return 50;
            case 6:
                return 100;
        }
        return 0;
    }

    private double getTileNangCapDo(int level) {
        switch (level) {
            case 0:
                return 50f;
            case 1:
                return 40f;
            case 2:
                return 30f;
            case 3:
                return 20f;
            case 4:
                return 10f;
            case 5:
                return 5f;
            case 6:
                return 2f;
            case 7:
                return 1f;
            case 8:
                return 0.5f;
            case 9:
                return 0.1f;
            case 10:
                return 0.05f;
            case 11:
                return 0.01f;
            case 12:
                return 0.005f;
        }
        return 0;
    }

    private int getCountDaNangCapDo(int level) {
        switch (level) {
            case 0:
                return 2;
            case 1:
                return 6;
            case 2:
                return 10;
            case 3:
                return 16;
            case 4:
                return 25;
            case 5:
                return 30;
            case 6:
                return 45;
            case 7:
                return 60;
        }
        return 0;
    }

    private int getCountDaBaoVe(int level) {
        return level + 1;
    }

    private int getGoldNangCapDo(int level) {
        switch (level) {
            case 0:
                return 10000;
            case 1:
                return 70000;
            case 2:
                return 300000;
            case 3:
                return 1500000;
            case 4:
                return 7000000;
            case 5:
                return 23000000;
            case 6:
                return 100000000;
            case 7:
                return 250000000;
        }
        return 0;
    }

    // --------------------------------------------------------------------------check
    private boolean isCoupleItemNangCap(Item item1, Item item2) {
        Item trangBi = null;
        Item daNangCap = null;
        if (item1 != null && item1.isNotNullItem()) {
            if (item1.template.type < 5) {
                trangBi = item1;
            } else if (item1.template.type == 14) {
                daNangCap = item1;
            }
        }
        if (item2 != null && item2.isNotNullItem()) {
            if (item2.template.type < 5) {
                trangBi = item2;
            } else if (item2.template.type == 14) {
                daNangCap = item2;
            }
        }
        if (trangBi != null && daNangCap != null) {
            if (trangBi.template.type == 0 && daNangCap.template.id == 223) {
                return true;
            } else if (trangBi.template.type == 1 && daNangCap.template.id == 222) {
                return true;
            } else if (trangBi.template.type == 2 && daNangCap.template.id == 224) {
                return true;
            } else if (trangBi.template.type == 3 && daNangCap.template.id == 221) {
                return true;
            } else if (trangBi.template.type == 4 && daNangCap.template.id == 220) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    private boolean isCoupleItemNangCapCheck(Item trangBi, Item daNangCap) {
        if (trangBi != null && daNangCap != null) {
            if (trangBi.template.type == 0 && daNangCap.template.id == 223) {
                return true;
            } else if (trangBi.template.type == 1 && daNangCap.template.id == 222) {
                return true;
            } else if (trangBi.template.type == 2 && daNangCap.template.id == 224) {
                return true;
            } else if (trangBi.template.type == 3 && daNangCap.template.id == 221) {
                return true;
            } else if (trangBi.template.type == 4 && daNangCap.template.id == 220) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    private boolean isDaPhaLe(Item item) {
        return item != null && (item.template.type == 30 || (item.template.id >= 14 && item.template.id <= 20));
    }
    private boolean checkHaveOption(Item item, int viTriOption, int idOption) {
        if (item != null && item.isNotNullItem()) {
            if (item.itemOptions.get(viTriOption).optionTemplate.id == idOption) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
    private boolean issachTuyetKy(Item item) {
        if (item != null && item.isNotNullItem()) {
            if (item.template.type == 77) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    private boolean isTrangBiPhaLeHoa(Item item) {
        if (item != null && item.isNotNullItem()) {
            if (item.template.type <= 5 || item.template.type == 32) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    private int getParamDaPhaLe(Item daPhaLe) {
        if (daPhaLe.template.type == 30) {
            return daPhaLe.itemOptions.get(0).param;
        }
        switch (daPhaLe.template.id) {
            case 20:
                return 5; // +5%hp
            case 19:
                return 5; // +5%ki
            case 18:
                return 5; // +5%hp/30s
            case 17:
                return 5; // +5%ki/30s
            case 16:
                return 3; // +3%sđ
            case 15:
                return 2; // +2%giáp
            case 14:
                return 1; // +5%né đòn
            case 2015:
                return 5; // +5%sd
            default:
                return -1;
        }
    }

    private int getOptionDaPhaLe(Item daPhaLe) {
        if (daPhaLe.template.type == 30) {
            return daPhaLe.itemOptions.get(0).optionTemplate.id;
        }
        switch (daPhaLe.template.id) {
            case 20:
                return 77;
            case 19:
                return 103;
            case 18:
                return 80;
            case 17:
                return 81;
            case 16:
                return 50;
            case 15:
                return 94;
            case 14:
                return 108;
            default:
                return -1;
        }
    }

    /**
     * Trả về id item c0
     *
     * @param gender
     * @param type
     * @return
     */
    private int getTempIdItemC0(int gender, int type) {
        if (type == 4) {
            return 12;
        }
        switch (gender) {
            case 0:
                switch (type) {
                    case 0:
                        return 0;
                    case 1:
                        return 6;
                    case 2:
                        return 21;
                    case 3:
                        return 27;
                }
                break;
            case 1:
                switch (type) {
                    case 0:
                        return 1;
                    case 1:
                        return 7;
                    case 2:
                        return 22;
                    case 3:
                        return 28;
                }
                break;
            case 2:
                switch (type) {
                    case 0:
                        return 2;
                    case 1:
                        return 8;
                    case 2:
                        return 23;
                    case 3:
                        return 29;
                }
                break;
        }
        return -1;
    }

    // Trả về tên đồ c0
    private String getNameItemC0(int gender, int type) {
        if (type == 4) {
            return "Rada cấp 1";
        }
        switch (gender) {
            case 0:
                switch (type) {
                    case 0:
                        return "Áo vải 3 lỗ";
                    case 1:
                        return "Quần vải đen";
                    case 2:
                        return "Găng thun đen";
                    case 3:
                        return "Giầy nhựa";
                }
                break;
            case 1:
                switch (type) {
                    case 0:
                        return "Áo sợi len";
                    case 1:
                        return "Quần sợi len";
                    case 2:
                        return "Găng sợi len";
                    case 3:
                        return "Giầy sợi len";
                }
                break;
            case 2:
                switch (type) {
                    case 0:
                        return "Áo vải thô";
                    case 1:
                        return "Quần vải thô";
                    case 2:
                        return "Găng vải thô";
                    case 3:
                        return "Giầy vải thô";
                }
                break;
        }
        return "";
    }

    // --------------------------------------------------------------------------Text
    // tab combine
    private String getTextTopTabCombine(int type) {
        switch (type) {
            case NANG_CAP_CAN_CAU:
                return "Anh muốn nâng cấp cần câu?";
            case SUA_CHUA_CAN_CAU:
                return "Anh muốn sửa chữa cần câu?";
            case TINH_LUYEN_CAI_TRANG:
                return "Cậu muốn tinh luyện cải trang?";
            case TAY_TINH_LUYEN_CAI_TRANG:
                return "Cậu muốn xóa hết các chỉ số tinh luyện cải trang?";
            case MO_CHI_SO_CAI_TRANG:
                return "Anh có muốn mở chỉ số cải trang?";
            case NANG_CAP_CHAN_MENH:
                return "Cậu có muốn khai mở chân mệnh? \n hãy để ta khai mở sức mạnh chân mệnh của cậu\n trở thành chân mệnh cấp cao hơn";
            case NANG_CAP_GAY_THIEN_SU:
                return "Cậu có gậy thiên sứ hả? \n hãy để ta khai mở sức mạnh gậy của cậu\n trở thành quyền trượng thiên sứ. Tới địa ngục để đập đá nhé";
            case OPEN_SKH_THAN_SU:
                return "Thiên sứ nhờ ta nâng cấp \n  trang bị của người thành\n SKH Thần sứ!";
            case OPEN_SKH_MA_SU:
                return "Thần hủy diệt nhờ ta nâng cấp \n  trang bị của người thành\n SKH Ma sứ!";
            case EP_SAO_TRANG_BI:
                return "Ta sẽ phù phép\ncho trang bị của ngươi\ntrở lên mạnh mẽ";
            case PHA_LE_HOA_TRANG_BI:
                return "Ta sẽ phù phép\ncho trang bị của ngươi\ntrở thành trang bị pha lê";
            case NHAP_NGOC_RONG:
                return "Ta sẽ phù phép\ncho 7 viên Ngọc Rồng\nthành 1 viên Ngọc Rồng cấp cao";
            case NANG_CAP_VAT_PHAM:
                return "Ta sẽ phù phép cho trang bị của ngươi trở lên mạnh mẽ";
            case PHAN_RA_DO_THAN_LINH:
                return "Ta sẽ phân rã \n  ngọc của người thành điểm!";
            case NANG_CAP_DO_TS:
                return "Ta sẽ nâng cấp \n  trang bị của người thành\n đồ thiên sứ!";
            case NANG_CAP_SKH_VIP:
                return "Thiên sứ nhờ ta nâng cấp \n  trang bị của người thành\n SKH VIP!";
            case NANG_CAP_BONG_TAI:
                return "Ta sẽ phù phép\ncho bông tai Porata của ngươi\nthành cấp 2";
            case MO_CHI_SO_BONG_TAI:
                return "Ta sẽ phù phép\ncho bông tai Porata cấp 2 của ngươi\ncó 1 chỉ số ngẫu nhiên";
            case NANG_CAP_LINH_THU:
                return "Ta sẽ phù phép\ncho Linh Thú cùi bắp của ngươi\ncó 1 chỉ số ngẫu nhiên";
            case CHE_TAO_TRANG_BI_TS:
                return "Chế tạo\ntrang bị thiên sứ";
            case NANG_CAP_BONG_TAI_CAP3:
                return "Ta sẽ phù phép\ncho bông tai Porata cấp 2 của ngươi\nthành cấp 3";
            case MO_CHI_SO_BONG_TAI_CAP3:
                return "Ta sẽ phù phép\ncho bông tai Porata cấp 3 của ngươi\ncó 1 chỉ số ngẫu nhiên";
            case NANG_CAP_BONG_TAI_CAP4:
                return "Ta sẽ phù phép\ncho bông tai Porata cấp 3 của ngươi\nthành cấp 4";
            case MO_CHI_SO_BONG_TAI_CAP4:
                return "Ta sẽ phù phép\ncho bông tai Porata cấp 4 của ngươi\ncó 1 chỉ số ngẫu nhiên";
            case LUYEN_HOA_CHIEN_LINH:
                return "Ta sẽ cùng người luyện hóa\nchiến linh";
            case EP_AN_TRANG_BI:
                return "Ép Ấn Trang Bị\nKhông Lucyonfire";
            case CREATE_TRUNG_HAC_AM:
                return "Ấp trứng \nKhông Lucyonfire";
            case CREATE_TRUNG_THIEN_THAN:
                return "Ấp trứng \nKhông Lucyonfire";
            case NGUYET_AN:
                return "Ép Ấn Trang Bị\nKhông Lucyonfire";
            case DOI_DIEM:
                return "Thức Ăn";
            case GIAM_DINH_SACH:
                return "Ta sẽ phù phép\ngiám định sách đó cho ngươi";
            case TAY_SACH:
                return "Ta sẽ phù phép\ntẩy sách đó cho ngươi";
            case NANG_CAP_SACH_TUYET_KY:
                return "Ta sẽ phù phép\nnâng cấp Sách Tuyệt Kỹ cho ngươi";
            case PHUC_HOI_SACH:
                return "Ta sẽ phù phép\nphục hồi sách cho ngươi";
            case PHAN_RA_SACH:
                return "Ta sẽ phù phép\nphân rã sách cho ngươi";
            default:
                return "";
        }
    }

    private String getTextInfoTabCombine(int type) {
        switch (type) {
            case NANG_CAP_CAN_CAU:
                return "vào hành trang\nChọn 1 cần câu dưới cấp 5 là 1 kit nâng cấp"
                        + "Chỉ cần chọn 'Nâng Cấp'";
            case SUA_CHUA_CAN_CAU:
                return "vào hành trang\nChọn 1 cần câu và 1 kit sửa chữa"
                        + "Chỉ cần chọn 'Nâng Cấp'";
            case TINH_LUYEN_CAI_TRANG:
                return "vào hành trang\nChọn 1 cái trang tinh luyện dưới 3 cấp và đá tinh luyện"
                        + "Chỉ cần chọn 'Nâng Cấp'";
            case TAY_TINH_LUYEN_CAI_TRANG:
                return "vào hành trang\nChọn 1 cái trang tinh luyện và đá tẩy tinh luyện"
                        + "Chỉ cần chọn 'Nâng Cấp'";
            case MO_CHI_SO_CAI_TRANG:
                return "vào hành trang\nChọn 1 cái trang chưa kích hoạt và 10 lồng đèn"
                        + "Chỉ cần chọn 'Nâng Cấp'";
            case NANG_CAP_CHAN_MENH:
                return "vào hành trang\nChọn 1 chân mệnh bất kì\nvà 10 viên ngọc rồng 1 sao + 5k HN"
                        + "Chỉ cần chọn 'Nâng Cấp'";
            case OPEN_SKH_THAN_SU:
                return "vào hành trang\nChọn 1 trang bị thiên sứ và hủy diệt bất kì\nChọn tiếp ngẫu nhiên 1 món SKH Thần linh \n và 150 đá ngũ sắc + 150k HN"
                        +
                        " đồ Thần Sứ sẽ cùng loại \n với đồ thiên sứ! hoặc đồ hủy diệt"
                        + "Chỉ cần chọn 'Nâng Cấp'";
            case NANG_CAP_GAY_THIEN_SU:
                return "vào hành trang\nChọn 1 trang bị gậy thiên sứ bất kì\nvà 50 đá thiên sứ + 50k HN"
                        + "Chỉ cần chọn 'Nâng Cấp'";
            case OPEN_SKH_MA_SU:
                return "vào hành trang\nChọn 1 trang bị hủy diệt và thần linh bất kì\nChọn tiếp ngẫu nhiên 1 món SKH thường \n và 60 đá ngũ sắc + 60k HN"
                        +
                        " đồ Ma Sứ sẽ cùng loại \n với đồ hủy diệt hoặc thần linh"
                        + "Chỉ cần chọn 'Nâng Cấp'";
            case EP_SAO_TRANG_BI:
                return "Chọn trang bị\n(Áo, quần, găng, giày hoặc rađa) có ô đặt sao pha lê\nChọn loại sao pha lê\n"
                        + "Sau đó chọn 'Nâng cấp'";
            case PHA_LE_HOA_TRANG_BI:
                return "Chọn trang bị\n(Áo, quần, găng, giày hoặc rađa)\nSau đó chọn 'Nâng cấp'";
            case NHAP_NGOC_RONG:
                return "Vào hành trang\nChọn 7 viên ngọc cùng sao\nSau đó chọn 'Làm phép'";
            case NANG_CAP_VAT_PHAM:
                return "vào hành trang\nChọn trang bị\n(Áo, quần, găng, giày hoặc rađa)\nChọn loại đá để nâng cấp\n"
                        + "Sau đó chọn 'Nâng cấp'";
            case PHAN_RA_DO_THAN_LINH:
                return "vào hành trang\nChọn item\n(Đồ Thần)\nChọn Đồ Thần Rồi Tách\n"
                        + "Sau đó chọn 'Tách'";
            case NANG_CAP_DO_TS:
                return "vào hành trang\nChọn 2 trang bị hủy diệt bất kì\nkèm 1 món đồ thần linh\n và 5 mảnh thiên sứ\n "
                        +
                        "sẽ cho ra đồ thiên sứ từ 0-15% chỉ số"
                        + "Sau đó chọn 'Nâng Cấp'";
            case NANG_CAP_SKH_VIP:
                return "vào hành trang\nChọn 1 trang bị thần linh bất kì\nChọn tiếp ngẫu nhiên 2 món SKH thường \n " +
                        "và 20 đá ngũ sắc. Đồ SKH VIP sẽ cùng loại \n với đồ thần linh!"
                        + "Chỉ cần chọn 'Nâng Cấp'";
            case NANG_CAP_BONG_TAI:
                return "Vào hành trang\nChọn bông tai Porata\nChọn mảnh bông tai để nâng cấp, số lượng\n99 cái\nSau đó chọn 'Nâng cấp'";
            case MO_CHI_SO_BONG_TAI:
                return "Vào hành trang\nChọn bông tai Porata\nChọn mảnh hồn bông tai số lượng 99 cái\nvà đá xanh lam để nâng cấp\nSau đó chọn 'Nâng cấp'";
            case NANG_CAP_LINH_THU:
                return "Vào hành trang\nChọn Linh Thú\nChọn đá ma thuật  số lượng 99 cái\nvà thức ăn để nâng cấp\nSau đó chọn 'Nâng cấp'";
//                   case NANG_CAP_SKH_HD:
//                return "vào hành trang\nChọn 3 trang bị hủy diệt khác nhau " + "có tỉ lệ ra đồ kích hoạt hủy diệt" + "\nsau đó chọn 'Nâng cấp'\n"
//                        + "Lưu ý món đầu tiên đặt vào sẽ là trang bị để nâng cấp!";
//            case NANG_CAP_SKH_TS:
//                return "vào hành trang\nChọn 3 trang bị thiên sứ khác nhau " + "có tỉ lệ ra đồ kích hoạt thiên sứ" + "\nsau đó chọn 'Nâng cấp'\n"
//                        + "Lưu ý món đầu tiên đặt vào sẽ là trang bị để nâng cấp!";
            case NANG_CAP_DO_HD:
                return "vào hành trang\nChọn 3 trang bị thần linh bất kì\nkèm 1 đá Linh Thạch(mua ở tiên cá) \n "
                        + "sẽ cho ra đồ huy diet từ 0-15% chỉ số\nđồ sẽ theo món bỏ vào số 1"
                        + "Sau đó chọn 'Nâng Cấp'";
            case CHE_TAO_TRANG_BI_TS:
                return "Cần 1 công thức vip\nMảnh trang bị tương ứng\n"
                        + "Số Lượng\n99"
                        + "Có thể thêm\nĐá nâng cấp (tùy chọn) để tăng tỉ lệ chế tạo\n"
                        + "Đá may mắn (tùy chọn) để tăng tỉ lệ các chỉ số cơ bản và chỉ số ẩn\n"
                        + "Sau đó chọn 'Nâng cấp'";
            case NANG_CAP_BONG_TAI_CAP3:
                return "Vào hành trang\nchọn bông tai Porata cấp 2\nchọn x999 MVBT C3\nSau đó chọn 'Nâng cấp'";
                 case NANG_TL_LEN_HUY_DIET:
                return "Vào hành trang\n chọn 1 món thần linh bất kỳ, sau đó nhấn 'Nâng cấp'";
                
            case NANG_HUY_DIET_LEN_SKH_VIP:
                return "Vào hành trang\n Chọn 3 món huỷ diệt bất kỳ, món đầu tiên sẽ làm gốc, sau đó chọn 'Nâng cấp'";
            case NANG_HUY_DIET_LEN_SKH:
                return "Vào hành trang\n Chọn 1 món huỷ diệt bất kỳ, sau đó chọn 'Nâng câp'";
            case MO_CHI_SO_BONG_TAI_CAP3:
                return "Vào hành trang\nChọn bông tai Porata cấp 3\nChọn thạch phù để nâng cấp, số lượng 99 viên\nđá xanh lam\nSau đó chọn 'Nâng cấp'";
            case NANG_CAP_BONG_TAI_CAP4:
                return "Vào hành trang\nchọn bông tai Porata cấp 3\nchọn x999 MVBT C4\nSau đó chọn 'Nâng cấp' ";
            case MO_CHI_SO_BONG_TAI_CAP4:
                return "Vào hành trang\nChọn bông tai Porata cấp 4\nChọn thạch phù số lượng 99 \nvà đá xanh lam số lượng 15 viên\nSau đó chọn 'Nâng cấp'";
            case EP_AN_TRANG_BI:
                return "Vào hành trang\nChọn Trang Bị Thiên Sứ Kích hoạt\nChọn Đá Ngũ Sắc Số Lượng 300 \nThêm 300 Thỏi Vàng \n 1 món Hủy diệt Kích Hoạt Vào\nSau đó chọn 'Nâng cấp'";
            case CREATE_TRUNG_THIEN_THAN:
                return "Vào hành trang\nChọn trứng thiên thần \nChọn Hồn linh thú Số Lượng 99 \nThêm 20 Thỏi Vàng Vào\nSau đó chọn 'Nâng cấp'";
            case CREATE_TRUNG_HAC_AM:
                return "Vào hành trang\nChọn trứng hắc ám \nChọn Hồn linh thú Số Lượng 99 \nThêm 20 Thỏi Vàng Vào\nSau đó chọn 'Nâng cấp'";
            case NGUYET_AN:
                return "Vào hành trang\nChọn Trang Bị\nChọn Đá Nâng Cấp Số Lượng 99 \nMấy Viên Kiểu Titan Ruby Các Thứ Ấy\nSau đó chọn 'Nâng cấp'\nCHÚ Ý KHÔNG DÙNG\nTRANG BỊ KÍCH HOẠT";
            case DOI_DIEM:
                return "Vào hành trang\nChọn x99 Thức Ăn\nSau đó chọn 'Nâng cấp'";
            case GIAM_DINH_SACH:
                return "Vào hành trang chọn\n1 sách cần giám định";
            case TAY_SACH:
                return "Vào hành trang chọn\n1 sách cần tẩy";
            case NANG_CAP_SACH_TUYET_KY:
                return "Vào hành trang chọn\nSách Tuyệt Kỹ 1 cần nâng cấp và 10 Kìm bấm giấy";
            case PHUC_HOI_SACH:
                return "Vào hành trang chọn\nCác Sách Tuyệt Kỹ cần phục hồi";
            case PHAN_RA_SACH:
                return "Vào hành trang chọn\n1 sách cần phân rã";
            default:
                return "";
        }
    }

}
