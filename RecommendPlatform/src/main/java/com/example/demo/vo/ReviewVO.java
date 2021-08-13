package com.example.demo.vo;

public class ReviewVO {
	private int res_num;
    private String res_name;
    private String res_subclass;
    private String res_score;
    private String res_address;
    private String res_review;
    private String res_review_point;
    private int uno;
    
   	public ReviewVO() {}
    
	public ReviewVO(int res_num, String res_name, String res_subclass, String res_score, String res_address,
			String res_review, String res_review_point, int uno) {
		super();
		this.res_num = res_num;
		this.res_name = res_name;
		this.res_subclass = res_subclass;
		this.res_score = res_score;
		this.res_address = res_address;
		this.res_review = res_review;
		this.res_review_point = res_review_point;
		this.uno = uno;
	}

	public int getRes_num() {
		return res_num;
	}

	public void setRes_num(int res_num) {
		this.res_num = res_num;
	}

	public String getRes_name() {
		return res_name;
	}

	public void setRes_name(String res_name) {
		this.res_name = res_name;
	}

	public String getRes_subclass() {
		return res_subclass;
	}

	public void setRes_subclass(String res_subclass) {
		this.res_subclass = res_subclass;
	}

	public String getRes_score() {
		return res_score;
	}

	public void setRes_score(String res_score) {
		this.res_score = res_score;
	}

	public String getRes_address() {
		return res_address;
	}

	public void setRes_address(String res_address) {
		this.res_address = res_address;
	}

	public String getRes_review() {
		return res_review;
	}

	public void setRes_review(String res_review) {
		this.res_review = res_review;
	}
    
	public int getUno() {
		return uno;
	}

	public void setUno(int uno) {
		this.uno = uno;
	}

	public String getRes_review_point() {
		return res_review_point;
	}

	public void setRes_review_point(String res_review_point) {
		this.res_review_point = res_review_point;
	}


}
