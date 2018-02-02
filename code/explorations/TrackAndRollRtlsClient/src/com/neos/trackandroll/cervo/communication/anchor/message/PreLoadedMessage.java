package com.neos.trackandroll.cervo.communication.anchor.message;

import com.neos.trackandroll.cervo.communication.anchor.protocol.AnchorProcessOut;
import com.neos.trackandroll.cervo.model.Anchor;

public class PreLoadedMessage {

    public static final AnchorMessage SET_POS_0X0108 = new AnchorMessage(AnchorProcessOut.PROCESS_SET_POS,"0x0108",
            Anchor.POSITION_0X0108);

    public static final AnchorMessage SET_POS_0X00B8 = new AnchorMessage(AnchorProcessOut.PROCESS_SET_POS,"0x00B8",
            Anchor.POSITION_0X00B8);

    public static final AnchorMessage SET_POS_0X00C7 = new AnchorMessage(AnchorProcessOut.PROCESS_SET_POS,"0x00C7",
            Anchor.POSITION_0X00C7);

    public static final AnchorMessage SET_POS_0X00C4 = new AnchorMessage(AnchorProcessOut.PROCESS_SET_POS,"0x00C4",
            Anchor.POSITION_0X00C4);

    public static final AnchorMessage SET_POS_0X00B9 = new AnchorMessage(AnchorProcessOut.PROCESS_SET_POS,"0x00B9",
            Anchor.POSITION_0X00B9);

    public static final AnchorMessage SET_POS_0X00CA = new AnchorMessage(AnchorProcessOut.PROCESS_SET_POS,"0x00CA",
            Anchor.POSITION_0X00CA);
}
