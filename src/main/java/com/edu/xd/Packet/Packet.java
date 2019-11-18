package com.edu.xd.Packet;

import lombok.Data;


@Data
public abstract class Packet {

    private String data;

    private String flag;

    private String channelId;


}
