package com.shdjrmyy.qgw.CompanyProject.BaseFolder;




public class AppBean {
	/*
	 * token验证
	 */
	private String id;
	private String appKey = null;
	private String timeSpan = null;
	private Integer appType = -1;//1患者端 2医生端
	private Integer mobileType = 1;//1android,2ios
	private String pageNo;//分页 从 开始
	private String pageCount;//分页 到
	private String sex;//性别
	private String name;
	private String age;//年龄
	private String idCard;//身份证
	private String mobile;//手机号
	private String national;//民族
	private String passwordnew;//修改密码
	private String diseasesID;//病种id
	private String currentPage;//分页 当前页面
	private String pageStr;//分页
	private String optionTag;//修改或添加字段，用update  或者 insert表示
	private String moduleCode;//模型id
	private String num;//患者编号
	private String hospitalID; // 医院id
	private String brithday; // 生日
	private int degree; // 学历
	private String provinceID; // 省份id
	private String cityID; // 城市id
	private String regionID; // 区
	private String address; // 
	private String createTime = DateTimeUtil.getStringDateOne(); // 添加时间; // 
	private String updateTime = DateTimeUtil.getStringDateOne(); // 修改时间; //
	private int delFlag = 0; // 删除标志
	private String delTime; // 删除时间
	private String remark; // 备注
	private String ids;//id列表
	private String createUser;//创建用户 
	private String updateUser;//修改用户
	private String descript;//描述
	private int followMaxNum;//最多访问次数
	private int followMaxYear;//最长访问时间年
	private int followMaxMonth;//最长访问时间月
	private int followMaxDay;//最长访问时间日
	private int followDayNum;//提前几天提醒
	private String followName;//随访名称
	private String followDescript;//随访描述
	private String endTime = DateTimeUtil.getStringDateOne();//终止时间
	private String endReason;//终止原因
	private String followMan;//随访人员
	private String hospitalNum; // 入院号
	private String inTime; // 入院时间
	private String outTime; // 出院时间编号
	private String diagnosis; // 诊断
	private String patientSay; // 主诉
	private String procedure; // 手术过程
	private String treat; // 诊疗特点
	private String LoginName;//登录名
	private int UserID; // 用户id
	private int userID;
	private int RoleID;//角色id
	private int PhotoID; // 图片id
	private String PhotoUrl; // 图片地址
	private String FirstLoginTime; // 是否登录激活
	private String title;//标题
	private String calendardescribe;//备忘录描述
	private String start = DateTimeUtil.getStringDateOne();//开始时间
	private String end = DateTimeUtil.getStringDateOne();//结束时间
	private String fieldControlName; // 受控项目
	
