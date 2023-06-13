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


                    </#if>
            </header>
        </div>
        <div class="container ">
            <br>
            <h2>Группа ${group.name}</h2>
            <div class="row">
                <div class="col-lg-4 col-md-4 col-sm-6 mb-4" >
                    <h1>
                        <form action="/group/${group.id}/dis/com" style="margin: 0px" class="btn  rounded-pill px-3"
                              method="post" enctype="multipart/form-data" >
                        <#if students??>
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
                </div>
                <div class="col-lg-6 col-md-4 col-sm-6 mb-4" >
                    <h1>
                        <#if topics??>
                            <select name="select-topic" class="form-select" multiple aria-label="multiple select example" id="group-edit">
                                <option value="0" label="--- Выбрать ---" />
                                <#list topics as topic>
                                    <option value="${topic.id}" label="${topic.variant} | ${topic.title}"></option>
                                </#list>
                            </select>


                                <input type="hidden" name="_csrf" value="${_csrf.token}">
                                <button class="btn btn-primary rounded-pill px-4" type="submit">Составить пару</button>
                            </form>
                        <form action="/group/${group.id}/dis/com/task-list" style="margin: 0px" class="btn  rounded-pill px-3"
                              method="post">
                            <input type="hidden" name="_csrf" value="${_csrf.token}">
                            <button class="btn btn-primary rounded-pill px-4" type="submit">Составить листы задания</button>
                        <#else>
                            <h3>Добавьте список тем </h3>
<#--                                <div class="form-outline">-->
<#--                                    <a class="btn btn-primary rounded-pill mb-3" role="button"-->
<#--                                       href="/download/files/2"  download="" title="Скачать файл">Cкачать файл шаблон списка тем</a>-->
<#--                                </div>-->
<#--                                <div class="form-outline">-->
<#--                                    <form action="/group/${group.id}/dis/upload-topic" style="margin: 0px" class="btn  rounded-pill px-3"-->
<#--                                          method="post" enctype="multipart/form-data" >-->
<#--        &lt;#&ndash;                                    <input type="file" name="listTopicFile" value="Выбрать список тем"/>&ndash;&gt;-->
<#--                                        <input type="file" name="listTopicFile" id="form3Examplev5" class="form-control form-control-lg"/>-->
<#--                                        <h5><label class="form-label">Выбрать список тем</label></h5>-->

<#--                                        <input type="hidden" name="_csrf" value="${_csrf.token}">-->
<#--        &lt;#&ndash;                                    <input type="submit" value="Добавить файл"/>&ndash;&gt;-->
<#--                                        <button class="btn btn-primary rounded-pill px-4" type="submit">Добавить файл</button>-->
<#--                                    </form>-->
<#--                                </div>-->
                        </#if>
                    </h1>
                </div>
                <div class="row">
                    <div class="col-lg-12 col-md-4 col-sm-6 mb-8" >
<#--                        <#if topics??>-->
<#--                            <form action="/group/${group.id}/dis/com" style="margin: 0px" class="btn  rounded-pill px-3"-->
<#--                                  method="post" enctype="multipart/form-data" >-->
<#--                                <input type="hidden" name="_csrf" value="${_csrf.token}">-->
<#--                                <button class="btn btn-primary rounded-pill px-4" type="submit">Составить пару</button>-->
<#--                            </form>-->
<#--                        </#if>-->
                        <#if composes??>
                        <select name="select-compose" class="form-select" multiple aria-label="multiple select example" id="group-edit">
                            <option value="0" label="--- Выбрать ---" />
                            <#list composes as compose>
                                <option value="${compose.id}" label="
                                        ${compose.groupStudentTeacher.student.ordinalNumber}
                                        ${compose.groupStudentTeacher.student.surname}
                                        ${compose.groupStudentTeacher.student.name}
                                        ${compose.groupStudentTeacher.student.patronymic}
                                        | ${compose.topic.variant} ${compose.topic.title}
                                        "></option>
                            </#list>
                        </select>
                        <#else>
<#--                        <h3>Добавьте список тем </h3>-->
                        </#if>
                    </div>
                </div>
            </div>
        </div>
    </section>
</body>
</html>