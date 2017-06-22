/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Acessorio;
import Model.Equipamento;
import Model.Marca;
import Model.OS;
import java.util.ArrayList;

/**
 *
 * @author andro
 */
public interface CadastroOSDAOInterface {
    public boolean insert(Equipamento equipamento, Marca marca, OS os, ArrayList<Acessorio> acessorios, String observacao) throws CadastroOSDAOException;
}
