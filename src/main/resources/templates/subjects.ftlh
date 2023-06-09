<!DOCTYPE html>
<html>
<head>
    <title>BUYSELL</title>
</head>
<body>
<h1>BUYSELL</h1><hr>
<h4>Предметы</h4>
<form action="/" method="get">
    Поиск по названию объявления: <input type="text" name="title"><br>
    <input type="submit" value="Поиск"/>
</form>
<#list teachers as teacher>
    <div>
        <p><b>${teacher.name}</b> ${teacher.surname} руб. | <a href="/teachers/teacher/${teacher.id}">Подробнее...</a></p>
    </div>
<#else>
    <h3>Предметов нет</h3>
</#list>
<hr>
<h3>Создать новый предмет</h3>
<form action="/teachers/teacher/create" method="post" enctype="multipart/form-data">
    Имя: <input type="text" name="surname"/><br><br>
    Отчетсво: <input type="text" name="name"/><br><br>
    Фамилия: <input type="text" name="patronymic"/><br><br>
    почта: <input type="email" name="email"/><br><br>
    пароль: <input type="text" name="password"/><br><br>
    должность: <input type="text" name="position"/><br><br>

    Первая фотография: <input type="file" name="file1"/><br><br>
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <input type="submit" value="Добавить Учителей"/>
</form>
</body>
</html>