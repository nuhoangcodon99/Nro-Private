package red.jdbc.daos;

import red.card.Card;
import red.team.item.Item;
import red.team.item.ItemTime;
import red.team.player.Friend;
import red.team.player.Fusion;
import red.team.player.Inventory;
import red.team.player.Player;
import red.team.skill.Skill;
import red.team.server.Manager;
import red.services.InventoryServiceNew;
import red.services.ItemTimeService;
import red.services.MapService;
import red.utils.Logger;
import com.girlkun.database.GirlkunDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;


public class PlayerDAO {

    public static boolean createNewPlayer(int userId, String name, byte gender, int hair) {
        try {
            JSONArray dataArray = new JSONArray();

            dataArray.add(2000000000); // vàng
            dataArray.add(200000); // ngọc xanh
            dataArray.add(20000); // hồng ngọc
            dataArray.add(0); // point
            dataArray.add(0); // event

            String inventory = dataArray.toJSONString();
            dataArray.clear();

            dataArray.add(21 + gender); // map
            dataArray.add(100); // x
            dataArray.add(336); // y
            String location = dataArray.toJSONString();
            dataArray.clear();

            dataArray.add(0); // giới hạn sức mạnh
            dataArray.add(2000); // sức mạnh
            dataArray.add(2000); // tiềm năng
            dataArray.add(1000); // thể lực
            dataArray.add(1000); // thể lực đầy
            dataArray.add(gender == 0 ? 200 : 100); // hp gốc
            dataArray.add(gender == 1 ? 200 : 100); // ki gốc
            dataArray.add(gender == 2 ? 15 : 10); // sức đánh gốc
            dataArray.add(0); // giáp gốc
            dataArray.add(0); // chí mạng gốc
            dataArray.add(0); // năng động
            dataArray.add(gender == 0 ? 200 : 100); // hp hiện tại
            dataArray.add(gender == 1 ? 200 : 100); // ki hiện tại
            String point = dataArray.toJSONString();
            dataArray.clear();

            dataArray.add(1); // level
            dataArray.add(5); // curent pea
            dataArray.add(0); // is upgrade
            dataArray.add(new Date().getTime()); // last time harvest
            dataArray.add(new Date().getTime()); // last time upgrade
            String magicTree = dataArray.toJSONString();
            dataArray.clear();
            /**
             *
             * [
             * {"temp_id":"1","option":[[5,7],[7,3]],"create_time":"49238749283748957""},
             * {"temp_id":"1","option":[[5,7],[7,3]],"create_time":"49238749283748957""},
             * {"temp_id":"-1","option":[],"create_time":"0""}, ... ]
             */

            int idAo = gender == 0 ? 0 : gender == 1 ? 1 : 2;
            int idQuan = gender == 0 ? 6 : gender == 1 ? 7 : 8;
            int def = gender == 2 ? 3 : 2;
            int hp = gender == 0 ? 30 : 20;

            JSONArray item = new JSONArray();
            JSONArray options = new JSONArray();
            JSONArray opt = new JSONArray();
            for (int i = 0; i < 16; i++) {
                if (i == 0) { // áo
                    opt.add(47); // id option
                    opt.add(def); // param option
                    item.add(idAo); // id item
                    item.add(1); // số lượng
                    options.add(opt.toJSONString());
                    opt.clear();
                } else if (i == 1) { // quần
                    opt.add(6); // id option
                    opt.add(hp); // param option
                    item.add(idQuan); // id item
                    item.add(1); // số lượng
                    options.add(opt.toJSONString());
                    opt.clear();
                } else {
                    item.add(-1); // id item
                    item.add(0); // số lượng
                }
                item.add(options.toJSONString()); // full option item
                item.add(System.currentTimeMillis()); // thời gian item được tạo
                dataArray.add(item.toJSONString());
                options.clear();
                item.clear();
            }
            String itemsBody = dataArray.toJSONString();
            dataArray.clear();

            for (int i = 0; i < 15; i++) { // item tạo player
                if (i == 0) { // thỏi vàng
                    opt.add(30); // id option cấm giao dịch
                    opt.add(1); // param option
                    item.add(457); // id item
                    item.add(10); // số lượng
                    options.add(opt.toJSONString());
                    opt.clear();
                } else {
                    item.add(-1); // id item
                    item.add(0); // số lượng
                }
                item.add(options.toJSONString()); // full option item
                item.add(System.currentTimeMillis()); // thời gian item được tạo
                dataArray.add(item.toJSONString());
                options.clear();
                item.clear();
            }
            String itemsBag = dataArray.toJSONString();
            dataArray.clear();

            for (int i = 0; i < 15; i++) {
                if (i == 0) { // rada
                    opt.add(0); // id option
                    opt.add(100); // param option
                    item.add(12); // id item
                    item.add(0); // số lượng
                    options.add(opt.toJSONString());
                    opt.clear();
                } else {
                    item.add(-1); // id item
                    item.add(0); // số lượng
                }
                item.add(options.toJSONString()); // full option item
                item.add(System.currentTimeMillis()); // thời gian item được tạo
                dataArray.add(item.toJSONString());
                options.clear();
                item.clear();
            }
            String itemsBox = dataArray.toJSONString();
            dataArray.clear();

            String itemsBoxLuckyRound = dataArray.toJSONString();
            dataArray.clear();

            String friends = dataArray.toJSONString();
            String enemies = dataArray.toJSONString();

            dataArray.add(0); // id nội tại
            dataArray.add(0); // chỉ số 1
            dataArray.add(0); // chỉ số 2
            dataArray.add(0); // số lần mở
            String intrinsic = dataArray.toJSONString();
            dataArray.clear();

            dataArray.add(0); // bổ huyết
            dataArray.add(0); // bổ khí
            dataArray.add(0); // giáp xên
            dataArray.add(0); // cuồng nộ
            dataArray.add(0); // ẩn danh
            dataArray.add(0); // mở giới hạn sức mạnh
            dataArray.add(0); // máy dò
            dataArray.add(0); // máy dò2
            dataArray.add(0); // thức ăn cold
            dataArray.add(0); // icon thức ăn cold
            String itemTime = dataArray.toJSONString();
            dataArray.clear();

            dataArray.add(1); // id nhiệm vụ
            dataArray.add(0); // index nhiệm vụ con
            dataArray.add(0); // số lượng đã làm
            String task = dataArray.toJSONString();
            dataArray.clear();

            String mabuEgg = dataArray.toJSONString();

            dataArray.add(System.currentTimeMillis()); // bùa trí tuệ
            dataArray.add(System.currentTimeMillis()); // bùa mạnh mẽ
            dataArray.add(System.currentTimeMillis()); // bùa da trâu
            dataArray.add(System.currentTimeMillis()); // bùa oai hùng
            dataArray.add(System.currentTimeMillis()); // bùa bất tử
            dataArray.add(System.currentTimeMillis()); // bùa dẻo dai
            dataArray.add(System.currentTimeMillis()); // bùa thu hút
            dataArray.add(System.currentTimeMillis()); // bùa đệ tử
            dataArray.add(System.currentTimeMillis()); // bùa trí tuệ x3
            dataArray.add(System.currentTimeMillis()); // bùa trí tuệ x4
            String charms = dataArray.toJSONString();
            dataArray.clear();

            int[] skillsArr = gender == 0 ? new int[] { 0, 1, 6, 9, 10, 20, 22, 24, 19 }
                    : gender == 1 ? new int[] { 2, 3, 7, 11, 12, 17, 18, 26, 19 }
                            : new int[] { 4, 5, 8, 13, 14, 21, 23, 25, 19 };
            // [{"temp_id":"4","point":0,"last_time_use":0},]

            JSONArray skill = new JSONArray();
            for (int i = 0; i < skillsArr.length; i++) {
                skill.add(skillsArr[i]); // id skill
                if (i == 0) {
                    skill.add(1); // level skill
                } else {
                    skill.add(0); // level skill
                }
                skill.add(0); // thời gian sử dụng trước đó
                dataArray.add(skill.toString());
                skill.clear();
            }
            String skills = dataArray.toJSONString();
            dataArray.clear();

            dataArray.add(gender == 0 ? 0 : gender == 1 ? 2 : 4);
            dataArray.add(-1);
            dataArray.add(-1);
            dataArray.add(-1);
            dataArray.add(-1);
            String skillsShortcut = dataArray.toJSONString();
            dataArray.clear();

            String petData = dataArray.toJSONString();

            JSONArray blackBall = new JSONArray();
            for (int i = 1; i <= 7; i++) {
                blackBall.add(0);
                blackBall.add(0);
                blackBall.add(0);
                dataArray.add(blackBall.toJSONString());
                blackBall.clear();
            }
            String dataBlackBall = dataArray.toString();
            dataArray.clear();

            dataArray.add(-1); // id side task
            dataArray.add(0); // thời gian nhận
            dataArray.add(0); // số lượng đã làm
            dataArray.add(0); // số lượng cần làm
            dataArray.add(20); // số nhiệm vụ còn lại có thể nhận
            dataArray.add(0); // mức độ nhiệm vụ
            String dataSideTask = dataArray.toJSONString();
            dataArray.clear();
            ///
            dataArray.add(-1); // id side task
            dataArray.add(0); // thời gian nhận
            dataArray.add(0); // số lượng đã làm
            dataArray.add(0); // số lượng cần làm
            dataArray.add(20); // số nhiệm vụ còn lại có thể nhận
            dataArray.add(0); // mức độ nhiệm vụ
            String dataClanTask = dataArray.toJSONString();
            dataArray.clear();

            ///
            String data_card = dataArray.toJSONString();
            String bill_data = dataArray.toJSONString();
            String ngokhong_data = dataArray.toJSONString();
            String goku_data = dataArray.toJSONString();
            String thienthan_data = dataArray.toJSONString();
            String hacam_data = dataArray.toJSONString();
            dataArray.clear();

            //
            dataArray.add(0); // bổ huyết
            dataArray.add(0); // bổ khí
            dataArray.add(0); // giáp xên
            dataArray.add(0); // cuồng nộ
            dataArray.add(0); // ẩn danh
            String itemTimeSC = dataArray.toJSONString();
            dataArray.clear();
            // bo mong
            JSONObject achievementObject = new JSONObject();
            achievementObject.put("numPvpWin", 0);
            achievementObject.put("numSkillChuong", 0);
            achievementObject.put("numFly", 0);
            achievementObject.put("numKillMobFly", 0);
            achievementObject.put("numKillNguoiRom", 0);
            achievementObject.put("numHourOnline", 0);
            achievementObject.put("numGivePea", 0);
            achievementObject.put("numSellItem", 0);
            achievementObject.put("numPayMoney", 0);
            achievementObject.put("numKillSieuQuai", 0);
            achievementObject.put("numHoiSinh", 0);
            achievementObject.put("numSkillDacBiet", 0);
            achievementObject.put("numPickGem", 0);

            List<Boolean> list = new ArrayList<>(Arrays.asList(new Boolean[Manager.ACHIEVEMENTS.size()]));
            Collections.fill(list, Boolean.FALSE);
            dataArray.addAll(list);
            achievementObject.put("listReceiveGem", dataArray);
            String info_achive = achievementObject.toJSONString();
            dataArray.clear();
            GirlkunDB.executeUpdate("insert into player"
                    + "(account_id, name, head, gender, have_tennis_space_ship, clan_id_sv" + Manager.SERVER + ", "
                    + "data_inventory, data_location, data_point, data_magic_tree, items_body, "
                    + "items_bag, items_box, items_box_lucky_round, friends, enemies, data_intrinsic, data_item_time,"
                    + "data_task, data_mabu_egg, data_charm, skills, skills_shortcut, pet,"
                    + "data_black_ball, data_side_task,data_clan_task, data_card, bill_data, data_item_time_sieu_cap,data_ngokhong_egg,data_goku_egg,info_achievement,data_thienthan_egg,data_hacam_egg) "
                    + "values ()", userId, name, hair, gender, 0, -1, inventory, location, point, magicTree,
                    itemsBody, itemsBag, itemsBox, itemsBoxLuckyRound, friends, enemies, intrinsic,
                    itemTime, task, mabuEgg, charms, skills, skillsShortcut, petData, dataBlackBall, dataSideTask,
                    dataClanTask,
                    data_card, bill_data, itemTimeSC, ngokhong_data, goku_data, info_achive, thienthan_data,
                    hacam_data);
            Logger.success("Tạo player mới thành công!");
            return true;
        } catch (Exception e) {
            Logger.logException(PlayerDAO.class, e, "Lỗi tạo player mới");
            return false;
        }
    }

