<!DOCTYPE html>
<html>
<head>
    <title>BUYSELL</title>
</head>
<body>
<h1>BUYSELL</h1><hr>
<h4>Товары со всей России</h4>
<form action="/" method="get">
    Поиск по названию объявления: <input type="text" name="name"><br>
    <input type="submit" value="Поиск"/>
</form>
<#list groups as group>
    <div>
        <p><b>${group.name}</b> ${group.facultyName} ${group.universityName}  | <a href="/group/${group.id}">Подробнее...</a></p>
    </div>
<#else>
    <h3>Товаров нет</h3>
</#list>
<hr>
<h3>Создать новую группу</h3>
<form action="/group/create" method="post">
    Номер группы: <input type="text" name="name"/><br><br>
    Факультет: <input type="text" name="facultyName"/><br><br>
    Университет: <input type="text" name="universityName"/><br><br>
    <input type="submit" value="Добавить группу"/>
</form>
</body>
</html>