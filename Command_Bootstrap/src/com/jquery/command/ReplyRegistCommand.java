package com.jquery.command;

import java.util.Date;

import com.jquery.dto.ReplyVO;

public class ReplyRegistCommand {
	
	private int bno;
	private String replyer;
	private String replytext;
	
	public int getBno() {
		return bno;
	}
	public void setBno(int bno) {
		this.bno = bno;
	}
	public String getReplyer() {
		return replyer;
	}
	public void setReplyer(String replyer) {
		this.replyer = replyer;
	}
	public String getReplytext() {
		return replytext;
	}
	public void setReplytext(String replytext) {
		this.replytext = replytext;
	}
	@Override
	public String toString() {
		return "ReplyRegistCommand [bno=" + bno + ", replyer=" + replyer + ", replytext=" + replytext + "]";
	}
	
	public ReplyVO toReplyVO() {
		ReplyVO reply = new ReplyVO();
		reply.setBno(bno);
		reply.setReplyer(replyer);
		reply.setReplytext(replytext);
		reply.setRegdate(new Date());
		reply.setUpdatedate(new Date());
		
		return reply;
	}

	
}






