package org.mql.models;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Comment {
	private long id;
	private Member member;
	private Streaming streaming;
	
	// extra columns
	private Date date;
	private String content;
	
	public Comment() {
		super();
	}

	public Comment(Member member, Streaming streaming, Date date, String content) {
		super();
		this.member = member;
		this.streaming = streaming;
		this.date = date;
		this.content = content;
	}

	@Id
	@GeneratedValue
	@Column(name = "comment_id")
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH,
			CascadeType.DETACH })
	@JoinColumn(name = "member_id")
	public Member getMember() {
		return member;
	}
	
	public void setMember(Member member) {
		this.member = member;
	}
	
	
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH,
			CascadeType.DETACH })
	@JoinColumn(name = "streaming_id")
	public Streaming getStreaming() {
		return streaming;
	}
	
	public void setStreaming(Streaming streaming) {
		this.streaming = streaming;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
