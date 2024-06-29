package red.services.func;

import java.util.HashMap;
import java.util.Map;

import com.girlkun.network.io.Message;
import red.consts.ConstNpc;
import red.consts.ConstPlayer;
import red.jdbc.daos.GodGK;
import red.jdbc.daos.PlayerDAO;
import red.team.item.Item;
import red.team.item.Item.ItemOption;
import red.team.map.Zone;
import red.team.player.Inventory;
import red.team.player.Player;
import red.team.server.Client;
import red.services.InventoryServiceNew;
import red.services.ItemService;
import red.services.ItemTimeService;
import red.services.NpcService;
import red.services.PlayerService;
import red.services.Service;
import red.utils.Logger;
import red.utils.Util;

import java.util.List;

/**
 *
 * @Stole By Lucy#0800💖
 *
 */
public class SummonDragon {

    public static final byte WISHED = 0;
    public static final byte TIME_UP = 1;

    public static final byte DRAGON_SHENRON = 0;
    public static final byte DRAGON_PORUNGA = 1;
    public static final byte DRAGON_BANG = 2;
    public static final byte DRAGON_XUONG = 3;
    //
    public static final short NGOC_RONG_1_SAO = 14;
    public static final short NGOC_RONG_2_SAO = 15;
    public static final short NGOC_RONG_3_SAO = 16;
    public static final short NGOC_RONG_4_SAO = 17;
    public static final short NGOC_RONG_5_SAO = 18;
    public static final short NGOC_RONG_6_SAO = 19;
    public static final short NGOC_RONG_7_SAO = 20;

    public static final short NGOC_RONGTRB1 = 2091;
    public static final short NGOC_RONGTRB2 = 2092;
    public static final short NGOC_RONGTRB3 = 2093;

    public static final short NGOC_RONG_DEN_1_SAO = 807;
    public static final short NGOC_RONG_DEN_2_SAO = 808;
    public static final short NGOC_RONG_DEN_3_SAO = 809;
    public static final short NGOC_RONG_DEN_4_SAO = 810;
    public static final short NGOC_RONG_DEN_5_SAO = 811;
    public static final short NGOC_RONG_DEN_6_SAO = 812;
    public static final short NGOC_RONG_DEN_7_SAO = 813;

    public static final short NGOC_RONG_BANG_1_SAO = 925;
    public static final short NGOC_RONG_BANG_2_SAO = 926;
    public static final short NGOC_RONG_BANG_3_SAO = 927;
    public static final short NGOC_RONG_BANG_4_SAO = 928;
    public static final short NGOC_RONG_BANG_5_SAO = 929;
    public static final short NGOC_RONG_BANG_6_SAO = 930;
    public static final short NGOC_RONG_BANG_7_SAO = 931;
    //

    public static final String SUMMON_SHENRON_TUTORIAL = "Có 3 cách gọi rồng thần. Gọi từ ngọc 1 sao, gọi từ ngọc 2 sao, hoặc gọi từ ngọc 3 sao\n"
            + "Các ngọc 4 sao đến 7 sao không thể gọi rồng thần được\n"
            + "Để gọi rồng 1 sao cần ngọc từ 1 sao đến 7 sao\n"
            + "Để gọi rồng 2 sao cần ngọc từ 2 sao đến 7 sao\n"
            + "Để gọi rồng 3 sao cần ngọc từ 3 sao đến 7sao\n"
            + "Điều ước rồng 3 sao: Capsule 3 sao, hoặc 2 triệu sức mạnh, hoặc 200k vàng\n"
            + "Điều ước rồng 2 sao: Capsule 2 sao, hoặc 20 triệu sức mạnh, hoặc 200 triệu vàng\n"
            + "Điều ước rồng 1 sao: Capsule 1 sao, hoặc 200 triệu sức mạnh, hoặc 2 tỏi, hoặc đẹp trai, hoặc....\n"
            + "Ngọc rồng sẽ mất ngay khi gọi rồng dù bạn có ước hay không\n"
            + "Quá 5 phút nếu không ước rồng thần sẽ bay mất";
    public static final String SHENRON_SAY = "Ta sẽ ban cho người 1 điều ước, ngươi có 5 phút, hãy suy nghĩ thật kỹ trước khi quyết định";
    public static final String SUMMON_SHENRON_TRB

            = "Để gọi rồng cần ngọc từ 1 sao đến 3 sao\n"
                    + "Điều ước rồng 1 sao: Tăng 30% Sức Đánh , hoặc 30% HP, hoặc 30% KI Trong 30 phút!\n"
                    + "Ngọc rồng sẽ mất ngay khi gọi rồng dù bạn có ước hay không\n"
                    + "Quá 5 phút nếu không ước rồng thần sẽ bay mất";
    public static final String[] SHENRON_1_STAR_TRB = new String[] { "Sức Đánh\n+30%", "HP\n+30%", "KI\n+30%",
            "30% crit", "Găng Đang Đeo\nLên 1% SD Chí Mạng" };
    // BLACK
    public static final String SUMMON_SHENRON_BLACK

            = "Để gọi rồng xương cần ngọc từ 1 sao đến 7 sao\n"
                    + "Điều ước rồng 1 sao: 20 Thỏi vàng , hoặc 5k Hồng ngọc, hoặc 20 Đá ngũ sắc, hoặc 20 quả hồng đào, hoặc 20 huy chương!\n"
                    + "Ngọc rồng sẽ mất ngay khi gọi rồng dù bạn có ước hay không\n"
                    + "Quá 5 phút nếu không ước rồng thần sẽ bay mất";
    public static final String[] SHENRON_1_STAR_BLACK = new String[] { "20\nThỏi Vàng", "5k\nHồng Ngọc", "20\nĐá ngũ sắc",
            "20\nHồng Đào", "20\nHuy chương" };
    // BANG
    public static final String SUMMON_SHENRON_BANG

