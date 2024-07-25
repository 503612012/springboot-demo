package com.oven.netty.util;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * 对象序列化工具
 */
@Slf4j
public class ObjectSerializerUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(ObjectSerializerUtils.class);

    /**
     * 反序列化
     */
    public static Object deSerilizer(byte[] data) {
        if (data != null && data.length > 0) {
            try {
                ByteArrayInputStream bis = new ByteArrayInputStream(data);
                ObjectInputStream ois = new ObjectInputStream(bis);
                return ois.readObject();
            } catch (Exception e) {
                LOGGER.info("[异常信息] {}", e.getMessage());
                log.error("系统异常：", e);
            }
        } else {
            LOGGER.info("[反序列化] 入参为空");
        }
        return null;
    }

    /**
     * 序列化对象
     */
    public static byte[] serilizer(Object obj) {
        if (obj != null) {
            try {
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(bos);
                oos.writeObject(obj);
                oos.flush();
                oos.close();
                return bos.toByteArray();
            } catch (IOException e) {
                log.error("系统异常：", e);
            }
        }
        return null;
    }

}
