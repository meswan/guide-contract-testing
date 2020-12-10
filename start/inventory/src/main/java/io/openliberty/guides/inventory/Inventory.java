// tag::copyright[]
/*******************************************************************************
 * Copyright (c) 2020 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
// end::copyright[]
package io.openliberty.guides.inventory;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;

public class Inventory {

    private final String url;

    public Inventory(String url) {
        this.url = url;
    }

    public String getEncoding() {
        Client client = ClientBuilder.newClient();
        Response response = client.target(url + "/system/properties/key/os.encoding").request().get();
        String result = response.readEntity(String.class);
        response.close();
        client.close();
        return result;
    }

    public String getEdition() {
        Client client = ClientBuilder.newClient();
        Response response = client.target(url + "/system/properties/key/wlp.user.dir.isDefault").request().get();
        String result = response.readEntity(String.class);
        response.close();
        client.close();
        return result;
    }

    public String getVersion() {
        Client client = ClientBuilder.newClient();
        Response response = client.target(url + "/system/properties/version").request().get();
        String result = response.readEntity(String.class);
        response.close();
        client.close();
        return result;
    }
}