package com.edu.xd.responsechain;

import com.edu.xd.Packet.Packet;
import io.netty.channel.ChannelHandlerContext;

public abstract class MainDecoder {

    private MainDecoder next;
    public void setNext(MainDecoder next){
        this.next = next;
    }
    public MainDecoder getNext(){
        return next;
    }

    public abstract Packet decode(String data, ChannelHandlerContext ctx);
}