            = "Để gọi rồng băng cần ngọc từ 1 sao đến 7 sao\n"
                    + "Điều ước rồng băng 1 sao: 20 mảnh áo , hoặc 20 mảnh quần, hoặc 20 mảnh găng , hoặc 20 mảnh nhẫn, hoặc 20 mảnh giày\n"
                    + "Ngọc rồng sẽ mất ngay khi gọi rồng dù bạn có ước hay không\n"
                    + "Quá 5 phút nếu không ước rồng thần sẽ bay mất";
    public static final String[] SHENRON_1_STAR_BANG = new String[] { "20 \n Mảnh Áo", "20 \n Mảnh Quần", "20 \n Mảnh Găng",
            "20 \n Mảnh Nhẫn", "20 \n Mảnh giày" };
    //
    public static final String[] SHENRON_1_STAR_WISHES_1 = new String[] { "Giàu có\n+2 Tỏi\nVàng",
            "Găng tay\nđang mang\nlên 1 cấp", "Chí mạng\nGốc +2%",
            "Thay\nChiêu 2-3\nĐệ tử", "Điều ước\nkhác" };
    public static final String[] SHENRON_1_STAR_WISHES_2 = new String[] { "Đẹp trai\nnhất\nVũ trụ",
            "Giàu có\n+2K\nHồng Ngọc", "Đột phá sức mạnh",
            "Găng tay đệ\nđang mang\nlên 1 cấp",
            "Điều ước\nkhác" };
    public static final String[] SHENRON_2_STARS_WHISHES = new String[] { "Giàu có\n+700K\nHồng Ngọc",
            "+20 Tr\nSức mạnh\nvà tiềm năng", "Giàu có\n+200 Tr\nVàng" };
    public static final String[] SHENRON_3_STARS_WHISHES = new String[] { "Giàu có\n+100\nHồng Ngọc",
            "+2 Tr\nSức mạnh\nvà tiềm năng", "Giàu có\n+20 Tr\nVàng" };
    // --------------------------------------------------------------------------
    private static SummonDragon instance;
    private final Map pl_dragonStar;

    private long lastTimeShenronAppeared;
    private long lastTimeShenronWait;
    private final int timeResummonShenron = 300000;

    private boolean isShenronAppear;
    private final int timeShenronWait = 300000;

    private final Thread update;
    private boolean active;
    // trb
    private long lastTimetrbAppeared;
    private long lastTimetrbWait;
    private final int timeResummontrb = 300000;
    private int playertrbnronId;
    // bang
    private long lastTimebangAppeared;
    private long lastTimebangWait;
    private final int timeResummonbang = 300000;
    private int playerbangnronId;
    // black
    private long lastTimeblackAppeared;
    private long lastTimeblackWait;
    private final int timeResummonblack = 300000;
    private int playerblacknronId;
    //
    public boolean isPlayerDisconnect;
    public Player playerSummonShenron;
    private int playerSummonShenronId;
    private Zone mapShenronAppear;
    private byte shenronStar;
    private int menuShenron;
    private byte select;

    private SummonDragon() {
        this.pl_dragonStar = new HashMap<>();
        this.update = new Thread(() -> {
            while (active) {
                try {
                    if (isShenronAppear) {
                        if (isPlayerDisconnect) {

                            List<Player> players = mapShenronAppear.getPlayers();
                            for (Player plMap : players) {
                                if (plMap.id == playerSummonShenronId) {
                                    playerSummonShenron = plMap;
                                    reSummonShenron();
                                    isPlayerDisconnect = false;
                                    break;
                                }
                            }

                        }
                        if (Util.canDoWithTime(lastTimeShenronWait, timeShenronWait)) {
                            shenronLeave(playerSummonShenron, TIME_UP);
                        }
                    }
                    Thread.sleep(1000);
                } catch (Exception e) {
                    Logger.logException(SummonDragon.class, e);
                }
            }
        });
        this.active();
    }

    private void active() {
        if (!active) {
            active = true;
            this.update.start();
        }
    }

    public void summonNamec(Player pl) {
        if (pl.zone.map.mapId == 7) {
            playerSummonShenron = pl;
            playerSummonShenronId = (int) pl.id;
            mapShenronAppear = pl.zone;
            sendNotifyShenronAppear();
            activeShenron(pl, true, SummonDragon.DRAGON_PORUNGA);
            sendWhishesNamec(pl);
        } else {
            Service.gI().sendThongBao(pl, "Không thể thực hiện");
        }
    }

    public static SummonDragon gI() {
        if (instance == null) {
            instance = new SummonDragon();
        }
        return instance;
    }

    public void openMenuSummonShenron(Player pl, byte dragonBallStar) {
        this.pl_dragonStar.put(pl, dragonBallStar);
        NpcService.gI().createMenuConMeo(pl, ConstNpc.SUMMON_SHENRON, -1, "Bạn muốn gọi rồng thần ?",
                "Hướng\ndẫn thêm\n(mới)", "Gọi\nRồng Thần\n" + dragonBallStar + " Sao");
    }

    public void summonShenron(Player pl) {
        if (pl.zone.map.mapId == 0 || pl.zone.map.mapId == 7 || pl.zone.map.mapId == 14) {
            if (checkShenronBall(pl)) {
                if (isShenronAppear) {
                    Service.gI().sendThongBao(pl, "Không thể thực hiện");
                    return;
                }

                if (Util.canDoWithTime(lastTimeShenronAppeared, timeResummonShenron)) {
                    // gọi rồng
                    playerSummonShenron = pl;
                    playerSummonShenronId = (int) pl.id;
                    mapShenronAppear = pl.zone;
                    byte dragonStar = (byte) pl_dragonStar.get(playerSummonShenron);
                    int begin = NGOC_RONG_1_SAO;
                    switch (dragonStar) {
                        case 2:
                            begin = NGOC_RONG_2_SAO;
                            break;
                        case 3:
                            begin = NGOC_RONG_3_SAO;
                            break;
                    }
                    for (int i = begin; i <= NGOC_RONG_7_SAO; i++) {
                        try {
                            InventoryServiceNew.gI().subQuantityItemsBag(pl,
                                    InventoryServiceNew.gI().findItemBag(pl, i), 1);
                        } catch (Exception ex) {
                        }
                    }
                    InventoryServiceNew.gI().sendItemBags(pl);
                    sendNotifyShenronAppear();
                    activeShenron(pl, true, SummonDragon.DRAGON_SHENRON);
                    sendWhishesShenron(pl);
                } else {
                    int timeLeft = (int) ((timeResummonShenron - (System.currentTimeMillis() - lastTimeShenronAppeared))
                            / 1000);
                    Service.gI().sendThongBao(pl, "Vui lòng đợi "
                            + (timeLeft < 7200 ? timeLeft + " giây" : timeLeft / 60 + " phút") + " nữa");
                }
            }
        } else {
            Service.gI().sendThongBao(pl, "Chỉ được gọi rồng thần ở ngôi làng trước nhà");
        }
    }