    public static void updatePlayer(Player player) {
        if (player.iDMark.isLoadedAllDataPlayer()) {
            long st = System.currentTimeMillis();
            try {
                JSONArray dataArray = new JSONArray();

                // data kim lượng
                dataArray.add(player.inventory.gold > Inventory.LIMIT_GOLD
                        ? Inventory.LIMIT_GOLD
                        : player.inventory.gold);
                dataArray.add(player.inventory.gem);
                dataArray.add(player.inventory.ruby);
                dataArray.add(player.inventory.coupon);
                dataArray.add(player.inventory.event);
                String inventory = dataArray.toJSONString();
                dataArray.clear();

                int mapId = -1;
                mapId = player.mapIdBeforeLogout;
                int x = player.location.x;
                int y = player.location.y;
                int hp = player.nPoint.hp;
                int mp = player.nPoint.mp;
                if (player.isDie()) {
                    mapId = player.gender + 21;
                    x = 300;
                    y = 336;
                    hp = 1;
                    mp = 1;
                } else {
                    if (MapService.gI().isMapDoanhTrai(mapId) || MapService.gI().isMapMiNuong(mapId)
                            || MapService.gI().isMapKhiGaHuyDiet(mapId) || MapService.gI().isMapBlackBallWar(mapId)
                            || MapService.gI().isMapBanDoKhoBau(mapId) || MapService.gI().isMapMaBu(mapId)
                            || MapService.gI().isMapSatan(mapId) || MapService.gI().isMapDiaNguc(mapId)) {
                        mapId = player.gender + 21;
                        x = 300;
                        y = 336;
                    }
                }

                // data vị trí
                dataArray.add(mapId);
                dataArray.add(x);
                dataArray.add(y);
                String location = dataArray.toJSONString();
                dataArray.clear();

                // data chỉ số
                dataArray.add(player.nPoint.limitPower);
                dataArray.add(player.nPoint.power);
                dataArray.add(player.nPoint.tiemNang);
                dataArray.add(player.nPoint.stamina);
                dataArray.add(player.nPoint.maxStamina);
                dataArray.add(player.nPoint.hpg);
                dataArray.add(player.nPoint.mpg);
                dataArray.add(player.nPoint.dameg);
                dataArray.add(player.nPoint.defg);
                dataArray.add(player.nPoint.critg);
                dataArray.add(player.numKillSieuHang);
                dataArray.add(player.rankSieuHang);
                dataArray.add(0);
                dataArray.add(hp);
                dataArray.add(mp);
                String point = dataArray.toJSONString();
                dataArray.clear();

                // data đậu thần
                dataArray.add(player.magicTree.level);
                dataArray.add(player.magicTree.currPeas);
                dataArray.add(player.magicTree.isUpgrade ? 1 : 0);
                dataArray.add(player.magicTree.lastTimeHarvest);
                dataArray.add(player.magicTree.lastTimeUpgrade);
                String magicTree = dataArray.toJSONString();
                dataArray.clear();

                // data body
                JSONArray dataItem = new JSONArray();
                for (Item item : player.inventory.itemsBody) {
                    JSONArray opt = new JSONArray();
                    if (item.isNotNullItem()) {
                        dataItem.add(item.template.id);
                        dataItem.add(item.quantity);
                        JSONArray options = new JSONArray();
                        for (Item.ItemOption io : item.itemOptions) {
                            opt.add(io.optionTemplate.id);
                            opt.add(io.param);
                            options.add(opt.toJSONString());
                            opt.clear();
                        }
                        dataItem.add(options.toJSONString());
                    } else {
                        dataItem.add(-1);
                        dataItem.add(0);
                        dataItem.add(opt.toJSONString());
                    }
                    dataItem.add(item.createTime);
                    dataArray.add(dataItem.toJSONString());
                    dataItem.clear();
                }
                String itemsBody = dataArray.toJSONString();
                dataArray.clear();

                // data bag
                for (Item item : player.inventory.itemsBag) {
                    JSONArray opt = new JSONArray();
                    if (item.isNotNullItem()) {
                        dataItem.add(item.template.id);
                        dataItem.add(item.quantity);
                        JSONArray options = new JSONArray();
                        for (Item.ItemOption io : item.itemOptions) {
                            opt.add(io.optionTemplate.id);
                            opt.add(io.param);
                            options.add(opt.toJSONString());
                            opt.clear();
                        }
                        dataItem.add(options.toJSONString());
                    } else {
                        dataItem.add(-1);
                        dataItem.add(0);
                        dataItem.add(opt.toJSONString());
                    }
                    dataItem.add(item.createTime);
                    dataArray.add(dataItem.toJSONString());
                    dataItem.clear();
                }
                String itemsBag = dataArray.toJSONString();
                dataArray.clear();

                // data card
                // data box
                for (Item item : player.inventory.itemsBox) {
                    JSONArray opt = new JSONArray();
                    if (item.isNotNullItem()) {
                        dataItem.add(item.template.id);
                        dataItem.add(item.quantity);
                        JSONArray options = new JSONArray();
                        for (Item.ItemOption io : item.itemOptions) {
                            opt.add(io.optionTemplate.id);
                            opt.add(io.param);
                            options.add(opt.toJSONString());
                            opt.clear();
                        }
                        dataItem.add(options.toJSONString());
                    } else {
                        dataItem.add(-1);
                        dataItem.add(0);
                        dataItem.add(opt.toJSONString());
                    }
                    dataItem.add(item.createTime);
                    dataArray.add(dataItem.toJSONString());
                    dataItem.clear();
                }
                String itemsBox = dataArray.toJSONString();
                dataArray.clear();

                // data box crack ball
                for (Item item : player.inventory.itemsBoxCrackBall) {
                    JSONArray opt = new JSONArray();
                    if (item.isNotNullItem()) {
                        dataItem.add(item.template.id);
                        dataItem.add(item.quantity);
                        JSONArray options = new JSONArray();
                        for (Item.ItemOption io : item.itemOptions) {
                            opt.add(io.optionTemplate.id);
                            opt.add(io.param);
                            options.add(opt.toJSONString());
                            opt.clear();
                        }
                        dataItem.add(options.toJSONString());
                    } else {
                        dataItem.add(-1);
                        dataItem.add(0);
                        dataItem.add(opt.toJSONString());
                    }
                    dataItem.add(item.createTime);
                    dataArray.add(dataItem.toJSONString());
                    dataItem.clear();
                }
                String itemsBoxLuckyRound = dataArray.toJSONString();
                dataArray.clear();

                // data bạn bè
                JSONArray dataFE = new JSONArray();
                for (Friend f : player.friends) {
                    dataFE.add(f.id);
                    dataFE.add(f.name);
                    dataFE.add(f.head);
                    dataFE.add(f.body);
                    dataFE.add(f.leg);
                    dataFE.add(f.bag);
                    dataFE.add(f.power);
                    dataArray.add(dataFE.toJSONString());
                    dataFE.clear();
                }
                String friend = dataArray.toJSONString();
                dataArray.clear();

                // data kẻ thù
                for (Friend e : player.enemies) {
                    dataFE.add(e.id);
                    dataFE.add(e.name);
                    dataFE.add(e.head);
                    dataFE.add(e.body);
                    dataFE.add(e.leg);
                    dataFE.add(e.bag);
                    dataFE.add(e.power);
                    dataArray.add(dataFE.toJSONString());
                    dataFE.clear();
                }
                String enemy = dataArray.toJSONString();
                dataArray.clear();

                // data nội tại
                dataArray.add(player.playerIntrinsic.intrinsic.id);
                dataArray.add(player.playerIntrinsic.intrinsic.param1);
                dataArray.add(player.playerIntrinsic.intrinsic.param2);
                dataArray.add(player.playerIntrinsic.countOpen);
                String intrinsic = dataArray.toJSONString();
                dataArray.clear();

                // data item time
                dataArray.add((player.itemTime.isUseBoHuyet
                        ? (ItemTime.TIME_ITEM - (System.currentTimeMillis() - player.itemTime.lastTimeBoHuyet))
                        : 0));
                dataArray.add((player.itemTime.isUseBoKhi
                        ? (ItemTime.TIME_ITEM - (System.currentTimeMillis() - player.itemTime.lastTimeBoKhi))
                        : 0));
                dataArray.add((player.itemTime.isUseGiapXen
                        ? (ItemTime.TIME_ITEM - (System.currentTimeMillis() - player.itemTime.lastTimeGiapXen))
                        : 0));
                dataArray.add((player.itemTime.isUseCuongNo
                        ? (ItemTime.TIME_ITEM - (System.currentTimeMillis() - player.itemTime.lastTimeCuongNo))
                        : 0));
                dataArray.add((player.itemTime.isUseAnDanh
                        ? (ItemTime.TIME_ITEM - (System.currentTimeMillis() - player.itemTime.lastTimeAnDanh))
                        : 0));
                dataArray.add((player.itemTime.isOpenPower
                        ? (ItemTime.TIME_OPEN_POWER - (System.currentTimeMillis() - player.itemTime.lastTimeOpenPower))
                        : 0));
                dataArray.add((player.itemTime.isUseMayDo
                        ? (ItemTime.TIME_MAY_DO - (System.currentTimeMillis() - player.itemTime.lastTimeUseMayDo))
                        : 0));
                dataArray.add((player.itemTime.isUseMayDo2
                        ? (ItemTime.TIME_MAY_DO - (System.currentTimeMillis() - player.itemTime.lastTimeUseMayDo2))
                        : 0));
                dataArray.add((player.itemTime.isEatMeal
                        ? (ItemTime.TIME_EAT_MEAL - (System.currentTimeMillis() - player.itemTime.lastTimeEatMeal))
                        : 0));
                dataArray.add(player.itemTime.iconMeal);
                dataArray.add((player.itemTime.isUseTDLT
                        ? ((player.itemTime.timeTDLT - (System.currentTimeMillis() - player.itemTime.lastTimeUseTDLT))
                                / 60 / 1000)
                        : 0));

                String itemTime = dataArray.toJSONString();
                dataArray.clear();

                // data nhiệm vụ
                dataArray.add(player.playerTask.taskMain.id);
                dataArray.add(player.playerTask.taskMain.index);
                dataArray.add(player.playerTask.taskMain.subTasks.get(player.playerTask.taskMain.index).count);
                String task = dataArray.toJSONString();
                dataArray.clear();

                // data nhiệm vụ hàng ngày
                dataArray
                        .add(player.playerTask.sideTask.template != null ? player.playerTask.sideTask.template.id : -1);
                dataArray.add(player.playerTask.sideTask.receivedTime);
                dataArray.add(player.playerTask.sideTask.count);
                dataArray.add(player.playerTask.sideTask.maxCount);
                dataArray.add(player.playerTask.sideTask.leftTask);
                dataArray.add(player.playerTask.sideTask.level);
                String sideTask = dataArray.toJSONString();
                dataArray.clear();
                // data clan
                dataArray
                        .add(player.playerTask.clanTask.template != null ? player.playerTask.clanTask.template.id : -1);
                dataArray.add(player.playerTask.clanTask.receivedTime);
                dataArray.add(player.playerTask.clanTask.count);
                dataArray.add(player.playerTask.clanTask.maxCount);
                dataArray.add(player.playerTask.clanTask.leftTask);
                dataArray.add(player.playerTask.clanTask.level);
                String clanTask = dataArray.toJSONString();
                dataArray.clear();
                // data trứng bư
                if (player.mabuEgg != null) {
                    dataArray.add(player.mabuEgg.lastTimeCreate);
                    dataArray.add(player.mabuEgg.timeDone);
                }
                String mabuEgg = dataArray.toJSONString();
                dataArray.clear();

                // data trứng bill
                if (player.billEgg != null) {
                    dataArray.add(player.billEgg.lastTimeCreate);
                    dataArray.add(player.billEgg.timeDone);
                }
                String billEgg = dataArray.toJSONString();
                dataArray.clear();
                // data trứng ngokhong
                if (player.ngokhongEgg != null) {
                    dataArray.add(player.ngokhongEgg.lastTimeCreate);
                    dataArray.add(player.ngokhongEgg.timeDone);
                }
                String ngokhongEgg = dataArray.toJSONString();
                dataArray.clear();
                // data trứng goku
                if (player.gokuEgg != null) {
                    dataArray.add(player.gokuEgg.lastTimeCreate);
                    dataArray.add(player.gokuEgg.timeDone);
                }
                String gokuEgg = dataArray.toJSONString();
                dataArray.clear();
                // data trứng thien than
                if (player.thienThanEgg != null) {
                    dataArray.add(player.thienThanEgg.lastTimeCreate);
                    dataArray.add(player.thienThanEgg.timeDone);
                }
                String thienThanEgg = dataArray.toJSONString();
                dataArray.clear();
                // data trứng hac am
                if (player.hacAmEgg != null) {
                    dataArray.add(player.hacAmEgg.lastTimeCreate);
                    dataArray.add(player.hacAmEgg.timeDone);
                }
                String hacAmEgg = dataArray.toJSONString();
                dataArray.clear();
                // data bùa
                dataArray.add(player.charms.tdTriTue);
                dataArray.add(player.charms.tdManhMe);
                dataArray.add(player.charms.tdDaTrau);
                dataArray.add(player.charms.tdOaiHung);
                dataArray.add(player.charms.tdBatTu);
                dataArray.add(player.charms.tdDeoDai);
                dataArray.add(player.charms.tdThuHut);
                dataArray.add(player.charms.tdDeTu);
                dataArray.add(player.charms.tdTriTue3);
                dataArray.add(player.charms.tdTriTue4);
                String charm = dataArray.toJSONString();
                dataArray.clear();

                // data skill
                JSONArray dataSkill = new JSONArray();
                for (Skill skill : player.playerSkill.skills) {
                    dataSkill.add(skill.template.id);
                    dataSkill.add(skill.point);
                    dataSkill.add(skill.lastTimeUseThisSkill);
                    dataSkill.add(skill.currLevel);
                    dataArray.add(dataSkill.toJSONString());
                    dataSkill.clear();
                }
                String skills = dataArray.toJSONString();
                dataArray.clear();
                dataArray.clear();

                // data skill shortcut
                for (int skillId : player.playerSkill.skillShortCut) {
                    dataArray.add(skillId);
                }
                String skillShortcut = dataArray.toJSONString();
                dataArray.clear();

                String pet = dataArray.toJSONString();
                String petInfo = dataArray.toJSONString();
                String petPoint = dataArray.toJSONString();
                String petBody = dataArray.toJSONString();
                String petSkill = dataArray.toJSONString();

                // data pet
                if (player.pet != null) {
                    dataArray.add(player.pet.typePet);
                    dataArray.add(player.pet.gender);
                    dataArray.add(player.pet.name);
                    dataArray.add(player.fusion.typeFusion);
                    int timeLeftFusion = (int) (Fusion.TIME_FUSION
                            - (System.currentTimeMillis() - player.fusion.lastTimeFusion));
                    dataArray.add(timeLeftFusion < 0 ? 0 : timeLeftFusion);
                    dataArray.add(player.pet.status);

                    petInfo = dataArray.toJSONString();
                    dataArray.clear();

                    dataArray.add(player.pet.nPoint.limitPower);
                    dataArray.add(player.pet.nPoint.power);
                    dataArray.add(player.pet.nPoint.tiemNang);
                    dataArray.add(player.pet.nPoint.stamina);
                    dataArray.add(player.pet.nPoint.maxStamina);
                    dataArray.add(player.pet.nPoint.hpg);
                    dataArray.add(player.pet.nPoint.mpg);
                    dataArray.add(player.pet.nPoint.dameg);
                    dataArray.add(player.pet.nPoint.defg);
                    dataArray.add(player.pet.nPoint.critg);
                    dataArray.add(player.pet.nPoint.hp);
                    dataArray.add(player.pet.nPoint.mp);
                    petPoint = dataArray.toJSONString();
                    dataArray.clear();

                    JSONArray items = new JSONArray();
                    JSONArray options = new JSONArray();
                    JSONArray opt = new JSONArray();
                    for (Item item : player.pet.inventory.itemsBody) {
                        if (item.isNotNullItem()) {
                            dataItem.add(item.template.id);
                            dataItem.add(item.quantity);
                            for (Item.ItemOption io : item.itemOptions) {
                                opt.add(io.optionTemplate.id);
                                opt.add(io.param);
                                options.add(opt.toJSONString());
                                opt.clear();
                            }
                            dataItem.add(options.toJSONString());
                        } else {
                            dataItem.add(-1);
                            dataItem.add(0);
                            dataItem.add(options.toJSONString());
                        }

                        dataItem.add(item.createTime);

                        items.add(dataItem.toJSONString());
                        dataItem.clear();
                        options.clear();
                    }
                    petBody = items.toJSONString();

                    JSONArray petSkills = new JSONArray();
                    for (Skill s : player.pet.playerSkill.skills) {
                        JSONArray pskill = new JSONArray();
                        if (s.skillId != -1) {
                            pskill.add(s.template.id);
                            pskill.add(s.point);
                        } else {
                            pskill.add(-1);
                            pskill.add(0);
                        }
                        petSkills.add(pskill.toJSONString());
                    }
                    petSkill = petSkills.toJSONString();

                    dataArray.add(petInfo);
                    dataArray.add(petPoint);
                    dataArray.add(petBody);
                    dataArray.add(petSkill);
                    pet = dataArray.toJSONString();
                }
                dataArray.clear();

                // data thưởng ngọc rồng đen
                for (int i = 0; i < player.rewardBlackBall.timeOutOfDateReward.length; i++) {
                    JSONArray dataBlackBall = new JSONArray();
                    dataBlackBall.add(player.rewardBlackBall.timeOutOfDateReward[i]);
                    dataBlackBall.add(player.rewardBlackBall.lastTimeGetReward[i]);
                    dataBlackBall.add(player.rewardBlackBall.quantilyBlackBall[i]);
                    dataArray.add(dataBlackBall.toJSONString());
                    dataBlackBall.clear();
                }
                String dataBlackBall = dataArray.toJSONString();
                dataArray.clear();

                dataArray.add((player.itemTime.isUseBoHuyetSC
                        ? (ItemTime.TIME_ITEM - (System.currentTimeMillis() - player.itemTime.lastTimeBoHuyetSC))
                        : 0));
                dataArray.add((player.itemTime.isUseBoKhiSC
                        ? (ItemTime.TIME_ITEM - (System.currentTimeMillis() - player.itemTime.lastTimeBoKhiSC))
                        : 0));
                dataArray.add((player.itemTime.isUseGiapXenSC
                        ? (ItemTime.TIME_ITEM - (System.currentTimeMillis() - player.itemTime.lastTimeGiapXenSC))
                        : 0));
                dataArray.add((player.itemTime.isUseCuongNoSC
                        ? (ItemTime.TIME_ITEM - (System.currentTimeMillis() - player.itemTime.lastTimeCuongNoSC))
                        : 0));
                dataArray.add((player.itemTime.isUseAnDanhSC
                        ? (ItemTime.TIME_ITEM - (System.currentTimeMillis() - player.itemTime.lastTimeAnDanhSC))
                        : 0));

                String itemTimeSC = dataArray.toJSONString();
                dataArray.clear();
                //
                JSONObject achievementObject = new JSONObject();
                achievementObject.put("numPvpWin", player.achievement.numPvpWin);
                achievementObject.put("numSkillChuong", player.achievement.numSkillChuong);
                achievementObject.put("numFly", player.achievement.numFly);
                achievementObject.put("numKillMobFly", player.achievement.numKillMobFly);
                achievementObject.put("numKillNguoiRom", player.achievement.numKillNguoiRom);
                achievementObject.put("numHourOnline", player.achievement.numHourOnline);
                achievementObject.put("numGivePea", player.achievement.numGivePea);
                achievementObject.put("numSellItem", player.achievement.numSellItem);
                achievementObject.put("numPayMoney", player.achievement.numPayMoney);
                achievementObject.put("numKillSieuQuai", player.achievement.numKillSieuQuai);
                achievementObject.put("numHoiSinh", player.achievement.numHoiSinh);
                achievementObject.put("numSkillDacBiet", player.achievement.numSkillDacBiet);
                achievementObject.put("numPickGem", player.achievement.numPickGem);

                dataArray.addAll(player.achievement.listReceiveGem);
                achievementObject.put("listReceiveGem", dataArray);
                String info_achive = achievementObject.toJSONString();
                // System.out.println("info "+info_achive);
                String query = "UPDATE player SET data_item_time_sieu_cap = ?, head = ?, have_tennis_space_ship = ?,"
                        + " clan_id_sv" + Manager.SERVER
                        + " = ?, data_inventory = ?, data_location = ?, data_point = ?, data_magic_tree = ?,"
                        + " items_body = ?, items_bag = ?, items_box = ?, items_box_lucky_round = ?, friends = ?,"
                        + " enemies = ?, data_intrinsic = ?, data_item_time = ?, data_task = ?, data_mabu_egg = ?, pet = ?,"
                        + " data_black_ball = ?, data_side_task = ?,data_clan_task =?, data_charm = ?, skills = ?,"
                        + " skills_shortcut = ?, pointPvp = ?, NguHanhSonPoint = ?, data_card = ?, bill_data = ?, data_goku_egg = ?, data_ngokhong_egg = ?,info_achievement = ?,pointBoss=?,data_thienthan_egg =?,data_hacam_egg=?,pointKarin = ?,pointNroNamec = ?,capChuyenSinh = ?,last_time_dd=?,count_open_box = ?,point_su_kien = ?,lvlThienSu =?,point_cau_ca=? WHERE id = ?";

                GirlkunDB.executeUpdate(query,
                        itemTimeSC,
                        player.head,
                        player.haveTennisSpaceShip,
                        (player.clan != null ? player.clan.id : -1),
                        inventory,
                        location,
                        point,
                        magicTree,
                        itemsBody,
                        itemsBag,
                        itemsBox,
                        itemsBoxLuckyRound,
                        friend,
                        enemy,
                        intrinsic,
                        itemTime,
                        task,
                        mabuEgg,
                        pet,
                        dataBlackBall,
                        sideTask,
                        clanTask,
                        charm,
                        skills,
                        skillShortcut,
                        player.pointPvp,
                        player.NguHanhSonPoint,
                        JSONValue.toJSONString(player.Cards),
                        billEgg,
                        gokuEgg,
                        ngokhongEgg,
                        info_achive,
                        player.pointBoss,
                        thienThanEgg,
                        hacAmEgg,
                        player.pointKarin,
                        player.pointNroNamec,
                        player.capChuyenSinh,
                        player.last_time_dd,
                        player.countOpenBox,
                        player.pointSukien,
                        player.lvlThienSu,
                        player.pointCauCa,
                        player.id);
                // Logger.success("Total time save player " + player.name + " thành công! "
                // + (System.currentTimeMillis() - st) + "\n");
            } catch (Exception e) {
                Logger.logException(PlayerDAO.class, e, "Lỗi save player " + player.name);
            }
        }
    }

