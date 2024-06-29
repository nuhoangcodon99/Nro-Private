package red.team.npc;

import java.util.ArrayList;
import java.util.List;

import red.consts.ConstNpc;
import red.consts.ConstTask;
import red.team.player.Player;
import red.team.server.Manager;
import red.services.TaskService;

public class NpcManager {

    public static Npc getByIdAndMap(int id, int mapId) {
        for (Npc npc : Manager.NPCS) {
            if (npc.tempId == id && npc.mapId == mapId) {
                return npc;
            }
        }
        return null;
    }

    public static Npc getNpc(byte tempId) {
        for (Npc npc : Manager.NPCS) {
            if (npc.tempId == tempId) {
                return npc;
            }
        }
        return null;
    }

    public static List<Npc> getNpcsByMapPlayer(Player player) {
        List<Npc> list = new ArrayList<>();
        if (player.zone != null) {
            for (Npc npc : player.zone.map.npcs) {
                if (npc.tempId == ConstNpc.QUA_TRUNG && player.mabuEgg == null && player.zone.map.mapId == (21 + player.gender)) {
                    continue;
                } else if (npc.tempId == ConstNpc.QUA_TRUNG_BILL && player.billEgg == null && player.zone.map.mapId == 7) {
                    continue;
                }else if (npc.tempId == ConstNpc.QUA_TRUNG_BILL && player.billEgg == null && player.zone.map.mapId == 154) {
                    continue;
                }else if (npc.tempId == ConstNpc.QUA_TRUNG_NGOKHONG && player.ngokhongEgg == null && player.zone.map.mapId == 0) {
                    continue;
                }else if (npc.tempId == ConstNpc.QUA_TRUNG_GOKU && player.gokuEgg == null && player.zone.map.mapId == 14) {
                    continue;
                }else if (npc.tempId == ConstNpc.QUA_TRUNG_THIEN_THAN && player.thienThanEgg == null && player.zone.map.mapId == 104) {
                    continue;
                }else if (npc.tempId == ConstNpc.QUA_TRUNG_HAC_AM && player.hacAmEgg == null && player.zone.map.mapId == 104) {
                    continue;
                }
                 else if (npc.tempId == ConstNpc.CALICK && TaskService.gI().getIdTask(player) < ConstTask.TASK_20_0) {
                    continue;
                }
                list.add(npc);
            }
        }
        return list;
    }
}
