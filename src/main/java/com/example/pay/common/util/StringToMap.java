package com.example.pay.common.util;

import cn.hutool.core.text.StrSpliter;
import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author mxuexxmy
 * @ClassName StringToMap
 * @Description TODO
 * @Date 11/21/2020 5:28 PM
 * @Version 1.0
 **/
@Data
public class StringToMap {

    public static Map<String, String> stringToMap(String str) {
        Map<String, String> map = new HashMap<>();
        String newStr = "";
        for (int i = 1; i < str.length() - 1; i++) {
            newStr += str.charAt(i);
        }
        List<String> listStr = StrSpliter.split(newStr, ",", true, true);
        for (String str1 : listStr) {
            List<String> str2 = StrSpliter.split(str1, "=", true, true);
            map.put(str2.get(0), str2.get(1));
        }
        return map;
    }


}
