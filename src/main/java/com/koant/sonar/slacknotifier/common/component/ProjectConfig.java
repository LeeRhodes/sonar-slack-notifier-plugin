package com.koant.sonar.slacknotifier.common.component;

import com.koant.sonar.slacknotifier.common.SlackNotifierProp;
import org.sonar.api.config.Settings;

import java.util.Objects;

/**
 * Created by ak on 17/10/16.
 * Modified by poznachowski
 */
public class ProjectConfig {
    private final String hook;
    private final String projectKey;
    private final boolean qgFailOnly;

    public ProjectConfig(String hook, String projectKey, boolean qgFailOnly) {
        this.hook = hook;
        this.projectKey = projectKey;
        this.qgFailOnly = qgFailOnly;
    }

    /**
     * Cloning constructor
     *
     * @param c
     */
    public ProjectConfig(ProjectConfig c) {
        this.hook = c.getHook();
        this.projectKey = c.getProjectKey();
        this.qgFailOnly = c.isQgFailOnly();
    }

    static ProjectConfig create(Settings settings, String configurationId) {
        String configurationPrefix = SlackNotifierProp.CONFIG.property() + "." + configurationId + ".";
        String hook = settings.getString(configurationPrefix + SlackNotifierProp.HOOK.property());
        String projectKey = settings.getString(configurationPrefix + SlackNotifierProp.PROJECT.property());
        boolean qgFailOnly = settings.getBoolean(configurationPrefix + SlackNotifierProp.QG_FAIL_ONLY.property());
        return new ProjectConfig(hook, projectKey, qgFailOnly);
    }

    public String getHook() {
        return hook;
    }

    public String getProjectKey() {
        return projectKey;
    }

    public boolean isQgFailOnly() {
        return qgFailOnly;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectConfig that = (ProjectConfig) o;
        return qgFailOnly == that.qgFailOnly &&
                Objects.equals(hook, that.hook) &&
                Objects.equals(projectKey, that.projectKey);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hook, projectKey, qgFailOnly);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ProjectConfig{");
        sb.append("hook='").append(hook).append('\'');
        sb.append(", projectKey='").append(projectKey).append('\'');
        sb.append(", qgFailOnly=").append(qgFailOnly);
        sb.append('}');
        return sb.toString();
    }
}
