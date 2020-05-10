package com.example.myapplication.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Time implements Parcelable {
    private int imgEscudo,imgCamisa,imgEstadio,idLiga,idTime,anoFundacao;
    private String pais,capacidadeEstadio,localizacaoEstadio,nomeEstadio,nomeLiga,nomeTime;

    public Time() {
    }

    public Time(int imgEscudo,String nomeTime){
        this.imgEscudo = imgEscudo;
        this.nomeTime = nomeTime;
    }

    public Time(int imgEscudo, int imgCamisa, int imgEstadio, int idLiga, int idTime, int anoFundacao, String pais, String capacidadeEstadio, String localizacaoEstadio, String nomeEstadio, String nomeLiga, String nomeTime) {
        this.imgEscudo = imgEscudo;
        this.imgCamisa = imgCamisa;
        this.imgEstadio = imgEstadio;
        this.idLiga = idLiga;
        this.idTime = idTime;
        this.anoFundacao = anoFundacao;
        this.pais = pais;
        this.capacidadeEstadio = capacidadeEstadio;
        this.localizacaoEstadio = localizacaoEstadio;
        this.nomeEstadio = nomeEstadio;
        this.nomeLiga = nomeLiga;
        this.nomeTime = nomeTime;
    }

    public int getImgEscudo() {
        return imgEscudo;
    }

    public void setImgEscudo(int imgEscudo) {
        this.imgEscudo = imgEscudo;
    }

    public int getImgCamisa() {
        return imgCamisa;
    }

    public void setImgCamisa(int imgCamisa) {
        this.imgCamisa = imgCamisa;
    }

    public int getImgEstadio() {
        return imgEstadio;
    }

    public void setImgEstadio(int imgEstadio) {
        this.imgEstadio = imgEstadio;
    }

    public int getIdLiga() {
        return idLiga;
    }

    public void setIdLiga(int idLiga) {
        this.idLiga = idLiga;
    }

    public int getIdTime() {
        return idTime;
    }

    public void setIdTime(int idTime) {
        this.idTime = idTime;
    }

    public int getAnoFundacao() {
        return anoFundacao;
    }

    public void setAnoFundacao(int anoFundacao) {
        this.anoFundacao = anoFundacao;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getCapacidadeEstadio() {
        return capacidadeEstadio;
    }

    public void setCapacidadeEstadio(String capacidadeEstadio) {
        this.capacidadeEstadio = capacidadeEstadio;
    }

    public String getLocalizacaoEstadio() {
        return localizacaoEstadio;
    }

    public void setLocalizacaoEstadio(String localizacaoEstadio) {
        this.localizacaoEstadio = localizacaoEstadio;
    }

    public String getNomeEstadio() {
        return nomeEstadio;
    }

    public void setNomeEstadio(String nomeEstadio) {
        this.nomeEstadio = nomeEstadio;
    }

    public String getNomeLiga() {
        return nomeLiga;
    }

    public void setNomeLiga(String nomeLiga) {
        this.nomeLiga = nomeLiga;
    }

    public String getNomeTime() {
        return nomeTime;
    }

    public void setNomeTime(String nomeTime) {
        this.nomeTime = nomeTime;
    }

    public static Creator<Time> getCREATOR() {
        return CREATOR;
    }

    protected Time(Parcel in) {
        imgEscudo = in.readInt();
        imgCamisa = in.readInt();
        imgEstadio = in.readInt();
        idLiga = in.readInt();
        idTime = in.readInt();
        anoFundacao = in.readInt();
        pais = in.readString();
        capacidadeEstadio = in.readString();
        localizacaoEstadio = in.readString();
        nomeEstadio = in.readString();
        nomeLiga = in.readString();
        nomeTime = in.readString();
    }

    public static final Creator<Time> CREATOR = new Creator<Time>() {
        @Override
        public Time createFromParcel(Parcel in) {
            return new Time(in);
        }

        @Override
        public Time[] newArray(int size) {
            return new Time[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(imgEscudo);
        dest.writeInt(imgCamisa);
        dest.writeInt(imgEstadio);
        dest.writeInt(idLiga);
        dest.writeInt(idTime);
        dest.writeInt(anoFundacao);
        dest.writeString(pais);
        dest.writeString(capacidadeEstadio);
        dest.writeString(localizacaoEstadio);
        dest.writeString(nomeEstadio);
        dest.writeString(nomeLiga);
        dest.writeString(nomeTime);
    }
}