    public void openMenuSummonShenronTRB(Player pl, byte dragonBallStar) {
        this.pl_dragonStar.put(pl, dragonBallStar);
        NpcService.gI().createMenuConMeo(pl, ConstNpc.SUMMON_SHENRONTRB, -1, "Bạn muốn gọi rồng thần TRB?",
                "Hướng\ndẫn thêm\n(mới)", "Gọi\nRồng Thần\n" + dragonBallStar + " Sao");
    }

    public void openMenuSummonShenronBang(Player pl, byte dragonBallStar) {
        this.pl_dragonStar.put(pl, dragonBallStar);
        NpcService.gI().createMenuConMeo(pl, ConstNpc.SUMMON_SHENRONBANG, -1, "Bạn muốn gọi rồng băng?",
                "Hướng\ndẫn thêm\n(mới)", "Gọi\nRồng Thần\n" + dragonBallStar + " Sao");
    }

    public void openMenuSummonShenronBlack(Player pl, byte dragonBallStar) {
        this.pl_dragonStar.put(pl, dragonBallStar);
        NpcService.gI().createMenuConMeo(pl, ConstNpc.SUMMON_SHENRONBLACK, -1, "Bạn muốn gọi rồng xương?",
                "Hướng\ndẫn thêm\n(mới)", "Gọi\nRồng Thần\n" + dragonBallStar + " Sao");
    }

    public void summonShenronTRB(Player pl) {
        if (pl.zone.map.mapId == 170) {
            if (checkShenronBalltrb(pl)) {
                if (isShenronAppear) {
                    Service.gI().sendThongBao(pl, "Không thể thực hiện");
                    return;
                }

                if (Util.canDoWithTime(lastTimetrbAppeared, timeResummontrb)) {
                    // gọi rồng
                    playerSummonShenron = pl;
                    playertrbnronId = (int) pl.id;
                    mapShenronAppear = pl.zone;
                    int begin = NGOC_RONGTRB1;

                    for (int i = begin; i <= NGOC_RONGTRB3; i++) {
                        try {
                            InventoryServiceNew.gI().subQuantityItemsBag(pl,
                                    InventoryServiceNew.gI().findItemBag(pl, i), 1);
                        } catch (Exception ex) {
                        }
                    }
                    InventoryServiceNew.gI().sendItemBags(pl);
                    sendNotifyShenronAppear();
                    activeShenron(pl, true, SummonDragon.DRAGON_PORUNGA);
                    sendWhishesShenrontrb(pl);
                } else {
                    int timeLeft = (int) ((timeResummontrb - (System.currentTimeMillis() - lastTimetrbAppeared))
                            / 1000);
                    Service.gI().sendThongBao(pl, "Vui lòng đợi "
                            + (timeLeft < 7200 ? timeLeft + " giây" : timeLeft / 60 + " phút") + " nữa");
                }
            }
        } else {
            Service.gI().sendThongBao(pl, "Chỉ được gọi rồng thần ở hành tinh Celreal");
        }
    }

    private void sendWhishesShenrontrb(Player pl) {
        byte dragonStar;
        try {
            dragonStar = (byte) pl_dragonStar.get(pl);
            this.shenronStar = dragonStar;
        } catch (Exception e) {
            dragonStar = this.shenronStar;
        }
        switch (dragonStar) {
            case 1:
                NpcService.gI().createMenuRongThieng(pl, ConstNpc.SHENRONTRB, SHENRON_SAY, SHENRON_1_STAR_TRB);
                break;
            case 2:
                NpcService.gI().createMenuRongThieng(pl, ConstNpc.SHENRONTRB, SHENRON_SAY, SHENRON_1_STAR_TRB);
                break;
            case 3:
                NpcService.gI().createMenuRongThieng(pl, ConstNpc.SHENRONTRB, SHENRON_SAY, SHENRON_1_STAR_TRB);
                break;
        }
    }

    private boolean checkShenronBalltrb(Player pl) {
        byte dragonStar = (byte) this.pl_dragonStar.get(pl);
        Item t2 = InventoryServiceNew.gI().findItemBag(pl, NGOC_RONGTRB2);
        Item t3 = InventoryServiceNew.gI().findItemBag(pl, NGOC_RONGTRB3);
        if (t2.quantity < 1 || t2 == null) {
            Service.gI().sendThongBao(pl, "Bạn còn thiếu 1 viên ngọc rồng 2 sao");
            return false;
        }
        if (t3.quantity < 1 || t2 == null) {
            Service.gI().sendThongBao(pl, "Bạn còn thiếu 1 viên ngọc rồng 3 sao");
            return false;
        }

        return true;
    }

    // bang
    public void summonShenronBang(Player pl) {
        if (pl.zone.map.mapId == 176) {
            if (checkShenronBallBang(pl)) {
                if (isShenronAppear) {
                    Service.gI().sendThongBao(pl, "Không thể thực hiện");
                    return;
                }

                if (Util.canDoWithTime(lastTimebangAppeared, timeResummonbang)) {
                    // gọi rồng
                    playerSummonShenron = pl;
                    playerbangnronId = (int) pl.id;
                    mapShenronAppear = pl.zone;
                    int begin = NGOC_RONG_BANG_1_SAO;

                    for (int i = begin; i <= NGOC_RONG_BANG_7_SAO; i++) {
                        try {
                            InventoryServiceNew.gI().subQuantityItemsBag(pl,
                                    InventoryServiceNew.gI().findItemBag(pl, i), 1);
                        } catch (Exception ex) {
                        }
                    }
                    InventoryServiceNew.gI().sendItemBags(pl);
                    sendNotifyShenronAppear();
                    activeShenron(pl, true, SummonDragon.DRAGON_BANG);
                    sendWhishesShenronBang(pl);
                } else {
                    int timeLeft = (int) ((timeResummonbang - (System.currentTimeMillis() - lastTimebangAppeared))
                            / 1000);
                    Service.gI().sendThongBao(pl, "Vui lòng đợi "
                            + (timeLeft < 7200 ? timeLeft + " giây" : timeLeft / 60 + " phút") + " nữa");
                }
            }
        } else {
            Service.gI().sendThongBao(pl, "Chỉ được gọi rồng thần ở hành tinh Thiên sứ 3");
        }
    }

