package com.portalprojectstool;

import com.portalprojects.entity.DuAn;
import com.portalprojects.repository.DuAnRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Date;

/**
 * @author phongtt35
 */

@SpringBootApplication
@EnableJpaRepositories(
        basePackages = "com.portalprojects.repository"
)
public class DBGenerator implements CommandLineRunner {


    private final boolean IS_RELEASE = false;

//    @Autowired
//    @Qualifier(DuAnRepository.NAME)
//    private DuAnRepository duAnRepository;

    @Override
    public void run(String... args) throws Exception {
        DuAn duAn = new DuAn();
        duAn.setId("1");
        duAn.setTenDuAn("aaaaaaaaaaaaaa");
//        duAnRepository.save(duAn);
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(DBGenerator.class);
        ctx.close();
    }

}