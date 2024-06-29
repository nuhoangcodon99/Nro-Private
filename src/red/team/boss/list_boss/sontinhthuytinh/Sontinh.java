/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package red.team.boss.list_boss.sontinhthuytinh;

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
import red.utils.Logger;
import red.utils.SkillUtil;
import red.utils.Util;

/**
 *
 * @author Khánh Đẹp Zoai
 */
public class Sontinh extends Boss {

    private int dameClan;
    private int hpClan;

    public Sontinh(int dame, int hp, Zone zone) throws Exception {
        super(Util.randomBossId(), Sontinh.SON_TINH);
        this.dameClan = dame;
        this.hpClan = hp;
        this.zoneFinal = zone;
    }

    public Sontinh(int dame, int hp, Zone zone, int id, BossData... data) throws Exception {
        super(id, data);
        this.dameClan = dame;
        this.hpClan = hp;
        this.zoneFinal = zone;
    }

    private static final BossData SON_TINH = new BossData(
            "Sơn tinh", //name
            ConstPlayer.TRAI_DAT, //gender
            new short[]{314, 315, 316, -1, -1, -1}, //outfit {head, body, leg, bag, aura, eff}
            500, //dame
            new int[]{500}, //hp
            new int[]{1}, //map join
            new int[][]{
                {Skill.KAMEJOKO, 3, 1000},
                {Skill.LIEN_HOAN, 7, 300},
                {Skill.DICH_CHUYEN_TUC_THOI,7,15000}
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

    public void initBase() {
        BossData data = this.data[this.currentLevel];
        this.name = data.getName();
        this.gender = data.getGender();
        this.nPoint.mpg = 23_07_2003;
        this.nPoint.dameg = this.dameClan;
        this.nPoint.hpg = this.hpClan;
        this.nPoint.hp = nPoint.hpg;
        this.nPoint.calPoint();
        this.initSkill();
        this.resetBase();
    }

    @Override
    public void joinMap() {
        if (zoneFinal != null) {
//            ChangeMapService.gI().changeMap(this, zoneFinal, 897, 384);
            joinMapByZone(zoneFinal);
            this.zone.isTrungUyTrangAlive = true;
        }
    }

    @Override
    public void active() {
        this.attack();
        this.changeToTypeNonPK();
        Service.gI().changeFlag(this, 9);
        
    }

    private long lastTimeFindPlayerToChangeFlag;

   

    @Override
    /**
     * Không senđ thông báo bên dưới
     */
    public void die(Player plKill) {
        if (plKill != null) {
            reward(plKill);
        }
        this.changeStatus(BossStatus.DIE);
    }

    @Override
    public void reward(Player plKill) {
        if (Util.isTrue(100, 100)) {
            ItemMap it = new ItemMap(this.zone, 753, 5, this.location.x, this.zone.map.yPhysicInTop(this.location.x,
                    this.location.y - 24), plKill.id);
            Service.gI().dropItemMap(this.zone, it);
        }
        plKill.pointBoss += 0;
        TaskService.gI().checkDoneTaskKillBoss(plKill, this);
    }

    @Override
    public void leaveMap() {
        this.zone.isTrungUyTrangAlive = false;
        super.leaveMap();
        synchronized (this) {
            BossManager.gI().removeBoss(this);
        }
        this.bossStatus = null;
        this.lastZone = null;
        this.playerTarger = null;
        this.bossAppearTogether = null;
        this.parentBoss = null;
        this.zoneFinal = null;
        this.dispose();
    }

    @Override
    public void moveTo(int x, int y) {
        if (this.location.x >= 300 || this.location.x <= 660) {
            byte dir = (byte) (this.location.x - x < 0 ? 1 : -1);
            byte move = (byte) Util.nextInt(40, 60);
            PlayerService.gI().playerMove(this, this.location.x + (dir == 1 ? move : -move), y + (Util.isTrue(3, 10) ? -50 : 0));
        }
    }

    private long lastTimeBlame;

   //@Override
    public int injured(Player plAtt, int damage, boolean piercing, boolean isMobAttack) {
 this.checkAnThan(plAtt);
        if (!this.isDie()) {
            // if (this.zone.isbulon13Alive) {
            //     if (System.currentTimeMillis() - lastTimeBlame > 3000) {
            //         this.chat("Ngu lắm đánh bulon 13 trước đi con zai");
            //     }
            //     lastTimeBlame = System.currentTimeMillis();
            //     return 0;
            // }
            // if (this.zone.isbulon14Alive) {
            //     if (System.currentTimeMillis() - lastTimeBlame > 5000) {
            //         this.chat("Ngu lắm đánh bulon 14 trước đi con zai");
            //     }
            //     lastTimeBlame = System.currentTimeMillis();
            //     return 0;
            // }
            if (!piercing && Util.isTrue(1, 100)) {
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

}
