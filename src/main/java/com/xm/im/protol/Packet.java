package com.xm.im.protol;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author xm
 * @Description 自定义协议格式
 * 4字节魔术 + 1字节协议版本 + 1字节序列化算法 + 4字节事件类型 + 4字节数据长度 + 数据部分
 * @date 2023/4/21
 * @since 1.0
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Packet implements Serializable {
    public static int MAGIC = 0x42D;
    private final int magic = MAGIC;
    private byte version = 1;
    private byte serializeType;
    private int command;
    private int len;
    private byte[] data;
}
