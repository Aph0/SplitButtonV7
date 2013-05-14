package org.vaadin.hene.splitbutton.client.splitbutton;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;
import com.vaadin.client.Util;

public class VSplitButton extends Composite {

    public static final String CLASSNAME = "v-splitbutton";

    private final Panel panel;

    private Widget buttonWidget;
    private Widget popupButtonWidget;

    private boolean initDone = false;

    private int buttonWidth;
    private int popupButtonWidth;
    private int buttonsHeight;

    private String width;
    private String height;

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

        if (!initDone) {
            setButtonWidth();
            // setHeight();
        }

        initDone = true;

    }

    private void setButtonWidth() {
        if (width == null) {
            buttonWidget.setWidth("");
            popupButtonWidth = -1;
        } else {
            popupButtonWidth = Util.getRequiredWidth(popupButtonWidget
                    .getElement());

            buttonWidth = getOffsetWidth() - popupButtonWidth;
            if (buttonWidth < 0) {
                buttonWidth = 0;
            }
            buttonWidget.setWidth(buttonWidth + "px");
        }
    }

    @Override
    public void setWidth(String width) {
        if (width == null || "".equals(width)) {
            this.width = null;
        } else {
            width = removeDecimals(width);
            this.width = width;
        }

        Util.setWidthExcludingPaddingAndBorder(this, width, 0);

        if (initDone) {
            setButtonWidth();
        }
    }

    private void setHeight() {
        if (height == null) {
            buttonWidget.setHeight("");
            popupButtonWidget.setHeight("");
            int buttonHeight = Util.getRequiredHeight(buttonWidget);
            int popupButtonHeight = Util.getRequiredHeight(popupButtonWidget);
            if (buttonHeight > popupButtonHeight) {
                buttonWidget.setHeight(buttonHeight + "px");
                popupButtonWidget.setHeight(buttonHeight + "px");
                buttonsHeight = buttonHeight;
            } else {
                buttonWidget.setHeight(popupButtonHeight + "px");
                popupButtonWidget.setHeight(popupButtonHeight + "px");
                buttonsHeight = popupButtonHeight;
            }
        } else {
            buttonWidget.setHeight(height);
            popupButtonWidget.setHeight(height);
            buttonsHeight = Integer.parseInt(height.substring(0,
                    height.length() - 2));
        }
    }

    private String removeDecimals(String string) {
        if (string == null) {
            return null;
        }
        if (string.equals("")) {
            return "";
        }

        return string.replaceAll("\\..*\\d", "");
    }

    // @Override
    @Override
    public void setHeight(String height) {
        super.setHeight(height);
        if (height == null || "".equals(height)) {
            this.height = null;
        } else {
            height = removeDecimals(height);
            this.height = height;
        }
        if (initDone) {
            setHeight();
        }
    }

}