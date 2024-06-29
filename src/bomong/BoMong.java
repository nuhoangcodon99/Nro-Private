
package bomong;

import red.consts.ConstPlayer;
import red.models.Template;
import red.team.player.Player;
import com.girlkun.network.io.Message;
import red.team.server.Manager;
import red.services.Service;
import red.utils.Logger;
import red.utils.Util;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * lucyonfire
 */
public class BoMong {

    public static final String TEN_SIEU_CAP = "%1";
    public static final String TEN_NGUOI_BAN_HANG = "%2";

    private Player player;
    public List<Boolean> listReceiveGem;

    public int numPvpWin;
    public int numSkillChuong;
    public int numFly;
    public int numKillMobFly;
    public int numKillNguoiRom;
    public long numHourOnline;
    public int numGivePea;
    public int numSellItem;
    public int numPayMoney;
    public int numKillSieuQuai;
    public int numHoiSinh;
    public int numSkillDacBiet;
    public int numPickGem;


    public BoMong(Player pl) {
        this.player = pl;
        this.listReceiveGem = new ArrayList<>(Manager.ACHIEVEMENTS.size());

    }

    public void Show() {
        Message msg = null;
        try {
            msg = new Message(-76);
            msg.writer().writeByte(0); // action
            msg.writer().writeByte(listReceiveGem.size()); // numArrchivement
            for (Template.AchievementTemplate temp : Manager.ACHIEVEMENTS) {

                msg.writer().writeUTF(temp.getInfo1()); // info1
                msg.writer().writeUTF( // info2
                        this.SwitchName(player, temp.getInfo2())
                                + " (" + Util.numberToMoney(getCount(temp.getIndex()))
                                + "/"
                                + Util.numberToMoney(temp.getCount_Purpose()) + ")");
                msg.writer().writeShort(temp.getGem()); // money
                msg.writer().writeBoolean(getCount(temp.getIndex()) > temp.getCount_Purpose()); // isfinish
                msg.writer().writeBoolean(this.listReceiveGem.get(temp.getIndex())); // isreceiv
            }
            this.player.sendMessage(msg);
            msg.cleanup();
        } catch (IOException e) {
            Logger.logException(BoMong.class, e);
            e.getStackTrace();
        } finally {
            if (msg != null) {
                msg.cleanup();
                msg = null;
            }
        }
    }

    public void receiveGem(byte index) {
        Template.AchievementTemplate temp = Manager.ACHIEVEMENTS.stream().filter(ac -> ac.getIndex() == index)
                .findFirst().orElse(null);

        if (temp != null) {
            Message msg = null;
            try {
                msg = new Message(-76);
                msg.writer().writeByte(1); // action
                msg.writer().writeByte(index); // index
                this.player.sendMessage(msg);
                msg.cleanup();
            } catch (IOException e) {
                Logger.logException(Manager.class, e, "receivegem bo mong");
            } finally {
                if (msg != null) {
                    msg.cleanup();
                    msg = null;
                }
            }
            this.listReceiveGem.set(index, Boolean.TRUE);
            this.player.inventory.ruby += temp.getGem();
            Service.getInstance().sendMoney(this.player);
            Service.getInstance().sendThongBao(this.player, "Nhận thành công " + temp.getGem() + " Thỏi Vàng 2");
            // System.out.println("nhan dc" + temp.getGem());
        } else {
            Service.getInstance().sendThongBao(this.player, "Không có phần thưởng");
        }
    }

    private String SwitchName(Player player, String text) {
        byte gender = player.gender;
        text = text.replaceAll(TEN_SIEU_CAP, gender == ConstPlayer.TRAI_DAT
                ? "Siêu Trái Đất"
                : (gender == ConstPlayer.NAMEC
                        ? "Siêu Namếc"
                        : "Siêu Xayda"));
        text = text.replaceAll(TEN_NGUOI_BAN_HANG, gender == ConstPlayer.TRAI_DAT
                ? "Bunma"
                : (gender == ConstPlayer.NAMEC
                        ? "Dende"
                        : "Appule"));
        return text;
    }

    public void plusCount(int indexAchie) {
        switch (indexAchie) {
            case 0:
            case 1:
                this.player.nPoint.power++;
                break;
            case 2:
                this.player.magicTree.level++;
                break;
            case 3:
                this.numPvpWin++;
                break;
            case 4:
                this.numSkillChuong++;
                break;
            case 5:
                this.numFly++;
                break;
            case 6:
                this.numKillMobFly++;
                break;
            case 7:
                this.numKillNguoiRom++;
                break;
            case 8:
                this.numHourOnline++;
                break;
            case 9:
                this.numGivePea++;
                break;
            case 10:
                this.numSellItem++;
                break;
            case 11:
                this.numPayMoney++;
                break;
            case 12:
                this.numKillSieuQuai++;
                break;
            case 13:
                this.numHoiSinh++;
                break;
            case 14:
                this.numSkillDacBiet++;
                break;
            case 15:
                this.numPickGem++;
                break;

            default:
                break;
        }
    }

    public void plusCount(int indexAchie, int count) {
        switch (indexAchie) {
            case 0:
            case 1:
                this.player.nPoint.power += count;
                break;
            case 2:
                this.player.magicTree.level += count;
                break;
            case 3:
                this.numPvpWin += count;
                break;
            case 4:
                this.numSkillChuong += count;
                break;
            case 5:
                this.numFly += count;
                break;
            case 6:
                this.numKillMobFly += count;
                break;
            case 7:
                this.numKillNguoiRom += count;
                break;
            case 8:
                this.numHourOnline += count;
                break;
            case 9:
                this.numGivePea += count;
                break;
            case 10:
                this.numSellItem += count;
                break;
            case 11:
                this.numPayMoney += count;
                break;
            case 12:
                this.numKillSieuQuai += count;
                break;
            case 13:
                this.numHoiSinh += count;
                break;
            case 14:
                this.numSkillDacBiet += count;
                break;
            case 15:
                this.numPickGem += count;
                break;
            default:
                break;
        }
    }

    private long getCount(int indexAchie) {
        switch (indexAchie) {
            case 0:
            case 1:
                return this.player.nPoint.power;
            case 2:
                return this.player.magicTree.level;
            case 3:
                return this.numPvpWin;
            case 4:
                return this.numSkillChuong;
            case 5:
                return this.numFly;
            case 6:
                return this.numKillMobFly;
            case 7:
                return this.numKillNguoiRom;
            case 8:
                return (this.numHourOnline / 1000 / 60 / 60);
            case 9:
                return this.numGivePea;
            case 10:
                return this.numSellItem;
            case 11:
                return this.numPayMoney;
            case 12:
                return this.numKillSieuQuai;
            case 13:
                return this.numHoiSinh;
            case 14:
                return this.numSkillDacBiet;
            case 15:
                return this.numPickGem;
            default:
                return 0;
        }
    }

    public void dispose() {
        this.player = null;
    }
}
