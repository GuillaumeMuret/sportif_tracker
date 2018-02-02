package com.neos.tcp.server.communication.message;


import com.google.gson.annotations.SerializedName;
import com.neos.tcp.server.communication.protocole.ProtocoleVocabulary;

public class Params {


    @SerializedName(ProtocoleVocabulary.JSON_CAPTEURS)
    private Capteur[] capteurs;

    @SerializedName(ProtocoleVocabulary.JSON_DATAS)
    private Data data;

    @SerializedName(ProtocoleVocabulary.JSON_CAPTEUR_ID)
    private int capteurId;

    @SerializedName(ProtocoleVocabulary.JSON_CAPTEUR_TYPE)
    private String capteurType;

    public Capteur[] getCapteurs() {
        return capteurs;
    }

    public void setCapteurs(Capteur[] capteurs) {
        this.capteurs = capteurs;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public String getCapteurType() {
        return capteurType;
    }

    public void setCapteurType(String capteurType) {
        this.capteurType = capteurType;
    }

    public int getCapteurId() {
        return capteurId;
    }

    public void setCapteurId(int capteurId) {
        this.capteurId = capteurId;
    }
}
