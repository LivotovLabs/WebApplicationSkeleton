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
import com.vaadin.annotations.PreserveOnRefresh;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.Widgetset;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;
import llabsapptemplatepkg.server.core.dao.LogsDao;

@Theme("app")
@PreserveOnRefresh
@Title("Web Skeleton WebApp")
@Widgetset(value = "llabsapptemplatepkg")
public class MainUI extends UI
{

    @Inject
    private LogsDao logsDao;

    public static MainUI getCurrent()
    {
        return (MainUI) UI.getCurrent();
    }

    protected void init(final VaadinRequest vaadinRequest)
    {
        setContent(new DashboardView());
    }

}