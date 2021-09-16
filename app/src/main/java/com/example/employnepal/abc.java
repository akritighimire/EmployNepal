//firebase ko child ko lagi same as UserInformation
package com.example.employnepal;

public class abc {
    private String jobtitle , jobdescription , noofemployees , joblocation , jobcategory ,workinghrs , salary , applybefore ;

    public abc(){
    }
    public abc(String jobtitle , String jobdescription , String noofemployees , String joblocation , String jobcategory ,String workinghrs , String salary , String applybefore){
        this.jobtitle = jobtitle;
        this.jobdescription = jobdescription;
        this.noofemployees = noofemployees;
        this.joblocation = joblocation;
        this.jobcategory = jobcategory;
        this.workinghrs = workinghrs;
        this.salary = salary;
        this.applybefore = applybefore;
    }

    public String getJobtitle(){
        return jobtitle;
    }
    public void setJobtitle(String jobtitle){
        this.jobtitle = jobtitle;
    }
    public String getJobdescription(){
        return jobdescription;
    }
    public void setJobdescription(String jobdescription){
        this.jobdescription = jobdescription;
    }
    public String getNoofemployees(){
        return noofemployees;
    }
    public void setNoofemployees(String noofemployees){
        this.noofemployees = noofemployees;
    }
    public String getJoblocation(){
        return joblocation;
    }
    public void setJoblocation(String joblocation) {
        this.joblocation = joblocation;
    }
    public String getJobcategory(){
        return jobcategory;
    }
    public void setJobcategory(String jobcategory) {
        this.jobcategory = jobcategory;
    }
    public String getWorkinghrs(){
        return workinghrs;
    }
    public void setWorkinghrs(String workinghrs) {
        this.workinghrs = workinghrs;
    }
    public String getApplybefore(){
        return applybefore;
    }
    public void setApplybefore(String applybefore) {
        this.applybefore = applybefore;
    }
}
