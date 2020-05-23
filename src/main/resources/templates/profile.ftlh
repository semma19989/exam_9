<#import "partials/main.ftlh" as main />

<@main.renderWith>
    <#if dto??>
        <h1>Your credentials!</h1>
        <p>${(dto.fullname)!'-- missing --'} - ${(dto.email)!'-- email missing --'}</p>
    <#else>
        <h1>The user is not found!</h1>
    </#if>
    <div class="bottom-box">
        <a href="/">On the homepage</a>
    </div>
</@main.renderWith>