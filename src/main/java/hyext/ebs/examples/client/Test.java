package hyext.ebs.examples.client;

/**
 * @description:
 * @author: 周世焕
 * @time: 2020-08-11 21:50
 */
import com.sun.jna.Native;
import com.sun.jna.win32.StdCallLibrary;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

public class Test {
    private Robot robot = null;//此处用robot去移动鼠标，可忽略
    public static final int CONTROL_PORT = 0x64;
    public static final int DATA_PORT = 0x60;

    public static final Map<String,Integer> map=new HashMap();

    public Test(){
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    static{
        map.put("0", KeyEvent.VK_0);
        map.put("1", KeyEvent.VK_1);
        map.put("2", KeyEvent.VK_2);
        map.put("3", KeyEvent.VK_3);
        map.put("4", KeyEvent.VK_4);
        map.put("5", KeyEvent.VK_5);
        map.put("6", KeyEvent.VK_6);
        map.put("7", KeyEvent.VK_7);
        map.put("8", KeyEvent.VK_8);
        map.put("9", KeyEvent.VK_9);
        map.put("a", KeyEvent.VK_A);
        map.put("b", KeyEvent.VK_B);
        map.put("c", KeyEvent.VK_C);
        map.put("d", KeyEvent.VK_D);
        map.put("e", KeyEvent.VK_E);
        map.put("f", KeyEvent.VK_F);
        map.put("g", KeyEvent.VK_G);
        map.put("h", KeyEvent.VK_H);
        map.put("i", KeyEvent.VK_I);
        map.put("j", KeyEvent.VK_J);
        map.put("k", KeyEvent.VK_K);
        map.put("l", KeyEvent.VK_L);
        map.put("m", KeyEvent.VK_M);
        map.put("n", KeyEvent.VK_N);
        map.put("o", KeyEvent.VK_O);
        map.put("p", KeyEvent.VK_P);
        map.put("q", KeyEvent.VK_Q);
        map.put("r", KeyEvent.VK_R);
        map.put("s", KeyEvent.VK_S);
        map.put("t", KeyEvent.VK_T);
        map.put("u", KeyEvent.VK_U);
        map.put("v", KeyEvent.VK_V);
        map.put("w", KeyEvent.VK_W);
        map.put("x", KeyEvent.VK_X);
        map.put("y", KeyEvent.VK_Y);
        map.put("z", KeyEvent.VK_Z);
        map.put("Tab", KeyEvent.VK_TAB);
        map.put("Space", KeyEvent.VK_SPACE);
        map.put("Shift", KeyEvent.VK_SHIFT);
        map.put("Cntl", KeyEvent.VK_CONTROL);
        map.put("Alt", KeyEvent.VK_ALT);
        map.put("F1",KeyEvent.VK_F1);
        map.put("F2",KeyEvent.VK_F2);
        map.put("F3",KeyEvent.VK_F3);
        map.put("F4",KeyEvent.VK_F4);
        map.put("F5",KeyEvent.VK_F5);
        map.put("F6",KeyEvent.VK_F6);
        map.put("F7",KeyEvent.VK_F7);
        map.put("F8",KeyEvent.VK_F8);
        map.put("F9",KeyEvent.VK_F9);
        map.put("F10",KeyEvent.VK_F10);
        map.put("F11",KeyEvent.VK_F11);
        map.put("F12",KeyEvent.VK_F12);

    }
    //使用User32库里面键位值转换
    public interface User32 extends StdCallLibrary {
        User32 Instance = (User32) Native.loadLibrary("User32",User32.class);
        int MapVirtualKeyA(int key, int type);
    }
    //此处是winIo使用关键
    public interface WinIo extends StdCallLibrary{
        WinIo Instance = (WinIo)Native.loadLibrary("WinIo64",WinIo.class);
        boolean InitializeWinIo();
        boolean GetPortVal(int portAddr, int pPortVal, int size);
        boolean SetPortVal(int portAddr, int portVal, int size) ;
        void ShutdownWinIo();
    }
    //将虚拟键位值转成扫描码
    public static int toScanCode(String key){
        try {
            return User32.Instance.MapVirtualKeyA(map.get(key).intValue(),0);
        } catch (Exception e) {
            return 0;
        }
    }


    public static void KBCWait4IBE() throws Exception{
        int val=0;
        do {
            if(!WinIo.Instance.GetPortVal(CONTROL_PORT,val, 1)){
                System.err.println("Cannot get the Port");
            }

        } while ((0x2&val)>0);
    }

    public static void KeyDown(int key) throws Exception{
        KBCWait4IBE();
        WinIo.Instance.SetPortVal(Test.CONTROL_PORT,0xD2,1);
        KBCWait4IBE();
        WinIo.Instance.SetPortVal(Test.DATA_PORT,key,1);
    }

    public static void KeyUp(int key) throws Exception{
        KBCWait4IBE();
        WinIo.Instance.SetPortVal(Test.CONTROL_PORT,0xD2,1);
        KBCWait4IBE();
        WinIo.Instance.SetPortVal(Test.DATA_PORT,(key|0x80),1);
    }

    public void mouseDemo() throws InterruptedException{
        robot.mouseMove(500, 290);
        Thread.sleep(650);
        robot.mousePress(KeyEvent.BUTTON1_MASK);
        Thread.sleep(150);
        robot.mouseRelease(KeyEvent.BUTTON1_MASK);
        Thread.sleep(999);
        System.out.println("鼠标点击完毕");
    }

    public static void main(String[] args) throws Exception{


        //System.setProperty("webdriver.ie.driver", "D:/IEDriverServer.exe");
        //WebDriver dr = new InternetExplorerDriver();
        // 打开网站
        //dr.get("https://www.hao123.com");
        // 获取源代码
        //System.out.println("--"+dr.getPageSource());

        //Selenium se = new Selenium();

        //se.mouseDemo();

        System.out.println("winIO64初始化是否成功："+WinIo.Instance.InitializeWinIo());//此处应该有判断，只有初始化成功才可继续往下走，否者直接终止
        Thread.sleep(1000);
        String s="helloworld";
        for (int i = 0; i < s.length(); i++) {
            KeyDown(toScanCode(""+s.charAt(i)));
            Thread.sleep(10);
            KeyUp(toScanCode(""+s.charAt(i)));
            Thread.sleep(200);
        }
        WinIo.Instance.ShutdownWinIo();


        // 关闭
        //System.out.println(dr.getPageSource());
        //Thread.sleep(20000);
        //dr.quit();
    }
}
