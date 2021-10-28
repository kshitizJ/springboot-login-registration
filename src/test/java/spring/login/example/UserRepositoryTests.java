package spring.login.example;

import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

// @DataJpaTest: to indicate that this test class will be running as data jpa test.
// @AutoConfigureTestDatabase: we are using real database so we use this annotation. Because spring data jpa test bydefault uses inmemory database.
@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
@Rollback(false)
public class UserRepositoryTests {

   @Autowired
   private UserRepository repo;

   @Autowired
   private TestEntityManager entityManager;

   @Test
   public void testCreateUser() {
      User user = new User();
      user.setEmail("omkar@mail.com");
      user.setPassword("password123");
      user.setFirstName("Omkar");
      user.setLastName("Chougule");
      User savedUser = repo.save(user);
      User existUser = entityManager.find(User.class, savedUser.getId());
      assertThat(existUser.getEmail()).isEqualTo(user.getEmail());
   }

   @Test
   public void testFindUserByEmail() {
      String email = "kshitiz@mail.com";
      User user = repo.findByEmail(email);
      assertThat(user).isNotNull();
   }

}
