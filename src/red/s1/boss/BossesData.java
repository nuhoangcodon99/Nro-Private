package red.s1.boss;

import red.consts.ConstPlayer;
import red.s1.boss.BossID;
import red.team.matches.TYPE_LOSE_PVP;
import red.team.skill.Skill;
import red.utils.Util;

/**
 * @Stole By Lucy#0800❤
 */
public class BossesData {

        /**
         * Prefix text chat |-1| Boss chat |-2| Player in map chat |-3| Parent chat
         * |0|,|1|,|n| Index boss in list chat
         */
        private static final int[][] FULL_DRAGON = new int[][] { { Skill.DRAGON, 1 }, { Skill.DRAGON, 2 },
                        { Skill.DRAGON, 3 }, { Skill.DRAGON, 4 }, { Skill.DRAGON, 5 }, { Skill.DRAGON, 6 },
                        { Skill.DRAGON, 7 } };
        private static final int[][] FULL_DEMON = new int[][] { { Skill.DEMON, 1 }, { Skill.DEMON, 2 },
                        { Skill.DEMON, 3 }, { Skill.DEMON, 4 }, { Skill.DEMON, 5 }, { Skill.DEMON, 6 },
                        { Skill.DEMON, 7 } };
        private static final int[][] FULL_GALICK = new int[][] { { Skill.GALICK, 1 }, { Skill.GALICK, 2 },
                        { Skill.GALICK, 3 }, { Skill.GALICK, 4 }, { Skill.GALICK, 5 }, { Skill.GALICK, 6 },
                        { Skill.GALICK, 7 } };
        private static final int[][] FULL_KAMEJOKO = new int[][] { { Skill.KAMEJOKO, 1 }, { Skill.KAMEJOKO, 2 },
                        { Skill.KAMEJOKO, 3 }, { Skill.KAMEJOKO, 4 }, { Skill.KAMEJOKO, 5 }, { Skill.KAMEJOKO, 6 },
                        { Skill.KAMEJOKO, 7 } };
        private static final int[][] FULL_MASENKO = new int[][] { { Skill.MASENKO, 1 }, { Skill.MASENKO, 2 },
                        { Skill.MASENKO, 3 }, { Skill.MASENKO, 4 }, { Skill.MASENKO, 5 }, { Skill.MASENKO, 6 },
                        { Skill.MASENKO, 7 } };
        private static final int[][] FULL_ANTOMIC = new int[][] { { Skill.ANTOMIC, 1 }, { Skill.ANTOMIC, 2 },
                        { Skill.ANTOMIC, 3 }, { Skill.ANTOMIC, 4 }, { Skill.ANTOMIC, 5 }, { Skill.ANTOMIC, 6 },
                        { Skill.ANTOMIC, 7 } };
        private static final int[][] FULL_LIENHOAN = new int[][] { { Skill.LIEN_HOAN, 1 }, { Skill.LIEN_HOAN, 2 },
                        { Skill.LIEN_HOAN, 3 }, { Skill.LIEN_HOAN, 4 }, { Skill.LIEN_HOAN, 5 }, { Skill.LIEN_HOAN, 6 },
                        { Skill.LIEN_HOAN, 7 } };
        private static final int[][] FULL_TDHS = new int[][] { { Skill.THAI_DUONG_HA_SAN, 1 },
                        { Skill.THAI_DUONG_HA_SAN, 2 }, { Skill.THAI_DUONG_HA_SAN, 3 }, { Skill.THAI_DUONG_HA_SAN, 4 },
                        { Skill.THAI_DUONG_HA_SAN, 5 }, { Skill.THAI_DUONG_HA_SAN, 6 },
                        { Skill.THAI_DUONG_HA_SAN, 7 } };

        private static final int REST_1_S = 1;
        private static final int REST_2_S = 2;
        private static final int REST_5_S = 5;
        private static final int REST_10_S = 10;
        private static final int REST_20_S = 20;
        private static final int REST_30_S = 30;
        private static final int REST_1_M = 60;
        private static final int REST_2_M = 120;
        private static final int REST_5_M = 300;
        private static final int REST_10_M = 600;
        private static final int REST_15_M = 900;
        private static final int REST_20_M = 1200;
        private static final int REST_30_M = 1800;
        private static final int REST_1_H = 3600;
        private static final int REST_2_H = 7200;
        private static final int REST_3_H = 10800;
        private static final int REST_4_H = 14400;
        private static final int REST_6_H = 21600;
        private static final int REST_8_H = 28800;
        private static final int REST_12_H = 43200;
        private static final int REST_18_H = 64200;
        private static final int REST_23_H = 82800;
        private static final int REST_24_H = 86400;

        // **************************************************************************
        // Boss nappa
        public static final BossData KUKU = new BossData(
                        "Kuku", // name
                        ConstPlayer.XAYDA, // gender
                        new short[] { 159, 160, 161, -1, -1, -1 }, // outfit {head, body, leg, bag, aura, eff}
                        10000, // dame
                        new int[] { 50000000 }, // hp
                        new int[] { 68, 69, 70, 71, 72 }, // map join
                        new int[][] {
                                        { Skill.MASENKO, 3, 1000 },
                                        { Skill.LIEN_HOAN, 7, 300 } },
                        new String[] {}, // text chat 1
                        new String[] {}, // text chat 2
                        new String[] { "|-2|Đẹp trai nó phải thế" }, // text chat 3
                        REST_5_M // second rest
        );

        public static final BossData MAP_DAU_DINH = new BossData(
                        "Mập Đầu Đinh", // name
                        ConstPlayer.XAYDA, // gender
                        new short[] { 165, 166, 167, -1, -1, -1 }, // outfit {head, body, leg, bag, aura, eff}
                        15000, // dame
                        new int[] { 6000000 }, // hp
                        new int[] { 63, 64, 65, 66, 67 }, // map join
                        new int[][] {
                                        { Skill.GALICK, 7, 300 },
                                        { Skill.ANTOMIC, 7, 1000 }, }, // skill //skill
                        new String[] {}, // text chat 1
                        new String[] { "|-1|Chết hết đi cho tao",
                                        "|-1|Tao sẽ giết hết bọn mày",
                                        "|-1|Hahaha",
                                        "|-1|Tao chỉ cần 10 phút để giết hết bọn mày",
                                        "|-1|Được rồi tao sẽ thổi bay hết bọn mày",
                                        "|-1|Muốn đùa thì thêm tí muối đi!",
                                        "|-2|Thằng này,tao nhịn mày lâu lắm rồi ấy nhá",
                                        "|-2|Coi thường nhau quá đấy", }, // text chat 2
                        new String[] { "|-1|Ôi bạn ơi ....ơi!!!" }, // text chat 3
                        REST_5_M // second rest
        );

        public static final BossData RAMBO = new BossData(
                        "Rambo", // name
                        ConstPlayer.XAYDA, // gender
                        new short[] { 162, 163, 164, -1, -1, -1 }, // outfit {head, body, leg, bag, aura, eff}
                        20000, // dame
                        new int[] { 70000000 }, // hp
                        new int[] { 74, 75, 76, 77 }, // map join
                        new int[][] {
                                        { Skill.KAMEJOKO, 7, 1000 },
                                        { Skill.LIEN_HOAN, 7, 300 },
                                        { Skill.KAMEJOKO, 1, 1000 }, }, // skill //skill
                        new String[] {}, // text chat 1
                        new String[] { "|-1|Hahaha",
                                        "|-1|Ngạc nhiên thật, đúng là mày đã tiến bộ rất nhanh..",
                                        "|-1|Tao sẽ cho mày biết lý do tại sao tao lại không dùng đến năng lực thực sự..",
                                        "|-1|Đến tao còn không thắng nổi thì đừng mộng tưởng đối đầu với đại ca Fide!",
                                        "|-1|Ha ha ha! Ngươi tưởng chạy trốn được sao?",
                                        "|-2|Oái..!",
                                        "|-2|Đừng tưởng thế này là xong..! Tao sẽ còn mạnh hơn nữa!", }, // text chat 2
                        new String[] { "|-1|Ôi bạn ơi..." }, // text chat 3
                        REST_5_M // second rest
        );

        // **************************************************************************
        // Boss tiểu đội sát thủ
        public static final BossData SO_4 = new BossData(
                        "Số 4 Guldo", // name
                        ConstPlayer.XAYDA, // gender
                        new short[] { 168, 169, 170, -1, -1, -1 }, // outfit {head, body, leg, bag, aura, eff}
                        25000, // dame
                        new int[] { 70000000 }, // hp
                        new int[] { 79, 31, 32, 33, 34 }, // map join
                        // new int[]{86}, //map join
                        new int[][] {
                                        { Skill.LIEN_HOAN, 7, 300 },
                                        { Skill.MASENKO, 7, 1000 },
                                        { Skill.THOI_MIEN, 7, 100000 },
                        }, // skill
                        new String[] {}, // text chat 1
                        new String[] { "|-1|Oải rồi hả?", "|-1|Ê cố lên nhóc",
                                        "|-1|Chán", "|-1|Đại ca Fide có nhầm không nhỉ",
                                        "|-1|Các ngươi không nhúc nhích được sao?",
                                        "|-1|HAHAHAHA", "|-1|Chỉ là bọn con nít"
                        }, // text chat 2
                        new String[] { "|-1|Cay quá!",
                                        "|-1|Ta mà lại thua được sao?",
                                        "|-1|Hãy trả thù cho ta!"
                        }, // text chat 3
                        REST_5_M

        );

        public static final BossData SO_3 = new BossData(
                        "Số 3 Recome", // name
                        ConstPlayer.XAYDA, // gender
                        new short[] { 174, 175, 176, -1, -1, -1 }, // outfit {head, body, leg, bag, aura, eff}
                        30000, // dame
                        new int[] { 75000000 }, // hp
                        new int[] { 79, 31, 32, 33, 34 }, // map join
                        new int[][] {
                                        { Skill.LIEN_HOAN, 7, 300 },
                                        { Skill.ANTOMIC, 4, 1000 },
                        }, // skill
                        new String[] {}, // text chat 1
                        new String[] { "|-1|Oải rồi hả?", "|-1|Ê cố lên nhóc",
                                        "|-1|Chán", "|-1|Đại ca Fide có nhầm không nhỉ",
                                        "|-1|Một mình tao chấp hết tụi bây",
                                        "|-1|HAHAHAHA", "|-1|Chỉ là bọn con nít"
                        }, // text chat 2
                        new String[] { "|-1|Cay quá!",
                                        "|-1|Ta mà lại thua được sao?",
                                        "|-1|Hãy trả thù cho ta!"
                        }, // text chat 3
                        TypeAppear.APPEAR_WITH_ANOTHER // type appear
        );

        public static final BossData SO_2 = new BossData(
                        "Số 2 Jeice", // name
                        ConstPlayer.XAYDA, // gender
                        new short[] { 171, 172, 173, -1, -1, -1 }, // outfit {head, body, leg, bag, aura, eff}
                        35000, // dame
                        new int[] { 80000000 }, // hp
                        new int[] { 79, 31, 32, 33, 34 }, // map join
                        new int[][] {
                                        { Skill.LIEN_HOAN, 7, 300 },
                                        { Skill.ANTOMIC, 3, 3000 },
                        }, // skill//skill
                        new String[] {}, // text chat 1
                        new String[] { "|-1|Oải rồi hả?", "|-1|Ê cố lên nhóc",
                                        "|-1|Chán", "|-1|Đại ca Fide có nhầm không nhỉ",
                                        "|-1|Một mình tao chấp hết tụi bây",
                                        "|-1|HAHAHAHA", "|-1|Chỉ là bọn con nít"
                        }, // text chat 2
                        new String[] { "|-1|Cay quá!",
                                        "|-1|Ta mà lại thua được sao?",
                                        "|-1|Hãy trả thù cho ta!"
                        }, // text chat 3
                        TypeAppear.APPEAR_WITH_ANOTHER // type appear
        );

        public static final BossData SO_1 = new BossData(
                        "Số 1 Burter", // name
                        ConstPlayer.XAYDA, // gender
                        new short[] { 177, 178, 179, -1, -1, -1 }, // outfit {head, body, leg, bag, aura, eff}
                        40000, // dame
                        new int[] { 85000000 }, // hp
                        new int[] { 79, 31, 32, 33, 34 }, // map join
                        new int[][] {
                                        { Skill.LIEN_HOAN, 7, 1000 },
                                        { Skill.KAMEJOKO, 4, 1000 },
                        }, // skill//skill
                        new String[] {}, // text chat 1
                        new String[] { "|-1|Oải rồi hả?", "|-1|Ê cố lên nhóc",
                                        "|-1|Chán", "|-1|Đại ca Fide có nhầm không nhỉ",
                                        "|-1|Một mình tao chấp hết tụi bây",
                                        "|-1|HAHAHAHA", "|-1|Chỉ là bọn con nít"
                        }, // text chat 2
                        new String[] { "|-1|Cay quá!",
                                        "|-1|Ta mà lại thua được sao?",
                                        "|-1|Hãy trả thù cho ta!"
                        }, // text chat 3
                        TypeAppear.APPEAR_WITH_ANOTHER // type appear
        );

        public static final BossData TIEU_DOI_TRUONG = new BossData(
                        "Tiểu đội trưởng Ginyu", // name
                        ConstPlayer.XAYDA, // gender
                        new short[] { 180, 181, 182, -1, -1, -1 }, // outfit {head, body, leg, bag, aura, eff}
                        45000, // dame
                        new int[] { 90000000 }, // hp
                        new int[] { 79, 31, 32, 33, 34 }, // map join
                        new int[][] {
                                        { Skill.SOCOLA, 7, 1000 },
                                        { Skill.MASENKO, 7, 1000 },
                                        { Skill.GALICK, 7, 1000 },
                        }, // skill
                        new String[] {}, // text chat 1
                        new String[] { "|-1|Oải rồi hả?", "|-1|Ê cố lên nhóc",
                                        "|-1|Chán", "|-1|Đại ca Fide có nhầm không nhỉ",
                                        "|-1|Một mình tao chấp hết tụi bây",
                                        "|-1|HAHAHAHA", "|-1|Chỉ là bọn con nít"
                        }, // text chat 2
                        new String[] { "|-1|Cay quá!"
                        }, // text chat 3
                        TypeAppear.APPEAR_WITH_ANOTHER // type appear
        );

        // **************************************************************************
        // Boss Fide đại ca
        public static final BossData FIDE_DAI_CA_1 = new BossData(
                        "Fide đại ca 1", // name
                        ConstPlayer.XAYDA, // gender
                        new short[] { 183, 184, 185, -1, -1, -1 }, // outfit {head, body, leg, bag, aura, eff}
                        50000, // dame
                        new int[] { 100000000 }, // hp
                        new int[] { 80 }, // map join
                        (int[][]) Util.addArray(FULL_ANTOMIC, FULL_GALICK), // skill
                        new String[] {
                        }, // text chat 1
                        new String[] { "|-1|Các ngươi tới số rồi mới gặp phải ta",
                                        "|-1|Toàn bọn tốt thí",
                                        "|-2|Không..thể..nào!!",
                                        "|-2|Không ngờ..Hắn mạnh cỡ này sao..!!",
                                        "|-1|Chúng mày nghĩ kiến lại thắng nổi khủng long sao?",
                                        "|-1|Hô hô hô",
                                        "|-1|Được thôi, nếu muốn chết đến vậy, ta rất vui lòng!!"
                        }, // text chat 2
                        new String[] { "|-1|Biến hình, hây aaaa..." }, // text chat 3
                        REST_5_M // second rest
        );

        public static final BossData FIDE_DAI_CA_2 = new BossData(
                        "Fide đại ca 2", // name
                        ConstPlayer.XAYDA, // gender
                        new short[] { 186, 187, 188, -1, -1, -1 }, // outfit {head, body, leg, bag, aura, eff}
                        60000, // dame
                        new int[] { 150000000 }, // hp
                        new int[] { 80 }, // map join
                        (int[][]) Util.addArray(FULL_ANTOMIC, FULL_LIENHOAN), // skill
                        new String[] {}, // text chat 1
                        new String[] { "|-1|Oải rồi hả?",
                                        "|-1|Ê cố lên nhóc",
                                        "|-1|Ôi, xin lỗi nhé. Sức mạnh lớn quá nên ta cũng chẳng điều khiển nổi nữa!",
                                        "|-1|Hahaha! Ấn tượng đấy! Tên nào cũng lủi rất nhanh!",
                                        "|-2|A...Tốc độ nhanh quá!",
                                        "|-1|Hình như... mày không phải là một thằng nhóc bình thường!",
                                        "|-1|Mấy đòn vừa rồi, nói thật là cũng đau đấy!",
                                        "|-1|Nhưng tiếc rằng đối thủ của mày lại là Fide này...",
                                        "|-2|Chết tiệt.. chúng ta đã đánh giá quá thấp sức mạnh của hắn!!",
                                        "|-2|Đồ..Đồ quái vật..!",
                                        "|-2|Tốc độ kinh hoàng quá! Ai mà né nổi chứ!", }, // text chat 2
                        new String[] { "|-1|Ác quỷ biến hình, Graaaaa...." }, // text chat 3
                        TypeAppear.ANOTHER_LEVEL // type appear
        );

        public static final BossData FIDE_DAI_CA_3 = new BossData(
                        "Fide đại ca 3", // name
                        ConstPlayer.XAYDA, // gender
                        new short[] { 189, 190, 191, -1, -1, -1 }, // outfit {head, body, leg, bag, aura, eff}
                        70000, // dame
                        new int[] { 200000000 }, // hp
                        new int[] { 80 }, // map join
                        (int[][]) Util.addArray(FULL_MASENKO, FULL_LIENHOAN), // skill
                        new String[] {}, // text chat 1
                        new String[] { "|-1|Ta nói các ngươi rồi! Sức mạnh này của ta còn đáng sợ hơn địa ngục!!",
                                        "|-1|Ta chơi thêm chút nữa chắc ngươi chóng mặt buồn nôn mất!!",
                                        "|-2|Ăn gì mà khỏe thế!",
                                        "|-2|Chết đi Fide!!!!",
                                        "|-1|Hô hô hô hô",
                                        "|-1|Chán thật! Khí của ngươi sắp hết rồi. Để ta tiễn ngươi về địa ngục!",
                                        "|-1|Ngươi quá tự cao rồi đấy,xem ta đây!", }, // text chat 2
                        new String[] { "|-1|Lũ khốn..",
                                        "|-1|..Một ngày nào đó ta sẽ quay lại và trả thù các ngươi",
                                        "|-1|Nhớ mặt tao đấy !", }, // text chat 3
                        TypeAppear.ANOTHER_LEVEL // type appear
        );

        // **************************************************************************
        // Boss TINHd
        public static final BossData DR_KORE = new BossData(
                        "Dr.Kôrê", // name
                        ConstPlayer.TRAI_DAT, // gender
                        new short[] { 255, 256, 257, -1, -1, -1 }, // outfit {head, body, leg, bag, aura, eff}
                        50000, // dame
                        new int[] { 300000000 }, // hp
                        new int[] { 96, 94, 93 }, // map join
                        new int[][] {
                                        { Skill.THOI_MIEN, 3, 100000 },
                                        { Skill.KAMEJOKO, 7, 1000 },
                                        { Skill.LIEN_HOAN, 7, 300 }, }, // skill
                        // skill
                        new String[] {}, // text chat 1
                        new String[] { "|-1|Oải rồi hả?",
                                        "|-1|Ê cố lên nhóc",
                                        "|-1|Chán",
                                        "|-1|Mi khá đấy, nhưng so với ta cũng chỉ là hạng tôm tép",
                                        "|-1|Lôi Công Trảo",
                                        "|-1|Cho dù ngươi có mạnh đến đâu.. thì cũng không đánh bại được rôbốt bọn ta",
                                        "|-2|Lão già khôn thật!!",
                                        "|-2|Hừ! Lão già khốn kiếp!", }, // text chat 2
                        new String[] {}, // text chat 3
                        TypeAppear.APPEAR_WITH_ANOTHER);

        public static final BossData ANDROID_19 = new BossData(
                        "Android 19", // name
                        ConstPlayer.TRAI_DAT, // gender
                        new short[] { 249, 250, 251, -1, -1, -1 }, // outfit {head, body, leg, bag, aura, eff}
                        50000, // dame
                        new int[] { 300000000 }, // hp
                        new int[] { 96, 94, 93 }, // map join
                        new int[][] {
                                        { Skill.KAMEJOKO, 7, 1000 },
                                        { Skill.LIEN_HOAN, 7, 300 }, }, // skill//skill
                        new String[] {}, // text chat 1
                        new String[] { "|-1|Oải rồi hả?",
                                        "|-1|Ê cố lên nhóc",
                                        "|-1|Chán",
                                        "|-1|Ngươi sẽ không bao giờ thắng được đâu!!",
                                        "|-2|Ngươi vừa hút được nhiều rồi đấy, nhưng giờ thì đừng hòng!!", }, // text
                                                                                                              // chat 2
                        new String[] {}, // text chat 3
                        REST_10_M, // second rest
                        new int[] { BossID.DR_KORE });

        // **************************************************************************
        public static final BossData ANDROID_13 = new BossData(
                        "Android 13", // name
                        ConstPlayer.TRAI_DAT, // gender
                        new short[] { 252, 253, 254, -1, -1, -1 }, // outfit {head, body, leg, bag, aura, eff}
                        32550, // dame
                        new int[] { 100000000 }, // hp
                        new int[] { 84, 104 }, // map join
                        new int[][] {
                                        { Skill.KAMEJOKO, 7, 1000 },
                                        { Skill.LIEN_HOAN, 7, 300 }, }, // skill
                        new String[] {}, // text chat 1
                        new String[] { "|-1|Sao thế hả? Ta mới chỉ khởi động thôi mà!",
                                        "|-2|Ngươi đánh giá thấp bọn ta quá đấy!",
                                        "|-2|Đừng có tưởng bở, lũ sâu bọ!",
                                        "|-1|Nếu có ý định gây trở ngại cho cuộc chiến giữa ta và Sôngôku, thì ta cũng sẽ giết ngươi ngay lập tức",
                                        "|-2|Ngươi tưởng ta để cho ngươi giết được ta ngay à?",
                                        "|-2|Đúng là mạnh mồm thật đấy!",
                                        "|-2|Đỡ này", }, // text chat 2
                        new String[] { "|-1|Sô..Sông...gôku....." }, // text chat 3
                        TypeAppear.CALL_BY_ANOTHER);

        public static final BossData ANDROID_14 = new BossData(
                        "Android 14", // name
                        ConstPlayer.TRAI_DAT, // gender
                        new short[] { 246, 247, 248, -1, -1, -1 }, // outfit {head, body, leg, bag, aura, eff}
                        30000, // dame
                        new int[] { 100000000 }, // hp
                        new int[] { 84, 104 }, // map join
                        new int[][] {
                                        { Skill.KAMEJOKO, 7, 1000 },
                                        { Skill.LIEN_HOAN, 7, 300 }, }, // skill
                        new String[] {}, // text chat 1
                        new String[] {}, // text chat 2
                        new String[] { "|0|Số 14 và số 15 tiêu tùng cả rồi à?" }, // text chat 3
                        REST_10_M,
                        new int[] { BossID.ANDROID_13, BossID.ANDROID_15 });

        public static final BossData ANDROID_15 = new BossData(
                        "Android 15", // name
                        ConstPlayer.TRAI_DAT, // gender
                        new short[] { 261, 262, 263, -1, -1, -1 }, // outfit {head, body, leg, bag, aura, eff}
                        39200, // dame
                        new int[] { 100000000 }, // hp
                        new int[] { 84, 104 }, // map join
                        new int[][] {
                                        { Skill.KAMEJOKO, 7, 1000 },
                                        { Skill.LIEN_HOAN, 7, 300 }, }, // skill//skill
                        new String[] {}, // text chat 1
                        new String[] {}, // text chat 2
                        new String[] { "|-2|Thì ra vẫn chỉ là một đống sắt vụn!" }, // text chat 3
                        TypeAppear.APPEAR_WITH_ANOTHER);
        // **************************************************************************

        public static final BossData PIC = new BossData(
                        "Số 17 Pic", // name
                        ConstPlayer.TRAI_DAT, // gender
                        new short[] { 237, 238, 239, -1, -1, -1 }, // outfit {head, body, leg, bag, aura, eff}
                        50000, // dame
                        new int[] { 400000000 }, // hp
                        new int[] { 97, 98, 99 }, // map join
                        new int[][] {
                                        { Skill.KAMEJOKO, 7, 1000 },
                                        { Skill.KAMEJOKO, 7, 1000 },
                                        { Skill.LIEN_HOAN, 7, 300 },
                                        { Skill.DICH_CHUYEN_TUC_THOI, 7, 30000 },
                        }, // skill
                        new String[] {}, // text chat 1
                        new String[] { "|-1|Ngươi thực sự rất mạnh dù không phải là một rôbốt. Ngươi không phải là Piccôlô",
                                        "|-1|Nhưng ta không quan tâm ngươi là ai, ta chỉ cần biết Gôku đang ở đâu!",
                                        "|-1|Sao ngươi không chịu nói cho ta biết chứ!?",
                                        "|-2|Mục đích của ngươi không phải là giết Gôku sao? Vì vậy ta không nói cho ngươi biết cậu ấy đang ở đâu",
                                        "|-1|Vậy thì ta bắt buộc phải tiếp tục đánh buộc ngươi phải nói ra!",
                                        "|-1|Lần này ta không nương tay đâu!",
                                        "|-2|Ngươi thực sự rất nhanh. Nhưng chưa đủ thực lực đâu!!",
                                        "|-1|Cái gì!? Đó là điều ngu ngốc nhất ta từng nghe.. ta là chiến binh mạnh nhất từ trước đến giờ.!",
                                        "|1|Hắn thực sự rất mạnh, đúng là cuộc chiến cân sức",
                                        "|-3|Pic, trả nhẽ cậu lại để thua mấy tên nhóc vặt này sao?"
                        }, // text chat 2
                        new String[] { "|1|Pic tiêu rồi, tớ lên trước nhé!",
                                        "|-3|Okê, xin cứ tự nhiên"
                        }, // text chat 3
                        TypeAppear.APPEAR_WITH_ANOTHER);

        public static final BossData POC = new BossData(
                        "Số 18 Poc", // name
                        ConstPlayer.TRAI_DAT, // gender
                        new short[] { 240, 241, 242, -1, -1, -1 }, // outfit {head, body, leg, bag, aura, eff}
                        50000, // dame
                        new int[] { 400000000 }, // hp
                        new int[] { 97, 98, 99 }, // map join
                        new int[][] {
                                        { Skill.KAMEJOKO, 7, 1000 },
                                        { Skill.KAMEJOKO, 7, 1000 },
                                        { Skill.LIEN_HOAN, 7, 300 },
                        }, // skill
                        new String[] {}, // text chat 1
                        new String[] { "|-1|Đừng tưởng ta đây là con gái mà dễ bắt nạt nhé",
                                        "|-1|Khôn hồn thì chỉ chỗ Gôku cho bọn ta nhanh đi",
                                        "|-3|Coi kìa, một lũ xúm lại bắt nạt một cô gái kìa..",
                                        "|-1|Đừng có mà trọng nam khinh nữ",
                                        "|-2|Tại sao cô gái xinh đẹp thế này mà lại là rôbốt nhỉ?"
                        }, // text chat 2
                        new String[] { "|-2|Cô gái xinh đẹp vậy mà lại bị tên tiến sĩ Kôrê biến thành người máy.." }, // text
                                                                                                                      // chat
                                                                                                                      // 3
                        TypeAppear.APPEAR_WITH_ANOTHER);

        public static final BossData KING_KONG = new BossData(
                        "Số 16 King Kong", // name
                        ConstPlayer.TRAI_DAT, // gender
                        new short[] { 243, 244, 245, -1, -1, -1 }, // outfit {head, body, leg, bag, aura, eff}
                        50000, // dame
                        new int[] { 400000000 }, // hp
                        new int[] { 97, 98, 99 }, // map join
                        new int[][] {
                                        { Skill.KAMEJOKO, 7, 1000 },
                                        { Skill.KAMEJOKO, 7, 1000 },
                                        { Skill.LIEN_HOAN, 7, 300 },
                        }, // skill
                        new String[] {}, // text chat 1
                        new String[] { "|-1|Mau đền mạng cho những người bạn của ta",
                                        "|-1|Sức mạnh của ta chênh nhau với các ngươi một trời một vực đấy!",
                                        "|-1|Thằng kia đừng để bọn nó trói tao !"
                        }, // text chat 2
                        new String[] {}, // text chat 3
                        REST_10_M,
                        new int[] { BossID.PIC, BossID.POC });
        // **************************************************************************
        // Boss cell

        public static final BossData XEN_BO_HUNG_1 = new BossData(
                        "Xên bọ hung",
                        ConstPlayer.XAYDA,
                        new short[] { 228, 229, 230, -1, -1, -1 },
                        80000,
                        new int[] { 500000000 },
                        new int[] { 100 },
                        new int[][] {
                                        { Skill.KAMEJOKO, 7, 1000 },
                                        { Skill.LIEN_HOAN, 7, 300 },
                                        { Skill.DICH_CHUYEN_TUC_THOI, 2, 60000 },
                                        { Skill.THAI_DUONG_HA_SAN, 7, 30000 },
                        }, // skill
                        new String[] {}, // text chat 1
                        new String[] { "|-2|Hắn làm ta bất ngờ đấy! Khốn kiếp!",
                                        "|-2|Tên đó muốn hấp thụ số 17 và 18 sao?",
                                        "|-1|Đến đây nào! Khi kết hợp với ta, ngươi sẽ trở nên bất bại, trở thành một thể sống hoàn mỹ!",
                                        "|-2|Mơ đi, sao ta phải để ngươi hấp thu hả!?",
                                        "|-2|Dù muốn hay không, ngươi cũng sẽ bị ta hấp thu thôi..",
                                        "|-2|Chúng ta không thể để hắn đạt đến dạng hoàn hảo được!",
                                        "|-2|Mục tiêu của hắn không phải là Sôngôku.., mà là phá hủy cả vũ trụ này!",
                                        "|-1|Làm đứt đuôi ta ư? Đừng quên ta có tế bào của Picôlô!!",
                                        "|-1|Ta có thể tái tạo.. mọi bộ phận cơ thể!",
                                        "|-2|Vậy thì để ngăn cản ngươi đạt đến dạng hoàn hảo, ta phải tiêu diệt ngươi!",
                                        "|-2|Hắn quá mạnh! Mình có thể làm được gì đây!?",
                                        "|-1|Có vẻ như ta đã trở nên quá mạnh, chắc là ta đã giết nhiều người hơn dự tính!!",
                                        "|-1|Ngươi không thể thắng nổi ta! Từ bỏ đi!!",
                                        "|-1|Đến lúc ta hấp thu ngươi rồi",
                                        "|-2|Đồ quái vật chết tiệt...",
                                        "|-1|Hê hê hê, rồi ngươi sẽ trở thành một phần của con quái vật này thôi!",
                                        "|-1|Lại thêm một tên ngốc nữa chán sống!"
                        }, // text chat 2
                        new String[] { "|-2|Khốn kiếp, Pic.. hắn bị Cell hấp thu rồi!!" }, // text chat 3
                        REST_10_M);

