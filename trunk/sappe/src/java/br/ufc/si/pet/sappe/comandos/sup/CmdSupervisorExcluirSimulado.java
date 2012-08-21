/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.si.pet.sappe.comandos.sup;

import br.ufc.si.pet.sappe.entidades.Simulado;
import br.ufc.si.pet.sappe.interfaces.Comando;
import br.ufc.si.pet.sappe.service.SimuladoService;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.FileUploadException;

/**
 *
 * @author gleyson
 */
public class CmdSupervisorExcluirSimulado implements Comando {

    public String executa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException, SQLException, FileUploadException, Exception {
        HttpSession session = request.getSession(true);
        try {
            Long id = Long.parseLong(request.getParameter("id"));
            SimuladoService simuladoService = new SimuladoService();
            simuladoService.deleteSimulado(id);
            List<Simulado> simulados = simuladoService.getAllSimulados();
            session.setAttribute("sup_simulados", simulados);
            session.setAttribute("sucesso", "Simulado excluido com sucesso.");
            return "/sup/sup_visualizar_simulados.jsp";
        } catch (NumberFormatException nfe) {
            session.setAttribute("erro", nfe.getMessage());
            nfe.printStackTrace();
            return "/sup/sup_visualizar_simulados.jsp";
        } catch (Exception e) {
            session.setAttribute("erro", e.getMessage());
            e.printStackTrace();
            return "/sup/sup_visualizar_simulados.jsp";
        }
    }
}
