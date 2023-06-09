<!DOCTYPE html>
<html>
<head>
    <title>BUYSELL</title>
</head>
<body>
<h1>BUYSELL</h1><hr>
<#if teacher.email??>
    <h3>Имя пользователя: <i>${teacher.name}</i></h3>
    <form action="/logout" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <input type="submit" value="Выйти"/>
    </form>
<#else>
    <a href="/login">Войти</a></h1>
</#if>
<h4>Группы</h4>
<form action="/" method="get">
    Поиск по названию объявления: <input type="text" name="name"><br>
    <input type="submit" value="Поиск"/>
</form>
<#list groups as group>
    <div>
        <p><b>${group.name}</b> ${group.facultyName} ${group.universityName}
            | <a href="/group/${group.id}">Подробнее...</a></p>
    </div>
<#else>
    <h3>Групп нету</h3>
</#list>
<#if teacher.email??>
<hr>
    <form action="/group/create-page" method="get">
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <input type="submit" value="Добавить группу" />
    </form>
<#--<button type="button" value="Добавить группу" href=""/>-->
<#--<h3>Создать новую группу</h3>-->
<#--<form action="/group/create" method="post" enctype="multipart/form-data">-->
<#--    Номер группы: <input type="text" name="name"/><br><br>-->
<#--    Факультет: <input type="text" name="facultyName"/><br><br>-->
<#--    Университет: <input type="text" name="universityName"/><br><br>-->

<#--    Загрузить список студентов: <input type="file" name="listStudentFile"/><br><br>-->
<#--    <input type="hidden" name="_csrf" value="${_csrf.token}">-->
<#--    <input type="submit" value="Добавить группу"/>-->
<#--</form>-->
</#if>
</body>
</html>