        public static final BossData XEN_BO_HUNG_2 = new BossData(
                        "Xên bọ hung 2",
                        ConstPlayer.XAYDA,
                        new short[] { 231, 232, 233, -1, -1, -1 },
                        100000,
                        new int[] { 600000000 },
                        new int[] { 100 },
                        new int[][] {
                                        { Skill.DICH_CHUYEN_TUC_THOI, 2, 60000 },
                                        { Skill.KAMEJOKO, 7, 1000 },
                                        { Skill.THAI_DUONG_HA_SAN, 7, 30000 },
                                        { Skill.LIEN_HOAN, 7, 300 } }, // skill
                        new String[] {}, // text chat 1
                        new String[] { "|-2|Nguy rồi... thực sự nguy to rồi!",
                                        "|-1|Các ngươi nghĩ có thể chạy được sao!?",
                                        "|-1|Muốn chạy khỏi ta thì đừng hòng!!",
                                        "|-1|Ta cũng ngạc nhiên với tốc độ của mình! Đó tất nhiên là do ta hấp thụ được số 17!",
                                        "|-2|Hắn nhanh quá!!",
                                        "|-1|Ta muốn thử xem sức mạnh này đến đâu...",
                                        "|-1|Hmm.. có vẻ như sức mạnh này đã tăng lên gấp bội!",
                                        "|-1|Đã đến lúc ta đạt đến trạng thái hoàn hảo.!",
                                        "|-1|Có vẻ như ngươi muốn bị ép hơn là tự nguyện!!",
                                        "|-2|Bây giờ ta chưa thể thắng được ngươi!! Nhưng ngươi đừng hòng huyênh hoang!!!",
                                        "|-1|Muốn chạy à!!? Ta sẽ không để ngươi thoát đâu!!", }, // text chat 2
                        new String[] { "|-1|Đến lúc rồi!" }, // text chat 3
                        TypeAppear.ANOTHER_LEVEL);

        public static final BossData XEN_BO_HUNG_3 = new BossData(
                        "Xên hoàn thiện",
                        ConstPlayer.XAYDA,
                        new short[] { 234, 235, 236, -1, -1, -1 },
                        120000,
                        new int[] { 700000000 },
                        new int[] { 100 },
                        new int[][] {
                                        { Skill.KAMEJOKO, 7, 1300 },
                                        { Skill.THAI_DUONG_HA_SAN, 7, 40000 },
                                        { Skill.DICH_CHUYEN_TUC_THOI, 2, 60000 },
                                        { Skill.KHIEN_NANG_LUONG, 7, 100000 },
                                        { Skill.LIEN_HOAN, 7, 300 },
                                        { Skill.THOI_MIEN, 7, 100000 } },
                        // skill

                        new String[] {}, // text chat 1
                        new String[] { "|-2|Cell đã đạt đến dạng hoàn hảo rồi!",
                                        "|-2|Đồ khốn, sao ngươi dám làm vậy với số 18!!",
                                        "|-2|Không ấn tượng lắm với dạng hoàn hảo của ngươi..",
                                        "|-2|Sao hắn không hề hấn gì?",
                                        "|-1|Xin lỗi.. Ngươi có thể giúp ta làm nóng cơ thể lên không !?",
                                        "|-2|Tình hình nguy cấp rồi!",
                                        "|-2|Khốn kiếp! Ngươi không chú tâm vào trận đấu!",
                                        "|-1|Thì ta đã bảo đây chỉ là làm nóng cơ thể mà!!",
                                        "|-1|Giờ ngươi chỉ là rác rưởi mà thôi!",
                                        "|-2|Không thể nào! Ngươi dù sao cũng chỉ là đồ sâu bọ!", }, // text chat 2
                        new String[] { "|-1|Oái.. không...",
                                        "|-1|Cơ thể hoàn hảo của ta!!",
                                        "|-1|Ta không tin chuyện này sẽ xảy ra!!",
                                        "|-1|Đồ khốn kiếp!! Rồi ngươi sẽ phải trả giá"
                        }, // text chat 3
                        TypeAppear.ANOTHER_LEVEL);// **************************************************************************
                                                  // *************
        public static final BossData SIEU_BO_HUNG_1 = new BossData(
                        "Xên Hoàn Thiện",
                        ConstPlayer.XAYDA,
                        new short[] { 234, 235, 236, -1, -1, -1 },
                        130000,
                        new int[] { 700000000 },
                        new int[] { 103 },
                        new int[][] {
                                        { Skill.LIEN_HOAN, 7, 300 },
                                        { Skill.THOI_MIEN, 7, 100000 },
                                        { Skill.DICH_CHUYEN_TUC_THOI, 2, 60000 },
                                        { Skill.KAMEJOKO, 7, 1000 },
                                        { Skill.DICH_CHUYEN_TUC_THOI, 7, 100000 },
                                        { Skill.THAI_DUONG_HA_SAN, 7, 100000 } },
                        // skill
                        new String[] {}, // text chat 1
                        new String[] { "|-1|Đến đây",
                                        "|-1|Làm tốt lắm! Thế mới là chiến đấu chứ",
                                        "|-1|Chẳng hay ho gì nếu không dồn hết sức vào trận đấu!",
                                        "|-1|Ngươi được lắm, thực sự rất khá đấy...",
                                        "|-1|Cảnh báo với ngươi là ta sẽ không thất bại như lúc nãy nữa đâu!!",
                                        "|-1|Giờ mà ngươi phí sức vào những đòn tấn công vô ích thì trận đấu sẽ vô vị lắm đấy!!",
                                        "|-1|Ta muốn ngươi giải trí cho ta thêm chút nữa!!",
                                        "|-1|Nếu không còn ai tham dự trò chơi của Cell,.. thì toàn bộ cư dân trái đất sẽ bị tiêu diệt!"
                        }, // text chat 2
                        new String[] { "|-1|Hô hô hô, đây sẽ là kết thúc của lũ ngu ngốc các ngươi!! Ta sẽ chết nhưng sẽ kéo theo cái hành tinh này luôn" }, // text
                                                                                                                                                             // chat
                                                                                                                                                             // 3
                        REST_10_M);

        public static final BossData SIEU_BO_HUNG_2 = new BossData(
                        "Xên Hoàn Thiện",
                        ConstPlayer.XAYDA,
                        new short[] { 234, 235, 236, -1, -1, -1 },
                        140000,
                        new int[] { 800000000 },
                        new int[] { 103 },
                        new int[][] {

                                        { Skill.KAMEJOKO, 7, 6 }, { Skill.KAMEJOKO, 6, 7 }, { Skill.KAMEJOKO, 5, 8 },
                                        { Skill.KAMEJOKO, 4, 9 }, { Skill.KAMEJOKO, 3, 10 }, { Skill.KAMEJOKO, 2, 11 },
                                        { Skill.KAMEJOKO, 1, 12 },
                                        { Skill.ANTOMIC, 1, 13 }, { Skill.ANTOMIC, 2, 14 }, { Skill.ANTOMIC, 3, 15 },
                                        { Skill.ANTOMIC, 4, 16 }, { Skill.ANTOMIC, 5, 17 }, { Skill.ANTOMIC, 6, 19 },
                                        { Skill.ANTOMIC, 7, 20 },
                                        { Skill.MASENKO, 1, 21 }, { Skill.MASENKO, 5, 22 }, { Skill.MASENKO, 6, 23 },
                                        { Skill.DICH_CHUYEN_TUC_THOI, 7, 100000 },
                                        { Skill.THAI_DUONG_HA_SAN, 7, 150000 },
                                        { Skill.LIEN_HOAN, 7, 300 } }, // skill
                        new String[] {}, // text chat 1
                        new String[] { "|-1|Đến đây",
                                        "|-1|Làm tốt lắm! Thế mới là chiến đấu chứ",
                                        "|-1|Chẳng hay ho gì nếu không dồn hết sức vào trận đấu!",
                                        "|-1|Ngươi được lắm, thực sự rất khá đấy...",
                                        "|-1|Cảnh báo với ngươi là ta sẽ không thất bại như lúc nãy nữa đâu!!",
                                        "|-1|Giờ mà ngươi phí sức vào những đòn tấn công vô ích thì trận đấu sẽ vô vị lắm đấy!!",
                                        "|-1|Ta muốn ngươi giải trí cho ta thêm chút nữa!!",
                                        "|-1|Nếu không còn ai tham dự trò chơi của Cell,.. thì toàn bộ cư dân trái đất sẽ bị tiêu diệt!" }, // text
                                                                                                                                            // chat
                                                                                                                                            // 2
                        new String[] {}, // text chat 3
                        TypeAppear.ANOTHER_LEVEL);

        public static final BossData SIEU_BO_HUNG_3 = new BossData(
                        "Siêu Xên Hoàn Thiện",
                        ConstPlayer.XAYDA,
                        new short[] { 234, 235, 236, -1, -1, -1 },
                        150000,
                        new int[] { 1000000000 },
                        new int[] { 103 },
                        new int[][] {
                                        { Skill.DEMON, 3, 1 }, { Skill.DEMON, 6, 2 }, { Skill.DRAGON, 7, 3 },
                                        { Skill.DRAGON, 1, 4 }, { Skill.GALICK, 5, 5 },
                                        { Skill.KAMEJOKO, 7, 6 }, { Skill.KAMEJOKO, 6, 7 }, { Skill.KAMEJOKO, 5, 8 },
                                        { Skill.KAMEJOKO, 4, 9 }, { Skill.KAMEJOKO, 3, 10 }, { Skill.KAMEJOKO, 2, 11 },
                                        { Skill.KAMEJOKO, 1, 12 },
                                        { Skill.ANTOMIC, 1, 13 }, { Skill.ANTOMIC, 2, 14 }, { Skill.ANTOMIC, 3, 15 },
                                        { Skill.ANTOMIC, 4, 16 }, { Skill.ANTOMIC, 5, 17 }, { Skill.ANTOMIC, 6, 19 },
                                        { Skill.ANTOMIC, 7, 20 },
                                        { Skill.MASENKO, 1, 21 }, { Skill.MASENKO, 5, 22 }, { Skill.MASENKO, 6, 23 },
                                        { Skill.KAMEJOKO, 7, 500 },
                                        { Skill.QUA_CAU_KENH_KHI, 3, 300000 },
                                        { Skill.DICH_CHUYEN_TUC_THOI, 2, 60000 },
                                        { Skill.DICH_CHUYEN_TUC_THOI, 7, 100000 },
                                        { Skill.KHIEN_NANG_LUONG, 7, 100000 },
                                        { Skill.THOI_MIEN, 7, 30000 } },
                        // skill
                        new String[] {}, // text chat 1
                        new String[] { "|-1|Đến đây",
                                        "|-1|Ta sẽ không chừa đứa nào trong bọn bây",
                                        "|-1|Hãy tan thành cát bụi cùng với hành tinh này!!",
                                        "|-1|Ha ha ha! Các ngươi thích thế này chứ hả?!",
                                        "|-2|Ta hận sức mạnh của chúng ta không đủ đánh bại hắn!!",
                                        "|-2|Thật khốn kiếp!!!",
                                        "|-2|Tấn công đi!! Ta biết mình không còn đủ sức nữa...",
                                        "|-2|Nhưng ta vẫn muốn tiêu diệt ngươi!!!", }, // text chat 2
                        new String[] { "|-1|Aahhh...",
                                        "|-1|Không thể nào... Điều này không thể nào xảy ra...",
                                        "|-1|Ta không tin...",
                                        "|-1|Ta là bấ...", }, // text chat 3
                        TypeAppear.ANOTHER_LEVEL);
        // **************************************************************************Boss
        // doanh trại
        public static final BossData TRUNG_UY_TRANG = new BossData(
                        "Trung úy trắng", // name
                        ConstPlayer.TRAI_DAT, // gender
                        new short[] { 141, 142, 143, -1, -1, -1 }, // outfit {head, body, leg, bag, aura, eff}
                        1200, // dame
                        new int[] { 150000 }, // hp
                        new int[] { 62 }, // map join
                        (int[][]) Util.addArray(FULL_DEMON), // skill
                        new String[] {}, // text chat 1
                        new String[] { "|-1|Nhóc con" }, // text chat 2
                        new String[] {}, // text chat 3
                        REST_10_S);
        // **************************************************************************
        public static final BossData COOLER_GOLD = new BossData(
                        "Cooler Vàng", // name
                        ConstPlayer.XAYDA, // gender
                        new short[] { 709, 710, 711, -1, -1, -1 }, // outfit {head, body, leg, bag, aura, eff}
                        150000, // dame
                        new int[] { 1000000000 }, // hp
                        new int[] { 155, 177, 178 }, // map join
                        new int[][] {
                                        { Skill.THOI_MIEN, 7, 100000 },
                                        { Skill.DICH_CHUYEN_TUC_THOI, 7, 30000 },
                                        { Skill.KAMEJOKO, 7, 1000 },
                                        { Skill.LIEN_HOAN, 4, 300 },
                                        { Skill.DICH_CHUYEN_TUC_THOI, 1, 60000 },
                                        { Skill.MASENKO, 7, 10000 } }, // skill
                        new String[] {}, // text chat 1
                        new String[] { "|-1|Tao hơn hẳn mày, mày nên cầu cho may mắn ở phía mày đi",
                                        "|-1|Ghê chưa ghê chưa!",
                                        "|-1|Tao có rất nhiều vật phẩm quý giá,nhưng với mày thì có cái..nịt",
                                        "|-1|Đánh tao à,lo mà luyện tập thêm đi",
                                        "|-1|Nói cho mày biết,tao là anh trai của Fide",
                                        "|-1|trạng thái Goldend Meta Cooler sẽ thiêu rụi mày"
                        }, // text chat 2
                        new String[] { "|-2|Đêm qua em đẹp lắm!" }, // text chat 3
                        REST_15_M // second rest
        );

        public static final BossData CUMBER = new BossData(
                        "Sayan Tà Ác Cumber", // name
                        ConstPlayer.XAYDA, // gender
                        new short[] { 1207, 1208, 1209, -1, 0, -1 }, // outfit {head, body, leg, bag, aura, eff}
                        200000, // dame
                        new int[] { 1500000000 }, // hp
                        new int[] { 155, 177, 178 }, // map join
                        new int[][] {
                                        { Skill.LIEN_HOAN, 7, 300 },
                                        { Skill.ANTOMIC, 7, 1000 },
                                        { Skill.DICH_CHUYEN_TUC_THOI, 1, 60000 }
                        }, // skill
                        new String[] {}, // text chat 1
                        new String[] { "|-1|Ta muốn tìm một đối thủ xứng tầm",
                                        "|-1|Đi chết đi!",
                                        "|-1|Các ngươi không phải đối thủ của ta đâu",
                                        "|-1|trạng thái Tà Ác sẽ thiêu rụi mày"
                        }, // text chat 2
                        new String[] { "|-2|Tên đó mạnh thật!" }, // text chat 3
                        REST_15_M // second rest
        );
        // **************************************************************************
        public static final BossData XEN_CON = new BossData(
                        "Xên con", // name
                        ConstPlayer.XAYDA, // gender
                        new short[] { 264, 265, 266, -1, -1, -1 }, // outfit {head, body, leg, bag, aura, eff}
                        150000, // dame
                        new int[] { 700000000 }, // hp
                        new int[] { 103 }, // map join
                        (int[][]) Util.addArray(FULL_DEMON, FULL_MASENKO), // skill
                        new String[] {}, // text chat 1
                        new String[] { "|-1|Tao hơn hẳn mày, mày nên cầu cho may mắn ở phía mày đi",
                                        "|-1|Ghê chưa ghê chưa!",
                                        "|-1|Tao có rất nhiều vật phẩm quý giá,nhưng với mày thì có cái..nịt",
                                        "|-1|Đánh tao à,lo mà luyện tập thêm đi",
                                        "|-1|Nói cho mày biết,tao là con của Xên ",
                                        "|-1|Tao sẽ thiêu rụi mày"
                        }, // text chat 2
                        new String[] { "|-2|Đêm qua em đẹp lắm!" }, // text chat 3
                        REST_1_M // second rest
        );
        // **************************************************************************

        // **************************************************************************cha
        // con fide
        public static final BossData VUA_COLD = new BossData(
                        "Thống Chế King COLD",
                        ConstPlayer.XAYDA,
                        new short[] { 712, 713, 714, -1, -1, -1 },
                        170000,
                        new int[] { 600000000 },
                        new int[] { 111 },
                        (int[][]) Util.addArray(FULL_KAMEJOKO, FULL_LIENHOAN), // skill
                        new String[] {}, // text chat 1
                        new String[] { "|-1|Thì ra đây là trái đất",
                                        "|-1|Hành tinh này bán đi chắc cũng kha khá đó!",
                                        "|-2|Ngươi làm ta khó chịu rồi đấy",
                                        "|-1|Ngươi sẽ không bao giờ thắng được đâu!!",
                                        "|-2|Tên này mạnh quá", }, // text chat 2
                        new String[] { "|-1|Xin hãy tha cho ta !",
                                        "|-1|Ta sẽ cho ngươi nửa số hành tinh ta đang giữ!",
                                        "|-1|Đừng màaa!" }, // text chat 3
                        REST_30_M, // second rest
                        new int[] { BossID.FIDE_ROBOT });

        public static final BossData FIDE_ROBOT = new BossData(
                        "Fide người máy", // name
                        ConstPlayer.TRAI_DAT, // gender
                        new short[] { 189, 190, 191, -1, -1, -1 }, // outfit {head, body, leg, bag, aura, eff}
                        180200, // dame
                        new int[] { 800000000 }, // hp
                        new int[] { 0, 1, 2, 3, 4 }, // map join
                        (int[][]) Util.addArray(FULL_GALICK, FULL_ANTOMIC), // skill
                        new String[] {}, // text chat 1
                        new String[] { "|-1|Haaaahaa",
                                        "|-1|Chúng ta sẽ hủy diệt hành tinh này",
                                        "|-1|Tên Sôn gô ku mãi vẫn chưa tới",
                                        "|-1|Ngươi sẽ không bao giờ thắng được đâu!!",
                                        "|-2|Để xem ai mới là người chiến thắng!!", }, // text chat 2
                        new String[] { "|-1|Ta thua rồi sao? Khôngggggggg!" }, // text chat 3
                        TypeAppear.APPEAR_WITH_ANOTHER);

        // **************************************************************************
        // **************************************************************************
        public static final BossData XUKA = new BossData(
                        "Cô Bé Shizuka", // name
                        ConstPlayer.XAYDA, // gender
                        new short[] { 802, 803, 804, -1, -1, -1 }, // outfit {head, body, leg, bag, aura, eff}
                        51111, // dame
                        new int[] { 200000000 }, // hp
                        new int[] { 6, 30 }, // map join
                        new int[][] {
                                        { Skill.MASENKO, 7, 1000 },

                                        { Skill.LIEN_HOAN, 7, 300 },
                                        { Skill.THAI_DUONG_HA_SAN, 7, 30000 },
                        }, // skill
                        new String[] {}, // text chat 1
                        new String[] { "|-1|Oải rồi hả?", "|-1|Ê cố lên nhóc",
                                        "|-1|Chán", "|-1|Đại ca Khoa có nhầm không nhỉ",
                                        "|-1|Các ngươi không nhúc nhích được sao?",
                                        "|-1|HAHAHAHA", "|-1|Chỉ là bọn con nít"
                        }, // text chat 2
                        new String[] {}, // text chat 3
                        TypeAppear.APPEAR_WITH_ANOTHER // type appear
        );

        public static final BossData XEKO = new BossData(
                        "Mõm Nhọn Suneo", // name
                        ConstPlayer.XAYDA, // gender
                        new short[] { 850, 851, 852, -1, -1, -1 }, // outfit {head, body, leg, bag, aura, eff}
                        51111, // dame
                        new int[] { 200000000 }, // hp
                        new int[] { 6, 30 }, // map join
                        new int[][] {
                                        { Skill.DICH_CHUYEN_TUC_THOI, 7, 100000 },
                                        { Skill.KAMEJOKO, 7, 1000 },
                                        { Skill.LIEN_HOAN, 7, 300 },
                                        { Skill.THAI_DUONG_HA_SAN, 7, 30000 },
                        }, // skill
                        new String[] {}, // text chat 1
                        new String[] { "|-1|Oải rồi hả?", "|-1|Ê cố lên nhóc",
                                        "|-1|Chán", "|-1|Đại ca có nhầm không nhỉ",
                                        "|-1|Một mình tao chấp hết tụi bây",
                                        "|-1|HAHAHAHA", "|-1|Chỉ là bọn con nít"
                        }, // text chat 2
                        new String[] {}, // text chat 3
                        TypeAppear.APPEAR_WITH_ANOTHER // type appear
        );

        public static final BossData CHAIEN = new BossData(
                        "Khỉ Đột Chaien", // name
                        ConstPlayer.XAYDA, // gender
                        new short[] { 847, 848, 849, -1, -1, -1 }, // outfit {head, body, leg, bag, aura, eff}
                        51111, // dame
                        new int[] { 200000000 }, // hp
                        new int[] { 6, 30 }, // map join
                        new int[][] {
                                        { Skill.GALICK, 7, 300 },
                                        { Skill.KAMEJOKO, 7, 10000 },
                                        { Skill.BIEN_KHI, 1, 600000 },
                                        { Skill.THAI_DUONG_HA_SAN, 7, 30000 },
                        }, // skill//skill
                        new String[] {}, // text chat 1
                        new String[] { "|-1|Oải rồi hả?", "|-1|Ê cố lên nhóc",
                                        "|-1|Chán", "|-1|Đại ca có nhầm không nhỉ",
                                        "|-1|Một mình tao chấp hết tụi bây",
                                        "|-1|HAHAHAHA", "|-1|Chỉ là bọn con nít"
                        }, // text chat 2
                        new String[] {}, // text chat 3
                        TypeAppear.APPEAR_WITH_ANOTHER // type appear
        );

        public static final BossData NOBITA = new BossData(
                        "Chú Bé Đần Nobita", // name
                        ConstPlayer.XAYDA, // gender
                        new short[] { 844, 845, 846, -1, -1, -1 }, // outfit {head, body, leg, bag, aura, eff}
                        51111, // dame
                        new int[] { 200000000 }, // hp
                        new int[] { 6, 30 }, // map join
                        new int[][] {
                                        { Skill.LIEN_HOAN, 7, 600 },
                                        { Skill.MASENKO, 7, 1000 },
                                        { Skill.ANTOMIC, 7, 10000 },
                        }, // skill//skill
                        new String[] {}, // text chat 1
                        new String[] { "|-1|Oải rồi hả?", "|-1|Ê cố lên nhóc",
                                        "|-1|Chán", "|-1|Đại ca Doraemon có nhầm không nhỉ",
                                        "|-1|Một mình tao chấp hết tụi bây",
                                        "|-1|HAHAHAHA", "|-1|Chỉ là bọn con nít"
                        }, // text chat 2
                        new String[] {}, // text chat 3
                        TypeAppear.APPEAR_WITH_ANOTHER // type appear
        );

        public static final BossData DORAEMON = new BossData(
                        "Người Máy Doraemon",
                        ConstPlayer.XAYDA,
                        new short[] { 790, 791, 792, -1, -1, -1 },
                        50000,
                        new int[] { 200000000 },
                        new int[] { 6, 30 },
                        // new int[]{14},
                        new int[][] {

                                        { Skill.DRAGON, 7, 20 },
                                        { Skill.DICH_CHUYEN_TUC_THOI, 7, 30000 },
                        }, // skill//skill
                        new String[] {}, // text chat 1
                        new String[] {}, // text chat 2
                        new String[] {}, // text chat 3
                        REST_10_M // second rest
        );

        // **************************************************************************

        // **************************************************************************
        public static final BossData BLACK_GOKU_BASE = new BossData(
                        "Black Goku", // name
                        ConstPlayer.TRAI_DAT, // gender
                        new short[] { 879, 880, 881, -1, -1, -1 }, // outfit {head, body, leg, bag, aura, eff}
                        260000, // dame
                        new int[] { 700000000 }, // hp
                        new int[] { 0, 1, 2, 3, 4, 5, 6, 27, 28, 29 }, // map join
                        new int[][] {
                                        { Skill.KAMEJOKO, 7, 1000 },
                                        { Skill.QUA_CAU_KENH_KHI, 3, 30000 },
                                        { Skill.LIEN_HOAN, 7, 300 },
                                        { Skill.DICH_CHUYEN_TUC_THOI, 2, 60000 },
                                        { Skill.DICH_CHUYEN_TUC_THOI, 7, 30000 },
                                        { Skill.THAI_DUONG_HA_SAN, 7, 30000 },
                        }, // skill
                        new String[] {}, // text chat 1
                        new String[] { "|-1|Các ngươi chỉ có vậy thôi sao?",
                                        "|-1|Đúng là loài người thấp kém",
                                        "|-2|Ngươi nói như thể ngươi không phải con người vậy?",
                                        "|-2|Chiếc nhẫn kia lẽ nào ngươi là một Kaioshin?!",
                                        "|-1|Các ngươi không nên biết quá nhiều",
                                        "|-2|Xem đòn đánh của ta đây !",
                                        "|-1|Được thôi, nếu muốn chết đến vậy, ta rất vui lòng!!"
                        }, // text chat 2
                        new String[] { "|-1|Biến hình! Super Sayan Rose" }, // text chat 3
                        REST_10_M // second rest
        );
        // **************************************************************************
        public static final BossData BLACK_GOKU = new BossData(
                        "Black Goku", // name
                        ConstPlayer.TRAI_DAT, // gender
                        new short[] { 879, 880, 881, -1, -1, -1 }, // outfit {head, body, leg, bag, aura, eff}
                        270000, // dame
                        new int[] { 700000000 }, // hp
                        new int[] { 102, 92, 93, 94, 96, 97, 98, 99, 100 }, // map join
                        new int[][] {
                                        { Skill.KAMEJOKO, 7, 1000 },
                                        { Skill.QUA_CAU_KENH_KHI, 3, 30000 },
                                        { Skill.LIEN_HOAN, 7, 300 },
                                        { Skill.DICH_CHUYEN_TUC_THOI, 2, 60000 },
                                        { Skill.DICH_CHUYEN_TUC_THOI, 7, 30000 },
                                        { Skill.THAI_DUONG_HA_SAN, 7, 30000 },
                        }, // skill
                        new String[] {}, // text chat 1
                        new String[] { "|-1|Các ngươi chỉ có vậy thôi sao?",
                                        "|-1|Đúng là loài người thấp kém",
                                        "|-2|Ngươi nói như thể ngươi không phải con người vậy?",
                                        "|-2|Chiếc nhẫn kia lẽ nào ngươi là một Kaioshin?!",
                                        "|-1|Các ngươi không nên biết quá nhiều",
                                        "|-2|Xem đòn đánh của ta đây !",
                                        "|-1|Được thôi, nếu muốn chết đến vậy, ta rất vui lòng!!"
                        }, // text chat 2
                        new String[] { "|-1|Biến hình! Super Sayan Rose" }, // text chat 3
                        REST_10_M // second rest
        );

        public static final BossData SUPER_BLACK_GOKU = new BossData(
                        "Super Black Goku Rose", // name
                        ConstPlayer.TRAI_DAT, // gender
                        new short[] { 553, 880, 881, -1, -1, -1 }, // outfit {head, body, leg, bag, aura, eff}
                        280000, // dame
                        new int[] { 1000000000 }, // hp
                        new int[] { 102, 92, 93, 94, 96, 97, 98, 99, 100 }, // map join
                        new int[][] {
                                        { Skill.KAMEJOKO, 7, 1000 },
                                        { Skill.QUA_CAU_KENH_KHI, 3, 30000 },
                                        { Skill.LIEN_HOAN, 7, 300 },
                                        { Skill.DICH_CHUYEN_TUC_THOI, 2, 60000 },
                                        { Skill.DICH_CHUYEN_TUC_THOI, 7, 30000 },
                                        { Skill.THAI_DUONG_HA_SAN, 7, 30000 },
                        }, // skill
                        new String[] {}, // text chat 1
                        new String[] { "|-1|Ta chính là người mang thân thể của Songoku",
                                        "|-1|Sức mạnh của ta là không có giới hạn",
                                        "|-1|Ta sẽ thống trị vũ trụ",
                                        "|-1|Để ta nói cho nghe,người Sayan sau khi hồi phục sức mạnh sẽ tăng lên rất nhiều",
                                        "|-2|Tại sao ngươi lại lấy thân thể của songoku chứ?"
                        }, // text chat 2

                        new String[] { "|-1|Chúng ta sẽ gặp lại nhau sớm thôi",
                                        "|-2|Ngươi nói gì chứ?" }, // text chat 3
                        TypeAppear.ANOTHER_LEVEL // type appear
        );

        // -------------------------------------------------------------------
        public static final BossData SUPER_BLACK_GOKU_2 = new BossData(
                        "Super Black Goku Rose", // name
                        ConstPlayer.TRAI_DAT, // gender
                        new short[] { 553, 880, 881, -1, -1, -1 }, // outfit {head, body, leg, bag, aura, eff}
                        280000, // dame
                        new int[] { 1100000000 }, // hp
                        new int[] { 102, 92, 93, 94, 96, 97, 98, 99, 100 }, // map join
                        // new int[]{14}, //map join
                        new int[][] {
                                        { Skill.KAMEJOKO, 7, 1000 },
                                        { Skill.QUA_CAU_KENH_KHI, 3, 30000 },
                                        { Skill.LIEN_HOAN, 7, 300 },
                                        { Skill.DICH_CHUYEN_TUC_THOI, 2, 60000 },
                                        { Skill.DICH_CHUYEN_TUC_THOI, 7, 30000 },
                                        { Skill.THAI_DUONG_HA_SAN, 7, 30000 },
                        }, // skill
                        new String[] {}, // text chat 1
                        new String[] { "|-1|Ta chính là người mang thân thể của Songoku",
                                        "|-1|Sức mạnh của ta là không có giới hạn",
                                        "|-1|Ta sẽ thống trị vũ trụ",
                                        "|-1|Để ta nói cho nghe,người Sayan sau khi hồi phục sức mạnh sẽ tăng lên rất nhiều",
                                        "|-2|Tại sao ngươi lại lấy thân thể của songoku chứ?"
                        }, // text chat 2

                        new String[] { "|-1|Hẹn gặp lại",
                                        "|-2|Không tiễn" }, // text chat 3
                        REST_10_M, // second rest
                        new int[] { BossID.ZAMASZIN });

        public static final BossData ZAMAS = new BossData(
                        "Kaioshin Zamas", // name
                        ConstPlayer.TRAI_DAT, // gender
                        new short[] { 433, 904, 905, -1, -1, -1 }, // outfit {head, body, leg, bag, aura, eff}
                        280500, // dame
                        new int[] { 1000000000 }, // hp
                        new int[] { 102, 92, 93, 94, 96, 97, 98, 99, 100 }, // map join
                        // new int[]{14}, //map join

                        new int[][] {
                                        { Skill.KAMEJOKO, 7, 1000 },
                                        { Skill.QUA_CAU_KENH_KHI, 3, 30000 },
                                        { Skill.LIEN_HOAN, 7, 300 },
                                        { Skill.DICH_CHUYEN_TUC_THOI, 2, 60000 },
                                        { Skill.DICH_CHUYEN_TUC_THOI, 7, 30000 },
                                        { Skill.THAI_DUONG_HA_SAN, 7, 30000 },
                        }, // skill
                        new String[] {}, // text chat 1
                        new String[] { "|-1|Ta là kaioshin của vũ trụ thứ 10 ",
                                        "|-1|Tên của ta là Zamas, ta sẽ thay đổi thế giới này",
                                        "|-1|Lũ con người các ngươi là những thứ ta cần loại bỏ đầu tiên",
                                        "|-2|Tại sao các ngươi lại nhắm tới con người bọn ta chứ?",
                                        "|-1|Bởi vì ta muốn thực hiện kế hoạch đưa con người về số 0 !",
                                        "|-1|Lần này ta không nương tay đâu!",
                                        "|-2|Ngươi thực sự rất mạnh. Nhưng chưa đủ thực lực đâu!!",
                                        "|-1|Cái gì!? Đó là điều ngu ngốc nhất ta từng nghe! Mau biến đi",
                                        "|-1|Hắn thực sự rất mạnh, đúng là cuộc chiến hay",
                                        "|-3|Không lí nào ta lại run sợ bọn con người sao"
                        }, // text chat 2

                        new String[] { "|-1|Chỉ còn một cách duy nhất mà thôi",
                                        "|-1|Bông tai Porata!" }, // text chat 3
                        TypeAppear.APPEAR_WITH_ANOTHER);
        // -------------------------------------------------------------------
        public static final BossData THANZM2 = new BossData(
                        "Thần Zamas Tối Thượng", // name
                        ConstPlayer.TRAI_DAT, // gender
                        new short[] { 903, 904, 905, -1, -1, -1 }, // outfit {head, body, leg, bag, aura, eff}
                        290000, // dame
                        new int[] { 1200000000 }, // hp
                        new int[] { 102, 92, 93, 94, 96, 97, 98, 99, 100 }, // map join
                        new int[][] {
                                        { Skill.DICH_CHUYEN_TUC_THOI, 7, 50000 },
                                        { Skill.KAMEJOKO, 5, 3000 },
                                        { Skill.DICH_CHUYEN_TUC_THOI, 2, 60000 },
                                        { Skill.QUA_CAU_KENH_KHI, 3, 330000 },
                                        { Skill.LIEN_HOAN, 7, 600 }, },

                        // skill
                        new String[] {}, // text chat 1
                        new String[] { "|-1|Ta chính là thế giới",
                                        "|-1|Ta chính là công lí",
                                        "|-1|Hãy chiêm ngưỡng vẻ đẹp của ta !Hỡi con người",
                                        "|-1|Sức mạnh to lớn nằm trong cơ thể bất tử",
                                        "|-1|Ta sẽ đem công lí tới toàn bộ vũ trụ này",
                                        "|-2|Ngươi cứ lải nhải hoài 2 chữ công lí vậy?",
                                        "|-1|Lũ các ngươi làm ta thấy đau rồi ấy haha"
                        }, // text chat 2
                        new String[] {}, // text chat 3
                        REST_15_M);

