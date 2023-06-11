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
            crossorigin="anonymous"></script>
    <link href="https://fonts.googleapis.com/css2?family=Lato&family=Raleway&display=swap" rel="stylesheet">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel='stylesheet' href='/static/css/group-style.css'>
</head>
<body>
    <section class="vh-100 gradient-custom">
        <div class="container ">
            <header class="d-flex  py-3">
                <#if teacher.email??>
    <#--                <h3>Имя пользователя: <i>${teacher.name}</i></h3>-->
                    <form action="/logout" method="post">
                        <input type="hidden" name="_csrf" value="${_csrf.token}">
                        <input  class=" nav-pills nav-item" type="submit" value="Выйти"/>
                    </form>
                <#else>
                    <ul class="nav nav-pills">
                        <li class="nav-item"><a href="/login" class="nav-link active" aria-current="page"  type="submit">Войти</a></li>
                    </ul>
                </#if>

            </header>
        </div>
        <div class="container ">
            <#if teacher.email??>
            <h1 class="my-4">Группы
                <form action="/group/create-page" method="get">
                    <input type="hidden" name="_csrf" value="${_csrf.token}">
                    <button class="btn btn-primary rounded-pill px-3" type="submit">Добавить группу</button>
                    <#--                <input type="submit" value="Добавить группу" />-->
                </form>
            </h1>
            <div class="row">
                <#list groups as group>
                    <div class="col-lg-3 col-md-4 col-sm-6 mb-4" >
                        <div class="card h-100">

                            <a href="/group/${group.id}">
                            <div class="card-body" >
                                <h4 class="card-title">
                                    <p>${group.name}</p>
                                </h4>
                                <p class="card-text">Факультет:    ${group.facultyName}</p>
                                <p class="card-text">Университет:  ${group.universityName}</p>
                            </div>
                            </a>
                        </div>
                    </div>
                <#else>
                    <h3>Групп нету</h3>
                </#list>
            </div>
            <#else>
                <h1 class="my-4">Группы</h1>
            </#if>
        </div>
    </section>
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
    integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
    crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
            integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"
            integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T"
            crossorigin="anonymous"></script>
</body>
<#--<body>-->

<#--<h1>BUYSELL</h1><hr>-->
<#--<#if teacher.email??>-->
<#--    <h3>Имя пользователя: <i>${teacher.name}</i></h3>-->
<#--    <form action="/logout" method="post">-->
<#--        <input type="hidden" name="_csrf" value="${_csrf.token}">-->
<#--        <input type="submit" value="Выйти"/>-->
<#--    </form>-->
<#--<#else>-->
<#--    <a href="/login">Войти</a></h1>-->
<#--</#if>-->
<#--<h4>Группы</h4>-->
<#--<form action="/" method="get">-->
<#--    Поиск по названию объявления: <input type="text" name="name"><br>-->
<#--    <input type="submit" value="Поиск"/>-->
<#--</form>-->
<#--<#list groups as group>-->
<#--    <div>-->
<#--        <p><b>${group.name}</b> ${group.facultyName} ${group.universityName}-->
<#--            | <a href="/group/${group.id}">Подробнее...</a></p>-->
<#--    </div>-->
<#--<#else>-->
<#--    <h3>Групп нету</h3>-->
<#--</#list>-->
<#--<#if teacher.email??>-->
<#--<hr>-->
<#--    <form action="/group/create-page" method="get">-->
<#--        <input type="hidden" name="_csrf" value="${_csrf.token}">-->
<#--        <input type="submit" value="Добавить группу" />-->
<#--    </form>-->
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
<#--</#if>-->
<#--</body>-->
</html>