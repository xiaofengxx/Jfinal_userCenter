<!DOCTYPE html>
<html class="login-alone">
<head>
    <title>Kisso-jfinal</title>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
    <link type="image/x-icon" rel="shortcut icon" href="/res/favicon.ico"/>
    <link rel="stylesheet" href="/static/css/lib/bootstrap.min.css">
    <link rel="stylesheet" href="/static/css/base.css">
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-4"></div>
        <div class="col-md-4 item">
            <form role="form" method="post" action="/login/login" class="far-away-top">
                <div class="form-group">
                    <label class="control-label sr-only">账号:</label>
                    <input class="form-control" type="text" name="username" placeholder="电子邮箱/手机号" autocomplete="off"
                           value="">
                </div>
                <div class="form-group">
                    <label class="control-label sr-only">密码：</label>
                    <input class="form-control" type="password" name="password" placeholder="请输入密码" autocomplete="off">
                </div>
                <div class="form-group">
                    <label class="control-label sr-only">验证码：</label>
                    <div class="col-md-9">
                        <input class="form-control" type="text" name="vcode" placeholder="验证码"/>
                    </div>
                    <img onclick="this.src=('/verify?reload='+(new Date()).getTime())" class="vcode" src="/verify"
                         width="85" height="35" alt="">
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <div class="checkbox">
                            <label>
                                <input type="checkbox" id="chk11" name="remember_me"> 记住密码
                            </label>
                        </div>
                    </div>
                </div>
                <div class="sr-only">
                    <input type="text" value="${url}" name="url">
                </div>
                <div class=form-group>
                    <input class="btn btn-success center-block" type="submit" value="登  录">
                </div>

                <input type="hidden" name="refer" value="site/">
            </form>
        </div>
    </div>
</div>
</body>
</html>
