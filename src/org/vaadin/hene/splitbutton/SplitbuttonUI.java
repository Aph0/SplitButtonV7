package org.vaadin.hene.splitbutton;

import org.vaadin.hene.popupbutton.PopupButton;

import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.Reindeer;

/**
 * Main UI class
 */
@SuppressWarnings("serial")
public class SplitbuttonUI extends UI {
    /*
     * @Override protected void init(VaadinRequest request) { final
     * VerticalLayout layout = new VerticalLayout(); layout.setWidth("100%");
     * layout.setMargin(true); setContent(layout); SplitButton b = new
     * SplitButton("test width 300px"); b.setWidth("300px");
     * b.addStyleName(SplitButton.STYLE_CHAMELEON); SplitButton b2 = new
     * SplitButton("test width 100%"); b2.setWidth("100%");
     * b2.addStyleName(SplitButton.STYLE_CHAMELEON); SplitButton b3 = new
     * SplitButton("test undef width height"); b3.setSizeUndefined();
     * SplitButton b4 = new SplitButton("Not setting anything"); VerticalLayout
     * vl = new VerticalLayout(); vl.setHeight("100px"); vl.setWidth("100px");
     * vl.addComponent(new Label("Test")); b.setContent(vl); b2.setContent(new
     * Label("hey")); b3.setContent(new Button("Button")); b3.setIcon(new
     * ThemeResource("icons/emotion_smile.png"));
     * b3.setStyleName(ChameleonTheme.BUTTON_ICON_ON_TOP);
     * b3.addStyleName(SplitButton.STYLE_CHAMELEON); b4.setContent(new
     * Label("nothing")); b4.setIcon(new
     * ThemeResource("icons/emotion_smile.png"));
     * b4.setStyleName(ChameleonTheme.BUTTON_ICON_ON_TOP);
     * b4.addStyleName(SplitButton.STYLE_CHAMELEON);
     * b4.addPopupVisibilityListener(new
     * SplitButton.SplitButtonPopupVisibilityListener() {
     * 
     * public void splitButtonPopupVisibilityChange(
     * SplitButtonPopupVisibilityEvent event) {
     * System.out.println(event.isPopupVisible());
     * 
     * } }); layout.addComponent(b); layout.addComponent(b2);
     * layout.addComponent(b3); HorizontalLayout hl = new HorizontalLayout();
     * hl.addComponent(b4); hl.addComponent(new Button("Only a button"));
     * layout.addComponent(hl);
     * 
     * }
     */

    @Override
    protected void init(VaadinRequest request) {
        getPage().setTitle("SplitButton with Chameleon Theme Demo Application");

        VerticalLayout mainLayout = new VerticalLayout();
        mainLayout.setMargin(true);
        mainLayout.setSizeFull();
        setContent(mainLayout);

        HorizontalLayout layout = new HorizontalLayout();
        layout.setSpacing(true);
        mainLayout.addComponent(layout);
        mainLayout.setExpandRatio(layout, 1);

        SplitButton splitButton1 = new SplitButton("Hey hey");
        splitButton1.setContent(new Label("HEY"));
        mainLayout.addComponent(splitButton1);
        if (true) {
            return;
        }
        splitButton1.setIcon(new ThemeResource("icons/emotion_smile.png"));
        splitButton1.setComponent(createSplitButtonPopupContent1());
        // splitButton1.setStyleName(ChameleonTheme.BUTTON_ICON_ON_TOP);
        // splitButton1.addStyleName(SplitButton.STYLE_CHAMELEON);
        layout.addComponent(splitButton1);

        SplitButton splitButton2 = new SplitButton("Accept");
        splitButton2.setIcon(new ThemeResource("icons/tick.png"));
        // splitButton2.addStyleName(SplitButton.STYLE_CHAMELEON);
        splitButton2.setComponent(createSplitButtonPopupContent2());
        layout.addComponent(splitButton2);

        SplitButton splitButton3 = new SplitButton("Caption");
        // splitButton3.addStyleName(SplitButton.STYLE_CHAMELEON);
        splitButton3.setComponent(createSplitButtonPopupContent3());
        layout.addComponent(splitButton3);

        mainLayout.addComponent(createIconsInfoLabel());

    }

    private Layout createSplitButtonPopupContent1() {
        GridLayout layout = new GridLayout(3, 3);
        layout.setSpacing(true);
        layout.setSizeUndefined();

        layout.addComponent(createButton(null, "icons/emotion_evilgrin.png"));
        layout.addComponent(createButton(null, "icons/emotion_grin.png"));
        layout.addComponent(createButton(null, "icons/emotion_happy.png"));
        layout.addComponent(createButton(null, "icons/emotion_suprised.png"));
        layout.addComponent(createButton(null, "icons/emotion_tongue.png"));
        layout.addComponent(createButton(null, "icons/emotion_unhappy.png"));
        layout.addComponent(createButton(null, "icons/emotion_waii.png"));
        layout.addComponent(createButton(null, "icons/emotion_wink.png"));

        return layout;
    }

    private Layout createSplitButtonPopupContent2() {
        HorizontalLayout layout = new HorizontalLayout();
        layout.setWidth("100px");
        layout.addComponent(createButton("Decline", "icons/cross.png"));

        return layout;
    }

    private Layout createSplitButtonPopupContent3() {
        VerticalLayout layout = new VerticalLayout();
        layout.setSpacing(true);
        layout.setWidth("250px");

        Label label = new Label(
                "Popup can contain any Vaadin Component or ComponentContainer.");
        layout.addComponent(label);

        TextField tf = new TextField("TextField");
        layout.addComponent(tf);
        Button button = new Button("Button");
        layout.addComponent(button);

        return layout;
    }

    private Button createButton(String caption, String icon) {
        Button button = new Button(caption, new ClickListener() {
            public void buttonClick(ClickEvent event) {
                Component c = event.getButton().getParent().getParent();
                if (c instanceof PopupButton) {
                    ((PopupButton) c).setPopupVisible(false);
                }
            }
        });
        button.setStyleName(Reindeer.BUTTON_LINK);
        button.setIcon(new ThemeResource(icon));
        return button;
    }

    private Label createIconsInfoLabel() {
        Label label = new Label(
                "Icons from <a href=\"http://www.fatcow.com/free-icons\" target=\"_parent\">FatCow.com</a>",
                Label.CONTENT_XHTML);
        label.setSizeUndefined();
        return label;
    }
}