<html>
<head>
    <title>login</title>
</head>
<body>
    <h3>${message!}</h3>
    <form action="/login" method="post">
        <input type="text" name="phone" placeholder="手机号">
        <input type="password" name="password" placeholder="密码">
        <button type="submit">提交</button>
    </form>
</body>
</html>