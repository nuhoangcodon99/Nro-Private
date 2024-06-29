/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package red.team.boss.list_boss.doanh_trai;

import red.consts.ConstPlayer;
import red.s1.boss.Boss;
import red.s1.boss.BossData;
import red.s1.boss.BossID;
import red.s1.boss.BossManager;
import red.s1.boss.BossStatus;
import red.team.map.ItemMap;
import red.team.map.Zone;
import red.team.player.Player;
import red.team.skill.Skill;
import red.services.EffectSkillService;
import red.services.PlayerService;
import red.services.Service;
import red.services.SkillService;
import red.services.TaskService;
import red.services.func.ChangeMapService;
import red.utils.Logger;
import red.utils.SkillUtil;
import red.utils.Util;

/**
 *
 * @author Khánh Đẹp Zoai
 */
public class TrungUyXanhLo extends TrungUyTrang {

    public TrungUyXanhLo(int dame, int hp, Zone zone) throws Exception {
        super(dame, hp, zone, Util.randomBossId(), TRUNG_UY_XANH_LO);
    }

    private static final BossData TRUNG_UY_XANH_LO = new BossData(
            "Trung úy xanh lơ", //name
            ConstPlayer.TRAI_DAT, //gender
            new short[]{135, 136, 137, -1, -1, -1}, //outfit {head, body, leg, bag, aura, eff}
            500, //dame
            new int[]{500}, //hp
            new int[]{1}, //map join
            new int[][]{
                {Skill.DRAGON, 3, 1000},
                {Skill.KAMEJOKO, 7, 1000},
//                {Skill.THAI_DUONG_HA_SAN, 3, 130000}
            },
            new String[]{}, //text chat 1
            new String[]{"|-1|Tao hơn hẳn mày, mày nên cầu cho may mắn ở phía mày đi",
                "|-1|Ha ha ha! Mắt mày mù à? Nhìn máy đo chỉ số đi!!",
                "|-1|Định chạy trốn hả, hử",
                "|-1|Ta sẽ tàn sát khu này trong vòng 5 phút nữa",
                "|-1|Hahaha mày đây rồi",
                "|-1|Tao đã có lệnh từ đại ca rồi"
            }, //text chat 2
            new String[]{"|-2|Đẹp trai nó phải thế"}, //text chat 3
            5 //second rest
    );

    @Override
    public void reward(Player plKill) {
        if (Util.isTrue(100, 100)) {
            ItemMap it = new ItemMap(this.zone, 611, 1, this.location.x, this.zone.map.yPhysicInTop(this.location.x,
                    this.location.y - 24), plKill.id);
            Service.gI().dropItemMap(this.zone, it);
        }
         plKill.pointBoss += 0;
        TaskService.gI().checkDoneTaskKillBoss(plKill, this);
    }

   @Override
    public int injured(Player plAtt, int damage, boolean piercing, boolean isMobAttack) {
 this.checkAnThan(plAtt);
        if (!this.isDie()) {
            if (!piercing && Util.isTrue(30, 100)) {
                this.chat("Xí hụt lêu lêu");
                return 0;
            }
            damage = this.nPoint.subDameInjureWithDeff(damage);
            if (!piercing && effectSkill.isShielding) {
                if (damage > nPoint.hpMax) {
                    EffectSkillService.gI().breakShield(this);
                }
                damage = 1;
            }
            this.nPoint.subHP(damage);
            if (isDie()) {
                this.setDie(plAtt);
                die(plAtt);
            }
            return damage;
        } else {
            return 0;
        }
    }

    private long lastTimeFindPlayerToChangeFlag;

    @Override
    public void active() {
        super.active(); //To change body of generated methods, choose Tools | Templates.
       this.attack();
       
       
    }

 
  

    @Override
    public void joinMap() {
        if (zoneFinal != null) {
            joinMapByZone(zoneFinal);
//            ChangeMapService.gI().changeMap(this, zoneFinal, 1071, 384);
            this.zone.isTrungUyTrangAlive = true;
        }
    }

    @Override
    public void moveTo(int x, int y) {
        byte dir = (byte) (this.location.x - x < 0 ? 1 : -1);
        byte move = (byte) Util.nextInt(50, 100);
        PlayerService.gI().playerMove(this, this.location.x + (dir == 1 ? move : -move), y + (Util.isTrue(3, 10) ? -25 : 0));
    }
}
