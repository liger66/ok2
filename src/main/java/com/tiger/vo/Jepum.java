package com.tiger.vo;

import javax.validation.constraints.Pattern;

public class Jepum {
	@Pattern(regexp="[가-힣]{2, 5}", message="한글 2 ~ 5")
	private String jepum;
	private String brand,giYY,season,pum,jepumGb,majinGb,giGb,saipPanYN;
	private String jepumNm,fixDt,sojeGb,priceGb,sengHt,image,sizGroup,inUser;
	private int giWonjaje,giBujaje,giImbong,giSobi,wonjaje,bujaje,imbong,won,sobi,giQty;
	private String giIpgoDt,fIpgoDt,lIpgoDt,giPanDt,fPanDt,lPanDt,inDate;
	
	public String getJepum() {
		return jepum;
	}
	public void setJepum(String jepum) {
		this.jepum = jepum;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getGiYY() {
		return giYY;
	}
	public void setGiYY(String giYY) {
		this.giYY = giYY;
	}
	public String getSeason() {
		return season;
	}
	public void setSeason(String season) {
		this.season = season;
	}
	public String getPum() {
		return pum;
	}
	public void setPum(String pum) {
		this.pum = pum;
	}
	public String getJepumGb() {
		return jepumGb;
	}
	public void setJepumGb(String jepumGb) {
		this.jepumGb = jepumGb;
	}
	public String getMajinGb() {
		return majinGb;
	}
	public void setMajinGb(String majinGb) {
		this.majinGb = majinGb;
	}
	public String getGiGb() {
		return giGb;
	}
	public void setGiGb(String giGb) {
		this.giGb = giGb;
	}
	public String getSaipPanYN() {
		return saipPanYN;
	}
	public void setSaipPanYN(String saipPanYN) {
		this.saipPanYN = saipPanYN;
	}
	public String getJepumNm() {
		return jepumNm;
	}
	public void setJepumNm(String jepumNm) {
		this.jepumNm = jepumNm;
	}
	public String getFixDt() {
		return fixDt;
	}
	public void setFixDt(String fixDt) {
		this.fixDt = fixDt;
	}
	public String getSojeGb() {
		return sojeGb;
	}
	public void setSojeGb(String sojeGb) {
		this.sojeGb = sojeGb;
	}
	public String getPriceGb() {
		return priceGb;
	}
	public void setPriceGb(String priceGb) {
		this.priceGb = priceGb;
	}
	public String getSengHt() {
		return sengHt;
	}
	public void setSengHt(String sengHt) {
		this.sengHt = sengHt;
	}
	
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getSizGroup() {
		return sizGroup;
	}
	public void setSizGroup(String sizGroup) {
		this.sizGroup = sizGroup;
	}
	public String getInUser() {
		return inUser;
	}
	public void setInUser(String inUser) {
		this.inUser = inUser;
	}
	public int getGiWonjaje() {
		return giWonjaje;
	}
	public void setGiWonjaje(int giWonjaje) {
		this.giWonjaje = giWonjaje;
	}
	public int getGiBujaje() {
		return giBujaje;
	}
	public void setGiBujaje(int giBujaje) {
		this.giBujaje = giBujaje;
	}
	public int getGiImbong() {
		return giImbong;
	}
	public void setGiImbong(int giImbong) {
		this.giImbong = giImbong;
	}
	public int getGiSobi() {
		return giSobi;
	}
	public void setGiSobi(int giSobi) {
		this.giSobi = giSobi;
	}
	public int getWonjaje() {
		return wonjaje;
	}
	public void setWonjaje(int wonjaje) {
		this.wonjaje = wonjaje;
	}
	public int getBujaje() {
		return bujaje;
	}
	public void setBujaje(int bujaje) {
		this.bujaje = bujaje;
	}
	public int getImbong() {
		return imbong;
	}
	public void setImbong(int imbong) {
		this.imbong = imbong;
	}
	public int getWon() {
		return won;
	}
	public void setWon(int won) {
		this.won = won;
	}
	public int getSobi() {
		return sobi;
	}
	public void setSobi(int sobi) {
		this.sobi = sobi;
	}
	public int getGiQty() {
		return giQty;
	}
	public String getGiIpgoDt() {
		return giIpgoDt;
	}
	public void setGiIpgoDt(String giIpgoDt) {
		this.giIpgoDt = giIpgoDt;
	}
	public String getlIpgoDt() {
		return lIpgoDt;
	}
	public void setlIpgoDt(String lIpgoDt) {
		this.lIpgoDt = lIpgoDt;
	}
	public String getGiPanDt() {
		return giPanDt;
	}
	public void setGiPanDt(String giPanDt) {
		this.giPanDt = giPanDt;
	}
	public String getfPanDt() {
		return fPanDt;
	}
	public void setfPanDt(String fPanDt) {
		this.fPanDt = fPanDt;
	}
	public String getlPanDt() {
		return lPanDt;
	}
	public void setlPanDt(String lPanDt) {
		this.lPanDt = lPanDt;
	}
	public String getInDate() {
		return inDate;
	}
	public void setInDate(String inDate) {
		this.inDate = inDate;
	}
	public void setGiQty(int giQty) {
		this.giQty = giQty;
	}
	public String getfIpgoDt() {
		return fIpgoDt;
	}
	public void setfIpgoDt(String fIpgoDt) {
		this.fIpgoDt = fIpgoDt;
	}	
}