        // **************************************************************************

        public static final BossData MABU = new BossData(
                        "Broly", // name
                        ConstPlayer.XAYDA, // gender
                        new short[] { 291, 292, 293, -1, -1, -1 }, // outfit {head, body, leg, bag, aura, eff}
                        Util.nextInt(100, 500), // dame
                        new int[] { Util.nextInt(1000, 10000) }, // hp
                        new int[] { 6, 27, 28, 29, 30, 13, 31, 32, 33, 34, 10, 19, 20, 35, 36, 37, 38 }, // map join
                        new int[][] { { Skill.KAMEJOKO, 7, 500 },
                        }, // skill
                        new String[] {}, // text chat 1
                        new String[] { "|-1|Các ngươi tới số rồi mới gặp phải ta",
                                        "|-1|Gaaaaaa",
                                        "|-2|Không..thể..nào!!",
                                        "|-2|Tên này điên thật rồi!!",
                                        "|-1|Được thôi, nếu muốn chết đến vậy, ta rất vui lòng!!"
                        }, // text chat 2
                        new String[] { "|-1|Khôngggggggg!!" }, // text chat 3
                        REST_30_S // type appear
        );
        // *******************

        // **************************************************************************
        // Boss hủy diệt
        public static final BossData THIEN_SU_WHIS = new BossData(
                        "Thiên sứ Whis", // name
                        ConstPlayer.NAMEC, // gender
                        new short[] { 838, 839, 840, -1, -1, -1 }, // outfit {head, body, leg, bag, aura, eff}
                        1, // dame
                        new int[] { 150 }, // hp
                        new int[] { 146, 147, 148 }, // map join
                        new int[][] {
                                        { Skill.KHIEN_NANG_LUONG, 7, 100000 },
                                        { Skill.DICH_CHUYEN_TUC_THOI, 7, 50000 }, }, // skill
                        new String[] {}, // text chat 1
                        new String[] { "|-1|Ta có thể ngồi ăn một chút được rồi!",
                                        "|-1|Các ngươi vẫn yếu vẫn như mọi khi",
                                        "|0|Thật là bực mình!",
                                        "|-2|Đây là sức mạnh của một thiên sứ sao?",
                                        "|-1|Hô Hô",
                                        "|-1|Các ngươi không đánh bại được ta đâu!",
                                        "|-2|Không thể nào",
                                        "|-2|Tại sao lại vậy chứ !", }, // text chat 2
                        new String[] { "|-1|Ta đi về đây!Cảm ơn vì món ăn" }, // text chat 3
                        REST_10_M, // second rest
                        new int[] { BossID.THAN_HUY_DIET });
        public static final BossData THAN_HUY_DIET = new BossData(
                        "Thần Hủy Diệt Berrus", // name
                        ConstPlayer.TRAI_DAT, // gender
                        new short[] { 508, 509, 510, -1, -1, -1 }, // outfit {head, body, leg, bag, aura, eff}
                        300000, // dame
                        new int[] { 1500000000 }, // hp
                        new int[] { 146, 147, 148 }, // map join
                        new int[][] {
                                        { Skill.MASENKO, 7, 200 },
                                        { Skill.DICH_CHUYEN_TUC_THOI, 2, 60000 },
                                        { Skill.LIEN_HOAN, 7, 300 }, }, // skill//skill
                        new String[] {}, // text chat 1
                        new String[] { "|-1|Các ngươi thật là yếu ớt",
                                        "|-1|Ta sẽ phá hủy hành tinh này",
                                        "|-1|Chán quá!",
                                        "|-1|Ta vẫn chưa dùng hết sức đâu!",
                                        "|-2|Hắn ta không cần phòng thủ luôn!", }, // text chat 2
                        new String[] { "|-1|Ta buồn ngủ quá!" }, // text chat 3
                        TypeAppear.APPEAR_WITH_ANOTHER);
        public static final BossData THIEN_SU_VADOS = new BossData(
                        "Thiên sứ Vados", // name
                        ConstPlayer.NAMEC, // gender
                        new short[] { 530, 531, 532, -1, -1, -1 }, // outfit {head, body, leg, bag, aura, eff}
                        1, // dame
                        new int[] { 150 }, // hp
                        new int[] { 146, 147, 148 }, // map join
                        new int[][] {
                                        { Skill.KHIEN_NANG_LUONG, 7, 100000 },
                                        { Skill.DICH_CHUYEN_TUC_THOI, 7, 50000 }, }, // skill
                        new String[] {}, // text chat 1
                        new String[] { "|-1|Ồ",
                                        "|-1|Ta làm vậy có hơi quá không?",
                                        "|0|Thật là bực mình!",
                                        "|-2|Sao ông ta lại mạnh tới vậy ?",
                                        "|-1|Hô Hô",
                                        "|-1|Các ngươi muốn đánh bại một Thiên Sứ sao?",
                                        "|-2|Khốn khiếp!",
                                        "|-2|Tại sao lại vậy chứ !", }, // text chat 2
                        new String[] { "|-1|Hẹn gặp lại,ta rất hài lòng về cuộc chiến" }, // text chat 3
                        REST_10_M, // second rest
                        new int[] { BossID.THAN_HUY_DIET_CHAMPA });
        public static final BossData THAN_HUY_DIET_CHAMPA = new BossData(
                        "Thần Hủy Diệt Champa", // name
                        ConstPlayer.TRAI_DAT, // gender
                        new short[] { 511, 512, 513, -1, -1, 77 }, // outfit {head, body, leg, bag, aura, eff}
                        300000, // dame
                        new int[] { 1500000000 }, // hp
                        new int[] { 146, 147, 148 }, // map join
                        new int[][] {
                                        { Skill.MASENKO, 3, 400 },
                                        { Skill.DICH_CHUYEN_TUC_THOI, 2, 60000 },
                                        { Skill.MASENKO, 3, 400 },
                                        { Skill.LIEN_HOAN, 7, 300 }, }, // skill//skill
                        new String[] {}, // text chat 1
                        new String[] { "|-1|Một lũ yếu ớt",
                                        "|-1|Ta sẽ phá hủy hành tinh này",
                                        "|-1|Chán quá!",
                                        "|-1|Không có ai đủ mạnh để đấu với ta sao?",
                                        "|-2|Hắn ta không cần phòng thủ luôn!", }, // text chat 2
                        new String[] { "|-1|Chết tiệt!" }, // text chat 3
                        TypeAppear.APPEAR_WITH_ANOTHER);
        // **************************************************************************
        // **************************************************************************
        // Boss goku
        public static final BossData SONGOKU_TA_AC = new BossData(
                        "Siêu Goku Tà Ác", // name
                        ConstPlayer.XAYDA, // gender
                        new short[] { 543, 57, 999, -1, -1, -1 }, // outfit {head, body, leg, bag, aura, eff}
                        210000, // dame
                        new int[] { 1000000000 }, // hp
                        new int[] { 155, 177, 178 }, // map join
                        new int[][] {
                                        { Skill.KAMEJOKO, 7, 1000 },
                                        { Skill.QUA_CAU_KENH_KHI, 3, 30000 },
                                        { Skill.LIEN_HOAN, 7, 300 },
                                        { Skill.DICH_CHUYEN_TUC_THOI, 2, 60000 },
                                        { Skill.DICH_CHUYEN_TUC_THOI, 7, 30000 },
                                        { Skill.THAI_DUONG_HA_SAN, 7, 30000 },
                        }, // skill
                        new String[] {}, // text chat 1
                        new String[] { "|-2|Tỉnh lại đi chú Goku",
                                        "|-2|Đừng để bị hắn chi phối!",
                                        "|-1|Định chạy trốn hả?",
                                        "|-1|Ta sẽ tàn sát khu này trong vòng 5 phút nữa",
                                        "|-2|Không được rồi!",
                                        "|-2|Phải cố hết sức thôi"
                        }, // text chat 2
                        new String[] { "|-2|Mau nghỉ ngơi nào chú Goku" }, // text chat 3
                        REST_15_M // second rest
        );

        // **************************************************************************
        // Boss nrd

        public static final BossData Rong_1Sao = new BossData(
                        "Rồng Syn 1 Sao", // name
                        ConstPlayer.XAYDA, // gender
                        new short[] { 204, 205, 206, -1, -1, -1 }, // outfit {head, body, leg, bag, aura, eff}
                        190000, // dame
                        new int[] { 1000000000 }, // hp
                        new int[] { 85 }, // map join
                        new int[][] {
                                        { Skill.LIEN_HOAN, 7, 300 },
                                        { Skill.KAMEJOKO, 5, 10000 } }, // skill
                        new String[] { "|-1|Gaaaaaa !!!!!!!!",
                                        "|-2|Tên kia là ai vậy",
                                        "|-1|Sức mạnh tà ác !"
                        }, // text chat 1
                        new String[] { "|-1|Ta muốn tìm một đối thủ xứng tầm",
                                        "|-1|Đi chết đi!",
                                        "|-1|Các ngươi không phải đối thủ của ta đâu",
                                        "|-1|trạng thái Tà Ác sẽ thiêu rụi mày"
                        }, // text chat 2
                        new String[] { "|-2|Tên đó mạnh thật!" }, // text chat 3
                        REST_24_H // second rest
        );
        public static final BossData Rong_2Sao = new BossData(
                        "Rồng Haze 2 Sao", // name
                        ConstPlayer.XAYDA, // gender
                        new short[] { 219, 220, 221, -1, -1, -1 }, // outfit {head, body, leg, bag, aura, eff}
                        190000, // dame
                        new int[] { 1000000000 }, // hp
                        new int[] { 86 }, // map join
                        new int[][] {
                                        { Skill.LIEN_HOAN, 7, 300 },
                                        { Skill.KAMEJOKO, 5, 1000 } }, // skill
                        new String[] { "|-1|Gaaaaaa !!!!!!!!",
                                        "|-2|Tên kia là ai vậy",
                                        "|-1|Sức mạnh tà ác !"
                        }, // text chat 1
                        new String[] { "|-1|Ta muốn tìm một đối thủ xứng tầm",
                                        "|-1|Đi chết đi!",
                                        "|-1|Các ngươi không phải đối thủ của ta đâu",
                                        "|-1|trạng thái Tà Ác sẽ thiêu rụi mày"
                        }, // text chat 2
                        new String[] { "|-2|Tên đó mạnh thật!" }, // text chat 3
                        REST_24_H // second rest

        );
        public static final BossData Rong_3Sao = new BossData(
                        "Rồng Eis 3 Sao", // name
                        ConstPlayer.XAYDA, // gender
                        new short[] { 207, 208, 209, -1, -1, -1 }, // outfit {head, body, leg, bag, aura, eff}
                        190000, // dame
                        new int[] { 1000000000 }, // hp
                        new int[] { 87 }, // map join
                        new int[][] {
                                        { Skill.LIEN_HOAN, 7, 300 },
                                        { Skill.THOI_MIEN, 4, 125000 },
                                        { Skill.THAI_DUONG_HA_SAN, 3, 150000 },
                                        { Skill.DICH_CHUYEN_TUC_THOI, 7, 100000 } }, // skill
                        new String[] { "|-1|Gaaaaaa !!!!!!!!",
                                        "|-2|Tên kia là ai vậy",
                                        "|-1|Sức mạnh tà ác !"
                        }, // text chat 1
                        new String[] { "|-1|Ta muốn tìm một đối thủ xứng tầm",
                                        "|-1|Đi chết đi!",
                                        "|-1|Các ngươi không phải đối thủ của ta đâu",
                                        "|-1|trạng thái Tà Ác sẽ thiêu rụi mày"
                        }, // text chat 2
                        new String[] { "|-2|Tên đó mạnh thật!" }, // text chat 3
                        REST_24_H // second rest

        );
        public static final BossData Rong_4Sao = new BossData(
                        "Rồng Nuova 4 Sao", // name
                        ConstPlayer.XAYDA, // gender
                        new short[] { 210, 211, 212, -1, -1, -1 }, // outfit {head, body, leg, bag, aura, eff}
                        190000, // dame
                        new int[] { 1000000000 }, // hp
                        new int[] { 88 }, // map join
                        new int[][] {
                                        { Skill.LIEN_HOAN, 7, 300 },
                                        { Skill.TROI, 7, 100000 },
                                        { Skill.KAMEJOKO, 5, 10000 } }, // skill
                        new String[] { "|-1|Gaaaaaa !!!!!!!!",
                                        "|-2|Tên kia là ai vậy",
                                        "|-1|Sức mạnh tà ác !"
                        }, // text chat 1
                        new String[] { "|-1|Ta muốn tìm một đối thủ xứng tầm",
                                        "|-1|Đi chết đi!",
                                        "|-1|Các ngươi không phải đối thủ của ta đâu",
                                        "|-1|trạng thái Tà Ác sẽ thiêu rụi mày"
                        }, // text chat 2
                        new String[] { "|-2|Tên đó mạnh thật!" }, // text chat 3
                        REST_24_H // second rest
        );
        public static final BossData Rong_5Sao = new BossData(
                        "Rồng Rage 5 Sao", // name
                        ConstPlayer.XAYDA, // gender
                        new short[] { 213, 214, 215, -1, -1, -1 }, // outfit {head, body, leg, bag, aura, eff}
                        190000, // dame
                        new int[] { 1000000000 }, // hp
                        new int[] { 89 }, // map join
                        new int[][] {
                                        { Skill.LIEN_HOAN, 7, 300 },
                                        { Skill.KAMEJOKO, 5, 10000 } }, // skill
                        new String[] { "|-1|Gaaaaaa !!!!!!!!",
                                        "|-2|Tên kia là ai vậy",
                                        "|-1|Sức mạnh tà ác !"
                        }, // text chat 1
                        new String[] { "|-1|Ta muốn tìm một đối thủ xứng tầm",
                                        "|-1|Đi chết đi!",
                                        "|-1|Các ngươi không phải đối thủ của ta đâu",
                                        "|-1|trạng thái Tà Ác sẽ thiêu rụi mày"
                        }, // text chat 2
                        new String[] { "|-2|Tên đó mạnh thật!" }, // text chat 3
                        REST_24_H // second rest
        );
        public static final BossData Rong_6Sao = new BossData(
                        "Rồng Oceanus 6 Sao", // name
                        ConstPlayer.XAYDA, // gender
                        new short[] { 222, 223, 224, -1, -1, -1 }, // outfit {head, body, leg, bag, aura, eff}
                        190000, // dame
                        new int[] { 1000000000 }, // hp
                        new int[] { 90 }, // map join
                        new int[][] {
                                        { Skill.LIEN_HOAN, 7, 400 },
                                        { Skill.KAMEJOKO, 5, 10000 } }, // skill
                        new String[] { "|-1|Gaaaaaa !!!!!!!!",
                                        "|-2|Tên kia là ai vậy",
                                        "|-1|Sức mạnh tà ác !"
                        }, // text chat 1
                        new String[] { "|-1|Ta muốn tìm một đối thủ xứng tầm",
                                        "|-1|Đi chết đi!",
                                        "|-1|Các ngươi không phải đối thủ của ta đâu",
                                        "|-1|trạng thái Tà Ác sẽ thiêu rụi mày"
                        }, // text chat 2
                        new String[] { "|-2|Tên đó mạnh thật!" }, // text chat 3
                        REST_24_H // second rest
        );
        public static final BossData Rong_7Sao = new BossData(
                        "Rồng Naturon 7 Sao", // name
                        ConstPlayer.XAYDA, // gender
                        new short[] { 216, 217, 218, -1, -1, -1 }, // outfit {head, body, leg, bag, aura, eff}
                        190000, // dame
                        new int[] { 1000000000 }, // hp
                        new int[] { 91 }, // map join
                        new int[][] {
                                        { Skill.LIEN_HOAN, 7, 300 },
                                        { Skill.KAMEJOKO, 5, 1000 } }, // skill
                        new String[] { "|-1|Gaaaaaa !!!!!!!!",
                                        "|-2|Tên kia là ai vậy",
                                        "|-1|Sức mạnh tà ác !"
                        }, // text chat 1
                        new String[] { "|-1|Ta muốn tìm một đối thủ xứng tầm",
                                        "|-1|Đi chết đi!",
                                        "|-1|Các ngươi không phải đối thủ của ta đâu",
                                        "|-1|trạng thái Tà Ác sẽ thiêu rụi mày"
                        }, // text chat 2
                        new String[] { "|-2|Tên đó mạnh thật!" }, // text chat 3
                        REST_24_H // second rest
        );
        // **************************************************************************Team
        // Mabu 12h
        public static final BossData MABU_12H = new BossData(
                        "Mabư",
                        ConstPlayer.XAYDA,
                        new short[] { 297, 298, 299, -1, -1, -1 },
                        280000,
                        new int[] { 1000000000 },
                        new int[] { 120 },
                        new int[][] {
                                        { Skill.LIEN_HOAN, 7, 200 },
                                        { Skill.KHIEN_NANG_LUONG, 7, 50000 },
                                        { Skill.THAI_DUONG_HA_SAN, 7, 70000 },
                                        { Skill.DICH_CHUYEN_TUC_THOI, 7, 100000 },

                                        { Skill.KAMEJOKO, 7, 1000 } },
                        new String[] { "|-2|Ma nhân Bư đã xuất hiện rồi" }, // text chat 1
                        new String[] { "|-1|Thấy ảo chưa nè!" }, // text chat 2
                        new String[] { "|-1|Nhớ mặt tao đấy",
                                        "|-1|Tobe continue.." }, // text chat 3
                        REST_10_M);
        public static final BossData DRABURA = new BossData(
                        "Ma Vương Dabura",
                        ConstPlayer.XAYDA,
                        new short[] { 418, 419, 420, -1, -1, -1 },
                        100000,
                        new int[] { 500000000 },
                        new int[] { 114 },
                        new int[][] {
                                        { Skill.LIEN_HOAN, 7, 300 },
                                        { Skill.DEMON, 7, 10000 } },
                        new String[] { "|-2|Ma nhân Bư đã xuất hiện rồi" }, // text chat 1
                        new String[] { "|-1|Thấy ảo chưa nè!" }, // text chat 2
                        new String[] { "|-1|Nhớ mặt tao đấy",
                                        "|-1|Tobe continue.." }, // text chat 3
                        REST_10_M);
        public static final BossData DRABURA_2 = new BossData(
                        "Ma Vương Dabura",
                        ConstPlayer.XAYDA,
                        new short[] { 418, 419, 420, -1, -1, -1 },
                        100000,
                        new int[] { 1000000000 },
                        new int[] { 119 },
                        new int[][] {
                                        { Skill.LIEN_HOAN, 7, 300 },
                                        { Skill.DEMON, 7, 10000 } },
                        new String[] { "|-2|Ma nhân Bư đã xuất hiện rồi" }, // text chat 1
                        new String[] { "|-1|Thấy ảo chưa nè!" }, // text chat 2
                        new String[] { "|-1|Nhớ mặt tao đấy",
                                        "|-1|Tobe continue.." }, // text chat 3
                        REST_10_M);
        public static final BossData BUI_BUI = new BossData(
                        "Bui Bui",
                        ConstPlayer.XAYDA,
                        new short[] { 451, 452, 453, -1, -1, -1 },
                        100000,
                        new int[] { 700000000 },
                        new int[] { 115 },
                        new int[][] {
                                        { Skill.LIEN_HOAN, 7, 300 },
                                        { Skill.DEMON, 7, 10000 } },
                        new String[] { "|-2|Ma nhân Bư đã xuất hiện rồi" }, // text chat 1
                        new String[] { "|-1|Thấy ảo chưa nè!" }, // text chat 2
                        new String[] { "|-1|Nhớ mặt tao đấy",
                                        "|-1|Tobe continue.." }, // text chat 3
                        REST_10_M);

        public static final BossData BUI_BUI_2 = new BossData(
                        "Bui Bui",
                        ConstPlayer.XAYDA,
                        new short[] { 451, 452, 453, -1, -1, -1 },
                        100000,
                        new int[] { 800000000 },
                        new int[] { 117 },
                        new int[][] {
                                        { Skill.LIEN_HOAN, 7, 300 },
                                        { Skill.DEMON, 7, 10000 } },
                        new String[] { "|-2|Ma nhân Bư đã xuất hiện rồi" }, // text chat 1
                        new String[] { "|-1|Thấy ảo chưa nè!" }, // text chat 2
                        new String[] { "|-1|Nhớ mặt tao đấy",
                                        "|-1|Tobe continue.." }, // text chat 3
                        REST_10_M);
        public static final BossData YACON = new BossData(
                        "Yacôn",
                        ConstPlayer.XAYDA,
                        new short[] { 415, 416, 417, -1, -1, -1 },
                        100000,
                        new int[] { 900000000 },
                        new int[] { 118 },
                        new int[][] {
                                        { Skill.DEMON, 7, 300 } },
                        new String[] { "|-2|Ma nhân Bư đã xuất hiện rồi" }, // text chat 1
                        new String[] { "|-1|Thấy ảo chưa nè!" }, // text chat 2
                        new String[] { "|-1|Nhớ mặt tao đấy",
                                        "|-1|Tobe continue.." }, // text chat 3
                        REST_10_M);
        //
        public static final BossData BU_BEO = new BossData(
                        "Bư béo",
                        ConstPlayer.XAYDA,
                        new short[] { 297, 298, 299, -1, -1, -1 },
                        200000,
                        new int[] { 900000000 },
                        new int[] { 144 },
                        new int[][] {
                                        { Skill.LIEN_HOAN, 7, 300 }, { Skill.KAMEJOKO, 7, 2000 },
                                        { Skill.SOCOLA, 7, 20000 }, { Skill.THOI_MIEN, 7, 40000 } },
                        new String[] {}, // text chat 1
                        new String[] { "|-1|Thấy ảo chưa nè!" }, // text chat 2
                        new String[] { "|-1|Nhớ mặt tao đấy",
                                        "|-1|Cay quá!",
                                        "|-1|Ta mà lại thua được sao?",
                                        "|-1|Hãy trả thù cho ta!" }, // text chat 3
                        REST_10_M);
        //

        public static final BossData BU_SUPER = new BossData(
                        "Siêu Bư",
                        ConstPlayer.XAYDA,
                        new short[] { 421, 422, 423, -1, -1, -1 },
                        220000,
                        new int[] { 1000000000 },
                        new int[] { 144 },
                        new int[][] {
                                        { Skill.LIEN_HOAN, 7, 300 }, { Skill.KAMEJOKO, 7, 2000 },
                                        { Skill.SOCOLA, 7, 20000 }, { Skill.THOI_MIEN, 7, 40000 } },
                        new String[] {}, // text chat 1
                        new String[] { "|-1|Thấy ảo chưa nè!" }, // text chat 2
                        new String[] { "|-1|Cay quá!",
                                        "|-1|Ta mà lại thua được sao?",
                                        "|-1|Hãy trả thù cho ta!" }, // text chat 3
                        TypeAppear.ANOTHER_LEVEL);
        //
        //
        public static final BossData BU_TENH = new BossData(
                        "Bư Tênh",
                        ConstPlayer.XAYDA,
                        new short[] { 424, 425, 426, -1, -1, -1 },
                        240000,
                        new int[] { 1100000000 },
                        new int[] { 144 },
                        new int[][] {
                                        { Skill.LIEN_HOAN, 7, 300 }, { Skill.KAMEJOKO, 7, 2000 },
                                        { Skill.SOCOLA, 7, 20000 }, { Skill.THOI_MIEN, 7, 40000 } },
                        new String[] {}, // text chat 1
                        new String[] { "|-1|Thấy ảo chưa nè!" }, // text chat 2
                        new String[] { "|-1|Nhớ mặt tao đấy",
                                        "|-1|Tobe continue.." }, // text chat 3
                        TypeAppear.ANOTHER_LEVEL);
        //
        public static final BossData BU_HAN = new BossData(
                        "Bư Han",
                        ConstPlayer.XAYDA,
                        new short[] { 427, 428, 429, -1, -1, -1 },
                        240000,
                        new int[] { 1500000000 },
                        new int[] { 144 },
                        new int[][] {
                                        { Skill.LIEN_HOAN, 7, 300 }, { Skill.KAMEJOKO, 7, 2000 },
                                        { Skill.SOCOLA, 7, 20000 }, { Skill.THOI_MIEN, 7, 40000 } },
                        new String[] {}, // text chat 1
                        new String[] { "|-1|Thấy ảo chưa nè!" }, // text chat 2
                        new String[] { "|-1|Nhớ mặt tao đấy",
                                        "|-1|Tobe continue.." }, // text chat 3
                        TypeAppear.ANOTHER_LEVEL);
        //
        public static final BossData BU_KID = new BossData(
                        "Bư Con",
                        ConstPlayer.XAYDA,
                        new short[] { 439, 440, 441, -1, -1, -1 },
                        300000,
                        new int[] { 2000000000 },
                        new int[] { 144 },
                        new int[][] {
                                        { Skill.LIEN_HOAN, 7, 300 }, { Skill.KAMEJOKO, 7, 2000 },
                                        { Skill.SOCOLA, 7, 20000 }, { Skill.THOI_MIEN, 7, 40000 } },
                        new String[] {}, // text chat 1
                        new String[] { "|-1|Thấy ảo chưa nè!" }, // text chat 2
                        new String[] { "|-1|Nhớ mặt tao đấy",
                                        "|-1|Tobe continue.." }, // text chat 3
                        TypeAppear.ANOTHER_LEVEL);
        //
        public static final BossData BU_BUNG = new BossData(
                        "Bư Bụng",
                        ConstPlayer.XAYDA,
                        new short[] { 421, 422, 423, -1, -1, -1 },
                        140000,
                        new int[] { 1000000000 },
                        new int[] { 128 },
                        new int[][] {
                                        { Skill.LIEN_HOAN, 7, 300 }, { Skill.KAMEJOKO, 7, 2000 },
                                        { Skill.SOCOLA, 7, 20000 }, { Skill.THOI_MIEN, 7, 40000 } },
                        new String[] {}, // text chat 1
                        new String[] { "|-1|Thấy ảo chưa nè!" }, // text chat 2
                        new String[] { "|-1|Nhớ mặt tao đấy",
                                        "|-1|Tobe continue.." }, // text chat 3
                        REST_1_M);
        //

        public static final BossData COOLER_1 = new BossData(
                        "Cooler", // name
                        ConstPlayer.TRAI_DAT, // gender
                        new short[] { 320, 321, 322, -1, -1, -1 }, // outfit {head, body, leg, bag, aura, eff}
                        120000, // dame
                        new int[] { 600000000 }, // hp
                        new int[] { 105, 106, 107 }, // map join
                        new int[][] {
                                        { Skill.THOI_MIEN, 7, 100000 },
                                        { Skill.DICH_CHUYEN_TUC_THOI, 7, 30000 },
                                        { Skill.KAMEJOKO, 7, 10000 },
                                        { Skill.LIEN_HOAN, 4, 300 },
                                        { Skill.DICH_CHUYEN_TUC_THOI, 2, 60000 },
                                        { Skill.MASENKO, 7, 10000 } }, // skill
                        new String[] {}, // text chat 1
                        new String[] { "|-1|Các ngươi thật là yếu ớt",
                                        "|-1|Chán quá!",
                                        "|-1|Không có ai đủ mạnh để đấu với ta sao?",
                                        "|-1|Ta sẽ cho ngươi cái nịt!",
                        }, // text chat 2
                        new String[] { "|-1|Biến hình !!!!!!!!!!!!" }, // text chat 3
                        REST_10_M);
        public static final BossData COOLER_2 = new BossData(
                        "Cooler 2", // name
                        ConstPlayer.TRAI_DAT, // gender
                        new short[] { 317, 318, 319, -1, -1, -1 }, // outfit {head, body, leg, bag, aura, eff}
                        140000, // dame
                        new int[] { 700000000 }, // hp
                        new int[] { 105, 106, 107 }, // map join
                        new int[][] {
                                        { Skill.THOI_MIEN, 7, 100000 },
                                        { Skill.DICH_CHUYEN_TUC_THOI, 7, 30000 },
                                        { Skill.KAMEJOKO, 7, 1000 },
                                        { Skill.LIEN_HOAN, 4, 300 },

                                        { Skill.MASENKO, 7, 10000 } }, // skill
                        new String[] {}, // text chat 1
                        new String[] { "|-1|Các ngươi thật là yếu ớt",
                                        "|-1|Ta sẽ phá hủy hành tinh này",
                                        "|-1|Chán quá!",
                                        "|-1|Không có ai đủ mạnh để đấu với ta sao?",
                                        "|-1|Ta nghèo lắm!Đừng săn ta nữa", }, // text chat 2
                        new String[] { "|-1|Đen lắm em trai !" }, // text chat 3
                        TypeAppear.APPEAR_WITH_ANOTHER);

