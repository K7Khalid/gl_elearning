package org.mql.models;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Comment {
	private long id;
	private Member member;
	private Streaming streaming;
	/*
	// extra columns
	private Date date;
	*/
	// to avoid problems during presentation in comments & ajax
	String dateText;
	
	@Column(columnDefinition="TEXT")
	private String content;
	
	public Comment() {
		super();
	}

	public Comment(Member member, Streaming streaming, String content) {
		super();
		this.member = member;
		this.streaming = streaming;
		this.content = content;
		setDateText();
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
	
	@JsonIgnore
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH,
			CascadeType.DETACH })
	@JoinColumn(name = "streaming_id")
	public Streaming getStreaming() {
		return streaming;
	}
	
	public void setStreaming(Streaming streaming) {
		this.streaming = streaming;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	

	public String getDateText() {
		return dateText;
	}

	public void setDateText(String dateText) {
		this.dateText = dateText;
	}
	
	public void setDateText() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		this.dateText = format.format(new Date());
	}

}
