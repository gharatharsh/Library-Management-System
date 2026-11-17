//package com.lms.entity;
//
//import java.time.LocalDateTime;
//
//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.ManyToMany;
//import jakarta.persistence.ManyToOne;
//import lombok.Getter;
//import lombok.Setter;
//
//@Entity
//@Getter
//@Setter
//public class Book {
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Integer bookId;
//	@Column(nullable = false)
//	private String title;
//	@Column(nullable = false)
//	private String author;
//	@Column(nullable = false)
//	private String category;
//	private int copies;
//	private LocalDateTime borrowTime;
//	private LocalDateTime returnTime;
//	
//	// has - a 
//	@ManyToMany
//	private User user;
	
	// Many books can belong to one user
//	@ManyToOne
//	@JoinColumn(name = "user_id")
//	private User user;	
// }



package com.lms.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bookId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    private String category;

    private int copies;

    private LocalDateTime borrowTime;

    private LocalDateTime returnTime;

    @ManyToMany
    private List<User> user;
}