    private boolean checkShenronBallBang(Player pl) {
        byte dragonStar = (byte) this.pl_dragonStar.get(pl);
        Item s2 = InventoryServiceNew.gI().findItemBag(pl, NGOC_RONG_BANG_2_SAO);
        Item s3 = InventoryServiceNew.gI().findItemBag(pl, NGOC_RONG_BANG_3_SAO);
        Item s4 = InventoryServiceNew.gI().findItemBag(pl, NGOC_RONG_BANG_4_SAO);
        Item s5 = InventoryServiceNew.gI().findItemBag(pl, NGOC_RONG_BANG_5_SAO);
        Item s6 = InventoryServiceNew.gI().findItemBag(pl, NGOC_RONG_BANG_6_SAO);
        Item s7 = InventoryServiceNew.gI().findItemBag(pl, NGOC_RONG_BANG_7_SAO);
        if (dragonStar == 1) {
            if (s2 == null || s2.quantity < 1) {
                Service.gI().sendThongBao(pl, "Bạn còn thiếu 1 viên ngọc rồng 2 sao");
                return false;
            }
            if (s3 == null || s3.quantity < 1) {
                Service.gI().sendThongBao(pl, "Bạn còn thiếu 1 viên ngọc rồng 3 sao");
                return false;
            }
        } else if (dragonStar == 2) {
            if (s3 == null || s3.quantity < 1) {
                Service.gI().sendThongBao(pl, "Bạn còn thiếu 1 viên ngọc rồng 3 sao");
                return false;
            }
        }
        if (s4 == null || s4.quantity < 1) {
            Service.gI().sendThongBao(pl, "Bạn còn thiếu 1 viên ngọc rồng 4 sao");
            return false;
        }
        if (s5 == null || s5.quantity < 1) {
            Service.gI().sendThongBao(pl, "Bạn còn thiếu 1 viên ngọc rồng 5 sao");
            return false;
        }
        if (s6 == null || s6.quantity < 1) {
            Service.gI().sendThongBao(pl, "Bạn còn thiếu 1 viên ngọc rồng 6 sao");
            return false;
        }
        if (s7 == null || s7.quantity < 1) {
            Service.gI().sendThongBao(pl, "Bạn còn thiếu 1 viên ngọc rồng 7 sao");
            return false;
        }
        return true;
    }

    private void sendWhishesShenronBang(Player pl) {
        byte dragonStar;
        try {
            dragonStar = (byte) pl_dragonStar.get(pl);
            this.shenronStar = dragonStar;
        } catch (Exception e) {
            dragonStar = this.shenronStar;
        }
        switch (dragonStar) {
            case 1:
                NpcService.gI().createMenuRongThieng(pl, ConstNpc.SHENRONBANG, SHENRON_SAY, SHENRON_1_STAR_BANG);
                break;

        }
    }

    // black
    public void summonShenronBlack(Player pl) {
        if (pl.zone.map.mapId == 159) {
            if (checkShenronBallBlack(pl)) {
                if (isShenronAppear) {
                    Service.gI().sendThongBao(pl, "Không thể thực hiện");
                    return;
                }

                if (Util.canDoWithTime(lastTimeblackAppeared, timeResummonblack)) {
                    // gọi rồng
                    playerSummonShenron = pl;
                    playerblacknronId = (int) pl.id;
                    mapShenronAppear = pl.zone;
                    int begin = NGOC_RONG_DEN_1_SAO;

                    for (int i = begin; i <= NGOC_RONG_DEN_7_SAO; i++) {
                        try {
                            InventoryServiceNew.gI().subQuantityItemsBag(pl,
                                    InventoryServiceNew.gI().findItemBag(pl, i), 1);
                        } catch (Exception ex) {
                        }
                    }
                    InventoryServiceNew.gI().sendItemBags(pl);
                    sendNotifyShenronAppear();
                    activeShenron(pl, true, SummonDragon.DRAGON_XUONG);
                    sendWhishesShenronBlack(pl);
                } else {
                    int timeLeft = (int) ((timeResummonblack - (System.currentTimeMillis() - lastTimeblackAppeared))
                            / 1000);
                    Service.gI().sendThongBao(pl, "Vui lòng đợi "
                            + (timeLeft < 7200 ? timeLeft + " giây" : timeLeft / 60 + " phút") + " nữa");
                }
            }
        } else {
            Service.gI().sendThongBao(pl, "Chỉ được gọi rồng thần ở nam thánh địa");
        }
    }

    private boolean checkShenronBallBlack(Player pl) {
        byte dragonStar = (byte) this.pl_dragonStar.get(pl);
        Item s2 = InventoryServiceNew.gI().findItemBag(pl, NGOC_RONG_DEN_2_SAO);
        Item s3 = InventoryServiceNew.gI().findItemBag(pl, NGOC_RONG_DEN_3_SAO);
        Item s4 = InventoryServiceNew.gI().findItemBag(pl, NGOC_RONG_DEN_4_SAO);
        Item s5 = InventoryServiceNew.gI().findItemBag(pl, NGOC_RONG_DEN_5_SAO);
        Item s6 = InventoryServiceNew.gI().findItemBag(pl, NGOC_RONG_DEN_6_SAO);
        Item s7 = InventoryServiceNew.gI().findItemBag(pl, NGOC_RONG_DEN_7_SAO);
        if (dragonStar == 1) {
            if (s2 == null || s2.quantity < 1) {
                Service.gI().sendThongBao(pl, "Bạn còn thiếu 1 viên ngọc rồng 2 sao");
                return false;
            }
            if (s3 == null || s3.quantity < 1) {
                Service.gI().sendThongBao(pl, "Bạn còn thiếu 1 viên ngọc rồng 3 sao");
                return false;
            }
        } else if (dragonStar == 2) {
            if (s3 == null || s3.quantity < 1) {
                Service.gI().sendThongBao(pl, "Bạn còn thiếu 1 viên ngọc rồng 3 sao");
                return false;
            }
        }
        if (s4 == null || s4.quantity < 1) {
            Service.gI().sendThongBao(pl, "Bạn còn thiếu 1 viên ngọc rồng 4 sao");
            return false;
        }
        if (s5 == null || s5.quantity < 1) {
            Service.gI().sendThongBao(pl, "Bạn còn thiếu 1 viên ngọc rồng 5 sao");
            return false;
        }
        if (s6 == null || s6.quantity < 1) {
            Service.gI().sendThongBao(pl, "Bạn còn thiếu 1 viên ngọc rồng 6 sao");
            return false;
        }
        if (s7 == null || s7.quantity < 1) {
            Service.gI().sendThongBao(pl, "Bạn còn thiếu 1 viên ngọc rồng 7 sao");
            return false;
        }
        return true;
    }

