let getAll = function () {
  // let token =
  //   "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxIiwiaWF0IjoxNjY0OTcyNDM4LCJleHAiOjE2NjU1NzcyMzh9.mbVvyQcsUtmJPKLWhIoKKfLJQsdVQZ1NN72MYmoiezsnRB8-0fPMjJQzutFPIzZTAROf8LmdK86yxWL3OWVa9g";
  // console.log(token);
  let token = localStorage.getItem("currentToken");

  axios({
    url: "http://localhost:8080/api/user/all",
    method: "GET",
    headers: { Authorization: `Bearer ${token}` },
  })
    .then(function (resp) {
      //Truy xuất đến thẻ tbody (nơi chứa giao diện danh sách user)
      let tbody = document.getElementById("users");
      console.log(resp); //test

      let content = "";
      for (let user of resp.data) {
        content += `
                    <tr>
                        <td>${user.id}</td>
                        <td>${user.email}</td>
                        <td>${user.fullname}</td>
                        <td>${user.address}</td>
                        <td>${user.phone}</td>
                        <td>${user.avatar}</td>
                        <td class="align-middle">
                            <span class="btn-group-vertical">
                                <a type="button" data-toggle="modal" data-target="#editForm" 
                                onclick="setEditId(${user.id})" class="btn btn-sm mb-1 btn-success"> Sửa </a>
                                <a class="btn btn-sm mb-1 btn-danger"
                                    onclick="onDelete(${user.id})">Xóa</a>
                            </span>
                        </td>
                    </tr>
                `;
      }

      // Thay đổi nội dung thẻ tbody
      tbody.innerHTML = content;
    })
    .catch(function (err) {
      console.log(err.response.data);
    });
};

let onDelete = function (id) {
  let token = localStorage.getItem("currentToken");
  axios({
    url: `http://localhost:8080/api/user/remove/${id}`,
    method: "DELETE",
    headers: { Authorization: `Bearer ${token}` },
  })
    .then(function (resp) {
      console.log(resp);
      getAll();
    })
    .catch(function (err) {
      console.log(err.response);
    });
};

let onAdd = function () {
  let token = localStorage.getItem("currentToken");
  var body = {
    username: document.getElementById("addUsername").value,
    email: document.getElementById("addEmail").value,
    password: document.getElementById("addPassword").value,
    fullname: document.getElementById("addFullName").value,
    address: document.getElementById("addAddress").value,
    phone: document.getElementById("addPhone").value,
    avatar: document.getElementById("addAvatar").value,
    roleId: document.getElementById("addRoleId").value,
    activated: document.getElementById("addActivated").value,
  };

  axios({
    url: `http://localhost:8080/api/user/add`,
    method: "POST",
    headers: { Authorization: `Bearer ${token}` },
    data: body,
  })
    .then(function (resp) {
      console.log(resp);
      getAll();
    })
    .catch(function (err) {
      console.log(err.response);
    });
};

let setEditId = function (id) {
  document.getElementById("editId").value = id;
};

let onEdit = function () {
  let token = localStorage.getItem("currentToken");
  var id = document.getElementById("editId").value;
  console.log(id);
  var body = {
    id: document.getElementById("editId").value,
    username: document.getElementById("editUsername").value,
    email: document.getElementById("editEmail").value,
    password: document.getElementById("editPassword").value,
    fullname: document.getElementById("editFullName").value,
    address: document.getElementById("editAddress").value,
    phone: document.getElementById("editPhone").value,
    avatar: document.getElementById("editAvatar").value,
    roleId: document.getElementById("editRoleId").value,
    activated: document.getElementById("editActivated").value,
  };

  axios({
    url: `http://localhost:8080/api/user/edit/${id}`,
    method: "PUT",
    headers: { Authorization: `Bearer ${token}` },
    data: body,
  })
    .then(function (resp) {
      console.log(resp);
      getAll();
    })
    .catch(function (err) {
      console.log(err.response);
    });
};

getAll();
