package com.xm.im.serializer.impl;

import com.alibaba.fastjson.JSON;
import com.xm.im.serializer.SerializeTypeEnum;
import com.xm.im.serializer.Serializer;

/**
 * @author xm
 * @Description todo
 * @date 2023/4/21
 * @since 1.0
 **/
public class JsonSerializer implements Serializer {

    @Override
    public SerializeTypeEnum getSerializeAlgorithm() {
        return SerializeTypeEnum.JSON;
    }

    @Override
    public byte[] serialize(Object obj) {
        return JSON.toJSONBytes(obj);
    }

    @Override
    public <T> T deserialize(byte[] data, Class<T> clazz) {
        return JSON.parseObject(data,clazz);
    }
}
