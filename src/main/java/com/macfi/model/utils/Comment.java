package com.macfi.model.utils;

import com.macfi.model.Waiver;
import com.macfi.model.person.Person;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "comment")
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

    public Comment(String content, Person author, Comment replyTo, Waiver waiver) {
        this.content = content;
        this.author = author;
        this.replyTo = replyTo;
        this.waiver = waiver;
    }
}
