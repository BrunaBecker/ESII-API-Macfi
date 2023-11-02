package com.macfi.model.utils;

import com.macfi.model.Waiver;
import com.macfi.model.person.Person;
import jakarta.persistence.*;

@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    @ManyToOne
    @JoinColumn(name = "author_id")
    private Person author;
    @ManyToOne
    @JoinColumn(name = "parent_comment_id")
    private Comment replyTo;

    @OneToOne
    private Waiver waiver;
}
