package org.example.watchstack.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "custom_lists")
public class CustomList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference("user-customlist")
    private User user;

    @Column(nullable = false)
    private String name;

    private String description;

    @OneToMany(mappedBy = "customList", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("customlist-listitem")
    private Set<ListItem> items = new HashSet<>();
}