	/*
	 * 用户登录
	 */
	private String username = null;
	private String password = null;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the appKey
	 */
	public String getAppKey() {
		return appKey;
	}
	/**
	 * @param appKey the appKey to set
	 */
	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}
	/**
	 * @return the timeSpan
	 */
	public String getTimeSpan() {
		return timeSpan;
	}
	/**
	 * @param timeSpan the timeSpan to set
	 */
	public void setTimeSpan(String timeSpan) {
		this.timeSpan = timeSpan;
	}
	/**
	 * @return the appType
	 */
	public Integer getAppType() {
		return appType;
	}
	/**
	 * @param appType the appType to set
	 */
	public void setAppType(Integer appType) {
		this.appType = appType;
	}
	/**
	 * @return the mobileType
	 */
	public Integer getMobileType() {
		return mobileType;
	}
	/**
	 * @param mobileType the mobileType to set
	 */
	public void setMobileType(Integer mobileType) {
		this.mobileType = mobileType;
	}
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public int getPhotoID() {
		return PhotoID;
	}
	public void setPhotoID(int photoID) {
		PhotoID = photoID;
	}
	public String getPhotoUrl() {
		return PhotoUrl;
	}
	public void setPhotoUrl(String photoUrl) {
		PhotoUrl = photoUrl;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getNational() {
		return national;
	}
	public void setNational(String national) {
		this.national = national;
	}
	
	public String getPasswordnew() {
		return passwordnew;
	}
	public void setPasswordnew(String passwordnew) {
		this.passwordnew = passwordnew;
	}
	public String getDiseasesID() {
		return diseasesID;
	}
	public void setDiseasesID(String diseasesID) {
		this.diseasesID = diseasesID;
	}
	
	public String getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(String currentPage) {
		this.currentPage = currentPage;
	}
	public String getOptionTag() {
		return optionTag;
	}
	public void setOptionTag(String optionTag) {
		this.optionTag = optionTag;
	}
	public String getModuleCode() {
		return moduleCode;
	}
	public void setModuleCode(String moduleCode) {
		this.moduleCode = moduleCode;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getHospitalID() {
		return hospitalID;
	}
	public void setHospitalID(String hospitalID) {
		this.hospitalID = hospitalID;
	}
	public String getBrithday() {
		return brithday;
	}
	public void setBrithday(String brithday) {
		this.brithday = brithday;
	}
	public int getDegree() {
		return degree;
	}
	public void setDegree(int degree) {
		this.degree = degree;
	}
	public String getProvinceID() {
		return provinceID;
	}
	public void setProvinceID(String provinceID) {
		this.provinceID = provinceID;
	}
	public String getCityID() {
		return cityID;
	}
	public void setCityID(String cityID) {
		this.cityID = cityID;
	}
	public String getRegionID() {
		return regionID;
	}
	public void setRegionID(String regionID) {
		this.regionID = regionID;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public int getDelFlag() {
		return delFlag;
	}
	public void setDelFlag(int delFlag) {
		this.delFlag = delFlag;
	}
	public String getDelTime() {
		return delTime;
	}
	public void setDelTime(String delTime) {
		this.delTime = delTime;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	public String getPageStr() {
		return pageStr;
	}
	public void setPageStr(String pageStr) {
		this.pageStr = pageStr;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public String getUpdateUser() {
		return updateUser;
	}
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
	public String getDescript() {
		return descript;
	}
	public void setDescript(String descript) {
		this.descript = descript;
	}
	public int getFollowMaxNum() {
		return followMaxNum;
	}
	public void setFollowMaxNum(int followMaxNum) {
		this.followMaxNum = followMaxNum;
	}
	public int getFollowMaxYear() {
		return followMaxYear;
	}
	public void setFollowMaxYear(int followMaxYear) {
		this.followMaxYear = followMaxYear;
	}
	public int getFollowMaxMonth() {
		return followMaxMonth;
	}
	public void setFollowMaxMonth(int followMaxMonth) {
		this.followMaxMonth = followMaxMonth;
	}
	public int getFollowMaxDay() {
		return followMaxDay;
	}
	public void setFollowMaxDay(int followMaxDay) {
		this.followMaxDay = followMaxDay;
	}
	public int getFollowDayNum() {
		return followDayNum;
	}
	public void setFollowDayNum(int followDayNum) {
		this.followDayNum = followDayNum;
	}
	public String getFollowName() {
		return followName;
	}
	public void setFollowName(String followName) {
		this.followName = followName;
	}
	public String getFollowDescript() {
		return followDescript;
	}
	public void setFollowDescript(String followDescript) {
		this.followDescript = followDescript;
	}
	
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getEndReason() {
		return endReason;
	}
	public void setEndReason(String endReason) {
		this.endReason = endReason;
	}
	public String getFollowMan() {
		return followMan;
	}
	public void setFollowMan(String followMan) {
		this.followMan = followMan;
	}
	public String getPageNo() {
		return pageNo;
	}
	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}
	public String getPageCount() {
		return pageCount;
	}
	public void setPageCount(String pageCount) {
		this.pageCount = pageCount;
	}
	public String getHospitalNum() {
		return hospitalNum;
	}
	public void setHospitalNum(String hospitalNum) {
		this.hospitalNum = hospitalNum;
	}
	public String getInTime() {
		return inTime;
	}
	public void setInTime(String inTime) {
		this.inTime = inTime;
	}
	public String getOutTime() {
		return outTime;
	}
	public void setOutTime(String outTime) {
		this.outTime = outTime;
	}
	public String getDiagnosis() {
		return diagnosis;
	}
	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}
	public String getPatientSay() {
		return patientSay;
	}
	public void setPatientSay(String patientSay) {
		this.patientSay = patientSay;
	}
	public String getProcedure() {
		return procedure;
	}
	public void setProcedure(String procedure) {
		this.procedure = procedure;
	}
	public String getTreat() {
		return treat;
	}
	public void setTreat(String treat) {
		this.treat = treat;
	}
	public String getLoginName() {
		return LoginName;
	}
	public void setLoginName(String loginName) {
		LoginName = loginName;
	}
	public int getUserID() {
		return UserID;
	}
	public void setUserID(int userID) {
		UserID = userID;
	}
	public int getRoleID() {
		return RoleID;
	}
	public void setRoleID(int roleID) {
		RoleID = roleID;
	}
	public String getFirstLoginTime() {
		return FirstLoginTime;
	}
	public void setFirstLoginTime(String firstLoginTime) {
		FirstLoginTime = firstLoginTime;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCalendardescribe() {
		return calendardescribe;
	}
	public void setCalendardescribe(String calendardescribe) {
		this.calendardescribe = calendardescribe;
	}
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	public String getEnd() {
		return end;
	}
	public void setEnd(String end) {
		this.end = end;
	}
	public String getFieldControlName() {
		return fieldControlName;
	}
	public void setFieldControlName(String fieldControlName) {
		this.fieldControlName = fieldControlName;
	}
	
}
