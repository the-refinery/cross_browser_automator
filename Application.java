 import java.awt.*;
 import java.awt.event.*;
 import java.io.*;
 import javax.swing.*;
 public class Application {
  public static void main(String[] arguments) throws AWTException, IOException {
   int i;
   int x = 0;
   GraphicsDevice[] screenDevices = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices();
   Screen[] screens = new Screen[screenDevices.length];
   DisplayMode currentDisplayMode;
   for (i = 0; i < screens.length; i++) {
    currentDisplayMode = screenDevices[i].getDisplayMode();
    screens[i] = new Screen(
     currentDisplayMode.getWidth(),
     currentDisplayMode.getHeight()
    );
   }
   String[] applications = new String[] {"GoogleChrome", "Firefox"};
   String[] applicationLoadedNames = new String[] {"Chrome", "Firefox"};
   Runtime runtime = Runtime.getRuntime();
   BrowserStatus status = new BrowserStatus(600, 300);
   for (i = 0; i < screens.length; i++) {
    runtime.exec("osascript ./coordinateMover.scpt " + applications[i] + " " + x + " 25 " + status.width + " " + status.height);
    x += screens[i].width;
    System.out.print(applications[i]);
    if (i < screens.length - 1) System.out.print(" | ");
   }
   System.out.print("\n");
   try {Thread.sleep(1000 * screens.length * 2);}
   catch (InterruptedException ie) {}
   ControlPanel panel = new ControlPanel(screens, status, applicationLoadedNames);
   Thread t = new Thread() {
    public void run() {
     while (true) {
      try {Thread.sleep(1000);}
      catch (InterruptedException ie) {}
     }
    }
   };
   t.start();
  }
 }
 class Operator {
  private Robot robot;
  private Screen[] screens;
  private Point initialCoordinates;
  private Point motionCoordinates;
  private BrowserStatus status;
  private String[] applications;
  private int i, last;
  public Operator(Robot robot, Screen[] screens, BrowserStatus status, String[] applications) {
   this.robot = robot;
   this.screens = screens;
   this.status = status;
   this.applications = applications;
   last = screens.length - 1;
   Point mouseLocation = MouseInfo.getPointerInfo().getLocation();
   initialCoordinates = new Point(mouseLocation.x, mouseLocation.y);
   motionCoordinates = new Point();
  }
  public JFrame createFrame(Point location, Dimension size, Component component, boolean visible) {
   JFrame frame = new JFrame();
   frame.setLocation(location.x, location.y);
   frame.setSize(size.width, size.height);
   if (component != null) frame.add(component);
   frame.setVisible(visible);
   return frame;
  }
  public void expandBrowsers(int horizontal, int vertical) {
   status.width += horizontal;
   status.height += vertical;
   try {
    Runtime runtime = Runtime.getRuntime();
    for (i = 0; i < screens.length; i++)
     runtime.exec("osascript ./resizer.scpt " + applications[i] + " " + status.width + " " + status.height);
   }
   catch (IOException ioe) {System.out.println(ioe.toString());}
  }
  public void scroll(int horizontal, int vertical) {
   try {
    status.horizontalScroll += horizontal;
    status.verticalScroll += vertical;
    Runtime runtime = Runtime.getRuntime();
    runtime.exec("osascript ./scroller.scpt " + status.horizontalScroll + " " + status.verticalScroll);
    motionCoordinates.x = 0;
    motionCoordinates.y = 0;
    initialCoordinates = MouseInfo.getPointerInfo().getLocation();
    for (i = 0; i < screens.length; i++) {
     if (applications[i] == "Firefox") {
      motionCoordinates.x += status.width / 2;
      motionCoordinates.y = status.height / 2;
      robot.mouseMove(motionCoordinates.x, motionCoordinates.y);
      robot.mousePress(InputEvent.BUTTON1_MASK);
      robot.mouseRelease(InputEvent.BUTTON1_MASK);
      robot.mousePress(InputEvent.BUTTON3_MASK);
      motionCoordinates.y -= vertical;
      robot.mouseMove(motionCoordinates.x, motionCoordinates.y);
      robot.mouseRelease(InputEvent.BUTTON3_MASK);
      robot.mouseMove(initialCoordinates.x, initialCoordinates.y);
      robot.mousePress(InputEvent.BUTTON1_MASK);
      robot.mouseRelease(InputEvent.BUTTON1_MASK);
      break;
     }
     motionCoordinates.x += screens[i].width;
    }
   }
   catch (IOException ioe) {}
  }
 }
 class Screen {
  public final int width, height;
  public Screen(int width, int height) {
   this.width = width;
   this.height = height;
  }
  public String toString() {return "String[width=" + width + ",height=" + height + "]";}
 }
 class ControlPanel extends JFrame implements KeyListener {
  private Operator operator;
  private Robot robot;
  private Screen[] screens;
  private BrowserStatus status;
  private String[] applications;
  public ControlPanel(Screen[] screens, BrowserStatus status, String[] applications) throws AWTException {
   setLocation(0, 500);
   setSize(100, 100);
   setVisible(true);
   setFocusable(true);
   robot = new Robot();
   this.screens = screens;
   this.status = status;
   this.applications = applications;
   operator = new Operator(robot, screens, status, applications);
   addKeyListener(this);
  }
  public void keyPressed(KeyEvent ke) {
   int key = ke.getKeyCode();
   if (key == KeyEvent.VK_J) operator.expandBrowsers(-50, 0);
   else if (key == KeyEvent.VK_L) operator.expandBrowsers(50, 0);
   else if (key == KeyEvent.VK_I) operator.expandBrowsers(0, -50);
   else if (key == KeyEvent.VK_K) operator.expandBrowsers(0, 50);
   else if (key == KeyEvent.VK_S) operator.scroll(0, 25);
   else if (key == KeyEvent.VK_W) operator.scroll(0, -25);
   else if (key == KeyEvent.VK_A) operator.scroll(-25, 0);
   else if (key == KeyEvent.VK_D) operator.scroll(25, 0);
  }
  public void keyReleased(KeyEvent ke) {}
  public void keyTyped(KeyEvent ke) {}
 }
 class BrowserStatus {
  public int width, height, verticalScroll, horizontalScroll;
  public BrowserStatus(int width, int height) {
   this.width = width;
   this.height = height;
   verticalScroll = 0;
   horizontalScroll = 0;
  }
 }