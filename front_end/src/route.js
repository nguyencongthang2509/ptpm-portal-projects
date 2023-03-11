app.config(function ($routeProvider, $locationProvider) {
  $locationProvider.hashPrefix("");
  $routeProvider
    // Admin
    .when("/admin/project-management", {
      templateUrl: "./pages/admin/admin-project-management.html",
      controller: AdminProjectManagementController,
      css: "../css/custom/admin/admin-project-management.css",
    })
    .when("/admin/project-management/detail/:id", {
      templateUrl: "./pages/admin/admin-project-management-detail.html",
      controller: AdminProjectManagementDetailController,
      css: "../css/custom/admin/admin-project-management-detail.css",
    })
    // Manager
    .when("/manager/human-resource-management", {
      templateUrl: "./pages/manager/manager-human-resource-management.html",
      controller: ManagerHumanResourceManagementController,
      css: "../css/custom/manager/manager-human-resource-management.css",
    })
    .when("/manager/period-management", {
      templateUrl: "./pages/manager/manager-period-management.html",
      controller: ManagerPeriodManagementController,
      css: "../css/custom/manager/manager-period-management.css",
    })
    .when("/manager/todo-management", {
      templateUrl: "./pages/manager/manager-todo-management.html",
      controller: ManagerTodoManagementController,
      css: "../css/custom/manager/manager-todo-management.css",
    })
    .when("/manager/project-management-history", {
      templateUrl: "./pages/manager/manager-project-management-history.html",
      controller: ManagerProjectManagementHistoryController,
      css: "../css/custom/manager/manager-project-management-history.css",
    })
    // Leader
    .when("/leader/period-management", {
      templateUrl: "./pages/leader/leader-period-management.html",
      controller: LeaderPeriodManagementController,
      css: "../css/custom/leader/leader-period-management.css",
    })
    .when("/leader/todo-management", {
      templateUrl: "./pages/leader/leader-todo-management.html",
      controller: LeaderTodoManagementController,
      css: "../css/custom/leader/leader-todo-management.css",
    })
    .when("/leader/work-history", {
      templateUrl: "./pages/leader/leader-work-history.html",
      controller: LeaderWorkHistoryController,
      css: "../css/custom/leader/leader-work-history.css",
    })
    // Member
    .when("/member/project-is-participating", {
      templateUrl: "./pages/member/member-project-is-participating.html",
      controller: MemberProjectIsParticipatingController,
      css: "../css/custom/member/member-project-is-participating.css",
    })
    .when("/member/work-history", {
      templateUrl: "./pages/member/member-work-history.html",
      controller: MemberWorkHistoryController,
      css: "../css/custom/member/member-work-history.css",
    })
    // Stakeholders
    .when("/stakeholder/projects", {
      templateUrl: "./pages/stakeholder/stakeholder-projects.html",
      controller: StakeholderProjectController,
      css: "../css/custom/stakeholder/stakeholder-projects.css",
    })
    .otherwise({
      redirectTo: "/admin/project-management",
    });
});

/*
.otherwise({
    redirectTo: function() {
      var userRole = AuthService.getUserRole();
      if (userRole === 'admin') {
        return '/admin';
      } else if (userRole === 'manager') {
        return '/manager';
      } else if (userRole === 'leader') {
        return '/leader';
      } else {
        return '/login';
      }
    }
    */
