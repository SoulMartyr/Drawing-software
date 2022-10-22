package App;

import UI.MainUI;

/**
 * @author LiuJiayuan
 * @version 1.0
 */
public class Main {
    /**
     * 程序入口
     */

    public static void main(String[] args) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        }catch(Exception e){
            System.out.println(e);
        }

        MainUI UI = new MainUI();
    }
}
