package com.taskflow.authservice.entity;

import com.taskflow.authservice.enums.SystemRole;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users", indexes = {
        @Index(name = "idx_users_is_active", columnList = "is_active"),
        @Index(name = "idx_users_current_org_id", columnList = "current_org_id")
})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "password", nullable = false, unique = true)
    private String password;

    @Column(name = "first_name", nullable = false, unique = true)
    private String firstName;

    @Column(name = "avatar_url", nullable = false, unique = true)
    private String avatarUrl;

    @Column(name = "last_name", nullable = false, unique = true)
    private String lastName;

    @Enumerated(EnumType.STRING)
    @Column(name = "system_role", nullable = false,length = 20)
    @Builder.Default
    private SystemRole systemRole = SystemRole.USER;

    @Column(name = "is_active", nullable = false)
    @Builder.Default
    private boolean isActive = false;

    @Column(name = "is_email_verified", nullable = false)
    @Builder.Default
    private boolean isEmailVerified = false;

    @Column(name = "last_login_at")
    private Integer lastLoginAt;

    @Column(name = "current_org_id")
    private UUID currentOrgId;

    @Column(name = "timezone", nullable = false, length = 50)
    @Builder.Default
    private String timezone = "UTC";

    @Column(name = "locale", nullable = false)
    @Builder.Default
    private String locale = "en";

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Integer createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private Integer updatedAt;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<RefreshToken> refreshTokens = new ArrayList<>();

    @Transient
    public String getFullName() {
        return firstName + " " + lastName;
    }

    public boolean isSuperAdmin() {
        return systemRole == SystemRole.SUPER_ADMIN;
    }
}