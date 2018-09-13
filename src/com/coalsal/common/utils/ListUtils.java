package com.coalsal.common.utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by li on 2017/6/8.
 */
public class ListUtils {

    /**
     * 对list里的字符串去重
     * @param object
     * @return
     */
    public static  boolean isNotNull(List  object) {
       if(object!=null&&object.size()>0){
    	   return true;
       }
        return false;
    }
}
