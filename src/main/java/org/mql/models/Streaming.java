package org.mql.models;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "streaming")
public class Streaming {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	@Column(name = "id")
	private int id;

	@Column(name = "title")
	private String title;

	@Column(name = "url")
	private String url;

	@Column(name = "TimeStarted")
	private String TimeStarted;

	@Column(name = "duration")
	private String duration;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "mod_id")
	private Module module;
	
	
	/*  Comments */
	@OneToMany (mappedBy = "streaming", cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH,
			CascadeType.DETACH } )
	private List<Comment> comments = new Vector<Comment>();
	
	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	
	public List<Comment> getComments() {
		return comments;
	}
	
	public void addComment(Comment comment) {
		comment.setStreaming(this);
		System.out.println("Comment : "+comment.getStreaming());
		comments.add(comment);
	}

	/*  */
	public Streaming() {

	}

	public Streaming(String title, String url, String timeStarted, String duration) {
		super();
		this.title = title;
		this.url = url;
		this.TimeStarted = timeStarted;
		this.duration = duration;
	}

	public String getDuration() {
		return duration;
	}

	public int getId() {
		return id;
	}

	public Module getModule() {
		return module;
	}

	public String getTimeStarted() {
		return TimeStarted;
	}

	public String getTitle() {
		return title;
	}

	public String getUrl() {
		return url;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setModule(Module module) {
		this.module = module;
	}

	public void setTimeStarted(String timeStarted) {
		this.TimeStarted = timeStarted;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "Streaming title : " + title;
	}

}