        public static final BossData FROST_1 = new BossData(
                        "Frost", // name
                        ConstPlayer.TRAI_DAT, // gender
                        new short[] { 493, 494, 495, -1, -1, -1 }, // outfit {head, body, leg, bag, aura, eff}
                        170000, // dame
                        new int[] { 800000000 }, // hp
                        new int[] { 108, 109, 110 }, // map join
                        new int[][] {
                                        { Skill.THOI_MIEN, 7, 100000 },
                                        { Skill.DICH_CHUYEN_TUC_THOI, 7, 30000 },
                                        { Skill.KAMEJOKO, 7, 1000 },
                                        { Skill.LIEN_HOAN, 4, 300 },
                                        { Skill.DICH_CHUYEN_TUC_THOI, 2, 60000 },
                                        { Skill.MASENKO, 7, 10000 } }, // skill
                        new String[] {}, // text chat 1
                        new String[] { "|-1|Các ngươi thật là yếu ớt",
                                        "|-1|Ta sẽ phá hủy hành tinh này",
                                        "|-1|Chán quá!",
                                        "|-1|Không có ai đủ mạnh để đấu với ta sao?",
                                        "|-1|Ta nghèo lắm!Đừng săn ta nữa", }, // text chat 2
                        new String[] { "|-1|Đen lắm em trai !" }, // text chat 3
                        REST_10_M);
        public static final BossData FROST_2 = new BossData(
                        "Frost 2", // name
                        ConstPlayer.TRAI_DAT, // gender
                        new short[] { 496, 497, 498, -1, -1, -1 }, // outfit {head, body, leg, bag, aura, eff}
                        180000, // dame
                        new int[] { 900000000 }, // hp
                        new int[] { 108, 109, 110 }, // map join
                        new int[][] {
                                        { Skill.THOI_MIEN, 7, 100000 },
                                        { Skill.DICH_CHUYEN_TUC_THOI, 7, 30000 },
                                        { Skill.KAMEJOKO, 7, 1000 },
                                        { Skill.LIEN_HOAN, 4, 300 },
                                        { Skill.DICH_CHUYEN_TUC_THOI, 2, 60000 },
                                        { Skill.MASENKO, 7, 10000 } }, // skill
                        new String[] {}, // text chat 1
                        new String[] { "|-1|Các ngươi thật là yếu ớt",
                                        "|-1|Ta sẽ phá hủy hành tinh này",
                                        "|-1|Chán quá!",
                                        "|-1|Không có ai đủ mạnh để đấu với ta sao?",
                                        "|-1|Ta nghèo lắm!Đừng săn ta nữa", }, // text chat 2
                        new String[] { "|-1|Đen lắm em trai !" }, // text chat 3
                        TypeAppear.APPEAR_WITH_ANOTHER);
        public static final BossData FROST_3 = new BossData(
                        "Frost 3", // name
                        ConstPlayer.TRAI_DAT, // gender
                        new short[] { 499, 500, 501, -1, -1, -1 }, // outfit {head, body, leg, bag, aura, eff}
                        180000, // dame
                        new int[] { 1000000000 }, // hp
                        new int[] { 108, 109, 110 }, // map join
                        new int[][] {
                                        { Skill.THOI_MIEN, 7, 100000 },
                                        { Skill.DICH_CHUYEN_TUC_THOI, 7, 30000 },
                                        { Skill.KAMEJOKO, 7, 1000 },
                                        { Skill.LIEN_HOAN, 4, 300 },

                                        { Skill.MASENKO, 7, 10000 } }, // skill
                        new String[] {}, // text chat 1
                        new String[] { "|-1|Các ngươi thật là yếu ớt",
                                        "|-1|Ta sẽ phá hủy hành tinh này",
                                        "|-1|Chán quá!",
                                        "|-1|Không có ai đủ mạnh để đấu với ta sao?",
                                        "|-1|Ta nghèo lắm!Đừng săn ta nữa", }, // text chat 2
                        new String[] { "|-1|Đen lắm em trai !" }, // text chat 3
                        TypeAppear.APPEAR_WITH_ANOTHER);
        public static final BossData SUPER_ANDROID_17 = new BossData(
                        "Super Hentai", // name
                        ConstPlayer.TRAI_DAT, // gender
                        new short[] { 636, 637, 638, -1, -1, -1 }, // outfit {head, body, leg, bag, aura, eff}
                        200000, // dame
                        new int[] { 1000000000 }, // hp
                        new int[] { 98, 99, 100, 96, 92, 93 }, // map join
                        new int[][] {
                                        { Skill.LIEN_HOAN, 1, 300 }, { Skill.LIEN_HOAN, 2, 400 },
                                        { Skill.LIEN_HOAN, 3, 500 }, { Skill.LIEN_HOAN, 4, 600 },
                                        { Skill.LIEN_HOAN, 5, 700 }, { Skill.LIEN_HOAN, 6, 800 },
                                        { Skill.LIEN_HOAN, 7, 900 },
                                        { Skill.KAMEJOKO, 7, 600 }, { Skill.KAMEJOKO, 6, 700 },
                                        { Skill.KAMEJOKO, 5, 800 }, { Skill.KAMEJOKO, 4, 900 },
                                        { Skill.KAMEJOKO, 3, 1000 }, { Skill.KAMEJOKO, 2, 1100 },
                                        { Skill.KAMEJOKO, 1, 1002 },
                                        { Skill.ANTOMIC, 1, 130 }, { Skill.ANTOMIC, 2, 140 }, { Skill.ANTOMIC, 3, 150 },
                                        { Skill.ANTOMIC, 4, 160 }, { Skill.ANTOMIC, 5, 170 }, { Skill.ANTOMIC, 6, 190 },
                                        { Skill.ANTOMIC, 7, 200 },
                                        { Skill.MASENKO, 1, 210 }, { Skill.MASENKO, 5, 220 }, { Skill.MASENKO, 6, 230 },
                                        { Skill.DICH_CHUYEN_TUC_THOI, 2, 60000 },
                                        { Skill.KHIEN_NANG_LUONG, 7, 50000 }, }, // skill//skill
                        new String[] {}, // text chat 1
                        new String[] {}, // text chat 2
                        new String[] {}, // text chat 3
                        REST_10_M,
                        new int[] { BossID.DR_KORE_GT, BossID.DR_MYUU });

        public static final BossData DR_MYUU = new BossData(
                        "Dr.Myuu", // name
                        ConstPlayer.TRAI_DAT, // gender
                        new short[] { 255, 256, 257, -1, -1, -1 }, // outfit {head, body, leg, bag, aura, eff}
                        60000, // dame
                        new int[] { 450000000 }, // hp
                        new int[] { 96, 94, 93 }, // map join
                        new int[][] {
                                        { Skill.THOI_MIEN, 3, 100000 },
                                        { Skill.KAMEJOKO, 7, 1000 },
                                        { Skill.LIEN_HOAN, 7, 300 }, }, // skill
                        // skill
                        new String[] {}, // text chat 1
                        new String[] { "|-1|Oải rồi hả?",
                                        "|-1|Ê cố lên nhóc",
                                        "|-1|Chán",
                                        "|-1|Mi khá đấy, nhưng so với ta cũng chỉ là hạng tôm tép",
                                        "|-1|Lôi Công Trảo",
                                        "|-1|Cho dù ngươi có mạnh đến đâu.. thì cũng không đánh bại được rôbốt bọn ta",
                                        "|-2|Lão già khôn thật!!",
                                        "|-2|Hừ! Lão già khốn kiếp!", }, // text chat 2
                        new String[] {}, // text chat 3
                        TypeAppear.APPEAR_WITH_ANOTHER);
        public static final BossData DR_KORE_GT = new BossData(
                        "Dr.Kore GT", // name
                        ConstPlayer.TRAI_DAT, // gender
                        new short[] { 255, 256, 257, -1, -1, -1 }, // outfit {head, body, leg, bag, aura, eff}
                        60000, // dame
                        new int[] { 450000000 }, // hp
                        new int[] { 96, 94, 93 }, // map join
                        new int[][] {
                                        { Skill.THOI_MIEN, 3, 100000 },
                                        { Skill.KAMEJOKO, 7, 1000 },
                                        { Skill.LIEN_HOAN, 7, 300 }, }, // skill
                        // skill
                        new String[] {}, // text chat 1
                        new String[] { "|-1|Oải rồi hả?",
                                        "|-1|Ê cố lên nhóc",
                                        "|-1|Chán",
                                        "|-1|Mi khá đấy, nhưng so với ta cũng chỉ là hạng tôm tép",
                                        "|-1|Lôi Công Trảo",
                                        "|-1|Cho dù ngươi có mạnh đến đâu.. thì cũng không đánh bại được rôbốt bọn ta",
                                        "|-2|Lão già khôn thật!!",
                                        "|-2|Hừ! Lão già khốn kiếp!", }, // text chat 2
                        new String[] {}, // text chat 3
                        TypeAppear.APPEAR_WITH_ANOTHER);

        public static final BossData SUPER_XEN = new BossData(
                        "Super Xên", // name
                        ConstPlayer.TRAI_DAT, // gender
                        new short[] { 234, 235, 236, -1, -1, -1 }, // outfit {head, body, leg, bag, aura, eff}
                        170000, // dame
                        new int[] { 1300000000 }, // hp
                        new int[] { 103 }, // map join
                        new int[][] {
                                        { Skill.LIEN_HOAN, 1, 300 }, { Skill.LIEN_HOAN, 2, 400 },
                                        { Skill.LIEN_HOAN, 3, 500 }, { Skill.LIEN_HOAN, 4, 600 },
                                        { Skill.LIEN_HOAN, 5, 700 }, { Skill.LIEN_HOAN, 6, 800 },
                                        { Skill.LIEN_HOAN, 7, 900 },
                                        { Skill.KAMEJOKO, 7, 600 }, { Skill.KAMEJOKO, 6, 700 },
                                        { Skill.KAMEJOKO, 5, 800 }, { Skill.KAMEJOKO, 4, 900 },
                                        { Skill.KAMEJOKO, 3, 1000 }, { Skill.KAMEJOKO, 2, 1100 },
                                        { Skill.KAMEJOKO, 1, 1002 },
                                        { Skill.ANTOMIC, 1, 130 }, { Skill.ANTOMIC, 2, 140 }, { Skill.ANTOMIC, 3, 150 },
                                        { Skill.ANTOMIC, 4, 160 }, { Skill.ANTOMIC, 5, 170 }, { Skill.ANTOMIC, 6, 190 },
                                        { Skill.ANTOMIC, 7, 200 },
                                        { Skill.MASENKO, 1, 210 }, { Skill.MASENKO, 5, 220 }, { Skill.MASENKO, 6, 230 },
                                        { Skill.DICH_CHUYEN_TUC_THOI, 2, 60000 },
                                        { Skill.KHIEN_NANG_LUONG, 7, 50000 }, }, // skill//skill
                        new String[] {}, // text chat 1
                        new String[] {}, // text chat 2
                        new String[] {}, // text chat 3
                        REST_10_M);
        ///
        public static final BossData KAMIRIN = new BossData(
                        "Touchi", // name
                        ConstPlayer.TRAI_DAT, // gender
                        new short[] { 356, 357, 358, -1, -1, -1 }, // outfit {head, body, leg, bag, aura, eff}
                        52000, // dame
                        new int[] { 400000000 }, // hp
                        new int[] { 29, 30, 5, 20, 37, 38, 13, 33, 34 }, // map join

                        (int[][]) Util.addArray(FULL_GALICK, FULL_KAMEJOKO, FULL_LIENHOAN, FULL_ANTOMIC, FULL_DEMON,
                                        FULL_MASENKO, FULL_DRAGON), // skill
                        new String[] {}, // text chat 1
                        new String[] { "|-1|Sao thế hả? Ta mới chỉ khởi động thôi mà!",
                                        "|-2|Ngươi đánh giá thấp bọn ta quá đấy!",
                                        "|-2|Đừng có tưởng bở, lũ sâu bọ member!",
                                        "|-1|Nếu có ý định gây trở ngại cho cuộc chiến giữa ta và Sôngôku, thì ta cũng sẽ giết ngươi ngay lập tức",
                                        "|-2|Ngươi tưởng ta để cho ngươi giết được ta ngay à?",
                                        "|-2|Đúng là mạnh mồm thật đấy!",
                                        "|-2|Đỡ này", }, // text chat 2
                        new String[] { "|-1|Sô..Sông...gôku....." }, // text chat 3
                        REST_15_M);

        public static final BossData KAMILOC = new BossData(
                        "LiuLiu", // name
                        ConstPlayer.TRAI_DAT, // gender
                        new short[] { 397, 398, 399, -1, -1, -1 }, // outfit {head, body, leg, bag, aura, eff}
                        42000, // dame
                        new int[] { 400000000 }, // hp
                        new int[] { 29, 30, 5, 20, 37, 38, 13, 33, 34 }, // map join
                        new int[][] {

                                        { Skill.DICH_CHUYEN_TUC_THOI, 7, 100000 },

                                        { Skill.THAI_DUONG_HA_SAN, 7, 130000 },
                                        { Skill.LIEN_HOAN, 7, 300 }, }, // skill
                        new String[] {}, // text chat 1
                        new String[] {}, // text chat 2
                        new String[] { "|0|   tiêu tùng cả rồi à?" }, // text chat 3
                        REST_15_M);

        public static final BossData KAMI_SOOME = new BossData(
                        "Bulma", // name
                        ConstPlayer.TRAI_DAT, // gender
                        new short[] { 409, 410, 411, -1, -1, -1 }, // outfit {head, body, leg, bag, aura, eff}
                        52000, // dame
                        new int[] { 400000000 }, // hp
                        new int[] { 29, 30, 5, 20, 37, 38, 13, 33, 34 }, // map join
                        new int[][] {
                                        { Skill.LIEN_HOAN, 1, 300 }, { Skill.LIEN_HOAN, 2, 400 },
                                        { Skill.LIEN_HOAN, 3, 500 }, { Skill.LIEN_HOAN, 4, 600 },
                                        { Skill.LIEN_HOAN, 5, 700 }, { Skill.LIEN_HOAN, 6, 800 },
                                        { Skill.LIEN_HOAN, 7, 900 },
                                        { Skill.KAMEJOKO, 7, 600 }, { Skill.KAMEJOKO, 6, 700 },
                                        { Skill.KAMEJOKO, 5, 800 }, { Skill.KAMEJOKO, 4, 900 },
                                        { Skill.KAMEJOKO, 3, 1000 }, { Skill.KAMEJOKO, 2, 1100 },
                                        { Skill.KAMEJOKO, 1, 1002 },
                                        { Skill.ANTOMIC, 1, 130 }, { Skill.ANTOMIC, 2, 140 }, { Skill.ANTOMIC, 3, 150 },
                                        { Skill.ANTOMIC, 4, 160 }, { Skill.ANTOMIC, 5, 170 }, { Skill.ANTOMIC, 6, 190 },
                                        { Skill.ANTOMIC, 7, 200 },
                                        { Skill.MASENKO, 1, 210 }, { Skill.MASENKO, 5, 220 }, { Skill.MASENKO, 6, 230 },

                                        { Skill.KHIEN_NANG_LUONG, 7, 50000 }, }, // skill//skill
                        new String[] {}, // text chat 1
                        new String[] {}, // text chat 2
                        new String[] { "|-2|Thì ra vẫn chỉ là một đống sắt vụn!" }, // text chat 3
                        REST_15_M);
        // giang ho
        public static final BossData SOI_GIANGHO = new BossData(
                        "Sói đại ca", // name
                        ConstPlayer.TRAI_DAT, // gender
                        new short[] { 394, 395, 396, -1, -1, -1 }, // outfit {head, body, leg, bag, aura, eff}
                        42000, // dame
                        new int[] { 50000000 }, // hp
                        new int[] { 0, 1, 2, 3, 4, 7, 8, 11, 12, 14, 15, 15, 17, 18, 19, 27, 28, 32, 32, 35, 36 }, // map
                                                                                                                   // join
                        new int[][] {
                                        { Skill.DRAGON, 1, 100 }, { Skill.DRAGON, 2, 200 }, { Skill.DRAGON, 3, 300 },
                                        { Skill.DRAGON, 7, 700 },
                                        { Skill.KAMEJOKO, 1, 1000 }, { Skill.KAMEJOKO, 2, 1200 },
                                        { Skill.KAMEJOKO, 5, 1500 }, { Skill.KAMEJOKO, 7, 1700 },
                                        { Skill.GALICK, 1, 100 } }, // skill
                        new String[] {}, // text chat 1
                        new String[] {}, // text chat 2
                        new String[] { "|-1|Cay quá!",
                                        "|-1|Ta mà lại thua được sao?",
                                        "|-1|Hãy trả thù cho ta!" }, // text chat 3
                        REST_15_M);

        public static final BossData ODO_GIANGHO = new BossData(
                        "Ở dơ đại ca", // name
                        ConstPlayer.TRAI_DAT, // gender
                        new short[] { 400, 401, 402, -1, -1, -1 }, // outfit {head, body, leg, bag, aura, eff}
                        44000, // dame
                        new int[] { 160000000 }, // hp
                        new int[] { 0, 1, 2, 3, 4, 7, 8, 11, 12, 14, 15, 15, 17, 18, 19, 27, 28, 32, 32, 35, 36 }, // map
                                                                                                                   // join
                        new int[][] {
                                        { Skill.DRAGON, 1, 100 }, { Skill.DRAGON, 2, 200 }, { Skill.DRAGON, 3, 300 },
                                        { Skill.DRAGON, 7, 700 },
                                        { Skill.KAMEJOKO, 1, 1000 }, { Skill.KAMEJOKO, 2, 1200 },
                                        { Skill.KAMEJOKO, 5, 1500 }, { Skill.KAMEJOKO, 7, 1700 },
                                        { Skill.GALICK, 1, 100 }
                        }, // skill
                        new String[] {}, // text chat 1
                        new String[] {}, // text chat 2
                        new String[] { "|-1|Cay quá!",
                                        "|-1|Ta mà lại thua được sao?",
                                        "|-1|Hãy trả thù cho ta!"
                        }, // text chat 3
                        TypeAppear.APPEAR_WITH_ANOTHER // type appear
        );

        public static final BossData XINBATO_GIANGHO = new BossData(
                        "Xinbato đại ca", // name
                        ConstPlayer.TRAI_DAT, // gender
                        new short[] { 359, 360, 361, -1, -1, -1 }, // outfit {head, body, leg, bag, aura, eff}
                        46000, // dame
                        new int[] { 170000000 }, // hp
                        new int[] { 0, 1, 2, 3, 4, 7, 8, 11, 12, 14, 15, 15, 17, 18, 19, 27, 28, 32, 32, 35, 36 }, // map
                                                                                                                   // join
                        new int[][] {
                                        { Skill.DRAGON, 1, 100 }, { Skill.DRAGON, 2, 200 }, { Skill.DRAGON, 3, 300 },
                                        { Skill.DRAGON, 7, 700 },
                                        { Skill.KAMEJOKO, 1, 1000 }, { Skill.KAMEJOKO, 2, 1200 },
                                        { Skill.KAMEJOKO, 5, 1500 }, { Skill.KAMEJOKO, 7, 1700 },
                                        { Skill.GALICK, 1, 100 }
                        }, // skill
                        new String[] {}, // text chat 1
                        new String[] {}, // text chat 2
                        new String[] { "|-1|Cay quá!",
                                        "|-1|Ta mà lại thua được sao?",
                                        "|-1|Hãy trả thù cho ta!"
                        }, // text chat 3
                        TypeAppear.APPEAR_WITH_ANOTHER // type appear
        );

        public static final BossData CHACHA_GIANGHO = new BossData(
                        "Chacha đại ca", // name
                        ConstPlayer.TRAI_DAT, // gender
                        new short[] { 362, 363, 364, -1, -1, -1 }, // outfit {head, body, leg, bag, aura, eff}
                        48000, // dame
                        new int[] { 180000000 }, // hp
                        new int[] { 0, 1, 2, 3, 4, 7, 8, 11, 12, 14, 15, 15, 17, 18, 19, 27, 28, 32, 32, 35, 36 }, // map
                                                                                                                   // join
                        new int[][] {
                                        { Skill.DRAGON, 1, 100 }, { Skill.DRAGON, 2, 200 }, { Skill.DRAGON, 3, 300 },
                                        { Skill.DRAGON, 7, 700 },
                                        { Skill.KAMEJOKO, 1, 1000 }, { Skill.KAMEJOKO, 2, 1200 },
                                        { Skill.KAMEJOKO, 5, 1500 }, { Skill.KAMEJOKO, 7, 1700 },
                                        { Skill.GALICK, 1, 100 }
                        }, // skill
                        new String[] {}, // text chat 1
                        new String[] {}, // text chat 2
                        new String[] { "|-1|Cay quá!",
                                        "|-1|Ta mà lại thua được sao?",
                                        "|-1|Hãy trả thù cho ta!"
                        }, // text chat 3
                        TypeAppear.APPEAR_WITH_ANOTHER // type appear
        );

        public static final BossData PONPUT_GIANGHO = new BossData(
                        "Ponput đại ca", // name
                        ConstPlayer.TRAI_DAT, // gender
                        new short[] { 365, 366, 367, -1, -1, -1 }, // outfit {head, body, leg, bag, aura, eff}
                        50000, // dame
                        new int[] { 190000000 }, // hp
                        new int[] { 0, 1, 2, 3, 4, 7, 8, 11, 12, 14, 15, 15, 17, 18, 19, 27, 28, 32, 32, 35, 36 }, // map
                                                                                                                   // join
                        new int[][] {
                                        { Skill.DRAGON, 1, 100 }, { Skill.DRAGON, 2, 200 }, { Skill.DRAGON, 3, 300 },
                                        { Skill.DRAGON, 7, 700 },
                                        { Skill.KAMEJOKO, 1, 1000 }, { Skill.KAMEJOKO, 2, 1200 },
                                        { Skill.KAMEJOKO, 5, 1500 }, { Skill.KAMEJOKO, 7, 1700 },
                                        { Skill.GALICK, 1, 100 }
                        }, // skill
                        new String[] {}, // text chat 1
                        new String[] {}, // text chat 2
                        new String[] { "|-1|Cay quá!",
                                        "|-1|Ta mà lại thua được sao?",
                                        "|-1|Hãy trả thù cho ta!"
                        }, // text chat 3
                        TypeAppear.APPEAR_WITH_ANOTHER // type appear
        );

        public static final BossData CHANXU_GIANGHO = new BossData(
                        "Chanxu đại ca", // name
                        ConstPlayer.TRAI_DAT, // gender
                        new short[] { 371, 372, 373, -1, -1, -1 }, // outfit {head, body, leg, bag, aura, eff}
                        52000, // dame
                        new int[] { 200000000 }, // hp
                        new int[] { 0, 1, 2, 3, 4, 7, 8, 11, 12, 14, 15, 15, 17, 18, 19, 27, 28, 32, 32, 35, 36 }, // map
                                                                                                                   // join
                        new int[][] {
                                        { Skill.DRAGON, 1, 100 }, { Skill.DRAGON, 2, 200 }, { Skill.DRAGON, 3, 300 },
                                        { Skill.DRAGON, 7, 700 },
                                        { Skill.KAMEJOKO, 1, 1000 }, { Skill.KAMEJOKO, 2, 1200 },
                                        { Skill.KAMEJOKO, 5, 1500 }, { Skill.KAMEJOKO, 7, 1700 },
                                        { Skill.GALICK, 1, 100 }
                        }, // skill
                        new String[] {}, // text chat 1
                        new String[] {}, // text chat 2
                        new String[] { "|-1|Cay quá!",
                                        "|-1|Ta mà lại thua được sao?",
                                        "|-1|Hãy trả thù cho ta!"
                        }, // text chat 3
                        TypeAppear.APPEAR_WITH_ANOTHER // type appear
        );

        public static final BossData TAUPAYPAY_GIANGHO = new BossData(
                        "Taupaypay đại ca", // name
                        ConstPlayer.TRAI_DAT, // gender
                        new short[] { 92, 93, 94, -1, -1, -1 }, // outfit {head, body, leg, bag, aura, eff}
                        54000, // dame
                        new int[] { 210000000 }, // hp
                        new int[] { 0, 1, 2, 3, 4, 7, 8, 11, 12, 14, 15, 15, 17, 18, 19, 27, 28, 32, 32, 35, 36 }, // map
                                                                                                                   // join
                        new int[][] {
                                        { Skill.DRAGON, 1, 100 }, { Skill.DRAGON, 2, 200 }, { Skill.DRAGON, 3, 300 },
                                        { Skill.DRAGON, 7, 700 },
                                        { Skill.KAMEJOKO, 1, 1000 }, { Skill.KAMEJOKO, 2, 1200 },
                                        { Skill.KAMEJOKO, 5, 1500 }, { Skill.KAMEJOKO, 7, 1700 },
                                        { Skill.GALICK, 1, 100 }
                        }, // skill
                        new String[] {}, // text chat 1
                        new String[] {}, // text chat 2
                        new String[] { "|-1|Cay quá!",
                                        "|-1|Ta mà lại thua được sao?",
                                        "|-1|Hãy trả thù cho ta!"
                        }, // text chat 3
                        TypeAppear.APPEAR_WITH_ANOTHER // type appear
        );
        public static final BossData YAMCHA_GIANGHO = new BossData(
                        "Yamcha đại ca", // name
                        ConstPlayer.TRAI_DAT, // gender
                        new short[] { 374, 375, 376, -1, -1, -1 }, // outfit {head, body, leg, bag, aura, eff}
                        56000, // dame
                        new int[] { 220000000 }, // hp
                        new int[] { 0, 1, 2, 3, 4, 7, 8, 11, 12, 14, 15, 15, 17, 18, 19, 27, 28, 32, 32, 35, 36 }, // map
                                                                                                                   // join
                        new int[][] {
                                        { Skill.DRAGON, 1, 100 }, { Skill.DRAGON, 2, 200 }, { Skill.DRAGON, 3, 300 },
                                        { Skill.DRAGON, 7, 700 },
                                        { Skill.KAMEJOKO, 1, 1000 }, { Skill.KAMEJOKO, 2, 1200 },
                                        { Skill.KAMEJOKO, 5, 1500 }, { Skill.KAMEJOKO, 7, 1700 },
                                        { Skill.GALICK, 1, 100 }
                        }, // skill
                        new String[] {}, // text chat 1
                        new String[] {}, // text chat 2
                        new String[] { "|-1|Cay quá!",
                                        "|-1|Ta mà lại thua được sao?",
                                        "|-1|Hãy trả thù cho ta!"
                        }, // text chat 3
                        TypeAppear.APPEAR_WITH_ANOTHER // type appear
        );

        public static final BossData JACKYCHUN_GIANGHO = new BossData(
                        "Jackychun đại ca", // name
                        ConstPlayer.TRAI_DAT, // gender
                        new short[] { 356, 357, 358, -1, -1, -1 }, // outfit {head, body, leg, bag, aura, eff}
                        58000, // dame
                        new int[] { 240000000 }, // hp
                        new int[] { 0, 1, 2, 3, 4, 7, 8, 11, 12, 14, 15, 15, 17, 18, 19, 27, 28, 32, 32, 35, 36 }, // map
                                                                                                                   // join
                        new int[][] {
                                        { Skill.DRAGON, 1, 100 }, { Skill.DRAGON, 2, 200 }, { Skill.DRAGON, 3, 300 },
                                        { Skill.DRAGON, 7, 700 },
                                        { Skill.KAMEJOKO, 1, 1000 }, { Skill.KAMEJOKO, 2, 1200 },
                                        { Skill.KAMEJOKO, 5, 1500 }, { Skill.KAMEJOKO, 7, 1700 },
                                        { Skill.GALICK, 1, 100 }
                        }, // skill
                        new String[] {}, // text chat 1
                        new String[] {}, // text chat 2
                        new String[] { "|-1|Cay quá!",
                                        "|-1|Ta mà lại thua được sao?",
                                        "|-1|Hãy trả thù cho ta!"
                        }, // text chat 3
                        TypeAppear.APPEAR_WITH_ANOTHER // type appear
        );

        public static final BossData THIENXINHANG_GIANGHO = new BossData(
                        "Thenxinhang đại ca", // name
                        ConstPlayer.TRAI_DAT, // gender
                        new short[] { 368, 369, 370, -1, -1, -1 }, // outfit {head, body, leg, bag, aura, eff}
                        60000, // dame
                        new int[] { 260000000 }, // hp
                        new int[] { 0, 1, 2, 3, 4, 7, 8, 11, 12, 14, 15, 15, 17, 18, 19, 27, 28, 32, 32, 35, 36 }, // map
                                                                                                                   // join
                        new int[][] {
                                        { Skill.DRAGON, 1, 100 }, { Skill.DRAGON, 2, 200 }, { Skill.DRAGON, 3, 300 },
                                        { Skill.DRAGON, 7, 700 },
                                        { Skill.KAMEJOKO, 1, 1000 }, { Skill.KAMEJOKO, 2, 1200 },
                                        { Skill.KAMEJOKO, 5, 1500 }, { Skill.KAMEJOKO, 7, 1700 },
                                        { Skill.THAI_DUONG_HA_SAN, 1, 15000 }
                        }, // skill
                        new String[] {}, // text chat 1
                        new String[] {}, // text chat 2
                        new String[] { "|-1|Cay quá!",
                                        "|-1|Ta mà lại thua được sao?",
                                        "|-1|Hãy trả thù cho ta!"
                        }, // text chat 3
                        TypeAppear.APPEAR_WITH_ANOTHER // type appear
        );

