package com.techlooper.entity;

import com.techlooper.model.Employer;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldIndex;

import java.util.List;

import static org.springframework.data.elasticsearch.annotations.FieldType.Integer;
import static org.springframework.data.elasticsearch.annotations.FieldType.*;
import static org.springframework.data.elasticsearch.annotations.FieldType.String;

@Document(indexName = "employerInformation", type = "company")
public class EmployerEntity {

    @Id
    private Long companyId;

    @Field(type = String, store = true, index = FieldIndex.not_analyzed)
    private String companyLogoURL;

    @Field(type = String, store = true, indexAnalyzer = "index_analyzer", searchAnalyzer = "search_analyzer")
    private String companyName;

    @Field(type = String, store = true, indexAnalyzer = "index_analyzer", searchAnalyzer = "search_analyzer")
    private String address;

    @Field(type = Integer)
    private Integer companySizeId;

    @Field(type = Nested)
    private List<Employer> employers;

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getCompanyLogoURL() {
        return companyLogoURL;
    }

    public void setCompanyLogoURL(String companyLogoURL) {
        this.companyLogoURL = companyLogoURL;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getCompanySizeId() {
        return companySizeId;
    }

    public void setCompanySizeId(Integer companySizeId) {
        this.companySizeId = companySizeId;
    }

    public List<Employer> getEmployers() {
        return employers;
    }

    public void setEmployers(List<Employer> employers) {
        this.employers = employers;
    }
}
