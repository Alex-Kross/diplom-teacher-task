<!DOCTYPE html>
<html>
<head>
    <title>BUYSELL</title>
</head>
<body>
<h1>BUYSELL</h1><hr>
<#--<#list files as file>-->
<#--    <a href="/files/${file.id}"  download="" title="всплывающая подсказка">скачать файл ${file.fileName}</a>-->
<#--&lt;#&ndash;    <img src="/files/${file.id}" height="20px"/><br><br>&ndash;&gt;-->
<#--</#list>-->
<#--<#if group.name??>-->
<h4>Подробная информация о группе</h4>
<b>Номер группы: </b>${group.name}<br>
<b>Факультет: </b>${group.facultyName}<br>
<b>Университет: </b>${group.universityName}<br>
<hr>
<#--<#else>-->
<#--    <a href="/login">Войти</a></h1>-->
<#--</#if>-->
<form action="/group/delete/${group.id}" method="post">
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <input type="submit" value="Удалить группу"/>
</form>

<form action="/group/${group.id}/subject/create" method="post">
    Название предмета: <input type="text" name="name"/><br><br>
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <input type="submit" value="Создать предмет"/>
</form>
<#if students?size != 0>
<table>
    <tr class="tableHeader">
        <td>Фамилия имя отчество</td>
        <td>Email</td>
        <td>Телефон</td>
        <td>ФИО родителя</td>
        <td>Email родителя</td>
        <td>Телефон родителя</td>
    </tr>
<#--    <#foreach student in students>-->
    <#list students as student>
        <tr class="tableBody">
            <td>${student.surname} ${student.name} ${student.patronymic}  </td>
            <td>${student.email}</td>
            <td>${student.phone}</td>
            <td>${student.parentFullName}</td>
            <td>${student.parentEmail}</td>
            <td>${student.parentPhone}</td>
<#--            <td>${order.ioGuid}</td>-->
<#--            <td>${order.lastUpdateDate?datetime}</td>-->
<#--            <td>${order.lastUpdatedByUser}</td>-->
        </tr>
    <#else>
        <h3>Список студентов не добавлен</h3>
    </#list>
<#--    </#foreach>-->
</table>
<#else>
    <h3>Список студентов не добавлен</h3>
    <br>
    <form action="/group/${group.id}/file/upload" method="post" enctype="multipart/form-data">
        <input type="file" name="listStudentFile" value="Выбрать файл списка студентов"/>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <input type="submit" value="Добавить список"/>
    </form>


</#if>
</body>
</html>