        ///
        public static final BossData CUMBERBLACK = new BossData(
                        "CumBer  Black", // name
                        ConstPlayer.TRAI_DAT, // gender
                        new short[] { 1204, 1205, 1206, -1, -1, -1 }, // outfit {head, body, leg, bag, aura, eff}
                        185000, // dame
                        new int[] { 1000000000 }, // hp
                        new int[] { 193 }, // map join
                        new int[][] {
                                        { Skill.LIEN_HOAN, 1, 300 }, { Skill.LIEN_HOAN, 2, 400 },
                                        { Skill.LIEN_HOAN, 3, 500 }, { Skill.LIEN_HOAN, 4, 600 },
                                        { Skill.LIEN_HOAN, 5, 700 }, { Skill.LIEN_HOAN, 6, 800 },
                                        { Skill.LIEN_HOAN, 7, 900 },
                                        { Skill.KAMEJOKO, 7, 600 }, { Skill.KAMEJOKO, 6, 700 },
                                        { Skill.KAMEJOKO, 5, 800 }, { Skill.KAMEJOKO, 4, 900 },
                                        { Skill.KAMEJOKO, 3, 1000 }, { Skill.KAMEJOKO, 2, 1100 },
                                        { Skill.KAMEJOKO, 1, 1002 },
                                        { Skill.ANTOMIC, 1, 130 }, { Skill.ANTOMIC, 2, 140 }, { Skill.ANTOMIC, 3, 150 },
                                        { Skill.ANTOMIC, 4, 160 }, { Skill.ANTOMIC, 5, 170 }, { Skill.ANTOMIC, 6, 190 },
                                        { Skill.ANTOMIC, 7, 200 },
                                        { Skill.MASENKO, 1, 210 }, { Skill.MASENKO, 5, 220 }, { Skill.MASENKO, 6, 230 },

                                        { Skill.QUA_CAU_KENH_KHI, 3, 30000 },
                                        { Skill.DICH_CHUYEN_TUC_THOI, 7, 30000 },
                                        { Skill.DICH_CHUYEN_TUC_THOI, 2, 60000 },
                                        { Skill.KHIEN_NANG_LUONG, 7, 50000 }, }, // skill//skill
                        new String[] {}, // text chat 1
                        new String[] {}, // text chat 2
                        new String[] {}, // text chat 3
                        REST_15_M);
        public static final BossData CUMBERYELLOW = new BossData(
                        "Cumber Vàng", // name
                        ConstPlayer.TRAI_DAT, // gender
                        new short[] { 1207, 1208, 1209, -1, -1, -1 }, // outfit {head, body, leg, bag, aura, eff}
                        90000, // dame
                        new int[] { 1000000000 }, // hp
                        new int[] { 194 }, // map join
                        new int[][] {
                                        { Skill.LIEN_HOAN, 1, 300 }, { Skill.LIEN_HOAN, 2, 400 },
                                        { Skill.LIEN_HOAN, 3, 500 }, { Skill.LIEN_HOAN, 4, 600 },
                                        { Skill.LIEN_HOAN, 5, 700 }, { Skill.LIEN_HOAN, 6, 800 },
                                        { Skill.LIEN_HOAN, 7, 900 },
                                        { Skill.KAMEJOKO, 7, 600 }, { Skill.KAMEJOKO, 6, 700 },
                                        { Skill.KAMEJOKO, 5, 800 }, { Skill.KAMEJOKO, 4, 900 },
                                        { Skill.KAMEJOKO, 3, 1000 }, { Skill.KAMEJOKO, 2, 1100 },
                                        { Skill.KAMEJOKO, 1, 1002 },
                                        { Skill.ANTOMIC, 1, 130 }, { Skill.ANTOMIC, 2, 140 }, { Skill.ANTOMIC, 3, 150 },
                                        { Skill.ANTOMIC, 4, 160 }, { Skill.ANTOMIC, 5, 170 }, { Skill.ANTOMIC, 6, 190 },
                                        { Skill.ANTOMIC, 7, 200 },
                                        { Skill.MASENKO, 1, 210 }, { Skill.MASENKO, 5, 220 }, { Skill.MASENKO, 6, 230 },

                                        { Skill.QUA_CAU_KENH_KHI, 3, 30000 },
                                        { Skill.THAI_DUONG_HA_SAN, 5, 100000 },
                                        { Skill.DICH_CHUYEN_TUC_THOI, 2, 60000 },
                                        { Skill.KHIEN_NANG_LUONG, 7, 50000 }, }, // skill//skill
                        new String[] {}, // text chat 1
                        new String[] {}, // text chat 2
                        new String[] {}, // text chat 3
                        REST_15_M);
        public static final BossData CUMBERRED = new BossData(
                        "Cumber Đỏ", // name
                        ConstPlayer.TRAI_DAT, // gender
                        new short[] { 2063, 2064, 2065, -1, -1, -1 }, // outfit {head, body, leg, bag, aura, eff}
                        90000, // dame
                        new int[] { 1000000000 }, // hp
                        new int[] { 195 }, // map join
                        new int[][] {
                                        { Skill.LIEN_HOAN, 1, 300 }, { Skill.LIEN_HOAN, 2, 400 },
                                        { Skill.LIEN_HOAN, 3, 500 }, { Skill.LIEN_HOAN, 4, 600 },
                                        { Skill.LIEN_HOAN, 5, 700 }, { Skill.LIEN_HOAN, 6, 800 },
                                        { Skill.LIEN_HOAN, 7, 900 },
                                        { Skill.KAMEJOKO, 7, 600 }, { Skill.KAMEJOKO, 6, 700 },
                                        { Skill.KAMEJOKO, 5, 800 }, { Skill.KAMEJOKO, 4, 900 },
                                        { Skill.KAMEJOKO, 3, 1000 }, { Skill.KAMEJOKO, 2, 1100 },
                                        { Skill.KAMEJOKO, 1, 1002 },
                                        { Skill.ANTOMIC, 1, 130 }, { Skill.ANTOMIC, 2, 140 }, { Skill.ANTOMIC, 3, 150 },
                                        { Skill.ANTOMIC, 4, 160 }, { Skill.ANTOMIC, 5, 170 }, { Skill.ANTOMIC, 6, 190 },
                                        { Skill.ANTOMIC, 7, 200 },
                                        { Skill.MASENKO, 1, 210 }, { Skill.MASENKO, 5, 220 }, { Skill.MASENKO, 6, 230 },

                                        { Skill.QUA_CAU_KENH_KHI, 3, 30000 },
                                        { Skill.THAI_DUONG_HA_SAN, 5, 100000 },
                                        { Skill.DICH_CHUYEN_TUC_THOI, 2, 60000 },
                                        { Skill.KHIEN_NANG_LUONG, 7, 50000 }, }, // skill//skill
                        new String[] {}, // text chat 1
                        new String[] {}, // text chat 2
                        new String[] {}, // text chat 3
                        REST_15_M);
        public static final BossData SOI_HEC_QUYN = BossData.builder()
                        .name("Sói Hẹc Quyn")
                        .gender(ConstPlayer.TRAI_DAT)
                        .dame(40000)
                        .hp(new int[] { 50000000 })
                        .outfit(new short[] { 394, 395, 396, -1, -1, -1 })
                        .mapJoin(new int[] { 129 })
                        .skillTemp(new int[][] {
                                        { Skill.DRAGON, 1, 100 }, { Skill.DRAGON, 2, 200 }, { Skill.DRAGON, 3, 300 },
                                        { Skill.DRAGON, 7, 700 },
                                        { Skill.KAMEJOKO, 1, 1000 }, { Skill.KAMEJOKO, 2, 1200 },
                                        { Skill.KAMEJOKO, 5, 1500 }, { Skill.KAMEJOKO, 7, 1700 },
                                        { Skill.GALICK, 1, 100 }
                        })
                        .secondsRest(REST_5_S)
                        .build();

        public static final BossData O_DO = BossData.builder()
                        .name("Ở Dơ")
                        .gender(ConstPlayer.TRAI_DAT)
                        .dame(60000)
                        .hp(new int[] { 80000000 })
                        .outfit(new short[] { 400, 401, 402, -1, -1, -1 })
                        .mapJoin(new int[] { 129 })
                        .skillTemp(new int[][] {
                                        { Skill.DRAGON, 1, 100 }, { Skill.DRAGON, 2, 200 }, { Skill.DRAGON, 3, 300 },
                                        { Skill.DRAGON, 7, 700 },
                                        { Skill.KAMEJOKO, 1, 1000 }, { Skill.KAMEJOKO, 2, 1200 },
                                        { Skill.KAMEJOKO, 5, 1500 }, { Skill.KAMEJOKO, 7, 1700 },
                                        { Skill.GALICK, 1, 100 }
                        })
                        .secondsRest(REST_5_S)
                        .build();

        public static final BossData XINBATO = BossData.builder()
                        .name("Xinbatô")
                        .gender(ConstPlayer.TRAI_DAT)
                        .dame(80000)
                        .hp(new int[] { 130000000 })
                        .outfit(new short[] { 359, 360, 361, -1, -1, -1 })
                        .mapJoin(new int[] { 129 })
                        .skillTemp(new int[][] {
                                        { Skill.DRAGON, 1, 100 }, { Skill.DRAGON, 2, 200 }, { Skill.DRAGON, 3, 300 },
                                        { Skill.DRAGON, 7, 700 },
                                        { Skill.KAMEJOKO, 1, 1000 }, { Skill.KAMEJOKO, 2, 1200 },
                                        { Skill.KAMEJOKO, 5, 1500 }, { Skill.KAMEJOKO, 7, 1700 },
                                        { Skill.GALICK, 1, 100 }
                        })
                        .secondsRest(REST_5_S)
                        .build();

        public static final BossData CHA_PA = BossData.builder()
                        .name("Cha pa")
                        .gender(ConstPlayer.TRAI_DAT)
                        .dame(100000)
                        .hp(new int[] { 170000000 })
                        .outfit(new short[] { 362, 363, 364, -1, -1, -1 })
                        .mapJoin(new int[] { 129 })
                        .skillTemp(new int[][] {
                                        { Skill.DRAGON, 1, 100 }, { Skill.DRAGON, 2, 200 }, { Skill.DRAGON, 3, 300 },
                                        { Skill.DRAGON, 7, 700 },
                                        { Skill.KAMEJOKO, 1, 1000 }, { Skill.KAMEJOKO, 2, 1200 },
                                        { Skill.KAMEJOKO, 5, 1500 }, { Skill.KAMEJOKO, 7, 1700 },
                                        { Skill.GALICK, 1, 100 }
                        })
                        .secondsRest(REST_5_S)
                        .build();

        public static final BossData PON_PUT = BossData.builder()
                        .name("Pon put")
                        .gender(ConstPlayer.TRAI_DAT)
                        .dame(120000)
                        .hp(new int[] { 190000000 })
                        .outfit(new short[] { 365, 366, 367, -1, -1, -1 })
                        .mapJoin(new int[] { 129 })
                        .skillTemp(new int[][] {
                                        { Skill.DRAGON, 1, 100 }, { Skill.DRAGON, 2, 200 }, { Skill.DRAGON, 3, 300 },
                                        { Skill.DRAGON, 7, 700 },
                                        { Skill.KAMEJOKO, 1, 1000 }, { Skill.KAMEJOKO, 2, 1200 },
                                        { Skill.KAMEJOKO, 5, 1500 }, { Skill.KAMEJOKO, 7, 1700 },
                                        { Skill.GALICK, 1, 100 }
                        })
                        .secondsRest(REST_5_S)
                        .build();

        public static final BossData CHAN_XU = BossData.builder()
                        .name("Chan xư")
                        .gender(ConstPlayer.TRAI_DAT)
                        .dame(140000)
                        .hp(new int[] { 240000000 })
                        .outfit(new short[] { 371, 372, 373, -1, -1, -1 })
                        .mapJoin(new int[] { 129 })
                        .skillTemp(new int[][] {
                                        { Skill.DRAGON, 1, 100 }, { Skill.DRAGON, 2, 200 }, { Skill.DRAGON, 3, 300 },
                                        { Skill.DRAGON, 7, 700 },
                                        { Skill.KAMEJOKO, 1, 1000 }, { Skill.KAMEJOKO, 2, 1200 },
                                        { Skill.KAMEJOKO, 5, 1500 }, { Skill.KAMEJOKO, 7, 1700 },
                                        { Skill.GALICK, 1, 100 }
                        })
                        .secondsRest(REST_5_S)
                        .build();

        public static final BossData TAU_PAY_PAY = BossData.builder()
                        .name("Tàu Pảy Pảy")
                        .gender(ConstPlayer.TRAI_DAT)
                        .dame(160000)
                        .hp(new int[] { 280000000 })
                        .outfit(new short[] { 92, 93, 94, -1, -1, -1 })
                        .mapJoin(new int[] { 129 })
                        .skillTemp(new int[][] {
                                        { Skill.DRAGON, 1, 100 }, { Skill.DRAGON, 2, 200 }, { Skill.DRAGON, 3, 300 },
                                        { Skill.DRAGON, 7, 700 },
                                        { Skill.KAMEJOKO, 1, 1000 }, { Skill.KAMEJOKO, 2, 1200 },
                                        { Skill.KAMEJOKO, 5, 1500 }, { Skill.KAMEJOKO, 7, 1700 },
                                        { Skill.GALICK, 1, 100 }
                        })
                        .secondsRest(REST_5_S)
                        .build();

        public static final BossData YAMCHA = BossData.builder()
                        .name("Yamcha")
                        .gender(ConstPlayer.TRAI_DAT)
                        .dame(200000)
                        .hp(new int[] { 340000000 })
                        .outfit(new short[] { 374, 375, 376, -1, -1, -1 })
                        .mapJoin(new int[] { 129 })
                        .skillTemp(new int[][] {
                                        { Skill.DRAGON, 1, 100 }, { Skill.DRAGON, 2, 200 }, { Skill.DRAGON, 3, 300 },
                                        { Skill.DRAGON, 7, 700 },
                                        { Skill.KAMEJOKO, 1, 1000 }, { Skill.KAMEJOKO, 2, 1200 },
                                        { Skill.KAMEJOKO, 5, 1500 }, { Skill.KAMEJOKO, 7, 1700 },
                                        { Skill.GALICK, 1, 100 }
                        })
                        .secondsRest(REST_5_S)
                        .build();

        public static final BossData JACKY_CHUN = BossData.builder()
                        .name("Jacky Chun")
                        .gender(ConstPlayer.TRAI_DAT)
                        .dame(220000)
                        .hp(new int[] { 390000000 })
                        .outfit(new short[] { 356, 357, 358, -1, -1, -1 })
                        .mapJoin(new int[] { 129 })
                        .skillTemp(new int[][] {
                                        { Skill.DRAGON, 1, 100 }, { Skill.DRAGON, 2, 200 }, { Skill.DRAGON, 3, 300 },
                                        { Skill.DRAGON, 7, 700 },
                                        { Skill.KAMEJOKO, 1, 1000 }, { Skill.KAMEJOKO, 2, 1200 },
                                        { Skill.KAMEJOKO, 5, 1500 }, { Skill.KAMEJOKO, 7, 1700 },
                                        { Skill.GALICK, 1, 100 }
                        })
                        .secondsRest(REST_5_S)
                        .build();

        public static final BossData THIEN_XIN_HANG = BossData.builder()
                        .name("Thiên Xin Hăng")
                        .gender(ConstPlayer.TRAI_DAT)
                        .dame(300000)
                        .hp(new int[] { 500000000 })
                        .outfit(new short[] { 368, 369, 370, -1, -1, -1 })
                        .mapJoin(new int[] { 129 })
                        .skillTemp(new int[][] {
                                        { Skill.DRAGON, 1, 100 }, { Skill.DRAGON, 2, 200 }, { Skill.DRAGON, 3, 300 },
                                        { Skill.DRAGON, 7, 700 },
                                        { Skill.KAMEJOKO, 1, 1000 }, { Skill.KAMEJOKO, 2, 1200 },
                                        { Skill.KAMEJOKO, 5, 1500 }, { Skill.KAMEJOKO, 7, 1700 },
                        // {Skill.THAI_DUONG_HA_SAN, 1, 15000}
                        })
                        .secondsRest(REST_5_S)
                        .build();

        public static final BossData THIEN_XIN_HANG_CLONE = BossData.builder()
                        .name("Thiên Xin Hăng")
                        .gender(ConstPlayer.TRAI_DAT)
                        .dame(75000)
                        .hp(new int[] { 200000000 })
                        .outfit(new short[] { 368, 369, 370, -1, -1, -1 })
                        .mapJoin(new int[] { 129 })
                        .skillTemp(new int[][] {
                                        { Skill.DRAGON, 1, 100 }, { Skill.DRAGON, 2, 200 }, { Skill.DRAGON, 3, 300 },
                                        { Skill.DRAGON, 7, 700 },
                                        { Skill.KAMEJOKO, 1, 1000 }, { Skill.KAMEJOKO, 2, 1200 },
                                        { Skill.KAMEJOKO, 5, 1500 }, { Skill.KAMEJOKO, 7, 1700 },
                        // {Skill.THAI_DUONG_HA_SAN, 1, 15000}
                        })
                        .secondsRest(REST_5_S)
                        .build();
        public static final BossData LIU_LIU = BossData.builder()
                        .name("Lêu Lêu")
                        .gender(ConstPlayer.TRAI_DAT)
                        .dame(250000)
                        .hp(new int[] { 250000000 })
                        .outfit(new short[] { 397, 398, 399, -1, -1, -1 })
                        .mapJoin(new int[] { 129 })
                        .skillTemp(new int[][] {
                                        { Skill.DRAGON, 1, 100 }, { Skill.DRAGON, 2, 200 }, { Skill.DRAGON, 3, 300 },
                                        { Skill.DRAGON, 7, 700 },
                                        { Skill.KAMEJOKO, 1, 1000 }, { Skill.KAMEJOKO, 2, 1200 },
                                        { Skill.KAMEJOKO, 5, 1500 }, { Skill.KAMEJOKO, 7, 1700 },
                                        { Skill.GALICK, 1, 100 }
                        })
                        .secondsRest(REST_5_S)
                        .build();

        // Thinh kinh
        public static final BossData DUONGTANG = new BossData(
                        "Đường Tăng", // name
                        ConstPlayer.TRAI_DAT, // gender
                        new short[] { 467, 468, 464, -1, -1, -1 }, // outfit {head, body, leg, bag, aura, eff}
                        3000, // dame
                        new int[] { 100 }, // hp
                        new int[] { 122, 123, 124 }, // map
                        new int[][] {
                                        { Skill.KHIEN_NANG_LUONG, 7, 300000 },
                                        { Skill.THAI_DUONG_HA_SAN, 7, 30000 } },
                        new String[] {
                        }, // text chat 1
                        new String[] {
                        }, // text chat 2
                        new String[] { "|-1|serizawa.store" }, // text chat 3
                        REST_1_H, // second rest
                        new int[] { BossID.NGOKHONG, BossID.CHUBATGIOI, BossID.XA_TANG } // boss join map together
        );
        public static final BossData NGOKHONG = new BossData(
                        "Ngộ Không", // name
                        ConstPlayer.TRAI_DAT, // gender
                        new short[] { 462, 463, 464, -1, -1, -1 }, // outfit {head, body, leg, bag, aura, eff}
                        100000, // dame
                        new int[] { 150 }, // hp
                        new int[] { 122, 123, 124 }, // map
                        new int[][] {
                                        { Skill.KHIEN_NANG_LUONG, 7, 300000 },
                                        { Skill.DRAGON, 1, 100 }, { Skill.DRAGON, 2, 200 }, { Skill.DRAGON, 3, 300 },
                                        { Skill.DRAGON, 7, 700 },
                                        { Skill.KAMEJOKO, 1, 1000 }, { Skill.KAMEJOKO, 2, 1200 },
                                        { Skill.KAMEJOKO, 5, 1500 }, { Skill.KAMEJOKO, 7, 1700 },
                                        { Skill.GALICK, 1, 100 }, { Skill.BIEN_KHI, 7, 300000 }
                        },
                        new String[] {
                        }, // text chat 1
                        new String[] {
                        }, // text chat 2
                        new String[] { "|-1|serizawa.store" }, // text chat 3
                        TypeAppear.APPEAR_WITH_ANOTHER // type appear
        );
        public static final BossData CHUBATGIOI = new BossData(
                        "Bát Giới", // name
                        ConstPlayer.TRAI_DAT, // gender
                        new short[] { 465, 466, 464, -1, -1, -1 }, // outfit {head, body, leg, bag, aura, eff}
                        100000, // dame
                        new int[] { 150 }, // hp
                        new int[] { 122, 123, 124 }, // map
                        new int[][] {
                                        { Skill.KHIEN_NANG_LUONG, 7, 300000 }, { Skill.DRAGON, 1, 100 },
                                        { Skill.THOI_MIEN, 7, 40000 }, { Skill.DRAGON, 3, 300 },
                                        { Skill.DRAGON, 7, 700 },
                                        { Skill.KAMEJOKO, 1, 1000 }, { Skill.KAMEJOKO, 2, 1200 },
                                        { Skill.KAMEJOKO, 5, 1500 }, { Skill.KAMEJOKO, 7, 1700 },
                                        { Skill.GALICK, 1, 100 },
                        },
                        new String[] {
                        }, // text chat 1
                        new String[] {
                        }, // text chat 2
                        new String[] { "|-1|serizawa.store" }, // text chat 3
                        TypeAppear.APPEAR_WITH_ANOTHER // type appear
        );
        public static final BossData XA_TANG = new BossData(
                        "Xa tăng", // name
                        ConstPlayer.TRAI_DAT, // gender
                        new short[] { 1296, 1297, 464, -1, -1, -1 }, // outfit {head, body, leg, bag, aura, eff}
                        100000, // dame
                        new int[] { 150 }, // hp
                        new int[] { 122, 123, 124 }, // map
                        new int[][] {
                                        { Skill.KHIEN_NANG_LUONG, 7, 300000 }, { Skill.DRAGON, 1, 100 },
                                        { Skill.THOI_MIEN, 7, 40000 }, { Skill.DRAGON, 3, 300 },
                                        { Skill.DRAGON, 7, 700 },
                                        { Skill.KAMEJOKO, 1, 1000 }, { Skill.KAMEJOKO, 2, 1200 },
                                        { Skill.KAMEJOKO, 5, 1500 }, { Skill.KAMEJOKO, 7, 1700 },
                                        { Skill.GALICK, 1, 100 },
                        },
                        new String[] {
                        }, // text chat 1
                        new String[] {
                        }, // text chat 2
                        new String[] { "|-1|serizawa.store" }, // text chat 3
                        TypeAppear.APPEAR_WITH_ANOTHER // type appear
        );
        // boject
        public static final BossData BOJACK_GIANGHO = new BossData(
                        "Bojack đại ca",
                        ConstPlayer.XAYDA,
                        new short[] { 323, 324, 325, -1, -1, -1 },
                        50000,
                        new int[] { 200000000 },
                        new int[] { 2, 3, 27, 28,17, 18, 35, 36,11, 12, 31, 32 }, //
                        // new int[]{14},
                        new int[][] {
                                        { Skill.LIEN_HOAN, 7, 200 },
                                        { Skill.DRAGON, 7, 1000 },
                                        { Skill.KAMEJOKO, 7, 1000 },
                                        { Skill.DICH_CHUYEN_TUC_THOI, 7, 30000 },
                        }, // skill//skill
                        new String[] {}, // text chat 1
                        new String[] { "|-1|Sao thế hả? Ta mới chỉ khởi động thôi mà!",
                                        "|-2|Ngươi đánh giá thấp bọn ta quá đấy!",
                                        "|-2|Đừng có tưởng bở, lũ sâu bọ member!",
                                        "|-1|Nếu có ý định gây trở ngại cho cuộc chiến giữa ta và Sôngôku, thì ta cũng sẽ giết ngươi ngay lập tức",
                                        "|-2|Ngươi tưởng ta để cho ngươi giết được ta ngay à?",
                                        "|-2|Đúng là mạnh mồm thật đấy!",
                                        "|-2|Đỡ này" }, // text chat 2
                        new String[] {}, // text chat 3
                        TypeAppear.APPEAR_WITH_ANOTHER // type appear
        );

        public static final BossData ZANGYA_GIANGHO = new BossData(
                        "Zangya đại ca", // name
                        ConstPlayer.XAYDA, // gender
                        new short[] { 332, 333, 334, -1, -1, -1 }, // outfit {head, body, leg, bag, aura, eff}
                        41111, // dame
                        new int[] { 190000000 }, // hp
                        new int[] { 2, 3, 27, 28,17, 18, 35, 36,11, 12, 31, 32 }, // map join
                        new int[][] {
                                        { Skill.LIEN_HOAN, 7, 300 },
                                        { Skill.THOI_MIEN, 7, 30000 },
                                        { Skill.MASENKO, 7, 1000 },
                                        { Skill.ANTOMIC, 7, 10000 },
                        }, // skill//skill
                        new String[] {}, // text chat 1
                        new String[] { "|-1|Oải rồi hả?", "|-1|Ê cố lên nhóc",
                                        "|-1|Chán", "|-1|Đại ca Bojack có nhầm không nhỉ",
                                        "|-1|Một mình tao chấp hết tụi bây",
                                        "|-1|HAHAHAHA", "|-1|Chỉ là bọn con nít"
                        }, // text chat 2
                        new String[] {}, // text chat 3
                        TypeAppear.APPEAR_WITH_ANOTHER // type appear
        );

        public static final BossData KOGU_GIANGHO = new BossData(
                        "Kogu đại ca", // name
                        ConstPlayer.XAYDA, // gender
                        new short[] { 329, 330, 331, -1, -1, -1 }, // outfit {head, body, leg, bag, aura, eff}
                        41111, // dame
                        new int[] { 180000000 }, // hp
                        new int[] { 2, 3, 27, 28,17, 18, 35, 36,11, 12, 31, 32 }, // map join
                        new int[][] {
                                        { Skill.LIEN_HOAN, 7, 300 },

                                        { Skill.MASENKO, 7, 1000 },
                                        { Skill.ANTOMIC, 7, 10000 },
                        }, // skill//skill
                        new String[] {}, // text chat 1
                        new String[] { "|-1|Oải rồi hả?", "|-1|Ê cố lên nhóc",
                                        "|-1|Chán", "|-1|Đại ca Bojack có nhầm không nhỉ",
                                        "|-1|Một mình tao chấp hết tụi bây",
                                        "|-1|HAHAHAHA", "|-1|Chỉ là bọn con nít"
                        }, // text chat 2
                        new String[] {}, // text chat 3
                        TypeAppear.APPEAR_WITH_ANOTHER // type appear
        );

        public static final BossData BUJIN_GIANGHO = new BossData(
                        "Bujin đại ca", // name
                        ConstPlayer.XAYDA, // gender
                        new short[] { 341, 342, 343, -1, -1, -1 }, // outfit {head, body, leg, bag, aura, eff}
                        41111, // dame
                        new int[] { 170000000 }, // hp
                        new int[] { 2, 3, 27, 28, 17, 18, 35, 36,11, 12, 31, 32 }, // map join
                        new int[][] {
                                        { Skill.LIEN_HOAN, 7, 300 },
                                        { Skill.MASENKO, 7, 1000 },
                                        { Skill.ANTOMIC, 7, 1000 },
                        }, // skill//skill
                        new String[] {}, // text chat 1
                        new String[] { "|-1|Oải rồi hả?", "|-1|Ê cố lên nhóc",
                                        "|-1|Chán", "|-1|Đại ca Bojack có nhầm không nhỉ",
                                        "|-1|Một mình tao chấp hết tụi bây",
                                        "|-1|HAHAHAHA", "|-1|Chỉ là bọn con nít"
                        }, // text chat 2
                        new String[] {}, // text chat 3
                        REST_10_M);

        public static final BossData BIDO_GIANGHO = new BossData(
                        "Bido đại ca", // name
                        ConstPlayer.XAYDA, // gender
                        new short[] { 335, 336, 337, -1, -1, -1 }, // outfit {head, body, leg, bag, aura, eff}
                        41111, // dame
                        new int[] { 160000000 }, // hp
                        new int[] { 2, 3, 27, 28,17, 18, 35, 36,11, 12, 31, 32 }, // map join
                        new int[][] {
                                        { Skill.LIEN_HOAN, 7, 300 },
                                        { Skill.KAMEJOKO, 7, 10000 },
                                        { Skill.MASENKO, 7, 1000 },
                                        { Skill.ANTOMIC, 7, 1000 },
                        }, // skill//skill
                        new String[] {}, // text chat 1
                        new String[] { "|-1|Oải rồi hả?", "|-1|Ê cố lên nhóc",
                                        "|-1|Chán", "|-1|Đại ca Bojack có nhầm không nhỉ",
                                        "|-1|Một mình tao chấp hết tụi bây",
                                        "|-1|HAHAHAHA", "|-1|Chỉ là bọn con nít"
                        }, // text chat 2
                        new String[] {}, // text chat 3
                        TypeAppear.APPEAR_WITH_ANOTHER // type appear
        );

        // kymestu
        public static final BossData TANJIRO_GIANGHO = new BossData(
                        "Tanjiro đại ca",
                        ConstPlayer.XAYDA,
                        new short[] { 1119, 1120, 1121, -1, -1, -1 },
                        50000,
                        new int[] { 200000000 },
                        new int[] { 17, 18, 35, 36, }, //
                        // new int[]{14},
                        new int[][] {
                                        { Skill.LIEN_HOAN, 7, 300 },
                                        { Skill.DRAGON, 7, 1000 },
                                        { Skill.KAMEJOKO, 7, 1000 },
                                        { Skill.DICH_CHUYEN_TUC_THOI, 7, 30000 },
                        }, // skill//skill
                        new String[] {}, // text chat 1
                        new String[] { "|-1|Sao thế hả? Ta mới chỉ khởi động thôi mà!",
                                        "|-2|Ngươi đánh giá thấp bọn ta quá đấy!",
                                        "|-2|Đừng có tưởng bở, lũ sâu bọ member!",
                                        "|-1|Nếu có ý định gây trở ngại cho cuộc chiến giữa ta và Sôngôku, thì ta cũng sẽ giết ngươi ngay lập tức",
                                        "|-2|Ngươi tưởng ta để cho ngươi giết được ta ngay à?",
                                        "|-2|Đúng là mạnh mồm thật đấy!",
                                        "|-2|Đỡ này" }, // text chat 2
                        new String[] {}, // text chat 3
                        REST_10_M // second rest
        );

        public static final BossData NEZUKO_GIANGHO = new BossData(
                        "Nezuko đại ca", // name
                        ConstPlayer.XAYDA, // gender
                        new short[] { 1128, 1129, 1130, -1, -1, -1 }, // outfit {head, body, leg, bag, aura, eff}
                        41111, // dame
                        new int[] { 170000000 }, // hp
                        new int[] { 17, 18, 35, 36 }, // map join
                        new int[][] {
                                        { Skill.LIEN_HOAN, 7, 300 },
                                        { Skill.KAMEJOKO, 7, 10000 },
                                        { Skill.MASENKO, 7, 1000 },
                                        { Skill.ANTOMIC, 7, 1000 },
                        }, // skill//skill
                        new String[] {}, // text chat 1
                        new String[] { "|-1|Oải rồi hả?", "|-1|Ê cố lên nhóc",
                                        "|-1|Chán", "|-1|Đại ca Tanjiro có nhầm không nhỉ",
                                        "|-1|Một mình tao chấp hết tụi bây",
                                        "|-1|HAHAHAHA", "|-1|Chỉ là bọn con nít"
                        }, // text chat 2
                        new String[] {}, // text chat 3
                        TypeAppear.APPEAR_WITH_ANOTHER // type appear
        );

        public static final BossData ZENITSU_GIANGHO = new BossData(
                        "Zenitsu đại ca", // name
                        ConstPlayer.XAYDA, // gender
                        new short[] { 1125, 1126, 1127, -1, -1, -1 }, // outfit {head, body, leg, bag, aura, eff}
                        41111, // dame
                        new int[] { 180000000 }, // hp
                        new int[] { 17, 18, 35, 36 }, // map join
                        new int[][] {
                                        { Skill.LIEN_HOAN, 7, 300 },
                                        { Skill.KAMEJOKO, 7, 10000 },
                                        { Skill.MASENKO, 7, 1000 },
                                        { Skill.ANTOMIC, 7, 1000 },
                        }, // skill//skill
                        new String[] {}, // text chat 1
                        new String[] { "|-1|Oải rồi hả?", "|-1|Ê cố lên nhóc",
                                        "|-1|Chán", "|-1|Đại ca Tanjiro có nhầm không nhỉ",
                                        "|-1|Một mình tao chấp hết tụi bây",
                                        "|-1|HAHAHAHA", "|-1|Chỉ là bọn con nít"
                        }, // text chat 2
                        new String[] {}, // text chat 3
                        TypeAppear.APPEAR_WITH_ANOTHER // type appear
        );
        public static final BossData INOSUKE_GIANGHO = new BossData(
                        "Inosuke đại ca", // name
                        ConstPlayer.XAYDA, // gender
                        new short[] { 1131, 1132, 1133, -1, -1, -1 }, // outfit {head, body, leg, bag, aura, eff}
                        41111, // dame
                        new int[] { 190000000 }, // hp
                        new int[] { 17, 18, 35, 36 }, // map join
                        new int[][] {
                                        { Skill.LIEN_HOAN, 7, 300 },
                                        { Skill.KAMEJOKO, 7, 1000 },
                                        { Skill.MASENKO, 7, 1000 },
                                        { Skill.ANTOMIC, 7, 1000 },
                        }, // skill//skill
                        new String[] {}, // text chat 1
                        new String[] { "|-1|Oải rồi hả?", "|-1|Ê cố lên nhóc",
                                        "|-1|Chán", "|-1|Đại ca Tanjiro có nhầm không nhỉ",
                                        "|-1|Một mình tao chấp hết tụi bây",
                                        "|-1|HAHAHAHA", "|-1|Chỉ là bọn con nít"
                        }, // text chat 2
                        new String[] {}, // text chat 3
                        TypeAppear.APPEAR_WITH_ANOTHER // type appear
        );
        // lufy
        public static final BossData LUFFY_GIANGHO = new BossData(
                        "Luffy đại ca",
                        ConstPlayer.XAYDA,
                        new short[] { 582, 583, 584, -1, -1, -1 },
                        50000,
                        new int[] { 200000000 },
                        new int[] { 11, 12, 31, 32 }, //
                        // new int[]{14},
                        new int[][] {
                                        { Skill.LIEN_HOAN, 7, 300 },
                                        { Skill.DRAGON, 7, 1000 },
                                        { Skill.KAMEJOKO, 7, 1000 },
                                        { Skill.DICH_CHUYEN_TUC_THOI, 7, 30000 },
                        }, // skill//skill
                        new String[] {}, // text chat 1
                        new String[] { "|-1|Sao thế hả? Ta mới chỉ khởi động thôi mà!",
                                        "|-2|Ngươi đánh giá thấp bọn ta quá đấy!",
                                        "|-2|Đừng có tưởng bở, lũ sâu bọ member!",
                                        "|-1|Nếu có ý định gây trở ngại cho cuộc chiến giữa ta và Sôngôku, thì ta cũng sẽ giết ngươi ngay lập tức",
                                        "|-2|Ngươi tưởng ta để cho ngươi giết được ta ngay à?",
                                        "|-2|Đúng là mạnh mồm thật đấy!",
                                        "|-2|Đỡ này" }, // text chat 2
                        new String[] {}, // text chat 3
                        TypeAppear.APPEAR_WITH_ANOTHER // type appear
        );

