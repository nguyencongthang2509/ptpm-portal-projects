window.MemberProjectIsParticipatingDetailController = function (
  $scope,
  $http,
  $timeout,
  $location,
  $routeParams,
  MeDetailProjectService,
  MeMemberService,
  MeResourceService,
  MeGetAllPeriodById,
  MeTodoService,
  MeAssignService,
  MeLabelService,
  MeDetailTodoService,
  MeMemberProjectService,
  MeTodoListService
) {
  document.body.style.backgroundImage =
    "url('" + localStorage.getItem("itemBackgroundImage") + "')";

  $scope.changeBackground = function (item) {
    localStorage.setItem("itemBackgroundImage", item);
    document.body.style.backgroundImage =
      "url('" + localStorage.getItem("itemBackgroundImage") + "')";
  };

  const menuButton = document.querySelector(".more-menu");
  const rightMenu = document.getElementById("right-menu");
  const changebackgroundMenu = document.getElementById("new-menu");
  const previousMenu = document.getElementById("previous-menu");
  const newMenu = document.getElementById("change-background");
  const closeMenuButton = document.getElementById("close-menu");

  let handleDocumentClick = function (event) {
    if (
      rightMenu.classList.contains("active") &&
      !rightMenu.contains(event.target) &&
      event.target !== menuButton &&
      event.target !== previousMenu
    ) {
      closeMenu(rightMenu, handleDocumentClick);
    }
  };

  let handleDocumentClickNewMenu = function (event) {
    if (
      changebackgroundMenu.classList.contains("active") &&
      !changebackgroundMenu.contains(event.target) &&
      event.target !== newMenu
    ) {
      closeMenu(changebackgroundMenu, handleDocumentClickNewMenu);
    }
  };

  function closeMenu(menu, handle) {
    menu.classList.remove("active");
    if (menu === changebackgroundMenu) {
      document.removeEventListener("click", handle);
    } else if (menu === rightMenu) {
      document.removeEventListener("click", handle);
    }
  }

  function toggleMenu(menu, handle) {
    menu.classList.toggle("active");
    if (menu.classList.contains("active")) {
      document.addEventListener("click", handle);
    } else {
      document.removeEventListener("click", handle);
    }
  }

  newMenu.addEventListener("click", function () {
    toggleMenu(changebackgroundMenu, handleDocumentClickNewMenu);
  });

  menuButton.addEventListener("click", function () {
    toggleMenu(rightMenu, handleDocumentClick);
  });

  closeMenuButton.addEventListener("click", function () {
    closeMenu(rightMenu, handleDocumentClick);
  });

  previousMenu.addEventListener("click", function () {
    closeMenu(changebackgroundMenu, handleDocumentClickNewMenu);
  });

  const socket = new SockJS(
    "http://localhost:6789/portal-projects-websocket-endpoint"
  );
  const stompClient = Stomp.over(socket);

  let idProject = $routeParams.id;
  $scope.projectId = idProject;

  // Get All
  async function fetchAllData(idProject) {
    await Promise.all([
      MeDetailProjectService.fetchProject(idProject),
      MeMemberService.fetchMembers(),
      // MeResourceService.fetchResources(idProject),
      MeGetAllPeriodById.fetchPeriods(idProject),
      MeMemberProjectService.fetchMembers(idProject),
      MeTodoListService.fetchTodoList(idProject),
    ]);

    $scope.detailProject = MeDetailProjectService.getProject();
    $scope.listMemberById = MeMemberService.getMembers();
    // $scope.listResource = MeResourceService.getResources();
    $scope.listPeriodById = MeGetAllPeriodById.getPeriods();
    $scope.listTask = MeTodoListService.getTodoList();

    let periodCurrent = $scope.listPeriodById.filter((pe) => {
      return pe.status == 1;
    })[0];
    if (periodCurrent) {
      $scope.valuePeriod = periodCurrent.id;
    } else {
      $scope.valueInput = "";
    }

    let periodCurrentId = localStorage.getItem(idProject);

    if (periodCurrent != null && periodCurrentId == null) {
      localStorage.setItem(idProject, $scope.valuePeriod);
    }

    if (periodCurrentId != null) {
      $location
        .path("/member/project-is-participating/" + idProject)
        .search({ idPeriod: periodCurrentId });
      $scope.loadDataTodo(periodCurrentId);
    }

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
    $scope.listTask.forEach((item) => {
      item.todoList = [];
      item.checkShowAddCard = false;
    });

    Promise.all(
      $scope.listTask.map((item) => {
        return MeTodoService.fetchTodo(idPeriod, item.id).then(function () {
          item.todoList = MeTodoService.getTodo();
          if (item.todoList != []) {
            const assignPromises = item.todoList.map((td) => {
              if (td.numberTodo != 0) {
                td.progressOfTodo = parseInt(
                  (td.numberTodoComplete / td.numberTodo) * 100
                );
              }

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
    localStorage.setItem(idProject, $scope.valuePeriod);
    $scope.loadDataTodo(localStorage.getItem(idProject));
  };

  // Drag And Drop

  $scope.onDragStartCard = function (item, index) {
    if (item != null) {
      $scope.draggedItem = item;
      $scope.draggedIndex = index;
    }
  };

  $scope.onDropCard = function (item, index) {
    let periodCurrentId = localStorage.getItem(idProject);
    if ($scope.itemDrag == null || $scope.itemDrag.indexTodoList != null) {
      if ($scope.draggedIndex === index || $scope.draggedIndex == -1) {
        return;
      }
      if ($scope.draggedItem != null) {
        let obj = {
          idTodoList: $scope.draggedItem.id,
          indexBefore: $scope.draggedIndex,
          indexAfter: index,
        };
        stompClient.send(
          "/action/update-todo-list" + "/" + idProject + "/" + periodCurrentId,
          {},
          JSON.stringify(obj)
        );
      }
    }
  };
  //
  $scope.selected = null;
  $scope.fromList = null;
  $scope.taskEnd = null;

  $scope.onDrop = function (event, index, item, external, type, xx) {
    console.log("Item", item, "dropped into", xx);
    $scope.itemDrag = item;
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
    $scope.listTask.forEach((item) => {
      item.checkShowAddCard = false;
    });
    $scope.listTask[value].checkShowAddCard = true;
    document.querySelectorAll(".card-body").forEach((item) => {
      item.style.maxHeight = "calc(100vh - 207px)";
    });

    setTimeout(() => {
      document.querySelectorAll(".card-body")[value].scrollTop = 100000;
      document.querySelectorAll(".card-body")[value].style.maxHeight =
        "calc(100vh - 175px)";
    }, 1);
  };

  $scope.valueInput = "";

  $scope.addNewCard = function (value) {
    let listTitle = document.querySelectorAll('[name="inputTitle"]');
    $scope.listTask[value].todoList.push({
      name: listTitle[value].value,
    });
    listTitle[value].value = "";
    $scope.listTask[value].checkShowAddCard = false;
  };

  $scope.closeAddNewCard = function (value) {
    $scope.listTask[value].checkShowAddCard = false;
    document.querySelectorAll(".card-body")[value].style.maxHeight =
      "calc(100vh - 207px)";
  };

  let descriptions = "";

  //Detail Todo
  $scope.actionDetailTodo = function (id, index, indexTask, itemListTask) {
    $scope.indexTodoInTask = index;
    $scope.indexTask = indexTask;
    $scope.showDialogShowMenu = false;
    $scope.itemListTask = itemListTask;
    Promise.all([
      MeDetailTodoService.fetchTodo(id),
      MeAssignService.fetchMember(id),
      MeLabelService.fetchLabel(id),
      MeDetailTodoService.fetchDetailTodo(id),
    ]).then(function () {
      $scope.detailTodo = MeDetailTodoService.getTodo();
      $scope.detailTodo.listMemberDetailTodo = $scope.listMemberById.filter(
        (member) => MeAssignService.getMembers().includes(member.id)
      );
      $scope.detailTodo.listLabelDetailTodo = MeLabelService.getLabels();
      $scope.detailTodo.listTaskDetailTodo =
        MeDetailTodoService.getTodoDetail();

      $scope.detailTodo.listTaskDetailTodo.forEach((item) => {
        item.checkShowFormUpdateTodoInTask = false;
      });

      descriptions = $scope.detailTodo.descriptions;

      let count = $scope.detailTodo.listTaskDetailTodo.filter(function (todo) {
        return todo.statusTodo === 1;
      }).length;

      $scope.resetHeightTextarea();

      let progressValue = 0;
      if ($scope.detailTodo.listTaskDetailTodo.length > 0) {
        progressValue = parseInt(
          (count / $scope.detailTodo.listTaskDetailTodo.length) * 100
        );
      }

      document.querySelector("#progressTodo").value = progressValue;
      $scope.$apply();
    });
  };

  $scope.resetHeightTextarea = function () {
    const textarea = document.querySelector(".textarea_custom_detail_todo");
    const rows = $scope.detailTodo.descriptions.split("\n").length;
    const lineHeight =
      parseInt(
        window.getComputedStyle(textarea).getPropertyValue("line-height")
      ) + 2.8;
    const height = rows * lineHeight;
    textarea.style.height = height + "px";
  };

  // popup add member
  $scope.showDialogAddMember = false;

  $scope.openDialogAddMember = function (event) {
    event.stopPropagation();
    $scope.showDialogAddLabelOut = false;
    $scope.showDialogAddLabel = false;
    $scope.showDialogAddPriorityLevel = false;
    $scope.showDialogAddMemberOut = false;
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

  //
  $scope.showDialogAddMemberOut = false;

  $scope.openDialogAddMemberOut = function (event) {
    event.stopPropagation();
    $scope.showDialogAddLabelOut = false;
    $scope.showDialogAddLabel = false;
    $scope.showDialogAddPriorityLevel = false;
    $scope.showDialogAddMember = false;
    if (!$scope.showDialogAddMemberOut) {
      $scope.showDialogAddMemberOut = true;
    } else {
      $scope.showDialogAddMemberOut = false;
    }
    $scope.dialogStyleAddMemberOut = {
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

    $scope.clickOutPopupAddMemberOut();
  };

  $scope.clickOutPopupAddMemberOut = function () {
    document.addEventListener(
      "click",
      function (event) {
        if (
          !document.querySelector(".dialogAddMemberOut").contains(event.target)
        ) {
          $scope.$apply(function () {
            $scope.closeDialogAddMemberOut();
          });
        }
      },
      { once: true }
    );
  };

  $scope.closeDialogAddMemberOut = function () {
    $scope.showDialogAddMemberOut = false;
  };
  //

  $scope.createAssign = function (idMember, idTodo) {
    let headers = {
      idMember: idMember,
      idTodo: idTodo,
      indexTask: $scope.indexTask,
      indexTodoInTask: $scope.indexTodoInTask,
    };

    stompClient.send(
      "/action/create-assign/" +
        idProject +
        "/" +
        localStorage.getItem(idProject),
      {},
      JSON.stringify(headers)
    );
  };

  $scope.deleteAssign = function (idMember, idTodo) {
    let headers = {
      idMember: idMember,
      idTodo: idTodo,
      indexTask: $scope.indexTask,
      indexTodoInTask: $scope.indexTodoInTask,
    };

    stompClient.send(
      "/action/delete-assign/" +
        idProject +
        "/" +
        localStorage.getItem(idProject),
      {},
      JSON.stringify(headers)
    );
  };

  $scope.actionReloadData = function (idTodo, indexTask, indexTodoInTask) {
    Promise.all([MeAssignService.fetchMember(idTodo)]).then(function () {
      const idMembers = MeAssignService.getMembers();
      let listMemberAfter = [];
      idMembers.forEach((meId) => {
        const member = $scope.listMemberById.find((me) => meId === me.id);
        if (member) {
          listMemberAfter.push(member);
        }
      });

      $scope.listTask[indexTask].todoList[indexTodoInTask].members =
        listMemberAfter;

      if ($scope.detailTodo != null) {
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
      }
      $scope.$apply();
    });
    $scope.clickOutPopupAddMember();
    $scope.clickOutPopupAddMemberOut();
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
    $scope.showDialogAddLabelOut = false;
    $scope.showDialogAddMemberOut = false;
    $scope.showDialogAddMember = false;
    $scope.showDialogAddPriorityLevel = false;
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

  //

  $scope.showDialogAddLabelOut = false;

  $scope.openDialogAddLabelOut = function (event) {
    $scope.showDialogAddLabel = false;
    $scope.showDialogAddMemberOut = false;
    $scope.showDialogAddMember = false;
    $scope.showDialogAddPriorityLevel = false;
    event.stopPropagation();
    if (!$scope.showDialogAddLabelOut) {
      $scope.showDialogAddLabelOut = true;
    } else {
      $scope.showDialogAddLabelOut = false;
    }
    $scope.dialogStyleAddLabelOut = {
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

    $scope.clickOutPopupAddLabelOut();
  };

  $scope.clickOutPopupAddLabelOut = function () {
    document.addEventListener(
      "click",
      function (event) {
        if (
          !document.querySelector(".dialogAddLabelOut").contains(event.target)
        ) {
          $scope.$apply(function () {
            $scope.closeDialogAddLabelOut();
          });
        }
      },
      { once: true }
    );
  };

  $scope.closeDialogAddLabelOut = function () {
    $scope.showDialogAddLabelOut = false;
  };

  //

  // Dialog deadline
  $scope.showDialogAddDeadline = false;

  $scope.openDialogAddDeadline = function (event) {
    event.stopPropagation();
    $scope.showDialogAddLabelOut = false;
    $scope.showDialogAddLabel = false;
    $scope.showDialogAddPriorityLevel = false;
    $scope.showDialogAddDeadlineOut = false;
    if (!$scope.showDialogAddDeadline) {
      $scope.showDialogAddDeadline = true;
    } else {
      $scope.showDialogAddDeadline = false;
    }
    $scope.dialogStyleAddDeadline = {
      top: event.clientY - 100 + "px",
      left: event.clientX - 450 + "px",
    };

    $scope.clickOutPopupAddDeadline();
  };

  $scope.clickOutPopupAddDeadline = function () {
    document.addEventListener(
      "click",
      function (event) {
        if (
          !document.querySelector(".dialogAddDeadline").contains(event.target)
        ) {
          $scope.$apply(function () {
            $scope.closeDialogAddDeadline();
          });
        }
      },
      { once: true }
    );
  };

  $scope.closeDialogAddDeadline = function () {
    $scope.showDialogAddDeadline = false;
  };
  //
  $scope.createLabelTodo = function (idLabel, idTodo) {
    let headers = {
      idLabel: idLabel,
      idTodo: idTodo,
      indexTask: $scope.indexTask,
      indexTodoInTask: $scope.indexTodoInTask,
    };
    let periodCurrentId = localStorage.getItem(idProject);
    stompClient.send(
      "/action/create-label-todo" + "/" + idProject + "/" + periodCurrentId,
      {},
      JSON.stringify(headers)
    );
  };

  $scope.deleteLabelTodo = function (idLabel, idTodo) {
    let headers = {
      idLabel: idLabel,
      idTodo: idTodo,
      indexTask: $scope.indexTask,
      indexTodoInTask: $scope.indexTodoInTask,
    };
    let periodCurrentId = localStorage.getItem(idProject);
    stompClient.send(
      "/action/delete-label-todo" + "/" + idProject + "/" + periodCurrentId,
      {},
      JSON.stringify(headers)
    );
  };

  $scope.actionReloadDataLabel = function (idTodo, indexTask, indexTodoInTask) {
    Promise.all([
      MeLabelService.fetchLabels(),
      MeLabelService.fetchLabel(idTodo),
    ]).then(function () {
      if ($scope.detailTodo != null) {
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
      }

      $scope.listTask[indexTask].todoList[indexTodoInTask].labels =
        MeLabelService.getLabels();

      $scope.$apply();
    });
    $scope.clickOutPopupAddLabel();
    $scope.clickOutPopupAddLabelOut();
  };

  $scope.changeCheckboxLabel = function (idLabel, idTodo, checkLabel) {
    if (checkLabel) {
      $scope.deleteLabelTodo(idLabel, idTodo);
    } else {
      $scope.createLabelTodo(idLabel, idTodo);
    }
  };

  // Popup độ ưu tiên
  $scope.showDialogAddPriorityLevel = false;

  $scope.openDialogAddPriorityLevel = function (event) {
    event.stopPropagation();
    $scope.showDialogAddLabelOut = false;
    $scope.showDialogAddLabel = false;
    $scope.showDialogAddMember = false;
    $scope.showDialogAddMemberOut = false;
    if (!$scope.showDialogAddPriorityLevel) {
      $scope.showDialogAddPriorityLevel = true;
    } else {
      $scope.showDialogAddPriorityLevel = false;
    }
    $scope.dialogStyleAddPriorityLevel = {
      top: event.clientY - 100 + "px",
      left: event.clientX - 450 + "px",
    };

    $scope.clickOutPopupAddPriorityLevel();
  };

  $scope.clickOutPopupAddPriorityLevel = function () {
    document.addEventListener(
      "click",
      function (event) {
        if (
          !document
            .querySelector(".dialogAddPriorityLevel")
            .contains(event.target)
        ) {
          $scope.$apply(function () {
            $scope.closeDialogAddPriorityLevel();
          });
        }
      },
      { once: true }
    );
  };

  $scope.closeDialogAddPriorityLevel = function () {
    $scope.showDialogAddPriorityLevel = false;
  };

  $scope.actionChangePriorityLevel = function (priorityLevel, idTodo) {
    let obj = {
      idTodo: idTodo,
      priorityLevel: priorityLevel,
      indexTask: $scope.indexTask,
      indexTodoInTask: $scope.indexTodoInTask,
    };
    let periodCurrentId = localStorage.getItem(idProject);
    stompClient.send(
      "/action/update-priority-todo" + "/" + idProject + "/" + periodCurrentId,
      {},
      JSON.stringify(obj)
    );
  };

  $scope.actionReloadDataPriorityTodo = function (
    idTodo,
    indexTask,
    indexTodoInTask
  ) {
    Promise.all([MeDetailTodoService.fetchTodo(idTodo)]).then(function () {
      if ($scope.detailTodo != null) {
        $scope.detailTodo.priorityLevel =
          MeDetailTodoService.getTodo().priorityLevel;
      }
      let priorityLevel = MeDetailTodoService.getTodo().priorityLevel;
      $scope.listTask[indexTask].todoList[indexTodoInTask].priorityLevel =
        priorityLevel == "QUAN_TRONG"
          ? 0
          : priorityLevel == "CAO"
          ? 1
          : priorityLevel == "TRUNG_BINH"
          ? 2
          : 3;

      $scope.$apply();
    });

    $scope.clickOutPopupAddPriorityLevel();
  };

  //

  $scope.showDialogShowMenu = false;

  $scope.openDialogShowMenu = function (event) {
    event.stopPropagation();
    $scope.showDialogAddLabelOut = false;
    $scope.showDialogAddLabel = false;
    $scope.showDialogAddMember = false;
    $scope.showDialogAddMemberOut = false;
    $scope.showDialogAddPriorityLevel = false;

    $scope.valuePeriod = localStorage.getItem(idProject);

    if (!$scope.showDialogShowMenu) {
      $scope.showDialogShowMenu = true;
    } else {
      $scope.showDialogShowMenu = false;
    }
    $scope.dialogStyleShowMenu = {
      top: event.clientY - 100 + "px",
      left: event.clientX - 450 + "px",
    };

    $scope.clickOutPopupShowMenu();
  };

  $scope.clickOutPopupShowMenu = function () {
    document.addEventListener(
      "click",
      function (event) {
        if (!document.querySelector(".dialogShowMenu").contains(event.target)) {
          $scope.$apply(function () {
            $scope.closeDialogShowMenu();
          });
        }
      },
      { once: true }
    );
  };

  $scope.closeDialogShowMenu = function () {
    $scope.showDialogShowMenu = false;
  };

  $scope.valueAddTodoInChecklist = "";
  $scope.showAddTodoInList = false;

  $scope.closeAddTodoInCheckList = function () {
    $scope.showAddTodoInList = false;
    $scope.valueAddTodoInChecklist = "";
  };

  $scope.addNewTodoInCheckList = function (index, idTodo) {
    if ($scope.valueAddTodoInChecklist.length == 0) {
      toastr.error("Tên đầu việc không được để trống !", "Thông báo!", {
        closeButton: true,
        progressBar: true,
        positionClass: "toast-top-center",
      });
    } else if ($scope.valueAddTodoInChecklist.length > 100) {
      toastr.error("Tên đầu việc không quá 100 kí tự !", "Thông báo!", {
        closeButton: true,
        progressBar: true,
        positionClass: "toast-top-center",
      });
    } else {
      let obj = {
        name: $scope.valueAddTodoInChecklist,
        idTodo: idTodo,
        indexTask: $scope.indexTask,
        indexTodoInTask: $scope.indexTodoInTask,
      };
      let periodCurrentId = localStorage.getItem(idProject);
      stompClient.send(
        "/action/create-todo-checklist" +
          "/" +
          idProject +
          "/" +
          periodCurrentId,
        {},
        JSON.stringify(obj)
      );
      toastr.success("Thêm đầu việc thành công !", "Thông báo!", {
        closeButton: true,
        progressBar: true,
        positionClass: "toast-top-center",
      });
      $scope.valueAddTodoInChecklist = "";
      $scope.closeAddTodoInCheckList();
    }
  };

  $scope.showSaveTodoInCheckList = function (index, name, event) {
    if (
      !event.target.classList.contains("form-check-input") &&
      !event.target.classList.contains("div_remove_todo_in_checklist") &&
      !event.target.classList.contains("remove_todo_in_checklist")
    ) {
      $scope.detailTodo.listTaskDetailTodo.forEach((item) => {
        item.checkShowFormUpdateTodoInTask = false;
      });
      $scope.detailTodo.listTaskDetailTodo[
        index
      ].checkShowFormUpdateTodoInTask = true;
      $(".textarea_update_todo_in_task").eq(index).val(name);
    }
  };

  $scope.saveTodoInCheckList = function (index, id) {
    if ($(".textarea_update_todo_in_task").eq(index).val().length === 0) {
      toastr.error("Tên đầu việc không được để trống !", "Thông báo!", {
        closeButton: true,
        progressBar: true,
        positionClass: "toast-top-center",
      });
    } else if (
      $(".textarea_update_todo_in_task").eq(index).val().length > 100
    ) {
      toastr.error("Tên đầu việc tối đa 100 kí tự !", "Thông báo!", {
        closeButton: true,
        progressBar: true,
        positionClass: "toast-top-center",
      });
    } else {
      let obj = {
        name: $(".textarea_update_todo_in_task").eq(index).val(),
        idTodo: id,
      };
      let periodCurrentId = localStorage.getItem(idProject);
      stompClient.send(
        "/action/update-todo-checklist" +
          "/" +
          idProject +
          "/" +
          periodCurrentId,
        {},
        JSON.stringify(obj)
      );
      toastr.success("Cập nhật thành công !", "Thông báo!", {
        closeButton: true,
        progressBar: true,
        positionClass: "toast-top-center",
      });
      $(".textarea_update_todo_in_task").eq(index).val("");
      $scope.closeSaveTodoInCheckList(index);
    }
  };

  $scope.closeSaveTodoInCheckList = function (index) {
    $timeout(function () {
      $scope.detailTodo.listTaskDetailTodo[
        index
      ].checkShowFormUpdateTodoInTask = false;
    });
  };

  $scope.actionReloadTodoInCheckList = function (message) {
    let obj = JSON.parse(message.body).data.data;
    let indexTask = JSON.parse(message.body).data.indexTask;
    let indexTodoInTask = JSON.parse(message.body).data.indexTodoInTask;
    let numberTodoComplete = JSON.parse(message.body).data.numberTodoComplete;
    let numberTodo = JSON.parse(message.body).data.numberTodo;

    if ($scope.detailTodo != null) {
      let newTodo = {
        id: obj.id,
        code: obj.code,
        name: obj.name,
        statusTodo: obj.statusTodo == "CHUA_HOAN_THANH" ? 0 : 1,
        checkShowFormUpdateTodoInTask: false,
      };
      $scope.detailTodo.listTaskDetailTodo.unshift(newTodo);
      if (numberTodo > 0) {
        document.querySelector("#progressTodo").value = parseInt(
          (numberTodoComplete / numberTodo) * 100
        );
      } else {
        document.querySelector("#progressTodo").value = 0;
      }
    }
    $scope.listTask[indexTask].todoList[indexTodoInTask].numberTodoComplete =
      numberTodoComplete;
    $scope.listTask[indexTask].todoList[indexTodoInTask].numberTodo =
      numberTodo;
    $scope.listTask[indexTask].todoList[indexTodoInTask].progressOfTodo =
      parseInt((numberTodoComplete / numberTodo) * 100);
    $scope.$apply();
  };

  $scope.actionReloadSaveTodoInCheckList = function (message) {
    if ($scope.detailTodo != null) {
      let obj = JSON.parse(message.body).data;
      $scope.detailTodo.listTaskDetailTodo.forEach((item) => {
        if (item.id == obj.id) {
          item.name = obj.name;
        }
      });
      $scope.$apply();
    }
  };

  $scope.changeProgressTodo = function (idTodo, statusTodo) {
    let obj = {
      idTodo: idTodo,
      statusTodo: statusTodo,
      todoId: $scope.detailTodo.id,
      indexTask: $scope.indexTask,
      indexTodoInTask: $scope.indexTodoInTask,
    };
    let periodCurrentId = localStorage.getItem(idProject);
    stompClient.send(
      "/action/update-statustodo-todo-checklist" +
        "/" +
        idProject +
        "/" +
        periodCurrentId,
      {},
      JSON.stringify(obj)
    );
  };

  $scope.actionReloadTodoList = function (message) {
    let idTodoList = JSON.parse(message.body).data.data;
    let indexBefore = JSON.parse(message.body).data.indexBefore;
    let indexAfter = JSON.parse(message.body).data.indexAfter;

    let objTodoList = $scope.listTask.find((tdlst) => tdlst.id === idTodoList);

    $scope.listTask.splice(indexBefore, 1);
    $scope.listTask.splice(indexAfter, 0, objTodoList);

    $scope.$apply();
  };

  $scope.actionReloadTodoInCheckListByUpdateStatusTodo = function (message) {
    let obj = JSON.parse(message.body).data.data;
    let indexTask = JSON.parse(message.body).data.indexTask;
    let indexTodoInTask = JSON.parse(message.body).data.indexTodoInTask;
    let numberTodoComplete = JSON.parse(message.body).data.numberTodoComplete;
    let numberTodo = JSON.parse(message.body).data.numberTodo;
    if ($scope.detailTodo != null) {
      $scope.detailTodo.listTaskDetailTodo.forEach((item) => {
        if (item.id == obj.id) {
          item.statusTodo = obj.statusTodo == "CHUA_HOAN_THANH" ? 0 : 1;
        }
      });
      if (numberTodo > 0) {
        document.querySelector("#progressTodo").value = parseInt(
          (numberTodoComplete / numberTodo) * 100
        );
      } else {
        document.querySelector("#progressTodo").value = 0;
      }
    }
    $scope.listTask[indexTask].todoList[indexTodoInTask].numberTodoComplete =
      numberTodoComplete;
    $scope.listTask[indexTask].todoList[indexTodoInTask].numberTodo =
      numberTodo;
    $scope.listTask[indexTask].todoList[indexTodoInTask].progressOfTodo =
      parseInt((numberTodoComplete / numberTodo) * 100);
    $scope.$apply();
  };

  $scope.removeTodoInCheckList = function (id) {
    let obj = {
      id: id,
      todoId: $scope.detailTodo.id,
      indexTask: $scope.indexTask,
      indexTodoInTask: $scope.indexTodoInTask,
    };
    let periodCurrentId = localStorage.getItem(idProject);
    stompClient.send(
      "/action/delete-todo-checklist" + "/" + idProject + "/" + periodCurrentId,
      {},
      JSON.stringify(obj)
    );
    toastr.success("Xóa thành công !", "Thông báo!", {
      closeButton: true,
      progressBar: true,
      positionClass: "toast-top-center",
    });
  };

  $scope.actionReloadTodoInCheckListDelete = function (message) {
    let id = JSON.parse(message.body).data.data;
    let indexTask = JSON.parse(message.body).data.indexTask;
    let indexTodoInTask = JSON.parse(message.body).data.indexTodoInTask;
    let numberTodoComplete = JSON.parse(message.body).data.numberTodoComplete;
    let numberTodo = JSON.parse(message.body).data.numberTodo;
    if ($scope.detailTodo != null) {
      for (let i = 0; i < $scope.detailTodo.listTaskDetailTodo.length; i++) {
        if ($scope.detailTodo.listTaskDetailTodo[i].id === id) {
          $scope.detailTodo.listTaskDetailTodo.splice(i, 1);
          break;
        }
      }
      if (numberTodo > 0) {
        document.querySelector("#progressTodo").value = parseInt(
          (numberTodoComplete / numberTodo) * 100
        );
      } else {
        document.querySelector("#progressTodo").value = 0;
      }
    }
    $scope.listTask[indexTask].todoList[indexTodoInTask].numberTodoComplete =
      numberTodoComplete;
    $scope.listTask[indexTask].todoList[indexTodoInTask].numberTodo =
      numberTodo;
    $scope.listTask[indexTask].todoList[indexTodoInTask].progressOfTodo =
      parseInt((numberTodoComplete / numberTodo) * 100);
    $scope.$apply();
  };

  // const textarea = document.querySelector(".textarea_custom_detail_todo");
  // textarea.style.height = textarea.scrollHeight + "px";

  // textarea.addEventListener("input", function () {
  //   const lines = this.value.split("\n").filter((line) => line.trim() !== "");
  //   const rowCount = lines.length;
  //   this.style.height = `${rowCount * 25}px`;
  // });

  const textarea = document.querySelector(".textarea_custom_detail_todo");
  let previousRowCount = 1;
  let nonEmptyRowCount = 1;

  textarea.addEventListener("input", function () {
    const lines = this.value.split("\n");
    nonEmptyRowCount = lines.filter((line) => line.trim() !== "").length;

    if (nonEmptyRowCount > 0 && nonEmptyRowCount !== previousRowCount) {
      const nonEmptyLines = lines.slice(-nonEmptyRowCount);
      this.style.height = `${nonEmptyRowCount * 25}px`;
      previousRowCount = nonEmptyRowCount;
    }
  });

  $scope.saveDescriptionsTodo = function () {
    const descriptionsNew = $scope.detailTodo.descriptions;
    if (descriptionsNew.length > 1000) {
      toastr.error("Mô tả tối đa 1000 ký tự !", "Thông báo!", {
        closeButton: true,
        progressBar: true,
        positionClass: "toast-top-center",
      });
      return;
    }
    if (descriptionsNew === descriptions) {
      return;
    }
    const lines = descriptionsNew.split("\n").filter((line, index, arr) => {
      const trimmedLine = line.trim();
      return index !== arr.length - 1 || trimmedLine !== "";
    });
    let obj = {
      idTodo: $scope.detailTodo.id,
      descriptions: lines.join("\n"),
      indexTask: $scope.indexTask,
      indexTodoInTask: $scope.indexTodoInTask,
    };
    let periodCurrentId = localStorage.getItem(idProject);
    stompClient.send(
      "/action/update-descriptions-todo" +
        "/" +
        idProject +
        "/" +
        periodCurrentId,
      {},
      JSON.stringify(obj)
    );
    toastr.success("Lưu mô tả thành công !", "Thông báo!", {
      closeButton: true,
      progressBar: true,
      positionClass: "toast-top-center",
    });
  };

  $scope.actionReloadTodoDescriptions = function (message) {
    let obj = JSON.parse(message.body).data.data;
    let indexTask = JSON.parse(message.body).data.indexTask;
    let indexTodoInTask = JSON.parse(message.body).data.indexTodoInTask;
    if ($scope.detailTodo != null && message != null) {
      $scope.detailTodo.descriptions = obj.descriptions;
      descriptions = obj.descriptions;
    }
    $scope.listTask[indexTask].todoList[indexTodoInTask].descriptions =
      obj.descriptions;
    $scope.$apply();
  };

  $scope.resetDescriptionsTodo = function () {
    $scope.detailTodo.descriptions = descriptions;
    $scope.resetHeightTextarea();
  };

  // Subscribe websocket
  stompClient.connect({}, function (frame) {
    let sessionId = /\/([^\/]+)\/websocket/.exec(
      stompClient.ws._transport.url
    )[1];
    let periodCurrentId = localStorage.getItem(idProject);

    // Message bắt lỗi trả về cho client thực hiện thao tác
    stompClient.subscribe(
      "/portal-projects/error/" + sessionId,
      function (message) {
        var errorObject = JSON.parse(message.body);
        toastr.error(errorObject.errorMessage, "Lỗi", {
          closeButton: true,
          progressBar: true,
          positionClass: "toast-top-center",
        });
      }
    );

    // Message gửi cho các client đang ở trong phòng
    stompClient.subscribe(
      "/portal-projects/update-descriptions-todo" +
        "/" +
        idProject +
        "/" +
        periodCurrentId,
      function (message) {
        $scope.actionReloadTodoDescriptions(message);
      },
      function (error) {
        alert(error);
      }
    );

    stompClient.subscribe(
      "/portal-projects/assign" + "/" + idProject + "/" + periodCurrentId,
      function (message) {
        if (JSON.parse(message.body).data.data.todoId != null) {
          $scope.actionReloadData(
            JSON.parse(message.body).data.data.todoId,
            JSON.parse(message.body).data.indexTask,
            JSON.parse(message.body).data.indexTodoInTask
          );
        } else {
          $scope.actionReloadData(
            JSON.parse(message.body).data.data,
            JSON.parse(message.body).data.indexTask,
            JSON.parse(message.body).data.indexTodoInTask
          );
        }
      }
    );

    stompClient.subscribe(
      "/portal-projects/label-todo" + "/" + idProject + "/" + periodCurrentId,
      function (message) {
        console.log(message);
        if (JSON.parse(message.body).data.data.todoId != null) {
          $scope.actionReloadDataLabel(
            JSON.parse(message.body).data.data.todoId,
            JSON.parse(message.body).data.indexTask,
            JSON.parse(message.body).data.indexTodoInTask
          );
        } else {
          $scope.actionReloadDataLabel(
            JSON.parse(message.body).data.data,
            JSON.parse(message.body).data.indexTask,
            JSON.parse(message.body).data.indexTodoInTask
          );
        }
      }
    );

    stompClient.subscribe(
      "/portal-projects/todo" + "/" + idProject + "/" + periodCurrentId,
      function (message) {
        $scope.actionReloadDataPriorityTodo(
          JSON.parse(message.body).data.data.id,
          JSON.parse(message.body).data.indexTask,
          JSON.parse(message.body).data.indexTodoInTask
        );
      }
    );

    stompClient.subscribe(
      "/portal-projects/todo-list" + "/" + idProject + "/" + periodCurrentId,
      function (message) {
        $scope.actionReloadTodoList(message);
      }
    );

    stompClient.subscribe(
      "/portal-projects/create-todo-checklist" +
        "/" +
        idProject +
        "/" +
        periodCurrentId,
      function (message) {
        $scope.actionReloadTodoInCheckList(message);
      }
    );

    stompClient.subscribe(
      "/portal-projects/update-todo-checklist" +
        "/" +
        idProject +
        "/" +
        periodCurrentId,
      function (message) {
        $scope.actionReloadSaveTodoInCheckList(message);
      }
    );

    stompClient.subscribe(
      "/portal-projects/update-statustodo-todo-checklist" +
        "/" +
        idProject +
        "/" +
        periodCurrentId,
      function (message) {
        $scope.actionReloadTodoInCheckListByUpdateStatusTodo(message);
      }
    );

    stompClient.subscribe(
      "/portal-projects/delete-todo-checklist" +
        "/" +
        idProject +
        "/" +
        periodCurrentId,
      function (message) {
        $scope.actionReloadTodoInCheckListDelete(message);
      }
    );
  });

  $scope.checkShowAddTodoList = false;

  $scope.closeAddTodoList = function () {
    $scope.checkShowAddTodoList = false;
    $scope.$apply();
  };
};
