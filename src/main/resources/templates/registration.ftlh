<!DOCTYPE html>
<html>
<head>
    <head>
        <meta charset="UTF8">
        <meta title="resume">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name='viewport' content='width=device-width, initial-scale=1.0'>

        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css2?family=Lato&family=Raleway&display=swap" rel="stylesheet">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel='stylesheet' href='/static/css/style.css'>
    </head>
</head>
<body>
    <form action="/registration" method="post">
        <section class="h-100 h-custom gradient-custom-2">
            <div class="container py-5 h-100">
                <div class="row d-flex justify-content-center align-items-center h-100">
                    <div class="col-12">
                        <div class="card card-registration card-registration-2" style="border-radius: 15px;">
                            <div class="card-body p-0">
                                <div class="row g-0">
                                    <div class="col-lg-6">
                                        <div class="p-5">
                                            <h3 class="fw-normal mb-5" style="color: #20aa5ecb;">Общая информация</h3>
                                            <div class="row">
                                                <div class="col-md-6 mb-4 pb-2">
                                                    <div class="form-outline">
                                                        <input type="text" name="name" id="form3Examplev2" class="form-control form-control-lg" />
                                                        <label class="form-label" for="form3Examplev2">Имя</label>
                                                    </div>
                                                </div>
                                                <div class="col-md-6 mb-4 pb-2">
                                                    <div class="form-outline">
                                                        <input type="text" name="surname" id="form3Examplev3" class="form-control form-control-lg" />
                                                        <label class="form-label" for="form3Examplev3">Фамилия</label>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="col-md-6 mb-4 pb-2 mb-md-0 pb-md-4">
                                                    <div class="form-outline">
                                                        <input type="text" name="patronymic" id="form3Examplev5" class="form-control form-control-lg" />
                                                        <label class="form-label" for="form3Examplev5">Отчество</label>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="mb-4 pb-2">
                                                <div class="form-outline">
                                                    <input type="text" name="position" id="form3Examplev4" class="form-control form-control-lg" />
                                                    <label class="form-label" for="form3Examplev4">Должность</label>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-lg-6 bg-indigo text-white">
                                        <div class="p-5">
                                            <h3 class="fw-normal mb-5">Важная информация</h3>

                                            <div class="mb-4 pb-2">
                                                <div class="form-outline form-white">
                                                    <input type="email"  name="email" id="form3Examplea2" class="form-control form-control-lg" />
                                                    <label class="form-label" for="form3Examplea2">Email</label>
                                                </div>
                                            </div>
                                            <div class="mb-4 pb-2">
                                                <div class="form-outline form-white">
                                                    <input type="password" name="password" id="form3Examplea3" class="form-control form-control-lg" />
                                                    <input type="hidden" name="_csrf" value="${_csrf.token}">
                                                    <label class="form-label" for="form3Examplea3">Пароль</label>
                                                </div>
                                            </div>
                                            <button type="submit" class="btn btn-light btn-lg"
                                                    data-mdb-ripple-color="dark">Зарегистрироваться</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    <#--    Имя: <input type="text" name="name"/><br><br>-->
    <#--    Фамилия: <input type="text" name="surname"/><br><br>-->
    <#--    Отчетсво: <input type="text" name="patronymic"/><br><br>-->
    <#--    должность: <input type="text" name="position"/><br><br>-->
    <#--    почта: <input type="email" name="email"/><br><br>-->
    <#--    пароль: <input type="password" name="password"/><br><br>-->
    <#--    <input type="hidden" name="_csrf" value="${_csrf.token}">-->
    <#--    <input type="submit" value="Вход"/>-->
    </form>
    <#if errorMessage??>
        <h2 style="color: red">${errorMessage}</h2>
    </#if>
</body>
</html>