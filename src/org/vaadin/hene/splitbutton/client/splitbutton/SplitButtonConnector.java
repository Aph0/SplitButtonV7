package org.vaadin.hene.splitbutton.client.splitbutton;

import java.util.ArrayList;
import java.util.List;

import org.vaadin.hene.popupbutton.widgetset.client.ui.PopupButtonState;
import org.vaadin.hene.splitbutton.SplitButton;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Widget;
import com.vaadin.client.ComponentConnector;
import com.vaadin.client.ConnectorHierarchyChangeEvent;
import com.vaadin.client.ConnectorHierarchyChangeEvent.ConnectorHierarchyChangeHandler;
import com.vaadin.client.HasComponentsConnector;
import com.vaadin.client.Util;
import com.vaadin.client.communication.StateChangeEvent;
import com.vaadin.client.ui.AbstractComponentConnector;
import com.vaadin.client.ui.SimpleManagedLayout;
import com.vaadin.shared.ui.Connect;

@Connect(SplitButton.class)
public class SplitButtonConnector extends AbstractComponentConnector implements
        HasComponentsConnector, ConnectorHierarchyChangeHandler,
        SimpleManagedLayout {

    private ComponentConnector button;
    private ComponentConnector popupButton;

    public SplitButtonConnector() {
        addConnectorHierarchyChangeHandler(this);
    }

    @Override
    protected void init() {
        // TODO Auto-generated method stub
        super.init();
    }

    @Override
    protected Widget createWidget() {
        return GWT.create(VSplitButton.class);
    }

    @Override
    public VSplitButton getWidget() {
        return (VSplitButton) super.getWidget();
    }

    @Override
    public SplitButtonState getState() {
        return (SplitButtonState) super.getState();
    }

    @Override
    public void onStateChanged(StateChangeEvent stateChangeEvent) {
        super.onStateChanged(stateChangeEvent);

    }

    @Override
    public void updateCaption(ComponentConnector connector) {
        // TODO Auto-generated method stub

    }

    @Override
    public List<ComponentConnector> getChildComponents() {
        return new ArrayList<ComponentConnector>() {
            {
                add(button);
                add(popupButton);
            }
        };
    }

    @Override
    public void setChildComponents(List<ComponentConnector> children) {
        button = children.get(0);
        popupButton = children.get(1);
        ((PopupButtonState) popupButton.getState()).popupPositionConnector = this;
    }

    @Override
    public HandlerRegistration addConnectorHierarchyChangeHandler(
            ConnectorHierarchyChangeHandler handler) {
        return ensureHandlerManager().addHandler(
                ConnectorHierarchyChangeEvent.TYPE, handler);
    }

    @Override
    public void onConnectorHierarchyChange(
            ConnectorHierarchyChangeEvent connectorHierarchyChangeEvent) {
        getWidget().setButtonWidgets(button.getWidget(),
                popupButton.getWidget());

    }

    @Override
    public void layout() {

        Widget popupButtonWidget = getWidget().getPopupButtonWidget();
        Widget buttonWidget = getWidget().getButtonWidget();
        String height = getState().height;

        if (height == null || height.equals("")) {
            buttonWidget.setHeight("");
            popupButtonWidget.setHeight("");
            int buttonHeight = Util.getRequiredHeight(buttonWidget);
            int popupButtonHeight = Util.getRequiredHeight(popupButtonWidget);
            if (buttonHeight > popupButtonHeight) {
                buttonWidget.setHeight(buttonHeight + "px");
                popupButtonWidget.setHeight(buttonHeight + "px");
            } else {
                buttonWidget.setHeight(popupButtonHeight + "px");
                popupButtonWidget.setHeight(popupButtonHeight + "px");
                // buttonsHeight = popupButtonHeight;
            }
        } else {
            buttonWidget.setHeight(height);
            popupButtonWidget.setHeight(height);
            // buttonsHeight = Integer.parseInt(height.substring(0,
            // height.length() - 2));
        }

    }

}
