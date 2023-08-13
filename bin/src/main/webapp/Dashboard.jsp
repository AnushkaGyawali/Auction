<%-- 
    Document   : Dashboard
    Created on : Aug 7, 2023, 7:29:02 PM
    Author     : Dell
--%>

<!DOCTYPE html>
<html>
<head>
    <title>Dashboard</title>
</head>
<body>
    <h1>Welcome to Your Dashboard</h1>
    <p>Hello, <%= request.getRemoteUser() %>!</p>
    <a href="logout">Logout</a>
</body>
</html>