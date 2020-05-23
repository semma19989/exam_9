
<#import "partials/main.ftlh" as main />
<@main.renderWith>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <style>
            body {margin:0;}

            .navbar {
                overflow: hidden;
                background-color: #333;
                position: fixed;
                top: 0;
                width: 100%;
            }

            .navbar a {
                float: left;
                display: block;
                color: #f2f2f2;
                text-align: center;
                padding: 14px 16px;
                text-decoration: none;
                font-size: 17px;
            }

            .navbar a:hover {
                background: #ddd;
                color: black;
            }

            .main {
                padding: 16px;
                margin-top: 30px;
                height: 1500px;
            }
        </style>
    </head>
    <body>

<div class="navbar">
    <a href="/register"> Registration </a>
    <a href="/login"> Enter </a>
    <a href="/profile"> Profile </a>
    <form class="flex box-128" action="/logout" method="post">
        <#if _csrf??>
            <input type="hidden" name="${(_csrf.parameterName)!'csrf'}" value="${(_csrf.token)!'--no-token--'}"/>
        </#if>
        <button class="flex box-128 logout-button">Выйти </button>
    </form>
</div>
<div class="main">
    <#if errorText?? >
    <div>
        <p>${errorText!'-- no message --'}</p>
    </div>
    </#if>


</div>
    </body>
</@main.renderWith>