    public static boolean subGoldBar(Player player, int num) {
        PreparedStatement ps = null;
        try (Connection con = GirlkunDB.getConnection();) {
            ps = con.prepareStatement("update account set thoi_vang = (thoi_vang - ?), active = ? where id = ?");
            ps.setInt(1, num);
            ps.setInt(2, player.getSession().actived ? 1 : 0);
            ps.setInt(3, player.getSession().userId);
            ps.executeUpdate();
            ps.close();
            player.getSession().goldBar -= num;
        } catch (Exception e) {
            Logger.logException(PlayerDAO.class, e, "Lỗi update thỏi vàng " + player.name);
            return false;
        } finally {
        }
        return false;
    }

    public static boolean subcoinBar(Player player, int num) {
        PreparedStatement ps = null;
        try (Connection con = GirlkunDB.getConnection();) {
            ps = con.prepareStatement("update account set coin = (coin - ?), active = ? where id = ?");
            ps.setInt(1, num);
            ps.setInt(2, player.getSession().actived ? 1 : 0);
            ps.setInt(3, player.getSession().userId);
            ps.executeUpdate();
            ps.close();
            player.getSession().coinBar -= num;
        } catch (Exception e) {
            Logger.logException(PlayerDAO.class, e, "Lỗi update Coin " + player.name);
            return false;
        } finally {
        }
        if (num > 1000) {
            insertHistoryGold(player, num);
        }
        return true;
    }

