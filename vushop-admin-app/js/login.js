function login() {
  let requestBody = {
    usernameOrEmail: $("input[name='usernameOrEmail']").val(),
    password: $("input[name='password']").val(),
  };

  console.log(requestBody);
  console.log(typeof requestBody);

  axios({
    url: `http://localhost:8080/api/auth/login`,
    method: "POST",
    responseType: "json",
    contentType: "application/json",
    data: requestBody,
  })
    .then(function (response) {
      console.log(response);
      console.log(response.status);
      console.log(response.data.token);

      if (response.status == 200) {
        localStorage.setItem("currentToken", response.data.token);
        window.location.href = "/index.html";
      }
    })
    .catch(function (err) {
      alert("Sai thông tin đăng nhập!");
      console.log(err.response);
    });
}
