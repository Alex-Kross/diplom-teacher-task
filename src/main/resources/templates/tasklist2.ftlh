<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF8">
    <meta title="resume">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name='viewport' content='width=device-width, initial-scale=1.0'>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
          rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM"
          crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz"
            crossorigin="anonymous"></script><link href="https://fonts.googleapis.com/css2?family=Lato&family=Raleway&display=swap" rel="stylesheet">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel='stylesheet' href='/static/css/dis-style.css'>
</head>
<body class="gradient-custom">
<section class="vh-100">
    <div class="container ">
        <header class="d-flex  py-3">
            <#if teacher.email??>
            <#--                <h3>Имя пользователя: <i>${teacher.name}</i></h3>-->
                <form action="/logout" method="post">
                    <input type="hidden" name="_csrf" value="${_csrf.token}">
                    <input  class=" nav-pills nav-item" type="submit" value="Выйти"/>
                </form>
                <ul class="nav nav-pills">
                    <li class="nav-item"><a href="/" class="nav-link active" aria-current="page">Группы</a></li>
                </ul>
            <#else>
                <ul class="nav nav-pills">
                    <li class="nav-item"><a href="/login" class="nav-link active" aria-current="page"  type="submit">Войти</a></li>
                </ul>

            </#if>
        </header>
    </div>
    <div class="container ">
        <br>
        <h2 class="my-4">Группа ${group.name}</h2>
        <#if compose.bytes??>
            <table class="table">
                <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Студент</th>

                    <th scope="col">Тема</th>
                    <th scope="col">Файл</th>
                </tr>
                </thead>
                <tbody>
                <#list composes as compose>
                <tr>
                    <th scope="row">${compose.groupStudentTeacher.student.ordinalNumber}</th>
                    <td>${compose.groupStudentTeacher.student.surname}
                        ${compose.groupStudentTeacher.student.name}
                        ${compose.groupStudentTeacher.student.patronymic}  </td>
                    <td>Вариант ${compose.topic.variant} ${compose.topic.title}</td>
                    <td>
                        <a href="/download/task-list/${compose.id}"
                           download="" title="всплывающая подсказка">
                            ${compose.fileName}
                        </a>
                    </td>
                </tr>
                </#list>
                </tbody>
            </table>
            <div class="form-outline">
                <a class="btn btn-primary rounded-pill mb-3" role="button"
                   href="/send/task-lists"
                   title="Отправить листы задания">Отправить студентам</a>
            </div>

            <#else>
                <div class="form-outline">
                    <a class="btn btn-primary rounded-pill mb-3" role="button"
                       href="/download/files/3"  download="" title="Скачать файл">Скачать шаблон листа задания</a>
                </div>
                <div class="form-outline">
                    <form action="/group/${group.id}/dis/com/task-list/upload" method="post" enctype="multipart/form-data">
                        <#--                                    <input type="file" name="listTopicFile" value="Выбрать список тем"/>-->
                        <input type="file" name="listTaskTemplate" id="form3Examplev5" class="form-control form-control-lg"/>
                        <h5><label class="form-label">Загрузить шаблон листа задания</label></h5>

                        <input type="hidden" name="_csrf" value="${_csrf.token}">
                        <#--                                    <input type="submit" value="Добавить файл"/>-->
                        <button class="btn btn-primary rounded-pill px-4" type="submit">Добавить файл</button>
                    </form>
                </div>
        </#if>
        <#--                <a href="/download/task-list/${compose.id}"  download="" title="всплывающая подсказка">-->
        <#--                ${compose.groupStudentTeacher.student.ordinalNumber}-->
        <#--                ${compose.groupStudentTeacher.student.surname}-->
        <#--                ${compose.groupStudentTeacher.student.name}-->
        <#--                ${compose.groupStudentTeacher.student.patronymic}-->
        <#--                | ${compose.topic.variant} ${compose.topic.title}-->
        <#--                | ${compose.fileName}</a>-->
    </div>
</section>
</body>
</html>