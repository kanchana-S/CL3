<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sample1</title>
    </head>
    <body>
             <h1>Result after performing Booth's Multiplication</h1>
            
        <%@page import="add.Addd2" %>
       <jsp:useBean id="mybean" scope="application" class="add.Addd2" />
        
        <jsp:setProperty name="mybean" property="number1"  />
        <jsp:setProperty name="mybean" property="number2"  />
        
     
           <h1> The multiplication is: <jsp:getProperty name="mybean" property="s" /></h1>       

    </body>

</html>