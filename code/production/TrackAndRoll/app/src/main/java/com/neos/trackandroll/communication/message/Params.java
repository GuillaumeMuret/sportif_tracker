package com.neos.trackandroll.communication.message;


import com.google.gson.annotations.SerializedName;
import com.neos.trackandroll.communication.protocole.ProtocolVocabulary;

public class Params {

    @SerializedName(ProtocolVocabulary.JSON_CAPTEURS)
    private Capteur[] capteurs;

    @SerializedName(ProtocolVocabulary.JSON_DATAS)
    private Data datas;

    @SerializedName(ProtocolVocabulary.JSON_CAPTEUR_ID)
    private String capteurId;

    @SerializedName(ProtocolVocabulary.JSON_CAPTEUR_TYPE)
    private String capteurType;

    public Capteur[] getCapteurs() {
        return capteurs;
    }

    public void setCapteurs(Capteur[] capteurs) {
        this.capteurs = capteurs;
    }

    public Data getDatas() {
        return datas;
    }

    public void setDatas(Data datas) {
        this.datas = datas;
    }

    public String getCapteurType() {
        return capteurType;
    }

    public void setCapteurType(String capteurType) {
        this.capteurType = capteurType;
    }

    public String getCapteurId() {
        return capteurId;
    }

    public void setCapteurId(String capteurId) {
        this.capteurId = capteurId;
    }
}
