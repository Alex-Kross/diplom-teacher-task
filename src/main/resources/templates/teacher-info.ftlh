<!DOCTYPE html>
<html>
<head>
    <title>BUYSELL</title>
</head>
<body>
<h1>BUYSELL</h1><hr>
<#list files as file>
    <a href="/files/${file.id}"  download="" title="всплывающая подсказка">скачать файл ${file.fileName}</a>
<#--    <img src="/files/${file.id}" height="20px"/><br><br>-->
</#list>
<h4>Подробная информация о преподе</h4>
<b>Имя: </b>${teacher.name}<br>
<b>Фамилия: </b>${teacher.surname}<br>
<b>Отчетсво: </b>${teacher.patronymic}<br>
<b>Должность: </b>${teacher.position}<br>
<b>email: </b>${teacher.email}<br>
<b>пароль: </b>${teacher.password}<br>
<hr>
<form action="/teachers/teacher/delete/${teacher.id}" method="post">
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <input type="submit" value="Удалить учителя"/>
</form>
</body>
</html>