<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="wp" uri="/aps-core" %>

<wp:ifauthorized permission="superuser">
	<li><a href="<s:url namespace="/do/jpbuspass/Buspass" action="list" />" ><s:text name="jpbuspass.title.buspassManagement" /></a></li>
</wp:ifauthorized>
