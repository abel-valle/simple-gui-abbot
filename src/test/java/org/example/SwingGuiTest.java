package org.example;

import abbot.finder.ComponentNotFoundException;
import abbot.finder.Matcher;
import abbot.finder.MultipleComponentsFoundException;
import abbot.finder.matchers.ClassMatcher;
import abbot.tester.ComponentTester;
import junit.extensions.abbot.ComponentTestFixture;
import junit.extensions.abbot.TestHelper;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.example.ui.controller.MainFrameController;
import org.example.ui.view.MainFrame;

import javax.swing.*;
import java.awt.*;

public class SwingGuiTest extends ComponentTestFixture {

    private ComponentTester tester;

    @BeforeTest
    protected void setUp() {
        tester = ComponentTester.getTester(MainFrame.class);
    }

    @Test
    public void testValidateText() {

        // The constructor create the components instances
        MainFrameController controller = new MainFrameController();
        // The components must be shown to control them
        controller.showMainFrameWindow();

        try {
            // Find the JTextArea instantiated
            JTextArea textArea = (JTextArea) getFinder().find(new ClassMatcher(JTextArea.class));

            // Find the JButton instantiated with the specified text expecting many buttons
            JButton button = (JButton) getFinder().find(new Matcher() {
                public boolean matches(Component c) {
                    // Add as much information as needed to distinguish the component
                    return c instanceof JButton && ((JButton) c).getText().equals("Say Welcome");
                }
            });

            for (int i = 0; i < 3; i++) {
                tester.actionDelay(1000);
                // Click on the obtained button
                tester.actionClick(button);
            }

            Assert.assertEquals(textArea.getText(), "Welcome IntelliJ IDEA Swing Creator\n" +
                    "Welcome IntelliJ IDEA Swing Creator\n" +
                    "Welcome IntelliJ IDEA Swing Creator\n");

            tester.actionDelay(5000);

        } catch (ComponentNotFoundException e) {
            e.printStackTrace();
        } catch (MultipleComponentsFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        TestHelper.runTests(args, SwingGuiTest.class);
    }
}