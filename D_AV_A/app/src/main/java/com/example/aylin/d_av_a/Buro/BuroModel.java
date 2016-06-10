package com.example.aylin.d_av_a.Buro;

/**
 * Created by aylin on 18.05.2016.
 */

public class BuroModel {
        public int id;
        public String officeName;
        public String address;
        public String tel1;
        public String tel2;
        public String fax;
        public String taxNo;
        public String taxDepartment;
        public BuroModel(int id, String officeName, String address, String tel1, String tel2,String fax, String taxNo, String taxDepartment) {
// TODO Auto-generated constructor stub
            this.id = id;
            this.officeName = officeName;
            this.address = address;
            this.tel1=tel1;
            this.tel2=tel2;
            this.fax=fax;
            this.taxNo=taxNo;
            this.taxDepartment=taxDepartment;
        }
        public BuroModel() {
        }
}
