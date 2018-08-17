<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
   <head>
      <title>Create Contract</title>
   </head>

   <body>
      <center>
            <h1>Create Contract</h1>
            <br>
            <h2>Edit contract:</h2>

            <table border="1" cellpadding="3">
                <caption><h3>Exhibition</h3></caption>
                <tr>
                    <th>ID</th>
                    <th>Title</th>
                    <th>Languages</th>
                </tr>

                <tr>
                    <th><c:out value='${exhibitionId}'/></th>
                    <th><c:out value='${exhibitionTitle}'/></th>
                    <th><c:out value='${exhibitionLangTags}'/></th>
                </tr>

            </table>

            <table border="1" cellpadding="5">
                <caption><h3>Exhibition Center</h3></caption>
                <tr>
                    <th>ID</th>
                    <th>Title</th>
                    <th>Address</th>
                    <th>eMail</th>
                    <th>Web page</th>
                    <th>Phones</th>
                </tr>

                <tr>
                    <th><c:out value='${exhibitionCenterId}'/></th>
                    <th><c:out value='${exhibitionCenterTitle}'/></th>
                    <th><c:out value='${exhibitionAddress}'/></th>
                    <th><c:out value='${exhibitionMail}'/></th>
                    <th><c:out value='${exhibitionWebPage}'/></th>
                    <th><c:out value='${exhibitionCenterPhones}'/></th>
                </tr>

            </table>

             <!-- Contract -->

             <h3>Contract :</h3>
             <form action="${pageContext.request.contextPath}/controller?command=editContract" method="post">
                Start exhibition date (yyyy-MM-dd):<input type="text" name="dateFrom" value='${dateFrom}'/><br/>
                End exhibition date (yyyy-MM-dd):<input type="text" name="dateTo" value='${dateTo}'/><br/>
                Price:<input type="text" name="price" value='${price}'/><br/>
                Work Time:<input type="text" name="workTime" value='${workTime}'/><br/>
                Max Ticket per day:<input type="text" name="maxTicketDay" value='${maxTicketDay}'/><br/>

                 <input type="hidden"  name="exhibitionId" value='${exhibitionId}'>
                 <input type="hidden"  name="exhibitionCenterId" value='${exhibitionCenterId}'>
                <button type="submit" name="saveChangesContract" value="Submit">Save</button>
                <button type="reset" value="Reset">Cancel</button>
                <br> ${error}
                <br> <h4>${insertInfo}<h4>
            </form>

             <!-- /Contract -->


            <br>
            <a href="${pageContext.request.contextPath}/controller?command=moderatorHome">moderator home</a>
            <br>
            <a href="${pageContext.request.contextPath}/controller?command=home">go home</a>
            <br>
            <a href="${pageContext.request.contextPath}/controller?command=logout">logout</a>


      </center>
   </body>
</html>
