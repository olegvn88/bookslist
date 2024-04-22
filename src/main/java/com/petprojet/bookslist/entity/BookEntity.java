package com.petprojet.bookslist.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "book")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "price")
    private Double price;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "book_author",
            joinColumns = {
                    @JoinColumn(name = "book_id", referencedColumnName = "id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "author_id", referencedColumnName = "id")
            }
    )
    private Set<AuthorEntity> assignedAuthors = new HashSet<>();
}
