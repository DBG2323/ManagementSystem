document.getElementById('loginForm').addEventListener('submit', function(e) {
    e.preventDefault();
    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;
    
    api.login(username, password).then(data => {
        if (data.token) {
            localStorage.setItem('token', data.token);
            window.location.href = 'dashboard.html';
        } else {
            alert('登录失败：' + (data.message || '用户名或密码错误'));
        }
    }).catch(err => {
        alert('登录失败，请检查后端服务是否启动');
    });
});