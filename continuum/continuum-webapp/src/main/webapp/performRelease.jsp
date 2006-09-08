<%@ taglib uri="/webwork" prefix="ww" %>
<%@ taglib uri="/tld/extremecomponents" prefix="ec" %>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ taglib uri="continuum" prefix="c1" %>
<html>
  <ww:i18n name="localization.Continuum">
    <head>
        <title><ww:text name="releaseProject.page.title"/></title>
    </head>
    <body>
      <h2>Prepare Project Release</h2>
      <ww:form action="performRelease!doPerform.action" method="post">
        <h3>Common Release Parameters</h3>
        <input type="hidden" name="projectId" value="<ww:property value="projectId"/>"/>
        <div class="axial">
          <table border="1" cellspacing="2" cellpadding="3" width="100%">
            <ww:textfield label="SCM Connection URL" name="scmUrl"/>
            <ww:textfield label="SCM Username" name="scmUsername"/>
            <ww:textfield label="SCM Password" name="scmPassword"/>
            <ww:textfield label="SCM Tag" name="scmTag"/>
            <ww:textfield label="SCM Tag Base" name="scmTagBase"/>
            <ww:textfield label="Maven Arguments" name="goals"/>
            <ww:checkbox label="Use Release Profile" name="useReleaseProfile" value="true"/>
          </table>
        <ww:submit/>
      </ww:form>
    </body>
  </ww:i18n>
</html>
