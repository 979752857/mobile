package com.tendy.utils;

import java.util.HashMap;
import java.util.Map;

public class GeomanticRule {

    public static void main(String[] args) {
        String phone = "18211899056";
        LuckRule luckRule = getPhoneLuck(phone);
        System.out.println(phone);
        System.out.println(JsonMapper.toJson(luckRule));
        CharacterRule characterRule = getPhoneCharacter(phone);
        System.out.println(JsonMapper.toJson(characterRule));
    }

    public static LuckRule getPhoneLuck(String phone){
        if(StringUtils.isBlank(phone)){
            return null;
        }
        StringBuilder sb = new StringBuilder();
        char[] chars = phone.toCharArray();
        for(int i = chars.length - 4 ; i < chars.length; i++){
            sb.append(String.valueOf(chars[i]));
        }
        Integer numTotal = Integer.valueOf(sb.toString());
        return luckRuleMap.get(numTotal%80);
    }

    public static CharacterRule getPhoneCharacter(String phone){
        if(StringUtils.isBlank(phone)){
            return null;
        }
        StringBuilder sb = new StringBuilder();
        Integer numTotal = 0;
        char[] chars = phone.toCharArray();
        for(int i = 0 ; i < chars.length; i++){
            numTotal += Integer.valueOf(String.valueOf(chars[i]));
        }
        while (true){
            if(numTotal < 10){
                break;
            }
            chars = String.valueOf(numTotal).toCharArray();
            numTotal = 0;
            for(int i = 0 ; i < chars.length; i++){
                numTotal += Integer.valueOf(String.valueOf(chars[i]));
            }
        }
        return characterRuleMap.get(numTotal);
    }

