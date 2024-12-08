//package com.inglab.user_auth_system.model;
//
//import com.inglab.user_auth_system.enums.Role;
//import com.inglab.user_auth_system.token.Token;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import org.springframework.data.annotation.Id;
//import org.springframework.data.mongodb.core.mapping.Document;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.util.Collection;
//import java.util.Collections;
//import java.util.List;
//
//@Data
//@Builder
//@NoArgsConstructor
//@AllArgsConstructor
//@Document(collection = "users") // MongoDB'ye uygun anotasyon
//public class User implements UserDetails {
//
//    @Id
//    private String id; // MongoDB'de String ID daha yaygındır
//    private String firstname;
//    private String lastname;
//    private String email;
//    private String password;
//
//    private Role role; // Enum, MongoDB'de otomatik olarak desteklenir
//
//    private List<Token> tokens; // Token nesneleri direkt olarak listelenebilir
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return Collections.singletonList(new SimpleGrantedAuthority(role.name()));
//    }
//
//    @Override
//    public String getPassword() {
//        return password;
//    }
//
//    @Override
//    public String getUsername() {
//        return email;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
//}
package com.inglab.user_auth_system.model;

import com.inglab.user_auth_system.enums.Role;
import com.inglab.user_auth_system.token.Token;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "users")
@Schema(description = "Represents a user in the system with authentication and authorization details")
public class User implements UserDetails {

    @Id
    @Schema(description = "Unique identifier for the user", example = "60c72b2f9b1e8e2f8f8f8f8f")
    private String id;

    @Schema(description = "User's first name", example = "John")
    private String firstname;

    @Schema(description = "User's last name", example = "Doe")
    private String lastname;

    @Schema(description = "User's email address (used as username)", example = "john.doe@example.com")
    private String email;

    @Schema(description = "User's password, securely stored", example = "hashed_password")
    private String password;

    @Schema(description = "User's role (e.g., 'user', 'admin')", example = "user")
    private Role role;

    @Schema(description = "List of tokens assigned to the user for session management")
    private List<Token> tokens;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    @Schema(hidden = true)
    public String getPassword() {
        return password;
    }

    @Override
    @Schema(description = "Email used as the username for authentication")
    public String getUsername() {
        return email;
    }

    @Override
    @Schema(description = "Indicates whether the account is expired or not", example = "true")
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @Schema(description = "Indicates whether the account is locked or not", example = "true")
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @Schema(description = "Indicates whether the credentials are expired or not", example = "true")
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @Schema(description = "Indicates whether the account is enabled or not", example = "true")
    public boolean isEnabled() {
        return true;
    }
}
