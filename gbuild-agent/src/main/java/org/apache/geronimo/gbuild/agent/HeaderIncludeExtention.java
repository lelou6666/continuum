/**
 *
 * Copyright 2004 The Apache Software Foundation
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.apache.geronimo.gbuild.agent;

import org.codehaus.plexus.logging.AbstractLogEnabled;

import java.util.Map;
import java.util.Iterator;

/**
 * @version $Rev$ $Date$
 */
public class HeaderIncludeExtention extends AbstractLogEnabled implements BuildAgentExtention {

    /**
     * @plexus.configuration
     */
    private String prefix;


    public void preProcess(Map build) {
    }

    public void postProcess(Map build, Map results) {
        Iterator keys = build.keySet().iterator();

        while (keys.hasNext()) {

            String key = (String) keys.next();

            if (key.startsWith(prefix)){

                include(key, build, results);

            }
        }
    }

    private void include(String header, Map build, Map results) {
        Object value = build.get(header);

        String text = toText(value);

        getLogger().debug("adding "+header + " = " + text);

        Object old = results.put(header, value);

        if (old != null){

            getLogger().warn("replaced "+header + " = " + toText(old));

        }
    }

    private String toText(Object value) {
        String text = value.toString();

        int LIMIT = 50;
        if (text != null && text.length() > LIMIT) {
            text  = text.substring(0, LIMIT - 3) + "...";
        }
        return text;
    }
}
