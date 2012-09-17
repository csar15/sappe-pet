/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.si.pet.sappe.comandos.alu;


import br.ufc.si.pet.sappe.entidades.Perfil;
import br.ufc.si.pet.sappe.entidades.Usuario;
import br.ufc.si.pet.sappe.interfaces.Comando;
import br.ufc.si.pet.sappe.service.PapelService;
import br.ufc.si.pet.sappe.service.PerfilService;
import br.ufc.si.pet.sappe.service.UsuarioService;
import br.ufc.si.pet.sappe.util.SendMail;
import br.ufc.si.pet.sappe.util.Util;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author gleyson
 */
public class CmdAdicionarAluno implements Comando {

    public String executa(HttpServletRequest request, HttpServletResponse response) {

        HttpSession hS = request.getSession(true);
        String login = request.getParameter("login");
        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        String rSenha = request.getParameter("rsenha");

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        Date data = Util.treatToDate(dateFormat.format(date));

        UsuarioService usuarioService = new UsuarioService();
        //verifica se o email fornecido ja está cadastrado no sistema
        Usuario usuario = usuarioService.getUsuarioByEmail(email);

        if (nome == null || nome.trim().isEmpty() || email == null || email.trim().isEmpty()
                || senha == null || senha.trim().isEmpty() || rSenha == null || rSenha.trim().isEmpty()) {
            hS.setAttribute("erro", "Preencha todos os campos obrigatórios (*).");
            return "/cadastro.jsp";
        } else if (!senha.trim().equals(rSenha)) {
            hS.setAttribute("erro", "A senha não confere com a sua confirmação.");
            return "/cadastro.jsp";
        } else if (usuario != null) {
            hS.setAttribute("erro", "Este email já está cadastrado.");
            return "/cadastro.jsp";
        } else {
            Usuario u = new Usuario();
            u.setLogin(login);
            u.setNome(nome);
            u.setEmail(email);
            u.setSenha(Util.criptografar(senha));
            u.setCodigo(Util.createRandomString(9));
            
            try{
                //insere o usário
                usuarioService.insertUsuario(u);

             //busca o usuário inserido pelo login e a senha

            u = usuarioService.getUsuarioByLoginSenha(u);


            //cria o perfil do usuário
            Perfil perfil = new Perfil();
            perfil.setUsuario(u);
            perfil.setPapel(new PapelService().getPapelById(1L));
            perfil.setDataCriacao(data);
            perfil.setAtivo(true);

            PerfilService pS = new PerfilService();
             //insere o perfil do usuário
                pS.insertPerfil(perfil);

                try {
                    //envia o email
                    SendMail.sendMail(perfil.getUsuario().getEmail(), "Conta Criada Com Sucesso.", "Oi " + perfil.getUsuario().getNome() + ", <br />"
                            + "Sua conta foi criada com sucesso,bem vindo ao sappe.<br /><br />" + "Para acessar sua conta click no Link abaixo.<br /><br />"
                            + "<a href=" + Util.getUrl(request) + "/sappe/index.jsp/>");

                } catch (AddressException ex) {
                    Logger.getLogger(CmdAdicionarAluno.class.getName()).log(Level.SEVERE, null, ex);
                    ex.printStackTrace();
                } catch (MessagingException ex) {
                    Logger.getLogger(CmdAdicionarAluno.class.getName()).log(Level.SEVERE, null, ex);
                    ex.printStackTrace();
                }catch(Exception e){
                    e.printStackTrace();
                }
                hS.setAttribute("sucesso", "Seu cadastro foi aceito,uma mensagem de confirmação foi enviado para o seu e-mail.");
                return "/cadastro.jsp";


            }catch(Exception e){
                e.printStackTrace();
                hS.setAttribute("erro", "Erro ao tentar cadastrar.");

            }
           
           
            
        }//fim do else


                hS.setAttribute("erro", "Erro ao tentar cadastrar.Não foi possível enviar o email");
                return "/cadastro.jsp";
    }//fim do método
}
