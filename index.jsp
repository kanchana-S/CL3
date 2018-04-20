<%-- 
    Document   : index.jsp
    Created on : Mar 28, 2016, 5:42:09 AM
    Author     : Suresh
--%>
<%@page import="java.io.FileWriter"%>
<%@page import="plagrism.PlagiarismDetector"%>
<%
    String str="";
    if (request.getParameter("type") != null) {
//        PlagiarismDetector pd = new PlagiarismDetector("/synonyms.txt");
        FileWriter fr = new FileWriter(request.getSession().getServletContext().getRealPath("/")+ "file1.txt");
        fr.write(request.getParameter("text"));
        fr.close();
    
        String synonymsFileName;
        String comparisonFileName;
        String baseFileName;

        int numTuples = 3;
        synonymsFileName = request.getSession().getServletContext().getRealPath("/")+"synonyms.txt";
        comparisonFileName = request.getSession().getServletContext().getRealPath("/")+"file1.txt";
        baseFileName = request.getSession().getServletContext().getRealPath("/")+"file2.txt";
        PlagiarismDetector pd = new PlagiarismDetector(synonymsFileName);

      str=  pd.getPlagiarismScore(synonymsFileName, comparisonFileName,
                baseFileName, numTuples);

    }

%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Web base Plagrism </title>
    </head>
    <body>
        <form action="index.jsp?type=plag" method="post">
            <table>
                <tr>
                    <td>Text</td>
                    <td>:</td>
                    <td><textarea name="text" rows="20" cols="160"><%=request.getParameter("text")%> </textarea></td>
                </tr>
                <tr>
                    <td> </td>
                    <td></td>
                    <td><button type="submit" >Submit</button></td>
                </tr>
                
                <%
    if (request.getParameter("type") != null) {
%>
 <tr>
                    <td>Output</td>
                    <td>:</td>
                    <td><textarea name="text1" rows="20" cols="160"><%=str%></textarea></td>
                </tr>
              <%
  }
%>
            </table>
        </form>
    </body>
</html>
