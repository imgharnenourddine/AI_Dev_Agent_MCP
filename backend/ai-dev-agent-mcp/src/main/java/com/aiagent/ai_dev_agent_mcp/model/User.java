package com.aiagent.ai_dev_agent_mcp.model;
import lombok.*;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Entity
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Table(name="users")

public class User implements UserDetails{
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        @Column(nullable=false)
        private String nom;
        @Column(nullable=false)
        private String prenom;
        @Column(nullable=false,unique = true)
        private String email;
        @Column(nullable = false)
        private  String password;
        @Enumerated(EnumType.STRING)
        @Column(nullable = false)
        private Role role;
        @Column(name="created_at")
        private LocalDateTime createdAt;
        @Column(name="updated_at")
        private LocalDateTime updatedAt;
        @Column (name="image_path")
        private String imagePath;
        @PrePersist
        protected void onCreate(){
            createdAt=LocalDateTime.now();
            updatedAt=LocalDateTime.now();
        }
        @PreUpdate
        protected void onUpdate(){
            updatedAt=LocalDateTime.now();
        }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_"+role.name()));
    }

    @Override
    public String getUsername() {
        return email;
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    public  enum Role{
                ETUDIANT,ADMIN
        }




}
