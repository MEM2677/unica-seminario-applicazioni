<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="wp" uri="/aps-core"%>
<%@ taglib prefix="jpbuspass" uri="/jpbuspass-core"%>

<jpbuspass:buspass var="buspass" />
<article>
<c:choose>
	<c:when test="${not empty buspass}">
	<h1><wp:i18n key="jpbuspass_BUSPASS_ID" />: <c:out value="${buspass.id}" /></h1>
	<ul>
		<li>
			<wp:i18n key="jpbuspass_BUSPASS_NAME" />: <c:out value="${buspass.name}" /><br />
			<wp:i18n key="jpbuspass_BUSPASS_LNAME" />: <c:out value="${buspass.lname}" /><br />
			<wp:i18n key="jpbuspass_BUSPASS_AGE" />: <c:out value="${buspass.age}" /><br />
			<wp:i18n key="jpbuspass_BUSPASS_TYPE" />: <c:out value="${buspass.type}" /><br />
		</li>
	</ul>
	</c:when>
	<c:otherwise>
	<div class="alert alert-error">
		<p><wp:i18n key="jpbuspass_BUSPASS_NOT_FOUND" /></p>
	</div>
	</c:otherwise>
</c:choose>
</article>