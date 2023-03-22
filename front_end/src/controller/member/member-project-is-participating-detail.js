window.MemberProjectIsParticipatingDetailController = function (
  $scope,
  $http,
  $routeParams,
  MeDetailProjectService,
  MeMemberService,
  MeResourceService,
  MeGetAllPeriodById,
  MeTodoService,
  MeAssignService,
  MeLabelService,
  MeDetailTodoService,
  MeMemberProjectService
) {
  let idProject = $routeParams.id;
  $scope.projectId = idProject;
  // Get All
  async function fetchAllData(idProject) {
    await Promise.all([
      MeDetailProjectService.fetchProject(idProject),
      MeMemberService.fetchMembers(),
      MeResourceService.fetchResources(idProject),
      MeGetAllPeriodById.fetchPeriods(idProject),
      MeMemberProjectService.fetchMembers(idProject),
    ]);

    $scope.detailProject = MeDetailProjectService.getProject();
    $scope.listMemberById = MeMemberService.getMembers();
    $scope.listResource = MeResourceService.getResources();
    $scope.listPeriodById = MeGetAllPeriodById.getPeriods();

    let periodCurrent = $scope.listPeriodById.filter((pe) => {
      return pe.status == 1;
    })[0];
    if (periodCurrent) {
      $scope.valuePeriod = periodCurrent.id;
    } else {
      $scope.valueInput = "";
    }
    $scope.currentPeriod = periodCurrent.id;
    $scope.loadDataTodo(periodCurrent.id);

    $scope.listMemberProject = [];
    const memberProject = MeMemberProjectService.getMembers();
    memberProject.forEach((meId) => {
      const member = $scope.listMemberById.find((me) => meId === me.id);
      if (member) {
        member.checkMemberAssign = false;
        $scope.listMemberProject.push(member);
      }
    });

    $scope.checkNumberMember = true;
    $scope.numberMemberMore = $scope.listMemberProject.length - 5;
    if ($scope.listMemberProject.length <= 5) {
      $scope.checkNumberMember = false;
    } else {
      $scope.checkNumberMember = true;
    }
  }

  fetchAllData(idProject);

  $scope.loadDataTodo = function (idPeriod) {
    $scope.listTask = [
      {
        id: 1,
        name: "VIỆC CẦN LÀM",
        todoList: [],
        checkShowAddCard: false,
      },
      {
        id: 2,
        name: "VIỆC ĐANG LÀM",
        todoList: [],
        checkShowAddCard: false,
      },
      {
        id: 3,
        name: "CẦN SỬA",
        todoList: [],
        checkShowAddCard: false,
      },
      {
        id: 4,
        name: "CẦN ĐÁNH GIÁ",
        todoList: [],
        checkShowAddCard: false,
      },
      {
        id: 5,
        name: "ĐÃ HOÀN THÀNH",
        todoList: [],
        checkShowAddCard: false,
      },
      {
        id: 6,
        name: "TẠM HOÃN",
        todoList: [],
        checkShowAddCard: false,
      },
    ];

    Promise.all(
      $scope.listTask.map((item) => {
        return MeTodoService.fetchTodo(idPeriod, item.id - 1).then(function () {
          item.todoList = MeTodoService.getTodo();
          if (item.todoList != []) {
            const assignPromises = item.todoList.map((td) => {
              return MeAssignService.fetchMember(td.id).then(function () {
                td.members = [];
                const idMembers = MeAssignService.getMembers();
                idMembers.forEach((meId) => {
                  const member = $scope.listMemberById.find(
                    (me) => meId === me.id
                  );
                  if (member) {
                    td.members.push(member);
                  }
                });
              });
            });

            const labelPromises = item.todoList.map((td) => {
              return MeLabelService.fetchLabel(td.id).then(function () {
                td.labels = MeLabelService.getLabels();
              });
            });
            return Promise.all([...assignPromises, ...labelPromises]);
          }
        });
      })
    );
  };

  $scope.changePeriod = function () {
    $scope.loadDataTodo($scope.valuePeriod);
  };

  // Drag And Drop

  $scope.selected = null;
  $scope.fromList = null;
  $scope.taskEnd = null;

  $scope.onDrop = function (event, index, item, external, type, xx) {
    console.log("Item", item, "dropped into", xx);
    $scope.taskEnd = xx;
    $scope.indexItemEnd = index;
  };

  $scope.onDragStart = function (index, list) {
    $scope.indexFirst = index;
    $scope.fromList = list;
  };

  $scope.onDragEnd = function (item, xx, event, index) {
    if (xx.id == $scope.taskEnd.id) {
      const indexItem = xx.todoList.indexOf(item);
      let temp = item;
      if (indexItem === -1 || $scope.indexItemEnd === indexItem) {
        return;
      } else if ($scope.indexItemEnd > indexItem) {
        xx.todoList.splice(indexItem, 1);
        xx.todoList.splice($scope.indexItemEnd - 1, 0, temp);
      } else {
        xx.todoList.splice(indexItem, 1);
        xx.todoList.splice($scope.indexItemEnd, 0, temp);
      }
    } else {
      $scope.fromList.splice($scope.indexFirst, 1);
      $scope.taskEnd.todoList.splice($scope.indexItemEnd, 0, item);
    }
  };

  // Add card

  $scope.addACard = function (value) {
    $scope.listTask[value - 1].checkShowAddCard = true;

    setTimeout(() => {
      document.querySelectorAll(".card-body")[value].scrollTop = 100000;
    }, 1);
  };

  $scope.valueInput = "";

  $scope.addNewCard = function (value) {
    let listTitle = document.querySelectorAll('[name="inputTitle"]');
    $scope.listTask[value - 1].todoList.push({
      name: listTitle[value].value,
    });
    listTitle[value - 1].value = "";
    $scope.listTask[value - 1].checkShowAddCard = false;
  };

  $scope.addNewResource = function () {};

  //Detail Todo

  $scope.actionDetailTodo = function (id, index, idTask) {
    $scope.indexTodoInTask = index;
    $scope.idTask = idTask;
    Promise.all([
      MeDetailTodoService.fetchTodo(id),
      MeAssignService.fetchMember(id),
      MeLabelService.fetchLabel(id),
      MeDetailTodoService.fetchDetailTodo(id),
    ]).then(function () {
      $scope.detailTodo = MeDetailTodoService.getTodo();
      if ($scope.detailTodo.priorityLevel == "QUAN_TRONG") {
        $scope.detailTodo.priorityLevel = 0;
      } else if ($scope.detailTodo.priorityLevel == "CAO") {
        $scope.detailTodo.priorityLevel = 1;
      } else if ($scope.detailTodo.priorityLevel == "TRUNG_BINH") {
        $scope.detailTodo.priorityLevel = 2;
      } else if ($scope.detailTodo.priorityLevel == "THAP") {
        $scope.detailTodo.priorityLevel = 3;
      }
      $scope.detailTodo.listMemberDetailTodo = $scope.listMemberById.filter(
        (member) => MeAssignService.getMembers().includes(member.id)
      );
      $scope.detailTodo.listLabelDetailTodo = MeLabelService.getLabels();
      $scope.detailTodo.listTaskDetailTodo =
        MeDetailTodoService.getTodoDetail();

      $scope.$apply();
    });
  };

  // popup add member
  $scope.showDialogAddMember = false;

  $scope.openDialogAddMember = function (event) {
    event.stopPropagation();
    $scope.showDialogAddLabel = false;
    if (!$scope.showDialogAddMember) {
      $scope.showDialogAddMember = true;
    } else {
      $scope.showDialogAddMember = false;
    }
    $scope.dialogStyleAddMember = {
      top: event.clientY - 100 + "px",
      left: event.clientX - 450 + "px",
    };

    $scope.listMemberProject.forEach((meId) => {
      let memberCheckAssign = $scope.detailTodo.listMemberDetailTodo.find(
        (me) => meId.id === me.id
      );
      if (memberCheckAssign != null) {
        meId.checkMemberAssign = true;
      } else {
        meId.checkMemberAssign = false;
      }
    });

    $scope.clickOutPopupAddMember();
  };

  $scope.clickOutPopupAddMember = function () {
    document.addEventListener(
      "click",
      function (event) {
        if (
          !document.querySelector(".dialogAddMember").contains(event.target)
        ) {
          $scope.$apply(function () {
            $scope.closeDialogAddMember();
          });
        }
      },
      { once: true }
    );
  };

  $scope.closeDialogAddMember = function () {
    $scope.showDialogAddMember = false;
  };

  $scope.createAssign = function (idMember, idTodo) {
    $http
      .post(apiMemberAssign + "?idMember=" + idMember + "&idTodo=" + idTodo)
      .then(function (response) {
        $scope.actionReloadData(idMember, idTodo);
      });
  };

  $scope.deleteAssign = function (idMember, idTodo) {
    $http
      .delete(apiMemberAssign + "?idMember=" + idMember + "&idTodo=" + idTodo)
      .then(function (response) {
        $scope.actionReloadData(idMember, idTodo);
      });
  };

  $scope.actionReloadData = function (idMember, idTodo) {
    Promise.all([MeAssignService.fetchMember(idTodo)]).then(function () {
      const idMembers = MeAssignService.getMembers();
      let listMemberAfter = [];
      idMembers.forEach((meId) => {
        const member = $scope.listMemberById.find((me) => meId === me.id);
        if (member) {
          listMemberAfter.push(member);
        }
      });

      $scope.listTask[$scope.idTask - 1].todoList[
        $scope.indexTodoInTask
      ].members = listMemberAfter;

      $scope.detailTodo.listMemberDetailTodo = $scope.listMemberById.filter(
        (member) => MeAssignService.getMembers().includes(member.id)
      );
      $scope.listMemberProject.forEach((meId) => {
        let memberCheckAssign = $scope.detailTodo.listMemberDetailTodo.find(
          (me) => meId.id === me.id
        );
        if (memberCheckAssign != null) {
          meId.checkMemberAssign = true;
        } else {
          meId.checkMemberAssign = false;
        }
      });
      $scope.$apply();
    });
    $scope.clickOutPopupAddMember();
  };

  $scope.createOrDeleteAssign = function (idMember, idTodo, memberCheckAssign) {
    if (memberCheckAssign) {
      $scope.deleteAssign(idMember, idTodo);
    } else {
      $scope.createAssign(idMember, idTodo);
    }
  };

  // popup add labels
  $scope.showDialogAddLabel = false;

  $scope.openDialogAddLabel = function (event) {
    $scope.showDialogAddMember = false;
    event.stopPropagation();
    if (!$scope.showDialogAddLabel) {
      $scope.showDialogAddLabel = true;
    } else {
      $scope.showDialogAddLabel = false;
    }
    $scope.dialogStyleAddLabel = {
      top: event.clientY - 100 + "px",
      left: event.clientX - 450 + "px",
    };

    MeLabelService.fetchLabels().then(function () {
      $scope.listLabel = MeLabelService.getAllLabels();

      $scope.listLabel.forEach((lbAll) => {
        let label = $scope.detailTodo.listLabelDetailTodo.find(
          (lbDetail) => lbAll.id === lbDetail.id
        );
        if (label != null) {
          lbAll.checkLabel = true;
        } else {
          lbAll.checkLabel = false;
        }
      });
    });

    $scope.clickOutPopupAddLabel();
  };

  $scope.clickOutPopupAddLabel = function () {
    document.addEventListener(
      "click",
      function (event) {
        if (!document.querySelector(".dialogAddLabel").contains(event.target)) {
          $scope.$apply(function () {
            $scope.closeDialogAddLabel();
          });
        }
      },
      { once: true }
    );
  };

  $scope.closeDialogAddLabel = function () {
    $scope.showDialogAddLabel = false;
  };

  $scope.createLabelTodo = function (idLabel, idTodo, checkLabel) {
    $http
      .post(apiMemberLabelTodo + "?idLabel=" + idLabel + "&idTodo=" + idTodo)
      .then(function (response) {
        $scope.actionReloadDataLabel(idLabel, idTodo);
      });
  };

  $scope.deleteLabelTodo = function (idLabel, idTodo, checkLabel) {
    $http
      .delete(apiMemberLabelTodo + "?idLabel=" + idLabel + "&idTodo=" + idTodo)
      .then(function (response) {
        $scope.actionReloadDataLabel(idLabel, idTodo);
      });
  };

  $scope.actionReloadDataLabel = function (idLabel, idTodo, checkLabel) {
    Promise.all([
      MeLabelService.fetchLabels(),
      MeLabelService.fetchLabel(idTodo),
      MeLabelService.fetchLabel(idTodo),
    ]).then(function () {
      $scope.detailTodo.listLabelDetailTodo = MeLabelService.getLabels();

      $scope.listLabel = MeLabelService.getAllLabels();
      $scope.listLabel.forEach((lbAll) => {
        let label = $scope.detailTodo.listLabelDetailTodo.find(
          (lbDetail) => lbAll.id === lbDetail.id
        );
        if (label != null) {
          lbAll.checkLabel = true;
        } else {
          lbAll.checkLabel = false;
        }
      });

      $scope.listTask[$scope.idTask - 1].todoList[
        $scope.indexTodoInTask
      ].labels = MeLabelService.getLabels();

      $scope.$apply();
    });
    $scope.clickOutPopupAddLabel();
  };

  $scope.changeCheckboxLabel = function (idLabel, idTodo, checkLabel) {
    if (checkLabel) {
      $scope.deleteLabelTodo(idLabel, idTodo, checkLabel);
    } else {
      $scope.createLabelTodo(idLabel, idTodo, checkLabel);
    }
  };
};