    public static boolean setIs_gift_box(Player player) {
        PreparedStatement ps = null;
        try (Connection con = GirlkunDB.getConnection();) {
            ps = con.prepareStatement("update account set is_gift_box = 0 where id = ?");
            ps.setInt(1, player.getSession().userId);
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            Logger.logException(PlayerDAO.class, e, "Lỗi update new_reg " + player.name);
            return false;
        }
        return true;
    }

    public static void addHistoryReceiveGoldBar(Player player, int goldBefore, int goldAfter,
            int goldBagBefore, int goldBagAfter, int goldBoxBefore, int goldBoxAfter) {
        PreparedStatement ps = null;
        try (Connection con = GirlkunDB.getConnection();) {
            ps = con.prepareStatement("insert into history_receive_goldbar(player_id,player_name,gold_before_receive,"
                    + "gold_after_receive,gold_bag_before,gold_bag_after,gold_box_before,gold_box_after) values (?,?,?,?,?,?,?,?)");
            ps.setInt(1, (int) player.id);
            ps.setString(2, player.name);
            ps.setInt(3, goldBefore);
            ps.setInt(4, goldAfter);
            ps.setInt(5, goldBagBefore);
            ps.setInt(6, goldBagAfter);
            ps.setInt(7, goldBoxBefore);
            ps.setInt(8, goldBoxAfter);
            ps.executeUpdate();
        } catch (Exception e) {
            Logger.logException(PlayerDAO.class, e, "Lỗi update thỏi vàng " + player.name);
        } finally {
            try {
                ps.close();
            } catch (Exception e) {
            }
        }
    }

