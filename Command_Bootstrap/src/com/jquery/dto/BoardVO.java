package com.jquery.dto;

import java.util.Date;
import java.util.List;

public class BoardVO {
	private int bno;          // 게시판번호
	private String title;
	private String writer;
	private String content;
	private int viewcnt;      // 조회수
	private Date regDate;     // 등록날짜
	private Date updatedate;  // 수정날짜
	
	List<AttachVO> attachList; //첨부파일
	
	private int replycnt; // 댓글 개수
	
	
	public BoardVO() {}
	public BoardVO(int bno, String title, String writer, String content, int viewcnt, Date regDate, Date updatedate) {
		super();
		this.bno = bno;
		this.title = title;
		this.writer = writer;
		this.content = content;
		this.viewcnt = viewcnt;
		this.regDate = regDate;
		this.updatedate = updatedate;
	}
	
	public BoardVO(int bno, String title, String writer, String content, int viewcnt, Date regDate, Date updatedate,
			List<AttachVO> attachList, int replycnt) {
		super();
		this.bno = bno;
		this.title = title;
		this.writer = writer;
		this.content = content;
		this.viewcnt = viewcnt;
		this.regDate = regDate;
		this.updatedate = updatedate;
		this.attachList = attachList;
		this.replycnt = replycnt;
	}
	
	
	public int getReplycnt() {
		return replycnt;
	}
	public void setReplycnt(int replycnt) {
		this.replycnt = replycnt;
	}
	public int getBno() {
		return bno;
	}
	public void setBno(int bno) {
		this.bno = bno;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getViewcnt() {
		return viewcnt;
	}
	public void setViewcnt(int viewcnt) {
		this.viewcnt = viewcnt;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public Date getUpdatedate() {
		return updatedate;
	}
	public void setUpdatedate(Date updatedate) {
		this.updatedate = updatedate;
	}
	public List<AttachVO> getAttachList() {
		return attachList;
	}
	public void setAttachList(List<AttachVO> attachList) {
		this.attachList = attachList;
	}
	@Override
	public String toString() {
		return "BoardVO [bno=" + bno + ", title=" + title + ", writer=" + writer + ", content=" + content + ", viewcnt="
				+ viewcnt + ", regDate=" + regDate + ", updatedate=" + updatedate + ", attachList=" + attachList
				+ ", replycnt=" + replycnt + "]";
	}
	
}
