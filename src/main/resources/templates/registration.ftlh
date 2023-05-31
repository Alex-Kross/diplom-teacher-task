<!DOCTYPE html>
<html>
<head>
    <title>BUYSELL</title>
</head>
<body>
<h1>BUYSELL</h1><hr>
<h4>Регистрация</h4>
<form action="/registration" method="post">
    Имя: <input type="text" name="name"/><br><br>
    Фамилия: <input type="text" name="surname"/><br><br>
    Отчетсво: <input type="text" name="patronymic"/><br><br>
    почта: <input type="email" name="email"/><br><br>
    должность: <input type="text" name="position"/><br><br>
    пароль: <input type="text" name="password"/><br><br>
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <input type="submit" value="Вход"/>
</form>
<#if errorMessage??>
    <h2 style="color: red">${errorMessage}</h2>
</#if>
</body>
</html>