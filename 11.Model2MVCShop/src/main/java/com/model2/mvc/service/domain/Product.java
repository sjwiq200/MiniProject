package com.model2.mvc.service.domain;



import java.sql.Date;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;


public class Product {

	private MultipartFile[] files;
	private String fileName;
    private String manuDate;
    private int price;
    private String prodDetail;
    private String prodName;
    private int prodNo;
    private Date regDate;
    private String proTranCode;
    private int prodCount;

    public Product(){
    }

    public String getProTranCode() {
        return proTranCode;
    }
    public void setProTranCode(String proTranCode) {
        this.proTranCode = proTranCode;
    }
    public String getFileName() {
        return fileName;
    }
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    public String getManuDate() {
        return manuDate;
    }
    public void setManuDate(String manuDate) {
        this.manuDate = manuDate;
    }
    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }
    public String getProdDetail() {
        return prodDetail;
    }
    public void setProdDetail(String prodDetail) {
        this.prodDetail = prodDetail;
    }
    public String getProdName() {
        return prodName;
    }
    public void setProdName(String prodName) {
        this.prodName = prodName;
    }
    public int getProdNo() {
        return prodNo;
    }
    public void setProdNo(int prodNo) {
        this.prodNo = prodNo;
    }
    public Date getRegDate() {
        return regDate;
    }
    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    public int getProdCount() {
		return prodCount;
	}

	public void setProdCount(int prodCount) {
		this.prodCount = prodCount;
	}
	
	public MultipartFile[] getFiles() {
		return files;
	}

	public void setFiles(MultipartFile[] files) {
		this.files = files;
	}

	@Override
	public String toString() {
		return "Product [files=" + Arrays.toString(files) + ", fileName=" + fileName + ", manuDate=" + manuDate
				+ ", price=" + price + ", prodDetail=" + prodDetail + ", prodName=" + prodName + ", prodNo=" + prodNo
				+ ", regDate=" + regDate + ", proTranCode=" + proTranCode + ", prodCount=" + prodCount + "]";
	}


	
}
