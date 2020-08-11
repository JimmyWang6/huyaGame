package hyext.ebs.examples.websocket;

import java.awt.*;

public class StringHandler {
    Robot robot = new Robot();

    public StringHandler() throws AWTException {
    }

    public void handle(String str) {
        if (str.length() >= 3) {

        } else {
            switch (str) {
                case "上":
                    press(0x26, 250);break;
                case "下":
                    press(0x28, 250);break;
                case "左":
                    press(0x25,250);break;
                case "右":
                    press(0x27,250);break;
                case "上上":
                    press(0x26, 500);break;
                case "下下":
                    press(0x28, 500);break;
                case "左左":
                    press(0x25,500);break;
                case "右右":
                    press(0x27,500);break;
                case "A":
                case "a":
                    press(0x90,250);break;
                case "B":
                case "b":
                    press(0x80,250);break;
                case "e":
                case "E":
                    press(0xD,250);break;
            }
        }
    }

    public void press(int key, int delay) {
        robot.delay(100);
        robot.keyPress(key);
        robot.delay(delay);
        robot.keyRelease(key);
    }

    public static void main(String[] args) throws AWTException {
        StringHandler stringHandler = new StringHandler();
        stringHandler.handle("上");
        stringHandler.handle("下");
        stringHandler.handle("左");
        stringHandler.handle("右");
        stringHandler.handle("上上");
        stringHandler.handle("下下");
        stringHandler.handle("左左");
        stringHandler.handle("右右");
    }
//    public static int handle(char c) {
//
//    }
}
