package com.xm.im.serializer;

import java.util.Arrays;
import lombok.Getter;

/**
 * @author xm
 * @Description todo
 * @date 2023/4/21
 * @since 1.0
 **/
@Getter
public enum SerializeTypeEnum {
    /** json **/
    JSON((byte) 3),
    UNKNOWN((byte)-127)
    ;

    byte code;

    SerializeTypeEnum(byte code) {
        this.code = code;
    }

    public static SerializeTypeEnum fromCode(byte code) {
        return Arrays.stream(values())
            .filter(seria -> seria.code == code)
            .findFirst()
            .orElse(UNKNOWN);
    }
}
