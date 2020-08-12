package hyext.ebs.examples.client;

import javafx.scene.input.KeyCode;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.*;
import java.net.Socket;

/**
 * @description:
 * @author: 周世焕
 * @time: 2020-08-11 18:19
 */
public class Client {
    public static void main(String[] argv) throws IOException, AWTException, InterruptedException {
        Socket socket = new Socket("localhost",9888);
        InputStream inputStream = socket.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String command = null;
        Robot robot = new Robot();
        while (true){
            command = bufferedReader.readLine();
            switch (command){
                case "w":
                case "W":
                    System.out.println("press w");
                    press(robot,KeyEvent.VK_W,200);
                    break;
                case "s":
                case "S":
                    System.out.println("press s");
                    press(robot,KeyEvent.VK_S,200);
                    break;
                case "a":
                case "A":
                    System.out.println("press a");
                    press(robot,KeyEvent.VK_A,200);
                    break;
                case "d":
                case "D":
                    System.out.println("press d");
                    press(robot,KeyEvent.VK_D,200);
                    break;
                case "z":
                case "Z":
                    System.out.println("press z");
                    press(robot,KeyEvent.VK_Z,200);
                    break;
                case "P":
                case "p":
                    System.out.println("press space");
                    press(robot,KeyEvent.VK_SPACE,200);
                    break;
                case "e":
                case "E":
                    System.out.println("press Enter");
                    press(robot,KeyEvent.VK_ENTER,200);
                    break;
                default:
            }
            Thread.sleep(100);
        }
    }

    private static void press(Robot robot,int code,int millis){
        try {
            robot.keyPress(code);
            Thread.sleep(millis);
            robot.keyRelease(code);
        }catch (Exception e){
            e.printStackTrace();
            robot.keyRelease(code);
        }
    }
}
