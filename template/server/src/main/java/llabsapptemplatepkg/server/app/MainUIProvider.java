/*
 * Copyright 2015 Livotov Labs Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package llabsapptemplatepkg.server.app;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.vaadin.server.UIClassSelectionEvent;
import com.vaadin.server.UICreateEvent;
import com.vaadin.server.UIProvider;

public class MainUIProvider extends UIProvider
{

    @Inject
    private Injector injector;

    @Override
    public MainUI createInstance(UICreateEvent event)
    {
        return injector.getInstance(MainUI.class);
    }

    @Override
    public Class<MainUI> getUIClass(UIClassSelectionEvent event)
    {
        return MainUI.class;
    }
}
