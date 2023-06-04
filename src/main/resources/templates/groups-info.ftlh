<!DOCTYPE html>
<html>
<head>
    <title>BUYSELL</title>
</head>
<body>
<h1>BUYSELL</h1><hr>
<#--<#list files as img>-->
<#--    <img src="/images/${img.id}" height="60px"/><br><br>-->
<#--</#list>-->
<h4>Подробная информация о группе</h4>
<b>Номер группы: </b>${group.name}<br>
<b>Факультет: </b>${group.facultyName}<br>
<b>Университет: </b>${group.universityName}<br>
<hr>
Загрузить список студентов: <input type="file" name="file1"/><br><br>
<form action="/group/delete/${group.id}" method="post">
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <input type="submit" value="Удалить группу"/>
</form>
</body>
</html>