    public static Map<Integer, LuckRule> luckRuleMap = new HashMap<>();
    static{
        luckRuleMap.put(1, new LuckRule("吉", "大展鸿图，信用得固，名利双收，可获成功。", 1));
        luckRuleMap.put(2, new LuckRule("凶", "根基不固，摇摇欲坠，一盛一衰，劳而无获。", 2));
        luckRuleMap.put(3, new LuckRule("吉", "根深蒂固，蒸蒸日上，如意吉祥，百事顺遂。", 3));
        luckRuleMap.put(4, new LuckRule("凶", "前途坎坷，苦难折磨，非有毅力，难望成功。", 4));
        luckRuleMap.put(5, new LuckRule("吉", "阴阳和合，生意兴隆，名利双收，后福重重。", 5));
        luckRuleMap.put(6, new LuckRule("吉", "万宝集门，天降幸运，立志奋发，得成大功。", 6));
        luckRuleMap.put(7, new LuckRule("吉", "独营生意，和气吉祥，排除万难，必获成功。", 7));
        luckRuleMap.put(8, new LuckRule("吉", "努力发达，贯彻志望，不忘进退，可望成功。", 8));
        luckRuleMap.put(9, new LuckRule("凶", "虽抱奇才，有才无命，独营无力，财力难望。", 9));
        luckRuleMap.put(10, new LuckRule("凶", "乌云遮月，暗淡无光，空费心力，徒劳无功。", 10));
        luckRuleMap.put(11, new LuckRule("吉", "草木逢春，枝叶沾露，稳健着实，必得人望。", 11));
        luckRuleMap.put(12, new LuckRule("凶", "薄弱无力，孤立无援，外祥内苦，谋事难成。", 12));
        luckRuleMap.put(13, new LuckRule("吉", "天赋吉运，能得人望，善用智慧，必获成功。", 13));
        luckRuleMap.put(14, new LuckRule("凶", "忍得若难，必有后福，是成是败，惟靠紧毅。", 14));
        luckRuleMap.put(15, new LuckRule("吉", "谦恭做事，外得人和，大事成就，一门兴隆。", 15));
        luckRuleMap.put(16, new LuckRule("吉", "能获众望，成就大业，名利双收，盟主四方。", 16));
        luckRuleMap.put(17, new LuckRule("吉", "排除万难，有贵人助，把握时机，可得成功。", 17));
        luckRuleMap.put(18, new LuckRule("吉", "经商做事，顺利昌隆，如能慎始，百事亨通。", 18));
        luckRuleMap.put(19, new LuckRule("凶", "成功虽早，慎防亏空，内外不合，障碍重重。", 19));
        luckRuleMap.put(20, new LuckRule("凶", "智商志大，历尽艰难，焦心忧劳，进得两难。", 20));
        luckRuleMap.put(21, new LuckRule("吉", "先历困苦，后得幸福，霜雪梅花，春来怒放。", 21));
        luckRuleMap.put(22, new LuckRule("凶", "秋草逢霜，怀才不遇，忧愁怨苦，事不如意。", 22));
        luckRuleMap.put(23, new LuckRule("吉", "旭日升天，名显四方，渐次进展，终成大业。", 23));
        luckRuleMap.put(24, new LuckRule("吉", "绵绣前程，须靠自力，多用智谋，能奏大功。", 24));
        luckRuleMap.put(25, new LuckRule("吉", "天时地利，只欠人和，讲信修睦，即可成功。", 25));
        luckRuleMap.put(26, new LuckRule("凶带吉", "波澜起伏，千变万化，凌架万难，必可成功。", 26));
        luckRuleMap.put(27, new LuckRule("吉带凶", "一成一败，一盛一衰，惟靠谨慎，可守成功。", 27));
        luckRuleMap.put(28, new LuckRule("凶", "鱼临旱地，难逃恶运，此数大凶，不如更名。", 28));
        luckRuleMap.put(29, new LuckRule("吉", "如龙得云，青云直上，智谋奋进，才略奏功。", 29));
        luckRuleMap.put(30, new LuckRule("吉带凶", "吉凶参半，得失相伴，投机取巧，吉凶无常。", 30));
        luckRuleMap.put(31, new LuckRule("吉", "此数大吉，名利双收，渐进向上，大业成就。", 31));
        luckRuleMap.put(32, new LuckRule("吉", "池中之龙，风云际会，一跃上天，成功可望。", 32));
        luckRuleMap.put(33, new LuckRule("吉", "意气用事，人和必失，如能慎始，必可昌隆。", 33));
        luckRuleMap.put(34, new LuckRule("凶", "灾难不绝，难望成功，此数大凶，不如更名。", 34));
        luckRuleMap.put(35, new LuckRule("吉", "中吉之数，进退保守，生意安稳，成就普通。", 35));
        luckRuleMap.put(36, new LuckRule("凶", "波澜得叠，常陷穷困，动不如静，有才无命。", 36));
        luckRuleMap.put(37, new LuckRule("吉", "逢凶化吉，吉人天相，风调雨顺，生意兴隆。", 37));
        luckRuleMap.put(38, new LuckRule("凶带吉", "名虽可得，利则难获，艺界发展，可望成功。", 38));
        luckRuleMap.put(39, new LuckRule("吉", "云开见月，虽有劳碌，光明坦途，指日可望。", 39));
        luckRuleMap.put(40, new LuckRule("吉带凶", "一成一衰，沉浮不定，知难而退，自获天佑。", 40));
        luckRuleMap.put(41, new LuckRule("吉", "天赋吉运，德望兼备，继续努力，前途无限。", 41));
        luckRuleMap.put(42, new LuckRule("吉带凶", "事业不专，十九不成，专心进取，可望成功。", 42));
        luckRuleMap.put(43, new LuckRule("吉带凶", "雨夜之花，外祥内苦，忍耐自重，转凶为吉。", 43));
        luckRuleMap.put(44, new LuckRule("凶", "虽用心计，事难遂愿，贪功冒进，必招失败。", 44));
        luckRuleMap.put(45, new LuckRule("吉", "杨柳遇春，绿叶发枝，冲破难关，一举成名。", 45));
        luckRuleMap.put(46, new LuckRule("凶", "坎坷不平，艰难重重，若无耐心，难望有成。", 46));
        luckRuleMap.put(47, new LuckRule("吉", "有贵人助，可成大业，虽遇不幸，浮沉不定。", 47));
        luckRuleMap.put(48, new LuckRule("吉", "美化丰实，鹤立鸡群，名利俱全，繁荣富贵。", 48));
        luckRuleMap.put(49, new LuckRule("凶", "遇吉则吉，遇凶则凶，惟靠谨慎，逢凶化吉。", 49));
        luckRuleMap.put(50, new LuckRule("吉带凶", "吉凶互见，一成一败，凶中有吉，吉中有凶。", 50));
        luckRuleMap.put(51, new LuckRule("吉带凶", "一盛一衰，浮沉不常，自重自处，可保平安。", 51));
        luckRuleMap.put(52, new LuckRule("吉", "草木逢春，雨过天晴，渡过难关，即获得成功。", 52));
        luckRuleMap.put(53, new LuckRule("吉带凶", "盛衰参半，外祥内苦，先吉后凶，先凶后吉。", 53));
        luckRuleMap.put(54, new LuckRule("凶", "虽倾全力，难望成功，此数大凶，最好改名。", 54));
        luckRuleMap.put(55, new LuckRule("吉带凶", "外观昌隆，内隐祸患，克服难关，开出泰运。", 55));
        luckRuleMap.put(56, new LuckRule("凶", "事与愿违，终难成功，欲速不达，有始无终。", 56));
        luckRuleMap.put(57, new LuckRule("凶带吉", "虽有困难，时来运转，旷野枯草，春来花开。", 57));
        luckRuleMap.put(58, new LuckRule("凶带吉", "半凶半吉，浮沉多端，始凶终吉，能保成功。", 58));
        luckRuleMap.put(59, new LuckRule("凶", "遇事猜疑，难望成事，大刀阔斧，始可有成。", 59));
        luckRuleMap.put(60, new LuckRule("凶", "黑暗无光，心迷意乱，出尔反尔，难定方针。", 60));
        luckRuleMap.put(61, new LuckRule("吉带凶", "运遮半月，内隐风波，应有谨慎，始保平安。", 61));
        luckRuleMap.put(62, new LuckRule("凶", "烦闷懊恼，事业难展，自防灾祸，始免困境。", 62));
        luckRuleMap.put(63, new LuckRule("吉", "万物化育，繁荣之象，专心一意，必能成功。", 63));
        luckRuleMap.put(64, new LuckRule("凶", "见异思迁，十九不成，徒劳无功，不如更名。", 64));
        luckRuleMap.put(65, new LuckRule("吉", "吉运自来，能享盛名，把握时机，必获成功。", 65));
        luckRuleMap.put(66, new LuckRule("凶", "黑夜温长，进退维谷，内外不和，信用缺乏。", 66));
        luckRuleMap.put(67, new LuckRule("吉", "独营事业，事事如意，功成名就，富贵自来。", 67));
        luckRuleMap.put(68, new LuckRule("吉", "思虎虑周祥，计书力行，不失先机，可望成功。", 68));
        luckRuleMap.put(69, new LuckRule("凶", "动摇不安，常陷逆境，不得时运，难得利润。", 69));
        luckRuleMap.put(70, new LuckRule("凶", "惨淡经营，难免贫困，此数不吉，最好改名。", 70));
        luckRuleMap.put(71, new LuckRule("吉带凶", "吉凶参半，惟赖勇气，贯彻力行，始可成功。", 71));
        luckRuleMap.put(72, new LuckRule("凶", "利害混集，凶多吉少，得而复失，难以安顺。", 72));
        luckRuleMap.put(73, new LuckRule("吉", "安乐自来，自然吉祥，力行不懈，终必成功。", 73));
        luckRuleMap.put(74, new LuckRule("凶", "利不及费，坐食山空，如无章法，难望成功。", 74));
        luckRuleMap.put(75, new LuckRule("吉带凶", "吉中带凶，欲速不达，进不如守，可保安祥。", 75));
        luckRuleMap.put(76, new LuckRule("凶", "此数大凶，破产之象，宜速改名，以避厄运。", 76));
        luckRuleMap.put(77, new LuckRule("吉带凶", "先苦后甘，先甜后苦，如能守成，不致失败。", 77));
        luckRuleMap.put(78, new LuckRule("吉带凶", "有得有失，华而不实，须防劫财，始保安顺。", 78));
        luckRuleMap.put(79, new LuckRule("凶", "如走夜路，前途无光，希望不大，劳而无功。", 79));
        luckRuleMap.put(80, new LuckRule("吉带凶", "得而复失，枉费心机，守成无贪，可保安稳。", 80));
        luckRuleMap.put(0, new LuckRule("吉", "最极之数，还本归元，能得繁荣，发达成功。", 0));
    }

