package com.lsp.web.entity;
import java.util.Date;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import com.lsp.web.genericentity.BaseEntity;

@Entity
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "t_userinfo" , indexes = {@Index(name="second_index",columnList = "mobilenumber")})

public class UserInfo extends BaseEntity {
    @Column(name = "web_source", length = 50)
    private String webSource;
    @Column(name = "sms_source", length = 50)
    private String smsSource;
    @Column(name = "source", length = 50)
    private String source;
    @Column(name = "campaign", length = 2000)
    private String campaign;
    @Column(name = "medium", length = 50)
    private String medium;
    @Column(name = "channel", length = 50)
    private String channel;
    @Column(name = "channel0", length = 50)
    private String channel0;
    @Column(name = "agent", length = 50)
    private String agent;
    @Column(name = "sub_agent", length = 50)
    private String sub_agent;
    @Column(name = "agent_user_id", columnDefinition = "INT NULL")
    private Integer agentUserId;
    @Column(name = "sub_agent_user_id", columnDefinition = "INT NULL")
    private Integer subagentUserId;
    @Column(name = "user_id", length = 250)
    private String userId;
    @Column(name = "device_id", length = 50)
    private String deviceId;
    @Column(name = "active", columnDefinition = "INT NULL default 1")
    private Integer active;
    @Column(name = "mobilenumber", length = 50)
    private String mobilenumber;
    @Column(name = "firstname", length = 50)
    private String firstname;
    @Column(name = "lastname", length = 50)
    private String lastname;
    @Column(name = "dob", length = 50)
    private String dob;
    @Column(name = "gender", length = 50)
    private String gender;
    @Column(name = "email", length = 50)
    private String email;
    @Column(name = "profession", length = 50)
    private String profession;
    @Column(name = "qualification", length = 50)
    private String qualification;
    @Column(name = "address1", length = 200)
    private String address1;
    @Column(name = "address2", length = 200)
    private String address2;
    @Column(name = "landmark", length = 50)
    private String landmark;
    @Column(name = "state", length = 50)
    private String state;
    @Column(name = "city", length = 50)
    private String city;
    @Column(name = "salary", columnDefinition = "decimal(20,2) NULL")
    private Float salary;
    @Column(name = "payment_type", columnDefinition = "INT NULL default 2")
    private Integer paymentType;
    @Column(name = "pincode", length = 50)
    private String pincode;
    @Column(name = "maritalstatus", length = 50)
    private String maritalstatus;
    @Column(name = "addresstype", length = 50)
    private String addresstype;
    @Column(name = "fathername", length = 50)
    private String fathername;
    @Column(name = "mothername", length = 50)
    private String mothername;
    @Column(name = "comments", length = 200)
    private String comments;
    @Column(name = "notice_time", columnDefinition = "datetime NULL")
    private Date noticeTime;
    @Column(name = "reg_time", columnDefinition = "datetime NULL")
    private Date regTime;
    @Column(name = "customer_id", length = 50)
    private String customerID;
    @Column(name = "status", length = 50)
    private String Status;
    @Column(name = "hubbleId", length = 200)
    private String hubbleId;
    @Column(name = "PAN", length = 200)
    private String pan;
    @Column(name = "step", columnDefinition = "INT default 0")
    private Integer step;
    @Column(name = "residence_type", length = 200)
    private String residence_type;
    @Column(name = "NOM", length = 50)
    private String nom;
    @Column(name = "company_name", length = 200)
    private String company_name;
    @Column(name = "degination", length = 200)
    private String degination;
    @Column(name = "bankname", length = 200)
    private String bankname;
    @Column(name = "bankid", length = 200)
    private String bankid;
    @Column(name = "yoe", length = 200)
    private String yoe;
    @Column(name = "netbanking", length = 200)
    private String netbanking;
    @Column(name = "emi", length = 200)
    private String emi;
    @Column(name = "loan_purpose_id", length = 200)
    private String loan_purpose_id;
    @Column(name = "officeLine1", length = 500)
    private String officeaddline1;
    @Column(name = "officeLine2", length = 200)
    private String officeaddline2;
    @Column(name = "collegename", length = 200)
    private String collegename;
    @Column(name = "workemail", length = 50)
    private String workemail;
    @Column(name = "officepincode", length = 50)
    private String officepincode;
    @Column(name = "creditprofile", length = 50)
    private String creditprofile;
    @Column(name = "hciemployetype", length = 50)
    private String hciemployetype;
    @Column(name = "Tier", columnDefinition="INT NULL")
    private Integer tier;
    @Column(name = "doj", length = 50)
    private String doj;
    @Column(name = "loanamount", length = 50)
    private String loanamount;
    @Column(name = "reference_1_name", length = 50)
    private String reference_1_name;
    @Column(name = "reference_1_relation", length = 50)
    private String reference_1_relation;
    @Column(name = "reference_1_phone", length = 50)
    private String reference_1_phone;
    @Column(name = "reference_1_addressline_1", length = 50)
    private String reference_1_addressline_1;
    @Column(name = "reference_1_addressline_2", length = 50)
    private String reference_1_addressline_2;
    @Column(name = "reference_1_pincode", length = 50)
    private String reference_1_pincode;
    @Column(name = "reference_1_residence_ownership", length = 50)
    private String reference_1_residence_ownership;
    @Column(name = "reference_2_name", length = 50)
    private String reference_2_name;
    @Column(name = "reference_2_phone", length = 50)
    private String reference_2_phone;
    @Column(name = "reference_2_relation", length = 50)
    private String reference_2_relation;
    @Column(name = "account_holder_name", length = 50)
    private String account_holder_name;
    @Column(name = "bank_type", length = 50)
    private String bank_type;
    @Column(name = "account_number", length = 50)
    private String account_number;
    @Column(name = "branch_name", length = 50)
    private String branch_name;
    @Column(name = "ifsc", length = 50)
    private String ifsc;
    @Column(name = "partnerLoanId", length = 50)
    private String partnerLoanId;
    @Column(name = "last_attribution_time")
    private Date last_attribution_time;
    @Column(name = "dpd_thirty", length = 50)
    private String dpd_thirty;
    @Column(name = "dpd_ninty", length = 50)
    private String dpd_ninty;
    @Column(name = "dpd_oneeighty", length = 50)
    private String dpd_oneeighty;
    @Column(name = "cc_present_flag", length = 50)
    private String cc_present_flag;
    @Column(name = "creditprofile_flag", length = 50)
    private String creditprofile_flag;
    
