<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="wp" uri="/aps-core" %>
<%@ taglib prefix="wpsa" uri="/apsadmin-core" %>

<%--CAL START --%>

<wp:info key="currentLang" var="currentLang" />

<c:set var="js_for_datepicker">
/* Italian initialisation for the jQuery UI date picker plugin. */
/* Written by Antonello Pasella (antonello.pasella@gmail.com). */
jQuery(function($){
$.datepicker.regional['it'] = {
closeText: 'Chiudi',
prevText: '&#x3c;Prec',
nextText: 'Succ&#x3e;',
currentText: 'Oggi',
monthNames: ['Gennaio','Febbraio','Marzo','Aprile','Maggio','Giugno',
'Luglio','Agosto','Settembre','Ottobre','Novembre','Dicembre'],
monthNamesShort: ['Gen','Feb','Mar','Apr','Mag','Giu',
'Lug','Ago','Set','Ott','Nov','Dic'],
dayNames: ['Domenica','Luned&#236','Marted&#236','Mercoled&#236','Gioved&#236','Venerd&#236','Sabato'],
dayNamesShort: ['Dom','Lun','Mar','Mer','Gio','Ven','Sab'],
dayNamesMin: ['Do','Lu','Ma','Me','Gi','Ve','Sa'],
weekHeader: 'Sm',
dateFormat: 'dd/mm/yy',
firstDay: 1,
isRTL: false,
showMonthAfterYear: false,
yearSuffix: ''};
});

jQuery(function($){
if (Modernizr.touch && Modernizr.inputtypes.date) {
$.each( $("input[data-isdate=true]"), function(index, item) {
item.type = 'date';
});
} else {
$.datepicker.setDefaults( $.datepicker.regional[ "<c:out value="${currentLang}" />" ] );
$("input[data-isdate=true]").datepicker({
       changeMonth: true,
       changeYear: true,
       dateFormat: "dd/mm/yy"
     });
}
});
</c:set>
<wp:headInfo type="JS" info="entando-misc-html5-essentials/modernizr-2.5.3-full.js" />
<wp:headInfo type="JS_EXT" info="http://code.jquery.com/ui/1.10.1/jquery-ui.js" />
<wp:headInfo type="CSS_EXT" info="http://code.jquery.com/ui/1.10.1/themes/base/jquery-ui.css" />
<wp:headInfo type="JS_RAW" info="${js_for_datepicker}" />

<%--CAL END --%>

<%--
optional CSS
<wp:headInfo type="CSS" info="widgets/buspass_list.css" />
--%>

<section class="buspass_list">

<h1><wp:i18n key="jpbuspass_BUSPASS_SEARCH_BUSPASS" /></h1>

<form action="<wp:action path="/ExtStr2/do/FrontEnd/jpbuspass/Buspass/search.action" />" method="post" >

  <fieldset>
		<label for="buspass_id"><wp:i18n key="jpbuspass_BUSPASS_ID" /></label>
		<input type="text" name="id" id="buspass_id" value="<s:property value="id" />" />
		<label for="buspass_name"><wp:i18n key="jpbuspass_BUSPASS_NAME" /></label>
		<input type="text" name="name" id="buspass_name" value="<s:property value="name" />" />
		<label for="buspass_lname"><wp:i18n key="jpbuspass_BUSPASS_LNAME" /></label>
		<input type="text" name="lname" id="buspass_lname" value="<s:property value="lname" />" />
		<label for="buspass_age"><wp:i18n key="jpbuspass_BUSPASS_AGE" /></label>
		<input type="text" name="age" id="buspass_age" value="<s:property value="age" />" />
		<label for="buspass_type"><wp:i18n key="jpbuspass_BUSPASS_TYPE" /></label>
		<input type="text" name="type" id="buspass_type" value="<s:property value="type" />" />
  </fieldset>

  <button type="submit" class="btn btn-primary">
    <wp:i18n key="SEARCH" />
  </button>

<wpsa:subset source="buspasssId" count="10" objectName="groupBuspass" advanced="true" offset="5">
<s:set var="group" value="#groupBuspass" />

<div class="margin-medium-vertical text-center">
	<s:include value="/WEB-INF/apsadmin/jsp/common/inc/pagerInfo.jsp" />
	<s:include value="/WEB-INF/apsadmin/jsp/common/inc/pager_formBlock.jsp" />
</div>

<p>
  <a href="<wp:action path="/ExtStr2/do/FrontEnd/jpbuspass/Buspass/new.action"></wp:action>" title="<wp:i18n key="NEW" />" class="btn btn-info">
    <span class="icon-plus-sign icon-white"></span>&#32;
    <wp:i18n key="NEW" />
  </a>
</p>

<table class="table table-bordered table-condensed table-striped">
<thead>
<tr>
  <th class="text-right">
    <wp:i18n key="jpbuspass_BUSPASS_ID" />
  </th>
	<th
                 class="text-left"><wp:i18n key="jpbuspass_BUSPASS_NAME" /></th>
	<th
                 class="text-left"><wp:i18n key="jpbuspass_BUSPASS_LNAME" /></th>
	<th
         class="text-right"        ><wp:i18n key="jpbuspass_BUSPASS_AGE" /></th>
	<th
                 class="text-left"><wp:i18n key="jpbuspass_BUSPASS_TYPE" /></th>
	<th>
    <wp:i18n key="jpbuspass_BUSPASS_ACTIONS" />
  </th>
</tr>
</thead>
<tbody>
<s:iterator var="buspassId">
<tr>
	<s:set var="buspass" value="%{getBuspass(#buspassId)}" />
	<td class="text-right">
    <a
      href="<wp:action path="/ExtStr2/do/FrontEnd/jpbuspass/Buspass/edit.action"><wp:parameter name="id"><s:property value="#buspass.id" /></wp:parameter></wp:action>"
      title="<wp:i18n key="EDIT" />: <s:property value="#buspass.id" />"
      class="label label-info display-block">
    <s:property value="#buspass.id" />&#32;
    <span class="icon-edit icon-white"></span>
    </a>
  </td>
	<td
            >
    <s:property value="#buspass.name" />  </td>
	<td
            >
    <s:property value="#buspass.lname" />  </td>
	<td
         class="text-right"    >
    <s:property value="#buspass.age" />  </td>
	<td
            >
    <s:property value="#buspass.type" />  </td>
	<td class="text-center">
    <a
      href="<wp:action path="/ExtStr2/do/FrontEnd/jpbuspass/Buspass/trash.action"><wp:parameter name="id"><s:property value="#buspass.id" /></wp:parameter></wp:action>"
      title="<wp:i18n key="TRASH" />: <s:property value="#buspass.id" />"
      class="btn btn-warning btn-small">
      <span class="icon-trash icon-white"></span>&#32;
      <wp:i18n key="TRASH" />
    </a>
  </td>
</tr>
</s:iterator>
</tbody>
</table>

<div class="margin-medium-vertical text-center">
	<s:include value="/WEB-INF/apsadmin/jsp/common/inc/pager_formBlock.jsp" />
</div>

</wpsa:subset>

</form>
</section>