    public static void updateItemReward(Player player) {
        String dataItemReward = "";
        for (Item item : player.getSession().itemsReward) {
            if (item.isNotNullItem()) {
                dataItemReward += "{" + item.template.id + ":" + item.quantity;
                if (!item.itemOptions.isEmpty()) {
                    dataItemReward += "|";
                    for (Item.ItemOption io : item.itemOptions) {
                        dataItemReward += "[" + io.optionTemplate.id + ":" + io.param + "],";
                    }
                    dataItemReward = dataItemReward.substring(0, dataItemReward.length() - 1) + "};";
                }
            }
        }
        PreparedStatement ps = null;
        ResultSet rs = null;
        try (Connection con = GirlkunDB.getConnection();) {
            ps = con.prepareStatement("update account set reward = ? where id = ?");
            ps.setString(1, dataItemReward);
            ps.setInt(2, player.getSession().userId);
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            Logger.logException(PlayerDAO.class, e, "Lỗi update phần thưởng " + player.name);
        } finally {
            try {
                ps.close();
            } catch (Exception e) {
            }
        }
    }

    public static boolean insertHistoryGold(Player player, int quantily) {
        PreparedStatement ps = null;
        try (Connection con = GirlkunDB.getConnection();) {
            ps = con.prepareStatement("insert into history_gold(name,gold) values (?,?)");
            ps.setString(1, player.name);
            ps.setInt(2, quantily);
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            Logger.logException(PlayerDAO.class, e, "Lỗi insert history_gold " + player.name);
            return false;
        }
        return true;
    }

