package red.team.boss.bdkb;
import red.consts.ConstPlayer;
import red.s1.boss.Boss;
import red.s1.boss.BossData;
import red.team.map.ItemMap;
import red.team.map.Zone;
import red.team.player.Player;
import red.team.skill.Skill;
import red.services.Service;
import red.utils.Util;

/**
 * @author BTH sieu cap vippr0
 */
public class TrungUyXanhLo extends Boss {
    private static final int[][] FULL_DEMON = new int[][]{{Skill.DEMON, 1}, {Skill.DEMON, 2}, {Skill.DEMON, 3}, {Skill.DEMON, 4}, {Skill.DEMON, 5}, {Skill.DEMON, 6}, {Skill.DEMON, 7}};

    public TrungUyXanhLo(Zone zone , int level, int dame, int hp) throws Exception {
        super(Util.randomBossId(), new BossData(
                "Trung úy xanh lơ", //name
                ConstPlayer.TRAI_DAT, //gender
                new short[]{141, 142, 143, -1, -1, -1}, //outfit {head, body, leg, bag, aura, eff}
                ((1000 + dame) * level), //dame
                new int[]{((50000 + hp) * level)}, //hp
                new int[]{137}, //map join
                (int[][]) Util.addArray(FULL_DEMON), //skill
                new String[]{}, //text chat 1
                new String[]{"|-1|Nhóc con"}, //text chat 2
                new String[]{}, //text chat 3
                60
        ));
        this.zone = zone;
    }
    @Override
    public void reward(Player plKill) {
     
        int lv = plKill.clan.banDoKhoBau.level;
        if (lv == 110) {
            
        } else if (lv <= 50) {  
        
        }
        if (Util.isTrue(100, 100)) {
            ItemMap it = new ItemMap(this.zone, 14, 1, this.location.x, this.zone.map.yPhysicInTop(this.location.x,
                    this.location.y - 24), plKill.id);
            Service.getInstance().dropItemMap(this.zone, it);
        }
    }
    @Override
    public void active() {
        super.active();
    }

    @Override
    public void joinMap() {
        super.joinMap();
    }
}





