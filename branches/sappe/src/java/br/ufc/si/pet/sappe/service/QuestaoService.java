/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.si.pet.sappe.service;

import br.ufc.si.pet.sappe.dao.QuestaoDAO;
import br.ufc.si.pet.sappe.entidades.Questao;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Gleyson
 */
public class QuestaoService {

    private QuestaoDAO questaoDAO;

    /**
     *
     */
    public QuestaoService() {
        questaoDAO = new QuestaoDAO();
    }

    public ArrayList<Questao> getListQuestoes(Long id) {
        try {
            ArrayList<Questao> questaos = questaoDAO.getListQuestoes(id);
            return questaos;
        } catch (SQLException ex) {
            Logger.getLogger(QuestaoService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    /**
     *
     * @return
     */
    public ArrayList<Questao> getListQuestoesByArea(Long id) {
        try {
            ArrayList<Questao> questaos = questaoDAO.getListQuestoesByArea(id);
            return questaos;
        } catch (SQLException ex) {
            Logger.getLogger(QuestaoService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}