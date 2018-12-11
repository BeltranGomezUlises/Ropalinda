/*
 * Copyright (C) 2018 Ulises Beltrán Gómez - beltrangomezulises@gmail.com
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package com.ub.ropalinda.models;

import com.ub.ropalinda.entities.Address;
import com.ub.ropalinda.entities.Customer;
import com.ub.ropalinda.utils.commons.Model;
import com.ub.ropalinda.utils.commons.reponses.UniqueException;
import com.ub.ropalinda.utils.validation.InvalidValueException;
import javax.persistence.EntityManager;

/**
 *
 * @author Ulises Beltrán Gómez - beltrangomezulises@gmail.com
 */
public class ModelAddress extends Model<Address, Integer> {

    public ModelAddress() {
        super(Address.class);
    }

    @Override
    public Address persist(Address t) throws UniqueException, InvalidValueException {
        EntityManager em = this.createEm();
        t.setCustomer(em.find(Customer.class, t.getCustomer().getMail()));
        Address ad = super.persist(t); //To change body of generated methods, choose Tools | Templates.
        em.close();
        return ad;
    }
    
    

}
