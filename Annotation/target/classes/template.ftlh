<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Html Form</title>
</head>
<body>
<form action="${action}" method="${method}">
    <#list inputs as input>
        <input type="${input.type}" name="${input.name}" placeholder="${input.placeholder}">
    </#list>
</form>
</body>
</html>