        public static final BossData ZORO_GIANGHO = new BossData(
                        "Zoro đại ca", // name
                        ConstPlayer.XAYDA, // gender
                        new short[] { 585, 586, 587, -1, -1, -1 }, // outfit {head, body, leg, bag, aura, eff}
                        41111, // dame
                        new int[] { 190000000 }, // hp
                        new int[] { 11, 12, 31, 32 }, // map join
                        new int[][] {
                                        { Skill.LIEN_HOAN, 7, 300 },
                                        { Skill.KAMEJOKO, 7, 1000 },
                                        { Skill.MASENKO, 7, 1000 },
                                        { Skill.ANTOMIC, 7, 1000 },
                        }, // skill//skill
                        new String[] {}, // text chat 1
                        new String[] { "|-1|Oải rồi hả?", "|-1|Ê cố lên nhóc",
                                        "|-1|Chán", "|-1|Đại ca Luffy có nhầm không nhỉ",
                                        "|-1|Một mình tao chấp hết tụi bây",
                                        "|-1|HAHAHAHA", "|-1|Chỉ là bọn con nít"
                        }, // text chat 2
                        new String[] {}, // text chat 3
                        TypeAppear.APPEAR_WITH_ANOTHER // type appear
        );
        public static final BossData SANJI_GIANGHO = new BossData(
                        "Sanji đại ca", // name
                        ConstPlayer.XAYDA, // gender
                        new short[] { 588, 589, 590, -1, -1, -1 }, // outfit {head, body, leg, bag, aura, eff}
                        41111, // dame
                        new int[] { 180000000 }, // hp
                        new int[] { 11, 12, 31, 32 }, // map join
                        new int[][] {
                                        { Skill.LIEN_HOAN, 7, 300 },
                                        { Skill.KAMEJOKO, 7, 1000 },
                                        { Skill.MASENKO, 7, 1000 },
                                        { Skill.ANTOMIC, 7, 1000 },
                        }, // skill//skill
                        new String[] {}, // text chat 1
                        new String[] { "|-1|Oải rồi hả?", "|-1|Ê cố lên nhóc",
                                        "|-1|Chán", "|-1|Đại ca Luffy có nhầm không nhỉ",
                                        "|-1|Một mình tao chấp hết tụi bây",
                                        "|-1|HAHAHAHA", "|-1|Chỉ là bọn con nít"
                        }, // text chat 2
                        new String[] {}, // text chat 3
                        TypeAppear.APPEAR_WITH_ANOTHER // type appear
        );
        public static final BossData BROOK_GIANGHO = new BossData(
                        "Brook đại ca", // name
                        ConstPlayer.XAYDA, // gender
                        new short[] { 591, 592, 593, -1, -1, -1 }, // outfit {head, body, leg, bag, aura, eff}
                        41111, // dame
                        new int[] { 170000000 }, // hp
                        new int[] { 11, 12, 31, 32 }, // map join
                        new int[][] {
                                        { Skill.LIEN_HOAN, 7, 300 },
                                        { Skill.KAMEJOKO, 7, 1000 },
                                        { Skill.MASENKO, 7, 1000 },
                                        { Skill.ANTOMIC, 7, 1000 },
                        }, // skill//skill
                        new String[] {}, // text chat 1
                        new String[] { "|-1|Oải rồi hả?", "|-1|Ê cố lên nhóc",
                                        "|-1|Chán", "|-1|Đại ca Luffy có nhầm không nhỉ",
                                        "|-1|Một mình tao chấp hết tụi bây",
                                        "|-1|HAHAHAHA", "|-1|Chỉ là bọn con nít"
                        }, // text chat 2
                        new String[] {}, // text chat 3
                        TypeAppear.APPEAR_WITH_ANOTHER // type appear
        );
        public static final BossData CHOPPER_GIANGHO = new BossData(
                        "Chopper đại ca", // name
                        ConstPlayer.XAYDA, // gender
                        new short[] { 606, 607, 608, -1, -1, -1 }, // outfit {head, body, leg, bag, aura, eff}
                        41111, // dame
                        new int[] { 160000000 }, // hp
                        new int[] { 11, 12, 31, 32 }, // map join
                        new int[][] {
                                        { Skill.LIEN_HOAN, 7, 300 },
                                        { Skill.KAMEJOKO, 7, 1000 },
                                        { Skill.MASENKO, 7, 1000 },
                                        { Skill.ANTOMIC, 7, 1000 },
                        }, // skill//skill
                        new String[] {}, // text chat 1
                        new String[] { "|-1|Oải rồi hả?", "|-1|Ê cố lên nhóc",
                                        "|-1|Chán", "|-1|Đại ca Luffy có nhầm không nhỉ",
                                        "|-1|Một mình tao chấp hết tụi bây",
                                        "|-1|HAHAHAHA", "|-1|Chỉ là bọn con nít"
                        }, // text chat 2
                        new String[] {}, // text chat 3
                        TypeAppear.APPEAR_WITH_ANOTHER // type appear
        );
        public static final BossData NAMI_GIANGHO = new BossData(
                        "Nami đại ca", // name
                        ConstPlayer.XAYDA, // gender
                        new short[] { 600, 601, 602, -1, -1, -1 }, // outfit {head, body, leg, bag, aura, eff}
                        41111, // dame
                        new int[] { 150000000 }, // hp
                        new int[] { 11, 12, 31, 32 }, // map join
                        new int[][] {
                                        { Skill.LIEN_HOAN, 7, 300 },
                                        { Skill.KAMEJOKO, 7, 1000 },
                                        { Skill.MASENKO, 7, 1000 },
                                        { Skill.ANTOMIC, 7, 1000 },
                        }, // skill//skill
                        new String[] {}, // text chat 1
                        new String[] { "|-1|Oải rồi hả?", "|-1|Ê cố lên nhóc",
                                        "|-1|Chán", "|-1|Đại ca Luffy có nhầm không nhỉ",
                                        "|-1|Một mình tao chấp hết tụi bây",
                                        "|-1|HAHAHAHA", "|-1|Chỉ là bọn con nít"
                        }, // text chat 2
                        new String[] {}, // text chat 3
                        TypeAppear.APPEAR_WITH_ANOTHER // type appear
        );
        public static final BossData FRANKY_GIANGHO = new BossData(
                        "Franky đại ca", // name
                        ConstPlayer.XAYDA, // gender
                        new short[] { 594, 595, 596, -1, -1, -1 }, // outfit {head, body, leg, bag, aura, eff}
                        41111, // dame
                        new int[] { 140000000 }, // hp
                        new int[] { 11, 12, 31, 32 }, // map join
                        new int[][] {
                                        { Skill.LIEN_HOAN, 7, 300 },
                                        { Skill.KAMEJOKO, 7, 1000 },
                                        { Skill.MASENKO, 7, 1000 },
                                        { Skill.ANTOMIC, 7, 1000 },
                        }, // skill//skill
                        new String[] {}, // text chat 1
                        new String[] { "|-1|Oải rồi hả?", "|-1|Ê cố lên nhóc",
                                        "|-1|Chán", "|-1|Đại ca Luffy có nhầm không nhỉ",
                                        "|-1|Một mình tao chấp hết tụi bây",
                                        "|-1|HAHAHAHA", "|-1|Chỉ là bọn con nít"
                        }, // text chat 2
                        new String[] {}, // text chat 3
                        TypeAppear.APPEAR_WITH_ANOTHER // type appear
        );
        public static final BossData USOP_GIANGHO = new BossData(
                        "Usop đại ca", // name
                        ConstPlayer.XAYDA, // gender
                        new short[] { 597, 598, 599, -1, -1, -1 }, // outfit {head, body, leg, bag, aura, eff}
                        41111, // dame
                        new int[] { 140000000 }, // hp
                        new int[] { 11, 12, 31, 32 }, // map join
                        new int[][] {
                                        { Skill.LIEN_HOAN, 7, 300 },
                                        { Skill.KAMEJOKO, 7, 1000 },
                                        { Skill.MASENKO, 7, 1000 },
                                        { Skill.ANTOMIC, 7, 1000 },
                        }, // skill//skill
                        new String[] {}, // text chat 1
                        new String[] { "|-1|Oải rồi hả?", "|-1|Ê cố lên nhóc",
                                        "|-1|Chán", "|-1|Đại ca Luffy có nhầm không nhỉ",
                                        "|-1|Một mình tao chấp hết tụi bây",
                                        "|-1|HAHAHAHA", "|-1|Chỉ là bọn con nít"
                        }, // text chat 2
                        new String[] {}, // text chat 3
                        TypeAppear.APPEAR_WITH_ANOTHER // type appear
        );
        public static final BossData ROBIN_GIANGHO = new BossData(
                        "Robin đại ca", // name
                        ConstPlayer.XAYDA, // gender
                        new short[] { 603, 604, 605, -1, -1, -1 }, // outfit {head, body, leg, bag, aura, eff}
                        41111, // dame
                        new int[] { 140000000 }, // hp
                        new int[] { 11, 12, 31, 32 }, // map join
                        new int[][] {
                                        { Skill.LIEN_HOAN, 7, 300 },
                                        { Skill.KAMEJOKO, 7, 1000 },
                                        { Skill.MASENKO, 7, 1000 },
                                        { Skill.ANTOMIC, 7, 1000 },
                        }, // skill//skill
                        new String[] {}, // text chat 1
                        new String[] { "|-1|Oải rồi hả?", "|-1|Ê cố lên nhóc",
                                        "|-1|Chán", "|-1|Đại ca Luffy có nhầm không nhỉ",
                                        "|-1|Một mình tao chấp hết tụi bây",
                                        "|-1|HAHAHAHA", "|-1|Chỉ là bọn con nít"
                        }, // text chat 2
                        new String[] {}, // text chat 3
                        REST_10_M);
        public static final BossData NGOKHONG_THAN = new BossData(
                        "Tề thiên đại thánh",
                        ConstPlayer.XAYDA,
                        new short[] { 462, 463, 464, -1, -1, -1 }, // outfit {head, body, leg, bag, aura, eff}
                        200000,
                        new int[] { 1200000000 },
                        new int[] { 1, 2, 3, 4, 5, 8, 9, 11, 12, 15, 16, 17, 18 },
                        new int[][] {
                                        { Skill.LIEN_HOAN, 7, 300 },
                                        { Skill.DICH_CHUYEN_TUC_THOI, 7, 30000 },
                                        { Skill.KAMEJOKO, 7, 500 },
                                        { Skill.DICH_CHUYEN_TUC_THOI, 3, 60000 },
                                        { Skill.THOI_MIEN, 7, 22000 },
                                        { Skill.THAI_DUONG_HA_SAN, 7, 24000 },
                                        { Skill.KHIEN_NANG_LUONG, 7, 60000 },
                                        { Skill.QUA_CAU_KENH_KHI, 7, 10000 },
                        },
                        new String[] {}, // text chat 1
                        new String[] { "|-1|Thấy ảo chưa nè!" }, // text chat 2
                        new String[] { "|-1|Nhớ mặt tao đấy",
                                        "|-1|Tobe continue.." }, // text chat 3
                        REST_3_H);
        public static final BossData MABU_THAN = new BossData(
                        "Mabư",
                        ConstPlayer.XAYDA,
                        new short[] { 903, 904, 905, -1, -1, -1 },
                        250000,
                        new int[] {1500000000},
                        new int[] { 223 },
                        new int[][] {
                                        { Skill.LIEN_HOAN, 7, 300 },
                                        { Skill.DICH_CHUYEN_TUC_THOI, 7, 30000 },
                                        { Skill.KAMEJOKO, 7, 500 },
                                        { Skill.DICH_CHUYEN_TUC_THOI, 3, 60000 },
                                        { Skill.THOI_MIEN, 7, 22000 },
                                        { Skill.THAI_DUONG_HA_SAN, 7, 24000 },
                                        { Skill.TAI_TAO_NANG_LUONG, 7, 2400 },
                                        { Skill.KHIEN_NANG_LUONG, 7, 60000 },
                                        { Skill.QUA_CAU_KENH_KHI, 7, 10000 },
                        },
                        new String[] {}, // text chat 1
                        new String[] { "|-1|Thấy ảo chưa nè!" }, // text chat 2
                        new String[] { "|-1|Nhớ mặt tao đấy",
                                        "|-1|Tobe continue.." }, // text chat 3
                        REST_3_H);
        public static final BossData BERUS_THAN = new BossData(
                        "Thần Hủy Diệt Berrus", // name
                        ConstPlayer.TRAI_DAT, // gender
                        new short[] { 508, 509, 510, -1, -1, -1 }, // outfit {head, body, leg, bag, aura, eff}
                        300000, // dame
                        new int[] { 1600000000 }, // hp
                        new int[] { 1, 2, 3, 4, 5, 8, 9, 11, 12, 15, 16, 17, 18 }, // map join
                        new int[][] {
                                        { Skill.LIEN_HOAN, 7, 300 },
                                        { Skill.DICH_CHUYEN_TUC_THOI, 7, 30000 },
                                        { Skill.KAMEJOKO, 7, 500 },
                                        { Skill.DICH_CHUYEN_TUC_THOI, 3, 60000 },
                                        { Skill.THOI_MIEN, 7, 22000 },
                                        { Skill.THAI_DUONG_HA_SAN, 7, 24000 },
                                        { Skill.KHIEN_NANG_LUONG, 7, 60000 },
                                        { Skill.QUA_CAU_KENH_KHI, 7, 10000 },
                        }, // skill//skill
                        new String[] {}, // text chat 1
                        new String[] { "|-1|Các ngươi thật là yếu ớt",
                                        "|-1|Ta sẽ phá hủy hành tinh này",
                                        "|-1|Chán quá!",
                                        "|-1|Ta vẫn chưa dùng hết sức đâu!",
                                        "|-2|Hắn ta không cần phòng thủ luôn!", }, // text chat 2
                        new String[] { "|-1|Ta buồn ngủ quá!" }, // text chat 3
                        REST_3_H);
        public static final BossData ADMIN_THAN = new BossData(
                        "Admin đẹp trai", // name
                        ConstPlayer.TRAI_DAT, // gender
                        new short[] { 2030, 2031, 2032, -1, 13, -1 }, // outfit {head, body, leg, bag, aura, eff}
                        350000, // dame
                        new int[] { 1800000000 }, // hp
                        new int[] { 1, 2, 3, 4, 5, 8, 9, 11, 12, 15, 16, 17, 18 }, // map join
                        new int[][] {
                                        { Skill.LIEN_HOAN, 7, 300 },
                                        { Skill.DICH_CHUYEN_TUC_THOI, 7, 30000 },
                                        { Skill.KAMEJOKO, 7, 500 },
                                        { Skill.DICH_CHUYEN_TUC_THOI, 3, 60000 },
                                        { Skill.THOI_MIEN, 7, 22000 },
                                        { Skill.THAI_DUONG_HA_SAN, 7, 24000 },
                                        { Skill.KHIEN_NANG_LUONG, 7, 60000 },
                                        { Skill.QUA_CAU_KENH_KHI, 7, 10000 },
                        },

                        // skill
                        new String[] {}, // text chat 1
                        new String[] { "|-1|Yếu thế cu",
                                        "|-1|Nạp lần đầu đi nhé",
                                        "|-1|Thiên thượng thiên hạ",
                                        "|-1|Duy ngã độc tôn",
                                        "|-1|Tuổi gì đòi 1vs1?", "|-2|Anh bảo chú này, nạp lần đầu đi!",
                                        "|-1|Gọi cả bang đến đây anh tiếp"
                        }, // text chat 2
                        new String[] {}, // text chat 3
                        REST_3_H);

        public static final BossData GRANOLA = new BossData(
                        "Granola",
                        ConstPlayer.XAYDA,
                        new short[] { 2018, 2019, 2020, -1, -1, -1 },
                        200000,
                        new int[] { 2000000000 },
                        new int[] { 177,178 },
                        new int[][] {
                                        { Skill.LIEN_HOAN, 7, 300 },
                                        { Skill.DICH_CHUYEN_TUC_THOI, 7, 30000 },
                                        { Skill.KAMEJOKO, 7, 500 },
                                        { Skill.DICH_CHUYEN_TUC_THOI, 2, 60000 },
                                        { Skill.THOI_MIEN, 7, 22000 },
                                        { Skill.THAI_DUONG_HA_SAN, 7, 24000 },
                                        { Skill.SOCOLA, 7, 4000 },
                                        { Skill.KHIEN_NANG_LUONG, 7, 60000 },
                                        { Skill.QUA_CAU_KENH_KHI, 3, 30000 },
                        },
                        new String[] {}, // text chat 1
                        new String[] { "|-1|Bọn xayda khốn khiếp!",
                                        "|-1|Tất cả là do bọn xayda", }, // text chat 2
                        new String[] { "|-1|Nhớ mặt tao đấy",
                                        "|-1|Tobe continue.." }, // text chat 3
                        REST_2_H);
        //// boss nữ
        public static final BossData CHICHI = new BossData(
                        "Thất Thất thỏ ngọc",
                        ConstPlayer.XAYDA,
                        new short[] { 1098, 1099, 1100, -1, -1, -1 },
                        60000,
                        new int[] { 100 },
                        new int[] { 0, 1, 2, 3, 4, 5, 7, 8, 9, 11, 12, 13, 14, 15, 16, 17, 18, 20, 42, 43, 44 },
                        new int[][] {
                                        { Skill.LIEN_HOAN, 7, 300 },
                                        { Skill.KAMEJOKO, 7, 500 },

                                        { Skill.THAI_DUONG_HA_SAN, 7, 24000 },
                                        { Skill.KHIEN_NANG_LUONG, 7, 60000 },
                        },
                        new String[] {}, // text chat 1
                        new String[] { "|-1|Hế lô mấy cưng!",
                        }, // text chat 2
                        new String[] { "|-1|Yametekudasai",
                        }, // text chat 3
                        REST_30_M);

        public static final BossData BULMA = new BossData(
                        "Bún mắm thỏ ngọc",
                        ConstPlayer.XAYDA,
                        new short[] { 409, 410, 411, -1, -1, -1 },
                        60000,
                        new int[] { 100 },
                        new int[] { 0, 1, 2, 3, 4, 5, 7, 8, 9, 11, 12, 13, 14, 15, 16, 17, 18, 20, 42, 43, 44 },
                        new int[][] {
                                        { Skill.LIEN_HOAN, 7, 300 },
                                        { Skill.KAMEJOKO, 7, 500 },

                                        { Skill.THAI_DUONG_HA_SAN, 7, 24000 },
                                        { Skill.KHIEN_NANG_LUONG, 7, 60000 },
                        },
                        new String[] {}, // text chat 1
                        new String[] { "|-1|Hế lô mấy cưng!",
                        }, // text chat 2
                        new String[] { "|-1|Yametekudasai",
                        }, // text chat 3
                        REST_30_M);

        public static final BossData LYTIEUNUONG = new BossData(
                        "Lý thỏ ngọc",
                        ConstPlayer.XAYDA,
                        new short[] { 1095, 1096, 1097, -1, -1, -1 },
                        60000,
                        new int[] { 100 },
                        new int[] { 0, 1, 2, 3, 4, 5, 7, 8, 9, 11, 12, 13, 14, 15, 16, 17, 18, 20, 42, 43, 44 },
                        new int[][] {
                                        { Skill.LIEN_HOAN, 7, 300 },

                                        { Skill.KAMEJOKO, 7, 500 },

                                        { Skill.THAI_DUONG_HA_SAN, 7, 24000 },
                                        { Skill.KHIEN_NANG_LUONG, 7, 60000 },
                        },
                        new String[] {}, // text chat 1
                        new String[] { "|-1|Hế lô mấy cưng!",
                        }, // text chat 2
                        new String[] { "|-1|Yametekudasai",
                        }, // text chat 3
                        REST_30_M);

        public static final BossData SO18 = new BossData(
                        "Thập bát thỏ ngọc",
                        ConstPlayer.XAYDA,
                        new short[] { 1101, 1102, 1103, -1, -1, -1 },
                        60000,
                        new int[] { 100 },
                        new int[] { 42, 43, 44, 0, 1, 2, 3, 4, 5, 7, 8, 9, 11, 12, 13, 14, 15, 16, 17, 18, 20 },
                        new int[][] {
                                        { Skill.LIEN_HOAN, 7, 300 },

                                        { Skill.KAMEJOKO, 7, 500 },

                                        { Skill.THAI_DUONG_HA_SAN, 7, 24000 },
                                        { Skill.KHIEN_NANG_LUONG, 7, 60000 },
                        },
                        new String[] {}, // text chat 1
                        new String[] { "|-1|Hế lô mấy cưng!",
                        }, // text chat 2
                        new String[] { "|-1|Yametekudasai",
                        }, // text chat 3
                        REST_30_M);
        public static final BossData THODAICA = new BossData(
                        "Thỏ đại ca",
                        ConstPlayer.XAYDA,
                        new short[] { 403, 404, 405, -1, -1, -1 },
                        90000,
                        new int[] { 150 },
                        new int[] { 42, 43, 44, 0, 1, 2, 3, 4, 5, 7, 8, 9, 11, 12, 13, 14, 15, 16, 17, 18, 20 },
                        new int[][] {
                                        { Skill.LIEN_HOAN, 7, 300 },

                                        { Skill.KAMEJOKO, 7, 500 },

                                        { Skill.THAI_DUONG_HA_SAN, 7, 24000 },
                                        { Skill.KHIEN_NANG_LUONG, 7, 60000 },
                        },
                        new String[] {}, // text chat 1
                        new String[] { "|-1|Hế lô mấy cưng!",
                        }, // text chat 2
                        new String[] { "|-1|Yametekudasai",
                        }, // text chat 3
                        REST_30_M);
        /// vo dai hat mit
        // giang ho
        public static final BossData DRACULA = new BossData(
                        "Dracula", // name
                        ConstPlayer.TRAI_DAT, // gender
                        new short[] { 353, 354, 355, -1, -1, -1 }, // outfit {head, body, leg, bag, aura, eff}
                        42000, // dame
                        new int[] { 150000000 }, // hp
                        new int[] { 0, 1, 2, 3, 4, 7, 8, 11, 12, 14, 15, 15, 17, 18, 19, 27, 28, 32, 32, 35, 36 }, // map
                                                                                                                   // join
                        new int[][] {
                                        { Skill.DRAGON, 1, 100 }, { Skill.DRAGON, 2, 200 }, { Skill.DRAGON, 3, 300 },
                                        { Skill.DRAGON, 7, 700 },
                                        { Skill.KAMEJOKO, 7, 100 }, { Skill.KAMEJOKO, 2, 1200 },
                                        { Skill.KAMEJOKO, 5, 1500 }, { Skill.KAMEJOKO, 7, 1700 },
                                        { Skill.GALICK, 1, 100 } }, // skill
                        new String[] {}, // text chat 1
                        new String[] {}, // text chat 2
                        new String[] { "|-1|Cay quá!",
                                        "|-1|Ta mà lại thua được sao?",
                                        "|-1|Hãy trả thù cho ta!" }, // text chat 3
                        REST_10_M);

        public static final BossData NGUOIVOHINH = new BossData(
                        "Vô hình", // name
                        ConstPlayer.TRAI_DAT, // gender
                        new short[] { 377, 378, 379, -1, -1, -1 }, // outfit {head, body, leg, bag, aura, eff}
                        44000, // dame
                        new int[] { 160000000 }, // hp
                        new int[] { 0, 1, 2, 3, 4, 7, 8, 11, 12, 14, 15, 15, 17, 18, 19, 27, 28, 32, 32, 35, 36 }, // map
                                                                                                                   // join
                        new int[][] {
                                        { Skill.DRAGON, 1, 100 }, { Skill.DRAGON, 2, 200 }, { Skill.DRAGON, 3, 300 },
                                        { Skill.DRAGON, 7, 700 },
                                        { Skill.KAMEJOKO, 7, 100 }, { Skill.KAMEJOKO, 2, 1200 },
                                        { Skill.KAMEJOKO, 5, 1500 }, { Skill.KAMEJOKO, 7, 1700 },
                                        { Skill.GALICK, 1, 100 }
                        }, // skill
                        new String[] {}, // text chat 1
                        new String[] {}, // text chat 2
                        new String[] { "|-1|Cay quá!",
                                        "|-1|Ta mà lại thua được sao?",
                                        "|-1|Hãy trả thù cho ta!"
                        }, // text chat 3
                        TypeAppear.APPEAR_WITH_ANOTHER // type appear
        );
        public static final BossData BONGBANG = new BossData(
                        "Bông băng", // name
                        ConstPlayer.TRAI_DAT, // gender
                        new short[] { 350, 351, 352, -1, -1, -1 }, // outfit {head, body, leg, bag, aura, eff}
                        44000, // dame
                        new int[] { 170000000 }, // hp
                        new int[] { 0, 1, 2, 3, 4, 7, 8, 11, 12, 14, 15, 15, 17, 18, 19, 27, 28, 32, 32, 35, 36 }, // map
                                                                                                                   // join
                        new int[][] {
                                        { Skill.DRAGON, 1, 100 }, { Skill.DRAGON, 2, 200 }, { Skill.DRAGON, 3, 300 },
                                        { Skill.DRAGON, 7, 700 },
                                        { Skill.KAMEJOKO, 7, 100 }, { Skill.KAMEJOKO, 2, 1200 },
                                        { Skill.KAMEJOKO, 5, 1500 }, { Skill.KAMEJOKO, 7, 1700 },
                                        { Skill.GALICK, 1, 100 }
                        }, // skill
                        new String[] {}, // text chat 1
                        new String[] {}, // text chat 2
                        new String[] { "|-1|Cay quá!",
                                        "|-1|Ta mà lại thua được sao?",
                                        "|-1|Hãy trả thù cho ta!"
                        }, // text chat 3
                        TypeAppear.APPEAR_WITH_ANOTHER // type appear
        );
        public static final BossData SATAN = new BossData(
                        "Sa tăng", // name
                        ConstPlayer.TRAI_DAT, // gender
                        new short[] { 344, 345, 346, -1, -1, -1 }, // outfit {head, body, leg, bag, aura, eff}
                        44000, // dame
                        new int[] { 180000000 }, // hp
                        new int[] { 0, 1, 2, 3, 4, 7, 8, 11, 12, 14, 15, 15, 17, 18, 19, 27, 28, 32, 32, 35, 36 }, // map
                                                                                                                   // join
                        new int[][] {
                                        { Skill.DRAGON, 1, 100 }, { Skill.DRAGON, 2, 200 }, { Skill.DRAGON, 3, 300 },
                                        { Skill.DRAGON, 7, 700 },
                                        { Skill.KAMEJOKO, 7, 100 }, { Skill.KAMEJOKO, 2, 1200 },
                                        { Skill.KAMEJOKO, 5, 1500 }, { Skill.KAMEJOKO, 7, 1700 },
                                        { Skill.GALICK, 1, 100 }
                        }, // skill
                        new String[] {}, // text chat 1
                        new String[] {}, // text chat 2
                        new String[] { "|-1|Cay quá!",
                                        "|-1|Ta mà lại thua được sao?",
                                        "|-1|Hãy trả thù cho ta!"
                        }, // text chat 3
                        TypeAppear.APPEAR_WITH_ANOTHER // type appear
        );
        public static final BossData THODAUBAC = new BossData(
                        "Gô han", // name
                        ConstPlayer.TRAI_DAT, // gender
                        new short[] { 347, 348, 349, -1, -1, -1 }, // outfit {head, body, leg, bag, aura, eff}
                        60000, // dame
                        new int[] { 200000000 }, // hp
                        new int[] { 0, 1, 2, 3, 4, 7, 8, 11, 12, 14, 15, 15, 17, 18, 19, 27, 28, 32, 32, 35, 36 }, // map
                                                                                                                   // join
                        new int[][] {
                                        { Skill.DRAGON, 1, 100 }, { Skill.DRAGON, 2, 200 }, { Skill.DRAGON, 3, 300 },
                                        { Skill.DRAGON, 7, 700 },
                                        { Skill.KAMEJOKO, 7, 100 }, { Skill.KAMEJOKO, 2, 1200 },
                                        { Skill.KAMEJOKO, 5, 1500 }, { Skill.KAMEJOKO, 7, 1700 },
                                        { Skill.GALICK, 1, 100 }
                        }, // skill
                        new String[] {}, // text chat 1
                        new String[] {}, // text chat 2
                        new String[] { "|-1|Cay quá!",
                                        "|-1|Ta mà lại thua được sao?",
                                        "|-1|Hãy trả thù cho ta!"
                        }, // text chat 3
                        TypeAppear.APPEAR_WITH_ANOTHER // type appear
        );

        //////
        public static final BossData GOKU_FAKE = new BossData(
                        "Gôku", // name
                        ConstPlayer.TRAI_DAT, // gender
                        new short[] { 0, 523, 524, -1, -1, -1 }, // outfit {head, body, leg, bag, aura, eff}
                        150000, // dame
                        new int[] { 800000000 }, // hp
                        new int[] { 131, 132, 133 }, // map join
                        new int[][] {
                                        { Skill.KAMEJOKO, 7, 1000 },
                                        { Skill.QUA_CAU_KENH_KHI, 3, 30000 },
                                        { Skill.LIEN_HOAN, 7, 300 },
                                        { Skill.DICH_CHUYEN_TUC_THOI, 2, 60000 },
                                        { Skill.DICH_CHUYEN_TUC_THOI, 7, 30000 },
                                        { Skill.THAI_DUONG_HA_SAN, 7, 30000 },
                        }, // skill
                        new String[] {}, // text chat 1
                        new String[] {
                                        "|-1|Ngươi nghĩ có thể đánh bại được sao?",
                                        "|-1|Ta sẽ trồng cây sức mạnh tại đây" }, // text chat 2
                        new String[] { "|-1|Cay quá!",
                                        "|-1|Ta mà lại thua được sao?",
                        }, // text chat 3
                        REST_30_M // type appear
        );
        //////
        public static final BossData POCOLO_FAKE = new BossData(
                        "Calic", // name
                        ConstPlayer.NAMEC, // gender
                        new short[] { 102, 523, 524, -1, -1, -1 }, // outfit {head, body, leg, bag, aura, eff}
                        150000, // dame
                        new int[] { 800000000 }, // hp
                        new int[] { 131, 132, 133 }, // map join
                        new int[][] {
                                        { Skill.KAMEJOKO, 7, 1000 },
                                        { Skill.QUA_CAU_KENH_KHI, 3, 30000 },
                                        { Skill.LIEN_HOAN, 7, 300 },
                                        { Skill.DICH_CHUYEN_TUC_THOI, 2, 60000 },
                                        { Skill.DICH_CHUYEN_TUC_THOI, 7, 30000 },
                                        { Skill.THAI_DUONG_HA_SAN, 7, 30000 },
                        }, // skill
                        new String[] {}, // text chat 1
                        new String[] {
                                        "|-1|Ngươi nghĩ có thể đánh bại được sao?",
                                        "|-1|Ta sẽ trở lại tuổi thanh xuân và thống trị hành tinh này" }, // text chat 2
                        new String[] { "|-1|Cay quá!",
                                        "|-1|Ta mà lại thua được sao?",
                        }, // text chat 3
                        REST_30_M // type appear
        );
        //////
        public static final BossData CADIC_FAKE = new BossData(
                        "Vegeta", // name
                        ConstPlayer.XAYDA, // gender
                        new short[] { 103, 523, 524, -1, -1, -1 }, // outfit {head, body, leg, bag, aura, eff}
                        150000, // dame
                        new int[] { 800000000 }, // hp
                        new int[] { 131, 132, 133 }, // map join
                        new int[][] {
                                        { Skill.KAMEJOKO, 7, 1000 },
                                        { Skill.QUA_CAU_KENH_KHI, 3, 30000 },
                                        { Skill.LIEN_HOAN, 7, 300 },
                                        { Skill.DICH_CHUYEN_TUC_THOI, 2, 60000 },
                                        { Skill.DICH_CHUYEN_TUC_THOI, 7, 30000 },
                                        { Skill.THAI_DUONG_HA_SAN, 7, 30000 },
                        }, // skill
                        new String[] {}, // text chat 1
                        new String[] {
                                        "|-1|Ngươi nghĩ có thể đánh bại được sao?",
                                        "|-1|Ta sẽ hủy diệt hành tinh này" }, // text chat 2
                        new String[] { "|-1|Cay quá!",
                                        "|-1|Ta mà lại thua được sao?",
                        }, // text chat 3
                        REST_30_M // type appear
        );
        public static final BossData PILAP = new BossData(
                        "Pi láp",
                        ConstPlayer.XAYDA,
                        new short[] { 612, 613, 614, -1, -1, -1 },
                        70000,
                        new int[] { 250000000 },
                        new int[] { 141, 142, 143 },
                        // new int[]{14},
                        new int[][] {

                                        { Skill.DRAGON, 7, 300 },
                                        { Skill.DICH_CHUYEN_TUC_THOI, 7, 20000 },
                        }, // skill//skill
                        new String[] {}, // text chat 1
                        new String[] {}, // text chat 2
                        new String[] {}, // text chat 3
                        REST_10_M, // second rest
                        new int[] { BossID.MAI, BossID.SU } // boss join map together
        );

