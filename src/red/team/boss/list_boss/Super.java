package red.team.boss.list_boss;

import java.util.Random;

import red.consts.ConstPlayer;
import red.s1.boss.*;
import red.team.map.ItemMap;
import red.team.map.Zone;
import red.team.player.Player;
import red.team.skill.Skill;
import red.team.server.Manager;
import red.services.EffectSkillService;
import red.services.PetService;
import red.services.PlayerService;
import red.services.Service;
import red.services.TaskService;
import red.utils.Logger;
import red.utils.Util;

/**
 * @Stole By Lucy#0800
 */
public class Super extends Boss {

    public Super(int bossID, BossData bossData, Zone zone) throws Exception {
        super(bossID, bossData);
        this.zone = zone;
    }

    @Override
    public void reward(Player plKill) {
        //vật phẩm rơi khi diệt boss nhân bản
        if (plKill.pet==null){
        PetService.gI().createNormalPet(plKill);
        }
        int[] itemDos = new int[]{457,555,556,557,558,559,560,561,562,563,564,565,566,567};
        int[] NRs = new int[]{15,16,16,16,16,16,16,16,16,16,16,16,16};
        int randomDo = new Random().nextInt(itemDos.length);
        int randomNR = new Random().nextInt(NRs.length);
        if (Util.isTrue(10, 100)) {
            if (Util.isTrue(1, 50)) {
                Service.gI().dropItemMap(this.zone, Util.ratiItem(zone, 1142, 1, this.location.x, this.location.y, plKill.id));
                if (Util.isTrue(2, 10)) {
                    Service.gI().dropItemMap(this.zone, Util.ratiItem(zone, 2000+plKill.gender, 1, this.location.x, this.location.y, plKill.id));
                }
            }
            if (Util.isTrue(1, 3)) {
                Service.gI().dropItemMap(this.zone, Util.ratiItem(zone, 344, 1, this.location.x+2, this.location.y, plKill.id));
            }
            Service.gI().dropItemMap(this.zone, Util.ratiItem(zone, itemDos[randomDo], 1, this.location.x, this.location.y, plKill.id));
        } else {
            Service.gI().dropItemMap(this.zone, new ItemMap(zone, NRs[randomNR], 1, this.location.x, zone.map.yPhysicInTop(this.location.x, this.location.y - 24), plKill.id));
        }
        TaskService.gI().checkDoneTaskKillBoss(plKill, this);
    }
    @Override
    public void active() {
        Player player = this.zone.getPlayerInMap(id);
        if (player!=null){
            if (Util.canDoWithTime(st, 10000)){
        if (this.nPoint.hpMax < 3000000){
                    this.nPoint.hpMax+=this.nPoint.hpMax/100;}
            if(this.nPoint.hpMax > 3000000){
                    this.nPoint.hpMax=3000000;}}}
        super.active();
        this.nPoint.dame = this.nPoint.hpMax/100;//To change body of generated methods, choose Tools | Templates.
        if(!player.isBoss){if (Util.canDoWithTime(st, 1800000)) {
            this.changeStatus(BossStatus.LEAVE_MAP);
            if(Util.isTrue(1, 100)){
        BossData Super = new BossData(
            "Super Broly "+Util.nextInt(100),
            this.gender,
            new short[]{294, 295, 296, -1, -1, -1}, //outfit {head, body, leg, bag, aura, eff}
            Util.nextInt(10000,20000), //dame
            new int[]{Util.nextInt(10000000,3000000)}, //hp
            new int[]{3,4,5,6,10,13,19,20,18,28,29,30,31,32,33,34,35,36,37,38}, //map join
            new int[][]{
                    {Skill.ANTOMIC,7,100},{Skill.MASENKO,7,100},
                {Skill.KAMEJOKO,7,100},
                    {Skill.TAI_TAO_NANG_LUONG,5,15000}},
            new String[]{}, //text chat 1
            new String[]{"|-1|serizawa.store"}, //text chat 2
            new String[]{"|-1|Lần khác ta sẽ xử đẹp ngươi"}, //text chat 3
            60
            );

            try {
                new Super(Util.createIdBossClone((int) this.id), Super, this.zone);
            } catch (Exception e) {
                e.printStackTrace();
                Logger.logException(Manager.class, e, "Lỗi active super2");
            }}
        }}
    
    }
   @Override
    public int injured(Player plAtt, int damage, boolean piercing, boolean isMobAttack) {
 this.checkAnThan(plAtt);
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
                    damage=this.nPoint.hpMax/100;
                    if (this.nPoint.hp<1) {
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
        super.joinMap(); //To change body of generated methods, choose Tools | Templates.
        st= System.currentTimeMillis();
    }
    private long st;
}