    private void sendWhishesShenronBlack(Player pl) {
        byte dragonStar;
        try {
            dragonStar = (byte) pl_dragonStar.get(pl);
            this.shenronStar = dragonStar;
        } catch (Exception e) {
            dragonStar = this.shenronStar;
        }
        switch (dragonStar) {
            case 1:
                NpcService.gI().createMenuRongThieng(pl, ConstNpc.SHENRONBLACK, SHENRON_SAY, SHENRON_1_STAR_BLACK);
                break;

        }
    }

    //
    private void reSummonShenron() {
        activeShenron(playerSummonShenron, true, SummonDragon.DRAGON_SHENRON);
        sendWhishesShenron(playerSummonShenron);
    }

    private void sendWhishesShenron(Player pl) {
        byte dragonStar;
        try {
            dragonStar = (byte) pl_dragonStar.get(pl);
            this.shenronStar = dragonStar;
        } catch (Exception e) {
            dragonStar = this.shenronStar;
        }
        switch (dragonStar) {
            case 1:
                NpcService.gI().createMenuRongThieng(pl, ConstNpc.SHENRON_1_1, SHENRON_SAY, SHENRON_1_STAR_WISHES_1);
                break;
            case 2:
                NpcService.gI().createMenuRongThieng(pl, ConstNpc.SHENRON_2, SHENRON_SAY, SHENRON_2_STARS_WHISHES);
                break;
            case 3:
                NpcService.gI().createMenuRongThieng(pl, ConstNpc.SHENRON_3, SHENRON_SAY, SHENRON_3_STARS_WHISHES);
                break;
        }
    }

    private void sendWhishesNamec(Player pl) {
        NpcService.gI().createMenuRongThieng(pl, ConstNpc.NAMEC_1,
                "Ta sẽ ban cho cả bang ngươi 1 điều ước, ngươi có 5 phút, hãy suy nghĩ thật kỹ trước khi quyết định",
                "x99 ngọc rồng 3 sao");
    }

    private void activeShenron(Player pl, boolean appear, byte type) {
        Message msg;
        try {
            msg = new Message(-83);
            msg.writer().writeByte(appear ? 0 : (byte) 1);
            if (appear) {
                msg.writer().writeShort(pl.zone.map.mapId);
                msg.writer().writeShort(pl.zone.map.bgId);
                msg.writer().writeByte(pl.zone.zoneId);
                msg.writer().writeInt((int) pl.id);
                msg.writer().writeUTF("");
                msg.writer().writeShort(pl.location.x);
                msg.writer().writeShort(pl.location.y);
                msg.writer().writeByte(type);
                lastTimeShenronWait = System.currentTimeMillis();
                lastTimetrbWait = System.currentTimeMillis();
                lastTimebangWait = System.currentTimeMillis();
                lastTimeblackWait= System.currentTimeMillis();
                isShenronAppear = true;
            }
            Service.gI().sendMessAllPlayer(msg);
        } catch (Exception e) {
        }
    }

    private boolean checkShenronBall(Player pl) {
        byte dragonStar = (byte) this.pl_dragonStar.get(pl);
        Item s2 = InventoryServiceNew.gI().findItemBag(pl, NGOC_RONG_2_SAO);
        Item s3 = InventoryServiceNew.gI().findItemBag(pl, NGOC_RONG_3_SAO);
        Item s4 = InventoryServiceNew.gI().findItemBag(pl, NGOC_RONG_4_SAO);
        Item s5 = InventoryServiceNew.gI().findItemBag(pl, NGOC_RONG_5_SAO);
        Item s6 = InventoryServiceNew.gI().findItemBag(pl, NGOC_RONG_6_SAO);
        Item s7 = InventoryServiceNew.gI().findItemBag(pl, NGOC_RONG_7_SAO);
        if (dragonStar == 1) {
            if (s2 == null || s2.quantity < 1) {
                Service.gI().sendThongBao(pl, "Bạn còn thiếu 1 viên ngọc rồng 2 sao");
                return false;
            }
            if (s3 == null || s3.quantity < 1) {
                Service.gI().sendThongBao(pl, "Bạn còn thiếu 1 viên ngọc rồng 3 sao");
                return false;
            }
        } else if (dragonStar == 2) {
            if (s3 == null || s3.quantity < 1) {
                Service.gI().sendThongBao(pl, "Bạn còn thiếu 1 viên ngọc rồng 3 sao");
                return false;
            }
        }
        if (s4 == null || s4.quantity < 1) {
            Service.gI().sendThongBao(pl, "Bạn còn thiếu 1 viên ngọc rồng 4 sao");
            return false;
        }
        if (s5 == null || s5.quantity < 1) {
            Service.gI().sendThongBao(pl, "Bạn còn thiếu 1 viên ngọc rồng 5 sao");
            return false;
        }
        if (s6 == null || s6.quantity < 1) {
            Service.gI().sendThongBao(pl, "Bạn còn thiếu 1 viên ngọc rồng 6 sao");
            return false;
        }
        if (s7 == null || s7.quantity < 1) {
            Service.gI().sendThongBao(pl, "Bạn còn thiếu 1 viên ngọc rồng 7 sao");
            return false;
        }
        return true;
    }

    private void sendNotifyShenronAppear() {
        Message msg;
        try {
            msg = new Message(-25);
            msg.writer().writeUTF(playerSummonShenron.name + " vừa gọi rồng thần tại "
                    + playerSummonShenron.zone.map.mapName + " khu vực " + playerSummonShenron.zone.zoneId);
            Service.gI().sendMessAllPlayerIgnoreMe(playerSummonShenron, msg);
            msg.cleanup();
        } catch (Exception e) {
        }
    }

