<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF8">
    <meta title="resume">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name='viewport' content='width=device-width, initial-scale=1.0'>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Lato&family=Raleway&display=swap" rel="stylesheet">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel='stylesheet' href='/static/css/dis-style.css'>
</head>
<body>
<h1>BUYSELL</h1><hr>
    <h1>
        <a href="/download/files/2"  download="" title="всплывающая подсказка">скачать файл шаблон списка тем</a>
    </h1>
    <br>
<h2>Группа ${group.name}</h2>
    <h1>
        <#if students??>
<#--        <select name="select-student" class="form-control border-0 mb-1  rounded shadow" id="group-edit">-->
        <select name="select-student" class="form-select" multiple aria-label="multiple select example" id="group-edit">
            <option value="0" label="--- Выбрать ---" />
            <#list students as student>
                <option value="${student.id}" label="${student.ordinalNumber} | ${student.surname} ${student.name} ${student.patronymic}"></option>
            </#list>
        </select>
        <#else>
        <h3>Список студентов не добавлен</h3>
        </#if>
    </h1>


<form action="/dis/upload-topic" method="post" >
    <input type="file" name="listTopicFile" value="Выбрать список тем"/>
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <input type="submit" value="Добавить файл"/>
</form>

</body>
</html>