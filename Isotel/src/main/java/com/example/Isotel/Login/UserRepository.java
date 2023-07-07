package com.example.Isotel.Login;

import com.example.Isotel.Usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Usuario, Long>  {
     Usuario findByUsername(String user);
}