    public void confirmWish() {
        switch (this.menuShenron) {
            case ConstNpc.SHENRON_1_1:
                switch (this.select) {
                    case 0: // 20 tr vàng
                        this.playerSummonShenron.inventory.gold = 2000000000;
                        PlayerService.gI().sendInfoHpMpMoney(this.playerSummonShenron);
                        break;
                    case 1: // găng tay đang đeo lên 1 cấp
                        Item item = this.playerSummonShenron.inventory.itemsBody.get(2);
                        if (item.isNotNullItem()) {
                            int level = 0;
                            for (ItemOption io : item.itemOptions) {
                                if (io.optionTemplate.id == 72) {
                                    level = io.param;
                                    if (level < 7) {
                                        io.param++;
                                    }
                                    break;
                                }
                            }
                            if (level < 7) {
                                if (level == 0) {
                                    item.itemOptions.add(new ItemOption(72, 1));
                                }
                                for (ItemOption io : item.itemOptions) {
                                    if (io.optionTemplate.id == 0) {
                                        io.param += (io.param * 10 / 100);
                                        break;
                                    }
                                }
                                InventoryServiceNew.gI().sendItemBody(playerSummonShenron);
                            } else {
                                Service.gI().sendThongBao(playerSummonShenron, "Găng tay của ngươi đã đạt cấp tối đa");
                                reOpenShenronWishes(playerSummonShenron);
                                return;
                            }
                        } else {
                            Service.gI().sendThongBao(playerSummonShenron, "Ngươi hiện tại có đeo găng đâu");
                            reOpenShenronWishes(playerSummonShenron);
                            return;
                        }
                        break;
                    case 2: // chí mạng +2%
                        if (this.playerSummonShenron.nPoint.critg < 9) {
                            this.playerSummonShenron.nPoint.critg += 2;
                        } else {
                            Service.gI().sendThongBao(playerSummonShenron,
                                    "Điều ước này đã quá sức với ta, ta sẽ cho ngươi chọn lại");
                            reOpenShenronWishes(playerSummonShenron);
                            return;
                        }
                        break;
                    case 3: // thay chiêu 2-3 đệ tử
                        if (playerSummonShenron.pet != null) {
                            if (playerSummonShenron.pet.playerSkill.skills.get(1).skillId != -1) {
                                playerSummonShenron.pet.openSkill2();
                                if (playerSummonShenron.pet.playerSkill.skills.get(2).skillId != -1) {
                                    playerSummonShenron.pet.openSkill3();
                                }
                            } else {
                                Service.gI().sendThongBao(playerSummonShenron,
                                        "Ít nhất đệ tử ngươi phải có chiêu 2 chứ!");
                                reOpenShenronWishes(playerSummonShenron);
                                return;
                            }
                        } else {
                            Service.gI().sendThongBao(playerSummonShenron, "Ngươi làm gì có đệ tử?");
                            reOpenShenronWishes(playerSummonShenron);
                            return;
                        }
                        break;
                }
                break;
            case ConstNpc.SHENRON_1_2:
                switch (this.select) {
                    case 0: // đẹp trai nhất vũ trụ
                        if (InventoryServiceNew.gI().getCountEmptyBag(playerSummonShenron) > 0) {
                            byte gender = this.playerSummonShenron.gender;
                            Item avtVip = ItemService.gI().createNewItem((short) (gender == ConstPlayer.TRAI_DAT ? 227
                                    : gender == ConstPlayer.NAMEC ? 228 : 229));
                            avtVip.itemOptions.add(new ItemOption(97, Util.nextInt(5, 10)));
                            avtVip.itemOptions.add(new ItemOption(77, Util.nextInt(10, 20)));
                            InventoryServiceNew.gI().addItemBag(playerSummonShenron, avtVip);
                            InventoryServiceNew.gI().sendItemBags(playerSummonShenron);
                        } else {
                            Service.gI().sendThongBao(playerSummonShenron, "Hành trang đã đầy");
                            reOpenShenronWishes(playerSummonShenron);
                            return;
                        }
                        break;
                    case 1: // +1,5 ngọc
                        this.playerSummonShenron.inventory.ruby += 2000;
                        PlayerService.gI().sendInfoHpMpMoney(this.playerSummonShenron);
                        break;
                    case 2: // suc manh tiem an
                        Item ct = ItemService.gI().createNewItem((short) 1193);
                        ct.itemOptions.add(new ItemOption(30, 0));
                        InventoryServiceNew.gI().addItemBag(playerSummonShenron, ct);
                        InventoryServiceNew.gI().sendItemBags(playerSummonShenron);
                        Service.gI().sendThongBao(playerSummonShenron, "Bạn nhận được sức mạnh tiềm ẩn");
                        break;
                    case 3: // găng tay đệ lên 1 cấp
                        if (this.playerSummonShenron.pet != null) {
                            Item item = this.playerSummonShenron.pet.inventory.itemsBody.get(2);
                            if (item.isNotNullItem()) {
                                int level = 0;
                                for (ItemOption io : item.itemOptions) {
                                    if (io.optionTemplate.id == 72) {
                                        level = io.param;
                                        if (level < 7) {
                                            io.param++;
                                        }
                                        break;
                                    }
                                }
                                if (level < 7) {
                                    if (level == 0) {
                                        item.itemOptions.add(new ItemOption(72, 1));
                                    }
                                    for (ItemOption io : item.itemOptions) {
                                        if (io.optionTemplate.id == 0) {
                                            io.param += (io.param * 10 / 100);
                                            break;
                                        }
                                    }
                                    Service.gI().point(playerSummonShenron);
                                } else {
                                    Service.gI().sendThongBao(playerSummonShenron,
                                            "Găng tay của đệ ngươi đã đạt cấp tối đa");
                                    reOpenShenronWishes(playerSummonShenron);
                                    return;
                                }
                            } else {
                                Service.gI().sendThongBao(playerSummonShenron, "Đệ ngươi hiện tại có đeo găng đâu");
                                reOpenShenronWishes(playerSummonShenron);
                                return;
                            }
                        } else {
                            Service.gI().sendThongBao(playerSummonShenron, "Ngươi đâu có đệ tử");
                            reOpenShenronWishes(playerSummonShenron);
                            return;
                        }
                        break;
                }
                break;

            case ConstNpc.SHENRON_2:
                switch (this.select) {
                    case 0: // +150 ngọc
                        this.playerSummonShenron.inventory.ruby += 300;
                        PlayerService.gI().sendInfoHpMpMoney(this.playerSummonShenron);
                        break;
                    case 1: // +20 tr smtn
                        Service.gI().addSMTN(this.playerSummonShenron, (byte) 2, 20000000, false);
                        break;
                    case 2: // 2 tr vàng
                        if (this.playerSummonShenron.inventory.gold > 1800000000) {
                            this.playerSummonShenron.inventory.gold = Inventory.LIMIT_GOLD;
                        } else {
                            this.playerSummonShenron.inventory.gold += 2000000000;
                        }
                        PlayerService.gI().sendInfoHpMpMoney(this.playerSummonShenron);
                        break;
                }
                break;
            case ConstNpc.SHENRON_3:
                switch (this.select) {
                    case 0: // +15 ngọc
                        this.playerSummonShenron.inventory.ruby += 50;
                        PlayerService.gI().sendInfoHpMpMoney(this.playerSummonShenron);
                        break;
                    case 1: // +2 tr smtn
                        Service.gI().addSMTN(this.playerSummonShenron, (byte) 2, 2000000, false);
                        break;
                    case 2: // 200k vàng
                        if (this.playerSummonShenron.inventory.gold > (2000000000 - 20000000)) {
                            this.playerSummonShenron.inventory.gold = Inventory.LIMIT_GOLD;
                        } else {
                            this.playerSummonShenron.inventory.gold += 20000000;
                        }
                        PlayerService.gI().sendInfoHpMpMoney(this.playerSummonShenron);
                        break;
                }
                break;
            case ConstNpc.SHENRONTRB:
                switch (this.select) {
                    case 0: // +30 sd
                        playerSummonShenron.itemTime.lastTimetrbsd = System.currentTimeMillis();
                        playerSummonShenron.itemTime.istrbsd = true;
                        ItemTimeService.gI().sendAllItemTime(playerSummonShenron);
                        Service.gI().point(playerSummonShenron);
                        break;
                    case 1: // +30hp
                        playerSummonShenron.itemTime.lastTimetrbhp = System.currentTimeMillis();
                        playerSummonShenron.itemTime.istrbhp = true;
                        ItemTimeService.gI().sendAllItemTime(playerSummonShenron);
                        Service.gI().point(playerSummonShenron);
                        break;
                    case 2: // 30ki
                        playerSummonShenron.itemTime.lastTimetrbki = System.currentTimeMillis();
                        playerSummonShenron.itemTime.istrbki = true;
                        ItemTimeService.gI().sendAllItemTime(playerSummonShenron);
                        Service.gI().point(playerSummonShenron);
                        break;
                    case 3: // +150 ngọc
                        playerSummonShenron.itemTime.lastTimetrbcrit = System.currentTimeMillis();
                        playerSummonShenron.itemTime.istrbcrit = true;
                        ItemTimeService.gI().sendAllItemTime(playerSummonShenron);
                        Service.gI().point(playerSummonShenron);
                        break;
                    case 4: // găng tay đang đeo lên 1 cấp
                        Item item = this.playerSummonShenron.inventory.itemsBody.get(2);
                        if (item.isNotNullItem()) {
                            int level = 0;
                            for (ItemOption io : item.itemOptions) {
                                if (io.optionTemplate.id == 5) {
                                    level = io.param;
                                    if (level < 7) {
                                        io.param++;
                                    }
                                    break;
                                }
                            }
                            if (level < 7) {
                                if (level == 0) {
                                    item.itemOptions.add(new ItemOption(5, 1));
                                }
                                for (ItemOption io : item.itemOptions) {
                                    if (io.optionTemplate.id == 0) {
                                        io.param += (io.param * 1 / 100);
                                        break;
                                    }
                                }
                                InventoryServiceNew.gI().sendItemBody(playerSummonShenron);
                            } else {
                                Service.gI().sendThongBao(playerSummonShenron, "Găng tay của ngươi đã đạt cấp tối đa");
                                reOpenShenronWishes(playerSummonShenron);
                                return;
                            }
                        } else {
                            Service.gI().sendThongBao(playerSummonShenron, "Ngươi hiện tại có đeo găng đâu");
                            reOpenShenronWishes(playerSummonShenron);
                            return;
                        }
                        break;
                }
                break;
            // bang
            case ConstNpc.SHENRONBANG:
                switch (this.select) {
                    case 0: // 99 manh ao
                        Item manhAo = ItemService.gI().createNewItem((short) 1066);
                        manhAo.quantity = 20;

                        InventoryServiceNew.gI().addItemBag(playerSummonShenron, manhAo);
                        InventoryServiceNew.gI().sendItemBags(playerSummonShenron);
                        Service.gI().sendThongBao(playerSummonShenron, "|2|Bạn nhận được 20 mảnh áo");

                        break;
                    case 1: // +30hp
                        Item manhQuan = ItemService.gI().createNewItem((short) 1067);
                        manhQuan.quantity = 20;

                        InventoryServiceNew.gI().addItemBag(playerSummonShenron, manhQuan);
                        InventoryServiceNew.gI().sendItemBags(playerSummonShenron);
                        Service.gI().sendThongBao(playerSummonShenron, "|2|Bạn nhận được 20 mảnh quần");
                        break;
                    case 2: // 30ki
                        Item manhGang = ItemService.gI().createNewItem((short) 1070);
                        manhGang.quantity = 20;

                        InventoryServiceNew.gI().addItemBag(playerSummonShenron, manhGang);
                        InventoryServiceNew.gI().sendItemBags(playerSummonShenron);
                        Service.gI().sendThongBao(playerSummonShenron, "|2|Bạn nhận được 20 mảnh găng");
                        break;
                    case 3: // +150 ngọc
                        Item manhNhan = ItemService.gI().createNewItem((short) 1069);
                        manhNhan.quantity = 20;

                        InventoryServiceNew.gI().addItemBag(playerSummonShenron, manhNhan);
                        InventoryServiceNew.gI().sendItemBags(playerSummonShenron);
                        Service.gI().sendThongBao(playerSummonShenron, "|2|Bạn nhận được 20 mảnh nhẫn");
                        break;
                    case 4: // găng tay đang đeo lên 1 cấp
                        Item manhGiay = ItemService.gI().createNewItem((short) 1068);
                        manhGiay.quantity = 20;

                        InventoryServiceNew.gI().addItemBag(playerSummonShenron, manhGiay);
                        InventoryServiceNew.gI().sendItemBags(playerSummonShenron);
                        Service.gI().sendThongBao(playerSummonShenron, "|2|Bạn nhận được 20 mảnh giày");
                        break;

                }
                break;
            case ConstNpc.SHENRONBLACK:
                switch (this.select) {
                    case 0: // +30 sd
                        Item thoiVang = ItemService.gI().createNewItem((short) 457);
                        thoiVang.quantity = 20;

                        InventoryServiceNew.gI().addItemBag(playerSummonShenron, thoiVang);
                        InventoryServiceNew.gI().sendItemBags(playerSummonShenron);
                        Service.gI().sendThongBao(playerSummonShenron, "|2|Bạn nhận được 20 thỏi vàng");
                        break;
                    case 1: // +30hp
                        Item hongNgoc = ItemService.gI().createNewItem((short) 1443);
                        hongNgoc.quantity = 5000;

                        InventoryServiceNew.gI().addItemBag(playerSummonShenron, hongNgoc);
                        InventoryServiceNew.gI().sendItemBags(playerSummonShenron);
                        Service.gI().sendThongBao(playerSummonShenron, "|2|Bạn nhận được 5k hồng ngọc");
                        
                        break;
                    case 2: // 30ki
                        Item daNguSac = ItemService.gI().createNewItem((short) 674);
                        daNguSac.quantity = 20;

                        InventoryServiceNew.gI().addItemBag(playerSummonShenron, daNguSac);
                        InventoryServiceNew.gI().sendItemBags(playerSummonShenron);
                        Service.gI().sendThongBao(playerSummonShenron, "|2|Bạn nhận được 20 đá ngũ sắc");
                        break;
                    case 3: // +150 ngọc
                        Item quaHongDao = ItemService.gI().createNewItem((short) 1183);
                        quaHongDao.quantity = 20;

                        InventoryServiceNew.gI().addItemBag(playerSummonShenron, quaHongDao);
                        InventoryServiceNew.gI().sendItemBags(playerSummonShenron);
                        Service.gI().sendThongBao(playerSummonShenron, "|2|Bạn nhận được 20 quả hồng đào");
                        break;
                    case 4: // găng tay đang đeo lên 1 cấp
                        Item huyHieu = ItemService.gI().createNewItem((short) 1184);
                        huyHieu.quantity = 20;

                        InventoryServiceNew.gI().addItemBag(playerSummonShenron, huyHieu);
                        InventoryServiceNew.gI().sendItemBags(playerSummonShenron);
                        Service.gI().sendThongBao(playerSummonShenron, "|2|Bạn nhận được 20 huy chương");
                        break;
                        
                }
                break;
            case ConstNpc.NAMEC_1:
                if (select == 0) {
                    if (playerSummonShenron.clan != null) {
                        playerSummonShenron.clan.members.forEach(m -> {
                            if (Client.gI().getPlayer(m.id) != null) {
                                Player p = Client.gI().getPlayer(m.id);
                                Item it = ItemService.gI().createNewItem((short) 16);
                                it.quantity = 30;
                                InventoryServiceNew.gI().addItemBag(p, it);
                                InventoryServiceNew.gI().sendItemBags(p);
                            } else {
                                Player p = GodGK.loadById(m.id);
                                if (p != null) {
                                    Item it = ItemService.gI().createNewItem((short) 16);
                                    it.quantity = 30;
                                    InventoryServiceNew.gI().addItemBag(p, it);
                                    PlayerDAO.updatePlayer(p);
                                }
                            }
                        });
                    } else {
                        Item it = ItemService.gI().createNewItem((short) 16);
                        it.quantity = 30;
                        InventoryServiceNew.gI().addItemBag(playerSummonShenron, it);
                        InventoryServiceNew.gI().sendItemBags(playerSummonShenron);
                    }
                }
                break;

        }
        shenronLeave(this.playerSummonShenron, WISHED);
    }

