package com.example.aylin.avukep.User;

/**
 * Created by aylin on 05.05.2016.
 */
public class UserInfoModel {
    public int id;
    public String tc;
    public String avukat;
    public String sicil;
    public String tel;

    public UserInfoModel(int id, String tc, String avukat, String tel, String sicil) {
// TODO Auto-generated constructor stub
        this.id = id;
        this.tc = tc;
        this.avukat = avukat;
        this.tel=tel;
        this.sicil=sicil;
    }
    public UserInfoModel() {
    }
}
