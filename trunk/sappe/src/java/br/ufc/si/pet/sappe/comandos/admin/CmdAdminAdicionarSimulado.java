/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.si.pet.sappe.comandos.admin;

import br.ufc.si.pet.sappe.entidades.Questao;
import br.ufc.si.pet.sappe.entidades.QuestaoSimulado;
import br.ufc.si.pet.sappe.entidades.Simulado;
import br.ufc.si.pet.sappe.entidades.Utility;
import br.ufc.si.pet.sappe.interfaces.Comando;
import br.ufc.si.pet.sappe.service.QuestaoService;
import br.ufc.si.pet.sappe.service.QuestaoSimuladoService;
import br.ufc.si.pet.sappe.service.SimuladoService;
import com.ibatis.sqlmap.client.SqlMapException;
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
public class CmdAdminAdicionarSimulado implements Comando {

    public String executa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException, SQLException, FileUploadException, Exception {
        HttpSession session = request.getSession(true);

        try {
            String nome = request.getParameter("nome");
            Long eid = Long.parseLong(request.getParameter("exame"));
            String data = request.getParameter("data");
            String hi = request.getParameter("hi");
            String ht = request.getParameter("ht");
            Integer nq = Integer.parseInt(request.getParameter("nq"));
            if (nome == null || nome.isEmpty() || eid == null || eid.equals(0L) || data == null || data.isEmpty() || hi == null || hi.isEmpty() || ht == null || ht.isEmpty()) {
                session.setAttribute("erro", "Preencha todos os campos (*).");
                return "/admin/admin_adicionar_simulado.jsp";
            } else {
                Utility utility = new Utility();
                utility.setIde(eid);
                utility.setQtdq(nq);
                QuestaoService qS = new QuestaoService();
                List<Questao> subListaDeQuestoes = qS.getListQuestoesByExame(utility);
                QuestaoSimuladoService questaoSimuladoService = new QuestaoSimuladoService();
                if (nq <= subListaDeQuestoes.size()) {
                    Simulado simulado = new Simulado();
                    simulado.setNome(nome);
                    simulado.setData(data);
                    simulado.setHoraini(hi);
                    simulado.setHorafim(ht);
                    simulado.setNum_questao(nq);
                    simulado.setExame_id(eid);
                    SimuladoService simuladoService = new SimuladoService();
                    simuladoService.inserir(simulado);
                    for (Questao q : subListaDeQuestoes) {
                        QuestaoSimulado questaoSimulado = new QuestaoSimulado();
                        questaoSimulado.setSimulado_id(simuladoService.proxId());
                        questaoSimulado.setQuestao_id(q.getId());
                        questaoSimuladoService.inserir(questaoSimulado);
                    }
                    session.setAttribute("sucesso", "Simulado cadastrado como sucesso.");
                } else {
                    session.setAttribute("erro", "No momento temos apenas " + subListaDeQuestoes.size() + " questões disponíveis para este exame.");
                }
            }
        } catch (SqlMapException e) {
            e.printStackTrace();
            session.setAttribute("erro", e.getMessage());
        } catch (NullPointerException npe) {
            session.setAttribute("erro", npe.getMessage());
            npe.printStackTrace();
        } catch (Exception e) {
            session.setAttribute("erro", e.getMessage());
            e.printStackTrace();
        }
        return "/admin/admin_adicionar_simulado.jsp";
    }
}
