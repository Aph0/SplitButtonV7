package org.vaadin.hene.splitbutton.client.splitbutton;

import java.util.List;

import org.vaadin.hene.popupbutton.widgetset.client.ui.PopupButtonState;
import org.vaadin.hene.splitbutton.SplitButton;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.LoadEvent;
import com.google.gwt.event.dom.client.LoadHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Widget;
import com.vaadin.client.ComponentConnector;
import com.vaadin.client.ConnectorHierarchyChangeEvent;
import com.vaadin.client.communication.StateChangeEvent;
import com.vaadin.client.ui.AbstractComponentContainerConnector;
import com.vaadin.client.ui.layout.ElementResizeEvent;
import com.vaadin.client.ui.layout.ElementResizeListener;
import com.vaadin.shared.ui.Connect;

@Connect(SplitButton.class)
public class SplitButtonConnector extends AbstractComponentContainerConnector {

    private ComponentConnector button;
    private ComponentConnector popupButton;

    private final ElementResizeListener popupButtonResizeListener = new ElementResizeListener() {
        public void onElementResize(ElementResizeEvent e) {
            int buttonWidth = getLayoutManager().getOuterWidth(e.getElement());

            if (getState().width == null || getState().width.equals("")) {
                // undef
            } else if (getState().width.endsWith("px")
                    || getState().width.endsWith("%")) {
                getWidget().getButtonWidget().getElement().getStyle()
                        .setPaddingRight(buttonWidth, Unit.PX);
                getWidget().getButtonWidget().getElement().getStyle()
                        .setMarginRight(-buttonWidth, Unit.PX);
            }

        }
    };

    private final ElementResizeListener leftButtonResizeListener = new ElementResizeListener() {
        public void onElementResize(ElementResizeEvent e) {
            int buttonHeight = getLayoutManager()
                    .getOuterHeight(e.getElement());
            getWidget().setButtonHeight(buttonHeight);
        }
    };

    private HandlerRegistration loadHandler;

    public SplitButtonConnector() {

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
    public void onConnectorHierarchyChange(
            ConnectorHierarchyChangeEvent connectorHierarchyChangeEvent) {
        List<ComponentConnector> children = getChildComponents();
        if (children.size() < 2) {
            return;
        }

        button = children.get(0);
        popupButton = children.get(1);
        ((PopupButtonState) popupButton.getState()).popupPositionConnector = this;

        if (loadHandler != null) {
            loadHandler.removeHandler();
        }
        loadHandler = button.getWidget().addHandler(new LoadHandler() {

            @Override
            public void onLoad(LoadEvent event) {
                // this in case there is an icon set
                getLayoutManager().setNeedsMeasure(button);
                getLayoutManager().setNeedsMeasure(SplitButtonConnector.this);
                getLayoutManager().setNeedsMeasure(popupButton);
            }

        }, LoadEvent.getType());

        getWidget().setButtonWidgets(button.getWidget(),
                popupButton.getWidget());
        getLayoutManager()
                .addElementResizeListener(popupButton.getWidget().getElement(),
                        popupButtonResizeListener);
        getLayoutManager().addElementResizeListener(
                button.getWidget().getElement(), leftButtonResizeListener);

    }

    @Override
    public void onUnregister() {

        getLayoutManager()
                .removeElementResizeListener(
                        popupButton.getWidget().getElement(),
                        popupButtonResizeListener);
        getLayoutManager().removeElementResizeListener(
                button.getWidget().getElement(), leftButtonResizeListener);
        if (loadHandler != null) {
            loadHandler.removeHandler();
        }
    }

}
