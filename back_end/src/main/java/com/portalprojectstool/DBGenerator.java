package com.portalprojectstool;

import com.portalprojects.entity.Assign;
import com.portalprojects.entity.Category;
import com.portalprojects.entity.Label;
import com.portalprojects.entity.LabelTodo;
import com.portalprojects.entity.MemberProject;
import com.portalprojects.entity.Period;
import com.portalprojects.entity.PeriodTodo;
import com.portalprojects.entity.Project;
import com.portalprojects.entity.ProjectCategory;
import com.portalprojects.entity.Resource;
import com.portalprojects.entity.Stakeholder;
import com.portalprojects.entity.StakeholderProject;
import com.portalprojects.entity.Todo;
import com.portalprojects.infrastructure.constant.PriorityLevel;
import com.portalprojects.infrastructure.constant.RoleMemberProject;
import com.portalprojects.infrastructure.constant.RoleStakeholderProject;
import com.portalprojects.infrastructure.constant.StatusPeriod;
import com.portalprojects.infrastructure.constant.StatusProject;
import com.portalprojects.infrastructure.constant.StatusTodo;
import com.portalprojects.infrastructure.constant.StatusWork;
import com.portalprojects.repository.AssignRepository;
import com.portalprojects.repository.CategoryRepository;
import com.portalprojects.repository.LabelRepository;
import com.portalprojects.repository.LabelTodoRepository;
import com.portalprojects.repository.MemberProjectRepository;
import com.portalprojects.repository.PeriodTodoRepository;
import com.portalprojects.repository.PeriodRepository;
import com.portalprojects.repository.ProjectCategoryRepository;
import com.portalprojects.repository.ProjectRepository;
import com.portalprojects.repository.ResourceRepository;
import com.portalprojects.repository.StakeholderProjectRepository;
import com.portalprojects.repository.StakeholderRepository;
import com.portalprojects.repository.TodoRepository;
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
    private AssignRepository assignRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private LabelRepository labelRepository;

    @Autowired
    private LabelTodoRepository labelTodoRepository;

    @Autowired
    private MemberProjectRepository memberProjectRepository;

    @Autowired
    private PeriodTodoRepository periodTodoRepository;

    @Autowired
    private PeriodRepository periodRepository;

    @Autowired
    private ProjectCategoryRepository projectCategoryRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ResourceRepository resourceRepository;

    @Autowired
    private StakeholderProjectRepository stakeholderProjectRepository;

    @Autowired
    private StakeholderRepository stakeholderRepository;

    @Autowired
    private TodoRepository todoRepository;

    @Override
    public void run(String... args) throws Exception {
        Category category1 = new Category();
        category1.setCode("Cate_1");
        category1.setName("Phát triển game");
        category1.setId(categoryRepository.save(category1).getId());

        Category category2 = new Category();
        category2.setCode("Cate_2");
        category2.setName("Phát triển web");
        category2.setId(categoryRepository.save(category2).getId());

        Category category3 = new Category();
        category3.setCode("Cate_2");
        category3.setName("Phát triển app");
        category3.setId(categoryRepository.save(category3).getId());

        Label label1 = new Label();
        label1.setCode("Bug");
        label1.setName("Sự cố hoặc lỗi");
        label1.setId(labelRepository.save(label1).getId());

        Label label2 = new Label();
        label2.setCode("Feature");
        label2.setName("Tính năng mới hoặc cải tiến");
        label2.setId(labelRepository.save(label2).getId());

        Label label3 = new Label();
        label3.setCode("Enhancement");
        label3.setName("Cải tiến hoặc bổ sung tính năng hiện có");
        label3.setId(labelRepository.save(label3).getId());

        Label label4 = new Label();
        label4.setCode("Design");
        label4.setName("Thiết kế giao diện người dùng và thiết kế hệ thống");
        label4.setId(labelRepository.save(label4).getId());

        Label label5 = new Label();
        label5.setCode("Marketing");
        label5.setName("Marketing, quảng cáo và PR");
        label5.setId(labelRepository.save(label5).getId());

        Label label6 = new Label();
        label6.setCode("Content");
        label6.setName("Viết nội dung");
        label6.setId(labelRepository.save(label6).getId());

        Label label7 = new Label();
        label7.setCode("Research");
        label7.setName("Nghiên cứu, phân tích dữ liệu");
        label7.setId(labelRepository.save(label7).getId());

        Label label8 = new Label();
        label8.setCode("Infrastructure");
        label8.setName("Hạ tầng, máy chủ, cơ sở dữ liệu và mạng");
        label8.setId(labelRepository.save(label8).getId());

        Label label9 = new Label();
        label9.setCode("Documentation");
        label9.setName("Viết tài liệu hướng dẫn");
        label9.setId(labelRepository.save(label9).getId());

        Label label10 = new Label();
        label10.setCode("Support");
        label10.setName("Hỗ trợ");
        label10.setId(labelRepository.save(label10).getId());

        Stakeholder stakeholder1 = new Stakeholder();
        stakeholder1.setName("Nguyễn Anh Dũng");
        stakeholder1.setUsername("dungna29");
        stakeholder1.setEmailFE("dungna29@fe.edu.vn");
        stakeholder1.setEmailFPT("dungna29@fpt.edu.vn");
        stakeholder1.setPhoneNumber("0864857239");
        stakeholder1.setId(stakeholderRepository.save(stakeholder1).getId());

        Stakeholder stakeholder2 = new Stakeholder();
        stakeholder2.setName("Đặng Quang Minh");
        stakeholder2.setUsername("minhdq8");
        stakeholder2.setEmailFE("minhdq8@fe.edu.vn");
        stakeholder2.setEmailFPT("minhdq8@fpt.edu.vn");
        stakeholder2.setPhoneNumber("0863452432");
        stakeholder2.setId(stakeholderRepository.save(stakeholder2).getId());

        Project project1 = new Project();
        project1.setCode("Project_1");
        project1.setName("Module quản lý dự án");
        project1.setStartTime(1678294800000L);
        project1.setEndTime(1685379600000L);
        project1.setProgress(Short.parseShort("0"));
        project1.setDescriptions("Mục đích của dự án là để quản lý các dự án của bộ môn PTPM");
        project1.setStatusProject(StatusProject.DANG_DIEN_RA);
        project1.setId((projectRepository.save(project1).getId()));

        ProjectCategory projectCategory1 = new ProjectCategory();
        projectCategory1.setCategoryId(category2.getId());
        projectCategory1.setProjectId(project1.getId());
        projectCategory1.setId(projectCategoryRepository.save(projectCategory1).getId());

        ProjectCategory projectCategory2 = new ProjectCategory();
        projectCategory2.setCategoryId(category1.getId());
        projectCategory2.setProjectId(project1.getId());
        projectCategory2.setId(projectCategoryRepository.save(projectCategory2).getId());

        StakeholderProject stakeholderProject1 = new StakeholderProject();
        stakeholderProject1.setProjectId(project1.getId());
        stakeholderProject1.setStakeholderId(stakeholder1.getId());
        stakeholderProject1.setRole(RoleStakeholderProject.PHONG_DAO_TAO);
        stakeholderProject1.setId(stakeholderProjectRepository.save(stakeholderProject1).getId());

        StakeholderProject stakeholderProject2 = new StakeholderProject();
        stakeholderProject2.setProjectId(project1.getId());
        stakeholderProject2.setStakeholderId(stakeholder2.getId());
        stakeholderProject2.setRole(RoleStakeholderProject.THIET_KE);
        stakeholderProject2.setId(stakeholderProjectRepository.save(stakeholderProject2).getId());

        MemberProject memberProject1 = new MemberProject();
        memberProject1.setMemberId("db39ebf0-bdd4-11ed-afa1-0242ac120002");
        memberProject1.setProjectId(project1.getId());
        memberProject1.setRole(RoleMemberProject.MANAGER);
        memberProject1.setStatus(StatusWork.DANG_LAM);
        memberProject1.setId(memberProjectRepository.save(memberProject1).getId());

        MemberProject memberProject2 = new MemberProject();
        memberProject2.setMemberId("c5cf1e20-bdd4-11ed-afa1-0242ac120002");
        memberProject2.setProjectId(project1.getId());
        memberProject2.setRole(RoleMemberProject.LEADER);
        memberProject2.setStatus(StatusWork.DANG_LAM);
        memberProject2.setId(memberProjectRepository.save(memberProject2).getId());

        MemberProject memberProject3 = new MemberProject();
        memberProject3.setMemberId("d21b02c0-bdd4-11ed-afa1-0242ac120002");
        memberProject3.setProjectId(project1.getId());
        memberProject3.setRole(RoleMemberProject.DEV);
        memberProject3.setStatus(StatusWork.DANG_LAM);
        memberProject3.setId(memberProjectRepository.save(memberProject3).getId());

        MemberProject memberProject4 = new MemberProject();
        memberProject4.setMemberId("f01c9f36-bdd4-11ed-afa1-0242ac120002");
        memberProject4.setProjectId(project1.getId());
        memberProject4.setRole(RoleMemberProject.TESTER);
        memberProject4.setStatus(StatusWork.DANG_LAM);
        memberProject4.setId(memberProjectRepository.save(memberProject4).getId());

        Resource resource1 = new Resource();
        resource1.setCode("Resource_1");
        resource1.setName("Link github");
        resource1.setProjectId(project1.getId());
        resource1.setContent("https://github.com/FPLHN-FACTORY/ptpm-portal-projects");
        resource1.setId(resourceRepository.save(resource1).getId());

        Resource resource2 = new Resource();
        resource2.setCode("Resource_2");
        resource2.setName("Link document");
        resource2.setProjectId(project1.getId());
        resource2.setContent("https://app.diagrams.net/#G1jHd124HSsQD_dwaXW_FJVguVM8Vf73oZ");
        resource2.setId(resourceRepository.save(resource2).getId());

        Period period1 = new Period();
        period1.setCode("period_1");
        period1.setName("Giai đoạn thiết kế database");
        period1.setStartTime(1678294800000L);
        period1.setEndTime(1682355600000L);
        period1.setTarget("Hoàn thiện khung database");
        period1.setProgress(Short.parseShort("0"));
        period1.setStatusPeriod(StatusPeriod.DANG_DIEN_RA);
        period1.setDescriptions("Giai đoạn quan trọng 1");
        period1.setProjectId(project1.getId());
        period1.setId(periodRepository.save(period1).getId());

        Period period2 = new Period();
        period2.setCode("period_2");
        period2.setName("Giai đoạn thiết kế giao diện");
        period2.setStartTime(1682355600000L);
        period2.setEndTime(1685379600000L);
        period2.setTarget("Hoàn thiện khung database");
        period2.setProgress(Short.parseShort("0"));
        period2.setStatusPeriod(StatusPeriod.CHUA_DIEN_RA);
        period2.setDescriptions("Giai đoạn quan trọng 2");
        period2.setProjectId(project1.getId());
        period2.setId(periodRepository.save(period2).getId());

        Todo todo1 = new Todo();
        todo1.setCode("Todo_1");
        todo1.setName("CRUD bảng category");
        todo1.setProgress((short) 0);
        todo1.setDescriptions("Không có mô tả");
        todo1.setDeadline(1679677200000L);
        todo1.setCompletionTime(null);
        todo1.setNote("Not note");
        todo1.setPriorityLevel(PriorityLevel.QUAN_TRONG);
        todo1.setStatusTodo(StatusTodo.DANG_DIEN_RA);
        todo1.setId(todoRepository.save(todo1).getId());

        Todo todo2 = new Todo();
        todo2.setCode("Todo_2");
        todo2.setName("CRUD bảng label");
        todo2.setProgress((short) 0);
        todo2.setDescriptions("Không có mô tả");
        todo2.setDeadline(1679677200000L);
        todo2.setCompletionTime(null);
        todo2.setNote("Not note");
        todo2.setPriorityLevel(PriorityLevel.QUAN_TRONG);
        todo2.setStatusTodo(StatusTodo.DANG_DIEN_RA);
        todo2.setId(todoRepository.save(todo2).getId());

        PeriodTodo periodTodo1 = new PeriodTodo();
        periodTodo1.setPeriodId(period1.getId());
        periodTodo1.setTodoId(todo1.getId());
        periodTodo1.setId(periodTodoRepository.save(periodTodo1).getId());

        PeriodTodo periodTodo2 = new PeriodTodo();
        periodTodo2.setPeriodId(period1.getId());
        periodTodo2.setTodoId(todo2.getId());
        periodTodo2.setId(periodTodoRepository.save(periodTodo2).getId());

        LabelTodo labelTodo1 = new LabelTodo();
        labelTodo1.setTodoId(todo1.getId());
        labelTodo1.setLabelId(label2.getId());
        labelTodo1.setId(labelTodoRepository.save(labelTodo1).getId());

        LabelTodo labelTodo2 = new LabelTodo();
        labelTodo2.setTodoId(todo2.getId());
        labelTodo2.setLabelId(label2.getId());
        labelTodo2.setId(labelTodoRepository.save(labelTodo2).getId());

        Assign assign1 = new Assign();
        assign1.setMemberId("c5cf1e20-bdd4-11ed-afa1-0242ac120002");
        assign1.setTodoId(todo1.getId());
        assign1.setId(assignRepository.save(assign1).getId());

        Assign assign2 = new Assign();
        assign2.setMemberId("d21b02c0-bdd4-11ed-afa1-0242ac120002");
        assign2.setTodoId(todo2.getId());
        assign2.setId(assignRepository.save(assign2).getId());
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(DBGenerator.class);
        ctx.close();
    }

}