        public static final BossData MAI = new BossData(
                        "Mai", // name
                        ConstPlayer.XAYDA, // gender
                        new short[] { 615, 616, 617, -1, -1, -1 }, // outfit {head, body, leg, bag, aura, eff}
                        71111, // dame
                        new int[] { 250000000 }, // hp
                        new int[] { 141, 142, 143 }, // map join
                        new int[][] {
                                        { Skill.LIEN_HOAN, 7, 300 },
                                        { Skill.MASENKO, 7, 1000 },
                                        { Skill.ANTOMIC, 7, 10000 },
                        }, // skill//skill
                        new String[] {}, // text chat 1
                        new String[] { "|-1|Oải rồi hả?", "|-1|Ê cố lên nhóc",
                                        "|-1|Chán", "|-1|Đại ca Doraemon có nhầm không nhỉ",
                                        "|-1|Một mình tao chấp hết tụi bây",
                                        "|-1|HAHAHAHA", "|-1|Chỉ là bọn con nít"
                        }, // text chat 2
                        new String[] {}, // text chat 3
                        TypeAppear.APPEAR_WITH_ANOTHER // type appear
        );

        public static final BossData SU = new BossData(
                        "Su", // name
                        ConstPlayer.XAYDA, // gender
                        new short[] { 618, 619, 620, -1, -1, -1 }, // outfit {head, body, leg, bag, aura, eff}
                        71111, // dame
                        new int[] { 250000000 }, // hp
                        new int[] { 141, 142, 143 }, // map join
                        new int[][] {
                                        { Skill.LIEN_HOAN, 7, 300 },
                                        { Skill.MASENKO, 7, 1000 },
                                        { Skill.ANTOMIC, 7, 10000 },
                        }, // skill//skill
                        new String[] {}, // text chat 1
                        new String[] { "|-1|Oải rồi hả?", "|-1|Ê cố lên nhóc",
                                        "|-1|Chán", "|-1|Đại ca Doraemon có nhầm không nhỉ",
                                        "|-1|Một mình tao chấp hết tụi bây",
                                        "|-1|HAHAHAHA", "|-1|Chỉ là bọn con nít"
                        }, // text chat 2
                        new String[] {}, // text chat 3
                        TypeAppear.APPEAR_WITH_ANOTHER // type appear
        );

        // chill
        public static final BossData CHILL_1 = new BossData(
                        "Chill 1", // name
                        ConstPlayer.XAYDA, // gender
                        new short[] { 1024, 1025, 1026, -1, -1, -1 }, // outfit {head, body, leg, bag, aura, eff}
                        220000, // dame
                        new int[] { 1000000000 }, // hp
                        new int[] { 160, 161, 162, 163 }, // map join
                        new int[][] {
                                        { Skill.KAMEJOKO, 7, 1000 },
                                        { Skill.QUA_CAU_KENH_KHI, 3, 30000 },
                                        { Skill.LIEN_HOAN, 7, 300 },
                                        { Skill.DICH_CHUYEN_TUC_THOI, 2, 60000 },
                                        { Skill.DICH_CHUYEN_TUC_THOI, 7, 30000 },
                                        { Skill.THAI_DUONG_HA_SAN, 7, 30000 },
                        }, // skill
                        new String[] {}, // text chat 1
                        new String[] { "|-1|Oải rồi hả?", "|-1|Ê cố lên nhóc",
                                        "|-1|Chán", "|-1|Đại ca Doraemon có nhầm không nhỉ",
                                        "|-1|Một mình tao chấp hết tụi bây",
                                        "|-1|HAHAHAHA", "|-1|Chỉ là bọn con nít"
                        }, // text chat 2
                        new String[] {}, // text chat 3
                        REST_15_M);

        public static final BossData CHILL_2 = new BossData(
                        "Chill 2", // name
                        ConstPlayer.XAYDA, // gender
                        new short[] { 1021, 1022, 1023, -1, -1, -1 }, // outfit {head, body, leg, bag, aura, eff}
                        260000, // dame
                        new int[] { 1500000000 }, // hp
                        new int[] { 160, 161, 162, 163 }, // map join
                        new int[][] {
                                        { Skill.KAMEJOKO, 7, 1000 },
                                        { Skill.QUA_CAU_KENH_KHI, 3, 30000 },
                                        { Skill.LIEN_HOAN, 7, 300 },
                                        { Skill.DICH_CHUYEN_TUC_THOI, 2, 60000 },
                                        { Skill.DICH_CHUYEN_TUC_THOI, 7, 30000 },
                                        { Skill.THAI_DUONG_HA_SAN, 7, 30000 },
                        }, // skill
                        new String[] {}, // text chat 1
                        new String[] { "|-1|Oải rồi hả?", "|-1|Ê cố lên nhóc",
                                        "|-1|Chán", "|-1|Đại ca Doraemon có nhầm không nhỉ",
                                        "|-1|Một mình tao chấp hết tụi bây",
                                        "|-1|HAHAHAHA", "|-1|Chỉ là bọn con nít"
                        }, // text chat 2
                        new String[] {}, // text chat 3
                        TypeAppear.APPEAR_WITH_ANOTHER // type appear
        );

        public static final BossData CAULIFA_1 = new BossData(
                        "Caulifa", // name
                        ConstPlayer.XAYDA, // gender
                        new short[] { 690, 691, 692, -1, -1, -1 }, // outfit {head, body, leg, bag, aura, eff}
                        200000, // dame
                        new int[] { 800000000 }, // hp
                        new int[] { 164, 165, 166, 167, 168 }, // map join
                        new int[][] {
                                        { Skill.THOI_MIEN, 7, 100000 },
                                        { Skill.DICH_CHUYEN_TUC_THOI, 7, 30000 },
                                        { Skill.KAMEJOKO, 7, 300 },
                                        { Skill.LIEN_HOAN, 4, 200 },
                                        { Skill.DICH_CHUYEN_TUC_THOI, 2, 60000 },
                                        { Skill.MASENKO, 7, 10000 } }, // skill//skill
                        new String[] {}, // text chat 1
                        new String[] { "|-1|Oải rồi hả?", "|-1|Ê cố lên nhóc",
                                        "|-1|Chán", "|-1|Bọn vũ trụ 7 các người yếu quá",
                                        "|-1|Một mình tao chấp hết tụi bây",
                                        "|-1|HAHAHAHA", "|-1|Chỉ là bọn con nít"
                        }, // text chat 2
                        new String[] {}, // text chat 3
                        REST_20_M);

        public static final BossData SUPER_CAULIFA = new BossData(
                        "Super Caulifa", // name
                        ConstPlayer.XAYDA, // gender
                        new short[] { 757, 691, 692, -1, -1, -1 }, // outfit {head, body, leg, bag, aura, eff}
                        300000, // dame
                        new int[] { 1400000000 }, // hp
                        new int[] { 164, 165, 166, 167, 168 }, // map join
                        new int[][] {
                                        { Skill.THOI_MIEN, 7, 100000 },
                                        { Skill.DICH_CHUYEN_TUC_THOI, 7, 30000 },
                                        { Skill.KAMEJOKO, 7, 1000 },
                                        { Skill.LIEN_HOAN, 7, 300 },

                                        { Skill.MASENKO, 7, 10000 } }, // skill//skill
                        new String[] {}, // text chat 1
                        new String[] { "|-1|Oải rồi hả?", "|-1|Ê cố lên nhóc",
                                        "|-1|Chán", "|-1|Mày làm chị phải nghiêm túc rồi",
                                        "|-1|Một mình tao chấp hết tụi bây",
                                        "|-1|HAHAHAHA", "|-1|Chỉ là bọn con nít"
                        }, // text chat 2
                        new String[] {}, // text chat 3
                        TypeAppear.APPEAR_WITH_ANOTHER // type appear
        );

        //
        public static final BossData THAN_VU_TRU = new BossData(
                        "Thần vũ trụ", // name
                        ConstPlayer.XAYDA, // gender
                        new short[] { 98, 99, 100, -1, -1, -1 }, // outfit {head, body, leg, bag, aura, eff}
                        200000, // dame
                        new int[] { 1000000000 }, // hp
                        new int[] { 156, 157, 158, 159 }, // map join
                        new int[][] {
                                        { Skill.THOI_MIEN, 7, 100000 },
                                        { Skill.DICH_CHUYEN_TUC_THOI, 7, 30000 },
                                        { Skill.KAMEJOKO, 7, 1000 },
                                        { Skill.LIEN_HOAN, 4, 300 },
                                        { Skill.DICH_CHUYEN_TUC_THOI, 2, 60000 },
                                        { Skill.MASENKO, 7, 10000 } }, // skill//skill
                        new String[] {}, // text chat 1
                        new String[] { "|-1|Ta sẽ dạy con chiêu Kaioken"
                        }, // text chat 2
                        new String[] {}, // text chat 3
                        REST_15_M);
        public static final BossData THAN_MEO = new BossData(
                        "Thần mèo", // name
                        ConstPlayer.XAYDA, // gender
                        new short[] { 89, 90, 91, -1, -1, -1 }, // outfit {head, body, leg, bag, aura, eff}
                        200000, // dame
                        new int[] { 1000000000 }, // hp
                        new int[] { 156, 157, 158, 159 }, // map join
                        new int[][] {
                                        { Skill.THOI_MIEN, 7, 100000 },
                                        { Skill.DICH_CHUYEN_TUC_THOI, 7, 30000 },
                                        { Skill.KAMEJOKO, 7, 1000 },
                                        { Skill.LIEN_HOAN, 7, 300 },
                                        { Skill.DICH_CHUYEN_TUC_THOI, 2, 60000 },
                                        { Skill.MASENKO, 7, 10000 } }, // skill//skill
                        new String[] {}, // text chat 1
                        new String[] { "|-1|Con cần phải tiến bộ hơn"
                        }, // text chat 2
                        new String[] {}, // text chat 3
                        REST_15_M);

        public static final BossData THUONG_DE = new BossData(
                        "Thượng đế", // name
                        ConstPlayer.XAYDA, // gender
                        new short[] { 86, 87, 88, -1, -1, -1 }, // outfit {head, body, leg, bag, aura, eff}
                        200000, // dame
                        new int[] { 1000000000 }, // hp
                        new int[] { 156, 157, 158, 159 }, // map join
                        new int[][] {
                                        { Skill.THOI_MIEN, 7, 100000 },
                                        { Skill.DICH_CHUYEN_TUC_THOI, 7, 30000 },
                                        { Skill.KAMEJOKO, 7, 1000 },
                                        { Skill.LIEN_HOAN, 4, 300 },
                                        { Skill.DICH_CHUYEN_TUC_THOI, 2, 60000 },
                                        { Skill.MASENKO, 7, 10000 } }, // skill//skill
                        new String[] {}, // text chat 1
                        new String[] { "|-1|Hãy làm chủ sức mạnh của mình"
                        }, // text chat 2
                        new String[] {}, // text chat 3
                        REST_15_M);

        ///
        public static final BossData BASIL = new BossData(
                        "Sói Basil", // name
                        ConstPlayer.XAYDA, // gender
                        new short[] { 745, 746, 747, -1, -1, -1 }, // outfit {head, body, leg, bag, aura, eff}
                        200000, // dame
                        new int[] { 600000000 }, // hp
                        new int[] { 164, 165, 166, 167, 168 }, // map join
                        new int[][] {
                                        { Skill.THOI_MIEN, 7, 100000 },
                                        { Skill.DICH_CHUYEN_TUC_THOI, 7, 30000 },
                                        { Skill.KAMEJOKO, 7, 1000 },
                                        { Skill.LIEN_HOAN, 4, 300 },
                                        { Skill.DICH_CHUYEN_TUC_THOI, 2, 60000 },
                                        { Skill.MASENKO, 7, 10000 } }, // skill//skill
                        new String[] {}, // text chat 1
                        new String[] { "|-1|Hãy làm chủ sức mạnh của mình"
                        }, // text chat 2
                        new String[] {}, // text chat 3
                        REST_20_M);
        ///
        public static final BossData LAVENDE = new BossData(
                        "Sói lavende", // name
                        ConstPlayer.XAYDA, // gender
                        new short[] { 748, 749, 750, -1, -1, -1 }, // outfit {head, body, leg, bag, aura, eff}
                        200000, // dame
                        new int[] { 600000000 }, // hp
                        new int[] { 164, 165, 166, 167, 168 }, // map join
                        new int[][] {
                                        { Skill.THOI_MIEN, 7, 100000 },
                                        { Skill.DICH_CHUYEN_TUC_THOI, 7, 30000 },
                                        { Skill.KAMEJOKO, 7, 1000 },
                                        { Skill.LIEN_HOAN, 4, 300 },
                                        { Skill.DICH_CHUYEN_TUC_THOI, 2, 60000 },
                                        { Skill.MASENKO, 7, 10000 } }, // skill//skill
                        new String[] {}, // text chat 1
                        new String[] { "|-1|Hãy làm chủ sức mạnh của mình"
                        }, // text chat 2
                        new String[] {}, // text chat 3
                        TypeAppear.APPEAR_WITH_ANOTHER // type appear
        );
        ///
        public static final BossData BERGAMO = new BossData(
                        "Sói Bergamo", // name
                        ConstPlayer.XAYDA, // gender
                        new short[] { 751, 752, 753, -1, -1, -1 }, // outfit {head, body, leg, bag, aura, eff}
                        200000, // dame
                        new int[] { 600000000 }, // hp
                        new int[] { 164, 165, 166, 167, 168 }, // map join
                        new int[][] {
                                        { Skill.THOI_MIEN, 7, 100000 },
                                        { Skill.DICH_CHUYEN_TUC_THOI, 7, 30000 },
                                        { Skill.KAMEJOKO, 7, 1000 },
                                        { Skill.LIEN_HOAN, 4, 300 },
                                        { Skill.DICH_CHUYEN_TUC_THOI, 2, 60000 },
                                        { Skill.MASENKO, 7, 10000 } }, // skill//skill
                        new String[] {}, // text chat 1
                        new String[] { "|-1|Hãy làm chủ sức mạnh của mình"
                        }, // text chat 2
                        new String[] {}, // text chat 3
                        TypeAppear.APPEAR_WITH_ANOTHER // type appear
        );

        ///
        public static final BossData ANDROID_21 = new BossData(
                        "Số 21", // name
                        ConstPlayer.XAYDA, // gender
                        new short[] { 687, 688, 689, -1, -1, -1 }, // outfit {head, body, leg, bag, aura, eff}
                        200000, // dame
                        new int[] { 1500000000 }, // hp
                        new int[] { 111 }, // map join
                        new int[][] {
                                        { Skill.THOI_MIEN, 7, 100000 },
                                        { Skill.DICH_CHUYEN_TUC_THOI, 7, 30000 },
                                        { Skill.KAMEJOKO, 7, 1000 },
                                        { Skill.LIEN_HOAN, 7, 300 },
                                        { Skill.DICH_CHUYEN_TUC_THOI, 2, 60000 },
                                        { Skill.MASENKO, 7, 10000 } }, // skill//skill
                        new String[] {}, // text chat 1
                        new String[] { "|-1|Nứng quá"
                        }, // text chat 2
                        new String[] {}, // text chat 3
                        REST_30_M // type appear
        );
        ///
        public static final BossData GOKU_BLUE = new BossData(
                        "Super Blue", // name
                        ConstPlayer.XAYDA, // gender
                        new short[] { 542, 523, 524, -1, -1, -1 }, // outfit {head, body, leg, bag, aura, eff}
                        200000, // dame
                        new int[] { 1000000000 }, // hp
                        new int[] { 139, 140 }, // map join
                        new int[][] {
                                        { Skill.THOI_MIEN, 7, 100000 },
                                        { Skill.DICH_CHUYEN_TUC_THOI, 7, 30000 },
                                        { Skill.KAMEJOKO, 7, 500 },
                                        { Skill.DICH_CHUYEN_TUC_THOI, 2, 60000 },
                                        { Skill.DRAGON, 7, 300 },
                        }, // skill//skill
                        new String[] {}, // text chat 1
                        new String[] { "|-1|Siêu phản động thần thánh"
                        }, // text chat 2
                        new String[] {}, // text chat 3
                        REST_30_M // type appear
        );
        public static final BossData CADIC_BLUE = new BossData(
                        "Super Blue", // name
                        ConstPlayer.XAYDA, // gender
                        new short[] { 538, 523, 524, -1, -1, -1 }, // outfit {head, body, leg, bag, aura, eff}
                        200000, // dame
                        new int[] { 1000000000 }, // hp
                        new int[] { 139, 140 }, // map join
                        new int[][] {
                                        { Skill.THOI_MIEN, 7, 100000 },
                                        { Skill.DICH_CHUYEN_TUC_THOI, 7, 30000 },
                                        { Skill.DICH_CHUYEN_TUC_THOI, 2, 60000 },
                                        { Skill.ANTOMIC, 7, 500 },
                                        { Skill.GALICK, 7, 300 },
                        }, // skill//skill
                        new String[] {}, // text chat 1
                        new String[] { "|-1|Siêu phản động thần thánh"
                        }, // text chat 2
                        new String[] {}, // text chat 3
                        REST_30_M // type appear
        );
        ///
        public static final BossData ZENO_VANG = new BossData(
                        "Super Zeno", // name
                        ConstPlayer.XAYDA, // gender
                        new short[] { 2060, 2061, 2062, -1, -1, -1 }, // outfit {head, body, leg, bag, aura, eff}
                        200000, // dame
                        new int[] { 1200000000 }, // hp
                        new int[] { 174, 175, 176 }, // map join
                        new int[][] {
                                        { Skill.THOI_MIEN, 7, 100000 },
                                        { Skill.DICH_CHUYEN_TUC_THOI, 7, 30000 },
                                        { Skill.DICH_CHUYEN_TUC_THOI, 2, 60000 },
                                        { Skill.ANTOMIC, 7, 400 },
                                        { Skill.GALICK, 7, 300 },
                        }, // skill//skill
                        new String[] {}, // text chat 1
                        new String[] { "|-1|Quỳ xuống dưới chân ta"
                        }, // text chat 2
                        new String[] {}, // text chat 3
                        REST_15_M // type appear
        );
        public static final BossData ZENO_XANH = new BossData(
                        "Rage Zeno", // name
                        ConstPlayer.XAYDA, // gender
                        new short[] { 2051, 2052, 2053, -1, -1, -1 }, // outfit {head, body, leg, bag, aura, eff}
                        250000, // dame
                        new int[] { 1400000000 }, // hp
                        new int[] { 174, 175, 176 }, // map join
                        new int[][] {
                                        { Skill.THOI_MIEN, 7, 100000 },
                                        { Skill.DICH_CHUYEN_TUC_THOI, 7, 30000 },
                                        { Skill.DICH_CHUYEN_TUC_THOI, 2, 60000 },
                                        { Skill.ANTOMIC, 7, 400 },
                                        { Skill.GALICK, 7, 300 },
                        }, // skill//skill
                        new String[] {}, // text chat 1
                        new String[] { "|-1|Quỳ xuống dưới chân ta"
                        }, // text chat 2
                        new String[] {}, // text chat 3
                        TypeAppear.APPEAR_WITH_ANOTHER // type appear
        );
        public static final BossData ZENO_TIM = new BossData(
                        "Mystic Zeno", // name
                        ConstPlayer.XAYDA, // gender
                        new short[] { 1213, 1214, 1215, -1, -1, -1 }, // outfit {head, body, leg, bag, aura, eff}
                        300000, // dame
                        new int[] { 1800000000 }, // hp
                        new int[] { 174, 175, 176 }, // map join
                        new int[][] {
                                        { Skill.THOI_MIEN, 7, 100000 },
                                        { Skill.DICH_CHUYEN_TUC_THOI, 7, 30000 },
                                        { Skill.DICH_CHUYEN_TUC_THOI, 2, 60000 },
                                        { Skill.ANTOMIC, 7, 400 },
                                        { Skill.GALICK, 7, 300 },
                        }, // skill//skill
                        new String[] {}, // text chat 1
                        new String[] { "|-1|Quỳ xuống dưới chân ta"
                        }, // text chat 2
                        new String[] {}, // text chat 3
                        TypeAppear.APPEAR_WITH_ANOTHER // type appear
        );
        public static final BossData UUB = new BossData(
                        "Uub", // name
                        ConstPlayer.XAYDA, // gender
                        new short[] { 946, 947, 948, -1, -1, -1 }, // outfit {head, body, leg, bag, aura, eff}
                        1000, // dame
                        new int[] { 1800000000 }, // hp
                        new int[] { 49 }, // map join
                        new int[][] {
                                        { Skill.KAMEJOKO, 7, 4000 },
                                        { Skill.DICH_CHUYEN_TUC_THOI, 2, 60000 },
                                        { Skill.LIEN_HOAN, 7, 300 },
                        },
                        new String[] {}, // text chat 1
                        new String[] { "|-1|Xin hãy luyện tập với con!"
                        }, // text chat 2
                        new String[] { "|-1|Mạnh quá!" }, // text chat 3
                        REST_1_M // type appear
        );
        public static final BossData BROLY_BASE_1 = new BossData(
                        "Broly", // name
                        ConstPlayer.XAYDA, // gender
                        new short[] { 1080, 1081, 1082, -1, -1, -1 }, // outfit {head, body, leg, bag, aura, eff}
                        200000, // dame
                        new int[] { 1000000000 }, // hp
                        new int[] { 170, 171, 172 }, // map join
                        new int[][] {
                                        { Skill.KAMEJOKO, 7, 4000 },
                                        { Skill.LIEN_HOAN, 7, 300 },
                                        { Skill.DICH_CHUYEN_TUC_THOI, 2, 10000 }, }, // skill//skill
                        new String[] {}, // text chat 1
                        new String[] { "|-1|Ngươi làm ta nổi giận rồi!"
                        }, // text chat 2
                        new String[] { "|-1|Argggg!" }, // text chat 3
                        REST_1_H // type appear
        );
        public static final BossData BROLY_BASE_2 = new BossData(
                        "Super Broly", // name
                        ConstPlayer.XAYDA, // gender
                        new short[] { 1083, 1084, 1085, -1, -1, -1 }, // outfit {head, body, leg, bag, aura, eff}
                        250000, // dame
                        new int[] { 1200000000 }, // hp
                        new int[] { 170, 171, 172 }, // map join
                        new int[][] {
                                        { Skill.KAMEJOKO, 7, 4000 },
                                        { Skill.LIEN_HOAN, 7, 300 },
                                        { Skill.DICH_CHUYEN_TUC_THOI, 2, 10000 },
                        }, // skill//skill
                        new String[] {}, // text chat 1
                        new String[] { "|-1|Ngươi làm ta nổi giận rồi!"
                        }, // text chat 2
                        new String[] { "|-1|Argggg!" }, // text chat 3
                        TypeAppear.APPEAR_WITH_ANOTHER);
        public static final BossData BROLY_BASE_3 = new BossData(
                        "Rage Broly", // name
                        ConstPlayer.XAYDA, // gender
                        new short[] { 1086, 1087, 1088, -1, -1, -1 }, // outfit {head, body, leg, bag, aura, eff}
                        300000, // dame
                        new int[] { 1400000000 }, // hp
                        new int[] { 170, 171, 172 }, // map join
                        new int[][] {
                                        { Skill.KAMEJOKO, 7, 4000 },
                                        { Skill.DICH_CHUYEN_TUC_THOI, 2, 60000 },
                                        { Skill.LIEN_HOAN, 7, 300 },
                        }, // skill//skill
                        new String[] {}, // text chat 1
                        new String[] { "|-1|Ngươi làm ta nổi giận rồi!"
                        }, // text chat 2
                        new String[] { "|-1|Hự!" }, // text chat 3
                        TypeAppear.APPEAR_WITH_ANOTHER);
        //
        public static final BossData VOI_9_NGA = new BossData(
                        "Voi 9 ngà", // name
                        ConstPlayer.XAYDA, // gender
                        new short[] { 1252, 1253, 1254, -1, 2, -1 }, // outfit {head, body, leg, bag, aura, eff}
                        50000, // dame
                        new int[] { 1000000000 }, // hp
                        new int[] { 42 }, // map join
                        new int[][] {
                                        { Skill.KAMEJOKO, 7, 4000 },
                                        { Skill.DICH_CHUYEN_TUC_THOI, 2, 60000 },
                                        { Skill.LIEN_HOAN, 7, 300 },
                        }, // skill//skill
                        new String[] {}, // text chat 1
                        new String[] { "|-1|Ngươi làm ta nổi giận rồi!"
                        }, // text chat 2
                        new String[] { "|-1|Hự!" }, // text chat 3
                        REST_15_M);
        public static final BossData GA_9_CUA = new BossData(
                        "Gà 9 cựa", // name
                        ConstPlayer.XAYDA, // gender
                        new short[] { 1246, 1247, 1248, -1, 7, -1 }, // outfit {head, body, leg, bag, aura, eff}
                        50000, // dame
                        new int[] { 1000000000 }, // hp
                        new int[] { 43 }, // map join
                        new int[][] {
                                        { Skill.KAMEJOKO, 7, 4000 },
                                        { Skill.DICH_CHUYEN_TUC_THOI, 2, 60000 },
                                        { Skill.LIEN_HOAN, 7, 300 },
                        }, // skill//skill
                        new String[] {}, // text chat 1
                        new String[] { "|-1|Ngươi làm ta nổi giận rồi!"
                        }, // text chat 2
                        new String[] { "|-1|Hự!" }, // text chat 3
                        REST_15_M);
        public static final BossData NGUA_9_LMAO = new BossData(
                        "Ngựa 9 hồng mao", // name
                        ConstPlayer.XAYDA, // gender
                        new short[] { 1249, 1250, 1251, -1, 6, -1 }, // outfit {head, body, leg, bag, aura, eff}
                        50000, // dame
                        new int[] { 1000000000 }, // hp
                        new int[] { 44 }, // map join
                        new int[][] {
                                        { Skill.KAMEJOKO, 7, 4000 },
                                        { Skill.DICH_CHUYEN_TUC_THOI, 2, 60000 },
                                        { Skill.LIEN_HOAN, 7, 300 },
                        }, // skill//skill
                        new String[] {}, // text chat 1
                        new String[] { "|-1|Ngươi làm ta nổi giận rồi!"
                        }, // text chat 2
                        new String[] { "|-1|Hự!" }, // text chat 3
                        REST_15_M);

        public static final BossData TAU_PAY_PAY_ROBOT = new BossData(
                        "Tàu pảy pảy người máy", // name
                        ConstPlayer.XAYDA, // gender
                        new short[] { 338, 339, 340, -1, 6, -1 }, // outfit {head, body, leg, bag, aura, eff}
                        100000, // dame
                        new int[] { 1000000000 }, // hp
                        new int[] { 189 }, // map join
                        new int[][] {
                                        { Skill.KAMEJOKO, 7, 4000 },
                                        { Skill.LIEN_HOAN, 7, 300 },
                        }, // skill//skill
                        new String[] {}, // text chat 1
                        new String[] { "|-1|Ngươi làm ta nổi giận rồi!"
                        }, // text chat 2
                        new String[] { "|-1|Hự!" }, // text chat 3
                        REST_30_S);
        public static final BossData TAU_PAY_PAY_KARIN = new BossData(
                        "Tàu pảy pảy", // name
                        ConstPlayer.XAYDA, // gender
                        new short[] { 92, 93, 94, -1, 6, -1 }, // outfit {head, body, leg, bag, aura, eff}
                        20000, // dame
                        new int[] { 100000000 }, // hp
                        new int[] { 47 }, // map join
                        new int[][] {
                                        { Skill.KAMEJOKO, 7, 4000 },
                                        { Skill.LIEN_HOAN, 7, 300 },
                        }, // skill//skill
                        new String[] {}, // text chat 1
                        new String[] { "|-1|Ngươi làm ta nổi giận rồi!"
                        }, // text chat 2
                        new String[] { "|-1|Hự!" }, // text chat 3
                        REST_5_M);
        public static final BossData THAN_MEO_KARIN = new BossData(
                        "Thần mèo", // name
                        ConstPlayer.XAYDA, // gender
                        new short[] { 89, 90, 91, -1, 6, -1 }, // outfit {head, body, leg, bag, aura, eff}
                        40000, // dame
                        new int[] { 200000000 }, // hp
                        new int[] { 46 }, // map join
                        new int[][] {
                                        { Skill.KAMEJOKO, 7, 4000 },
                                        { Skill.LIEN_HOAN, 7, 300 },
                        }, // skill//skill
                        new String[] {}, // text chat 1
                        new String[] { "|-1|Ngươi làm ta nổi giận rồi!"
                        }, // text chat 2
                        new String[] { "|-1|Hự!" }, // text chat 3
                        REST_5_M);
          public static final BossData YAJIROBE_KARIN = new BossData(
                        "Yajirobe", // name
                        ConstPlayer.XAYDA, // gender
                        new short[] { 77, 78, 79, -1, 6, -1 }, // outfit {head, body, leg, bag, aura, eff}
                        40000, // dame
                        new int[] { 200000000 }, // hp
                        new int[] { 46 }, // map join
                        new int[][] {
                                        { Skill.KAMEJOKO, 7, 4000 },
                                        { Skill.LIEN_HOAN, 7, 300 },
                        }, // skill//skill
                        new String[] {}, // text chat 1
                        new String[] { "|-1|Ngươi làm ta nổi giận rồi!"
                        }, // text chat 2
                        new String[] { "|-1|Hự!" }, // text chat 3
                        REST_5_M);
        public static final BossData THUONG_DE_KARIN = new BossData(
                        "Thượng đế", // name
                        ConstPlayer.XAYDA, // gender
                        new short[] { 86, 87, 88, -1, 6, -1 }, // outfit {head, body, leg, bag, aura, eff}
                        60000, // dame
                        new int[] { 300000000 }, // hp
                        new int[] { 45 }, // map join
                        new int[][] {
                                        { Skill.KAMEJOKO, 7, 4000 },
                                        { Skill.LIEN_HOAN, 7, 300 },
                        }, // skill//skill
                        new String[] {}, // text chat 1
                        new String[] { "|-1|Ngươi làm ta nổi giận rồi!"
                        }, // text chat 2
                        new String[] { "|-1|Hự!" }, // text chat 3
                        REST_5_M);

