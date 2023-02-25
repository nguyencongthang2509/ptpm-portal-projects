package com.portalprojectstool;

import com.portalprojects.entity.DuAn;
import com.portalprojects.repository.DuAnRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author thangncph26123
 */

@SpringBootApplication
@EnableJpaRepositories(
        basePackages = "com.portalprojects.repository"
)
public class DBGenerator implements CommandLineRunner {


    private final boolean IS_RELEASE = false;

    @Autowired
    private DuAnRepository duAnRepository;

    @Override
    public void run(String... args) throws Exception {
//        DuAn duAn = new DuAn();
//        duAn.setMaDuAn("DA1");
//        duAn.setTenDuAn("aaaaaaaaaaaaaa");
//        duAn.setTaiNguyen("Link git:");
//        duAnRepository.save(duAn);
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(DBGenerator.class);
        ctx.close();
    }

}