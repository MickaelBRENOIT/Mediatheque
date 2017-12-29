package com.mickaelbrenoit.business.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mickaelbrenoit.business.model.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByLogin(String login);
	User findByLoginAndPassword(String login, String password);
	List<User> findByLastName(String lastName);
	List<User> findByFirstNameAndLastName(String firstName, String lastName);
	
    @Query("select p from User p where p.role.name = :roleName order by p.lastName, p.firstName")
    List<User> findByRole(@Param("roleName") String roleName);
    
    @Query("SELECT p FROM User p where p.role.name = :role ORDER BY p.lastName, p.firstName")
	List<User> findAllByRole(@Param("role") String role);

    // pour dire à spring-data-jpa que cette query est une opération update et qu'elle requiert executeUpdate() et non executeQuery()
//    @Modifying
//    @Query("update User p set p.active = :active where p.id = :id")
//    void activate(@Param("active") Boolean active, @Param("id") Long id);

}