        public static final BossData THAN_VU_TRU_KARIN = new BossData(
                        "Thần vũ trự", // name
                        ConstPlayer.XAYDA, // gender
                        new short[] { 98, 99, 100, -1, 6, -1 }, // outfit {head, body, leg, bag, aura, eff}
                        80000, // dame
                        new int[] { 400000000 }, // hp
                        new int[] { 48 }, // map join
                        new int[][] {
                                        { Skill.KAMEJOKO, 7, 4000 },
                                        { Skill.LIEN_HOAN, 7, 300 },
                        }, // skill//skill
                        new String[] {}, // text chat 1
                        new String[] { "|-1|Ngươi làm ta nổi giận rồi!"
                        }, // text chat 2
                        new String[] { "|-1|Hự!" }, // text chat 3
                        REST_5_M);
        public static final BossData TO_SU_KARIN = new BossData(
                        "Tổ sư kaio", // name
                        ConstPlayer.XAYDA, // gender
                        new short[] { 448, 449, 450, -1, 6, -1 }, // outfit {head, body, leg, bag, aura, eff}
                        100000, // dame
                        new int[] { 700000000 }, // hp
                        new int[] { 50 }, // map join
                        new int[][] {
                                        { Skill.KAMEJOKO, 7, 4000 },
                                        { Skill.LIEN_HOAN, 7, 300 },
                        }, // skill//skill
                        new String[] {}, // text chat 1
                        new String[] { "|-1|Ngươi làm ta nổi giận rồi!"
                        }, // text chat 2
                        new String[] { "|-1|Hự!" }, // text chat 3
                        REST_5_M);
        public static final BossData THAN_HUY_DIET_KARIN = new BossData(
                        "Thần hủy diệt", // name
                        ConstPlayer.XAYDA, // gender
                        new short[] { 508, 509, 510, -1, 6, -1 }, // outfit {head, body, leg, bag, aura, eff}
                        160000, // dame
                        new int[] { 1500000000 }, // hp
                        new int[] { 154 }, // map join
                        new int[][] {
                                        { Skill.KAMEJOKO, 7, 4000 },
                                        { Skill.LIEN_HOAN, 7, 300 },
                        }, // skill//skill
                        new String[] {}, // text chat 1
                        new String[] { "|-1|Ngươi làm ta nổi giận rồi!"
                        }, // text chat 2
                        new String[] { "|-1|Hự!" }, // text chat 3
                        REST_5_M);

        // ho tong
        public static final BossData KI_LAN_HO_TONG = new BossData(
                        "Kì lân",
                        (byte) 0,
                        new short[] { 763, 764, 765, -1, -1, -1 },
                        100000,
                        new int[] { 100 },
                        new int[] { 6 },
                        new int[][] {
                                        { Skill.DICH_CHUYEN_TUC_THOI, 1, 60000 }
                        },
                        new String[] {}, // text chat 1
                        new String[] { "|-1|Chậm thôi",
                                        "|-1|Cứu ta ",
                                        "|-1|Ui da đau quá",
                                        "|-1|Nhanh thế? ",
                                        "|-1|Lề mề quá, đứng gần ta thôi"
                        }, // text chat 2
                        new String[] {}, // text chat 3
                        60);

        public static final BossData DUONG_TANG_HO_TONG = new BossData(
                        "Đường Tank",
                        (byte) 0,
                        new short[] { 467, 468, 469, -1, -1, -1 },
                        100000,
                        new int[] { 100 },
                        new int[] { 0, 103 },
                        new int[][] {
                                        { Skill.DICH_CHUYEN_TUC_THOI, 7, 15000 } },
                        new String[] {}, // text chat 1
                        new String[] { "|-1|Chậm thôi",
                                        "|-1|Cứu ta ",
                                        "|-1|Ui da đau quá",
                                        "|-1|Nhanh thế? ",
                                        "|-1|Lề mề quá, đứng gần ta thôi"
                        }, // text chat 2
                        new String[] {}, // text chat 3
                        60);
        public static final BossData MI_NUONG_HO_TONG = new BossData(
                        "Mị nương",
                        (byte) 0,
                        new short[] { 841, 842, 843, -1, -1, -1 },
                        100000,
                        new int[] { 100 },
                        new int[] { 179 },
                        new int[][] {
                                        { Skill.DICH_CHUYEN_TUC_THOI, 7, 15000 } },
                        new String[] {}, // text chat 1
                        new String[] { "|-1|Chậm thôi",
                                        "|-1|Cứu ta ",
                                        "|-1|Ui da đau quá",
                                        "|-1|Nhanh thế? ",
                                        "|-1|Lề mề quá, đứng gần ta thôi"
                        }, // text chat 2
                        new String[] {}, // text chat 3
                        60);
        public static final BossData TRAI_DAT = new BossData(
                        "Trái đất", // name
                        ConstPlayer.TRAI_DAT, // gender
                        new short[] { 569, 472, 473, -1, 6, -1 }, // outfit {head, body, leg, bag, aura, eff}
                        100000, // dame
                        new int[] { 100000000 }, // hp
                        new int[] { 190 }, // map join
                        new int[][] { { Skill.DRAGON, 7, 400 },
                                        { Skill.KAMEJOKO, 7, 5000 },
                                        { Skill.DICH_CHUYEN_TUC_THOI, 7, 30000 },
                        }, // skill//skill
                        new String[] {}, // text chat 1
                        new String[] { "|-1|Ngươi làm ta nổi giận rồi!"
                        }, // text chat 2
                        new String[] { "|-1|Hự!" }, // text chat 3
                        REST_5_M);
        // NAMEC new short[] { 536, 476, 477, -1, -1, -1 },
        public static final BossData NAMEC = new BossData(
                        "Na mếc", // name
                        ConstPlayer.NAMEC, // gender
                        new short[] { 536, 476, 477, -1, 6, -1 }, // outfit {head, body, leg, bag, aura, eff}
                        100000, // dame
                        new int[] { 100000000 }, // hp
                        new int[] { 191 }, // map join
                        new int[][] { { Skill.LIEN_HOAN, 7, 300 },
                                        { Skill.MASENKO, 7, 500 },
                                        { Skill.SOCOLA, 7, 30000 },
                        }, // skill//skill
                        new String[] {}, // text chat 1
                        new String[] { "|-1|Ngươi làm ta nổi giận rồi!"
                        }, // text chat 2
                        new String[] { "|-1|Hự!" }, // text chat 3
                        REST_5_M);
        // XAYDA new short[] { 538, 474, 475, -1, -1, -1 },
        public static final BossData XAYDA = new BossData(
                        "Xay da", // name
                        ConstPlayer.XAYDA, // gender
                        new short[] { 538, 474, 475, -1, 6, -1 }, // outfit {head, body, leg, bag, aura, eff}
                        100000, // dame
                        new int[] { 100000000 }, // hp
                        new int[] { 192 }, // map join
                        new int[][] { { Skill.GALICK, 7, 300 },
                                        { Skill.ANTOMIC, 7, 2000 },
                                        { Skill.DICH_CHUYEN_TUC_THOI, 7, 30000 },
                        }, // skill//skill
                        new String[] {}, // text chat 1
                        new String[] { "|-1|Ngươi làm ta nổi giận rồi!"
                        }, // text chat 2
                        new String[] { "|-1|Hự!" }, // text chat 3
                        REST_5_M);
        public static final BossData LUCYONFIRE = new BossData(
                        "Lucy", // name
                        ConstPlayer.XAYDA, // gender
                        new short[] { 1056, 1057, 1058, -1, -1, -1 }, // outfit {head, body, leg, bag, aura, eff}
                        200000, // dame
                        new int[] { 200 }, // hp
                        new int[] { 177 }, // map join
                        new int[][] { { Skill.LIEN_HOAN, 7, 300 },
                                        { Skill.KAMEJOKO, 7, 2000 },
                                        { Skill.DICH_CHUYEN_TUC_THOI, 7, 20000 },
                        }, // skill//skill
                        new String[] {}, // text chat 1
                        new String[] { "|7|Hãy về phe của ta!"
                        }, // text chat 2
                        new String[] { "|-1|Hự!" }, // text chat 3
                        REST_15_M);

        public static final BossData KABUTO = new BossData(
                        "Kabuto", // name
                        ConstPlayer.XAYDA, // gender
                        new short[] { 1053, 1054, 1055, -1, -1, -1 }, // outfit {head, body, leg, bag, aura, eff}
                        200000, // dame
                        new int[] { 200 }, // hp
                        new int[] { 178 }, // map join
                        new int[][] { { Skill.LIEN_HOAN, 7, 300 },
                                        { Skill.KAMEJOKO, 7, 2000 },
                                        { Skill.DICH_CHUYEN_TUC_THOI, 7, 20000 },
                        }, // skill//skill
                        new String[] {}, // text chat 1
                        new String[] { "|7|Hãy về phe của ta!"
                        }, // text chat 2
                        new String[] { "|-1|Hự!" }, // text chat 3
                        REST_15_M);

        // dia nguc
        public static final BossData FIDE_DIA_NGUC = new BossData(
                        "Fide", // name
                        ConstPlayer.XAYDA, // gender
                        new short[] { 189, 190, 191, 45, -1, -1 }, // outfit {head, body, leg, bag, aura, eff}
                        200000, // dame
                        new int[] { 1200000000 }, // hp
                        new int[] { 197, 197, 199 }, // map join
                        new int[][] { { Skill.LIEN_HOAN, 7, 300 },
                                        { Skill.ANTOMIC, 7, 2000 },
                                        { Skill.DICH_CHUYEN_TUC_THOI, 7, 20000 },
                        },
                        new String[] {}, // text chat 1
                        new String[] { "|-1|Ta nói các ngươi rồi! Sức mạnh này của ta còn đáng sợ hơn địa ngục!!",
                                        "|-1|Ta chơi thêm chút nữa chắc ngươi chóng mặt buồn nôn mất!!",
                                        "|-2|Ăn gì mà khỏe thế!",
                                        "|-2|Chết đi Fide!!!!",
                                        "|-1|Hô hô hô hô",
                                        "|-1|Chán thật! Khí của ngươi sắp hết rồi. Để ta tiễn ngươi về địa ngục!",
                                        "|-1|Ngươi quá tự cao rồi đấy,xem ta đây!", }, // text chat 2
                        new String[] { "|-1|Lũ khốn..",
                                        "|-1|..Một ngày nào đó ta sẽ quay lại và trả thù các ngươi",
                                        "|-1|Nhớ mặt tao đấy !", }, // text chat 3
                        REST_15_M // type appear
        );
        // dia nguc
        public static final BossData XEN_DIA_NGUC = new BossData(
                        "Xên bọ hung", // name
                        ConstPlayer.XAYDA, // gender
                        new short[] { 234, 235, 236, 45, -1, -1 }, // outfit {head, body, leg, bag, aura, eff}
                        200000, // dame
                        new int[] { 1500000000 }, // hp
                        new int[] { 197, 197, 199 }, // map join
                        new int[][] { { Skill.LIEN_HOAN, 7, 300 },
                                        { Skill.KAMEJOKO, 7, 4000 },
                                        { Skill.DICH_CHUYEN_TUC_THOI, 7, 20000 },
                        },
                        new String[] {}, // text chat 1
                        new String[] { "|-1|Ta nói các ngươi rồi! Sức mạnh này của ta còn đáng sợ hơn địa ngục!!",
                                        "|-1|Ta chơi thêm chút nữa chắc ngươi chóng mặt buồn nôn mất!!",
                                        "|-2|Ăn gì mà khỏe thế!",
                                        "|-2|Chết đi Xên!!!!",
                                        "|-1|Hô hô hô hô",
                                        "|-1|Chán thật! Khí của ngươi sắp hết rồi. Để ta tiễn ngươi về địa ngục!",
                                        "|-1|Ngươi quá tự cao rồi đấy,xem ta đây!", }, // text chat 2
                        new String[] { "|-1|Lũ khốn..",
                                        "|-1|..Một ngày nào đó ta sẽ quay lại và trả thù các ngươi",
                                        "|-1|Nhớ mặt tao đấy !", }, // text chat 3
                        REST_15_M // type appear
        );
        // Khi dot
        public static final BossData KHI_BAC = new BossData(
                        "Khỉ Bạc", // name
                        ConstPlayer.XAYDA, // gender
                        new short[] { 198, 193, 194, -1, -1, -1 }, // outfit {head, body, leg, bag, aura, eff}
                        100000, // dame
                        new int[] { 1000000000 }, // hp
                        new int[] { 20, 37, 38 }, // map join
                        new int[][] { { Skill.LIEN_HOAN, 7, 300 },
                                        { Skill.KAMEJOKO, 7, 4000 },
                                        { Skill.DICH_CHUYEN_TUC_THOI, 7, 20000 },
                        },
                        new String[] {}, // text chat 1
                        new String[] { "|-1|Ta nói các ngươi rồi! Sức mạnh này của ta còn đáng sợ hơn địa ngục!!",
                                        "|-1|Ta chơi thêm chút nữa chắc ngươi chóng mặt buồn nôn mất!!",
                                        "|-2|Ăn gì mà khỏe thế!",
                                        "|-1|Hô hô hô hô",
                                        "|-1|Chán thật! Khí của ngươi sắp hết rồi. Để ta tiễn ngươi về địa ngục!",
                                        "|-1|Ngươi quá tự cao rồi đấy,xem ta đây!", }, // text chat 2
                        new String[] { "|-1|Lũ khốn..",
                                        "|-1|..Một ngày nào đó ta sẽ quay lại và trả thù các ngươi",
                                        "|-1|Nhớ mặt tao đấy !", }, // text chat 3
                        REST_15_M // type appear
        );
        public static final BossData KHI_VANG = new BossData(
                        "Khỉ Vàng", // name
                        ConstPlayer.XAYDA, // gender
                        new short[] { 197, 193, 194, -1, -1, -1 }, // outfit {head, body, leg, bag, aura, eff}
                        100000, // dame
                        new int[] { 1000000000 }, // hp
                        new int[] { 20, 37, 38 }, // map join
                        new int[][] { { Skill.LIEN_HOAN, 7, 300 },
                                        { Skill.KAMEJOKO, 7, 4000 },
                                        { Skill.DICH_CHUYEN_TUC_THOI, 7, 20000 },
                        },
                        new String[] {}, // text chat 1
                        new String[] { "|-1|Ta nói các ngươi rồi! Sức mạnh này của ta còn đáng sợ hơn địa ngục!!",
                                        "|-1|Ta chơi thêm chút nữa chắc ngươi chóng mặt buồn nôn mất!!",
                                        "|-2|Ăn gì mà khỏe thế!",
                                        "|-1|Hô hô hô hô",
                                        "|-1|Chán thật! Khí của ngươi sắp hết rồi. Để ta tiễn ngươi về địa ngục!",
                                        "|-1|Ngươi quá tự cao rồi đấy,xem ta đây!", }, // text chat 2
                        new String[] { "|-1|Lũ khốn..",
                                        "|-1|..Một ngày nào đó ta sẽ quay lại và trả thù các ngươi",
                                        "|-1|Nhớ mặt tao đấy !", }, // text chat 3
                        REST_15_M // type appear
        );
        public static final BossData KHI_DEN = new BossData(
                        "Khỉ Đen", // name
                        ConstPlayer.XAYDA, // gender
                        new short[] { 192, 193, 194, -1, -1, -1 }, // outfit {head, body, leg, bag, aura, eff}
                        100000, // dame
                        new int[] { 1000000000 }, // hp
                        new int[] { 20, 37, 38 }, // map join
                        new int[][] { { Skill.LIEN_HOAN, 7, 300 },
                                        { Skill.KAMEJOKO, 7, 4000 },
                                        { Skill.DICH_CHUYEN_TUC_THOI, 7, 20000 },
                        },
                        new String[] {}, // text chat 1
                        new String[] { "|-1|Ta nói các ngươi rồi! Sức mạnh này của ta còn đáng sợ hơn địa ngục!!",
                                        "|-1|Ta chơi thêm chút nữa chắc ngươi chóng mặt buồn nôn mất!!",
                                        "|-2|Ăn gì mà khỏe thế!",
                                        "|-1|Hô hô hô hô",
                                        "|-1|Chán thật! Khí của ngươi sắp hết rồi. Để ta tiễn ngươi về địa ngục!",
                                        "|-1|Ngươi quá tự cao rồi đấy,xem ta đây!", }, // text chat 2
                        new String[] { "|-1|Lũ khốn..",
                                        "|-1|..Một ngày nào đó ta sẽ quay lại và trả thù các ngươi",
                                        "|-1|Nhớ mặt tao đấy !", }, // text chat 3
                        REST_15_M // type appear
        );
        // NHAT NGUYET
        public static final BossData NHAT_THAN = new BossData(
                        "Nhật thần", // name
                        ConstPlayer.XAYDA, // gender
                        new short[] { 1298, 1299, 1300, -1, -1, -1 }, // outfit {head, body, leg, bag, aura, eff}
                        100000, // dame
                        new int[] { 1500000000 }, // hp
                        new int[] { 3, 4, 17, 18, 11, 12 }, // map join
                        new int[][] { { Skill.LIEN_HOAN, 7, 300 },
                                        { Skill.KAMEJOKO, 7, 4000 },
                                        { Skill.DICH_CHUYEN_TUC_THOI, 7, 20000 },
                        },
                        new String[] {}, // text chat 1
                        new String[] { "|-1|Ta nói các ngươi rồi! Sức mạnh này của ta còn đáng sợ hơn địa ngục!!",
                                        "|-1|Ta chơi thêm chút nữa chắc ngươi chóng mặt buồn nôn mất!!",
                                        "|-2|Ăn gì mà khỏe thế!",
                                        "|-1|Hô hô hô hô",
                                        "|-1|Chán thật! Khí của ngươi sắp hết rồi. Để ta tiễn ngươi về địa ngục!",
                                        "|-1|Ngươi quá tự cao rồi đấy,xem ta đây!", }, // text chat 2
                        new String[] { "|-1|Lũ khốn..",
                                        "|-1|..Một ngày nào đó ta sẽ quay lại và trả thù các ngươi",
                                        "|-1|Nhớ mặt tao đấy !", }, // text chat 3
                        REST_30_M, // type appear,
                        new int[] { BossID.NGUYET_THAN }

        );
        public static final BossData NGUYET_THAN = new BossData(
                        "Nguyệt thần", // name
                        ConstPlayer.XAYDA, // gender
                        new short[] { 1290, 1291, 1292, -1, -1, -1 }, // outfit {head, body, leg, bag, aura, eff}
                        100000, // dame
                        new int[] { 1900000000 }, // hp
                        new int[] { 3, 4, 17, 18, 11, 12 }, // map join
                        new int[][] { { Skill.LIEN_HOAN, 7, 300 },
                                        { Skill.KAMEJOKO, 7, 4000 },
                                        { Skill.DICH_CHUYEN_TUC_THOI, 7, 20000 },
                        },
                        new String[] {}, // text chat 1
                        new String[] { "|-1|Ta nói các ngươi rồi! Sức mạnh này của ta còn đáng sợ hơn địa ngục!!",
                                        "|-1|Ta chơi thêm chút nữa chắc ngươi chóng mặt buồn nôn mất!!",
                                        "|-2|Ăn gì mà khỏe thế!",
                                        "|-1|Hô hô hô hô",
                                        "|-1|Chán thật! Khí của ngươi sắp hết rồi. Để ta tiễn ngươi về địa ngục!",
                                        "|-1|Ngươi quá tự cao rồi đấy,xem ta đây!", }, // text chat 2
                        new String[] { "|-1|Lũ khốn..",
                                        "|-1|..Một ngày nào đó ta sẽ quay lại và trả thù các ngươi",
                                        "|-1|Nhớ mặt tao đấy !", }, // text chat 3
                        TypeAppear.APPEAR_WITH_ANOTHER // type appear
        );
        //////// nro namec
        public static final BossData KUKU_NRO = new BossData(
                        "Kuku", // name
                        ConstPlayer.XAYDA, // gender
                        new short[] { 159, 160, 161, -1, -1, -1 }, // outfit {head, body, leg, bag, aura, eff}
                        200000, // dame
                        new int[] { 1200000000 }, // hp
                        new int[] { 206 }, // map join
                        new int[][] {

                                        { Skill.LIEN_HOAN, 7, 300 },
                                        { Skill.KAMEJOKO, 7, 1000 },
                                        { Skill.DICH_CHUYEN_TUC_THOI, 7, 15000 },
                        },
                        new String[] {}, // text chat 1
                        new String[] {}, // text chat 2
                        new String[] { "|-2|Đẹp trai nó phải thế" }, // text chat 3
                        REST_10_M // second rest
        );

        public static final BossData MAP_DAU_DINH_NRO = new BossData(
                        "Mập Đầu Đinh", // name
                        ConstPlayer.XAYDA, // gender
                        new short[] { 165, 166, 167, -1, -1, -1 }, // outfit {head, body, leg, bag, aura, eff}
                        220000, // dame
                        new int[] { 1300000000 }, // hp
                        new int[] { 206 }, // map join
                        new int[][] {
                                        { Skill.LIEN_HOAN, 7, 300 },
                                        { Skill.KAMEJOKO, 7, 1000 },
                                        { Skill.DICH_CHUYEN_TUC_THOI, 7, 15000 }, }, // skill //skill
                        new String[] {}, // text chat 1
                        new String[] { "|-1|Chết hết đi cho tao",
                                        "|-1|Tao sẽ giết hết bọn mày",
                                        "|-1|Hahaha",
                                        "|-1|Tao chỉ cần 10 phút để giết hết bọn mày",
                                        "|-1|Được rồi tao sẽ thổi bay hết bọn mày",
                                        "|-1|Muốn đùa thì thêm tí muối đi!",
                                        "|-2|Thằng này,tao nhịn mày lâu lắm rồi ấy nhá",
                                        "|-2|Coi thường nhau quá đấy", }, // text chat 2
                        new String[] { "|-1|Ôi bạn ơi ....ơi!!!" }, // text chat 3
                        TypeAppear.ANOTHER_LEVEL// second rest
        );

        public static final BossData RAMBO_NRO = new BossData(
                        "Rambo", // name
                        ConstPlayer.XAYDA, // gender
                        new short[] { 162, 163, 164, -1, -1, -1 }, // outfit {head, body, leg, bag, aura, eff}
                        240000, // dame
                        new int[] { 1400000000 }, // hp
                        new int[] { 206 }, // map join
                        new int[][] {
                                        { Skill.LIEN_HOAN, 7, 300 },
                                        { Skill.KAMEJOKO, 7, 1000 },
                                        { Skill.DICH_CHUYEN_TUC_THOI, 7, 15000 }, }, // skill //skill
                        new String[] {}, // text chat 1
                        new String[] { "|-1|Hahaha",
                                        "|-1|Ngạc nhiên thật, đúng là mày đã tiến bộ rất nhanh..",
                                        "|-1|Tao sẽ cho mày biết lý do tại sao tao lại không dùng đến năng lực thực sự..",
                                        "|-1|Đến tao còn không thắng nổi thì đừng mộng tưởng đối đầu với đại ca Fide!",
                                        "|-1|Ha ha ha! Ngươi tưởng chạy trốn được sao?",
                                        "|-2|Oái..!",
                                        "|-2|Đừng tưởng thế này là xong..! Tao sẽ còn mạnh hơn nữa!", }, // text chat 2
                        new String[] { "|-1|Ôi bạn ơi..." }, // text chat 3
                        TypeAppear.ANOTHER_LEVEL // second rest
        );

        // **************************************************************************
        // Boss tiểu đội sát thủ
        public static final BossData SO_4_NRO = new BossData(
                        "Số 4 Guldo", // name
                        ConstPlayer.XAYDA, // gender
                        new short[] { 168, 169, 170, -1, -1, -1 }, // outfit {head, body, leg, bag, aura, eff}
                        260000, // dame
                        new int[] { 1500000000 }, // hp
                        new int[] { 206 }, // map join
                        // new int[]{86}, //map join
                        new int[][] {
                                        { Skill.LIEN_HOAN, 7, 300 },
                                        { Skill.KAMEJOKO, 7, 1000 },
                                        { Skill.DICH_CHUYEN_TUC_THOI, 7, 15000 },
                        }, // skill
                        new String[] {}, // text chat 1
                        new String[] { "|-1|Oải rồi hả?", "|-1|Ê cố lên nhóc",
                                        "|-1|Chán", "|-1|Đại ca Fide có nhầm không nhỉ",
                                        "|-1|Các ngươi không nhúc nhích được sao?",
                                        "|-1|HAHAHAHA", "|-1|Chỉ là bọn con nít"
                        }, // text chat 2
                        new String[] { "|-1|Cay quá!",
                                        "|-1|Ta mà lại thua được sao?",
                                        "|-1|Hãy trả thù cho ta!"
                        }, // text chat 3
                        TypeAppear.ANOTHER_LEVEL

        );

        public static final BossData SO_3_NRO = new BossData(
                        "Số 3 Recome", // name
                        ConstPlayer.XAYDA, // gender
                        new short[] { 174, 175, 176, -1, -1, -1 }, // outfit {head, body, leg, bag, aura, eff}
                        300000, // dame
                        new int[] { 1600000000 }, // hp
                        new int[] { 206 }, // map join
                        new int[][] {
                                        { Skill.LIEN_HOAN, 7, 300 },
                                        { Skill.KAMEJOKO, 7, 1000 },
                                        { Skill.DICH_CHUYEN_TUC_THOI, 7, 15000 },
                        }, // skill
                        new String[] {}, // text chat 1
                        new String[] { "|-1|Oải rồi hả?", "|-1|Ê cố lên nhóc",
                                        "|-1|Chán", "|-1|Đại ca Fide có nhầm không nhỉ",
                                        "|-1|Một mình tao chấp hết tụi bây",
                                        "|-1|HAHAHAHA", "|-1|Chỉ là bọn con nít"
                        }, // text chat 2
                        new String[] { "|-1|Cay quá!",
                                        "|-1|Ta mà lại thua được sao?",
                                        "|-1|Hãy trả thù cho ta!"
                        }, // text chat 3
                        TypeAppear.ANOTHER_LEVEL // type appear
        );

        public static final BossData SO_2_NRO = new BossData(
                        "Số 2 Jeice", // name
                        ConstPlayer.XAYDA, // gender
                        new short[] { 171, 172, 173, -1, -1, -1 }, // outfit {head, body, leg, bag, aura, eff}
                        320000, // dame
                        new int[] { 1700000000 }, // hp
                        new int[] { 206 }, // map join
                        new int[][] {
                                        { Skill.LIEN_HOAN, 7, 300 },
                                        { Skill.KAMEJOKO, 7, 1000 },
                                        { Skill.DICH_CHUYEN_TUC_THOI, 7, 15000 },
                        }, // skill//skill
                        new String[] {}, // text chat 1
                        new String[] { "|-1|Oải rồi hả?", "|-1|Ê cố lên nhóc",
                                        "|-1|Chán", "|-1|Đại ca Fide có nhầm không nhỉ",
                                        "|-1|Một mình tao chấp hết tụi bây",
                                        "|-1|HAHAHAHA", "|-1|Chỉ là bọn con nít"
                        }, // text chat 2
                        new String[] { "|-1|Cay quá!",
                                        "|-1|Ta mà lại thua được sao?",
                                        "|-1|Hãy trả thù cho ta!"
                        }, // text chat 3
                        TypeAppear.ANOTHER_LEVEL // type appear
        );

        public static final BossData SO_1_NRO = new BossData(
                        "Số 1 Burter", // name
                        ConstPlayer.XAYDA, // gender
                        new short[] { 177, 178, 179, -1, -1, -1 }, // outfit {head, body, leg, bag, aura, eff}
                        340000, // dame
                        new int[] { 1800000000 }, // hp
                        new int[] { 206 }, // map join
                        new int[][] {
                                        { Skill.LIEN_HOAN, 7, 300 },
                                        { Skill.KAMEJOKO, 7, 1000 },
                                        { Skill.DICH_CHUYEN_TUC_THOI, 7, 15000 },
                        }, // skill//skill
                        new String[] {}, // text chat 1
                        new String[] { "|-1|Oải rồi hả?", "|-1|Ê cố lên nhóc",
                                        "|-1|Chán", "|-1|Đại ca Fide có nhầm không nhỉ",
                                        "|-1|Một mình tao chấp hết tụi bây",
                                        "|-1|HAHAHAHA", "|-1|Chỉ là bọn con nít"
                        }, // text chat 2
                        new String[] { "|-1|Cay quá!",
                                        "|-1|Ta mà lại thua được sao?",
                                        "|-1|Hãy trả thù cho ta!"
                        }, // text chat 3
                        TypeAppear.ANOTHER_LEVEL // type appear
        );

        public static final BossData TIEU_DOI_TRUONG_NRO = new BossData(
                        "Tiểu đội trưởng Ginyu", // name
                        ConstPlayer.XAYDA, // gender
                        new short[] { 180, 181, 182, -1, -1, -1 }, // outfit {head, body, leg, bag, aura, eff}
                        360000, // dame
                        new int[] { 1900000000 }, // hp
                        new int[] { 206 }, // map join
                        new int[][] {
                                        { Skill.LIEN_HOAN, 7, 300 },
                                        { Skill.KAMEJOKO, 7, 1000 },
                                        { Skill.DICH_CHUYEN_TUC_THOI, 7, 15000 },
                        }, // skill
                        new String[] {}, // text chat 1
                        new String[] { "|-1|Oải rồi hả?", "|-1|Ê cố lên nhóc",
                                        "|-1|Chán", "|-1|Đại ca Fide có nhầm không nhỉ",
                                        "|-1|Một mình tao chấp hết tụi bây",
                                        "|-1|HAHAHAHA", "|-1|Chỉ là bọn con nít"
                        }, // text chat 2
                        new String[] { "|-1|Cay quá!"
                        }, // text chat 3
                        TypeAppear.ANOTHER_LEVEL // type appear
        );

        public static final BossData PAIKUHAN = new BossData(
                        "Paikuhan", // name
                        ConstPlayer.XAYDA, // gender
                        new short[] { 1326, 1327, 1328, 45, -1, -1 }, // outfit {head, body, leg, bag, aura, eff}
                        300000, // dame
                        new int[] { 1700000000 }, // hp
                        new int[] {200,201,202,203 }, // map join
                        new int[][] {
                                        { Skill.LIEN_HOAN, 7, 300 },
                                        { Skill.KAMEJOKO, 7, 1000 },
                                        { Skill.DICH_CHUYEN_TUC_THOI, 7, 15000 },
                        }, // skill
                        new String[] {}, // text chat 1
                        new String[] { "|-1|Oải rồi hả?", "|-1|Ê cố lên nhóc",
                                        "|-1|Chán",
                                        "|-1|Một mình tao chấp hết tụi bây",
                                        "|-1|HAHAHAHA", "|-1|Chỉ là bọn con nít"
                        }, // text chat 2
                        new String[] { "|-1|Cay quá!"
                        }, // text chat 3
                        REST_15_M // type appear
        );
        public static final BossData DEMONKING = new BossData(
                        "Vua quỷ địa ngục", // name
                        ConstPlayer.XAYDA, // gender
                        new short[] { 1329, 1330, 1331, 45, -1, -1 }, // outfit {head, body, leg, bag, aura, eff}
                        400000, // dame
                        new int[] { 1900000000 }, // hp
                        new int[] { 200,201,202,203}, // map join
                        new int[][] {
                                        { Skill.LIEN_HOAN, 7, 300 },
                                        { Skill.KAMEJOKO, 7, 1000 },
                                        { Skill.DICH_CHUYEN_TUC_THOI, 7, 15000 },
                        }, // skill
                        new String[] {}, // text chat 1
                        new String[] { "|-1|Oải rồi hả?", "|-1|Ê cố lên nhóc",
                                        "|-1|Chán",
                                        "|-1|Một mình tao chấp hết tụi bây",
                                        "|-1|HAHAHAHA", "|-1|Chỉ là bọn con nít"
                        }, // text chat 2
                        new String[] { "|-1|Cay quá!"
                        }, // text chat 3
                        REST_15_M // type appear
        );
}
