package net.witixin.armoreablemobs;

import java.util.ArrayList;
import java.util.List;

public class Utilities {
    public static <T> List<T> mergeOrMakeList(List<T> objList, T obj){
        if (objList == null){
            objList = new ArrayList<>();
        }
        objList.add(obj);
        return objList;
    }
}
