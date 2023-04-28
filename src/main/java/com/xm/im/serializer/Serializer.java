package com.xm.im.serializer;

/**
 * @author xm
 * @Description todo
 * @date 2023/4/21
 * @since 1.0
 **/
public interface Serializer {

    /**
     * 获取序列化算法
     * @return
     */
    SerializeTypeEnum getSerializeAlgorithm();

    /**
     * 将对象转为二进制
     * @param obj
     * @return
     */
    byte[] serialize(Object obj);

    /**
     * 将二进制数据转为对象
     * @param data 数据
     * @param clazz 对象类型
     * @param <T>
     * @return
     */
    <T> T deserialize(byte[] data,Class<T> clazz);
}
