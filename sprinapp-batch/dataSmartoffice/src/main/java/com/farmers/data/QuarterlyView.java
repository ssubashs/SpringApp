package com.farmers.data;


public class QuarterlyView {
	

		private String agentnum;

		private Integer year;

		private Integer quarter;

		private String datacategory;

		private String autoagentdata;	

		private String autodistrictdata;
		
		private String autostatedata;
		
		private String fireagentdata;	

		private String firedistrictdata;
		
		private String firestatedata;

		private String pastautoagentdata;

		private String pastautodistrictdata;

		private String pastautostatedata;
		
		private String pastfireagentdata;

		private String pastfiredistrictdata;

		private String pastfirestatedata;

		private String focusarea;
		
		

		public QuarterlyView(String agentnum1,Integer year1,Integer quarter1,String datacategory1)
		{
			super();
			this.agentnum = agentnum1;
			this.year = year1;
			this.quarter = quarter1;
			this.setDatacategory(datacategory1);
					
		}
		
		public String getAgentnum() {
			return agentnum;
		}

		public void setAgentnum(String agentnum) {
			this.agentnum = agentnum;
		}

		public Integer getYear() {
			return year;
		}

		public void setYear(Integer year) {
			this.year = year;
		}

		public Integer getQuarter() {
			return quarter;
		}

		public void setQuarter(Integer quarter) {
			this.quarter = quarter;
		}

		public String getDatacategory() {
			return datacategory;
		}

		public void setDatacategory(String datacategory) {
			switch(datacategory){
			case "Quotes":
				{
					this.datacategory = "Quotes";
					break;
				}
			case "Policies":
				{
					this.datacategory = "Policies in force";
					break;
				}		
			case "Retention":
				{
					this.datacategory = "Retention";
					break;
				}
			case "Close rate":
			{
				this.datacategory = "Close rate";
				break;
			}
			case "New Business":
			{
				this.datacategory = "New business/households";
				break;
			}
			default:
			{
				this.datacategory = datacategory;
			}
			
				
			}
			this.datacategory = datacategory;
		}

		public String getAutoagentdata() {
			return autoagentdata;
		}

		public void setAutoagentdata(String autoagentdata) {
			this.autoagentdata = autoagentdata;
		}

		public String getAutodistrictdata() {
			return autodistrictdata;
		}

		public void setAutodistrictdata(String autodistrictdata) {
			this.autodistrictdata = autodistrictdata;
		}

		public String getAutostatedata() {
			return autostatedata;
		}

		public void setAutostatedata(String autostatedata) {
			this.autostatedata = autostatedata;
		}

		public String getFireagentdata() {
			return fireagentdata;
		}

		public void setFireagentdata(String fireagentdata) {
			this.fireagentdata = fireagentdata;
		}

		public String getFiredistrictdata() {
			return firedistrictdata;
		}

		public void setFiredistrictdata(String firedistrictdata) {
			this.firedistrictdata = firedistrictdata;
		}

		public String getFirestatedata() {
			return firestatedata;
		}

		public void setFirestatedata(String firestatedata) {
			this.firestatedata = firestatedata;
		}

		public String getPastautoagentdata() {
			return pastautoagentdata;
		}

		public void setPastautoagentdata(String pastautoagentdata) {
			this.pastautoagentdata = pastautoagentdata;
		}

		public String getPastautodistrictdata() {
			return pastautodistrictdata;
		}

		public void setPastautodistrictdata(String pastautodistrictdata) {
			this.pastautodistrictdata = pastautodistrictdata;
		}

		public String getPastautostatedata() {
			return pastautostatedata;
		}

		public void setPastautostatedata(String pastautostatedata) {
			this.pastautostatedata = pastautostatedata;
		}

		public String getPastfireagentdata() {
			return pastfireagentdata;
		}

		public void setPastfireagentdata(String pastfireagentdata) {
			this.pastfireagentdata = pastfireagentdata;
		}

		public String getPastfiredistrictdata() {
			return pastfiredistrictdata;
		}

		public void setPastfiredistrictdata(String pastfiredistrictdata) {
			this.pastfiredistrictdata = pastfiredistrictdata;
		}

		public String getPastfirestatedata() {
			return pastfirestatedata;
		}

		public void setPastfirestatedata(String pastfirestatedata) {
			this.pastfirestatedata = pastfirestatedata;
		}

		
		
		public String getFocusarea() {
			return focusarea;
		}

		public void setFocusarea(String focusarea) {
			this.focusarea = focusarea;
		}

	

}
