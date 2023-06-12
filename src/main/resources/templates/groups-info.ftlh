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
    <link rel='stylesheet' href='/static/css/group-info.css'>
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
            <h3 class="my-4">
            <p>Номер группы: ${group.name}</p>
            <p>Факультет: ${group.facultyName}</p>
            <p>Университет: ${group.universityName}</p>
            <form action="/group/delete/${group.id}" method="post">
                <input type="hidden" name="_csrf" value="${_csrf.token}">
                <button class="btn btn-primary rounded-pill px-3" type="submit">Удалить группу</button>
            </form>
                <h3>Добавить список тем </h3>
                <div class="form-outline">
                    <a class="btn btn-primary rounded-pill mb-3" role="button"
                       href="/download/files/2"  download="" title="Скачать файл">Cкачать файл шаблон списка тем</a>
                </div>
                <div class="form-outline">
                    <form action="/group/${group.id}/dis/upload-topic" method="post" enctype="multipart/form-data"
                          style="margin: 0px" class="btn  rounded-pill px-3">
                        <#--                                    <input type="file" name="listTopicFile" value="Выбрать список тем"/>-->
                        <input type="file" name="listTopicFile" id="form3Examplev5" class="form-control form-control-lg"/>
                        <h5><label class="form-label">Выбрать список тем</label></h5>

                        <input type="hidden" name="_csrf" value="${_csrf.token}">
                        <#--                                    <input type="submit" value="Добавить файл"/>-->
                        <button class="btn btn-primary rounded-pill px-4" type="submit">Добавить файл</button>
                    </form>
                </div>
<#--            <form action="/group/${group.id}/dis" method="post">-->
<#--                <input type="hidden" name="_csrf" value="${_csrf.token}">-->
<#--                <button class="btn btn-primary rounded-pill px-3" type="submit">Распределить задания</button>-->
<#--            </form>-->
            </h3>




        <#if students?size != 0>
            <table class="table">
                <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Фамилия имя отчество</th>
                    <th scope="col">Email</th>
                    <th scope="col">Телефон</th>
                    <th scope="col">ФИО родителя</th>
                    <th scope="col">Email родителя</th>
                    <th scope="col">Телефон родителя</th>
                </tr>
                </thead>

                <tbody>
                <tr>
                <#list students as student>
                    <th scope="row">${student.ordinalNumber}</th>
                    <td>${student.surname} ${student.name} ${student.patronymic}  </td>
                    <td>${student.email}</td>
                    <td>${student.phone}</td>
                    <td>${student.parentFullName}</td>
                    <td>${student.parentEmail}</td>
                    <td>${student.parentPhone}</td>
                </tr>
                <#else>
                    <h3>Список студентов не добавлен</h3>
                </#list>
                </tbody>
            </table>

            <#else>
                <h3>Список студентов не добавлен</h3>
                <br>
                <form action="/group/${group.id}/file/upload" method="post" enctype="multipart/form-data">
                    <div class="form-outline">
                        <a class="btn btn-primary rounded-pill mb-3" role="button"
                           href="/download/files/1"  download="" title="Скачать файл">Скачать шаблон со списком студентов</a>
                    </div>
<#--                    <a href="/download/files/1"  download="" title="всплывающая подсказка">скачать файл шаблон списка студентов</a>-->
                    <select name="select-file" class="form-control border-0 mb-1  rounded shadow" id="group-edit">
                        <#--                                        required="required" id="group-edit">-->
                        <option value="0" label="--- Выбрать ---" />
                        <#list files as file>
                            <option value="${file.id}" label="${file.fileName}"></option>
                        </#list>
                    </select>
                    <h5><label class="form-label">Список файлов преподавателя</label></h5>
                    <div class="form-outline">
                        <div class="custom-file">
                            <input type="file" name="listStudentFile" id="form3Examplev5" class="form-control form-control-lg"/>
                            <h5><label class="form-label" for="form3Examplev5">Загрузить новый список студентов</label></h5>
                        </div>
                    </div>
<#--                    <input type="file" name="listStudentFile" value="Выбрать файл списка студентов"/>-->
<#--                    <input type="hidden" name="_csrf" value="${_csrf.token}">-->
<#--                    <input type="submit" value="Добавить список"/>-->
                </form>


            </#if>
        </div>
    </section>
</body>
</html>