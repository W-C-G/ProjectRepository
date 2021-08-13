package com.example.demo.vo;

public class QAVO {
	private int num;
    private String title;
    private String id;
    private String wdate;
    private String contents;
    private int topnum;
	
    public QAVO() {}
    
    public QAVO(int num, String title, String id, String wdate, String contents, int topnum) {
		super();
		this.num = num;
		this.title = title;
		this.id = id;
		this.wdate = wdate;
		this.contents = contents;
		this.topnum = topnum;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getWdate() {
		return wdate;
	}
	public void setWdate(String wdate) {
		this.wdate = wdate;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public int getTopnum() {
		return topnum;
	}
	public void setTopnum(int topnum) {
		this.topnum = topnum;
	}
    
    
}
