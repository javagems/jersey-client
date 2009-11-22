/*
 *
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * 
 * Copyright 1997-2007 Sun Microsystems, Inc. All rights reserved.
 * 
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common Development
 * and Distribution License("CDDL") (collectively, the "License").  You
 * may not use this file except in compliance with the License. You can obtain
 * a copy of the License at https://jersey.dev.java.net/CDDL+GPL.html
 * or jersey/legal/LICENSE.txt.  See the License for the specific
 * language governing permissions and limitations under the License.
 * 
 * When distributing the software, include this License Header Notice in each
 * file and include the License file at jersey/legal/LICENSE.txt.
 * Sun designates this particular file as subject to the "Classpath" exception
 * as provided by Sun in the GPL Version 2 section of the License file that
 * accompanied this code.  If applicable, add the following below the License
 * Header, with the fields enclosed by brackets [] replaced by your own
 * identifying information: "Portions Copyrighted [year]
 * [name of copyright owner]"
 * 
 * Contributor(s):
 * 
 * If you wish your version of this file to be governed by only the CDDL or
 * only the GPL Version 2, indicate your decision by adding "[Contributor]
 * elects to include this software in this distribution under the [CDDL or GPL
 * Version 2] license."  If you don't indicate a single choice of license, a
 * recipient has the option to distribute your version of this file under
 * either the CDDL, the GPL Version 2 or to extend the choice of license to
 * its licensees as provided above.  However, if you add GPL Version 2 code
 * and therefore, elected the GPL Version 2 license, then the option applies
 * only if the new code is made subject to such option by the copyright
 * holder.
 */

package com.sun.jersey.api.client.filter;

import com.sun.jersey.api.client.ClientHandler;


/**
 * An abstract class providing support for registering and managing a chain
 * of {@link ClientFilter} instances.
 * <p>
 * A {@link ClientFilter} instance MUST be occur at most once in any
 * {@link Filterable} instance, otherwise unexpected results may occur.
 * If it is necessary to add the same type of {@link ClientFilter} more than once
 * to the same {@link Filterable} instance or to more than one {@link Filterable}
 * instance then a new instance of that {@link ClientFilter} MUST be added.
 * 
 * @author Paul.Sandoz@Sun.Com
 */
public abstract class Filterable {
    private final ClientHandler root;
    
    private ClientHandler head;
   
    /**
     * Construct with a root client handler.
     *
     * @param root the root handler to handle the request and return a response.
     */
    protected Filterable(ClientHandler root) {
        this.root = this.head = root;
    }
    
    /**
     * Construct from an existing filterable instance.
     * 
     * @param that the filter to copy.
     */
    protected Filterable(Filterable that) {
        this.root = that.root;
        this.head = that.head;
    }
    
    /**
     * Add a filter to the filter chain.
     * 
     * @param f the filter to add.
     */
    public void addFilter(ClientFilter f) {
        f.setNext(head);
        this.head = f;
    }

    /**
     * Remove a filter from the chain.
     * 
     * @param f the filter to remove.
     */
    public void removeFilter(ClientFilter f) {
        // No filters added
        if (head == root) {
            return;
        }

        // Filter is at the head
        if (head == f) {
            head = f.getNext();
            return;
        }

        ClientFilter e = (ClientFilter)head;
        while (e.getNext() != f) {
            if (e.getNext() == root) return;
            
            e = (ClientFilter)e.getNext();
        }
        
        e.setNext(f.getNext());
    }

    /**
     * Check if a filter is present in the chain.
     *
     * @param f the filter to remove.
     * @return return true if the filter is present, otherwise false.
     */
    public boolean isFilterPreset(ClientFilter f) {
        if (head == root) {
            return false;
        }

        if (head == f) {
            return true;
        }

        ClientFilter e = (ClientFilter)head;
        while (e.getNext() != f) {
            if (e.getNext() == root) return false;

            e = (ClientFilter)e.getNext();
        }

        return true;
    }

    /**
     * Remove all filters from the filter chain.
     */
    public void removeAllFilters() {
        this.head = root;
    }
    
    /**
     * Get the head client handler of the filter chain.
     * 
     * @return the head client handler of the filter chain.
     */
    protected ClientHandler getHeadHandler() {
        return head;
    }
}