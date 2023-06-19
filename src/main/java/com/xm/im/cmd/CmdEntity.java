package com.xm.im.cmd;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author xm
 * @Description todo
 * @date 2023/4/28
 * @since 1.0
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CmdEntity<T> {

    private Integer commandType;

    private Class<T> commandClazz;

    private T commandMsg;
}