    //getters and setters
    
	public String getWebSource() {
		return webSource;
	}
	public void setWebSource(String webSource) {
		this.webSource = webSource;
	}
	public String getSmsSource() {
		return smsSource;
	}
	public void setSmsSource(String smsSource) {
		this.smsSource = smsSource;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getCampaign() {
		return campaign;
	}
	public void setCampaign(String campaign) {
		this.campaign = campaign;
	}
	public String getMedium() {
		return medium;
	}
	public void setMedium(String medium) {
		this.medium = medium;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public String getChannel0() {
		return channel0;
	}
	public void setChannel0(String channel0) {
		this.channel0 = channel0;
	}
	public String getAgent() {
		return agent;
	}
	public void setAgent(String agent) {
		this.agent = agent;
	}
	public String getSub_agent() {
		return sub_agent;
	}
	public void setSub_agent(String sub_agent) {
		this.sub_agent = sub_agent;
	}
	public Integer getAgentUserId() {
		return agentUserId;
	}
	public void setAgentUserId(Integer agentUserId) {
		this.agentUserId = agentUserId;
	}
	public Integer getSubagentUserId() {
		return subagentUserId;
	}
	public void setSubagentUserId(Integer subagentUserId) {
		this.subagentUserId = subagentUserId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	public Integer getActive() {
		return active;
	}
	public void setActive(Integer active) {
		this.active = active;
	}
	public String getMobilenumber() {
		return mobilenumber;
	}
	public void setMobilenumber(String mobilenumber) {
		this.mobilenumber = mobilenumber;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getProfession() {
		return profession;
	}
	public void setProfession(String profession) {
		this.profession = profession;
	}
	public String getQualification() {
		return qualification;
	}
	public void setQualification(String qualification) {
		this.qualification = qualification;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String getLandmark() {
		return landmark;
	}
	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public Float getSalary() {
		return salary;
	}
	public void setSalary(Float salary) {
		this.salary = salary;
	}
	public Integer getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(Integer paymentType) {
		this.paymentType = paymentType;
	}
	public String getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	public String getMaritalstatus() {
		return maritalstatus;
	}
	public void setMaritalstatus(String maritalstatus) {
		this.maritalstatus = maritalstatus;
	}
	public String getAddresstype() {
		return addresstype;
	}
	public void setAddresstype(String addresstype) {
		this.addresstype = addresstype;
	}
	public String getFathername() {
		return fathername;
	}
	public void setFathername(String fathername) {
		this.fathername = fathername;
	}
	public String getMothername() {
		return mothername;
	}
	public void setMothername(String mothername) {
		this.mothername = mothername;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public Date getNoticeTime() {
		return noticeTime;
	}
	public void setNoticeTime(Date noticeTime) {
		this.noticeTime = noticeTime;
	}
	public Date getRegTime() {
		return regTime;
	}
	public void setRegTime(Date regTime) {
		this.regTime = regTime;
	}
	public String getCustomerID() {
		return customerID;
	}
	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	public String getHubbleId() {
		return hubbleId;
	}
	public void setHubbleId(String hubbleId) {
		this.hubbleId = hubbleId;
	}
	public String getPan() {
		return pan;
	}
	public void setPan(String pan) {
		this.pan = pan;
	}
	public Integer getStep() {
		return step;
	}
	public void setStep(Integer step) {
		this.step = step;
	}
	public String getResidence_type() {
		return residence_type;
	}
	public void setResidence_type(String residence_type) {
		this.residence_type = residence_type;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getCompany_name() {
		return company_name;
	}
	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}
	public String getDegination() {
		return degination;
	}
	public void setDegination(String degination) {
		this.degination = degination;
	}
	public String getBankname() {
		return bankname;
	}
	public void setBankname(String bankname) {
		this.bankname = bankname;
	}
	public String getBankid() {
		return bankid;
	}
	public void setBankid(String bankid) {
		this.bankid = bankid;
	}
	public String getYoe() {
		return yoe;
	}
	public void setYoe(String yoe) {
		this.yoe = yoe;
	}
	public String getNetbanking() {
		return netbanking;
	}
	public void setNetbanking(String netbanking) {
		this.netbanking = netbanking;
	}
	public String getEmi() {
		return emi;
	}
	public void setEmi(String emi) {
		this.emi = emi;
	}
	public String getLoan_purpose_id() {
		return loan_purpose_id;
	}
	public void setLoan_purpose_id(String loan_purpose_id) {
		this.loan_purpose_id = loan_purpose_id;
	}
	public String getOfficeaddline1() {
		return officeaddline1;
	}
	public void setOfficeaddline1(String officeaddline1) {
		this.officeaddline1 = officeaddline1;
	}
	public String getOfficeaddline2() {
		return officeaddline2;
	}
	public void setOfficeaddline2(String officeaddline2) {
		this.officeaddline2 = officeaddline2;
	}
	public String getCollegename() {
		return collegename;
	}
	public void setCollegename(String collegename) {
		this.collegename = collegename;
	}
	public String getWorkemail() {
		return workemail;
	}
	public void setWorkemail(String workemail) {
		this.workemail = workemail;
	}
	public String getOfficepincode() {
		return officepincode;
	}
	public void setOfficepincode(String officepincode) {
		this.officepincode = officepincode;
	}
	public String getCreditprofile() {
		return creditprofile;
	}
	public void setCreditprofile(String creditprofile) {
		this.creditprofile = creditprofile;
	}
	public String getHciemployetype() {
		return hciemployetype;
	}
	public void setHciemployetype(String hciemployetype) {
		this.hciemployetype = hciemployetype;
	}
	public Integer getTier() {
		return tier;
	}
	public void setTier(Integer tier) {
		this.tier = tier;
	}
	public String getDoj() {
		return doj;
	}
	public void setDoj(String doj) {
		this.doj = doj;
	}
	public String getLoanamount() {
		return loanamount;
	}
	public void setLoanamount(String loanamount) {
		this.loanamount = loanamount;
	}
	public String getReference_1_name() {
		return reference_1_name;
	}
	public void setReference_1_name(String reference_1_name) {
		this.reference_1_name = reference_1_name;
	}
	public String getReference_1_relation() {
		return reference_1_relation;
	}
	public void setReference_1_relation(String reference_1_relation) {
		this.reference_1_relation = reference_1_relation;
	}
	public String getReference_1_phone() {
		return reference_1_phone;
	}
	public void setReference_1_phone(String reference_1_phone) {
		this.reference_1_phone = reference_1_phone;
	}
	public String getReference_1_addressline_1() {
		return reference_1_addressline_1;
	}
	public void setReference_1_addressline_1(String reference_1_addressline_1) {
		this.reference_1_addressline_1 = reference_1_addressline_1;
	}
	public String getReference_1_addressline_2() {
		return reference_1_addressline_2;
	}
	public void setReference_1_addressline_2(String reference_1_addressline_2) {
		this.reference_1_addressline_2 = reference_1_addressline_2;
	}
	public String getReference_1_pincode() {
		return reference_1_pincode;
	}
	public void setReference_1_pincode(String reference_1_pincode) {
		this.reference_1_pincode = reference_1_pincode;
	}
	public String getReference_1_residence_ownership() {
		return reference_1_residence_ownership;
	}
	public void setReference_1_residence_ownership(String reference_1_residence_ownership) {
		this.reference_1_residence_ownership = reference_1_residence_ownership;
	}
	public String getReference_2_name() {
		return reference_2_name;
	}
	public void setReference_2_name(String reference_2_name) {
		this.reference_2_name = reference_2_name;
	}
	public String getReference_2_phone() {
		return reference_2_phone;
	}
	public void setReference_2_phone(String reference_2_phone) {
		this.reference_2_phone = reference_2_phone;
	}
	public String getReference_2_relation() {
		return reference_2_relation;
	}
	public void setReference_2_relation(String reference_2_relation) {
		this.reference_2_relation = reference_2_relation;
	}
	public String getAccount_holder_name() {
		return account_holder_name;
	}
	public void setAccount_holder_name(String account_holder_name) {
		this.account_holder_name = account_holder_name;
	}
	public String getBank_type() {
		return bank_type;
	}
	public void setBank_type(String bank_type) {
		this.bank_type = bank_type;
	}
	public String getAccount_number() {
		return account_number;
	}
	public void setAccount_number(String account_number) {
		this.account_number = account_number;
	}
	public String getBranch_name() {
		return branch_name;
	}
	public void setBranch_name(String branch_name) {
		this.branch_name = branch_name;
	}
	public String getIfsc() {
		return ifsc;
	}
	public void setIfsc(String ifsc) {
		this.ifsc = ifsc;
	}
	public String getPartnerLoanId() {
		return partnerLoanId;
	}
	public void setPartnerLoanId(String partnerLoanId) {
		this.partnerLoanId = partnerLoanId;
	}
	public Date getLast_attribution_time() {
		return last_attribution_time;
	}
	public void setLast_attribution_time(Date last_attribution_time) {
		this.last_attribution_time = last_attribution_time;
	}
	public String getDpd_thirty() {
		return dpd_thirty;
	}
	public void setDpd_thirty(String dpd_thirty) {
		this.dpd_thirty = dpd_thirty;
	}
	public String getDpd_ninty() {
		return dpd_ninty;
	}
	public void setDpd_ninty(String dpd_ninty) {
		this.dpd_ninty = dpd_ninty;
	}
	public String getDpd_oneeighty() {
		return dpd_oneeighty;
	}
	public void setDpd_oneeighty(String dpd_oneeighty) {
		this.dpd_oneeighty = dpd_oneeighty;
	}
	public String getCc_present_flag() {
		return cc_present_flag;
	}
	public void setCc_present_flag(String cc_present_flag) {
		this.cc_present_flag = cc_present_flag;
	}
	public String getCreditprofile_flag() {
		return creditprofile_flag;
	}
	public void setCreditprofile_flag(String creditprofile_flag) {
		this.creditprofile_flag = creditprofile_flag;
	}
    
}