package com.example.aylin.avukep.BuroAvukat;

/**
 * Created by aylin on 23.05.2016.
 */
public class BuroAvModel {
        public String value;
    public String image;
        public BuroAvModel( String value,String image) {
            this.value = value; this.image=image;
        }
        public BuroAvModel() {
        }
       public String getValue(){
        return value;
    }
        public String getImage(){return image;}
}
