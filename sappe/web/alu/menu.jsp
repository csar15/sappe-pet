<%-- 
    Document   : menu
    Created on : 30/12/2011, 16:53:29
    Author     : gleyson
--%>
<%@page import="br.ufc.si.pet.sappe.entidades.Aluno"%>

<%
            Aluno a = (Aluno) session.getAttribute("user");
%>

<div id="menu">
    <ul id="nav">
        <li>
            <a href="../ServletCentral?comando=CmdRedirecionar&url=/alu/index.jsp">Home</a>
        </li>
        <li>
            <a href="../ServletCentral?comando=CmdRedirecionar&url=/alu/realizar_prova.jsp">Realizar Prova</a>
        </li>
        <li>
            <a href="../ServletCentral?comando=CmdVisualizarProvas&id=<%= a.getUsuario().getId()%>">Visualizar Provas</a>
        </li>
        <li>
            <a href="../ServletCentral?comando=CmdRedirecionar&url=/cadastro.jsp">Novo Cadastro</a>
        </li>
        <li>
            <a href="../ServletCentral?comando=CmdEditarCadastro">Editar Cadastro</a>
        </li>
         <li>
            <a href="../ServletCentral?comando=CmdVisualizarSimulados">Visualizar Simulados</a>
        </li>
        <li>
            <a href="../ServletCentral?comando=CmdLogout">Sair</a>
        </li>
    </ul>
</div>