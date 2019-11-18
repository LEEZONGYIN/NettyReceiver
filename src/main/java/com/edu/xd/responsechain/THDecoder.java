package com.edu.xd.responsechain;



import com.edu.xd.Packet.Packet;
import com.edu.xd.Packet.THPacket;
import io.netty.channel.ChannelHandlerContext;

public class THDecoder extends MainDecoder {

    @Override
    public Packet decode(String data, ChannelHandlerContext ctx) {

        if (data.length() == 18) {
            THPacket thPacket = new THPacket();
            thPacket.setFlag("A");
            thPacket.setData(data);
            thPacket.setChannelId(ctx.channel().id().asShortText());
            return thPacket;
        } else if (getNext() != null) {

           return getNext().decode(data, ctx);
        } else {
            System.out.println("thPacket为空");
            return null;
        }
    }

}
