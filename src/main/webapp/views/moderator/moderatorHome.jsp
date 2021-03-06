<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page errorPage="../utility/error.jsp" %>
<c:choose>
   <c:when test="${langBundle == null}">
      <fmt:setBundle basename="strings_ru"/>
   </c:when>
   <c:otherwise>
      <fmt:setBundle basename="${langBundle}"/>
   </c:otherwise>
</c:choose>
<html>
   <head>
      <title>MHP</title>
      <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
   </head>
   <body>
      <header>
         <div>
            <jsp:include page="../utility/header.jsp" />
         </div>
      </header>
      <center>
         <div class="content">
            <section class="main">
               <h1><fmt:message key="title.moderatorHome"/></h1>
               <br>
               <form action="${pageContext.request.contextPath}/controller?command=expoCenterManagement" method="post">
                  <input type="submit" value='<fmt:message key="title.expoCenterManagement"/>'>
               </form>
               <form action="${pageContext.request.contextPath}/controller?command=expoManagement" method="post">
                  <input type="submit" value='<fmt:message key="title.expoManagement"/>'>
               </form>
               <form action="${pageContext.request.contextPath}/controller?command=contractManagement" method="post">
                  <input type="submit" value='<fmt:message key="title.contractManagement"/>'>
               </form>
               <form action="${pageContext.request.contextPath}/controller?command=addExpoCenter" method="post">
                  <input type="submit" value='<fmt:message key="title.addExhibitionCenter"/>'>
               </form>
               <form action="${pageContext.request.contextPath}/controller?command=addExposition" method="post">
                  <input type="submit" value='<fmt:message key="title.addExhibition"/>'>
               </form>
               <form action="${pageContext.request.contextPath}/controller?command=waitApprovalTicket" method="post">
                  <input type="submit" value='<fmt:message key="title.waitApproval"/>'>
               </form>
               <form action="${pageContext.request.contextPath}/controller?command=approvedTicket" method="post">
                  <input type="submit" value='<fmt:message key="title.approvedTicket"/>'>
               </form>
               <br/>
               <form action="${pageContext.request.contextPath}/controller?command=home" method="post">
                  <input type="submit" value='<fmt:message key="btn.goHome"/>'>
               </form>
            </section>
         </div>
      </center>
      <footer>
         <jsp:include page="../utility/footer.jsp" />
      </footer>
   </body>
</html>