package com.runner.web.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="users")
@Data
@NoArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable
            (name="user_role",
                    joinColumns=@JoinColumn(name="user_id", referencedColumnName="id"),
                    inverseJoinColumns=@JoinColumn(name="role_id", referencedColumnName="id")
            )
    private List<Roles> roles = new ArrayList<>();
}
