package red.team.boss.list_boss.hungvuong;

import java.util.Random;

import red.s1.boss.Boss;
import red.s1.boss.BossID;
import red.s1.boss.BossStatus;
import red.s1.boss.BossesData;
import red.team.map.ItemMap;
import red.team.player.Player;
import red.team.skill.Skill;
import red.services.PetService;
import red.services.Service;
import red.services.TaskService;
import red.utils.Util;

/**
 *
 * @Stole By Lucy#0800
 */
public class NguaChinLmao extends Boss {

    public NguaChinLmao() throws Exception {
        super(BossID.NGUA_9_LMAO, BossesData.NGUA_9_LMAO);
    }

    @Override
    public void active() {
        super.active(); //To change body of generated methods, choose Tools | Templates.
       if (Util.canDoWithTime(st, 900000)) {
           this.changeStatus(BossStatus.LEAVE_MAP);
       }
       
       
    }
    @Override
    public void reward(Player plKill) {
        plKill.pointBoss += 0;
        for(int i=0;i<=10;i++){
          Service.gI().dropItemMap(this.zone, Util.ratiItem(zone, 1212, 1, this.location.x+i*5, this.location.y, -1));
        }
        TaskService.gI().checkDoneTaskKillBoss(plKill, this);
    }
    @Override
    public void joinMap() {
        super.joinMap(); //To change body of generated methods, choose Tools | Templates.
        st = System.currentTimeMillis();
    }
    private long st;
}

/**
 * Vui lòng không sao chép mã nguồn này dưới mọi hình thức. Hãy tôn trọng tác
 * giả của mã nguồn này. Xin cảm ơn! - Lucy
 */
