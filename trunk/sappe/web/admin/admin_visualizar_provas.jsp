<%-- 
    Document   : admin_visualizar_provas
    Created on : 20/01/2012, 06:00:06
    Author     : gleyson
--%>

<%@page import="br.ufc.si.pet.sappe.service.TipoService"%>
<%@page import="br.ufc.si.pet.sappe.entidades.Tipo"%>
<%@page import="br.ufc.si.pet.sappe.entidades.Prova"%>
<%@page import="java.util.List"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="../css/style.css" rel="stylesheet" type="text/css" />
        <title>JSP Page</title>
    </head>
    <body>
        <div id="tudo">
            <div id="topo">
                <img src="../images/sappe2.gif" width="959" height="76" alt="sappe2"/>
            </div>
            <%@include file="/admin/menu.jsp" %>
            <div id="direita"></div>
            <div id="meio">
                <label><h3 class="titulo">Visualizar Provas</h3></label><br /><br /><br />
                <div id="bh"></div>
                <div id="content">
                    <table>
                        <thead>
                            <tr>
                                <th class="datatableHeader">Prova</th>
                                <th class="datatableHeader">Ação</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                        List<Prova> provas = (List<Prova>) session.getAttribute("subListaDeProvas");
                                        for (Prova p : provas) {
                                            TipoService tS = new TipoService();
                                            Tipo tipo = tS.getTipoById(p.getTipo_id());
                            %>
                            <tr>
                                <td>
                                    <%= tipo.getNome()%> - <%= p.getData()%>
                                </td>
                                <td><div align="center"><a href="../ServletCentral?comando=CmdAdminCorrigirProva&id=<%= p.getId()%>">Corrigir Prova</a></div></td>
                            </tr>
                            <%}%>
                        </tbody>
                    </table>
                </div>
            </div>
            <div id="direita"></div>
            <div id="footer">
                <center><img alt="Logotipo UFC"  class="imagemUFC" src="../images/UFC2.png"/></center>
                <h6>Versão 1.0 Beta - Universidade Federal do Ceará - Campus Quixadá</h6>
            </div>
        </div>
    </body>
</html>
