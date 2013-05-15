package org.vaadin.hene.splitbutton.client.splitbutton;

import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;

public class VSplitButton extends Composite {

    public static final String CLASSNAME = "v-splitbutton";

    private final Panel panel;

    private Widget buttonWidget;
    private Widget popupButtonWidget;

    private Integer buttonHeight;

    public VSplitButton() {

        panel = new FlowPanel();
        initWidget(panel);

        setStyleName(CLASSNAME);
    }

    public Widget getButtonWidget() {
        return buttonWidget;
    }

    public Widget getPopupButtonWidget() {
        return popupButtonWidget;
    }

    public void setButtonWidgets(Widget buttonWidget, Widget popupButtonWidget) {
        this.buttonWidget = buttonWidget;
        this.popupButtonWidget = popupButtonWidget;

        panel.add(buttonWidget);
        panel.add(popupButtonWidget);

    }

    public Element getPopupButtonElement() {
        return popupButtonWidget.getElement();
    }

    public void setButtonHeight(int buttonHeight) {
        this.buttonHeight = buttonHeight;
        adjustHeight();
    }

    public void adjustHeight() {
        if (buttonHeight != null) {

            popupButtonWidget.setHeight("100%");
            panel.setHeight(buttonHeight + "px");
            //
            // if (buttonHeight < popupButtonHeight) {
            // buttonWidget.setHeight(popupButtonHeight + "px");
            // popupButtonWidget.setHeight(popupButtonHeight + "px");
            // } else {
            // buttonWidget.setHeight(buttonHeight + "px");
            // popupButtonWidget.setHeight(buttonHeight + "px");
            // }
        }

    }

}