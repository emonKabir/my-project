package com.example.emon.bdmedic.Laboratory;

import java.util.HashMap;
import java.util.Map;

public class InvestigationData {
    private Integer id;
    private String name;
    private String normal_finding;
    private String increased;
    private String decreased;
    private String others;
    //private String createdAt;
    //private String updatedAt;

    public InvestigationData(Integer id,String name, String normal_finding, String increased, String decreased, String others) {
        this.id = id;
        this.name = name;
        this.normal_finding = normal_finding;
        this.increased = increased;
        this.decreased = decreased;
        this.others = others;
    }

    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNormal_finding() {
        return normal_finding;
    }

    public void setNormal_finding(String normal_finding) {
        this.normal_finding = normal_finding;
    }

    public String getIncreased() {
        return increased;
    }

    public void setIncreased(String increased) {
        this.increased = increased;
    }

    public String getDecreased() {
        return decreased;
    }

    public void setDecreased(String decreased) {
        this.decreased = decreased;
    }

    public String getOthers() {
        return others;
    }

    public void setOthers(String others) {
        this.others = others;
    }

       /* public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }*/



    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }
}
