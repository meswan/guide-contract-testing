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
package it.io.openliberty.sample.system;

import au.com.dius.pact.provider.junit5.HttpTestTarget;
import au.com.dius.pact.provider.junit5.PactVerificationContext;
import au.com.dius.pact.provider.junit5.PactVerificationInvocationContextProvider;
import au.com.dius.pact.provider.junitsupport.Consumer;
import au.com.dius.pact.provider.junitsupport.Provider;
import au.com.dius.pact.provider.junitsupport.State;
import au.com.dius.pact.provider.junitsupport.loader.PactBroker;
import au.com.dius.pact.provider.junitsupport.loader.VersionSelector;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;

@Provider("System")
@Consumer("Inventory")
@PactBroker(
        host = "localhost",
        port = "9292",
        consumerVersionSelectors = {
                @VersionSelector(tag = "open-liberty-pact")
        })
public class SystemBrokerIT {
  @TestTemplate
  @ExtendWith(PactVerificationInvocationContextProvider.class)
  void pactVerificationTestTemplate(PactVerificationContext context) {
    context.verifyInteraction();
  }

  @BeforeAll
  static void enablePublishingPact() {
    System.setProperty("pact.verifier.publishResults", "true");
  }

  @BeforeEach
  void before(PactVerificationContext context) {
    int port = Integer.parseInt(System.getProperty("http.port"));
    context.setTarget(new HttpTestTarget("localhost", port));
  }

  @State("os.encoding is UTF-8")
  public void validEncoding() {
  }

  @State("Default directory is true")
  public void validEdition() {
  }

  @State("version is 1.1")
  public void validVersion() {
  }
}
