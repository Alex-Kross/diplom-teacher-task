<#--<#import "blocks/template.ftlh" as t>-->
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF8">
    <meta title="resume">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name='viewport' content='width=device-width, initial-scale=1.0'>
    <!--        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">-->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz" crossorigin="anonymous"></script>
    <link href="https://fonts.googleapis.com/css2?family=Lato&family=Raleway&display=swap" rel="stylesheet">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel='stylesheet' href='/static/css/style-login.css'>
</head>
<body>
<#--<h1>BUYSELL</h1><hr>-->
<#--<h4>Авторизация</h4>-->
<#--<form action="/login" method="post">-->
<#--    Эл. почта: <input type="email" name="username"><br><br>-->
<#--    Пароль: <input type="password" name="password"><br><br>-->
<#--    <input type="hidden" name="_csrf" value="${_csrf.token}">-->
<#--    <input type="submit" value="Вход"/>-->
<#--</form>-->
<#--<a href="/registration">Зарегистрироваться</a>-->
<section class="vh-100 gradient-custom">
    <div class="container py-5 h-100">
        <div class="row d-flex justify-content-center align-items-center h-100">
            <div class="col-12 col-md-8 col-lg-6 col-xl-5">
                <div class="card bg-indigo text-white" style="border-radius: 1rem;">
                    <div class="card-body p-5 text-center ">
                        <div class="mb-md-5 mt-md-4 pb-5">
                            <h2 class="fw-bold mb-2 text-uppercase">Вход</h2>
                            <form action="/login" method="post">
                                <div class="form-outline form-white mb-4">
                                    <input type="email" name="username" id="typeEmailX" class="form-control form-control-lg" />
                                    <label class="form-label" for="typeEmailX">Email</label>
                                </div>
                                <div class="form-outline form-white mb-4">
                                    <input type="password" name="password" id="typePasswordX" class="form-control form-control-lg" />
                                    <input type="hidden" name="_csrf" value="${_csrf.token}">
                                    <label class="form-label" for="typePasswordX">Пароль</label>
                                </div>
                                <button class="btn btn-outline-light btn-lg px-5" type="submit">Войти</button>
                            </form>
                        </div>
                        <div>
                            <p class="mb-0">Нужен аккаунт?    <a href="/registration" class="text-white-50 fw-bold">Зарегистрироваться</a>
                            </p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
</body>
</html>