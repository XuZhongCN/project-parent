package com.cpscs.common.dto.do2dto;

import java.lang.reflect.Field;

/**
 * /**
 *
 * @Description
 * @Author xuzhong <xuzhongiop@qq.com>
 * @Version V1.0.0
 * @Since 1.0
 * @Date 2019/1/14 0014
 */
public class Do2DtoUtil<T> {
    public T doConvert(Class<T> dtoClass,T object,Class doClass,Object doObject){
        Field[] fields = dtoClass.getDeclaredFields();

        try {
            object = dtoClass.newInstance();
            for (int i = 0; i < fields.length; i++) {

                fields[i].setAccessible(true);
                String fieldName = fields[i].getName();
                if("SerializableId".equalsIgnoreCase(fieldName)){
                    continue;
                }
                Field doField=null;
                try {
                    doField=doClass.getDeclaredField(fieldName);
                }catch(Exception e){
                    //e.printStackTrace();
                    System.err.println("属性"+fieldName+"不存在,于是跳过了这个属性");
                    continue;
                }
                doField.setAccessible(true);

                fields[i].set(object,doField.get(doObject));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        System.out.println(object);
        return object;
    }

}
