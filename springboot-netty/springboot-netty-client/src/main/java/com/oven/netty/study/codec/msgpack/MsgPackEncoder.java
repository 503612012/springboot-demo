package com.oven.netty.study.codec.msgpack;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import lombok.extern.slf4j.Slf4j;
import org.msgpack.MessagePack;

import java.io.IOException;

/**
 * MsgPack编码器
 */
@Slf4j
public class MsgPackEncoder extends MessageToByteEncoder<Object> {

    @Override
    protected void encode(ChannelHandlerContext ctx, Object msg, ByteBuf out) {
        MessagePack messagePack = new MessagePack();
        // 序列化
        byte[] write = new byte[0];
        try {
            write = messagePack.write(msg);
        } catch (IOException e) {
            log.error("系统异常：", e);
        }
        out.writeBytes(write);
    }

}
