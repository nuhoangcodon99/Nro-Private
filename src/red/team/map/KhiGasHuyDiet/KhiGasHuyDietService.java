package red.team.map.KhiGasHuyDiet;

import red.team.item.Item;
import red.team.boss.list_boss.KhiGasHuyDiet.DrLychee;
import red.team.boss.list_boss.KhiGasHuyDiet.Hatchiyack;
import red.team.map.KhiGasHuyDiet.KhiGasHuyDiet;
import static red.team.map.KhiGasHuyDiet.KhiGasHuyDiet.TIME_KHI_GA_HUY_DIET;
import red.team.map.Zone;
import red.team.mob.Mob;
import red.team.player.Player;
import red.services.InventoryServiceNew;
import red.services.MapService;
import red.services.Service;
import red.services.func.ChangeMapService;
import red.utils.Logger;
import red.utils.Util;
import java.util.List;

/**
 *
 * @author BTH
 *
 */
public class KhiGasHuyDietService {

    private static KhiGasHuyDietService i;

    private KhiGasHuyDietService() {

    }

    public static KhiGasHuyDietService gI() {
        if (i == null) {
            i = new KhiGasHuyDietService();
        }
        return i;
    }
    
    public void openKhiGaHuyDiet(Player player, byte level) {
        if (level >= 1 && level <= 110) {
            if (player.clan != null && player.clan.KhiGaHuyDiet == null) {
                Item item = InventoryServiceNew.gI().findItemBag(player, 14);
                Item item2 = InventoryServiceNew.gI().findItemBag(player, 611);

                if (item != null && item2 != null && item.quantity > 0 && item2.quantity > 0) {
                
                    KhiGasHuyDiet khiGaHuyDiet = null;
                    for (KhiGasHuyDiet kghd : KhiGasHuyDiet.KHI_GA_HUY_DIETS) {
                        if (!kghd.isOpened) {
                            khiGaHuyDiet = kghd;
                            break;
                        }
                    }
                    if (khiGaHuyDiet != null) {
                        InventoryServiceNew.gI().subQuantityItemsBag(player, item, 1);
                        InventoryServiceNew.gI().sendItemBags(player);
                    
                        khiGaHuyDiet.openKhiGaHuyDiet(player, player.clan, level);
                        try {
                            long bossDamage = (20 * level);
                            long bossMaxHealth = (2 * level);
                            bossDamage = Math.min(bossDamage, 200000000L);
                            bossMaxHealth = Math.min(bossMaxHealth, 2000000000L);
                            DrLychee boss = new DrLychee(
                                    player.clan.KhiGaHuyDiet.getMapById(188),
                                    player.clan.KhiGaHuyDiet.level,
                                    
                                    (int) bossDamage,
                                    (int) bossMaxHealth
                            );
                            Hatchiyack boss2 = new Hatchiyack(
                                    player.clan.KhiGaHuyDiet.getMapById(188),
                                    player.clan.KhiGaHuyDiet.level,
                                    
                                    (int) bossDamage,
                                    (int) bossMaxHealth
                            );
                        } catch (Exception exception) {
                            Logger.logException(KhiGasHuyDietService.class, exception, "Error initializing boss");
                        }
                    } else {
                        Service.getInstance().sendThongBao(player, "Khí gas đang đầy, cút đi chỗ khác mà chơi..");
                    }
                } else {
                    Service.getInstance().sendThongBao(player, "Cần 1 bản đồ và 1 viên 1s ??");
                }
            } else {
                Service.getInstance().sendThongBao(player, "Không thể thực hiện");
            }
        } else {
            Service.getInstance().sendThongBao(player, "Không thể thực hiện");
        }
    }
}

