package com.xm.im.serializer;

import com.xm.im.serializer.impl.JsonSerializer;

/**
 * @author xm
 * @Description todo
 * @date 2023/4/28
 * @since 1.0
 **/
public class SerializeFactory {

    /**
     * 获取序列化实现
     * @param type
     * @return
     */
    public static Serializer get(byte type) {
        SerializeTypeEnum typeEnum = SerializeTypeEnum.fromCode(type);
        Serializer serializer;
        switch (typeEnum) {
            case UNKNOWN:
                serializer = null;
                break;
            case JSON:
            default:
                serializer = new JsonSerializer();
        }
        return serializer;
    }
}
