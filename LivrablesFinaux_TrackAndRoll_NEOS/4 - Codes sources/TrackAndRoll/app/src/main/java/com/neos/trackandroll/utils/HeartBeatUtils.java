package com.neos.trackandroll.utils;


import com.neos.trackandroll.model.session.Session;
import com.neos.trackandroll.model.session.data.PlayerHeartBeatData;

import java.util.ArrayList;

public class HeartBeatUtils {

    public static ArrayList<PlayerHeartBeatData> getBpmDatas(Session session, String uuid){
        ArrayList<PlayerHeartBeatData> bpmDatasList = new ArrayList<>();



        for(int i=0; i<session.getPlayerSessionDataMap().get(uuid).getPlayerHeartBeatData().size(); i++){
            //bpmDatasList.add(session.getPlayerSessionDataMap().get(uuid).getPlayerHeartBeatData());
            //bpmDatasList.add()
        }


        return bpmDatasList;
    }


}
