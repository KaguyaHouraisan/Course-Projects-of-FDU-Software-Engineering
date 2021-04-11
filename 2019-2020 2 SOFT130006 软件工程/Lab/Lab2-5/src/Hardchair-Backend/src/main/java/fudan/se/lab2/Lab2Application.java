package fudan.se.lab2;

import fudan.se.lab2.domain.Authority;
import fudan.se.lab2.domain.Paper;
import fudan.se.lab2.domain.User;
import fudan.se.lab2.repository.AuthorityRepository;
import fudan.se.lab2.repository.ConferenceRepository;
import fudan.se.lab2.repository.PaperRepository;
import fudan.se.lab2.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;
import java.util.HashSet;

/**
 * Welcome to 2020 Software Engineering Lab2.
 * This is your first lab to write your own code and build a spring boot application.
 * Enjoy it :)
 *
 * @author LBW
 */
@SpringBootApplication
public class Lab2Application {

    public static void main(String[] args) {
        SpringApplication.run(Lab2Application.class, args);
    }

    /**
     * This is a function to create some basic entities when the application starts.
     * Now we are using a In-Memory database, so you need it.
     * You can change it as you like.
     */
    @Bean
    public CommandLineRunner dataLoader(PaperRepository paperRepository, UserRepository userRepository, AuthorityRepository authorityRepository, ConferenceRepository conferenceRepository, PasswordEncoder passwordEncoder) {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                // Create authorities if not exist.
                Authority adminAuthority = getOrCreateAuthority("Admin", authorityRepository);
                final String name="yanzeyu";
                final String yzy="yzy";
                User admin = new User(
                        "admin",
                        "libowen",
                        passwordEncoder.encode("password"),
                        "libowen",
                        "fdu",
                        "",
                        new HashSet<>(Collections.singletonList(adminAuthority))
                );
                // Create an admin if not exists.
                if (userRepository.findByUsername("admin") == null) {
                    userRepository.save(admin);
                }
                User test = new User(
                        "a",
                        name,
                        passwordEncoder.encode(""),
                        name,
                        "fdu",
                        "a@qq.com",
                        new HashSet<>(Collections.singletonList(adminAuthority))
                );
                // Create an admin if not exists.
                if (userRepository.findByUsername("a") == null) {
                    userRepository.save(test);
                }

                User test2 = new User(
                        "atest",
                        name,
                        passwordEncoder.encode(""),
                        name,
                        "fdfu",
                        "b@qq.com",
                        new HashSet<>(Collections.singletonList(adminAuthority))
                );
                // Create an admin if not exists.
                if (userRepository.findByUsername("atest") == null) {
                    userRepository.save(test2);
                }
                //debug
                User test3 = new User(
                        "a1",
                        yzy,
                        passwordEncoder.encode(""),
                        yzy,
                        "fdfu",
                        "b@qq.com",
                        new HashSet<>(Collections.singletonList(adminAuthority))
                );
                // Create an admin if not exists.
                userRepository.save(test3);

                User test4 = new User(
                        "a2",
                        yzy,
                        passwordEncoder.encode(""),
                        yzy,
                        "fdfru",
                        "b@qq.com",
                        new HashSet<>(Collections.singletonList(adminAuthority))
                );
                // Create an admin if not exists.
                userRepository.save(test4);

                User test5 = new User(
                        "a3",
                        yzy,
                        passwordEncoder.encode(""),
                        yzy,
                        "rfdfu",
                        "b@qq.com",
                        new HashSet<>(Collections.singletonList(adminAuthority))
                );
                // Create an admin if not exists.
                userRepository.save(test5);

                Long a = 2L;
                Paper paper = new Paper("a", "a", "a", "a", "a", a);
                paperRepository.save(paper);
            }

            private Authority getOrCreateAuthority(String authorityText, AuthorityRepository authorityRepository) {
                Authority authority = authorityRepository.findByAuthority(authorityText);
                if (authority == null) {
                    authority = new Authority(authorityText);
                    authorityRepository.save(authority);
                }
                return authority;
            }
        };
    }
}