    public void showConfirmShenron(Player pl, int menu, byte select) {
        this.menuShenron = menu;
        this.select = select;
        String wish = null;
        switch (menu) {
            case ConstNpc.SHENRON_1_1:
                wish = SHENRON_1_STAR_WISHES_1[select];
                break;
            case ConstNpc.SHENRON_1_2:
                wish = SHENRON_1_STAR_WISHES_2[select];
                break;
            case ConstNpc.SHENRON_2:
                wish = SHENRON_2_STARS_WHISHES[select];
                break;
            case ConstNpc.SHENRON_3:
                wish = SHENRON_3_STARS_WHISHES[select];
                break;
            case ConstNpc.NAMEC_1:
                wish = "x99 ngọc rồng 3 sao";
                break;
            case ConstNpc.SHENRONTRB:
                wish = SHENRON_1_STAR_TRB[select];
                break;
            case ConstNpc.SHENRONBANG:
                wish = SHENRON_1_STAR_BANG[select];
                break;
            case ConstNpc.SHENRONBLACK:
                wish = SHENRON_1_STAR_BLACK[select];
                break;
        }
        NpcService.gI().createMenuRongThieng(pl, ConstNpc.SHENRON_CONFIRM, "Ngươi có chắc muốn ước?", wish, "Từ chối");
    }

