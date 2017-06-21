/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.OS;

/**
 *
 * @author andro
 */
public interface OSDAOInterface {
    public boolean insert(OS os) throws OSDAOException;
}
