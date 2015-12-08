package com.techlooper.model;

import java.util.List;

public class SalaryReviewResultDto {

    private Long createdDateTime;

    private String jobTitle;

    private List<Integer> jobLevelIds;

    private Long locationId;

    private Integer netSalary;

    private List<String> skills;

    private String reportTo;

    private List<Long> jobCategories;

    private Long companySizeId;

    private int gender;

    private int age;

    private SalaryReport salaryReport;

    private List<TopPaidJob> topPaidJobs;

    private SalaryReviewSurvey salaryReviewSurvey;

    private String campaign;

    private Long usdToVndRate;

    private List<SimilarSalaryReview> similarSalaryReviews;

    private String email;

    private String jobAlertEmail;

    public Long getCreatedDateTime() {
        return createdDateTime;
    }

    public void setCreatedDateTime(Long createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public List<Integer> getJobLevelIds() {
        return jobLevelIds;
    }

    public void setJobLevelIds(List<Integer> jobLevelIds) {
        this.jobLevelIds = jobLevelIds;
    }

    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    public Integer getNetSalary() {
        return netSalary;
    }

    public void setNetSalary(Integer netSalary) {
        this.netSalary = netSalary;
    }

    public List<String> getSkills() {
        return skills;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }

    public String getReportTo() {
        return reportTo;
    }

    public void setReportTo(String reportTo) {
        this.reportTo = reportTo;
    }

    public List<Long> getJobCategories() {
        return jobCategories;
    }

    public void setJobCategories(List<Long> jobCategories) {
        this.jobCategories = jobCategories;
    }

    public Long getCompanySizeId() {
        return companySizeId;
    }

    public void setCompanySizeId(Long companySizeId) {
        this.companySizeId = companySizeId;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public SalaryReport getSalaryReport() {
        return salaryReport;
    }

    public void setSalaryReport(SalaryReport salaryReport) {
        this.salaryReport = salaryReport;
    }

    public List<TopPaidJob> getTopPaidJobs() {
        return topPaidJobs;
    }

    public void setTopPaidJobs(List<TopPaidJob> topPaidJobs) {
        this.topPaidJobs = topPaidJobs;
    }

    public SalaryReviewSurvey getSalaryReviewSurvey() {
        return salaryReviewSurvey;
    }

    public void setSalaryReviewSurvey(SalaryReviewSurvey salaryReviewSurvey) {
        this.salaryReviewSurvey = salaryReviewSurvey;
    }

    public String getCampaign() {
        return campaign;
    }

    public void setCampaign(String campaign) {
        this.campaign = campaign;
    }

    public Long getUsdToVndRate() {
        return usdToVndRate;
    }

    public void setUsdToVndRate(Long usdToVndRate) {
        this.usdToVndRate = usdToVndRate;
    }

    public List<SimilarSalaryReview> getSimilarSalaryReviews() {
        return similarSalaryReviews;
    }

    public void setSimilarSalaryReviews(List<SimilarSalaryReview> similarSalaryReviews) {
        this.similarSalaryReviews = similarSalaryReviews;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getJobAlertEmail() {
        return jobAlertEmail;
    }

    public void setJobAlertEmail(String jobAlertEmail) {
        this.jobAlertEmail = jobAlertEmail;
    }
}
