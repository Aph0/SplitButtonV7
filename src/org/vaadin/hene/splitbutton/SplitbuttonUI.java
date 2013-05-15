package org.vaadin.hene.splitbutton;

import com.vaadin.annotations.Theme;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 * Main UI class
 */
@SuppressWarnings("serial")
@Theme("splitbutton")
public class SplitbuttonUI extends UI {

    @Override
    protected void init(VaadinRequest request) {
        getPage().setTitle("SplitButton with Chameleon Theme Demo Application");

        VerticalLayout mainLayout = new VerticalLayout();
        mainLayout.setMargin(true);
        mainLayout.setSizeFull();
        setContent(mainLayout);

        HorizontalLayout layout = new HorizontalLayout();
        layout.setSizeFull();
        layout.setSpacing(true);

        final SplitButton splitButton1 = new SplitButton("Hey hey");
        splitButton1.setContent(new Label("Test"));

        splitButton1.setIcon(new ThemeResource("icons/emotion_smile.png"));
        splitButton1.setComponent(new Label("Test"));
        layout.addComponent(splitButton1);

        SplitButton splitButton2 = new SplitButton("300px wide");
        splitButton2.setWidth("300px");
        splitButton2.setIcon(new ThemeResource("icons/tick.png"));
        splitButton2.setComponent(new Label("Test"));
        layout.addComponent(splitButton2);

        SplitButton splitButton3 = new SplitButton("100% wide");
        splitButton3.setComponent(new Label("Test"));
        splitButton3.setWidth("100%");
        layout.addComponent(splitButton3);
        layout.setExpandRatio(splitButton3, 1);

        SplitButton splitButton4 = new SplitButton("lower corner");
        splitButton4.setComponent(new Label("Test"));

        Button b = new Button("set First button 200px wide");
        b.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(ClickEvent event) {
                splitButton1.setWidth("200px");

            }
        });

        mainLayout.addComponent(b);
        mainLayout.addComponent(splitButton4);
        mainLayout.addComponent(layout);
        mainLayout.setExpandRatio(layout, 1);

    }

}