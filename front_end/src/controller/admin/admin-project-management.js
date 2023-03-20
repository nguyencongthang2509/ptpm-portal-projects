window.AdminProjectManagementController = function (
  $scope,
  $http,
  $location,
  AdGetAllProject,
  MeMemberService,
  AdMemberProjcetService,
) {
  $scope.listProject = [];
  $scope.form_project = {
    id: "",
    code: "",
    name: "",
    descriptions: "",
    startTime: "",
    endTime: "",
    progress: 0,
    statusProject: "1",
  };

  $scope.form_project_update = {
    id: "",
    code: "",
    name: "",
    descriptions: "",
    startTime: "",
    endTime: "",
    progress: 0,
    statusProject: "1",
  };

  //load data
  $scope.arrayPage = [];
  function loadDataListProject() {
    AdGetAllProject.fetchProject().then(function () {
      $scope.listProject = AdGetAllProject.getProject();
      $scope.totalPages = AdGetAllProject.getTotalpages();
      $scope.arrayPage = genArrayPage($scope.totalPages);
    });
  }

  loadDataListProject();

  //search tên dự án
  $scope.input_search = "";
  $scope.search = function () {
    AdGetAllProject.searchProject($scope.input_search).then(function () {
      $scope.listProject = AdGetAllProject.getProject();
      $scope.totalPages = AdGetAllProject.getTotalpages();
      $scope.arrayPage = genArrayPage($scope.totalPages);
    });
  };

  function genArrayPage(value) {
    let array = [];
    for (let i = 1; i <= value; i++) {
      array.push(i);
    }
    return array;
  }

  $scope.currentPage = 0;
  $scope.actionPage = function (currentPage) {
    $scope.currentPage = currentPage;
    AdGetAllProject.pageProject($scope.input_search, currentPage).then(
      function () {
        $scope.listProject = AdGetAllProject.getProject();
        $scope.arrayPage = genArrayPage(AdGetAllProject.getTotalpages());
      }
    );
  };

  $scope.clearInputModalAdd = function () {
    $scope.form_project.name = "";
    $scope.form_project.code = "";
    $scope.form_project.descriptions = "";
    $scope.form_project.startTime = "";
    $scope.form_project.endTime = "";
  };

  // thêm project
  $scope.addProject = function () {
    console.log($scope.form_project);
    let check = 0;
    if ($scope.form_project.code === "") {
      ++check;
      $scope.errorCodeAdd = "Mã dự án không để trống";
    } else if ($scope.form_project.code.length > 15) {
      $scope.errorCodeAdd = "Mã dự án không quá 15 ký tự";
      return;
    } else {
      $scope.errorCodeAdd = "";
    }

    if ($scope.form_project.name === "") {
      ++check;
      $scope.errorNameAdd = "Tên dự án không để trống";
    } else {
      $scope.errorNameAdd = "";
    }

    if ($scope.form_project.startTime === "") {
      ++check;
      $scope.errorStartTimeAdd = "Ngày bắt đầu dự án không để trống";
    } else {
      $scope.errorStartTimeAdd = "";
    }

    if ($scope.form_project.endTime === "") {
      ++check;
      $scope.errorEndTimeAdd = "Ngày kết thúc dự án không để trống";
    } else {
      $scope.errorEndTimeAdd = "";
    }

    if (
      $scope.form_project.endTime.getTime() <
      $scope.form_project.startTime.getTime()
    ) {
      $scope.errorStartTimeAdd =
        "Ngày bắt đầu dự án không nhỏ hơn ngày hiện tại";
      ++check;
    } else {
      $scope.errorStartTimeAdd = "";
    }

    if ($scope.form_project.descriptions === "") {
      ++check;
      $scope.errorDescriptionsAdd = "Mô tả dự án không để trống";
    } else {
      $scope.errorDescriptionsAdd = "";
    }

    if (check == 0) {
      $http
        .post(projcetAPI, {
          code: $scope.form_project.code,
          name: $scope.form_project.name,
          descriptions: $scope.form_project.descriptions,
          startTime: $scope.form_project.startTime.getTime(),
          endTime: $scope.form_project.endTime.getTime(),
          progress: $scope.form_project.progress,
          statusProject: $scope.form_project.statusProject,
        })
        .then(
          function (response) {
            toastr.success("Thêm thành công", "Thông báo!", {
              closeButton: true,
              progressBar: true,
              positionClass: "toast-top-center",
            });
            $("#exampleModal").modal("hide");
            loadDataListProject();
          },
          function (error) {
            toastr.error(error.data.message, "Thông báo!", {
              closeButton: true,
              progressBar: true,
              positionClass: "toast-top-center",
            });
          }
        );
    }
  };

  $scope.Update = function (event) {
    event.preventDefault();
    let projectId = $scope.form_project_update.id;
    console.log(projectId);
    let api = projcetAPI + "/" + projectId;

    //check
    let check = 0;
    if ($scope.form_project_update.code === "") {
      ++check;
      $scope.errorCodeUpdate = "Mã dự án không để trống";
    } else if ($scope.form_project_update.code.length > 15) {
      $scope.errorCodeUpdate = "Mã dự án không quá 15 ký tự";
      return;
    } else {
      $scope.errorCodeUpdate = "";
    }

    if ($scope.form_project_update.name === "") {
      ++check;
      $scope.errorNameUpdate = "Tên dự án không để trống";
    } else {
      $scope.errorNameUpdate = "";
    }

    if ($scope.form_project_update.startTime === "") {
      ++check;
      $scope.errorStartTimeUpdate = "Ngày bắt đầu dự án không để trống";
    } else {
      $scope.errorStartTimeUpdate = "";
    }

    if ($scope.form_project_update.endTime === "") {
      ++check;
      $scope.errorEndTimeUpdate = "Ngày kết thúc dự án không để trống";
    } else {
      $scope.errorEndTimeUpdate = "";
    }

    if (
      $scope.form_project_update.endTime.getTime() <
      $scope.form_project_update.startTime.getTime()
    ) {
      $scope.errorStartTimeUpdate =
        "Ngày bắt đầu dự án không nhỏ hơn ngày hiện tại";
      ++check;
    } else {
      $scope.errorStartTimeUpdate = "";
    }

    if ($scope.form_project_update.descriptions === "") {
      ++check;
      $scope.errorDescriptionsUpdate = "Mô tả dự án không để trống";
    } else {
      $scope.errorDescriptionsUpdate = "";
    }

    if (check == 0) {
      $http
        .put(api, {
          code: $scope.form_project_update.code,
          name: $scope.form_project_update.name,
          descriptions: $scope.form_project_update.descriptions,
          startTime: $scope.form_project_update.startTime.getTime(),
          endTime: $scope.form_project_update.endTime.getTime(),
          // if để set trạng thái
          progress: 0,
          statusProject: "1",
        })
        .then(
          function (response) {
            toastr.success("Update thành công", "Thông báo!", {
              closeButton: true,
              progressBar: true,
              positionClass: "toast-top-center",
            });
            $("#exampleModalUpdate").modal("hide");
            loadDataListProject();
          },
          function (error) {
            toastr.error(error.data.message, "Thông báo!", {
              closeButton: true,
              progressBar: true,
              positionClass: "toast-top-center",
            });
          }
        );
    }
  };

  // member
  $scope.listMemberById = [];
  MeMemberService.fetchMembers().then(function () {
    $scope.listMemberById = MeMemberService.getMembers();
  });

  // detail
  $scope.indexProject = -1;
  $scope.form_getOneMemberProject = {};
  $scope.detail = function (event, index) {
    event.preventDefault();
    let project = $scope.listProject[index];
    console.log(project);
    $scope.form_project_update.id = project.id;
    $scope.form_project_update.code = project.code;
    $scope.form_project_update.name = project.name;
    $scope.form_project_update.startTime = new Date(project.startTime);
    $scope.form_project_update.endTime = new Date(project.endTime);
    $scope.form_project_update.progress = project.progress;
    $scope.form_project_update.statusProject = project.statusProject;
    $scope.form_project_update.descriptions = project.descriptions;
    $scope.indexProject = index;
    loadDataListMemberJoinProject(project.id);
  };

  function getOneMemberProject (idMemberProject){
    return $http
      .get(member_ProjcetAPI+"/" + idMemberProject)
      .then(
        function (response) {
          if (response.status === 200) {
            $scope.form_getOneMemberProject = response.data;
          }
          return response;
        },
        function (errors) {
          console.log(errors);
        }
      );
  }
  // get member join project
  $scope.listMemberJoinProject = [];
  function loadDataListMemberJoinProject(idProject) {
    AdMemberProjcetService.findAllMemberJoinProject(idProject).then(
      function () {
        let list = [];
        let membersAll = $scope.listMemberById;
        list = AdMemberProjcetService.getMemberProject();
        console.log(list);
        console.log(membersAll);
        $scope.listMemberJoinProject = membersAll.filter(function (obj1) {
          return list.data.some(function (obj2) {
            return obj2.memberId === obj1.id;
          });
        });
        console.log($scope.listMemberJoinProject);
      }
    );
  }

   // thêm member vào dựu án 
  $scope.listCheck = [];
  function chekcMemberJoinProject (idProject, idMember){
    AdMemberProjcetService.findAllMemberJoinProject(idProject).then(
      function () {
        list = AdMemberProjcetService.getMemberProject();
        $scope.listCheck = list.data.filter(function (obj1) {
            return obj1.memberId === idMember;
        });
      }
    );
  }
  
  $scope.addMemberProject = function (event, index) {
    event.preventDefault();
    let check = [];
    let member = $scope.listMemberById[index];
    let project = $scope.listProject[$scope.indexProject];
    let api = member_ProjcetAPI ;
    check =  $scope.listCheck;
    if (check.length ===0) {
      $http
        .post(api, {
          memberId : member.id,
          projectId : project.id,
          role : "2",
          status : "0"
        })
        .then(
          function (response) {
            toastr.success("Thêm thành công", "Thông báo!", {
              closeButton: true,
              progressBar: true,
              positionClass: "toast-top-center",
            });
            $("#modal_showMember").modal("hide");
            loadDataListProject();
          },
          function (error) {
            toastr.error(error.data.message, "Thông báo!", {
              closeButton: true,
              progressBar: true,
              positionClass: "toast-top-center",
            });
          }
        );
    }
  };

};
