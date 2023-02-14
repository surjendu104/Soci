package com.surjendu.blog.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Post")
@NoArgsConstructor
@Getter
@Setter
public class Post {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer postId;

	@Column(name = "Post Title", length = 100, nullable = false)
	private String postTitle;

	@Column(name = "Post Content", length = 10000, nullable = false)
	private String postContent;

	@Column(name = "Post Image Name", length = 100, nullable = false)
	private String postImageName;

	@Column(name = "Post Date", length = 100, nullable = false)
	private Date postDate;
	
	@ManyToOne
	@JoinColumn(name = "category id")
	private Category category;
	
	@ManyToOne
	@JoinColumn(name = "user id")
	private User user;
	
	@OneToMany(mappedBy = "post",cascade = CascadeType.ALL)
	private Set<Comment> comments = new HashSet<>();

}
