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
package com.ub.ropalinda.controllers;

import com.ub.ropalinda.entities.PurchaseOrder;
import com.ub.ropalinda.models.ModelOrder;
import com.ub.ropalinda.models.PersistOrder;
import com.ub.ropalinda.utils.UtilsJWT;
import static com.ub.ropalinda.utils.UtilsService.error;
import static com.ub.ropalinda.utils.UtilsService.invalidToken;
import static com.ub.ropalinda.utils.UtilsService.warning;
import com.ub.ropalinda.utils.commons.Controller;
import com.ub.ropalinda.utils.commons.reponses.AccessDeniedException;
import com.ub.ropalinda.utils.commons.reponses.Response;
import com.ub.ropalinda.utils.validation.InvalidValueException;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

/**
 *
 * @author Ulises Beltrán Gómez - beltrangomezulises@gmail.com
 */
@Path("/orders")
public class ControllerOrder extends Controller<ModelOrder, PurchaseOrder, Integer> {

    public ControllerOrder() {
        super(new ModelOrder());
    }

    @POST
    @Path("/persist")
    public Response persistOrder(@HeaderParam("Authorization") String token, PersistOrder persistOrder){
        Response r = new Response();
        try {
            UtilsJWT.validate(token);                        
            r.setData(this.model.persistOrder(persistOrder));
        } catch (AccessDeniedException e) {
            invalidToken(r);
        } catch (InvalidValueException e) {
            warning(r, e.getMessage(), e.getMessage());
        } catch (Exception e) {
            error(r, e);
        }        
        return r;
    }
}