    public void reOpenShenronWishes(Player pl) {
        switch (menuShenron) {
            case ConstNpc.SHENRON_1_1:
                NpcService.gI().createMenuRongThieng(pl, ConstNpc.SHENRON_1_1, SHENRON_SAY, SHENRON_1_STAR_WISHES_1);
                break;
            case ConstNpc.SHENRON_1_2:
                NpcService.gI().createMenuRongThieng(pl, ConstNpc.SHENRON_1_2, SHENRON_SAY, SHENRON_1_STAR_WISHES_2);
                break;
            case ConstNpc.SHENRON_2:
                NpcService.gI().createMenuRongThieng(pl, ConstNpc.SHENRON_2, SHENRON_SAY, SHENRON_2_STARS_WHISHES);
                break;
            case ConstNpc.SHENRON_3:
                NpcService.gI().createMenuRongThieng(pl, ConstNpc.SHENRON_3, SHENRON_SAY, SHENRON_3_STARS_WHISHES);
                break;
        }
    }

    public void shenronLeave(Player pl, byte type) {
        if (type == WISHED) {
            NpcService.gI().createTutorial(pl, -1,
                    "Điều ước của ngươi đã trở thành sự thật\nHẹn gặp ngươi lần sau, ta đi ngủ đây, bái bai");
        } else {
            NpcService.gI().createMenuRongThieng(pl, ConstNpc.IGNORE_MENU,
                    "Ta buồn ngủ quá rồi\nHẹn gặp ngươi lần sau, ta đi đây, bái bai");
        }
        activeShenron(pl, false, SummonDragon.DRAGON_SHENRON);
        this.isShenronAppear = false;
        this.menuShenron = -1;
        this.select = -1;
        this.playerSummonShenron = null;
        this.playerSummonShenronId = -1;
        this.shenronStar = -1;
        this.mapShenronAppear = null;
        lastTimeShenronAppeared = System.currentTimeMillis();
        lastTimebangAppeared = System.currentTimeMillis();
        lastTimeblackAppeared = System.currentTimeMillis();
        lastTimetrbAppeared = System.currentTimeMillis();
    }

    // --------------------------------------------------------------------------
}
