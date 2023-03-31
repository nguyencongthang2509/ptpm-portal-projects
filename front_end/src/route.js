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
    .when("/admin/category-management", {
      templateUrl: "./pages/admin/admin-category-management.html",
      controller: AdminCategoryManagementController,
      css: "../css/custom/admin/admin-category-management.css",
    })
    .when("/admin/label-management", {
      templateUrl: "./pages/admin/admin-label-management.html",
      controller: AdminLabelManagementController,
      css: "../css/custom/admin/admin-label-management.css",
    })
    .when("/admin/stakeholder-management", {
      templateUrl: "./pages/admin/admin-stakeholder-management.html",
      controller: AdminStakeholderManagementController,
      css: "../css/custom/admin/admin-stakeholder-management.css",
    })
    // Member
    .when("/member/project-is-participating", {
      templateUrl: "./pages/member/member-project-is-participating.html",
      controller: MemberProjectIsParticipatingController,
      css: "../css/custom/member/member-project-is-participating.css",
    })
    .when("/member/project-is-participating/:id", {
      templateUrl: "./pages/member/member-project-is-participating-detail.html",
      controller: MemberProjectIsParticipatingDetailController,
      css: "../css/custom/member/member-project-is-participating-detail.css",
      reloadOnSearch: false
    })
    .when("/member/period-project/:id", {
      templateUrl: "./pages/member/member-period-project.html",
      controller: MemberPeriodProjectController,
      css: "../css/custom/member/member-period-project.css",
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
