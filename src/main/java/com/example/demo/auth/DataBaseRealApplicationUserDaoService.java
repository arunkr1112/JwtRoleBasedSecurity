package com.example.demo.auth;

import com.example.demo.UserDao;
import com.example.demo.model.User;
import com.example.demo.model.UserPermission;
import com.example.demo.model.UserRole;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static com.example.demo.security.ApplicationUserRole.*;

@Repository("flexibalService")
public class DataBaseRealApplicationUserDaoService implements ApplicationUserDao {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    UserDao userDao;
    @Autowired
    public DataBaseRealApplicationUserDaoService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<ApplicationUser> selectApplicationUserByUsername(String username) {


        User user = userDao.findByUserName(username);
        if(user == null){
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        ApplicationUser applicationUser =new ApplicationUser(
                user.getUserName(), user.getPassword(), getAuthority(user), true,
                true, true, true);
        Optional<ApplicationUser> applicationUserOptional = Optional.of(applicationUser);
        return applicationUserOptional;
    }

    private Set<SimpleGrantedAuthority> getAuthority(User user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        user.getUserRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
        });
        return authorities;
    }

    private Set<SimpleGrantedAuthority> getAuthorityPermission(User user) {
        Set<UserRole> userRoles = user.getUserRoles();
        Set<UserPermission> userPermissions =null;
         userRoles.forEach(userRole -> userPermissions.addAll(userRole.getUserPermissions()));
        Set<SimpleGrantedAuthority> permissions = userPermissions.stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermissionName()))
                .collect(Collectors.toSet());
        user.getUserRoles().forEach(userRole -> permissions.add(new SimpleGrantedAuthority("ROLE_" + userRole.getRoleName())));
        return permissions;
    }

 /*   private User getDBApplicationUsers(String username) {
        User user = userDao.findByUserName(username);
        ApplicationUser applicationUser = new ApplicationUser(
                user.getUsername(),
                passwordEncoder.encode(user.getPassword()),
                user.getGrantedAuthorities(),
                true,
                true,
                true
        )
        return user;
    }*/
    private List<ApplicationUser> getApplicationUsers() {
        List<ApplicationUser> applicationUsers = Lists.newArrayList(
                new ApplicationUser(
                        "annasmith",
                        passwordEncoder.encode("password"),
                        STUDENT.getGrantedAuthorities(),
                        true,
                        true,
                        true,
                        true
                ),
                new ApplicationUser(
                        "linda",
                        passwordEncoder.encode("password"),
                        ADMIN.getGrantedAuthorities(),
                        true,
                        true,
                        true,
                        true
                ),
                new ApplicationUser(
                        "tom",
                        passwordEncoder.encode("password"),
                        ADMINTRAINEE.getGrantedAuthorities(),
                        true,
                        true,
                        true,
                        true
                )
        );

        return applicationUsers;
    }

}
