package com.example.emon.bdmedic.PriceCalculator;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Dosage {



        @SerializedName("status")

        private Integer status;
        @SerializedName("message")

        private String message;
        @SerializedName("data")

        private List<DosageData> data = null;

        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public List<DosageData> getData() {
            return data;
        }

        public void setData(List<DosageData> data) {
            this.data = data;
        }


}
