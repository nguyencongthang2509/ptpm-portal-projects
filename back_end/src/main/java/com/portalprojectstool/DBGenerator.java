package com.portalprojectstool;

import com.portalprojects.entity.Assign;
import com.portalprojects.entity.Category;
import com.portalprojects.entity.Label;
import com.portalprojects.entity.LabelProject;
import com.portalprojects.entity.LabelTodo;
import com.portalprojects.entity.MemberProject;
import com.portalprojects.entity.Period;
import com.portalprojects.entity.PeriodTodo;
import com.portalprojects.entity.Project;
import com.portalprojects.entity.ProjectCategory;
import com.portalprojects.entity.Stakeholder;
import com.portalprojects.entity.StakeholderProject;
import com.portalprojects.entity.Todo;
import com.portalprojects.entity.TodoList;
import com.portalprojects.infrastructure.constant.Constants;
import com.portalprojects.infrastructure.constant.PriorityLevel;
import com.portalprojects.infrastructure.constant.RoleMemberProject;
import com.portalprojects.infrastructure.constant.RoleStakeholderProject;
import com.portalprojects.infrastructure.constant.StatusLabel;
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
import com.portalprojects.repository.LabelProjectRepository;
import com.portalprojects.repository.StakeholderProjectRepository;
import com.portalprojects.repository.StakeholderRepository;
import com.portalprojects.repository.TodoListRepository;
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
    private LabelProjectRepository labelProjectRepository;

    @Autowired
    private StakeholderProjectRepository stakeholderProjectRepository;

    @Autowired
    private StakeholderRepository stakeholderRepository;

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private TodoListRepository todoListRepository;

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
        label1.setColorLabel(Constants.COLOR_FF4500);
        label1.setStatusLabel(StatusLabel.FIX_CUNG);
        label1.setId(labelRepository.save(label1).getId());

        Label label2 = new Label();
        label2.setCode("Feature");
        label2.setName("Tính năng mới hoặc cải tiến");
        label2.setStatusLabel(StatusLabel.FIX_CUNG);
        label2.setColorLabel(Constants.COLOR_47799C);
        label2.setId(labelRepository.save(label2).getId());

        Label label3 = new Label();
        label3.setCode("Enhancement");
        label3.setStatusLabel(StatusLabel.FIX_CUNG);
        label3.setName("Cải tiến hoặc bổ sung tính năng hiện có");
        label3.setColorLabel(Constants.COLOR_FA8072);
        label3.setId(labelRepository.save(label3).getId());

        Label label4 = new Label();
        label4.setCode("Design");
        label4.setStatusLabel(StatusLabel.FIX_CUNG);
        label4.setName("Thiết kế giao diện người dùng và thiết kế hệ thống");
        label4.setColorLabel(Constants.COLOR_ADFF2F);
        label4.setId(labelRepository.save(label4).getId());

        Label label5 = new Label();
        label5.setCode("Marketing");
        label5.setName("Marketing, quảng cáo và PR");
        label5.setColorLabel(Constants.COLOR_7AA1E4);
        label5.setStatusLabel(StatusLabel.FIX_CUNG);
        label5.setId(labelRepository.save(label5).getId());

        Label label6 = new Label();
        label6.setCode("Content");
        label6.setName("Viết nội dung");
        label6.setColorLabel(Constants.COLOR_FFA500);
        label6.setStatusLabel(StatusLabel.FIX_CUNG);
        label6.setId(labelRepository.save(label6).getId());

        Label label7 = new Label();
        label7.setCode("Research");
        label7.setName("Nghiên cứu, phân tích dữ liệu");
        label7.setColorLabel(Constants.COLOR_FFD700);
        label7.setStatusLabel(StatusLabel.FIX_CUNG);
        label7.setId(labelRepository.save(label7).getId());

        Label label8 = new Label();
        label8.setCode("Infrastructure");
        label8.setStatusLabel(StatusLabel.FIX_CUNG);
        label8.setName("Hạ tầng, máy chủ, cơ sở dữ liệu và mạng");
        label8.setColorLabel(Constants.COLOR_FF6347);
        label8.setId(labelRepository.save(label8).getId());

        Label label9 = new Label();
        label9.setCode("Documentation");
        label9.setName("Viết tài liệu hướng dẫn");
        label9.setStatusLabel(StatusLabel.FIX_CUNG);
        label9.setColorLabel(Constants.COLOR_FFFF00);
        label9.setId(labelRepository.save(label9).getId());

        Label label10 = new Label();
        label10.setCode("Support");
        label10.setName("Hỗ trợ");
        label10.setStatusLabel(StatusLabel.FIX_CUNG);
        label10.setColorLabel(Constants.COLOR_EE82EE);
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

        LabelProject labelProject1 = new LabelProject();
        labelProject1.setLabelId(label1.getId());
        labelProject1.setProjectId(project1.getId());
        labelProject1.setId((labelProjectRepository.save(labelProject1).getId()));

        LabelProject labelProject2 = new LabelProject();
        labelProject2.setLabelId(label2.getId());
        labelProject2.setProjectId(project1.getId());
        labelProject2.setId((labelProjectRepository.save(labelProject2).getId()));

        LabelProject labelProject3 = new LabelProject();
        labelProject3.setLabelId(label3.getId());
        labelProject3.setProjectId(project1.getId());
        labelProject3.setId((labelProjectRepository.save(labelProject3).getId()));

        LabelProject labelProject4 = new LabelProject();
        labelProject4.setLabelId(label4.getId());
        labelProject4.setProjectId(project1.getId());
        labelProject4.setId((labelProjectRepository.save(labelProject4).getId()));

        LabelProject labelProject5 = new LabelProject();
        labelProject5.setLabelId(label5.getId());
        labelProject5.setProjectId(project1.getId());
        labelProject5.setId((labelProjectRepository.save(labelProject5).getId()));

        LabelProject labelProject6 = new LabelProject();
        labelProject6.setLabelId(label6.getId());
        labelProject6.setProjectId(project1.getId());
        labelProject6.setId((labelProjectRepository.save(labelProject6).getId()));

        LabelProject labelProject7 = new LabelProject();
        labelProject7.setLabelId(label7.getId());
        labelProject7.setProjectId(project1.getId());
        labelProject7.setId((labelProjectRepository.save(labelProject7).getId()));

        LabelProject labelProject8 = new LabelProject();
        labelProject8.setLabelId(label8.getId());
        labelProject8.setProjectId(project1.getId());
        labelProject8.setId((labelProjectRepository.save(labelProject8).getId()));

        LabelProject labelProject9 = new LabelProject();
        labelProject9.setLabelId(label9.getId());
        labelProject9.setProjectId(project1.getId());
        labelProject9.setId((labelProjectRepository.save(labelProject9).getId()));

        LabelProject labelProject10 = new LabelProject();
        labelProject10.setLabelId(label10.getId());
        labelProject10.setProjectId(project1.getId());
        labelProject10.setId((labelProjectRepository.save(labelProject10).getId()));

        TodoList todoList1 = new TodoList();
        todoList1.setCode("TodoList_1");
        todoList1.setName("VIỆC CẦN LÀM");
        todoList1.setProjectId(project1.getId());
        todoList1.setIndexTodoList(Byte.parseByte("0"));
        todoList1.setId((todoListRepository.save(todoList1).getId()));

        TodoList todoList2 = new TodoList();
        todoList2.setCode("TodoList_2");
        todoList2.setProjectId(project1.getId());
        todoList2.setName("VIỆC ĐANG LÀM");
        todoList2.setIndexTodoList(Byte.parseByte("1"));
        todoList2.setId((todoListRepository.save(todoList2).getId()));

        TodoList todoList3 = new TodoList();
        todoList3.setCode("TodoList_3");
        todoList3.setProjectId(project1.getId());
        todoList3.setName("CẦN SỬA");
        todoList3.setIndexTodoList(Byte.parseByte("2"));
        todoList3.setId((todoListRepository.save(todoList3).getId()));

        TodoList todoList4 = new TodoList();
        todoList4.setCode("TodoList_4");
        todoList4.setName("CẦN ĐÁNH GIÁ");
        todoList4.setProjectId(project1.getId());
        todoList4.setIndexTodoList(Byte.parseByte("3"));
        todoList4.setId((todoListRepository.save(todoList4).getId()));

        TodoList todoList5 = new TodoList();
        todoList5.setCode("TodoList_5");
        todoList5.setName("ĐÃ HOÀN THÀNH");
        todoList5.setProjectId(project1.getId());
        todoList5.setIndexTodoList(Byte.parseByte("4"));
        todoList5.setId((todoListRepository.save(todoList5).getId()));

        TodoList todoList6 = new TodoList();
        todoList6.setCode("TodoList_6");
        todoList6.setName("TẠM HOÃN");
        todoList6.setProjectId(project1.getId());
        todoList6.setIndexTodoList(Byte.parseByte("5"));
        todoList6.setId((todoListRepository.save(todoList6).getId()));

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
        memberProject1.setStatusWork(StatusWork.DANG_LAM);
        memberProject1.setId(memberProjectRepository.save(memberProject1).getId());

        MemberProject memberProject2 = new MemberProject();
        memberProject2.setMemberId("c5cf1e20-bdd4-11ed-afa1-0242ac120002");
        memberProject2.setProjectId(project1.getId());
        memberProject2.setRole(RoleMemberProject.LEADER);
        memberProject2.setStatusWork(StatusWork.DANG_LAM);
        memberProject2.setId(memberProjectRepository.save(memberProject2).getId());

        MemberProject memberProject3 = new MemberProject();
        memberProject3.setMemberId("d21b02c0-bdd4-11ed-afa1-0242ac120002");
        memberProject3.setProjectId(project1.getId());
        memberProject3.setRole(RoleMemberProject.DEV);
        memberProject3.setStatusWork(StatusWork.DANG_LAM);
        memberProject3.setId(memberProjectRepository.save(memberProject3).getId());

        MemberProject memberProject4 = new MemberProject();
        memberProject4.setMemberId("f01c9f36-bdd4-11ed-afa1-0242ac120002");
        memberProject4.setProjectId(project1.getId());
        memberProject4.setRole(RoleMemberProject.TESTER);
        memberProject4.setStatusWork(StatusWork.DANG_LAM);
        memberProject4.setId(memberProjectRepository.save(memberProject4).getId());

        MemberProject memberProject5 = new MemberProject();
        memberProject5.setMemberId("f6b388e6-bdd4-11ed-afa1-0242ac120002");
        memberProject5.setProjectId(project1.getId());
        memberProject5.setRole(RoleMemberProject.DEV);
        memberProject5.setStatusWork(StatusWork.DANG_LAM);
        memberProject5.setId(memberProjectRepository.save(memberProject5).getId());

        MemberProject memberProject6 = new MemberProject();
        memberProject6.setMemberId("02e78c02-bdd5-11ed-afa1-0242ac120002");
        memberProject6.setProjectId(project1.getId());
        memberProject6.setRole(RoleMemberProject.TESTER);
        memberProject6.setStatusWork(StatusWork.DANG_LAM);
        memberProject6.setId(memberProjectRepository.save(memberProject6).getId());

        MemberProject memberProject7 = new MemberProject();
        memberProject7.setMemberId("0791e31a-bdd5-11ed-afa1-0242ac120002");
        memberProject7.setProjectId(project1.getId());
        memberProject7.setRole(RoleMemberProject.DEV);
        memberProject7.setStatusWork(StatusWork.DANG_LAM);
        memberProject7.setId(memberProjectRepository.save(memberProject7).getId());

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
        todo1.setTodoListId(todoList1.getId());
        todo1.setTodoId(null);
        todo1.setPriorityLevel(PriorityLevel.QUAN_TRONG);
        todo1.setStatusTodo(StatusTodo.CHUA_HOAN_THANH);
        todo1.setId(todoRepository.save(todo1).getId());

        Todo todo2 = new Todo();
        todo2.setCode("Todo_2");
        todo2.setName("CRUD bảng label");
        todo2.setProgress((short) 0);
        todo2.setDescriptions("Không có mô tả");
        todo2.setDeadline(1679677200000L);
        todo2.setTodoListId(todoList1.getId());
        todo2.setCompletionTime(null);
        todo2.setNote("Not note");
        todo2.setTodoId(null);
        todo2.setPriorityLevel(PriorityLevel.CAO);
        todo2.setStatusTodo(StatusTodo.CHUA_HOAN_THANH);
        todo2.setId(todoRepository.save(todo2).getId());

        Todo todo3 = new Todo();
        todo3.setCode("Todo_3");
        todo3.setName("Làm tài liệu đặc tả");
        todo3.setProgress((short) 0);
        todo3.setDescriptions("Không có mô tả");
        todo3.setDeadline(1679677200000L);
        todo3.setTodoListId(todoList1.getId());
        todo3.setCompletionTime(null);
        todo3.setNote("Not note");
        todo3.setTodoId(null);
        todo3.setPriorityLevel(PriorityLevel.TRUNG_BINH);
        todo3.setStatusTodo(StatusTodo.CHUA_HOAN_THANH);
        todo3.setId(todoRepository.save(todo3).getId());

        Todo todo4 = new Todo();
        todo4.setCode("Todo_4");
        todo4.setName("Làm mình làm mẩy");
        todo4.setProgress((short) 0);
        todo4.setDescriptions("Không có mô tả");
        todo4.setDeadline(1679677200000L);
        todo4.setCompletionTime(null);
        todo4.setTodoListId(todoList2.getId());
        todo4.setTodoId(null);
        todo4.setNote("Not note");
        todo4.setPriorityLevel(PriorityLevel.THAP);
        todo4.setStatusTodo(StatusTodo.CHUA_HOAN_THANH);
        todo4.setId(todoRepository.save(todo4).getId());

        Todo todo5 = new Todo();
        todo5.setCode("Todo_5");
        todo5.setName("Phân quyền");
        todo5.setProgress((short) 0);
        todo5.setDescriptions("Không có mô tả");
        todo5.setDeadline(1679677200000L);
        todo5.setTodoListId(todoList2.getId());
        todo5.setCompletionTime(null);
        todo5.setNote("Not note");
        todo5.setTodoId(null);
        todo5.setPriorityLevel(PriorityLevel.CAO);
        todo5.setStatusTodo(StatusTodo.CHUA_HOAN_THANH);
        todo5.setId(todoRepository.save(todo5).getId());

        Todo todo6 = new Todo();
        todo6.setCode("Todo_6");
        todo6.setName("Đăng nhập");
        todo6.setProgress((short) 0);
        todo6.setDescriptions("");
        todo6.setDeadline(1679677200000L);
        todo6.setTodoListId(todoList2.getId());
        todo6.setCompletionTime(null);
        todo6.setTodoId(null);
        todo6.setNote("Not note");
        todo6.setPriorityLevel(null);
        todo6.setStatusTodo(StatusTodo.CHUA_HOAN_THANH);
        todo6.setId(todoRepository.save(todo6).getId());

        Todo todo7 = new Todo();
        todo7.setCode("Todo_7");
        todo7.setName("CRUD các đầu việc");
        todo7.setProgress((short) 33);
        todo7.setDescriptions("Không có mô tả");
        todo7.setDeadline(1679677200000L);
        todo7.setTodoListId(todoList2.getId());
        todo7.setCompletionTime(null);
        todo7.setTodoId(null);
        todo7.setNote("Not note");
        todo7.setPriorityLevel(PriorityLevel.QUAN_TRONG);
        todo7.setStatusTodo(StatusTodo.CHUA_HOAN_THANH);
        todo7.setId(todoRepository.save(todo7).getId());

        Todo todo7_2 = new Todo();
        todo7_2.setCode("Todo_7_2");
        todo7_2.setName("Sửa đầu việc");
        todo7_2.setTodoId(todo7.getId());
        todo7_2.setStatusTodo(StatusTodo.CHUA_HOAN_THANH);
        todo7_2.setId(todoRepository.save(todo7_2).getId());

        Todo todo7_1 = new Todo();
        todo7_1.setCode("Todo_7_1");
        todo7_1.setName("Thêm đầu việc");
        todo7_1.setTodoId(todo7.getId());
        todo7_1.setStatusTodo(StatusTodo.CHUA_HOAN_THANH);
        todo7_1.setId(todoRepository.save(todo7_1).getId());

        Todo todo7_3 = new Todo();
        todo7_3.setCode("Todo_7_2");
        todo7_3.setName("Xem đầu việc");
        todo7_3.setStatusTodo(StatusTodo.DA_HOAN_THANH);
        todo7_3.setTodoId(todo7.getId());
        todo7_3.setId(todoRepository.save(todo7_3).getId());

        Todo todo8 = new Todo();
        todo8.setCode("Todo_8");
        todo8.setName("Đăng ký");
        todo8.setProgress((short) 0);
        todo8.setDescriptions("Không có mô tả");
        todo8.setDeadline(1679677200000L);
        todo8.setTodoId(null);
        todo8.setCompletionTime(null);
        todo8.setTodoListId(todoList2.getId());
        todo8.setNote("Not note");
        todo8.setPriorityLevel(PriorityLevel.CAO);
        todo8.setStatusTodo(StatusTodo.CHUA_HOAN_THANH);
        todo8.setId(todoRepository.save(todo8).getId());

        Todo todo9 = new Todo();
        todo9.setCode("Todo_9");
        todo9.setName("Đọc ghi file excel");
        todo9.setProgress((short) 0);
        todo9.setDescriptions("Không có mô tả");
        todo9.setDeadline(1679677200000L);
        todo9.setTodoId(null);
        todo9.setTodoListId(todoList2.getId());
        todo9.setCompletionTime(null);
        todo9.setNote("Not note");
        todo9.setPriorityLevel(PriorityLevel.TRUNG_BINH);
        todo9.setStatusTodo(StatusTodo.CHUA_HOAN_THANH);
        todo9.setId(todoRepository.save(todo9).getId());

        Todo todo10 = new Todo();
        todo10.setCode("Todo_10");
        todo10.setName("Gửi mail tự động");
        todo10.setProgress((short) 0);
        todo10.setDescriptions("Không có mô tả");
        todo10.setDeadline(1679677200000L);
        todo10.setTodoId(null);
        todo10.setTodoListId(todoList2.getId());
        todo10.setCompletionTime(null);
        todo10.setNote("Not note");
        todo10.setPriorityLevel(PriorityLevel.THAP);
        todo10.setStatusTodo(StatusTodo.CHUA_HOAN_THANH);
        todo10.setId(todoRepository.save(todo10).getId());

        PeriodTodo periodTodo1 = new PeriodTodo();
        periodTodo1.setPeriodId(period1.getId());
        periodTodo1.setTodoId(todo1.getId());
        periodTodo1.setId(periodTodoRepository.save(periodTodo1).getId());

        PeriodTodo periodTodo2 = new PeriodTodo();
        periodTodo2.setPeriodId(period1.getId());
        periodTodo2.setTodoId(todo2.getId());
        periodTodo2.setId(periodTodoRepository.save(periodTodo2).getId());

        PeriodTodo periodTodo3 = new PeriodTodo();
        periodTodo3.setPeriodId(period1.getId());
        periodTodo3.setTodoId(todo3.getId());
        periodTodo3.setId(periodTodoRepository.save(periodTodo3).getId());

        PeriodTodo periodTodo4 = new PeriodTodo();
        periodTodo4.setPeriodId(period1.getId());
        periodTodo4.setTodoId(todo4.getId());
        periodTodo4.setId(periodTodoRepository.save(periodTodo4).getId());

        PeriodTodo periodTodo5 = new PeriodTodo();
        periodTodo5.setPeriodId(period1.getId());
        periodTodo5.setTodoId(todo5.getId());
        periodTodo5.setId(periodTodoRepository.save(periodTodo5).getId());

        PeriodTodo periodTodo6 = new PeriodTodo();
        periodTodo6.setPeriodId(period1.getId());
        periodTodo6.setTodoId(todo6.getId());
        periodTodo6.setId(periodTodoRepository.save(periodTodo6).getId());

        PeriodTodo periodTodo7 = new PeriodTodo();
        periodTodo7.setPeriodId(period1.getId());
        periodTodo7.setTodoId(todo7.getId());
        periodTodo7.setId(periodTodoRepository.save(periodTodo7).getId());

        PeriodTodo periodTodo8 = new PeriodTodo();
        periodTodo8.setPeriodId(period1.getId());
        periodTodo8.setTodoId(todo8.getId());
        periodTodo8.setId(periodTodoRepository.save(periodTodo8).getId());

        PeriodTodo periodTodo9 = new PeriodTodo();
        periodTodo9.setPeriodId(period1.getId());
        periodTodo9.setTodoId(todo9.getId());
        periodTodo9.setId(periodTodoRepository.save(periodTodo9).getId());

        PeriodTodo periodTodo10 = new PeriodTodo();
        periodTodo10.setPeriodId(period1.getId());
        periodTodo10.setTodoId(todo10.getId());
        periodTodo10.setId(periodTodoRepository.save(periodTodo10).getId());

        LabelTodo labelTodo1 = new LabelTodo();
        labelTodo1.setTodoId(todo1.getId());
        labelTodo1.setLabelId(label2.getId());
        labelTodo1.setId(labelTodoRepository.save(labelTodo1).getId());

        LabelTodo labelTodo2 = new LabelTodo();
        labelTodo2.setTodoId(todo2.getId());
        labelTodo2.setLabelId(label4.getId());
        labelTodo2.setId(labelTodoRepository.save(labelTodo2).getId());

        LabelTodo labelTodo3 = new LabelTodo();
        labelTodo3.setTodoId(todo1.getId());
        labelTodo3.setLabelId(label5.getId());
        labelTodo3.setId(labelTodoRepository.save(labelTodo3).getId());

        LabelTodo labelTodo4 = new LabelTodo();
        labelTodo4.setTodoId(todo3.getId());
        labelTodo4.setLabelId(label9.getId());
        labelTodo4.setId(labelTodoRepository.save(labelTodo4).getId());

        LabelTodo labelTodo5 = new LabelTodo();
        labelTodo5.setTodoId(todo4.getId());
        labelTodo5.setLabelId(label1.getId());
        labelTodo5.setId(labelTodoRepository.save(labelTodo5).getId());

        LabelTodo labelTodo6 = new LabelTodo();
        labelTodo6.setTodoId(todo5.getId());
        labelTodo6.setLabelId(label4.getId());
        labelTodo6.setId(labelTodoRepository.save(labelTodo6).getId());

        LabelTodo labelTodo7 = new LabelTodo();
        labelTodo7.setTodoId(todo6.getId());
        labelTodo7.setLabelId(label3.getId());
        labelTodo7.setId(labelTodoRepository.save(labelTodo7).getId());

        LabelTodo labelTodo8 = new LabelTodo();
        labelTodo8.setTodoId(todo7.getId());
        labelTodo8.setLabelId(label5.getId());
        labelTodo8.setId(labelTodoRepository.save(labelTodo8).getId());

        LabelTodo labelTodo9 = new LabelTodo();
        labelTodo9.setTodoId(todo8.getId());
        labelTodo9.setLabelId(label7.getId());
        labelTodo9.setId(labelTodoRepository.save(labelTodo9).getId());

        LabelTodo labelTodo10 = new LabelTodo();
        labelTodo10.setTodoId(todo9.getId());
        labelTodo10.setLabelId(label2.getId());
        labelTodo10.setId(labelTodoRepository.save(labelTodo10).getId());

        LabelTodo labelTodo11 = new LabelTodo();
        labelTodo11.setTodoId(todo10.getId());
        labelTodo11.setLabelId(label8.getId());
        labelTodo11.setId(labelTodoRepository.save(labelTodo11).getId());

        LabelTodo labelTodo12 = new LabelTodo();
        labelTodo12.setTodoId(todo7.getId());
        labelTodo12.setLabelId(label8.getId());
        labelTodo12.setId(labelTodoRepository.save(labelTodo12).getId());

        LabelTodo labelTodo13 = new LabelTodo();
        labelTodo13.setTodoId(todo8.getId());
        labelTodo13.setLabelId(label5.getId());
        labelTodo13.setId(labelTodoRepository.save(labelTodo13).getId());

        LabelTodo labelTodo14 = new LabelTodo();
        labelTodo14.setTodoId(todo7.getId());
        labelTodo14.setLabelId(label1.getId());
        labelTodo14.setId(labelTodoRepository.save(labelTodo14).getId());

        LabelTodo labelTodo15 = new LabelTodo();
        labelTodo15.setTodoId(todo7.getId());
        labelTodo15.setLabelId(label2.getId());
        labelTodo15.setId(labelTodoRepository.save(labelTodo15).getId());

        LabelTodo labelTodo16 = new LabelTodo();
        labelTodo16.setTodoId(todo7.getId());
        labelTodo16.setLabelId(label3.getId());
        labelTodo16.setId(labelTodoRepository.save(labelTodo16).getId());

        LabelTodo labelTodo17 = new LabelTodo();
        labelTodo17.setTodoId(todo7.getId());
        labelTodo17.setLabelId(label4.getId());
        labelTodo17.setId(labelTodoRepository.save(labelTodo17).getId());

        LabelTodo labelTodo18 = new LabelTodo();
        labelTodo18.setTodoId(todo7.getId());
        labelTodo18.setLabelId(label6.getId());
        labelTodo18.setId(labelTodoRepository.save(labelTodo18).getId());

        Assign assign1 = new Assign();
        assign1.setMemberId("c5cf1e20-bdd4-11ed-afa1-0242ac120002");
        assign1.setTodoId(todo1.getId());
        assign1.setId(assignRepository.save(assign1).getId());

        Assign assign2 = new Assign();
        assign2.setMemberId("f01c9f36-bdd4-11ed-afa1-0242ac120002");
        assign2.setTodoId(todo1.getId());
        assign2.setId(assignRepository.save(assign2).getId());

        Assign assign3 = new Assign();
        assign3.setMemberId("d21b02c0-bdd4-11ed-afa1-0242ac120002");
        assign3.setTodoId(todo2.getId());
        assign3.setId(assignRepository.save(assign3).getId());

        Assign assign4 = new Assign();
        assign4.setMemberId("f01c9f36-bdd4-11ed-afa1-0242ac120002");
        assign4.setTodoId(todo4.getId());
        assign4.setId(assignRepository.save(assign4).getId());

        Assign assign5 = new Assign();
        assign5.setMemberId("f01c9f36-bdd4-11ed-afa1-0242ac120002");
        assign5.setTodoId(todo7.getId());
        assign5.setId(assignRepository.save(assign5).getId());

        Assign assign5_1 = new Assign();
        assign5_1.setMemberId("d21b02c0-bdd4-11ed-afa1-0242ac120002");
        assign5_1.setTodoId(todo7.getId());
        assign5_1.setId(assignRepository.save(assign5_1).getId());

        Assign assign5_2 = new Assign();
        assign5_2.setMemberId("c5cf1e20-bdd4-11ed-afa1-0242ac120002");
        assign5_2.setTodoId(todo7.getId());
        assign5_2.setId(assignRepository.save(assign5_2).getId());

        Assign assign6 = new Assign();
        assign6.setMemberId("d21b02c0-bdd4-11ed-afa1-0242ac120002");
        assign6.setTodoId(todo8.getId());
        assign6.setId(assignRepository.save(assign6).getId());

        Assign assign7 = new Assign();
        assign7.setMemberId("f01c9f36-bdd4-11ed-afa1-0242ac120002");
        assign7.setTodoId(todo9.getId());
        assign7.setId(assignRepository.save(assign7).getId());

        Assign assign8 = new Assign();
        assign8.setMemberId("d21b02c0-bdd4-11ed-afa1-0242ac120002");
        assign8.setTodoId(todo10.getId());
        assign8.setId(assignRepository.save(assign8).getId());

        //

        Project project2 = new Project();
        project2.setCode("Project_2");
        project2.setName("Module quản lý dự án aaaaaaaaaaaaaa");
        project2.setStartTime(1678294800000L);
        project2.setEndTime(1685379600000L);
        project2.setProgress(Short.parseShort("0"));
        project2.setDescriptions("Mục đích của dự án là để quản lý các dự án của bộ môn PTPM");
        project2.setStatusProject(StatusProject.DANG_DIEN_RA);
        project2.setId((projectRepository.save(project2).getId()));

        TodoList todoList1_2 = new TodoList();
        todoList1_2.setCode("TodoList_1");
        todoList1_2.setName("VIỆC CẦN LÀM");
        todoList1_2.setProjectId(project2.getId());
        todoList1_2.setIndexTodoList(Byte.parseByte("0"));
        todoList1_2.setId((todoListRepository.save(todoList1_2).getId()));

        TodoList todoList2_2 = new TodoList();
        todoList2_2.setCode("TodoList_2");
        todoList2_2.setProjectId(project2.getId());
        todoList2_2.setName("VIỆC ĐANG LÀM");
        todoList2_2.setIndexTodoList(Byte.parseByte("1"));
        todoList2_2.setId((todoListRepository.save(todoList2).getId()));

        ProjectCategory projectCategory1_1 = new ProjectCategory();
        projectCategory1.setCategoryId(category2.getId());
        projectCategory1.setProjectId(project2.getId());
        projectCategory1.setId(projectCategoryRepository.save(projectCategory1_1).getId());

        MemberProject memberProject1_2 = new MemberProject();
        memberProject1_2.setMemberId("c5cf1e20-bdd4-11ed-afa1-0242ac120002");
        memberProject1_2.setProjectId(project2.getId());
        memberProject1_2.setRole(RoleMemberProject.MANAGER);
        memberProject1_2.setStatusWork(StatusWork.DANG_LAM);
        memberProject1_2.setId(memberProjectRepository.save(memberProject1_2).getId());

        Period period1_2 = new Period();
        period1_2.setCode("period_1");
        period1_2.setName("Giai đoạn thiết kế database");
        period1_2.setStartTime(1678294800000L);
        period1_2.setEndTime(1682355600000L);
        period1_2.setTarget("Hoàn thiện khung database");
        period1_2.setProgress(Short.parseShort("0"));
        period1_2.setStatusPeriod(StatusPeriod.DANG_DIEN_RA);
        period1_2.setDescriptions("Giai đoạn quan trọng 1");
        period1_2.setProjectId(project2.getId());
        period1_2.setId(periodRepository.save(period1_2).getId());

        Period period2_2 = new Period();
        period2_2.setCode("period_2");
        period2_2.setName("Giai đoạn thiết kế giao diện");
        period2_2.setStartTime(1682355600000L);
        period2_2.setEndTime(1685379600000L);
        period2_2.setTarget("Hoàn thiện khung database");
        period2_2.setProgress(Short.parseShort("0"));
        period2_2.setStatusPeriod(StatusPeriod.CHUA_DIEN_RA);
        period2_2.setDescriptions("Giai đoạn quan trọng 2");
        period2_2.setProjectId(project2.getId());
        period2_2.setId(periodRepository.save(period2_2).getId());

        Todo todo1_2 = new Todo();
        todo1_2.setCode("Todo_1");
        todo1_2.setName("CRUD bảng category");
        todo1_2.setProgress((short) 0);
        todo1_2.setDescriptions("Không có mô tả");
        todo1_2.setDeadline(1679677200000L);
        todo1_2.setCompletionTime(null);
        todo1_2.setNote("Not note");
        todo1_2.setTodoListId(todoList1_2.getId());
        todo1_2.setTodoId(null);
        todo1_2.setPriorityLevel(PriorityLevel.QUAN_TRONG);
        todo1_2.setStatusTodo(StatusTodo.CHUA_HOAN_THANH);
        todo1_2.setId(todoRepository.save(todo1_2).getId());

        PeriodTodo periodTodo1_2 = new PeriodTodo();
        periodTodo1_2.setPeriodId(period1_2.getId());
        periodTodo1_2.setTodoId(todo1_2.getId());
        periodTodo1_2.setId(periodTodoRepository.save(periodTodo1_2).getId());

    }

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(DBGenerator.class);
        ctx.close();
    }

}