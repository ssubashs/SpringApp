package com.farmers.data;

public class PerformanceData {
	
	 private String lob;
	
	 private Long newbusiness;

	 private Long pif;

	 private Long quotes;
	
	 private Long ytdnewbusiness;

	 private Long ytdpif;

	 private Long ytdquotes;

	public String getLob() {
		return lob;
	}

	public void setLob(String lob) {
		this.lob = lob;
	}

	public Long getNewbusiness() {
		return newbusiness;
	}

	public void setNewbusiness(Long newbusiness) {
		this.newbusiness = newbusiness;
	}

	public Long getPif() {
		return pif;
	}

	public void setPif(Long pif) {
		this.pif = pif;
	}

	public Long getQuotes() {
		return quotes;
	}

	public void setQuotes(Long quotes) {
		this.quotes = quotes;
	}

	public Long getYtdnewbusiness() {
		return ytdnewbusiness;
	}

	public void setYtdnewbusiness(Long ytdnewbusiness) {
		this.ytdnewbusiness = ytdnewbusiness;
	}

	public Long getYtdpif() {
		return ytdpif;
	}

	public void setYtdpif(Long ytdpif) {
		this.ytdpif = ytdpif;
	}

	public Long getYtdquotes() {
		return ytdquotes;
	}

	public void setYtdquotes(Long ytdquotes) {
		this.ytdquotes = ytdquotes;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((lob == null) ? 0 : lob.hashCode());
		result = prime * result
				+ ((newbusiness == null) ? 0 : newbusiness.hashCode());
		result = prime * result + ((pif == null) ? 0 : pif.hashCode());
		result = prime * result + ((quotes == null) ? 0 : quotes.hashCode());
		result = prime * result
				+ ((ytdnewbusiness == null) ? 0 : ytdnewbusiness.hashCode());
		result = prime * result + ((ytdpif == null) ? 0 : ytdpif.hashCode());
		result = prime * result
				+ ((ytdquotes == null) ? 0 : ytdquotes.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PerformanceData other = (PerformanceData) obj;
		if (lob == null) {
			if (other.lob != null)
				return false;
		} else if (!lob.equals(other.lob))
			return false;
		if (newbusiness == null) {
			if (other.newbusiness != null)
				return false;
		} else if (!newbusiness.equals(other.newbusiness))
			return false;
		if (pif == null) {
			if (other.pif != null)
				return false;
		} else if (!pif.equals(other.pif))
			return false;
		if (quotes == null) {
			if (other.quotes != null)
				return false;
		} else if (!quotes.equals(other.quotes))
			return false;
		if (ytdnewbusiness == null) {
			if (other.ytdnewbusiness != null)
				return false;
		} else if (!ytdnewbusiness.equals(other.ytdnewbusiness))
			return false;
		if (ytdpif == null) {
			if (other.ytdpif != null)
				return false;
		} else if (!ytdpif.equals(other.ytdpif))
			return false;
		if (ytdquotes == null) {
			if (other.ytdquotes != null)
				return false;
		} else if (!ytdquotes.equals(other.ytdquotes))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PerformanceData [lob=" + lob + ", newbusiness=" + newbusiness
				+ ", pif=" + pif + ", quotes=" + quotes + ", ytdnewbusiness="
				+ ytdnewbusiness + ", ytdpif=" + ytdpif + ", ytdquotes="
				+ ytdquotes + "]";
	}
	
	
	
	
	
}