    public static boolean checkLogout(Connection con, Player player) {
        long lastTimeLogout = 0;
        long lastTimeLogin = 0;
        try {
            PreparedStatement ps = con.prepareStatement("select * from account where id = ? limit 1");
            ps.setInt(1, player.getSession().userId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lastTimeLogout = rs.getTimestamp("last_time_logout").getTime();
                lastTimeLogin = rs.getTimestamp("last_time_login").getTime();
            }
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException ex) {
            }
        } catch (Exception e) {
            return false;
        }
        return lastTimeLogout > lastTimeLogin;
    }

    public static void LogNapTIen(String uid, String menhgia, String seri, String code, String tranid) {
        String UPDATE_PASS = "INSERT INTO naptien(uid,sotien,seri,code,loaithe,time,noidung,tinhtrang,tranid,magioithieu) VALUES(?,?,?,?,?,?,?,?,?,?)";
        try {
            Connection conn = GirlkunDB.getConnection();
            PreparedStatement ps = null;
            // UPDATE NRSD,
            ps = conn.prepareStatement(UPDATE_PASS);
            conn.setAutoCommit(false);
            // NGOC RONG SAO DEN
            ps.setString(1, uid);
            ps.setString(2, menhgia);
            ps.setString(3, seri);
            ps.setString(4, code);

            ps.setString(5, "VIETTEL");
            ps.setString(6, "123123123123");
            ps.setString(7, "dang nap the");
            ps.setString(8, "0");
            ps.setString(9, tranid);
            ps.setString(10, "0");
            if (ps.executeUpdate() == 1) {
            }

            conn.commit();
            // UPDATE NRSD
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            Logger.logException(Manager.class, e, "Lỗi player dao nap tien");
        }
    }

    public static void nhanthuong1(Player player) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}