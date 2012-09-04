/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.si.pet.sappe.dao;

import br.ufc.si.pet.sappe.dao.config.PostGresMapConfig;
import br.ufc.si.pet.sappe.entidades.Questao;
import br.ufc.si.pet.sappe.entidades.Utility;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author gleyson
 */
public class QuestaoDAO {

    public ArrayList<Questao> getListQuestoes(Utility utility) throws SQLException {
        return (ArrayList<Questao>) PostGresMapConfig.getSqlMapClient().queryForList("getListQuestoes", utility);
    }

    public ArrayList<Questao> getAllAnosQuestoesByExame(Long id) throws SQLException {
        return (ArrayList<Questao>) PostGresMapConfig.getSqlMapClient().queryForList("getAllAnosQuestoesByExame", id);
    }

    public Questao getById(Long id) throws SQLException {
        return (Questao) PostGresMapConfig.getSqlMapClient().queryForObject("getQuestaoById", id);
    }

    public ArrayList<Questao> getListQuestoesByArea(Utility utility) throws SQLException {
        return (ArrayList<Questao>) PostGresMapConfig.getSqlMapClient().queryForList("getListQuestoesByArea", utility);
    }

    public ArrayList<Questao> getListQuestoesByAreaSimulado(Utility utility) throws SQLException {
        return (ArrayList<Questao>) PostGresMapConfig.getSqlMapClient().queryForList("getListQuestoesByAreaSimulado", utility);
    }

    public ArrayList<Questao> getListQuestoesByExame(Utility utility) throws SQLException {
        return (ArrayList<Questao>) PostGresMapConfig.getSqlMapClient().queryForList("getListQuestoesByExame", utility);
    }



     public ArrayList<Questao> getListQuestoesByAnoExame(String ano) throws SQLException {
        return (ArrayList<Questao>) PostGresMapConfig.getSqlMapClient().queryForList("getQuestoesByAnoExame", ano);
    }//fim do método

    public ArrayList<Questao> getAllListQuestoes() throws SQLException {
        return (ArrayList<Questao>) PostGresMapConfig.getSqlMapClient().queryForList("getAllQuestoes");
    }//fim do método

    public boolean deleteQuestao(long id){
        try{
            PostGresMapConfig.getSqlMapClient().delete("deleteQuestao",id);
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }//fim do método


    public boolean update(Questao questao){
        try{
            PostGresMapConfig.getSqlMapClient().update("updateQuestao", questao);
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }

    }//fim do método

}//fim da classe
