package com.ecnu.pinyin;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;

import java.util.Arrays;

/**
 * Created by yerunjie on 2018-12-29
 *
 * @author yerunjie
 */
public class PinYinMain {
    public static void main(String[] args) throws Exception{
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        //大小写
        format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        //音标
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        System.out.println(Arrays.toString(PinyinHelper.toHanyuPinyinStringArray('陈', format)));
    }
}
