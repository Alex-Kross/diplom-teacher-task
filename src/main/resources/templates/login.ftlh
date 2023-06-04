<!DOCTYPE html>
<html>
<#--    <head>-->
<#--        <meta charset="UTF8">-->
<#--        <meta title="resume">-->
<#--        <meta http-equiv="X-UA-Compatible" content="IE=edge">-->
<#--        <meta name='viewport' content='width=device-width, initial-scale=1.0'>-->

<#--        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">-->
<#--        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>-->
<#--        <link href="https://fonts.googleapis.com/css2?family=Lato&family=Raleway&display=swap" rel="stylesheet">-->
<#--        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>-->
<#--        <link rel="preconnect" href="https://fonts.googleapis.com">-->
<#--        <link rel='stylesheet' href='css/style-login.css'>-->
<#--        <title>Webapp</title>-->
<#--    </head>-->
<body>
<h1>BUYSELL</h1><hr>
<h4>Авторизация</h4>
<form action="/login" method="post">
    Эл. почта: <input type="email" name="username"><br><br>
    Пароль: <input type="password" name="password"><br><br>
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <input type="submit" value="Вход"/>
</form>
<a href="/registration">Зарегистрироваться</a>
</body>
<#--    <body>-->
<#--        <section class="vh-100 gradient-custom">-->
<#--            <div class="container py-5 h-100">-->
<#--                <div class="row d-flex justify-content-center align-items-center h-100">-->
<#--                    <div class="col-12 col-md-8 col-lg-6 col-xl-5">-->
<#--                        <div class="card bg-indigo text-white" style="border-radius: 1rem; background-color: #48cc84fb;">-->
<#--                            <div class="card-body p-5 text-center ">-->
<#--                                <div class="mb-md-5 mt-md-4 pb-5">-->
<#--                                    <h2 class="fw-bold mb-2 text-uppercase">Вход</h2>-->
<#--                                    <form action="/login" method="post">-->
<#--                                        <div class="form-outline form-white mb-4">-->
<#--                                            <input type="email" name="username" id="typeEmailX" class="form-control form-control-lg" />-->
<#--                                            <label class="form-label" for="typeEmailX">Email</label>-->
<#--                                        </div>-->
<#--                                        <div class="form-outline form-white mb-4">-->
<#--                                            <input type="password" name="password" id="typePasswordX" class="form-control form-control-lg" />-->
<#--                                            <label class="form-label" for="typePasswordX">Пароль</label>-->
<#--                                        </div>-->
<#--                                        <button class="btn btn-outline-light btn-lg px-5" type="submit">Войти</button>-->
<#--                                    </form>-->
<#--                                </div>-->
<#--                                <div>-->
<#--                                    <p class="mb-0">Нужен аккаунт?    <a href="/registration" class="text-white-50 fw-bold">Зарегистрироваться</a>-->
<#--                                    </p>-->
<#--                                </div>-->
<#--                            </div>-->
<#--                        </div>-->
<#--                    </div>-->
<#--                </div>-->
<#--            </div>-->
<#--        </section>-->
<#--    </body>-->
</html>