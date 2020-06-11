package com.neotys.advanced.action.apache.kafka.connect;

import com.google.common.base.Optional;
import com.neotys.action.argument.Arguments;
import com.neotys.action.argument.Option;
import com.neotys.extensions.action.Action;
import com.neotys.extensions.action.ActionParameter;
import com.neotys.extensions.action.engine.ActionEngine;

import javax.swing.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public final class KafkaConnectAction implements Action {

    private static final String BUNDLE_NAME = "com.neotys.advanced.action.apache.kafka.connect.bundle";
    private static final String DISPLAY_NAME = ResourceBundle.getBundle(BUNDLE_NAME, Locale.getDefault()).getString("displayName");
    private static final String DISPLAY_PATH = ResourceBundle.getBundle(BUNDLE_NAME, Locale.getDefault()).getString("displayPath");

    private static final ImageIcon DISPLAY_ICON;

    static {
        URL iconURL = KafkaConnectAction.class.getResource(ResourceBundle.getBundle(BUNDLE_NAME, Locale.getDefault()).getString("iconPath"));
        if (iconURL != null) {
            DISPLAY_ICON = new ImageIcon(iconURL);
        } else {
            DISPLAY_ICON = null;
        }
    }

    @Override
    public String getType() {
        return "KafkaConnect";
    }

    @Override
    public List<ActionParameter> getDefaultActionParameters() {
        final List<ActionParameter> parameters = new ArrayList<ActionParameter>();

        KafkaConnectOption[] arrayOfConnectOption;
        int j = (arrayOfConnectOption = KafkaConnectOption.values()).length;

        for (int i = 0; i < j; i++) {
            KafkaConnectOption option = arrayOfConnectOption[i];
            if (Option.AppearsByDefault.True.equals(option.getAppearsByDefault())) {
                parameters.add(new ActionParameter(option.getName(), option.getDefaultValue(), option.getType()));
            }
        }
        return parameters;
    }

    @Override
    public Class<? extends ActionEngine> getEngineClass() {
        return KafkaConnectActionEngine.class;
    }

    @Override
    public Icon getIcon() {
        return DISPLAY_ICON;
    }

    @Override
    public String getDescription() {
        StringBuilder description = new StringBuilder();

        description.append("Connect to a Kafka broker.\nMore information can be found here : http://kafka.apache.org/0102/documentation/#producerconfigs\n\n" + Arguments.getArgumentDescriptions(KafkaConnectOption.values()));
        return description.toString();
    }

    @Override
    public String getDisplayName() {
        return DISPLAY_NAME;
    }

    @Override
    public String getDisplayPath() {
        return DISPLAY_PATH;
    }

    @Override
    public Optional<String> getMinimumNeoLoadVersion() {
        return Optional.absent();
    }

    @Override
    public Optional<String> getMaximumNeoLoadVersion() {
        return Optional.absent();
    }
}