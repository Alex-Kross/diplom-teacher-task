<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
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
        <div class="container">
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
<#--            <header class="d-flex justify-content-center py-3">-->
<#--                <ul class="nav nav-pills">-->
<#--                    -->
<#--                    <li class="nav-item"><a href="#" class="nav-link">Предметы</a></li>-->
<#--                    <li class="nav-item"><a href="#" class="nav-link">Распределение</a></li>-->
<#--                    <li class="nav-item"><a href="#" class="nav-link">Отправка</a></li>-->
<#--                    <li class="nav-item"><a href="#" class="nav-link">Учёт</a></li>-->
<#--                </ul>-->
<#--            </header>-->
        </div>
        <form action="/group/create" method="post" enctype="multipart/form-data">
            <div class="container">
                <h1 class="my-4">Добавление группы
                    <!-- <button class="btn btn-primary rounded-pill px-3" type="button">Добавить группу</button> -->
                </h1>
                <div class="row">
                    <div class="col-md-8 mb-6 pb-4">
                        <div class="form-outline">
                            <div>
                                <select name="select-group" class="form-control border-0 mb-1  rounded shadow" id="group-edit">
<#--                                        required="required" id="group-edit">-->
                                    <option value="0" label="--- Выбрать ---" />
                                    <#list groups as group>
                                    <option value="${group.id}" label="${group.name} | ${group.facultyName} | ${group.universityName}"></option>
                                    </#list>
                                </select>
                                <h5><label class="form-label" for="form3Examplev2" >Список существующих групп</label></h5>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-8 mb-6 pb-4">
                        <div class="form-outline">
<#--                            <button class="btn btn-primary rounded-pill px-3 " type="submit">Добавить</button>-->
                            <a class="btn btn-primary rounded-pill mb-3"
                               data-toggle="collapse" href="#collapseExample" role="button"
                               aria-expanded="true" aria-controls="collapseExample"> Добавить новую группу</a>
                        </div>
                    </div>
                    <div class="collapse" id="collapseExample" style="">
                        <div class="col-md-8 mb-6 pb-4">
                            <div class="form-outline">
                                <input type="text" name="name" id="form3Examplev2" class="form-control form-control-lg" />
                                <h5><label class="form-label" for="form3Examplev2">Название группы</label></h5>
                            </div>
                        </div>
                        <div class="col-md-8 mb-6 pb-4">
                            <div class="form-outline">
                                <input type="text" name="facultyName" id="form3Examplev3" class="form-control form-control-lg" />
                                <h5><label class="form-label" for="form3Examplev3">Название факультета</label></h5>
                            </div>
                        </div>
                        <div class="col-md-8 mb-6 pb-4">
                            <div class="form-outline">
                                <input type="text" name="universityName"  id="form3Examplev4" class="form-control form-control-lg" />
                                <h5><label class="form-label" for="form3Examplev4">Название университета</label></h5>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-8 mb-6 pb-4">
                        <div class="form-outline">
                            <div>
                                <select name="select-file" class="form-control border-0 mb-1  rounded shadow" id="group-edit">
                                    <#--                                        required="required" id="group-edit">-->
                                    <option value="0" label="--- Выбрать ---" />
                                    <#list files as file>
                                        <option value="${file.id}" label="${file.fileName}"></option>
                                    </#list>
                                </select>
                                <h5><label class="form-label" for="group-edit" >Список уже загружженых файлов</label></h5>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-8 mb-6 pb-4">
                        <div class="form-outline">
                            <a class="btn btn-primary rounded-pill mb-3" role="button"
                               href="/download/files/1"  download="" title="Скачать файл">Скачать шаблон со списком студентов</a>
                        </div>
                    </div>
                    <div class="col-md-8 mb-6 pb-4">
                        <form>
                            <div class="form-outline">
                                <div class="custom-file">
                                    <input type="file" name="listStudentFile" id="form3Examplev5" class="form-control form-control-lg"/>
                                    <h5><label class="form-label" for="form3Examplev5">Загрузить новый список студентов</label></h5>
                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="col-md-8 mb-6 pb-4">
                        <div class="form-outline">
                            <input type="hidden" name="_csrf" value="${_csrf.token}">
                            <button class="btn btn-primary rounded-pill px-3 " type="submit">Добавить</button>

                            <form action="/" method="get" style="margin: 0px" class="btn  rounded-pill px-3">
                                <input type="hidden" name="_csrf" value="${_csrf.token}">
                                <button class="btn btn-primary rounded-pill px-4" type="submit">Отмена</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </form>
    </section>

<#--<h3>Создать новую группу</h3>-->
<#--<form action="/group/create" method="post" enctype="multipart/form-data">-->
<#--    Номер группы: <input type="text" name="name"/><br><br>-->
<#--    Факультет: <input type="text" name="facultyName"/><br><br>-->
<#--    Университет: <input type="text" name="universityName"/><br><br>-->

<#--    Загрузить список студентов: <input type="file" name="listStudentFile"/><br><br>-->
<#--    <input type="hidden" name="_csrf" value="${_csrf.token}">-->
<#--    <input type="submit" value="Добавить группу"/>-->
<#--</form>-->
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
</html>