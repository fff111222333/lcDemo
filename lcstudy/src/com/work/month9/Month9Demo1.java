package com.work.month9;

import com.work.month9.dto.DictDataDO;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author fjc
 * @title
 * @description
 * @date 2023/9/7 9:36
 */
public class Month9Demo1 {
    public static void main(String[] args) {

        DictDataDO dictDataDO1 = new DictDataDO();
        dictDataDO1.setDictType("ty");
        dictDataDO1.setSort(2);

        DictDataDO dictDataDO2 = new DictDataDO();
        dictDataDO1.setDictType("jy");
        dictDataDO1.setSort(1);

        List<DictDataDO> list = new ArrayList<DictDataDO>();
        list.add(dictDataDO1);
        list.add(dictDataDO2);

//        List<DictDataDO> dictDataDOS = filterNull(list);
//        dictDataDOS.sort(COMPATER);
        list.sort(COMPATER);
       // System.out.println();
    }



    private static final Comparator<DictDataDO> COMPATER = Comparator.comparing(DictDataDO::getDictType)
            .thenComparingInt(DictDataDO::getSort);
    private static final Comparator<DictDataDO> COMPATER1 = Comparator.comparing(DictDataDO::getDictType);


/*  通过网上查询，得知该问题应该是List中有null值引起的。即ArrayList中的数组长度默认为10，
    如果对其添加值个数小于10，就会有null值存在。而sort()方法会遍历每个对象取该对象的日期字段，
    当对象为null时再取其日期字段值自然会报空指针异常。*/

    public static List<DictDataDO> filterNull(List<DictDataDO> list){
        List<DictDataDO> filterList = new ArrayList<DictDataDO>();
        for(DictDataDO dictDataDO: list){
            if(dictDataDO != null){
                filterList.add(dictDataDO);
            }
        }
        return filterList;
    }


}