    public static Map<Integer, CharacterRule> characterRuleMap = new HashMap<>();
    static{
        characterRuleMap.put(1, new CharacterRule("要面包不要爱情", "责任心重，尤其对工作充满热诚，是个彻头彻尾工作狂。但往往因为过分专注职务，而忽略身边的家人及朋友，是个宁要面包不需要爱情的理性主义者。", 1));
        characterRuleMap.put(2, new CharacterRule("不善表达/疑心重", "在乎身边各人对自己的评价及观感，不善表达个人情感，是个先考虑别人感受，再作出相应配合的内敛一族。对于爱侣，经常存有怀疑之心。", 2));
        characterRuleMap.put(3, new CharacterRule("大胆行事冲动派", "爱好追寻刺激，有不理后果大胆行事的倾向。崇尚自由奔放的恋爱，会拼尽全力爱一场，是就算明知无结果都在所不惜的冲动派。", 3));
        characterRuleMap.put(4, new CharacterRule("高度戒备难交心", "经常处于戒备状态，对任何事都没法放松处理，孤僻性情难以交朋结友。对于爱情，就更加会慎重处理。任何人必须经过长期观察及通过连番考验，方会减除戒备与你交往。", 4));
        characterRuleMap.put(5, new CharacterRule("好奇心旺求知欲强", "好奇心极度旺盛，求知欲又强，有打烂沙盘问到笃的锲而不舍精神。此外，你天生有语言天分，学习外文比一般人更易掌握。", 5));
        characterRuleMap.put(6, new CharacterRule("做事喜好凭直觉", "有特强的第六灵感，性格率直无机心，深得朋辈爱戴。感情路上多采多姿。做事喜好凭个人直觉及预感做决定。", 6));
        characterRuleMap.put(7, new CharacterRule("独断独行/吸引人", "为人独断独行，事事自行作主解决，鲜有求助他人。而这份独立个性，正正就是吸引异性的特质。但其实心底里，却是渴望有人可让他/她依赖。", 7));
        characterRuleMap.put(8, new CharacterRule("热情/善变梦想家", "对人热情无遮掩，时常梦想可以谈一场戏剧性恋爱，亲身体会个中悲欢离合的动人经历，是个大梦想家。但对于感情却易变卦。", 8));
        characterRuleMap.put(9, new CharacterRule("自我牺牲/性格被动", "习惯于无条件付出，从不祈求有回报，有为了成全他人不惜牺牲自己的情操。但讲到本身的爱情观，却流于被动，往往因为内敛而错过大好姻缘。", 9));
    }
}