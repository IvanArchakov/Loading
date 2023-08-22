import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Main {
    public static void main(String[] args) {
        String wayZip = "C://Java//Games//savegames//zip1.zip";
        String wayFolder = "C://Java//Games//savegames";
        String player = "C://Java//Games//savegames//save2.dat";
        openZip(wayZip, wayFolder);
        System.out.println(openProgress(player));
    }

    public static void openZip(String zip, String folder) {
        try (ZipInputStream zis = new ZipInputStream(new FileInputStream(zip))) {
            ZipEntry zipEntry;
            String name;
            while ((zipEntry = zis.getNextEntry()) != null) {
                name = zipEntry.getName();
                FileOutputStream fos = new FileOutputStream(name);
                for (int c = zis.read(); c != -1; c = zis.read()) {
                    fos.write(c);
                }
                fos.flush();
                zis.closeEntry();
                fos.close();
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static GameProgress openProgress(String save) {
        GameProgress gameProgress = null;
        try (FileInputStream fis = new FileInputStream(save); ObjectInputStream ois = new ObjectInputStream(fis)) {
            gameProgress = (GameProgress) ois.readObject();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return gameProgress;
    }
}
