/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package red.team.boss.list_boss;

import java.util.Random;

import red.s1.boss.Boss;
import red.s1.boss.BossData;
import red.s1.boss.BossID;
import red.s1.boss.BossStatus;
import red.s1.boss.BossesData;
import red.team.map.ItemMap;
import red.team.player.Player;
import red.team.skill.Skill;
import red.team.server.Manager;
import red.services.EffectSkillService;
import red.services.PetService;
import red.services.Service;
import red.utils.Logger;
import red.utils.Util;

/**
 * @@Stole By Lucy#0800
 */
public class Mabu extends Boss {

    public Mabu() throws Exception {
        super(BossID.MABU, BossesData.MABU);
    }

    @Override
    public void reward(Player plKill) {
        if (Util.isTrue(33, 100)) {
            BossData Super = new BossData(
                    "Super Broly " + Util.nextInt(100),
                    this.gender,
                    new short[] { 294, 295, 296, -1, -1, -1 }, // outfit {head, body, leg, bag, aura, eff}
                    Util.nextInt(100, 3000), // dame
                    new int[] { Util.nextInt(1000000, 3000000) }, // hp
                    new int[] { 3, 4, 5, 6, 10, 13, 19, 20, 18, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38 }, // map
                                                                                                              // join
                    new int[][] {
                            { Skill.ANTOMIC, 7, 100 }, { Skill.MASENKO, 7, 100 },
                            { Skill.KAMEJOKO, 7, 100 },
                            { Skill.TAI_TAO_NANG_LUONG, 5, 15000 } },
                    new String[] {}, // text chat 1
                    new String[] { "|-1|serizawa.store" }, // text chat 2
                    new String[] { "|-1|Lần khác ta sẽ xử đẹp ngươi" }, // text chat 3
                    60);

            try {
                new Super(Util.createIdBossClone((int) this.id), Super, this.zone);
            } catch (Exception e) {
                e.printStackTrace();
                Logger.logException(Manager.class, e, "Lỗi super");
            }
        }
    }

    @Override
    public void active() {
        Player player = this.zone.getPlayerInMap(id);
        if (player != null) {
            if (Util.canDoWithTime(st, 10000)) {
                if (this.nPoint.hpMax < 3000000) {
                    this.nPoint.hpMax += this.nPoint.hpMax / 100;
                }
                if (this.nPoint.hpMax > 3000000) {
                    this.nPoint.hpMax = 3000000;
                }
            }
        }
        super.active();
        this.nPoint.dame = this.nPoint.hpMax / 100;// To change body of generated methods, choose Tools | Templates.
        if (!player.isBoss) {
            if (Util.canDoWithTime(st, 1800000)) {
                this.changeStatus(BossStatus.LEAVE_MAP);
                if (Util.isTrue(1, 100)) {
                    BossData Super = new BossData(
                            "Super Broly " + Util.nextInt(100),
                            this.gender,
                            new short[] { 294, 295, 296, -1, -1, -1 }, // outfit {head, body, leg, bag, aura, eff}
                            Util.nextInt(100, 3000), // dame
                            new int[] { Util.nextInt(1000000, 30000000) }, // hp
                            new int[] { 6 }, // map join
                            new int[][] {
                                    { Skill.ANTOMIC, 7, 100 }, { Skill.MASENKO, 7, 100 },
                                    { Skill.KAMEJOKO, 7, 100 },
                                    { Skill.TAI_TAO_NANG_LUONG, 5, 15000 } },
                            new String[] {}, // text chat 1
                            new String[] { "|-1|serizawa.store" }, // text chat 2
                            new String[] { "|-1|Lần khác ta sẽ xử đẹp ngươi" }, // text chat 3
                            60);

                    try {
                        new Super(Util.createIdBossClone((int) this.id), Super, this.zone);
                    } catch (Exception e) {
                        e.printStackTrace();
                        Logger.logException(Manager.class, e, "Lỗi active super");
                    }
                }
            }
        }

    }

    @Override
    public int injured(Player plAtt, int damage, boolean piercing, boolean isMobAttack) {
        this.checkAnThan(plAtt);

        if (this != null) {

            this.nPoint.isMabu = true;
        }
        if (plAtt != null) {
            switch (plAtt.playerSkill.skillSelect.template.id) {
                case Skill.ANTOMIC:
                case Skill.DEMON:
                case Skill.DRAGON:
                case Skill.GALICK:
                case Skill.KAIOKEN:
                case Skill.KAMEJOKO:
                case Skill.DICH_CHUYEN_TUC_THOI:
                case Skill.LIEN_HOAN:
                    damage = this.nPoint.hpMax / 100;
                    if (this.nPoint.hp < 1) {
                        this.setDie(plAtt);
                        die(this);
                    }
                    return super.injured(plAtt, damage, !piercing, isMobAttack);
            }
        }
        return super.injured(plAtt, damage, piercing, isMobAttack);
    }

    @Override
    public void joinMap() {
        super.joinMap(); // To change body of generated methods, choose Tools | Templates.
        st = System.currentTimeMillis();
    }

    private long st;
}