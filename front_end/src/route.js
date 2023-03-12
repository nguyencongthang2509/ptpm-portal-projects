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
